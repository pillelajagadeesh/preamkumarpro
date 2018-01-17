package appraamlabs.controllers;

import java.util.List;

import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.DBObject;

import appraamlabs.models.DriverProfile;
import appraamlabs.models.Obd;
import appraamlabs.models.VehicleProfile;
import appraamlabs.service.DriverBeaconService;
import appraamlabs.service.DriverProfileService;
import appraamlabs.service.ObdService;
import appraamlabs.service.VehicleProfileService;
import appraamlabs.service.VehicleSerfService;
import appraamlabs.service.dtos.ResponseDTO;
import appraamlabs.service.gcmNotification.GCMNotificationService;
import appraamlabs.utils.constants.MessageConstants;

@RestController
@RequestMapping("/gcm")
public class GCMController {

	@Autowired
	private DriverProfileService driverProfileService;
	
	@Autowired
	private GCMNotificationService gcmNotificationService;
	
	@Autowired
	private ObdService obdService;
	
	@Autowired
	private Environment env;
	
	private static final Logger logger = LoggerFactory.getLogger(GCMController.class);
	
	@RequestMapping("/pushNotification/{tokenId}")
	@GET
	@Path("/pushNotification/tokenId")
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody ResponseDTO sendGCMNotification(@PathVariable("tokenId") String tokenId){
		
		List<Obd> obds = obdService.findAll();
		
		if(obds.isEmpty()){
			return new ResponseDTO(500, "Not obds exists");
		}else{
			for(Obd obd: obds){
				int vehicleStatus = Integer.parseInt(obd.getVEHICLE_STATUS());
			   if(vehicleStatus >= 40){
				   gcmNotificationService.sendGCMNotification(tokenId, "Vehicle is moving over speed of 40");
			   }else if(vehicleStatus < 40 && vehicleStatus >=20){
				   gcmNotificationService.sendGCMNotification(tokenId, "Vehicle is moving with optimum speed of 20 and above");
			   }else{
				   gcmNotificationService.sendGCMNotification(tokenId, "Vehicle is moving with low speed of below 20");
			   }
			}
		}
		return new ResponseDTO(200, "GCM notification pushed");
	}
	
	@RequestMapping("/getAll")
	@GET
	@Path("/getAll")
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody ResponseDTO getAll(){
		
		logger.debug("Request to get all obds");
		
		//List<Obd> obds = obdService.findAll();
		Iterable<DBObject> obds = obdService.aggregateBasedOnLatestDate();
		
		for(DBObject dbObject : obds){
			String serfId= String.valueOf(dbObject.get("_id"));
			String speed = String.valueOf(dbObject.get("speed"));
			String status = String.valueOf(dbObject.get("status"));
			int statusval = Integer.parseInt(status);
			
			Optional<DriverProfile> optDriverProfile = driverProfileService.getByDriverId(String.valueOf(dbObject.get("driverId")));
			
			if(optDriverProfile.isPresent()){
				logger.debug("Driver exists with driver Name : "+optDriverProfile.get().getDriverFirstName());
			}
			
			//Optional<VehicleProfile> optionalVehicleProfile = vehicleProfileService.findVehicleProfileBySerfId(serfId);
			   if(statusval > 3){
				   gcmNotificationService.sendGCMNotification(env.getProperty(MessageConstants.GCM_TOKEN),
						   "Vehicle is driving over speed of "+speed+" by driver id : "+String.valueOf(dbObject.get("driverId")));
			   }else if(statusval == 1){
				   gcmNotificationService.sendGCMNotification(env.getProperty(MessageConstants.GCM_TOKEN),
						   "Vehicle is driving speed of "+speed+" by driver id : "+String.valueOf(dbObject.get("driverId")));
			   }else if(statusval == 0){
				   gcmNotificationService.sendGCMNotification(env.getProperty(MessageConstants.GCM_TOKEN),
						   "Vehicle is stoped by driver id : "+String.valueOf(dbObject.get("driverId")));
			   }
			}
		return new ResponseDTO(200, "GCM notification pushed", obds);
	}
}

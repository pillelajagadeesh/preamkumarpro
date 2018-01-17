package appraamlabs.controllers;

import java.util.ArrayList;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
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

import appraamlabs.models.Beacon;
import appraamlabs.models.Configuration;
import appraamlabs.models.DriverProfile;
import appraamlabs.models.Obd;
import appraamlabs.models.SerfsBoard;
import appraamlabs.models.Trips;
import appraamlabs.models.VehicleProfile;
import appraamlabs.models.configuration.ConfigurationStatus;
import appraamlabs.service.BeaconService;
import appraamlabs.service.ConfigurationService;
import appraamlabs.service.DriverProfileService;
import appraamlabs.service.ObdService;
import appraamlabs.service.SerfsBoardService;
import appraamlabs.service.TripsService;
import appraamlabs.service.VehicleProfileService;
import appraamlabs.service.dtos.BeaconHawkeyeResponseDTO;
import appraamlabs.service.dtos.ResponseDTO;
import appraamlabs.utils.constants.MessageConstants;
import appraamlabs.utils.enums.BeaconType;

@RestController
@RequestMapping("/hawkeye")
public class HawkEyeController {

	@Autowired
	Environment env;
	
	@Autowired
	private ObdService obdService;
	
	@Autowired
	private TripsService tripsService;
	
	@Autowired
	private SerfsBoardService serfsBoardService;
	
	@Autowired
	private VehicleProfileService vehicleProfileService;
	
	@Autowired
	private BeaconService beaconService;
	
	@Autowired
	private DriverProfileService driverProfileService;
	
	@Autowired
	private ConfigurationService configurationService;

	private static final Logger logger = LoggerFactory.getLogger(HawkEyeController.class);
	
	  @RequestMapping("/getAll")
	  @GET
	  @Path("/getAll")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO getAllConfigurations() throws NoSuchElementException{
		
		  logger.debug("Request to access obd data in hawkeye");
	    List<Obd> obds = null;
	    
	    try{
	    	obds = obdService.findAll();
	    }catch(Exception ex) {
	    	logger.error("Error while accessing obd data with message {}", ex.getMessage());
		      return new ResponseDTO(500, ex.getMessage());
		    }
	    return new ResponseDTO(200, "Configuration's data", obds);
	  }
	  
	  @RequestMapping("/getAllTrips")
	  @GET
	  @Path("/getAllTrips")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO getAllTripsData() throws NoSuchElementException{
		  logger.debug("Request to access all trips");
		  Iterable<DBObject> dbObjects =  null;

		    try{
		    	dbObjects =  tripsService.getLatestTripsData();
		    }catch(Exception ex) {
		    	logger.error("Error while accessing trips with message {}", ex.getMessage());
			      return new ResponseDTO(500, ex.getMessage());
			    }
		return new ResponseDTO(200, "Trips data", dbObjects);
	  }
	  
	  @RequestMapping("/getAllWaypointBeacons")
	  @GET
	  @Path("/getAllWaypointBeacons")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO getAllWaypointBeacons() throws NoSuchElementException{
		  
		  logger.debug("Request to access all waypoint beacons in hawkeye");
		  List<Beacon> beacons =  null;
		  List<Configuration> optionalConfiguration = configurationService.findAll();
		  List<BeaconHawkeyeResponseDTO> beaconHawkeyes = new ArrayList<BeaconHawkeyeResponseDTO>();
		    try{
		    	//beacons =  beaconService.getAllBeaconsByType(BeaconType.WPBEACON);
		    	beacons =  beaconService.findAllWpBeaconsForHawkeye(BeaconType.DRIVERBEACON);
		    	for(Beacon beacon : beacons){
		    	String lat = null;
		    	String lng = null;
			  	if(!beacon.getUuid().equals("NULL") && !beacon.getUuid().equals(null) && beacon.getUuid().length() == 32 ){
	    	    String latLng = this.splitLatLongs(beacon.getUuid(), optionalConfiguration.get(0));
	    	    String[] splitLatLng = latLng.split("-");
	    	    lat = splitLatLng[0];
	    	    lng = splitLatLng[1];
	    	    }
	    	    BeaconHawkeyeResponseDTO beaconHawkeye = new BeaconHawkeyeResponseDTO();
	    	    beaconHawkeye.setMacId(beacon.getMacId());
	    	    beaconHawkeye.setUuid(beacon.getUuid());
	    	    beaconHawkeye.setTxPower(beacon.getTxPower());
	    	    beaconHawkeye.setType(beacon.getType());
	    	    beaconHawkeye.setLat(lat);
	    	    beaconHawkeye.setLng(lng);
	    	    beaconHawkeye.setMajor(beacon.getMajor());
	    	    beaconHawkeye.setMinor(beacon.getMinor());
	    	    beaconHawkeye.setBeaconPeriod(beacon.getBeaconPeriod());
	    	    beaconHawkeye.setStatus(beacon.getStatus());
	    	    beaconHawkeye.setCreatedDate(beacon.getCreatedDate());
	    	    beaconHawkeye.setUpdatedDate(beacon.getUpdatedDate());
	    	    beaconHawkeyes.add(beaconHawkeye);
		    	}
		    }catch(Exception ex) {
		    	logger.error("Error while accessing all waypoint beacons with message {}", ex.getMessage());
			      return new ResponseDTO(500, ex.getMessage());
			    }
		return new ResponseDTO(200, "Trips data", beaconHawkeyes);
	  }
	  
	  @RequestMapping("/getAllObdStatus")
	  @GET
	  @Path("/getAllObdStatus")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO getAllObdStatusData() throws NoSuchElementException{
		  
		  logger.debug("Request to access all obd status data in hawkeye");
		  Iterable<DBObject> dbObjects =  null;
		  
		  List<DBObject> obdDBObjects = new ArrayList<DBObject>();
		  
		    try{
		    	dbObjects =  tripsService.getLatestTripsStatus();
		    	for(DBObject dbObject : dbObjects){
		    		Optional<Trips> optTrips = tripsService.findTripsByUniqueIDAndDeviceTime(String.valueOf(dbObject.get("_id")), String.valueOf(dbObject.get("lastTripData")));
		    		Iterable<DBObject> obdObjects = tripsService.getLatestObdStatus(String.valueOf(dbObject.get("_id")));
		    				    		
		    		//DBObject finalDBObj = dbObject;
		    		//String latitude = String.valueOf(dbObject.get("latitude"));
		    		String latitude = optTrips.get().getLatitiude();
		    		String longitude = optTrips.get().getLongitude();
		    		//String longitude = String.valueOf(dbObject.get("longitude"));
		    	    //String uuid = String.valueOf(dbObject.get("uuid"));
		    		String uuid = optTrips.get().getUuId();
		    		
		    		Optional<SerfsBoard> optionalSerfBoard = serfsBoardService.findOneByDeviceName(String.valueOf(dbObject.get("_id")));
		    		
		    		if(optionalSerfBoard.isPresent()){
		    		for(DBObject finalDBObj : obdObjects){
			    		logger.debug("DATA OBJ {}", finalDBObj);
			    		
			    		Optional<Obd> optionalobd = obdService.findObdBySerfIdAndTime(String.valueOf(finalDBObj.get("_id")), String.valueOf(finalDBObj.get("lastTripData")));
		    	    //obdDBObjects.add(dbObject);
		    	    //finalDBObj = obdDbObjects.iterator().next();
		    			VehicleProfile vehicleProfile = this.getVehicleProfileBySerfName(String.valueOf(finalDBObj.get("_id")));
		    			if(vehicleProfile!=null){
		  		    List<Configuration> optionalConfiguration = configurationService.findAll();
		  		    finalDBObj.put("status", optionalobd.get().getVEHICLE_STATUS());
		  		    finalDBObj.put("speed", optionalobd.get().getVEHICLE_SPEED());
		    	    finalDBObj.put("configuration", this.getConfigurationStatusByCode(String.valueOf(finalDBObj.get("status")), optionalConfiguration, uuid, String.valueOf(finalDBObj.get("speed"))));
		    	    finalDBObj.put("vehicleprofile", vehicleProfile);
		    	    finalDBObj.put("driverprofile", this.getDriverProfileByDriverId(optTrips.get().getDriver()));
		    	    finalDBObj.put("trips", optTrips.get());
		    	    String lat = null;
		    	    String lng = null;
		  		  if(!uuid.equals("NULL") && !uuid.equals(null) && uuid.length() == 32){
		    	    String latLng = this.splitLatLongs(uuid, optionalConfiguration.get(0));
		    	    String[] splitLatLng = latLng.split("-");
		    	    try{
		    	    double lattitude = Double.parseDouble(splitLatLng[0]);
					double lonngitude = Double.parseDouble(splitLatLng[1]);
               	    lat = splitLatLng[0];
  	                lng = splitLatLng[1];
  	                if(lattitude == 0.0 && lonngitude == 0.0){
  	               	    lat = env.getProperty(MessageConstants.HAWKEYE_LATITUDE);
  	  	                lng = env.getProperty(MessageConstants.HAWKEYE_LONGITUDE);
  	                }
		    	    }catch(Exception ex){
		    	    	logger.error("Error with message {}", ex.getMessage());
			  			  lat = latitude;
			  			  lng = longitude;
		    	    }
		  		  }else if(latitude.equals("NULL") && longitude.equals("NULL") || Double.parseDouble(latitude) == 0.0 && Double.parseDouble(longitude) == 0.0){
	               	    lat = env.getProperty(MessageConstants.HAWKEYE_LATITUDE);
  	  	                lng = env.getProperty(MessageConstants.HAWKEYE_LONGITUDE);
		  		  }else{
		  			  lat = latitude;
		  			  lng = longitude;
		  		  }
		    	    finalDBObj.put("lat", lat);
		    	    finalDBObj.put("lng", lng);
		    	    
		    		obdDBObjects.add(finalDBObj);
		    		//dbObject.put("data", finalDBObj);
		    	    //dbObject.put("dbobject", finalDBObj);
		    			}
		    		}
		    		}
		    		
		    		//obdDBObjects.add(dbObject);
		    	}
		    }catch(Exception ex) {
		    	logger.error("Error while accessing obd data in hawkeye with message {}", ex.getMessage());
			      return new ResponseDTO(500, ex.getMessage());
			    }
		return new ResponseDTO(200, "Trips data", obdDBObjects);
	  }
	  
	  @RequestMapping("/getDriverAndVehicleDetails/{serfName}/{driverId}")
	  @GET
	  @Path("/getDriverAndVehicleDetails/{serfName}/{driverId}")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO getDriverAndVehicleDetails(@PathVariable("serfName") String serfName, @PathVariable("driverId") String driverId) throws NoSuchElementException{
		  logger.debug("Request to access driver and vehicle details by serfname {} and driverId {}", serfName, driverId);
		  Map<String,Object> driverVehicleMap = new HashMap<String,Object>(); 
		  
		    try{
		    	Optional<VehicleProfile> optVehicleProfile = vehicleProfileService.findVehicleProfileBySerfName(serfName);
		    	Optional<DriverProfile> optDriverProfile = driverProfileService.getByDriverId(driverId);
		    	
		    	if(optVehicleProfile.isPresent()){
		    	driverVehicleMap.put("vehicle", optVehicleProfile.get());
		    	}
		    	if(optDriverProfile.isPresent()){
		    	driverVehicleMap.put("driver", optDriverProfile.get());
		    	}
		    	
		    }catch(Exception ex) {
		    	logger.debug("Error while accessing driver vehicle details with message {}", ex.getMessage());
			      return new ResponseDTO(500, ex.getMessage());
			    }
		return new ResponseDTO(200, "Vehicle and Drivers data", driverVehicleMap);
	  }
	  
	  private String splitLatLongs(String uuid, Configuration configuration){
		  //String word = "onetwotwoone"
				  //int length = uuid.length();
		          String lat = uuid.substring(configuration.getMarkerposition().getLatitude().getFrom()-1, configuration.getMarkerposition().getLatitude().getTo());
				  //String lat = uuid.substring(16, 24);
		          String lng = uuid.substring(configuration.getMarkerposition().getLongitude().getFrom()-1, configuration.getMarkerposition().getLongitude().getTo());
				  //String lng = uuid.substring(24, 32);
				  
				  String lat1 = lat.substring(0, 2);
				  String lat2 = lat.substring(2, 6);
				  
				  String lng1 = lng.substring(0, 2);
				  String lng2 = lng.substring(2, 6);
				  
				  return lat1+"."+lat2+"-"+lng1+"."+lng2;
	  }
	  
	  private ConfigurationStatus getConfigurationStatusByCode(String code, List<Configuration> optionalConfiguration, String uuid, String speed){
		  
		  if(uuid.charAt(0) == 'b' && speed.equals("0") || uuid.charAt(0) == 'B' && speed.equals("0")){
			  for (ConfigurationStatus configurationStatus : optionalConfiguration.get(0).getConfigurationStatus()) {
					if(configurationStatus.getCode().equals("0")){
						return configurationStatus;
					}/*else if(configurationStatus.getCode().equals("-1") && speed.equals("-1")){//&& configurationStatus.getFromRange().equals(speed) && configurationStatus.getToRange().equals(speed)
						return configurationStatus;
					}*/
				}
		  }else if(uuid.charAt(0) == 'c' && speed.equals("0") || uuid.charAt(0) == 'C' && speed.equals("0")){
			  for (ConfigurationStatus configurationStatus : optionalConfiguration.get(0).getConfigurationStatus()) {
					if(configurationStatus.getCode().equals("7")){
						return configurationStatus;
					}
				}
		  }else if(uuid.charAt(0) == 'f' && speed.equals("0") || uuid.charAt(0) == 'F' && speed.equals("0")){
			  for (ConfigurationStatus configurationStatus : optionalConfiguration.get(0).getConfigurationStatus()) {
					if(configurationStatus.getCode().equals("6")){
						return configurationStatus;
					}
				}			  
		  }else if(uuid.charAt(0) == 'a' && speed.equals("0") || uuid.charAt(0) == 'A' && speed.equals("0")){
			  for (ConfigurationStatus configurationStatus : optionalConfiguration.get(0).getConfigurationStatus()) {
					if(configurationStatus.getCode().equals("5")){
						return configurationStatus;
					}
				}
		  }else if(speed.equals("-1")){
			  for (ConfigurationStatus configurationStatus : optionalConfiguration.get(0).getConfigurationStatus()) {
				  if(configurationStatus.getCode().equals("-1")){ /*&& configurationStatus.getFromRange().equals(speed) && configurationStatus.getToRange().equals(speed)*/
						return configurationStatus;
					}
				}
		  }else{
		  for (ConfigurationStatus configurationStatus : optionalConfiguration.get(0).getConfigurationStatus()) {
			if(configurationStatus.getCode().equals(code)){
				return configurationStatus;
			}
		}
		  }
		return null;
	  }
	  
	  private VehicleProfile getVehicleProfileBySerfName(String serfName){
		  
		  if(!serfName.equals("NULL") || !serfName.equals(null)){
		  Optional<VehicleProfile> optVehicle = vehicleProfileService.findVehicleProfileBySerfName(serfName);
		  if(optVehicle.isPresent()){
		  return optVehicle.get();
		  }
		  }
		return null;
	  }
	  
	  private DriverProfile getDriverProfileByDriverId(String driverId){
		  
		  if(!driverId.equals("NULL") || !driverId.equals(null)){
		 Optional<DriverProfile> optDriverProfile =  driverProfileService.getByDriverId(driverId);
		 if(optDriverProfile.isPresent()){
		return optDriverProfile.get();
		 }
		  }
		  return null;
	  }
}

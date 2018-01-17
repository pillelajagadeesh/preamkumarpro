package appraamlabs.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import appraamlabs.models.Beacon;
import appraamlabs.models.DriverBeacon;
import appraamlabs.models.DriverProfile;
import appraamlabs.service.BeaconService;
import appraamlabs.service.DriverBeaconService;
import appraamlabs.service.DriverProfileService;
import appraamlabs.service.dtos.DriverBeaconDTO;
import appraamlabs.service.dtos.ResponseDTO;
import appraamlabs.utils.enums.Status;

@RestController
@RequestMapping("/driverBeacon")
public class DriverBeaconController {

	@Autowired
	private DriverBeaconService driverBeaconService;
	
	@Autowired
	private DriverProfileService driverProfileService;
	
	@Autowired
	private BeaconService beaconService;
	
	private static Logger logger = LoggerFactory.getLogger(DriverProfileController.class);
		
	@RequestMapping("/save")
	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody ResponseDTO create(@RequestBody DriverBeaconDTO driverBeaconDTO) {
		 logger.debug("Request to register driver becon mapping  with driver's emp id {}",driverBeaconDTO.getEmpId());
		
		Optional<DriverProfile> optionalDriverProfile = driverProfileService.getByEmpId(driverBeaconDTO.getEmpId());
		
		if(!optionalDriverProfile.isPresent()){
			// logger.error("Driver exists with employee id {}",optionalDriverProfile.get().getEmpId());
			return new ResponseDTO(400, "No driver id exists with emp id "+driverBeaconDTO.getEmpId());
		}
		
/*		Optional<Beacon> optionalBeacon = beaconService.findBeaconByUuid(driverBeaconDTO.getBeaconID());
		
		if(!optionalBeacon.isPresent()){
			return new ResponseDTO(400, "No Beacon id exists with id "+driverBeaconDTO.getBeaconID());
		}*/
		
		//Optional<DriverBeacon> optdriverBeacon = driverBeaconService.findDriverBeaconByEmpIdAndBeaconId(driverBeaconDTO.getEmpID(), driverBeaconDTO.getBeaconID());
/*		Optional<DriverBeacon> optdriverBeacon = driverBeaconService.findById(driverBeaconDTO.getId());*/
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();

		if(optionalDriverProfile.isPresent()){
			DriverProfile driverProfile = optionalDriverProfile.get();
			driverProfile.setDriverId(driverBeaconDTO.getDriverId());
			driverProfile.setUpdatedDate(formatter.format(today));
			driverProfileService.saveDriverProfile(driverProfile);
			return new ResponseDTO(200, "Driver Beacon mapped successfully");
		}else{
			  logger.error("No driver exists with with employee Id{}", driverBeaconDTO.getEmpId());
			return new ResponseDTO(400, "No driver exists with employee Id : "+driverBeaconDTO.getEmpId());
		}
	}
	
	  @RequestMapping("/getAll")
	  @GET
	  @Path("/getAll")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO getAllBeacons() throws NoSuchElementException{
		  logger.debug("Request to get all driver beacon mappings");
		
	    List<DriverBeacon> driverBeacons = null;
	    
	    try{
	    	driverBeacons = driverBeaconService.findAll();
/*	    	Collections.sort (driverBeacons,
                    new Comparator<DriverBeacon> ()
                    { public int compare ( DriverBeacon a,  DriverBeacon d)
                          { return (d.getUpdateDate().compareTo(a.getUpdateDate())); }});*/
	    }catch(Exception ex) {
	    	logger.error("Error with message {}",ex.getMessage());
		      return new ResponseDTO(500, ex.getMessage());
		    }
	    return new ResponseDTO(200, "DriverBeacon's data", driverBeacons);
	  }
	
	
	
	@RequestMapping("/get-by-driverId/{driverId}")
	  @GET
	  @Path("/get-by-driverId/{driverId}")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO getByDriverId(@PathVariable("driverId") String driverId) throws NoSuchElementException{
		logger.debug("Request to get driverbeaons  with driver id {}",driverId);
	    Optional<DriverBeacon> optionalDriverBeacon = driverBeaconService.findBydriverId(driverId);
	    
	    if(optionalDriverBeacon.isPresent()){
	    return new ResponseDTO(200, "Beacon data", optionalDriverBeacon.get());
	    }else{
	    	  logger.error("No driver exists with with employee Id{}", optionalDriverBeacon.get().getBeaconID());
	    	return new ResponseDTO(404, "No driverbeacon exists");
	    }
	    
	  }
	
	  @RequestMapping("/get-by-beaconId/{beaconId}")
	  @GET
	  @Path("/get-by-beaconId/{beaconId}")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO getByBeaconId(@PathVariable("beaconId") String beaconId) throws NoSuchElementException{
		  logger.debug("Request to get driverbeaons  with beacon id {}",beaconId);
	    Optional<DriverBeacon> optionalDriverBeacon = driverBeaconService.findBeaconById(beaconId);
	    
	    if(optionalDriverBeacon.isPresent()){
	    return new ResponseDTO(200, "Beacon data", optionalDriverBeacon.get());
	    }else{
	    	 logger.error("No driver exists with with employee Id{}", optionalDriverBeacon.get().getBeaconID());
	    	return new ResponseDTO(404, "No driverbeacon exists");
	    }
	    
	  }
	
	  @RequestMapping("/getAllUnmappedDriverBeacons")
	  @GET
	  @Path("/getAllUnmappedDriverBeacons")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO getAllUnmappedDriverBeacons() throws NoSuchElementException{
		  logger.debug("Request to get all unmapped driver beacons");
		
	    List<Beacon> beacons = null;
	    List<DriverProfile> driverProfiles = null;
	    
	    List<DriverProfile> allDriverProfiles = driverProfileService.getAllDriverProfiles();
	    //List<DriverBeacon> listDriverBeacons = driverBeaconService.findAll();
	    driverProfiles = driverProfileService.findEmptyDriverIds();
	    
	    List<String> beaconIds = this.getArrayOfBeaconIdsInDriverBeacon(allDriverProfiles);
	    //List<String> empIds = this.getArrayOfempIdsInDriverBeacon(listDriverBeacons);
	    
	    Map<String, Object> unmappedDriverBeacons = new HashMap<String, Object>();
	    try{
	    	beacons = beaconService.findUnmappedBeaconsByUuid(beaconIds);
	    	//driverProfiles = driverProfileService.findUnmappedDriverProfilesByEmpId(empIds);
	    	
	    	/*unmappedDriverBeacons.put("beacons", beacons);
	    	unmappedDriverBeacons.put("driverProfiles", driverProfiles);*/
	    	unmappedDriverBeacons.put("driverProfiles", driverProfiles);
	    	unmappedDriverBeacons.put("beacons", beacons);
	    }catch(Exception ex) {
	    	logger.error("Error with message {}",ex.getMessage());
		      return new ResponseDTO(500, ex.getMessage());
		    }
	    return new ResponseDTO(200, "Unmapped DriverBeacon's data", unmappedDriverBeacons);
	  }
	  
	  @RequestMapping("/update/driverbeacon/{id}")
	  @PUT
	  @Path("/update/driverbeacon/{id}")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO updateDriverBeacon(@RequestBody DriverBeaconDTO driverBeaconDTO, @PathVariable("id") String id) {
		  logger.debug("Request to get driverbeaons  with beacon id {}",id);
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
          Date today = Calendar.getInstance().getTime();

		  Optional<DriverProfile> optionalDriverProfile = driverProfileService.getByEmpId(driverBeaconDTO.getEmpId());
			
			if(!optionalDriverProfile.isPresent()){
				 logger.error("No driver id exists with with employee Id{}", driverBeaconDTO.getEmpId());
				return new ResponseDTO(400, "No driver id exists with emp id "+driverBeaconDTO.getEmpId());
			}
			
			/*Optional<Beacon> optionalBeacon = beaconService.findBeaconByUuid(driverBeaconDTO.getBeaconID());
			
			if(!optionalBeacon.isPresent()){
				return new ResponseDTO(400, "No Beacon id exists with id "+driverBeaconDTO.getBeaconID());
			}*/
			
		  Optional<DriverBeacon> existingOplDriverBeacon = driverBeaconService.findById(id);
		  
		  if(existingOplDriverBeacon.isPresent()){
			  DriverBeacon existingDriverBeacon = existingOplDriverBeacon.get();
			  existingDriverBeacon.setStatus(Status.INACTIVE);
			  existingDriverBeacon.setUpdateDate(formatter.format(today));
			  driverBeaconService.save(existingDriverBeacon);
			  
			  DriverBeacon newDriverBeacon = new DriverBeacon(driverBeaconDTO.getMine(), driverBeaconDTO.getEmpId(), driverBeaconDTO.getDriverFirstName(),
						driverBeaconDTO.getDriverLastName(), driverBeaconDTO.getEmpType(), driverBeaconDTO.getContractAgency(),
						driverBeaconDTO.getDriverId());
			  newDriverBeacon.setCreatedDate(existingDriverBeacon.getCreatedDate());
			  newDriverBeacon.setUpdateDate(formatter.format(today));
			  newDriverBeacon.setStatus(Status.ACTIVE);
			  
			  driverBeaconService.save(newDriverBeacon);
				
				//Update driver profile driverid with beacon id
				DriverProfile driverProfile = optionalDriverProfile.get();
				driverProfile.setDriverId(newDriverBeacon.getBeaconID());
				driverProfileService.saveDriverProfile(driverProfile);
				
			  return new ResponseDTO(200, "Driver Beacon updated successfully", existingDriverBeacon);
			  
		  }else{
			 
			  return new ResponseDTO(500, "Invalid request");
		  }
	}
	
	  @RequestMapping("/delete/{id}")
	  @DELETE
	  @Path("/delete/(id}")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO delete(@PathVariable("id") String id) {
		  logger.debug("Request to delete driver  with employee id {}",id);
		  
		  Optional<DriverBeacon> existingOplDriverBeacon = driverBeaconService.findById(id);
		  
		  if(existingOplDriverBeacon.isPresent()){
			  DriverBeacon existingDriverBeacon = existingOplDriverBeacon.get();
			  existingDriverBeacon.setStatus(Status.INACTIVE);
			  driverBeaconService.save(existingDriverBeacon);
			  return new ResponseDTO(Integer.parseInt(HttpStatus.OK.toString()), "DriverBeacon deleted Successfully");
		  }
		  else{
			  logger.error("Driver not exists with id {}",existingOplDriverBeacon.get().getEmpId());
		        return new ResponseDTO(500, "Invalid request");
		  }
	  }
	  
	  
	  public List<String> getArrayOfBeaconIdsInDriverBeacon(List<DriverProfile> listDriverProfiles){
		  
		  List<String> beaconIds = new ArrayList<String>();
		  
		  for (DriverProfile driverProfile : listDriverProfiles) {
			  if(driverProfile.getDriverId()!=null && driverProfile.getDriverId()!= "" && !driverProfile.getDriverId().isEmpty()){			  
			beaconIds.add(driverProfile.getDriverId());
			  }
		}
		  return beaconIds;
	  }

	  public List<String> getArrayOfempIdsInDriverBeacon(List<DriverBeacon> listDriverBeacons){
		  
		  List<String> empIds = new ArrayList<String>();
		  
		  for (DriverBeacon driverBeacon : listDriverBeacons) {
			  empIds.add(driverBeacon.getEmpId());
		}
		  return empIds;
	  }
}

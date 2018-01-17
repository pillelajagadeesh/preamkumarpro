package appraamlabs.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import appraamlabs.models.SerfsBoard;
import appraamlabs.models.VehicleProfile;
import appraamlabs.models.VehicleSerf;
import appraamlabs.service.SerfsBoardService;
import appraamlabs.service.VehicleProfileService;
import appraamlabs.service.VehicleSerfService;
import appraamlabs.service.dtos.ResponseDTO;
import appraamlabs.service.dtos.VehicleSerfDTO;
import appraamlabs.utils.enums.Status;

@RestController
@RequestMapping("/vehicleSerf")
public class VehicleSerfController {

	@Autowired
	private VehicleSerfService vehicleSerfService;
	
	@Autowired
	private VehicleProfileService vehicleProfileService;
	
	@Autowired
	private SerfsBoardService serfsBoardService;
	
	private static Logger logger = LoggerFactory.getLogger(VehicleSerfController.class);
	
	@RequestMapping("/save")
	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody ResponseDTO create(@RequestBody VehicleSerfDTO vehicleSerfDTO) {
		logger.debug("Request to register vehicle serf mapping  with vehicle no {}",vehicleSerfDTO.getVehicleNo());
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();

		
		//Optional<VehicleSerf> optVehicleSerf = vehicleSerfService.findVehicleSerfByVehicleNumberAndSerfName(vehicleSerfDTO.getVehicleNo(), vehicleSerfDTO.getSerfsName());
/*		Optional<VehicleSerf> optVehicleSerf = vehicleSerfService.findById(vehicleSerfDTO.getId());*/
		
		Optional<VehicleProfile> optVehicleProfile = vehicleProfileService.findVehicleProfileByVehicleNo(vehicleSerfDTO.getVehicleNo());
		
		if(!optVehicleProfile.isPresent()){
			
			return new ResponseDTO(400, "No vehicle exists with vehicle no "+vehicleSerfDTO.getVehicleNo());
		}
		
		if(optVehicleProfile.isPresent()){
/*			  VehicleSerf oldVehicleSerf = optVehicleSerf.get();
			  oldVehicleSerf.setVehicleNo(vehicleSerfDTO.getVehicleNo());
			  oldVehicleSerf.setDriverID(vehicleSerfDTO.getDriverID());
			  oldVehicleSerf.setSerfsName(vehicleSerfDTO.getSerfsName());
			  oldVehicleSerf.setMine(vehicleSerfDTO.getMine()); 
			  oldVehicleSerf.setVehicleModel(vehicleSerfDTO.getVehicleModel());
			  oldVehicleSerf.setMake(vehicleSerfDTO.getMake());
			  oldVehicleSerf.setCreatedDate(new Date());
			  oldVehicleSerf.setUpdateDate(new Date());
			  vehicleSerfService.save(oldVehicleSerf);*/
			  
				VehicleProfile vehicleProfile = optVehicleProfile.get();
				vehicleProfile.setSerfName(vehicleSerfDTO.getSerfsName());
				vehicleProfile.setUpdatedDate(formatter.format(today));
				vehicleProfileService.saveVehicleProfile(vehicleProfile);
				
				
			return new ResponseDTO(200, "Vehicle Serf mapped successfully", vehicleProfile);
		}else{
			logger.error("No vehicle exists with with vehicle no{}", vehicleSerfDTO.getVehicleNo());
			return new ResponseDTO(400, "vehicle doesn't exists with vehicle No : "+vehicleSerfDTO.getVehicleNo());
		}
		
/*		VehicleSerf vehicleSerf = null;*/
/*		VehicleProfile vehicleProfile = null;
		try {
			vehicleSerf = new VehicleSerf(vehicleSerfDTO.getVehicleNo(), vehicleSerfDTO.getDriverID(), vehicleSerfDTO.getSerfsName(),
					vehicleSerfDTO.getMine(), vehicleSerfDTO.getVehicleModel(), vehicleSerfDTO.getMake());
			vehicleSerf.setStatus(Status.ACTIVE);
			vehicleSerf.setCreatedDate(new Date());
			vehicleSerf.setUpdateDate(new Date());
			vehicleSerfService.save(vehicleSerf);

			
				vehicleProfile = optVehicleProfile.get();
				vehicleProfile.setSerfName(vehicleSerfDTO.getSerfsName());
				vehicleProfileService.saveVehicleProfile(vehicleProfile);
		} catch (Exception ex) {
			// return ex.getMessage();
			return new ResponseDTO(500, ex.getStackTrace().toString());
		}
		return new ResponseDTO(200, "Vehicle Serf successfully Registered", vehicleProfile);*/
	}
	
	  @RequestMapping("/getAll")
	  @GET
	  @Path("/getAll")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO getAllVehicleSerfs() throws NoSuchElementException{
		  logger.debug("Request to get all Vehicle serf mappings");
	    List<VehicleSerf> vehicleSerfs = null;
	    
	    try{
	    	vehicleSerfs = vehicleSerfService.findAll();
	    	/*Collections.sort (vehicleSerfs,
                    new Comparator<VehicleSerf> ()
                    { public int compare ( VehicleSerf a,  VehicleSerf d)
                          { return (d.getUpdateDate().compareTo(a.getUpdateDate())); }});*/
	    }catch(Exception ex) {
	    	logger.error("Error with message {}",ex.getMessage());
		      return new ResponseDTO(500, ex.getMessage());
		    }
	    return new ResponseDTO(200, "Vehicle Serf's data", vehicleSerfs);
	  }
	 
	  @RequestMapping("/get-by-vehicleNumber/{vehicleNo}")
	  @GET
	  @Path("/get-by-vehicleNumber/{vehicleNo}")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO getByDriverId(@PathVariable("vehicleNo") String vehicleNo) throws NoSuchElementException{
		
	    Optional<VehicleSerf> optionalvehicleserf = vehicleSerfService.findVehicleSerfByVehicleNo(vehicleNo);
	    
	    if(optionalvehicleserf.isPresent()){
	    return new ResponseDTO(200, "Vehicle serf data", optionalvehicleserf.get());
	    }else{
	    	return new ResponseDTO(404, "No vehicleserf exists");
	    }
	    
	  }
	 
	  @RequestMapping("/get-by-serfName/{serfName}")
	  @GET
	  @Path("/get-by-serfName/{serfName}")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO getByBeaconId(@PathVariable("serfName") String serfName) throws NoSuchElementException{
		//  logger.debug("Request to get vehicle by serf name {}",vehicleSerfDTO.getVehicleNo());
	    Optional<VehicleSerf> optionalVehicleSerf = vehicleSerfService.findVehicleSerfBySerfName(serfName);
	    
	    if(optionalVehicleSerf.isPresent()){
	    return new ResponseDTO(200, "Vehicle Serf data", optionalVehicleSerf.get());
	    }else{
	    	return new ResponseDTO(404, "No vehicleserf exists");
	    }
	    
	  }
	 
	  @RequestMapping("/getAllUnmappedVehicleSerfs")
	  @GET
	  @Path("/getAllUnmappedVehicleSerfs")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO getAllUnmappedVehicleSerfs() throws NoSuchElementException{
		  logger.debug("Request to get All unmapped vehicles");
		
	    //List<VehicleSerf> listVehicleSerfs = vehicleSerfService.findAll();
	    
		  List<VehicleProfile> vehicleProfiles = vehicleProfileService.getAllVehicleProfiles();
		  
		  List<VehicleProfile> unmappedvehicleProfiles = this.getAllVehicleNosofSerfNameNull();
		  
	    List<String> serfIds = this.getArrayOfSerfIdsInSerfBoards(vehicleProfiles);
/*	    List<String> vehicleNos = this.getArrayOfVehicleNosInVehicleSerfs(listVehicleSerfs);*/
	    
	    Map<String, Object> unmappedDriverBeacons = new HashMap<String, Object>();
	    try{
/*		    List<VehicleProfile> vehicleProfiles = vehicleProfileService.findUnmappedVehiclesByVehicleNo(vehicleNos);*/
		    List<SerfsBoard> serfBoards = serfsBoardService.findUnmappedSerfsByDeviveNames(serfIds);
	    	
		    unmappedDriverBeacons.put("vehicleProfiles", unmappedvehicleProfiles);
		    unmappedDriverBeacons.put("serfBoards", serfBoards);
/*	    	unmappedDriverBeacons.put("vehicleProfiles", vehicleProfiles);
	    	unmappedDriverBeacons.put("serfBoards", serfBoards);*/
	    }catch(Exception ex) {
		      return new ResponseDTO(500, ex.getMessage());
		    }
	    return new ResponseDTO(200, "Unmapped vehicle serfs data", unmappedDriverBeacons);
	  }
	  
	  @RequestMapping("/update/vehicleserf/{id}")
	  @PUT
	  @Path("/update/vehicleserf/{id}")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO updateDriverBeacon(@RequestBody VehicleSerfDTO vehicleSerfDTO, @PathVariable("id") String id) {
		  logger.debug("Request to update vehicle-serf mapping with vehicle no {}",vehicleSerfDTO.getVehicleNo());
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
          Date today = Calendar.getInstance().getTime();

		  
		  Optional<VehicleSerf> existingOplVehicleSerf = vehicleSerfService.findById(id);
		  
		  if(existingOplVehicleSerf.isPresent()){
			  //update the old existing one to inactive
			  VehicleSerf existingVehicleSerf = existingOplVehicleSerf.get();
			  existingVehicleSerf.setStatus(Status.INACTIVE);
			  existingVehicleSerf.setUpdateDate(formatter.format(today));
			  
			  vehicleSerfService.save(existingVehicleSerf);			  
			  
			  //save new vehicle serf to active
			  VehicleSerf newVehicleSerf = new VehicleSerf(vehicleSerfDTO.getVehicleNo(), vehicleSerfDTO.getDriverID(), vehicleSerfDTO.getSerfsName(),
						vehicleSerfDTO.getMine(), vehicleSerfDTO.getVehicleModel(), vehicleSerfDTO.getMake());
			  newVehicleSerf.setStatus(Status.ACTIVE);
			  newVehicleSerf.setCreatedDate(existingVehicleSerf.getCreatedDate());
			  newVehicleSerf.setUpdateDate(formatter.format(today));
			  vehicleSerfService.save(newVehicleSerf);
				
				Optional<VehicleProfile> optVehicleProfile = vehicleProfileService.findVehicleProfileByVehicleNo(newVehicleSerf.getVehicleNo());
				if(optVehicleProfile.isPresent()){
					VehicleProfile vehicleProfile = optVehicleProfile.get();
					vehicleProfile.setSerfName(newVehicleSerf.getSerfsName());
					vehicleProfileService.saveVehicleProfile(vehicleProfile);
				}
				
			  return new ResponseDTO(200, "DriverBeacon updated successfully", newVehicleSerf);
			  
		  }else{
			  return new ResponseDTO(500, "Invalid request");
		  }
	}
	 
	  @RequestMapping("/delete/{id}")
	  @DELETE
	  @Path("/delete/(id}")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO delete(@PathVariable("id") String id) {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
          Date today = Calendar.getInstance().getTime();

		  
		  Optional<VehicleSerf> existingOplVehicleSerf = vehicleSerfService.findById(id);
		  
		  if(existingOplVehicleSerf.isPresent()){
			  VehicleSerf existingVehicleSerf = existingOplVehicleSerf.get();
			  existingVehicleSerf.setStatus(Status.INACTIVE);
			  existingVehicleSerf.setUpdateDate(formatter.format(today));
			  vehicleSerfService.save(existingVehicleSerf);
			  return new ResponseDTO(Integer.parseInt(HttpStatus.OK.toString()), "Vehicle Serf deleted Successfully");
		  }
		  else{
		        return new ResponseDTO(500, "Invalid request");
		  }
	  }
	 
	  public List<String> getArrayOfVehicleNosInVehicleSerfs(List<VehicleSerf> listVehicleSerfs){
		  
		  List<String> vehicleNos = new ArrayList<String>();
		  
		  for (VehicleSerf vehicleSerf : listVehicleSerfs) {
			  vehicleNos.add(vehicleSerf.getVehicleNo());
		}
		  return vehicleNos;
	  }
	  
	  public List<String> getArrayOfSerfIdsInSerfBoards(List<VehicleProfile> listVehicleProfiles){
		  
		  List<String> SerfIds = new ArrayList<String>();
		  
		  for (VehicleProfile vehicleProfile : listVehicleProfiles) {
			  if(vehicleProfile.getSerfName()!= null && !vehicleProfile.getSerfName().isEmpty()){
			  SerfIds.add(vehicleProfile.getSerfName());
			  }
		}
		  return SerfIds;
	  }
	  
	  public List<VehicleProfile> getAllVehicleNosofSerfNameNull(){
		  List<VehicleProfile> optVehicleProfiles = vehicleProfileService.findUnmappedVehiclesByVehicleNo();
		  
		  /*List<String> vehicleNos = new ArrayList<String>();
		  
		  for(VehicleProfile vehicleProfile: optVehicleProfiles){
			  String vehicleNo = vehicleProfile.getVehicleNo();
			  vehicleNos.add(vehicleNo);
		  }*/
		  return optVehicleProfiles;
	  }
}

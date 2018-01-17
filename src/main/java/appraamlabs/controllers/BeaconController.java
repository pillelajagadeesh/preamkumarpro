package appraamlabs.controllers;

import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import appraamlabs.models.Audit;
import appraamlabs.models.Beacon;
import appraamlabs.models.DriverProfile;
import appraamlabs.service.AuditService;
import appraamlabs.service.BeaconService;
import appraamlabs.service.DriverProfileService;
import appraamlabs.service.dtos.BeaconDTO;
import appraamlabs.service.dtos.ResponseDTO;
import appraamlabs.utils.enums.BeaconType;
import appraamlabs.utils.enums.CollectionName;
import appraamlabs.utils.enums.DeviceStatus;

@RestController
@RequestMapping("/beacon")
public class BeaconController {

	@Autowired
	private BeaconService beaconService;
	
	@Autowired
	private DriverProfileService driverProfileService;
	
	@Autowired
	AuditService auditService;
	  
	private static Logger logger = LoggerFactory.getLogger(BeaconController.class);
	
	  @RequestMapping("/getwpbeacon-by-macId/{macId}")
	  @GET
	  @Path("/getwpbeacon-by-macId/{macId}")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO getByEmail(@PathVariable("macId") String macId) throws NoSuchElementException{
		  
		  logger.debug("Request to access waypoint beacon by macId {}"+macId);
	    Optional<Beacon> optionalBeacon = beaconService.findBeaconByMacId(macId);
	    
	    if(optionalBeacon.isPresent()){
	    	logger.debug("Beacon exists with macId {}", optionalBeacon.get().getMacId());
		    return new ResponseDTO(HttpStatus.OK.value(), "Beacon data", optionalBeacon.get());	    	
	    }else{
	    	logger.error("No waypoint beacon exists with macId {}",macId);
	      return new ResponseDTO(HttpStatus.BAD_REQUEST.value(), "Beacon not found with macId "+macId);
	    }

	  }
	  
	  @RequestMapping("/getwpbeacon-by-uuid-macId/{uuid}/{macId}")
	  @GET
	  @Path("/getwpbeacon-by-uuid-macId/{uuid}/{macId}")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO getWpBeaconByUuid(@PathVariable("uuid") String uuid, @PathVariable("macId") String macId) throws NoSuchElementException{
		  
		  logger.debug("Request to access waypoint beacon by uuid {} and macId {}", uuid, macId);
	    Optional<Beacon> optionalBeacon = beaconService.findBeaconByUuid(uuid);
	    Optional<Beacon> optBeacon = beaconService.findBeaconByMacId(macId);
	    
	    if(optionalBeacon.isPresent() && optBeacon.isPresent()){
	    	logger.error("Waypoint beacon exists with uuid {} and macId {}", optionalBeacon.get().getUuid(), optBeacon.get().getMacId());
		    return new ResponseDTO(HttpStatus.BAD_REQUEST.value(), "Waypoint beacon already exists with uuid "+uuid+" and macId "+macId);
	    }
	    if(optionalBeacon.isPresent()){
	    	logger.error("Waypoint beacon exists with uuid {}", optionalBeacon.get().getUuid());
		    return new ResponseDTO(HttpStatus.BAD_REQUEST.value(), "Waypoint beacon already exists with uuid "+uuid);
	    }	    
	    if(optBeacon.isPresent()){
	    	logger.error("Waypoint beacon exists with macId {}", optBeacon.get().getMacId());
		    return new ResponseDTO(HttpStatus.BAD_REQUEST.value(), "Waypoint beacon already exists with macId ", macId);	    	
	    }
	    	logger.debug("No waypoint beacon exists with uuid {} and macId {}", uuid, macId);
		    return new ResponseDTO(HttpStatus.OK.value(), "No waypoint beacon found with uuid "+uuid+" and macId "+macId);	    	
	  }
	  
	  @RequestMapping("/getAllwpbeacons")
	  @GET
	  @Path("/getAllwpbeacons")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO getAllWPBeacons(Pageable pageable) throws NoSuchElementException{
		  
		  logger.debug("Request to access all waypoint beacons");
	    Page<Beacon> beacons = null;
	    try {
	    	beacons = beaconService.findAllWpBeacons(BeaconType.DRIVERBEACON, pageable);
/*	    	beacons = beaconService.findAllWpBeacons(BeaconType.DRIVERBEACON);*/
	    	/*Collections.sort (beacons,
                    new Comparator<Beacon> ()
                    { public int compare ( Beacon a,  Beacon d)
                          { return (d.getUpdatedDate().compareTo(a.getUpdatedDate())); }});*/
	    }
	    catch(Exception ex) {
	    	logger.error("Error with message {}",ex.getMessage());
	      return new ResponseDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	    }
	    return new ResponseDTO(HttpStatus.OK.value(), "Waypoint Beacon's data", beacons);
	  }
	  
	  @RequestMapping("/searchwpbeacon/{searchText}")
	  @GET
	  @Path("/searchwpbeacon/{searchText}")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO searchWPBeacons(@PathVariable("searchText") String searchText) throws NoSuchElementException{
		  
		  logger.debug("Request to search all waypoint beacons by text {}", searchText);
	    List<Beacon> beacons = null;
	    try {
	    	beacons = beaconService.searchWPBeacons(searchText);
	    }
	    catch(Exception ex) {
	    	logger.error("Error with message {}",ex.getMessage());
	      return new ResponseDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	    }
	    return new ResponseDTO(HttpStatus.OK.value(), "Waypoint Beacon's data by search", beacons);
	  }
	  
	  @RequestMapping("/getAlldriverbeacons")
	  @GET
	  @Path("/getAlldriverbeacons")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO getAllDriverBeacons(Pageable pageable) throws NoSuchElementException{
		  
		  logger.debug("Request access all driver beacons");
	    Page<Beacon> beacons = null;
	    try {
	    	beacons = beaconService.getAllBeaconsByType(BeaconType.DRIVERBEACON, pageable);
	    	/*Collections.sort (beacons,
                    new Comparator<Beacon> ()
                    { public int compare ( Beacon a,  Beacon d)
                          { return (d.getUpdatedDate().compareTo(a.getUpdatedDate())); }});*/
	    }
	    catch(Exception ex) {
	    	logger.error("Error with message {}",ex.getMessage());
	      return new ResponseDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	    }
	    return new ResponseDTO(HttpStatus.OK.value(), "Driver Beacon's data", beacons);
	  }

	  @RequestMapping("/searchdriverbeacon/{searchText}")
	  @GET
	  @Path("/searchdriverbeacon/{searchText}")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO searchDriverBeacons(@PathVariable("searchText") String searchText) throws NoSuchElementException{
		  
		  logger.debug("Request to search all driver beacons by text {}", searchText);
	    List<Beacon> beacons = null;
	    try {
	    	beacons = beaconService.searchDriverBeacons(searchText);
	    }
	    catch(Exception ex) {
	    	logger.error("Error with message {}",ex.getMessage());
	      return new ResponseDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	    }
	    return new ResponseDTO(HttpStatus.OK.value(), "Driver Beacon's data by search", beacons);
	  }
	  
	  @RequestMapping("/get-by-id/{id}")
	  @GET
	  @Path("/get-by-id/{id}")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO getById(@PathVariable("id") String id) throws NoSuchElementException {
		  
		  logger.debug("Request to access beacon by id {}",id);
	    Optional<Beacon> beacon = null;
	    try {
	    	beacon = beaconService.getById(id);
	    }
	    catch(Exception ex) {
	    	logger.error("Error while accessing beacon by id {} with message {}",id, ex.getMessage());
	      return new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
	    }
	    return new ResponseDTO(HttpStatus.OK.value(), "Beacon's data", beacon.get());
	  }

	  @RequestMapping("/save/wpbeacon")
	  @POST
	  @Path("/save/wpbeacon")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO create(@RequestBody BeaconDTO beaconDTO) {
		  
		  logger.debug("Request to register waypoint beacon with macId {}",beaconDTO.getMacId());
		  Optional<Beacon> optionalBeacon =beaconService.findBeaconByMacId(beaconDTO.getMacId());
/*		  Optional<Beacon> optionalBeacon =beaconService.findBeaconByMacId(beaconDTO.getMacId(), beaconDTO.getType());*/
		  
		  if(optionalBeacon.isPresent()){
			  logger.error("Waypoint beacon exists with macId {}",beaconDTO.getMacId());
		        return new ResponseDTO(HttpStatus.BAD_REQUEST.value(), "Beacon already exists with macId "+beaconDTO.getMacId());
		  }
		  
		  Optional<Beacon> optBeacon =beaconService.findBeaconByUuid(beaconDTO.getUuid());
/*		  Optional<Beacon> optBeacon =beaconService.findBeaconByUuid(beaconDTO.getUuid(), beaconDTO.getType());*/
		  
		  if(optBeacon.isPresent()){
			  logger.error("Waypoint beacon exists with uuid {}",beaconDTO.getUuid());
		        return new ResponseDTO(HttpStatus.BAD_REQUEST.value(), "Beacon already exists with Uuid "+beaconDTO.getUuid());
		  }
		  
		  Beacon beacon = null;
	    try {
	    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			  Date today = Calendar.getInstance().getTime();
	    	beacon = new Beacon(beaconDTO.getMacId(), beaconDTO.getUuid(), beaconDTO.getMajor(), beaconDTO.getMinor(),
	    			beaconDTO.getBeaconPeriod(), beaconDTO.getTxPower(), beaconDTO.getUser(), beaconDTO.getMine());
	      beacon.setType(beaconDTO.getType());
/*		  beacon.setType(beaconDTO.getType());*/
	      beacon.setStatus(DeviceStatus.ACTIVE);
	      beacon.setCreatedDate(formatter.format(today));
	      beacon.setUpdatedDate(formatter.format(today));
	      beaconService.saveBeacon(beacon);
	    }
	    catch(Exception ex) {
	    	logger.error("Error while saving waypoint beacon with macId{} with message {}",beaconDTO.getMacId(),ex.getMessage());
	        return new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
	    }
	    return new ResponseDTO(HttpStatus.OK.value(), "Waypoint beacon successfully Registered with macId : "+beacon.getMacId(), beacon);
	  }

	  @RequestMapping("/save/driverbeacon")
	  @POST
	  @Path("/save/driverbeacon")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO createDriverBeacon(@RequestBody BeaconDTO beaconDTO) { /*String email, String name*/
	
		  logger.debug("Request to register driver beacon with macId {}",beaconDTO.getMacId());
		  
		  Optional<Beacon> optionalBeacon =beaconService.findBeaconByMacId(beaconDTO.getMacId());
/*		  Optional<Beacon> optionalBeacon =beaconService.findBeaconByMacId(beaconDTO.getMacId(), beaconDTO.getType());*/
		  
		  if(optionalBeacon.isPresent()){
			  logger.error("Driver beacon exists with macId {}",beaconDTO.getMacId());
		        return new ResponseDTO(HttpStatus.BAD_REQUEST.value(), "Beacon already exists with macId "+beaconDTO.getMacId());
		  }
		  
		  Optional<Beacon> optBeacon =beaconService.findBeaconByUuid(beaconDTO.getUuid());
/*		  Optional<Beacon> optBeacon =beaconService.findBeaconByUuid(beaconDTO.getUuid(), beaconDTO.getType());*/
		  
		  if(optBeacon.isPresent()){
			  logger.error("Driver beacon exists with uuid {}",beaconDTO.getUuid());
		        return new ResponseDTO(HttpStatus.BAD_REQUEST.value(), "Beacon already exists with Uuid "+beaconDTO.getUuid());
		  }
		  
		  Beacon beacon = null;
	    try {
	    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			  Date today = Calendar.getInstance().getTime();
	    	beacon = new Beacon(beaconDTO.getMacId(), beaconDTO.getUuid(), beaconDTO.getMajor(), beaconDTO.getMinor(),
	    			beaconDTO.getBeaconPeriod(), beaconDTO.getTxPower(), beaconDTO.getUser(), beaconDTO.getMine());
	      beacon.setType(beaconDTO.getType());
/*		  beacon.setType(beaconDTO.getType());*/
	      beacon.setStatus(DeviceStatus.ACTIVE);
	      beacon.setCreatedDate(formatter.format(today));
	      beacon.setUpdatedDate(formatter.format(today));
	      beaconService.saveBeacon(beacon);
	    }
	    catch(Exception ex) {
	    	logger.error("Error saving driver beacon with macId {} with message {}", beaconDTO.getMacId() , ex.getMessage());
	        return new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
	    }
	    return new ResponseDTO(HttpStatus.OK.value(), "Driver beacon successfully registered with macId : "+beacon.getMacId(), beacon);
	  }
	  
	  @RequestMapping("/update/driverbeacon/{id}")
	  @PUT
	  @Path("/update/driverbeacon/{id}")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO updateDriverBeacon(@RequestBody BeaconDTO beaconDTO, @PathVariable("id") String id) throws ParseException {

		  logger.debug("Request to update driver beacon with macId {}",beaconDTO.getMacId());
		  
		  Optional<Beacon> existingOplBeacon = beaconService.getById(id);
		  //Beacon beacon = null;
		  Optional<DriverProfile> optDriverProfile = null;
		  DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		  Date today = Calendar.getInstance().getTime();
		  if(existingOplBeacon.isPresent() && existingOplBeacon.get().getStatus().equals(DeviceStatus.ACTIVE) && 
				  existingOplBeacon.get().getType().equals(BeaconType.DRIVERBEACON)){
/*				  existingOplBeacon.get().getType().equals(beaconDTO.getType())){*/
			  optDriverProfile = driverProfileService.getByDriverId(existingOplBeacon.get().getUuid());
			  
			  Beacon existingBeacon = existingOplBeacon.get();
			  existingBeacon.setStatus(DeviceStatus.ROLLBACK);
			  existingBeacon.setUpdatedDate(formatter.format(today));
			  beaconService.saveBeacon(existingBeacon);
			  
			  //Updating rollback data to audit
			  updateAudit(existingBeacon.getMacId(), existingBeacon.getId());

		  }else{
			  logger.debug("Driver beacon doesn't exists with macId {}",beaconDTO.getMacId());
			  return new ResponseDTO(HttpStatus.BAD_REQUEST.value(), "No driver beacon exists with maId "+beaconDTO.getMacId());
		  }
		  
		  Beacon newBeacon = null;
		  
	    try {
	    	newBeacon = new Beacon(beaconDTO.getMacId(), beaconDTO.getUuid(), beaconDTO.getMajor(), beaconDTO.getMinor(),
	    			beaconDTO.getBeaconPeriod(), beaconDTO.getTxPower(),beaconDTO.getUser(), beaconDTO.getMine());
	    	newBeacon.setType(beaconDTO.getType());
/*	    	newBeacon.setType(beaconDTO.getType());*/
	    	newBeacon.setStatus(DeviceStatus.ACTIVE);
	    	newBeacon.setCreatedDate(formatter.format(today));
	    	newBeacon.setUpdatedDate(formatter.format(today));
	      beaconService.saveBeacon(newBeacon);
	      
	    //UPdate the driverprofile driverId with beacon uuid
		  if(optDriverProfile.isPresent()){
			  DriverProfile driverProfile = optDriverProfile.get();
			  driverProfile.setDriverId(beaconDTO.getUuid());
		  driverProfileService.saveDriverProfile(driverProfile);
		  }
	    }
	    catch(Exception ex) {
	    	logger.error("Error with macId {} with message {}",beaconDTO.getMacId(), ex.getMessage());
	        return new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
	    }
	    return new ResponseDTO(HttpStatus.OK.value(), "Driver Beacon successfully updated with macId "+beaconDTO.getMacId(), newBeacon);
	  }
	  
	  @RequestMapping("/update/wpbeacon/{id}")
	  @PUT
	  @Path("/update/wpbeacon/{id}")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO updateWPBeacon(@RequestBody BeaconDTO beaconDTO, @PathVariable("id") String id) throws ParseException {
		  
		  logger.debug("Request to update waypoint beacon with macId {}", beaconDTO.getMacId());
		  
		  Optional<Beacon> existingOplBeacon = beaconService.getById(id);
		  
		  DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		  Date today = Calendar.getInstance().getTime();
		  if(existingOplBeacon.isPresent() && existingOplBeacon.get().getStatus().equals(DeviceStatus.ACTIVE)){
/*				  existingOplBeacon.get().getType().equals(BeaconType.WPBEACON)){*/
			  Beacon existingBeacon = existingOplBeacon.get();
			  existingBeacon.setStatus(DeviceStatus.ROLLBACK);
			  existingBeacon.setUpdatedDate(formatter.format(today));
			  beaconService.saveBeacon(existingBeacon);
			  
			  //Updating rollback data to audit
			  updateAudit(existingBeacon.getMacId(), existingBeacon.getId());

		  }else{
			  logger.error("Waypoint beacon doesn't exists with macId {}",beaconDTO.getMacId());
			  return new ResponseDTO(HttpStatus.BAD_REQUEST.value(), "Waypoint beacon doesn't exists with macId");
		  }
		  
		  Beacon newBeacon = null;
		  
	    try {
	    	newBeacon = new Beacon(beaconDTO.getMacId(), beaconDTO.getUuid(), beaconDTO.getMajor(), beaconDTO.getMinor(),
	    			beaconDTO.getBeaconPeriod(), beaconDTO.getTxPower(), beaconDTO.getUser(), beaconDTO.getMine());
	    	newBeacon.setType(beaconDTO.getType());
/*	    	newBeacon.setType(beaconDTO.getType());*/
	    	newBeacon.setStatus(DeviceStatus.ACTIVE);
	    	newBeacon.setCreatedDate(formatter.format(today));
	    	newBeacon.setUpdatedDate(formatter.format(today));
	      beaconService.saveBeacon(newBeacon);
	    }
	    catch(Exception ex) {
	    	logger.error("Error while updating waypoint beacon macId {} with message {}", beaconDTO.getMacId(), ex.getMessage());
	        return new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
	    }
	    return new ResponseDTO(HttpStatus.OK.value(), "Driver beacon successfully updated with macId "+beaconDTO.getMacId(), newBeacon);
	  }
	  
	  @RequestMapping("/rollback/wpbeacon/{macId}")
	  @PUT
	  @Path("/rollback/wpbeacon/{macId}")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO rollbackWPBeacon(@RequestBody BeaconDTO beaconDTO, @PathVariable("macId") String macId) throws ParseException {
		  
		  logger.debug("Request to rollback waypoint beacon with macId {}",macId);
		  
          Optional<Audit> optionalAudit = auditService.getByMacId(macId);
          Optional<Beacon> optionalBeacon = beaconService.findBeaconByMacId(macId);
/*		  Optional<Beacon> optionalBeacon = beaconService.findBeaconByMacId(macId, beaconDTO.getType());*/
          
          DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
          Date today = Calendar.getInstance().getTime();
		  Optional<Beacon> existingOplBeacon = null;
		  if(optionalAudit.isPresent()){
			  existingOplBeacon = beaconService.getById(optionalAudit.get().getRefId());
			  Beacon existingRollbackbeacon = existingOplBeacon.get();
			  existingRollbackbeacon.setStatus(DeviceStatus.ACTIVE);
			  existingRollbackbeacon.setUpdatedDate(formatter.format(today));
		      beaconService.saveBeacon(existingRollbackbeacon);
			  
			  Beacon beacon  = optionalBeacon.get();
/*			  beacon.setType(BeaconType.WPBEACON);*/
/*			  beacon.setType(beaconDTO.getType());*/
		      beacon.setStatus(DeviceStatus.ROLLBACK);
		      beacon.setUpdatedDate(formatter.format(today));
		      beaconService.saveBeacon(beacon);
		      
		    //Update the rollback data in audit
		      updateAudit(beacon.getMacId(), beacon.getId());
		      
		      return new ResponseDTO(HttpStatus.OK.value(), "Waypoint beacon rollback successfully with macId "+beaconDTO.getMacId());
		  }else{
			        return new ResponseDTO(HttpStatus.OK.value(), "No rollback waypoint beacon exists with macId "+beaconDTO.getMacId()); 
		  }

	  }
	  
	  @RequestMapping("/rollback/driverbeacon/{macId}")
	  @PUT
	  @Path("/rollback/driverbeacon/{macId}")
	  @Consumes(MediaType.APPLICATION_JSON)  
	  public @ResponseBody ResponseDTO rollbackDriverBeacon(@RequestBody BeaconDTO beaconDTO, @PathVariable("macId") String macId) throws ParseException {
		  
		  logger.debug("Request to rollback driver beacon with macId {}",macId);
		  
          Optional<Audit> optionalAudit = auditService.getByMacId(macId);
		  Optional<Beacon> OptionalBeacon = beaconService.findBeaconByMacId(macId);
/*		  Optional<Beacon> OptionalBeacon = beaconService.findBeaconByMacId(macId, beaconDTO.getType());*/
		  
		  DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		  Date today = Calendar.getInstance().getTime(); 
		  Optional<Beacon> existingOplBeacon = null;
		  if(optionalAudit.isPresent()){
			  existingOplBeacon = beaconService.getById(optionalAudit.get().getRefId());
			  Beacon existingRollbackbeacon = existingOplBeacon.get();
			  existingRollbackbeacon.setStatus(DeviceStatus.ACTIVE);
			  existingRollbackbeacon.setUpdatedDate(formatter.format(today));
		      
		      //Saving beacon with Active status
		      beaconService.saveBeacon(existingRollbackbeacon);
		      
		      //Make active beacon to rollback
		      Beacon newBeacon = OptionalBeacon.get();
		      newBeacon.setStatus(DeviceStatus.ROLLBACK);
		      newBeacon.setUpdatedDate(formatter.format(today));
		      newBeacon = beaconService.saveBeacon(newBeacon);
		      
		    //Update the rollback data in audit
		      updateAudit(newBeacon.getMacId(), newBeacon.getId());
		      
		      return new ResponseDTO(HttpStatus.OK.value(), "Driver beacon rollback successfull with macId "+beaconDTO.getMacId());
		  }else{
			        return new ResponseDTO(HttpStatus.BAD_REQUEST.value(), "No rollback driver beacon exists with macId "+beaconDTO.getMacId()); 
		  }

	  }

     public void updateAudit(String macId, String id) throws ParseException{
		  
		  //push rollback data to Audit entity
		  Optional<Audit> optAudit = auditService.getByMacId(macId);
		  
		  DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		  Date today = Calendar.getInstance().getTime();
		  if(optAudit.isPresent()){
			  Audit audit = optAudit.get();
			  audit.setDate(formatter.format(today));
			  audit.setRefId(id);
			  auditService.saveAudit(audit);
		  }else{
		     Audit audit = new Audit(id, CollectionName.BEACONS, formatter.format(today), null);
		     audit.setMacId(macId);
			 auditService.saveAudit(audit);
		  }
	  }
     
}

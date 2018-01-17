package appraamlabs.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import java.util.List;
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
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.NestedServletException;

import com.jcraft.jsch.JSchException;

import appraamlabs.models.Audit;
import appraamlabs.models.SerfsBoard;
import appraamlabs.models.VehicleProfile;
import appraamlabs.models.VehicleSerf;
import appraamlabs.service.AuditService;
import appraamlabs.service.SerfsBoardService;
import appraamlabs.service.VehicleProfileService;
import appraamlabs.service.dtos.ResponseDTO;
import appraamlabs.service.dtos.SerfsBoardDTO;
import appraamlabs.utils.constants.MessageConstants;
import appraamlabs.utils.enums.CollectionName;
import appraamlabs.utils.enums.DeviceStatus;

@RestController
@RequestMapping("/serf")
public class SerfsBoardController {

	@Autowired
    private SerfsBoardService serfsBoardService;
	
	@Autowired
	private AuditService auditService;
	
	@Autowired
	private VehicleProfileService vehicleProfileService;
	
	@Autowired
	private Environment env;
	
	/*@Autowired
	private ResberryPiService resberryPiService;*/

	private static final Logger logger = LoggerFactory.getLogger(SerfsBoardController.class);
	 
	  @RequestMapping("/getAll")
	  @GET
	  @Path("/getAll")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO getAllSerfs(Pageable pageable) {
		  
		  logger.debug("Request to get all serfs");
	    Page<SerfsBoard> serfBoards = null;
	    try {
	    	serfBoards = serfsBoardService.getAllSerfsBoard(pageable);
	    	
	    	/*Collections.sort (serfBoards,
                    new Comparator<SerfsBoard> ()
                    { public int compare ( SerfsBoard a,  SerfsBoard d)
                          { return (d.getUpdatedDate().compareTo(a.getUpdatedDate())); }});*/
	    	
	    }
	    catch(Exception ex) {
	    	logger.error("Error with message {}",ex.getMessage());
	      return new ResponseDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	    }
	    return new ResponseDTO(HttpStatus.OK.value(), env.getProperty(MessageConstants.SERF_SUCCESS_MESSAGE), serfBoards);
	  }
	  
	  @RequestMapping("/get-by-macId/{macId}")
	  @GET
	  @Path("/get-by-macId/{macId}")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO getByMacId(@PathVariable("macId") String macId) {
		  
		  logger.debug("Request to get serf by macId {}",macId);
	    Optional<SerfsBoard> optionalSerfsBoard = null;
	    try {
	    	optionalSerfsBoard = serfsBoardService.findSerfsBoardByMacId(macId);
	    }
	    catch(Exception ex) {
	      return new ResponseDTO(HttpStatus.BAD_REQUEST.value(), "SerfsBoard not found");
	    }
	    return new ResponseDTO(HttpStatus.OK.value(), "SerfsBoard details with macId "+optionalSerfsBoard.get().getMacId(), optionalSerfsBoard.get());
	  }

	  @RequestMapping("/get-by-id/{id}")
	  @GET
	  @Path("/get-by-id/{id}")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO getById(@PathVariable("id") String id) throws NestedServletException {
		  
		  logger.debug("Request to access serf by id {}",id);
	    Optional<SerfsBoard> serfsBoard = null;
	    try {
	    	serfsBoard = serfsBoardService.getById(id);
	    }
	    catch(Exception ex) {
	      return new ResponseDTO(HttpStatus.BAD_REQUEST.value(), "SerfsBoard not found");
	    }
	    return new ResponseDTO(HttpStatus.OK.value(), "SerfsBoard details with id "+serfsBoard.get().getId(), serfsBoard.get());
	  }

	  @RequestMapping("/search/{searchText}")
	  @GET
	  @Path("/search/{searchText}")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO searchSerf(@PathVariable("searchText") String searchText) throws NestedServletException {
		  
		  logger.debug("Request to search serf by text {}",searchText);
	    List<SerfsBoard> serfsBoards = null;
	    try {
	    	serfsBoards = serfsBoardService.searchSerfsBoard(searchText);
	    }
	    catch(Exception ex) {
	      return new ResponseDTO(HttpStatus.BAD_REQUEST.value(), "SerfsBoard not found");
	    }
	    return new ResponseDTO(HttpStatus.OK.value(), "SerfsBoard details by search", serfsBoards);
	  }
	  
	  @RequestMapping("/save")
	  @POST
	  @Path("/save")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO create(@RequestBody SerfsBoardDTO serfsBoardDTO) { /*String email, String name*/
		  		  
		  logger.debug("Request to save serf with macId {}",serfsBoardDTO.getMacId());
		  Optional<SerfsBoard> optionalSerfBoard = serfsBoardService.findSerfsBoardByMacId(serfsBoardDTO.getMacId());
		  
		  if(optionalSerfBoard.isPresent()){
			  logger.error("Serf exists with macId {}",optionalSerfBoard.get().getMacId());
			  return new ResponseDTO(HttpStatus.BAD_REQUEST.value(), "Serf exists with macId "+optionalSerfBoard.get().getMacId());
		  }
		  
		  Optional<SerfsBoard> optSerfsBoard = serfsBoardService.findOneByDeviceName(serfsBoardDTO.getDeviceName());
		  
		  if(optSerfsBoard.isPresent()){
			  logger.error("Serf exists with devicename {}",optSerfsBoard.get().getDeviceName());
			  return new ResponseDTO(HttpStatus.BAD_REQUEST.value(), "Serf exists with device name "+optSerfsBoard.get().getDeviceName());			  
		  }
		  
		  SerfsBoard serfsBoard = null;
		  SerfsBoard activeSerfsBoard = null;
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
          Date today = Calendar.getInstance().getTime();

	    try {
	    	//for inserting new Defult status serf
	    	serfsBoard = new SerfsBoard(serfsBoardDTO.getUuid(), serfsBoardDTO.getMacId(), serfsBoardDTO.getDeviceName(),
	    			serfsBoardDTO.getUid(), serfsBoardDTO.getFtpUrl(), serfsBoardDTO.getUserName(), serfsBoardDTO.getPassword(), 
	    			serfsBoardDTO.getSyncTime(), serfsBoardDTO.getGprsConnection(), serfsBoardDTO.getObdProtocol(), 
	    			serfsBoardDTO.getFtpTimeIntervel(), serfsBoardDTO.getObdTimeIntervel(), serfsBoardDTO.getMajor(), 
	    			serfsBoardDTO.getMinor(), serfsBoardDTO.getUser(), serfsBoardDTO.getMine());
	    	serfsBoard.setStatus(DeviceStatus.DEFAULT);
	    	serfsBoard.setCreatedDate(formatter.format(today));
	    	serfsBoard.setUpdatedDate(formatter.format(today));
	    	serfsBoardService.saveSerfsBoard(serfsBoard);
	    	
	    	//for inserting new Active status serf
	    	activeSerfsBoard = new SerfsBoard(serfsBoardDTO.getUuid(), serfsBoardDTO.getMacId(), serfsBoardDTO.getDeviceName(),
	    			serfsBoardDTO.getUid(), serfsBoardDTO.getFtpUrl(), serfsBoardDTO.getUserName(), serfsBoardDTO.getPassword(), 
	    			serfsBoardDTO.getSyncTime(), serfsBoardDTO.getGprsConnection(), serfsBoardDTO.getObdProtocol(), 
	    			serfsBoardDTO.getFtpTimeIntervel(), serfsBoardDTO.getObdTimeIntervel(), serfsBoardDTO.getMajor(), 
	    			serfsBoardDTO.getMinor(), serfsBoardDTO.getUser(), serfsBoardDTO.getMine());
	    	activeSerfsBoard.setStatus(DeviceStatus.ACTIVE);
	    	activeSerfsBoard.setCreatedDate(formatter.format(today));
	    	activeSerfsBoard.setUpdatedDate(formatter.format(today));
	    	serfsBoardService.saveSerfsBoard(activeSerfsBoard);
	    }
	    catch(Exception ex) {
	    	logger.error("Error with message {}", ex.getMessage());
	        return new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getStackTrace().toString());
	    }
	    return new ResponseDTO(HttpStatus.OK.value(), "SerfsBoard registered successfully with macId "+activeSerfsBoard.getMacId()+" and device name "
	    +activeSerfsBoard.getDeviceName(), activeSerfsBoard);
	  }
	  
	  @RequestMapping("/update/{id}")
	  @PUT
	  @Path("/update/{id}")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO updateSerf(@RequestBody SerfsBoardDTO serfsBoardDTO, @PathVariable("id") String id) {
		  
		  logger.debug("Request to update serf by id {}", id);
		  Optional<SerfsBoard> existingOplSerfsBoard = serfsBoardService.getById(id);
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
          Date today = Calendar.getInstance().getTime();

          Optional<VehicleProfile> optionalVehicleProfile = null;
		  if(existingOplSerfsBoard.isPresent() && existingOplSerfsBoard.get().getStatus().equals(DeviceStatus.ACTIVE)){
			  //Updating serf details
			  optionalVehicleProfile = vehicleProfileService.findVehicleProfileBySerfName(existingOplSerfsBoard.get().getDeviceName());
			  SerfsBoard existingSerfsBoard = existingOplSerfsBoard.get();			 
			  existingSerfsBoard.setStatus(DeviceStatus.ROLLBACK);
			  existingSerfsBoard.setUpdatedDate(formatter.format(today));
			  serfsBoardService.saveSerfsBoard(existingSerfsBoard);
			  
			  //Editing Audit entity
			  updateAudit(existingSerfsBoard.getMacId(), existingSerfsBoard.getId());

		  }
		  else{
			  logger.error("No serf exists with active status to update with id {}", id);
		        return new ResponseDTO(HttpStatus.BAD_REQUEST.value(), "No serf exists to update with id "+id);
		  }
		  SerfsBoard newSerfsBoard = null;
	    try {
	    	//Saving serf details
	    	newSerfsBoard = new SerfsBoard(serfsBoardDTO.getUuid(), serfsBoardDTO.getMacId(), serfsBoardDTO.getDeviceName(),
	    			serfsBoardDTO.getUid(), serfsBoardDTO.getFtpUrl(), serfsBoardDTO.getUserName(), serfsBoardDTO.getPassword(), 
	    			serfsBoardDTO.getSyncTime(), serfsBoardDTO.getGprsConnection(), serfsBoardDTO.getObdProtocol(),
	    			serfsBoardDTO.getFtpTimeIntervel(), serfsBoardDTO.getObdTimeIntervel(), serfsBoardDTO.getMajor(),
	    			serfsBoardDTO.getMinor(), serfsBoardDTO.getUser(), serfsBoardDTO.getMine());
	    	newSerfsBoard.setStatus(DeviceStatus.ACTIVE);
	    	newSerfsBoard.setCreatedDate(formatter.format(today));
	    	newSerfsBoard.setUpdatedDate(formatter.format(today));
	    	newSerfsBoard = serfsBoardService.saveSerfsBoard(newSerfsBoard);
	    	
	    	//UPdate vehicle serfName with serfs devicename
			  if(optionalVehicleProfile.isPresent()){
				  VehicleProfile vehicleProfile = optionalVehicleProfile.get();
				  vehicleProfile.setSerfName(serfsBoardDTO.getDeviceName());
				  vehicleProfileService.saveVehicleProfile(vehicleProfile);
			  }
	    }
	    catch(Exception ex) {
	    	logger.error("Error with message {}",ex.getMessage());
	        return new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getStackTrace().toString());
	    }
	    return new ResponseDTO(200, "SerfsBoard updated successfully with macId "+newSerfsBoard.getMacId()+" and device name "
	    +newSerfsBoard.getDeviceName(), newSerfsBoard);
	  }
	  
	  @RequestMapping("/rollback/{macId}")
	  @PUT
	  @Path("/rollback/{macId}")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO rollbackSerf(@RequestBody SerfsBoardDTO serfsBoardDTO, @PathVariable("macId") String macId) throws IOException, JSchException {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
          Date today = Calendar.getInstance().getTime();

		  logger.debug("Request to rollback serf with macId {}",macId);
		  Optional<Audit> optionalAudit = auditService.getByMacId(macId);
		  Optional<SerfsBoard> optionalSerfBoard = serfsBoardService.getSerfByMacIdAndStatus(macId, DeviceStatus.ACTIVE);
		  
		  Optional<SerfsBoard> existingOplSerfsBoard = null;
		  if(optionalAudit.isPresent()){
			  existingOplSerfsBoard = serfsBoardService.getById(optionalAudit.get().getRefId());
		  }
		  
		  if(existingOplSerfsBoard != null && existingOplSerfsBoard.get().getStatus().equals(DeviceStatus.ROLLBACK)){
			  
			  //Updating rollback to active
			  SerfsBoard existingSerfsBoard = existingOplSerfsBoard.get();
			  existingSerfsBoard.setStatus(DeviceStatus.ACTIVE);
			  existingSerfsBoard.setUpdatedDate(formatter.format(today));
			  serfsBoardService.saveSerfsBoard(existingSerfsBoard);
			  
			  //Convert a object to file and send to rasberry pi
			  //resberryPiService.sendFileToResberrySsh(existingOplSerfsBoard.get());
			  
			  //Updating active to rollback
			  SerfsBoard newSerfsBoard = optionalSerfBoard.get();
		    	newSerfsBoard.setStatus(DeviceStatus.ROLLBACK);
		    	newSerfsBoard.setUpdatedDate(formatter.format(today));
		    	newSerfsBoard = serfsBoardService.saveSerfsBoard(newSerfsBoard);
		    	
		    	//Editing Audit entity for rollback serf
				  updateAudit(newSerfsBoard.getMacId(), newSerfsBoard.getId());
		  }
		  else{
			  logger.error("No rollback exists with macId {}", macId);
		        return new ResponseDTO(HttpStatus.BAD_REQUEST.value(), "No rollback data exists with macId "+macId);
		  }
		  
	    return new ResponseDTO(HttpStatus.OK.value(), "SerfsBoard successfully updated to resberry pi with macId "+macId);
	  }

	  @RequestMapping("/validateUpdate/{macId}")
	  @PUT
	  @Path("/validateUpdate/{macId}")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO validateUpdateSerf(@RequestBody SerfsBoardDTO serfsBoardDTO, @PathVariable("macId") String macId) throws IOException, JSchException {
		  
		  logger.debug("Request to access validate and update with macId {}", macId);
		  Optional<SerfsBoard> existingOplSerfsBoard = serfsBoardService.getSerfByMacIdAndStatus(macId, DeviceStatus.ACTIVE);
		  
		  if(existingOplSerfsBoard.isPresent() && existingOplSerfsBoard.get().getStatus().equals(DeviceStatus.ACTIVE)){
			  
			  //Convert a object to file and send to rasberry pi
			  //resberryPiService.sendFileToResberrySsh(existingOplSerfsBoard.get());
		  }
		  else{
			  logger.error("No serf exists with macId {}", macId);
		        return new ResponseDTO(HttpStatus.BAD_REQUEST.value(), "No Serfboard exists with macId "+macId+" to validate and update");
		  }
	    return new ResponseDTO(HttpStatus.OK.value(), "SerfsBoard successfully updated to resberry pi with macId "+macId);
	  }

	  @RequestMapping("/restore/{macId}")
	  @PUT
	  @Path("/restore/{macId}")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO restoreSerf(@RequestBody SerfsBoardDTO serfsBoardDTO, @PathVariable("macId") String macId) throws IOException, JSchException {
		  
		  logger.debug("Request to restore serf with macId {}", macId);
		  Optional<SerfsBoard> existingOplSerfsBoard = serfsBoardService.getSerfByMacIdAndStatus(macId, DeviceStatus.DEFAULT);
		  Optional<SerfsBoard> optionalActiveSerfBoard = serfsBoardService.getSerfByMacIdAndStatus(macId, DeviceStatus.ACTIVE);
		  
		  if(existingOplSerfsBoard.isPresent() && existingOplSerfsBoard.get().getStatus().equals(DeviceStatus.DEFAULT)){
				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
              Date today = Calendar.getInstance().getTime();

			  
			   SerfsBoard existingActiveSerfsBoard = optionalActiveSerfBoard.get();
			   existingActiveSerfsBoard.setStatus(DeviceStatus.ROLLBACK);
			   existingActiveSerfsBoard.setUpdatedDate(formatter.format(today));
			   serfsBoardService.saveSerfsBoard(existingActiveSerfsBoard);
		    	
		    	//Editing Audit entity for rollback serf
				  updateAudit(existingActiveSerfsBoard.getMacId(), existingActiveSerfsBoard.getId());
			  				  
				  SerfsBoard newSerfsBoard = new SerfsBoard(existingOplSerfsBoard.get().getUuid(), existingOplSerfsBoard.get().getMacId(), existingOplSerfsBoard.get().getDeviceName(),						  
						  existingOplSerfsBoard.get().getUid(), existingOplSerfsBoard.get().getFtpUrl(), existingOplSerfsBoard.get().getUserName(), existingOplSerfsBoard.get().getPassword(), 
						  existingOplSerfsBoard.get().getSyncTime(), existingOplSerfsBoard.get().getGprsConnection(), existingOplSerfsBoard.get().getObdProtocol(),
						  existingOplSerfsBoard.get().getFtpTimeIntervel(), existingOplSerfsBoard.get().getObdTimeIntervel(), existingOplSerfsBoard.get().getMajor(),
						  existingOplSerfsBoard.get().getMinor(), existingOplSerfsBoard.get().getUser(), existingOplSerfsBoard.get().getMine());
			    	newSerfsBoard.setStatus(DeviceStatus.ACTIVE);
			    	newSerfsBoard.setCreatedDate(formatter.format(today));
			    	newSerfsBoard = serfsBoardService.saveSerfsBoard(newSerfsBoard);
			  
					  //Convert a object to file and send to rasberry pi
					  //resberryPiService.sendFileToResberrySsh(existingOplSerfsBoard.get());
		  }
		  else{
			  logger.error("No serf exists to restore with macId {}", macId);
		        return new ResponseDTO(HttpStatus.BAD_REQUEST.value(), "No restore data exists with macId "+macId);
		  }
	    return new ResponseDTO(HttpStatus.OK.value(), "Restore successfull for macId "+macId);
	  }
	  
	  public void updateAudit(String macId, String id){
		  
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
		     Audit audit = new Audit(id, CollectionName.SERFS, formatter.format(today), null);
		     audit.setMacId(macId);
			  auditService.saveAudit(audit);
		  }
	  }
}

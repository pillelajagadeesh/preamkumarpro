package appraamlabs.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import appraamlabs.models.Beacon;
import appraamlabs.models.DriverProfile;
import appraamlabs.models.Issues;
import appraamlabs.models.SerfsBoard;
import appraamlabs.models.VehicleProfile;
import appraamlabs.service.BeaconService;
import appraamlabs.service.DriverProfileService;
import appraamlabs.service.IssuesService;
import appraamlabs.service.SerfsBoardService;
import appraamlabs.service.VehicleProfileService;
import appraamlabs.service.dtos.IssuesDTO;
import appraamlabs.service.dtos.ResponseDTO;
import appraamlabs.utils.enums.IssueType;

@RestController
@RequestMapping("/issues")
public class IssuesController {

	@Autowired
	private IssuesService issuesService;
	
	@Autowired
	private DriverProfileService driverProfileService;
	
	@Autowired
	private SerfsBoardService serfsBoardService;
	
	@Autowired
	private VehicleProfileService vehicleProfileService;
	
	private static Logger logger = LoggerFactory.getLogger(IssuesController.class);
	
	  @RequestMapping("/delete/{id}")
	  @DELETE
	  @Path("/delete/(id}")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO delete(@PathVariable("id") String id) {
		  
		  logger.debug("Request to delete issue by id {}", id);
		  Optional<Issues> optIssues = issuesService.findOne(id);
		  
		  if(optIssues.isPresent()){
			  logger.debug("Issue exists with id {}", id);
			  issuesService.delete(optIssues.get());
	        return new ResponseDTO(200, "Issues deleted with id "+id);
	    }else{
	    	logger.error("Issue doesn't exists with id {}", id);
	    	return new ResponseDTO(400, "No issue exists with id "+id);
	    }
	  }
	  
	  @RequestMapping("/get-by-uuid/{uuid}")
	  @GET
	  @Path("/get-by-uuid/{uuid}")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO getByUuid(@PathVariable("uuid") String uuid) {
		  logger.debug("Request to access issue by uuid {}", uuid);
		  Optional<Issues> optIssues = issuesService.findIssuesByUuid(uuid);
		  
		  if(optIssues.isPresent()){
			  logger.debug("Issue exists with uuid {}", uuid);
	        return new ResponseDTO(200, "Issues data", optIssues.get());
	    }else{
			  logger.debug("No issue exists with uuid {}", uuid);	    	
	    	return new ResponseDTO(400, "No issue exists with uuid "+uuid);
	    }
	  }
	  
	  @RequestMapping("/get-issues-opened")
	  @GET
	  @Path("/get-issues-opened")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO getAllOpenedIssues() {
		  logger.debug("Request to access all opened issues");
		  List<Issues> issues = null;
		  
		  try{
			  issues = issuesService.findAllIssuesByType(IssueType.OPEN);
		        return new ResponseDTO(200, "Opened Issues data", issues);
		  }catch(Exception ex){
			  logger.error("Error while accessing opened issues with message {}", ex.getMessage());
		    	return new ResponseDTO(400, "Internel server with message : "+ex.getMessage());
		  }
		  
	  }
	  
	  @RequestMapping("/get-issues-opened-drivers")
	  @GET
	  @Path("/get-issues-opened-drivers")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO getAllOpenedIssuesWithDrivers() {
		  logger.debug("Request to access all opened driver issues");
		  List<Issues> issues = null;
		  List<String> issuesList = new ArrayList<String>();
		  
		  try{
			  issues = issuesService.findLatestIssuesByType(IssueType.OPEN);

			  for(Issues issue: issues){
				  
				  issuesList.add(issue.getCommands());
	/*		  Optional<DriverProfile> optDriverProfile = driverProfileService.getByDriverId(issue.getUuid());
			  Optional<SerfsBoard> optSerfBoard = serfsBoardService.findByUuid(issue.getUuid());
			  Optional<VehicleProfile> optVehicleProfile = null;
			  if(optSerfBoard.isPresent()){
			  optVehicleProfile = vehicleProfileService.findVehicleProfileBySerfName(optSerfBoard.get().getDeviceName());
			  }*/
			  
/*			  if(optDriverProfile.isPresent() && optVehicleProfile!=null && optVehicleProfile.isPresent()){
				  DriverProfile driverProfile = optDriverProfile.get();
				  VehicleProfile vehicleProfile = optVehicleProfile.get();
				  issuesList.add("Vehicle no "+vehicleProfile.getVehicleNo()+" with driver "+driverProfile.getDriverFirstName()+" "+driverProfile.getDriverLastName()+" facing "+issue.getCommands());
			  }else if(optDriverProfile.isPresent() && optVehicleProfile == null){
				  DriverProfile driverProfile = optDriverProfile.get();
				  issuesList.add("Vehicle no unknown with driver "+driverProfile.getDriverFirstName()+" "+driverProfile.getDriverLastName()+" facing "+issue.getCommands());
			  }else if(!optDriverProfile.isPresent() && optVehicleProfile != null && optVehicleProfile.isPresent()){
				  VehicleProfile vehicleProfile = optVehicleProfile.get();
				  issuesList.add("Vehicle no "+vehicleProfile.getVehicleNo()+" with driver unknown facing "+issue.getCommands());
			  }else{
				  issuesList.add("Vehicle no unknown with driver unknown facing "+issue.getCommands());
			  }*/
			  }
		        return new ResponseDTO(200, "Opened Issues data", issuesList);
		  }catch(Exception ex){
			  logger.error("Error while accessing driver issues with message {}", ex.getMessage());
		    	return new ResponseDTO(400, "Internel server with message : "+ex.getMessage());
		  }
		  
	  }
	  
	  @RequestMapping("/get-issues-closed")
	  @GET
	  @Path("/get-issues-closed")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO getAllClosedIssues() {
		  logger.debug("Request to access all closed issues");
		  List<Issues> issues = null;
		  
		  try{
			  issues = issuesService.findAllIssuesByType(IssueType.CLOSED);
		        return new ResponseDTO(200, "Opened Issues data", issues);
		  }catch(Exception ex){
			  logger.error("Error while accessing all opened issues with message {}", ex.getMessage());
		    	return new ResponseDTO(400, "Internel server with message : "+ex.getMessage());
		  }
		  
	  }
	  
	  @RequestMapping("/get-by-id/{id}")
	  @GET
	  @Path("/get-by-id/{id}")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO getByEmail(@PathVariable("id") String id) {
		  logger.debug("Request to access issue by id {}", id);
		  Optional<Issues> optIssues = issuesService.findOne(id);
		  
		  if(optIssues.isPresent()){
			  logger.debug("Issue exists with id {}", id);
	        return new ResponseDTO(200, "Issues data", optIssues.get());
	    }else{
			  logger.debug("No issue exists with id {}", id);	    	
	    	return new ResponseDTO(400, "No issue exists with id "+id);
	    }
	  }
	  
	  @RequestMapping("/save")
	  @POST
	  @Path("/save")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO create(@RequestBody IssuesDTO issuesDTO) { /*String email, String name*/

		  logger.debug("Request to save issue with uuid {}", issuesDTO.getUuid());
		  Issues issues = null;
		  
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	          Date today = Calendar.getInstance().getTime();
	    try {
	    	issues = new Issues(issuesDTO.getUuid(), issuesDTO.getStatus(), issuesDTO.getCommands(), issuesDTO.getType());
	    	issues.setCreatedDate(formatter.format(today));
	    	issues.setUpdatedDate(formatter.format(today));
	    	issuesService.save(issues);
	    }
	    catch(Exception ex) {
	      //return ex.getMessage();
	    	logger.error("Error while saving issue with uuid {} with message {}", issuesDTO.getUuid(), ex.getMessage());
	        return new ResponseDTO(500, ex.getStackTrace().toString());
	    }
	    return new ResponseDTO(200, "Issues successfully Registered", issues);
	  }
	  
	  @RequestMapping("/update/{id}")
	  @PUT
	  @Path("/update/{id}")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO update(@RequestBody IssuesDTO issuesDTO, @PathVariable("id") String id) {
		  
		  logger.debug("Request to update issue by id {}", id);
		  Optional<Issues> optIssue = issuesService.findOne(id);
		  
		  DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
          Date today = Calendar.getInstance().getTime();
		  if(optIssue.isPresent()){
			  logger.debug("Issue exists with id {}", id);
			  Issues existingIssue = optIssue.get();
			  existingIssue.setCommands(issuesDTO.getCommands());
			  existingIssue.setStatus(issuesDTO.getStatus());
			  existingIssue.setType(issuesDTO.getType());
			  existingIssue.setUuid(issuesDTO.getUuid());
			  existingIssue.setUpdatedDate(formatter.format(today));
			  issuesService.save(existingIssue);
			  
			  return new ResponseDTO(200, "Issue successfully Updated", existingIssue);
		  }else{
			  logger.debug("No issue exists with id {}", id);
			  return new ResponseDTO(400, "No issue exists with id "+id);
		  }
	  }
}

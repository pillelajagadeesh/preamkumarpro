package appraamlabs.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jta.atomikos.AtomikosLoggingLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.NestedServletException;

import appraamlabs.models.Beacon;
import appraamlabs.models.DriverProfile;
import appraamlabs.models.SerfsBoard;
import appraamlabs.service.BeaconService;
import appraamlabs.service.DriverProfileService;
import appraamlabs.service.dtos.DriverProfileDTO;
import appraamlabs.service.dtos.ResponseDTO;
import appraamlabs.service.impl.BeaconServiceImpl;
import appraamlabs.utils.enums.Status;

@RestController
@RequestMapping("/driverProfile")
public class DriverProfileController {

	@Autowired
	private DriverProfileService driverProfileService;
	
	@Autowired
	private BeaconService beaconService;
	
	private static Logger logger = LoggerFactory.getLogger(DriverProfileController.class);

	@RequestMapping("/delete/{id}")
	@DELETE
	@Path("/delete/(id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody ResponseDTO delete(@PathVariable("id") String id) {
		logger.debug("Request to delete driver  with employee id {}",id);
		
		Optional<DriverProfile> existingOplDriver = driverProfileService.getById(id);

		if (existingOplDriver.isPresent()) {
			DriverProfile existingDriver = existingOplDriver.get();
			driverProfileService.saveDriverProfile(existingDriver);
		} else {
			logger.error("Driver not exists with id {}",existingOplDriver.get().getEmpId());
			return new ResponseDTO(500, "Invalid request");
		}
		return new ResponseDTO(200, "driver details successfully deleted", existingOplDriver);
	}
	
	@RequestMapping("/search/{searchText}")
	  @GET
	  @Path("/search/{searchText}")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO searchDrivers(@PathVariable("searchText") String searchText) throws NestedServletException {
		  
		  logger.debug("Request to search drivers by text {}",searchText);
	    List<DriverProfile> drivers = null;
	    try {
	    	drivers = driverProfileService.searchDriver(searchText);
	    }
	    catch(Exception ex) {
	    	logger.error("Error while searing driver with name {} with message {}", searchText, ex.getMessage());
	      return new ResponseDTO(HttpStatus.BAD_REQUEST.value(), "driver not found");
	    }
	    return new ResponseDTO(HttpStatus.OK.value(), "driver details by search", drivers);
	  }
	
	
	@RequestMapping("/clean/{id}")
	@DELETE
	@Path("/clean/(id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody ResponseDTO clean(@PathVariable("id") String id) {
		logger.debug("Request to clean driver  with employee id {}",id);

		DriverProfile driverProfile = new DriverProfile(id);
		driverProfileService.deleteDriverProfile(driverProfile);

		return new ResponseDTO(200, "driver details successfully cleaned");
	}

	@RequestMapping("/get-by-id/{id}")
	@GET
	@Path("/get-by-id/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody ResponseDTO getById(@PathVariable("id") String id) throws NoSuchElementException {
		logger.debug("Request to get driver  with driver id {}",id);

		Optional<DriverProfile> driverProfile = null;
		try {
			driverProfile = driverProfileService.getById(id);
		} catch (Exception ex) {
			logger.error("Error with message {}",ex.getMessage());
			return new ResponseDTO(500, ex.getMessage());
		}
		return new ResponseDTO(200, "Driver data", driverProfile.get());
	}
	
	@RequestMapping("/get-by-empId/{empId}")
	@GET
	@Path("/get-by-empId/{empId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody ResponseDTO getByEmpId(@PathVariable("empId") String empId) throws NoSuchElementException {
		logger.debug("Request to get driver  with employee id {}",empId);
		Optional<DriverProfile> driverProfile = null;
		try {
			driverProfile = driverProfileService.getByEmpId(empId);
		} catch (Exception ex) {
			logger.error("Error with message {}",ex.getMessage());
			return new ResponseDTO(500, ex.getMessage());
		}
		return new ResponseDTO(200, "Driver data", driverProfile.get());
	}

	@RequestMapping("/save")
	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody ResponseDTO create(@RequestBody DriverProfileDTO driverProfileDTO) {
		  logger.debug("Request to register driver  with employee id {}",driverProfileDTO.getEmpId());
		
		Optional<DriverProfile> optionalDriverProfile = driverProfileService.getByEmpId(driverProfileDTO.getEmpId());
		
		if(optionalDriverProfile.isPresent()){
			
			 logger.error("Driver exists with employee id {}",optionalDriverProfile.get().getEmpId());
			return new ResponseDTO(400, "Driver already exists with employee id "+driverProfileDTO.getEmpId());
		}
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();

		
		DriverProfile driverProfile = null;
		try {
			driverProfile = new DriverProfile(driverProfileDTO.getMine(),
					driverProfileDTO.getEmpId(), driverProfileDTO.getDriverFirstName(),
					driverProfileDTO.getDriverLastName(), driverProfileDTO.getDriverRole(),
					driverProfileDTO.getContractAgency(), driverProfileDTO.getContractExpiryDate(),
					driverProfileDTO.getDriverLiscenceExpiryDate(), driverProfileDTO.getUploadDriverPhotocopy(), driverProfileDTO.getGender(),
					driverProfileDTO.getDateOfBirth(), driverProfileDTO.getMobileNo(), driverProfileDTO.getLicenseNo(),
					driverProfileDTO.getLicenseIssuedPlace(), driverProfileDTO.getUser());
			driverProfile.setDriverId("");
			driverProfile.setCreatedDate(formatter.format(today));
			driverProfile.setUpdatedDate(formatter.format(today));
/*			driverProfile.setStatus(Status.ACTIVE);*/
			driverProfile.setDriverStatus(driverProfileDTO.getDriverStatus());
			driverProfileService.saveDriverProfile(driverProfile);
		} catch (Exception ex) {
			// return ex.getMessage();
			logger.error("Error with message {}", ex.getMessage());
			return new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getStackTrace().toString());
		}
		return new ResponseDTO(HttpStatus.OK.value(), "DriverProfile successfully Registered with empId "+driverProfile.getEmpId(), driverProfile);
		
	}

	@RequestMapping("/getAll")
	@GET
	@Path("/getAll")
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody ResponseDTO getAll(Pageable pageable) throws NoSuchElementException {
		
		 logger.debug("Request to access all drivers");
		Page<DriverProfile> driverProfiles = null;
		//List<DriverProfile> driverProfilesnew = new ArrayList<DriverProfile>();
		
		try {
			driverProfiles = driverProfileService.getAllDriverProfiles(pageable);

/*			for(DriverProfile driverProfile: driverProfiles.getContent())
			{
				Optional<Beacon> beacon=beaconService.findBeaconByUuid(driverProfile.getDriverId());
				if(beacon.isPresent())
				{//logger.debug("beacon present {} " , beacon.get().getUuid());
					driverProfilesnew.add(driverProfile);
					
				}
				else
				{
					driverProfile.setDriverId("");
					driverProfileService.saveDriverProfile(driverProfile);
					driverProfilesnew.add(driverProfile);
				}
				
			}*/
			//logger.debug("existing" , driverProfiles);
			//logger.debug("new {} " , driverProfilesnew);
			/*Collections.sort (driverProfiles,
                    new Comparator<DriverProfile> ()
                    { public int compare ( DriverProfile a,  DriverProfile d)
                          { return (d.getUpdatedDate().compareTo(a.getUpdatedDate())); }});*/
		} catch (Exception ex) {
			logger.error("Error with message {}",ex.getMessage());
			return new ResponseDTO(500, ex.getMessage());
		}
		return new ResponseDTO(200, "Driver Details data", driverProfiles);
	}

	@RequestMapping("/getAllUnmappedDrivers")
	@GET
	@Path("/getAllUnmappedDrivers")
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody ResponseDTO getAllUnmappedDrivers(Pageable pageable) throws NoSuchElementException {
		
		 logger.debug("Request to access all drivers");
		Page<DriverProfile> driverProfiles = null;
		//List<DriverProfile> driverProfilesnew = new ArrayList<DriverProfile>();
		
		try {
			driverProfiles = driverProfileService.findAllMappedDrivers(pageable);
		} catch (Exception ex) {
			logger.error("Error with message {}",ex.getMessage());
			return new ResponseDTO(500, ex.getMessage());
		}
		return new ResponseDTO(200, "Unmapped Driver Details data", driverProfiles);
	}
	
	@RequestMapping("/update/{id}")
	@GET
	@Path("/update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody ResponseDTO update(@PathVariable("id") String id,
			@RequestBody DriverProfileDTO driverProfileDTO) throws NoSuchElementException {
		 logger.debug("Request to update driver by emp id {}", driverProfileDTO.getEmpId());
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();

		Optional<DriverProfile> existingOplDriver = driverProfileService.getById(id);
		

		DriverProfile existingDriver = null;
/*		if (existingOplDriver.isPresent() && existingOplDriver.get().getStatus().equals(Status.ACTIVE)) {*/
		if (existingOplDriver.isPresent()) {
			
		 existingDriver = existingOplDriver.get();
/*	        existingDriver.setStatus(Status.INACTIVE);*/
/*	        existingDriver.setUpdatedDate(new Date());*/
		  existingDriver.setMine(driverProfileDTO.getMine());
		  
	       // existingDriver.setEmpId(driverProfileDTO.getEmpId());
	        existingDriver.setDriverId(existingOplDriver.get().getDriverId());
	        existingDriver.setDriverFirstName(driverProfileDTO.getDriverFirstName());
	        existingDriver.setUser(driverProfileDTO.getUser());
	        existingDriver.setDriverLastName(driverProfileDTO.getDriverLastName());
	        existingDriver.setDriverRole(driverProfileDTO.getDriverRole());
	        existingDriver.setContractAgency(driverProfileDTO.getContractAgency());
	        existingDriver.setContractExpiryDate(driverProfileDTO.getContractExpiryDate());
	        existingDriver.setDriverLiscenceExpiryDate(driverProfileDTO.getDriverLiscenceExpiryDate());
	        existingDriver.setUploadDriverPhotocopy(driverProfileDTO.getUploadDriverPhotocopy());
	        existingDriver.setGender(driverProfileDTO.getGender());
	        existingDriver.setDateOfBirth(driverProfileDTO.getDateOfBirth());
	        existingDriver.setMobileNo(driverProfileDTO.getMobileNo());
	        existingDriver.setLicenseNo(driverProfileDTO.getLicenseNo());
	        existingDriver.setLicenseIssuedPlace(driverProfileDTO.getLicenseIssuedPlace());
	            existingDriver.setDriverStatus(driverProfileDTO.getDriverStatus());
	            existingDriver.setUpdatedDate(formatter.format(today));
			driverProfileService.saveDriverProfile(existingDriver);
			
		} else {
			  logger.error("No driver exists with active status to update with id {}", id);
			return new ResponseDTO(HttpStatus.BAD_REQUEST.value(), "No driver exists to update with empid "+id);
		}
		
		return new ResponseDTO(HttpStatus.OK.value(), "driver details successfully updated with empid "+existingDriver.getEmpId(), existingDriver);

	}

}

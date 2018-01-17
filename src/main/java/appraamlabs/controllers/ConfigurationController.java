package appraamlabs.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import appraamlabs.models.Configuration;
import appraamlabs.models.configuration.ConfigurationNotification;
import appraamlabs.models.configuration.ConfigurationStatus;
import appraamlabs.models.configuration.Latitude;
import appraamlabs.models.configuration.Longitude;
import appraamlabs.models.configuration.Markerposition;
import appraamlabs.service.ConfigurationService;
import appraamlabs.service.dtos.ConfigurationDTO;
import appraamlabs.service.dtos.ResponseDTO;
import appraamlabs.service.dtos.ConfigurationDTOs.ConfigurationNotificationDTO;
import appraamlabs.service.dtos.ConfigurationDTOs.ConfigurationStatusDTO;
import appraamlabs.service.dtos.ConfigurationDTOs.LatitudeDTO;
import appraamlabs.service.dtos.ConfigurationDTOs.LongitudeDTO;
import appraamlabs.service.dtos.ConfigurationDTOs.MarkerPositionDTO;

@RestController
@RequestMapping("/configuration")
public class ConfigurationController {

	@Autowired
	private ConfigurationService configurationService;
	
	@Autowired
	private Environment env;
	
	private static Logger logger = LoggerFactory.getLogger(ConfigurationController.class);
	
	@RequestMapping("/save")
	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody ResponseDTO create(@RequestBody ConfigurationDTO configurationDTO) {
		logger.debug("Request to save configuration");
		
		List<Configuration> optionalConfiguration = configurationService.findAll();
		
		Configuration configuration = null;
		
		ConfigurationNotification configurationNotification= this.getConfigurationNotificationFromConfigurationNotificationDTO(configurationDTO.getConfigurationNotificationDTO());
		Markerposition markerposition = this.getMarkerPositionFromMarkerPositionDTO(configurationDTO.getMarkerpositionDTO());
		List<ConfigurationStatus> listConfigurationStatus = this.getConfigurationStatusFromCOnfigurationStatus(configurationDTO.getConfigurationStatus());
		
		if(!optionalConfiguration.isEmpty()){
			configuration = optionalConfiguration.get(0);
			configuration.setConfigurationNotification(configurationNotification);
			configuration.setMarkerposition(markerposition);
			configuration.setConfigurationStatus(listConfigurationStatus);
			configuration = configurationService.save(configuration);
			return new ResponseDTO(200, "Configuration updated successfully ", configuration);
		}else{
			configuration = new Configuration(configurationNotification, markerposition, listConfigurationStatus);
			configuration = configurationService.save(configuration);
			return new ResponseDTO(200, "Configuration saved successfully ",configuration);
		}
		
	}
	
	@RequestMapping("/getAll")
	  @GET
	  @Path("/getAll")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO getAllConfigurations() throws NoSuchElementException{
		 logger.debug("Request to get all Configurations");
	    List<Configuration> configurations = null;
	    
	    try{
	    	configurations = configurationService.findAll();
	    }catch(Exception ex) {
	    	logger.error("Error with message {}",ex.getMessage());
		      return new ResponseDTO(500, ex.getMessage());
		    }
	    return new ResponseDTO(200, "Configuration's data", configurations);
	  }
	
	  @RequestMapping("/getAllConfigurationStatus")
	  @GET
	  @Path("/getAllConfigurationStatus")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO getAllConfigurationStatuses() throws NoSuchElementException{
		  logger.debug("Request to get all Configuration status");
	    List<Configuration> configurations = null;
	    List<ConfigurationStatus> configurationStatus = null;
	    
	    try{
	    	configurations = configurationService.findAll();
		    configurationStatus = configurations.get(0).getConfigurationStatus();
	    }catch(Exception ex) {
	    	logger.error("Error with message {}",ex.getMessage());
		      return new ResponseDTO(500, ex.getMessage());
		    }
	    return new ResponseDTO(200, "Configuration status data", configurationStatus);
	  }

		@RequestMapping("/saveConfigutaionStatus")
		@POST
		@Path("/saveConfigutaionStatus")
		@Consumes(MediaType.APPLICATION_JSON)
		public @ResponseBody ResponseDTO createConfigurationStatus(@RequestBody ConfigurationStatusDTO configurationStatusDTO) {
			logger.debug("Request to save configuration status");
			List<Configuration> optionalConfiguration = configurationService.findAll();
			
			Configuration configuration = null;
			ConfigurationStatus configurationStatus = getConfigurationStatusEqualToCode(optionalConfiguration.get(0).getConfigurationStatus(), configurationStatusDTO.getCode());
			if(configurationStatus!=null){
				return new ResponseDTO(400, "Configuration status already exists with status : "+configurationStatusDTO.getCode());
			}
			
/*			ConfigurationNotification configurationNotification= this.getConfigurationNotificationFromConfigurationNotificationDTO(configurationDTO.getConfigurationNotificationDTO());
			Markerposition markerposition = this.getMarkerPositionFromMarkerPositionDTO(configurationDTO.getMarkerpositionDTO());*/
			
			if(!optionalConfiguration.isEmpty()){
				configuration = optionalConfiguration.get(0);
				List<ConfigurationStatus> listConfigurationStatus = configuration.getConfigurationStatus();
				ConfigurationStatus configurationStatus2 = ConfigurationStatusDTOToConfigurationStatus(configurationStatusDTO);
				listConfigurationStatus.add(configurationStatus2);
				configuration.setConfigurationStatus(listConfigurationStatus);
				configuration = configurationService.save(configuration);
				return new ResponseDTO(200, "Configuration saved successfully ", configuration);
			}else{
				return new ResponseDTO(400, "No configuration exists to update");
			}
			
		}
		
	private ConfigurationNotification getConfigurationNotificationFromConfigurationNotificationDTO(ConfigurationNotificationDTO configurationNotificationDTO){		
		ConfigurationNotification configurationNotification = new ConfigurationNotification(configurationNotificationDTO.getMessage(), configurationNotificationDTO.getInterval());
		return configurationNotification;
	}
	
	private Markerposition getMarkerPositionFromMarkerPositionDTO(MarkerPositionDTO markerPositionDTO){
		Latitude latitude = this.getLatitudeFromLatitudeDTO(markerPositionDTO.getLatitudeDTO());
		Longitude longitude = this.getLongitudeFromLongitudeDTO(markerPositionDTO.getLongitudeDTO());		
		return new Markerposition(latitude, longitude);
	}
	
	private Latitude getLatitudeFromLatitudeDTO(LatitudeDTO latitudeDTO){
	    Latitude latitude = new Latitude(latitudeDTO.getFrom(), latitudeDTO.getTo());
		return latitude;
	}
	
	private Longitude getLongitudeFromLongitudeDTO(LongitudeDTO longitudeDTO){
	    Longitude longitude = new Longitude(longitudeDTO.getFrom(), longitudeDTO.getTo());
		return longitude;
	}
	
	private List<ConfigurationStatus> getConfigurationStatusFromCOnfigurationStatus(List<ConfigurationStatusDTO> listConfigurationDTO){
		
		List<ConfigurationStatus> listConfigurationStatus = new ArrayList<ConfigurationStatus>();
		
		for(ConfigurationStatusDTO configurationStatusDTO : listConfigurationDTO){
		ConfigurationStatus configurationStatus = new ConfigurationStatus(configurationStatusDTO.getConfigurationType(), configurationStatusDTO.getFromRange(), 
				configurationStatusDTO.getToRange(), configurationStatusDTO.getStatus(), configurationStatusDTO.getCode(), configurationStatusDTO.getNotification());
		listConfigurationStatus.add(configurationStatus);
		}
		return listConfigurationStatus;
	}
	
	private ConfigurationStatus getConfigurationStatusEqualToCode(List<ConfigurationStatus> configurationStatus, String code){
		
		for(ConfigurationStatus configurationStatus2 : configurationStatus){
			if(configurationStatus2.getCode().equals(code)){
				return configurationStatus2;
			}
		}
		return null;
	}
	
	private ConfigurationStatus ConfigurationStatusDTOToConfigurationStatus(ConfigurationStatusDTO configurationStatusDTO){
		
		ConfigurationStatus configurationStatus = new ConfigurationStatus(configurationStatusDTO.getConfigurationType(), configurationStatusDTO.getFromRange()
				, configurationStatusDTO.getToRange(), configurationStatusDTO.getStatus(), configurationStatusDTO.getCode(), configurationStatusDTO.getNotification());
		return configurationStatus;
	}
}
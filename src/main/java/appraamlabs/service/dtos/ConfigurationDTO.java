package appraamlabs.service.dtos;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import appraamlabs.service.dtos.ConfigurationDTOs.ConfigurationNotificationDTO;
import appraamlabs.service.dtos.ConfigurationDTOs.ConfigurationStatusDTO;
import appraamlabs.service.dtos.ConfigurationDTOs.MarkerPositionDTO;

public class ConfigurationDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("notification")
	private ConfigurationNotificationDTO configurationNotificationDTO;
	
	@JsonProperty("markerposition")
	private MarkerPositionDTO markerpositionDTO;
	
	@JsonProperty("status")
	private List<ConfigurationStatusDTO> configurationStatus;

	public ConfigurationNotificationDTO getConfigurationNotificationDTO() {
		return configurationNotificationDTO;
	}

	public void setConfigurationNotificationDTO(ConfigurationNotificationDTO configurationNotificationDTO) {
		this.configurationNotificationDTO = configurationNotificationDTO;
	}

	public MarkerPositionDTO getMarkerpositionDTO() {
		return markerpositionDTO;
	}

	public void setMarkerpositionDTO(MarkerPositionDTO markerpositionDTO) {
		this.markerpositionDTO = markerpositionDTO;
	}

	public List<ConfigurationStatusDTO> getConfigurationStatus() {
		return configurationStatus;
	}

	public void setConfigurationStatus(List<ConfigurationStatusDTO> configurationStatus) {
		this.configurationStatus = configurationStatus;
	}

	@Override
	public String toString() {
		return "ConfigurationDTO [configurationNotificationDTO=" + configurationNotificationDTO + ", markerpositionDTO="
				+ markerpositionDTO + ", configurationStatus=" + configurationStatus + "]";
	}

}

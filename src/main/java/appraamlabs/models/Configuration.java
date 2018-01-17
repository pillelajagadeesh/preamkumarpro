package appraamlabs.models;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import appraamlabs.models.configuration.ConfigurationNotification;
import appraamlabs.models.configuration.ConfigurationStatus;
import appraamlabs.models.configuration.Markerposition;

@Document(collection="configuration")
public class Configuration implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Field("notification")
	private ConfigurationNotification configurationNotification;
	
	@Field("markerposition")
	private Markerposition markerposition;
	
	@Field("status")
	private List<ConfigurationStatus> configurationStatus;
		
	public Configuration(ConfigurationNotification configurationNotification, Markerposition markerposition, List<ConfigurationStatus> configurationStatus){
		this.configurationNotification = configurationNotification;
		this.markerposition = markerposition;
		this.configurationStatus = configurationStatus;
	}
	
	public ConfigurationNotification getConfigurationNotification() {
		return configurationNotification;
	}

	public void setConfigurationNotification(ConfigurationNotification configurationNotification) {
		this.configurationNotification = configurationNotification;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<ConfigurationStatus> getConfigurationStatus() {
		return configurationStatus;
	}

	public void setConfigurationStatus(List<ConfigurationStatus> configurationStatus) {
		this.configurationStatus = configurationStatus;
	}

	public Markerposition getMarkerposition() {
		return markerposition;
	}

	public void setMarkerposition(Markerposition markerposition) {
		this.markerposition = markerposition;
	}

	@Override
	public String toString() {
		return "Configuration [id=" + id + ", configurationNotification=" + configurationNotification
				+ ", markerposition=" + markerposition + ", configurationStatus=" + configurationStatus + "]";
	}

	
}

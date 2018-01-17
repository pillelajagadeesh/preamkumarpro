package appraamlabs.service.dtos.ConfigurationDTOs;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConfigurationNotificationDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("Message")
	private String message;
	
	private long interval;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getInterval() {
		return interval;
	}

	public void setInterval(long interval) {
		this.interval = interval;
	}

	@Override
	public String toString() {
		return "ConfigurationNotificationDTO [message=" + message + ", interval=" + interval + "]";
	}

}

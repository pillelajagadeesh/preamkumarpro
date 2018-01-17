package appraamlabs.models.configuration;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

public class ConfigurationNotification implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Field("Message")
	private String message;
	
	@Field("intervel")
	private long interval;

	
	public ConfigurationNotification(String message, long interval) {
		//super();
		this.message = message;
		this.interval = interval;
	}

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
		return "ConfigurationNotification [message=" + message + ", interval=" + interval + "]";
	}
	
}

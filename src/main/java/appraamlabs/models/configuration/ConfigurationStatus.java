package appraamlabs.models.configuration;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

public class ConfigurationStatus implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Field("ConfigurationType")
	private String configurationType;
	
	@Field("FromRange")
	private String fromRange;
	
	@Field("ToRange")
	private String toRange;
	
	@Field("Status")
	private String status;
	
	@Field("Code")
	private String code;

	@Field("Notification")
	private boolean notification;
	
	public ConfigurationStatus(String configurationType, String fromRange, String toRange, String status, String code, boolean notification) {
		//super();
		this.configurationType = configurationType;
		this.fromRange = fromRange;
		this.toRange = toRange;
		this.status = status;
		this.code = code;
		this.notification = notification;
	}

	public String getConfigurationType() {
		return configurationType;
	}

	public void setConfigurationType(String configurationType) {
		this.configurationType = configurationType;
	}

	public String getFromRange() {
		return fromRange;
	}

	public void setFromRange(String fromRange) {
		this.fromRange = fromRange;
	}

	public String getToRange() {
		return toRange;
	}

	public void setToRange(String toRange) {
		this.toRange = toRange;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
	public boolean getNotification() {
		return notification;
	}

	public void setNotification(boolean notification) {
		this.notification = notification;
	}

	@Override
	public String toString() {
		return "ConfigurationStatus [configurationType=" + configurationType + ", fromRange=" + fromRange + ", toRange="
				+ toRange + ", status=" + status + ", code=" + code + ", notification=" + notification + "]";
	}

}

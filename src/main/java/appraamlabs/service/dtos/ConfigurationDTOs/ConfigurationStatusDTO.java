package appraamlabs.service.dtos.ConfigurationDTOs;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConfigurationStatusDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("configurationType")
	private String configurationType;

	private String fromRange;
	
	private String toRange;
	
	private String status;
	
	private String code;

	@JsonProperty("notification")
	private boolean notification;
	
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
		return "ConfigurationStatusDTO [configurationType=" + configurationType + ", fromRange=" + fromRange
				+ ", toRange=" + toRange + ", status=" + status + ", code=" + code + "]";
	}

}

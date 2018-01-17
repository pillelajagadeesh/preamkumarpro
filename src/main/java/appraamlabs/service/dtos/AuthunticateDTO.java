package appraamlabs.service.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class AuthunticateDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	  @NotNull
	  private String deviceId;
	  
	  @NotNull
	  private String email;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "AuthunticateDTO [deviceId=" + deviceId + ", email=" + email + "]";
	}

}

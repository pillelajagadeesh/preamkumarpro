package appraamlabs.service.dtos;

import java.io.Serializable;

public class UserDTO implements Serializable {
	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L; 

	  private String role;
	  
	  private String password;
	 
	  private String username;
	  
	  private String devicetoken;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDevicetoken() {
		return devicetoken;
	}

	public void setDevicetoken(String devicetoken) {
		this.devicetoken = devicetoken;
	}

	@Override
	public String toString() {
		return "UserDTO [role=" + role + ", password=" + password + ", username=" + username + ", devicetoken="
				+ devicetoken + "]";
	}
	 	  
}

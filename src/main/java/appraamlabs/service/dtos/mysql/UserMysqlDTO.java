package appraamlabs.service.dtos.mysql;

import java.io.Serializable;

public class UserMysqlDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String deviceToken;
	
	private String role;
	
	private String password;
	
	private String userName;

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "UserMysqlDTO [deviceToken=" + deviceToken + ", role=" + role + ", password=" + password + ", userName="
				+ userName + "]";
	}
	
}

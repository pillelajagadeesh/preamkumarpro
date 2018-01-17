package appraamlabs.models;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User implements Serializable{

  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;

  @Id
  private String id;
  
  private String role;
  
  private String password;
 
  private String username;
  
  private String devicetoken;
  
  public User(String id) { 
    this.id = id;
  }

  public User(String role, String password, String username, String devicetoken) {
	    this.role = role;
	    this.password = password;
	    this.username = username;
	    this.devicetoken = devicetoken;
	  }

  @PersistenceConstructor
  public User(String id,String role, String password, String username, String devicetoken) {
	   //super();
	    this.id = id;
	    this.role = role;
	    this.password = password;
	    this.username = username;
	    this.devicetoken = devicetoken;
	  }
  
public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
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
	return "User [id=" + id + ", role=" + role + ", password=" + password + ", username=" + username + ", devicetoken="
			+ devicetoken + "]";
}
  
} // class User

package appraamlabs.service.dtos;

import java.io.Serializable;

public class DriverBeaconDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String mine;
	private String user;	
	private String empId;
	private String driverFirstName;
	private String driverLastName;
	private String dateOfBirth;
	private String empType;
	private String contractAgency;
	private String driverId;
	
	public String getMine() {
		return mine;
	}
	public void setMine(String mine) {
		this.mine = mine;
	}
	
	public String getDriverFirstName() {
		return driverFirstName;
	}
	public void setDriverFirstName(String driverFirstName) {
		this.driverFirstName = driverFirstName;
	}
	public String getDriverLastName() {
		return driverLastName;
	}
	public void setDriverLastName(String driverLastName) {
		this.driverLastName = driverLastName;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getEmpType() {
		return empType;
	}
	public void setEmpType(String empType) {
		this.empType = empType;
	}
	public String getContractAgency() {
		return contractAgency;
	}
	public void setContractAgency(String contractAgency) {
		this.contractAgency = contractAgency;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
/*	public DriverBeaconDTO(String mine, String driverID, String firstName, String lastName, String empType,
			String contractAgency, String beaconID) {
		super();
		this.mine = mine;
		this.driverID = driverID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.empType = empType;
		this.contractAgency = contractAgency;
		this.beaconID = beaconID;
	}*/
	
}

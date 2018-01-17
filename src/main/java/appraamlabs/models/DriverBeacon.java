package appraamlabs.models;

import java.io.Serializable;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import appraamlabs.utils.enums.Status;

@Document(collection = "driver_beacons")
public class DriverBeacon implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String mine;
	private String empId;
	private String driverFirstName;
	private String driverLastName;
	private String dateOfBirth;
	private String empType;
	private String contractAgency;
	private String beaconID;
	private Status status;
	private String createdDate;
	private String updateDate;
	
	public DriverBeacon(String mine, String empId, String driverFirstName, String driverLastName, String empType,
			String contractAgency, String beaconID){
		this.mine = mine;
		this.empId = empId;
		this.driverFirstName = driverFirstName;
		this.driverLastName = driverLastName;
		this.empType = empType;
		this.contractAgency = contractAgency;
		this.beaconID = beaconID;
	}
	
/*	@PersistenceConstructor
	public DriverBeacon(String id, String mine, String firstName, String lastName, String empType,
			String contractAgency, String beaconID, Status status, Date createdDate, Date updatedDate){
		this.id = id;
		this.mine = mine;
		this.driverID = driverID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.empType = empType;
		this.contractAgency = contractAgency;
		this.beaconID = beaconID;
		this.status = status;
		this.createdDate = createdDate;
		this.updateDate = updatedDate;
	}*/
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMine() {
		return mine;
	}
	public void setMine(String mine) {
		this.mine = mine;
	}
	
	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
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
	public String getBeaconID() {
		return beaconID;
	}
	public void setBeaconID(String beaconID) {
		this.beaconID = beaconID;
	}
	
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "DriverBeacon [id=" + id + ", mine=" + mine + ", empId=" + empId + ", driverFirstName=" + driverFirstName
				+ ", driverLastName=" + driverLastName + ", dateOfBirth=" + dateOfBirth + ", empType=" + empType
				+ ", contractAgency=" + contractAgency + ", beaconID=" + beaconID + ", status=" + status
				+ ", createdDate=" + createdDate + ", updateDate=" + updateDate + "]";
	}
	
}

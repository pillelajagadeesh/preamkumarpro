package appraamlabs.models;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import appraamlabs.utils.enums.DriverRoleType;
import appraamlabs.utils.enums.DriverStatus;
import appraamlabs.utils.enums.Status;

@Document(collection = "driver_profile")
public class DriverProfile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String mine;
	private String user;
	private String empId;
	private String driverId;
	private String driverFirstName;
	private String driverLastName;
	private String gender;
	//private Date dateOfBirth;
	private String dateOfBirth;
	private long mobileNo;
	private DriverRoleType driverRole;
	private String contractAgency;
	//private Date contractExpiryDate;
	private String contractExpiryDate;
	private String licenseNo;
	private String licenseIssuedPlace;
	//private Date driverLiscenceExpiryDate;
	private String driverLiscenceExpiryDate;
	private String uploadDriverPhotocopy;
	private String createdDate;
	private String updatedDate;
	private DriverStatus driverStatus;
	
	public DriverProfile(){
		
	}
	
    public DriverProfile(String id){
		this.id = id;
	}

	public DriverProfile(String mine, String empId, String driverFirstName,
			String driverLastName, DriverRoleType driverRole, String contractAgency, String contractExpiryDate,
			String driverLiscenceExpiryDate, String uploadDriverPhotocopy, String gender, String dateOfBirth, long mobileNo,
			String licenseNo, String licenseIssuedPlace, String user) {
		//super();
		this.mine = mine;
		this.empId = empId;
		this.driverFirstName = driverFirstName;
		this.driverLastName = driverLastName;
		this.driverRole = driverRole;
		this.contractAgency = contractAgency;
		this.contractExpiryDate = contractExpiryDate;
		this.driverLiscenceExpiryDate = driverLiscenceExpiryDate;
		this.uploadDriverPhotocopy = uploadDriverPhotocopy;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.mobileNo = mobileNo;
		this.licenseNo = licenseNo;
		this.licenseIssuedPlace = licenseIssuedPlace;
		this.user = user;
	}

/*	@PersistenceConstructor
	public DriverProfile(String id,String mine, String empId, String driverId, String driverFirstName,
			String driverLastName, DriverRoleType driverRole, String contractAgency, String contractExpiryDate,
			String driverLiscenceExpiryDate, String uploadDriverPhotocopy, String gender, String dateOfBirth, long mobileNo,
			String licenseNo, String licenseIssuedPlace, Date createdDate, Date updatedDate,
			Status status, DriverStatus driverStatus) {
		this.id = id;
		this.mine = mine;
		this.empId = empId;
		this.driverId = driverId;
		this.driverFirstName = driverFirstName;
		this.driverLastName = driverLastName;
		this.driverRole = driverRole;
		this.contractAgency = contractAgency;
		this.contractExpiryDate = contractExpiryDate;
		this.driverLiscenceExpiryDate = driverLiscenceExpiryDate;
		this.uploadDriverPhotocopy = uploadDriverPhotocopy;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.mobileNo = mobileNo;
		this.licenseNo = licenseNo;
		this.licenseIssuedPlace = licenseIssuedPlace;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.status = status;
		this.driverStatus = driverStatus;
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

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
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

	public DriverRoleType getDriverRole() {
		return driverRole;
	}

	public void setDriverRole(DriverRoleType driverRole) {
		this.driverRole = driverRole;
	}

	public String getContractAgency() {
		return contractAgency;
	}

	public void setContractAgency(String contractAgency) {
		this.contractAgency = contractAgency;
	}

	public String getContractExpiryDate() {
		return contractExpiryDate;
	}

	public void setContractExpiryDate(String contractExpiryDate) {
		this.contractExpiryDate = contractExpiryDate;
	}

	public String getDriverLiscenceExpiryDate() {
		return driverLiscenceExpiryDate;
	}

	public void setDriverLiscenceExpiryDate(String driverLiscenceExpiryDate) {
		this.driverLiscenceExpiryDate = driverLiscenceExpiryDate;
	}

	public String getUploadDriverPhotocopy() {
		return uploadDriverPhotocopy;
	}

	public void setUploadDriverPhotocopy(String uploadDriverPhotocopy) {
		this.uploadDriverPhotocopy = uploadDriverPhotocopy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public DriverStatus getDriverStatus() {
		return driverStatus;
	}

	public void setDriverStatus(DriverStatus driverStatus) {
		this.driverStatus = driverStatus;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String getLicenseIssuedPlace() {
		return licenseIssuedPlace;
	}

	public void setLicenseIssuedPlace(String licenseIssuedPlace) {
		this.licenseIssuedPlace = licenseIssuedPlace;
	}
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "DriverProfile [id=" + id + ", mine=" + mine + ", empId=" + empId + ", driverId=" + driverId
				+ ", driverFirstName=" + driverFirstName + ", driverLastName=" + driverLastName + ", gender=" + gender
				+ ", dateOfBirth=" + dateOfBirth + ", mobileNo=" + mobileNo + ", driverRole=" + driverRole
				+ ", contractAgency=" + contractAgency + ", contractExpiryDate=" + contractExpiryDate + ", licenseNo="
				+ licenseNo + ", licenseIssuedPlace=" + licenseIssuedPlace + ", driverLiscenceExpiryDate="
				+ driverLiscenceExpiryDate + ", uploadDriverPhotocopy=" + uploadDriverPhotocopy + ", createdDate="
				+ createdDate + ", updatedDate=" + updatedDate + ", driverStatus=" + driverStatus + "]";
	}
	
	
}

package appraamlabs.service.dtos;

import java.io.Serializable;
import java.util.Date;

import appraamlabs.utils.enums.DriverRoleType;
import appraamlabs.utils.enums.DriverStatus;
import appraamlabs.utils.enums.Status;

public class DriverProfileDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String mine;
	private String empId;
	private String user;
/*	private String driverId;*/
	private String driverFirstName;
	private String driverLastName;
	private String gender;
	private String dateOfBirth;
	private long mobileNo;
	private DriverRoleType driverRole;
	private String contractAgency;
	private String contractExpiryDate;
	private String licenseNo;
	private String licenseIssuedPlace;
	private String driverLiscenceExpiryDate;
	private String uploadDriverPhotocopy;
	private DriverStatus driverStatus;

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
/*	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}*/
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
	
	public DriverStatus getDriverStatus() {
		return driverStatus;
	}
	public void setDriverStatus(DriverStatus driverStatus) {
		this.driverStatus = driverStatus;
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "DriverProfileDTO [mine=" + mine + ", empId=" + empId + ", user=" + user + ", driverFirstName="
				+ driverFirstName + ", driverLastName=" + driverLastName + ", gender=" + gender + ", dateOfBirth="
				+ dateOfBirth + ", mobileNo=" + mobileNo + ", driverRole=" + driverRole + ", contractAgency="
				+ contractAgency + ", contractExpiryDate=" + contractExpiryDate + ", licenseNo=" + licenseNo
				+ ", licenseIssuedPlace=" + licenseIssuedPlace + ", driverLiscenceExpiryDate="
				+ driverLiscenceExpiryDate + ", uploadDriverPhotocopy=" + uploadDriverPhotocopy + ", driverStatus="
				+ driverStatus + "]";
	}
	
}

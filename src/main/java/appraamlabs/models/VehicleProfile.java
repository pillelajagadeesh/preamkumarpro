package appraamlabs.models;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import appraamlabs.utils.enums.DriverRoleType;
import appraamlabs.utils.enums.Status;
import appraamlabs.utils.enums.VehicleStatus;

@Document(collection = "vehicle_profile")
public class VehicleProfile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String mine;
	private String user;
	private String vehicleNo;
	private String vehicleModel;
	@Field("serfId")
	private String serfName;
	private DriverRoleType roleType;
	private String contractAgency;
	//private Date contractExpiryDate;
	private String contractExpiryDate;
	private String registrationYear;
	private String make;
	
	private String fuelLevel;
	private String kmDriven;
	private String insuranceProvider;
	//private Date insuranceExpiryDate;
	private String insuranceExpiryDate;
/*	private Status status;*/
	private VehicleStatus vehicleStatus;
	private String createdDate;
	private String updatedDate;
	private String vehicleDoorNo;
	
	
	
	public VehicleProfile( String mine, String vehicleNo, String vehicleModel, String serfName,
			DriverRoleType roleType, String contractAgency, String contractExpiryDate, String registrationYear,
			String make,  String fuelLevel, String kmDriven, String insuranceProvider,
			String insuranceExpiryDate, VehicleStatus vehicleStatus, 
			String vehicleDoorNo, String user) {
		super();
		
		this.mine = mine;
		this.vehicleNo = vehicleNo;
		this.vehicleModel = vehicleModel;
		this.serfName = serfName;
		this.roleType = roleType;
		this.contractAgency = contractAgency;
		this.contractExpiryDate = contractExpiryDate;
		this.registrationYear = registrationYear;
		this.make = make;
		
		this.fuelLevel = fuelLevel;
		this.kmDriven = kmDriven;
		this.insuranceProvider = insuranceProvider;
		this.insuranceExpiryDate = insuranceExpiryDate;
		this.vehicleStatus = vehicleStatus;
		this.vehicleDoorNo = vehicleDoorNo;
		this.user = user;
	}

	/*@PersistenceConstructor
	public VehicleProfile(String id, String mine, String vehicleNo, String vehicleModel, String serfName, DriverRoleType roleType,
			String contractAgency, String contractExpiryDate,String registrationYear, String make, String model, String fuelLevel,
			String kmDriven, String insuranceProvider, String insuranceExpiryDate, VehicleStatus vehicleStatus, 
			Date createdDate, Date updatedDate) {
		super();
		this.id = id;
		this.mine = mine;
		this.vehicleNo = vehicleNo;
		this.vehicleModel = vehicleModel;
		this.serfName = serfName;
		this.roleType = roleType;
		this.contractAgency = contractAgency;
		this.contractExpiryDate = contractExpiryDate;
		this.registrationYear = registrationYear;
		this.make = make;
		this.model = model;
		this.fuelLevel = fuelLevel;
		this.kmDriven = kmDriven;
		this.insuranceProvider = insuranceProvider;
		this.insuranceExpiryDate = insuranceExpiryDate;
		this.vehicleStatus = vehicleStatus;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}*/
	
	
	public String getId() {
		return id;
	}

	public String getVehicleDoorNo() {
		return vehicleDoorNo;
	}

	public void setVehicleDoorNo(String vehicleDoorNo) {
		this.vehicleDoorNo = vehicleDoorNo;
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

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	public String getSerfName() {
		return serfName;
	}

	public void setSerfName(String serfName) {
		this.serfName = serfName;
	}

	public DriverRoleType getRoleType() {
		return roleType;
	}

	public void setRoleType(DriverRoleType roleType) {
		this.roleType = roleType;
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

	public String getRegistrationYear() {
		return registrationYear;
	}

	public void setRegistrationYear(String registrationYear) {
		this.registrationYear = registrationYear;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	

	public String getFuelLevel() {
		return fuelLevel;
	}

	public void setFuelLevel(String fuelLevel) {
		this.fuelLevel = fuelLevel;
	}

	public String getKmDriven() {
		return kmDriven;
	}

	public void setKmDriven(String kmDriven) {
		this.kmDriven = kmDriven;
	}

	public String getInsuranceProvider() {
		return insuranceProvider;
	}

	public void setInsuranceProvider(String insuranceProvider) {
		this.insuranceProvider = insuranceProvider;
	}

	public String getInsuranceExpiryDate() {
		return insuranceExpiryDate;
	}

	public void setInsuranceExpiryDate(String insuranceExpiryDate) {
		this.insuranceExpiryDate = insuranceExpiryDate;
	}

	public VehicleStatus getVehicleStatus() {
		return vehicleStatus;
	}

	public void setVehicleStatus(VehicleStatus vehicleStatus) {
		this.vehicleStatus = vehicleStatus;
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

	public VehicleProfile() {
		//super();
	}

	public VehicleProfile(String id){
		this.id = id;
	}
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "VehicleProfile [id=" + id + ", mine=" + mine + ", user=" + user + ", vehicleNo=" + vehicleNo
				+ ", vehicleModel=" + vehicleModel + ", serfName=" + serfName + ", roleType=" + roleType
				+ ", contractAgency=" + contractAgency + ", contractExpiryDate=" + contractExpiryDate
				+ ", registrationYear=" + registrationYear + ", make=" + make + ", fuelLevel="
				+ fuelLevel + ", kmDriven=" + kmDriven + ", insuranceProvider=" + insuranceProvider
				+ ", insuranceExpiryDate=" + insuranceExpiryDate + ", vehicleStatus=" + vehicleStatus + ", createdDate="
				+ createdDate + ", updatedDate=" + updatedDate + ", vehicleDoorNo=" + vehicleDoorNo + "]";
	}
			
}

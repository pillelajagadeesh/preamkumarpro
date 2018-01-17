package appraamlabs.service.dtos;

import java.io.Serializable;

import java.util.Date;

import appraamlabs.utils.enums.DriverRoleType;
import appraamlabs.utils.enums.Status;
import appraamlabs.utils.enums.VehicleStatus;

public class VehicleProfileDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String mine;
	private String user;
	private String vehicleNo;
	private String vehicleModel;
	private String serfName;
	private DriverRoleType roleType;
	private String contractAgency;
	private String contractExpiryDate;
	private String registrationYear;
	private String make;
	
	private String fuelLevel;
	private String kmDriven;
	private String insuranceProvider;
	private String insuranceExpiryDate;
/*	private Status status;*/
	private VehicleStatus vehicleStatus;
	private String vehicleDoorNo;
	
	
	
	public String getVehicleDoorNo() {
		return vehicleDoorNo;
	}
	public void setVehicleDoorNo(String vehicleDoorNo) {
		this.vehicleDoorNo = vehicleDoorNo;
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

/*	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}*/

	public VehicleStatus getVehicleStatus() {
		return vehicleStatus;
	}

	public void setVehicleStatus(VehicleStatus vehicleStatus) {
		this.vehicleStatus = vehicleStatus;
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "VehicleProfileDTO [mine=" + mine + ", user=" + user + ", vehicleNo=" + vehicleNo + ", vehicleModel="
				+ vehicleModel + ", serfName=" + serfName + ", roleType=" + roleType + ", contractAgency="
				+ contractAgency + ", contractExpiryDate=" + contractExpiryDate + ", registrationYear="
				+ registrationYear + ", make=" + make + ", fuelLevel=" + fuelLevel + ", kmDriven="
				+ kmDriven + ", insuranceProvider=" + insuranceProvider + ", insuranceExpiryDate=" + insuranceExpiryDate
				+ ", vehicleStatus=" + vehicleStatus + ", vehicleDoorNo=" + vehicleDoorNo + "]";
	}
	
}

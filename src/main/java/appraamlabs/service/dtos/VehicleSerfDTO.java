package appraamlabs.service.dtos;

import java.io.Serializable;

import appraamlabs.utils.enums.Status;

public class VehicleSerfDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String vehicleNo;
	private String driverID;
	private String serfsName;
	private String mine;
	private String vehicleModel;
	private String make;
	private Status status;
	
	public String getSerfsName() {
		return serfsName;
	}
	public void setSerfsName(String serfsName) {
		this.serfsName = serfsName;
	}
	public String getMine() {
		return mine;
	}
	public void setMine(String mine) {
		this.mine = mine;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public String getDriverID() {
		return driverID;
	}
	public void setDriverID(String driverID) {
		this.driverID = driverID;
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
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
		
}

package appraamlabs.models;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import appraamlabs.utils.enums.Status;

@Document(collection="vehicle_serf")
public class VehicleSerf implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String vehicleNo;
	private String driverID;
	private String serfsName;
	private String mine;
	private String vehicleModel;
	private String make;
	private Status status;
	private String createdDate;
	private String updateDate;
	
	public VehicleSerf(){
		
	}
	
	public VehicleSerf(String vehicleNo, String driverID, String serfsName, String mine, String vehicleModel,
			String make){
		this.vehicleNo = vehicleNo;
		this.driverID = driverID;
		this.serfsName = serfsName;
		this.mine = mine;
		this.vehicleModel = vehicleModel;
	    this.make = make;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getDriverID() {
		return driverID;
	}
	public void setDriverID(String driverID) {
		this.driverID = driverID;
	}
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

	@Override
	public String toString() {
		return "VehicleSerf [id=" + id + ", vehicleNo=" + vehicleNo + ", driverID=" + driverID + ", serfsName="
				+ serfsName + ", mine=" + mine + ", vehicleModel=" + vehicleModel + ", make=" + make + ", status="
				+ status + ", createdDate=" + createdDate + ", updateDate=" + updateDate + "]";
	}
	
	
}

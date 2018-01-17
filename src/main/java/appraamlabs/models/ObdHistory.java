package appraamlabs.models;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="obdhistory")
public class ObdHistory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    private String id;
	
	@Field(value="Serf_Id")
    private String serfId;
    
	@Field(value="Battery_life")
	private String batteryLife;
	
	@Field(value="DRIVER_ID")
    private String driverId;
    
	@Field(value="FUEL_TANK_LEVEL_INPUT")
	private String fuelTankLevelInput;
	
	@Field(value="VEHICLE_STATUS")
    private String vehicleStatus;
	
	@Field(value="ENGINE_RPM")
    private String engineRpm;
	
	@Field(value="DIST_TRAVELLED")
    private String distTravelled;
	
	@Field(value="MIL_ON")
    private String milOn;
	
	@Field(value="NUM_OF_DTC")
    private String numOfDtc;
	
	@Field(value="VEHICLE_SPEED")
	private String vehicleSpeed;
	
	@Field(value="TIME")
    private String time;
    
	@Field(value="FUEL_TYPE")
	private String fuelType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSerfId() {
		return serfId;
	}

	public void setSerfId(String serfId) {
		this.serfId = serfId;
	}

	public String getBatteryLife() {
		return batteryLife;
	}

	public void setBatteryLife(String batteryLife) {
		this.batteryLife = batteryLife;
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public String getFuelTankLevelInput() {
		return fuelTankLevelInput;
	}

	public void setFuelTankLevelInput(String fuelTankLevelInput) {
		this.fuelTankLevelInput = fuelTankLevelInput;
	}

	public String getVehicleStatus() {
		return vehicleStatus;
	}

	public void setVehicleStatus(String vehicleStatus) {
		this.vehicleStatus = vehicleStatus;
	}

	public String getEngineRpm() {
		return engineRpm;
	}

	public void setEngineRpm(String engineRpm) {
		this.engineRpm = engineRpm;
	}

	public String getDistTravelled() {
		return distTravelled;
	}

	public void setDistTravelled(String distTravelled) {
		this.distTravelled = distTravelled;
	}

	public String getMilOn() {
		return milOn;
	}

	public void setMilOn(String milOn) {
		this.milOn = milOn;
	}

	public String getNumOfDtc() {
		return numOfDtc;
	}

	public void setNumOfDtc(String numOfDtc) {
		this.numOfDtc = numOfDtc;
	}

	public String getVehicleSpeed() {
		return vehicleSpeed;
	}

	public void setVehicleSpeed(String vehicleSpeed) {
		this.vehicleSpeed = vehicleSpeed;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	@Override
	public String toString() {
		return "ObdHistory [id=" + id + ", serfId=" + serfId + ", batteryLife=" + batteryLife + ", driverId=" + driverId
				+ ", fuelTankLevelInput=" + fuelTankLevelInput + ", vehicleStatus=" + vehicleStatus + ", engineRpm="
				+ engineRpm + ", distTravelled=" + distTravelled + ", milOn=" + milOn + ", numOfDtc=" + numOfDtc
				+ ", vehicleSpeed=" + vehicleSpeed + ", time=" + time + ", fuelType=" + fuelType + "]";
	}
	
}

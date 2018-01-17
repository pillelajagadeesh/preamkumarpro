package appraamlabs.models.mysql;

import java.io.Serializable;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="obd")
public class ObdMysql implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	//@GeneratedValue
	@Column(name="_id", unique = true, nullable = false)
	private String id;
	
	@Column(name="Serf_Id")
	private String serfId;
	
	@Column(name="VEHICLE_SPEED")
	private int vehicleSpeed;
	
	@Column(name="DRIVER_ID")
	private String driverId;
	
	@Column(name="VEHICLE_STATUS")
	private int vehicleStatus;
	
	@Column(name="device_time_unix")
	private int deviceTimeUnix;
	
	@Column(name="device_time_ist")
	private Date deviceTimeIst;
	
	@Column(name="Battery_life")
	private String batteryLife;

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

	public int getVehicleSpeed() {
		return vehicleSpeed;
	}

	public void setVehicleSpeed(int vehicleSpeed) {
		this.vehicleSpeed = vehicleSpeed;
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public int getVehicleStatus() {
		return vehicleStatus;
	}

	public void setVehicleStatus(int vehicleStatus) {
		this.vehicleStatus = vehicleStatus;
	}

	public int getDeviceTimeUnix() {
		return deviceTimeUnix;
	}

	public void setDeviceTimeUnix(int deviceTimeUnix) {
		this.deviceTimeUnix = deviceTimeUnix;
	}

	public Date getDeviceTimeIst() {
		return deviceTimeIst;
	}

	public void setDeviceTimeIst(Date deviceTimeIst) {
		this.deviceTimeIst = deviceTimeIst;
	}

	public String getBatteryLife() {
		return batteryLife;
	}

	public void setBatteryLife(String batteryLife) {
		this.batteryLife = batteryLife;
	}

	@Override
	public String toString() {
		return "ObdMysql [id=" + id + ", serfId=" + serfId + ", vehicleSpeed=" + vehicleSpeed + ", driverId=" + driverId
				+ ", vehicleStatus=" + vehicleStatus + ", deviceTimeUnix=" + deviceTimeUnix + ", deviceTimeIst="
				+ deviceTimeIst + ", batteryLife=" + batteryLife + "]";
	}

}

package appraamlabs.service.dtos;

import java.io.Serializable;
import java.util.Date;

import appraamlabs.utils.enums.BeaconType;
import appraamlabs.utils.enums.DeviceStatus;

public class BeaconHawkeyeResponseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String macId;
	private String uuid;
	private int  major;
	private int  minor;
	private String beaconPeriod;
	private String txPower;
	private BeaconType type;
	private DeviceStatus status;
	private String createdDate;
	private String updatedDate;
	private String lat;
	private String lng;
	
	public String getMacId() {
		return macId;
	}
	public void setMacId(String macId) {
		this.macId = macId;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public int getMajor() {
		return major;
	}
	public void setMajor(int major) {
		this.major = major;
	}
	public int getMinor() {
		return minor;
	}
	public void setMinor(int minor) {
		this.minor = minor;
	}
	public String getBeaconPeriod() {
		return beaconPeriod;
	}
	public void setBeaconPeriod(String beaconPeriod) {
		this.beaconPeriod = beaconPeriod;
	}
	public String getTxPower() {
		return txPower;
	}
	public void setTxPower(String txPower) {
		this.txPower = txPower;
	}
	public BeaconType getType() {
		return type;
	}
	public void setType(BeaconType type) {
		this.type = type;
	}
	public DeviceStatus getStatus() {
		return status;
	}
	public void setStatus(DeviceStatus status) {
		this.status = status;
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
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	
}

package appraamlabs.models;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import appraamlabs.utils.enums.BeaconType;
import appraamlabs.utils.enums.DeviceStatus;

@Document(collection = "beacons")
public class Beacon implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String macId;
	private String uuid;
	private int  major;
	private int  minor;
	private String beaconPeriod;
	private String user;
	private String mine;
/*	private String distance;
	private String rssi;*/
	private String txPower;
/*	private String lastAliveOn;*/
	private BeaconType type;
	private DeviceStatus status;
	private String createdDate;
	private String updatedDate;

	public Beacon(String macId, String uuid, int major, int minor, String beaconPeriod, String txPower, String user, String mine){
		this.macId = macId;
		this.uuid = uuid;
		this.major = major;
		this.minor = minor;
		this.beaconPeriod = beaconPeriod;
		this.txPower = txPower;
		this.user = user;
		this.mine = mine;
	}

	/*@PersistenceConstructor
	public Beacon(String id, String macId, String uuid, int major, int minor, int beaconPeriod, int txPower, BeaconType type, DeviceStatus status, Date createdDate, Date updatedDate){
		this.id = id;
		this.macId = macId;
		this.uuid = uuid;
		this.major = major;
		this.minor = minor;
		this.beaconPeriod = beaconPeriod;
		this.txPower = txPower;
		this.type = type;
		this.status = status;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}*/
	
	public Beacon(){
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getMacId() {
		return macId;
	}

	public void setMacId(String macId) {
		this.macId = macId;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getMine() {
		return mine;
	}

	public void setMine(String mine) {
		this.mine = mine;
	}

	@Override
	public String toString() {
		return "Beacon [id=" + id + ", macId=" + macId + ", uuid=" + uuid + ", major=" + major + ", minor=" + minor
				+ ", beaconPeriod=" + beaconPeriod + ", user=" + user + ", mine=" + mine + ", txPower=" + txPower
				+ ", type=" + type + ", status=" + status + ", createdDate=" + createdDate + ", updatedDate="
				+ updatedDate + "]";
	}
	
}

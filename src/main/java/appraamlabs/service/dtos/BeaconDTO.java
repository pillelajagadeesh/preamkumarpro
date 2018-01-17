package appraamlabs.service.dtos;

import java.io.Serializable;

import appraamlabs.utils.enums.BeaconType;

public class BeaconDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String uuid;
	private String macId;
	private int  major;
	private int  minor;
	private String user;
	private String mine;
	private String beaconPeriod;
	private String txPower;
	private BeaconType type;
/*	private BeaconType type;*/
		
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
	/*public BeaconType getType() {
		return type;
	}
	public void setType(BeaconType type) {
		this.type = type;
	}*/
	public BeaconType getType() {
		return type;
	}
	public void setType(BeaconType type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "BeaconDTO [uuid=" + uuid + ", macId=" + macId + ", major=" + major + ", minor=" + minor + ", user="
				+ user + ", mine=" + mine + ", beaconPeriod=" + beaconPeriod + ", txPower=" + txPower + ", type=" + type
				+ "]";
	}
	
}

package appraamlabs.models;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="trips")
public class Trips implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	@Field("GPS_Time")
	private String gpsTime;
	
	@Field("Device_Time")
	private String deviceTime;
	
	@Field("Mac_ID")
	private String macId;
	
	@Field("driver")
	private String driver;
	
	@Field("Longitude")
	private String longitude;
	
	@Field("Exit")
	private String exit;
	
	@Field("Entry")
	private String entry;
	
	@Field("Minor")
	private String minor;
	
	@Field("Major")
	private String major;
	
	@Field("UUID")
	private String uuId;
	
	@Field("Latitiude")
	private String latitiude;
	
	@Field("Trips")
	private String trips;
	
	@Field("Unique_ID")
	private String uniqueID;
	
	@Field("GPS_Date")
	private String gpsDate;
	
	@Field("RSSI")
	private String rssi;

	public Trips(String gpsTime, String deviceTime, String macId, String driver, String longitude, String exit, String entry,
			String minor, String major, String uuId, String latitiude, String trips, String uniqueID, String gpsDate, String rssi){
		this.gpsTime = gpsTime;
		this.deviceTime = deviceTime;
		this.macId = macId;
		this.driver = driver;
		this.longitude = longitude;
		this.exit = exit;
		this.entry = entry;
		this.minor = minor;
		this.major = major;
		this.uuId = uuId;
		this.latitiude = latitiude;
		this.trips = trips;
		this.uniqueID = uniqueID;
		this.gpsDate = gpsDate;
		this.rssi = rssi;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGpsTime() {
		return gpsTime;
	}

	public void setGpsTime(String gpsTime) {
		this.gpsTime = gpsTime;
	}

	public String getDeviceTime() {
		return deviceTime;
	}

	public void setDeviceTime(String deviceTime) {
		this.deviceTime = deviceTime;
	}

	public String getMacId() {
		return macId;
	}

	public void setMacId(String macId) {
		this.macId = macId;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getExit() {
		return exit;
	}

	public void setExit(String exit) {
		this.exit = exit;
	}

	public String getEntry() {
		return entry;
	}

	public void setEntry(String entry) {
		this.entry = entry;
	}

	public String getMinor() {
		return minor;
	}

	public void setMinor(String minor) {
		this.minor = minor;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getUuId() {
		return uuId;
	}

	public void setUuId(String uuId) {
		this.uuId = uuId;
	}

	public String getLatitiude() {
		return latitiude;
	}

	public void setLatitiude(String latitiude) {
		this.latitiude = latitiude;
	}

	public String getUniqueID() {
		return uniqueID;
	}

	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}

	public String getGpsDate() {
		return gpsDate;
	}

	public void setGpsDate(String gpsDate) {
		this.gpsDate = gpsDate;
	}

	public String getRssi() {
		return rssi;
	}

	public void setRssi(String rssi) {
		this.rssi = rssi;
	}

	
	public String getTrips() {
		return trips;
	}

	public void setTrips(String trips) {
		this.trips = trips;
	}

	@Override
	public String toString() {
		return "Trips [id=" + id + ", gpsTime=" + gpsTime + ", deviceTime=" + deviceTime + ", macId=" + macId
				+ ", driver=" + driver + ", longitude=" + longitude + ", exit=" + exit + ", entry=" + entry + ", minor="
				+ minor + ", major=" + major + ", uuId=" + uuId + ", latitiude=" + latitiude + ", trips=" + trips
				+ ", uniqueID=" + uniqueID + ", gpsDate=" + gpsDate + ", rssi=" + rssi + "]";
	}

}

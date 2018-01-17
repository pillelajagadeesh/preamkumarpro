package appraamlabs.models;

import java.io.Serializable;

import java.util.Date;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import appraamlabs.utils.enums.DeviceStatus;

@Document(collection = "serfs")
public class SerfsBoard implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//@Id
	private String id;   //vehicleNumber
	private String mine;  //To which mine this serf belongs to
	private String user;
	private String uuid;
	private String macId;
	private String deviceName;
	private String ftpUrl; //This is a ftpUrl
	private String uid;
	private String major;
	private String minor;
	private String userName; //This is Driver details id to which driver it has to be maintained
	private String password;  //To which access card this serf belongs to
	private String syncTime;
	private String gprsConnection;  //boolean
	private int ftpTimeIntervel; //integer
	private int obdTimeIntervel;
	private String obdProtocol;
	private DeviceStatus status;
	private String createdDate;
	private String updatedDate;
	
	public SerfsBoard(){
		
	}
	
	/*@PersistenceConstructor
	public SerfsBoard(String id, String mineId, String uuid, String macId, String deviceName, String ftpUrl, String uid,
			double major, double minor, String userName, String password, String syncTime, boolean gprsConnection,int ftpTimeIntervel, 
			int obdTimeIntervel, String obdProtocol, DeviceStatus status, Date createdDate, Date updatedDate){
		super();
		this.id = id;
		this.mineId = mineId;
		this.uuid = uuid;
		this.macId = macId;
		this.deviceName = deviceName;
		this.uid = uid;
		this.ftpUrl = ftpUrl;
		this.userName = userName;		
		this.password = password;
		this.syncTime = syncTime;
		this.gprsConnection = gprsConnection;
		this.obdProtocol = obdProtocol;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.status = status;
		this.ftpTimeIntervel = ftpTimeIntervel;
		this.obdTimeIntervel = obdTimeIntervel;
		this.major = major;
		this.minor = minor;
	}*/
	
	public SerfsBoard(String uuid, String macId, String deviceName, String uid, String ftpUrl, String userName, String password,
			String syncTime, String gprsConnection, String obdProtocol, int ftpTimeIntervel, int obdTimeIntervel,
			String major, String minor,String user, String mine){
		this.uuid = uuid;
		this.macId = macId;
		this.deviceName = deviceName;
		this.uid = uid;
		this.ftpUrl = ftpUrl;
		this.userName = userName;		
		this.password = password;
		this.syncTime = syncTime;
		this.gprsConnection = gprsConnection;
		this.obdProtocol = obdProtocol;
		this.ftpTimeIntervel = ftpTimeIntervel;
		this.obdTimeIntervel = obdTimeIntervel;
		this.major = major;
		this.minor = minor;
		this.user = user;
		this.mine = mine;
	}

	public SerfsBoard(String macId){
		this.macId = macId;
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
	public String getMacId() {
		return macId;
	}
	public void setMacId(String macId) {
		this.macId = macId;
	}
	public String getMine() {
		return mine;
	}
	public void setMine(String mine) {
		this.mine = mine;
	}
	
	public String getFtpUrl() {
		return ftpUrl;
	}

	public void setFtpUrl(String ftpUrl) {
		this.ftpUrl = ftpUrl;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSyncTime() {
		return syncTime;
	}

	public void setSyncTime(String syncTime) {
		this.syncTime = syncTime;
	}

	public String getGprsConnection() {
		return gprsConnection;
	}

	public void setGprsConnection(String gprsConnection) {
		this.gprsConnection = gprsConnection;
	}

	public String getObdProtocol() {
		return obdProtocol;
	}

	public void setObdProtocol(String obdProtocol) {
		this.obdProtocol = obdProtocol;
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

	public DeviceStatus getStatus() {
		return status;
	}

	public void setStatus(DeviceStatus status) {
		this.status = status;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getMinor() {
		return minor;
	}

	public void setMinor(String minor) {
		this.minor = minor;
	}

	public int getFtpTimeIntervel() {
		return ftpTimeIntervel;
	}

	public void setFtpTimeIntervel(int ftpTimeIntervel) {
		this.ftpTimeIntervel = ftpTimeIntervel;
	}

	public int getObdTimeIntervel() {
		return obdTimeIntervel;
	}

	public void setObdTimeIntervel(int obdTimeIntervel) {
		this.obdTimeIntervel = obdTimeIntervel;
	}
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "SerfsBoard [id=" + id + ", mine=" + mine + ", user=" + user + ", uuid=" + uuid + ", macId=" + macId
				+ ", deviceName=" + deviceName + ", ftpUrl=" + ftpUrl + ", uid=" + uid + ", major=" + major + ", minor="
				+ minor + ", userName=" + userName + ", password=" + password + ", syncTime=" + syncTime
				+ ", gprsConnection=" + gprsConnection + ", ftpTimeIntervel=" + ftpTimeIntervel + ", obdTimeIntervel="
				+ obdTimeIntervel + ", obdProtocol=" + obdProtocol + ", status=" + status + ", createdDate="
				+ createdDate + ", updatedDate=" + updatedDate + "]";
	}
	
}

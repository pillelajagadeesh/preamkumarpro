package appraamlabs.service.dtos;

import java.io.Serializable;

public class SerfsBoardDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String mine;  //To which mine this serf belongs to
	private String user;	
	private String uuid;
	private String macId;
	private String deviceName;
	private String uid;
	private String major;
	private String minor;
	private String ftpUrl; //This is a ftpUrl
	private String userName; //This is Driver details id to which driver it has to be maintained
	private String password;  //To which access card this serf belongs to
	private String syncTime;
	private String gprsConnection; //boolean
	private int ftpTimeIntervel; //integer
	private int obdTimeIntervel;
	private String obdProtocol;
	
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
		return "SerfsBoardDTO [mine=" + mine + ", user=" + user + ", uuid=" + uuid + ", macId=" + macId
				+ ", deviceName=" + deviceName + ", uid=" + uid + ", major=" + major + ", minor=" + minor + ", ftpUrl="
				+ ftpUrl + ", userName=" + userName + ", password=" + password + ", syncTime=" + syncTime
				+ ", gprsConnection=" + gprsConnection + ", ftpTimeIntervel=" + ftpTimeIntervel + ", obdTimeIntervel="
				+ obdTimeIntervel + ", obdProtocol=" + obdProtocol + "]";
	}
	
}

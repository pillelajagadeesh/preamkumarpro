package appraamlabs.models;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import appraamlabs.utils.enums.CollectionName;

@Document(collection = "audits")
public class Audit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String refId;
	private CollectionName type;
	private String macId;
	private String date;
	private String userId;
	
	public Audit(String refId, CollectionName type, String date, String userId){
		this.refId = refId;
		this.type = type;
		this.date = date;
		this.userId = userId;
	}
	
	public Audit(){		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRefId() {
		return refId;
	}
	public void setRefId(String refId) {
		this.refId = refId;
	}
	public CollectionName getType() {
		return type;
	}
	public void setType(CollectionName type) {
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}	
	public String getMacId() {
		return macId;
	}
	public void setMacId(String macId) {
		this.macId = macId;
	}

	@Override
	public String toString() {
		return "Audit [id=" + id + ", refId=" + refId + ", type=" + type + ", macId=" + macId + ", date=" + date
				+ ", userId=" + userId + "]";
	}	
	
}

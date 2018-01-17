package appraamlabs.models;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import appraamlabs.utils.enums.IssueType;

public class Issues implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String uuid;
	private String status;
	private String commands;
	private IssueType type;
	private String createdDate;
	private String updatedDate;
	
	public Issues(String uuid, String status, String commands, IssueType type) {
		super();
		this.uuid = uuid;
		this.status = status;
		this.commands = commands;
		this.type = type;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCommands() {
		return commands;
	}
	public void setCommands(String commands) {
		this.commands = commands;
	}
	public IssueType getType() {
		return type;
	}
	public void setType(IssueType type) {
		this.type = type;
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
	@Override
	public String toString() {
		return "Issues [id=" + id + ", uuid=" + uuid + ", status=" + status + ", commands=" + commands + ", type="
				+ type + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
	}
	
}

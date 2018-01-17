package appraamlabs.service.dtos;

import java.io.Serializable;

import appraamlabs.utils.enums.IssueType;

public class IssuesDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String uuid;
	private String status;
	private String commands;
	private IssueType type;
	
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
	@Override
	public String toString() {
		return "IssuesDTO [uuid=" + uuid + ", status=" + status + ", commands=" + commands + ", type=" + type + "]";
	}

}

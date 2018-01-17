package appraamlabs.service.dtos;

import java.io.Serializable;

public class ErrorResponseDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer statusCode;
	  
	private String message;
	
	public ErrorResponseDTO(Integer statusCode, String message){
		this.statusCode = statusCode;
		this.message = message;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorResponseDTO [statusCode=" + statusCode + ", message=" + message + "]";
	}

}

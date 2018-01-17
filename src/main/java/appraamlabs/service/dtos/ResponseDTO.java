package appraamlabs.service.dtos;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseDTO implements Serializable{

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int statusCode;
	  
	private String message;
	
	private Object data;
	
	public ResponseDTO(int statusCode, String message, Object data){
		this.statusCode = statusCode;
		this.message = message;
		this.data = data;
	}

	
	public ResponseDTO(int statusCode, String message){
		this.statusCode = statusCode;
		this.message = message;
	}
	
	public ResponseDTO(){
		
	}
	
	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getObject() {
		return data;
	}

	public void setObject(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseDTO [statusCode=" + statusCode + ", message=" + message + ", data=" + data + "]";
	}
	
}

package appraamlabs.service.dtos.ConfigurationDTOs;

import java.io.Serializable;

public class LatitudeDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int from;
	
    private int to;

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
	}

	@Override
	public String toString() {
		return "LatitudeDTO [from=" + from + ", to=" + to + "]";
	}
    
}

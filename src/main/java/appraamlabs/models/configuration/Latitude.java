package appraamlabs.models.configuration;

import java.io.Serializable;

public class Latitude implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private int from;
	
    private int to;

	public Latitude(int from, int to) {
		//super();
		this.from = from;
		this.to = to;
	}

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
		return "Latitude [from=" + from + ", to=" + to + "]";
	}

}

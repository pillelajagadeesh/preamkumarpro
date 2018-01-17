package appraamlabs.models.configuration;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

public class Markerposition implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Field("lat")
	private Latitude latitude;
	
	@Field("long")
	private Longitude longitude;

	public Markerposition(Latitude latitude, Longitude longitude) {
		//super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Latitude getLatitude() {
		return latitude;
	}

	public void setLatitude(Latitude latitude) {
		this.latitude = latitude;
	}

	public Longitude getLongitude() {
		return longitude;
	}

	public void setLongitude(Longitude longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Markerposition [latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	
}

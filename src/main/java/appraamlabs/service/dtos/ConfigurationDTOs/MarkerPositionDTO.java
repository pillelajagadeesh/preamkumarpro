package appraamlabs.service.dtos.ConfigurationDTOs;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MarkerPositionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("lat")
	private LatitudeDTO latitudeDTO;
	
	@JsonProperty("long")	
	private LongitudeDTO longitudeDTO;

	public LatitudeDTO getLatitudeDTO() {
		return latitudeDTO;
	}

	public void setLatitudeDTO(LatitudeDTO latitudeDTO) {
		this.latitudeDTO = latitudeDTO;
	}

	public LongitudeDTO getLongitudeDTO() {
		return longitudeDTO;
	}

	public void setLongitudeDTO(LongitudeDTO longitudeDTO) {
		this.longitudeDTO = longitudeDTO;
	}

	@Override
	public String toString() {
		return "MarkerPositionDTO [latitudeDTO=" + latitudeDTO + ", longitudeDTO=" + longitudeDTO + "]";
	}
}

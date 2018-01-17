package appraamlabs.models;

import java.io.Serializable;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="obd")
public class Obd implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
/*	@Field("Serf_Id")*/
	private String Serf_Id;
	
/*	@Field("DRIVER_ID")*/
	private String DRIVER_ID;
	
/*	@Field("VEHICLE_STATUS")*/
	private String VEHICLE_STATUS;

/*	@Field("MIL_ON=1/OFF=0")
	private String milOnOff;
	
	@Field("DIST._TRAVELLED_CODE_CLRD")
	private String distTravelled;
	
	private String FUEL_TANK_LEVEL_INPUT;
	
	private String ENGINE_RPM;
	
	private String NUM_OF_DTC;
	
	@Field("PETROL/DIESEL=0/1")
	private String petrolDiesel;*/
	
	@Field("TIME")
	private String time;
	
	@Field("VEHICLE_SPEED")
	private String VEHICLE_SPEED;
	
	
/*	@PersistenceConstructor
	public Obd(String id, String Serf_Id, String DRIVER_ID, String VEHICLE_STATUS){
		this.id = id;
		this.Serf_Id = Serf_Id;
		this.DRIVER_ID = DRIVER_ID;
		this.VEHICLE_STATUS = VEHICLE_STATUS;
	}*/
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSerf_Id() {
		return Serf_Id;
	}

	public void setSerf_Id(String serf_Id) {
		Serf_Id = serf_Id;
	}

	public String getDRIVER_ID() {
		return DRIVER_ID;
	}

	public void setDRIVER_ID(String dRIVER_ID) {
		DRIVER_ID = dRIVER_ID;
	}

	public String getVEHICLE_STATUS() {
		return VEHICLE_STATUS;
	}

	public void setVEHICLE_STATUS(String vEHICLE_STATUS) {
		VEHICLE_STATUS = vEHICLE_STATUS;
	}
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getVEHICLE_SPEED() {
		return VEHICLE_SPEED;
	}

	public void setVEHICLE_SPEED(String vEHICLE_SPEED) {
		VEHICLE_SPEED = vEHICLE_SPEED;
	}

	@Override
	public String toString() {
		return "Obd [id=" + id + ", Serf_Id=" + Serf_Id + ", DRIVER_ID=" + DRIVER_ID + ", VEHICLE_STATUS="
				+ VEHICLE_STATUS + ", time=" + time + ", VEHICLE_SPEED=" + VEHICLE_SPEED + "]";
	}
	
}

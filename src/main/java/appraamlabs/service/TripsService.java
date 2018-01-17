package appraamlabs.service;

import java.util.List;
import java.util.Optional;

import com.mongodb.DBObject;

import appraamlabs.models.Trips;

public interface TripsService {

	public List<Trips> findAll();
	 
    public Optional<Trips> findTripsById(String id);
  	 	
 	public Optional<Trips> findTripsByMacId(String macId);
 	
    public List<Trips> findTripsByDriver(String driver);
 	
 	public Optional<Trips> findTripsByMacIdAndDriver(String macId, String driver);
 	
 	public Iterable<DBObject> getLatestTripsData();
 	
 	public Iterable<DBObject> getLatestTripsStatus();
 	
 	public Iterable<DBObject> getLatestObdStatus(String serfId);
 	
 	public Iterable<DBObject> getLatestTripsBydriverId(String driverId);
 	
	public Iterable<DBObject> getLatestTripsBySerfId(String serfId);
	
 	public Optional<Trips> findTripsByUniqueIDAndDeviceTime(String uniqueID, String deviceTime);
 	
 	public Optional<Trips> findTripsByUniqueIdAndTrips(String uniqueId, String trips);
}
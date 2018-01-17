package appraamlabs.service;

import java.util.List;
import java.util.Optional;

import appraamlabs.models.DriverBeacon;

public interface DriverBeaconService {

	public void deleteDriverBeacon(DriverBeacon deleted);
	 
	public Optional<DriverBeacon> findBeaconById(String beaconId);
	 	
	public void save(DriverBeacon driverBeacon);
	 	 	
	public Optional<DriverBeacon> findBydriverId(String driverId);
	 
	public Optional<DriverBeacon> findById(String id);
	
	public Optional<DriverBeacon> findDriverBeaconByEmpIdAndBeaconId(String empId, String beaconId);
	
	public List<DriverBeacon> findAll();
	
	public List<Object> getUnmappedDriverBeacons(List<String> beaconIds);
}

package appraamlabs.service;

import java.util.List;
import java.util.Optional;

import appraamlabs.models.VehicleSerf;

public interface VehicleSerfService {

	public void deleteDriverBeacon(VehicleSerf vehicleSerf);
	 
	public Optional<VehicleSerf> findVehicleSerfByVehicleNo(String vehicleNo);
	 	
	public void save(VehicleSerf vehicleSerf);
	 	 	
	public Optional<VehicleSerf> findVehicleSerfBySerfName(String serfName);
	 
	public Optional<VehicleSerf> findById(String id);
	
	public Optional<VehicleSerf> findVehicleSerfByVehicleNumberAndSerfName(String vehicleNumber, String serfName);
	
	public List<VehicleSerf> findAll();
}

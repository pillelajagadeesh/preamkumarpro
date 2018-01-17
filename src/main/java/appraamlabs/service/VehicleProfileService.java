package appraamlabs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import appraamlabs.models.VehicleProfile;

public interface VehicleProfileService {

	public VehicleProfile saveVehicleProfile(VehicleProfile vehicleProfile);
	
	public void deleteVehicleProfile(VehicleProfile vehicleProfile);
	
	public List<VehicleProfile> getAllVehicleProfiles();

	public Page<VehicleProfile> findAllVehicles(Pageable pageable);
	   
	public Optional<VehicleProfile> getById(String id);
	
/*	public Beacon update(Beacon beacon);*/
	
	public Optional<VehicleProfile> findVehicleProfileByVehicleNo(String vehicleNo);
	
	public Optional<VehicleProfile> findVehicleProfileBySerfName(String serfName);
	
/* 	public List<VehicleProfile> findUnmappedVehiclesByVehicleNo(List<String> vehiclesNos);*/

 	public List<VehicleProfile> findUnmappedVehiclesByVehicleNo();
 	
 	public List<VehicleProfile> searchVehicle(String serchText);
 	
    public Page<VehicleProfile> findAllMappedVehicles(Pageable pageable);
}

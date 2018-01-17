package appraamlabs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import appraamlabs.models.DriverProfile;

public interface DriverProfileService {

public DriverProfile saveDriverProfile(DriverProfile driverProfile);
	
	public void deleteDriverProfile(DriverProfile driverProfile);
	
	public List<DriverProfile> getAllDriverProfiles();
	
	public Page<DriverProfile> getAllDriverProfiles(Pageable pageable);

 	public List<DriverProfile> findUnmappedDriverProfilesByEmpId(List<String> driverIds);
 	
	public Optional<DriverProfile> getById(String id);
	
	public Optional<DriverProfile> getByEmpId(String EmpId);
	
	public Optional<DriverProfile> getByDriverId(String driverId);
	
	public List<DriverProfile> findEmptyDriverIds();

	public List<DriverProfile> searchDriver(String searchText);
	
    public Page<DriverProfile> findAllMappedDrivers(Pageable pageable);
}

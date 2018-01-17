package appraamlabs.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

import appraamlabs.models.DriverBeacon;
import appraamlabs.utils.enums.Status;

@Component
public interface DriverBeaconRepository extends MongoRepository<DriverBeacon, String> {

	public void delete(DriverBeacon deleted);
	 
	@Query(value="{status : ?0}")
    public List<DriverBeacon> findAll(Status status, Sort sort);
 
 	@Query(value="{beaconId : ?0, 'status' : ?1}")
    public Optional<DriverBeacon> findBeaconById(String beaconId, Status status);
 	
 	public DriverBeacon save(DriverBeacon driverBeacon);
 	 	
 	@Query(value="{driverId : ?0, 'status' : ?1}")
 	public Optional<DriverBeacon> findBydriverId(String driverId,  Status status);

 	@Query(value="{id : ?0, 'status' : ?1}")
 	public Optional<DriverBeacon> findById(String id,  Status status);
 
 	@Query(value="{id : ?0}")
 	public void delete(String id);
 	
 	@Query(value="{empId : ?0, 'beaconId' : ?1, 'status' : ?2}")
 	public Optional<DriverBeacon> findDriverBeaconByEmpIdAndBeaconId(String empId, String beaconId, Status status);
 
}

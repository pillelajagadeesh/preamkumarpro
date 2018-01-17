package appraamlabs.repositories;

import java.util.List;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

import appraamlabs.models.Beacon;
import appraamlabs.models.DriverProfile;
import appraamlabs.models.SerfsBoard;
import appraamlabs.models.VehicleProfile;
import appraamlabs.utils.enums.BeaconType;
import appraamlabs.utils.enums.DeviceStatus;
import appraamlabs.utils.enums.Status;

@Component
public interface DriverProfileRepository extends MongoRepository<DriverProfile, Long> {

	public void delete(DriverProfile driverProfile);
	 
/*	@Query(value="{status : ?0}")*/
    public List<DriverProfile> findAll(Sort sort); 
    
    public Page<DriverProfile> findAll(Pageable pageable);
    
    @Query(value="{driverId : {$ne : ''}}")
    public Page<DriverProfile> findAllMappedDrivers(Pageable pageable);
    
 	@Query(value="{id : ?0}")
    public Optional<DriverProfile> findOne(String id);
 
 	@Query(value="{empId : {$nin : ?0}}") 
 	public List<DriverProfile> findUnmappedDriverProfilesByEmpId(List<String> empIds);
 	
 	public DriverProfile save(DriverProfile driverProfile);
 	
/* 	public DriverProfile updateDriverProfile(DriverProfile user);*/
 	
 	@Query(value="{empId : ?0}")
 	public Optional<DriverProfile> findDriverProfileByEmpId(String empId);
 	
 	@Query(value="{driverId : ?0}")
 	public Optional<DriverProfile> findDriverProfileByDriverId(String driverId);
 	
 	@Query(value="{driverId : ''}")
 	public List<DriverProfile> findEmptyDriverIds();

 		
 	@Query(value="{ $and: [ { $or: [ {empId: {$regex : ?0}},{driverId: {$regex : ?0}},{driverFirstName: {$regex:?0}},"
 			+ " {driverLastName: {$regex:?0}}, {gender: {$regex:?0}}, {dateOfBirth: {$regex:?0}}, {driverRole: {$regex:?0}},"
 			+ "{contractAgency: {$regex:?0}},{licenseNo: {$regex:?0}},{contractExpiryDate: {$regex:?0}},{licenseIssuedPlace: {$regex:?0}},"
 			+ "{driverLiscenceExpiryDate: {$regex:?0}},{driverStatus: {$regex:?0}}   ]} ] }")
	public List<DriverProfile> searchDriver(String searchText, Sort sort);
	
}

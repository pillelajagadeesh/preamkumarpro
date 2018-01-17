package appraamlabs.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

import appraamlabs.models.VehicleProfile;
import appraamlabs.utils.enums.VehicleStatus;

@Component
public interface VehicleProfileRepository extends MongoRepository<VehicleProfile, Long> {

	public void delete(VehicleProfile vehicleProfile);
	
	//@Query(value="{status : ?0}")
    public List<VehicleProfile> findAll(Sort sort);
 
    public Page<VehicleProfile> findAll(Pageable pageable);
    
	@Query(value="{serfName : {$ne : ''}}")
    public Page<VehicleProfile> findAllMappedVehicles(Pageable pageable);
	
 	@Query(value="{id : ?0}")
    public Optional<VehicleProfile> findOne(String id);

 	public VehicleProfile save(VehicleProfile vehicleProfile);
 	 	
 	@Query(value="{vehicleNo : ?0}") /*fields="{email : 1}"*/
 	public Optional<VehicleProfile> findVehicleProfileByVehicleNo(String vehicleNo);
 	
 	@Query(value="{serfName : ?0, vehicleStatus : ?1}")
 	public Optional<VehicleProfile> findVehicleProfileBySerfName(String serfName, VehicleStatus vehicleStatus);
 	
/* 	@Query(value="{vehicleNo : {$nin : ?0}}")
 	public List<VehicleProfile> findUnmappedVehiclesByVehicleNo(List<String> vehiclesNos);*/

 	@Query(value="{serfName : ''}")
 	public List<VehicleProfile> findUnmappedVehiclesByVehicleNo();
 	
 	@Query(value="{ $and: [ { $or: [ {vehicleNo: {$regex : ?0}},{vehicleModel: {$regex : ?0}},{roleType: {$regex:?0}},"
 			+ " {contractAgency: {$regex:?0}}, {make: {$regex:?0}}, {serfId: {$regex:?0}}, {vehicleDoorNo: {$regex:?0}}   ]}] }")
 	public List<VehicleProfile> searchVehicle(String serchText,Sort sort);
}

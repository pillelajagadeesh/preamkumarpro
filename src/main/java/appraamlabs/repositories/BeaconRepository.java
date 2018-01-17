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
import appraamlabs.models.DriverBeacon;
import appraamlabs.models.SerfsBoard;
import appraamlabs.utils.enums.BeaconType;
import appraamlabs.utils.enums.DeviceStatus;

@Component
public interface BeaconRepository extends MongoRepository<Beacon, String> {

	public void delete(Beacon deleted);
	 
	@Query(value="{status : 'ACTIVE'}")
    public List<Beacon> findAll();
 
 	@Query(value="{id : ?0}")
    public Optional<Beacon> findBeaconById(String id);
 
 	/*@Query(value="{uuid : ?0}")
    public Optional<Beacon> findBeaconByUuid(String uuid);*/
 	
 	@Query(value="{uuid : ?0, status : ?1}")
    public Optional<Beacon> findBeaconByUuid(String uuid, DeviceStatus status);
 	
 	@Query(value="{uuid : ?0, 'status' : ?1, 'type' : {$ne: ?2}}")
    public Optional<Beacon> findBeaconByUuid(String uuid, DeviceStatus status, BeaconType type);
 	
 	
 	
 	@Query(value="{uuid : {$nin : ?0}, 'type' : ?1, 'status' : ?2}")
 	public List<Beacon> findUnmappedBeaconsByUuid(List<String> beaconIds, BeaconType type, DeviceStatus status);
 	
 	public Beacon save(Beacon user);
 	 	
 	@Query(value="{macId : ?0, 'status' : ?1}")
 	public Optional<Beacon> findBeaconByMacId(String macId, DeviceStatus status);
 
	@Query(value="{macId : ?0, 'status' : ?1, 'type' : {$ne: ?2}}")
 	public Optional<Beacon> findBeaconByMacId(String macId, DeviceStatus status, BeaconType type);
	
 	@Query(value="{id : ?0}")
 	public void delete(String id);
 	
/* 	@Query(value="{'type' : ?0, 'status' : ?1, {'sort' : [['updatedDate', '-1']]}}")*//*'updatedDate' : {$exists:true}  '$orderby' : {updatedDate : -1}}*/
 	@Query("{'type' : ?0, 'status' : ?1}")
    public Page<Beacon> findAllByType(BeaconType type, DeviceStatus status, Pageable pageable);
 	
 	@Query("{'type' : {$ne: ?0}, 'status' : ?1}")
 	public List<Beacon> findAllWpBeacons(BeaconType type, DeviceStatus status, Sort sort);
 	
 	@Query("{'type' : {$ne: ?0}, 'status' : ?1}")
 	public Page<Beacon> findAllWpBeacons(BeaconType type, DeviceStatus status, Pageable pageable);
 	
 	@Query(value="{ $and: [ { $or: [ {macId: {$regex : ?0}},{uuid: {$regex:?0}}]}, {status : ?1}, {type : {$ne: ?2} }] }")
 	public List<Beacon> searchWPBeacons(String serchText, DeviceStatus status, BeaconType type, Sort sort);
 	
 	@Query(value="{ $and: [ { $or: [ {macId: {$regex : ?0}},{uuid: {$regex:?0}}]}, {status : ?1}, {type : ?2}] }")
 	public List<Beacon> searchDBeacons(String serchText, DeviceStatus status, BeaconType type, Sort sort);
 	
}



package appraamlabs.repositories;

import java.util.List;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

import appraamlabs.models.SerfsBoard;
import appraamlabs.utils.enums.DeviceStatus;

@Component
//@RepositoryRestResource(collectionResourceRel = "serfs", path = "serfs")
public interface SerfBoardRepository extends MongoRepository<SerfsBoard, String> {

	public void delete(SerfsBoard serfsBoard);
	 
/*	@Query(value="{status : ?0}")
    public List<SerfsBoard> findAll(DeviceStatus status, Sort sort);*/
 
	@Query(value="{status : ?0}")
    public Page<SerfsBoard> findAll(DeviceStatus status, Pageable pageable);
	
 	@Query(value="{id : ?0}")
    public Optional<SerfsBoard> findSerfsBoardById(String id);
 
 	public SerfsBoard save(SerfsBoard serfsBoard);
 	
 	//public SerfsBoard updateObject(SerfsBoard serfsBoard);
 	
 	@Query(value="{macId : ?0}") /*fields="{email : 1}"*/
 	public Optional<SerfsBoard> findSerfsBoardByMacId(String macId);

 	@Query(value="{uuid : ?0, status : ?1}")
 	public Optional<SerfsBoard> findByUuid(String uuid, DeviceStatus status);
 	
 	@Query(value="{id : ?0}")
 	public void delete(String id);

 	@Query(value="{macId : ?0, status : ?1}")
 	public Optional<SerfsBoard> findOne(String macId, DeviceStatus status);
 	
 	@Query(value="{deviceName : ?0, status : ?1}")
 	public Optional<SerfsBoard> findOneByDeviceName(String deviceName, DeviceStatus status);
 	
 	@Query(value="{deviceName : {$nin : ?0}, 'status' : ?1}")
 	public List<SerfsBoard> findUnmappedSerfsByDeviveNames(List<String> deviceNames, DeviceStatus status);
 	
 	@Query(value="{ $and: [ { $or: [ {deviceName: {$regex : ?0}},{macId: {$regex : ?0}},{uuid: {$regex:?0}},"
 			+ " {uid: {$regex:?0}}, {ftpUrl: {$regex:?0}}, {userName: {$regex:?0}}]}, {status : ?1}] }")
 	public List<SerfsBoard> searchSerfsBoard(String serchText, DeviceStatus status, Sort sort);
}

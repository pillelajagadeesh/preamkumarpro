package appraamlabs.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import appraamlabs.models.Audit;

public interface AuditRepository extends MongoRepository<Audit, String> {
	
	public void delete(Audit deleted);
	 
    public List<Audit> findAll();
 
 	@Query(value="{id : ?0}")
    public Optional<Audit> findAuditById(String id);

 	@Query(value="{macId : ?0}")
 	public Optional<Audit> findAuditByMacId(String macId);
 
 	public Audit save(Audit audit);
 	 	
}

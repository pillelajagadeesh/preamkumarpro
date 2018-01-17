package appraamlabs.repositories;

import java.util.List;
import java.util.Optional;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

import appraamlabs.models.Issues;
import appraamlabs.models.SerfsBoard;
import appraamlabs.utils.enums.IssueType;

@Component
public interface IssuesRepository extends MongoRepository<Issues, Long> {

	public void delete(Issues deleted);

    public List<Issues> findAll();

 	@Query(value="{id : ?0}")
    public Optional<Issues> findOne(String id);
 
 	@Query(value="{type : ?0}")
 	public List<Issues> findAllIssuesByType(IssueType type);
 	
 	@Query(value="{type : ?0}")
 	public List<Issues> findLatestIssuesByType(IssueType type, Pageable pageable);
 	
 	public Issues save(Issues issues);
 	 	
 	@Query(value="{uuid : ?0, commands : ?1}") /*fields="{email : 1}"*/
 	public Optional<Issues> findIssuesByUuidAndCommands(String uuid, String commands);
 	
 	@Query(value="{uuid : ?0}") /*fields="{email : 1}"*/
 	public Optional<Issues> findIssuesByUuid(String uuid);
}

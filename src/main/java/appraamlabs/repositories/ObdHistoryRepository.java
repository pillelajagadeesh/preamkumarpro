package appraamlabs.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import appraamlabs.models.ObdHistory;

@Component
public interface ObdHistoryRepository extends MongoRepository<ObdHistory, String> {

	public List<ObdHistory> findAll();
	
	@Query(value="serfId : ?0, time : ?1")
	public Optional<ObdHistory> findOneBySerfIdAndTime(String serfId, String time);
	
}

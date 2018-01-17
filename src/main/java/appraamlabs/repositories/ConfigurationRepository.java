package appraamlabs.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

import appraamlabs.models.Configuration;

@Component
public interface ConfigurationRepository extends MongoRepository<Configuration, String> {

	public void delete(Configuration configuration);
	
    public List<Configuration> findAll();
 
 	@Query(value="{id : ?0}")
    public Optional<Configuration> findOneById(String id);

 	public Configuration save(Configuration configuration);
 	 
}

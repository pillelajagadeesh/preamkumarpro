package appraamlabs.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

import appraamlabs.models.Trips;

@Component
public interface TripsRepository extends MongoRepository<Trips, String> {
	
    	 public List<Trips> findAll();
	 
	 	@Query(value="{id : ?0}")
	    public Optional<Trips> findTripsById(String id);
	  	 	
	 	@Query(value="{macId : ?0}")
	 	public Optional<Trips> findTripsByMacId(String macId);
	 	
	 	@Query(value="{'driver' : ?0}")
	    public List<Trips> findTripsByDriver(String driver);
	 	
	 	@Query(value="{macId : ?0, driver : ?1}")
	 	public Optional<Trips> findTripsByMacIdAndDriver(String macId, String driver);

	 	@Query(value="{uniqueID : ?0, deviceTime : ?1}")
	 	public Optional<Trips> findTripsByUniqueIDAndDeviceTime(String uniqueID, String deviceTime);
	 	
	 	@Query(value="{uniqueID : ?0, trips : ?1}")
	 	public Optional<Trips> findTripsByUniqueIdAndTrips(String uniqueId, String trips);
}

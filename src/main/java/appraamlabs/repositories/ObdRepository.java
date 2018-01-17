package appraamlabs.repositories;

import java.util.List;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

import appraamlabs.models.Obd;

@Component
public interface ObdRepository extends MongoRepository<Obd, String> {
	
    public List<Obd> findAll();
 
 	@Query(value="{id : ?0}")
    public Optional<Obd> findObdById(String id);
  	 	
 	@Query(value="{serfId : ?0}")
 	public Optional<Obd> findObdBySerfId(String serfId);
 	
 	@Query(value="{'driverId' : ?0}")
    public List<Obd> findObdByDriverId(String driverId);
 	
 	@Query(value="{serfId : ?0, driverId : ?1}")
 	public Optional<Obd> findObdBySerfIdAndDriverId(String serfId, String driverId);

 	@Query(value="{Serf_Id : ?0, time : ?1}")
 	public Optional<Obd> findObdBySerfIdAndTime(String serfId, String time);
 	
 	/*public static List<Obd> aggregationByAll() {
 	  Aggregation aggregation = newAggregation(
 	    group("_id","Serf_Id").sum("numberOfCars").as("total"),
 	    sort(Sort.Direction.ASC, previousOperation() "brand")    
 	  );

 	  AggregationResults groupResults = mongoTemplate.aggregate(
 	    aggregation, DriverProfile.class, SerfsBoard.class);
 	  
 	  List<Obd> salesReport = groupResults.getMappedResults();

 	  return salesReport;
 	 }*/

 	/*GroupOperation getGroupOperation() {
	    return group("warehouse")
	        .last("warehouse").as("warehouse")
	        .addToSet("id").as("productIds")
	        .avg("price").as("averagePrice")
	        .sum("price").as("totalRevenue");
	}*/
 	
 	@Query(value="[{$group:{_id:'$Serf_Id',status:{$first:'$VEHICLE_STATUS'},speed:{$first:'$VEHICLE_SPEED'}"
			+ ",lastTripData:{$last:'$time'}}},{$lookup:{from: 'driver_profile', localField: 'DRIVER_ID',foreignField: 'driverId',"+
	          "as: 'driverInfo'}},{"+
	      "$lookup:{from: 'serfs',localField: 'Serf_Id',"+
	          "foreignField: 'Uid',as: 'driverInfo'}}]")
 	public List<Obd> findByAggregation();
 	
/* 	public List<Obd> findAllObd(org.springframework.data.mongodb.core.query.Query query);*/
 	
/* 	public List<Obd> findObdByTime(BasicQuery query);*/
 }

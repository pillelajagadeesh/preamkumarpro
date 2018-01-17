package appraamlabs.service.impl;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import appraamlabs.models.Trips;
import appraamlabs.repositories.TripsRepository;
import appraamlabs.service.TripsService;

@Service
public class TripsServiceImpl implements TripsService {

	@Autowired
	private TripsRepository tripsRepository;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	private static final Logger logger = LoggerFactory.getLogger(TripsServiceImpl.class);
	
	@Override
	public List<Trips> findAll() {
		// TODO Auto-generated method stub
		return tripsRepository.findAll();
	}

	@Override
	public Optional<Trips> findTripsById(String id) {
		// TODO Auto-generated method stub
		return tripsRepository.findTripsById(id);
	}

	@Override
	public Optional<Trips> findTripsByMacId(String macId) {
		// TODO Auto-generated method stub
		return tripsRepository.findTripsByMacId(macId);
	}

	@Override
	public List<Trips> findTripsByDriver(String driver) {
		// TODO Auto-generated method stub
		return tripsRepository.findTripsByDriver(driver);
	}

	@Override
	public Optional<Trips> findTripsByMacIdAndDriver(String macId, String driver) {
		// TODO Auto-generated method stub
		return tripsRepository.findTripsByMacIdAndDriver(macId, driver);
	}

 	public Iterable<DBObject> getLatestTripsData(){
 		
		 DBCollection tripsTable = mongoTemplate.getCollection("trips");
 		
		@SuppressWarnings("deprecation")
		AggregationOutput output = tripsTable.aggregate(new BasicDBObject("$group",
                new BasicDBObject("_id", "$Unique_ID")
                .append("driverId", new BasicDBObject("$last" , "$driver"))
                .append("trips", new BasicDBObject("$last", "$Trips"))
                .append("uuid", new BasicDBObject("$last" , "$UUID"))
                .append("lastTripData", new BasicDBObject("$last", "$Device_Time"))),
				new BasicDBObject("$lookup", new BasicDBObject("from","driver_profile")
				.append("localField", "driverId")
				.append("foreignField", "driverId")
				.append("as", "driversData")),
				new BasicDBObject("$lookup", new BasicDBObject("from","serfs")
						.append("localField", "_id")
						.append("foreignField", "deviceName")
						.append("as", "serfsData")),
/*				new BasicDBObject("$lookup", new BasicDBObject("from","obd")
						.append("localField", "driverId")
						.append("foreignField", "DRIVER_ID")
						.append("status", new BasicDBObject("$last" ,"$VEHICLE_STATUS"))
						.append("as", "obdData")),*/
				       /* .append("$group", new BasicDBObject("_id", 
								"$_id").append("time", new BasicDBObject("$max", "$time"))),*/
				 
/*						new BasicDBObject("$filter", new BasicDBObject("input", "$obdData"))
						.append("as", "obdDatafinal"))
						.append("cond", new BasicDBObject("$last", "$$obdDatafinal.time")))),*/
				new BasicDBObject(new BasicDBObject("$unwind","$serfsData")),
				new BasicDBObject(new BasicDBObject("$match", new BasicDBObject("serfsData.status", "ACTIVE")))
			    );
		
		return output.results();
	}
 	
 	public Iterable<DBObject> getLatestTripsStatus(){
 		
		 DBCollection tripsTable = mongoTemplate.getCollection("trips");
		
		@SuppressWarnings("deprecation")
		AggregationOutput output = tripsTable.aggregate(new BasicDBObject("$group",
              new BasicDBObject("_id", "$Unique_ID")
              /*.append("$sort", new BasicDBObject("driverId", "$driver")
                .append("uuid", "$UUID")
                .append("noOfTrips", "$Trips"))*/
               .append("driverId" , new BasicDBObject("$last","$driver"))
/*               .append("uuid", new BasicDBObject("$last","$UUID"))
               .append("latitude", new BasicDBObject("$last", "$Latitiude"))
               .append("longitude", new BasicDBObject("$last", "$Longitude"))*/
/*               .append("tripsData",  new BasicDBObject("$max","$Trips"))*/
               .append("lastTripData", new BasicDBObject("$max","$Device_Time")))
/*				new BasicDBObject("$lookup", new BasicDBObject("from","vehicle_profile")
						.append("localField", "_id")
						.append("foreignField", "serfId")
						.append("as", "vehicleData")),
				new BasicDBObject("$lookup", new BasicDBObject("from","driver_profile")
						.append("localField", "driverId")
						.append("foreignField", "driverId")
						.append("as", "driverData"))*/
			    );
		return output.results();
	}
 	
 	public Iterable<DBObject> getLatestObdStatus(String serfId){
 		
		 DBCollection obdTable = mongoTemplate.getCollection("obd");
		
		 
		@SuppressWarnings("deprecation")
		AggregationOutput output = obdTable.aggregate(
			  new BasicDBObject("$group",
              new BasicDBObject("_id", "$Serf_Id")
              .append("lastTripData", new BasicDBObject("$max", "$TIME"))),
			  new BasicDBObject("$match", new BasicDBObject("_id", serfId))
/*			new BasicDBObject("$lookup", new BasicDBObject("from","configuration")
						.append("localField", "status")
						.append("foreignField", "Code")
						.append("as", "configurationData"))*/
			    );
		
		return output.results();
	}
 
	public Iterable<DBObject> getLatestTripsBydriverId(String driverId){
 		
		 DBCollection obdTable = mongoTemplate.getCollection("trips");
		 
		@SuppressWarnings("deprecation")
		AggregationOutput output = obdTable.aggregate(new BasicDBObject("$group",
             new BasicDBObject("_id", "$driver")
             .append("uuid", new BasicDBObject("$last" , "$UUID"))
             .append("time", new BasicDBObject("$last", "$Device_Time"))),
			  new BasicDBObject("$match", new BasicDBObject("_id", driverId)));
		
		return output.results();
	}
	
	public Iterable<DBObject> getLatestTripsBySerfId(String serfId){
 		
		 DBCollection obdTable = mongoTemplate.getCollection("trips");
		 
		@SuppressWarnings("deprecation")
		AggregationOutput output = obdTable.aggregate(new BasicDBObject("$group",
            new BasicDBObject("_id", "$Unique_ID")
            .append("uuid", new BasicDBObject("$last" , "$UUID"))
            .append("time", new BasicDBObject("$last", "$Device_Time"))),
			  new BasicDBObject("$match", new BasicDBObject("_id", serfId)));
		
		return output.results();
	}

	@Override
	public Optional<Trips> findTripsByUniqueIDAndDeviceTime(String uniqueID, String deviceTime) {
		// TODO Auto-generated method stub
		return tripsRepository.findTripsByUniqueIDAndDeviceTime(uniqueID, deviceTime);
	}

	@Override
	public Optional<Trips> findTripsByUniqueIdAndTrips(String uniqueId, String trips) {
		// TODO Auto-generated method stub
		return tripsRepository.findTripsByUniqueIdAndTrips(uniqueId, trips);
	}
}

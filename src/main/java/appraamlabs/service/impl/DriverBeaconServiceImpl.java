package appraamlabs.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

import appraamlabs.models.DriverBeacon;
import appraamlabs.repositories.DriverBeaconRepository;
import appraamlabs.service.DriverBeaconService;
import appraamlabs.utils.enums.BeaconType;
import appraamlabs.utils.enums.Status;

@Service
public class DriverBeaconServiceImpl implements DriverBeaconService {

	@Autowired
	private DriverBeaconRepository driverBeaconRepository;

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public void deleteDriverBeacon(DriverBeacon driverBeacon) {
		// TODO Auto-generated method stub
		driverBeaconRepository.delete(driverBeacon);
	}

	@Override
	public Optional<DriverBeacon> findBeaconById(String beaconId) {
		// TODO Auto-generated method stub
		return driverBeaconRepository.findBeaconById(beaconId, Status.ACTIVE);
	}

	@Override
	public void save(DriverBeacon driverBeacon) {
		// TODO Auto-generated method stub
		driverBeaconRepository.save(driverBeacon);
	}

	@Override
	public Optional<DriverBeacon> findBydriverId(String driverId) {
		// TODO Auto-generated method stub
		return driverBeaconRepository.findBydriverId(driverId, Status.ACTIVE);
	}

	@Override
	public Optional<DriverBeacon> findDriverBeaconByEmpIdAndBeaconId(String empId, String beaconId) {
		// TODO Auto-generated method stub
		return driverBeaconRepository.findDriverBeaconByEmpIdAndBeaconId(empId, beaconId, Status.ACTIVE);
	}

	@Override
	public Optional<DriverBeacon> findById(String id) {
		// TODO Auto-generated method stub
		return driverBeaconRepository.findById(id, Status.ACTIVE);
	}

	@Override
	public List<DriverBeacon> findAll() {
		// TODO Auto-generated method stub
		return driverBeaconRepository.findAll(Status.ACTIVE, new Sort(Sort.Direction.DESC, "updatedDate"));
	}

	@Override
	public List<Object> getUnmappedDriverBeacons(List<String> beaconIds) {
		// TODO Auto-generated method stub
		
		List<Object> objects = new ArrayList<Object>();
	     DBCollection beaconsTable = mongoTemplate.getCollection("beacons");
	     
			@SuppressWarnings("deprecation")
			AggregationOutput beaconsOutput = beaconsTable.aggregate(
					new BasicDBObject("uuid", new BasicDBObject("$nin", beaconIds)));/*,
					new BasicDBObject("$match", new BasicDBObject("type",BeaconType.DRIVERBEACON.toString()))*/
					//new BasicDBObject("$not", new BasicDBObject("mappedbeacons", "")));
			objects.add(beaconsOutput.results());
/*		@SuppressWarnings("deprecation")
		AggregationOutput beaconsOutput = beaconsTable.aggregate(
				new BasicDBObject("$lookup",new BasicDBObject("from","beacons")
				.append("localField", "beaconID").append("foreignField", new BasicDBObject("", ""))
				.append("foreignField", "uuid")
				.append("as", "mappedbeacons")),
				new BasicDBObject(new BasicDBObject("$unwind","$mappedbeacons")),
				new BasicDBObject("$match", new BasicDBObject("mappedbeacons.type",BeaconType.DRIVERBEACON.toString())));
				//new BasicDBObject("$not", new BasicDBObject("mappedbeacons", "")));
		
	     DBCollection driversTable = mongoTemplate.getCollection("driver_beacons");
	     
		@SuppressWarnings("deprecation")
		AggregationOutput driversOutput = driversTable.aggregate(
				new BasicDBObject("$lookup", new BasicDBObject("from","driver_profile")
				.append("localField", "driverID")
				.append("foreignField", "driverId")
				.append("as", "mappeddrivers")),
				new BasicDBObject(new BasicDBObject("$unwind","$mappeddrivers")));*/
		
			return objects;
	}

}

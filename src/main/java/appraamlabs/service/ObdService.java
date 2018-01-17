package appraamlabs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.query.BasicQuery;

import com.mongodb.DBObject;

import appraamlabs.models.Obd;

public interface ObdService {

    public List<Obd> findAll();
    
    public Optional<Obd> findObdById(String id);
  	 	
 	public Optional<Obd> findObdBySerfId(String serfId);
 	
    public List<Obd> findObdByDriverId(String driverId);
 	
 	public Optional<Obd> findObdBySerfIdAndDriverId(String serfId, String driverId);
 	
 	public Iterable<DBObject> aggregateBasedOnLatestDate();
 	
 	public Optional<Obd> findObdBySerfIdAndTime(String serfId, String time);
 }

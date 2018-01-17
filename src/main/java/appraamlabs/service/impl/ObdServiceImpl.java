package appraamlabs.service.impl;

import java.util.List;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import appraamlabs.models.Configuration;
import appraamlabs.models.DriverProfile;
import appraamlabs.models.Obd;
import appraamlabs.models.User;
import appraamlabs.models.VehicleProfile;
import appraamlabs.models.configuration.ConfigurationStatus;
import appraamlabs.repositories.ObdRepository;
import appraamlabs.service.ConfigurationService;
import appraamlabs.service.DriverProfileService;
import appraamlabs.service.ObdService;
import appraamlabs.service.TripsService;
import appraamlabs.service.UserService;
import appraamlabs.service.VehicleProfileService;
import appraamlabs.service.gcmNotification.GCMNotificationService;

@Service
public class ObdServiceImpl implements ObdService {

	@Autowired
	private ObdRepository obdRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private GCMNotificationService gCMNotificationService;

	@Autowired
	private DriverProfileService driverProfileService;
	
	@Autowired 
	private VehicleProfileService vehicleProfileService;
	
	@Autowired
	private ConfigurationService configurationService;
	
	@Autowired
	private TripsService tripsService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private Environment env;
	
	private static final Logger logger = LoggerFactory.getLogger(ObdServiceImpl.class);
	
	@Override
	public List<Obd> findAll() {
		// TODO Auto-generated method stub
		return obdRepository.findAll();
	}

	@Override
	public Optional<Obd> findObdById(String id) {
		// TODO Auto-generated method stub
		return obdRepository.findObdById(id);
	}

	@Override
	public Optional<Obd> findObdBySerfId(String serfId) {
		// TODO Auto-generated method stub
		return obdRepository.findObdBySerfId(serfId);
	}

	@Override
	public List<Obd> findObdByDriverId(String driverId) {
		// TODO Auto-generated method stub
		return obdRepository.findObdByDriverId(driverId);
	}

	@Override
	public Optional<Obd> findObdBySerfIdAndDriverId(String serfId, String driverId) {
		// TODO Auto-generated method stub
		return obdRepository.findObdBySerfIdAndDriverId(serfId, driverId);
	}

	@Override
	public Iterable<DBObject> aggregateBasedOnLatestDate() {
		// TODO Auto-generated method stub
		
/*		TypedAggregation<Obd> aggregation = Aggregation.newAggregation(Obd.class,
			    group("state", "city")
			       .sum("population").as("pop"),
			    sort(ASC, "pop", "state", "city"),
			    group("state")
			       .last("city").as("biggestCity")
			       .last("pop").as("biggestPop")
			       .first("city").as("smallestCity")
			       .first("pop").as("smallestPop"),
			    project()
			       .and("state").previousOperation()
			       .and("biggestCity")
			          .nested(bind("name", "biggestCity").and("population", "biggestPop"))
			       .and("smallestCity")
			          .nested(bind("name", "smallestCity").and("population", "smallestPop")),
			    sort(ASC, "state")
			);*/
		
		/*Aggregation agg = */
		DBCollection obdTable = mongoTemplate.getCollection("obd");
		
		@SuppressWarnings("deprecation")
		AggregationOutput output = obdTable.aggregate(new BasicDBObject("$group",
                new BasicDBObject("_id", "$Serf_Id")
                .append("driverId", new BasicDBObject("$last" , "$DRIVER_ID"))
/*                .append("status", new BasicDBObject("$first", "$VEHICLE_STATUS"))
                .append("speed", new BasicDBObject("$first", "$VEHICLE_SPEED"))*/
                .append("status", new BasicDBObject("$last", "$VEHICLE_STATUS"))
                .append("speed", new BasicDBObject("$last", "$VEHICLE_SPEED"))
                .append("lastTripData", new BasicDBObject("$last", "$TIME"))),
				new BasicDBObject("$lookup", new BasicDBObject("from","driver_profile")
				.append("localField", "DRIVER_ID")
				.append("foreignField", "driverId")
				.append("as", "driverInfo")),
				new BasicDBObject("$lookup", new BasicDBObject("from","serfs")
						.append("localField", "Serf_Id")
						.append("foreignField", "deviceName")
/*						.append("foreignField", "Uid")*/
						.append("as", "serfInfo"))
				);
		return output.results();
/*		mongoTemplate.aggregate(aggregation, outputType)*/
		//return obdRepository.findAllObd(query);
	}
	
	@Scheduled(fixedDelay = 60000)
	public void sendPushNotification() throws java.util.NoSuchElementException{
		
     DBCollection obdTable = mongoTemplate.getCollection("obd");
 		
		@SuppressWarnings("deprecation")
		AggregationOutput output = obdTable.aggregate(new BasicDBObject("$group",
                new BasicDBObject("_id", "$Serf_Id")
                .append("driverId", new BasicDBObject("$last" , "$DRIVER_ID"))
                .append("status", new BasicDBObject("$last", "$VEHICLE_STATUS"))
                .append("speed", new BasicDBObject("$last", "$VEHICLE_SPEED"))
                .append("lastTripData", new BasicDBObject("$last", "$TIME"))),
				new BasicDBObject("$lookup", new BasicDBObject("from","driver_profile")
				.append("localField", "DRIVER_ID")
				.append("foreignField", "driverId")
				.append("as", "driverInfo")),
/*				new BasicDBObject("$lookup", new BasicDBObject("from","serfs")
						.append("localField", "Serf_Id")
						.append("foreignField", "deviceName")
						.append("foreignField", "Uid")
						.append("as", "serfInfo"))*/
				new BasicDBObject("$lookup", new BasicDBObject("from","vehicle_profile")
						.append("localField", "_id")
						.append("foreignField", "serfId")
						.append("as", "vehicleInfo"))
				);
/*		logger.debug("output : {}", output.results());*/
		this.gcmPushNotification(output.results());
	}
	
	@Scheduled(fixedDelay = 180000)
	public void sendIdelPushNotification() throws java.util.NoSuchElementException{
		
     DBCollection obdTable = mongoTemplate.getCollection("obdhistory");
 		
		@SuppressWarnings("deprecation")
		AggregationOutput output = obdTable.aggregate(new BasicDBObject("$group",
                new BasicDBObject("_id", "$Serf_Id")
                .append("driverId", new BasicDBObject("$last" , "$DRIVER_ID"))
                .append("status", new BasicDBObject("$last", "$VEHICLE_STATUS"))
                .append("speed", new BasicDBObject("$last", "$VEHICLE_SPEED"))
                .append("lastTripData", new BasicDBObject("$last", "$TIME")))
/*				new BasicDBObject("$lookup", new BasicDBObject("from","driver_profile")
				.append("localField", "DRIVER_ID")
				.append("foreignField", "driverId")
				.append("as", "driverInfo")),
				new BasicDBObject("$lookup", new BasicDBObject("from","vehicle_profile")
						.append("localField", "_id")
						.append("foreignField", "serfId")
						.append("as", "vehicleInfo"))*/
				);
		this.gcmIdelPushNotification(output.results());
	}
	
	public void gcmIdelPushNotification(Iterable<DBObject> obds) throws java.util.NoSuchElementException{

		logger.debug("Request to send idel notification");
		
		for(DBObject dbObject : obds){
						
			String speed = String.valueOf(dbObject.get("speed"));
			String code = String.valueOf(dbObject.get("status"));

			Optional<DriverProfile> optionalDriverProfile = driverProfileService.getByDriverId(String.valueOf(dbObject.get("driverId")));
			
			Optional<VehicleProfile> optionalVehicleProfile = vehicleProfileService.findVehicleProfileBySerfName(String.valueOf(dbObject.get("_id")));

			List<Configuration> listConfiguration =  configurationService.findAll();
			
			Iterable<DBObject> iterDbObject = tripsService.getLatestTripsBySerfId(String.valueOf(dbObject.get("_id")));
			
			DBObject dbObject2=null;
			if(iterDbObject.iterator().hasNext()){
			dbObject2 = iterDbObject.iterator().next();
			}
			
			List<User> usersList = userService.getAllUsers();
			
			ConfigurationStatus configurationStatus = null;
			if(dbObject2!=null){
			configurationStatus = this.getConfigurationStatusByCode(code, listConfiguration, String.valueOf(dbObject2.get("uuid")), speed);
			}
			
			if(configurationStatus!=null && configurationStatus.getCode().equals("0") && configurationStatus.getNotification() && optionalVehicleProfile.isPresent()
					|| configurationStatus!=null && configurationStatus.getCode().equals("-1") && configurationStatus.getNotification() && optionalVehicleProfile.isPresent()){
			String message = sendNotification(optionalDriverProfile, optionalVehicleProfile, configurationStatus, speed, code, listConfiguration.get(0).getConfigurationNotification().getMessage());
			logger.debug("Idel message : {}",message);
			for (User user : usersList){
				if(!user.getDevicetoken().equals("0") && !user.getDevicetoken().isEmpty()){
					logger.debug("USER TOKEN {}", user.getDevicetoken());
					gCMNotificationService.sendGCMNotification(user.getDevicetoken(),message);
				}
			}
			}
			}
		
	}
	
	public void gcmPushNotification(Iterable<DBObject> obds) throws java.util.NoSuchElementException{

		logger.debug("Request to get all obds");
		
		for(DBObject dbObject : obds){
			//String serfId= String.valueOf(dbObject.get("_id"));
			String speed = String.valueOf(dbObject.get("speed"));
			String code = String.valueOf(dbObject.get("status"));
			
			//int statusval = Integer.parseInt(code);
			
			Optional<DriverProfile> optionalDriverProfile = driverProfileService.getByDriverId(String.valueOf(dbObject.get("driverId")));
			
			Optional<VehicleProfile> optionalVehicleProfile = vehicleProfileService.findVehicleProfileBySerfName(String.valueOf(dbObject.get("_id")));

			List<Configuration> listConfiguration =  configurationService.findAll();

			List<User> usersList = userService.getAllUsers();
			//Optional<VehicleProfile> optionalVehicleProfile = vehicleProfileService.findVehicleProfileBySerfId(serfId);
/*			   if(statusval > 3 || statusval == 1 || statusval == 0){*/
			ConfigurationStatus configurationStatus = this.getConfigurationStatusFromConfiguration(listConfiguration.get(0), code, speed);
			
			   if(configurationStatus!=null && configurationStatus.getNotification() && !configurationStatus.getCode().equals("0") && !configurationStatus.getCode().equals("-1") && optionalVehicleProfile.isPresent()){
			String message = sendNotification(optionalDriverProfile, optionalVehicleProfile, configurationStatus, speed, code, listConfiguration.get(0).getConfigurationNotification().getMessage());
			//messages.add(message);
			logger.debug("Message : {}",message);
			//gCMNotificationService.sendGCMNotification(env.getProperty(MessageConstants.GCM_TOKEN),message);
			for (User user : usersList){
				if(!user.getDevicetoken().equals("0") && !user.getDevicetoken().isEmpty()){
					logger.debug("USER TOKEN {}", user.getDevicetoken());
					gCMNotificationService.sendGCMNotification(user.getDevicetoken(),message);
				}
			}
			}
			}
		
	}
	
	public String sendNotification(Optional<DriverProfile> optionalDriverProfile, Optional<VehicleProfile> optionalVehicleProfile, ConfigurationStatus configurationStatus, String speed, String code, String message){
		   //String message[] = optionalConfiguration.get().getMessage().split("{}");
		
		/*ConfigurationStatus configurationStatus = this.getConfigurationStatusFromConfiguration(configuration, code);*/
		
		//String message = configuration.getConfigurationNotification().getMessage();
		
		//String message = null
		if(optionalVehicleProfile.isPresent()){
			message = message.replace("{&vn}", optionalVehicleProfile.get().getVehicleNo());
			//message = message.replace("{vehicle number}", optionalVehicleProfile.get().getVehicleNo());
		}else{
			message = message.replace("{&vn}", "unknown");
			//message = message.replace("{vehicle number}", "unknown");
		}
		
		if(configurationStatus!= null){
		     message = message.replace("{&s}", configurationStatus.getStatus());
			 //message = message.replace("{status}", configurationStatus.getStatus());
		}else{
			message = message.replace("{&s}", "unknown");
			//message = message.replace("{status}", "unknown");
		}
		
    	   if(optionalDriverProfile.isPresent()){
			   message = message.replace("{&d}", optionalDriverProfile.get().getDriverFirstName()
				   +" "+optionalDriverProfile.get().getDriverLastName());
/*			   message = message.replace("{Driver name}", optionalDriverProfile.get().getDriverFirstName()
					   +" "+optionalDriverProfile.get().getDriverLastName());*/
		   }else{
			   message = message.replace("{&d}", "unknown");
			   //message = message.replace("{Driver name}", "unknown");
				//message = "Vehicle is driving with speed of "+speed+" with status "+configurationStatus.getStatus()+" by driver : null";
		   }
    	   
    	   if(message.contains("{&sp}")){
    	       message = message.replace("{&sp}", speed);
        	   //message = message.replace("{speed}", speed);
       	}
	   return message;
	}
	
	private ConfigurationStatus getConfigurationStatusByCode(String code, List<Configuration> optionalConfiguration, String uuid, String speed){
		  
		  if(uuid.charAt(0) == 'b' && speed.equals("0") || uuid.charAt(0) == 'B' && speed.equals("0")){
			  for (ConfigurationStatus configurationStatus : optionalConfiguration.get(0).getConfigurationStatus()) {
					if(configurationStatus.getCode().equals("0")){
						return configurationStatus;
					}/*else if(configurationStatus.getCode().equals("-1") && speed.equals("-1")){//&& configurationStatus.getFromRange().equals(speed) && configurationStatus.getToRange().equals(speed)
						return configurationStatus;
					}*/
				}
		  }else if(uuid.charAt(0) == 'b' && speed.equals("-1") || uuid.charAt(0) == 'B' && speed.equals("-1")){
			  for (ConfigurationStatus configurationStatus : optionalConfiguration.get(0).getConfigurationStatus()) {
			  if(configurationStatus.getCode().equals("-1")){//&& configurationStatus.getFromRange().equals(speed) && configurationStatus.getToRange().equals(speed)
					return configurationStatus;
				}
			  }
		  }
		return null;
	  }
	
	private ConfigurationStatus getConfigurationStatusFromConfiguration(Configuration configuration, String code, String speed){
		
		List<ConfigurationStatus> listConfigurationStatus = configuration.getConfigurationStatus();
		if(!speed.equals("-1") && !speed.equals("0")){
		for (ConfigurationStatus configurationStatus : listConfigurationStatus) {
			if(configurationStatus.getCode().equals(code)){
				return configurationStatus;
			}
		}
		}
		return null;
	}

	@Override
	public Optional<Obd> findObdBySerfIdAndTime(String serfId, String time) {
		// TODO Auto-generated method stub
		return obdRepository.findObdBySerfIdAndTime(serfId, time);
	}
}

package appraamlabs.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

import appraamlabs.models.DriverBeacon;
import appraamlabs.models.VehicleSerf;
import appraamlabs.utils.enums.Status;

@Component
public interface VehicleSerfRepository extends MongoRepository<VehicleSerf, String> {

    public void delete(VehicleSerf vehicleSerf);

    @Query(value="{id : ?0, 'status' : ?1}")
    public Optional<VehicleSerf> findById(String id, Status status);

    @Query(value="{status : ?0}")
    public List<VehicleSerf> findAll(Status status, Sort sort);

	@Query(value="{vehicleNo : ?0, 'status' : ?1}")
    public Optional<VehicleSerf> findVehicleSerfByVehicleNo(String vehicleNumber, Status status);

	@Query(value="{serfsName : ?0, 'status' : ?1}")
 	public Optional<VehicleSerf> findVehicleSerfBySerfName(String serfsName,  Status status);

 	public VehicleSerf save(VehicleSerf vehicleSerf);
 
 	@Query(value="{vehicleNo : ?0, 'serfsName' : ?1, 'status' : ?2}") /*fields="{email : 1}"*/
 	public Optional<VehicleSerf> findVehicleSerfByVehicleNoAndSerfsName(String vehicleNumber, String serfsName, Status status);
 
}

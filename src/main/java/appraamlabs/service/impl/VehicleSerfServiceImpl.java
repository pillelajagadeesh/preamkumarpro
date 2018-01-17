package appraamlabs.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import appraamlabs.models.DriverBeacon;
import appraamlabs.models.VehicleSerf;
import appraamlabs.repositories.VehicleSerfRepository;
import appraamlabs.service.VehicleSerfService;
import appraamlabs.utils.enums.Status;

@Service
public class VehicleSerfServiceImpl implements VehicleSerfService {

	@Autowired
	private VehicleSerfRepository vehicleSerfRepository;
	
	@Override
	public void deleteDriverBeacon(VehicleSerf vehicleSerf) {
		// TODO Auto-generated method stub
		vehicleSerfRepository.delete(vehicleSerf);
	}

	@Override
	public Optional<VehicleSerf> findVehicleSerfByVehicleNo(String vehicleNo) {
		// TODO Auto-generated method stub
		return vehicleSerfRepository.findVehicleSerfByVehicleNo(vehicleNo, Status.ACTIVE);
	}

	@Override
	public void save(VehicleSerf vehicleSerf) {
		// TODO Auto-generated method stub
		vehicleSerfRepository.save(vehicleSerf);
	}

	@Override
	public Optional<VehicleSerf> findVehicleSerfBySerfName(String serfName) {
		// TODO Auto-generated method stub
		return vehicleSerfRepository.findVehicleSerfBySerfName(serfName, Status.ACTIVE);
	}

	@Override
	public Optional<VehicleSerf> findById(String id) {
		// TODO Auto-generated method stub
		return vehicleSerfRepository.findById(id, Status.ACTIVE);
	}

	@Override
	public Optional<VehicleSerf> findVehicleSerfByVehicleNumberAndSerfName(String vehicleNumber, String serfName) {
		// TODO Auto-generated method stub
		return vehicleSerfRepository.findVehicleSerfByVehicleNoAndSerfsName(vehicleNumber, serfName,Status.ACTIVE);
	}

	@Override
	public List<VehicleSerf> findAll() {
		// TODO Auto-generated method stub
		return vehicleSerfRepository.findAll(Status.ACTIVE, new Sort(Sort.Direction.DESC, "updatedDate"));
	}

}

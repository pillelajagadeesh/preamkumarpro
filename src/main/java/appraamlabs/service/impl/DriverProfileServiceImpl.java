package appraamlabs.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import appraamlabs.models.DriverProfile;
import appraamlabs.models.SerfsBoard;
import appraamlabs.repositories.DriverProfileRepository;
import appraamlabs.service.DriverProfileService;
import appraamlabs.utils.enums.DeviceStatus;
import appraamlabs.utils.enums.Status;

@Service
public class DriverProfileServiceImpl implements DriverProfileService {

	@Autowired
	DriverProfileRepository driverProfileRepository;
	
	@Override
	public DriverProfile saveDriverProfile(DriverProfile driverProfile) {
		// TODO Auto-generated method stub
		return driverProfileRepository.save(driverProfile);
	}

	@Override
	public void deleteDriverProfile(DriverProfile driverProfile) {
		// TODO Auto-generated method stub
		driverProfileRepository.delete(driverProfile);
	}

	@Override
	public List<DriverProfile> getAllDriverProfiles() {
		// TODO Auto-generated method stub
		return driverProfileRepository.findAll(new Sort(Sort.Direction.DESC, "updatedDate"));
	}

	@Override
	public Optional<DriverProfile> getById(String id) {
		// TODO Auto-generated method stub
		return driverProfileRepository.findOne(id);
	}

	@Override
	public Optional<DriverProfile> getByEmpId(String empId) {
		// TODO Auto-generated method stub
		return driverProfileRepository.findDriverProfileByEmpId(empId);
	}

	@Override
	public Optional<DriverProfile> getByDriverId(String driverId) {
		// TODO Auto-generated method stub
		return driverProfileRepository.findDriverProfileByDriverId(driverId);
	}

	@Override
	public List<DriverProfile> findUnmappedDriverProfilesByEmpId(List<String> driverIds) {
		// TODO Auto-generated method stub
		return driverProfileRepository.findUnmappedDriverProfilesByEmpId(driverIds);
	}

	@Override
	public List<DriverProfile> findEmptyDriverIds() {
		// TODO Auto-generated method stub
		return driverProfileRepository.findEmptyDriverIds();
	}

	@Override
	public List<DriverProfile> searchDriver(String searchText) {
		// TODO Auto-generated method stub
		return driverProfileRepository.searchDriver(searchText, new Sort(Sort.Direction.DESC, "updatedDate"));
	}

	@Override
	public Page<DriverProfile> getAllDriverProfiles(Pageable pageable) {
		// TODO Auto-generated method stub
		return driverProfileRepository.findAll(new PageRequest(pageable.getPageNumber()-1, 
				pageable.getPageSize(),new Sort(Sort.Direction.DESC, "updatedDate")));
	}

	@Override
	public Page<DriverProfile> findAllMappedDrivers(Pageable pageable) {
		// TODO Auto-generated method stub
		return driverProfileRepository.findAllMappedDrivers(new PageRequest(pageable.getPageNumber()-1, 
				pageable.getPageSize(),new Sort(Sort.Direction.DESC, "updatedDate")));
	}

}

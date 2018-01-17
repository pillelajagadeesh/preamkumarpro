package appraamlabs.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import appraamlabs.models.VehicleProfile;
import appraamlabs.repositories.VehicleProfileRepository;
import appraamlabs.service.VehicleProfileService;
import appraamlabs.utils.enums.Status;
import appraamlabs.utils.enums.VehicleStatus;

@Service
public class VehicleProfileServiceImpl implements VehicleProfileService {

	@Autowired
	private VehicleProfileRepository vehicleProfileRepository;
	
	@Override
	public VehicleProfile saveVehicleProfile(VehicleProfile vehicleProfile) {
		// TODO Auto-generated method stub
		return vehicleProfileRepository.save(vehicleProfile);
	}

	@Override
	public void deleteVehicleProfile(VehicleProfile vehicleProfile) {
		// TODO Auto-generated method stub
		vehicleProfileRepository.delete(vehicleProfile);
	}

	@Override
	public List<VehicleProfile> getAllVehicleProfiles() {
		// TODO Auto-generated method stub
		return vehicleProfileRepository.findAll(new Sort(Sort.Direction.DESC, "updatedDate"));
	}

	@Override
	public Optional<VehicleProfile> getById(String id) {
		// TODO Auto-generated method stub
		return vehicleProfileRepository.findOne(id);
	}

	@Override
	public Optional<VehicleProfile> findVehicleProfileByVehicleNo(String vehicleNo) {
		// TODO Auto-generated method stub
		return vehicleProfileRepository.findVehicleProfileByVehicleNo(vehicleNo);
	}

	@Override
	public Optional<VehicleProfile> findVehicleProfileBySerfName(String serfId) {
		// TODO Auto-generated method stub
		return vehicleProfileRepository.findVehicleProfileBySerfName(serfId, VehicleStatus.ACTIVE);
	}

	@Override
	public List<VehicleProfile> findUnmappedVehiclesByVehicleNo() {
		// TODO Auto-generated method stub
		return vehicleProfileRepository.findUnmappedVehiclesByVehicleNo();
	}

	@Override
	public List<VehicleProfile> searchVehicle(String serchText) {
		// TODO Auto-generated method stub
		return vehicleProfileRepository.searchVehicle(serchText, new Sort(Sort.Direction.DESC, "updatedDate"));
	}

	@Override
	public Page<VehicleProfile> findAllMappedVehicles(Pageable pageable) {
		// TODO Auto-generated method stub
		return vehicleProfileRepository.findAllMappedVehicles(new PageRequest(pageable.getPageNumber()-1, 
				pageable.getPageSize(),new Sort(Sort.Direction.DESC, "updatedDate")));
	}

	@Override
	public Page<VehicleProfile> findAllVehicles(Pageable pageable) {
		// TODO Auto-generated method stub
		return vehicleProfileRepository.findAll(new PageRequest(pageable.getPageNumber()-1, 
				pageable.getPageSize(),new Sort(Sort.Direction.DESC, "updatedDate")));
	}

}

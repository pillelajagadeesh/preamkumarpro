package appraamlabs.service.impl;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import appraamlabs.models.Beacon;
import appraamlabs.models.DriverBeacon;
import appraamlabs.repositories.BeaconRepository;
import appraamlabs.service.BeaconService;
import appraamlabs.utils.enums.BeaconType;
import appraamlabs.utils.enums.DeviceStatus;

@Service
public class BeaconServiceImpl implements BeaconService {

	@Autowired
	private BeaconRepository beaconRepository;

	@Override
	public Beacon saveBeacon(Beacon beacon) {
		// TODO Auto-generated method stub
		return beaconRepository.save(beacon);
	}

	@Override
	public void deleteBeacon(Beacon beacon) {
		// TODO Auto-generated method stub
		beaconRepository.delete(beacon);
	}

	@Override
	public List<Beacon> getAllBeacons() {
		// TODO Auto-generated method stub
		return beaconRepository.findAll();
	}

	@Override
	public Optional<Beacon> getById(String id) {
		// TODO Auto-generated method stub
		return beaconRepository.findBeaconById(id);
	}

	@Override
	public Optional<Beacon> findBeaconByMacId(String macId) {
		// TODO Auto-generated method stub
		return beaconRepository.findBeaconByMacId(macId, DeviceStatus.ACTIVE);
	}

	@Override
	public void deleteBeaconById(String id) {
		// TODO Auto-generated method stub
		beaconRepository.delete(id);
	}

	@Override
	public Page<Beacon> getAllBeaconsByType(BeaconType type, Pageable pageable) {
		// TODO Auto-generated method stub
		return beaconRepository.findAllByType(type, DeviceStatus.ACTIVE,  new PageRequest(pageable.getPageNumber()-1, 
				pageable.getPageSize(),new Sort(Sort.Direction.DESC, "updatedDate")));
	}

	@Override
	public Optional<Beacon> findBeaconByUuid(String uuid) {
		// TODO Auto-generated method stub
		return beaconRepository.findBeaconByUuid(uuid, DeviceStatus.ACTIVE);
	}

	@Override
	public List<Beacon> findUnmappedBeaconsByUuid(List<String> beaconIds) {
		// TODO Auto-generated method stub
		return beaconRepository.findUnmappedBeaconsByUuid(beaconIds, BeaconType.DRIVERBEACON, DeviceStatus.ACTIVE);
	}

	@Override
	public Optional<Beacon> findBeaconByMacId(String macId, BeaconType type) {
		// TODO Auto-generated method stub
		return beaconRepository.findBeaconByMacId(macId, DeviceStatus.ACTIVE, type);
	}

	@Override
	public Optional<Beacon> findBeaconByUuid(String uuid, BeaconType type) {
		// TODO Auto-generated method stub
		return beaconRepository.findBeaconByUuid(uuid, DeviceStatus.ACTIVE, type);
	}

	@Override
	public List<Beacon> searchWPBeacons(String serchText) {
		// TODO Auto-generated method stub
		return beaconRepository.searchWPBeacons(serchText, DeviceStatus.ACTIVE, BeaconType.DRIVERBEACON, new Sort(Sort.Direction.DESC, "updatedDate"));
	}

	@Override
	public List<Beacon> searchDriverBeacons(String serchText) {
		// TODO Auto-generated method stub
		return beaconRepository.searchDBeacons(serchText, DeviceStatus.ACTIVE, BeaconType.DRIVERBEACON, new Sort(Sort.Direction.DESC, "updatedDate"));
	}

	@Override
	public Page<Beacon> findAllWpBeacons(BeaconType type, Pageable pageable) {
		// TODO Auto-generated method stub
		return beaconRepository.findAllWpBeacons(type, DeviceStatus.ACTIVE, new PageRequest(pageable.getPageNumber()-1, 
				pageable.getPageSize(),new Sort(Sort.Direction.DESC, "updatedDate")));
	}
	
	@Override
	public List<Beacon> findAllWpBeaconsForHawkeye(BeaconType type) {
		// TODO Auto-generated method stub
		return beaconRepository.findAllWpBeacons(type, DeviceStatus.ACTIVE, new Sort(Sort.Direction.DESC, "updatedDate"));
	}
}

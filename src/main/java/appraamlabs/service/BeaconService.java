package appraamlabs.service;

import java.util.List;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import appraamlabs.models.Beacon;
import appraamlabs.utils.enums.BeaconType;
import appraamlabs.utils.enums.DeviceStatus;

public interface BeaconService {

	public Beacon saveBeacon(Beacon beacon);
	
	public void deleteBeacon(Beacon beacon);
	
	public List<Beacon> getAllBeacons();

	public Optional<Beacon> getById(String id);
	
    public Optional<Beacon> findBeaconByUuid(String uuid);
    
    public Optional<Beacon> findBeaconByUuid(String uuid, BeaconType type);
    
 	public List<Beacon> findUnmappedBeaconsByUuid(List<String> beaconIds);
 	
	public Optional<Beacon> findBeaconByMacId(String macId);
	
	public Optional<Beacon> findBeaconByMacId(String macId, BeaconType type);
	
	public void deleteBeaconById(String id);
	
	public Page<Beacon> getAllBeaconsByType(BeaconType type, Pageable pageable);
	
 	public List<Beacon> searchWPBeacons(String serchText);
 	
 	public List<Beacon> searchDriverBeacons(String serchText);
 	
 	public Page<Beacon> findAllWpBeacons(BeaconType type, Pageable pageable);
 	
	public List<Beacon> findAllWpBeaconsForHawkeye(BeaconType type);
}

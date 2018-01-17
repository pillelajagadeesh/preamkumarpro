package appraamlabs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import appraamlabs.models.SerfsBoard;
import appraamlabs.utils.enums.DeviceStatus;

public interface SerfsBoardService {

	public SerfsBoard saveSerfsBoard(SerfsBoard serfsBoard);
	
	public void deleteSerfsBoard(SerfsBoard serfsBoard);
	
	public Page<SerfsBoard> getAllSerfsBoard(Pageable pageable);

	public Optional<SerfsBoard> getById(String id);
	
	//public SerfsBoard updateSerfsBoard(SerfsBoard serfsBoard);
	
	public Optional<SerfsBoard> findSerfsBoardByMacId(String macId);
	
	public void deleteSerfsBoard(String id);
	
	public Optional<SerfsBoard> getSerfByMacIdAndStatus(String macId, DeviceStatus status);
	
 	public List<SerfsBoard> findUnmappedSerfsByDeviveNames(List<String> deviceNames);
	
 	public Optional<SerfsBoard> findOneByDeviceName(String deviceName);
 	
 	public List<SerfsBoard> searchSerfsBoard(String serchText);
 	
 	public Optional<SerfsBoard> findByUuid(String uuid);
}

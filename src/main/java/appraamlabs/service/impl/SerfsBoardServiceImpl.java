package appraamlabs.service.impl;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import appraamlabs.models.SerfsBoard;
import appraamlabs.repositories.SerfBoardRepository;
import appraamlabs.service.SerfsBoardService;
import appraamlabs.utils.enums.DeviceStatus;

@Service
public class SerfsBoardServiceImpl implements SerfsBoardService {

	@Autowired
	private SerfBoardRepository serfBoardRepository;
	
	@Override
	public SerfsBoard saveSerfsBoard(SerfsBoard serfsBoard) {
		// TODO Auto-generated method stub
		return serfBoardRepository.save(serfsBoard);
	}

	@Override
	public void deleteSerfsBoard(SerfsBoard serfsBoard) {
		// TODO Auto-generated method stub
		serfBoardRepository.delete(serfsBoard);
	}

	@Override
	public Page<SerfsBoard> getAllSerfsBoard(Pageable pageable) {
		// TODO Auto-generated method stub
		return serfBoardRepository.findAll(DeviceStatus.ACTIVE, new PageRequest(pageable.getPageNumber()-1, 
	            pageable.getPageSize(), new Sort(Sort.Direction.DESC, "updatedDate")));
	}

	@Override
	public Optional<SerfsBoard> getById(String id) {
		// TODO Auto-generated method stub
		return serfBoardRepository.findSerfsBoardById(id);
	}

	@Override
	public Optional<SerfsBoard> findSerfsBoardByMacId(String macId) {
		// TODO Auto-generated method stub
		return serfBoardRepository.findSerfsBoardByMacId(macId);
	}

	@Override
	public void deleteSerfsBoard(String id) {
		// TODO Auto-generated method stub
		serfBoardRepository.delete(id);
	}

	@Override
	public Optional<SerfsBoard> getSerfByMacIdAndStatus(String macId, DeviceStatus status) {
		// TODO Auto-generated method stub
		return serfBoardRepository.findOne(macId, status);
	}

	@Override
	public List<SerfsBoard> findUnmappedSerfsByDeviveNames(List<String> deviceNames) {
		// TODO Auto-generated method stub
		return serfBoardRepository.findUnmappedSerfsByDeviveNames(deviceNames, DeviceStatus.ACTIVE);
	}

	@Override
	public Optional<SerfsBoard> findOneByDeviceName(String deviceName) {
		// TODO Auto-generated method stub
		return serfBoardRepository.findOneByDeviceName(deviceName, DeviceStatus.ACTIVE);
	}

	@Override
	public List<SerfsBoard> searchSerfsBoard(String searchText) {
		// TODO Auto-generated method stub
		return serfBoardRepository.searchSerfsBoard(searchText, DeviceStatus.ACTIVE, new Sort(Sort.Direction.DESC, "updatedDate"));
	}

	@Override
	public Optional<SerfsBoard> findByUuid(String uuid) {
		// TODO Auto-generated method stub
		return serfBoardRepository.findByUuid(uuid, DeviceStatus.ACTIVE);
	}
	
}

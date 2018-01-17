package appraamlabs.service.impl;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appraamlabs.models.ObdHistory;
import appraamlabs.repositories.ObdHistoryRepository;
import appraamlabs.service.ObdHistoryService;

@Service
public class ObdHistoryServiceImpl implements ObdHistoryService {

	@Autowired
	private ObdHistoryRepository obdHistoryRepository;
	
	@Override
	public List<ObdHistory> findAll() {
		// TODO Auto-generated method stub
		return obdHistoryRepository.findAll();
	}

	@Override
	public Optional<ObdHistory> findOne(String serfId, String time) {
		// TODO Auto-generated method stub
		return obdHistoryRepository.findOneBySerfIdAndTime(serfId, time);
	}

}

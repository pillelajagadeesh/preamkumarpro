package appraamlabs.service;

import java.util.List;
import java.util.Optional;

import appraamlabs.models.ObdHistory;

public interface ObdHistoryService {

	public List<ObdHistory> findAll();
	
	public Optional<ObdHistory> findOne(String serfId, String time);
}

package appraamlabs.service.impl;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import appraamlabs.models.Issues;
import appraamlabs.repositories.IssuesRepository;
import appraamlabs.service.IssuesService;
import appraamlabs.utils.enums.IssueType;

@Service
public class IsseuesServiceImpl implements IssuesService {

	@Autowired
	private IssuesRepository issuesRepository;
	
	@Override
	public void delete(Issues deleted) {
		// TODO Auto-generated method stub
		issuesRepository.delete(deleted);
	}

	@Override
	public List<Issues> findAll() {
		// TODO Auto-generated method stub
		return issuesRepository.findAll();
	}

	@Override
	public Optional<Issues> findOne(String id) {
		// TODO Auto-generated method stub
		return issuesRepository.findOne(id);
	}

	@Override
	public Issues save(Issues issues) {
		// TODO Auto-generated method stub
		return issuesRepository.save(issues);
	}

	@Override
	public Optional<Issues> findIssuesByUuidAndCommands(String uuid, String commands) {
		// TODO Auto-generated method stub
		return issuesRepository.findIssuesByUuidAndCommands(uuid, commands);
	}

	@Override
	public Optional<Issues> findIssuesByUuid(String uuid) {
		// TODO Auto-generated method stub
		return issuesRepository.findIssuesByUuid(uuid);
	}

	@Override
	public List<Issues> findAllIssuesByType(IssueType type) {
		// TODO Auto-generated method stub
		return issuesRepository.findAllIssuesByType(type);
	}

	@Override
	public List<Issues> findLatestIssuesByType(IssueType type) {
		// TODO Auto-generated method stub
		return issuesRepository.findLatestIssuesByType(type, new PageRequest(0, 5, new Sort(Sort.Direction.DESC, "updatedDate")));
	}

}

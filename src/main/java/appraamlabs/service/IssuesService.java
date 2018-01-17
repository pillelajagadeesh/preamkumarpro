package appraamlabs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import appraamlabs.models.Issues;
import appraamlabs.utils.enums.IssueType;

public interface IssuesService {

	public void delete(Issues deleted);

    public List<Issues> findAll();

    public Optional<Issues> findOne(String id);
 
 	public Issues save(Issues issues);
 	 	
 	public Optional<Issues> findIssuesByUuidAndCommands(String uuid, String commands);
 	
 	public Optional<Issues> findIssuesByUuid(String uuid);
 	
 	public List<Issues> findAllIssuesByType(IssueType type);
 	
 	public List<Issues> findLatestIssuesByType(IssueType type);
}

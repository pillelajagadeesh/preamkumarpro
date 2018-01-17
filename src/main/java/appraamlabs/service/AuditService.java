package appraamlabs.service;

import java.util.List;
import java.util.Optional;

import appraamlabs.models.Audit;

public interface AuditService {

	public Audit saveAudit(Audit audit);
	
	public void deleteAudit(Audit audit);
	
	public List<Audit> getAllAudits();

	public Optional<Audit> getById(String id);
	
	public Optional<Audit> getByMacId(String macId);
			
}

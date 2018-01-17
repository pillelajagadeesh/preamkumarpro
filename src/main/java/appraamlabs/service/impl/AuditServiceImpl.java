package appraamlabs.service.impl;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appraamlabs.models.Audit;
import appraamlabs.repositories.AuditRepository;
import appraamlabs.service.AuditService;

@Service
public class AuditServiceImpl implements AuditService {

	@Autowired
	public AuditRepository auditRepository;
	
	@Override
	public Audit saveAudit(Audit audit) {
		// TODO Auto-generated method stub
		return auditRepository.save(audit);
	}

	@Override
	public void deleteAudit(Audit audit) {
		// TODO Auto-generated method stub
		auditRepository.delete(audit);		
	}

	@Override
	public List<Audit> getAllAudits() {
		// TODO Auto-generated method stub
		return auditRepository.findAll();
	}

	@Override
	public Optional<Audit> getById(String id) {
		// TODO Auto-generated method stub
		return auditRepository.findAuditById(id);
	}

	@Override
	public Optional<Audit> getByMacId(String macId) {
		// TODO Auto-generated method stub
		return auditRepository.findAuditByMacId(macId);
	}

}

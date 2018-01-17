package appraamlabs.service.mysql.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import appraamlabs.models.mysql.ObdMysql;
import appraamlabs.repositories.mysql.ObdMysqlRepository;
import appraamlabs.service.mysql.ObdMysqlService;

@Service
public class ObdMysqlServiceImpl implements ObdMysqlService {

	@Resource
	private ObdMysqlRepository obdMysqlRepository;
	
	@Override
	@Transactional
	public List<ObdMysql> findAll() {
		// TODO Auto-generated method stub
		return obdMysqlRepository.findAll();
	}

	/*@Override
	@Transactional
	public Optional<ObdMysql> findBySerfId() {
		// TODO Auto-generated method stub
		return obdMysqlRepository.findBySerfId();
	}

	@Override
	@Transactional
	public Optional<ObdMysql> findByDriverId() {
		// TODO Auto-generated method stub
		return null;
	}*/

}

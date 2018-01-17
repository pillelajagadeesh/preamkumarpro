package appraamlabs.service.mysql;

import java.util.List;

import appraamlabs.models.mysql.ObdMysql;

public interface ObdMysqlService {

	public List<ObdMysql> findAll();
	
/*	public Optional<ObdMysql> findBySerfId();
	
	public Optional<ObdMysql> findByDriverId();*/
}

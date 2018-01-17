package appraamlabs.service.mysql;

import java.util.List;
import java.util.Optional;

import appraamlabs.models.mysql.UserMysql;

public interface UserMysqlService {

	public List<UserMysql> findAll();
	
	public Optional<UserMysql> findOne(String username, String password);
}

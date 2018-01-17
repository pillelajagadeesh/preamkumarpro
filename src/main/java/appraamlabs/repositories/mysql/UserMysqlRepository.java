package appraamlabs.repositories.mysql;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import appraamlabs.models.mysql.UserMysql;

public interface UserMysqlRepository extends JpaRepository<UserMysql, String> {

	public List<UserMysql> findAll();
	
	@Query(value="SELECT * FROM users WHERE username=?1 && password=?2", nativeQuery=true)
	public Optional<UserMysql> findOne(String username, String password);
	
}

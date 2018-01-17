package appraamlabs.repositories.mysql;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import appraamlabs.models.mysql.ObdMysql;

//@Transactional
//@Repository
public interface ObdMysqlRepository extends JpaRepository<ObdMysql, String> {
	
	public List<ObdMysql> findAll();
	
	/*public Optional<ObdMysql> findBySerfId();
	
	public Optional<ObdMysql> findByDriverId();*/

}

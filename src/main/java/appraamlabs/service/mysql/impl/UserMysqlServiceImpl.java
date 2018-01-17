package appraamlabs.service.mysql.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appraamlabs.models.mysql.UserMysql;
import appraamlabs.repositories.mysql.UserMysqlRepository;
import appraamlabs.service.mysql.UserMysqlService;

@Service
public class UserMysqlServiceImpl implements UserMysqlService {

	@Autowired
	private UserMysqlRepository userMysqlRepository;
	
	@Override
	public List<UserMysql> findAll() {
		// TODO Auto-generated method stub
		return userMysqlRepository.findAll();
	}

	@Override
	public Optional<UserMysql> findOne(String username, String password) {
		// TODO Auto-generated method stub
		return userMysqlRepository.findOne(username, password);
	}

}

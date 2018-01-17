package appraamlabs.service.impl;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appraamlabs.models.User;
import appraamlabs.repositories.UserRepository;
import appraamlabs.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		userRepository.delete(user);
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public Optional<User> getById(String id) {
		// TODO Auto-generated method stub
		return userRepository.findOne(id);
	}

	@Override
	public Optional<User> findUserByUserNameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		return userRepository.findByUsernameAndPassword(username, password);
	}

	@Override
	public Optional<User> findUserByUserName(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}

}

package appraamlabs.service;

import java.util.List;
import java.util.Optional;

import appraamlabs.models.User;

public interface UserService {

	public User saveUser(User user);
	
	public void deleteUser(User user);
	
	public List<User> getAllUsers();

	public Optional<User> getById(String id);
		
	public Optional<User> findUserByUserName(String username);
	
	public Optional<User> findUserByUserNameAndPassword(String username, String password);
}

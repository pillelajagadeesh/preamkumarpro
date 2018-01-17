package appraamlabs.repositories;

import java.util.List;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

import appraamlabs.models.User;

@Component
public interface UserRepository extends MongoRepository<User, Long> {

	public void delete(User deleted);
	 
    public List<User> findAll();
 
 	@Query(value="{id : ?0}")
    public Optional<User> findOne(String id);
 
 	public User save(User user);
 	 	
 	@Query(value="{'username' : ?0, 'password' : ?1}") /*fields="{email : 1}"*/
 	public Optional<User> findByUsernameAndPassword(String username, String password);
 	
 	@Query(value="{'username' : ?0}") /*fields="{email : 1}"*/
 	public Optional<User> findByUsername(String username);
}

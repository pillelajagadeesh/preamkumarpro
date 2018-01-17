package appraamlabs.controllers;

import appraamlabs.models.User;


import appraamlabs.service.UserService;
import appraamlabs.service.dtos.ResponseDTO;
import appraamlabs.service.dtos.UserDTO;

import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.NestedServletException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/user")
public class UserController {

   @Autowired
   private UserService userService;
   
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
   
  @RequestMapping("/delete/{id}")
  @DELETE
  @Path("/delete/(id}")
  @Consumes(MediaType.APPLICATION_JSON)
  public @ResponseBody ResponseDTO delete(@PathVariable("id") String id) {
	  logger.debug("Request to delete user with id {}", id);
    try {
      User user = new User(id);
      //_userDao.delete(user);
      userService.deleteUser(user);
    }
    catch(Exception ex) {
  	  logger.error("Error while deleting user with id {} with message {}", id, ex.getMessage());
        return new ResponseDTO(500, "Something went wrong");
    }
    return new ResponseDTO(200, "User deleted successfully");
  }
  
  @RequestMapping("/get-by-username/{username}")
  @GET
  @Path("/get-by-username/{username}")
  @Consumes(MediaType.APPLICATION_JSON)
  public @ResponseBody ResponseDTO getByEmail(@PathVariable("username") String username) {
	  
	  logger.debug("Request to access user with username {}", username);
    Optional<User> user = null;
    try {
/*      User user = _userDao.getByEmail(email);
      userId = String.valueOf(user.getId());*/
    	user = userService.findUserByUserName(username);
    }
    catch(Exception ex) {
    	logger.error("Error while accessing user with username {} with message {}", username, ex.getMessage());
      return new ResponseDTO(500, "User not found");
    }
    return new ResponseDTO(200, "User details", user.get());
  }

  @RequestMapping("/get-by-id/{id}")
  @GET
  @Path("/get-by-id/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  public @ResponseBody ResponseDTO getById(@PathVariable("id") String id) throws NestedServletException {
	  logger.debug("Request to access user by id {}", id);
    Optional<User> user = null;
    try {
/*      User user = _userDao.getByEmail(email);
      userId = String.valueOf(user.getId());*/
    	user = userService.getById(id);
    }
    catch(Exception ex) {
    	logger.error("Error while accessing user by id {} with message {}", id, ex.getMessage());
      return new ResponseDTO(500, "User not found");
    }
    return new ResponseDTO(200, "User details", user.get());
  }

  @RequestMapping("/save")
  @POST
  @Path("/save")
  @Consumes(MediaType.APPLICATION_JSON)
  public @ResponseBody ResponseDTO create(@RequestBody UserDTO userDTO) { /*String email, String name*/
	  logger.debug("Request to save user by username {}",userDTO.getUsername());
	  User user = null;
    try {
     user = new User(userDTO.getRole(), userDTO.getPassword(), userDTO.getUsername(), userDTO.getDevicetoken());      
      user = userService.saveUser(user);
      //_userDao.save(user);
    }
    catch(Exception ex) {
      //return ex.getMessage();
    	logger.error("Error while saving user by username {} with message {}",userDTO.getUsername(),ex.getMessage());
        return new ResponseDTO(500, ex.getStackTrace().toString());
    }
    return new ResponseDTO(200, "User successfully Registered", user);
  }
  
  @RequestMapping("/update")
  @PUT
  @Path("/update")
  @Consumes(MediaType.APPLICATION_JSON)
  public @ResponseBody ResponseDTO update(@RequestBody UserDTO userDTO) {
	  
	  logger.debug("Request to update user by username {}",userDTO.getUsername());
	  Optional<User> optUser = userService.findUserByUserName(userDTO.getUsername());
	  
	  if(optUser.isPresent() && optUser.get().getPassword().equals(userDTO.getPassword())){
		  logger.debug("User exists with username {} ", userDTO.getUsername());
		  User existingUser = optUser.get();
		  existingUser.setDevicetoken(userDTO.getDevicetoken());
		  userService.saveUser(existingUser);
		  return new ResponseDTO(200, "User successfully Updated", existingUser);
	  }else{
		  logger.error("No user exists by username {} and password {}",userDTO.getUsername(), userDTO.getPassword());
		  return new ResponseDTO(400, "No user exists with username "+userDTO.getUsername()+" and password "+userDTO.getPassword());
	  }
  }

/*  @RequestMapping("/authunticate")
  @POST
  @Path("/authunticate")
  @Consumes(MediaType.APPLICATION_JSON)
  @ResponseBody
  public ResponseDTO authunticateUser(@RequestBody AuthunticateDTO authunticateDTO){
	  //User user = _userDao.getByEmail(authunticateDTO.getEmail());
	  User user = userService.findUserByEmail(authunticateDTO.getEmail());
	  //User user = userRepository.findUserByEmail(authunticateDTO.getEmail());
	  if(user.getEmail().equals(authunticateDTO.getEmail()) && user.getDeviceId().equals(authunticateDTO.getDeviceId())){
		    return new ResponseDTO(200, "Authuntication successfull", user);  
	  }else{
		  if(user.getEmail().equals(authunticateDTO.getEmail()) && !user.getDeviceId().equals(authunticateDTO.getDeviceId())){
			  user.setDeviceId(authunticateDTO.getDeviceId());
			  //_userDao.update(user);
			  user = userService.saveUser(user);
			  userRepository.update(user);
			  return new ResponseDTO(200, "Authuntication successfull", user);
		  }else{
		      return new ResponseDTO(401, "Unauthorized");
		  }
	  }
	  
  }*/
} // class UserController

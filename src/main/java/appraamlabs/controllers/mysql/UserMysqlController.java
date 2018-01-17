package appraamlabs.controllers.mysql;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import appraamlabs.models.mysql.UserMysql;
import appraamlabs.service.dtos.ResponseDTO;
import appraamlabs.service.dtos.mysql.UserMysqlDTO;
import appraamlabs.service.mysql.UserMysqlService;

@RestController
@RequestMapping("/usermysql")
public class UserMysqlController {

	@Autowired
	private UserMysqlService userMysqlService;
	
	private static Logger logger = LoggerFactory.getLogger(UserMysqlController.class);
	
	@RequestMapping("/getAll")
	@GET
	@Path("/getAll")
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody ResponseDTO findAllUsers(){
		
		logger.debug("Request to access users");
		List<UserMysql> users =  userMysqlService.findAll();
		return new ResponseDTO(200, "Users data ", users);
	}
	
	@RequestMapping("/uservalidation")
	@POST
	@Path("/uservalidation")
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody ResponseDTO userValidation(@RequestBody UserMysqlDTO userMysqlDTO){
		
		logger.debug("Request to access user with username {}",userMysqlDTO.getUserName());
		Optional<UserMysql> optUser =  userMysqlService.findOne(userMysqlDTO.getUserName(), userMysqlDTO.getPassword());
		if(optUser.isPresent()){
			return new ResponseDTO(200, "Users data ", optUser.get());
		}else{
			return new ResponseDTO(400, "No user data found");
		}

	}
}

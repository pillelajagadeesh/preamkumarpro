package appraamlabs.controllers.mysql;

import java.util.List;
import java.util.NoSuchElementException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import appraamlabs.models.mysql.ObdMysql;
import appraamlabs.service.dtos.ResponseDTO;
import appraamlabs.service.mysql.ObdMysqlService;

@RestController
@RequestMapping("/obdmysql")
public class ObdMysqlController {

	@Autowired
	private ObdMysqlService obdMysqlService;
	
	private static Logger logger = LoggerFactory.getLogger(ObdMysqlController.class);
	
	@RequestMapping("/getAll")
	  @GET
	  @Path("/getAll")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO getAllWPBeacons() throws NoSuchElementException{
		  
		  logger.debug("Request to access all waypoint beacons");
	    List<ObdMysql> obds = null;
	    try {
	    	obds = obdMysqlService.findAll();
	    }
	    catch(Exception ex) {
	    	logger.error("Error with message {}",ex.getMessage());
	      return new ResponseDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	    }
	    return new ResponseDTO(HttpStatus.OK.value(), "Obd's data", obds);
	  }
}

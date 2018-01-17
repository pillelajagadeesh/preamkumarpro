package appraamlabs.controllers;

import java.util.NoSuchElementException;

import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.apache.catalina.Manager;
import org.apache.catalina.Session;
import org.apache.catalina.session.StandardSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import appraamlabs.service.dtos.ResponseDTO;

@RestController
@RequestMapping("/pentaho")
public class PentahoServerController {
	
	private static Logger logger = LoggerFactory.getLogger(PentahoServerController.class);
	
	  @RequestMapping("/getUserAndRole")
	  @GET
	  @Path("/getUserAndRole")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public @ResponseBody ResponseDTO getUserAndRole(Session session) throws NoSuchElementException{
		  
		  logger.debug("Request to get pentaho user and role");
		  
		  String name;
	    try {
	    	name = session.getPrincipal().getName();
	    	Manager manager = session.getManager();
	    	Session[] sessions = manager.findSessions();
	    	int sessionLength = sessions.length;
	    }
	    catch(Exception ex) {
	    	logger.error("Error with message {}",ex.getMessage());
	      return new ResponseDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	    }
	    return new ResponseDTO(HttpStatus.OK.value(), "Session Attribute name : "+name);
	  }

}

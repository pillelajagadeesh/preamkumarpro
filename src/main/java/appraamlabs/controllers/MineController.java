package appraamlabs.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import appraamlabs.models.MineModel;
import appraamlabs.models.VehicleProfile;
import appraamlabs.service.MineService;
import appraamlabs.service.dtos.ResponseDTO;


@RestController
@RequestMapping("/mine")
public class MineController {

	@Autowired
	private MineService mineService;
	
	private static Logger logger = LoggerFactory.getLogger(MineController.class);
	
	
	@RequestMapping("/getAllMines")
	@GET
	@Path("/getAllMines")
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody ResponseDTO getAll() throws NoSuchElementException {
		logger.debug("Request to get all Mines");
		List<MineModel> mineModel = null;
		try {
			mineModel = mineService.getAllMines();
			
		} catch (Exception ex) {
			logger.error("Error with message {}",ex.getMessage());
			return new ResponseDTO(500, ex.getMessage());
		}
		return new ResponseDTO(200, "Mines data", mineModel);
	}
	
	
}

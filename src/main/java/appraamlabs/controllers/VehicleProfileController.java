package appraamlabs.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.NestedServletException;

import appraamlabs.models.Beacon;
import appraamlabs.models.DriverBeacon;
import appraamlabs.models.DriverProfile;
import appraamlabs.models.SerfsBoard;
import appraamlabs.models.VehicleProfile;
import appraamlabs.service.SerfsBoardService;
import appraamlabs.service.VehicleProfileService;
import appraamlabs.service.dtos.DriverProfileDTO;
import appraamlabs.service.dtos.ResponseDTO;
import appraamlabs.service.dtos.VehicleProfileDTO;
import appraamlabs.utils.enums.Status;

@RestController
@RequestMapping("/vehicle")
public class VehicleProfileController {

	@Autowired
	private VehicleProfileService vehicleProfileService;

	@Autowired
	private SerfsBoardService serfsBoardService;

	private static Logger logger = LoggerFactory.getLogger(VehicleProfileController.class);

	@RequestMapping("/delete/{id}")
	@DELETE
	@Path("/delete/(id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody ResponseDTO delete(@PathVariable("id") String id) {
		logger.debug("Request to delete vehicle  with vehicle id {}", id);
		Optional<VehicleProfile> optvehiclePro = vehicleProfileService.getById(id);

		if (optvehiclePro.isPresent()) {
			VehicleProfile existingVehiclePro = optvehiclePro.get();
			vehicleProfileService.saveVehicleProfile(existingVehiclePro);
		} else {
			return new ResponseDTO(500, "Invalid request");
		}
		return new ResponseDTO(200, "vehicle successfully deleted", optvehiclePro);
	}

	@RequestMapping("/search/{searchText}")
	@GET
	@Path("/search/{searchText}")
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody ResponseDTO searchSerf(@PathVariable("searchText") String searchText)
			throws NestedServletException {

		logger.debug("Request to search serf by text {}", searchText);
		List<VehicleProfile> vehicleSearch = null;
		try {
			vehicleSearch = vehicleProfileService.searchVehicle(searchText);
		} catch (Exception ex) {
			return new ResponseDTO(HttpStatus.BAD_REQUEST.value(), "SerfsBoard not found");
		}
		return new ResponseDTO(HttpStatus.OK.value(), "SerfsBoard details by search", vehicleSearch);
	}

	@RequestMapping("/get-by-vehicleNo/{vehicleNo}")
	@GET
	@Path("/get-by-vehicleNo/{vehicleNo}")
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody ResponseDTO getByVehicleNo(@PathVariable("vehicleNo") String vehicleNo)
			throws NoSuchElementException {
		logger.debug("Request to get vehicle by vehicle no {}", vehicleNo);

		Optional<VehicleProfile> optionalVehicleProfile = vehicleProfileService
				.findVehicleProfileByVehicleNo(vehicleNo);

		if (optionalVehicleProfile.isPresent()) {
			return new ResponseDTO(200, "Vehicle data", optionalVehicleProfile.get());
		} else {
			return new ResponseDTO(404, "No vehicle exists");
		}

	}

	@RequestMapping("/clean/{id}")
	@DELETE
	@Path("/clean/(id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody ResponseDTO clean(@PathVariable("id") String id) {
		logger.debug("Request to clean vehicle by id {}", id);
		VehicleProfile vehicleProfile = new VehicleProfile(id);
		vehicleProfileService.deleteVehicleProfile(vehicleProfile);

		return new ResponseDTO(200, "vehicle details successfully cleaned");
	}

	@RequestMapping("/save")
	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody ResponseDTO create(@RequestBody VehicleProfileDTO vehicleProfileDTO) {
		logger.debug("Request to save vehicle by vehicle no {}", vehicleProfileDTO.getVehicleNo());
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date today = Calendar.getInstance().getTime();

		Optional<VehicleProfile> optVehicleProfile = vehicleProfileService
				.findVehicleProfileByVehicleNo(vehicleProfileDTO.getVehicleNo());

		if (!optVehicleProfile.isPresent()) {
			VehicleProfile vehicleProfile = new VehicleProfile(vehicleProfileDTO.getMine(),
					vehicleProfileDTO.getVehicleNo(), vehicleProfileDTO.getVehicleModel(), "",
					vehicleProfileDTO.getRoleType(), vehicleProfileDTO.getContractAgency(),
					vehicleProfileDTO.getContractExpiryDate(), vehicleProfileDTO.getRegistrationYear(),
					vehicleProfileDTO.getMake(), vehicleProfileDTO.getFuelLevel(), vehicleProfileDTO.getKmDriven(),
					vehicleProfileDTO.getInsuranceProvider(), vehicleProfileDTO.getInsuranceExpiryDate(),
					vehicleProfileDTO.getVehicleStatus(), vehicleProfileDTO.getVehicleDoorNo(),
					vehicleProfileDTO.getUser());

			vehicleProfile.setCreatedDate(formatter.format(today));
			vehicleProfile.setUpdatedDate(formatter.format(today));
			vehicleProfileService.saveVehicleProfile(vehicleProfile);

			return new ResponseDTO(Integer.parseInt(HttpStatus.OK.toString()),
					"VehicleProfile successfully Registered with vehicle No" + vehicleProfileDTO.getVehicleNo(),
					vehicleProfile);
		} else {
			return new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
					"Vehicle already registered with vehicle No " + vehicleProfileDTO.getVehicleNo());
		}

	}

	@RequestMapping("/getAll")
	@GET
	@Path("/getAll")
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody ResponseDTO getAll(Pageable pageable) throws NoSuchElementException {
		logger.debug("Request to get all vehicles");
		//List<VehicleProfile> vehicleProfile = null;
		Page<VehicleProfile> vehicleProfiles = null;
		try {
			vehicleProfiles = vehicleProfileService.findAllVehicles(pageable);

			/*for (VehicleProfile vp : vehicleProfile) {
				Optional<SerfsBoard> serf = serfsBoardService.findOneByDeviceName(vp.getSerfName());
				if (serf.isPresent()) {
					vehicleProfilenew.add(vp);
				} else {
					vp.setSerfName("");
					vehicleProfileService.saveVehicleProfile(vp);
					vehicleProfilenew.add(vp);
				}

			}*/

			/*
			 * Collections.sort (vehicleProfile, new Comparator<VehicleProfile>
			 * () { public int compare ( VehicleProfile a, VehicleProfile d) {
			 * return (d.getUpdatedDate().compareTo(a.getUpdatedDate())); }});
			 */
		} catch (Exception ex) {
			logger.error("Error with message {}", ex.getMessage());
			return new ResponseDTO(500, ex.getMessage());
		}
		return new ResponseDTO(200, "Vehicle's data", vehicleProfiles);
	}
	
	@RequestMapping("/getAllMappedVehicles")
	@GET
	@Path("/getAllMappedVehicles")
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody ResponseDTO getAllMappedVehicles(Pageable pageable) throws NoSuchElementException {
		logger.debug("Request to get all unmapped vehicles");
		Page<VehicleProfile> vehicleProfiles = null;
		try {
			vehicleProfiles = vehicleProfileService.findAllMappedVehicles(pageable);
		} catch (Exception ex) {
			logger.error("Error with message {}", ex.getMessage());
			return new ResponseDTO(500, ex.getMessage());
		}
		return new ResponseDTO(200, "Mapped Vehicle's data", vehicleProfiles);
	}

	@RequestMapping("/update/{id}")
	@GET
	@Path("/update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody ResponseDTO update(@PathVariable("id") String id,
			@RequestBody VehicleProfileDTO vehicleProfileDTO) throws NoSuchElementException {
		logger.debug("Request to update vehicle by vehicle no {}", vehicleProfileDTO.getVehicleNo());
		Optional<VehicleProfile> existingVehicleProfile = vehicleProfileService.getById(id);
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date today = Calendar.getInstance().getTime();

		/*
		 * if (existingVehicleProfile.isPresent() &&
		 * existingVehicleProfile.get().getStatus().equals(Status.ACTIVE)) {
		 */
		if (existingVehicleProfile.isPresent()) {
			VehicleProfile oldVehicleProfile = existingVehicleProfile.get();
			oldVehicleProfile.setMine(vehicleProfileDTO.getMine());
			oldVehicleProfile.setVehicleNo(vehicleProfileDTO.getVehicleNo());
			oldVehicleProfile.setVehicleModel(vehicleProfileDTO.getVehicleModel());
			oldVehicleProfile.setSerfName(vehicleProfileDTO.getSerfName());
			oldVehicleProfile.setRoleType(vehicleProfileDTO.getRoleType());
			oldVehicleProfile.setContractAgency(vehicleProfileDTO.getContractAgency());
			oldVehicleProfile.setContractExpiryDate(vehicleProfileDTO.getContractExpiryDate());
			oldVehicleProfile.setRegistrationYear(vehicleProfileDTO.getRegistrationYear());
			oldVehicleProfile.setMake(vehicleProfileDTO.getMake());

			oldVehicleProfile.setFuelLevel(vehicleProfileDTO.getFuelLevel());
			oldVehicleProfile.setKmDriven(vehicleProfileDTO.getKmDriven());
			oldVehicleProfile.setInsuranceProvider(vehicleProfileDTO.getInsuranceProvider());
			oldVehicleProfile.setInsuranceExpiryDate(vehicleProfileDTO.getInsuranceExpiryDate());
			oldVehicleProfile.setVehicleStatus(vehicleProfileDTO.getVehicleStatus());
			oldVehicleProfile.setVehicleDoorNo(vehicleProfileDTO.getVehicleDoorNo());
			oldVehicleProfile.setUpdatedDate(formatter.format(today));
			vehicleProfileService.saveVehicleProfile(oldVehicleProfile);
		} else {
			return new ResponseDTO(HttpStatus.BAD_REQUEST.value(), "No vehicle exists to update with vehicle no " + id);
		}

		return new ResponseDTO(HttpStatus.OK.value(),
				"vehicle details successfully updated with vehicle no " + existingVehicleProfile.get().getVehicleNo(),
				existingVehicleProfile);

	}

}

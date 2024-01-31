package rs.ac.uns.ftn.springsecurityexample.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.springsecurityexample.dto.AppointmentDTO;
import rs.ac.uns.ftn.springsecurityexample.dto.CompanyDTO;
import rs.ac.uns.ftn.springsecurityexample.mapper.AppointmentMapper;
import rs.ac.uns.ftn.springsecurityexample.mapper.CompanyMapper;
import rs.ac.uns.ftn.springsecurityexample.model.Appointment;
import rs.ac.uns.ftn.springsecurityexample.model.Company;
import rs.ac.uns.ftn.springsecurityexample.model.User;
import rs.ac.uns.ftn.springsecurityexample.service.AppointmentService;
import rs.ac.uns.ftn.springsecurityexample.service.CompanyService;
import rs.ac.uns.ftn.springsecurityexample.service.UserService;

@RestController
@RequestMapping(value = "api/appointment", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin

public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private UserService userService;
	
	@PutMapping("/take/{appointmentId}/{equipmentId}") 
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<AppointmentDTO> takeAppointment(@PathVariable Long appointmentId,@PathVariable Long equipmentId, Principal loggedUser) {
		User user = this.userService.findByUsername(loggedUser.getName());
		Appointment appointment = this.appointmentService.takeAppointment(appointmentId, equipmentId, user.getId());
		if(appointment == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		AppointmentDTO dto = AppointmentMapper.toDTO(appointment);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@PutMapping("/cancel/{appointmentId}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<AppointmentDTO> cancelAppointment(@PathVariable Long appointmentId) {
		Appointment appointment = this.appointmentService.cancelAppointment(appointmentId);
		if(appointment == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		AppointmentDTO dto = AppointmentMapper.toDTO(appointment);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@GetMapping("/allFutureForUser") 
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<AppointmentDTO>> getAllFutureAppointmentsForUser(Principal loggedUser) {
		User user = this.userService.findByUsername(loggedUser.getName());
		List<Appointment> appointments = this.appointmentService.getAllFutureAppointmentsForUser(user.getId());
		List<AppointmentDTO> dtos = new ArrayList<>();
		for (Appointment appointment : appointments) {
			dtos.add(AppointmentMapper.toDTO(appointment));
		}
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("/available/{companyId}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<AppointmentDTO>> getFutureAvailableByCompanyId(@PathVariable Long companyId) {
		List<Appointment> appointments = this.appointmentService.getFutureAvailableByCompanyId(companyId);
		List<AppointmentDTO> dtos = new ArrayList<>();
		for (Appointment appointment : appointments) {
			dtos.add(AppointmentMapper.toDTO(appointment));
		}
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
/*
	@GetMapping("/available/equipment/{equipmentId}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<AppointmentDTO>> getFutureAvailableByEquipmentId(@PathVariable Long equipmentId) {
		List<Appointment> appointments = this.appointmentService.getFutureAvailableByEquipmentId(equipmentId);
		List<AppointmentDTO> dtos = new ArrayList<>();
		for (Appointment appointment : appointments) {
			dtos.add(AppointmentMapper.toDTO(appointment));
		}
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}*/
	/*
	@GetMapping("/equipment/{equipmentId}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<AppointmentDTO>> getByEquipmentId(@PathVariable Long equipmentId) {
		List<Appointment> appointments = this.appointmentService.getByEquipmentId(equipmentId);
		List<AppointmentDTO> dtos = new ArrayList<>();
		for (Appointment appointment : appointments) {
			dtos.add(AppointmentMapper.toDTO(appointment));
		}
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}*/

}

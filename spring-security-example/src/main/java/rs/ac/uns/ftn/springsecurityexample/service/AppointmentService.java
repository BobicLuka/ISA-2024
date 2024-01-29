package rs.ac.uns.ftn.springsecurityexample.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.springsecurityexample.model.Appointment;
import rs.ac.uns.ftn.springsecurityexample.model.User;
import rs.ac.uns.ftn.springsecurityexample.model.enums.AppointmentStatus;
import rs.ac.uns.ftn.springsecurityexample.repository.AppointmentRepository;

@Service
public class AppointmentService {
	@Autowired
	protected AppointmentRepository repository;
	
	@Autowired
	private UserService userService;
	
	public Appointment getById(long id) {
		Appointment company = repository.findById(id).orElse(null);
		return company;
	}

	public List<Appointment> getByEquipmentId(Long equipmentId) {
		return repository.findByEquipmentId(equipmentId);
	}

	public List<Appointment> getFutureAvailableByEquipmentId(Long equipmentId) {
		return new ArrayList<Appointment>(repository.findByEquipmentIdAndStatusInAndStartDateAfter(equipmentId,
				Arrays.asList(AppointmentStatus.SCHEDULED, AppointmentStatus.CANCELED), LocalDateTime.now()));
	}
	
	public Appointment takeAppointment(Long id, Long userId) {
		Appointment appointment = repository.findById(id).orElseGet(null);
		User user = userService.findById(userId);
		if(appointment == null || user == null) {
			return null;
		}
		if(appointment.getStatus() != AppointmentStatus.TAKEN && appointment.getStartDate().isAfter(LocalDateTime.now())) {	//ako termin nije vec zauzet			
			appointment.setStatus(AppointmentStatus.TAKEN);
			appointment.setUser(user);
		}
		return repository.save(appointment);
	}
	
	public List<Appointment> getAllFutureAppointmentsForUser(Long userId) {
		return repository.findByUserIdAndStartDateAfter(userId, LocalDateTime.now());
	}
	
}

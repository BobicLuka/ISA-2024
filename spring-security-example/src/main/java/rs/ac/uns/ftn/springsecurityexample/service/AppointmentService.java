package rs.ac.uns.ftn.springsecurityexample.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.springsecurityexample.model.Appointment;
import rs.ac.uns.ftn.springsecurityexample.model.Equipment;
import rs.ac.uns.ftn.springsecurityexample.model.User;
import rs.ac.uns.ftn.springsecurityexample.model.enums.AppointmentStatus;
import rs.ac.uns.ftn.springsecurityexample.repository.AppointmentRepository;

@Service
public class AppointmentService {
	@Autowired
	protected AppointmentRepository repository;
	@Autowired
	private UserService userService;
	@Autowired
	private EquipmentService equipmentService;
	@Autowired
	private EmailService emailService;

	public Appointment getById(long id) {
		Appointment company = repository.findById(id).orElse(null);
		return company;
	}

	public List<Appointment> getByEquipmentId(Long equipmentId) {
		return repository.findByEquipmentId(equipmentId);
	}

	public List<Appointment> getFutureAvailable() {
		return new ArrayList<Appointment>(repository.findByStatusInAndStartDateAfter(
				Arrays.asList(AppointmentStatus.SCHEDULED, AppointmentStatus.CANCELED), LocalDateTime.now()));
	}

	public Appointment takeAppointment(Long appointmentId, Long equipmentId, Long userId) {

		Appointment appointment = repository.findById(appointmentId).orElseGet(null);
		Equipment equipment = equipmentService.getById(equipmentId);
		User user = userService.findById(userId);

		if (!canUserMakeAppointment(user, appointment) || appointment == null || user == null || equipment == null) {
			return null;
		}

		if (appointment.getStatus() != AppointmentStatus.TAKEN
				&& appointment.getStartDate().isAfter(LocalDateTime.now())) { // ako termin nije vec zauzet
			appointment.setStatus(AppointmentStatus.TAKEN);
			appointment.setUser(user);
			appointment.setEquipment(equipment);
		}else {
			return null;
		}
		Appointment reservedAppointment = repository.save(appointment);
		try {
			emailService.sendQrCode(user, reservedAppointment);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reservedAppointment;
	}

	private boolean canUserMakeAppointment(User user, Appointment appointment) {
		if (appointment.getStatus() != AppointmentStatus.CANCELED) {
			return true;
		}
		return user.getId() != appointment.getUser().getId();
	}


	public Appointment cancelAppointment(Long appointmentId) {
		Appointment appointment = repository.findById(appointmentId).orElseGet(null);
		User user = appointment.getUser();
		if (appointment == null || user == null) {
			return null;
		}
		LocalDateTime appointmentStart = appointment.getStartDate();
		if (appointment.getStatus() == AppointmentStatus.TAKEN && appointmentStart.isAfter(LocalDateTime.now())) {
			appointment.setStatus(AppointmentStatus.CANCELED);
			appointment.setEquipment(null);
			// appointment.setUser(null)
			if (appointmentStart.minusDays(1).isBefore(LocalDateTime.now())) {
				user.setPenaltyPoints(user.getPenaltyPoints() + 2);
			} else {
				user.setPenaltyPoints(user.getPenaltyPoints() + 1);
			}
			User savedUser = userService.saveUser(user);
			appointment.setUser(savedUser); // nzm jel treba
			appointment = repository.save(appointment);
		}
		return appointment;
	}

	public List<Appointment> getAllFutureAppointmentsForUser(Long userId) {
		return repository.findByUserIdAndStartDateAfter(userId, LocalDateTime.now());
	}
	
	/*
	 public List<Appointment> getFutureAvailableByEquipmentId(Long equipmentId) {
		return new ArrayList<Appointment>(repository.findByEquipmentIdAndStatusInAndStartDateAfter(equipmentId,
				Arrays.asList(AppointmentStatus.SCHEDULED, AppointmentStatus.CANCELED), LocalDateTime.now()));
	}
	 */
}

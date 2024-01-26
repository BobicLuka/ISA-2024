package rs.ac.uns.ftn.springsecurityexample.model;

import java.time.LocalDateTime;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import rs.ac.uns.ftn.springsecurityexample.model.enums.AppointmentStatus;


@Entity
@Table(name = "APPOINTMENT")
public class Appointment {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "status", nullable = false)
	private AppointmentStatus status;

	@Column(name = "startDate", nullable = false)
	private LocalDateTime startDate;

	@Column(name = "duration", nullable = false)
	private int duration;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "equipment_id", nullable = false)
	private Equipment equipment;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "administrator_id", nullable = false)
	private User administrator;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = true)
	private User user;

}
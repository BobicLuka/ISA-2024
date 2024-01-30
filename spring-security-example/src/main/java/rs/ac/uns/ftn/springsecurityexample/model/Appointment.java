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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AppointmentStatus getStatus() {
		return status;
	}

	public void setStatus(AppointmentStatus status) {
		this.status = status;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public User getAdministrator() {
		return administrator;
	}

	public void setAdministrator(User administrator) {
		this.administrator = administrator;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public String getQrCodeData() {
		return "Appointment [id=" + id + ", startDate=" + startDate + ", duration=" + duration
				+ ", equipmentId=" + equipment.getId() + ", administratorId=" + administrator.getId() + ", userId=" + user.getId() + "]";
	}

}
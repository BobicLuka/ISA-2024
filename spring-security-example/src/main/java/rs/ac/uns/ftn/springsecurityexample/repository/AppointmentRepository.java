package rs.ac.uns.ftn.springsecurityexample.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.springsecurityexample.model.Appointment;
import rs.ac.uns.ftn.springsecurityexample.model.Company;
import rs.ac.uns.ftn.springsecurityexample.model.Complaint;
import rs.ac.uns.ftn.springsecurityexample.model.enums.AppointmentStatus;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	
	List<Appointment> findByEquipmentId(Long equipmentId);
	List<Appointment> findByEquipmentIdAndStatusInAndStartDateAfter(Long equipmentId, List<AppointmentStatus> statusList, LocalDateTime currentDate);
	List<Appointment> findByUserIdAndStartDateAfter(Long userId, LocalDateTime currentDate);
	List<Appointment> findByAdministrator_Company_IdAndStatusInAndStartDateAfter(Long companyId, List<AppointmentStatus> statusList, LocalDateTime currentDate);
	List<Appointment> findByUserIdAndAdministratorIdAndStartDateAfter(Long userId,Long administratorId, LocalDateTime currentDate);
	List<Appointment> findByUserIdAndEquipment_Company_IdAndStartDateAfter(Long userId,Long companyId, LocalDateTime currentDate);


}
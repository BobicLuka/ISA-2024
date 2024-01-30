package rs.ac.uns.ftn.springsecurityexample.mapper;

import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.springsecurityexample.dto.AppointmentDTO;
import rs.ac.uns.ftn.springsecurityexample.model.Appointment;

@Component
public class AppointmentMapper {

	public static AppointmentDTO toDTO(Appointment entity) {
		AppointmentDTO dto = new AppointmentDTO();
		dto.setId(entity.getId());
		dto.setStatus(entity.getStatus());
		dto.setStartDate(entity.getStartDate());
		dto.setDuration(entity.getDuration());

		if (entity.getEquipment() != null) {
			dto.setEquipmentId(entity.getEquipment().getId());
			dto.setEquipmentName(entity.getEquipment().getName());
		}
		if (entity.getAdministrator() != null) {
			dto.setAdministratorId(entity.getAdministrator().getId());
			dto.setAdministratorName(
					entity.getAdministrator().getFirstName() + " " + entity.getAdministrator().getLastName());
		}
		if (entity.getUser() != null) {
			dto.setUserId(entity.getUser().getId());
			dto.setUserName(entity.getUser().getFirstName() + " " + entity.getUser().getLastName());
		}
		return dto;
	}

}

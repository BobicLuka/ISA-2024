package rs.ac.uns.ftn.springsecurityexample.mapper;

import org.springframework.stereotype.Component;

import rs.ac.uns.ftn.springsecurityexample.dto.ComplaintResponseDTO;
import rs.ac.uns.ftn.springsecurityexample.model.ComplaintResponse;

@Component
public class ComplaintResponseMapper {

	public static ComplaintResponseDTO toDTO(ComplaintResponse entity) {
		ComplaintResponseDTO dto = new ComplaintResponseDTO();
		dto.setId(entity.getId());
		dto.setResponse(entity.getResponse());
		dto.setComplaintId(entity.getComplaint().getId());
		dto.setSystemAdminId(entity.getSystemAdmin().getId());

		return dto;
	}
	
	
}
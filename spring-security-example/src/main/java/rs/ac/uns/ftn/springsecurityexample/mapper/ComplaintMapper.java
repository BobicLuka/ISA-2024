package rs.ac.uns.ftn.springsecurityexample.mapper;

import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.springsecurityexample.dto.ComplaintDTO;
import rs.ac.uns.ftn.springsecurityexample.model.Complaint;

@Component
public class ComplaintMapper {

	public static ComplaintDTO toDTO(Complaint entity) {
		ComplaintDTO dto = new ComplaintDTO();
		dto.setId(entity.getId());
		dto.setText(entity.getText());
		dto.setComplainantId(entity.getComplainant().getId());
		dto.setComplainantName(entity.getComplainant().getFirstName() + " " + entity.getComplainant().getLastName());

		if (entity.getAdmin() != null) {
			dto.setAdminId(entity.getAdmin().getId());
			dto.setAdminName(entity.getAdmin().getFirstName() + " " + entity.getAdmin().getLastName());
		}

		if (entity.getCompany() != null) {
			dto.setCompanyId(entity.getCompany().getId());
			dto.setCompanyName(entity.getCompany().getName());
		}

		if (entity.getComplaintResponse() != null) {
			dto.setComplaintResponseId(entity.getComplaintResponse().getId());
			dto.setResponse(entity.getComplaintResponse().getResponse());
			dto.setAdminWhoRespondedId(entity.getComplaintResponse().getSystemAdmin().getId());
			dto.setAdminWhoRespondedName(entity.getComplaintResponse().getSystemAdmin().getFirstName() + " "
					+ entity.getComplaintResponse().getSystemAdmin().getLastName());

		}

		return dto;
	}
}

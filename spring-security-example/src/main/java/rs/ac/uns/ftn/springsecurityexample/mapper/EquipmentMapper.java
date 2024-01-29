package rs.ac.uns.ftn.springsecurityexample.mapper;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

import rs.ac.uns.ftn.springsecurityexample.dto.CompanyDTO;
import rs.ac.uns.ftn.springsecurityexample.dto.EquipmentDTO;
import rs.ac.uns.ftn.springsecurityexample.model.Company;
import rs.ac.uns.ftn.springsecurityexample.model.Equipment;

@Component

public class EquipmentMapper {
	
	public static EquipmentDTO toDTO(Equipment entity) {
		EquipmentDTO dto = new EquipmentDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setPrice(entity.getPrice());
		dto.setCompanyId(entity.getCompany().getId());
		dto.setCompanyName(entity.getCompany().getName());
		return dto;
	}
	
	
}

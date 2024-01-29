package rs.ac.uns.ftn.springsecurityexample.mapper;

import org.springframework.stereotype.Component;

import rs.ac.uns.ftn.springsecurityexample.dto.CompanyDTO;
import rs.ac.uns.ftn.springsecurityexample.model.Company;

@Component
public class CompanyMapper {
	
	public static CompanyDTO toDTO(Company company) {
		CompanyDTO dto = new CompanyDTO();
		dto.setDescription(company.getDescription());
		dto.setId(company.getId());
		dto.setName(company.getName());
		return dto;
	}
	

	
}

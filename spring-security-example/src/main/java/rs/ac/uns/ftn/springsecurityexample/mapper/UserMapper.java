package rs.ac.uns.ftn.springsecurityexample.mapper;

import org.springframework.stereotype.Component;

import rs.ac.uns.ftn.springsecurityexample.dto.UserDTO;
import rs.ac.uns.ftn.springsecurityexample.model.User;

@Component
public class UserMapper {
	
	public static UserDTO toDTO(User entity) {
		UserDTO dto = new UserDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getFirstName() + " " + entity.getLastName());
		dto.setUsername(entity.getUsername());
		dto.setEmail(entity.getEmail());
		dto.setPenaltyPoints(entity.getPenaltyPoints());
		dto.setCity(entity.getCity());
		dto.setCountry(entity.getCountry());
		dto.setPhoneNumber(entity.getPhoneNumber());
		dto.setProfession(entity.getProfession());
		dto.setRole(entity.getRoles().get(0).getName());
		
		return dto;
	}
}

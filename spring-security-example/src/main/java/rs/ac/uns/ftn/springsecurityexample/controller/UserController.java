package rs.ac.uns.ftn.springsecurityexample.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.springsecurityexample.dto.CompanyDTO;
import rs.ac.uns.ftn.springsecurityexample.dto.UserDTO;
import rs.ac.uns.ftn.springsecurityexample.mapper.CompanyMapper;
import rs.ac.uns.ftn.springsecurityexample.mapper.UserMapper;
import rs.ac.uns.ftn.springsecurityexample.model.Company;
import rs.ac.uns.ftn.springsecurityexample.model.User;
import rs.ac.uns.ftn.springsecurityexample.service.UserService;

// Primer kontrolera cijim metodama mogu pristupiti samo autorizovani korisnici
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/activateAccount/{activationCode}")
	public ResponseEntity<String> verifyUser(@PathVariable String activationCode) {
		if (userService.activateAccount(activationCode)) {
			String message = "<div class=\"container text-center\">\n"
					+ "    <h3>Your account is successfully activated!</h3>\n" + "</div>";
			return ResponseEntity.ok().header("Content-Type", "text/html").body(message);
		} else {
			String errorMessage = "<div class=\"container text-center\">\n"
					+ "    <h3>This activation link is not valid.</h3>\n" + "</div>";
			return ResponseEntity.status(HttpStatus.NOT_FOUND).header("Content-Type", "text/html").body(errorMessage);
		}
	}

	@GetMapping("user/allAdmins")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<UserDTO>> getAllAdmins() {
		List<User> admins = this.userService.getAllAdmins();
		List<UserDTO> dtos = new ArrayList<>();
		for (User admin : admins) {
			UserDTO dto = new UserDTO();
			dto.setId(admin.getId());
			dto.setName(admin.getFirstName() + " " + admin.getLastName());
			dtos.add(dto);
		}
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}

	@GetMapping("/whoami")
	public  ResponseEntity<UserDTO> getLoggedUser(Principal user) {
		User loggedUser = this.userService.findByUsername(user.getName());
		return new ResponseEntity<>(UserMapper.toDTO(loggedUser), HttpStatus.OK);
	}

}

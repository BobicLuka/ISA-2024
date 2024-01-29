package rs.ac.uns.ftn.springsecurityexample.controller;

import java.security.Principal;
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
	            String message = "<div class=\"container text-center\">\n" +
	                    "    <h3>Your account is successfully activated!</h3>\n" +
	                    "</div>";
	            return ResponseEntity.ok()
	                    .header("Content-Type", "text/html")
	                    .body(message);
	        } else {
	            String errorMessage = "<div class=\"container text-center\">\n" +
	                    "    <h3>This activation link is not valid.</h3>\n" +
	                    "</div>";
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .header("Content-Type", "text/html")
	                    .body(errorMessage);
	        }
	    }
	
	

	@GetMapping("/user/{userId}")
	@PreAuthorize("hasRole('ADMIN')")
	public User loadById(@PathVariable Long userId) {
		return this.userService.findById(userId);
	}

	@GetMapping("/user/all")
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> loadAll() {
		return this.userService.findAll();
	}

	@GetMapping("/whoami")
	@PreAuthorize("hasRole('USER')")
	public User user(Principal user) {
		return this.userService.findByUsername(user.getName());
	}

}

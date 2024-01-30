package rs.ac.uns.ftn.springsecurityexample.service.impl;

import java.sql.Timestamp;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnore;

import rs.ac.uns.ftn.springsecurityexample.dto.UserRequest;
import rs.ac.uns.ftn.springsecurityexample.model.Company;
import rs.ac.uns.ftn.springsecurityexample.model.Role;
import rs.ac.uns.ftn.springsecurityexample.model.User;
import rs.ac.uns.ftn.springsecurityexample.repository.UserRepository;
import rs.ac.uns.ftn.springsecurityexample.service.EmailService;
import rs.ac.uns.ftn.springsecurityexample.service.RoleService;
import rs.ac.uns.ftn.springsecurityexample.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private EmailService emailService;


	@Override
	public User findByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username);
	}

	public User findById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	public List<User> findAll() throws AccessDeniedException {
		return userRepository.findAll();
	}

	@Override
	public User save(UserRequest userRequest) {
		User u = new User();
		u.setUsername(userRequest.getUsername());

		//proveri checkPassword i password jesu iste
		u.setPassword(passwordEncoder.encode(userRequest.getPassword()));

		u.setFirstName(userRequest.getFirstname());
		u.setLastName(userRequest.getLastname());

		u.setEnabled(false);

		u.setEmail(userRequest.getEmail());

		List<Role> roles = roleService.findByName("ROLE_USER"); // check
		u.setRoles(roles);

		u.setPenaltyPoints(0);
		u.setCity(userRequest.getCity());
		u.setCountry(userRequest.getCountry());
		u.setPhoneNumber(userRequest.getPhoneNumber());
		u.setProfession(userRequest.getProfession());
		u.setCompanyInfo(userRequest.getCompanyInfo());
		u.setActivationCode(generateActivationCode());
		sendActivationCode(u);
		return this.userRepository.save(u);
	}

	private void sendActivationCode(User user) {
		emailService.sendActivationCode(user);		
	}

	private static String generateActivationCode() {
		// Generate a unique random string
		String randomString = UUID.randomUUID().toString();

		// Append a timestamp to ensure uniqueness
		long timestamp = System.currentTimeMillis();
		String uniqueCode = randomString + timestamp;

		// Encode the unique code using Base64
		byte[] uniqueCodeBytes = uniqueCode.getBytes();
		return Base64.getUrlEncoder().withoutPadding().encodeToString(uniqueCodeBytes);
	}
	
	@Override
	public boolean activateAccount(String activationCode) {
		User user = userRepository.findByActivationCode(activationCode);
		if(user != null && !user.isEnabled()) {
			user.setEnabled(true);
			userRepository.save(user);
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public User saveUser(User user) {
		return this.userRepository.save(user);
	}
	
}

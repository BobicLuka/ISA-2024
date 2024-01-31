package rs.ac.uns.ftn.springsecurityexample.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.springsecurityexample.dto.CompanyDTO;
import rs.ac.uns.ftn.springsecurityexample.dto.ComplaintDTO;
import rs.ac.uns.ftn.springsecurityexample.dto.ComplaintResponseDTO;
import rs.ac.uns.ftn.springsecurityexample.mapper.CompanyMapper;
import rs.ac.uns.ftn.springsecurityexample.mapper.ComplaintMapper;
import rs.ac.uns.ftn.springsecurityexample.mapper.ComplaintResponseMapper;
import rs.ac.uns.ftn.springsecurityexample.model.Company;
import rs.ac.uns.ftn.springsecurityexample.model.Complaint;
import rs.ac.uns.ftn.springsecurityexample.model.ComplaintResponse;
import rs.ac.uns.ftn.springsecurityexample.model.User;
import rs.ac.uns.ftn.springsecurityexample.service.ComplaintService;
import rs.ac.uns.ftn.springsecurityexample.service.UserService;

@RestController
@RequestMapping(value = "api/complaint", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class ComplaintController {
	
	@Autowired
	private ComplaintService complaintService;
	@Autowired
	private UserService userService;
	
	@PostMapping("")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<ComplaintDTO> create(@RequestBody ComplaintDTO complaintDTO, Principal loggedUser) {
		User user = this.userService.findByUsername(loggedUser.getName());
		complaintDTO.setComplainantId(user.getId());
		Complaint complaint = this.complaintService.create(complaintDTO);
		if(complaint == null) {
			return  new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(ComplaintMapper.toDTO(complaint), HttpStatus.CREATED);
	}
	
	@PostMapping("/response")
	@PreAuthorize("hasRole('SYSTEM_ADMIN')")
	public ResponseEntity<ComplaintResponseDTO> respond(@RequestBody ComplaintResponseDTO complaintResponseDTO, Principal loggedUser) {
		User user = this.userService.findByUsername(loggedUser.getName());
		complaintResponseDTO.setSystemAdminId(user.getId());
		ComplaintResponse complaintResponse = this.complaintService.respond(complaintResponseDTO);
		if(complaintResponse == null) {
			return  new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(ComplaintResponseMapper.toDTO(complaintResponse), HttpStatus.CREATED);
	}
	
	@GetMapping("/withoutResponse")
	@PreAuthorize("hasRole('SYSTEM_ADMIN')")
	public ResponseEntity<List<ComplaintDTO>> getAllComplaintWithoutResponse() {
		List<Complaint> complaints = this.complaintService.getAllWithoutResponse();
		List<ComplaintDTO> dtos = new ArrayList<>();
		for (Complaint complaint : complaints) {
			dtos.add(ComplaintMapper.toDTO(complaint));
		}
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("/forUser")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<ComplaintDTO>> getAllComplaintForUserId(Principal loggedUser) {
		User user = this.userService.findByUsername(loggedUser.getName());
		List<Complaint> complaints = this.complaintService.getAllForUserId(user.getId());
		List<ComplaintDTO> dtos = new ArrayList<>();
		for (Complaint complaint : complaints) {
			dtos.add(ComplaintMapper.toDTO(complaint));
		}
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
}

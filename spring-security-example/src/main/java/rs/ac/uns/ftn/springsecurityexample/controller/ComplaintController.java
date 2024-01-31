package rs.ac.uns.ftn.springsecurityexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.springsecurityexample.dto.ComplaintDTO;
import rs.ac.uns.ftn.springsecurityexample.dto.ComplaintResponseDTO;
import rs.ac.uns.ftn.springsecurityexample.mapper.ComplaintMapper;
import rs.ac.uns.ftn.springsecurityexample.mapper.ComplaintResponseMapper;
import rs.ac.uns.ftn.springsecurityexample.model.Complaint;
import rs.ac.uns.ftn.springsecurityexample.model.ComplaintResponse;
import rs.ac.uns.ftn.springsecurityexample.service.ComplaintService;

@RestController
@RequestMapping(value = "api/complaint", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class ComplaintController {
	
	@Autowired
	private ComplaintService complaintService;
	
	@PostMapping("")
	public ResponseEntity<ComplaintDTO> create(@RequestBody ComplaintDTO complaintDTO) {
		Complaint complaint = this.complaintService.create(complaintDTO);
		if(complaint == null) {
			return  new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(ComplaintMapper.toDTO(complaint), HttpStatus.CREATED);
	}
	
	@PostMapping("/response")
	public ResponseEntity<ComplaintResponseDTO> respond(@RequestBody ComplaintResponseDTO complaintResponseDTO) {
		ComplaintResponse complaintResponse = this.complaintService.respond(complaintResponseDTO);
		if(complaintResponse == null) {
			return  new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(ComplaintResponseMapper.toDTO(complaintResponse), HttpStatus.CREATED);
	}
}

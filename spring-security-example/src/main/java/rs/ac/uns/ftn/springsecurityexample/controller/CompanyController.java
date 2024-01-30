package rs.ac.uns.ftn.springsecurityexample.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.springsecurityexample.dto.CompanyDTO;
import rs.ac.uns.ftn.springsecurityexample.mapper.CompanyMapper;
import rs.ac.uns.ftn.springsecurityexample.model.Company;
import rs.ac.uns.ftn.springsecurityexample.model.User;
import rs.ac.uns.ftn.springsecurityexample.service.CompanyService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping(value = "api/company", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public CompanyDTO getById(@PathVariable Long id) {
		return CompanyMapper.toDTO(this.companyService.getById(id));
	}

	@GetMapping("/search/{name}")
	public ResponseEntity<List<CompanyDTO>> searchByName(@PathVariable String name) {
		List<Company> companies = this.companyService.searchByName(name);
		List<CompanyDTO> dtos = new ArrayList<>();
		for (Company company : companies) {
			dtos.add(CompanyMapper.toDTO(company));
		}
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}

	@GetMapping("")
	public ResponseEntity<List<CompanyDTO>> getAll() {
		List<Company> companies = this.companyService.getAll();
		List<CompanyDTO> dtos = new ArrayList<>();
		for (Company company : companies) {
			dtos.add(CompanyMapper.toDTO(company));
		}
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}

}
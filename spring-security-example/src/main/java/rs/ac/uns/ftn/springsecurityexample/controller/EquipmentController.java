package rs.ac.uns.ftn.springsecurityexample.controller;

import java.util.ArrayList;
import java.util.List;

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
import rs.ac.uns.ftn.springsecurityexample.dto.EquipmentDTO;
import rs.ac.uns.ftn.springsecurityexample.mapper.CompanyMapper;
import rs.ac.uns.ftn.springsecurityexample.mapper.EquipmentMapper;
import rs.ac.uns.ftn.springsecurityexample.model.Company;
import rs.ac.uns.ftn.springsecurityexample.model.Equipment;
import rs.ac.uns.ftn.springsecurityexample.service.CompanyService;
import rs.ac.uns.ftn.springsecurityexample.service.EquipmentService;

@RestController
@RequestMapping(value = "api/equipment", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin

public class EquipmentController {
	@Autowired
	private EquipmentService equipmentService;
	
	@GetMapping("/search/{name}/{companyId}")
	public ResponseEntity<List<EquipmentDTO>> searchByName(@PathVariable String name, @PathVariable Long companyId) {
		 List<Equipment> equipment = this.equipmentService.searchByNameAndCompanyId(name, companyId);
		 List<EquipmentDTO> dtos = new ArrayList<>();
		 for (Equipment e : equipment) {
			 dtos.add(EquipmentMapper.toDTO(e));
		}
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
}

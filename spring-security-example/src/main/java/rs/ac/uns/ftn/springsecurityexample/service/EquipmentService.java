package rs.ac.uns.ftn.springsecurityexample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.springsecurityexample.model.Company;
import rs.ac.uns.ftn.springsecurityexample.model.Equipment;
import rs.ac.uns.ftn.springsecurityexample.repository.EquipmentRepository;

@Service
public class EquipmentService {
	
	@Autowired
	protected EquipmentRepository repository;
	
	public List<Equipment> searchByName(String name) {
	    return repository.findByNameContainingIgnoreCase(name);
	}
}

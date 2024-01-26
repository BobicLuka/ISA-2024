package rs.ac.uns.ftn.springsecurityexample.service;

import java.awt.print.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.springsecurityexample.model.Company;
import rs.ac.uns.ftn.springsecurityexample.repository.CompanyRepository;
import java.util.List;

@Service
public class CompanyService {
	@Autowired
	protected CompanyRepository repository;
	
	
	public Company getById(long id) {
		Company company = repository.findById(id).orElse(null);
	    return company;
	}
	
	public List<Company> searchByName(String name) {
	    return repository.findByNameContainingIgnoreCase(name);
	}
	

}

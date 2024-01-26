package rs.ac.uns.ftn.springsecurityexample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.springsecurityexample.model.Company;
import rs.ac.uns.ftn.springsecurityexample.model.User;

public interface CompanyRepository extends JpaRepository<Company, Long>{
	
    List<Company> findByNameContainingIgnoreCase(String name);

}

package rs.ac.uns.ftn.springsecurityexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.springsecurityexample.model.ComplaintResponse;

public interface ComplaintResponseRepository extends JpaRepository<ComplaintResponse, Long> {
	
}

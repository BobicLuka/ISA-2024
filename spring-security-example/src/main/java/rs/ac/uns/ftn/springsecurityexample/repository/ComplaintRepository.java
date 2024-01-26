package rs.ac.uns.ftn.springsecurityexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.springsecurityexample.model.Complaint;

public interface ComplaintRepository  extends JpaRepository<Complaint, Long>{

}

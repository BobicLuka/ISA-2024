package rs.ac.uns.ftn.springsecurityexample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rs.ac.uns.ftn.springsecurityexample.model.Complaint;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
	 @Query("SELECT c FROM Complaint c WHERE c.id NOT IN (SELECT cr.complaint.id FROM ComplaintResponse cr)")
	    List<Complaint> findAllWithoutResponse();

	List<Complaint> findByComplainantId(Long userId);
}

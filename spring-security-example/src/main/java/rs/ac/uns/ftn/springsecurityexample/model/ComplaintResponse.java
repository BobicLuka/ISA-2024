package rs.ac.uns.ftn.springsecurityexample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="COMPLAINT_RESPONSE")
public class ComplaintResponse {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "response", nullable = false)
	private String response;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "system_admin_id", nullable = false)
	private User systemAdmin;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "complaint_id", nullable = false)
	private Complaint complaint;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public User getSystemAdmin() {
		return systemAdmin;
	}

	public void setSystemAdmin(User systemAdmin) {
		this.systemAdmin = systemAdmin;
	}

	public Complaint getComplaint() {
		return complaint;
	}

	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}

}

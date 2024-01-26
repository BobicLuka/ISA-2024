package rs.ac.uns.ftn.springsecurityexample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="COMPLAINT")
public class Complaint {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "text", nullable = false)
	private String text;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "complainant_id", nullable = false)
	private User complainant; // podnosilac zalbe

	// moze se zaliti na admina ili kompaniju
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "admin_id", nullable = true)
	private User admin;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "company_id", nullable = true)
	private Company company;
}

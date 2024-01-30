package rs.ac.uns.ftn.springsecurityexample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.springsecurityexample.dto.ComplaintDTO;
import rs.ac.uns.ftn.springsecurityexample.model.Complaint;
import rs.ac.uns.ftn.springsecurityexample.model.Company;

import rs.ac.uns.ftn.springsecurityexample.model.User;
import rs.ac.uns.ftn.springsecurityexample.repository.ComplaintRepository;

@Service
public class ComplaintService {
	@Autowired
	private ComplaintRepository repository;

	@Autowired
	private UserService userService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private RoleService roleService;

	public Complaint create(ComplaintDTO complaintDTO) {
		
		User admin = userService.findById(complaintDTO.getAdminId()); // moze biti null vidi jel problem
		Company company = companyService.getById(complaintDTO.getCompanyId());

		if (admin == null && company == null) {
			return null;
		}
		
		User complainant = userService.findById(complaintDTO.getComplainantId());
		Long roleAdminId = roleService.findByName("ROLE_ADMIN").get(0).getId();
		
		if (admin != null) { // zalbe za admina uslovi
			if (!admin.getRoles().stream().anyMatch(role -> role.getId().equals(roleAdminId))) {
				return null; // ako se ne zali na admina nego drugu ulogu
			}
			if (!appointmentService.hadAppointmentWithAdmin(complainant.getId(), admin.getId())) { // morao je imati termin kod admina da bi se zalio
				return null;
			}
		}else {	//zalbe za company
			if (!appointmentService.hadAppointmentWithCompany(complainant.getId(), company.getId())) { // morao je imati termin kod admina da bi se zalio
				return null;
			}
		}
		
		Complaint complaint = new Complaint();
		complaint.setText(complaintDTO.getText());
		complaint.setComplainant(complainant);
		complaint.setAdmin(admin);
		complaint.setCompany(company);

		return this.repository.save(complaint);
	}

	

}

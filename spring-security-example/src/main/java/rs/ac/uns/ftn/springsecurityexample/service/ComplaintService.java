package rs.ac.uns.ftn.springsecurityexample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.springsecurityexample.dto.ComplaintDTO;
import rs.ac.uns.ftn.springsecurityexample.dto.ComplaintResponseDTO;
import rs.ac.uns.ftn.springsecurityexample.model.Complaint;
import rs.ac.uns.ftn.springsecurityexample.model.ComplaintResponse;
import rs.ac.uns.ftn.springsecurityexample.model.Company;

import rs.ac.uns.ftn.springsecurityexample.model.User;
import rs.ac.uns.ftn.springsecurityexample.repository.ComplaintRepository;
import rs.ac.uns.ftn.springsecurityexample.repository.ComplaintResponseRepository;

@Service
public class ComplaintService {
	@Autowired
	private ComplaintRepository repository;

	@Autowired
	private ComplaintResponseRepository complaintResponseRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private EmailService emailService;

	public Complaint findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	public Complaint create(ComplaintDTO complaintDTO) {

		User admin = userService.findById(complaintDTO.getAdminId());
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
			if (!appointmentService.hadAppointmentWithAdmin(complainant.getId(), admin.getId())) {
				return null;// morao je imati termin kod admina da bi se zalio
			}
		} else { // zalbe za company uslov: morao je imati termin u kompaniji
			if (!appointmentService.hadAppointmentWithCompany(complainant.getId(), company.getId())) {
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

	public ComplaintResponse respond(ComplaintResponseDTO complaintResponseDTO) {
		Complaint complaint = findById(complaintResponseDTO.getComplaintId());
		if (complaint == null) {
			return null;
		}
		User systemAdmin = userService.findById(complaintResponseDTO.getSystemAdminId());
		if (systemAdmin == null) {
			return null;
		}
		ComplaintResponse complaintResponse = new ComplaintResponse();
		complaintResponse.setResponse(complaintResponseDTO.getResponse());
		complaintResponse.setComplaint(complaint);
		complaintResponse.setSystemAdmin(systemAdmin);
		emailService.sendComplaintResponse(complaintResponse);
		return this.complaintResponseRepository.save(complaintResponse);
	}

	public List<Complaint> getAllWithoutResponse() {
		return repository.findAllWithoutResponse();
	}

	public List<Complaint> getAllForUserId(Long userId) {
		return  repository.findByComplainantId(userId);
	}

}

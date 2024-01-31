package rs.ac.uns.ftn.springsecurityexample.dto;


public class ComplaintDTO {
	private Long id;
	private String text;
	private Long complainantId; 
	private String complainantName;
	private Long adminId;
	private String adminName;
	private Long companyId;
	private String companyName;
	
	private Long complaintResponseId;
	private String response;
	private Long adminWhoRespondedId;
	private String adminWhoRespondedName;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Long getComplainantId() {
		return complainantId;
	}
	public void setComplainantId(Long complainantId) {
		this.complainantId = complainantId;
	}
	public String getComplainantName() {
		return complainantName;
	}
	public void setComplainantName(String complainantName) {
		this.complainantName = complainantName;
	}
	public Long getAdminId() {
		return adminId;
	}
	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Long getComplaintResponseId() {
		return complaintResponseId;
	}
	public void setComplaintResponseId(Long complaintResponseId) {
		this.complaintResponseId = complaintResponseId;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public Long getAdminWhoRespondedId() {
		return adminWhoRespondedId;
	}
	public void setAdminWhoRespondedId(Long adminWhoRespondedId) {
		this.adminWhoRespondedId = adminWhoRespondedId;
	}
	public String getAdminWhoRespondedName() {
		return adminWhoRespondedName;
	}
	public void setAdminWhoRespondedName(String adminWhoRespondedName) {
		this.adminWhoRespondedName = adminWhoRespondedName;
	}

}

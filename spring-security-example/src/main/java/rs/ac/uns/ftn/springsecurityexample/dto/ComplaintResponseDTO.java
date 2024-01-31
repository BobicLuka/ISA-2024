package rs.ac.uns.ftn.springsecurityexample.dto;


public class ComplaintResponseDTO {
	private Long id;
	private String response;
	private Long systemAdminId;
	private Long complaintId;
	
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
	public Long getSystemAdminId() {
		return systemAdminId;
	}
	public void setSystemAdminId(Long systemAdminId) {
		this.systemAdminId = systemAdminId;
	}
	public Long getComplaintId() {
		return complaintId;
	}
	public void setComplaintId(Long complaintId) {
		this.complaintId = complaintId;
	}
	
	
}

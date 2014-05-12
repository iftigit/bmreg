package org.table;

public class TtcDTO {
	
	private int ttcId;
	private String ttcName;
	private String ttcPrincipal;
	private String address;
	private String phone;
	private String emailAddress;
	
	private String interviewDate;
	
	public int getTtcId() {
		return ttcId;
	}
	public void setTtcId(int ttcId) {
		this.ttcId = ttcId;
	}
	public String getTtcName() {
		return ttcName;
	}
	public void setTtcName(String ttcName) {
		this.ttcName = ttcName;
	}
	public String getInterviewDate() {
		return interviewDate;
	}
	public void setInterviewDate(String interviewDate) {
		this.interviewDate = interviewDate;
	}
	public String getTtcPrincipal() {
		return ttcPrincipal;
	}
	public void setTtcPrincipal(String ttcPrincipal) {
		this.ttcPrincipal = ttcPrincipal;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	

}

package org.table;

public class DemoDTO {

	private int demoId;
	private String demoName;
	private int demoDistrictId;
	private String demoDistrictName;
	
	
	/* Report 1 Parameter [Start]*/
	private String paymentDate;
	private int totalRegistration;
	private int totalAmount;
	
	private String userFullName;
	private String userDesignation;
	
	private String payorderNo;
	private String payorderBank;
	/* Report 1 Parameter [End]*/
	
	public int getDemoId() {
		return demoId;
	}
	public void setDemoId(int demoId) {
		this.demoId = demoId;
	}
	public String getDemoName() {
		return demoName;
	}
	public void setDemoName(String demoName) {
		this.demoName = demoName;
	}
	public int getDemoDistrictId() {
		return demoDistrictId;
	}
	public void setDemoDistrictId(int demoDistrictId) {
		this.demoDistrictId = demoDistrictId;
	}
	public String getDemoDistrictName() {
		return demoDistrictName;
	}
	public void setDemoDistrictName(String demoDistrictName) {
		this.demoDistrictName = demoDistrictName;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public int getTotalRegistration() {
		return totalRegistration;
	}
	public void setTotalRegistration(int totalRegistration) {
		this.totalRegistration = totalRegistration;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getUserFullName() {
		return userFullName;
	}
	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}
	public String getUserDesignation() {
		return userDesignation;
	}
	public void setUserDesignation(String userDesignation) {
		this.userDesignation = userDesignation;
	}
	public String getPayorderNo() {
		return payorderNo;
	}
	public void setPayorderNo(String payorderNo) {
		this.payorderNo = payorderNo;
	}
	public String getPayorderBank() {
		return payorderBank;
	}
	public void setPayorderBank(String payorderBank) {
		this.payorderBank = payorderBank;
	}
	
}

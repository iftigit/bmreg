package org.table;

public class SelectPersonDTO {

	private String empFullName;
	private String regId;
	private String fatherName;
	private String motherName;
	private String mobileNo;
	private String iDate;
	private String ttcNmae;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	
	
	public String getEmpFullName() {
		return empFullName;
	}
	public void setEmpFullName(String empFullName) {
		this.empFullName = empFullName;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getIDate() {
		return iDate;
	}
	public void setIDate(String date) {
		iDate = date;
	}
	public String getTtcNmae() {
		return ttcNmae;
	}
	public void setTtcNmae(String ttcNmae) {
		this.ttcNmae = ttcNmae;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		if(addressLine1==null)
			this.addressLine1 = " ";
		else		
			this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		if(addressLine2==null)
			this.addressLine2 = " ";
		else		
			this.addressLine2 = addressLine2;
	}
	public String getAddressLine3() {
		return addressLine3;
	}
	public void setAddressLine3(String addressLine3) {
		if(addressLine3==null)
			this.addressLine3 = " ";
		else		
			this.addressLine3 = addressLine3;
	}
	
	
	
	
	
}

package org.table;

import java.io.Serializable;

public class TechnicalTeamDTO implements Serializable {

	private static final long serialVersionUID = 3412731324163767760L;
	private String contactName;
	private String mobileNo;
	
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	

}

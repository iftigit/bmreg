package org.table;

import java.io.Serializable;

public class UserTmpDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String userId;
	private String divisionId;
	private String divisionName;
	private String districtId;
	private String districtName;
	private String upazilaId;
	private String upazilaName;
	private String unionId;
	private String unionName;
	private String requestedBy;
	private String requestedOn;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getUpazilaId() {
		return upazilaId;
	}
	public void setUpazilaId(String upazilaId) {
		this.upazilaId = upazilaId;
	}
	public String getUpazilaName() {
		return upazilaName;
	}
	public void setUpazilaName(String upazilaName) {
		this.upazilaName = upazilaName;
	}
	public String getUnionId() {
		return unionId;
	}
	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}
	public String getUnionName() {
		return unionName;
	}
	public void setUnionName(String unionName) {
		this.unionName = unionName;
	}
	public String getRequestedBy() {
		return requestedBy;
	}
	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}
	public String getRequestedOn() {
		return requestedOn;
	}
	public void setRequestedOn(String requestedOn) {
		this.requestedOn = requestedOn;
	}
	
	
	
	
}

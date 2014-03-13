package org.table;

public class RegTokenDTO {

	private int tokenId;
	private String createdBy;
	private String assignedTo;
	private String payOrderNumber;
	private String payOrderDate;
	private String payOrderBank;
	private String payOrderBranch;
	private float totalAmount;
	private int regFee;
	private int totalToken;
	private String recruitingAgencyName;
	private String recruitingAgencyId;
	private String insertedOn;
	private String regToken;
	
	private String[] tokenList;

	public int getTokenId() {
		return tokenId;
	}

	public void setTokenId(int tokenId) {
		this.tokenId = tokenId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public String getPayOrderNumber() {
		return payOrderNumber;
	}

	public void setPayOrderNumber(String payOrderNumber) {
		this.payOrderNumber = payOrderNumber;
	}

	public String getPayOrderDate() {
		return payOrderDate;
	}

	public void setPayOrderDate(String payOrderDate) {
		this.payOrderDate = payOrderDate;
	}

	public String getPayOrderBank() {
		return payOrderBank;
	}

	public void setPayOrderBank(String payOrderBank) {
		this.payOrderBank = payOrderBank;
	}

	public String getPayOrderBranch() {
		return payOrderBranch;
	}

	public void setPayOrderBranch(String payOrderBranch) {
		this.payOrderBranch = payOrderBranch;
	}

	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getRecruitingAgencyName() {
		return recruitingAgencyName;
	}

	public void setRecruitingAgencyName(String recruitingAgencyName) {
		this.recruitingAgencyName = recruitingAgencyName;
	}

	public String getRecruitingAgencyId() {
		return recruitingAgencyId;
	}

	public void setRecruitingAgencyId(String recruitingAgencyId) {
		this.recruitingAgencyId = recruitingAgencyId;
	}

	public String getInsertedOn() {
		return insertedOn;
	}

	public void setInsertedOn(String insertedOn) {
		this.insertedOn = insertedOn;
	}

	public String[] getTokenList() {
		return tokenList;
	}

	public void setTokenList(String[] tokenList) {
		this.tokenList = tokenList;
	}

	public int getRegFee() {
		return regFee;
	}

	public void setRegFee(int regFee) {
		this.regFee = regFee;
	}

	public int getTotalToken() {
		return totalToken;
	}

	public void setTotalToken(int totalToken) {
		this.totalToken = totalToken;
	}

	public String getRegToken() {
		return regToken;
	}

	public void setRegToken(String regToken) {
		this.regToken = regToken;
	}
	

	
}

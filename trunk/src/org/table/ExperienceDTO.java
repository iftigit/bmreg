package org.table;

public class ExperienceDTO {
	
	private int expType; //1=Local; 2=Abroad;
	private int countryId;
	private String countryName;
	
	private int jobCategoryId;
	private String jobCategoryName;
	
	private int jobSubCategoryId;
	private String jobSubCategoryName;
	
	private int jobSubSubCategoryId;
	private String jobSubSubCategoryName;
	
	private int totalYears;

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public int getJobCategoryId() {
		return jobCategoryId;
	}

	public void setJobCategoryId(int jobCategoryId) {
		this.jobCategoryId = jobCategoryId;
	}

	public String getJobCategoryName() {
		return jobCategoryName;
	}

	public void setJobCategoryName(String jobCategoryName) {
		this.jobCategoryName = jobCategoryName;
	}

	public int getJobSubCategoryId() {
		return jobSubCategoryId;
	}

	public void setJobSubCategoryId(int jobSubCategoryId) {
		this.jobSubCategoryId = jobSubCategoryId;
	}

	public String getJobSubCategoryName() {
		return jobSubCategoryName;
	}

	public void setJobSubCategoryName(String jobSubCategoryName) {
		this.jobSubCategoryName = jobSubCategoryName;
	}

	public int getJobSubSubCategoryId() {
		return jobSubSubCategoryId;
	}

	public void setJobSubSubCategoryId(int jobSubSubCategoryId) {
		this.jobSubSubCategoryId = jobSubSubCategoryId;
	}

	public String getJobSubSubCategoryName() {
		return jobSubSubCategoryName;
	}

	public void setJobSubSubCategoryName(String jobSubSubCategoryName) {
		this.jobSubSubCategoryName = jobSubSubCategoryName;
	}

	
	public int getTotalYears() {
		return totalYears;
	}

	public void setTotalYears(int totalYears) {
		this.totalYears = totalYears;
	}

	public int getExpType() {
		return expType;
	}

	public void setExpType(int expType) {
		this.expType = expType;
	}

	
	

}

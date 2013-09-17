package org.table;

public class JobPreferenceDTO {


	private int categoryId;
	private String categoryName;
	
	private int subCategoryId;
	private String subCategoryName;
	
	private int subSubCategoryId;
	private String subSubCategoryName;
	
	private int jobId;
	private String jobTitle;
	
	
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getSubCategoryId() {
		return subCategoryId;
	}
	public void setSubCategoryId(int subCategoryId) {
		this.subCategoryId = subCategoryId;
	}
	public String getSubCategoryName() {
		return subCategoryName;
	}
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
	public int getSubSubCategoryId() {
		return subSubCategoryId;
	}
	public void setSubSubCategoryId(int subSubCategoryId) {
		this.subSubCategoryId = subSubCategoryId;
	}
	public String getSubSubCategoryName() {
		return subSubCategoryName;
	}
	public void setSubSubCategoryName(String subSubCategoryName) {
		this.subSubCategoryName = subSubCategoryName;
	}
		
	
	
}

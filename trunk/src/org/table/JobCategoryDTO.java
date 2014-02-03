package org.table;

public class JobCategoryDTO extends JobPreferenceDTO {
	
	private int mainJobVisibility;
	private int subJobVisibility;
	private int subSubJobVisibility;
	
	public int getMainJobVisibility() {
		return mainJobVisibility;
	}
	public void setMainJobVisibility(int mainJobVisibility) {
		this.mainJobVisibility = mainJobVisibility;
	}
	public int getSubJobVisibility() {
		return subJobVisibility;
	}
	public void setSubJobVisibility(int subJobVisibility) {
		this.subJobVisibility = subJobVisibility;
	}
	public int getSubSubJobVisibility() {
		return subSubJobVisibility;
	}
	public void setSubSubJobVisibility(int subSubJobVisibility) {
		this.subSubJobVisibility = subSubJobVisibility;
	}
	
	

}

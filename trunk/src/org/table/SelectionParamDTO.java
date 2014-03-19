package org.table;

import java.util.ArrayList;

public class SelectionParamDTO {
	
	private int selectionId;
	private String agentId;
	private String agentCompanyName;
	private String workOrder;
	private String countryPreference;
	private String gender;
	private String languages;
	private String jobPreference;
	private String jobExperience;
	private String yearOfExperience;
	private String selectionDate;
	private String expireDate;
	private String status;
	private String workOrderTotal;
	private String suggestedTotal;
	private String jobPreferenceDesc;
	private String jobExperienceDesc;
	
	private ArrayList<JobPreferenceDTO> jobPrefererenceList;
	private ExperienceDTO jobExperienceList;
	
	
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public String getWorkOrder() {
		return workOrder;
	}
	public void setWorkOrder(String workOrder) {
		this.workOrder = workOrder;
	}
	public String getCountryPreference() {
		return countryPreference;
	}
	public void setCountryPreference(String countryPreference) {
		this.countryPreference = countryPreference;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getLanguages() {
		return languages;
	}
	public void setLanguages(String languages) {
		this.languages = languages;
	}
	public String getJobPreference() {
		return jobPreference;
	}
	public void setJobPreference(String jobPreference) {
		this.jobPreference = jobPreference;
	}
	public String getJobExperience() {
		return jobExperience;
	}
	public void setJobExperience(String jobExperience) {
		this.jobExperience = jobExperience;
	}
	
	public ArrayList<JobPreferenceDTO> getJobPrefererenceList() {
		return jobPrefererenceList;
	}
	public void setJobPrefererenceList(
			ArrayList<JobPreferenceDTO> jobPrefererenceList) {
		this.jobPrefererenceList = jobPrefererenceList;
	}
	public ExperienceDTO getJobExperienceList() {
		return jobExperienceList;
	}
	public void setJobExperienceList(ExperienceDTO jobExperienceList) {
		this.jobExperienceList = jobExperienceList;
	}	
	public int getSelectionId() {
		return selectionId;
	}
	public void setSelectionId(int selectionId) {
		this.selectionId = selectionId;
	}
	public String getSelectionDate() {
		return selectionDate;
	}
	public void setSelectionDate(String selectionDate) {
		this.selectionDate = selectionDate;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAgentCompanyName() {
		return agentCompanyName;
	}
	public void setAgentCompanyName(String agentCompanyName) {
		this.agentCompanyName = agentCompanyName;
	}
	public String getYearOfExperience() {
		return yearOfExperience;
	}
	public void setYearOfExperience(String yearOfExperience) {
		this.yearOfExperience = yearOfExperience;
	}
	public String getWorkOrderTotal() {
		return workOrderTotal;
	}
	public void setWorkOrderTotal(String workOrderTotal) {
		this.workOrderTotal = workOrderTotal;
	}
	public String getSuggestedTotal() {
		return suggestedTotal;
	}
	public void setSuggestedTotal(String suggestedTotal) {
		this.suggestedTotal = suggestedTotal;
	}
	public String getJobPreferenceDesc() {
		return jobPreferenceDesc;
	}
	public void setJobPreferenceDesc(String jobPreferenceDesc) {
		this.jobPreferenceDesc = jobPreferenceDesc;
	}
	public String getJobExperienceDesc() {
		return jobExperienceDesc;
	}
	public void setJobExperienceDesc(String jobExperienceDesc) {
		this.jobExperienceDesc = jobExperienceDesc;
	}
	
}

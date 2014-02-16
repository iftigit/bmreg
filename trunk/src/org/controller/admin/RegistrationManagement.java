package org.controller.admin;

import java.util.ArrayList;
import org.model.RegistrationDAO;
import org.table.PersonalInfoDTO;

import com.opensymphony.xwork2.ActionSupport;

public class RegistrationManagement extends ActionSupport{

	private static final long serialVersionUID = 1L;
	ArrayList<PersonalInfoDTO> registrationList=new ArrayList<PersonalInfoDTO>();
	private PersonalInfoDTO personalInfo;
	private String jobseekerNumber;
	private String passportNo;
	private String nationalId;
	private String birthRegId;
	private String empFullName;
	private String empBirthDate;
	private String empMobileNumber;
	
	public String searchJobseeker()
	{
		RegistrationDAO regDAO=new RegistrationDAO();
		personalInfo=new PersonalInfoDTO();
		personalInfo.setJobseekerNumber(jobseekerNumber==null?"":jobseekerNumber);
		personalInfo.setPassportNo(passportNo==null?"":passportNo);
		personalInfo.setNationalId(nationalId==null?"":nationalId);
		personalInfo.setBirthRegId(birthRegId==null?"":birthRegId);
		personalInfo.setEmpFullName(empFullName==null?"":empFullName);
		personalInfo.setEmpBirthDate(empBirthDate==null?"":empBirthDate);
		personalInfo.setEmpMobileNumber(empMobileNumber==null?"":empMobileNumber);
		registrationList=regDAO.searchJobSeeker(personalInfo);
		return SUCCESS;
	}
	public String getRegInfo(){
	
		return SUCCESS;
	}


	public ArrayList<PersonalInfoDTO> getRegistrationList() {
		return registrationList;
	}
	public void setRegistrationList(ArrayList<PersonalInfoDTO> registrationList) {
		this.registrationList = registrationList;
	}
	public PersonalInfoDTO getPersonalInfo() {
		return personalInfo;
	}
	public void setPersonalInfo(PersonalInfoDTO personalInfo) {
		this.personalInfo = personalInfo;
	}


	public String getJobseekerNumber() {
		return jobseekerNumber;
	}


	public void setJobseekerNumber(String jobseekerNumber) {
		this.jobseekerNumber = jobseekerNumber;
	}


	public String getPassportNo() {
		return passportNo;
	}


	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}


	public String getNationalId() {
		return nationalId;
	}


	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}


	public String getBirthRegId() {
		return birthRegId;
	}


	public void setBirthRegId(String birthRegId) {
		this.birthRegId = birthRegId;
	}


	public String getEmpFullName() {
		return empFullName;
	}


	public void setEmpFullName(String empFullName) {
		this.empFullName = empFullName;
	}


	public String getEmpBirthDate() {
		return empBirthDate;
	}


	public void setEmpBirthDate(String empBirthDate) {
		this.empBirthDate = empBirthDate;
	}


	public String getEmpMobileNumber() {
		return empMobileNumber;
	}


	public void setEmpMobileNumber(String empMobileNumber) {
		this.empMobileNumber = empMobileNumber;
	}
	
	
	
}

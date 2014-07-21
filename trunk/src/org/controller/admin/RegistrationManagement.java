package org.controller.admin;

import java.util.ArrayList;

import org.apache.struts2.ServletActionContext;
import org.model.CountryDAO;
import org.model.LanguageDAO;
import org.model.RegistrationDAO;
import org.omg.CORBA.Request;
import org.table.AddressDTO;
import org.table.CountryDTO;
import org.table.EducationDTO;
import org.table.ExperienceDTO;
import org.table.JobPreferenceDTO;
import org.table.LanguageDTO;
import org.table.NomineeDTO;
import org.table.PersonalInfoDTO;
import org.table.TrainingDTO;

import com.opensymphony.xwork2.ActionSupport;

public class RegistrationManagement extends ActionSupport{

	private static final long serialVersionUID = 1L;
	ArrayList<PersonalInfoDTO> registrationList=new ArrayList<PersonalInfoDTO>();
	private PersonalInfoDTO personalInfo;
	private PersonalInfoDTO personalDTO;
	private NomineeDTO nomineeDTO;
	private EducationDTO educationDTO;
	private String localExperience;
	private String abroadExperience;
	private String countryPreferenceIds;
	private String countryPreferenceNames;
	private String jobPreference;
	private String languages;
	private String trainings;
	
	AddressDTO  pAddress;
	AddressDTO  mAddress;
	
	ArrayList<ExperienceDTO> localExperienceList=new ArrayList<ExperienceDTO>();	
	ArrayList<ExperienceDTO> abroadExperienceList=new ArrayList<ExperienceDTO>();
	ArrayList<ExperienceDTO> experienceList=new ArrayList<ExperienceDTO>();
	ArrayList<JobPreferenceDTO> jobPreferenceList=new ArrayList<JobPreferenceDTO>();
	ArrayList<LanguageDTO> allLanguageList=new ArrayList<LanguageDTO>();
	ArrayList<LanguageDTO> empLanguageList=new ArrayList<LanguageDTO>();
	ArrayList<TrainingDTO> trainingList=new ArrayList<TrainingDTO>();
	private ArrayList<CountryDTO> countryList=new ArrayList<CountryDTO>();
	
	
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
	
		RegistrationDAO regDAO=new RegistrationDAO();
		personalDTO=regDAO.getPersonalInformation(jobseekerNumber);
		nomineeDTO=regDAO.getNomineeInformation(jobseekerNumber);
		educationDTO=regDAO.getEducationInformation(jobseekerNumber);
		localExperienceList=regDAO.getExperienceList(jobseekerNumber, "1");
		abroadExperienceList=regDAO.getExperienceList(jobseekerNumber, "2");
		
		jobPreferenceList=regDAO.getJobPreferenceList(jobseekerNumber);
		
		empLanguageList=regDAO.getLanguageList(jobseekerNumber);
		trainingList=regDAO.getTrainingList(jobseekerNumber);
		
		countryList=CountryDAO.getAllCountry(0);
		ArrayList<CountryDTO> tmpCountryList=new ArrayList<CountryDTO>();
		String[] countryArr=personalDTO.getCountryPreferenceStr().split("\\|", -1);
		for(int i=0;i<countryList.size();i++)
		{
			CountryDTO cdto=new CountryDTO();
			cdto=countryList.get(i);
			for(int j=0;j<countryArr.length;j++)
			{
				if(cdto.getCountryId()==Integer.parseInt(countryArr[j].trim()))
				{
					cdto.setIsSelected("Y");
				}
			}
			tmpCountryList.add(cdto);
		}
		countryList=tmpCountryList;
		
		allLanguageList=LanguageDAO.getAllLanguage(0);
		
		
			
		PersonalInfoDTO addressInfo=regDAO.getAddressInfo(jobseekerNumber);
		pAddress=addressInfo.getPermanentAddress();
		mAddress=addressInfo.getMailingAddress();
//		personalDTO.setPermanentAddress(pAddress);
//		personalDTO.setMailingAddress(mAddress);
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
	public PersonalInfoDTO getPersonalDTO() {
		return personalDTO;
	}
	public void setPersonalDTO(PersonalInfoDTO personalDTO) {
		this.personalDTO = personalDTO;
	}
	public NomineeDTO getNomineeDTO() {
		return nomineeDTO;
	}
	public void setNomineeDTO(NomineeDTO nomineeDTO) {
		this.nomineeDTO = nomineeDTO;
	}
	public EducationDTO getEducationDTO() {
		return educationDTO;
	}
	public void setEducationDTO(EducationDTO educationDTO) {
		this.educationDTO = educationDTO;
	}
	public String getLocalExperience() {
		return localExperience;
	}
	public void setLocalExperience(String localExperience) {
		this.localExperience = localExperience;
	}
	public String getAbroadExperience() {
		return abroadExperience;
	}
	public void setAbroadExperience(String abroadExperience) {
		this.abroadExperience = abroadExperience;
	}
	public String getCountryPreferenceIds() {
		return countryPreferenceIds;
	}
	public void setCountryPreferenceIds(String countryPreferenceIds) {
		this.countryPreferenceIds = countryPreferenceIds;
	}
	public String getCountryPreferenceNames() {
		return countryPreferenceNames;
	}
	public void setCountryPreferenceNames(String countryPreferenceNames) {
		this.countryPreferenceNames = countryPreferenceNames;
	}
	public String getJobPreference() {
		return jobPreference;
	}
	public void setJobPreference(String jobPreference) {
		this.jobPreference = jobPreference;
	}
	public String getLanguages() {
		return languages;
	}
	public void setLanguages(String languages) {
		this.languages = languages;
	}
	public String getTrainings() {
		return trainings;
	}
	public void setTrainings(String trainings) {
		this.trainings = trainings;
	}
	public AddressDTO getpAddress() {
		return pAddress;
	}
	public void setpAddress(AddressDTO pAddress) {
		this.pAddress = pAddress;
	}
	public AddressDTO getmAddress() {
		return mAddress;
	}
	public void setmAddress(AddressDTO mAddress) {
		this.mAddress = mAddress;
	}
	public ArrayList<ExperienceDTO> getLocalExperienceList() {
		return localExperienceList;
	}
	public void setLocalExperienceList(ArrayList<ExperienceDTO> localExperienceList) {
		this.localExperienceList = localExperienceList;
	}
	public ArrayList<ExperienceDTO> getAbroadExperienceList() {
		return abroadExperienceList;
	}
	public void setAbroadExperienceList(
			ArrayList<ExperienceDTO> abroadExperienceList) {
		this.abroadExperienceList = abroadExperienceList;
	}
	public ArrayList<ExperienceDTO> getExperienceList() {
		return experienceList;
	}
	public void setExperienceList(ArrayList<ExperienceDTO> experienceList) {
		this.experienceList = experienceList;
	}
	public ArrayList<JobPreferenceDTO> getJobPreferenceList() {
		return jobPreferenceList;
	}
	public void setJobPreferenceList(ArrayList<JobPreferenceDTO> jobPreferenceList) {
		this.jobPreferenceList = jobPreferenceList;
	}	
	public ArrayList<TrainingDTO> getTrainingList() {
		return trainingList;
	}
	public void setTrainingList(ArrayList<TrainingDTO> trainingList) {
		this.trainingList = trainingList;
	}
	public ArrayList<CountryDTO> getCountryList() {
		return countryList;
	}
	public void setCountryList(ArrayList<CountryDTO> countryList) {
		this.countryList = countryList;
	}
	public ArrayList<LanguageDTO> getAllLanguageList() {
		return allLanguageList;
	}
	public void setAllLanguageList(ArrayList<LanguageDTO> allLanguageList) {
		this.allLanguageList = allLanguageList;
	}
	public ArrayList<LanguageDTO> getEmpLanguageList() {
		return empLanguageList;
	}
	public void setEmpLanguageList(ArrayList<LanguageDTO> empLanguageList) {
		this.empLanguageList = empLanguageList;
	}
	
	
	
}

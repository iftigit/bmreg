package org.table;

import java.util.ArrayList;

public class PersonalInfoDTO {

	private String jobseekerNumber;
	private String empGivenName;
	private String empLastName;
	private String empFullName;	
	private String empFatherName;
	private String empMotherName;
	private String empBirthDate;
	private String empAge;
	private String empBirthDistrict;
	private String empBirthUpazilaOrThana;
	private String empBirthDistrictName;
	private String empBirthUpazilaOrThanaName;
	private String empGender;
	private String empReligion;
	private String empMaritalStatus;
	private String empChildYN;
	private String empSonCount;
	private String empDaughterCount;
	private String empSpouseName;	
	private String empMobileNumber;
	private String empHeightFeet;
	private String empHeightInches;
	private String empHeightCM;
	private String empWeight;
	private String empBloodGroup;
	private String empDisabilityYN;
	private String empDisabilityDetail;
	
	
	private String nationalId;
	private String birthRegId;
	private String passportNo;
	private String oldPassportNo;
	private String passportIssueDate;
	private String passportExpDate;
	
	private ArrayList<CountryDTO> countryPreference;
	private String countryPreferenceStr;
	private String countryPreferenceString;
	
	private String educationalQualificatoin;
	private String instituteName;
	private String passingYear;
	
	private AddressDTO permanentAddress ;
	private AddressDTO mailingAddress ;
	

	private JobPreferenceDTO jobPreferenceDTO;
	private ExperienceDTO    localExperience;
	private ExperienceDTO    abroadExperience;
	
	private String printDateTime;
	private String regDateTime;
	
	public String getJobseekerNumber() {
		return jobseekerNumber;
	}
	public void setJobseekerNumber(String jobseekerNumber) {
		this.jobseekerNumber = jobseekerNumber;
	}
	public String getEmpGivenName() {
		return empGivenName;
	}
	public void setEmpGivenName(String empGivenName) {
		this.empGivenName = empGivenName;
	}
	public String getEmpLastName() {
		return empLastName;
	}
	public void setEmpLastName(String empLastName) {
		this.empLastName = empLastName;
	}
	public String getEmpFullName() {
		return empFullName;
	}
	public void setEmpFullName(String empFullName) {
		this.empFullName = empFullName;
	}
	public String getEmpFatherName() {
		return empFatherName;
	}
	public void setEmpFatherName(String empFatherName) {
		this.empFatherName = empFatherName;
	}
	public String getEmpMotherName() {
		return empMotherName;
	}
	public void setEmpMotherName(String empMotherName) {
		this.empMotherName = empMotherName;
	}
	public String getEmpBirthDate() {
		return empBirthDate;
	}
	public void setEmpBirthDate(String empBirthDate) {
		this.empBirthDate = empBirthDate;
	}
	public String getEmpBirthDistrict() {
		return empBirthDistrict;
	}
	public void setEmpBirthDistrict(String empBirthDistrict) {
		this.empBirthDistrict = empBirthDistrict;
	}
	
	public String getEmpBirthUpazilaOrThana() {
		return empBirthUpazilaOrThana;
	}
	public void setEmpBirthUpazilaOrThana(String empBirthUpazilaOrThana) {
		this.empBirthUpazilaOrThana = empBirthUpazilaOrThana;
	}
	public String getEmpGender() {
		return empGender;
	}
	public void setEmpGender(String empGender) {
		this.empGender = empGender;
	}
	public String getEmpReligion() {
		return empReligion;
	}
	public void setEmpReligion(String empReligion) {
		this.empReligion = empReligion;
	}
	public String getEmpMaritalStatus() {
		return empMaritalStatus;
	}
	public void setEmpMaritalStatus(String empMaritalStatus) {
		this.empMaritalStatus = empMaritalStatus;
	}
	public String getEmpChildYN() {
		return empChildYN;
	}
	public void setEmpChildYN(String empChildYN) {
		this.empChildYN = empChildYN;
	}
	public String getEmpSonCount() {
		return empSonCount;
	}
	public void setEmpSonCount(String empSonCount) {
		this.empSonCount = empSonCount;
	}
	public String getEmpDaughterCount() {
		return empDaughterCount;
	}
	public void setEmpDaughterCount(String empDaughterCount) {
		this.empDaughterCount = empDaughterCount;
	}
	public String getEmpSpouseName() {
		return empSpouseName;
	}
	public void setEmpSpouseName(String empSpouseName) {
		this.empSpouseName = empSpouseName;
	}
	public String getEmpMobileNumber() {
		return empMobileNumber;
	}
	public void setEmpMobileNumber(String empMobileNumber) {
		this.empMobileNumber = empMobileNumber;
	}
	public String getEmpHeightFeet() {
		return empHeightFeet;
	}
	public void setEmpHeightFeet(String empHeightFeet) {
		this.empHeightFeet = empHeightFeet;
	}
	public String getEmpHeightInches() {
		return empHeightInches;
	}
	public void setEmpHeightInches(String empHeightInches) {
		this.empHeightInches = empHeightInches;
	}
	public String getEmpWeight() {
		return empWeight;
	}
	public void setEmpWeight(String empWeight) {
		this.empWeight = empWeight;
	}
	public String getEmpBloodGroup() {
		return empBloodGroup;
	}
	public void setEmpBloodGroup(String empBloodGroup) {
		this.empBloodGroup = empBloodGroup;
	}
	public String getEmpDisabilityYN() {
		return empDisabilityYN;
	}
	public void setEmpDisabilityYN(String empDisabilityYN) {
		this.empDisabilityYN = empDisabilityYN;
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
	public String getPassportNo() {
		return passportNo;
	}
	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}
	public String getOldPassportNo() {
		return oldPassportNo;
	}
	public void setOldPassportNo(String oldPassportNo) {
		this.oldPassportNo = oldPassportNo;
	}
	public String getPassportIssueDate() {
		return passportIssueDate;
	}
	public void setPassportIssueDate(String passportIssueDate) {
		this.passportIssueDate = passportIssueDate;
	}
	public String getPassportExpDate() {
		return passportExpDate;
	}
	public void setPassportExpDate(String passportExpDate) {
		this.passportExpDate = passportExpDate;
	}
	public ArrayList<CountryDTO> getCountryPreference() {
		return countryPreference;
	}
	public void setCountryPreference(ArrayList<CountryDTO> countryPreference) {
		this.countryPreference = countryPreference;
	}
	public String getCountryPreferenceString() {
		return countryPreferenceString;
	}
	public void setCountryPreferenceString(String countryPreferenceString) {
		this.countryPreferenceString = countryPreferenceString;
	}
	public String getEducationalQualificatoin() {
		return educationalQualificatoin;
	}
	public void setEducationalQualificatoin(String educationalQualificatoin) {
		this.educationalQualificatoin = educationalQualificatoin;
	}
	public String getInstituteName() {
		return instituteName;
	}
	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}
	public String getPassingYear() {
		return passingYear;
	}
	public void setPassingYear(String passingYear) {
		this.passingYear = passingYear;
	}
	public AddressDTO getPermanentAddress() {
		return permanentAddress;
	}
	public void setPermanentAddress(AddressDTO permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	public AddressDTO getMailingAddress() {
		return mailingAddress;
	}
	public void setMailingAddress(AddressDTO mailingAddress) {
		this.mailingAddress = mailingAddress;
	}
	public JobPreferenceDTO getJobPreferenceDTO() {
		return jobPreferenceDTO;
	}
	public void setJobPreferenceDTO(JobPreferenceDTO jobPreferenceDTO) {
		this.jobPreferenceDTO = jobPreferenceDTO;
	}
	public ExperienceDTO getLocalExperience() {
		return localExperience;
	}
	public void setLocalExperience(ExperienceDTO localExperience) {
		this.localExperience = localExperience;
	}
	public ExperienceDTO getAbroadExperience() {
		return abroadExperience;
	}
	public void setAbroadExperience(ExperienceDTO abroadExperience) {
		this.abroadExperience = abroadExperience;
	}
	public String getEmpHeightCM() {
		return empHeightCM;
	}
	public void setEmpHeightCM(String empHeightCM) {
		this.empHeightCM = empHeightCM;
	}
	public String getEmpDisabilityDetail() {
		return empDisabilityDetail;
	}
	public void setEmpDisabilityDetail(String empDisabilityDetail) {
		this.empDisabilityDetail = empDisabilityDetail;
	}
	public String getCountryPreferenceStr() {
		return countryPreferenceStr;
	}
	public void setCountryPreferenceStr(String countryPreferenceStr) {
		this.countryPreferenceStr = countryPreferenceStr;
	}
	public String getEmpBirthDistrictName() {
		return empBirthDistrictName;
	}
	public void setEmpBirthDistrictName(String empBirthDistrictName) {
		this.empBirthDistrictName = empBirthDistrictName;
	}
	public String getEmpBirthUpazilaOrThanaName() {
		return empBirthUpazilaOrThanaName;
	}
	public void setEmpBirthUpazilaOrThanaName(String empBirthUpazilaOrThanaName) {
		this.empBirthUpazilaOrThanaName = empBirthUpazilaOrThanaName;
	}
	public String getPrintDateTime() {
		return printDateTime;
	}
	public void setPrintDateTime(String printDateTime) {
		this.printDateTime = printDateTime;
	}
	public String getRegDateTime() {
		return regDateTime;
	}
	public void setRegDateTime(String regDateTime) {
		this.regDateTime = regDateTime;
	}
	public String getEmpAge() {
		return empAge;
	}
	public void setEmpAge(String empAge) {
		this.empAge = empAge;
	}
	
	
	
		
	
}

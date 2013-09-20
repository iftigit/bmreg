package org.controller.registration;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.model.CountryDAO;
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


public class EditRegistrationFormAction extends ActionSupport{

	private static final long serialVersionUID = 5524574220103897205L;
	PersonalInfoDTO personalDTO;
	NomineeDTO		nomineeDTO;
	EducationDTO    educationDTO;
	
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
	ArrayList<JobPreferenceDTO> jobPreferenceList=new ArrayList<JobPreferenceDTO>();
	ArrayList<LanguageDTO> languageList=new ArrayList<LanguageDTO>();
	ArrayList<TrainingDTO> trainingList=new ArrayList<TrainingDTO>();
	
	private String form_error;
	private String allRows="";

	private ArrayList<CountryDTO> countryList=new ArrayList<CountryDTO>();
	
	public String execute() {

	   /* PersonalInfoDTO duplicateSumissionCheck=(PersonalInfoDTO) ServletActionContext.getRequest().getSession().getAttribute("sessionObj_PersonalInfo");
	
		if(duplicateSumissionCheck==null)
		{
			return "blankForm";
		}
		*/
		
		ServletActionContext.getRequest().getSession().setAttribute("form_error", "edit_form");
		return "success";
	}

	public void validate()
	{
		HashMap<Integer, String>  countryMap=(HashMap<Integer, String>) getServletContext().getAttribute("ALL_COUNTRY_MAP");
		HashMap<Integer, String>  jobMainCategoryMap=(HashMap<Integer, String>) getServletContext().getAttribute("ALL_JOB_MAIN_CATEGORY_MAP");
		HashMap<Integer, String>  jobSubCategoryMap=(HashMap<Integer, String>) getServletContext().getAttribute("ALL_JOB_SUB_CATEGORY_MAP");
		HashMap<Integer, String>  allJobMap=(HashMap<Integer, String>) getServletContext().getAttribute("ALL_JOB_MAP");
		
		countryList=CountryDAO.getAllCountry();

		allRows = this.localExperience;
		System.out.println("Local Experience String:"+allRows);		
		
		if(!allRows.equalsIgnoreCase("")){
		String[] allExperience=allRows.split("99ifti99");
		for(int i=0;i<allExperience.length;i++)
		{
			String[] tmpExp=new String[4];
			String hold=allExperience[i];
			ExperienceDTO expDTO=new ExperienceDTO();
			tmpExp=hold.split("88khayer88");
			
						
			expDTO.setJobCategoryId(Integer.parseInt(tmpExp[0]));
			if(tmpExp.length>=2 && !tmpExp[1].equalsIgnoreCase(""))
				expDTO.setJobSubCategoryId(Integer.parseInt(tmpExp[1]));
			if(tmpExp.length>=3 && !tmpExp[2].equalsIgnoreCase(""))
				expDTO.setJobSubSubCategoryId(Integer.parseInt(tmpExp[2]));
			
			expDTO.setJobCategoryName(allJobMap.get(Integer.parseInt(tmpExp[0])));
			if(tmpExp.length>=2 && !tmpExp[1].equalsIgnoreCase(""))
				expDTO.setJobSubCategoryName(allJobMap.get(Integer.parseInt(tmpExp[1])));
			if(tmpExp.length>=3 && !tmpExp[2].equalsIgnoreCase(""))
				expDTO.setJobSubSubCategoryName(allJobMap.get(Integer.parseInt(tmpExp[2])));
						
			expDTO.setTotalYears(Float.parseFloat(tmpExp[tmpExp.length-1]));					
			localExperienceList.add(expDTO);
			
		}
		}
		allRows = this.abroadExperience;
		System.out.println("Abroad Experience String:"+allRows);		
		if(!allRows.equalsIgnoreCase("")){
		String[]  allExperience=allRows.split("99ifti99");
		for(int i=0;i<allExperience.length;i++)
		{
			String[] tmpExp=new String[5];
			String hold=allExperience[i];
			ExperienceDTO expDTO=new ExperienceDTO();
			tmpExp=hold.split("88khayer88");
			
			expDTO.setCountryId(Integer.parseInt(tmpExp[0]));
			if(tmpExp.length>=2 && !tmpExp[1].equalsIgnoreCase(""))
				expDTO.setJobCategoryId(Integer.parseInt(tmpExp[1]));
			if(tmpExp.length>=3 && !tmpExp[2].equalsIgnoreCase(""))
				expDTO.setJobSubCategoryId(Integer.parseInt(tmpExp[2]));
			if(tmpExp.length>=4 && !tmpExp[3].equalsIgnoreCase(""))
				expDTO.setJobSubSubCategoryId(Integer.parseInt(tmpExp[3]));
			
			
			expDTO.setCountryName(countryMap.get(Integer.parseInt(tmpExp[0])));
			if(tmpExp.length>=2 && !tmpExp[1].equalsIgnoreCase(""))
				expDTO.setJobCategoryName(allJobMap.get(Integer.parseInt(tmpExp[1])));
			if(tmpExp.length>=3 && !tmpExp[2].equalsIgnoreCase(""))
				expDTO.setJobSubCategoryName(allJobMap.get(Integer.parseInt(tmpExp[2])));
			if(tmpExp.length>=4 && !tmpExp[3].equalsIgnoreCase(""))
				expDTO.setJobSubSubCategoryName(allJobMap.get(Integer.parseInt(tmpExp[3])));
						
			expDTO.setTotalYears(Float.parseFloat(tmpExp[tmpExp.length-1]));					
			
			
			abroadExperienceList.add(expDTO);
		}
		}
		
		
		allRows = this.jobPreference;
		if(!allRows.equalsIgnoreCase("")){
		String[] allJobPrefernce=allRows.split("99ifti99");
		for(int i=0;i<allJobPrefernce.length;i++)
		{
			String[] tmpJob=new String[3];
			String hold=allJobPrefernce[i];
			JobPreferenceDTO jobDTO=new JobPreferenceDTO();
			tmpJob=hold.split("88khayer88");
			
			if(tmpJob[0]!=null && !tmpJob[0].equalsIgnoreCase("") && !tmpJob[0].equalsIgnoreCase("undefined"))
				jobDTO.setCategoryId(Integer.parseInt(tmpJob[0]));			
			if(tmpJob[1]!=null && !tmpJob[1].equalsIgnoreCase("") && !tmpJob[1].equalsIgnoreCase("undefined"))
				jobDTO.setSubCategoryId(Integer.parseInt(tmpJob[1]));			
			if(tmpJob[2]!=null && !tmpJob[2].equalsIgnoreCase("") && !tmpJob[2].equalsIgnoreCase("undefined"))
				jobDTO.setSubSubCategoryId(Integer.parseInt(tmpJob[2]));
			
			
			if(tmpJob[0]!=null && !tmpJob[0].equalsIgnoreCase("") && !tmpJob[0].equalsIgnoreCase("undefined"))
				jobDTO.setCategoryName(allJobMap.get(Integer.parseInt(tmpJob[0])));			
			if(tmpJob[1]!=null && !tmpJob[1].equalsIgnoreCase("") && !tmpJob[1].equalsIgnoreCase("undefined"))
				jobDTO.setSubCategoryName(allJobMap.get(Integer.parseInt(tmpJob[1])));			
			if(tmpJob[2]!=null && !tmpJob[2].equalsIgnoreCase("") && !tmpJob[2].equalsIgnoreCase("undefined"))
				jobDTO.setSubSubCategoryName(allJobMap.get(Integer.parseInt(tmpJob[2])));
			
			jobPreferenceList.add(jobDTO);
		}
		}
		
		
		if(countryPreferenceIds!=null && !countryPreferenceIds.equalsIgnoreCase(""))
		{
			String tmpCountry="";
			String countryArr[]=countryPreferenceIds.split(",");
			for(int i=0;i<countryArr.length;i++)
				tmpCountry+=countryMap.get(Integer.parseInt(countryArr[i].trim()))+", ";
			
			if(tmpCountry.length()>0)
				tmpCountry=tmpCountry.substring(0, tmpCountry.length()-2);

			countryPreferenceNames=tmpCountry;
			
		}
		
		allRows = this.languages;
		System.out.println("Language String:"+allRows);
		if(allRows!=null && !allRows.equals(""))
		{
			String[] allLanguages=allRows.split("99ifti99");
			for(int i=0;i<allLanguages.length;i++)
			{
				String[] tempLng=new String[3];
				String hold=allLanguages[i];
				LanguageDTO lngDTO=new LanguageDTO();
				tempLng=hold.split("88khayer88");
				lngDTO.setLanguage(tempLng[0]);
				lngDTO.setOralSkill(tempLng[1]);
				lngDTO.setWritingSkill(tempLng[2]);
				
				languageList.add(lngDTO);
	
			}
		}
		
		
		allRows = this.trainings;
		System.out.println("Training String:"+allRows);
		if(allRows !=null && !allRows.equals(""))
		{
			String[] allTraining=allRows.split("99ifti99");
			for(int i=0;i<allTraining.length;i++)
			{
				String[] tempTrain=new String[3];
				String hold=allTraining[i];
				TrainingDTO trainDTO=new TrainingDTO();
				tempTrain=hold.split("88khayer88");
				trainDTO.setTrainingName(tempTrain[0]);
				trainDTO.setFromWhere(tempTrain[1]);
				trainDTO.setDuration(tempTrain[2]);
				trainDTO.setDescription(tempTrain[3]);
				
				trainingList.add(trainDTO);
			}
		}
		
		String[] countryArr=countryPreferenceIds.split(",");
		ArrayList<CountryDTO> tmpCountryList=new ArrayList<CountryDTO>();
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

	public ArrayList<JobPreferenceDTO> getJobPreferenceList() {
		return jobPreferenceList;
	}

	public void setJobPreferenceList(ArrayList<JobPreferenceDTO> jobPreferenceList) {
		this.jobPreferenceList = jobPreferenceList;
	}

	public ArrayList<LanguageDTO> getLanguageList() {
		return languageList;
	}

	public void setLanguageList(ArrayList<LanguageDTO> languageList) {
		this.languageList = languageList;
	}

	public ArrayList<TrainingDTO> getTrainingList() {
		return trainingList;
	}

	public void setTrainingList(ArrayList<TrainingDTO> trainingList) {
		this.trainingList = trainingList;
	}

	public String getAllRows() {
		return allRows;
	}

	public void setAllRows(String allRows) {
		this.allRows = allRows;
	}

	public String getForm_error() {
		return form_error;
	}


	public void setForm_error(String formError) {
		form_error = formError;
	}
	
	public ArrayList<CountryDTO> getCountryList() {
		return countryList;
	}

	public void setCountryList(ArrayList<CountryDTO> countryList) {
		this.countryList = countryList;
	}

	public ServletContext getServletContext()
	{
		return ServletActionContext.getServletContext();
	}

}

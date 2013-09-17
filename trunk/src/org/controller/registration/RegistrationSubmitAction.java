package org.controller.registration;


import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.model.CountryDAO;
import org.model.RegistrationDAO;
import org.singleton.RegistrationSingleton;
import org.table.AddressDTO;
import org.table.CountryDTO;
import org.table.EducationDTO;
import org.table.ExperienceDTO;
import org.table.JobPreferenceDTO;
import org.table.LanguageDTO;
import org.table.LogDTO;
import org.table.NomineeDTO;
import org.table.PersonalInfoDTO;
import org.table.TrainingDTO;
import org.table.UserDTO;

import com.opensymphony.xwork2.ActionSupport;

public class RegistrationSubmitAction  extends ActionSupport{

	private static final long serialVersionUID = 3937606573865099216L;
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
	ArrayList<ExperienceDTO> experienceList=new ArrayList<ExperienceDTO>();
	ArrayList<JobPreferenceDTO> jobPreferenceList=new ArrayList<JobPreferenceDTO>();
	ArrayList<LanguageDTO> languageList=new ArrayList<LanguageDTO>();
	ArrayList<TrainingDTO> trainingList=new ArrayList<TrainingDTO>();
	private ArrayList<CountryDTO> countryList=new ArrayList<CountryDTO>();
	
	
	
	private String allRows="";
	boolean error=false;

	
@SuppressWarnings("unchecked")
public String execute() throws Exception 
	{		               
	
		PersonalInfoDTO duplicateSumissionCheck=(PersonalInfoDTO) ServletActionContext.getRequest().getSession().getAttribute("sessionObj_PersonalInfo");
		String xForward=ServletActionContext.getRequest().getHeader("X-Forwarded-For")==null?"":ServletActionContext.getRequest().getHeader("X-Forwarded-For");
		String via=ServletActionContext.getRequest().getHeader("Via")==null?"":ServletActionContext.getRequest().getHeader("Via");
		String remoteId=ServletActionContext.getRequest().getRemoteAddr()==null?"":ServletActionContext.getRequest().getRemoteAddr();
		String submittedAuthKey=xForward+via+remoteId;
		LogDTO  logInfoDTO=new LogDTO();
		logInfoDTO.setxForward(xForward);
		logInfoDTO.setVia(via);
		logInfoDTO.setRemoteAddress(remoteId);
		
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("loggedInUser");
		String userId="";String userType="";
		if(loggedInUser==null){
			userId="";
			userType="Individual";
		}
		else{
			userId=loggedInUser.getUserId();
			userType=loggedInUser.getUserType();
		}
		if(duplicateSumissionCheck==null)
		{
			return "blankForm";
		}
//		else if(!loggedInUser.getAuthenticationKey().equalsIgnoreCase(submittedAuthKey) && !loggedInUser.getUserType().equalsIgnoreCase("UISC_REG_OPERATOR"))
//		{
//			return "logout";
//		}
//		else if(loggedInUser.getAccessRight()==0)
//		{
//			return "timeOver";	
//		}
		
		RegistrationDAO regDAO=new RegistrationDAO();
        String registrationId="";
        registrationId=RegistrationSingleton.generateRegistrationId(pAddress.getDistrictId(),personalDTO.getEmpGender());
        String response=regDAO.insertEmpRegistrationInfo(
        		 registrationId,
				 personalDTO,
				 nomineeDTO,
				 educationDTO, 
				 logInfoDTO,
				 experienceList,
				 jobPreferenceList,
				 languageList,
				 trainingList,			 								 
				 userId,userType
				 );
        if(response.equalsIgnoreCase("SUCCESS"))
        {
        	ServletActionContext.getRequest().getSession().setAttribute("sessionObj_regId",registrationId);
        	personalDTO=null;
        	nomineeDTO=null;
        	ServletActionContext.getRequest().getSession().setAttribute("sessionObj_PersonalInfo",null);
        	return "success";
        }
        else
        {
        	addFieldError( "Err_RegSubmit", response);        	
        	return "input";
        }

        
	} //End of Method...

public void validate()
{
	RegistrationDAO regDao=new RegistrationDAO();
	countryList=CountryDAO.getAllCountry();
	
	HashMap<Integer, String>  countryMap=(HashMap<Integer, String>) getServletContext().getAttribute("ALL_COUNTRY_MAP");
	HashMap<Integer, String>  jobMainCategoryMap=(HashMap<Integer, String>) getServletContext().getAttribute("ALL_JOB_MAIN_CATEGORY_MAP");
	HashMap<Integer, String>  jobSubCategoryMap=(HashMap<Integer, String>) getServletContext().getAttribute("ALL_JOB_SUB_CATEGORY_MAP");
	HashMap<Integer, String>  allJobMap=(HashMap<Integer, String>) getServletContext().getAttribute("ALL_JOB_MAP");
	HashMap<Integer, String>  allDegreeMap=(HashMap<Integer, String>) getServletContext().getAttribute("ALL_DEGREE_MAP");
	HashMap<Integer, String>  allRelationMap=(HashMap<Integer, String>) getServletContext().getAttribute("ALL_RELATION_MAP");
	
	

	HashMap<Integer, String>  allDivisionMap=(HashMap<Integer, String>) getServletContext().getAttribute("ALL_DIVISTION_MAP");
	HashMap<Integer, String>  allDistrictMap=(HashMap<Integer, String>) getServletContext().getAttribute("ALL_DISTRICT_MAP");
	HashMap<Integer, String>  allUpazillaOrThanaMap=(HashMap<Integer, String>) getServletContext().getAttribute("AL_UPAZILLA_THANA_MAP");
	HashMap<Integer, String>  allUnionOrWardMap=(HashMap<Integer, String>) getServletContext().getAttribute("ALL_UNION_WARD_MAP");
	HashMap<Integer, String>  allMauzaOrMohollaMap=(HashMap<Integer, String>) getServletContext().getAttribute("ALL_MAUZA_MOHOLLA_MAP");
	HashMap<Integer, String>  allVillageMap=(HashMap<Integer, String>) getServletContext().getAttribute("ALL_VILLAGE_MAP");
			
	
	nomineeDTO.getAddress().setDivisionName(allDivisionMap.get(Integer.valueOf(nomineeDTO.getAddress().getDivisionId())));
	nomineeDTO.getAddress().setDistrictName(allDistrictMap.get(Integer.valueOf(nomineeDTO.getAddress().getDistrictId())));
	nomineeDTO.getAddress().setUpazillaOrThanaName(allUpazillaOrThanaMap.get(Integer.valueOf(nomineeDTO.getAddress().getUpazillaOrThanaId())));
	nomineeDTO.getAddress().setUnionOrWardName(allUnionOrWardMap.get(Integer.valueOf(nomineeDTO.getAddress().getUnionOrWardId())));
	nomineeDTO.getAddress().setMauzaOrMohollaName(allMauzaOrMohollaMap.get(Integer.valueOf(nomineeDTO.getAddress().getMauzaOrMohollaId())));
	nomineeDTO.getAddress().setVillageName(allVillageMap.get(Integer.valueOf(nomineeDTO.getAddress().getVillageId())));

	pAddress.setDivisionName(allDivisionMap.get(Integer.valueOf(pAddress.getDivisionId())));
	pAddress.setDistrictName(allDistrictMap.get(Integer.valueOf(pAddress.getDistrictId())));
	pAddress.setUpazillaOrThanaName(allUpazillaOrThanaMap.get(Integer.valueOf(pAddress.getUpazillaOrThanaId())));
	pAddress.setUnionOrWardName(allUnionOrWardMap.get(Integer.valueOf(pAddress.getUnionOrWardId())));
	pAddress.setMauzaOrMohollaName(allMauzaOrMohollaMap.get(Integer.valueOf(pAddress.getMauzaOrMohollaId())));
	pAddress.setVillageName(allVillageMap.get(Integer.valueOf(pAddress.getVillageId())));

	mAddress.setDivisionName(allDivisionMap.get(Integer.valueOf(mAddress.getDivisionId())));
	mAddress.setDistrictName(allDistrictMap.get(Integer.valueOf(mAddress.getDistrictId())));
	mAddress.setUpazillaOrThanaName(allUpazillaOrThanaMap.get(Integer.valueOf(mAddress.getUpazillaOrThanaId())));
	mAddress.setUnionOrWardName(allUnionOrWardMap.get(Integer.valueOf(mAddress.getUnionOrWardId())));
	mAddress.setMauzaOrMohollaName(allMauzaOrMohollaMap.get(Integer.valueOf(mAddress.getMauzaOrMohollaId())));
	mAddress.setVillageName(allVillageMap.get(Integer.valueOf(mAddress.getVillageId())));

	educationDTO.setHeighestDegreeName(allDegreeMap.get(Integer.valueOf(educationDTO.getHeighestDegreeId())));
	
	
	nomineeDTO.setNomineeRelationName(allRelationMap.get(Integer.valueOf(nomineeDTO.getNomineeRelation())));
	nomineeDTO.setContact1RelationName(allRelationMap.get(Integer.valueOf(nomineeDTO.getContact1Relation())));
	nomineeDTO.setContact2RelationName(allRelationMap.get(Integer.valueOf(nomineeDTO.getContact2Relation())));
	nomineeDTO.setContact3RelationName(allRelationMap.get(Integer.valueOf(nomineeDTO.getContact3Relation())));
	
	
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
		
					
		expDTO.setTotalYears(Integer.parseInt(tmpExp[tmpExp.length-1]));	
		expDTO.setExpType(1);
		experienceList.add(expDTO);
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
		
					
		expDTO.setTotalYears(Integer.parseInt(tmpExp[tmpExp.length-1]));					
		expDTO.setExpType(2);
		
		experienceList.add(expDTO);
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
		String countryArr1[]=countryPreferenceIds.split(",");
		for(int i=0;i<countryArr1.length;i++)
			tmpCountry+=countryMap.get(Integer.parseInt(countryArr1[i].trim()))+", ";
		
		if(tmpCountry.length()>0)
			tmpCountry=tmpCountry.substring(0, tmpCountry.length()-2);

		countryPreferenceNames=tmpCountry;
		
	}
	
	allRows = this.languages;
	System.out.println("Language String:"+allRows);
	if(!allRows.equals(""))
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
	if(!allRows.equals(""))
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
	
	personalDTO.setPermanentAddress(pAddress);
	personalDTO.setMailingAddress(mAddress);
	personalDTO.setCountryPreferenceString(countryPreferenceIds.replaceAll(", ", "|"));
	
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
	/*---------------Server Side Validation -----------------*/
	String errorMsg="";
	if(personalDTO.getEmpGivenName().trim().equalsIgnoreCase("") && personalDTO.getEmpLastName().trim().equalsIgnoreCase("")){
		addFieldError( "sMsg_seekerName", " Provide Jobseeker Name." );
		errorMsg="Provide Jobseeker Name, ";
		error=true;
	}
	if(personalDTO.getEmpFatherName().trim().equalsIgnoreCase("")){
		addFieldError( "sMsg_fatherName", " Provide Father's Name." );
		errorMsg+="Provide Father's Name, ";
		error=true;
	}
	if(personalDTO.getEmpMobileNumber().trim().equalsIgnoreCase("")){
		addFieldError( "sMsg_montherName", " Provide Mother's Name." );
		errorMsg+="Provide Mother's Name, ";
		error=true;
	}
	try{
		float heightCM=(float) (((Float.parseFloat(personalDTO.getEmpHeightFeet()))*12+Float.parseFloat(personalDTO.getEmpHeightInches().equalsIgnoreCase("")?"0":personalDTO.getEmpHeightInches()))*2.54);
		personalDTO.setEmpHeightCM(String.valueOf(heightCM));
	}
	catch(Exception ex)
	{
		addFieldError( "sMsg_height", " Incorrect Height Information." );
		errorMsg+="Incorrect Height Information, ";
		error=true;
	}
	if(personalDTO.getEmpBirthDate().trim().equalsIgnoreCase("")){
		addFieldError( "sMsg_birthDate", " Provide Birth Date." );
		errorMsg+="Provide Birth Date, ";
		error=true;
	}
	if(personalDTO.getEmpGender().trim().equalsIgnoreCase("")){
		addFieldError( "sMsg_sex", " Select Gender." );
		errorMsg+="Select Gender, ";
		error=true;
	}
	if(personalDTO.getEmpReligion().trim().equalsIgnoreCase("")){
		addFieldError( "sMsg_religion", " Select Religion." );
		errorMsg+="Select Religion, ";
		error=true;
	}
	if(!personalDTO.getEmpMaritalStatus().equalsIgnoreCase("Single") && personalDTO.getEmpSpouseName().trim().equalsIgnoreCase("")){
		addFieldError( "sMsg_spouseName", " Provide Spouse Name." );
		errorMsg+="Provide Spouse Name, ";
		error=true;
	}
	if(personalDTO.getEmpMobileNumber().trim().equalsIgnoreCase("")){
		addFieldError( "sMsg_mobile", " Provide Mobile Number." );
		errorMsg+="Provide Mobile Number, ";
		error=true;
	}
	if(personalDTO.getEmpWeight().trim().equalsIgnoreCase("")){
		addFieldError( "sMsg_weight", " Provide Weight." );
		errorMsg+="Provide Weight, ";
		error=true;
	}
	
	
	if(personalDTO.getNationalId().equalsIgnoreCase("") && personalDTO.getBirthRegId().equalsIgnoreCase("")){
		addFieldError( "sMsg_nationalId_birthReg", " Provide either Birth Reg. No or National Id Number." );
		errorMsg+="Provide either Birth Reg. No or National Id Number, ";
		error=true;
	}
	if(!personalDTO.getNationalId().equalsIgnoreCase("") ){
		if(regDao.getNationalIdCount(personalDTO.getNationalId().trim())>0)
		{
			addFieldError( "sMsg_nationalId_birthReg", " National Id Already Exist." );
			errorMsg+="National Id Already Exist, ";
			error=true;
		}
	}
	if(!personalDTO.getBirthRegId().equalsIgnoreCase("") ){
		if(regDao.getBirthRegIdCount(personalDTO.getBirthRegId().trim())>0)
		{
			addFieldError( "sMsg_nationalId_birthReg", " Brith Reg. Id Already Exist." );
			errorMsg+="Brith Reg. Id Already Exist, ";
			error=true;
		}
	}
	
	if(!personalDTO.getPassportNo().equalsIgnoreCase("")){
		if(personalDTO.getPassportIssueDate().equalsIgnoreCase("")){
		addFieldError( "sMsg_passportIssueDate", " Provide Passport Issue Date." );
		errorMsg+="Provide Passport Issue Date, ";
		error=true;
		}
		if(personalDTO.getPassportExpDate().equalsIgnoreCase("")){
		addFieldError( "sMsg_passportExpDate", " Provide Passport Expire Date." );
		errorMsg+="Provide Passport Expire Date, ";
		error=true;
		}
	}
	if(nomineeDTO.getNomineeRelation().equalsIgnoreCase("")){
		addFieldError( "msg_nomineeName_relationship", " Select Nominee Relationship." );
		errorMsg+="Select Nominee Relationship, ";
		error=true;
	}
	if(nomineeDTO.getNomineeName().equalsIgnoreCase("")){
		addFieldError( "msg_nomineeName_relationship", " Provide Nominee Name." );
		errorMsg+="Provide Nominee Name, ";
		error=true;
	}
	if(nomineeDTO.getNomineeFatherName().equalsIgnoreCase("")){
		addFieldError( "sMsg_nomineeFather", " Provide Nominee Father's Name." );
		errorMsg+="Provide Nominee Father's Name, ";
		error=true;
	}
	if(nomineeDTO.getNomineeMotherName().equalsIgnoreCase("")){
		addFieldError( "sMsg_nomineeMother", " Provide Nominee Mother's Name." );
		errorMsg+="Provide Nominee Mother's Name, ";
		error=true;
	}
	if(nomineeDTO.getAddress().getDivisionId()==null || nomineeDTO.getAddress().getDivisionName()==null||
	   nomineeDTO.getAddress().getDivisionId().equalsIgnoreCase("") || nomineeDTO.getAddress().getDivisionName().equalsIgnoreCase("")){
		addFieldError( "sMsg_nomineeDivision", " Select Division." );
		errorMsg+="Provide Nominee Division, ";
		error=true;
	}
	if(nomineeDTO.getAddress().getDistrictId()==null||nomineeDTO.getAddress().getDivisionName()==null||
	   nomineeDTO.getAddress().getDistrictId().equalsIgnoreCase("")||nomineeDTO.getAddress().getDivisionName().equalsIgnoreCase("")){
		addFieldError( "sMsg_nomineeDistrict", " Select District." );
		errorMsg+="Provide Nominee District, ";
		error=true;
	}
	if(nomineeDTO.getAddress().getUpazillaOrThanaId()==null||nomineeDTO.getAddress().getUpazillaOrThanaName()==null||
	   nomineeDTO.getAddress().getUpazillaOrThanaId().equalsIgnoreCase("")||nomineeDTO.getAddress().getUpazillaOrThanaName().equalsIgnoreCase("")){
		addFieldError( "sMsg_nomineeUpazillaOrThana", " Select Upazilla or Thana." );
		errorMsg+="Provide Nominee Upazilla or Thana, ";
		error=true;
	}
	if(nomineeDTO.getAddress().getUnionOrWardId()==null||nomineeDTO.getAddress().getUnionOrWardName()==null||
	   nomineeDTO.getAddress().getUnionOrWardId().equalsIgnoreCase("")||nomineeDTO.getAddress().getUnionOrWardName().equalsIgnoreCase("")){
		addFieldError( "sMsg_nomineeUnionOrWard", " Select Union or Ward." );
		errorMsg+="Provide Nominee Union or Ward, ";
		error=true;
	}
	if(nomineeDTO.getAddress().getMauzaOrMohollaId()==null||nomineeDTO.getAddress().getMauzaOrMohollaName()==null||
	   nomineeDTO.getAddress().getMauzaOrMohollaId().equalsIgnoreCase("")||nomineeDTO.getAddress().getMauzaOrMohollaName().equalsIgnoreCase("")){
		addFieldError( "sMsg_nomineeMauzaOrMoholla", " Select Mauza or Moholla." );
		errorMsg+="Provide Nominee Mauza or Moholla, ";
		error=true;
	}
	if(nomineeDTO.getAddress().getVillageId()==null||nomineeDTO.getAddress().getVillageName()==null||
	   nomineeDTO.getAddress().getVillageId().equalsIgnoreCase("")||nomineeDTO.getAddress().getVillageName().equalsIgnoreCase("")){
		addFieldError( "sMsg_nomineeVillage", " Select Village." );
		errorMsg+="Provide Nominee Village, ";
		error=true;
	}
	/*
	if(nomineeDTO.getAddress().getPostCode()==null||nomineeDTO.getAddress().getPostCode().equalsIgnoreCase("")){
		addFieldError( "sMsg_nomineePostCode", " Provide Post Code." );
		errorMsg+="Provide Nominee Post Code, ";
		error=true;
	}
	if(nomineeDTO.getAddress().getPostOffice()==null||nomineeDTO.getAddress().getPostOffice().equalsIgnoreCase("")){
		addFieldError( "sMsg_nomineePostOffice", " Provide Post Office." );
		errorMsg+="Provide Nominee Post Office, ";
		error=true;
	}
	*/
	if(nomineeDTO.getNomineePhoneOrMobile()==null || nomineeDTO.getNomineePhoneOrMobile().equalsIgnoreCase("")){
		addFieldError( "sMsg_nomineePhone", " Provide Mobile or Phone Number." );
		errorMsg+="Provide Nominee Mobile or Phone Number, ";
		error=true;
	}
	if(nomineeDTO.getContact1Name().equalsIgnoreCase("") || nomineeDTO.getContact1Mobile().equalsIgnoreCase("") || nomineeDTO.getContact1Relation().equalsIgnoreCase("")){
		addFieldError( "sMsg_nomineeContactPerson1", " Provide all Information for Contact 1." );
		errorMsg+="Provide all Information for Contact 1, ";
		error=true;
	}
	if(nomineeDTO.getContact2Name().equalsIgnoreCase("") || nomineeDTO.getContact2Mobile().equalsIgnoreCase("") || nomineeDTO.getContact2Relation().equalsIgnoreCase("")){
		addFieldError( "sMsg_nomineeContactPerson2", " Provide all Information for Contact 2." );
		errorMsg+="Provide all Information for Contact 2, ";
		error=true;
	}
	if(nomineeDTO.getContact3Name().equalsIgnoreCase("") || nomineeDTO.getContact3Mobile().equalsIgnoreCase("") || nomineeDTO.getContact3Relation().equalsIgnoreCase("")){
		addFieldError( "sMsg_nomineeContactPerson3", " Provide all Information for Contact 3." );
		errorMsg+="Provide all Information for Contact 3, ";
		error=true;
	}
	
	if(educationDTO.getHeighestDegreeId()==0){
		addFieldError( "sMsg_heighestDegree", " Select Degree Name." );
		errorMsg+="Select Degree Name, ";
		error=true;
	}
	if(educationDTO.getHeighestDegreeId()!=0 && educationDTO.getHeighestDegreeId()!=7 &&
	    educationDTO.getLastInstitute().equalsIgnoreCase("")){
		addFieldError( "sMsg_lastInstitute", " Provide Institute Name." );
		errorMsg+="Provide Institute Name, ";
		error=true;
	}
	if(educationDTO.getHeighestDegreeId()!=0 && educationDTO.getHeighestDegreeId()!=7 &&
		educationDTO.getPassingYear().equalsIgnoreCase("")){
		addFieldError( "sMsg_passingYear", " Provide Passing Year." );
		errorMsg+="Provide Passing Year, ";
		error=true;
	}
	
	
	if(pAddress.getDivisionId()==null||pAddress.getDistrictId()==null||
	   pAddress.getUpazillaOrThanaId()==null||pAddress.getUnionOrWardId()==null||
	   pAddress.getMauzaOrMohollaId()==null|| pAddress.getVillageId()==null||

	   
	   pAddress.getDivisionName()==null||pAddress.getDistrictName()==null||
	   pAddress.getUpazillaOrThanaName()==null||pAddress.getUnionOrWardName()==null||
	   pAddress.getMauzaOrMohollaName()==null|| pAddress.getVillageName()==null||
			
	   pAddress.getDivisionId().equalsIgnoreCase("")||pAddress.getDistrictId().equalsIgnoreCase("")||
	   pAddress.getUpazillaOrThanaId().equalsIgnoreCase("")||pAddress.getUnionOrWardId().equalsIgnoreCase("")||
	   pAddress.getMauzaOrMohollaId().equalsIgnoreCase("")|| pAddress.getVillageId().equalsIgnoreCase("")||
	   
	   pAddress.getDivisionName().equalsIgnoreCase("")||pAddress.getDistrictName().equalsIgnoreCase("")||
	   pAddress.getUpazillaOrThanaName().equalsIgnoreCase("")||pAddress.getUnionOrWardName().equalsIgnoreCase("")||
	   pAddress.getMauzaOrMohollaName().equalsIgnoreCase("")|| pAddress.getVillageName().equalsIgnoreCase("")
	   
		){
	
		addFieldError( "sMsg_permanentAddress", " Correct Permanent Address." );
		errorMsg+="Correct Permanent Address, ";
		error=true;	
	 }
	if(mAddress.getDivisionId()==null||mAddress.getDistrictId()==null||
	   mAddress.getUpazillaOrThanaId()==null||mAddress.getUnionOrWardId()==null||
	   mAddress.getMauzaOrMohollaId()==null|| mAddress.getVillageId()==null ||
	   
	   mAddress.getDivisionName()==null||mAddress.getDistrictName()==null||
	   mAddress.getUpazillaOrThanaName()==null||mAddress.getUnionOrWardName()==null||
	   mAddress.getMauzaOrMohollaName()==null|| mAddress.getVillageName()==null||
			   
	   mAddress.getDivisionId().equalsIgnoreCase("")||mAddress.getDistrictId().equalsIgnoreCase("")||
	   mAddress.getUpazillaOrThanaId().equalsIgnoreCase("")||mAddress.getUnionOrWardId().equalsIgnoreCase("")||
	   mAddress.getMauzaOrMohollaId().equalsIgnoreCase("")|| mAddress.getVillageId().equalsIgnoreCase("") ||
	   
	   mAddress.getDivisionName().equalsIgnoreCase("")||mAddress.getDistrictName().equalsIgnoreCase("")||
	   mAddress.getUpazillaOrThanaName().equalsIgnoreCase("")||mAddress.getUnionOrWardName().equalsIgnoreCase("")||
	   mAddress.getMauzaOrMohollaName().equalsIgnoreCase("")|| mAddress.getVillageName().equalsIgnoreCase("")
	  ){
			
	   addFieldError( "sMsg_mailingAddress", " Correct Mailing Address." );
	   errorMsg+="Correct Mailing Address, ";
	   error=true;	
	 }
//	error=true;
//	addFieldError( "sMsg_mailingAddress", " Correct Mailing Address." );
	if(error==true){
		ServletActionContext.getRequest().getSession().setAttribute("form_error", "form_error");
		
		addFieldError( "Err_RegSubmit", errorMsg.substring(0, errorMsg.length()-2));
		return;
	}
	
	/*---------------- End of Server Side Validation ----------------------*/

	
}



	public ServletContext getServletContext()
	{
		return ServletActionContext.getServletContext();
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



	public boolean isError() {
		return error;
	}



	public void setError(boolean error) {
		this.error = error;
	}

	public ArrayList<ExperienceDTO> getExperienceList() {
		return experienceList;
	}

	public void setExperienceList(ArrayList<ExperienceDTO> experienceList) {
		this.experienceList = experienceList;
	}

	public ArrayList<CountryDTO> getCountryList() {
		return countryList;
	}

	public void setCountryList(ArrayList<CountryDTO> countryList) {
		this.countryList = countryList;
	}

	
}

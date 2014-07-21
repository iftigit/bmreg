package org.controller.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.model.CountryDAO;
import org.model.RegistrationDAO;
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

public class PreviewRegFormAction extends ActionSupport{
	
	private static final long serialVersionUID = -4949680482269443391L;
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
	private ArrayList<CountryDTO> countryList=new ArrayList<CountryDTO>();
	
	
	private String allRows="";
	boolean error=false;	
	
	public String execute() throws Exception 
	{
		System.out.println("In Preview Action ...");
	    return SUCCESS;	
	}
	
	public void validate()
	{
		RegistrationDAO regDao=new RegistrationDAO();
		ServletActionContext.getRequest().getSession().setAttribute("sessionObj_PersonalInfo", personalDTO);
		
		countryList=CountryDAO.getAllCountry(0);
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
		
		/*========================*/
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
		
		if(personalDTO.getEmpBirthDistrict()!=null && !personalDTO.getEmpBirthDistrict().equalsIgnoreCase("")){
		personalDTO.setEmpBirthDistrictName(allDistrictMap.get(Integer.valueOf((personalDTO.getEmpBirthDistrict()))));
		}
		if(personalDTO.getEmpBirthUpazilaOrThana()!=null && !personalDTO.getEmpBirthUpazilaOrThana().equalsIgnoreCase("")){
		personalDTO.setEmpBirthUpazilaOrThanaName(allUpazillaOrThanaMap.get(Integer.valueOf((personalDTO.getEmpBirthUpazilaOrThana()))));
		}
		
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
						
			try{
			expDTO.setTotalYears(Float.parseFloat(tmpExp[tmpExp.length-1]));
			}
			catch(Exception ex){
				addFieldError( "sMsg_expLocal", " Total year should be a numeric number." );
				error=true;
				//expDTO.setTotalYears(0);
			}
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
						
			try{
			expDTO.setTotalYears(Float.parseFloat(tmpExp[tmpExp.length-1]));					
			}
			catch(Exception ex){
				addFieldError( "sMsg_expAborad", " Total year should be a numeric number." );
				error=true;
				//expDTO.setTotalYears(0);
			}
			
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
		/*========================*/
		/*---------------Server Side Validation -----------------*/		
		if(personalDTO.getEmpGivenName().trim().equalsIgnoreCase("") && personalDTO.getEmpLastName().trim().equalsIgnoreCase("")){
			addFieldError( "sMsg_seekerName", " Provide Name." );
			error=true;
		}
		if(personalDTO.getEmpFatherName().trim().equalsIgnoreCase("")){
			addFieldError( "sMsg_fatherName", " Provide Father's Name." );
			error=true;
		}
		if(personalDTO.getEmpMobileNumber().trim().equalsIgnoreCase("")){
			addFieldError( "sMsg_montherName", " Provide Mother's Name." );
			error=true;
		}
		try{
			float heightCM=(float) (((Float.parseFloat(personalDTO.getEmpHeightFeet()))*12+Float.parseFloat(personalDTO.getEmpHeightInches().equalsIgnoreCase("")?"0":personalDTO.getEmpHeightInches()))*2.54);
			personalDTO.setEmpHeightCM(String.valueOf(heightCM));
		}
		catch(Exception ex)
		{
			addFieldError( "sMsg_height", " Incorrect Height Information." );
			error=true;
		}
		if(personalDTO.getEmpBirthDate().trim().equalsIgnoreCase("")){
			addFieldError( "sMsg_birthDate", " Provide Birth Date." );
			error=true;
		}
		
		if(personalDTO.getEmpGender().trim().equalsIgnoreCase("")){
			addFieldError( "sMsg_sex", " Select Gender." );
			error=true;
		}
		if(personalDTO.getEmpReligion().trim().equalsIgnoreCase("")){
			addFieldError( "sMsg_religion", " Select Religion." );
			error=true;
		}
		if(!personalDTO.getEmpMaritalStatus().equalsIgnoreCase("Single") && personalDTO.getEmpSpouseName().trim().equalsIgnoreCase("")){
			addFieldError( "sMsg_spouseName", " Provide Spouse Name." );
			error=true;
		}
		if(personalDTO.getEmpMobileNumber().trim().equalsIgnoreCase("")){
			addFieldError( "sMsg_mobile", " Provide Mobile Number." );
			error=true;
		}
		if(personalDTO.getEmpWeight().trim().equalsIgnoreCase("")){
			addFieldError( "sMsg_weight", " Provide Weight." );
			error=true;
		}
		
		if(!personalDTO.getPassportNo().equalsIgnoreCase("")){
			if(personalDTO.getPassportIssueDate().equalsIgnoreCase("")){
			addFieldError( "sMsg_passportIssueDate", " Provide Passport Issue Date." );
			error=true;
			}
			if(personalDTO.getPassportExpDate().equalsIgnoreCase("")){
			addFieldError( "sMsg_passportExpDate", " Provide Passport Expire Date." );
			error=true;
			}
		}
		if(personalDTO.getNationalId().equalsIgnoreCase("") && personalDTO.getBirthRegId().equalsIgnoreCase("") && personalDTO.getPassportNo().equalsIgnoreCase("")){
			addFieldError( "sMsg_nationalId_birthReg", " Provide either Birth Reg. No, National Id or Passport Number Number." );
			error=true;
		}
		if(!personalDTO.getNationalId().trim().equalsIgnoreCase("") ){
			if(regDao.getNationalIdCount(personalDTO.getNationalId().trim())>0 && !personalDTO.getNationalId().trim().equalsIgnoreCase(personalDTO.getOldNationalId()))
			{
				addFieldError( "sMsg_nationalId_birthReg", " National Id Already Exist." );
				error=true;
			}
		}
			if(personalDTO.getNationalId().length()<13 || personalDTO.getNationalId().length()>18)
				if(personalDTO.getNationalId()!=null && !personalDTO.getNationalId().equalsIgnoreCase("") ){
			{
				addFieldError( "sMsg_nationalId_birthReg", " National Id should be 13-18 digit long." );
				error=true;
			}
		}
		if(!personalDTO.getBirthRegId().trim().equalsIgnoreCase("") ){
			if(regDao.getBirthRegIdCount(personalDTO.getBirthRegId().trim())>0 && !personalDTO.getBirthRegId().trim().equalsIgnoreCase(personalDTO.getOldBirthRegId()))
			{
				addFieldError( "sMsg_nationalId_birthReg", " Brith Reg. Id Already Exist." );
				error=true;
			}
		}
		
		if(!personalDTO.getPassportNo().trim().equalsIgnoreCase("")){
			if(personalDTO.getPassportIssueDate().equalsIgnoreCase("")){
			addFieldError( "sMsg_passportIssueDate", " Provide Passport Issue Date." );
			error=true;
			}
			if(personalDTO.getPassportExpDate().trim().equalsIgnoreCase("")){
			addFieldError( "sMsg_passportExpDate", " Provide Passport Expire Date." );
			error=true;
			}
		}
		if(nomineeDTO.getNomineeRelation().trim().equalsIgnoreCase("")){
			addFieldError( "msg_nomineeName_relationship", " Select Nominee Relationship." );
			error=true;
		}
		if(nomineeDTO.getNomineeName().trim().equalsIgnoreCase("")){
			addFieldError( "msg_nomineeName_relationship", " Provide Nominee Name." );
			error=true;
		}
		if(nomineeDTO.getNomineeFatherName().trim().equalsIgnoreCase("")){
			addFieldError( "sMsg_nomineeFather", " Provide Nominee Father's Name." );
			error=true;
		}
		if(nomineeDTO.getNomineeMotherName().trim().equalsIgnoreCase("")){
			addFieldError( "sMsg_nomineeMother", " Provide Nominee Mother's Name." );
			error=true;
		}
		if(nomineeDTO.getAddress().getDivisionId().equalsIgnoreCase("")){
			addFieldError( "sMsg_nomineeDivision", " Select Division." );
			error=true;
		}
		if(nomineeDTO.getAddress().getDistrictId().equalsIgnoreCase("")){
			addFieldError( "sMsg_nomineeDistrict", " Select District." );
			error=true;
		}
		if(nomineeDTO.getAddress().getUpazillaOrThanaId().equalsIgnoreCase("")){
			addFieldError( "sMsg_nomineeUpazillaOrThana", " Select Upazilla or Thana." );
			error=true;
		}
		if(nomineeDTO.getAddress().getUnionOrWardId().equalsIgnoreCase("")){
			addFieldError( "sMsg_nomineeUnionOrWard", " Select Union or Ward." );
			error=true;
		}
		if(nomineeDTO.getAddress().getMauzaOrMohollaId().equalsIgnoreCase("")){
			addFieldError( "sMsg_nomineeMauzaOrMoholla", " Select Mauza or Moholla." );
			error=true;
		}
		if(nomineeDTO.getAddress().getVillageId().equalsIgnoreCase("")){
			addFieldError( "sMsg_nomineeVillage", " Select Village." );
			error=true;
		}
		/*
		if(nomineeDTO.getAddress().getPostCode().equalsIgnoreCase("")){
			addFieldError( "sMsg_nomineePostCode", " Provide Post Code." );
			error=true;
		}
		
		if(nomineeDTO.getAddress().getPostOffice().equalsIgnoreCase("")){
			addFieldError( "sMsg_nomineePostOffice", " Provide Post Office." );
			error=true;
		}
		*/
		if(nomineeDTO.getNomineePhoneOrMobile()==null || nomineeDTO.getNomineePhoneOrMobile().trim().equalsIgnoreCase("")){
			addFieldError( "sMsg_nomineePhone", " Provide Mobile or Phone Number." );
			error=true;
		}
		if(nomineeDTO.getContact1Name().trim().equalsIgnoreCase("") || nomineeDTO.getContact1Mobile().trim().equalsIgnoreCase("") || nomineeDTO.getContact1Relation().trim().equalsIgnoreCase("")){
			addFieldError( "sMsg_nomineeContactPerson1", " Provide all Information for Contact 1" );
			error=true;
		}
		if(nomineeDTO.getContact2Name().trim().equalsIgnoreCase("") || nomineeDTO.getContact2Mobile().trim().equalsIgnoreCase("") || nomineeDTO.getContact2Relation().trim().equalsIgnoreCase("")){
			addFieldError( "sMsg_nomineeContactPerson2", " Provide all Information for Contact 2" );
			error=true;
		}
		if(nomineeDTO.getContact3Name().trim().equalsIgnoreCase("") || nomineeDTO.getContact3Mobile().trim().equalsIgnoreCase("") || nomineeDTO.getContact3Relation().trim().equalsIgnoreCase("")){
			addFieldError( "sMsg_nomineeContactPerson3", " Provide all Information for Contact 3" );
			error=true;
		}
		
		if(educationDTO.getHeighestDegreeId()==0){
			addFieldError( "sMsg_heighestDegree", " Select Degree Name." );
			error=true;
		}
		if(educationDTO.getHeighestDegreeId()!=0 && educationDTO.getHeighestDegreeId()!=7 &&
		    educationDTO.getLastInstitute().equalsIgnoreCase("")){
			addFieldError( "sMsg_lastInstitute", " Provide Institute Name." );
			error=true;
		}
		if(educationDTO.getHeighestDegreeId()!=0 && educationDTO.getHeighestDegreeId()!=7 &&
			educationDTO.getPassingYear().equalsIgnoreCase("")){
			addFieldError( "sMsg_passingYear", " Provide Passing Year." );
			error=true;
		}
		
		
		if(pAddress.getDivisionId().equalsIgnoreCase("")||pAddress.getDistrictId().equalsIgnoreCase("")||
		   pAddress.getUpazillaOrThanaId().equalsIgnoreCase("")||pAddress.getUnionOrWardId().equalsIgnoreCase("")||
		   pAddress.getMauzaOrMohollaId().equalsIgnoreCase("")|| pAddress.getVillageId().equalsIgnoreCase("")
		   
		   ){
		
			addFieldError( "sMsg_permanentAddress", " Correct Permanent Address." );
			error=true;	
		 }
		if(mAddress.getDivisionId().equalsIgnoreCase("")||mAddress.getDistrictId().equalsIgnoreCase("")||
		   mAddress.getUpazillaOrThanaId().equalsIgnoreCase("")||mAddress.getUnionOrWardId().equalsIgnoreCase("")||
		   mAddress.getMauzaOrMohollaId().equalsIgnoreCase("")|| mAddress.getVillageId().equalsIgnoreCase("") 
		   
		   ){
				
		   addFieldError( "sMsg_mailingAddress", " Correct Mailing Address." );
		   error=true;	
		 }
		if(jobPreference.trim().equalsIgnoreCase("")){
			addFieldError( "sMsg_expJobPreference", " Select at least one job." );
			error=true;
		}
		if(languages.trim().equalsIgnoreCase("")){
			addFieldError( "sMsg_language", " Select at least one language." );
			error=true;
		}
		
		if(personalDTO.getEmpDisabilityYN().equalsIgnoreCase("Y") && personalDTO.getEmpDisabilityDetail().trim().equalsIgnoreCase("")){
			addFieldError( "sMsg_disabilityDetail", " Provide disablility detail." );
			error=true;
		}
		if(educationDTO.getHeighestDegreeId()!=7)
		{
			if(educationDTO.getLastInstitute()==null || educationDTO.getLastInstitute().trim().equalsIgnoreCase(""))
			{
				addFieldError( "sMsg_lastInstitute", " Provide Institute Name." );
				error=true;
			}
		}
		if(educationDTO.getHeighestDegreeId()!=7)
		{
			if(educationDTO.getPassingYear()==null || educationDTO.getPassingYear().trim().equalsIgnoreCase(""))
			{
				addFieldError( "sMsg_passingYear", " Select Passing year." );
				error=true;
			}
		}
		
		if(personalDTO.getPassportIssueDate()!=null && personalDTO.getPassportExpDate()!=null
		&& !personalDTO.getPassportIssueDate().trim().equalsIgnoreCase("") && !personalDTO.getPassportExpDate().trim().equalsIgnoreCase(""))
		{		
			try{
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		    	Date issueDate = sdf.parse(personalDTO.getPassportIssueDate());
		    	Date expDate = sdf.parse(personalDTO.getPassportExpDate());
		
		
		    	if(issueDate.compareTo(expDate)>0){
		    		addFieldError( "sMsg_passportExpDate", " Exp. date is Smaller than the Issue date." );
		    		error=true;
		    	}
			}catch(ParseException ex){
	    		ex.printStackTrace();
	    		addFieldError( "sMsg_passportExpDate", " Incorrect Issue or Exp. Date." );
	    		error=true;
	    	}	
		}
		
		if(!personalDTO.getEmpMaritalStatus().equalsIgnoreCase("Single") && personalDTO.getEmpChildYN().equalsIgnoreCase("Y"))
		{
			if(personalDTO.getEmpSonCount()==null || personalDTO.getEmpSonCount().equalsIgnoreCase(""))
			{
				addFieldError( "sMsg_maritalStatus", " Provide Total Son." );
				error=true;
			}
			if(personalDTO.getEmpDaughterCount()==null || personalDTO.getEmpDaughterCount().equalsIgnoreCase(""))
			{
				addFieldError( "sMsg_maritalStatus", " Provide Total Daughter." );
				error=true;
			}
			if(personalDTO.getEmpSonCount()!=null && personalDTO.getEmpDaughterCount()!=null
			   &&
			   !personalDTO.getEmpSonCount().equalsIgnoreCase("") && !personalDTO.getEmpDaughterCount().equalsIgnoreCase("")
			   )
			{
				try{
				if(Integer.parseInt(personalDTO.getEmpSonCount())==0 && Integer.parseInt(personalDTO.getEmpDaughterCount())==0)
				{
					addFieldError( "sMsg_maritalStatus", " Total Son and Daughter can't be zero." );
					error=true;
				}
				}
				catch(Exception ex)
				{
					addFieldError( "sMsg_maritalStatus", " Invalid Son or Daughter Number Found." );
					error=true;
				}
			}
		}
		
		//error=true;
		//addFieldError( "sMsg_mailingAddress", " Correct Mailing Address." );
		if(error==true){
			ServletActionContext.getRequest().getSession().setAttribute("form_error", "form_error");
			return;
		}
		
		/*---------------- End of Server Side Validation ----------------------*/
		

		if(personalDTO.getEmpChildYN().equalsIgnoreCase("N")){
			personalDTO.setEmpSonCount(null);
			personalDTO.setEmpDaughterCount(null);
		}
		if(personalDTO.getEmpMaritalStatus().equalsIgnoreCase("Single")){
			personalDTO.setEmpSonCount(null);
			personalDTO.setEmpDaughterCount(null);
			personalDTO.setEmpSpouseName(null);
		}
		if(personalDTO.getEmpDisabilityYN().equalsIgnoreCase("N"))
			personalDTO.setEmpDisabilityDetail(null);
		if(educationDTO.getHeighestDegreeId()==7){
			educationDTO.setLastInstitute(null);
			educationDTO.setPassingYear(null);
		}
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



	public String getAllRows() {
		return allRows;
	}



	public void setAllRows(String allRows) {
		this.allRows = allRows;
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
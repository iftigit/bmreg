package org.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import oracle.jdbc.driver.OracleCallableStatement;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import org.table.AddressDTO;
import org.table.EducationDTO;
import org.table.ExperienceDTO;
import org.table.JobPreferenceDTO;
import org.table.LanguageDTO;
import org.table.LogDTO;
import org.table.NomineeDTO;
import org.table.PersonalInfoDTO;
import org.table.SelectPersonDTO;
import org.table.TrainingDTO;

import util.connection.ConnectionManager;


public class RegistrationDAO {
	
	
	 public String insertEmpRegistrationInfo(String registrationId,
			 								 PersonalInfoDTO personalDTO,
			 								 NomineeDTO nomineeDTO,
			 								 EducationDTO educationDTO, 
			 								 LogDTO  logInfoDTO,
			 								 ArrayList<ExperienceDTO> experienceList,
			 								 ArrayList<JobPreferenceDTO> jobPreferenceList,
			 								 ArrayList<LanguageDTO> languageList,
			 								 ArrayList<TrainingDTO> trainingList,			 								 
			 								 String userid,String userType		 							 			 
			 								 ) 
	    {	     
			 	String response="";
			 	Connection conn = ConnectionManager.getConnection();
			 	OracleCallableStatement stmt=null;
			 
				Integer[] country = new Integer[experienceList.size()];
				Integer[] jobCategory = new Integer[experienceList.size()];
				Integer[] subJobCategory = new Integer[experienceList.size()];
				Integer[] subSubJobCategory = new Integer[experienceList.size()];
				Float[] expYear = new Float[experienceList.size()];
				Integer[] expType = new Integer[experienceList.size()];
				
				String[] language = new String[languageList.size()];
				String[] oral = new String[languageList.size()];
				String[] writing = new String[languageList.size()];
				
				Integer[] preferredJobCategory = new Integer[jobPreferenceList.size()];
				Integer[] preferredSubJobCategory = new Integer[jobPreferenceList.size()];
				Integer[] preferredSubSubJobCategory = new Integer[jobPreferenceList.size()];				
				
				String[] trainingName = new String[trainingList.size()];
				String[] trainingFrom = new String[trainingList.size()];
				String[] trainingDuration = new String[trainingList.size()];
				String[] trainingDesc = new String[trainingList.size()];
				
				
				
				for (int i = 0; i < experienceList.size(); i++)
				{
					ExperienceDTO _expDTO = (ExperienceDTO) experienceList.get(i);
					
					country[i] 	  		=  _expDTO.getCountryId();
					jobCategory[i]  	=  _expDTO.getJobCategoryId();
					subJobCategory[i] 	=  _expDTO.getJobSubCategoryId();
					subSubJobCategory[i]=  _expDTO.getJobSubSubCategoryId();
					expYear[i] 		  	=  _expDTO.getTotalYears();
					expType[i] 		  	=  _expDTO.getExpType();
				}
				

				for (int i = 0; i < languageList.size(); i++)
				{
					LanguageDTO _lngDTO = (LanguageDTO) languageList.get(i);
					
					language[i] =  _lngDTO.getLanguage();
					oral[i]  	=  _lngDTO.getOralSkill();
					writing[i] 	=  _lngDTO.getWritingSkill();
				}

				for (int i = 0; i < jobPreferenceList.size(); i++)
				{
					JobPreferenceDTO _jPDTO = (JobPreferenceDTO) jobPreferenceList.get(i);
					
					preferredJobCategory[i] 	   	  =  _jPDTO.getCategoryId();
					preferredSubJobCategory[i]        =  _jPDTO.getSubCategoryId();
					preferredSubSubJobCategory[i] 	  =  _jPDTO.getSubSubCategoryId();
				}
				
				for (int i = 0; i < trainingList.size(); i++)
				{
					TrainingDTO  _trainDTO = (TrainingDTO) trainingList.get(i);
					
					trainingName[i] 	=  _trainDTO.getTrainingName().trim();
					trainingFrom[i]     =  _trainDTO.getFromWhere().trim();
					trainingDuration[i] =  _trainDTO.getDuration().trim();
					trainingDesc[i]     =  _trainDTO.getDescription().trim();
				}
			 
			    try
				  {
			    	
			    	ArrayDescriptor arrNumber = new ArrayDescriptor("NUMBERARRAY", conn);
			    	ArrayDescriptor arrString = new ArrayDescriptor("VARCHARARRAY", conn);
					
					ARRAY inputCountry=new ARRAY(arrNumber,conn,country);
					ARRAY inputJobCat=new ARRAY(arrNumber,conn,jobCategory);
					ARRAY inputJobSubCat=new ARRAY(arrNumber,conn,subJobCategory);
					ARRAY inputJobSubSubCat=new ARRAY(arrNumber,conn,subSubJobCategory);
					ARRAY inputExpYear=new ARRAY(arrNumber,conn,expYear);
					ARRAY inputExpType=new ARRAY(arrNumber,conn,expType);
					
					ARRAY inputlanguage=new ARRAY(arrString,conn,language);
					ARRAY inputoral=new ARRAY(arrString,conn,oral);
					ARRAY inputwriting=new ARRAY(arrString,conn,writing);

					ARRAY inputPreferredJobCat=new ARRAY(arrNumber,conn,preferredJobCategory);
					ARRAY inputPreferredJobSubCat=new ARRAY(arrNumber,conn,preferredSubJobCategory);
					ARRAY inputPreferredJobSubSubCat=new ARRAY(arrNumber,conn,preferredSubSubJobCategory);

					ARRAY inputTrainingName=new ARRAY(arrString,conn,trainingName);
					ARRAY inputTrainingFrom=new ARRAY(arrString,conn,trainingFrom);
					ARRAY inputTrainingDuration=new ARRAY(arrString,conn,trainingDuration);
					ARRAY inputTrainingDesc=new ARRAY(arrString,conn,trainingDesc);
					
				
					System.out.println("Procedure Insert_RegInfo Begins");
					 stmt = (OracleCallableStatement) conn.prepareCall(
							 	  "{ call Insert_RegistrationInfo(?,?,?,?,?,?,?,?,?,?," +
					                                    "?,?,?,?,?,?,?,?,?,?, " +
					                                    "?,?,?,?,?,?,?,?,?,?, " +
					                                    "?,?,?,?,?,?,?,?,?,?, " +
					                                    "?,?,?,?,?,?,?,?,?,?, " +
					                                    "?,?,?,?,?,?,?,?,?,?, " +
					                                    "?,?,?,?,?,?,?,?,?,?, " +
					                                    "?,?,?,?,?,?,?,?,?,?, " +
					                                    "?,?,?,?,?,?,?,?,?,?, " +
					                                    "?,?,?,?,?,?,?,?,?) }");
					 

				 		stmt.setString(1,  registrationId);
						stmt.setString(2,  personalDTO.getEmpGivenName());
						stmt.setString(3,  personalDTO.getEmpLastName());
						stmt.setString(4,  personalDTO.getEmpFatherName().trim());
						stmt.setString(5,  personalDTO.getEmpMotherName().trim());
						stmt.setString(6,  personalDTO.getEmpBirthDate());
						stmt.setString(7,  personalDTO.getEmpBirthDistrict());
						stmt.setString(8,  personalDTO.getEmpBirthUpazilaOrThana());
						stmt.setString(9,  personalDTO.getEmpGender());
						stmt.setString(10, personalDTO.getEmpReligion());
						stmt.setString(11, personalDTO.getEmpMaritalStatus());
						stmt.setString(12, personalDTO.getEmpChildYN());
						stmt.setString(13, personalDTO.getEmpSonCount());
						stmt.setString(14, personalDTO.getEmpDaughterCount());
						stmt.setString(15, personalDTO.getEmpSpouseName());
						stmt.setString(16, personalDTO.getEmpMobileNumber().trim());
						stmt.setString(17, personalDTO.getEmpHeightFeet());
						stmt.setString(18, personalDTO.getEmpHeightInches());
						//stmt.setString(19, personalDTO.getEmpHeightCM());
						stmt.setString(19, personalDTO.getEmpHeightCM()); 
						
						stmt.setString(20, personalDTO.getEmpWeight());
						stmt.setString(21, personalDTO.getEmpBloodGroup());
						stmt.setString(22, personalDTO.getEmpDisabilityYN());
						stmt.setString(23, personalDTO.getEmpDisabilityDetail().trim());
						stmt.setString(24, personalDTO.getNationalId());
						stmt.setString(25, personalDTO.getBirthRegId());
						stmt.setString(26, personalDTO.getPassportNo());
						stmt.setString(27, personalDTO.getOldPassportNo());
						stmt.setString(28, personalDTO.getPassportIssueDate());
						stmt.setString(29, personalDTO.getPassportExpDate());
						stmt.setString(30, personalDTO.getCountryPreferenceString());
						
						stmt.setString(31, personalDTO.getPermanentAddress().getDivisionId());
						stmt.setString(32, personalDTO.getPermanentAddress().getDistrictId());
						stmt.setString(33, personalDTO.getPermanentAddress().getUpazillaOrThanaId());
						stmt.setString(34, personalDTO.getPermanentAddress().getUnionOrWardId());
						stmt.setString(35, personalDTO.getPermanentAddress().getMauzaOrMohollaId());
						stmt.setString(36, personalDTO.getPermanentAddress().getVillageId());
						stmt.setString(37, personalDTO.getPermanentAddress().getPostOffice());
						stmt.setString(38, personalDTO.getPermanentAddress().getPostCode());
						stmt.setString(39, personalDTO.getPermanentAddress().getRoadNumber());
						stmt.setString(40, personalDTO.getPermanentAddress().getHouseHoldNumber());
						
						stmt.setString(41, personalDTO.getMailingAddress().getDivisionId());
						stmt.setString(42, personalDTO.getMailingAddress().getDistrictId());
						stmt.setString(43, personalDTO.getMailingAddress().getUpazillaOrThanaId());
						stmt.setString(44, personalDTO.getMailingAddress().getUnionOrWardId());
						stmt.setString(45, personalDTO.getMailingAddress().getMauzaOrMohollaId());
						stmt.setString(46, personalDTO.getMailingAddress().getVillageId());
						stmt.setString(47, personalDTO.getMailingAddress().getPostOffice());
						stmt.setString(48, personalDTO.getMailingAddress().getPostCode());
						stmt.setString(49, personalDTO.getMailingAddress().getRoadNumber());
						stmt.setString(50, personalDTO.getMailingAddress().getHouseHoldNumber());
						
						
						stmt.setInt(51, educationDTO.getHeighestDegreeId());
						stmt.setString(52, educationDTO.getLastInstitute().trim());
						stmt.setString(53, educationDTO.getPassingYear());						
						
						stmt.setString(54, nomineeDTO.getNomineeName().trim());
						stmt.setString(55, nomineeDTO.getNomineeRelation());
						stmt.setString(56, nomineeDTO.getNomineeFatherName().trim());
						stmt.setString(57, nomineeDTO.getNomineeMotherName().trim());
						stmt.setString(58, nomineeDTO.getNomineePhoneOrMobile().trim());
						stmt.setString(59, nomineeDTO.getContact1Name().trim());						
						stmt.setString(60, nomineeDTO.getContact1Mobile().trim());
						stmt.setString(61, nomineeDTO.getContact1Relation().trim());
						stmt.setString(62, nomineeDTO.getContact2Name().trim());
						stmt.setString(63, nomineeDTO.getContact2Mobile().trim());
						stmt.setString(64, nomineeDTO.getContact2Relation().trim());
						stmt.setString(65, nomineeDTO.getContact3Name().trim());
						stmt.setString(66, nomineeDTO.getContact3Mobile().trim());
						stmt.setString(67, nomineeDTO.getContact3Relation().trim());
						
						stmt.setString(68, nomineeDTO.getAddress().getDivisionId());
						stmt.setString(69, nomineeDTO.getAddress().getDistrictId());
						stmt.setString(70, nomineeDTO.getAddress().getUpazillaOrThanaId());
						stmt.setString(71, nomineeDTO.getAddress().getUnionOrWardId());
						stmt.setString(72, nomineeDTO.getAddress().getMauzaOrMohollaId());
						stmt.setString(73, nomineeDTO.getAddress().getVillageId());
						stmt.setString(74, nomineeDTO.getAddress().getPostOffice());
						stmt.setString(75, nomineeDTO.getAddress().getPostCode());
						stmt.setString(76, nomineeDTO.getAddress().getRoadNumber());
						stmt.setString(77, nomineeDTO.getAddress().getHouseHoldNumber());
						
						stmt.setARRAY(78, inputCountry);
						stmt.setARRAY(79, inputJobCat);
						stmt.setARRAY(80, inputJobSubCat);
						stmt.setARRAY(81, inputJobSubSubCat);
						stmt.setARRAY(82, inputExpYear);
						stmt.setARRAY(83, inputExpType);
						
						stmt.setARRAY(84, inputlanguage);
						stmt.setARRAY(85, inputoral);
						stmt.setARRAY(86, inputwriting);
						
						stmt.setARRAY(87, inputPreferredJobCat);
						stmt.setARRAY(88, inputPreferredJobSubCat);
						stmt.setARRAY(89, inputPreferredJobSubSubCat);
						
						stmt.setARRAY(90, inputTrainingName);
						stmt.setARRAY(91, inputTrainingFrom);
						stmt.setARRAY(92, inputTrainingDuration);
						stmt.setARRAY(93, inputTrainingDesc);
						
						
						stmt.setString(94, logInfoDTO.getxForward()); 
						stmt.setString(95, logInfoDTO.getVia());
						stmt.setString(96, logInfoDTO.getRemoteAddress());

						stmt.setString(97, userType);
						stmt.setString(98, userid);
						
						stmt.registerOutParameter(99, java.sql.Types.VARCHAR);
						stmt.executeUpdate();
						response = (stmt.getString(99)).trim();
						System.out.println("Response : " + response);
						}
					    catch (Exception e){e.printStackTrace();return response;}
				 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
							{e.printStackTrace();}stmt = null;conn = null;}
			 	
				 		return response;	           
	    }
	 
	 public PersonalInfoDTO getPersonalInformation(String registrationId)
	 {
		 	Connection conn = ConnectionManager.getConnection();
		   String sql = "  select tmp3.*, dist_name birth_dist_name from  " +
		   				"  (Select tmp2.*,dist_name pdistrict_name " +
		   				"  from " +
		   				"  (Select tmp1.*,villname villageName from " +
		   				"  (  " +
		   				" Select EMP_PERSONAL.jobseekerid,(GIVEN_NAME||' '||LAST_NAME) fullName,given_name,last_name,FATHER_NAME,MOTHER_NAME,MOBILE, " +
		   				" to_char(BIRTH_DATE,'dd-mm-YYYY') birthDate,GENDER,NATIONALID,BIRTHREGID,PPOST_OFFICE,PPOST_CODE,PROAD_NUMBER,PVILLAGE,PDISTRICT,BIRTH_DISTRICT, " +
		   				" to_char(sysdate,'dd-mm-YYYY HH:MI:SS') printedOn,to_char(REG_DATE,'dd-mm-YYYY HH24:MI:SS') applicationDateTime,REMOTE_ADDRESS " +
		   				" from EMP_PERSONAL,EMP_REG_LOG,EMP_ADDRESS Where EMP_PERSONAL.jobseekerid=?   AND  EMP_PERSONAL.jobseekerid=EMP_REG_LOG.jobseekerid " +
		   				" AND    EMP_PERSONAL.jobseekerid=EMP_ADDRESS.jobseekerid " +
		   				" )tmp1 left outer join village  " +
		   				" on tmp1.PVILLAGE=village.VILLID " +
		   				" )tmp2 left outer join district " +
		   				" on tmp2.PDISTRICT=district.DIST_ID " +
		   				" )tmp3 left outer join district " +
		   				" on tmp3.BIRTH_DISTRICT=district.DIST_ID   "; 
		     
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   PersonalInfoDTO personalDto  = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, registrationId);
				r = stmt.executeQuery();
				if (r.next())
				{
					String gender=r.getString("GENDER");
					if(gender!=null)
					{
						if(gender.equalsIgnoreCase("M"))
							gender="Male";
						else if(gender.equalsIgnoreCase("F"))
							gender="Female";
					}
					personalDto=new PersonalInfoDTO();
					personalDto.setEmpFullName(r.getString("fullName"));
					personalDto.setEmpFatherName(r.getString("FATHER_NAME"));
					personalDto.setEmpMotherName(r.getString("MOTHER_NAME"));
					personalDto.setJobseekerNumber(r.getString("jobseekerid"));
					personalDto.setPrintDateTime(r.getString("printedOn"));
					personalDto.setRegDateTime(r.getString("applicationDateTime"));
					personalDto.setEmpMobileNumber(r.getString("MOBILE"));
					
					personalDto.setEmpGivenName(r.getString("GIVEN_NAME"));
					personalDto.setEmpLastName(r.getString("LAST_NAME"));
					personalDto.setNationalId(r.getString("NATIONALID"));
					personalDto.setBirthRegId(r.getString("BIRTHREGID"));
					personalDto.setEmpGender(gender);
					personalDto.setEmpBirthDate(r.getString("birthDate"));
					personalDto.setEmpBirthDistrictName(r.getString("birth_dist_name"));
					
					AddressDTO addDto=new AddressDTO();
					addDto.setDistrictName(r.getString("pdistrict_name"));
					addDto.setPostCode(r.getString("PPOST_CODE"));
					addDto.setPostOffice(r.getString("PPOST_OFFICE"));
					addDto.setVillageName(r.getString("villageName"));
					addDto.setRoadNumber(r.getString("PROAD_NUMBER"));
					personalDto.setIpAddress(r.getString("REMOTE_ADDRESS"));
					
					personalDto.setPermanentAddress(addDto);
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 		return personalDto;
	 }
	 
	 /*
	 public String updateEmpRegistrationInfo(PersonalInfoDTO personalDTO,AddressDTO addressDTO,String userId) 
			{	     
				String response="error";
				TransactionManager transactionManager=new TransactionManager();
				Connection conn = transactionManager.getConnection();
				PreparedStatement stmt = null;
				int totalUpdate=0;
				
				String personalInfoSql="Update JOBSEEKER Set FIRSTNAME=?, " +
					"MIDDLENAME=?, " +
					"LASTNAME=?, " +
					"FATHERNAME=?, " +
					"MOTHERNAME=?, " +
					"SPOUSNAME=?, " +
					"NATIONALID=?, " +
					"BIRTHDATE=to_date(?,'DD-MM-YYYY'), " +
					"AGE=?, " +
					"RELIGION=?, " +
					"MARITALSTATUS=?, " +
					"SEX=?, " +
					"WEIGHT=?, " +
					"PASSPORTNO=?, " +
					"PASSPORT_ISSUE_DATE=to_date(?,'DD-MM-YYYY'), " +
					"PASSPORT_EXP_DATE=to_date(?,'DD-MM-YYYY'), " +
					"NOMINEE_NAME=?, " +
					"NOMINEE_RELATION=?, " +
					"NOMINEE_ADDRESS=?, " +
					"NOMINEE_PHONE=?, " +
					"CONTACT_NAME=?, " +
					"CONTACT_PHONE=?, " +
					"JOB1=?, " +
					"JOBCAT2=?, " +
					"JOBSUBCAT2=?, " +
					"HEIGHT_FEET=?, " +
					"HEIGHT_INCHES=? " +
					"Where JOBSEEKER_NUMBER=? ";
				
			  String addressInfoSql="Update ADDRESS Set MAIL_DIV=?, " +
				  "MAIL_DIS=?, " +
				  "MAIL_THANA=?, " +
				  "MAIL_UNION=?, " +
				  "MAIL_POST_OFFICE=?, " +
				  "MAIL_POST_CODE=?, " +
				  "MAIL_ADDRESS_LINE1=?, " +
				  "MAIL_ADDRESS_LINE2=?, " +
				  "MAIL_EMAIL=?, " +
				  "MAIL_MOBILE=?,  " +
				  
				  "PER_POST_OFFICE=?, " +
				  "PER_POST_CODE=?, " +
				  "PER_ADDRESS_LINE1=?, " +
				  "PER_ADDRESS_LINE2=?, " +
				  "PER_EMAIL=?, " +
				  "PER_MOBILE=?  " +
				  
				  "Where JOBSEEKER_NUMBER=? ";
			  
			  String sqlEdit = " Insert Into EDIT_LOG(JOBSEEKER_NUMBER,USERID,UPDATE_DATE) VALUES(?,?,SYSDATE)";
			  
				try
				{
					stmt = conn.prepareStatement(personalInfoSql);

					stmt.setString(1,personalDTO.getEmpFname());
					stmt.setString(2,personalDTO.getEmpMname());
					stmt.setString(3,personalDTO.getEmpLname());					
					stmt.setString(4,personalDTO.getFatherName());
					stmt.setString(5,personalDTO.getMotherName());
					stmt.setString(6,personalDTO.getSpousName());
					stmt.setString(7,personalDTO.getNationalId());
					stmt.setString(8,personalDTO.getBirthDate());
					stmt.setString(9,personalDTO.getAge());
					stmt.setString(10,personalDTO.getReligion());
					stmt.setString(11,personalDTO.getMaritalStatus());
					stmt.setString(12,personalDTO.getSex());
					stmt.setString(13,personalDTO.getWeight());
					stmt.setString(14,personalDTO.getPassportNo());
					stmt.setString(15,personalDTO.getPassportIssueDate());
					stmt.setString(16,personalDTO.getPassportExpireDate());
					stmt.setString(17,personalDTO.getNomineeName());
					stmt.setString(18,personalDTO.getNomineeRelation());
					stmt.setString(19,personalDTO.getNomineeAddress());
					stmt.setString(20,personalDTO.getNomineePhone());
					//stmt.setString(21,personalDTO.getContactName());
					//stmt.setString(22,personalDTO.getContactMobile());
					stmt.setString(23,personalDTO.getDesiredJob1());
					stmt.setString(24,personalDTO.getDesiredJobCat1());
					stmt.setString(25,personalDTO.getDesiredJobSubcategory1());
					stmt.setString(26,personalDTO.getHeightFeet());
					stmt.setString(27,personalDTO.getHeightInches());
					stmt.setString(28,personalDTO.getMaritalStatus());
					stmt.setString(29,personalDTO.getJobseekerNumber());
						
					totalUpdate+=stmt.executeUpdate();
					
					stmt = conn.prepareStatement(addressInfoSql);
					
					stmt.setString(1,addressDTO.getmDivision());
					stmt.setString(2,addressDTO.getmDistrict());
					stmt.setString(3,addressDTO.getmThana());
					stmt.setString(4,addressDTO.getMUnion());
					stmt.setString(5,addressDTO.getmPost());
					stmt.setString(6,addressDTO.getmPostCode());
					stmt.setString(7,addressDTO.getmAddressLine1());
					stmt.setString(8,addressDTO.getmAddressLine2());
					stmt.setString(9,addressDTO.getmEmail());
					stmt.setString(10,addressDTO.getpMobile());
					
					stmt.setString(11,addressDTO.getpPost());
					stmt.setString(12,addressDTO.getpPostCode());
					stmt.setString(13,addressDTO.getpAddressLine1());
					stmt.setString(14,addressDTO.getpAddressLine2());
					stmt.setString(15,addressDTO.getpEmail());
					stmt.setString(16,addressDTO.getpMobile());
					
					stmt.setString(17,personalDTO.getJobseekerNumber());
					
					totalUpdate+=stmt.executeUpdate();
					
					
					stmt = conn.prepareStatement(sqlEdit);
					stmt.setString(1, personalDTO.getJobseekerNumber());
					stmt.setString(2, userId);
					totalUpdate+=stmt.executeUpdate();
					
					
					transactionManager.commit();
					
					
					
					if(totalUpdate==3)
						response="success";
					else
						response="noupdate";
				} 

				catch (Exception e){e.printStackTrace();
				try {
					transactionManager.rollback();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
 		finally{try{stmt.close();transactionManager.close();} catch (Exception e)
			{e.printStackTrace();}stmt = null;conn = null;}
 		
				
				return response;	           
		}
	 
	 public PersonalInfoDTO getPersonalInformation(String registrationId)
	 {
		 	Connection conn = ConnectionManager.getConnection();
		   String sql = " select jobseeker.jobseeker_number,(firstname||' '||middlename|| ' '||lastname) fullname,fathername,mothername, " +
		   				" to_char(sysdate,'dd-mm-YYYY HH:MI:SS') printedOn,REAL_IP, " +
		   				" to_char(APPLICATION_DATETIME,'dd-mm-YYYY HH24:MI:SS') applicationDateTime,UNIONNAME,PER_MOBILE from JOBSEEKER,UNIONS,ADDRESS " +
		   				" where jobseeker.jobseeker_number=? AND UNIONS.UNIONID=COTA_UNION AND JOBSEEKER.JOBSEEKER_NUMBER=ADDRESS.JOBSEEKER_NUMBER";
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   PersonalInfoDTO personalDto  = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, registrationId);
				r = stmt.executeQuery();
				if (r.next())
				{
					personalDto=new PersonalInfoDTO();
					personalDto.setEmpFullName(r.getString("FULLNAME"));
					personalDto.setFatherName(r.getString("FATHERNAME"));
					personalDto.setMotherName(r.getString("MOTHERNAME"));
					personalDto.setRegId(r.getString("jobseeker_number"));
					personalDto.setPrintedOn(r.getString("printedOn"));
					personalDto.setApplicationDateTime(r.getString("applicationDateTime"));
					personalDto.setIpAddress(r.getString("REAL_IP"));
					personalDto.setQuotaUnionName(r.getString("UNIONNAME"));
					personalDto.setContactMobileNumber(r.getString("PER_MOBILE"));
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 		return personalDto;
	 }
	 
	 public PersonalInfoDTO getAllPersonalInformation(String registrationId)
	 {
		 	Connection conn = ConnectionManager.getConnection();
		   String sql = " select jobseeker.jobseeker_number, jobseeker.firstname, jobseeker.middlename, jobseeker.lastname, " +
		   		        " jobseeker.fathername, jobseeker.mothername,SPOUSNAME,NATIONALID, " +
		   		        " TO_CHAR(BIRTHDATE,'DD-MM-YYYY') BDATE,AGE,RELIGION,MARITALSTATUS,SEX,WEIGHT,PASSPORTNO, " +
		   		        " TO_CHAR(PASSPORT_ISSUE_DATE,'DD-MM-YYYY') PASS_ISSUE_DATE,TO_CHAR(PASSPORT_EXP_DATE,'DD-MM-YYYY') PASS_EXP_DATE, " +
		   		        " NOMINEE_NAME,NOMINEE_RELATION,NOMINEE_ADDRESS,NOMINEE_PHONE,CONTACT_NAME,CONTACT_PHONE,HEIGHT_FEET,HEIGHT_INCHES,  " +
		   		        " JOB1,JOBCAT2,JOBSUBCAT2 from JOBSEEKER,SECONDLOTTERY_T1 " +
		   				" where jobseeker.jobseeker_number=? and jobseeker.jobseeker_number= SECONDLOTTERY_T1.jobseeker_number";
		   
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   PersonalInfoDTO personalDto  = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, registrationId);
				r = stmt.executeQuery();
				if (r.next())
				{
					personalDto=new PersonalInfoDTO();
					personalDto.setJobseekerNumber(r.getString("JOBSEEKER_NUMBER"));
					personalDto.setEmpFname(r.getString("FIRSTNAME"));
					personalDto.setEmpMname(r.getString("MIDDLENAME"));
					personalDto.setEmpLname(r.getString("LASTNAME"));
					personalDto.setFatherName(r.getString("FATHERNAME"));
					personalDto.setMotherName(r.getString("MOTHERNAME"));
					personalDto.setSpousName(r.getString("SPOUSNAME"));
					personalDto.setNationalId(r.getString("NATIONALID"));
					personalDto.setBirthDate(r.getString("BDATE"));
					personalDto.setAge(r.getString("AGE"));
					personalDto.setReligion(r.getString("RELIGION"));
					personalDto.setMaritalStatus(r.getString("MARITALSTATUS"));
					personalDto.setSex(r.getString("SEX"));
					personalDto.setWeight(r.getString("WEIGHT"));
					personalDto.setPassportNo(r.getString("PASSPORTNO"));
					personalDto.setPassportIssueDate(r.getString("PASS_ISSUE_DATE"));
					personalDto.setPassportExpireDate(r.getString("PASS_EXP_DATE"));
					personalDto.setNomineeName(r.getString("NOMINEE_NAME"));
					personalDto.setNomineeRelation(r.getString("NOMINEE_RELATION"));
					personalDto.setNomineeAddress(r.getString("NOMINEE_ADDRESS"));
					personalDto.setNomineePhone(r.getString("NOMINEE_PHONE"));
					//personalDto.setContactName(r.getString("CONTACT_NAME"));					
					//personalDto.setContactMobile(r.getString("CONTACT_PHONE"));
					personalDto.setHeightFeet(r.getString("HEIGHT_FEET"));
					personalDto.setHeightInches(r.getString("HEIGHT_INCHES"));
					
					personalDto.setDesiredJob1(r.getString("JOB1"));
					personalDto.setDesiredJobCat1(r.getString("JOBCAT2"));
					personalDto.setDesiredJobSubcategory1(r.getString("JOBSUBCAT2"));
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 		return personalDto;
	 }
	 
	 
	 
	 
	 public SelectPersonDTO getSelectPersonal(String registrationId)
	 {
		   Connection conn = ConnectionManager.getConnection();
		   String sql="SELECT sl.jobseeker_number, " +
		   		"       (sl.firstname || ' ' || sl.middlename || ' ' || sl.lastname " +
		   		"       ) fullname, sl.fathername, sl.mothername, ad.per_mobile, " +
		   		"       TO_CHAR (sl.idate, 'dd-mm-YYYY') idate, mt.ttc_name, mt.address_line1, " +
		   		"       mt.address_line2, mt.address_line3 " +
		   		"  FROM secondlottery_t1 sl, address ad, mst_ttc mt " +
		   		" WHERE sl.jobseeker_number = ad.jobseeker_number " +
		   		"   AND sl.ttc_id = mt.ttc_id " +
		   		"   AND sl.jobseeker_number = ? ";
	   
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   SelectPersonDTO personalDto  = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, registrationId);
				r = stmt.executeQuery();
				if (r.next())
				{
					personalDto=new SelectPersonDTO();
					personalDto.setEmpFullName(r.getString("FULLNAME"));
					personalDto.setFatherName(r.getString("FATHERNAME"));
					personalDto.setMotherName(r.getString("MOTHERNAME"));
					personalDto.setRegId(r.getString("jobseeker_number"));
					personalDto.setMobileNo(r.getString("PER_MOBILE"));
					personalDto.setIDate(r.getString("IDATE"));
					personalDto.setTtcNmae(r.getString("TTC_NAME"));
					personalDto.setAddressLine1(r.getString("address_Line1"));
					personalDto.setAddressLine2(r.getString("address_Line2"));
					personalDto.setAddressLine3(r.getString("address_Line3"));
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 		return personalDto;
	 }
	 
	 
	 public byte [] getImage(String registrationId)
	 {
		 Connection conn = ConnectionManager.getConnection();
		 byte [] image= null;
		 String sql ="select IMAGE from  bio_image where JOBSEEKER_NUMBER = ? ";
		 PreparedStatement stmt = null;
		   ResultSet r = null;
		   
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, registrationId);
				r = stmt.executeQuery();
				if (r.next())
				{
					image=r.getBytes("IMAGE");
				}
				
			}catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
			{e.printStackTrace();}stmt = null;conn = null;}
		 
		 return image;
	 }
	 
	 
	 public PoliceDTO getPoliceData(String registrationId)
	 {
		   Connection conn = ConnectionManager.getConnection();
		   String sql="SELECT  " +
		   "	JOBSEEKER_NUMBER, NAME, GENDER,  " +
		   "   FATHERNAME, PASSPORTNO, RACE,  " +
		   "   RELIGION, BIRTHDATE, initcap(ADDRESS) ADDRESS,  " +
		   "   ADDRESS1, ADDRESS2, ADDRESS3,  " +
		   "   BIRTHPLACE, TTCNAME, TTCDATE " +
		   "	FROM DB_BMG2G.POLICE_DATA  " +
		   "	where JOBSEEKER_NUMBER = ? ";
	   
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   PoliceDTO pDto  = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, registrationId);
				r = stmt.executeQuery();
				if (r.next())
				{
					pDto=new PoliceDTO();
					pDto.setRegno(r.getString("JOBSEEKER_NUMBER"));
					pDto.setName(r.getString("NAME"));
					pDto.setGender(r.getString("GENDER"));
					pDto.setFathername(r.getString("FATHERNAME"));
					pDto.setPasportno(r.getString("PASSPORTNO"));
					pDto.setRace(r.getString("RACE"));
					pDto.setReligion(r.getString("RELIGION"));
					pDto.setBirthdate(r.getString("BIRTHDATE"));
					pDto.setAddress(r.getString("ADDRESS"));
					pDto.setAddress1(r.getString("ADDRESS1"));
					pDto.setAddress2(r.getString("ADDRESS2"));
					pDto.setAddress3(r.getString("BIRTHPLACE"));
					pDto.setBirthplace(r.getString("BIRTHPLACE"));
					pDto.setTtcname(r.getString("TTCNAME"));
					pDto.setTtcdate(r.getString("TTCDATE"));					
					
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 		return pDto;
	 }
	 
	 
	 
	 public ArrayList<PoliceDTO> getPoliceDataAll(String registrationId)
	 {
		   Connection conn = ConnectionManager.getConnection();
		   ArrayList<PoliceDTO> plist = new ArrayList<PoliceDTO>();
		   
		   String sql="SELECT  " +
		   "	JOBSEEKER_NUMBER, NAME, GENDER,  " +
		   "   FATHERNAME, PASSPORTNO, RACE,  " +
		   "   RELIGION, BIRTHDATE, initcap(ADDRESS) ADDRESS,  " +
		   "   ADDRESS1, ADDRESS2, ADDRESS3,  " +
		   "   BIRTHPLACE, TTCNAME, TTCDATE " +
		   "	FROM DB_BMG2G.POLICE_DATA  " +
		   "	where dist_id = ? ";
	   
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   PoliceDTO pDto  = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, registrationId);
				r = stmt.executeQuery();
				while (r.next())				
				{
					pDto=new PoliceDTO();
					pDto.setRegno(r.getString("JOBSEEKER_NUMBER"));
					pDto.setName(r.getString("NAME"));
					pDto.setGender(r.getString("GENDER"));
					pDto.setFathername(r.getString("FATHERNAME"));
					pDto.setPasportno(r.getString("PASSPORTNO"));
					pDto.setRace(r.getString("RACE"));
					pDto.setReligion(r.getString("RELIGION"));
					pDto.setBirthdate(r.getString("BIRTHDATE"));
					pDto.setAddress(r.getString("ADDRESS"));
					pDto.setAddress1(r.getString("ADDRESS1"));
					pDto.setAddress2(r.getString("ADDRESS2"));
					pDto.setAddress3(r.getString("BIRTHPLACE"));
					pDto.setBirthplace(r.getString("BIRTHPLACE"));
					pDto.setTtcname(r.getString("TTCNAME"));
					pDto.setTtcdate(r.getString("TTCDATE"));
					
					plist.add(pDto);
					
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 		return plist;
	 }
	 
	 
	 public int updateInterviewInforamtion(String registrationId,int ttcId,String interviewDate)
	 {
		 	Connection conn = ConnectionManager.getConnection();
		   String sql = " Update JOBSEEKER Set TTC_ID=?,INTVIEW_DATE=to_date(?,'dd-MM-YYYY') Where JOBSEEKER_NUMBER=?";
		   PreparedStatement stmt = null;
		   int response=0;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, ttcId);
				stmt.setString(2, interviewDate);
				stmt.setString(3, registrationId);
				response = stmt.executeUpdate();
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 		return response;
	 }
	 
	 public ArrayList<JobseekerDTO> getAllRegisteredJobseeker(String operatorId)
	 {
		 Connection conn = ConnectionManager.getConnection();
		 ArrayList<JobseekerDTO> jobSeekerList=new ArrayList<JobseekerDTO>();
		   String sql = " Select JOBSEEKER.JOBSEEKER_NUMBER,FIRSTNAME,MIDDLENAME,LASTNAME,FATHERNAME,MOTHERNAME,DIVISION.DIVISION_NAME, " +
		   		" DISTRICT.DIST_NAME,THANA.THANA_NAME,UNIONS.UNIONNAME from jobseeker,ADDRESS,DIVISION,DISTRICT,THANA,UNIONS Where " +
		   		"  OP_USERID=? " +
		   		" And JOBSEEKER.JOBSEEKER_NUMBER=ADDRESS.JOBSEEKER_NUMBER " +
		   		" And ADDRESS.PER_DIV=DIVISION.DIVISIONID " +
		   		"  And ADDRESS.PER_DIS=DISTRICT.DIST_ID " +
		   		"  And ADDRESS.PER_THANA=THANA.THANAID " +
		   		"  And ADDRESS.PER_UNION=UNIONS.UNIONID";
		   
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   JobseekerDTO jobseeker  = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, operatorId);
				r = stmt.executeQuery();
				while (r.next())
				{
					jobseeker=new JobseekerDTO();
					jobseeker.setRegId(r.getString("JOBSEEKER_NUMBER"));
					jobseeker.setName(r.getString("FIRSTNAME")+" "+r.getString("MIDDLENAME")+" "+r.getString("LASTNAME"));
					jobseeker.setFatherName(r.getString("FATHERNAME"));
					jobseeker.setMotherName(r.getString("MOTHERNAME"));
					jobseeker.setpDivisionName(r.getString("DIVISION_NAME"));
					jobseeker.setpDistrictName(r.getString("DIST_NAME"));
					jobseeker.setpThanaName(r.getString("THANA_NAME"));
					jobseeker.setpUnionName(r.getString("UNIONNAME"));
					jobSeekerList.add(jobseeker);
					
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 		return jobSeekerList;
	 }
*/
	 
	 public int getNomineeContactPhoneCount(String phoneNumber)
	 {
		   Connection conn = ConnectionManager.getConnection();
		   String sql="select count(*) total from EMP_NOMINEE Where contact1_mobile=? or contact2_mobile=? or contact3_mobile=?";
	   
		   PreparedStatement stmt = null;
		   ResultSet r = null;

		   int count=0;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, phoneNumber);
				stmt.setString(2, phoneNumber);
				stmt.setString(3, phoneNumber);
				r = stmt.executeQuery();
				if (r.next())
				{
					count=r.getInt("total");
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 		return count;
	 }
	 
	 public int getNationalIdCount(String nationalId)
	 {
		   Connection conn = ConnectionManager.getConnection();
		   String sql="select count(*) total from EMP_PERSONAL Where NATIONALID=?";
	   
		   PreparedStatement stmt = null;
		   ResultSet r = null;

		   int count=0;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, nationalId);
				r = stmt.executeQuery();
				if (r.next())
				{
					count=r.getInt("total");
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 		return count;
	 }
	 
	 public int getBirthRegIdCount(String birthRegId)
	 {
		   Connection conn = ConnectionManager.getConnection();
		   String sql="select count(*) total from EMP_PERSONAL Where BIRTHREGID=?";
	   
		   PreparedStatement stmt = null;
		   ResultSet r = null;

		   int count=0;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, birthRegId);
				r = stmt.executeQuery();
				if (r.next())
				{
					count=r.getInt("total");
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 		return count;
	 }
	 

}

package org.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.table.GeneralInfoDTO;
import org.table.InterviewInfoDTO;

import util.connection.ConnectionManager;

public class InterviewDAO {
	
	
	public GeneralInfoDTO getJobSeekerGeneralInfo(String jobSeekerNumber)
	{
		 	Connection conn = ConnectionManager.getConnection();
		   String sql = "Select ST.*,DIVISION.DIVISION_NAME,DISTRICT.DIST_NAME,THANA.THANA_NAME,UNIONS.UNIONNAME,MST_TTC.TTC_NAME,TO_CHAR(ST.IDATE,'DD-MM-YYYY') INTERVIEW_DATE,TRIM(FIRSTNAME||' '||MIDDLENAME||' '||LASTNAME) FULLNAME from SECONDLOTTERY_T1 ST,DIVISION,DISTRICT,THANA,UNIONS,MST_TTC " +
		   "Where ST.DIV=DIVISION.DIVISIONID " +
		   "And   ST.DIST=DISTRICT.DIST_ID " +
		   "And   ST.THANA=THANA.THANAID " +
		   "And   ST.UNIONS=UNIONS.UNIONID " +
		   "And   ST.TTC_ID=MST_TTC.TTC_ID " +
		   "And   JOBSEEKER_NUMBER=?";

		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   GeneralInfoDTO generalDto  = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, jobSeekerNumber);
				r = stmt.executeQuery();
				if (r.next())
				{
					generalDto=new GeneralInfoDTO();
					generalDto.setJobseekerNumber(r.getString("JOBSEEKER_NUMBER"));					
					generalDto.setJobseekerName(r.getString("FULLNAME"));
					generalDto.setFatherName(r.getString("FATHERNAME"));
					generalDto.setMotherName(r.getString("MOTHERNAME"));
					generalDto.setPerDivision(r.getString("DIVISION_NAME"));
					generalDto.setPerDistrict(r.getString("DIST_NAME"));
					generalDto.setPerUpazilla(r.getString("THANA_NAME"));
					generalDto.setPerUnion(r.getString("UNIONNAME"));
					
					generalDto.setTtcId(r.getString("TTC_ID"));
					generalDto.setTtcName(r.getString("TTC_NAME"));
					generalDto.setInterviewDate(r.getString("INTERVIEW_DATE"));
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 		return generalDto;
	 }
	 
	
	public GeneralInfoDTO getMedicalInfo(String jobSeekerNumber)
	{
		 	Connection conn = ConnectionManager.getConnection();
		   String sql = "Select ST.*,DIVISION.DIVISION_NAME,DISTRICT.DIST_NAME,THANA.THANA_NAME,UNIONS.UNIONNAME,MST_TTC.TTC_NAME, " +
		   "TO_CHAR(ST.IDATE,'DD-MM-YYYY') INTERVIEW_DATE,TRIM(FIRSTNAME||' '||MIDDLENAME||' '||LASTNAME) FULLNAME ,STI.MEDICAL_RESULT,STI.MEDICAL_COMMENTS " +
		   "from SECONDLOTTERY_T1 ST,DIVISION,DISTRICT,THANA,UNIONS,MST_TTC,SECONDLOTTERY_T1_INTERVIEW STI " +
		   "Where ST.DIV=DIVISION.DIVISIONID   " +
		   "And   ST.DIST=DISTRICT.DIST_ID  " +
		   "And   ST.THANA=THANA.THANAID  " +
		   "And   ST.UNIONS=UNIONS.UNIONID  " +
		   "And   ST.TTC_ID=MST_TTC.TTC_ID  " +
		   "AND   ST.JOBSEEKER_NUMBER=STI.JOBSEEKER_NUMBER " +
		   "And   ST.JOBSEEKER_NUMBER=? ";


		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   GeneralInfoDTO generalDto  = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, jobSeekerNumber);
				r = stmt.executeQuery();
				if (r.next())
				{
					generalDto=new GeneralInfoDTO();
					generalDto.setJobseekerNumber(r.getString("JOBSEEKER_NUMBER"));					
					generalDto.setJobseekerName(r.getString("FULLNAME"));
					generalDto.setFatherName(r.getString("FATHERNAME"));
					generalDto.setMotherName(r.getString("MOTHERNAME"));
					generalDto.setPerDivision(r.getString("DIVISION_NAME"));
					generalDto.setPerDistrict(r.getString("DIST_NAME"));
					generalDto.setPerUpazilla(r.getString("THANA_NAME"));
					generalDto.setPerUnion(r.getString("UNIONNAME"));
					
					generalDto.setTtcId(r.getString("TTC_ID"));
					generalDto.setTtcName(r.getString("TTC_NAME"));
					generalDto.setInterviewDate(r.getString("INTERVIEW_DATE"));
					
					generalDto.setMedicalResult(r.getString("MEDICAL_RESULT")==null?"":r.getString("MEDICAL_RESULT"));
					generalDto.setMedicalComments(r.getString("MEDICAL_COMMENTS")==null?"":r.getString("MEDICAL_COMMENTS"));
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 		return generalDto;
	 }
	
	public boolean updateMedicalInfo(String jobSeekerNumber,String result,String comments)
	{
		  Connection conn = ConnectionManager.getConnection();
		   String sql = "Update SECONDLOTTERY_T1_INTERVIEW Set MEDICAL_RESULT=?, MEDICAL_COMMENTS=? WHERE JOBSEEKER_NUMBER=?";
		   


		   PreparedStatement stmt = null;
		   int r=0;
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, result);
				stmt.setString(2, comments);
				stmt.setString(3, jobSeekerNumber);
				r = stmt.executeUpdate();
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	if(r==1)
	 		return true;
	 	else
	 		return false;
	 	
	 }

	public InterviewInfoDTO getGeneralInfoForInterview(String jobSeekerNumber)
	{
		   Connection conn = ConnectionManager.getConnection();
		   String sql = "Select lt.*,J.*,adr.*,div.*,dist.*,thana.*,un.*,TO_CHAR(BIRTHDATE,'DD-MM-YYYY') bdate,VIVA_STATUS from " +
		   "SECONDLOTTERY_T1 lt,JOBSEEKER j,ADDRESS adr,DIVISION div,DISTRICT dist,THANA thana,UNIONS un " +
		   "Where j.JOBSEEKER_NUMBER=adr.JOBSEEKER_NUMBER " +
		   "And   j.JOBSEEKER_NUMBER=lt.JOBSEEKER_NUMBER " +
		   "And   j.JOBSEEKER_NUMBER=? " +
		   "And   lt.DIV=div.DIVISIONID " +
		   "And   lt.DIST=dist.DIST_ID " +
		   "And   lt.THANA=thana.THANAID " +
		   "And   lt.UNIONS=un.UNIONID ";


		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   InterviewInfoDTO interviewDTO  = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, jobSeekerNumber);
				r = stmt.executeQuery();
				if (r.next())
				{
					interviewDTO=new InterviewInfoDTO();
					interviewDTO.setJobSeekerNumber(r.getString("JOBSEEKER_NUMBER"));					
					interviewDTO.setJobSeekerName(r.getString("FIRSTNAME")==null?"":r.getString("FIRSTNAME")+" "+r.getString("MIDDLENAME")==null?"":r.getString("MIDDLENAME")+" "+r.getString("LASTNAME")==null?"":r.getString("LASTNAME"));
					interviewDTO.setFatherName(r.getString("FATHERNAME"));
					interviewDTO.setMotherName(r.getString("MOTHERNAME"));
					interviewDTO.setBirthDate(r.getString("bdate"));
					interviewDTO.setAge(r.getString("AGE"));
					interviewDTO.setGender(r.getString("SEX"));
					interviewDTO.setWeight(r.getString("WEIGHT"));
					
					interviewDTO.setHeight(r.getString("HEIGHT_FEET")+" Feet "+r.getString("HEIGHT_INCHES")+" Inches");
					interviewDTO.setPerDivision(r.getString("DIVISION_NAME"));
					interviewDTO.setPerDistrict(r.getString("DIST_NAME"));
					
					interviewDTO.setPerUpazilla(r.getString("THANA_NAME"));
					interviewDTO.setPerUnion(r.getString("UNIONNAME"));
					interviewDTO.setPerStreet(r.getString("PER_ADDRESS_LINE1"));
					
					interviewDTO.setPerPostCode(r.getString("PER_POST_CODE"));
					interviewDTO.setPerPostOffice(r.getString("PER_POST_OFFICE"));
					
					/*
					interviewDTO.setNationality(r.getString("ISNATIONALITYOK"));
					interviewDTO.setWillingness(r.getString("WILLINGNESS"));
					interviewDTO.setAgeOk(r.getString("ISAGEOK"));
					interviewDTO.setVillagePeople(r.getString("VILLAGE_PEOPLE"));
					interviewDTO.setWeightLifting(r.getString("WEIGHT_LIFTING"));
					interviewDTO.setIsHeightOk(r.getString("ISHEIGHTOK"));
					interviewDTO.setIsWeightOk(r.getString("ISWEIGHTOK"));
					interviewDTO.setSelected(r.getString("SELECTED"));
					interviewDTO.setCommets(r.getString("COMMENTS"));
					*/
					
					interviewDTO.setVivaStatus(r.getString("VIVA_STATUS")==null?"":r.getString("VIVA_STATUS"));
					
					}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 		return interviewDTO;
	 }
	
	public boolean saveVivaStatus(InterviewInfoDTO interviewDTO)
	{
		  Connection conn = ConnectionManager.getConnection();
		   String sql = "Update SECONDLOTTERY_T1 Set VIVA_STATUS=? WHERE JOBSEEKER_NUMBER=?";
		   


		   PreparedStatement stmt = null;
		   int r=0;
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, interviewDTO.getVivaStatus());
				stmt.setString(2, interviewDTO.getJobSeekerNumber());
				r = stmt.executeUpdate();
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	if(r==1)
	 		return true;
	 	else
	 		return false;
	 	
	 }
}

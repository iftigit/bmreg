package org.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.table.JobseekerDTO;
import org.table.LotteryDTO;
import org.table.LotteryStatusDTO;
import org.table.SelectedEmpDTO;
import org.table.SelectionParamDTO;

import oracle.jdbc.driver.OracleCallableStatement;

import util.connection.ConnectionManager;

public class LotteryDAO {


	public ArrayList<SelectionParamDTO> getSelectionList(String agentId,String workOrder){
		
		ArrayList<SelectionParamDTO> selectionList=null;
		Connection conn = ConnectionManager.getConnection();
		String sql = "Select * from SELECTION_CRITERIA,AGENT_LICENCE Where SELECTION_CRITERIA.Agent_Id=AGENT_LICENCE.Agent_Id and  SELECTION_CRITERIA.Agent_Id=? and Work_Order like ?  order by Selection_Date desc";
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, agentId);
			stmt.setString(2, "%"+workOrder+"%");
			r = stmt.executeQuery();
			int count=0;
			SelectionParamDTO selection=null;
			while (r.next())
			{
				if(count==0)
					selectionList=new ArrayList<SelectionParamDTO>();
				
				selection=new SelectionParamDTO();
				
				selection.setSelectionId(r.getInt("SELECTION_ID"));
				selection.setAgentId(r.getString("AGENT_ID"));
				selection.setAgentCompanyName(r.getString("COMPANY_NAME"));
				selection.setWorkOrder(r.getString("WORK_ORDER"));
				selection.setGender(r.getString("GENDER"));
				selection.setYearOfExperience(r.getString("EXPERIENCE_YEAR"));
				selection.setSelectionDate(r.getString("SELECTION_DATE"));
				selection.setExpireDate(r.getString("EXPIRE_DATE"));
				selection.setStatus(r.getString("STATUS"));
				selection.setLanguages(r.getString("LANGUAGE"));
				
				selectionList.add(selection);
				count++;
				
			}
		} 
		catch (Exception e){e.printStackTrace();}
 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
			{e.printStackTrace();}stmt = null;conn = null;}
 		
		
		return selectionList;
	}
	public ArrayList<SelectedEmpDTO> getSelectionDetail(int selectionId){
		
		ArrayList<SelectedEmpDTO> jobseekerList=null;
		Connection conn = ConnectionManager.getConnection();
		String sql = " Select tmp1.*, " +
					 " Division.DIVISION_NAME M_DIVISION_NAME,District.DIST_NAME M_DIST_NAME,Thana.THANA_NAME M_THANA_NAME,Unions.UNIONNAME M_UNIONNAME,Mauza.MAUZANAME M_MAUZANAME, " +
					 " Village.VILLNAME M_VILLNAME,address.MPOST_OFFICE ,address.MPOST_CODE,address.MROAD_NUMBER,address.MHOUSEHOLD_NUMBER   " +
					 " From " +
					 " ( " +
					 " Select personal.JOBSEEKERID,GIVEN_NAME,LAST_NAME,FATHER_NAME,TO_CHAR(BIRTH_DATE,'DD-MM-YYYY') BIRTH_DATE,GENDER,MARITAL_STATUS,MOBILE,SELECTED_YN, " +
					 " Division.DIVISION_NAME P_DIVISION_NAME,District.DIST_NAME P_DIST_NAME,Thana.THANA_NAME P_THANA_NAME,Unions.UNIONNAME P_UNIONNAME,Mauza.MAUZANAME P_MAUZANAME, " +
					 " Village.VILLNAME P_VILLNAME,address.PPOST_OFFICE ,address.PPOST_CODE,address.PROAD_NUMBER,address.PHOUSEHOLD_NUMBER " +
					 " From SELECTION_LOG log,EMP_PERSONAL personal, EMP_ADDRESS address,Division,District,Thana,Unions,Mauza,Village " +
					 " Where log.JOBSEEKERID=personal.JOBSEEKERID " +
					 " And log.JOBSEEKERID=address.JOBSEEKERID " +
					 " And address.pdivision=division.DIVISIONID " +
					 " And address.PDISTRICT=district.DIST_ID " +
					 " And address.PUPAZILA_OR_THANA=Thana.THANAID " +
					 " And address.PUNION_OR_WARD=Unions.UNIONID " +
					 " And address.PMAUZA_OR_MOHOLLA=Mauza.MAUZAID " +
					 " And address.PVILLAGE=Village.VILLID  " +
					 " And log.SELECTION_ID=? " +
					 " )tmp1,EMP_ADDRESS address,Division,District,Thana,Unions,Mauza,Village " +
					 " Where " +
					 " tmp1.JOBSEEKERID=address.JOBSEEKERID " +
					 " And address.pdivision=division.DIVISIONID " +
					 " And address.PDISTRICT=district.DIST_ID " +
					 " And address.PUPAZILA_OR_THANA=Thana.THANAID " +
					 " And address.PUNION_OR_WARD=Unions.UNIONID " +
					 " And address.PMAUZA_OR_MOHOLLA=Mauza.MAUZAID " +
					 " And address.PVILLAGE=Village.VILLID Order by GIVEN_NAME";
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, selectionId);
			r = stmt.executeQuery();
			int count=0;
			SelectedEmpDTO emp=null;
			while (r.next())
			{
				if(count==0)
					jobseekerList=new ArrayList<SelectedEmpDTO>();
				
				emp=new SelectedEmpDTO();
				emp.setJobseekerId(r.getString("JOBSEEKERID"));
				emp.setGivenName(r.getString("GIVEN_NAME"));
				emp.setLastName(r.getString("LAST_NAME"));
				emp.setFatherName(r.getString("FATHER_NAME"));
				emp.setBirthDate(r.getString("BIRTH_DATE"));
				emp.setGender(r.getString("GENDER"));
				emp.setMaritalStatus(r.getString("MARITAL_STATUS"));
				emp.setMobileNumber(r.getString("MOBILE"));
				emp.setSelectedYN(r.getString("SELECTED_YN"));
				emp.setpDivisionName(r.getString("P_DIVISION_NAME"));
				emp.setpDistrictName(r.getString("P_DIST_NAME"));
				emp.setpThanaName(r.getString("P_THANA_NAME"));
				emp.setpUnionName(r.getString("P_UNIONNAME"));
				emp.setpMauzaName(r.getString("P_MAUZANAME"));
				emp.setpVillageName(r.getString("P_VILLNAME"));
				emp.setpPostCode(r.getString("PPOST_CODE"));
				emp.setpPostOffice(r.getString("PPOST_OFFICE"));
				emp.setpRoadNumber(r.getString("PROAD_NUMBER"));
				emp.setpHouseholdNumber(r.getString("PHOUSEHOLD_NUMBER"));
				
				emp.setmDivisionName(r.getString("M_DIVISION_NAME"));
				emp.setmDistrictName(r.getString("M_DIST_NAME"));
				emp.setmThanaName(r.getString("M_THANA_NAME"));
				emp.setmUnionName(r.getString("M_UNIONNAME"));
				emp.setmMauzaName(r.getString("M_MAUZANAME"));
				emp.setmVillageName(r.getString("M_VILLNAME"));
				emp.setmPostCode(r.getString("MPOST_CODE"));
				emp.setmPostOffice(r.getString("MPOST_OFFICE"));
				emp.setmRoadNumber(r.getString("MROAD_NUMBER"));
				emp.setmHouseholdNumber(r.getString("MHOUSEHOLD_NUMBER"));
				
				jobseekerList.add(emp);
				count++;
				
			}
		} 
		catch (Exception e){e.printStackTrace();}
 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
			{e.printStackTrace();}stmt = null;conn = null;}
 		
		
		return jobseekerList;
	}
	public SelectionParamDTO getSelectionCriteria(int selectionId){
		

		Connection conn = ConnectionManager.getConnection();
		String sql = "Select * from SELECTION_CRITERIA,AGENT_LICENCE  where SELECTION_CRITERIA.Agent_Id=AGENT_LICENCE.Agent_Id AND Selection_Id=?";
		PreparedStatement stmt = null;
		ResultSet r = null;
		SelectionParamDTO selection=null;
		try
		{
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, selectionId);			
			r = stmt.executeQuery();
			
			if (r.next())
			{
				
				selection=new SelectionParamDTO();
				
				selection.setSelectionId(r.getInt("SELECTION_ID"));
				selection.setAgentId(r.getString("AGENT_ID"));
				selection.setAgentCompanyName(RADAO.getCompanyNameFromAgentId(r.getString("AGENT_ID")));
				selection.setWorkOrder(r.getString("WORK_ORDER"));
				selection.setGender(r.getString("GENDER"));
				selection.setYearOfExperience(r.getString("EXPERIENCE_YEAR"));
				selection.setSelectionDate(r.getString("SELECTION_DATE"));
				selection.setExpireDate(r.getString("EXPIRE_DATE"));
				selection.setStatus(r.getString("STATUS"));
				selection.setLanguages(r.getString("LANGUAGE"));
				
				
			}
		} 
		catch (Exception e){e.printStackTrace();}
 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
			{e.printStackTrace();}stmt = null;conn = null;}
 		
		
		return selection;
	}
	public boolean saveJobseekerSelection(int selectionId,String[] jobseekerList)
	{
		   	
		   
	 	  Connection conn = ConnectionManager.getConnection();
	 	  
	 	  String sql = "Update SELECTION_LOG Set SELECTED_YN='N' where Selection_Id=?";
		   PreparedStatement stmt = null;
		   
			try
			{
				conn.setAutoCommit(false);
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, selectionId);
				int a=stmt.executeUpdate();
				Statement stmt1 = conn.createStatement();
				for(int i=0;i<jobseekerList.length;i++){
					sql = "Update SELECTION_LOG Set SELECTED_YN='Y' where Selection_Id="+selectionId+" and JOBSEEKERID='"+jobseekerList[i]+"'";
					stmt1.addBatch(sql);
				}
				int b[]=stmt1.executeBatch();
				conn.commit();
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return false;

	}
	public static synchronized int jobseekerSelection(SelectionParamDTO selection)
	{
		 int responseCode=0;
		 Connection conn = ConnectionManager.getConnection();
		 OracleCallableStatement stmt=null;
		 String[] jobPrefArr=selection.getJobPreference().split("@");
		 String   jobPreQueryStr="";
		 String   selectValStr="";
		 
		 String[] jobExpArr=selection.getJobExperience().split("@");
		 String   jobExpQueryStr="";
		 
		 String tableJobPref="";
		 String tableJobExp="";
		 int totalYearExp=Integer.parseInt(selection.getYearOfExperience()==null||selection.getYearOfExperience().equalsIgnoreCase("")?"0":selection.getYearOfExperience());
		 String experienceYearQuery="";
		 
		 String languageQueryString="";
		 String countryQueryString="";
		 String tableLanguage="";
		 String genderQueryString="";
		 if(selection.getLanguages()!=null && !selection.getLanguages().equalsIgnoreCase("")){
			 tableLanguage=" ,EMP_LANGUAGE lang ";
			 languageQueryString=" And lang.LANGUAGE in ('"+selection.getLanguages().replaceAll(", ", "','")+"') "+
								 " And per.JOBSEEKERID=lang.JOBSEEKER_ID ";
		 }
		 if(selection.getCountryPreference()!=null && !selection.getCountryPreference().equalsIgnoreCase("")){
			 countryQueryString=" And (per.PREFERRED_COUNTRIES like ('"+selection.getCountryPreference().replaceAll(", ", "|")+"%')  or per.PREFERRED_COUNTRIES like ('%|"+selection.getCountryPreference().replaceAll(", ", "|")+"|%') or per.PREFERRED_COUNTRIES like ('%|"+selection.getCountryPreference().replaceAll(", ", "|")+"')) ";
		 }
		 if(!selection.getGender().equalsIgnoreCase("A"))
			 genderQueryString=" And per.GENDER='"+selection.getGender()+"' ";
		 
		 if(selection.getJobPreference()!=null && !selection.getJobPreference().equalsIgnoreCase("") && jobPrefArr.length>0 && Integer.parseInt(jobPrefArr[0].split("#")[0])>0){
			 for(int i=0;i<jobPrefArr.length;i++){
				 String[] jobPrefInd=jobPrefArr[i].split("#");
				 selectValStr="";
				 	for(int j=0;j<jobPrefInd.length;j++){
				 		selectValStr+=jobPrefInd[j]+",";
				 	}
				 	if(selectValStr.length()>0)
				 		selectValStr=selectValStr.substring(0, selectValStr.length()-1);
				 	
				 jobPreQueryStr+=" (jobPref.category,jobPref.sub_category,jobPref.sub_sub_category) in (select "+selectValStr+" from dual) or";
			 }
			 if(jobPreQueryStr.length()>0)
			 {
				 jobPreQueryStr=jobPreQueryStr.substring(0, jobPreQueryStr.length()-3);
				 jobPreQueryStr=" And  per.JOBSEEKERID=jobPref.JOBSEEKERID And  ("+jobPreQueryStr+") ";
				 tableJobPref=" ,EMP_JOB_PREFERENCE jobPref ";				 				 
			 }
		 }
		 
		 if(!selection.getJobExperience().equalsIgnoreCase("") && jobExpArr.length>0 && Integer.parseInt(jobExpArr[0].split("#")[0])>0){
			 for(int i=0;i<jobExpArr.length;i++){
				 String[] jobExpInd=jobExpArr[i].split("#");
				 if(jobExpInd.length>2){
					 String[] subSubJobExp=jobExpInd[2].split(",");
					 for(int j=0;j<subSubJobExp.length;j++){
						 selectValStr=jobExpInd[0]+","+jobExpInd[1]+","+subSubJobExp[j];
						 jobExpQueryStr+=" (jobExp.JOB_CATEGORY,jobExp.SUB_JOB_CATEGORY,jobExp.SUB_SUB_JOB_CATEGORY) in (select "+selectValStr+" from dual) or";
						 experienceYearQuery+=" (experience.JOB_CATEGORY,experience.SUB_JOB_CATEGORY,experience.SUB_SUB_JOB_CATEGORY) in (select "+selectValStr+" from dual) or";
					 }
				 }
				 else{
					 selectValStr=jobExpInd[0]+","+jobExpInd[1];
					 jobExpQueryStr+=" (jobExp.JOB_CATEGORY,jobExp.SUB_JOB_CATEGORY,jobExp.SUB_SUB_JOB_CATEGORY) in (select "+selectValStr+" from dual) or";
					 experienceYearQuery+=" (experience.JOB_CATEGORY,experience.SUB_JOB_CATEGORY,experience.SUB_SUB_JOB_CATEGORY) in (select "+selectValStr+" from dual) or";
				 }
			 }
		 }
		 if(jobExpQueryStr.length()>0)
		 {
			 jobExpQueryStr=jobExpQueryStr.substring(0, jobExpQueryStr.length()-3);
			 experienceYearQuery=experienceYearQuery.substring(0, experienceYearQuery.length()-3);
			 jobExpQueryStr=" And  ("+jobExpQueryStr+") ";
			 tableJobExp=" ,EMP_EXPERIENCE jobExp ";
		 }
		 if(totalYearExp>0){
			 
			 experienceYearQuery=" And per.JOBSEEKERID in (Select distinct jobseekerid from ( " +
			 					  " Select JOBSEEKERID,SUM(EXP_YEAR)  FROM EMP_EXPERIENCE  experience " +
			 					  " Where ( " +
			 					  	experienceYearQuery+
			 					  "      ) " +
			 					  "  GROUP BY JOBSEEKERID HAVING SUM(EXP_YEAR)>"+totalYearExp+" ) )";
		 }
		 else
			 experienceYearQuery="";
		 
		 String filterQuery=" Select * from (Select distinct per.JOBSEEKERID  From EMP_PERSONAL per,EMP_ADDRESS addr"+tableLanguage+tableJobPref+tableJobExp+
		 					" Where Assign_Status='N' " +
		 					" And per.JOBSEEKERID=addr.JOBSEEKERID " +
		 					genderQueryString+" "+languageQueryString+" "+countryQueryString+
		 					" " +jobPreQueryStr+" "+jobExpQueryStr+" "+experienceYearQuery +")";
		 					//" " +jobPreQueryStr+" "+jobExpQueryStr+" "+experienceYearQuery +")";
		 System.out.println(filterQuery);
		    try
			  {
			
				System.out.println("Procedure jobseekerSelection Begins");
				 stmt = (OracleCallableStatement) conn.prepareCall(
						 	  "{ call jobseekerSelection(?,?,?,?,?,?,?,?,?,?,?,?) }");
				 

			 		stmt.setString(1,  filterQuery);
				    stmt.setString(2, selection.getAgentId());
				    stmt.setString(3, selection.getWorkOrder());
				    stmt.setString(4, selection.getCountryPreference());
				    stmt.setString(5, selection.getGender());
				    stmt.setString(6, selection.getLanguages());
				    stmt.setString(7, selection.getJobPreference());
				    stmt.setString(8, selection.getJobExperience());
				    stmt.setString(9, selection.getYearOfExperience());
			 		stmt.setInt(10,  Integer.parseInt(selection.getWorkOrderTotal()));
			 		stmt.setInt(11,  Integer.parseInt(selection.getSuggestedTotal()));
					stmt.registerOutParameter(12, java.sql.Types.NUMERIC);
					stmt.executeUpdate();
					responseCode = stmt.getInt(12);
					System.out.println("Response : " + responseCode);
					}
				    catch (Exception e){e.printStackTrace();}
			 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
						{e.printStackTrace();}stmt = null;conn = null;}
		 	
			 		return responseCode;
	}
	
	public int getTotalRegisteredJobseeker(String districtId)
	{
		
		int total=0;
		Connection conn = ConnectionManager.getConnection();
		   String sql = "Select count(*) total from ADDRESS Where per_Dis=?";
		   PreparedStatement stmt = null;
		   ResultSet r = null;

		   try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, districtId);
				r = stmt.executeQuery();
				if (r.next())
				{
					total=r.getInt("total");
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return total;
		
	}
	
	public int getTotalCotaNumber(String districtId)
	{
		
		int total=0;
		Connection conn = ConnectionManager.getConnection();
		   String sql = "select sum(cota_t) total from unions Where thanaid in (Select thanaid from thana where districtid=?)";
		   PreparedStatement stmt = null;
		   ResultSet r = null;

		   try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, districtId);
				r = stmt.executeQuery();
				if (r.next())
				{
					total=r.getInt("total");
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return total;
		
	}
	public int getTotalSelected(String districtId)
	{
		
		int total=0;
		Connection conn = ConnectionManager.getConnection();
		String sql = "select count(*) total from FIRSTLOTTERY Where dist=?";
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, districtId);
			r = stmt.executeQuery();
			if (r.next())
			{
				total=r.getInt("total");
			}
		} 
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return total;
		
	}
	
	public static synchronized String processLottery(String userId)
	{
		 String response="error";
		 Connection conn = ConnectionManager.getConnection();
		 OracleCallableStatement stmt=null;
		 
		    try
			  {
			
				System.out.println("Procedure firstlottery_p Begins");
				 stmt = (OracleCallableStatement) conn.prepareCall(
						 	  "{ call firstlottery_p(?,?) }");
				 

			 		stmt.setString(1,  userId);					
					stmt.registerOutParameter(2, java.sql.Types.VARCHAR);
					stmt.executeUpdate();
					response = (stmt.getString(2)).trim();
					System.out.println("Response : " + response);
					}
				    catch (Exception e){e.printStackTrace();}
			 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
						{e.printStackTrace();}stmt = null;conn = null;}
		 	
			 		return response;
	}
	
	public static synchronized String processDivisionLottery(String divisionId)
	{
		 String response="error";
		 Connection conn = ConnectionManager.getConnection();
		 OracleCallableStatement stmt=null;
		 
		    try
			  {
			
				System.out.println("Procedure SECONDLOTTERY_P1 Begins");
				 stmt = (OracleCallableStatement) conn.prepareCall(
						 	  "{ call SECONDLOTTERY_P1(?,?) }");
				 

			 		stmt.setString(1, divisionId);					
					stmt.registerOutParameter(2, java.sql.Types.VARCHAR);
					stmt.executeUpdate();
					response = (stmt.getString(2)).trim();
					System.out.println("Response : " + response);
					}
				    catch (Exception e){e.printStackTrace();}
			 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
						{e.printStackTrace();}stmt = null;conn = null;}
		 	
			 		return response;
	}
	
	public String getLotteryResult(String districtId)
	{
		   	
		   
	 	   Connection conn = ConnectionManager.getConnection();
	 	  String sql = "SELECT   firstlottery.jobseeker_number, (firstname || ' ' || middlename || ' ' || lastname) jobseekername, " +
		        " fathername, mothername,unions.UNIONNAME " +
		        " FROM firstlottery,address,unions WHERE dist = ? " +
		        " And firstlottery.UNIONS=address.PER_UNION " +
		        " And firstlottery.JOBSEEKER_NUMBER=address.JOBSEEKER_NUMBER " +
		        " And firstlottery.UNIONS=unions.UNIONID ORDER BY unionname";
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   String selectedList="error";
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, districtId);
				r = stmt.executeQuery();
				while (r.next())
				{
					selectedList+=r.getString("JOBSEEKER_NUMBER")+"IICTG2GIFTI"+r.getString("JOBSEEKERNAME").replaceAll("'", "").replaceAll("\"", "")+"IICTG2GIFTI"+r.getString("FATHERNAME").replaceAll("'", "").replaceAll("\"", "")+"IICTG2GIFTI"+r.getString("MOTHERNAME").replaceAll("'", "").replaceAll("\"", "")+"IICTG2GIFTI"+r.getString("UNIONNAME").replaceAll("'", "").replaceAll("\"", "")+"NEWJOBSEEKERG2G";
				}
				
				if(selectedList.length()>0)
					selectedList=selectedList.substring(0, selectedList.length()-15);
			} 
			catch (Exception e){e.printStackTrace();selectedList="error";}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return selectedList;

	}
	
	public String getDivisionLotteryResult(String divisionId)
	{
		   	
		   
	 	   Connection conn = ConnectionManager.getConnection();
	 	   String sql = " SELECT   SECONDLOTTERY_T1.jobseeker_number, (firstname || ' ' || middlename || ' ' || lastname) jobseekername, " +
	 	  		       " fathername, mothername,unions.UNIONNAME " +
	 	  		       " FROM SECONDLOTTERY_T1,address,unions WHERE div=? " +
	 	  		       " And SECONDLOTTERY_T1.UNIONS=address.PER_UNION " +
	 	  		       " And SECONDLOTTERY_T1.JOBSEEKER_NUMBER=address.JOBSEEKER_NUMBER " +
	 	  		       " And SECONDLOTTERY_T1.UNIONS=unions.UNIONID ORDER BY unionname ";
	 	  
	 	   PreparedStatement stmt = null;
		   ResultSet r = null;
		   String selectedList="error";
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, divisionId);
				r = stmt.executeQuery();
				while (r.next())
				{
					selectedList+=r.getString("JOBSEEKER_NUMBER")+"IICTG2GIFTI"+r.getString("JOBSEEKERNAME").replaceAll("'", "").replaceAll("\"", "")+"IICTG2GIFTI"+r.getString("FATHERNAME").replaceAll("'", "").replaceAll("\"", "")+"IICTG2GIFTI"+r.getString("MOTHERNAME").replaceAll("'", "").replaceAll("\"", "")+"IICTG2GIFTI"+r.getString("UNIONNAME").replaceAll("'", "").replaceAll("\"", "")+"NEWJOBSEEKERG2G";
				}
				
				if(selectedList.length()>0)
					selectedList=selectedList.substring(0, selectedList.length()-15);
			} 
			catch (Exception e){e.printStackTrace();selectedList="error";}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return selectedList;

	}
	
	public ArrayList<LotteryDTO> getLotteryArrayList(String districtId)
	{
		   	
			ArrayList<LotteryDTO> lotteryList=new ArrayList<LotteryDTO>();
	 	   Connection conn = ConnectionManager.getConnection();
		   String sql = "SELECT   firstlottery.jobseeker_number, (firstname || ' ' || middlename || ' ' || lastname) jobseekername, " +
		   		        " fathername, mothername,unions.UNIONNAME " +
		   		        " FROM firstlottery,address,unions WHERE dist = ? " +
		   		        " And firstlottery.UNIONS=address.PER_UNION " +
		   		        " And firstlottery.JOBSEEKER_NUMBER=address.JOBSEEKER_NUMBER " +
		   		        " And firstlottery.UNIONS=unions.UNIONID ORDER BY unionname";
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   LotteryDTO lottery=new LotteryDTO();
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, districtId);
				r = stmt.executeQuery();
				while (r.next())
				{
					lottery=new LotteryDTO();
					lottery.setJobseekerName(r.getString("JOBSEEKERNAME"));
					lottery.setFatherName(r.getString("FATHERNAME"));
					lottery.setMotherName(r.getString("MOTHERNAME"));
					lottery.setJobseekerNumber(r.getString("JOBSEEKER_NUMBER"));
					lottery.setUnionName(r.getString("UNIONNAME"));
					lotteryList.add(lottery);
				}
			
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return lotteryList;

	}
	public ArrayList<LotteryDTO> getLotteryArrayListForReport(String districtId)
	{
		   	
		   ArrayList<LotteryDTO> lotteryList=new ArrayList<LotteryDTO>();
	 	   Connection conn = ConnectionManager.getConnection();
	 	  
	 	   
	 	  String sql=" SELECT   firstlottery.jobseeker_number, (firstname || ' ' || middlename || ' ' || lastname) jobseekername,  " +
		      " fathername, mothername,unions.UNIONNAME,unions.unionid,thana.THANA_NAME,unions.cota_t " +
		      " FROM firstlottery,address,unions,thana WHERE dist = ? " +
		      " And firstlottery.UNIONS=address.PER_UNION " +
		      " And firstlottery.JOBSEEKER_NUMBER=address.JOBSEEKER_NUMBER " +
		      " And firstlottery.UNIONS=unions.UNIONID and thana.THANAID=firstlottery.THANA ORDER BY unions, gserial";
	 	  
	 	   
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   LotteryDTO lottery=new LotteryDTO();
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, districtId);
				r = stmt.executeQuery();
				while (r.next())
				{
					lottery=new LotteryDTO();
					lottery.setJobseekerName(r.getString("JOBSEEKERNAME"));
					lottery.setFatherName(r.getString("FATHERNAME"));
					lottery.setMotherName(r.getString("MOTHERNAME"));
					lottery.setJobseekerNumber(r.getString("JOBSEEKER_NUMBER"));
					lottery.setUnionName(r.getString("UNIONNAME"));
					lottery.setUnionId(r.getString("unionid"));
					lottery.setUpazillaName(r.getString("THANA_NAME"));
					lottery.setTotalQuota(r.getString("cota_t"));
					
					lotteryList.add(lottery);
				}
			
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return lotteryList;

	}
	
	
	public ArrayList<LotteryDTO> getP1LotteryArrayListForReport(String divisionId)
	{
		   	
			ArrayList<LotteryDTO> lotteryList=new ArrayList<LotteryDTO>();
	 	   Connection conn = ConnectionManager.getConnection();
	 	   
	 	   /*
	 	   String sql=" SELECT SECONDLOTTERY_T1.jobseeker_number, (firstname || ' ' || middlename || ' ' || lastname) jobseekername," +
	 	   		      " fathername, mothername,unions.UNIONNAME,unions.unionid,DIVISION.DIVISION_NAME,DISTRICT.DIST_NAME,thana.THANA_NAME,UNIONS.UNIONNAME,unions.cota_f " +
	 	   		      " FROM SECONDLOTTERY_T1,unions,thana,district,division WHERE SECONDLOTTERY_T1.DIV = ? " +
	 	   		      " And SECONDLOTTERY_T1.DIV=division.DIVISIONID " +
	 	   		      " And SECONDLOTTERY_T1.DIST=district.DIST_ID " +
	 	   		      " And SECONDLOTTERY_T1.THANA=thana.THANAID " +
	 	   		      " AND SECONDLOTTERY_T1.UNIONS=UNIONS.UNIONID " +
	 	   		      " Order by division_name,dist_name,thana_name,unions.unionname";
	 	   */
	 String sql="SELECT * FROM  " +
	 	 "(SELECT SECONDLOTTERY_T1.jobseeker_number, (firstname || ' ' || middlename || ' ' || lastname) jobseekername, " +
	 	 "fathername, mothername,unions.unionid, " +
	 	 "DIVISION.DIVISION_NAME,DISTRICT.DIST_NAME,thana.THANA_NAME, " +
	 	 "DIVISION.DIVISIONID,DISTRICT.DIST_ID,thana.THANAID, " +
	 	 "UNIONS.UNIONNAME,unions.cota_f  " +
	 	 "FROM SECONDLOTTERY_T1,unions,thana,district,division WHERE SECONDLOTTERY_T1.DIV = ? " +
	 	 "And SECONDLOTTERY_T1.DIV=division.DIVISIONID " +
	 	 "And SECONDLOTTERY_T1.DIST=district.DIST_ID  " +
	 	 "And SECONDLOTTERY_T1.THANA=thana.THANAID  " +
	 	 "AND SECONDLOTTERY_T1.UNIONS=UNIONS.UNIONID  " +
	 	 "Order by division_name,dist_name,thana_name,unions.unionname " +
	 	 ")MAIN_TBL " +
	 	 "left outer join  " +
	 	 "(select divisionid,sum(cota_f) div_cota from  " +
	 	 "( " +
	 	 "select * from district left outer join  " +
	 	 "( " +
	 	 "Select thana.DISTRICTID,cota_f  from thana left outer join " +
	 	 "(SELECT   thanaid, SUM (cota_f) cota_f FROM unions GROUP BY thanaid)tmp1 " +
	 	 "on thana.thanaid=tmp1.thanaid " +
	 	 ")tmp2 " +
	 	 "on district.DIST_ID=tmp2.districtid " +
	 	 ")tmp3 group by divisionid) div_cota_tbl " +
	 	 "on MAIN_TBL.DIVISIONID=div_cota_tbl.divisionid " +
	 	 "LEFT OUTER JOIN (Select districtid,sum(cota_f) dist_cota from  " +
	 	 "( " +
	 	 "Select thana.DISTRICTID,cota_f  from thana left outer join " +
	 	 "(SELECT   thanaid, SUM (cota_f) cota_f FROM unions GROUP BY thanaid)tmp1 " +
	 	 "on thana.thanaid=tmp1.thanaid " +
	 	 ")tmp2 group by districtid) dist_cota_tbl " +
	 	 "on MAIN_TBL.DIST_ID=dist_cota_tbl.districtid " +
	 	 "LEFT OUTER JOIN  " +
	 	 "(SELECT   thanaid, SUM (cota_f) thana_cota FROM unions GROUP BY thanaid) thana_cota_tbl " +
	 	 "on MAIN_TBL.thanaid=thana_cota_tbl.thanaid " +
	 	 "Left OUTER JOIN " +
	 	 "(select div,count(*) selected_div from secondlottery_t1 group by div) div_selected_tbl " +
	 	 "on MAIN_TBL.DIVISIONID= div_selected_tbl.div " +
	 	 "LEFT OUTER JOIN " +
	 	 "(select dist,count(*) selected_dist from secondlottery_t1 group by dist) dist_selected_tbl " +
	 	 "on MAIN_TBL.dist_id= dist_selected_tbl.dist " +
	 	 "LEFT OUTER JOIN " +
	 	 "(select thana,count(*) selected_thana from secondlottery_t1 group by thana) thana_selected_tbl " +
	 	 "on MAIN_TBL.thanaid= thana_selected_tbl.thana order by div,dist,thana,unionid";

	 	   
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   LotteryDTO lottery=new LotteryDTO();
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, divisionId);
				r = stmt.executeQuery();
				while (r.next())
				{
					lottery=new LotteryDTO();
					lottery.setJobseekerName(r.getString("JOBSEEKERNAME"));
					lottery.setFatherName(r.getString("FATHERNAME"));
					lottery.setMotherName(r.getString("MOTHERNAME"));
					lottery.setJobseekerNumber(r.getString("JOBSEEKER_NUMBER"));
					
					lottery.setDivisionId(r.getString("DIVISIONID"));
					lottery.setDivisionName(r.getString("DIVISION_NAME"));
					
					lottery.setDistrictId(r.getString("DISTRICTID"));
					lottery.setDistrictName(r.getString("DIST_NAME"));
					
					lottery.setUpazillaId(r.getString("THANA"));
					lottery.setUpazillaName(r.getString("THANA_NAME"));
					
					lottery.setUnionName(r.getString("UNIONNAME"));
					
					
					lottery.setUpazillaId(r.getString("THANAID"));
					lottery.setUnionId(r.getString("unionid"));					
					lottery.setTotalQuota(r.getString("cota_f"));
					
					lottery.setDivQuota(r.getString("DIV_COTA"));
					lottery.setDivSelected(r.getString("SELECTED_DIV"));
					lottery.setDistQuota(r.getString("DIST_COTA"));
					lottery.setDistSelected(r.getString("SELECTED_DIST"));
					lottery.setUpazillaQuota(r.getString("THANA_COTA"));
					lottery.setUpazillaSelected(r.getString("SELECTED_THANA"));
					
					lotteryList.add(lottery);
				}
			
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return lotteryList;

	}
	public String getLotteryStatus(String userId,String districtId)
	{
		
		String status="";
		Connection conn = ConnectionManager.getConnection();
		   String sql = "select decode(FIRSTLOTTERY,null,'NOTCOMPLETED',FIRSTLOTTERY) STATUS from mst_user where DISTRICT_ID=? and userid=? ";
		   PreparedStatement stmt = null;
		   ResultSet r = null;

		   try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, districtId);
				stmt.setString(2, userId);
				r = stmt.executeQuery();
				if (r.next())
				{
					status=r.getString("STATUS");
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return status;
		
	}
	
	public String getLotteryShortSummaryResult(String divisionId)
	{
		
		DecimalFormat df = new DecimalFormat("#,##,##,###.##");
		Connection conn = ConnectionManager.getConnection();
		   String sql = " Select firstLotTbl.totalSelection,totalRegTbl.totalRegistration,totalCotaTbl.totalQuota from  " +
		   		        " (Select count(*) totalSelection from FirstLottery Where Div=?)firstLotTbl, " +
		   		        " (Select count(*) totalRegistration from Address Where per_div=?)totalRegTbl, " +
		   		        " (select sum(cota_f) totalQuota from unions Where thanaid in " +
		   		        " ( " +
		   		        " select thanaid from thana Where districtid in " +
		   		        " ( " +
		   		        " Select dist_id from district  where divisionid=? " +
		   		        " ) " +
		   		        " ))totalCotaTbl";
		   
		   PreparedStatement stmt = null;
		   ResultSet r = null;

		   double totalReg=0;
		   double totalPriliSelected=0;
		   double totalQuota=0;

		   try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, divisionId);
				stmt.setString(2, divisionId);
				stmt.setString(3, divisionId);
				r = stmt.executeQuery();
				if (r.next())
				{

					totalReg=r.getDouble("totalRegistration");
					totalPriliSelected=r.getDouble("totalSelection");
					totalQuota=r.getDouble("totalQuota");
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 		String response=" <table width='98%' border='0' align='center' style='border:1px solid grey;'> " +
				   " <tr> " +
				   " <td width='70%' bgcolor='#DBB7FF' height='30' style='padding-left:20px;'>Total Registered Job-Seekers</td> " +
				   " <td width='30%' bgcolor='#C58AFF' style='text-align:right; padding-right:10px;font-weight:bold;'>"+df.format(totalReg)+"</td> " +
				   " </tr> " +
				   " <tr> " +
				   " <td height='30' bgcolor='#FFFFBB' style='padding-left:20px;'>Preliminary Selected<br>(out of 36,038)</td> " +
				   " <td bgcolor='#FFFF6C' style='text-align:right; padding-right:10px;font-weight:bold;'>"+df.format(totalPriliSelected)+"</td> " +
				   " </tr> " +
				   " <tr> " +
				   " <td bgcolor='#C4D7D7' height='30' style='padding-left:20px;'>Quota (out of 11,500)</td> " +
				   " <td bgcolor='#9DBDBD' style='text-align:right; padding-right:10px;font-weight:bold;'>"+df.format(totalQuota)+"</td> " +
				   " </tr> " +
				   " </table>";
	 		
	 	return response;
		
	}
	
	public ArrayList<LotteryStatusDTO> getFinalLotteryPhasewiseStatus(int phaseId)
	{
		   	
			ArrayList<LotteryStatusDTO> lotteryStatList=new ArrayList<LotteryStatusDTO>();
	 	   Connection conn = ConnectionManager.getConnection();
	 	   
	 	   String sql=" select DIVISION.DIVISIONID,DIVISION_NAME,STATUS,PHASEID from division left outer join LOTTERY_PHASE_STATUS " +
	 	   		      " on division.divisionid=LOTTERY_PHASE_STATUS.DIVISIONID " +
	 	   		      " And PHASEID=? Order by division.divisionid";
	 	   
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   LotteryStatusDTO lStatusDTO=new LotteryStatusDTO();
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, phaseId);
				r = stmt.executeQuery();
				while (r.next())
				{
					lStatusDTO=new LotteryStatusDTO();
					lStatusDTO.setDivisionId(r.getString("DIVISIONID"));
					lStatusDTO.setDivisionName(r.getString("DIVISION_NAME"));
					lStatusDTO.setStatus(r.getString("STATUS"));
					lStatusDTO.setPhaseId(r.getString("PHASEID"));
					
					lotteryStatList.add(lStatusDTO);
				}
			
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return lotteryStatList;

	}
	
	public String getPendingDivisionListString(ArrayList<LotteryStatusDTO> lotteryStatusList)
	{
		String response="";
		
		for(int i=0;i<lotteryStatusList.size();i++)
		{
			if(lotteryStatusList.get(i).getStatus()==null || !lotteryStatusList.get(i).getStatus().equalsIgnoreCase("completed"))
			{
				response+=lotteryStatusList.get(i).getDivisionId()+",";
			}
		}
		
		if(response.length()>0)
			response=response.substring(0, response.length()-1);
		return response;
	}
	


}

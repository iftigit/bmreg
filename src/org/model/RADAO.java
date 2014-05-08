package org.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.table.CountryDTO;
import org.table.RecruitingAgencyDTO;

import util.connection.ConnectionManager;

public class RADAO {

	public static ArrayList<RecruitingAgencyDTO> getRecruitingAgencyList(String agencyId)
	{
		ArrayList<RecruitingAgencyDTO> agencyList=new ArrayList<RecruitingAgencyDTO>();
		
	 	   Connection conn = ConnectionManager.getConnection();
	 	   String sql="";
	 	   if(agencyId.equalsIgnoreCase("all"))
	 		   sql = "Select AGENT_LICENCE.*,to_char(LICENSEDATE,'dd-MM-YYYY') LICENSEDATE_EXT1,to_char(LICENSE_VALID_UPTO,'dd-MM-YYYY') LICENSE_VALID_UPTO_EXT1 from AGENT_LICENCE order by company_name";
	 	   else
	 		  sql = "Select AGENT_LICENCE.*,to_char(LICENSEDATE,'dd-MM-YYYY') LICENSEDATE_EXT1,to_char(LICENSE_VALID_UPTO,'dd-MM-YYYY') LICENSE_VALID_UPTO_EXT1 from AGENT_LICENCE Where LICENCE_NO='"+agencyId+"'";
	 	   
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   RecruitingAgencyDTO raDTO  = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				r = stmt.executeQuery();
				while (r.next())
				{
					raDTO=new RecruitingAgencyDTO();
					raDTO.setAgentFileRef(r.getString("AGENT_FILE_REF"));
					raDTO.setCompanyName(r.getString("COMPANY_NAME"));
					raDTO.setAddress(r.getString("ADDRESS"));
					raDTO.setPhone(r.getString("PHONE"));
					raDTO.setEmailAddress(r.getString("EMAIL"));
					raDTO.setFax(r.getString("FAX"));
					raDTO.setLicenseNumber(r.getString("LICENCE_NO"));
					raDTO.setSpace(r.getString("SPACE"));
					raDTO.setStatus(r.getString("STATUS"));
					raDTO.setCompanyType(r.getString("COMPANY_TYPE"));
					raDTO.setLicenseDate(r.getString("LICENSEDATE_EXT1"));
					raDTO.setLicenseValidTill(r.getString("LICENSE_VALID_UPTO_EXT1"));
					raDTO.setMinistryRef(r.getString("MINISTRY_REFERENCE"));
					raDTO.setContactPerson(r.getString("CONTACT_PERSON"));
					raDTO.setDesignation(r.getString("DESIGNATION"));
					raDTO.setComments(r.getString("COMMENTS"));
					raDTO.setBranch(r.getString("BRANCH"));
					raDTO.setTrainingCenter(r.getString("TRAINING_CENTER"));
					raDTO.setCeoName(r.getString("CEONAME"));
					raDTO.setCeoContactInfo(r.getString("CEOCONTACTNO"));
					
					agencyList.add(raDTO);
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return agencyList;

	}
	
	public static ArrayList<RecruitingAgencyDTO> searchRecruitingAgency(String agencyName,String agencyLicense)
	{
		ArrayList<RecruitingAgencyDTO> agencyList=new ArrayList<RecruitingAgencyDTO>();
		   if(agencyName.equalsIgnoreCase(""))
			   agencyName="notassigned";
	 	   Connection conn = ConnectionManager.getConnection();
	 	   String sql="Select * from AGENT_LICENCE Where  lower(COMPANY_NAME) like lower('%"+agencyName+"%') or lower(LICENCE_NO) like lower('%"+agencyLicense+"%')";
	 	   
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   RecruitingAgencyDTO raDTO  = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				r = stmt.executeQuery();
				while (r.next())
				{
					raDTO=new RecruitingAgencyDTO();
					raDTO.setAgentFileRef(r.getString("AGENT_FILE_REF"));
					raDTO.setCompanyName(r.getString("COMPANY_NAME"));
					raDTO.setAddress(r.getString("ADDRESS"));
					raDTO.setPhone(r.getString("PHONE"));
					raDTO.setEmailAddress(r.getString("EMAIL"));
					raDTO.setFax(r.getString("FAX"));
					raDTO.setLicenseNumber(r.getString("LICENCE_NO"));
					raDTO.setSpace(r.getString("SPACE"));
					raDTO.setStatus(r.getString("STATUS"));
					raDTO.setCompanyType(r.getString("COMPANY_TYPE"));
					raDTO.setLicenseDate(r.getString("LICENSEDATE"));
					raDTO.setLicenseValidTill(r.getString("LICENSE_VALID_UPTO"));
					raDTO.setMinistryRef(r.getString("MINISTRY_REFERENCE"));
					raDTO.setContactPerson(r.getString("CONTACT_PERSON"));
					raDTO.setDesignation(r.getString("DESIGNATION"));
					raDTO.setComments(r.getString("COMMENTS"));
					raDTO.setBranch(r.getString("BRANCH"));
					raDTO.setTrainingCenter(r.getString("TRAINING_CENTER"));
					raDTO.setCeoName(r.getString("CEONAME"));
					raDTO.setCeoContactInfo(r.getString("CEOCONTACTNO"));
					
					agencyList.add(raDTO);
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return agencyList;

	}
	
	public static boolean createNewRA(RecruitingAgencyDTO rAgent)
	{
		Connection conn = ConnectionManager.getConnection();
	 	   String sql = " Insert into AGENT_LICENCE(AGENT_FILE_REF,COMPANY_NAME,ADDRESS,PHONE,EMAIL,FAX,LICENCE_NO, " +
	 	   		        " SPACE,STATUS,COMPANY_TYPE,LICENSEDATE,LICENSE_VALID_UPTO,MINISTRY_REFERENCE,CONTACT_PERSON,DESIGNATION, " +
	 	   		        " COMMENTS,BRANCH,TRAINING_CENTER,CEONAME,CEOCONTACTNO)  " +
	 	   		        " Values(?,?,?,?,?,?,?,?,?," +
	 	   		        "        ?,to_date(?,'dd-MM-YYYY'),to_date(?,'dd-MM-YYYY'),?,?,?,?,?,?,?," +
	 	   		        "        ?)";
		   PreparedStatement stmt = null;
		   boolean resp=false;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, rAgent.getAgentFileRef());
				stmt.setString(2, rAgent.getCompanyName());
				stmt.setString(3, rAgent.getAddress());
				stmt.setString(4, rAgent.getPhone());
				stmt.setString(5, rAgent.getEmailAddress());
				stmt.setString(6, rAgent.getFax());
				stmt.setString(7, rAgent.getLicenseNumber());
				stmt.setString(8, rAgent.getSpace());
				stmt.setString(9, rAgent.getStatus());
				stmt.setString(10, rAgent.getCompanyType());
				stmt.setString(11, rAgent.getLicenseDate());
				stmt.setString(12, rAgent.getLicenseValidTill());
				stmt.setString(13, rAgent.getMinistryRef());
				stmt.setString(14, rAgent.getContactPerson());
				stmt.setString(15, rAgent.getDesignation());
				stmt.setString(16, rAgent.getComments());
				stmt.setString(17, rAgent.getBranch());
				stmt.setString(18, rAgent.getTrainingCenter());
				stmt.setString(19, rAgent.getCeoName());
				stmt.setString(20, rAgent.getCeoContactInfo());
				if(stmt.executeUpdate()==1)
					resp=true;
					
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return resp;

	}
	
	public static boolean updateRA(RecruitingAgencyDTO rAgent)
	{
		Connection conn = ConnectionManager.getConnection();
 	    String sql = " Update  AGENT_LICENCE set AGENT_FILE_REF=?,COMPANY_NAME=?,ADDRESS=?,PHONE=?,EMAIL=?,FAX=?," +
	 	                " SPACE=?,STATUS=?,COMPANY_TYPE=?,LICENSEDATE=to_date(?,'dd-MM-YYYY'),LICENSE_VALID_UPTO=to_date(?,'dd-MM-YYYY')," +
	 	                " MINISTRY_REFERENCE=?,CONTACT_PERSON=?,DESIGNATION=?,COMMENTS=?,BRANCH=?,TRAINING_CENTER=?,CEONAME=?,CEOCONTACTNO=?,STATUS_COMMENTS=? Where LICENCE_NO=?";
		PreparedStatement stmt = null;
		boolean resp=false;
		   
		try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, rAgent.getAgentFileRef());
				stmt.setString(2, rAgent.getCompanyName());
				stmt.setString(3, rAgent.getAddress());
				stmt.setString(4, rAgent.getPhone());
				stmt.setString(5, rAgent.getEmailAddress());
				stmt.setString(6, rAgent.getFax());
				stmt.setString(7, rAgent.getSpace());
				stmt.setString(8, rAgent.getStatus());
				stmt.setString(9, rAgent.getCompanyType());
				stmt.setString(10, rAgent.getLicenseDate());
				stmt.setString(11, rAgent.getLicenseValidTill());
				stmt.setString(12, rAgent.getMinistryRef());
				stmt.setString(13, rAgent.getContactPerson());
				stmt.setString(14, rAgent.getDesignation());
				stmt.setString(15, rAgent.getComments());
				stmt.setString(16, rAgent.getBranch());
				stmt.setString(17, rAgent.getTrainingCenter());
				stmt.setString(18, rAgent.getCeoName());
				stmt.setString(19, rAgent.getCeoContactInfo());
				stmt.setString(20, rAgent.getStatusComments());
				stmt.setString(21, rAgent.getLicenseNumber());
				if(stmt.executeUpdate()==1)
					resp=true;
					
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return resp;

	}
	public static String getCompanyNameFromAgentId(String agencyId)
	{
		
	 	   Connection conn = ConnectionManager.getConnection();
	 	   String sql="Select company_name from AGENT_LICENCE Where LICENCE_NO=?";
	 	   
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   String companyName="";
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, agencyId);
				r = stmt.executeQuery();
				if (r.next())
				{
					companyName=r.getString("COMPANY_NAME");
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return companyName;

	}
}

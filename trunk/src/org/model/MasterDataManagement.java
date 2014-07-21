package org.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.table.AddressDTO;
import org.table.CountryDTO;
import org.table.DegreeDTO;
import org.table.PassingYearDTO;

import util.connection.ConnectionManager;
import util.connection.TransactionManager;

public class MasterDataManagement {
	
	
	
	public static PassingYearDTO getPassingYearMstData()
	{
		
	 	   Connection conn = ConnectionManager.getConnection();
	 	   String sql = "Select PASSING_YEAR_FROM,PASSING_YEAR_TO from MST_PASSING_YEAR";
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   PassingYearDTO passingYearDTO  = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				r = stmt.executeQuery();
				while (r.next())
				{
					passingYearDTO=new PassingYearDTO();
					passingYearDTO.setFromYear(r.getString("PASSING_YEAR_FROM"));
					passingYearDTO.setToYear(r.getString("PASSING_YEAR_TO"));
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return passingYearDTO;

	}
	
	public static boolean updatePassingYearInfo(int fromYear,int toYear)
	{
		
		 Connection conn = ConnectionManager.getConnection();
		   String sql = " Update MST_PASSING_YEAR Set PASSING_YEAR_FROM=?,PASSING_YEAR_TO=?";
		   int operation=0;
		   PreparedStatement stmt = null;
			try
			{
				stmt = conn.prepareStatement(sql);
				
			    stmt.setInt(1, fromYear);
			    stmt.setInt(2, toYear);
			    operation=stmt.executeUpdate();
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
		
		if(operation==1)
			return true;
		else
			return false;
	}
	
	public static boolean addNewDegreeName(DegreeDTO degreeDTO)
	{
		
	 	   Connection conn = ConnectionManager.getConnection();
	 	   String sql = "Insert into MST_DEGREE(DEGREE_ID,DEGREE_NAME,VIEW_SERIAL) values(SQN_DEGREEID.nextval,?,?)";
		   PreparedStatement stmt = null;
		   boolean resp=false;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, degreeDTO.getDegreeName());
				stmt.setInt(2, degreeDTO.getViewSerial());
				if(stmt.executeUpdate()==1)
					resp=true;
					
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return resp;

	}
	public static boolean updateDegreeName(DegreeDTO degreeDTO)
	{
		
	 	   Connection conn = ConnectionManager.getConnection();
	 	   String sql = "Update MST_DEGREE set DEGREE_NAME=?,VIEW_SERIAL=? Where DEGREE_ID=?";
		   PreparedStatement stmt = null;
		   boolean resp=false;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, degreeDTO.getDegreeName());
				stmt.setInt(2, degreeDTO.getViewSerial());
				stmt.setInt(3, degreeDTO.getDegreeId());
				if(stmt.executeUpdate()==1)
					resp=true;
					
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return resp;

	}
	public static boolean deleteDegreeName(int degreeId)
	{
		
	 	   Connection conn = ConnectionManager.getConnection();
	 	   String sql = "Delete MST_DEGREE Where DEGREE_ID=?";
		   PreparedStatement stmt = null;
		   boolean resp=false;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, degreeId);
				if(stmt.executeUpdate()==1)
					resp=true;
					
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return resp;

	}
	public static boolean updateCountry(CountryDTO countryDTO)
	{
		
		 Connection conn = ConnectionManager.getConnection();
		   String sql = " Update MST_COUNTRY Set VISIBILITY=? Where COUNTRY_ID=?";
		   int operation=0;
		   PreparedStatement stmt = null;
			try
			{
				stmt = conn.prepareStatement(sql);
				
			    stmt.setInt(1, countryDTO.getVisibility());
			    stmt.setInt(2, countryDTO.getCountryId());
			    operation=stmt.executeUpdate();
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
		
		if(operation==1)
			return true;
		else
			return false;
	}
	
	public static boolean updateCountryBatch(List<CountryDTO> countryList)
	{
		
		 Connection conn = ConnectionManager.getConnection();
		   String sql = " Update MST_COUNTRY Set VISIBILITY=? Where COUNTRY_ID=?";
		   int[] operation=null;
		   PreparedStatement stmt = null;
			try
			{
				stmt = conn.prepareStatement(sql);
				for(int i=0;i<countryList.size();i++){
					stmt.setInt(1, countryList.get(i).getVisibility());
				    stmt.setInt(2, countryList.get(i).getCountryId());	
				    stmt.addBatch();
				}
			    
			    operation=stmt.executeBatch();
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
		
		if(operation.length==countryList.size())
			return true;
		else
			return false;
	}
	
	public static boolean createNewJob(String jobName,int levelNo)
	{
		
		 Connection conn = ConnectionManager.getConnection();
		   String sql = " Insert into MST_JOBS(JOB_ID,JOB_TITLE,LEVEL_NO,VISIBILITY) values(SQN_JOB.NEXTVAL,?,?,1)";
		   int operation=0;
		   PreparedStatement stmt = null;
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, jobName);
			    stmt.setInt(2, levelNo);	
		    
			    operation=stmt.executeUpdate();
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
		
		if(operation>=1)
			return true;
		else
			return false;
	}
	
	public static boolean jobExistsYN(String jobName,int levelNo)
	{
		
		   Connection conn = ConnectionManager.getConnection();
		   String sql = " Select count(job_id) total from mst_jobs Where lower(job_title)=lower(?) and level_no=?";
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   int total=0;
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, jobName);
			    stmt.setInt(2, levelNo);	
		    
			    r = stmt.executeQuery();
				if (r.next())
					total=r.getInt("TOTAL");
			    
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
		
		if(total>=1)
			return true;
		else
			return false;
	}
	
	public static boolean editJobMapping(String deleteQuery,String insertQuery)
	{
		TransactionManager transactionManager=new TransactionManager();
		Connection conn = transactionManager.getConnection();
		PreparedStatement stmt = null;
		boolean response=false;
		try
			{
				stmt = conn.prepareStatement(deleteQuery);
				stmt.executeUpdate();
				stmt = conn.prepareStatement(insertQuery);
				stmt.executeUpdate();
					
				transactionManager.commit();
				response=true;
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
	
}

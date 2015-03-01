package org.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.table.AgeLimitDTO;
import org.table.CountryDTO;
import org.table.DegreeDTO;
import org.table.PassingYearDTO;
import org.table.PaymentMethodDTO;
import org.table.RegTypeDTO;

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
	
	public static AgeLimitDTO getAgeLimitMstData()
	{
		
	 	   Connection conn = ConnectionManager.getConnection();
	 	   String sql = "Select MIN_AGE,MAX_AGE from MST_ALLOWED_AGE_LIMIT";
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   AgeLimitDTO ageLimitDTO  = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				r = stmt.executeQuery();
				while (r.next())
				{
					ageLimitDTO=new AgeLimitDTO();
					ageLimitDTO.setMinAge(r.getFloat("MIN_AGE"));
					ageLimitDTO.setMaxAge(r.getFloat("MAX_AGE"));
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return ageLimitDTO;

	}
	
	public static ArrayList<RegTypeDTO> getRegTypeMstData()
	{
		
	 	   Connection conn = ConnectionManager.getConnection();
	 	   String sql = "Select TYPE_ID,REGID_SUFFIX,TYPE_NAME,TYPE_DESC,ISACTIVE from MST_REGTYPE";
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   RegTypeDTO regTypeDTO  = null;
		   ArrayList<RegTypeDTO> regTypeList=new ArrayList<RegTypeDTO>();
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				r = stmt.executeQuery();
				while (r.next())
				{
					regTypeDTO=new RegTypeDTO();
					regTypeDTO.setTypeId(r.getInt("TYPE_ID"));
					regTypeDTO.setRegIdSuffix(r.getString("REGID_SUFFIX"));
					regTypeDTO.setTypeName(r.getString("TYPE_NAME"));
					regTypeDTO.setTypeDesc(r.getString("TYPE_DESC"));
					regTypeDTO.setIsActive(r.getInt("ISACTIVE"));
					regTypeList.add(regTypeDTO);
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return regTypeList;
	}
	
	public static RegTypeDTO getActiveRegType()
	{
		
	 	   Connection conn = ConnectionManager.getConnection();
	 	   String sql = "Select TYPE_ID,REGID_SUFFIX,TYPE_NAME,TYPE_DESC,ISACTIVE from MST_REGTYPE Where ISACTIVE=1";
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   RegTypeDTO regTypeDTO  = null;
		   int count=0;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				r = stmt.executeQuery();
				while (r.next())
				{
					regTypeDTO=new RegTypeDTO();
					regTypeDTO.setTypeId(r.getInt("TYPE_ID"));
					regTypeDTO.setRegIdSuffix(r.getString("REGID_SUFFIX"));
					regTypeDTO.setTypeName(r.getString("TYPE_NAME"));
					regTypeDTO.setTypeDesc(r.getString("TYPE_DESC"));
					regTypeDTO.setIsActive(r.getInt("ISACTIVE"));
					count++;
				}
				if(count>1)
					regTypeDTO=null;
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return regTypeDTO;
	}
	
	public static boolean addNewRegType(RegTypeDTO regTypeDTO)
	{
		
	 	   Connection conn = ConnectionManager.getConnection();
	 	   String sql = "Insert into MST_REGTYPE(TYPE_ID,REGID_SUFFIX,TYPE_NAME,TYPE_DESC,ISACTIVE)  " +
	 	   		" values(SQN_REGTYPE_ID.nextval,?,?,?,?)";
		   PreparedStatement stmt = null;
		   boolean resp=false;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, regTypeDTO.getRegIdSuffix());
				stmt.setString(2, regTypeDTO.getTypeName());
				stmt.setString(3, regTypeDTO.getTypeDesc());
				stmt.setInt(4, regTypeDTO.getIsActive());
				if(stmt.executeUpdate()==1)
					resp=true;
					
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return resp;

	}
	public static boolean updateRegType(RegTypeDTO regTypeDTO)
	{
		
	 	   Connection conn = ConnectionManager.getConnection();
	 	   String sql = "Update MST_REGTYPE set REGID_SUFFIX=?,TYPE_NAME=?,TYPE_DESC=?,ISACTIVE=? Where TYPE_ID=?";
		   PreparedStatement stmt = null;
		   boolean resp=false;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, regTypeDTO.getRegIdSuffix());
				stmt.setString(2, regTypeDTO.getTypeName());
				stmt.setString(3, regTypeDTO.getTypeDesc());
				stmt.setInt(4, regTypeDTO.getIsActive());
				stmt.setInt(5, regTypeDTO.getTypeId());
				if(stmt.executeUpdate()==1)
					resp=true;
					
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return resp;

	}
	
	public static boolean deleteRegType(int regTypeId)
	{
		
	 	   Connection conn = ConnectionManager.getConnection();
	 	   String sql = "Delete MST_REGTYPE Where TYPE_ID=?";
		   PreparedStatement stmt = null;
		   boolean resp=false;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, regTypeId);
				if(stmt.executeUpdate()==1)
					resp=true;
					
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return resp;

	}
	
	
	public static boolean updateAgeLimitInfo(float minYear,float maxYear)
	{
		
		 Connection conn = ConnectionManager.getConnection();
		   String sql = " Update MST_ALLOWED_AGE_LIMIT Set MIN_AGE=?,MAX_AGE=?";
		   int operation=0;
		   PreparedStatement stmt = null;
			try
			{
				stmt = conn.prepareStatement(sql);
				
			    stmt.setFloat(1, minYear);
			    stmt.setFloat(2, maxYear);
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
				conn.setAutoCommit(false);//commit

				for(int i=0;i<countryList.size();i++){
					stmt.setInt(1, countryList.get(i).getVisibility());
				    stmt.setInt(2, countryList.get(i).getCountryId());	
				    stmt.addBatch();
				}
			    
				operation=stmt.executeBatch();
			    conn.commit();
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
	
	public static ArrayList<PaymentMethodDTO> getActivePaymentMethods()
	{
		
	 	   Connection conn = ConnectionManager.getConnection();
	 	   String sql = "Select * from MST_PAYMENT_METHODS Where Active_YN=1";
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   PaymentMethodDTO pMethodDTO  = null;
		   ArrayList<PaymentMethodDTO> methodList=new ArrayList<PaymentMethodDTO>();
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				r = stmt.executeQuery();
				while (r.next())
				{
					pMethodDTO=new PaymentMethodDTO();
					pMethodDTO.setPaymentMethodId(r.getInt("METHOD_ID"));
					pMethodDTO.setPaymentMethodName(r.getString("METHOD_NAME"));
					pMethodDTO.setIsActive(r.getInt("ACTIVE_YN"));
					methodList.add(pMethodDTO);
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return methodList;
	}
}

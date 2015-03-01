package org.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.table.GenderDTO;

import util.connection.ConnectionManager;

public class ParameterDAO {

	
	public static ArrayList<GenderDTO> getGenderList(String type)
	{
		ArrayList<GenderDTO> genderList=new ArrayList<GenderDTO>();
		
	 	   Connection conn = ConnectionManager.getConnection();
	 	   String sql="";
	 	   if(type.equalsIgnoreCase("all"))
	 	      sql = "Select * from MST_ALLOWED_GENDER  ORDER BY GENDER_ID";
	 	   else
	 		  sql = "Select * from MST_ALLOWED_GENDER Where VISIBILITY=1  ORDER BY GENDER_ID ";
	 	   
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   GenderDTO gender  = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				r = stmt.executeQuery();
				while (r.next())
				{
					gender=new GenderDTO();
					gender.setGenderId(r.getString("GENDER_ID"));
					gender.setFieldId(r.getString("FIELD_ID"));
					gender.setFieldName(r.getString("FIELD_NAME"));
					gender.setExtraAttribute(r.getString("EXTRA_ATTRIBUTE"));
					gender.setVisibility(r.getInt("VISIBILITY"));
					gender.setTabIndex(r.getInt("TAB_INDEX"));
					gender.setCaption(r.getString("CAPTION"));
					genderList.add(gender);
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return genderList;

	}
	
	public static boolean updateGenderList(List<GenderDTO> genderList)
	{		
		   Connection conn = ConnectionManager.getConnection();
		    
		   String sql = " Update MST_ALLOWED_GENDER Set FIELD_ID=?,FIELD_NAME=?,EXTRA_ATTRIBUTE=?,VISIBILITY=?,TAB_INDEX=?,CAPTION=? Where GENDER_ID=?";
		   int[] operation=null;
		   PreparedStatement stmt = null;
			try
			{	stmt = conn.prepareStatement(sql);
				conn.setAutoCommit(false);//commit
				for(int i=0;i<genderList.size();i++){
					
					stmt.setString(1, genderList.get(i).getFieldId());
				    stmt.setString(2, genderList.get(i).getFieldName());	
				    stmt.setString(3, genderList.get(i).getExtraAttribute());
				    stmt.setInt(4, genderList.get(i).getVisibility());
				    stmt.setInt(5, genderList.get(i).getTabIndex());
				    stmt.setString(6, genderList.get(i).getCaption());
				    stmt.setString(7, genderList.get(i).getGenderId());
				    stmt.addBatch();
				}			    
			    operation=stmt.executeBatch();
			    conn.commit();
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
		
		if(operation.length==genderList.size())
			return true;
		else
			return false;
	}
	
}

package org.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.table.CountryDTO;
import org.table.LanguageDTO;

import util.connection.ConnectionManager;

public class LanguageDAO {

	public static ArrayList<LanguageDTO> getAllLanguage(int allOrActive)
	{
		ArrayList<LanguageDTO> languageList=new ArrayList<LanguageDTO>();
		
	 	   Connection conn = ConnectionManager.getConnection();
	 	   String sql="";
	 	   if(allOrActive==0)
		      sql = "Select * from MST_LANGUAGE Order by Language_Name";
	 	   else 
	 		  sql = "Select * from MST_LANGUAGE Where VISIBILITY=1 Order by Language_Name"; 

		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   LanguageDTO language  = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				r = stmt.executeQuery();
				while (r.next())
				{
					language=new LanguageDTO();
					language.setLanguage(r.getString("LANGUAGE_NAME"));
					language.setVisibility(r.getInt("Visibility"));
					languageList.add(language);
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return languageList;

	}
	
	public static boolean updateLanauge(LanguageDTO languageDTO)
	{
		
		 Connection conn = ConnectionManager.getConnection();
		   String sql = " Update MST_LANGUAGE Set VISIBILITY=? Where LANGUAGE_NAME=?";
		   int operation=0;
		   PreparedStatement stmt = null;
			try
			{
				stmt = conn.prepareStatement(sql);
				
			    stmt.setInt(1, languageDTO.getVisibility());
			    stmt.setString(2, languageDTO.getLanguage());
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
	public static boolean updateLanaugeBatch(List<LanguageDTO> languageList)
	{
		
		 Connection conn = ConnectionManager.getConnection();
		   String sql = " Update MST_LANGUAGE Set VISIBILITY=? Where LANGUAGE_NAME=?";
		   int[] operation=null;
		   PreparedStatement stmt = null;
			try
			{
				stmt = conn.prepareStatement(sql);
				for(int i=0;i<languageList.size();i++){
					stmt.setInt(1, languageList.get(i).getVisibility());
				    stmt.setString(2, languageList.get(i).getLanguage());	
				    stmt.addBatch();
				}
			    
			    operation=stmt.executeBatch();
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
		
		if(operation.length==languageList.size())
			return true;
		else
			return false;
	}
	
	
}

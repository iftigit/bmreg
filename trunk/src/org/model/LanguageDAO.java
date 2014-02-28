package org.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.table.CountryDTO;
import org.table.LanguageDTO;

import util.connection.ConnectionManager;

public class LanguageDAO {

	public static ArrayList<LanguageDTO> getAllLanguage()
	{
		ArrayList<LanguageDTO> languageList=new ArrayList<LanguageDTO>();
		
	 	   Connection conn = ConnectionManager.getConnection();
		   String sql = "Select * from MST_LANGUAGE Order by Language_Name";
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
					languageList.add(language);
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return languageList;

	}
}

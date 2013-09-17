package org.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.table.CountryDTO;
import org.table.DegreeDTO;

import util.connection.ConnectionManager;

public class DegreeDAO {

	public static ArrayList<DegreeDTO> getAllDegree()
	{
		ArrayList<DegreeDTO> degreeList=new ArrayList<DegreeDTO>();
		
	 	   Connection conn = ConnectionManager.getConnection();
		   String sql = "SELECT DEGREE_ID,DEGREE_NAME,VIEW_SERIAL FROM MST_DEGREE ORDER BY VIEW_SERIAL";
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   DegreeDTO degreeDTO  = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				r = stmt.executeQuery();
				while (r.next())
				{
					degreeDTO=new DegreeDTO();
					degreeDTO.setDegreeId(r.getInt("DEGREE_ID"));
					degreeDTO.setDegreeName(r.getString("DEGREE_NAME"));
					degreeDTO.setViewSerial(r.getInt("VIEW_SERIAL"));
					
					degreeList.add(degreeDTO);
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return degreeList;

	}
}

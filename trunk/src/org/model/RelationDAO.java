package org.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.table.AddressDTO;
import org.table.RelationDTO;

import util.connection.ConnectionManager;

public class RelationDAO {
	
	public static ArrayList<RelationDTO> getAllRelation()
	{
		ArrayList<RelationDTO> relationList=new ArrayList<RelationDTO>();
		
	 	   Connection conn = ConnectionManager.getConnection();
		   //String sql = "SELECT DIVISIONID,DIVISION_NAME FROM DIVISION ORDER BY DIVISIONID";
	 	  String sql = "SELECT * FROM MST_RELATION Where ACTIVE_YN='Y' order by VIEW_ORDER";
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   RelationDTO relationDTO  = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				r = stmt.executeQuery();
				while (r.next())
				{
					relationDTO=new RelationDTO();
					relationDTO.setRelationId(r.getInt("RELATION_ID"));
					relationDTO.setRelationName(r.getString("RELATION_NAME"));
					relationList.add(relationDTO);
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return relationList;

	}

}

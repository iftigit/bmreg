package org.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.table.RecruitingAgencyDTO;

import util.connection.ConnectionManager;

public class SettingsDAO {

	public static int getRegistrationFeeAmount()
	{
		  
		
	 	   Connection conn = ConnectionManager.getConnection();
	 	   String sql="Select param_value from MST_SETTING where Param_Name='DEMO_REG_FEE'";

	 	   PreparedStatement stmt = null;
		   ResultSet r = null;
		   int fee=0;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				r = stmt.executeQuery();
				if (r.next())
				{
					fee=r.getInt("param_value");
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return fee;

	}
	
	
}

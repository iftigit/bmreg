package org.singleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.table.TtcDTO;

import oracle.jdbc.driver.OracleCallableStatement;

import util.connection.ConnectionManager;


public class RegistrationSingleton {
    
    //Marking default constructor private
    //to avoid direct instantiation.
    private RegistrationSingleton() {
    }
 
    public static synchronized String generateRegistrationId(String distId,String sex)
	 {
   	   	   String registrationId = null;
	 	   Connection conn = ConnectionManager.getConnection();
	 	   OracleCallableStatement stmt=null;
		   try
			  {
				 System.out.println("Procedure Generate_RegId Begins");
				 stmt = (OracleCallableStatement) conn.prepareCall(
						 	  "{ call Generate_RegId(?,?,?) }");
				 

			 		stmt.setString(1,  distId);
					stmt.setString(2,  sex);
					stmt.registerOutParameter(3, java.sql.Types.VARCHAR);
					stmt.executeUpdate();
					registrationId = (stmt.getString(3)).trim();
					System.out.println("registrationId : " + registrationId);
			  }
			    catch (Exception e){e.printStackTrace();return registrationId;}
		 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
					{e.printStackTrace();}stmt = null;conn = null;}
		 			 		
	 	return registrationId;
	 }
    
    
    
}



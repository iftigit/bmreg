package org.model;

import java.sql.Connection;
import oracle.jdbc.driver.OracleCallableStatement;
import org.table.RegTokenDTO;

import util.connection.ConnectionManager;

public class RegTokenDAO {

	public String insertTokenInformation(RegTokenDTO regToken) 
		{	     
					String response="";
					Connection conn = ConnectionManager.getConnection();
					OracleCallableStatement stmt=null;					

					try
					{
					
					System.out.println("Procedure Insert_New_Reg_Token Begins");
					stmt = (OracleCallableStatement) conn.prepareCall(
					  "{ call Insert_New_Reg_Token(?,?,?,?,?,?,?,?,?) }");
					
					
					stmt.setString(1,  regToken.getCreatedBy());
					stmt.setString(2,  regToken.getAssignedTo());
					stmt.setString(3,  regToken.getPayOrderNumber());
					stmt.setString(4,  regToken.getPayOrderDate());
					stmt.setString(5,  regToken.getPayOrderBank());
					stmt.setString(6,  regToken.getPayOrderBranch());
					stmt.setFloat(7,  regToken.getTotalAmount());
					stmt.setString(8,  regToken.getRecruitingAgencyId());
					
					stmt.registerOutParameter(9, java.sql.Types.VARCHAR);
					stmt.executeUpdate();
					response = (stmt.getString(9)).trim();
					System.out.println("Response : " + response);
					}
					catch (Exception e){e.printStackTrace();return response;}
					finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
					{e.printStackTrace();}stmt = null;conn = null;}
					
					return response;	           
					}
	
}

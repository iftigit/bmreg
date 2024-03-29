package org.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import oracle.jdbc.driver.OracleCallableStatement;
import org.table.RegTokenDTO;
import org.table.SelectedEmpDTO;

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
	
  public ArrayList<RegTokenDTO> getTokenList(int tokenId){
		
	  	RegTokenDTO token;
	  	ArrayList<RegTokenDTO> tokenList=new ArrayList<RegTokenDTO>();
		Connection conn = ConnectionManager.getConnection();
		String sql = " Select * from DTL_REGISTRATION_TOKEN Where Token_Id="+tokenId;
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			r = stmt.executeQuery();
			int count=0;
			while (r.next())
			{
				token=new RegTokenDTO();
				
				token.setRegToken(r.getString("TOKEN"));				
				tokenList.add(token);
				count++;
				
			}
		} 
		catch (Exception e){e.printStackTrace();}
 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
			{e.printStackTrace();}stmt = null;conn = null;}
 		
		
		return tokenList;
	}
  
  public RegTokenDTO getTokenBasicInfo(int tokenId){
		
	  	RegTokenDTO token=null;
		Connection conn = ConnectionManager.getConnection();
		String sql = " Select token_id,assigned_to,to_char(Inserted_on,'dd-mm-YYYY HH24:MI:SS') INSERTED_ON from MST_REGISTRATION_TOKEN where token_id="+tokenId;
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			r = stmt.executeQuery();
			if (r.next())
			{
				token=new RegTokenDTO();
												
				token.setTokenId(r.getInt("token_id"));
				token.setAssignedTo(r.getString("assigned_to"));
				token.setInsertedOn(r.getString("INSERTED_ON"));
				
				
			}
		} 
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
			{e.printStackTrace();}stmt = null;conn = null;}
		
		
		return token;
	}
  
  public ArrayList<RegTokenDTO> getTokenList(String userId,int tokenId,String fromDate,String toDate){
		
	  	RegTokenDTO token=null;
		Connection conn = ConnectionManager.getConnection();
		String sql="";
		
		if(tokenId==0){
		
			if(fromDate!=null && toDate!=null && !fromDate.equalsIgnoreCase("") && !toDate.equalsIgnoreCase("")){
				sql = " SELECT token_id, tab_to_string(CAST(COLLECT(token||'('||status||')') AS t_varchar2_tab))  " +
			     " AS tokens,to_char(created_on,'dd-MM-YYYY')created_on_e FROM   DTL_REGISTRATION_TOKEN where userid='"+userId+"' " + 
			     " and to_date(TO_char (created_on, 'dd-MM-YYYY'),'dd-MM-YYYY')  >=to_date('"+fromDate+"','dd-MM-YYYY') and to_date(TO_char (created_on, 'dd-MM-YYYY'),'dd-MM-YYYY') <=to_date('"+toDate+"','dd-MM-YYYY') "+ 
			     " GROUP BY token_id,to_char(created_on,'dd-MM-YYYY') order by token_id desc";
			}
			else{
			sql = " SELECT token_id, tab_to_string(CAST(COLLECT(token||'('||status||')') AS t_varchar2_tab))  " +
				     " AS tokens,to_char(created_on,'dd-MM-YYYY')created_on_e FROM   DTL_REGISTRATION_TOKEN where userid='"+userId+"' " +
				     " GROUP BY token_id,to_char(created_on,'dd-MM-YYYY') order by token_id desc";
			}
		}
		else
			  sql = " SELECT token_id, tab_to_string(CAST(COLLECT(token||'('||status||')') AS t_varchar2_tab))  " +
		            " AS tokens,to_char(created_on,'dd-MM-YYYY')created_on_e FROM   DTL_REGISTRATION_TOKEN where userid='"+userId+"'  and token_Id='"+tokenId+"'" +
		            " GROUP BY token_id,to_char(created_on,'dd-MM-YYYY') order by token_id desc";
			
		ArrayList<RegTokenDTO> tokenList=new ArrayList<RegTokenDTO>();
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			r = stmt.executeQuery();
			while (r.next())
			{
				token=new RegTokenDTO();
												
				token.setTokenId(r.getInt("token_id"));
				token.setInsertedOn(r.getString("created_on_e"));
				token.setTokenListString(r.getString("tokens"));

				tokenList.add(token);
				
			}
		} 
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
			{e.printStackTrace();}stmt = null;conn = null;}
		
		
		return tokenList;
	}

  
	
}

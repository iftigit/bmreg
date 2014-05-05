package org.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import oracle.jdbc.driver.OracleCallableStatement;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import org.table.RecruitingAgencyDTO;
import org.table.TechnicalTeamDTO;
import org.table.UserDTO;
import org.table.UserTmpDTO;

import util.connection.ConnectionManager;

public class UserDAO {
	
	
	public UserDTO validateLogin(String userId,String password)
	{
		UserDTO user=null;
		
		 Connection conn = ConnectionManager.getConnection();
		   String sql = " Select MST_USER.*,case when sysdate >= start_date then 'yes' else 'no' end S_DATE,case when sysdate <= end_date then 'yes' else 'no' end E_DATE " +
		   		        " from MST_USER Where userid=? and password=?";
		   
		   PreparedStatement stmt = null;
		   ResultSet r = null;
			try
			{
				stmt = conn.prepareStatement(sql);
				
			    stmt.setString(1, userId);
			    stmt.setString(2, password);

			    
				r = stmt.executeQuery();
				if (r.next())
				{
					user=new UserDTO();
					user.setUserId(r.getString("USERID"));
					user.setPassword(r.getString("PASSWORD"));
					user.setUserType(r.getString("USER_TYPE"));
					user.setLocalIp(r.getString("LOCAL_IP")==null?"":r.getString("LOCAL_IP"));
					user.setVia(r.getString("VIA")==null?"":r.getString("VIA"));
					user.setRealIp(r.getString("REAL_IP"));
					user.setDivisionId(r.getString("DIVISION_ID"));
					user.setDistrictId(r.getString("DISTRICT_ID"));
					user.setUpazillaId(r.getString("UPAZILLA_ID"));
					user.setUnionId(r.getString("UNION_ID"));
					
					String startDate=r.getString("S_DATE");
					String endDate=r.getString("E_DATE");
		        	
		        	if(startDate.equalsIgnoreCase("yes") && endDate.equalsIgnoreCase("yes")){
		        		user.setAccessRight(1);
		        	}
		        	else
		        		user.setAccessRight(0);
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
		
		
		return user;
	}
	
	
	public UserDTO getUserFromUserId(String userId)
	{
		UserDTO user=null;
		
		 Connection conn = ConnectionManager.getConnection();
		   String sql = " Select MST_USER.*,case when sysdate >= start_date then 'yes' else 'no' end S_DATE,case when sysdate <= end_date then 'yes' else 'no' end E_DATE " +
		   		        " from MST_USER Where userid=?";
		   
		   PreparedStatement stmt = null;
		   ResultSet r = null;
			try
			{
				stmt = conn.prepareStatement(sql);
				
			    stmt.setString(1, userId);
			   			    
				r = stmt.executeQuery();
				if (r.next())
				{
					user=new UserDTO();
					user.setUserId(r.getString("USERID"));
					user.setPassword(r.getString("PASSWORD"));
					user.setUserType(r.getString("USER_TYPE"));
					
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
		
		
		return user;
	}
	
	public String getAuthenticationKey(String userId,String password)
	{
		String authenticationKye="";
		
		 Connection conn = ConnectionManager.getConnection();
		   String sql = " Select Local_Ip||Via||Real_Ip authentication_key from MST_USER Where userid=? and password=?";
		   
		   PreparedStatement stmt = null;
		   ResultSet r = null;
			try
			{
				stmt = conn.prepareStatement(sql);
				
			    stmt.setString(1, userId);
			    stmt.setString(2, password);

			    
				r = stmt.executeQuery();
				if (r.next())
				{
					authenticationKye=r.getString("authentication_key");
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
		
		
		return authenticationKye==null?"":authenticationKye;
	}
	
	public boolean updateLoginKey(String userId,String password,String localIp,String via,String realIp)
	{
		
		 Connection conn = ConnectionManager.getConnection();
		   String sql = " Update MST_USER Set LOCAL_IP=?,VIA=?,REAL_IP=? Where USERID=? and PASSWORD=?";
		   int operation=0;
		   PreparedStatement stmt = null;
			try
			{
				stmt = conn.prepareStatement(sql);
				
			    stmt.setString(1, localIp);
			    stmt.setString(2, via);
			    stmt.setString(3, realIp);
			    stmt.setString(4, userId);
			    stmt.setString(5, password);
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
	
	public ArrayList<TechnicalTeamDTO> getTechnicalTeam(String districtId)
	{
		ArrayList<TechnicalTeamDTO> teamList=new ArrayList<TechnicalTeamDTO>();
		TechnicalTeamDTO team=null;
		
		 Connection conn = ConnectionManager.getConnection();
		   String sql = " Select * from TECH_CONTACT_INFO Where dist_id=? Order by CONTACT_PERSON";
		   
		   PreparedStatement stmt = null;
		   ResultSet r = null;
			try
			{
				stmt = conn.prepareStatement(sql);
				
			    stmt.setString(1, districtId);
			    
				r = stmt.executeQuery();
				while (r.next())
				{
					team=new TechnicalTeamDTO();
					team.setContactName(r.getString("CONTACT_PERSON"));
					team.setMobileNo(r.getString("CONTACT_MOBILE"));
					
					teamList.add(team);
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
		
		
		return teamList;
	}
	public String insertNewUserRequest(String userId,String userType,String divisionId,String districtId,String upazilaId,
            String unionId,String requestedBy) 
	{	     
		String response="";
		Connection conn = ConnectionManager.getConnection();
		OracleCallableStatement stmt=null;
		
		try {
		System.out.println("Procedure Insert_User_Request Begins");
		stmt = (OracleCallableStatement) conn.prepareCall(
		"{ call Insert_User_Request(?,?,?,?,?,?,?) }");
		
		stmt.setString(1,  userId);
		stmt.setString(2,  divisionId);
		stmt.setString(3,  districtId);
		stmt.setString(4,  upazilaId);
		stmt.setString(5,  unionId);
		stmt.setString(6,  requestedBy);
		
		stmt.registerOutParameter(7, java.sql.Types.VARCHAR);
		stmt.executeUpdate();
		response = (stmt.getString(7)).trim();
		System.out.println("Response : " + response);	
		}
		catch (Exception e){e.printStackTrace();return response;}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return response;	           
	}
	
	public ArrayList<UserTmpDTO> getNewUserList()
	{
		ArrayList<UserTmpDTO> userList=new ArrayList<UserTmpDTO>();
		UserTmpDTO user=null;
		
		 Connection conn = ConnectionManager.getConnection();
		   String sql = " select distinct userid,mst_user_tmp.division_id,division_name,dist_id,dist_name,thanaid,thana_name,unionid,unionname,requested_by,TO_CHAR (requested_on, 'dd-MM-YYYY HH24:MI:SS') requested_on from  " +
		   		        " (select division_id,division_name from division,mst_user_tmp where divisionid=mst_user_tmp.DIVISION_ID) t1, " +
		   		        " (select dist_id,dist_name from district,mst_user_tmp where dist_id=mst_user_tmp.DISTRICT_ID) t2, " +
		   		        " (select thanaid,thana_name from thana,mst_user_tmp where thanaid=mst_user_tmp.UPAZILA_ID) t3, " +
		   		        " (select unionid,unionname from unions,mst_user_tmp where unionid=mst_user_tmp.UNION_ID) t4, " +
		   		        "  mst_user_tmp where mst_user_tmp.DIVISION_ID=t1.division_id " +
		   		        "  and mst_user_tmp.DISTRICT_ID=t2.dist_id " +
		   		        "  and mst_user_tmp.UPAZILA_ID=t3.thanaid " +
		   		        "  and mst_user_tmp.UNION_ID=t4.unionid " +
		   		        "  order by requested_on";
		   
		   PreparedStatement stmt = null;
		   ResultSet r = null;
			try
			{
				stmt = conn.prepareStatement(sql);
				
				r = stmt.executeQuery();
				while (r.next())
				{
					user=new UserTmpDTO();
					
					user.setUserId(r.getString("USERID"));
					user.setDivisionId(r.getString("DIVISION_ID"));
					user.setDivisionName(r.getString("DIVISION_NAME"));
					user.setDistrictId(r.getString("DIST_ID"));
					user.setDistrictName(r.getString("DIST_NAME"));
					user.setUpazilaId(r.getString("THANAID"));
					user.setUpazilaName(r.getString("THANA_NAME"));
					user.setUnionId(r.getString("UNIONID"));
					user.setUnionName(r.getString("UNIONNAME"));
					user.setRequestedBy(r.getString("REQUESTED_BY"));
					user.setRequestedOn(r.getString("REQUESTED_ON"));					
					
					userList.add(user);
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
		
		
		return userList;
	}
	
	public String approveNewUserRequest(String[]  userList,String startDate,String endDate)	
	  {
		  String response="";
		 	Connection conn = ConnectionManager.getConnection();
		 	OracleCallableStatement stmt=null;
		  
		    try
			  {
		    	
		    	ArrayDescriptor arrString = new ArrayDescriptor("VARCHARARRAY", conn);
		    	
				ARRAY inputUser=new ARRAY(arrString,conn,userList);
				
			
				System.out.println("Procedure Update_User_Request Begins");
				 stmt = (OracleCallableStatement) conn.prepareCall(
						 	  "{ call Update_User_Request(?,?,?,?) }");
				 
					
					stmt.setARRAY(1, inputUser);
					stmt.setString(2,  startDate);
					stmt.setString(3,  endDate);
					stmt.registerOutParameter(4, java.sql.Types.VARCHAR);
					stmt.executeUpdate();
					response = (stmt.getString(4)).trim();
					System.out.println("Response : " + response);
					}
				    catch (Exception e){e.printStackTrace();return response;}
			 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
						{e.printStackTrace();}stmt = null;conn = null;}
		 	
			 		return response;	           
	}
	
	
	public static boolean createNewUser(UserDTO user)
	{
		Connection conn = ConnectionManager.getConnection();
	 	   String sql = " Insert into MST_USER(USERID,PASSWORD,USER_TYPE,DIVISION_ID,DISTRICT_ID,UPAZILLA_ID,UNION_ID, " +
	 	   				" START_DATE,END_DATE,FULL_NAME,DESIGNATION) " +
	 	   				" Values(?,?,?,?,?,?,?,to_date(?,'dd-MM-YYYY HH24:MI:SS'),to_date(?,'dd-MM-YYYY HH24:MI:SS'),?,?)";
		   PreparedStatement stmt = null;
		   boolean resp=false;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, user.getUserId());
				stmt.setString(2, user.getPassword());
				stmt.setString(3, user.getUserType());
				stmt.setString(4, user.getDivisionId());
				stmt.setString(5, user.getDistrictId());
				stmt.setString(6, user.getUpazillaId());
				stmt.setString(7, user.getUnionId());
				stmt.setString(8, user.getFormDate());
				stmt.setString(9, user.getToDate());
				stmt.setString(10, user.getUserName());
				stmt.setString(11, user.getDesignation());
				
				if(stmt.executeUpdate()==1)
					resp=true;
					
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return resp;

	}

	public static ArrayList<UserDTO> getUserList(String userType,String value)
	{
		ArrayList<UserDTO> userList=new ArrayList<UserDTO>();
		UserDTO user=null;
		
		 Connection conn = ConnectionManager.getConnection();
		 String sql="";
		   
		   if(userType.equalsIgnoreCase("DEMO_REG_OPERATOR"))
			   sql = " Select * from MST_USER Where USER_TYPE='"+userType+"' and  DISTRICT_ID="+value;
		   
		   PreparedStatement stmt = null;
		   ResultSet r = null;
			try
			{
				stmt = conn.prepareStatement(sql);
				r = stmt.executeQuery();
				while (r.next())
				{
					user=new UserDTO();
					user.setUserId(r.getString("USERID"));
					userList.add(user);
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
		
		
		return userList;
	}

	public static boolean updatePassword(String userId,String newPassword)
	{
		Connection conn = ConnectionManager.getConnection();
		String sql=" Update MST_USER  set PASSWORD=?  Where USERID=?";
		int response=0;
		PreparedStatement stmt = null;
			try
			{
				stmt = conn.prepareStatement(sql);

				stmt.setString(1,newPassword);
				stmt.setString(2,userId);
				
				response = stmt.executeUpdate();
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		

		 	if(response==1)
		 		return true;
		 	else
		 		return false;
	}
	
	public static ArrayList<UserDTO> getDemoUserList(String usreId)
	{
		ArrayList<UserDTO> userList=new ArrayList<UserDTO>();
		UserDTO user=null;
		
		 Connection conn = ConnectionManager.getConnection();
 	     String sql = " Select * from MST_USER where district_id in (Select district_id from MST_USER where userid='"+usreId+"') and USER_TYPE='DEMO_REG_OPERATOR'";
		   
		   PreparedStatement stmt = null;
		   ResultSet r = null;
			try
			{
				stmt = conn.prepareStatement(sql);
				
				r = stmt.executeQuery();
				while (r.next())
				{
					user=new UserDTO();
					
					user.setUserId(r.getString("USERID"));
					userList.add(user);
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
		
		
		return userList;
	}
	
	
}

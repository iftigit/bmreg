package org.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import oracle.jdbc.driver.OracleCallableStatement;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import org.table.AddressDTO;
import org.table.EducationDTO;
import org.table.ExperienceDTO;
import org.table.JobPreferenceDTO;
import org.table.LanguageDTO;
import org.table.LogDTO;
import org.table.NomineeDTO;
import org.table.PersonalInfoDTO;
import org.table.TrainingDTO;

import util.connection.ConnectionManager;

public class AddressDAO {


	public static ArrayList<AddressDTO> getAllDivision()
	{
		ArrayList<AddressDTO> divisionList=new ArrayList<AddressDTO>();
		
	 	   Connection conn = ConnectionManager.getConnection();
		   //String sql = "SELECT DIVISIONID,DIVISION_NAME FROM DIVISION ORDER BY DIVISIONID";
	 	  //String sql = "Select DIVN,NAM from ADDRESS where ZILA IS NULL ORDER BY NAM";
	 	  String sql = "Select DIVISIONID,DIVISION_NAME from DIVISION  ORDER BY DIVISION_NAME";
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   AddressDTO addressDto  = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				r = stmt.executeQuery();
				while (r.next())
				{
					addressDto=new AddressDTO();
					addressDto.setDivisionId(r.getString("DIVISIONID"));
					addressDto.setDivisionName(r.getString("DIVISION_NAME"));
					divisionList.add(addressDto);
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return divisionList;

	}
	
	public static ArrayList<AddressDTO> getAllDistrict()
	{
		ArrayList<AddressDTO> districtList=new ArrayList<AddressDTO>();
		
	 	   Connection conn = ConnectionManager.getConnection();
		   String sql = "Select DIST_ID,DIST_NAME from DISTRICT ORDER BY DIST_NAME";
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   AddressDTO addressDto  = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				r = stmt.executeQuery();
				while (r.next())
				{
					addressDto=new AddressDTO();
					addressDto.setDistrictName(r.getString("DIST_NAME"));
					addressDto.setDistrictId(r.getString("DIST_ID"));
					districtList.add(addressDto);
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
		return districtList;
	}
	
	public static ArrayList<AddressDTO> getAllUpazillaOrThana()
	{
		ArrayList<AddressDTO> districtList=new ArrayList<AddressDTO>();
		
	 	   Connection conn = ConnectionManager.getConnection();
		   String sql = "Select THANAID,THANA_NAME from THANA ORDER BY THANA_NAME";
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   AddressDTO addressDto  = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				r = stmt.executeQuery();
				while (r.next())
				{
					addressDto=new AddressDTO();
					addressDto.setUpazillaOrThanaId(r.getString("THANAID"));
					addressDto.setUpazillaOrThanaName(r.getString("THANA_NAME"));
					districtList.add(addressDto);
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
		return districtList;
	}
	
	public static ArrayList<AddressDTO> getAllUnionOrWard()
	{
		ArrayList<AddressDTO> districtList=new ArrayList<AddressDTO>();
		
	 	   Connection conn = ConnectionManager.getConnection();
		   String sql = "Select UNIONID,UNIONNAME from UNIONS ORDER BY UNIONNAME";
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   AddressDTO addressDto  = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				r = stmt.executeQuery();
				while (r.next())
				{
					addressDto=new AddressDTO();
					addressDto.setUnionOrWardId(r.getString("UNIONID"));
					addressDto.setUnionOrWardName(r.getString("UNIONNAME"));
					districtList.add(addressDto);
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
		return districtList;
	}
	public static ArrayList<AddressDTO> getAllMauzaOrMoholla()
	{
		ArrayList<AddressDTO> districtList=new ArrayList<AddressDTO>();
		
	 	   Connection conn = ConnectionManager.getConnection();
		   String sql = "Select MAUZAID,MAUZANAME from MAUZA ORDER BY MAUZANAME";
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   AddressDTO addressDto  = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				r = stmt.executeQuery();
				while (r.next())
				{
					addressDto=new AddressDTO();
					addressDto.setMauzaOrMohollaId(r.getString("MAUZAID"));
					addressDto.setMauzaOrMohollaName(r.getString("MAUZANAME"));
					districtList.add(addressDto);
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
		return districtList;
	}
	
	public static ArrayList<AddressDTO> getAllVillage()
	{
		ArrayList<AddressDTO> districtList=new ArrayList<AddressDTO>();
		
	 	   Connection conn = ConnectionManager.getConnection();
		   String sql = "Select VILLID,VILLNAME from VILLAGE ORDER BY VILLNAME";
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   AddressDTO addressDto  = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				r = stmt.executeQuery();
				while (r.next())
				{
					addressDto=new AddressDTO();
					addressDto.setVillageId(r.getString("VILLID"));
					addressDto.setVillageName(r.getString("VILLNAME"));
					districtList.add(addressDto);
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
		return districtList;
	}
	
	public String getDivisionNameFromId(int divId)
	{
	 	   Connection conn = ConnectionManager.getConnection();
		   String sql = "SELECT DIVISION_NAME FROM DIVISION WHERE DIVISIONID=?";
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   String divName="";
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, divId);
				r = stmt.executeQuery();
				if (r.next())
				{
					divName=r.getString("DIVISION_NAME");
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return divName;

	}
	
	public String getDistrictNameFromId(int distId)
	{
	 	   Connection conn = ConnectionManager.getConnection();
		   String sql = "Select DIST_NAME from DISTRICT Where dist_id=?";
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   String districtName="";
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, distId);
				r = stmt.executeQuery();
				if (r.next())
				{
					districtName=r.getString("DIST_NAME");
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return districtName;

	}
	

	public String getThanaNameFromId(String thanaId)
	{
	 	   Connection conn = ConnectionManager.getConnection();
		   String sql = "Select THANA_NAME from THANA where Thanaid=?";
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   String districtName="";
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, thanaId);
				r = stmt.executeQuery();
				if (r.next())
				{
					districtName=r.getString("THANA_NAME");
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return districtName;

	}
	
	public String getUnionNameFromId(String unionId)
	{
	 	   Connection conn = ConnectionManager.getConnection();
		   String sql = "Select UNIONNAME from UNIONS where UNIONID=?";
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   String unionName="";
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, unionId);
				r = stmt.executeQuery();
				if (r.next())
				{
					unionName=r.getString("UNIONNAME");
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return unionName;

	}
	
	
	

	
	
	public ArrayList<String> getDistrict(String divId)
	{
		ArrayList<String> distList=new ArrayList<String>();
		Connection conn = ConnectionManager.getConnection();
		//String sql = "Select ZILA,NAM from ADDRESS where UPZA IS NULL and zila is not NULL AND  divN='"+divId+"'  ORDER BY NAM";
		String sql = "Select DIST_ID,DIST_NAME from DISTRICT  Where DIVISIONID='"+divId+"'  ORDER BY DIST_NAME";

		Statement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.createStatement();
			r = stmt.executeQuery(sql);
			while (r.next())
			{
				distList.add(r.getString("DIST_NAME"));
				distList.add(r.getString("DIST_ID"));
			}
		} 
		catch (Exception e){e.printStackTrace();}
 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
			{e.printStackTrace();}stmt = null;conn = null;}

		return distList;
	}
	
	//public ArrayList<String> getUpazillaOrThana(String divisionId,String districtId)
	public ArrayList<String> getUpazillaOrThana(String districtId)
	{
		ArrayList<String> upzillaOrThanaList=new ArrayList<String>();
		Connection conn = ConnectionManager.getConnection();
		
		//String sql = " select UPZA,NAM from address where zila='"+districtId+"' and divn='"+divisionId+"' and upza is not null and psa is null order by nam ";
		//String sql = " select UPZA,NAM from address where zila='"+districtId+"'  and upza is not null and psa is null order by nam ";
		String sql = " select THANAID,THANA_NAME from THANA where DISTRICTID='"+districtId+"' order by THANA_NAME ";
		

		Statement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.createStatement();
			r = stmt.executeQuery(sql);
			while (r.next())
			{
				upzillaOrThanaList.add(r.getString("THANA_NAME"));
				upzillaOrThanaList.add(r.getString("THANAID"));
			}
		} 
		catch (Exception e){e.printStackTrace();}
 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
			{e.printStackTrace();}stmt = null;conn = null;}

		return upzillaOrThanaList;
	}
	
	//public ArrayList<String> getUnionOrWard(String divisionId,String districtId,String upazillaOrThanaId)
	public ArrayList<String> getUnionOrWard(String upazillaOrThanaId)
	{
		ArrayList<String> unionOrWardList=new ArrayList<String>();
		Connection conn = ConnectionManager.getConnection();
		
		String sql = " Select UNIONID,UNIONNAME from UNIONS where THANAID='"+upazillaOrThanaId+"' order by UNIONNAME ";

		Statement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.createStatement();
			r = stmt.executeQuery(sql);
			while (r.next())
			{
				unionOrWardList.add(r.getString("UNIONNAME"));
				unionOrWardList.add(r.getString("UNIONID"));
			}
		} 
		catch (Exception e){e.printStackTrace();}
 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
			{e.printStackTrace();}stmt = null;conn = null;}

		return unionOrWardList;
	}
	
	//public ArrayList<String> getMauzaOrMoholla(String divisionId,String districtId,String upazillaOrThanaId,String unionOrWardId)
	public ArrayList<String> getMauzaOrMoholla(String unionOrWardId)
	{
		ArrayList<String> mauzaOrMohollahList=new ArrayList<String>();
		Connection conn = ConnectionManager.getConnection();
		
		String sql = " Select MAUZAID,MAUZANAME from MAUZA where UNIONID='"+unionOrWardId+"'  order by MAUZANAME ";

		Statement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.createStatement();
			r = stmt.executeQuery(sql);
			while (r.next())
			{
				mauzaOrMohollahList.add(r.getString("MAUZANAME"));
				mauzaOrMohollahList.add(r.getString("MAUZAID"));
			}
		} 
		catch (Exception e){e.printStackTrace();}
 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
			{e.printStackTrace();}stmt = null;conn = null;}

		return mauzaOrMohollahList;
	}
	
	//public ArrayList<String> getVillage(String divisionId,String districtId,String upazillaOrThanaId,String unionOrWardId,String mauzaOrMoholla)
	public ArrayList<String> getVillage(String mauzaOrMoholla)
	{
		ArrayList<String> villageList=new ArrayList<String>();
		Connection conn = ConnectionManager.getConnection();
		
		String sql = " Select VILLID,VILLNAME from VILLAGE where MAUZAID='"+mauzaOrMoholla+"'   order by VILLNAME ";

		Statement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.createStatement();
			r = stmt.executeQuery(sql);
			while (r.next())
			{
				villageList.add(r.getString("VILLNAME"));
				villageList.add(r.getString("VILLID"));
			}
		} 
		catch (Exception e){e.printStackTrace();}
 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
			{e.printStackTrace();}stmt = null;conn = null;}

		return villageList;
	}
	
	public String insertNewAddress(String requestType,String divisionId,String districtId,String upazilaId,String upazilaName,
			                       String unionId, String unionName,String mauzaId,String mauzaName,
			                       String villageName) 
	{	     
		String response="";
		Connection conn = ConnectionManager.getConnection();
		OracleCallableStatement stmt=null;

	try {
		System.out.println("Procedure Insert_New_Address Begins");
		stmt = (OracleCallableStatement) conn.prepareCall(
		  "{ call Insert_New_Address(?,?,?,?,?,?,?,?,?,?,?) }");

		stmt.setString(1,  requestType);
		stmt.setString(2,  divisionId);
		stmt.setString(3,  districtId);
		stmt.setString(4,  upazilaId);
		stmt.setString(5,  upazilaName);
		stmt.setString(6,  unionId);
		stmt.setString(7,  unionName);
		stmt.setString(8,  mauzaId);
		stmt.setString(9,  mauzaName);
		stmt.setString(10,  villageName);
		
		stmt.registerOutParameter(11, java.sql.Types.VARCHAR);
		stmt.executeUpdate();
		response = (stmt.getString(11)).trim();
		System.out.println("Response : " + response);	
	}
	catch (Exception e){e.printStackTrace();return response;}
	finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
	{e.printStackTrace();}stmt = null;conn = null;}

	return response;	           
  }
	
	
}
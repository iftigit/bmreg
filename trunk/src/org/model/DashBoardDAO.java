package org.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.table.CotaDTO;

import util.connection.ConnectionManager;

public class DashBoardDAO {
	
	 DecimalFormat df = new DecimalFormat("#,##,##,###.##");
	public String getDivisionWiseStat(String divisionId,String divisionName)
	{		
		Connection conn = ConnectionManager.getConnection();
		String sql = " SELECT   dist_name, dist_id, divisionid, total " +
				     " FROM district " +
				     " LEFT OUTER JOIN " +
				     " (SELECT   PDISTRICT, COUNT (*) total " +
				     " FROM EMP_ADDRESS " +
				     " WHERE PDIVISION = ? " +
				     " GROUP BY PDISTRICT) tmp1 ON district.dist_id = tmp1.PDISTRICT " +
				     " WHERE divisionid = ? " +
				     " ORDER BY dist_name ";

		System.out.println("Stat by District (sql) :" + sql);
		PreparedStatement stmt = null;
		ResultSet r = null;
		double totalReg=0;
		double totalAllRegCount=getTotalRegCount();
		String response=" <p class='statLine1'> " +
						" Registration Statistics for <font style='color:blue'> <b>"+divisionName+"</b> </font> Division.<br/><font style='color:#006006'>[Country Total="+df.format(totalAllRegCount)+"]</font>" +
						" </p>";
			   response+=" <table class='divisionStatTable' width='100%'> " +
						" 	<tr> " +
						"     <th>District</th><th>Total Registered Jobseeker</th> " +
						"	</tr>";
		try
		{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, divisionId);
			stmt.setString(2, divisionId);
			r = stmt.executeQuery();
			while (r.next())
			{

				response+="<tr onclick=\"loadDistrictStatistics("+r.getString("dist_id")+",'"+r.getString("dist_name")+"')\">";
				response+="<td width='60%' align='left' style='padding-right:10px;'>"+r.getString("dist_name")+"</td>";
				if(r.getInt("total")==0)
					response+="<td class='redCell' width='40%'align='right' style='padding-right:10px;'>"+r.getInt("total")+"</td>";
				else
					response+="<td width='40%' align='right' style='padding-right:10px;'>"+r.getString("total")+"</td>";
	
				
				response+="</tr>";	
				totalReg+=r.getInt("total");
			}
			response+="<tr><td align='right'><b>Total</b></td><td align='right' style='padding-right:10px;font-weight:bold;'>"+df.format(totalReg)+"</td></tr>";
			response+="</table>";
		}
		
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}

		return response;
	}
	
	public String divisionWiseResultStat(String divisionId,String divisionName)
	{		
		Connection conn = ConnectionManager.getConnection();
		String sql = "Select dist_name,dist_id,divisionid,total From DISTRICT Left Outer Join (  " +
		"Select DIST,count(*) total from SECONDLOTTERY_T1 Where DIV=? Group by DIST)tmp1 On District.DIST_ID=tmp1.DIST " +
		"Where divisionid=? Order by Dist_Name ";

		System.out.println("Stat by District (sql) :" + sql);
		PreparedStatement stmt = null;
		ResultSet r = null;
		double totalReg=0;
		double totalAllRegCount=getTotalSelectedCount();
//		/double totalAllRegCount=0;
		String response=" <p class='statLine1'> " +
						" Lottery Result Statistics for <font style='color:blue'> <b>"+divisionName+"</b> </font> Division.<br/><font style='color:#006006'>[Country Total="+df.format(totalAllRegCount)+"]</font>" +
						" </p>";
			   response+=" <table class='divisionStatTable' width='100%'> " +
						" 	<tr> " +
						"     <th>District</th><th>Total Selected Jobseeker</th> " +
						"	</tr>";
		try
		{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, divisionId);
			stmt.setString(2, divisionId);
			r = stmt.executeQuery();
			while (r.next())
			{

				response+="<tr onclick=\"loadDistrictStatistics("+r.getString("dist_id")+",'"+r.getString("dist_name")+"')\">";
				response+="<td width='60%' align='left' style='padding-right:10px;'>"+r.getString("dist_name")+"</td>";
				if(r.getInt("total")==0)
					response+="<td class='redCell' width='40%'align='right' style='padding-right:10px;'>"+r.getInt("total")+"</td>";
				else
					response+="<td width='40%' align='right' style='padding-right:10px;'>"+r.getString("total")+"</td>";
	
				
				response+="</tr>";	
				totalReg+=r.getInt("total");
			}
			response+="<tr><td align='right'><b>Total</b></td><td align='right' style='padding-right:10px;font-weight:bold;'>"+df.format(totalReg)+"</td></tr>";
			response+="</table>";
		}
		
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}

		return response;
	}
	
	
	public String getDistrictWiseStat(String districtId,String districtName)
	{		
		
		Connection conn = ConnectionManager.getConnection();
		String sql = " SELECT thana_name, thanaid, districtid, total " +
				     "   FROM thana " +
				     " LEFT OUTER JOIN  " +
				     " (SELECT   PUPAZILA_OR_THANA, COUNT (*) total " +
				     " FROM EMP_ADDRESS " +
				     " WHERE PDISTRICT = ? " +
				     " GROUP BY PUPAZILA_OR_THANA) tmp1 ON thana.thanaid = tmp1.PUPAZILA_OR_THANA " +
				     " WHERE districtid = ?";

		System.out.println("Stat by Upazilla (sql) :" + sql);
		PreparedStatement stmt = null;
		ResultSet r = null;
		double totalReg=0;
		String response=" <p class='statLine1'> " +
						" Registration Statistics for <font style='color:blue'> "+districtName+" </font> District" +
						" </p>";

		      response+=" <table class='divisionStatTable' width='100%'> " +
						" 	<tr> " +
						"     <th>Upazilla</th><th>Total Registered Jobseeker</th> " +
						"	</tr>";
		try
		{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, districtId);
			stmt.setString(2, districtId);
			r = stmt.executeQuery();
			while (r.next())
			{
				response+="<tr onclick=\"loadThanaStatistics("+r.getString("THANAID")+",'"+r.getString("THANA_name")+"')\">";
				response+="<td align='left' style='padding-right:10px;'>"+r.getString("thana_name")+"</td>";
				if(r.getInt("total")==0)
					response+="<td class='redCell' width='40%'align='right' style='padding-right:10px;'>"+r.getInt("total")+"</td>";
				else
					response+="<td width='40%' align='right' style='padding-right:10px;'>"+r.getString("total")+"</td>";
				response+="</tr>";	
				totalReg+=r.getInt("total");
			}
			response+="<tr><td align='right'><b>Total</b></td><td align='right' style='padding-right:10px;font-weight:bold;'>"+df.format(totalReg)+"</td></tr>";
			response+="</table>";
		}
		
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}

		return response;
	}
	
	public String getDistrictWiseResultStat(String districtId,String districtName)
	{		
		
		Connection conn = ConnectionManager.getConnection();
		String sql = "  Select THANA_name,THANAID,DISTRICTID,total From THANA  " +
					" Left Outer Join (  " +
					" Select thana,count(*) total from  SECONDLOTTERY_T1 Where dist=? " +
					" Group by thana)tmp1 On THANA.THANAID=tmp1.thana  " +
					" Where DISTRICTID=? ";

		System.out.println("Stat by Upazilla (sql) :" + sql);
		PreparedStatement stmt = null;
		ResultSet r = null;
		double totalReg=0;
		String response=" <p class='statLine1'> " +
						" Lottery Result Statistics for <font style='color:blue'> "+districtName+" </font> District" +
						" </p>";

		      response+=" <table class='divisionStatTable' width='100%'> " +
						" 	<tr> " +
						"     <th>Upazilla/Thana</th><th>Total Selected Jobseeker</th> " +
						"	</tr>";
		try
		{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, districtId);
			stmt.setString(2, districtId);
			r = stmt.executeQuery();
			while (r.next())
			{
				response+="<tr onclick=\"loadThanaStatistics("+r.getString("THANAID")+",'"+r.getString("THANA_name")+"')\">";
				response+="<td align='left' style='padding-right:10px;'>"+r.getString("thana_name")+"</td>";
				if(r.getInt("total")==0)
					response+="<td class='redCell' width='40%'align='right' style='padding-right:10px;'>"+r.getInt("total")+"</td>";
				else
					response+="<td width='40%' align='right' style='padding-right:10px;'>"+r.getString("total")+"</td>";
				response+="</tr>";	
				totalReg+=r.getInt("total");
			}
			response+="<tr><td align='right'><b>Total</b></td><td align='right' style='padding-right:10px;font-weight:bold;'>"+df.format(totalReg)+"</td></tr>";
			response+="</table>";
		}
		
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}

		return response;
	}
	
	
	
	public String getThanaWiseStat(String thanaId,String thanaName)
	{		
		
		Connection conn = ConnectionManager.getConnection();
		String sql = " SELECT unionname, unionid, thanaid, total " +
				     "  FROM unions " +
				     " LEFT OUTER JOIN " +
				     " (SELECT   PUNION_OR_WARD, COUNT (*) total  " +
				     "  FROM EMP_ADDRESS " +
				     "  WHERE PUNION_OR_WARD = ? " +
				     "  GROUP BY PUNION_OR_WARD) tmp1 ON unions.unionid = tmp1.PUNION_OR_WARD " +
				     "  WHERE thanaid = ?";

		System.out.println("Stat by Union (sql) :" + sql);
		PreparedStatement stmt = null;
		ResultSet r = null;
		double totalReg=0;
		String response=" <p class='statLine1'> " +
						" Registration Statistics for <font style='color:blue'> "+thanaName+" </font> Upazilla/Thana" +
						" </p>";

		      response+=" <table class='divisionStatTable' width='100%'> " +
						" 	<tr> " +
						"     <th>Union/Ward</th><th>Total Registered Jobseeker</th> " +
						"	</tr>";
		try
		{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, thanaId);
			stmt.setString(2, thanaId);
			r = stmt.executeQuery();
			while (r.next())
			{
				response+="<tr>";
				response+="<td align='left' style='padding-right:10px;'>"+r.getString("UNIONNAME")+"</td>";
				if(r.getInt("total")==0)
					response+="<td class='redCell' width='40%'align='right' style='padding-right:10px;'>"+r.getInt("total")+"</td>";
				else
					response+="<td width='40%' align='right' style='padding-right:10px;'>"+r.getString("total")+"</td>";
				response+="</tr>";	
				totalReg+=r.getInt("total");
			}
			response+="<tr><td align='right'><b>Total</b></td><td align='right' style='padding-right:10px;font-weight:bold;'>"+df.format(totalReg)+"</td></tr>";
			response+="</table>";
		}
		
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}

		return response;
	}
	
	public String getThanaWiseResultStat(String thanaId,String thanaName)
	{		
		
		Connection conn = ConnectionManager.getConnection();
		String sql = "Select UNIONNAME,UNIONID,THANAID,total From UNIONS  " +
		"Left Outer Join (  " +
		"Select unions,count(*) total from SECONDLOTTERY_T1 Where thana=? " +
		"Group by unions)tmp1   On UNIONS.UNIONID =tmp1.unions  " +
		"Where THANAID=? ";

		System.out.println("Stat by Union (sql) :" + sql);
		PreparedStatement stmt = null;
		ResultSet r = null;
		double totalReg=0;
		String response=" <p class='statLine1'> " +
						" Lottery Result Statistics for <font style='color:blue'> "+thanaName+" </font> Upazilla" +
						" </p>";

		      response+=" <table class='divisionStatTable' width='100%'> " +
						" 	<tr> " +
						"     <th>Union/Ward</th><th>Total Selected Jobseeker</th> " +
						"	</tr>";
		try
		{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, thanaId);
			stmt.setString(2, thanaId);
			r = stmt.executeQuery();
			while (r.next())
			{
				response+="<tr>";
				response+="<td align='left' style='padding-right:10px;'>"+r.getString("UNIONNAME")+"</td>";
				if(r.getInt("total")==0)
					response+="<td class='redCell' width='40%'align='right' style='padding-right:10px;'>"+r.getInt("total")+"</td>";
				else
					response+="<td width='40%' align='right' style='padding-right:10px;'>"+r.getString("total")+"</td>";
				response+="</tr>";	
				totalReg+=r.getInt("total");
			}
			response+="<tr><td align='right'><b>Total</b></td><td align='right' style='padding-right:10px;font-weight:bold;'>"+df.format(totalReg)+"</td></tr>";
			response+="</table>";
		}
		
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}

		return response;
	}
	
	public String getRefreshSelectBoxDivision(int refreshRate)
	{
		String selectBox=" <select onchange='refreshDivisionStat(this.value)' style='width:100px;border:1px solid grey;'> " +
						 " <option value='10000' "+(refreshRate==10000?"selected='selected'":"")+">10</option> " +
						 " <option value='30000' "+(refreshRate==30000?"selected='selected'":"")+">30</option> " +
						 " <option value='60000' "+(refreshRate==60000?"selected='selected'":"")+">60</option> " +
						 " <option value='0' "+(refreshRate==0?"selected='selected'":"")+">Never</option> " +
						 " </select>";
		
		return selectBox;
	}
	public String getRefreshSelectBoxDistrict(int refreshRate)
	{
		String selectBox=" <select onchange='refreshDistrictStat(this.value)' style='width:100px;border:1px solid grey;'> " +
						 " <option value='10000' "+(refreshRate==10000?"selected='selected'":"")+">10</option> " +
						 " <option value='30000' "+(refreshRate==30000?"selected='selected'":"")+">30</option> " +
						 " <option value='60000' "+(refreshRate==60000?"selected='selected'":"")+">60</option> " +
						 " <option value='0' "+(refreshRate==0?"selected='selected'":"")+">Never</option> " +
						 " </select>";
		
		return selectBox;
	}
	
	public double getTotalRegCount()
	{		
		Connection conn = ConnectionManager.getConnection();
		String sql = " Select count(*) total from JOBSEEKER ";

		System.out.println("Stat by Total (sql) :" + sql);
		PreparedStatement stmt = null;
		ResultSet r = null;
		double totalRegCount=0;
		try
		{
			stmt = conn.prepareStatement(sql);
			r = stmt.executeQuery();
			if (r.next())
			{

				totalRegCount=r.getDouble("total");
			}
			
		}
		
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}

		return totalRegCount;
	}
	
	public double getTotalSelectedCount()
	{		
		Connection conn = ConnectionManager.getConnection();
		String sql = " Select count(*) total from SECONDLOTTERY_T1 ";

		System.out.println("Stat by Total (sql) :" + sql);
		PreparedStatement stmt = null;
		ResultSet r = null;
		double totalRegCount=0;
		try
		{
			stmt = conn.prepareStatement(sql);
			r = stmt.executeQuery();
			if (r.next())
			{

				totalRegCount=r.getDouble("total");
			}
			
		}
		
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}

		return totalRegCount;
	}
	
}

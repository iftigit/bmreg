package org.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.table.TtcDTO;

import util.connection.ConnectionManager;

public class TtcDAO {
	public static ArrayList<TtcDTO> getAllTtc()
	{
		   ArrayList<TtcDTO> ttcList=new ArrayList<TtcDTO>();
	 	   Connection conn = ConnectionManager.getConnection();
	 	   String sql="Select ID,NAME from MST_TTC";
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   TtcDTO ttcDto  = null;
			try
			{
				stmt = conn.prepareStatement(sql);
				r = stmt.executeQuery();
				while (r.next())
				{
					ttcDto=new TtcDTO();
					ttcDto.setTtcId(r.getInt("ID"));
					ttcDto.setTtcName(r.getString("NAME"));
					ttcList.add(ttcDto);
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		return ttcList;
	}
	public ArrayList<String> getTrade(String ttcTd)
	{
		ArrayList<String> tradeList=new ArrayList<String>();
		Connection conn = ConnectionManager.getConnection();
		//String sql = "Select ZILA,NAM from ADDRESS where UPZA IS NULL and zila is not NULL AND  divN='"+divId+"'  ORDER BY NAM";
		String sql = "Select ID,NAME from MST_TTC_TRADE  Where TTID='"+ttcTd+"'  ORDER BY ID";

		Statement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.createStatement();
			r = stmt.executeQuery(sql);
			while (r.next())
			{
				tradeList.add(r.getString("NAME"));
				tradeList.add(r.getString("ID"));
			}
		} 
		catch (Exception e){e.printStackTrace();}
 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
			{e.printStackTrace();}stmt = null;conn = null;}

		return tradeList;
	}
}

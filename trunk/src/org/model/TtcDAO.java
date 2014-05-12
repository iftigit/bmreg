package org.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.table.CountryDTO;
import org.table.RecruitingAgencyDTO;
import org.table.TtcDTO;
import org.table.TtcTradeDTO;

import util.connection.ConnectionManager;

public class TtcDAO {
	public static ArrayList<TtcDTO> getAllTtc()
	{
		   ArrayList<TtcDTO> ttcList=new ArrayList<TtcDTO>();
	 	   Connection conn = ConnectionManager.getConnection();
	 	   String sql="Select * from MST_TTC";
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
					ttcDto.setTtcPrincipal(r.getString("PRINCIPAL"));
					ttcDto.setAddress(r.getString("ADDRESS"));
					ttcDto.setPhone(r.getString("TEL"));
					ttcDto.setEmailAddress(r.getString("EMAIL"));
					ttcList.add(ttcDto);
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		return ttcList;
	}
	public static ArrayList<TtcDTO> getTTC(String ttcId)
	{
		   TtcDTO ttcDto  = null;
		   ArrayList<TtcDTO> ttcList=new ArrayList<TtcDTO>();
	 	   Connection conn = ConnectionManager.getConnection();
	 	   String sql="";
	 	   if(ttcId.equalsIgnoreCase("all"))
	 		   sql = "Select * from MST_TTC";
	 	   else
	 		  sql = "Select * from MST_TTC where id="+ttcId;
	 	   
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				r = stmt.executeQuery();
				while (r.next())
				{
					ttcDto=new TtcDTO();
					ttcDto.setTtcId(r.getInt("ID"));
					ttcDto.setTtcName(r.getString("NAME"));
					ttcDto.setTtcPrincipal(r.getString("PRINCIPAL"));
					ttcDto.setAddress(r.getString("ADDRESS"));
					ttcDto.setPhone(r.getString("TEL"));
					ttcDto.setEmailAddress(r.getString("EMAIL"));
					ttcList.add(ttcDto);
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return ttcList;

	}
	
	public static ArrayList<TtcTradeDTO> getTrade(String tradeId)
	{
		   TtcTradeDTO trade  = null;
		   ArrayList<TtcTradeDTO> tradeList=new ArrayList<TtcTradeDTO>();
	 	   Connection conn = ConnectionManager.getConnection();
	 	   String sql="";
	 	   if(tradeId.equalsIgnoreCase("all"))
	 		   sql = "Select * from MST_TRADE";
	 	   else
	 		  sql = "Select * from MST_TRADE where id="+tradeId;
	 	   
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				r = stmt.executeQuery();
				while (r.next())
				{
					 trade=new TtcTradeDTO();
					 trade.setId(r.getInt("ID"));
					 trade.setTradeName(r.getString("TRADE_NAME"));
					 tradeList.add(trade);
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return tradeList;

	}
	
	public ArrayList<String> getTradeForTTC(String ttcId)
	{
		ArrayList<String> tradeList=new ArrayList<String>();
		Connection conn = ConnectionManager.getConnection();
		String sql = "SELECT * FROM TTC_TRADE_MAP,MST_TRADE WHERE TTC_TRADE_MAP.TRADE_ID=MST_TRADE.ID AND TTC_TRADE_MAP.TTC_ID="+ttcId+" TRADE_ID";

		Statement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.createStatement();
			r = stmt.executeQuery(sql);
			while (r.next())
			{
				tradeList.add(r.getString("TRADE_NAME"));
				tradeList.add(r.getString("TRADE_ID"));
			}
		} 
		catch (Exception e){e.printStackTrace();}
 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
			{e.printStackTrace();}stmt = null;conn = null;}

		return tradeList;
	}
	
	public boolean createNewTTC(TtcDTO ttc)
	{
		Connection conn = ConnectionManager.getConnection();
	 	   String sql = " Insert into MST_TTC(ID,NAME,PRINCIPAL,ADDRESS,TEL,EMAIL)  " +
	 	   		        " Values(SQN_TTC.NEXTVAL,?,?,?,?,?)";
		   PreparedStatement stmt = null;
		   boolean resp=false;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, ttc.getTtcName());
				stmt.setString(2, ttc.getTtcPrincipal());
				stmt.setString(3, ttc.getAddress());
				stmt.setString(4, ttc.getPhone());
				stmt.setString(5, ttc.getEmailAddress());
				if(stmt.executeUpdate()==1)
					resp=true;
					
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return resp;

	}
	
	public boolean createNewTrade(TtcTradeDTO trade)
	{
		Connection conn = ConnectionManager.getConnection();
	 	   String sql = " Insert into MST_TRADE(ID,TRADE_NAME)  " +
	 	   		        " Values(SQN_TRADE.NEXTVAL,?)";
		   PreparedStatement stmt = null;
		   boolean resp=false;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, trade.getTradeName());
				if(stmt.executeUpdate()==1)
					resp=true;
					
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return resp;

	}
	
	public TtcDTO getTTCbyName(String ttcName)
	{
	 	   Connection conn = ConnectionManager.getConnection();
	 	   String sql="Select * from MST_TTC  where lower(Name) =lower('"+ttcName+"')";
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
					ttcDto.setTtcPrincipal(r.getString("PRINCIPAL"));
					ttcDto.setAddress(r.getString("ADDRESS"));
					ttcDto.setPhone(r.getString("TEL"));
					ttcDto.setEmailAddress(r.getString("EMAIL"));
					
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		return ttcDto;
	}
	public TtcTradeDTO getTradebyName(String tradeName)
	{
	 	   Connection conn = ConnectionManager.getConnection();
	 	   String sql="Select * from MST_Trade  where lower(TRADE_NAME) =lower('"+tradeName+"')";
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   TtcTradeDTO tradeDdt  = null;
			try
			{
				stmt = conn.prepareStatement(sql);
				r = stmt.executeQuery();
				while (r.next())
				{
					tradeDdt=new TtcTradeDTO();
					tradeDdt.setTtcId(r.getInt("ID"));
					tradeDdt.setTtcName(r.getString("TRADE_NAME"));
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		return tradeDdt;
	}
	
	public static boolean updateTTC(TtcDTO ttcDto)
	{
		Connection conn = ConnectionManager.getConnection();
 	    String sql = " Update  MST_TTC set NAME=?,PRINCIPAL=?,ADDRESS=?,TEL=?,EMAIL=? where id=?";
		PreparedStatement stmt = null;
		boolean resp=false;
		   
		try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, ttcDto.getTtcName());
				stmt.setString(2, ttcDto.getTtcPrincipal());
				stmt.setString(3, ttcDto.getAddress());
				stmt.setString(4, ttcDto.getPhone());
				stmt.setString(5, ttcDto.getEmailAddress());
				stmt.setInt(6, ttcDto.getTtcId());
				if(stmt.executeUpdate()==1)
					resp=true;
					
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return resp;

	}
	public static boolean updateTrade(TtcTradeDTO trade)
	{
		Connection conn = ConnectionManager.getConnection();
 	    String sql = " Update  MST_TRADE set TRADE_NAME=? WHERE ID=?";
		PreparedStatement stmt = null;
		boolean resp=false;
		   
		try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, trade.getTradeName());
				stmt.setInt(2, trade.getId());
				if(stmt.executeUpdate()==1)
					resp=true;
					
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return resp;

	}
	
	public static ArrayList<TtcTradeDTO> getTtcTradeMap(String ttcId)
	{
		   TtcTradeDTO trade  = null;
		   ArrayList<TtcTradeDTO> tradeList=new ArrayList<TtcTradeDTO>();
	 	   Connection conn = ConnectionManager.getConnection();
   		   String sql = " Select * from mst_trade left join ttc_trade_map on mst_trade.ID=ttc_trade_map.TRADE_ID and TTC_ID="+ttcId;
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				r = stmt.executeQuery();
				while (r.next())
				{
					 trade=new TtcTradeDTO();
					 trade.setId(r.getInt("ID"));
					 trade.setTradeName(r.getString("TRADE_NAME"));
					 trade.setTtcId(r.getInt("TTC_ID"));
					 tradeList.add(trade);
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return tradeList;

	}
	
	public static boolean insertTtcTradeMap(int ttcId,List<TtcTradeDTO> tradeList)
	{
		
		  Connection conn = ConnectionManager.getConnection();
		   String sql = " insert into ttc_trade_map values(?,?)";
		   int[] operation=null;
		   PreparedStatement stmt = null;
			try
			{
				stmt = conn.prepareStatement(sql);
				for(int i=0;i<tradeList.size();i++){
					stmt.setInt(1, ttcId);
				    stmt.setInt(2, tradeList.get(i).getId());	
				    stmt.addBatch();
				}
			    
			    operation=stmt.executeBatch();
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
		
		if(operation.length==tradeList.size())
			return true;
		else
			return false;
	}
	public static boolean deleteTtcTradeMap(int ttcId)
	{
		
	 	   Connection conn = ConnectionManager.getConnection();
	 	   String sql = "Delete TTC_TRADE_MAP Where TTC_ID=?";
		   PreparedStatement stmt = null;
		   boolean resp=false;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, ttcId);
				stmt.execute();
				resp=true;
					
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return resp;

	}
	
}

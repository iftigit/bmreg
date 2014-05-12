package org.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import org.table.DemoDTO;

import util.connection.ConnectionManager;

public class DemoDAO {

	public ArrayList<DemoDTO> getDemoList()
	 {
		   Connection conn = ConnectionManager.getConnection();
		   String sql = "  Select MST_DEMO.*,DIST_NAME from MST_DEMO,DISTRICT Where MST_DEMO.DEMO_DISTRICT_ID=DISTRICT.DIST_ID  order by demo_name"; 
		     
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   ArrayList<DemoDTO> demoList=null;
		   DemoDTO demoDTO  = null;

		   
			try
			{
				stmt = conn.prepareStatement(sql);
				r = stmt.executeQuery();
				int count=0;
				while (r.next())
				{
					if(count==0)
						demoList=new ArrayList<DemoDTO>();
					
					demoDTO=new DemoDTO();

					demoDTO.setDemoId(r.getInt("DEMO_ID"));
					demoDTO.setDemoName(r.getString("DEMO_NAME"));
					demoDTO.setDemoDistrictId(r.getInt("DEMO_DISTRICT_ID"));
					demoDTO.setDemoDistrictName(r.getString("DIST_NAME"));
					
					demoList.add(demoDTO);
					count++;
					
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 		return demoList;
	 }
	
	public HashMap<Integer,ArrayList<DemoDTO>> getDemoWiseTotalRegAmount(String fromDate,String toDate,String demoId)
	 {
		   Connection conn = ConnectionManager.getConnection();
		   String sql = "  Select MAX(MST_DEMO.DEMO_ID) DEMO_ID,MAX(MST_DEMO.DEMO_NAME) DEMO_NAME,to_char(PAYMENT_DATE,'dd-MM-YYYY') PAYMENT_DATE,COUNT(EMP_REG_PAYMENT.JOBSEEKERID) total_reg,SUM(PAYMENT_AMOUNT) total_amount from EMP_REG_PAYMENT,EMP_REG_LOG,MST_USER,MST_DEMO " +
		   				"  Where Payment_Date>=to_date('"+fromDate+"','dd-MM-YYYY') and Payment_Date<=to_date('"+toDate+"','dd-MM-YYYY') and Payment_Method='Token' " +
		   				"  And EMP_REG_PAYMENT.JOBSEEKERID=EMP_REG_LOG.JOBSEEKERID " +
		   				"  And EMP_REG_LOG.REG_BY=MST_USER.USERID " +
		   				"  And MST_DEMO.DEMO_ID=MST_USER.DEMO_ID ";
		   if(!demoId.equalsIgnoreCase("-9"))
			   sql+=" And MST_DEMO.DEMO_ID="+demoId;
		   
		   	   sql+=" GROUP BY to_char(PAYMENT_DATE,'dd-MM-YYYY') ORDER BY DEMO_NAME";
		   	   
		     
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   HashMap<Integer,ArrayList<DemoDTO>> demoHashMap=null;
		   ArrayList<DemoDTO> demoList  = null;
		   DemoDTO demoDTO=null;

		   
			try
			{
				stmt = conn.prepareStatement(sql);
				r = stmt.executeQuery();
				int count=0;
				int preDemo=0;
				int previousLoopDemoId=0;
				while (r.next())
				{
					if(count==0)
						demoHashMap=new HashMap<Integer,ArrayList<DemoDTO>>();
					
					if(preDemo!=r.getInt("DEMO_ID")){
						
						if(preDemo!=0)
							demoHashMap.put(previousLoopDemoId, demoList);
						
						demoList=new ArrayList<DemoDTO>();
						demoDTO=new DemoDTO();						
						demoDTO.setDemoId(r.getInt("DEMO_ID"));
						demoDTO.setDemoName(r.getString("DEMO_NAME"));
						demoDTO.setPaymentDate(r.getString("PAYMENT_DATE"));
						demoDTO.setTotalRegistration(r.getInt("TOTAL_REG"));
						demoDTO.setTotalAmount(r.getInt("TOTAL_AMOUNT"));
						demoList.add(demoDTO);
						
					}
					else{
												
						demoDTO=new DemoDTO();						
						demoDTO.setDemoId(r.getInt("DEMO_ID"));
						demoDTO.setDemoName(r.getString("DEMO_NAME"));
						demoDTO.setPaymentDate(r.getString("PAYMENT_DATE"));
						demoDTO.setTotalRegistration(r.getInt("TOTAL_REG"));
						demoDTO.setTotalAmount(r.getInt("TOTAL_AMOUNT"));
						demoList.add(demoDTO);
						
					}
					count++;
					previousLoopDemoId=r.getInt("DEMO_ID");
					preDemo=r.getInt("DEMO_ID");
										
				}
				
				if(count>0)
					demoHashMap.put(previousLoopDemoId, demoList);
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 		return demoHashMap;
	 }
	
	public HashMap<Integer,ArrayList<DemoDTO>> getDemoUserWiseTotalRegAmount(String fromDate,String toDate,String demoId)
	 {
		   Connection conn = ConnectionManager.getConnection();
		   String sql = "   Select MAX(MST_DEMO.DEMO_ID) DEMO_ID,MAX(MST_DEMO.DEMO_NAME) DEMO_NAME,MAX(MST_USER.FULL_NAME) FULL_NAME, MAX(MST_USER.DESIGNATION) DESIGNATION,to_char(PAYMENT_DATE,'dd-MM-YYYY') PAYMENT_DATE,COUNT(EMP_REG_PAYMENT.JOBSEEKERID) total_reg,SUM(PAYMENT_AMOUNT) total_amount " +
		   				"   from EMP_REG_PAYMENT,EMP_REG_LOG,MST_USER,MST_DEMO " +
		   				"   Where Payment_Date>=to_date('"+fromDate+"','dd-MM-YYYY') and Payment_Date<=to_date('"+toDate+"','dd-MM-YYYY') and Payment_Method='Token' " +
		   				"   And EMP_REG_PAYMENT.JOBSEEKERID=EMP_REG_LOG.JOBSEEKERID " +
		   				"   And EMP_REG_LOG.REG_BY=MST_USER.USERID " +
		   				"   And MST_DEMO.DEMO_ID=MST_USER.DEMO_ID ";
		   
		   if(!demoId.equalsIgnoreCase("-9"))
			   sql+=" And MST_DEMO.DEMO_ID="+demoId;
		   
		   	   sql+="   GROUP BY to_char(PAYMENT_DATE,'dd-MM-YYYY'),EMP_REG_LOG.REG_BY ORDER BY DEMO_NAME";
		   				 
		     
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   HashMap<Integer,ArrayList<DemoDTO>> demoHashMap=null;
		   ArrayList<DemoDTO> demoList  = null;
		   DemoDTO demoDTO=null;

		   
			try
			{
				stmt = conn.prepareStatement(sql);
				r = stmt.executeQuery();
				int count=0;
				int preDemo=0;
				int previousLoopDemoId=0;
				while (r.next())
				{
					if(count==0)
						demoHashMap=new HashMap<Integer,ArrayList<DemoDTO>>();
					
					if(preDemo!=r.getInt("DEMO_ID")){
						
						if(preDemo!=0)
							demoHashMap.put(previousLoopDemoId, demoList);
						
						demoList=new ArrayList<DemoDTO>();
						demoDTO=new DemoDTO();						
						demoDTO.setDemoId(r.getInt("DEMO_ID"));
						demoDTO.setDemoName(r.getString("DEMO_NAME"));
						demoDTO.setPaymentDate(r.getString("PAYMENT_DATE"));
						demoDTO.setUserFullName(r.getString("FULL_NAME"));
						demoDTO.setUserDesignation(r.getString("DESIGNATION"));
						demoDTO.setTotalRegistration(r.getInt("TOTAL_REG"));
						demoDTO.setTotalAmount(r.getInt("TOTAL_AMOUNT"));
						demoList.add(demoDTO);
						
						
						
					}
					else{
												
						demoDTO=new DemoDTO();						
						demoDTO.setDemoId(r.getInt("DEMO_ID"));
						demoDTO.setDemoName(r.getString("DEMO_NAME"));
						demoDTO.setPaymentDate(r.getString("PAYMENT_DATE"));
						demoDTO.setUserFullName(r.getString("FULL_NAME"));
						demoDTO.setUserDesignation(r.getString("DESIGNATION"));
						demoDTO.setTotalRegistration(r.getInt("TOTAL_REG"));
						demoDTO.setTotalAmount(r.getInt("TOTAL_AMOUNT"));
						demoList.add(demoDTO);
						
					}
					count++;
					previousLoopDemoId=r.getInt("DEMO_ID");
					preDemo=r.getInt("DEMO_ID");	
				}
				
				if(count>0)
					demoHashMap.put(previousLoopDemoId, demoList);
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 		return demoHashMap;
	 }
	
	public HashMap<Integer,ArrayList<DemoDTO>> getPayOrderList(String fromDate,String toDate,String demoId)
	 {
		   Connection conn = ConnectionManager.getConnection();
		   String sql = "  Select mst_demo.demo_id,mst_demo.demo_name,to_char(inserted_on,'dd-MM-YYYY') inserted_on,pay_order_number,pay_order_bank,total_amount from MST_REGISTRATION_TOKEN,MST_USER,MST_DEMO " +
		   		   		"  Where inserted_on>=to_date('"+fromDate+"','dd-MM-YYYY') and inserted_on<=to_date('"+toDate+"','dd-MM-YYYY') " +
		   		   		"  AND MST_REGISTRATION_TOKEN.ASSIGNED_TO=MST_USER.USERID " +
		   		   		"  AND MST_USER.DEMO_ID=MST_DEMO.DEMO_ID ";
		   
		   if(!demoId.equalsIgnoreCase("-9"))
			   sql+=" And MST_DEMO.DEMO_ID="+demoId;
		   
		   	   sql+="  order by inserted_on,mst_demo.demo_id ";
		   				 
		   
		   		   		
		     
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   HashMap<Integer,ArrayList<DemoDTO>> demoHashMap=null;
		   ArrayList<DemoDTO> demoList  = null;
		   DemoDTO demoDTO=null;

		   
			try
			{
				stmt = conn.prepareStatement(sql);
				r = stmt.executeQuery();
				int count=0;
				int preDemo=0;
				int previousLoopDemoId=0;
				while (r.next())
				{
					if(count==0)
						demoHashMap=new HashMap<Integer,ArrayList<DemoDTO>>();
					
					if(preDemo!=r.getInt("DEMO_ID")){
						
						if(preDemo!=0)
							demoHashMap.put(previousLoopDemoId, demoList);
						
						demoList=new ArrayList<DemoDTO>();
						demoDTO=new DemoDTO();						
						demoDTO.setDemoId(r.getInt("DEMO_ID"));
						demoDTO.setDemoName(r.getString("DEMO_NAME"));
						demoDTO.setPaymentDate(r.getString("INSERTED_ON"));
						demoDTO.setPayorderNo(r.getString("PAY_ORDER_NUMBER"));
						demoDTO.setPayorderBank(r.getString("PAY_ORDER_BANK"));
						demoDTO.setTotalAmount(r.getInt("TOTAL_AMOUNT"));
						demoList.add(demoDTO);
						
					}
					else{
												
						demoDTO=new DemoDTO();						
						demoDTO.setDemoId(r.getInt("DEMO_ID"));
						demoDTO.setDemoName(r.getString("DEMO_NAME"));
						demoDTO.setPaymentDate(r.getString("INSERTED_ON"));
						demoDTO.setPayorderNo(r.getString("PAY_ORDER_NUMBER"));
						demoDTO.setPayorderBank(r.getString("PAY_ORDER_BANK"));
						demoDTO.setTotalAmount(r.getInt("TOTAL_AMOUNT"));
						demoList.add(demoDTO);
						
					}
					count++;
					previousLoopDemoId=r.getInt("DEMO_ID");
					preDemo=r.getInt("DEMO_ID");	
				}
				
				if(count>0)
					demoHashMap.put(previousLoopDemoId, demoList);
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 		return demoHashMap;
	 }
	
}

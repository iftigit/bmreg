package org.controller.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.model.TtcDAO;
import org.table.TtcDTO;
import org.table.TtcTradeDTO;

import util.connection.ConnectionManager;

import com.opensymphony.xwork2.ActionSupport;

public class TtcManagement extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	private ArrayList<TtcDTO> ttcList=new ArrayList<TtcDTO>();
	private ArrayList<TtcTradeDTO> tradeList=new ArrayList<TtcTradeDTO>();
	
	private TtcDTO ttc;
	private TtcTradeDTO trade;
	private String msg="";
	private String ttcId;
	private String tradeId;
	private String ttcName;
	private String ttcPrincipal;
	private String address;
	private String phone;
	private String emailAddress;
	private String tradeName;
	private String tradeListStr;
	
	public String fetchTtcList()
	{
		if(ttcId==null || ttcId.equalsIgnoreCase(""))
			ttcId="all";
		ttcList=TtcDAO.getTTC(ttcId);
		
		if(ttcId.equalsIgnoreCase("all"))
			return "ttcList";
		else{
			if(ttcList.size()>0)
				ttc=ttcList.get(0);
			return "singleTtc";
		}
	}	
	public String fetchTradeList()
	{
		if(tradeId==null || tradeId.equalsIgnoreCase(""))
			tradeId="all";
		tradeList=TtcDAO.getTrade(tradeId);
		
		if(tradeId.equalsIgnoreCase("all"))
			return "tradeList";
		else{
			if(tradeList.size()>0)
				trade=tradeList.get(0);
			return "singleTrade";
		}
	}	
	
	public String createNewTtc()
	{
		TtcDAO ttcDAO=new TtcDAO();
		TtcDTO ttcDTO=ttcDAO.getTTCbyName(ttc.getTtcName());
		if(ttcDTO!=null){
			msg="TTC name already exist.";
		}
		else {
		boolean response=ttcDAO.createNewTTC(this.ttc);
			if(response==true){			
				msg="Successfully Created new TTC";
				ttc=null;
				
				ArrayList<TtcDTO> TTCList=new ArrayList<TtcDTO>();
				TTCList=TtcDAO.getAllTtc();
				HashMap<Integer, String>  ttcMap = new HashMap<Integer, String>();						
				ServletActionContext.getServletContext().setAttribute("ALL_TTC", TTCList);
				
				for(int i=0;i<TTCList.size();i++)
				{
					ttcMap.put(TTCList.get(i).getTtcId(), TTCList.get(i).getTtcName());
					
				}
				ServletActionContext.getServletContext().setAttribute("ALL_TTC_MAP", ttcMap);
				
			}
			else
				msg="Problem in creating new TTC";
		}
	
		return SUCCESS;
	}
	public String updateTtc()
	{
		ttc=new TtcDTO();
		
		ttc.setTtcId(Integer.parseInt(ttcId));
		ttc.setTtcName(ttcName);
		ttc.setTtcPrincipal(ttcPrincipal);
		ttc.setAddress(address);
		ttc.setPhone(phone);
		ttc.setEmailAddress(emailAddress);
								
		HttpServletResponse response = ServletActionContext.getResponse();
		
		boolean resp=false;
			resp=TtcDAO.updateTTC(ttc);
			if(resp==true){			
				msg="Successfully Update TTC Information.";
				ttc=null;
				
				ArrayList<TtcDTO> TTCList=new ArrayList<TtcDTO>();
				TTCList=TtcDAO.getAllTtc();
				HashMap<Integer, String>  ttcMap = new HashMap<Integer, String>();						
				ServletActionContext.getServletContext().setAttribute("ALL_TTC", TTCList);
				
				for(int i=0;i<TTCList.size();i++)
				{
					ttcMap.put(TTCList.get(i).getTtcId(), TTCList.get(i).getTtcName());
					
				}
				ServletActionContext.getServletContext().setAttribute("ALL_TTC_MAP", ttcMap);
			}
			else
				msg="Problem in Updating TTC Info.";
				
		
		try{
        	response.setContentType("text/html");
        	response.setHeader("Cache-Control", "no-cache");
        	response.getWriter().write(msg);
        	response.flushBuffer();
          }
        catch(Exception e) {e.printStackTrace();}
        
		return null;	
	}

	public String updateTrade()
	{
		trade=new TtcTradeDTO();
		
		trade.setId(Integer.parseInt(tradeId));
		trade.setTradeName(tradeName);
								
		HttpServletResponse response = ServletActionContext.getResponse();
		
		boolean resp=false;
			resp=TtcDAO.updateTrade(trade);
			if(resp==true){			
				msg="Successfully Update Trade Information.";
				ttc=null;
			}
			else
				msg="Problem in Updating Trade Info.";
				
		
		try{
        	response.setContentType("text/html");
        	response.setHeader("Cache-Control", "no-cache");
        	response.getWriter().write(msg);
        	response.flushBuffer();
          }
        catch(Exception e) {e.printStackTrace();}
        
		return null;	
	}

	public String createNewTrade()
	{
		TtcDAO ttcDAO=new TtcDAO();
		TtcTradeDTO tradeDTO=ttcDAO.getTradebyName(trade.getTradeName());
		if(tradeDTO!=null){
			msg="Trade name already exist.";
		}
		else {
		boolean response=ttcDAO.createNewTrade(this.trade);
			if(response==true){			
				msg="Successfully Created new Trade";
				trade=null;
			}
			else
				msg="Problem in creating new Trade";
		}
	
		return SUCCESS;
	}
	public String ttcTradeMapHome(){
		ttcList=TtcDAO.getTTC("all");
		return SUCCESS;
	}
	
	public String fetchTradeListForTTC(){
		tradeList=TtcDAO.getTtcTradeMap(ttcId);
		return SUCCESS;
	}
	
	
	
	public String updateTradeListForTTC(){
		HttpServletResponse response = ServletActionContext.getResponse();
		List<TtcTradeDTO> tradeList=new ArrayList<TtcTradeDTO>();
		String msg="";
		TtcTradeDTO trade=null;
		String[] tradeArr=tradeListStr.split("#");
		for(int i=0;i<tradeArr.length;i++){
			trade=new  TtcTradeDTO();
			trade.setId(Integer.parseInt(tradeArr[i]));
			tradeList.add(trade);
		}
		boolean resp1=TtcDAO.deleteTtcTradeMap(Integer.parseInt(ttcId));
		boolean resp2=false;
		if(resp1==true)
			resp2=TtcDAO.insertTtcTradeMap(Integer.parseInt(ttcId), tradeList);
		
		if(resp2==true)
			msg="Information Successfully Updated";
		else
			msg="Error in Update Information";
		

		try{
        	response.setContentType("text/html");
        	response.setHeader("Cache-Control", "no-cache");
        	response.getWriter().write(msg);
        	response.flushBuffer();
          }
        catch(Exception e) {e.printStackTrace();}
        
        return null;
	}
	
	
	public ArrayList<TtcDTO> getTtcList() {
		return ttcList;
	}


	public void setTtcList(ArrayList<TtcDTO> ttcList) {
		this.ttcList = ttcList;
	}


	public ArrayList<TtcTradeDTO> getTradeList() {
		return tradeList;
	}


	public void setTradeList(ArrayList<TtcTradeDTO> tradeList) {
		this.tradeList = tradeList;
	}


	public TtcDTO getTtc() {
		return ttc;
	}


	public void setTtc(TtcDTO ttc) {
		this.ttc = ttc;
	}


	public TtcTradeDTO getTrade() {
		return trade;
	}


	public void setTrade(TtcTradeDTO trade) {
		this.trade = trade;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getTtcId() {
		return ttcId;
	}
	public void setTtcId(String ttcId) {
		this.ttcId = ttcId;
	}
	public String getTtcName() {
		return ttcName;
	}
	public void setTtcName(String ttcName) {
		this.ttcName = ttcName;
	}
	public String getTtcPrincipal() {
		return ttcPrincipal;
	}
	public void setTtcPrincipal(String ttcPrincipal) {
		this.ttcPrincipal = ttcPrincipal;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	public String getTradeListStr() {
		return tradeListStr;
	}
	public void setTradeListStr(String tradeListStr) {
		this.tradeListStr = tradeListStr;
	}
	
	
}
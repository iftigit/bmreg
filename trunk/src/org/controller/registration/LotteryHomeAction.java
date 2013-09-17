package org.controller.registration;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.model.AddressDAO;
import org.model.LotteryDAO;
import org.table.LotteryDTO;
import org.table.LotteryStatusDTO;
import org.table.UserDTO;


import com.opensymphony.xwork2.ActionSupport;

public class LotteryHomeAction extends ActionSupport{

	private static final long serialVersionUID = 6485791838606873787L;
	
	private String districtName;
	private int totalRegJobseeker;
	private int totalCotaNumber;
	private int totalSelected;
	private String lotteryStatus;
	AddressDAO addressDAO=new AddressDAO();
	LotteryDAO lottery=new LotteryDAO();
	ArrayList<LotteryDTO> lotteryList=new ArrayList<LotteryDTO>();
	private String jobseekerNumber;
	LotteryDAO lotteryService=new LotteryDAO();
	ArrayList<LotteryStatusDTO> lotteryStatusList=new ArrayList<LotteryStatusDTO>();
	String divisionId;
	String pendingDivisionListString;
	
	public String lotteryHome()
	{
		String localIp=ServletActionContext.getRequest().getHeader("X-Forwarded-For")==null?"":ServletActionContext.getRequest().getHeader("X-Forwarded-For");
		String via=ServletActionContext.getRequest().getHeader("Via")==null?"":ServletActionContext.getRequest().getHeader("Via");
		String realIp=ServletActionContext.getRequest().getRemoteAddr()==null?"":ServletActionContext.getRequest().getRemoteAddr();
		String submittedAuthKey=localIp+via+realIp;
		
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("loggedInUser");
		
		lotteryStatus=lottery.getLotteryStatus(loggedInUser.getUserId(), loggedInUser.getDistrictId());
		
		if(!loggedInUser.getAuthenticationKey().equalsIgnoreCase(submittedAuthKey))
		{
			return "logout";
		}
		else if(!loggedInUser.getUserType().equalsIgnoreCase("LOTTERY_DC_ADMIN"))
		{
			return "logout";
		}
		else if(loggedInUser.getAccessRight()==0)
		{
			return "timeOver";	
		}
		
		districtName=addressDAO.getDistrictNameFromId(Integer.parseInt(loggedInUser.getDistrictId()));
		
		if(lotteryStatus.equalsIgnoreCase("completed"))
		{
			lotteryList=lottery.getLotteryArrayList(loggedInUser.getDistrictId());
		}
			
		totalRegJobseeker=lottery.getTotalRegisteredJobseeker(loggedInUser.getDistrictId());
		totalCotaNumber=lottery.getTotalCotaNumber(loggedInUser.getDistrictId());
		totalSelected=lottery.getTotalSelected(loggedInUser.getDistrictId());
		
		return "success";
	}
	
	
	public String ministryLotteryHome()
	{
		String localIp=ServletActionContext.getRequest().getHeader("X-Forwarded-For")==null?"":ServletActionContext.getRequest().getHeader("X-Forwarded-For");
		String via=ServletActionContext.getRequest().getHeader("Via")==null?"":ServletActionContext.getRequest().getHeader("Via");
		String realIp=ServletActionContext.getRequest().getRemoteAddr()==null?"":ServletActionContext.getRequest().getRemoteAddr();
		String submittedAuthKey=localIp+via+realIp;
		
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("loggedInUser");
		
		lotteryStatus=lottery.getLotteryStatus(loggedInUser.getUserId(), loggedInUser.getDistrictId());
		
		if(!loggedInUser.getAuthenticationKey().equalsIgnoreCase(submittedAuthKey))
		{
			return "logout";
		}
		else if(!loggedInUser.getUserType().equalsIgnoreCase("LOTTERY_MINISTRY_ADMIN"))
		{
			return "logout";
		}
		else if(loggedInUser.getAccessRight()==0)
		{
			return "timeOver";	
		}
		
		lotteryStatusList=lottery.getFinalLotteryPhasewiseStatus(1);
		pendingDivisionListString=lottery.getPendingDivisionListString(lotteryStatusList);
//		districtName=addressDAO.getDistrictNameFromId(Integer.parseInt(loggedInUser.getDistrictId()));
//		
//		if(lotteryStatus.equalsIgnoreCase("completed"))
//		{
//			lotteryList=lottery.getLotteryArrayList(loggedInUser.getDistrictId());
//		}
//			
//		totalRegJobseeker=lottery.getTotalRegisteredJobseeker(loggedInUser.getDistrictId());
//		totalCotaNumber=lottery.getTotalCotaNumber(loggedInUser.getDistrictId());
//		totalSelected=lottery.getTotalSelected(loggedInUser.getDistrictId());
		
		return "success";
	}
	
	public String processLottery()
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		
		String localIp=ServletActionContext.getRequest().getHeader("X-Forwarded-For")==null?"":ServletActionContext.getRequest().getHeader("X-Forwarded-For");
		String via=ServletActionContext.getRequest().getHeader("Via")==null?"":ServletActionContext.getRequest().getHeader("Via");
		String realIp=ServletActionContext.getRequest().getRemoteAddr()==null?"":ServletActionContext.getRequest().getRemoteAddr();
		String submittedAuthKey=localIp+via+realIp;
		
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("loggedInUser");
		
		if(!loggedInUser.getAuthenticationKey().equalsIgnoreCase(submittedAuthKey))
		{
			return "logout";
		}
		else if(!loggedInUser.getUserType().equalsIgnoreCase("LOTTERY_DC_ADMIN"))
		{
			return "logout";
		}
		else if(loggedInUser.getAccessRight()==0)
		{
			return "timeOver";	
		}
		
		String res=LotteryDAO.processLottery(loggedInUser.getUserId());

		if(res.equalsIgnoreCase("success"))
		{
			res=lottery.getLotteryResult(loggedInUser.getDistrictId());
		}
		
		try{
        	response.setContentType("text/xml");
        	response.setHeader("Cache-Control", "no-cache");
        	response.getWriter().write(res);
        	response.flushBuffer();
          }
        catch(Exception e) {e.printStackTrace();}
        
		return null;
		

	}
	
	public String searchFirstLotteryResult()
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		String result="";
		try{
        	response.setContentType("text/xml");
        	response.setHeader("Cache-Control", "no-cache");
        	response.getWriter().write(result);
        	response.flushBuffer();
          }
        catch(Exception e) {e.printStackTrace();}
        
		return null;
	}
	
	public String processDivisionLottery()
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		
		String localIp=ServletActionContext.getRequest().getHeader("X-Forwarded-For")==null?"":ServletActionContext.getRequest().getHeader("X-Forwarded-For");
		String via=ServletActionContext.getRequest().getHeader("Via")==null?"":ServletActionContext.getRequest().getHeader("Via");
		String realIp=ServletActionContext.getRequest().getRemoteAddr()==null?"":ServletActionContext.getRequest().getRemoteAddr();
		String submittedAuthKey=localIp+via+realIp;
		
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("loggedInUser");
		
		if(!loggedInUser.getAuthenticationKey().equalsIgnoreCase(submittedAuthKey))
		{
			return "logout";
		}
		else if(!loggedInUser.getUserType().equalsIgnoreCase("LOTTERY_MINISTRY_ADMIN"))
		{
			return "logout";
		}
		else if(loggedInUser.getAccessRight()==0)
		{
			return "timeOver";	
		}
		
		String res=LotteryDAO.processDivisionLottery(this.divisionId);

		if(res.equalsIgnoreCase("success"))
		{
			res=lottery.getDivisionLotteryResult(this.divisionId);
		}
		
		try{
        	response.setContentType("text/xml");
        	response.setHeader("Cache-Control", "no-cache");
        	response.getWriter().write(res);
        	response.flushBuffer();
          }
        catch(Exception e) {e.printStackTrace();}
        
		return null;
		

	}
	
	public String getDivisionWiseLotterySummary()
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		String result=lotteryService.getLotteryShortSummaryResult(this.divisionId);
		try{
        	response.setContentType("text/xml");
        	response.setHeader("Cache-Control", "no-cache");
        	response.getWriter().write(result);
        	response.flushBuffer();
          }
        catch(Exception e) {e.printStackTrace();}
        
		return null;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public int getTotalRegJobseeker() {
		return totalRegJobseeker;
	}

	public void setTotalRegJobseeker(int totalRegJobseeker) {
		this.totalRegJobseeker = totalRegJobseeker;
	}

	public int getTotalCotaNumber() {
		return totalCotaNumber;
	}

	public void setTotalCotaNumber(int totalCotaNumber) {
		this.totalCotaNumber = totalCotaNumber;
	}

	public int getTotalSelected() {
		return totalSelected;
	}

	public void setTotalSelected(int totalSelected) {
		this.totalSelected = totalSelected;
	}

	public String getLotteryStatus() {
		return lotteryStatus;
	}

	public void setLotteryStatus(String lotteryStatus) {
		this.lotteryStatus = lotteryStatus;
	}

	public ArrayList<LotteryDTO> getLotteryList() {
		return lotteryList;
	}

	public void setLotteryList(ArrayList<LotteryDTO> lotteryList) {
		this.lotteryList = lotteryList;
	}

	public String getJobseekerNumber() {
		return jobseekerNumber;
	}

	public void setJobseekerNumber(String jobseekerNumber) {
		this.jobseekerNumber = jobseekerNumber;
	}


	public String getDivisionId() {
		return divisionId;
	}


	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}


	public ArrayList<LotteryStatusDTO> getLotteryStatusList() {
		return lotteryStatusList;
	}


	public void setLotteryStatusList(ArrayList<LotteryStatusDTO> lotteryStatusList) {
		this.lotteryStatusList = lotteryStatusList;
	}


	public String getPendingDivisionListString() {
		return pendingDivisionListString;
	}


	public void setPendingDivisionListString(String pendingDivisionListString) {
		this.pendingDivisionListString = pendingDivisionListString;
	}
	
	
	

}

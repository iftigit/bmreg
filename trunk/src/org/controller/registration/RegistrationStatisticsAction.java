package org.controller.registration;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.model.DashBoardDAO;
import org.table.UserDTO;

import com.opensymphony.xwork2.ActionSupport;

public class RegistrationStatisticsAction  extends ActionSupport{

	private static final long serialVersionUID = -4335406653533806139L;
	String districtId;
	String districtName;
	String divisionId;
	String divisionName;
	String thanaId;
	String thanaName;
	int refreshRate;
	
	public String execute()
	{
		String localIp=ServletActionContext.getRequest().getHeader("X-Forwarded-For")==null?"":ServletActionContext.getRequest().getHeader("X-Forwarded-For");
		String via=ServletActionContext.getRequest().getHeader("Via")==null?"":ServletActionContext.getRequest().getHeader("Via");
		String realIp=ServletActionContext.getRequest().getRemoteAddr()==null?"":ServletActionContext.getRequest().getRemoteAddr();
		String submittedAuthKey=localIp+via+realIp;
		
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("loggedInUser");
		if(!loggedInUser.getAuthenticationKey().equalsIgnoreCase(submittedAuthKey))
		{
			return "logout";
		}
		else if(!loggedInUser.getUserType().equalsIgnoreCase("REG_VIEW_ADMIN") && !loggedInUser.getUserType().equalsIgnoreCase("REG_LOT_VIEW_ADMIN"))
		{
			return "logout";
		}
		else if(loggedInUser.getAccessRight()==0)
		{
			return "timeOver";	
		}
		
		return "success";
	}
	
	public String divisionStatistics()
	{
		HttpServletResponse response = ServletActionContext.getResponse();

		DashBoardDAO dbdao=new DashBoardDAO();
		String table=dbdao.getDivisionWiseStat(this.divisionId,this.divisionName);
		

		try{
        	response.setContentType("text/xml");
        	response.setHeader("Cache-Control", "no-cache");
        	response.getWriter().write(table);
        	response.flushBuffer();
          }
        catch(Exception e) {e.printStackTrace();}
        
        
		return null;
		

	}
	
	public String divisionResultStat()
	{
		HttpServletResponse response = ServletActionContext.getResponse();

		DashBoardDAO dbdao=new DashBoardDAO();
		String table=dbdao.divisionWiseResultStat(this.divisionId,this.divisionName);
		

		try{
        	response.setContentType("text/xml");
        	response.setHeader("Cache-Control", "no-cache");
        	response.getWriter().write(table);
        	response.flushBuffer();
          }
        catch(Exception e) {e.printStackTrace();}
        
        
		return null;
		
	}
	
	
	public String districtStatistics()
	{
		HttpServletResponse response = ServletActionContext.getResponse();

		DashBoardDAO dbdao=new DashBoardDAO();
		String table=dbdao.getDistrictWiseStat(this.districtId,this.districtName);

		try{
        	response.setContentType("text/xml");
        	response.setHeader("Cache-Control", "no-cache");
        	response.getWriter().write(table);
        	response.flushBuffer();
          }
        catch(Exception e) {e.printStackTrace();}
        
        
		return null;
	}
	
	public String districtResultStat()
	{
		HttpServletResponse response = ServletActionContext.getResponse();

		DashBoardDAO dbdao=new DashBoardDAO();
		String table=dbdao.getDistrictWiseResultStat(this.districtId,this.districtName);

		try{
        	response.setContentType("text/xml");
        	response.setHeader("Cache-Control", "no-cache");
        	response.getWriter().write(table);
        	response.flushBuffer();
          }
        catch(Exception e) {e.printStackTrace();}
        
        
		return null;
	}
	
	public String thanaStatistics()
	{
		HttpServletResponse response = ServletActionContext.getResponse();

		DashBoardDAO dbdao=new DashBoardDAO();
		String table=dbdao.getThanaWiseStat(this.thanaId,this.thanaName);

		try{
        	response.setContentType("text/xml");
        	response.setHeader("Cache-Control", "no-cache");
        	response.getWriter().write(table);
        	response.flushBuffer();
          }
        catch(Exception e) {e.printStackTrace();}
        
        
		return null;
		

	}
	
	public String thanaResultStat()
	{
		HttpServletResponse response = ServletActionContext.getResponse();

		DashBoardDAO dbdao=new DashBoardDAO();
		String table=dbdao.getThanaWiseResultStat(this.thanaId,this.thanaName);

		try{
        	response.setContentType("text/xml");
        	response.setHeader("Cache-Control", "no-cache");
        	response.getWriter().write(table);
        	response.flushBuffer();
          }
        catch(Exception e) {e.printStackTrace();}
        
        
		return null;
		

	}
	
	
	public String ministryLotteryResultDashboard()
	{
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
		
		return "success";
	}
	

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public int getRefreshRate() {
		return refreshRate;
	}

	public void setRefreshRate(int refreshRate) {
		this.refreshRate = refreshRate;
	}

	public String getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}

	public String getDivisionName() {
		return divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	public String getThanaId() {
		return thanaId;
	}

	public void setThanaId(String thanaId) {
		this.thanaId = thanaId;
	}

	public String getThanaName() {
		return thanaName;
	}

	public void setThanaName(String thanaName) {
		this.thanaName = thanaName;
	}
	
	
}

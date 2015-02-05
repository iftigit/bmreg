package org.controller.admin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import org.apache.struts2.ServletActionContext;
import org.model.NewPaawordDAO;
import org.model.UserDAO;
import org.table.UserDTO;
import org.table.UserTmpDTO;

import com.opensymphony.xwork2.ActionSupport;

public class UserApproveAdministration extends ActionSupport{

	private static final long serialVersionUID = 1L;
	ArrayList<UserTmpDTO> userList=new ArrayList<UserTmpDTO>();
	String[] approveUserList;
	private String startDate;
	private String endDate;
	private String message;
	
	
	public String approveNewUserAndSendSms()
	{
		
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("loggedInUser");
		UserDAO userDao=new UserDAO();
		this.message="";
		String res="";
		if(loggedInUser.getAccessRight()==0)
		{
			return "timeOver";	
		}
		else if(!loggedInUser.getUserType().equalsIgnoreCase("SYSTEM_ADMIN"))
		{
			return "logout";
		}
		else if(startDate==null || endDate==null || startDate.equalsIgnoreCase("") || endDate.equalsIgnoreCase(""))
		{
			this.message="Please provide start and end date.";
			userList=userDao.getNewUserList();
			return "input";
		}
		if(approveUserList!=null)
		{
			res=userDao.approveNewUserRequest(approveUserList,startDate,endDate);
			if(res.equalsIgnoreCase("SUCCESS")){
				UserDAO.sendPassword(approveUserList);
				this.message="Successfully Approved and SMS send to the approved users";
			}
			else
				this.message=res;
			
			approveUserList=null;
		}
		else
			this.message="Please select at least one user from the list.";
		
		userList=userDao.getNewUserList();
		if(res.equalsIgnoreCase("SUCCESS"))
			return "success";
		else
			return "input";
	}

	 
	
	 
	public ArrayList<UserTmpDTO> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<UserTmpDTO> userList) {
		this.userList = userList;
	}
	public String[] getApproveUserList() {
		return approveUserList;
	}
	public void setApproveUserList(String[] approveUserList) {
		this.approveUserList = approveUserList;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

}

package org.controller.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.model.RegistrationDAO;
import org.model.UserDAO;
import org.table.UserDTO;
import org.table.UserTmpDTO;

import com.opensymphony.xwork2.ActionSupport;

public class UserAdministration extends ActionSupport{

	private static final long serialVersionUID = 1L;
	ArrayList<UserTmpDTO> userList=new ArrayList<UserTmpDTO>();
	String[] approveUserList;
	private String message;
	private String userId;
	private String password;
	private String userType;
	private String division;
	private String district;
	private String upazila;
	private String union;
	private String startDate;
	private String endDate;
	private String userName;
	private String designation;
	
	public String checkRequestedUserList()
	{
		UserDAO userDao=new UserDAO();
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("loggedInUser");
		if(loggedInUser.getAccessRight()==0)
		{
			return "timeOver";	
		}
		else if(!loggedInUser.getUserType().equalsIgnoreCase("SYSTEM_ADMIN"))
		{
			return "logout";
		}
		
		userList=userDao.getNewUserList();
		
		return SUCCESS;
	}
	public String newUserForm(){
		RegistrationDAO regDAO=new RegistrationDAO();
		password=regDAO.getRandomPassword();
		return SUCCESS;
	}
	public String createNewUser(){
		
		int count=UserDAO.checkUserIdAvailability(userId);
		String msg="";
		HttpServletResponse response = ServletActionContext.getResponse();
		
		if(count==0){

		UserDTO user=new UserDTO();
		user.setUserId(userId);
		user.setPassword(password);
		user.setUserType(userType);
		user.setDivisionId(division);
		user.setDistrictId(district);
		user.setUnionId(union);
		user.setUpazillaId(upazila);
		user.setFormDate(startDate);
		user.setToDate(endDate);
		user.setUserName(userName);
		user.setDesignation(designation);
				
		
		boolean resp=UserDAO.createNewUser(user);
		
		if(resp==true)
			msg="Successfully Created New User.";
		}
		else{
			msg="Duplicate userid found.Please use a different userid.";
		}
		
		try{
        	response.setContentType("text/html");
        	response.setHeader("Cache-Control", "no-cache");
        	response.getWriter().write(msg);
        	response.flushBuffer();
          }
        catch(Exception e) {e.printStackTrace();}
        
        RegistrationDAO regDAO=new RegistrationDAO();
        password=regDAO.getRandomPassword();
		return null;	
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}	
	public String getUpazila() {
		return upazila;
	}
	public void setUpazila(String upazila) {
		this.upazila = upazila;
	}
	public String getUnion() {
		return union;
	}
	public void setUnion(String union) {
		this.union = union;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	

}

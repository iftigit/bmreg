package org.controller.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;
import org.model.UserDAO;
import org.table.UserDTO;

import com.opensymphony.xwork2.ActionSupport;


public class User extends ActionSupport implements ServletContextAware{
	
	private static final long serialVersionUID = -7546229642641652565L;
	private String oldPassword;
	private String newPassword;
	private String confirmNewPassword;
	
	public String changePassword(){
		
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("loggedInUser");	
		HttpServletResponse response = ServletActionContext.getResponse();
		String responseString="";
	    String PASSWORD_PATTERN ="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,10})";
	    Pattern pattern= Pattern.compile(PASSWORD_PATTERN);
	    Matcher matcher = pattern.matcher(newPassword);		  
		boolean resp=false;
		if(!oldPassword.equals(loggedInUser.getPassword())){
			responseString="Incorrect Old Password";
		}
		else if(newPassword.length()<6 || newPassword.length()>10){
			responseString="Password must be at least 6-10 character long.";
		}
		else if(!newPassword.equals(confirmNewPassword)){
			responseString="New Password and Confirm New Password are not equal.";
		}
		else if(matcher.matches()==false)
		{
			responseString="New Password Must Contains at least one digit, one uppercase character, one lowercase character. Length will be 6-10 characters long.";
		}
		else
		{
			resp=UserDAO.updatePassword(loggedInUser.getUserId(), newPassword);
			if(resp==true)
				responseString="Password Successfully Updated.";
			else
				responseString="Error in Password Update.";
		}
		try{
        	response.setContentType("text/xml");
        	response.setHeader("Cache-Control", "no-cache");
        	response.getWriter().write(responseString);
        	response.flushBuffer();
          }
        catch(Exception e) {e.printStackTrace();}
        
		return null;
	}
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}
	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}
	
	

}

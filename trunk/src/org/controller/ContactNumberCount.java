package org.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;
import org.model.RegistrationDAO;

import com.opensymphony.xwork2.ActionSupport;

public class ContactNumberCount  extends ActionSupport implements ServletContextAware{
	
	
	private static final long serialVersionUID = 8078022176693325618L;
	private String contactNumber;
	
	
	
	public String execute()
	{		

		HttpServletResponse response = ServletActionContext.getResponse();
		RegistrationDAO rdao=new RegistrationDAO();
		int count=rdao.getNomineeContactPhoneCount(contactNumber);
		

		try{
        	response.setContentType("text/xml");
        	response.setHeader("Cache-Control", "no-cache");
        	response.getWriter().write(String.valueOf(count));
        	response.flushBuffer();
          }
        catch(Exception e) {e.printStackTrace();}
		return null;
		
		
		

	}
	
	
	
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}
	

}

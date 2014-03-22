package org.controller.registration;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.model.CountryDAO;
import org.model.LanguageDAO;
import org.model.RegistrationDAO;
import org.table.CountryDTO;
import org.table.LanguageDTO;
import org.table.UserDTO;
import org.util.PassPhrase;

import com.opensymphony.xwork2.ActionSupport;


public class RegistrationAction extends ActionSupport{
	
	private static final long serialVersionUID = 8818190897989491886L;
	private ArrayList<CountryDTO> countryList=new ArrayList<CountryDTO>();
	private ArrayList<LanguageDTO> languageList=new ArrayList<LanguageDTO>();
	private String regToken;
	
	public String regHomeAction()
	{
		//this.rc= PassPhrase.getNext();
		//countryList=(ArrayList<CountryDTO>)getServletContext().getAttribute("ALL_COUNTRY");
		countryList=CountryDAO.getAllCountry();
		languageList=LanguageDAO.getAllLanguage("active");
		
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("loggedInUser");
		if(!loggedInUser.getUserType().equalsIgnoreCase("UISC_REG_OPERATOR") && !loggedInUser.getUserType().equalsIgnoreCase("DEMO_REG_OPERATOR"))
		{
			return "logout";
		}
		else if(loggedInUser.getAccessRight()==0)
		{
			return "timeOver";	
		}
		
		ServletActionContext.getRequest().getSession().setAttribute("form_error", null);
		return "REG_HOME";
	}
	
	public String validateRegToken(){
		
		HttpServletResponse response = ServletActionContext.getResponse();
		RegistrationDAO regDAO=new RegistrationDAO();
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("loggedInUser");
		String userId=loggedInUser.getUserId();
		String userType=loggedInUser.getUserType();
		boolean valid=regDAO.isValidRegToken(userId,userType, regToken);
		String msg="";
		if(valid==true)
			msg="success";
		else 
			msg="error";
		try{
        	response.setContentType("text/html");
        	response.setHeader("Cache-Control", "no-cache");
        	response.getWriter().write(msg);
        	response.flushBuffer();
          }
        catch(Exception e) {e.printStackTrace();}
        
        return null;
	}

	public ArrayList<CountryDTO> getCountryList() {
		return countryList;
	}

	public void setCountryList(ArrayList<CountryDTO> countryList) {
		this.countryList = countryList;
	}

	public ServletContext getServletContext()
	{
		return ServletActionContext.getServletContext();
	}

	public ArrayList<LanguageDTO> getLanguageList() {
		return languageList;
	}

	public void setLanguageList(ArrayList<LanguageDTO> languageList) {
		this.languageList = languageList;
	}

	public String getRegToken() {
		return regToken;
	}

	public void setRegToken(String regToken) {
		this.regToken = regToken;
	}
	

}

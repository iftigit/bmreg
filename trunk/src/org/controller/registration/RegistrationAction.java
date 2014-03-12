package org.controller.registration;

import java.util.ArrayList;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.model.CountryDAO;
import org.model.LanguageDAO;
import org.table.CountryDTO;
import org.table.LanguageDTO;
import org.table.UserDTO;
import org.util.PassPhrase;

import com.opensymphony.xwork2.ActionSupport;


public class RegistrationAction extends ActionSupport{
	
	private static final long serialVersionUID = 8818190897989491886L;
	private ArrayList<CountryDTO> countryList=new ArrayList<CountryDTO>();
	private ArrayList<LanguageDTO> languageList=new ArrayList<LanguageDTO>();
	
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
	

}

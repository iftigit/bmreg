package org.controller.registration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.model.CountryDAO;
import org.model.LanguageDAO;
import org.model.RegistrationDAO;
import org.table.CountryDTO;
import org.table.LanguageDTO;
import org.table.PersonalInfoDTO;
import org.table.UserDTO;

import com.opensymphony.xwork2.ActionSupport;


public class RegistrationAction extends ActionSupport{
	
	private static final long serialVersionUID = 8818190897989491886L;
	private ArrayList<CountryDTO> countryList=new ArrayList<CountryDTO>();
	private ArrayList<LanguageDTO> languageList=new ArrayList<LanguageDTO>();
	private String regToken;
	private String tmpRegId;
	private String paymentPin;
	private String paymentOperator;
	private String mobileNumber;
	private ArrayList<PersonalInfoDTO> nonAckList=new ArrayList<PersonalInfoDTO>();
	
	public String regHomeAction()
	{
		//this.rc= PassPhrase.getNext();
		//countryList=(ArrayList<CountryDTO>)getServletContext().getAttribute("ALL_COUNTRY");
		countryList=CountryDAO.getAllCountry(1);
		languageList=LanguageDAO.getAllLanguage(1);
		
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("loggedInUser");
		if(!loggedInUser.getUserType().equalsIgnoreCase("UISC_REG_OPERATOR") && !loggedInUser.getUserType().equalsIgnoreCase("DEMO_REG_OPERATOR") && !loggedInUser.getUserType().equalsIgnoreCase("OPEN_REG_OPERATOR"))
		{
			return "logout";
		}
		else if(loggedInUser.getAccessRight()==0)
		{
			return "timeOver";	
		}
		else if(getServletContext().getAttribute("ACTIVE_REGTYPE")==null){
			return "noRegType";
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
   public String validatePaymentPin(){
		
		HttpServletResponse response = ServletActionContext.getResponse();
		RegistrationDAO regDAO=new RegistrationDAO();
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("loggedInUser");
		boolean valid=regDAO.isValidPaymentPin(loggedInUser.getUserType(),paymentOperator,paymentPin,mobileNumber);
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
	
   public String nonAckRegistration()
   {
	   UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("loggedInUser");
	   RegistrationDAO rdao=new RegistrationDAO();
	   nonAckList=rdao.getNonAckEmpList(loggedInUser.getUserType(), loggedInUser.getUserId());
	   return "success";
   }
   public String ackTmpRegId()
   {
	    String resp="";
	    RegistrationDAO regDAO=new RegistrationDAO();
	    HttpServletResponse response = ServletActionContext.getResponse();
	    UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("loggedInUser");
		if(!loggedInUser.getUserType().equalsIgnoreCase("UISC_REG_OPERATOR") && !loggedInUser.getUserType().equalsIgnoreCase("SYSTEM_ADMIN"))
		{
			return "logout";
		}
		PersonalInfoDTO personalInfo=regDAO.getPersonalInformation(tmpRegId);
		try{	
			String url="http://123.49.43.139:9999/bmet/bmetsend2teletalk.php?user=bmet&password=bmet123&tmp_reg_id="+tmpRegId+"&name="+URLEncoder.encode(personalInfo.getEmpFullName(),"UTF-8")+"&dist="+URLEncoder.encode(personalInfo.getPermanentAddress().getDistrictName(),"UTF-8")+"&thana="+URLEncoder.encode(personalInfo.getPermanentAddress().getUpazillaOrThanaName(),"UTF-8")+"&contact_number="+URLEncoder.encode(personalInfo.getEmpMobileNumber(),"UTF-8");			
			URL ackUrl = new URL(url);
			URLConnection yc = ackUrl.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
			String inputLine;
			String inputLine1="";			
			while ((inputLine = in.readLine()) != null)
			{
				System.out.println(inputLine);
				if(inputLine!=null)
					inputLine1+=inputLine;
			}
			in.close();
		    if(inputLine1!=null && inputLine1.endsWith("<reply>1</reply>"))
			    {
		 		regDAO.updateAcknowledgement(tmpRegId);
		 		resp="Success";
			    }
		    else
		    	resp="Failed";
			}
		catch(Exception ex){
			resp="Failed";
		}
			
		try{
        	response.setContentType("text/html");
        	response.setHeader("Cache-Control", "no-cache");
        	response.getWriter().write(resp);
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

	public String getPaymentPin() {
		return paymentPin;
	}

	public void setPaymentPin(String paymentPin) {
		this.paymentPin = paymentPin;
	}

	public String getPaymentOperator() {
		return paymentOperator;
	}

	public void setPaymentOperator(String paymentOperator) {
		this.paymentOperator = paymentOperator;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public ArrayList<PersonalInfoDTO> getNonAckList() {
		return nonAckList;
	}

	public void setNonAckList(ArrayList<PersonalInfoDTO> nonAckList) {
		this.nonAckList = nonAckList;
	}

	public String getTmpRegId() {
		return tmpRegId;
	}

	public void setTmpRegId(String tmpRegId) {
		this.tmpRegId = tmpRegId;
	}
	

}

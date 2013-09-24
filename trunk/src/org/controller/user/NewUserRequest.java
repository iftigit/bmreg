package org.controller.user;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;
import org.model.AddressDAO;
import org.model.UserDAO;
import org.table.UserDTO;

import com.opensymphony.xwork2.ActionSupport;

public class  NewUserRequest extends ActionSupport implements ServletContextAware{

	private static final long serialVersionUID = 1L;

	private String userId;
	private String divisionId;
	private String districtId;
	private String upazilaId;
	private String unionId;
	
	public String execute()
	{
		UserDAO userDao=new UserDAO();
		HttpServletResponse response = ServletActionContext.getResponse();
		String responseString="";
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("loggedInUser");
		if(loggedInUser.getAccessRight()==0)
		{
			return "timeOver";	
		}
		else if(!loggedInUser.getUserType().equalsIgnoreCase("REG_DC_ADMIN"))
		{
			return "logout";
		}
		
		if(userId.equalsIgnoreCase(""))
		{
			responseString="Please provide UserId";
		}
		else if(divisionId==null || districtId==null || upazilaId==null || unionId==null || loggedInUser==null ||
				divisionId.equalsIgnoreCase("") || districtId.equalsIgnoreCase("") ||upazilaId.equalsIgnoreCase("") ||
				unionId.equalsIgnoreCase("")){
			responseString="Something went wrong. You have not provided all the necessary information.";
		}
		else{
		    String res=userDao.insertNewUserRequest(userId, "", divisionId, districtId, upazilaId, unionId, loggedInUser.getUserId());
		    
			if(res.equalsIgnoreCase("SUCCESS")){
				responseString="New User has successfully been added. BMET System Admin will send the password through a SMS to new user's mobile phone.";
			}
			else
				responseString="Error Occured during saving the information. Please contact with System Admin.\n\nTechnical Detail :"+res;
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
	
	
	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getDivisionId() {
		return divisionId;
	}


	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}


	public String getDistrictId() {
		return districtId;
	}


	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}


	public String getUpazilaId() {
		return upazilaId;
	}


	public void setUpazilaId(String upazilaId) {
		this.upazilaId = upazilaId;
	}


	public String getUnionId() {
		return unionId;
	}


	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}


	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}

}

package org.controller.admin;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.model.MasterDataManagement;
import org.table.AgeLimitDTO;

import com.opensymphony.xwork2.ActionSupport;

public class AgeLimitManagement extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private String minYear;
	private String maxYear;
	
	public String execute()
	{		
		AgeLimitDTO ageLimitDTO=MasterDataManagement.getAgeLimitMstData();
		this.minYear=String.valueOf(ageLimitDTO.getMinAge());
		this.maxYear=String.valueOf(ageLimitDTO.getMaxAge());
		return SUCCESS;	
	}
	public String saveAgeLimitInfo()
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		float minYear=0;
		float maxYear=0;
		boolean resp=false;
		String msg="";
		try{
			minYear=Float.parseFloat(this.minYear);
			maxYear=Float.parseFloat(this.maxYear);
			resp=MasterDataManagement.updateAgeLimitInfo(minYear, maxYear);
			if(resp==true)
				msg="Information Successfully Updated.";
			else
				msg="Error in Update Operation.";
		}
		catch(Exception ex){
			ex.printStackTrace();
			msg="Invalid Min Age or Max Age";
		}
		
		try{
        	response.setContentType("text/xml");
        	response.setHeader("Cache-Control", "no-cache");
        	response.getWriter().write(msg);
        	response.flushBuffer();
          }
        catch(Exception e) {e.printStackTrace();}
        
		return null;
		
	}
	public String getMinYear() {
		return minYear;
	}
	public void setMinYear(String minYear) {
		this.minYear = minYear;
	}
	public String getMaxYear() {
		return maxYear;
	}
	public void setMaxYear(String maxYear) {
		this.maxYear = maxYear;
	}
	

	
	
}

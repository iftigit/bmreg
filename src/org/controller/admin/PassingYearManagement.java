package org.controller.admin;

import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.model.MasterDataManagement;
import org.table.PassingYearDTO;
import com.opensymphony.xwork2.ActionSupport;

public class PassingYearManagement extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private String fromYear;
	private String toYear;
	
	public String execute()
	{		
		PassingYearDTO passingYearDTO=MasterDataManagement.getPassingYearMstData();
		this.fromYear=passingYearDTO.getFromYear();
		this.toYear=passingYearDTO.getToYear();
		return SUCCESS;	
	}
	public String savePassingYearInfo()
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		int fromYear=0;
		int toYear=0;
		boolean resp=false;
		String msg="";
		try{
			fromYear=Integer.parseInt(this.fromYear);
			toYear=Integer.parseInt(this.toYear);
			resp=MasterDataManagement.updatePassingYearInfo(fromYear, toYear);
			if(resp==true)
				msg="Information Successfully Updated.";
			else
				msg="Error in Update Operation.";
		}
		catch(Exception ex){
			ex.printStackTrace();
			msg="Invalid From Year or To Year.";
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

	public String getFromYear() {
		return fromYear;
	}

	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}

	public String getToYear() {
		return toYear;
	}

	public void setToYear(String toYear) {
		this.toYear = toYear;
	}

	
}

package org.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.model.CountryDAO;
import org.model.MasterDataManagement;
import org.table.CountryDTO;
import org.table.DegreeDTO;

import com.opensymphony.xwork2.ActionSupport;

public class CountryManagement extends ActionSupport{

	private static final long serialVersionUID = 1L;
	ArrayList<CountryDTO> countryList;
	private int countryId;
	private int visibility;
	private List<CountryDTO> cList;
	
	public String execute()
	{
		countryList=CountryDAO.getAllCountry();
		return SUCCESS;	
	}
	
	public String updateCountry()
	{		
		HttpServletResponse response = ServletActionContext.getResponse();
		String msg="Error in Update Operation.";
		CountryDTO countryDTO=new CountryDTO();
		countryDTO.setCountryId(this.countryId);
		countryDTO.setVisibility(this.visibility);		

		boolean resp=MasterDataManagement.updateCountry(countryDTO);
		
		if(resp==true)
			msg="Successfully Updated";
		
		try{
        	response.setContentType("text/html");
        	response.setHeader("Cache-Control", "no-cache");
        	response.getWriter().write(msg);
        	response.flushBuffer();
          }
        catch(Exception e) {e.printStackTrace();}
        
		return null;
								
	}
	public String updateCountryList(){
			
		boolean resp=MasterDataManagement.updateCountryBatch(cList);
		countryList=CountryDAO.getAllCountry();
		return SUCCESS;
		
	}

	public ArrayList<CountryDTO> getCountryList() {
		return countryList;
	}

	public void setCountryList(ArrayList<CountryDTO> countryList) {
		this.countryList = countryList;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public int getVisibility() {
		return visibility;
	}

	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}

	public List<CountryDTO> getcList() {
		return cList;
	}

	public void setcList(List<CountryDTO> cList) {
		this.cList = cList;
	}

	
	

}

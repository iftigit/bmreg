package org.controller.admin;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;
import org.model.AddressDAO;

import com.opensymphony.xwork2.ActionSupport;

public class FetchAddressEntryForm extends ActionSupport implements ServletContextAware{
	
	private static final long serialVersionUID = 596377582962381206L;
	
	public String requestType;
	private String divisionId;
	private String districtId;
	private String upazilaId;
	private String unionId;
	private String mauzaId;
	private String villageId;
	
	private String upazilaName;
	private String unionName;
	private String mauzaName;
	private String villageName;
	
	public String execute()
	{
		if(requestType.equalsIgnoreCase("1"))
			return "UPAZILA_ENTRY";
		else if(requestType.equalsIgnoreCase("2"))
			return "UNION_ENTRY";
		else if(requestType.equalsIgnoreCase("3"))
			return "MAUZA_ENTRY";
		else if(requestType.equalsIgnoreCase("4"))
			return "VILLAGE_ENTRY";
		return null;
	}
	
	public String saveNewAddressInfo()
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		AddressDAO addDAO=new AddressDAO();
		String responseString="";
		if(requestType.equalsIgnoreCase("upazila"))
		{
			if(upazilaName.trim().equalsIgnoreCase(""))
				responseString="Upazila/Thana/City Corp./Pauroshova Name cannot be empty";
			else{
				String res=addDAO.insertNewAddress(requestType, divisionId, districtId, upazilaId, upazilaName, unionId, unionName, mauzaId, mauzaName, villageName);
				if(res.equalsIgnoreCase("SUCCESS")){
					responseString="Upazila/Thana/City Corp./Pauroshova Successfully Added.";
					getServletContext().setAttribute("UPAZILLA_OR_THANA_BY_DIVISION_DISTRICT_"+divisionId+"_"+districtId,null);
				}
				else
					responseString="Error Occured during saving the information. Please contact with System Admin.\n\nTechnical Detail :"+res;
			}			
		}
		else if(requestType.equalsIgnoreCase("union"))
		{
			if(unionName.trim().equalsIgnoreCase(""))
				responseString="Union/Ward Name cannot be empty";
			else{
				String res=addDAO.insertNewAddress(requestType, divisionId, districtId, upazilaId, upazilaName, unionId, unionName, mauzaId, mauzaName, villageName);
				if(res.equalsIgnoreCase("SUCCESS")){
					responseString="Union/Ward Successfully Added.";
					getServletContext().setAttribute("UNION_OR_WARD_BY_DIVISION_DISTRICT_UPAZILLAorTHANA_"+divisionId+"_"+districtId+"_"+upazilaId,null);
				}
				else
					responseString="Error Occured during saving the information. Please contact with System Admin.\n\nTechnical Detail :"+res;
			}			
		}
		else if(requestType.equalsIgnoreCase("mauza"))
		{
			if(mauzaName.trim().equalsIgnoreCase(""))
				responseString="Mauza/Moholla Name cannot be empty";
			else{
				String res=addDAO.insertNewAddress(requestType, divisionId, districtId, upazilaId, upazilaName, unionId, unionName, mauzaId, mauzaName, villageName);
				if(res.equalsIgnoreCase("SUCCESS")){
					responseString="Mauza/Moholla Successfully Added.";
					getServletContext().setAttribute("MAUZA_OR_MOHOLLA_BY_DIVISION_DISTRICT_UPAZILLAorTHANA_UNIONorWARD_"+divisionId+"_"+districtId+"_"+upazilaId+"_"+unionId,null);
				}
				else
					responseString="Error Occured during saving the information. Please contact with System Admin.\n\nTechnical Detail :"+res;
			}			
		}
		else if(requestType.equalsIgnoreCase("village"))
		{
			if(villageName.trim().equalsIgnoreCase(""))
				responseString="Village Name cannot be empty";
			else{
				String res=addDAO.insertNewAddress(requestType, divisionId, districtId, upazilaId, upazilaName, unionId, unionName, mauzaId, mauzaName, villageName);
				if(res.equalsIgnoreCase("SUCCESS")){
					responseString="Village Successfully Added.";
					getServletContext().setAttribute("MAUZA_OR_MOHOLLA_BY_DIVISION_DISTRICT_UPAZILLAorTHANA_UNIONorWARD_MAUZAorMOHOLLA_"+divisionId+"_"+districtId+"_"+upazilaId+"_"+unionId+"_"+mauzaId,null);
				}
				else
					responseString="Error Occured during saving the information. Please contact with System Admin.\n\nTechnical Detail :"+res;
			}			
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
	
	
	public String getRequestType() {
		return requestType;
	}



	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}



	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub		
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

	public String getMauzaId() {
		return mauzaId;
	}

	public void setMauzaId(String mauzaId) {
		this.mauzaId = mauzaId;
	}

	public String getVillageId() {
		return villageId;
	}

	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}

	public String getUpazilaName() {
		return upazilaName;
	}

	public void setUpazilaName(String upazilaName) {
		this.upazilaName = upazilaName;
	}

	public String getUnionName() {
		return unionName;
	}

	public void setUnionName(String unionName) {
		this.unionName = unionName;
	}

	public String getMauzaName() {
		return mauzaName;
	}

	public void setMauzaName(String mauzaName) {
		this.mauzaName = mauzaName;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	
	public ServletContext getServletContext()
	{
		return ServletActionContext.getServletContext();
	}
}

package org.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.model.MasterDataManagement;
import org.table.RegTypeDTO;

import com.opensymphony.xwork2.ActionSupport;

public class RegTypeManagement extends ActionSupport{

	private static final long serialVersionUID = 1L;
	ArrayList<RegTypeDTO> regTypeList;
	private List<RegTypeDTO> rList;
	private int typeId;
	private String regIdSuffix;
	private String typeName;
	private String typeDesc;
	private int isActive;	
	
	public String execute()
	{		
		regTypeList=MasterDataManagement.getRegTypeMstData();		
		return SUCCESS;	
	}
	
	public String addNewRegType()
	{		
		HttpServletResponse response = ServletActionContext.getResponse();
		RegTypeDTO regTypeDTO=new RegTypeDTO();
		regTypeDTO.setRegIdSuffix(this.regIdSuffix);
		regTypeDTO.setTypeName(this.typeName);
		regTypeDTO.setTypeDesc(this.typeDesc);
		regTypeDTO.setIsActive(this.isActive);
		String msg="Error in Add Operation.";
		boolean resp=MasterDataManagement.addNewRegType(regTypeDTO);
		
		if(resp==true)
			msg="Successfully Added";
		
		try{
        	response.setContentType("text/html");
        	response.setHeader("Cache-Control", "no-cache");
        	response.getWriter().write(msg);
        	response.flushBuffer();
          }
        catch(Exception e) {e.printStackTrace();}
        
		return null;	
	}
	
	public String updateRegType()
	{		
		HttpServletResponse response = ServletActionContext.getResponse();
		String msg="Error in Update Operation.";	

		RegTypeDTO typeDTO=new RegTypeDTO();
		typeDTO.setTypeId(this.typeId);
		typeDTO.setRegIdSuffix(this.regIdSuffix);
		typeDTO.setTypeName(this.typeName);
		typeDTO.setTypeDesc(this.typeDesc);
		typeDTO.setIsActive(this.isActive);
		
		boolean resp=MasterDataManagement.updateRegType(typeDTO);
		
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
	
	public String deleteRegType()
	{		
		HttpServletResponse response = ServletActionContext.getResponse();
		
		String msg="Error in Delete Operation.";
		boolean resp=MasterDataManagement.deleteRegType(typeId);
		if(resp==true)
			msg="Successfully Deleted";
		
		try{
        	response.setContentType("text/html");
        	response.setHeader("Cache-Control", "no-cache");
        	response.getWriter().write(msg);
        	response.flushBuffer();
          }
        catch(Exception e) {e.printStackTrace();}
        
		return null;
								
	}
	
	public ArrayList<RegTypeDTO> getRegTypeList() {
		return regTypeList;
	}
	public void setRegTypeList(ArrayList<RegTypeDTO> regTypeList) {
		this.regTypeList = regTypeList;
	}
	public List<RegTypeDTO> getrList() {
		return rList;
	}
	public void setrList(List<RegTypeDTO> rList) {
		this.rList = rList;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getRegIdSuffix() {
		return regIdSuffix;
	}
	public void setRegIdSuffix(String regIdSuffix) {
		this.regIdSuffix = regIdSuffix;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	
	
	
}
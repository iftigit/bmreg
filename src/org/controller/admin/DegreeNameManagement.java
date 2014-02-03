package org.controller.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.model.DegreeDAO;
import org.model.MasterDataManagement;
import org.table.DegreeDTO;

import com.opensymphony.xwork2.ActionSupport;

public class DegreeNameManagement extends ActionSupport{

	private static final long serialVersionUID = 1L;
	ArrayList<DegreeDTO> degreeList;
	private int degreeId;
	private String degreeName;
	private int viewSerial;
	
	public String execute()
	{		
		degreeList=DegreeDAO.getAllDegree();		
		return SUCCESS;	
	}
	public String addNewDegreeName()
	{		
		HttpServletResponse response = ServletActionContext.getResponse();
		DegreeDTO degreeDTO=new DegreeDTO();
		degreeDTO.setDegreeName(this.degreeName);
		degreeDTO.setViewSerial(this.viewSerial);
		String msg="Error in Add Operation.";
		boolean resp=MasterDataManagement.addNewDegreeName(degreeDTO);
		
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
	public String updateDegreeName()
	{		
		HttpServletResponse response = ServletActionContext.getResponse();
		String msg="Error in Update Operation.";
		DegreeDTO degreeDTO=new DegreeDTO();
		degreeDTO.setDegreeName(this.degreeName);
		degreeDTO.setViewSerial(this.viewSerial);		
		degreeDTO.setDegreeId(this.degreeId);
		boolean resp=MasterDataManagement.updateDegreeName(degreeDTO);
		
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
	public String deleteDegreeName()
	{		
		HttpServletResponse response = ServletActionContext.getResponse();
		
		String msg="Error in Delete Operation.";
		boolean resp=MasterDataManagement.deleteDegreeName(degreeId);
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
	
	

	public ArrayList<DegreeDTO> getDegreeList() {
		return degreeList;
	}

	public void setDegreeList(ArrayList<DegreeDTO> degreeList) {
		this.degreeList = degreeList;
	}
	public int getDegreeId() {
		return degreeId;
	}
	public void setDegreeId(int degreeId) {
		this.degreeId = degreeId;
	}
	public String getDegreeName() {
		return degreeName;
	}
	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}
	public int getViewSerial() {
		return viewSerial;
	}
	public void setViewSerial(int viewSerial) {
		this.viewSerial = viewSerial;
	}
	
	


}

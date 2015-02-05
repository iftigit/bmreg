package org.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.model.ParameterDAO;
import org.table.GenderDTO;

import com.opensymphony.xwork2.ActionSupport;

public class GenderManagement extends ActionSupport{

	private static final long serialVersionUID = 1L;
	ArrayList<GenderDTO> genderList;
	private List<GenderDTO> gList;
	
	public String execute()
	{
		genderList=ParameterDAO.getGenderList("all");
		return SUCCESS;	
	}
	
	
	public String updateGenderList(){
			
		boolean resp=ParameterDAO.updateGenderList(gList);
		genderList=ParameterDAO.getGenderList("all");
		return SUCCESS;
		
	}


	public ArrayList<GenderDTO> getGenderList() {
		return genderList;
	}


	public void setGenderList(ArrayList<GenderDTO> genderList) {
		this.genderList = genderList;
	}


	public List<GenderDTO> getgList() {
		return gList;
	}


	public void setgList(List<GenderDTO> gList) {
		this.gList = gList;
	}
	
	
}

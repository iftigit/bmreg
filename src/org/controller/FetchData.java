package org.controller;

import java.util.ArrayList;

import org.apache.struts2.ServletActionContext;

public class FetchData {
	
	ArrayList dataLst;
	
	public String execute(){
		String distid = (String)ServletActionContext.getRequest().getParameter("distid").toString();
		String thanaid = (String)ServletActionContext.getRequest().getParameter("thanaid").toString();
		
		DataDAO dDAO =new DataDAO();
		this.dataLst=dDAO.getDataList(distid, thanaid);
		
		return "success";
	}

	public ArrayList getDataLst() {
		return dataLst;
	}

	public void setDataLst(ArrayList dataLst) {
		this.dataLst = dataLst;
	}
	
	
}

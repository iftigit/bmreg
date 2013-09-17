package org.controller;

import java.util.ArrayList;

import org.apache.struts2.ServletActionContext;

public class DataEntryContinue {

	private ArrayList<DataOO> distLst;

	public ArrayList<DataOO> getDistLst() {
		return distLst;
	}

	public void setDistLst(ArrayList<DataOO> distLst) {
		this.distLst = distLst;
	}
	
	
	public String execute() throws Exception {		
		System.out.println("[!!^!! In DataUpdateAction.java Class !!^!!] :-");		
		ArrayList<DataOO> dist_all =getDistLst();
		
		
		DataDAO dDAO=new DataDAO();
		
		dDAO.updateDataInfo(dist_all);
		
		 
         distLst=dDAO.getDistrictList();
         setDistLst(distLst);
         ServletActionContext.getRequest().getSession().setAttribute("distLst",distLst);
		
		
				
		return "success";
	}
}

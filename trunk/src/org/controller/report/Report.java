package org.controller.report;

import java.util.ArrayList;

import org.model.DemoDAO;
import org.table.DemoDTO;

import com.opensymphony.xwork2.ActionSupport;

public class Report  extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	private ArrayList<DemoDTO> demoList=new ArrayList<DemoDTO>();
	  			
	
	public String demoRegistrationInfo()
	{
		DemoDAO demoDAO=new DemoDAO();
		demoList=demoDAO.getDemoList();
		
		return SUCCESS;
	}

	public ArrayList<DemoDTO> getDemoList() {
		return demoList;
	}
	public void setDemoList(ArrayList<DemoDTO> demoList) {
		this.demoList = demoList;
	}
}

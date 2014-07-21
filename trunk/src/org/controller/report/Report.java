package org.controller.report;

import java.util.ArrayList;

import org.model.DemoDAO;
import org.model.UserDAO;
import org.table.DemoDTO;
import org.table.UserDTO;

import com.opensymphony.xwork2.ActionSupport;

public class Report  extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	private ArrayList<DemoDTO> demoList=new ArrayList<DemoDTO>();
	private ArrayList<UserDTO> userList=new ArrayList<UserDTO>();
	  			
	
	public String demoRegistrationInfo()
	{
		DemoDAO demoDAO=new DemoDAO();
		demoList=demoDAO.getDemoList();
		
		return SUCCESS;
	}
	
	public String openUserRegReportHome()
	{
		UserDAO userDAO=new UserDAO();
		userList=userDAO.getUserListByRoleName("OPEN_REG_OPERATOR");
		
		return SUCCESS;
	}

	public String raSummeryReportHome()
	{
		return SUCCESS;
	}
	
	
	
	public ArrayList<DemoDTO> getDemoList() {
		return demoList;
	}
	public void setDemoList(ArrayList<DemoDTO> demoList) {
		this.demoList = demoList;
	}

	public ArrayList<UserDTO> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<UserDTO> userList) {
		this.userList = userList;
	}
	
}

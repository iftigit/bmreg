package org.controller.regToken;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.model.RegTokenDAO;
import org.model.UserDAO;
import org.table.RegTokenDTO;
import org.table.SettingDTO;
import org.table.UserDTO;

import com.opensymphony.xwork2.ActionSupport;

public class TokenManagement extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	private RegTokenDTO regToken;
	private String regFee;
	private String msg;
	private ArrayList<UserDTO> userList;
	
	public String regTokenHome()
	{
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("loggedInUser");
		
		userList=UserDAO.getUserList("DEMO_REG_OPERATOR",loggedInUser.getDistrictId());
		HashMap<String, SettingDTO> paramList=(HashMap<String, SettingDTO>)getServletContext().getAttribute("ALL_SETTING_PARAM");
		regFee=((SettingDTO)paramList.get("DEMO_REG_FEE")).getParamValue();
		return SUCCESS;
	}

	public String saveRegToken()
	{
		HashMap<String, SettingDTO> paramList=(HashMap<String, SettingDTO>)getServletContext().getAttribute("ALL_SETTING_PARAM");
		RegTokenDAO regTokenDAO=new RegTokenDAO();
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("loggedInUser");
		regToken.setCreatedBy(loggedInUser.getUserId());
		regToken.setAssignedTo("demo1");
		String response=regTokenDAO.insertTokenInformation(regToken);
		
		regFee=((SettingDTO)paramList.get("DEMO_REG_FEE")).getParamValue();
		if(response.equalsIgnoreCase("SUCCESS")){
			msg="Token Successfully Created";
			regToken=null;
		}
		else
			msg="Error in Token Creation";
		return SUCCESS;
	}
	
	public void validate(){
		
	}
	
		
	public RegTokenDTO getRegToken() {
		return regToken;
	}

	public void setRegToken(RegTokenDTO regToken) {
		this.regToken = regToken;
	}

	public String getRegFee() {
		return regFee;
	}

	public void setRegFee(String regFee) {
		this.regFee = regFee;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	
	public ArrayList<UserDTO> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<UserDTO> userList) {
		this.userList = userList;
	}

	public ServletContext getServletContext()
	{
		return ServletActionContext.getServletContext();
	}
}

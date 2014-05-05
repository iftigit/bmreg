package org.controller.regToken;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.model.JobCategoryDAO;
import org.model.LotteryDAO;
import org.model.RegTokenDAO;
import org.model.UserDAO;
import org.table.RegTokenDTO;
import org.table.SelectionParamDTO;
import org.table.SettingDTO;
import org.table.UserDTO;
import org.table.UserTmpDTO;

import util.connection.ConnectionManager;

import com.opensymphony.xwork2.ActionSupport;

public class TokenManagement extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	private RegTokenDTO regToken;
	private String regFee;
	private String msg;
	private ArrayList<UserDTO> userList;
	private String tokenId;
	private String demoUserId;
	ArrayList<RegTokenDTO> tokenList=new ArrayList<RegTokenDTO>();
	private String tokenListString;
	private String fromDate;
	private String toDate;
	
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
//		regToken.setAssignedTo("demo1");
		String response=regTokenDAO.insertTokenInformation(regToken);
		
		regFee=((SettingDTO)paramList.get("DEMO_REG_FEE")).getParamValue();
		if(!response.equalsIgnoreCase("-1")){
			msg="Token Successfully Created";
			regToken=null;
			tokenId=response;
			return "report";
		}
		else
			msg="Error in Token Creation";
		return SUCCESS;
	}
	
	public String tokenHistoryHome(){
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("loggedInUser");
		userList=UserDAO.getDemoUserList(loggedInUser.getUserId());
		return SUCCESS;
	}
	
	public String searchTokenHistory(){
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("loggedInUser");
		if(!loggedInUser.getUserType().equalsIgnoreCase("DEMO_REG_ADMIN"))
		{
			return "logout";
		}
		RegTokenDAO regTokenDAO=new RegTokenDAO();
		tokenList=regTokenDAO.getTokenList(demoUserId,0,fromDate,toDate);
		
		return SUCCESS;
	}
	public String tokenPrintPreview(){
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("loggedInUser");
		if(!loggedInUser.getUserType().equalsIgnoreCase("DEMO_REG_ADMIN"))
		{
			return "logout";
		}
		RegTokenDAO regTokenDAO=new RegTokenDAO();
		tokenList=regTokenDAO.getTokenList(demoUserId,Integer.parseInt(tokenId),null,null);
		
		tokenListString=tokenList.get(0).getTokenListString();
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
	
	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	
	public String getDemoUserId() {
		return demoUserId;
	}

	public void setDemoUserId(String demoUserId) {
		this.demoUserId = demoUserId;
	}
	
	public ArrayList<RegTokenDTO> getTokenList() {
		return tokenList;
	}

	public void setTokenList(ArrayList<RegTokenDTO> tokenList) {
		this.tokenList = tokenList;
	}

	public ServletContext getServletContext()
	{
		return ServletActionContext.getServletContext();
	}

	public String getTokenListString() {
		return tokenListString;
	}

	public void setTokenListString(String tokenListString) {
		this.tokenListString = tokenListString;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
}

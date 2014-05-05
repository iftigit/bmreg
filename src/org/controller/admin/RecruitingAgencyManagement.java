package org.controller.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.model.JobCategoryDAO;
import org.model.RADAO;
import org.table.JobPreferenceDTO;
import org.table.RecruitingAgencyDTO;

import com.opensymphony.xwork2.ActionSupport;

public class RecruitingAgencyManagement extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	private ArrayList<RecruitingAgencyDTO> agentList=new ArrayList<RecruitingAgencyDTO>();
	private String raId;
	private String agentId;
	private String agentName;
	private String agentLicense;
	private RecruitingAgencyDTO rAgent;
	private String msg;
	
	private String companyName;
	private String address;
	private String phone;
	private String licenseNumber;
	private String status;
	private String statusComments;
	private String pastStatus;
	private String companyType;
	private String licenseDate;
	private String licenseValidTill;
	private String contactPerson;
	private String designation;
				
	
	public String fetchRaList()
	{
		if(raId==null || raId.equalsIgnoreCase(""))
			raId="all";
		agentList=RADAO.getRecruitingAgencyList(raId);
		
		if(raId.equalsIgnoreCase("all"))
			return "raList";
		else{
			if(agentList.size()>0)
				rAgent=agentList.get(0);
			return "singleRA";
		}
	}
	public String searchRaList(){
		agentList=RADAO.searchRecruitingAgency(agentName,agentLicense);
		return SUCCESS;
	}
	public String createNewRa()
	{
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	    	Date licenseDate = sdf.parse(rAgent.getLicenseDate());
	    	Date llisenseValidUpto = sdf.parse(rAgent.getLicenseValidTill());
	
	
	    	if(licenseDate.compareTo(llisenseValidUpto)>0){
	    		msg="License date cannot be greater than the valid upto date.";
	    	}
		}catch(ParseException ex){
    		ex.printStackTrace();
    		msg="Incorrect format of License date.";
    	}
		if(msg==null || msg.equalsIgnoreCase("")){
			boolean response=RADAO.createNewRA(rAgent);
			if(response==true){			
				msg="Successfully Created new Recruting Agency";
				rAgent=null;
			}
			else
				msg="Problem in creating new Recruting Agency";
		}
		return SUCCESS;
	}
	public String updateRa()
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		String msg="Error in Add Operation.";
		
		try{
        	response.setContentType("text/html");
        	response.setHeader("Cache-Control", "no-cache");
        	response.getWriter().write(msg);
        	response.flushBuffer();
          }
        catch(Exception e) {e.printStackTrace();}
        
		return null;	
	}
	public ArrayList<RecruitingAgencyDTO> getAgentList() {
		return agentList;
	}
	public void setAgentList(ArrayList<RecruitingAgencyDTO> agentList) {
		this.agentList = agentList;
	}
	public String getRaId() {
		return raId;
	}
	public void setRaId(String raId) {
		this.raId = raId;
	}
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getAgentLicense() {
		return agentLicense;
	}
	public void setAgentLicense(String agentLicense) {
		this.agentLicense = agentLicense;
	}
	public RecruitingAgencyDTO getrAgent() {
		return rAgent;
	}
	public void setrAgent(RecruitingAgencyDTO rAgent) {
		this.rAgent = rAgent;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusComments() {
		return statusComments;
	}
	public void setStatusComments(String statusComments) {
		this.statusComments = statusComments;
	}
	public String getPastStatus() {
		return pastStatus;
	}
	public void setPastStatus(String pastStatus) {
		this.pastStatus = pastStatus;
	}
	public String getCompanyType() {
		return companyType;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	public String getLicenseDate() {
		return licenseDate;
	}
	public void setLicenseDate(String licenseDate) {
		this.licenseDate = licenseDate;
	}
	public String getLicenseValidTill() {
		return licenseValidTill;
	}
	public void setLicenseValidTill(String licenseValidTill) {
		this.licenseValidTill = licenseValidTill;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
}

package org.controller.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
	
	public String fetchRaList()
	{
		if(raId==null || raId.equalsIgnoreCase(""))
			raId="all";
		agentList=RADAO.getRecruitingAgencyList(raId);
		return SUCCESS;	
	}
	public String searchRaList(){
		agentList=RADAO.searchRecruitingAgency(agentId,agentName,agentLicense);
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
		if(msg.equalsIgnoreCase("")){
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
		
}

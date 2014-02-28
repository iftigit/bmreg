package org.controller.admin;

import java.util.ArrayList;

import org.model.CountryDAO;
import org.model.LanguageDAO;
import org.model.RADAO;
import org.table.CountryDTO;
import org.table.LanguageDTO;
import org.table.RecruitingAgencyDTO;
import org.table.SelectionParamDTO;

import com.opensymphony.xwork2.ActionSupport;

public class LotteryManagement extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	private ArrayList<RecruitingAgencyDTO> agentList=new ArrayList<RecruitingAgencyDTO>();
	private ArrayList<CountryDTO> countryList=new ArrayList<CountryDTO>();
	ArrayList<LanguageDTO> languageList=new ArrayList<LanguageDTO>();
	private SelectionParamDTO selection;
	private String agentId;
	private String workOrder;
	
	public String selectionHome()
	{
		agentList=RADAO.getRecruitingAgencyList("all");
		countryList=CountryDAO.getAllCountry();
		languageList=LanguageDAO.getAllLanguage();
		return SUCCESS;	
	}
	public String jobseekerSelection(){
		
		return null;
	}
	public String raLotteryHome(){
		
		agentList=RADAO.getRecruitingAgencyList("all");
		return SUCCESS;
	}
	public String searchSelection(){
		return SUCCESS;
	}

	public ArrayList<RecruitingAgencyDTO> getAgentList() {
		return agentList;
	}

	public void setAgentList(ArrayList<RecruitingAgencyDTO> agentList) {
		this.agentList = agentList;
	}

	public ArrayList<CountryDTO> getCountryList() {
		return countryList;
	}

	public void setCountryList(ArrayList<CountryDTO> countryList) {
		this.countryList = countryList;
	}

	public ArrayList<LanguageDTO> getLanguageList() {
		return languageList;
	}

	public void setLanguageList(ArrayList<LanguageDTO> languageList) {
		this.languageList = languageList;
	}
	public SelectionParamDTO getSelection() {
		return selection;
	}
	public void setSelection(SelectionParamDTO selection) {
		this.selection = selection;
	}
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public String getWorkOrder() {
		return workOrder;
	}
	public void setWorkOrder(String workOrder) {
		this.workOrder = workOrder;
	}
}

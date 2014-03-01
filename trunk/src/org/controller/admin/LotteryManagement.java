package org.controller.admin;

import java.util.ArrayList;

import org.model.CountryDAO;
import org.model.LanguageDAO;
import org.model.LotteryDAO;
import org.model.RADAO;
import org.table.CountryDTO;
import org.table.LanguageDTO;
import org.table.RecruitingAgencyDTO;
import org.table.SelectedEmpDTO;
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
	private ArrayList<SelectionParamDTO> selectionList;
	private ArrayList<SelectedEmpDTO> jobseekerList;
	private int selectionId;
	String[] selectStatusList;
	private String msg;
	
	public String selectionHome()
	{
		agentList=RADAO.getRecruitingAgencyList("all");
		countryList=CountryDAO.getAllCountry();
		languageList=LanguageDAO.getAllLanguage();
		return SUCCESS;	
	}
	public String jobseekerSelection(){
		
		int responseCode=LotteryDAO.jobseekerSelection(selection);
		if(responseCode==-11){
			msg="No Jobseeker found for the selected criteria.";
			return SUCCESS;
		}
		agentList=RADAO.getRecruitingAgencyList("all");
		countryList=CountryDAO.getAllCountry();
		languageList=LanguageDAO.getAllLanguage();
		selectionId=responseCode;
		return "report";
	}
	public String raLotteryHome(){
		
		agentList=RADAO.getRecruitingAgencyList("all");
		return SUCCESS;
	}
	public String searchSelection(){
		LotteryDAO lotteryDAO=new LotteryDAO();
		selectionList=lotteryDAO.getSelectionList(agentId,workOrder);
		return SUCCESS;
	}
	public String fetchSelectionDetail(){
		LotteryDAO lotteryDAO=new LotteryDAO();
		jobseekerList=lotteryDAO.getSelectionDetail(selectionId);
		selection=lotteryDAO.getSelectionCriteria(selectionId);
		return SUCCESS;
	}
	
	public String saveJobseekerSelection(){
		LotteryDAO lotteryDAO=new LotteryDAO();
		boolean result=lotteryDAO.saveJobseekerSelection(selectionId,selectStatusList);
		jobseekerList=lotteryDAO.getSelectionDetail(selectionId);
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
	public ArrayList<SelectionParamDTO> getSelectionList() {
		return selectionList;
	}
	public void setSelectionList(ArrayList<SelectionParamDTO> selectionList) {
		this.selectionList = selectionList;
	}
	public int getSelectionId() {
		return selectionId;
	}
	public void setSelectionId(int selectionId) {
		this.selectionId = selectionId;
	}
	public ArrayList<SelectedEmpDTO> getJobseekerList() {
		return jobseekerList;
	}
	public void setJobseekerList(ArrayList<SelectedEmpDTO> jobseekerList) {
		this.jobseekerList = jobseekerList;
	}
	public String[] getSelectStatusList() {
		return selectStatusList;
	}
	public void setSelectStatusList(String[] selectStatusList) {
		this.selectStatusList = selectStatusList;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}	
}

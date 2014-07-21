package org.controller.admin;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.model.CountryDAO;
import org.model.JobCategoryDAO;
import org.model.LanguageDAO;
import org.model.LotteryDAO;
import org.model.RADAO;
import org.table.CountryDTO;
import org.table.LanguageDTO;
import org.table.RecruitingAgencyDTO;
import org.table.SelectedEmpDTO;
import org.table.SelectionParamDTO;
import org.table.SelectionReportFieldDTO;
import org.table.UserDTO;

import util.connection.ConnectionManager;

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
	private ArrayList<SelectionReportFieldDTO> fieldList;
	private String selectionId;
	String[] selectStatusList;
	private String msg;
	private String operationType;
	private String reportType;

	private String cExpYears;
	private String cJobExp;
	private String cJobPreference;
	private String cGender;
	private String cCountryPrefernce;
	private String cLanguage;
	
		
	public String selectionHome()
	{
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("loggedInUser");
		if(!loggedInUser.getUserType().equalsIgnoreCase("SYSTEM_ADMIN"))
		{
			return "logout";
		}
		

		agentList=RADAO.getRecruitingAgencyList("all");
		countryList=CountryDAO.getAllCountry(0);
		languageList=LanguageDAO.getAllLanguage(0);
		return SUCCESS;	
	}
	public String jobseekerSelection(){

		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("loggedInUser");
		if(!loggedInUser.getUserType().equalsIgnoreCase("SYSTEM_ADMIN"))
		{
			return "logout";
		}

		LotteryDAO lotteryDAO=new LotteryDAO();
		int responseCode=lotteryDAO.jobseekerSelection(selection);
		if(responseCode==-11 || responseCode==0){
			msg="No Jobseeker found for the selected criteria.";
			agentList=RADAO.getRecruitingAgencyList("all");
			countryList=CountryDAO.getAllCountry(0);
			languageList=LanguageDAO.getAllLanguage(0);
			
			return "selectionHome";
		}
		selectionId=String.valueOf(responseCode);
		reportType=selection.getReportType();
		return SUCCESS;
	}
	public String raLotteryHome(){
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("loggedInUser");
		if(!loggedInUser.getUserType().equalsIgnoreCase("SYSTEM_ADMIN"))
		{
			return "logout";
		}

		agentList=RADAO.getRecruitingAgencyList("all");
		return SUCCESS;
	}
	public String searchSelection(){
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("loggedInUser");
		if(!loggedInUser.getUserType().equalsIgnoreCase("SYSTEM_ADMIN"))
		{
			return "logout";
		}

		LotteryDAO lotteryDAO=new LotteryDAO();
		selectionList=lotteryDAO.getSelectionList(agentId,workOrder);
		
		if(selectionList!=null && selectionList.size()>0){
			JobCategoryDAO jcDAO=new JobCategoryDAO();
			Connection conn = ConnectionManager.getConnection();
			for(int i=0;i<selectionList.size();i++){
				SelectionParamDTO selection=selectionList.get(i);
				selection.setJobPreferenceDesc(jcDAO.getJobPreferenceDescription(conn,selection.getJobPreference()));	
				selection.setJobExperienceDesc(jcDAO.getJobExperienceDescription(conn,selection.getJobExperience()));
				selectionList.set(i, selection);
			}
			ConnectionManager.closeConnection(conn);
		}
		return SUCCESS;
	}
	
	public String fetchJobseekerCount()
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		LotteryDAO lotteryDAO=new LotteryDAO();
		
		SelectionParamDTO selection=new SelectionParamDTO();
		
		selection.setYearOfExperience(cExpYears);
		selection.setJobExperience(cJobExp);
		selection.setJobPreference(cJobPreference);
		selection.setGender(cGender);
		selection.setCountryPreference(cCountryPrefernce);
		selection.setLanguages(cLanguage.equalsIgnoreCase("null")?null:cLanguage);
		
		
		int total=lotteryDAO.getTotalJobseeker(selection);
		
		try{
        	response.setContentType("text/html");
        	response.setHeader("Cache-Control", "no-cache");
        	response.getWriter().write(String.valueOf(total));
        	response.flushBuffer();
          }
        catch(Exception e) {e.printStackTrace();}
        
		return null;
	}
	public String fetchSelectionDetail(){
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("loggedInUser");
		if(!loggedInUser.getUserType().equalsIgnoreCase("SYSTEM_ADMIN"))
		{
			return "logout";
		}

		LotteryDAO lotteryDAO=new LotteryDAO();
		JobCategoryDAO jcDAO=new JobCategoryDAO();
		jobseekerList=lotteryDAO.getSelectionDetail(Integer.parseInt(selectionId));
		selection=lotteryDAO.getSelectionCriteria(Integer.parseInt(selectionId));
		Connection conn = ConnectionManager.getConnection();
		selection.setJobExperienceDesc(jcDAO.getJobExperienceDescription(conn,selection.getJobExperience()));
		selection.setJobPreferenceDesc(jcDAO.getJobPreferenceDescription(conn,selection.getJobPreference()));
		ConnectionManager.closeConnection(conn);
		if(operationType!=null && operationType.equalsIgnoreCase("save"))
			msg="Successfully Saved.";
		else if(operationType!=null && operationType.equalsIgnoreCase("operationType"))
			msg="Successfully Submitted the Selection.";
		
		return SUCCESS;
	}
	
	public String saveJobseekerSelection(){
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("loggedInUser");
		if(!loggedInUser.getUserType().equalsIgnoreCase("SYSTEM_ADMIN"))
		{
			return "logout";
		}

		LotteryDAO lotteryDAO=new LotteryDAO();
		boolean result=lotteryDAO.saveJobseekerSelection(Integer.parseInt(selectionId),selectStatusList);
		
		if(result==true && operationType.equalsIgnoreCase("finalSubmit")){
			operationType="save";
			boolean fResult=lotteryDAO.finalSubmitJobseekerSelection(Integer.parseInt(selectionId));
			if(fResult==true)
				operationType="finalSubmit";
		}
		jobseekerList=lotteryDAO.getSelectionDetail(Integer.parseInt(selectionId));
		agentList=RADAO.getRecruitingAgencyList("all");
				
		return SUCCESS;
	}
	public String selectionReportSetting(){
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("loggedInUser");
		if(!loggedInUser.getUserType().equalsIgnoreCase("SYSTEM_ADMIN"))
		{
			return "logout";
		}

		LotteryDAO lotteryDAO=new LotteryDAO();
		fieldList=lotteryDAO.getSettingReportFields();
		return SUCCESS;
	}
	public String saveSelectionReportSetting(){
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("loggedInUser");
		if(!loggedInUser.getUserType().equalsIgnoreCase("SYSTEM_ADMIN"))
		{
			return "logout";
		}

		LotteryDAO lotteryDAO=new LotteryDAO();
		boolean response=lotteryDAO.updateSelectionReportSettings(fieldList);
		fieldList=lotteryDAO.getSettingReportFields();
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
	
	public String getSelectionId() {
		return selectionId;
	}
	public void setSelectionId(String selectionId) {
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
	public ArrayList<SelectionReportFieldDTO> getFieldList() {
		return fieldList;
	}
	public void setFieldList(ArrayList<SelectionReportFieldDTO> fieldList) {
		this.fieldList = fieldList;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getcExpYears() {
		return cExpYears;
	}
	public void setcExpYears(String cExpYears) {
		this.cExpYears = cExpYears;
	}
	public String getcJobExp() {
		return cJobExp;
	}
	public void setcJobExp(String cJobExp) {
		this.cJobExp = cJobExp;
	}
	public String getcJobPreference() {
		return cJobPreference;
	}
	public void setcJobPreference(String cJobPreference) {
		this.cJobPreference = cJobPreference;
	}
	public String getcGender() {
		return cGender;
	}
	public void setcGender(String cGender) {
		this.cGender = cGender;
	}
	public String getcCountryPrefernce() {
		return cCountryPrefernce;
	}
	public void setcCountryPrefernce(String cCountryPrefernce) {
		this.cCountryPrefernce = cCountryPrefernce;
	}
	public String getcLanguage() {
		return cLanguage;
	}
	public void setcLanguage(String cLanguage) {
		this.cLanguage = cLanguage;
	}
	
	
}

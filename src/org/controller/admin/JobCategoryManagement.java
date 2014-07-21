
package org.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.model.JobCategoryDAO;
import org.model.MasterDataManagement;
import org.table.JobCategoryDTO;
import org.table.JobPreferenceDTO;

import com.opensymphony.xwork2.ActionSupport;

public class JobCategoryManagement extends ActionSupport{

	private static final long serialVersionUID = 1L;
	ArrayList<JobCategoryDTO> jobList;
	
	private int categoryId;
	private String categoryName;
	
	private int subCategoryId;
	private String subCategoryName;
	
	private int subSubCategoryId;
	private String subSubCategoryName;
	
	private int jobId;
	private String jobName;
	private int jobTypeId;
	private int visibility;
	
	private int pJobCategoryId;
	private int pJobSubCategoryId;
	private int pJobSubSubCategoryId;
	
	
	private JobPreferenceDTO jobDTO;
	
	private ArrayList<JobPreferenceDTO> mainJobList=new ArrayList<JobPreferenceDTO>();
	private ArrayList<JobPreferenceDTO> subJobList=new ArrayList<JobPreferenceDTO>();
	private ArrayList<JobPreferenceDTO> subSubJobList=new ArrayList<JobPreferenceDTO>();
	
	public String execute()
	{
		jobList=JobCategoryDAO.getAllJob();
		mainJobList=JobCategoryDAO.getAllJob(1,1);
		return SUCCESS;	
	}
	public String saveJobStatus()
	{		
		HttpServletResponse response = ServletActionContext.getResponse();
		String msg="Error in Update Operation.";
		
		boolean resp=JobCategoryDAO.updateJobStatus(jobId, visibility);
		
		if(resp==true){
			msg="Successfully Updated";

			ArrayList<JobPreferenceDTO> activeJobCategoryList=new ArrayList<JobPreferenceDTO>();
			activeJobCategoryList=JobCategoryDAO.getAllJob(1,1);
			ServletActionContext.getServletContext().setAttribute("ACTIVE_JOB_MAIN_CATEGORY", activeJobCategoryList);
			

		}
		
		try{
        	response.setContentType("text/html");
        	response.setHeader("Cache-Control", "no-cache");
        	response.getWriter().write(msg);
        	response.flushBuffer();
          }
        catch(Exception e) {e.printStackTrace();}
        
		return null;
								
	}
	public String createNewJob()
	{		
		HttpServletResponse response = ServletActionContext.getResponse();
		boolean resp=false;
		String msg="Error in new Job create Operation.";
		if(MasterDataManagement.jobExistsYN(jobName,jobTypeId)==true){
			msg="Job already exists.";
		}
		else{
		    resp=MasterDataManagement.createNewJob(jobName,jobTypeId);
		
			if(resp==true)
				msg="New Job Successfully Created.";
		}

		try{
        	response.setContentType("text/html");
        	response.setHeader("Cache-Control", "no-cache");
        	response.getWriter().write(msg);
        	response.flushBuffer();
          }
        catch(Exception e) {e.printStackTrace();}
        
		return null;
								
	}
	
	public String getJobEditPanel()
	{		
		mainJobList=JobCategoryDAO.getAllJob(1,0);
		subJobList=JobCategoryDAO.getSubJobs(categoryId,2,0);
		subSubJobList=JobCategoryDAO.getSubJobs(subCategoryId,3,0);
		jobDTO=new JobPreferenceDTO();
		jobDTO.setCategoryId(categoryId);
		jobDTO.setSubCategoryId(subCategoryId);
		jobDTO.setSubSubCategoryId(subSubCategoryId);
        
		return SUCCESS;								
	}
	public String editJobCategoryMapping(){
		HttpServletResponse response = ServletActionContext.getResponse();
		String msg="";
		
		if(categoryId!=pJobCategoryId)
			msg="You are not allowed to change Main Job Category during edit operation.";
		else{
		String deleteQuery="delete JOBS_MAPPING where (parent_id,child_id) in (("+pJobCategoryId+","+pJobSubCategoryId+") , ("+pJobSubCategoryId+","+pJobSubSubCategoryId+"))";
		
		String insert1="";
		String insert2="";

		if(subCategoryId!=-99)
			insert1="INTO JOBS_MAPPING values ("+categoryId+","+subCategoryId+")";
		if(subSubCategoryId!=-99)
			insert2="INTO JOBS_MAPPING values ("+subCategoryId+","+subSubCategoryId+")";
		
		String insertQuery="INSERT ALL   "+insert1+"  "+insert2+" SELECT * FROM dual";
		

		boolean resp=MasterDataManagement.editJobMapping(deleteQuery, insertQuery);
		if(resp==true)
			msg="Information successfully updated.";
		else
			msg="Error during update operation.";
		}
		
		try{
        	response.setContentType("text/html");
        	response.setHeader("Cache-Control", "no-cache");
        	response.getWriter().write(msg);
        	response.flushBuffer();
          }
        catch(Exception e) {e.printStackTrace();}
        
		return null;

	}
	
	public ArrayList<JobCategoryDTO> getJobList() {
		return jobList;
	}


	public void setJobList(ArrayList<JobCategoryDTO> jobList) {
		this.jobList = jobList;
	}


	public int getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public int getSubCategoryId() {
		return subCategoryId;
	}


	public void setSubCategoryId(int subCategoryId) {
		this.subCategoryId = subCategoryId;
	}


	public String getSubCategoryName() {
		return subCategoryName;
	}


	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}


	public int getSubSubCategoryId() {
		return subSubCategoryId;
	}


	public void setSubSubCategoryId(int subSubCategoryId) {
		this.subSubCategoryId = subSubCategoryId;
	}


	public String getSubSubCategoryName() {
		return subSubCategoryName;
	}


	public void setSubSubCategoryName(String subSubCategoryName) {
		this.subSubCategoryName = subSubCategoryName;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public int getVisibility() {
		return visibility;
	}
	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public int getJobTypeId() {
		return jobTypeId;
	}
	public void setJobTypeId(int jobTypeId) {
		this.jobTypeId = jobTypeId;
	}
	public ArrayList<JobPreferenceDTO> getMainJobList() {
		return mainJobList;
	}
	public void setMainJobList(ArrayList<JobPreferenceDTO> mainJobList) {
		this.mainJobList = mainJobList;
	}
	public ArrayList<JobPreferenceDTO> getSubJobList() {
		return subJobList;
	}
	public void setSubJobList(ArrayList<JobPreferenceDTO> subJobList) {
		this.subJobList = subJobList;
	}
	public ArrayList<JobPreferenceDTO> getSubSubJobList() {
		return subSubJobList;
	}
	public void setSubSubJobList(ArrayList<JobPreferenceDTO> subSubJobList) {
		this.subSubJobList = subSubJobList;
	}
	public JobPreferenceDTO getJobDTO() {
		return jobDTO;
	}
	public void setJobDTO(JobPreferenceDTO jobDTO) {
		this.jobDTO = jobDTO;
	}
	public int getpJobCategoryId() {
		return pJobCategoryId;
	}
	public void setpJobCategoryId(int pJobCategoryId) {
		this.pJobCategoryId = pJobCategoryId;
	}
	public int getpJobSubCategoryId() {
		return pJobSubCategoryId;
	}
	public void setpJobSubCategoryId(int pJobSubCategoryId) {
		this.pJobSubCategoryId = pJobSubCategoryId;
	}
	public int getpJobSubSubCategoryId() {
		return pJobSubSubCategoryId;
	}
	public void setpJobSubSubCategoryId(int pJobSubSubCategoryId) {
		this.pJobSubSubCategoryId = pJobSubSubCategoryId;
	}
	
	
}

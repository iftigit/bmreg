package org.controller.interview;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;
import org.model.InterviewDAO;
import org.table.InterviewInfoDTO;

import com.opensymphony.xwork2.ActionSupport;

public class InterviewAction extends ActionSupport implements ServletContextAware{
	

	private static final long serialVersionUID = 1245190563723783190L;
	private String jobSeekerNumber;
	private InterviewInfoDTO interviewInfo;
	String message;
	
	public String fetchEmpInfoForInterview()
	{
		InterviewDAO interviewDao=new InterviewDAO();
		interviewInfo=interviewDao.getGeneralInfoForInterview(jobSeekerNumber);
		message="";
		return SUCCESS;
	}
	
	public String saveVivaStatus()
	{
		InterviewDAO interviewDao=new InterviewDAO();
		boolean res=interviewDao.saveVivaStatus(interviewInfo);
		if(res==true)
		{
			interviewInfo=null;
			message="Information Successfully Updated";
		}
		else
		{
			interviewInfo=interviewDao.getGeneralInfoForInterview(interviewInfo.getJobSeekerNumber());
			message="Error in Update Operation.";
		}
		return SUCCESS;
	}
	
	public String getJobSeekerNumber() {
		return jobSeekerNumber;
	}
	public void setJobSeekerNumber(String jobSeekerNumber) {
		this.jobSeekerNumber = jobSeekerNumber;
	}
	
	public InterviewInfoDTO getInterviewInfo() {
		return interviewInfo;
	}


	public void setInterviewInfo(InterviewInfoDTO interviewInfo) {
		this.interviewInfo = interviewInfo;
	}


	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}

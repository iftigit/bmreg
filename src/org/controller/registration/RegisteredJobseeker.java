package org.controller.registration;

import java.util.ArrayList;


import org.apache.struts2.ServletActionContext;
import org.model.RegistrationDAO;
import org.table.UserDTO;

import com.opensymphony.xwork2.ActionSupport;

public class RegisteredJobseeker extends ActionSupport{


	private static final long serialVersionUID = -7704533686085177439L;
	
	private ArrayList<JobseekerDTO> jobseekerList;

	RegistrationDAO regDao=new  RegistrationDAO();
	public String getRegisteredJobseekerList()
	{
	
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("loggedInUser");
		//jobseekerList=regDao.getAllRegisteredJobseeker(loggedInUser.getUserId());
		    
    
		return "success";
	}

	public ArrayList<JobseekerDTO> getJobseekerList() {
		return jobseekerList;
	}

	public void setJobseekerList(ArrayList<JobseekerDTO> jobseekerList) {
		this.jobseekerList = jobseekerList;
	}
	
	

}

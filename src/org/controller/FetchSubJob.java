package org.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;
import org.model.JobCategoryDAO;

import com.opensymphony.xwork2.ActionSupport;

public class FetchSubJob extends ActionSupport implements ServletContextAware{


	private static final long serialVersionUID = 5147299393496003409L;

	private int parentJobId;
	private int jobLevel;
	private int componentIndex;
	private String selectType;
	public String execute()
	{
		String res="";
		
		HttpServletResponse response = ServletActionContext.getResponse();
		JobCategoryDAO jcDao=new JobCategoryDAO();
		res=jcDao.getJobCagegorySelectBox(parentJobId,jobLevel,componentIndex,selectType);
		
		
		try{
        	response.setContentType("text/xml");
        	response.setHeader("Cache-Control", "no-cache");
        	response.getWriter().write(res);
        	response.flushBuffer();
          }
        catch(Exception e) {e.printStackTrace();}
        
		return null;
		

	}
	
	public int getParentJobId() {
		return parentJobId;
	}

	public void setParentJobId(int parentJobId) {
		this.parentJobId = parentJobId;
	}

	public int getJobLevel() {
		return jobLevel;
	}

	public void setJobLevel(int jobLevel) {
		this.jobLevel = jobLevel;
	}

	public int getComponentIndex() {
		return componentIndex;
	}

	public void setComponentIndex(int componentIndex) {
		this.componentIndex = componentIndex;
	}
	

	public String getSelectType() {
		return selectType;
	}

	public void setSelectType(String selectType) {
		this.selectType = selectType;
	}

	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}

}

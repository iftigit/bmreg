package org.test;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionContext;



public class Test  {
	
	
	

	

	
	public String execute() 
	{
		
//		Map application = (Map) ActionContext.getContext().get("ATTR_NAME");
//		
		String a=(String)getServletContext().getAttribute("ATTR_NAME");
		System.out.println(a);
		return "success";
	}

	public ServletContext getServletContext()
	{
		return ServletActionContext.getServletContext();
	}






	

}

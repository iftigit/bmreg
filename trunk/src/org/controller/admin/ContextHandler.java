package org.controller.admin;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;



	public class ContextHandler extends ActionSupport implements ServletContextAware{
	
		private static final long serialVersionUID = 6143050756360251924L;

		public ServletContext getServletContext()
		{
			return ServletActionContext.getServletContext();
		}

		public void setServletContext(ServletContext arg0) {
			// TODO Auto-generated method stub
			
		}
	}

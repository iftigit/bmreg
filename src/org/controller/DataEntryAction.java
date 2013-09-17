package org.controller;


import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class DataEntryAction extends ActionSupport {
	
	public String execute() throws Exception {
		ServletActionContext.getRequest().getSession().setAttribute("form_error", null);
        return SUCCESS;
    }
	
	
	
}

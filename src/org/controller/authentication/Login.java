package org.controller.authentication;

import com.opensymphony.xwork2.ActionSupport;

public class Login extends ActionSupport{
	

	private static final long serialVersionUID = 4667542029545661438L;

	public String execute()
	{
		System.out.println("Login Form ...");
		return "success";
	}
	public String homePage()
	{
		return "success";
	}

}

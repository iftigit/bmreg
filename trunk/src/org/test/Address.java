package org.test;

import java.io.IOException;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class Address extends HttpServlet {
	  /**
	 * 
	 */
	private static final long serialVersionUID = -7227671581272961718L;



	public void doGet(HttpServletRequest request,
	                    HttpServletResponse response)
	      throws ServletException, IOException {
		  
		  
	      
	    // Use "request" to read incoming HTTP headers (e.g. cookies)
	    // and HTML form data (e.g. data the user entered and submitted)
	    
	    // Use "response" to specify the HTTP response line and headers
	    // (e.g. specifying the content type, setting cookies).
	    
	    
	    // Use "out" to send content to browser
	  }
	


public void init(ServletConfig config)  throws ServletException
{	
	super.init(config);



    // save for other servlets
    config.getServletContext().setAttribute("ATTR_NAME", "Ifti");
//	String myProp="Toraf";
//	
//	//ServletActionContext.getRequest().getSession().setAttribute("aaaa","true");
//	
//	
//	((ActionContext) ServletActionContext.getRequest().getSession()).put("myId", myProp);
//	
//	request.setAttribute("myId", myProp);
//	
//	session_map.put("username", "Mir Abu");
	
	
	
//	Object attr = ServletActionContext.getServletContext().getAttribute("ATTR_NAME");
	// Do something with it and...
	
	
	
	//ServletActionContext.getServletContext().setAttribute("ATTR_NAME", "Toraf");
	// getServletContext().setAttribute("ATTR_NAME", "Ifti"); 
	
	
	//return SUCCESS;

}





}

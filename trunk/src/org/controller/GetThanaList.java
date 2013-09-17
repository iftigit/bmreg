package org.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.struts2.ServletActionContext;


import com.opensymphony.xwork2.ActionSupport;

public class GetThanaList extends ActionSupport{

	public String execute() throws Exception {
		
		String id=(String)ServletActionContext.getRequest().getParameter("id");
		String index=(String)ServletActionContext.getRequest().getParameter("index");
		
		String tid=(String)ServletActionContext.getRequest().getParameter("tid")==null?"null":(String)ServletActionContext.getRequest().getParameter("tid");
		
		DataDAO cDAO=new DataDAO();
		
		ArrayList list=cDAO.getThanaList(id);

		String str="<select name='thanaid' id='thanaid'   style='width: 140px'>";


		//str+="<option value='0' selected='selected'>--Select Item--</option>";
		
		str+="<option value='0'>--Select Thana-- "+  
		   
		      "</option>";
		
		
		
		
		for(Object o:list)
		{
		 String s=(String)o;
		 //String[] st=s.split("#");
		 
		 if((!tid.equalsIgnoreCase("null"))&&(tid.equalsIgnoreCase(s)))
		 str+="<option value='"+s+"' selected='selected'>"+s+"</option>";
		 else
		  str+="<option value='"+s+"'>"+s+"</option>"; 
		}
		
		
		
		str+="</select>";
		try {
		
			ServletActionContext.getResponse().getWriter().write(str);
			   
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
   }
	
}

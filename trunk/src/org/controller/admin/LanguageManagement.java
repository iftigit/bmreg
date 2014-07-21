package org.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.model.LanguageDAO;
import org.table.LanguageDTO;

import com.opensymphony.xwork2.ActionSupport;

public class LanguageManagement extends ActionSupport{

	private static final long serialVersionUID = 1L;
	ArrayList<LanguageDTO> languageList;
	private String languageName;
	private int visibility;
	private List<LanguageDTO> lList;
	
	public String execute()
	{
		languageList=LanguageDAO.getAllLanguage(0);
		return SUCCESS;	
	}
	
	public String updateLanguage()
	{		
		HttpServletResponse response = ServletActionContext.getResponse();
		String msg="Error in Update Operation.";
		LanguageDTO languageDTO=new LanguageDTO();
		languageDTO.setLanguage(languageName);
		languageDTO.setVisibility(this.visibility);		

		boolean resp=LanguageDAO.updateLanauge(languageDTO);
		
		if(resp==true)
			msg="Successfully Updated";
		
		try{
        	response.setContentType("text/html");
        	response.setHeader("Cache-Control", "no-cache");
        	response.getWriter().write(msg);
        	response.flushBuffer();
          }
        catch(Exception e) {e.printStackTrace();}
        
		return null;
								
	}
	public String updateLanguageList(){
			
		boolean resp=LanguageDAO.updateLanaugeBatch(lList);
		languageList=LanguageDAO.getAllLanguage(0);
		return SUCCESS;
		
	}

	public int getVisibility() {
		return visibility;
	}

	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}

	public ArrayList<LanguageDTO> getLanguageList() {
		return languageList;
	}

	public void setLanguageList(ArrayList<LanguageDTO> languageList) {
		this.languageList = languageList;
	}

	
	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	public List<LanguageDTO> getlList() {
		return lList;
	}

	public void setlList(List<LanguageDTO> lList) {
		this.lList = lList;
	}

	
}

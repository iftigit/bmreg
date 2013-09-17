package org.controller.cota;

import java.util.ArrayList;

import org.model.CotaDAO;

import com.opensymphony.xwork2.ActionSupport;

public class CotaSelectionAction extends ActionSupport {
	
	
	private ArrayList cotaLst;
	public String execute() throws Exception {
         CotaDAO cDAO=new CotaDAO();
        cotaLst=cDAO.getAllDistWithCota();
        setCotaLst(cotaLst);
        return SUCCESS;
    }
	public ArrayList getCotaLst() {
		return cotaLst;
	}
	public void setCotaLst(ArrayList cotaLst) {
		this.cotaLst = cotaLst;
	}
	
	

}

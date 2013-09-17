package org.controller.cota;

import java.util.ArrayList;
import java.util.Map;

import org.model.CotaDAO;
import org.table.CotaDTO;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CotaUpdateAction {

	private ArrayList<CotaDTO> distcota;
	public ArrayList<CotaDTO> getDistcota() {
		return distcota;
	}

	public void setDistcota(ArrayList<CotaDTO> distcota) {
		this.distcota = distcota;
	}
	
	
	public String execute() throws Exception {		
		System.out.println("[!!^!! In CotaUpdateAction.java Class !!^!!] :-");		
		ArrayList<CotaDTO> dist_cota =getDistcota();
		CotaDAO cotaDAO=new CotaDAO();
		
		cotaDAO.updateCotaInfo(dist_cota);
				
		return "success";
	}

	
		
	
}

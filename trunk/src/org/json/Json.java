package org.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;


import org.apache.struts2.ServletActionContext;
import org.model.AddressDAO;
import org.model.TtcDAO;
import org.table.TtcDTO;

import com.opensymphony.xwork2.ActionSupport;



public class Json extends ActionSupport
{

	private static final long serialVersionUID = 8960849566966201064L;
	private String divisionId;
	private String districtId;
	private String upazillaOrThanaId;
	private String unionOrWardId;
	private String mauzaOrMohollaId;
	private String villageId;
	private String TTC;
	
	private Map<String, ArrayList<String>> divisionList;
	private Map<String, ArrayList<String>> districtList;
	private Map<String, ArrayList<String>> tradeList;
	private Map<String, ArrayList<String>> upazillaOrThanaList;
	private Map<String, ArrayList<String>> unionOrWardList;
	private Map<String, ArrayList<String>> mauzaOrMohollaList;
	private Map<String, ArrayList<String>> villageList;
	
	public void fetchDistrict()
	{
		districtList = new HashMap<String, ArrayList<String>>();
		ArrayList<String> dlist=(ArrayList<String>) getServletContext().getAttribute("DISTRICT_BY_DIVISION_"+divisionId);
		if(dlist==null)
		{
			AddressDAO addDAO = new AddressDAO();
			dlist = addDAO.getDistrict(divisionId);			
			districtList.put("districtList", dlist);
			getServletContext().setAttribute("DISTRICT_BY_DIVISION_"+divisionId,dlist);
		}
		else
			districtList.put("districtList", dlist);
		
			
	}
	public String getTrade()
	{
		tradeList = new HashMap<String, ArrayList<String>>();
		ArrayList<String> tList=(ArrayList<String>) getServletContext().getAttribute("TRADE_BY_TTC_"+TTC);
		if(tList==null)
		{
			TtcDAO addDAO = new TtcDAO();
			tList = addDAO.getTradeForTTC(TTC);
			tradeList.put("tradeList", tList);
			getServletContext().setAttribute("TRADE_BY_TTC_"+TTC,tList);
		}
		else
			tradeList.put("tradeList", tList);
		return SUCCESS;
		
			
	}

	
	public void fetchUpazillaOrThana()
	{
		upazillaOrThanaList = new HashMap<String, ArrayList<String>>();
		ArrayList<String> utList=(ArrayList<String>) getServletContext().getAttribute("UPAZILLA_OR_THANA_BY_DIVISION_DISTRICT_"+divisionId+"_"+districtId);
		if(utList==null)
		{
			AddressDAO addDAO = new AddressDAO();
			//utList = addDAO.getUpazillaOrThana(divisionId,districtId);
			utList = addDAO.getUpazillaOrThana(districtId);
			upazillaOrThanaList.put("upazillaOrThanaList", utList);
			getServletContext().setAttribute("UPAZILLA_OR_THANA_BY_DIVISION_DISTRICT_"+divisionId+"_"+districtId,utList);
		}
		else
			upazillaOrThanaList.put("upazillaOrThanaList", utList);
	
	}
	public void fetchUnionOrWard()
	{
		unionOrWardList = new HashMap<String, ArrayList<String>>();
		ArrayList<String> uwList=(ArrayList<String>) getServletContext().getAttribute("UNION_OR_WARD_BY_DIVISION_DISTRICT_UPAZILLAorTHANA_"+divisionId+"_"+districtId+"_"+upazillaOrThanaId);
		if(uwList==null)
		{
			AddressDAO addDAO = new AddressDAO();
			//uwList = addDAO.getUnionOrWard(divisionId,districtId,upazillaOrThanaId);
			uwList = addDAO.getUnionOrWard(upazillaOrThanaId);
			unionOrWardList.put("unionOrWardList", uwList);
			getServletContext().setAttribute("UNION_OR_WARD_BY_DIVISION_DISTRICT_UPAZILLAorTHANA_"+divisionId+"_"+districtId+"_"+upazillaOrThanaId,uwList);
		}
		else
			unionOrWardList.put("unionOrWardList", uwList);
	
	}
	
	public void fetchMauzaOrMoholla()
	{
		mauzaOrMohollaList = new HashMap<String, ArrayList<String>>();
		ArrayList<String> mmList=(ArrayList<String>) getServletContext().getAttribute("MAUZA_OR_MOHOLLA_BY_DIVISION_DISTRICT_UPAZILLAorTHANA_UNIONorWARD_"+divisionId+"_"+districtId+"_"+upazillaOrThanaId+"_"+unionOrWardId);
	
		if(mmList==null)
		{
			AddressDAO addDAO = new AddressDAO();
			//mmList = addDAO.getMauzaOrMoholla(divisionId,districtId,upazillaOrThanaId,unionOrWardId);		
			mmList = addDAO.getMauzaOrMoholla(unionOrWardId);
			mauzaOrMohollaList.put("mauzaOrMohollaList", mmList);
			getServletContext().setAttribute("MAUZA_OR_MOHOLLA_BY_DIVISION_DISTRICT_UPAZILLAorTHANA_UNIONorWARD_"+divisionId+"_"+districtId+"_"+upazillaOrThanaId+"_"+unionOrWardId,mmList);
		}
		else
			mauzaOrMohollaList.put("mauzaOrMohollaList", mmList);
	
	}
	public void fetchVillage()
	{
		villageList = new HashMap<String, ArrayList<String>>();
		ArrayList<String> vList=(ArrayList<String>) getServletContext().getAttribute("VILLAGE_BY_DIVISION_DISTRICT_UPAZILLAorTHANA_UNIONorWARD_MAUZAorMOHOLLA_"+divisionId+"_"+districtId+"_"+upazillaOrThanaId+"_"+unionOrWardId+"_"+mauzaOrMohollaId);
		if(vList==null)
		{
			AddressDAO addDAO = new AddressDAO();
			//vList = addDAO.getVillage(divisionId,districtId,upazillaOrThanaId,unionOrWardId,mauzaOrMohollaId);
			vList = addDAO.getVillage(mauzaOrMohollaId);
			villageList.put("villageList", vList);
			getServletContext().setAttribute("VILLAGE_BY_DIVISION_DISTRICT_UPAZILLAorTHANA_UNIONorWARD_MAUZAorMOHOLLA_"+divisionId+"_"+districtId+"_"+upazillaOrThanaId+"_"+unionOrWardId+"_"+mauzaOrMohollaId,vList);
		}
		else
			villageList.put("villageList", vList);
	
	}
	
	
	/*
	public void fetchThana()
	{
		thanaList = new HashMap<String, ArrayList<String>>();
		ArrayList<String> tlist=(ArrayList<String>) getServletContext().getAttribute("THANA_BY_DISTRICT_"+distId);
		if(tlist==null)
		{
			AddressDAO addDAO = new AddressDAO();
			tlist = addDAO.getThana(distId);			
			thanaList.put("thanaList", tlist);
			getServletContext().setAttribute("THANA_BY_DISTRICT_"+distId,tlist);
		}
		else
			thanaList.put("thanaList", tlist);
	
	}

	public void fetchUnion()
	{
		unionList = new HashMap<String, ArrayList<String>>();
		ArrayList<String> ulist=(ArrayList<String>) getServletContext().getAttribute("UNION_BY_THANA_"+thanaId);
		
		if(ulist==null)
		{
			AddressDAO addDAO = new AddressDAO();
			ulist = addDAO.getUnion(thanaId);			
			unionList.put("unionList", ulist);
			getServletContext().setAttribute("UNION_BY_THANA_"+thanaId,ulist);
		}
		else
			unionList.put("unionList", ulist);
	
	}

	public void fetchArea()
	{
		ArrayList<String> aList = new ArrayList<String>();
		AddressDAO addDAO = new AddressDAO();
		//aList = addDAO.getArea(wardId,thanaId,districtId);
		areaList = new HashMap<String, ArrayList<String>>();
		areaList.put("areaList", aList);
	}
		public String getArea()
	{
		fetchArea();
		return SUCCESS;
	}
		public String getThana()
	{
		fetchThana();
		return SUCCESS;
	}

	public String getUnion()
	{
		fetchUnion();
		return SUCCESS;
	}

	
*/


	public String getDistrict()
	{
		fetchDistrict();
		return SUCCESS;
	}
	public String getUpazillaOrThana()
	{
		fetchUpazillaOrThana();
		return SUCCESS;
	}

	public String getUnionOrWard()
	{
		fetchUnionOrWard();
		return SUCCESS;
	}
	public String getMauzaOrMoholla()
	{
		fetchMauzaOrMoholla();
		return SUCCESS;
	}
	public String getVillage()
	{
		fetchVillage();
		return SUCCESS;
	}
	


	public String getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getUpazillaOrThanaId() {
		return upazillaOrThanaId;
	}

	public void setUpazillaOrThanaId(String upazillaOrThanaId) {
		this.upazillaOrThanaId = upazillaOrThanaId;
	}

	public String getUnionOrWardId() {
		return unionOrWardId;
	}

	public void setUnionOrWardId(String unionOrWardId) {
		this.unionOrWardId = unionOrWardId;
	}

	public String getMauzaOrMohollaId() {
		return mauzaOrMohollaId;
	}

	public void setMauzaOrMohollaId(String mauzaOrMohollaId) {
		this.mauzaOrMohollaId = mauzaOrMohollaId;
	}

	public String getVillageId() {
		return villageId;
	}

	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}

	public Map<String, ArrayList<String>> getDivisionList() {
		return divisionList;
	}

	public void setDivisionList(Map<String, ArrayList<String>> divisionList) {
		this.divisionList = divisionList;
	}

	public Map<String, ArrayList<String>> getUpazillaOrThanaList() {
		return upazillaOrThanaList;
	}

	public void setUpazillaOrThanaList(
			Map<String, ArrayList<String>> upazillaOrThanaList) {
		this.upazillaOrThanaList = upazillaOrThanaList;
	}

	public Map<String, ArrayList<String>> getUnionOrWardList() {
		return unionOrWardList;
	}

	public void setUnionOrWardList(Map<String, ArrayList<String>> unionOrWardList) {
		this.unionOrWardList = unionOrWardList;
	}

	public Map<String, ArrayList<String>> getMauzaOrMohollaList() {
		return mauzaOrMohollaList;
	}

	public void setMauzaOrMohollaList(
			Map<String, ArrayList<String>> mauzaOrMohollaList) {
		this.mauzaOrMohollaList = mauzaOrMohollaList;
	}

	public Map<String, ArrayList<String>> getVillageList() {
		return villageList;
	}

	public void setVillageList(Map<String, ArrayList<String>> villageList) {
		this.villageList = villageList;
	}

	public Map<String, ArrayList<String>> getDistrictList()
	{
		return districtList;
	}

	public void setDistrictList(Map<String, ArrayList<String>> districtList)
	{
		this.districtList = districtList;
	}

	

	
	public ServletContext getServletContext()
	{
		return ServletActionContext.getServletContext();
	}


	public String getTTC() {
		return TTC;
	}


	public void setTTC(String ttc) {
		TTC = ttc;
	}
	public Map<String, ArrayList<String>> getTradeList() {
		return tradeList;
	}
	public void setTradeList(Map<String, ArrayList<String>> tradeList) {
		this.tradeList = tradeList;
	}
}
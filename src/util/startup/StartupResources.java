package util.startup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.model.AddressDAO;
import org.model.CountryDAO;
import org.model.DegreeDAO;
import org.model.JobCategoryDAO;
import org.model.MasterDataManagement;
import org.model.ParameterDAO;
import org.model.RegistrationDAO;
import org.model.RelationDAO;
import org.model.TtcDAO;
import org.model.UserDAO;
import org.table.AddressDTO;
import org.table.AgeLimitDTO;
import org.table.CountryDTO;
import org.table.DegreeDTO;
import org.table.GenderDTO;
import org.table.JobPreferenceDTO;
import org.table.RegTypeDTO;
import org.table.RelationDTO;
import org.table.SettingDTO;
import org.table.TtcDTO;

public class StartupResources  extends HttpServlet {
	
	private static final long serialVersionUID = -7227671581272961718L;

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {
		  
	  }
	


	public void init(ServletConfig config)  throws ServletException
	{	
		super.init(config);
		
		UserDAO userDao=new UserDAO();
		
		ArrayList<CountryDTO> countryList=new ArrayList<CountryDTO>();
		ArrayList<CountryDTO> activeCountryList=new ArrayList<CountryDTO>();
		countryList=CountryDAO.getAllCountry(0);
		activeCountryList=CountryDAO.getAllCountry(1);
		
		ArrayList<RelationDTO> relationList=new ArrayList<RelationDTO>();
		relationList=RelationDAO.getAllRelation();
		
		ArrayList<DegreeDTO> degreeList=new ArrayList<DegreeDTO>();
		degreeList=DegreeDAO.getAllDegree();
		
		try {Thread.sleep(2000);} 
		catch (InterruptedException e) {e.printStackTrace();}
		
		ArrayList<AddressDTO> divisionList=new ArrayList<AddressDTO>();
		divisionList=AddressDAO.getAllDivision();
		
		ArrayList<AddressDTO> districtList=new ArrayList<AddressDTO>();
		districtList=AddressDAO.getAllDistrict();
		
		ArrayList<AddressDTO> upazillaOrThanaList=new ArrayList<AddressDTO>();
		upazillaOrThanaList=AddressDAO.getAllUpazillaOrThana();
		
		ArrayList<AddressDTO> unionOrWardList=new ArrayList<AddressDTO>();
		unionOrWardList=AddressDAO.getAllUnionOrWard();
				
		ArrayList<AddressDTO> mauzaOrMohollaList=new ArrayList<AddressDTO>();
		mauzaOrMohollaList=AddressDAO.getAllMauzaOrMoholla();
		
		ArrayList<AddressDTO> villageList=new ArrayList<AddressDTO>();
		villageList=AddressDAO.getAllVillage();
		
		try {Thread.sleep(2000);} 
		catch (InterruptedException e) {e.printStackTrace();}
		
		config.getServletContext().setAttribute("ALL_DIVISION", divisionList);
		config.getServletContext().setAttribute("ALL_DISTRICT", districtList);
		config.getServletContext().setAttribute("ALL_UPAZILLA_THANA", upazillaOrThanaList);
		config.getServletContext().setAttribute("ALL_UNON_WARD", unionOrWardList);
		config.getServletContext().setAttribute("ALL_MAUZA_MOHOLLA", mauzaOrMohollaList);
		config.getServletContext().setAttribute("ALL_VILLAGE", villageList);
		
		
		config.getServletContext().setAttribute("ALL_COUNTRY", countryList);
		config.getServletContext().setAttribute("ACTIVE_COUNTRY", activeCountryList);
		config.getServletContext().setAttribute("ALL_RELATION", relationList);
		config.getServletContext().setAttribute("ALL_DEGREE", degreeList);
		
		
		HashMap<Integer, String>  countryMap = new HashMap<Integer, String>();		
		for(int i=0;i<countryList.size();i++)
			countryMap.put(countryList.get(i).getCountryId(), countryList.get(i).getCountryName());
		config.getServletContext().setAttribute("ALL_COUNTRY_MAP", countryMap);
		

		HashMap<Integer, String>  relationMap = new HashMap<Integer, String>();				
		for(int i=0;i<relationList.size();i++)
			relationMap.put(relationList.get(i).getRelationId(), relationList.get(i).getRelationName());
		
		config.getServletContext().setAttribute("ALL_RELATION_MAP", relationMap);

		
		HashMap<Integer, String>  degreeMap = new HashMap<Integer, String>();				
		for(int i=0;i<degreeList.size();i++)
			degreeMap.put(degreeList.get(i).getDegreeId(), degreeList.get(i).getDegreeName());
		
		config.getServletContext().setAttribute("ALL_DEGREE_MAP", degreeMap);

		ArrayList<TtcDTO> TTCList=new ArrayList<TtcDTO>();
		TTCList=TtcDAO.getAllTtc();
		HashMap<Integer, String>  ttcMap = new HashMap<Integer, String>();
				
		config.getServletContext().setAttribute("ALL_TTC", TTCList);
		
		for(int i=0;i<TTCList.size();i++)
		{
			ttcMap.put(TTCList.get(i).getTtcId(), TTCList.get(i).getTtcName());
			
		}
		
		config.getServletContext().setAttribute("ALL_TTC_MAP", ttcMap);
		
		ArrayList<JobPreferenceDTO> jobCategoryList=new ArrayList<JobPreferenceDTO>();
		ArrayList<JobPreferenceDTO> activeJobCategoryList=new ArrayList<JobPreferenceDTO>();
		jobCategoryList=JobCategoryDAO.getAllJob(1,0);
		activeJobCategoryList=JobCategoryDAO.getAllJob(1,1);
		
		try {Thread.sleep(2000);} 
		catch (InterruptedException e) {e.printStackTrace();}
		
		
		HashMap<Integer, String>  jobCategoryMap = new HashMap<Integer, String>();
				
		config.getServletContext().setAttribute("ALL_JOB_MAIN_CATEGORY", jobCategoryList);
		config.getServletContext().setAttribute("ACTIVE_JOB_MAIN_CATEGORY", activeJobCategoryList);
		
		for(int i=0;i<jobCategoryList.size();i++)
		{
			jobCategoryMap.put(jobCategoryList.get(i).getJobId(), jobCategoryList.get(i).getJobTitle());
			
		}
		
		config.getServletContext().setAttribute("ALL_JOB_MAIN_CATEGORY_MAP", jobCategoryMap);
		
		
		ArrayList<JobPreferenceDTO> jobSubCategoryList=new ArrayList<JobPreferenceDTO>();
		ArrayList<JobPreferenceDTO> activeJobSubCategoryList=new ArrayList<JobPreferenceDTO>();
		
		jobSubCategoryList=JobCategoryDAO.getAllJob(2,0);
		activeJobSubCategoryList=JobCategoryDAO.getAllJob(2,1);
		
		HashMap<Integer, String>  jobSubCategoryMap = new HashMap<Integer, String>();
		
		
		config.getServletContext().setAttribute("ALL_JOB_SUB_CATEGORY", jobSubCategoryMap);
		config.getServletContext().setAttribute("ACTIVE_JOB_SUB_CATEGORY", activeJobSubCategoryList);
		
		for(int i=0;i<jobSubCategoryList.size();i++)
		{
			jobSubCategoryMap.put(jobSubCategoryList.get(i).getJobId(), jobSubCategoryList.get(i).getJobTitle());
			
		}
		
		config.getServletContext().setAttribute("ALL_JOB_SUB_CATEGORY_MAP", jobSubCategoryMap);
		

	
		ArrayList<JobPreferenceDTO> allJobList=new ArrayList<JobPreferenceDTO>();
		ArrayList<JobPreferenceDTO> activeJobList=new ArrayList<JobPreferenceDTO>();
		
		allJobList=JobCategoryDAO.getAllJob(99,0);
		activeJobList=JobCategoryDAO.getAllJob(99,1);
		HashMap<Integer, String>  allJobMap = new HashMap<Integer, String>();
		
		
		config.getServletContext().setAttribute("ALL_JOB", allJobList);
		config.getServletContext().setAttribute("ACTIVE_JOB", activeJobList);
		
		for(int i=0;i<allJobList.size();i++)
		{
			allJobMap.put(allJobList.get(i).getJobId(), allJobList.get(i).getJobTitle());
			
		}
		
		config.getServletContext().setAttribute("ALL_JOB_MAP", allJobMap);
		
		
		/*******************Address******************/
		HashMap<Integer, String>  divisionMap = new HashMap<Integer, String>();
		HashMap<Integer, String>  districtMap = new HashMap<Integer, String>();
		HashMap<Integer, String>  upazillaOrThanaMap = new HashMap<Integer, String>();
		HashMap<Integer, String>  unionOrWardMap = new HashMap<Integer, String>();
		HashMap<Integer, String>  mauzaOrMohollaMap = new HashMap<Integer, String>();
		HashMap<Integer, String>  villageMap = new HashMap<Integer, String>();
		
		for(int i=0;i<divisionList.size();i++){
			divisionMap.put(Integer.valueOf(divisionList.get(i).getDivisionId()), divisionList.get(i).getDivisionName());			
		}		
		
		for(int i=0;i<districtList.size();i++){
			districtMap.put(Integer.valueOf(districtList.get(i).getDistrictId()), districtList.get(i).getDistrictName());			
		}
		
		for(int i=0;i<upazillaOrThanaList.size();i++){
			upazillaOrThanaMap.put(Integer.valueOf(upazillaOrThanaList.get(i).getUpazillaOrThanaId()), upazillaOrThanaList.get(i).getUpazillaOrThanaName());			
		}
		
		for(int i=0;i<unionOrWardList.size();i++){
			unionOrWardMap.put(Integer.valueOf(unionOrWardList.get(i).getUnionOrWardId()), unionOrWardList.get(i).getUnionOrWardName());			
		}
		
		for(int i=0;i<mauzaOrMohollaList.size();i++){		
			mauzaOrMohollaMap.put(Integer.valueOf(mauzaOrMohollaList.get(i).getMauzaOrMohollaId()), mauzaOrMohollaList.get(i).getMauzaOrMohollaName());			
		}
		
		for(int i=0;i<villageList.size();i++){
			villageMap.put(Integer.valueOf(villageList.get(i).getVillageId()), villageList.get(i).getVillageName());			
		}
		
		RegistrationDAO regDAO=new RegistrationDAO();
		HashMap<String, SettingDTO> settingHash=regDAO.getParamSettings();
		
		

		
		config.getServletContext().setAttribute("ALL_DIVISTION_MAP", divisionMap);
		config.getServletContext().setAttribute("ALL_DISTRICT_MAP", districtMap);
		config.getServletContext().setAttribute("AL_UPAZILLA_THANA_MAP", upazillaOrThanaMap);
		config.getServletContext().setAttribute("ALL_UNION_WARD_MAP", unionOrWardMap);
		config.getServletContext().setAttribute("ALL_MAUZA_MOHOLLA_MAP", mauzaOrMohollaMap);
		config.getServletContext().setAttribute("ALL_VILLAGE_MAP", villageMap);
		config.getServletContext().setAttribute("ALL_SETTING_PARAM", settingHash);
		
		AgeLimitDTO ageLimit=MasterDataManagement.getAgeLimitMstData();
		config.getServletContext().setAttribute("MIN_AGE", ageLimit.getMinAge());
		config.getServletContext().setAttribute("MAX_AGE", ageLimit.getMaxAge());
		config.getServletContext().setAttribute("ALLOWED_GENDER", ParameterDAO.getGenderList("active"));
		
		RegTypeDTO regType=MasterDataManagement.getActiveRegType();			
		config.getServletContext().setAttribute("ACTIVE_REGTYPE", regType==null?null:regType.getTypeId());
		config.getServletContext().setAttribute("REGID_SUFFIX", regType==null?null:regType.getRegIdSuffix());
		
		config.getServletContext().setAttribute("ACTIVE_PAYMENT_METHODS", MasterDataManagement.getActivePaymentMethods());
		
		
		userDao.updateLoginStatus("allUser", 0);
	
	}

}

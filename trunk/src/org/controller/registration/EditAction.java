package org.controller.registration;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;
import org.model.AddressDAO;
import org.model.RegistrationDAO;
import org.table.AddressDTO;
import org.table.NomineeDTO;
import org.table.PersonalInfoDTO;
import org.table.UserDTO;

import com.opensymphony.xwork2.ActionSupport;

public class EditAction extends ActionSupport implements ServletContextAware{
	
	private static final long serialVersionUID = 596377582962381206L;
	private String jobSeekerNumber;
	
	PersonalInfoDTO personalDTO;
	AddressDTO      addressDTO;
	NomineeDTO      nomineeDTO;

	/*
	
	public String fetchJobseekerDetail()
	{
		RegistrationDAO regDao=new RegistrationDAO();
		AddressDAO addressDao=new AddressDAO();
		String localIp=ServletActionContext.getRequest().getHeader("X-Forwarded-For")==null?"":ServletActionContext.getRequest().getHeader("X-Forwarded-For");
		String via=ServletActionContext.getRequest().getHeader("Via")==null?"":ServletActionContext.getRequest().getHeader("Via");
		String realIp=ServletActionContext.getRequest().getRemoteAddr()==null?"":ServletActionContext.getRequest().getRemoteAddr();
		String submittedAuthKey=localIp+via+realIp;
		
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("loggedInUser");
		
		if(!loggedInUser.getAuthenticationKey().equalsIgnoreCase(submittedAuthKey))
		{
			return "logout";
		}
		else if(!loggedInUser.getUserType().equalsIgnoreCase("EDIT_OPERATOR"))
		{
			return "logout";
		}
		else if(loggedInUser.getAccessRight()==0)
		{
			return "timeOver";	
		}
		
		this.personalDTO=regDao.getAllPersonalInformation(jobSeekerNumber.toUpperCase());
		this.addressDTO=addressDao.getAllAddressInformation(jobSeekerNumber.toUpperCase());
        
		if(this.personalDTO==null)
		{
			return INPUT;
		}
		else
			return SUCCESS;
		

	}
	
	public String updateJobseekerDetail()
	{
		String localIp=ServletActionContext.getRequest().getHeader("X-Forwarded-For")==null?"":ServletActionContext.getRequest().getHeader("X-Forwarded-For");
		String via=ServletActionContext.getRequest().getHeader("Via")==null?"":ServletActionContext.getRequest().getHeader("Via");
		String realIp=ServletActionContext.getRequest().getRemoteAddr()==null?"":ServletActionContext.getRequest().getRemoteAddr();
		String submittedAuthKey=localIp+via+realIp;
		
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("loggedInUser");
		String messages="";
		boolean error=false;
		
		RegistrationDAO regDAO=new RegistrationDAO();
		
		if(!loggedInUser.getAuthenticationKey().equalsIgnoreCase(submittedAuthKey))
		{
			return "logout";
		}
		else if(!loggedInUser.getUserType().equalsIgnoreCase("EDIT_OPERATOR"))
		{
			return "logout";
		}
		else if(loggedInUser.getAccessRight()==0)
		{
			return "timeOver";	
		}
		
		if(personalDTO.getEmpFname()==null || personalDTO.getEmpFname().equalsIgnoreCase("") )
		{
			messages+="* Jobseeker's First Name cannot be blank.";			
		}
		if(personalDTO.getFatherName()==null||personalDTO.getFatherName().equalsIgnoreCase(""))
		{
			messages+="* Father Name cannot be blank.";			
		}
		if(personalDTO.getMotherName()==null || personalDTO.getMotherName().equalsIgnoreCase("") )
		{
			messages+="* Mother Name cannot be blank.";			
		}
		if(personalDTO.getBirthDate()==null || personalDTO.getBirthDate().equalsIgnoreCase(""))
		{
			messages+="* Birth Date cannot be blank.";			
		}
		if(personalDTO.getWeight()==null || personalDTO.getWeight().equalsIgnoreCase(""))
		{
			messages+="* Weight cannot be blank.";			
		}
		if(personalDTO.getHeightFeet()==null || personalDTO.getHeightFeet().equalsIgnoreCase(""))
		{
			messages+="* Height Feet cannot be blank.";			
		}
		if(personalDTO.getReligion()==null || personalDTO.getReligion().equalsIgnoreCase("select"))
		{
			messages+="* Please select your religion.";			
		}
		if(addressDTO.getpThana()==null || addressDTO.getpThana().equalsIgnoreCase("select"))
		{
			messages+="* Please select Permanent Address Upazilla Correctly.";			
		}
		if(addressDTO.getmThana()==null || addressDTO.getmThana().equalsIgnoreCase("select"))
		{
			messages+="* Please select Mailing Address Upazilla Correctly.";			
		}
		if(addressDTO.getPUnion()==null || addressDTO.getPUnion().equalsIgnoreCase("select"))
		{
			messages+="* Please select Permanent Address Union Correctly.";			
		}
		if(addressDTO.getMUnion()==null || addressDTO.getMUnion().equalsIgnoreCase("select"))
		{
			messages+="* Please select Mailing Address Union Correctly.";			
		}
		
		if( personalDTO.getNomineeName()==null || personalDTO.getNomineeName().equalsIgnoreCase(""))
		{
			messages+="* Nominee Name cannot be blank.";			
		}
		if(personalDTO.getNomineeRelation()==null || personalDTO.getNomineeRelation().equalsIgnoreCase("none"))
		{
			messages+="* Please select Nominee Religion.";			
		}
		if(personalDTO.getNomineeAddress()==null || personalDTO.getNomineeAddress().equalsIgnoreCase(""))
		{
			messages+="* Please provide nominee's address.";			
		}
		if(personalDTO.getNomineePhone()==null || personalDTO.getNomineePhone().equalsIgnoreCase(""))
		{
			messages+="* Please provide nominee's phone number.";			
		}
		
		
		if(addressDTO.getmDivision()==null || addressDTO.getmDivision().equalsIgnoreCase("selected"))
		{
			messages+="* Please select mailing Address Division.";			
		}
		if(addressDTO.getmDistrict()==null || addressDTO.getmDistrict().equalsIgnoreCase("selected"))
		{
			messages+="* Please select mailing Address District.";			
		}
		if(addressDTO.getmThana()==null || addressDTO.getmThana().equalsIgnoreCase("selected"))
		{
			messages+="* Please select mailing Address Upazilla.";			
		}
		if(addressDTO.getMUnion()==null || addressDTO.getMUnion().equalsIgnoreCase("selected"))
		{
			messages+="* Please select mailing Address Union.";			
		}
		
		if(!messages.equalsIgnoreCase(""))
		{addFieldError( "Common_Messages", " Please Correct the following errors. \n"+messages );error=true;}
		
		
		if(error)
	    {		
	    	return INPUT;
	    }
		

		String status=regDAO.updateEmpRegistrationInfo(personalDTO, addressDTO,loggedInUser.getUserId());
		if(status.equalsIgnoreCase("success"))
		{
			addActionMessage("Information Successfully Updated for Job-Seeker Number :"+personalDTO.getJobseekerNumber());
			return SUCCESS;
		}
		else if(status.equalsIgnoreCase("noupdate"))
		{
			addActionMessage("Problem in Update Operation for Job-Seeker Number :"+personalDTO.getJobseekerNumber()+" , Please contact with System Admin.");
			return INPUT;
		}
		else			
			return INPUT;
		
	}
*/
	
	public String getJobSeekerNumber() {
		return jobSeekerNumber;
	}


	public void setJobSeekerNumber(String jobSeekerNumber) {
		this.jobSeekerNumber = jobSeekerNumber;
	}

	
	public PersonalInfoDTO getPersonalDTO() {
		return personalDTO;
	}


	public void setPersonalDTO(PersonalInfoDTO personalDTO) {
		this.personalDTO = personalDTO;
	}


	public AddressDTO getAddressDTO() {
		return addressDTO;
	}


	public void setAddressDTO(AddressDTO addressDTO) {
		this.addressDTO = addressDTO;
	}


	public NomineeDTO getNomineeDTO() {
		return nomineeDTO;
	}


	public void setNomineeDTO(NomineeDTO nomineeDTO) {
		this.nomineeDTO = nomineeDTO;
	}


	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}
	

}

package org.controller.medical;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;
import org.model.InterviewDAO;
import org.table.GeneralInfoDTO;
import org.table.UserDTO;

import com.opensymphony.xwork2.ActionSupport;

public class MedicalAction extends ActionSupport implements ServletContextAware{
	
	private String jobSeekerNumber;
	private String medicalResult;
	private String comments;
	
	private static final long serialVersionUID = 8698353911295976964L;
	InterviewDAO interviewDAO=new InterviewDAO();

	public String execute()
	{
		String res="";
		String footerTable="";
		String headerMessage="";
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		String currentDate=dateFormat.format(date);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		
		String localIp=ServletActionContext.getRequest().getHeader("X-Forwarded-For")==null?"":ServletActionContext.getRequest().getHeader("X-Forwarded-For");
		String via=ServletActionContext.getRequest().getHeader("Via")==null?"":ServletActionContext.getRequest().getHeader("Via");
		String realIp=ServletActionContext.getRequest().getRemoteAddr()==null?"":ServletActionContext.getRequest().getRemoteAddr();
		String submittedAuthKey=localIp+via+realIp;
		
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("loggedInUser");
		
		if(!loggedInUser.getAuthenticationKey().equalsIgnoreCase(submittedAuthKey))
		{
			return "logout";
		}
		else if(!loggedInUser.getUserType().equalsIgnoreCase("TTC_MEDICAL"))
		{
			return "logout";
		}
		else if(loggedInUser.getAccessRight()==0)
		{
			return "timeOver";	
		}
		
		GeneralInfoDTO genearaInfo=interviewDAO.getMedicalInfo(jobSeekerNumber);

		if(genearaInfo==null)
		{
			res="<table width='100%' border='0'> " +
			"  <tr> " +
			"    <td align='center' style='text-align:center;'> " +
			"      <img src='/BMREG_WEB/resources/images/sorryIcon.png' border='0' /> " +
			"    </td> " +
			"  </tr> " +
			"  <tr> " +
			"    <td align='center' style='color:red; font-weight:bold; font-size:18px;padding-top:30px;text-align:center;'> " +
			"        Sorry, the given Jobseeker Number has not been selected for the Medical Process. " +
			"    </td> " +
			"  </tr> " +
			"  <tr> " +
			"    <td>&nbsp;</td> " +
			"  </tr> " +
			"</table> ";
		}
		else
		{
			String imageStr="<img src='/BMREG_WEB/resources/images/smile.jpg' border='0' width='150' height='150' />";
			String messageStr="Congratulation";
			if(!genearaInfo.getTtcId().equalsIgnoreCase(loggedInUser.getDivisionId()))
			{
				headerMessage="<img src='/BMREG_WEB/resources/images/redAlert.png' border='0' width='150' height='150' /><br/>"+
				"Sorry, your Medical center is not under this TTC Center.";
			}

			else if(genearaInfo.getMedicalResult().equalsIgnoreCase("Y"))
			{
				headerMessage="<font style='color:green;font-size:24px;font-weight:bold;'>Medically Fit</font>";
					
				footerTable="<tr>"+
	            "<td colspan='4' style='text-align:center;'><font style='font-size:20px;'><b>Comments</b></font><br/><textarea rows=5 cols='80' style='border: 1px solid grey;font-size:16px;'>"+genearaInfo.getMedicalComments()+"</textarea></td>"+
	            "</tr>";

			}
			else if(genearaInfo.getMedicalResult().equalsIgnoreCase("N"))
			{
				headerMessage="<font style='color:red;font-size:24px;font-weight:bold;'>Medically Unfit</font>";
				footerTable="<tr>"+
	            "<td colspan='4' style='text-align:center;'><font style='font-size:20px;'><b>Comments</b></font><br/><textarea rows=5 cols='80' style='border: 1px solid grey;font-size:16px;'>"+genearaInfo.getMedicalComments()+"</textarea></td>"+
	            "</tr>";
			}
			else if(genearaInfo.getMedicalResult().equalsIgnoreCase(""))
			{
				headerMessage="";
				footerTable="<tr>"+
				            "<td colspan='4' style='text-align:center;'><font style='font-size:20px;'><b>Comments</b></font><br/><textarea rows=5 cols='80' style='border: 1px solid grey;font-size:16px;' id='comments'></textarea></td>"+
				            "</tr>"+
				            "<tr>"+
				            "  <td colspan='2' style='text-align:center'><input type='button' name='medicalfit' id='medicalfit' value='Medically Fit'  style='width:200px; height:50px;font-weight:bold; color:green;font-size:16px;' onclick=\"submitMedicalResult('Y')\"/></td> "+
				            "  <td colspan='2' style='text-align:center'><input type='button' name='medicalUnfit' id='medicalUnfit' value='Medically UnFit'  style='width:200px; height:50px;font-weight:bold; color:red;font-size:16px;'  onclick=\"submitMedicalResult('N')\"/></td>"+
				            "</tr>";

			}
			else
			{
				
			}
			res="<table width='100%' border='0'> " +
			"  <tr> " +
			"    <td align='center' style='text-align:center;'> " +
			" 	 "+headerMessage+" " +
			"	</td> " +
			"  </tr> " +
			"  <tr> " +
			"    <td style='text-align:center; padding-top:20px;'> " +
			"	<table width='98%' border='0' align='center' style='border:1px solid #D1D6FA'> " +
			"  <tr bgcolor='#D1D6FA'> " +
			"    <td width='20%' height='30px'>Job-Seeker Number</td> " +
			"    <td width='30%'>"+genearaInfo.getJobseekerNumber()+"</td> " +
			"    <td width='20%'>Full Name</td> " +
			"    <td width='30%'>"+genearaInfo.getJobseekerName()+"</td> " +
			"  </tr> " +
			"  <tr> " +
			"    <td height='30px'>Father Name</td> " +
			"    <td>"+genearaInfo.getFatherName()+"</td> " +
			"    <td>Mother Name</td> " +
			"    <td>"+genearaInfo.getMotherName()+"</td> " +
			"  </tr> " +
			"  <tr bgcolor='#D1D6FA'> " +
			"    <td height='30px'>Division</td> " +
			"    <td>"+genearaInfo.getPerDivision()+"</td> " +
			"    <td>District</td> " +
			"    <td>"+genearaInfo.getPerDistrict()+"</td> " +
			"  </tr> " +
			"  <tr> " +
			"    <td height='30px'>Upazilla</td> " +
			"    <td>"+genearaInfo.getPerUpazilla()+"</td> " +
			"    <td>Union</td> " +
			"    <td>"+genearaInfo.getPerUnion()+"</td> " +
			"  </tr> " +
			"  <tr bgcolor='#D1D6FA'> " +
			"    <td height='30px'>TTC Name</td> " +
			"    <td>"+genearaInfo.getTtcName()+"</td> " +
			"    <td>Interview Date</td> " +
			"    <td>"+genearaInfo.getInterviewDate()+"</td> " +
			"  </tr> " +
			footerTable+
			"</table> " +
			"    </td> " +
			"  </tr> " +
			"</table> ";
		}
		
		try{
        	response.setContentType("text/xml");
        	response.setHeader("Cache-Control", "no-cache");
        	response.getWriter().write(res);
        	response.flushBuffer();
          }
        catch(Exception e) {e.printStackTrace();}
        
		return null;
		

	}
	
	public String submitMedicalData()
	{
		String res="";
		HttpServletResponse response = ServletActionContext.getResponse();
		if(interviewDAO.updateMedicalInfo(jobSeekerNumber, medicalResult, comments)==true)
		{
			res="success";
		}
		else
		{
			res="error";
		}
		
		try{
        	response.setContentType("text/xml");
        	response.setHeader("Cache-Control", "no-cache");
        	response.getWriter().write(res);
        	response.flushBuffer();
          }
        catch(Exception e) {e.printStackTrace();}
        
		return null;
	}

	
	public String getJobSeekerNumber() {
		return jobSeekerNumber;
	}


	public void setJobSeekerNumber(String jobSeekerNumber) {
		this.jobSeekerNumber = jobSeekerNumber;
	}


	public String getMedicalResult() {
		return medicalResult;
	}


	public void setMedicalResult(String medicalResult) {
		this.medicalResult = medicalResult;
	}


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
package org.controller.admin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import org.apache.struts2.ServletActionContext;
import org.model.NewPaawordDAO;
import org.model.UserDAO;
import org.table.UserDTO;
import org.table.UserTmpDTO;

import com.opensymphony.xwork2.ActionSupport;

public class UserApproveAdministration extends ActionSupport{

	private static final long serialVersionUID = 1L;
	ArrayList<UserTmpDTO> userList=new ArrayList<UserTmpDTO>();
	String[] approveUserList;
	private String startDate;
	private String endDate;
	private String message;
	
	
	public String approveNewUserAndSendSms()
	{
		
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("loggedInUser");
		UserDAO userDao=new UserDAO();
		this.message="";
		String res="";
		if(loggedInUser.getAccessRight()==0)
		{
			return "timeOver";	
		}
		else if(!loggedInUser.getUserType().equalsIgnoreCase("SYSTEM_ADMIN"))
		{
			return "logout";
		}
		else if(startDate==null || endDate==null || startDate.equalsIgnoreCase("") || endDate.equalsIgnoreCase(""))
		{
			this.message="Please provide start and end date.";
			userList=userDao.getNewUserList();
			return "input";
		}
		if(approveUserList!=null)
		{
			res=userDao.approveNewUserRequest(approveUserList,startDate,endDate);
			if(res.equalsIgnoreCase("SUCCESS")){
				sendFirstPassword(approveUserList);
				this.message="Successfully Approved and SMS send to the approved users";
			}
			else
				this.message=res;
			
			approveUserList=null;
		}
		else
			this.message="Please select at least one user from the list.";
		
		userList=userDao.getNewUserList();
		if(res.equalsIgnoreCase("SUCCESS"))
			return "success";
		else
			return "input";
	}
	  public String sendFirstPassword(String[] userList)
	  {
		  UserDAO udao=new UserDAO();
		  String pass = "";
		  int counter=0;
		  for(int i=0;i<userList.length;i++)
		  {
			  UserDTO udto=udao.getUserFromUserId(userList[i]);
			  if(udto!=null && (udto.getPassword()==null || udto.getPassword().equalsIgnoreCase("")) )
			  {
			  try
			  {
				  pass = getPasswordCode().substring(0, 5);
				  NewPaawordDAO.setNewPassword(userList[i], pass);
				  String pass1="pls login: g2g.bmet.gov.bd ID:type your mobile no abong Password:"+pass+" .login korte na parle call korun 09613016364.";

				  URL yahoo;
					if(userList[i].substring(0,3).equalsIgnoreCase("011"))
						yahoo = new URL("http://123.49.3.58:8081/web_send_sms.php?ms="+URLEncoder.encode("88"+userList[i])+
								"&txt="+URLEncoder.encode(pass1)+
								"&username="+URLEncoder.encode("bmet2")+"&password="+URLEncoder.encode("BI909")); 		  
					else
						yahoo = new URL("http://123.49.3.58:8081/web_send_sms.php?ms="+URLEncoder.encode("88"+userList[i])+
								"&txt="+URLEncoder.encode(pass1)+
								"&username="+URLEncoder.encode("bmet")+"&password="+URLEncoder.encode("BI909")); 		  
						
					URLConnection yc = yahoo.openConnection();
					BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
					String inputLine;
					String inputLine1="";
					while ((inputLine = in.readLine()) != null)
					{
						System.out.println(inputLine);
						if(inputLine!=null)
							inputLine1+=inputLine;
					}
					in.close();
					counter++;
					if(counter%500==0)
						System.out.println("yes");
//					Thread.sleep(100);
			  }
				  catch(Exception e)
				  {
					  e.printStackTrace();
				  }
			  }
		  }
		  System.out.println("END");
		  return null;
	  }
	  public String getPasswordCode() {
	      Random rand = new Random();
	      int length = rand.nextInt(6) + 8;
	      char[] password = new char[length];
	      for (int x = 0; x < length; x++) {
	        int randDecimalAsciiVal = 0;
	        int cas = rand.nextInt(3);
	        if (cas == 0)
	          randDecimalAsciiVal = rand.nextInt(9) + 48;
	        else if (cas == 1)
	          randDecimalAsciiVal = rand.nextInt(26) + 65;
	        else
	          randDecimalAsciiVal = rand.nextInt(26) + 97;
	        password[x] = (char) randDecimalAsciiVal;
	      }
	      String result = String.valueOf(password);
	      

	      while(result.contains("l") || result.contains("1") || result.contains("I") || result.contains("o") || result.contains("O") || result.contains("0"))
	      {
	              result=result.replaceAll("l", "");
	              result=result.replaceAll("1", "");
	              result=result.replaceAll("I", "");
	              result=result.replaceAll("o", "");
	              result=result.replaceAll("O", "");
	              result=result.replaceAll("0", "");
	              
	              if(result.length()<6)
	                      result=getSecurityCode();
	              
	      }
	      
	      return result.toUpperCase();
	    }
	
	  public String getSecurityCode() {
		    Random rand = new Random();
		    int length = rand.nextInt(6) + 8;
		    char[] password = new char[length];
		    for (int x = 0; x < length; x++) {
		      int randDecimalAsciiVal = 0;
		      int cas = rand.nextInt(3);
		      if (cas == 0)
		        randDecimalAsciiVal = rand.nextInt(9) + 48;
		      else if (cas == 1)
		        randDecimalAsciiVal = rand.nextInt(26) + 65;
		      else
		        randDecimalAsciiVal = rand.nextInt(26) + 97;
		      password[x] = String.valueOf(randDecimalAsciiVal/10).charAt(0);
		    }
		    String result = String.valueOf(password);
		    return result;
		  }	 

	public ArrayList<UserTmpDTO> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<UserTmpDTO> userList) {
		this.userList = userList;
	}
	public String[] getApproveUserList() {
		return approveUserList;
	}
	public void setApproveUserList(String[] approveUserList) {
		this.approveUserList = approveUserList;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

}

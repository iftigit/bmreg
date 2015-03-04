package org.controller.authentication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import org.model.*;
import org.table.UserDTO;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletResponse;

public class NewPassword extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 633695203157893843L;
	HttpServletResponse response = ServletActionContext.getResponse();
	public String securityCode()
	{
System.out.println((String) ServletActionContext.getRequest().getSession().getAttribute("captchaText"));
System.out.println((String) ServletActionContext.getRequest().getParameter("mobile"));
System.out.println((String) ServletActionContext.getRequest().getParameter("ctext"));

		String submittedCode = (String) ServletActionContext.getRequest().getParameter("ctext");
		String generatedCode = (String) ServletActionContext.getRequest().getSession().getAttribute("captchaText");
		try
		{
			if(!submittedCode.equalsIgnoreCase(generatedCode))
			{
				response.getOutputStream().write("Please type captcha text correctly".getBytes());
				return null;
			}
//			response.getOutputStream().write(getSecurityCode().getBytes());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			String scode = getSecurityCode();
			if(scode.length()>5)
				scode=scode.substring(0,5);
			String mobile=(String) ServletActionContext.getRequest().getParameter("mobile");
			String response1 = new NewPaawordDAO().setSecurityCode(mobile, scode);
			if(response1.equalsIgnoreCase("no"))
			{
				response.getOutputStream().write("Please type correct mobile no".getBytes());
				return null;
			}
			if(response1.equalsIgnoreCase("yes"))
			{
				URL yahoo;
				if(mobile.substring(0,3).equalsIgnoreCase("011"))
					yahoo = new URL("http://123.49.3.58:8081/web_send_sms.php?ms="+URLEncoder.encode("88"+mobile)+
						"&txt="+URLEncoder.encode("Your Conformation Code="+scode)+"&username="+URLEncoder.encode("bmet")+
						"&password="+URLEncoder.encode("bmet9090")); 
				else
					yahoo = new URL("http://123.49.3.58:8081/web_send_sms.php?ms="+URLEncoder.encode("88"+mobile)+
							"&txt="+URLEncoder.encode("Your Conformation Code="+scode)+"&username="+URLEncoder.encode("bmet")+
							"&password="+URLEncoder.encode("bmet9090"));
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
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public String getNewPassword()
	{
		System.out.println((String) ServletActionContext.getRequest().getSession().getAttribute("captchaText"));
		System.out.println((String) ServletActionContext.getRequest().getParameter("mobile"));
		System.out.println((String) ServletActionContext.getRequest().getParameter("ctext"));

		String submittedCode = (String) ServletActionContext.getRequest().getParameter("ctext");
		String generatedCode = (String) ServletActionContext.getRequest().getSession().getAttribute("captchaText");
		try
		{
			if(!submittedCode.equalsIgnoreCase(generatedCode))
			{
				response.getOutputStream().write("Please type captcha text correctly".getBytes());
				return null;
			}
//			response.getOutputStream().write(getSecurityCode().getBytes());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			String scode = (String) ServletActionContext.getRequest().getParameter("scode").trim();
			String mobile=(String) ServletActionContext.getRequest().getParameter("mobile");
			String pass = getPasswordCode().substring(0, 5);
			String response1 = new NewPaawordDAO().setPasswordCode(mobile, scode, pass);
			if(response1.equalsIgnoreCase("no"))
			{
				response.getOutputStream().write("Please type correct mobile no or code".getBytes());
				return null;
			}
			if(response1.equalsIgnoreCase("yes"))
			{
				URL yahoo;
				if(mobile.substring(0,3).equalsIgnoreCase("011"))
					yahoo = new URL("http://123.49.3.58:8081/web_send_sms.php?ms="+URLEncoder.encode("88"+mobile)+
						"&txt="+URLEncoder.encode("Your password is="+pass)+"&username="+URLEncoder.encode("bmet")+
						"&password="+URLEncoder.encode("bmet9090")); 
				else
					yahoo = new URL("http://123.49.3.58:8081/web_send_sms.php?ms="+URLEncoder.encode("88"+mobile)+
							"&txt="+URLEncoder.encode("Your password is="+pass)+"&username="+URLEncoder.encode("bmet")+
							"&password="+URLEncoder.encode("bmet9090")); 
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
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}	
	public String np_pp()
	{
		try
		{
			String mobile = (String) ServletActionContext.getRequest().getParameter("mobile");
			if(mobile==null || mobile.length()<11)
			{
				response.getOutputStream().write("You are not authorized user.".getBytes());
				return null;
			}
			
			mobile = mobile.trim().substring(mobile.length()-11, mobile.length());
			
			
System.out.println("SMS mobile :"+(String) ServletActionContext.getRequest().getParameter("mobile"));			
System.out.println("SMS code :"+(String) ServletActionContext.getRequest().getParameter("code"));

			String pass = getPasswordCode().substring(0, 5);
			String response1 = new NewPaawordDAO().setPasswordCode(mobile, "no1234", pass);
			
			if(response1.equalsIgnoreCase("no"))
			{
				response.getOutputStream().write("You are not authorized user.".getBytes());
				return null;
			}
			if(response1.equalsIgnoreCase("yes"))
			{
				response.getOutputStream().write(("Your Password is :"+pass+"").getBytes());
				
//				URL yahoo;
//				if(mobile.substring(0,3).equalsIgnoreCase("011"))
//					yahoo = new URL("http://123.49.3.58:8081/web_send_sms.php?ms="+URLEncoder.encode("88"+mobile)+
//						"&txt="+URLEncoder.encode("Your password is="+pass)+"&username="+URLEncoder.encode("bmet2")+
//						"&password="+URLEncoder.encode("bmet231"));
//				else
//					yahoo = new URL("http://123.49.3.58:8081/web_send_sms.php?ms="+URLEncoder.encode("88"+mobile)+
//							"&txt="+URLEncoder.encode("Your password is="+pass)+"&username="+URLEncoder.encode("bmet")+
//							"&password="+URLEncoder.encode("bmet231")); 		  
//					
//				URLConnection yc = yahoo.openConnection();
//				BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
//				String inputLine;
//				String inputLine1="";
//				while ((inputLine = in.readLine()) != null)
//				{
//					System.out.println(inputLine);
//					if(inputLine!=null)
//						inputLine1+=inputLine;
//				}
//				in.close();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public String execute()
	{
//		try
//		{
//			response.getOutputStream().write(getSecurityCode().getBytes());
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
		return null;
	}
  public String sendPasswordBySms()
  {
	  ArrayList<UserDTO> tmp = null;
	  tmp = new NewPaawordDAO().getAllUser();
//	  tmp = new NewPaawordDAO().getFirstLottery();
	  Iterator<UserDTO> it = tmp.iterator();
	  String pass = "";
	  int counter=0;
	  while(it.hasNext())
	  {
		  try
		  {
			  UserDTO ut = it.next();

			  pass = getPasswordCode().substring(0, 5);
			  NewPaawordDAO.setNewPassword(ut.getUserId(), pass);
			  String pass1="pls login: registration.bmet.gov.bd ID:type your mobile no abong Password:"+pass+" .login korte na parle call korun 09613016364.";


			  URL yahoo;
				if(ut.getUserId().substring(0,3).equalsIgnoreCase("011"))
					yahoo = new URL("http://123.49.3.58:8081/web_send_sms.php?ms="+URLEncoder.encode("88"+ut.getUserId())+
							"&txt="+URLEncoder.encode(pass1)+
							"&username="+URLEncoder.encode("bmet")+"&password="+URLEncoder.encode("bmet9090")); 		  
				else
					yahoo = new URL("http://123.49.3.58:8081/web_send_sms.php?ms="+URLEncoder.encode("88"+ut.getUserId())+
							"&txt="+URLEncoder.encode(pass1)+
							"&username="+URLEncoder.encode("bmet")+"&password="+URLEncoder.encode("bmet9090")); 		  
					
				URLConnection yc = yahoo.openConnection();
				BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
				String inputLine;
				String inputLine1="";
				while ((inputLine = in.readLine()) != null)
				{					
					if(inputLine!=null)
						inputLine1+=inputLine;
					
				}
				System.out.println(inputLine1);
				in.close();
				counter++;
				if(counter%100==0){
					Thread.sleep(2000);
					System.out.println("----------------------------");
					System.out.println("Total Sms Send : "+counter);
					System.out.println("----------------------------");
				}
				
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }
	  }
	  System.out.println("END");
	  return null;
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
  public String getPasswordCode1() {
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
	    return result;
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

 
  
  public String getLottery1()
  {
	  
		String submittedCode = (String) ServletActionContext.getRequest().getParameter("ctext");
		String generatedCode = (String) ServletActionContext.getRequest().getSession().getAttribute("captchaText");
		try
		{
			if(!submittedCode.equalsIgnoreCase(generatedCode))
			{
				response.getOutputStream().write("Please type captcha text correctly".getBytes());
				return null;
			}
//			response.getOutputStream().write(getSecurityCode().getBytes());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			String regid = (String) ServletActionContext.getRequest().getParameter("regid");
			response.getOutputStream().write(new NewPaawordDAO().getLottery1(regid).getBytes());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;	  
  }
  
}

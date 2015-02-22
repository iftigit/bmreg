package org.controller.payment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.model.PaymentDAO;
import org.table.UserDTO;

import com.opensymphony.xwork2.ActionSupport;

public class TeletalkAPI extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String userId;
	private String password;
	private String tmpRegId;
	private String amount;
	
	public String paymentInfo()
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		String xForward=ServletActionContext.getRequest().getHeader("X-Forwarded-For")==null?"":ServletActionContext.getRequest().getHeader("X-Forwarded-For");
		String via=ServletActionContext.getRequest().getHeader("Via")==null?"":ServletActionContext.getRequest().getHeader("Via");
		String remoteAddress=ServletActionContext.getRequest().getRemoteAddr()==null?"":ServletActionContext.getRequest().getRemoteAddr();
		
		System.out.println("##############     ttreceive        ###############  " + xForward);
		System.out.println("##############     ttreceive        ###############  " + via);
		System.out.println("##############     ttreceive        ###############  " + remoteAddress);
		
		
		if(!remoteAddress.equalsIgnoreCase("123.49.43.158") ||  !userId.equalsIgnoreCase("ttBmetRegUser") || password.equalsIgnoreCase("BmetQWSA!@"))
		{
			System.out.println("############## ttreceive from wrong ip###############  " + xForward);
			System.out.println("############## ttreceive from wrong ip###############  " + via);
			System.out.println("############## ttreceive from wrong ip###############  " + remoteAddress);

			try{
	        	response.setContentType("text/xml");
	        	response.setHeader("Cache-Control", "no-cache");
	        	response.getWriter().write("<reply>0</reply>");
	        	response.flushBuffer();
	          }
	        catch(Exception e) {e.printStackTrace();}	        
			return null;
		}
		
		String user=userId==null?"Not Given":userId;
		String pass=password==null?"Not Given":password;
		String tmpRegId=this.tmpRegId==null?"Not Given":this.tmpRegId;
		
		if(user!=null)
			user=user.trim().equalsIgnoreCase("")?"Blank Given":user;
		if(pass!=null)
			pass=pass.trim().equalsIgnoreCase("")?"Blank Given":pass;
		if(tmpRegId!=null)
			tmpRegId=tmpRegId.trim().equalsIgnoreCase("")?"Blank Given":tmpRegId;

		try
		{
			if(user.equalsIgnoreCase("Not Given") || user.equalsIgnoreCase("Blank Given") || pass.equalsIgnoreCase("Not Given") || pass.equalsIgnoreCase("Blank Given") || tmpRegId.equalsIgnoreCase("Not Given") || tmpRegId.equalsIgnoreCase("Blank Given"))
			{
				response.getOutputStream().write("<reply>0</reply>".getBytes());
				return null;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		try
		{
			

			PaymentDAO paymentDAO=new PaymentDAO();
			if(paymentDAO.insertOperatorPaymentInfo(xForward,via,remoteAddress,1,Float.parseFloat(amount),tmpRegId)==true)
				response.getOutputStream().write("<reply>1</reply>".getBytes());
			else
				response.getOutputStream().write("<reply>0</reply>".getBytes());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public String checkPaymentInfo()
	{
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("loggedInUser");
		if(!loggedInUser.getUserType().equalsIgnoreCase("UISC_REG_OPERATOR") && !loggedInUser.getUserType().equalsIgnoreCase("SYSTEM_ADMIN") && !loggedInUser.getUserType().equalsIgnoreCase("ADHOC_REG_OPERATOR"))
		{
			return "logout";
		}
		String[] infoArr=null;
		String xForward=ServletActionContext.getRequest().getHeader("X-Forwarded-For")==null?"":ServletActionContext.getRequest().getHeader("X-Forwarded-For");
		String via=ServletActionContext.getRequest().getHeader("Via")==null?"":ServletActionContext.getRequest().getHeader("Via");
		String remoteAddress=ServletActionContext.getRequest().getRemoteAddr()==null?"":ServletActionContext.getRequest().getRemoteAddr();
		HttpServletResponse response = ServletActionContext.getResponse();
		String resp="";
		try{	
			String url="http://123.49.43.139:9999/bmet/bmetfromteletalk.php?user=bmet&password=bmet123&tmp_reg_id="+tmpRegId;
			URL ackUrl = new URL(url);
			URLConnection yc = ackUrl.openConnection();
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

			if(inputLine1!=null && inputLine1.endsWith("<reply>0</reply>"))
		    {
				resp="Payment Note Received";
		    }
			else if(inputLine1!=null && !inputLine1.endsWith("<reply>0</reply>"))
			{
				if(inputLine1.startsWith("<reply>")){
					
					inputLine1.replaceAll("<reply>", "");
					inputLine1.replaceAll("</reply>", "");
					infoArr=inputLine1.split(",");
				}
					
				PaymentDAO paymentDAO=new PaymentDAO();
				paymentDAO.insertOperatorPaymentInfo(xForward,via,remoteAddress,1,Float.parseFloat(infoArr[2]),infoArr[0]);
				resp="Payment Successfully received";
			}
		}
			catch(Exception ex)
			{ex.printStackTrace();}

			try{
	        	response.setContentType("text/html");
	        	response.setHeader("Cache-Control", "no-cache");
	        	response.getWriter().write(resp);
	        	response.flushBuffer();
	          }
	        catch(Exception e) {e.printStackTrace();}
	        
	        return null;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getTmpRegId() {
		return tmpRegId;
	}

	public void setTmpRegId(String tmpRegId) {
		this.tmpRegId = tmpRegId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	
	
}
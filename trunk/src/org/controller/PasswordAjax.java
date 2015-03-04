package org.controller;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;
import org.table.UserDTO;

import com.opensymphony.xwork2.ActionSupport;

public class PasswordAjax extends ActionSupport implements ServletContextAware{
	
	private String unionId;
	private String userId;

	private static final long serialVersionUID = 8698353911295976964L;
	DataDAO dataDao=new DataDAO();

	public String execute()
	{
		String res="";
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
		else if(!loggedInUser.getUserType().equalsIgnoreCase("A2I_OPERATOR"))
		{
			return "logout";
		}
//		else if(loggedInUser.getAccessRight()==0)
//		{
//			return "timeOver";	
//		}
		
		ArrayList<UserDTO> userList=dataDao.getUserList(unionId);

		if(userList.size()==0)
		{
			res="<table width='100%' border='0'> " +
			"  <tr> " +
			"    <td align='center' style='color:red; font-weight:bold; font-size:18px;padding-top:30px;text-align:center;'> " +
			"        Sorry, no operator found for the selected union. " +
			"    </td> " +
			"  </tr> " +
			"  <tr> " +
			"    <td>&nbsp;</td> " +
			"  </tr> " +
			"</table> ";
		}
		else
		{
			res="<table width='80%' align='center' border='0'style='border:1px solid grey;' >";
			res+="<tr style='background-color:#DDDDDD'><td align='center' width='50%'><b>UserId</b></td><td align='left'  width='50%' style='padding-left:10px;'><b>Password</b></td></tr>";
			for(int i=0;i<userList.size();i++)
			{
				String bgColor="";
				if(i%2==0) bgColor="style='background-color:#D5D5FF'";
				else bgColor="style='background-color:#FFFFFF'";
					
				res+="<tr "+bgColor+">";
				res+=" <td align='center'>"+userList.get(i).getUserId()+"</td>";
				res+=" <td align='left' style='padding-left:10px;'>"+userList.get(i).getPassword()+"</td>";
				res+="</tr>";
			}
			res+="</table>";
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
	
	public String byUserId()
	{
		String res="";
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
		else if(!loggedInUser.getUserType().equalsIgnoreCase("A2I_OPERATOR"))
		{
			return "logout";
		}
//		else if(loggedInUser.getAccessRight()==0)
//		{
//			return "timeOver";	
//		}
		
		ArrayList<UserDTO> userList=dataDao.passwordByUserId(userId);

		if(userList.size()==0)
		{
			res="<table width='100%' border='0'> " +
			"  <tr> " +
			"    <td align='center' style='color:red; font-weight:bold; font-size:18px;padding-top:30px;text-align:center;'> " +
			"        Sorry, no operator found for the given UserId. " +
			"    </td> " +
			"  </tr> " +
			"  <tr> " +
			"    <td>&nbsp;</td> " +
			"  </tr> " +
			"</table> ";
		}
		else
		{
			res="<table width='80%' align='center' border='0'style='border:1px solid grey;' >";
			res+="<tr style='background-color:#DDDDDD'><td align='center' width='50%'><b>UserId</b></td><td align='left'  width='50%' style='padding-left:10px;'><b>Password</b></td></tr>";
			for(int i=0;i<userList.size();i++)
			{
				String bgColor="";
				if(i%2==0) bgColor="style='background-color:#D5D5FF'";
				else bgColor="style='background-color:#FFFFFF'";
					
				res+="<tr "+bgColor+">";
				res+=" <td align='center'>"+userList.get(i).getUserId()+"</td>";
				res+=" <td align='left' style='padding-left:10px;'>"+userList.get(i).getPassword()+"</td>";
				res+="</tr>";
			}
			res+="</table>";
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

	
	

	public String getUnionId() {
		return unionId;
	}




	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}




	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args)
	{
		System.out.println("--");
	}
	

}

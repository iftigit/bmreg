package org.controller.authentication;

import java.util.ArrayList;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.model.AddressDAO;
import org.model.LotteryDAO;
import org.model.UserDAO;
import org.table.AddressDTO;
import org.table.UserDTO;

import com.opensymphony.xwork2.ActionSupport;

public class CheckValidity extends ActionSupport{

	private static final long serialVersionUID = -3308886200897622656L;
	UserDTO user=new UserDTO();
	UserDAO userDao=new UserDAO();
	private String userId;
	private String password;
	private String lotteryStatus;
	public String execute()
	{

		user = (UserDTO) ServletActionContext.getRequest().getSession().getAttribute("loggedInUser");
		if(user!=null && (user.getUserType().equalsIgnoreCase("UISC_REG_OPERATOR") || user.getUserType().equalsIgnoreCase("UISC_REG_OPERATOR")))	
		{
			return "regOperator";
		}
		else if(user!=null && (user.getUserType().equalsIgnoreCase("DEMO_REG_ADMIN")))	
		{
			return "demoRegAdmin";
		}		
		else if(user!=null && user.getUserType().equalsIgnoreCase("REG_VIEW_ADMIN"))	
		{
			return "regViewAdmin";
		}
		else if(user!=null && user.getUserType().equalsIgnoreCase("REG_LOT_VIEW_ADMIN"))	
		{
			return "regLotViewAdmin";
		}
		else if(user!=null && user.getUserType().equalsIgnoreCase("LOTTERY_DC_ADMIN"))	
		{
			return "dcLotteryAdmin";
		}
		else if(user!=null && user.getUserType().equalsIgnoreCase("LOTTERY_MINISTRY_ADMIN"))
		{
			return "ministryLotteryAdmin";
		}
		else if(user!=null && user.getUserType().equalsIgnoreCase("TTC_CONF_OPERATOR"))
		{
			return "ttcConfirmOperator";
		}
		else if(user!=null && user.getUserType().equalsIgnoreCase("TTC_CHARIMAN"))
		{
			return "ttcCharimanHome";
		}
		else if(user!=null && user.getUserType().equalsIgnoreCase("TTC_MEDICAL"))
		{
			return "ttcMedicalHome";
		}
		else if(user!=null && user.getUserType().equalsIgnoreCase("EDIT_OPERATOR"))
		{
			return "editOperatorHome";
		}
		else if(user!=null && user.getUserType().equalsIgnoreCase("A2I_OPERATOR"))
		{
			return "a2iOperatorHome";
		}
		else if(user!=null && user.getUserType().equalsIgnoreCase("SYSTEM_ADMIN"))
		{
			return "systemAdminHome";
		}
		else if(user!=null && user.getUserType().equalsIgnoreCase("REG_DC_ADMIN"))
		{
			return "regDcAdminHome";
		}
		
		
		
		
		
		String submittedAuthKey="";
		String savedAuthKey="";
		String localIp=ServletActionContext.getRequest().getHeader("X-Forwarded-For")==null?"":ServletActionContext.getRequest().getHeader("X-Forwarded-For");
		String via=ServletActionContext.getRequest().getHeader("Via")==null?"":ServletActionContext.getRequest().getHeader("Via");
		String realIp=ServletActionContext.getRequest().getRemoteAddr()==null?"":ServletActionContext.getRequest().getRemoteAddr();
		submittedAuthKey=localIp+via+realIp;
		AddressDAO addressDao=new AddressDAO();
			
		this.user=userDao.validateLogin(userId, password);
		if(user==null)
		{
			addActionMessage("Invalid Userid or Password.");
			return INPUT;
		}
		else
		{
				
			
			boolean flag=false;
//			savedAuthKey=userDao.getAuthenticationKey(userId, password);
//			if(savedAuthKey.equalsIgnoreCase(""))
//			{
				flag=userDao.updateLoginKey(userId, password, localIp, via, realIp);
				user.setAuthenticationKey(localIp+via+realIp);
//			}
//			else if(submittedAuthKey.equalsIgnoreCase(savedAuthKey))
//			{
//				flag=true;
//				user.setAuthenticationKey(savedAuthKey);
//			}
				flag=true;
				
			if(flag==true)
			{
				ServletActionContext.getRequest().getSession().setAttribute("loggedInUser", user);
				if(user.getUserType().equalsIgnoreCase("UISC_REG_OPERATOR") || user.getUserType().equalsIgnoreCase("DEMO_REG_OPERATOR"))	
				{
//					ServletActionContext.getRequest().getSession().setAttribute("OPERATOR_DIVISION", addressDao.getDivision(Integer.parseInt(user.getDivisionId())));
//					ServletActionContext.getRequest().getSession().setAttribute("OPERATOR_DISTRICT", addressDao.getOperatorDistrict(Integer.parseInt(user.getDistrictId())));
//					ServletActionContext.getRequest().getSession().setAttribute("OPERATOR_UPAZILLA", addressDao.getUpazilla(user.getUpazillaId(),user.getDistrictId()));
					ArrayList<AddressDTO> abc=(ArrayList<AddressDTO>) getServletContext().getAttribute("OPERATOR_DISTRICT");
					ServletActionContext.getRequest().getSession().setAttribute("CONTACT_INFO", userDao.getTechnicalTeam(user.getDistrictId()));
					return "regOperator";
				}
				if(user.getUserType().equalsIgnoreCase("DEMO_REG_ADMIN"))	
				{
					return "demoRegAdmin";
				}
				else if(user.getUserType().equalsIgnoreCase("REG_VIEW_ADMIN"))	
				{
					ServletActionContext.getRequest().getSession().setAttribute("ALL_DIVISION", addressDao.getAllDivision());
					return "regViewAdmin";
				}
				else if(user.getUserType().equalsIgnoreCase("REG_LOT_VIEW_ADMIN"))	
				{
					ServletActionContext.getRequest().getSession().setAttribute("ALL_DIVISION", addressDao.getAllDivision());
					return "regLotViewAdmin";
				}
				else if(user.getUserType().equalsIgnoreCase("LOTTERY_DC_ADMIN"))	
				{
					LotteryDAO lottery=new LotteryDAO();
					ServletActionContext.getRequest().getSession().setAttribute("DC_DISTRICT", user.getDistrictId());
					lotteryStatus=lottery.getLotteryStatus(user.getUserId(), user.getDistrictId());
					
					return "dcLotteryAdmin";
				}
				else if(user.getUserType().equalsIgnoreCase("USER_VIEW_ADMIN"))	
				{
					ServletActionContext.getRequest().getSession().setAttribute("USER_VIEW", userDao.getTechnicalTeam(user.getDistrictId()));
					return "showUser";
				}
				else if( user.getUserType().equalsIgnoreCase("LOTTERY_MINISTRY_ADMIN"))
				{
					return "ministryLotteryAdmin";
				}
				else if(user!=null && user.getUserType().equalsIgnoreCase("TTC_CONF_OPERATOR"))
				{
					return "ttcConfirmOperator";
				}
				else if(user!=null && user.getUserType().equalsIgnoreCase("TTC_CHARIMAN"))
				{
					return "ttcCharimanHome";
				}
				else if(user!=null && user.getUserType().equalsIgnoreCase("TTC_MEDICAL"))
				{
					return "ttcMedicalHome";
				}
				else if(user!=null && user.getUserType().equalsIgnoreCase("EDIT_OPERATOR"))
				{
					return "editOperatorHome";
				}				
				else if(user!=null && user.getUserType().equalsIgnoreCase("SYSTEM_ADMIN"))
				{
					return "systemAdminHome";
				}
				else if(user!=null && user.getUserType().equalsIgnoreCase("REG_DC_ADMIN"))
				{
					return "regDcAdminHome";
				}
				

				else
					return INPUT;
			}
			else
			{
				addActionMessage("Ip address changed....Please regenerate password and then try again.");
				return INPUT;
			}
		}
	}

	public ServletContext getServletContext()
	{
		return ServletActionContext.getServletContext();
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
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

	public String getLotteryStatus() {
		return lotteryStatus;
	}

	public void setLotteryStatus(String lotteryStatus) {
		this.lotteryStatus = lotteryStatus;
	}
	
}

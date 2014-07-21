package util.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.struts2.ServletActionContext;
import org.model.UserDAO;
import org.table.UserDTO;
 
public class SessionListener implements HttpSessionListener {
 
  private static int totalActiveSessions;
 
  public static int getTotalActiveSession(){
	return totalActiveSessions;
  }
 
  public void sessionCreated(HttpSessionEvent arg0) {
	//totalActiveSessions++;
	//System.out.println("sessionCreated - add one session into counter");
  }
 
  public void sessionDestroyed(HttpSessionEvent arg0) {
	  UserDTO user = (UserDTO) ServletActionContext.getRequest().getSession().getAttribute("loggedInUser");
	  UserDAO userDao=new UserDAO();
	  userDao.updateLoginStatus(user.getUserId(), 0);
  }	
}

package util.connection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class ConnectionManager {
    

    private  static DataSource ds = null;
    
     
    //Marking default constructor private
    //to avoid direct instantiation.
    private ConnectionManager() {
    }
 
    //Get instance for class SimpleSingleton
    public static Connection getConnection() {
     
            Connection conn=null;       	
        	try {
	    			if (ds==null) 
	    			{
	    				Context initContext = new InitialContext();
	    				Context envContext = (Context) initContext.lookup("java:/comp/env");
	    				ds = (DataSource) envContext.lookup("jdbc/reg2g");
	    			}
	    			conn = ds.getConnection();
    			} 
        	catch (Exception e) 
        		{
    		      try {
    		          Thread.currentThread();
    		          Thread.sleep(2000);
    		          conn = ds.getConnection();
    		        }
    		        catch (Exception ex) {
    					System.out.println("******##############################################################################");
    					System.out.println(ex.getMessage());
    					System.out.println("******############################ Exception in Get Connection ################################");
    		     }
    			System.out.println("##############################################################################");
    			System.out.println(e.getMessage());
    			System.out.println("##############################################################################");
    		}
    		
        
     
        return conn;
    }
    
    public static void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection = null;
		}
	}
    
}



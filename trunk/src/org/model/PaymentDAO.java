package org.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import util.connection.ConnectionManager;

public class PaymentDAO {

	public boolean insertOperatorPaymentInfo(String xForward,String via,String remoteAddress,int operator,float amount,String tmpRegId) {

		Connection conn = ConnectionManager.getConnection();
	 	   String sql = " Update EMP_REG_PAYMENT Set Payment_date=sysdate,Payment_Amount=?,Payment_Method='TELETALK',Payment_Status='PAID', " +
	 	   			 	" XFORWARD=?,VIA=?,REMOTE_ADDRESS=? Where TMP_REG_ID=? and PAYMENT_STATUS='NON-PAID'";

		   PreparedStatement stmt = null;
		   boolean resp=false;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setFloat(1,amount );
				stmt.setString(2, xForward);
				stmt.setString(3, via);
				stmt.setString(4, remoteAddress);
				stmt.setString(5, tmpRegId);

				if(stmt.executeUpdate()==1)
					resp=true;
					
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return resp;
	}
	
}

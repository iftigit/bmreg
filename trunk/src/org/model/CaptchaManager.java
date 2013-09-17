package org.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.table.CotaDTO;


import util.connection.ConnectionManager;

public class CaptchaManager {

	private static  String sqlString="Insert into CAPTCHA_STORE values(?,?,?)";
	private static PreparedStatement stmt = null;
	
	
	public static int saveCaptchCode(String randomCode,String captchaCode,int formType)
	{
		Connection conn = ConnectionManager.getConnection();
		int response=0;

		System.out.println("Random Code ==>"+randomCode);
		System.out.println("Captch Code ==>"+captchaCode);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		try
		{
			stmt = conn.prepareStatement(sqlString);
			stmt.setString(1, randomCode);
			stmt.setString(2, captchaCode);
			stmt.setInt(3, formType);
			response = stmt.executeUpdate();
			
		}
		
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}

		return response;
		
	}
	
	public boolean validateCaptcha(String randomCode,String captchaCode,int formType)
	{
		Connection conn = ConnectionManager.getConnection();
		String sql="Select count(*) total from CAPTCHA_STORE Where RANDOM_CODE=? and CAPTCHA_CODE=? and FORM_TYPE=?";
		ResultSet r = null;
		int total=0;

		try
		{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, randomCode);
			stmt.setString(2, captchaCode);
			stmt.setInt(3, formType);
			
			r = stmt.executeQuery();
			if (r.next())
			{
				total=r.getInt("total");
			}
			
		}
		
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}

		if(total>0)
			return true;
		else
			return false;
		
	}
	
	public boolean validateCaptcha(String randomCode)
	{
		Connection conn = ConnectionManager.getConnection();
		String sql="DELETE CAPTCHA_STORE WHERE RANDOM_CODE=?";
		int response=0;
		boolean res=false;
		try
		{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, randomCode);
			
			response = stmt.executeUpdate();
			res=true;
			
		}
		
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}

		return res;
		
	}

}

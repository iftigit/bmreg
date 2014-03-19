package org.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.table.AddressDTO;
import org.table.CountryDTO;

import util.connection.ConnectionManager;

public class CountryDAO {
	
	public static ArrayList<CountryDTO> getAllCountry()
	{
		ArrayList<CountryDTO> countryList=new ArrayList<CountryDTO>();
		
	 	   Connection conn = ConnectionManager.getConnection();
		   String sql = "SELECT COUNTRY_ID,SHORT_NAME,Visibility FROM MST_COUNTRY ORDER BY SHORT_NAME";
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   CountryDTO countryDto  = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				r = stmt.executeQuery();
				while (r.next())
				{
					countryDto=new CountryDTO();
					countryDto.setCountryId(r.getInt("COUNTRY_ID"));
					countryDto.setCountryName(r.getString("SHORT_NAME"));
					countryDto.setIsSelected("N");
					countryDto.setVisibility(r.getInt("Visibility"));
					countryList.add(countryDto);
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return countryList;

	}
	
	public String getCountry(int countryId)
	{

		
	 	   Connection conn = ConnectionManager.getConnection();
		   String sql = "SELECT COUNTRY_ID,SHORT_NAME,Visibility FROM MST_COUNTRY Where COUNTRY_ID="+countryId;
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   CountryDTO countryDto  = null;
		   String countryName="";
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				r = stmt.executeQuery();
				if (r.next())
				{
					countryName=r.getString("SHORT_NAME");
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return countryName;

	}

}

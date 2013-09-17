package org.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import oracle.jdbc.driver.OracleCallableStatement;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import org.table.CotaDTO;

import util.connection.ConnectionManager;

public class DataDAO {
	public ArrayList getDistrictList()
    {
        ArrayList list = new ArrayList();
        Connection conn = ConnectionManager.getConnection();
      
        String sql ="SELECT distinct  trim(district) " +
        "    FROM toraf " +
        " ORDER BY trim(district) ";
        ResultSet r = null;
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            r = stmt.executeQuery(sql);
            while (r.next()) {
            	String pro =r.getString(1);            	
                list.add(pro);
            }
        } catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
        
		return list;
    } 
	
	
	
	
	public ArrayList getThanaList(String dist)
    {
        ArrayList list = new ArrayList();
        Connection conn = ConnectionManager.getConnection();
      
        String sql ="SELECT distinct trim(thana) " +
        "    FROM toraf " +
        " WHERE district = '"+dist+"' " +
        " ORDER BY trim(thana) ";
        ResultSet r = null;
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            r = stmt.executeQuery(sql);
            while (r.next()) {
            	String pro =r.getString(1);            	
                list.add(pro);
            }
        } catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
        
		return list;
    } 
	
	public ArrayList getDataList(String dist, String thana)
    {
		ArrayList list = new ArrayList();
		Connection conn = ConnectionManager.getConnection();
		
		String sql="select unions,nvl(name1,' '),nvl(tel1,''),nvl(name2,' '),nvl(tel2,'') " +
		"	from toraf " +
		"	where district='"+dist+"' " +
		"	and thana='"+thana+"' " +
		"	order by idx ";
		ResultSet r = null;
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            r = stmt.executeQuery(sql);
            while (r.next()) {
            	
            	DataOO pro = new DataOO();
            	//String pro =r.getString(1);            	
                pro.setUnion(r.getString(1));
                pro.setName1(r.getString(2));
                pro.setMob1(r.getString(3));
                pro.setName2(r.getString(4));
                pro.setMob2(r.getString(5));
            	pro.setDist(dist);
            	pro.setThana(thana);
            	list.add(pro);
            }
        } catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
        
		list.trimToSize();
		return list;
    }
	
	public boolean updateDataInfo(ArrayList distCota)
	{
		
		Connection conn = ConnectionManager.getConnection();
		OracleCallableStatement stmt = null;
		
		String[] dist=new String[distCota.size()];
		String[] thana=new String[distCota.size()];
		String[] union=new String[distCota.size()];
		String[] name1=new String[distCota.size()];
		String[] mob1=new String[distCota.size()];
		String[] name2=new String[distCota.size()];
		String[] mob2=new String[distCota.size()];
		
		for(int i=0;i<distCota.size();i++)
		{
			dist[i]=((DataOO)distCota.get(i)).getDist();
			thana[i]=((DataOO)distCota.get(i)).getThana();
			union[i]=((DataOO)distCota.get(i)).getUnion();
			name1[i]=((DataOO)distCota.get(i)).getName1();
			mob1[i]=((DataOO)distCota.get(i)).getMob1();
			name2[i]=((DataOO)distCota.get(i)).getName2();
			mob2[i]=((DataOO)distCota.get(i)).getMob2();
			
		}		
		
         try {
        	 
    		ArrayDescriptor NarrayDescriptor = new ArrayDescriptor("REGDATAARRAY", conn);
    		
   			System.out.println("**-->> Procedure Name: [UpdateDataInfo] Start");
   			
            
            ARRAY inputdist = new ARRAY(NarrayDescriptor, conn, dist);
            ARRAY inputthana = new ARRAY(NarrayDescriptor, conn, thana);
            ARRAY inputunion = new ARRAY(NarrayDescriptor, conn, union);
            ARRAY inputname1 = new ARRAY(NarrayDescriptor, conn, name1);
            ARRAY inputmob1 = new ARRAY(NarrayDescriptor, conn, mob1);
            ARRAY inputname2 = new ARRAY(NarrayDescriptor, conn, name2);
            ARRAY inputmob2 = new ARRAY(NarrayDescriptor, conn, mob2);
                       
            
            stmt = (OracleCallableStatement) conn.prepareCall("{ call update_data_Info(?,?,?,?,?,?,?)  }");

            System.out.println("Procedure End.");
          
            stmt.setArray(1, inputdist);
            stmt.setArray(2, inputthana);
            stmt.setArray(3, inputunion);
            stmt.setArray(4, inputname1);
            stmt.setArray(5, inputmob1);
            stmt.setArray(6, inputname2);
            stmt.setArray(7, inputmob2); 
            
            
            stmt.executeUpdate();
        }
        catch (Exception e){e.printStackTrace();}
        finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
        
        return true;

	}
	
	
	
}

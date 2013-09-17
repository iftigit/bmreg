package org.model;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.table.CotaDTO;

import oracle.jdbc.driver.OracleCallableStatement;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import util.connection.ConnectionManager;


public class CotaDAO {
	
	
	public ArrayList getAllDistWithCota()
	{
		ArrayList allCota=new ArrayList();		
		Connection conn = ConnectionManager.getConnection();
		ArrayList returnList=new ArrayList();

		String sql = "select d.DIST_NAME,c.DIST_ID,c.COTA,c.TOTAL from cota c, district d " +
			" where c.DIST_ID=d.DIST_ID " +
			" order by c.DIST_ID ";

		System.out.println("All Cota (sql) :" + sql);

		PreparedStatement stmt = null;
		ResultSet r = null;

		try
		{
			stmt = conn.prepareStatement(sql);
			r = stmt.executeQuery();
			while (r.next())
			{
			   CotaDTO dDTO=new CotaDTO();
			   dDTO.setDistname(r.getString(1));			   
			   dDTO.setDistid(r.getString(2));		   
			   dDTO.setDiscota(r.getFloat(3));
			   dDTO.setTotal(r.getInt(4));			   
			   allCota.add(dDTO);

			}
		}
		
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}

		return allCota;
	}
	
	public boolean updateCotaInfo(ArrayList distCota)
	{
		
		Connection conn = ConnectionManager.getConnection();
		OracleCallableStatement stmt = null;
		
		String[] distid=new String[distCota.size()];
		float[] distcota=new float[distCota.size()];
		long[] total = new long[distCota.size()];
		
		for(int i=0;i<distCota.size();i++)
		{
			distid[i]=((CotaDTO) distCota.get(i)).getDistid();
		    distcota[i]=((CotaDTO) distCota.get(i)).getDiscota();
		    total[i]= ((CotaDTO) distCota.get(i)).getTotal();
		}		
		
         try {
        	 
    		ArrayDescriptor NarrayDescriptor = new ArrayDescriptor("NUMBERARRAY", conn);
    		
   			System.out.println("**-->> Procedure Name: [UpdateCotaInfo] Start");
   			
            
            ARRAY inputdistId = new ARRAY(NarrayDescriptor, conn, distid);
            ARRAY inputdistCota = new ARRAY(NarrayDescriptor,conn, distcota);
            ARRAY inputtotal = new ARRAY(NarrayDescriptor,conn, total);
            
            stmt = (OracleCallableStatement) conn.prepareCall("{ call update_Cota_Info(?,?,?)  }");

            System.out.println("Procedure End.");
          
            stmt.setArray(1, inputdistId);
            stmt.setArray(2, inputdistCota);
            stmt.setArray(3, inputtotal);
            stmt.executeUpdate();
        }
        catch (Exception e){e.printStackTrace();}
        finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
        
        return true;

	}	
	
	
	
}

package org.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.table.CountryDTO;
import org.table.JobPreferenceDTO;

import util.connection.ConnectionManager;

public class JobCategoryDAO {
	
	
	public static ArrayList<JobPreferenceDTO> getAllJob(int level)
	{
		   ArrayList<JobPreferenceDTO> jobList=new ArrayList<JobPreferenceDTO>();
		
	 	   Connection conn = ConnectionManager.getConnection();
	 	  String sql="";
	 	   if(level==99)
	 		  sql = "Select * from MST_JOBS order by job_title";
	 	   else
	 		  sql = "Select * from MST_JOBS Where level_no="+level+" order by job_title";
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   JobPreferenceDTO jobDto  = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				r = stmt.executeQuery();
				while (r.next())
				{
					jobDto=new JobPreferenceDTO();
					jobDto.setJobId(r.getInt("JOB_ID"));
					jobDto.setJobTitle(r.getString("JOB_TITLE"));
					jobList.add(jobDto);
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return jobList;

	}
	
	public String getJobCagegorySelectBox(int parentJobId,int level,int componentIndex,String selectType)
	{
		
	 	   Connection conn = ConnectionManager.getConnection();
		   String sql = " Select * from mst_jobs where job_id in(select child_id from JOBS_MAPPING where parent_id="+parentJobId+")   " +
		   		        " and level_no="+level+"  order by job_title";
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   String selectTxt="&nbsp;";
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				r = stmt.executeQuery();
				int counter=0;
				while (r.next())
				{
					if(counter==0)
						selectTxt+="<option value=''>Select</option>";
					
					selectTxt+="<option value='"+r.getInt("JOB_ID")+"'>"+r.getString("JOB_TITLE")+"</option>";
					counter++;
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 	
	 		if(!selectTxt.equalsIgnoreCase("&nbsp;"))
	 		{
	 			
	 			if(selectType.equalsIgnoreCase("jobPreferenceJobCategory"))
	 			{
	 			if(level==2)
	 				selectTxt="<select name='subJob_1_"+componentIndex+"' id='subJob_1_"+componentIndex+"' style='width:150px;border:1px solid grey;' onchange=\"fetchJobCategory(this.value,3,"+componentIndex+",'leftDiv"+componentIndex+"3','jobPreferenceJobCategory')\">"+selectTxt;
	 			else
	 				selectTxt="<select name='subJob_2_"+componentIndex+"' id='subJob_2_"+componentIndex+"' style='width:150px;border:1px solid grey;'>"+selectTxt;
	 			selectTxt+="</select>";
	 			}
	 			else if(selectType.equalsIgnoreCase("abroadJobCategory"))
	 			{
	 			if(level==2)
	 				selectTxt="<select name='abroadSubJob_1_"+componentIndex+"' id='abroadSubJob_1_"+componentIndex+"' style='width:120px;border:1px solid grey;' onchange=\"fetchJobCategory(this.value,3,"+componentIndex+",'abroadExpJobSubSubCat"+componentIndex+"','abroadJobCategory')\">"+selectTxt;
	 			else
	 				selectTxt="<select name='abroadSubJob_2_"+componentIndex+"' id='abroadSubJob_2_"+componentIndex+"' style='width:120px;border:1px solid grey;'>"+selectTxt;
	 			selectTxt+="</select>";
	 			}
	 			else if(selectType.equalsIgnoreCase("localJobCategory"))
	 			{
	 			if(level==2)
	 				selectTxt="<select name='localSubJob_1_"+componentIndex+"' id='localSubJob_1_"+componentIndex+"' style='width:120px;border:1px solid grey;' onchange=\"fetchJobCategory(this.value,3,"+componentIndex+",'localExpJobSubSubCat"+componentIndex+"','localJobCategory')\">"+selectTxt;
	 			else
	 				selectTxt="<select name='localSubJob_2_"+componentIndex+"' id='localSubJob_2_"+componentIndex+"' style='width:120px;border:1px solid grey;'>"+selectTxt;
	 			selectTxt+="</select>";
	 			}
	 		}
	 	return selectTxt;

	}


}

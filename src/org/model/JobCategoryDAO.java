package org.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.table.CountryDTO;
import org.table.JobCategoryDTO;
import org.table.JobPreferenceDTO;

import util.connection.ConnectionManager;

public class JobCategoryDAO {
	
	private static final Connection conn = ConnectionManager.getConnection();
	public static ArrayList<JobCategoryDTO> getAllJob()
	{
		   ArrayList<JobCategoryDTO> jobList=new ArrayList<JobCategoryDTO>();
		
	 	   Connection conn = ConnectionManager.getConnection();
	 	   String sql="Select main_jobid,subjob_id,sub_subjobid,main_job_title,sub_job_title,sub_subjobtitle,main_job_visibility,sub_job_visibility,sub_subjob_visibility from  " +
				 	  "( " +
				 	  "Select tmp4.*,job_title sub_subjobtitle,visibility sub_subjob_visibility from  " +
				 	  "( " +
				 	  "Select tmp3.*,JOBS_MAPPING.child_id sub_subjobId from ( " +
				 	  "Select tmp2.*,MST_JOBS.JOB_TITLE sub_job_title,visibility sub_job_visibility from (Select child_id subJob_id,tmp1.* from (Select job_id main_jobId,job_title main_job_title,visibility main_job_visibility from MST_JOBS where Level_No=1 order by job_title)tmp1  left outer join JOBS_MAPPING " +
				 	  "on parent_id=main_jobId)tmp2,MST_JOBS  " +
				 	  "Where tmp2.subJob_id=MST_JOBS.job_id)tmp3 left outer join jobs_mapping " +
				 	  "on subjob_id=jobs_mapping.PARENT_ID " +
				 	  ")tmp4 left outer join mst_jobs " +
				 	  "on sub_subjobid=job_id " +
				 	  ") " +
				 	  "order by main_jobid,subjob_id,sub_subjobid ";
	 	   
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   JobCategoryDTO jobDto  = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				r = stmt.executeQuery();
				while (r.next())
				{
					jobDto=new JobCategoryDTO();
					jobDto.setCategoryId(r.getInt("MAIN_JOBID"));
					jobDto.setCategoryName(r.getString("MAIN_JOB_TITLE"));
					jobDto.setMainJobVisibility(r.getInt("MAIN_JOB_VISIBILITY"));
					
					jobDto.setSubCategoryId(r.getInt("SUBJOB_ID"));
					jobDto.setSubCategoryName(r.getString("SUB_JOB_TITLE"));
					jobDto.setSubJobVisibility(r.getInt("SUB_JOB_VISIBILITY"));
					
					jobDto.setSubSubCategoryId(r.getInt("SUB_SUBJOBID"));
					jobDto.setSubSubCategoryName(r.getString("SUB_SUBJOBTITLE"));
					jobDto.setSubSubJobVisibility(r.getInt("SUB_SUBJOB_VISIBILITY"));
					
					jobList.add(jobDto);
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
	 		
	 	return jobList;

	}
	
	public static ArrayList<JobPreferenceDTO> getAllJob(int level)
	{
		   ArrayList<JobPreferenceDTO> jobList=new ArrayList<JobPreferenceDTO>();
		
	 	   Connection conn = ConnectionManager.getConnection();
	 	  String sql="";
	 	   if(level==99)
	 		  sql = "Select * from MST_JOBS where VISIBILITY=1 order by job_title";
	 	   else
	 		  sql = "Select * from MST_JOBS Where level_no="+level+" and VISIBILITY=1 order by job_title";
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
	
	public static ArrayList<JobPreferenceDTO> getSubJobs(int patentJobId,int level)
	{
		   ArrayList<JobPreferenceDTO> jobList=new ArrayList<JobPreferenceDTO>();
		
	 	   Connection conn = ConnectionManager.getConnection();
	 	   //String sql = "Select * from MST_JOBS where job_Id in (Select child_id from JOBS_MAPPING where Parent_Id="+patentJobId+") and Level_No="+level+" Order by Job_Title";
	 	  String sql = "Select * from MST_JOBS where  Level_No="+level+" Order by Job_Title";
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
	 			else if(selectType.equalsIgnoreCase("lotteryJobCategory"))
	 			{
	 			if(level==2)
	 				selectTxt="<select name='lotterySubJob_1_"+componentIndex+"' id='lotterySubJob_1_"+componentIndex+"' style='width:120px;border:1px solid grey;' onchange=\"fetchJobCategory(this.value,3,"+componentIndex+",'lotteryJobSubSubCat"+componentIndex+"','lotteryJobCategory')\">"+selectTxt;
	 			else
	 				selectTxt="<select name='lotterySubJob_2_"+componentIndex+"' id='lotterySubJob_2_"+componentIndex+"' style='width:120px;border:1px solid grey;' multiple='multiple' class='txtBox'>"+selectTxt;
	 			selectTxt+="</select>";
	 			}
	 			else if(selectType.equalsIgnoreCase("jobManagement"))
	 			{
	 			if(level==2)
	 				selectTxt="<select name='subJob1' id='subJob1' style='width:200px;border:1px solid grey;' onchange=\"fetchJobCategory(this.value,3,0,'subJobDiv"+level+"','jobManagement')\">"+selectTxt;
	 			else
	 				selectTxt="<select name='subJob2' id='subJob2' style='width:200px;border:1px solid grey;'>"+selectTxt;
	 			selectTxt+="</select>";
	 			}
	 		}
	 	return selectTxt;

	}
	
	public static boolean updateJobStatus(int jobId,int status)
	{
		
		 Connection conn = ConnectionManager.getConnection();
		 String sql="";
		 if(status==0){
		  sql = " Update MST_JOBS Set VISIBILITY=? Where JOB_ID in "+
		                "(select child_id from JOBS_MAPPING "+
"where parent_id in (select child_id from JOBS_MAPPING where parent_id=?)"+
" union "+
"select child_id from JOBS_MAPPING where parent_id=? union select ? from dual)";
		 }
		 else if(status==1){
			  sql = " Update MST_JOBS Set VISIBILITY=? Where JOB_ID in "+
              "(select parent_id from JOBS_MAPPING "+
"where child_id in (select parent_id from JOBS_MAPPING where child_id=?)"+
" union "+
"select parent_id from JOBS_MAPPING where child_id=? union select ? from dual)";
}
		   
		   int operation=0;
		   PreparedStatement stmt = null;
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, status);
			    stmt.setInt(2, jobId);	
			    stmt.setInt(3, jobId);
			    stmt.setInt(4, jobId);
			    
			    operation=stmt.executeUpdate();
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
		
		if(operation>=1)
			return true;
		else
			return false;
	}
	
	public String  getJobPreferenceDescription(String jobPreferenceStr)
	{
		 String sql="";
		 String jobDescriptionStr="";
		 if(jobPreferenceStr!=null){
		 String[] jobArr=jobPreferenceStr.split("@");
		 		   
		   ResultSet r = null;
		   PreparedStatement stmt = null;
			try
			{
				for(int i=0;i<jobArr.length;i++){
					
					if(jobDescriptionStr.length()>0)
						jobDescriptionStr=jobDescriptionStr.substring(0, jobDescriptionStr.length()-2);
					jobDescriptionStr+=" "+i+1+". ";
					String[] jobTypeArr=jobArr[i].split("#");
					
					for(int j=0;j<jobTypeArr.length;j++){
						sql="Select JOB_TITLE FROM MST_JOBS Where job_id="+jobTypeArr[j]+" and level_no="+(j+1);
						stmt = JobCategoryDAO.conn.prepareStatement(sql);
						r = stmt.executeQuery();
						if (r.next())
						{
							jobDescriptionStr+=r.getString("JOB_TITLE")+", ";
						}
					}
				}
				
				
			    
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();
	 		//ConnectionManager.closeConnection(conn);
	 		} catch (Exception e)
				{e.printStackTrace();}stmt = null;//JobCategoryDAO.c = null;
				}
		 	}
		 
		 if(jobDescriptionStr.length()>0)
				jobDescriptionStr=jobDescriptionStr.substring(0, jobDescriptionStr.length()-2);
	 		return jobDescriptionStr;
		
	}
	
	public String  getJobExperienceDescription(String jobExperience)
	{
		 String sql="";
		 String jobDescriptionStr="";
		 if(jobExperience!=null && !jobExperience.equalsIgnoreCase("")){
		 		   
		   ResultSet r = null;
		   PreparedStatement stmt = null;
			try
			{
					String[] jobTypeArr=jobExperience.split("#");
					
					for(int j=0;j<jobTypeArr.length;j++){
						if(j==2)
							sql="Select JOB_TITLE FROM MST_JOBS Where job_id in ("+jobTypeArr[j]+") and level_no="+(j+1);
						else
							sql="Select JOB_TITLE FROM MST_JOBS Where job_id="+jobTypeArr[j]+" and level_no="+(j+1);
						stmt = JobCategoryDAO.conn.prepareStatement(sql);
						r = stmt.executeQuery();
						if (r.next())
						{
							jobDescriptionStr+=r.getString("JOB_TITLE")+", ";
						}
					}
				}
			
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();
	 		//ConnectionManager.closeConnection(conn);
	 		} catch (Exception e)
				{e.printStackTrace();}stmt = null;//JobCategoryDAO.c = null;
				}
		 	}
		 
		 if(jobDescriptionStr.length()>0)
				jobDescriptionStr=jobDescriptionStr.substring(0, jobDescriptionStr.length()-2);
	 		return jobDescriptionStr;
		
	}
	public static void main(String args[]){		
		JobCategoryDAO jcd=new JobCategoryDAO();
		jcd.getJobPreferenceDescription("252#253#271@180#189#0@");
	}


}

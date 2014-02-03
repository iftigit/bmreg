<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="org.controller.registration.*" %>
<%@ page import="java.util.ArrayList" %>
<head>


<meta content="text/html;charset=utf-8" http-equiv="Content-Type">
<meta content="utf-8" http-equiv="encoding">
<title>BMET</title>
 <link rel="stylesheet" href="/BMREG_WEB/resources/css/style.css" />	
 <script type="text/javascript" src="/BMREG_WEB/resources/js/lib/prototype-1.6.0.2.js"></script>	
  <script type="text/javascript" src="/BMREG_WEB/resources/js/lib/jquery-1.6.4.min.js"></script> 
<script type="text/javascript" src="/BMREG_WEB/resources/js/util/util.js"></script>
<script type="text/javascript" src="/BMREG_WEB/resources/js/util/numeric.js"></script>
 
 <link type="text/css" rel="Stylesheet" href="/BMREG_WEB/resources/js/lib/jquery.validity.1.2.0/jquery.validity.css" />
        <script type="text/javascript" src="/BMREG_WEB/resources/js/lib/jquery.validity.1.2.0/jQuery.validity.js"></script>
 
  <script type="text/javascript" src="/BMREG_WEB/resources/js/address.js"></script>
  
  <script type="text/javascript">

var ajax_load="<br/><center><img src='/BMREG_WEB/resources/images/ajax-loader1.gif' border='0' /></center>";

function saveNewJob()
{
  
  var jobName=document.getElementById("jobName").value;
  var jobTypeId=document.getElementById("jobTypeId").value;
  
  if(jobName=="")
  {
   alert("Please provide Job Name.");
   return;
  }
  else if(jobTypeId=="none")
  {
   alert("Please Select Job Type.");
   return;
  }
  
  
  var loadUrl="createNewJob.action?jobName="+jobName+"&jobTypeId="+jobTypeId;
			jQuery("#msgDiv")  
				.html(ajax_load)  
				.load(loadUrl, {},function(responseText){ 
				    if(responseText=="New Job Successfully Created.")
				    {
				      document.getElementById("jobName").value="";
				      document.getElementById("jobTypeId").value="none";
				    }
				    alert(responseText); 				    
					jQuery("#msgDiv").html(responseText);			   
				});

}

function saveJobStatus(jobId,visibility)
{
 var loadUrl="saveJobStatus.action?jobId="+jobId+"&visibility="+visibility;
			jQuery("#msgDiv")  
				.html(ajax_load)  
				.load(loadUrl, {},function(responseText){ 
				    alert(responseText); 
					jQuery("#msgDiv").html(responseText);
					fetchJobList();			   
				}); 				
}

function fetchJobList()
{
 var loadUrl="fetchJobList.action";
			jQuery("#jobListDiv")  
				.html(ajax_load)  
				.load(loadUrl, {},function(responseText){  
					jQuery("#jobListDiv").html(responseText);
				});
}
function changeImage(imgId,imgSrc){
document.getElementById(imgId).src=imgSrc;
}

function fetchJobCategory(parentJobId,level,componentIndex,waitingDiv,selectType)
{
	    
        document.getElementById(waitingDiv).innerHTML="";
 		var ajax_url="<img src='/BMREG_WEB/resources/images/ajax-loader.gif' alt='Loading ....' />"; 
		var url="/BMREG_WEB/fetchSubJob.action?etc="+new Date().getTime();
		
			$("#"+waitingDiv) 
			.html(ajax_url)  
			.load(url, {parentJobId: parentJobId,jobLevel: level,componentIndex:componentIndex,selectType:selectType},function(responseText){  
				if(responseText!="")
				$("#"+waitingDiv).innerHTML= responseText;
			});
}
function loadEditPanel(mainJobId,subJobId,subSubJobId)
{
	var loadUrl="getJobEditPanel.action";
			jQuery("#addEditPanel")  
				.html(ajax_load)  
				.load(loadUrl,{categoryId: mainJobId,subCategoryId: subJobId,subSubCategoryId: subSubJobId},function(responseText){  
					jQuery("#addEditPanel").html(responseText);
				});
}
</script>
</head>
<body style="margin: 0px;">

<div style="width: 100%;height: 100px;border-bottom: 1px solid #006219;">
<center>
<div style="width: 1000px;height: 100px;border-right: 1px solid #006219;border-left: 1px solid #006219;">
	<div style="float: left; margin-top: 20px;width: 100px;">
	 &nbsp;&nbsp;&nbsp; <img src="/BMREG_WEB/resources/images/bagladesh_logo.gif" width="60" height="60" />
	</div>
	<div style="float: left;margin-left: 30px;color: black;margin-top: 15px;text-align: left;">
	 	<div style="font-size: 27px;font-weight: bold;">Bureau of Manpower, Employment & Training (BMET)</div>
	</div>
</div>
</center>
</div>
<form method="post" action="approveNewUserAndSendSms.action">
<center>
<br/>
<div class="box" style="margin-top: 20px;width: 980px;text-align: center;">
    <h3>Job Category Management</h3>
    <div style="padding-bottom: 10px;padding-left: 10px;">
      <table width="98%" style="border: 1px dotted green;">
            <tr>
            	<td colspan="5" style="text-align: left;color: maroon;font-size: 15px;font-weight: bold;" height="30">
            		Create New Job
            	</td>
            </tr>
      		<tr>
      			<td width="10%" style="text-align: left;" height="30">Job Name</td>
      			<td width="50%" style="text-align: left;">
      				<input type="text" name="jobName" id="jobName" value="<s:property value='startDate' />" style="border: 1px solid gray;width: 350px;" />
      			</td>        			
      			<td width="10%" style="text-align: left;">Job Type</td>
      			<td width="20%" style="text-align: left;">
      				<select style="border: 1px solid gray;width: 200px;" name="jobTypeId" id="jobTypeId" >
      				  <option value="none">Select a Type</option>
      				  <option value="1">Main Job</option>
      				  <option value="2">Sub-Job</option>
      				  <option value="3">Sub sub Job</option>
      				</select>
      			</td>
      			<td width="10%" style="text-align: center;"><input type="button" value="Create"  name="newJob" onclick="saveNewJob()" />  </td>
      		</tr>
      </table>

    </div><br/>
    <div style="padding-bottom: 10px;padding-left: 10px;" id="addEditPanel">
      <table width="98%" style="border: 1px dotted green;">
            <tr>
            	<td colspan="5" style="text-align: left;color: maroon;font-size: 15px;font-weight: bold;" height="30">
            		Add/Edit Job Mapping
            	</td>
            </tr>
      		<tr>
      			<td width="8%" style="text-align: left;" height="30">Main Job</td>
      			<td width="22%">
      				<select style="border: 1px solid gray;width: 200px;" onchange="fetchJobCategory(this.value,2,0,'subJobDiv1','jobManagement')">
      				<option value="-99">Select Main Job</option>
      				<s:iterator value="mainJobList">
      				 <option value="<s:property value="jobId" />"><s:property value="jobTitle" /></option>
      				</s:iterator>
      				</select>
      			</td>
      			<td width="6%" style="text-align: left;">Sub Job</td>
      			<td width="22%" id="subJobDiv1">
      				<select style="border: 1px solid gray;width: 200px;" >
      					<option value="-99">Select Sub Job</option>
      				</select>
      			</td>
      			<td width="10%" style="text-align: left;">Sub-sub Job</td>
      			<td width="22%" id="subJobDiv2">
      				<select style="border: 1px solid gray;width: 200px;">
      					<option value="-99">Select Sub-Sub Job</option>
      				</select>
      			</td> 
      			<td width="10%" style="text-align: center;"><input type="button" value="Update"  name="editJob" /></td>
      		</tr>
      </table>

    </div><br/>
    <div id="jobListDiv">
    <table width="98%" align="center" border="0" cellpadding="2" cellspacing="0" style="border: 1px solid grey;">
     
     <tr bgcolor="#F1F1F1">
     
        <td width="5%" align="left" style="padding-left: 5px;" height="25">SL</td>
      	<td width="27%" align="left" style="padding-left: 2px;" height="25">Main Job</td>
      	<td width="27%" align="left" style="padding-left: 2px;" height="25">Sub Job</td>
      	<td width="27%" align="left" style="padding-left: 2px;" height="25">Sub-Sub Job</td>
      	<td width="14%" align="center" style="padding-left: 2px;" height="25">Edit</td>
      </tr>
      <s:iterator value="jobList" status="indx">
      <s:if test="#indx.even == true">
        <tr bgcolor="#eeeeee">
      </s:if>
      <s:if test="#indx.odd == true">
        <tr>
      </s:if>
        <td align="left" style="padding-left: 5px;" height="25"><s:property value="#indx.count" />.</td>
      	<td align="left" style="padding-left: 2px;" height="25">
      	&nbsp;&nbsp; 
      	<s:if test="mainJobVisibility==1">      	      		      	
	      	<img id="mainEnableImg<s:property value="#indx.count" />" src='/BMREG_WEB/resources/images/enableDark.png' border='0' height="18" width="18" />	      	 
	      	&nbsp;
	      	<a href="javascript:void(0)" onclick="saveJobStatus(<s:property value="categoryId" />,0)" style="text-decoration: none;"> 
	      	<img id="mainDisableImg<s:property value="#indx.count" />" src='/BMREG_WEB/resources/images/disableLight.png' border='0' height="18" width="18" onmouseover="changeImage('mainDisableImg<s:property value="#indx.count" />','/BMREG_WEB/resources/images/disableDark.png')" onmouseout="changeImage('mainDisableImg<s:property value="#indx.count" />','/BMREG_WEB/resources/images/disableLight.png')"/>
	      	</a>
	      	<s:property value="categoryName" />
	      	(<s:property value="categoryId" />)
      	</s:if>
      	<s:if test="mainJobVisibility==0">      	
	      	<a href="javascript:void(0)" onclick="saveJobStatus(<s:property value="categoryId" />,1)" style="text-decoration: none;">
	      	<img id="mainEnableImg<s:property value="#indx.count" />" src='/BMREG_WEB/resources/images/enableLight.png' border='0' height="18" width="18"  onmouseover="changeImage('mainEnableImg<s:property value="#indx.count" />','/BMREG_WEB/resources/images/enableDark.png')" onmouseout="changeImage('mainEnableImg<s:property value="#indx.count" />','/BMREG_WEB/resources/images/enableLight.png')"/>
	      	</a> 
	      	&nbsp; 
	      	<img id="mainDisableImg<s:property value="#indx.count" />" src='/BMREG_WEB/resources/images/disableDark.png' border='0' height="18" width="18" />
	      	<font color="#8E8E8E">
		      	<s:property value="categoryName" />
		      	(<s:property value="categoryId" />)
	      	</font>
      	</s:if>
      	      	
      	</td>
      	<td align="left" style="padding-left: 2px;" height="25">
      	&nbsp;&nbsp; 
      	<s:if test="subJobVisibility==1">      	      		      	
	      	<img id="subEnableImg<s:property value="#indx.count" />" src='/BMREG_WEB/resources/images/enableDark.png' border='0' height="18" width="18" />	      	 
	      	&nbsp;
	      	<a href="javascript:void(0)" onclick="saveJobStatus(<s:property value="subCategoryId" />,0)" style="text-decoration: none;"> 
	      	<img id="subDisableImg<s:property value="#indx.count" />" src='/BMREG_WEB/resources/images/disableLight.png' border='0' height="18" width="18" onmouseover="changeImage('subDisableImg<s:property value="#indx.count" />','/BMREG_WEB/resources/images/disableDark.png')" onmouseout="changeImage('subDisableImg<s:property value="#indx.count" />','/BMREG_WEB/resources/images/disableLight.png')"/>
	      	</a>
	      	<s:property value="subCategoryName" />
	      	(<s:property value="subCategoryId" />) 
      	</s:if>
      	<s:if test="subJobVisibility==0">      	
	      	<a href="javascript:void(0)" onclick="saveJobStatus(<s:property value="subCategoryId" />,1)" style="text-decoration: none;">
	      	<img id="subEnableImg<s:property value="#indx.count" />" src='/BMREG_WEB/resources/images/enableLight.png' border='0' height="18" width="18"  onmouseover="changeImage('subEnableImg<s:property value="#indx.count" />','/BMREG_WEB/resources/images/enableDark.png')" onmouseout="changeImage('subEnableImg<s:property value="#indx.count" />','/BMREG_WEB/resources/images/enableLight.png')"/>
	      	</a> 
	      	&nbsp; 
	      	<img id="subDisableImg<s:property value="#indx.count" />" src='/BMREG_WEB/resources/images/disableDark.png' border='0' height="18" width="18" />
	      	<font color="#8E8E8E"> 
		      	<s:property value="subCategoryName" />
		      	(<s:property value="subCategoryId" />)
	      	</font>
      	</s:if>
      	     	
      	</td>
      	<td align="left" style="padding-left: 2px;" height="25">
      	 
      	<s:if test="subSubCategoryId!=0">
      	 &nbsp;&nbsp; 
      	<s:if test="subSubJobVisibility==1">      	      		      	
	      	<img id="subSubEnableImg<s:property value="#indx.count" />" src='/BMREG_WEB/resources/images/enableDark.png' border='0' height="18" width="18" />	      	 
	      	&nbsp;
	      	<a href="javascript:void(0)" onclick="saveJobStatus(<s:property value="subSubCategoryId" />,0)" style="text-decoration: none;"> 
	      	<img id="subSubDisableImg<s:property value="#indx.count" />" src='/BMREG_WEB/resources/images/disableLight.png' border='0' height="18" width="18" onmouseover="changeImage('subSubDisableImg<s:property value="#indx.count" />','/BMREG_WEB/resources/images/disableDark.png')" onmouseout="changeImage('subSubDisableImg<s:property value="#indx.count" />','/BMREG_WEB/resources/images/disableLight.png')"/>
	      	</a>
	      	<s:property value="subSubCategoryName" />
	      	(<s:property value="subSubCategoryId" />) 
      	</s:if>
      	<s:if test="subSubJobVisibility==0">      	
	      	<a href="javascript:void(0)" onclick="saveJobStatus(<s:property value="subSubCategoryId" />,1)" style="text-decoration: none;">
	      	<img id="subSubEnableImg<s:property value="#indx.count" />" src='/BMREG_WEB/resources/images/enableLight.png' border='0' height="18" width="18"  onmouseover="changeImage('subSubEnableImg<s:property value="#indx.count" />','/BMREG_WEB/resources/images/enableDark.png')" onmouseout="changeImage('subSubEnableImg<s:property value="#indx.count" />','/BMREG_WEB/resources/images/enableLight.png')"/>
	      	</a> 
	      	&nbsp; 
	      	<img id="subSubDisableImg<s:property value="#indx.count" />" src='/BMREG_WEB/resources/images/disableDark.png' border='0' height="18" width="18" />
	      	<font color="#8E8E8E"> 
		      	<s:property value="subSubCategoryName" />
		      	(<s:property value="subSubCategoryId" />)
	      	</font>
      	</s:if>
      	</s:if>
      	 
      	</td>
      	
      	<td align="center" style="padding-left: 10px;" height="25">
      	
      	<a href="javascript:void(0)" onclick="loadEditPanel('<s:property value="categoryId" />','<s:property value="subCategoryId" />','<s:property value="subSubCategoryId" />')">
	      	<img id="edit<s:property value="#indx.count" />" src='/BMREG_WEB/resources/images/edit.png' border='0' height="18" width="18" />
  		</a>    	
      	</td>
      	      	
        </tr> 
      </s:iterator>
      
      
    </table>
    </div>   
<br/>     

</div>
    <br/>
    <a href="systemAdminHome.action">Go Home</a>
    <br/>
<p style="height: 30px" id="msgDiv"></p>
</center>
</form>

</body>

</html>
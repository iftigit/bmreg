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

function searchJobSeeker()
{

var jobseekerNumber=document.getElementById("jobseekerNumber").value;
var passportNo=document.getElementById("passportNo").value;
var nationalId=document.getElementById("nationalId").value;
var birthRegId=document.getElementById("birthRegId").value;
var empFullName=document.getElementById("empFullName").value;
var empBirthDate=document.getElementById("empBirthDate").value;
var empMobileNumber=document.getElementById("empMobileNumber").value;

var loadUrl="searchJobSeeker.action?jobseekerNumber="+jobseekerNumber+"&passportNo="+passportNo+"&nationalId="+nationalId+"&birthRegId="+birthRegId+"&empFullName="+empFullName+"&empBirthDate="+empBirthDate+"&empMobileNumber="+empMobileNumber;
			jQuery("#searchResultDiv")  
				.html(ajax_load)  
				.load(loadUrl, {},function(responseText){
				  
					jQuery("#searchResultDiv").html(responseText);
									   
				});
				
}


</script>
</head>
<body style="margin: 0px;">

<div style="width: 100%;height: 100px;border-bottom: 1px solid #006219;">
<center>
<div style="width: 1000px;height: 100px;border-right: 1px solid #006219;border-left: 1px solid #006219;">
	<div style="float: left; margin-top: 20px;width: 100px;">
	 <img src="/BMREG_WEB/resources/images/bagladesh_logo.gif" width="60" height="60" />
	</div>
	<div style="float: left;margin-left: 30px;color: black;margin-top: 15px;text-align: left;">
	 	<div style="font-size: 27px;font-weight: bold;">Bureau of Manpower, Employment & Training (BMET)</div>
	</div>
</div>
</center>
</div>
<center>
<br/>
<div class="box" style="margin-top: 30px;width: 900px;text-align: center;">
    <h3>Registration Correction Search Form</h3>
    <div style="padding-bottom: 10px;">
    <table width="80%" align="center" border="0">
     	<tr>
     		<td width="15%" align="left">Registration Id</td>
     		<td width="35%" align="left"><input type="text" name="jobseekerNumber" id="jobseekerNumber" value="" style="border: 1px solid gray;" /></td>
     		<td width="15%" align="left">Passport No</td>
     		<td width="35%" align="left"><input type="text" name="passportNo" id="passportNo" value="" style="border: 1px solid gray;" /></td>
        </tr>
        <tr>
     		<td align="left">National Id</td>
     		<td align="left"><input type="text" name="nationalId" id="nationalId" value="" style="border: 1px solid gray;" /></td>
     		<td align="left">Birth Reg. Id</td>
     		<td align="left"><input type="text" name="birthRegId" id="birthRegId" value="" style="border: 1px solid gray;" /></td>
        </tr>
        <tr>
     		<td align="left">Name</td>
     		<td align="left"><input type="text" name="empFullName" id="empFullName" value="" style="border: 1px solid gray;" /></td>
     		<td align="left">Date of Birth</td>
     		<td align="left"><input type="text" name=empBirthDate id="empBirthDate" value="" style="border: 1px solid gray;" /></td>
        </tr>
        <tr>
     		<td align="left">Mobile No.</td>
     		<td align="left"><input type="text" name="empMobileNumber" id="empMobileNumber" value="" style="border: 1px solid gray;" /></td>
     		<td align="left"></td>
     		<td align="left"></td>
        </tr>
        
    </table>
<p style="padding-top: 20px;">     
<input type="button" name="search" value="Search Jobseeker" style="width: 200px;height: 35px;"  onclick="searchJobSeeker()"/>
</p>
</div>

<p id="searchResultDiv"></p>
    
    <p style="padding-bottom: 10px;">
    <a href="systemAdminHome.action">Go Home</a>
    </p>

</div>

</center>

</body>

</html>
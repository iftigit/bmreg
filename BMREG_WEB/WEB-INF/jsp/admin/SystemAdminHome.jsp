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
<title>Reg. System - Admin Home</title>
 <link rel="stylesheet" href="/BMREG_WEB/resources/css/style.css" />	
  <script type="text/javascript" src="/BMREG_WEB/resources/js/lib/jquery-1.6.4.min.js"></script> 
<script type="text/javascript" src="/BMREG_WEB/resources/js/util/util.js"></script>
<script type="text/javascript" src="/BMREG_WEB/resources/js/util/numeric.js"></script>
 <script type="text/javascript" src="/BMREG_WEB/resources/js/util/util.js"></script>
 
 <link type="text/css" rel="Stylesheet" href="/BMREG_WEB/resources/js/lib/jquery.validity.1.2.0/jquery.validity.css" />
        <script type="text/javascript" src="/BMREG_WEB/resources/js/lib/jquery.validity.1.2.0/jQuery.validity.js"></script>
 

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
	 	<div style="font-size: 20px;margin-top: 10px;">Online Registration System</div>
	</div>
</div>
</center>
</div>
<center>
<br/>
<div class="box" style="margin-top: 100px;width: 500px;text-align: center;">
    <h3>Activities</h3>
    
<form id="loginForm" name="loginForm" method="post" action="checkValidity.action">    

<table width="100%" border="0" cellspacing="1" class="infoTable">
	<tr>
    	 <td style="text-align: left;padding-left: 10px;vertical-align: top;padding-top: 10px;" width="40%">
     		Address
     	 </td>
     	 <td style="text-align: left;padding-left: 5px;vertical-align: top;" width="60%">
     		<img src="/BMREG_WEB/resources/images/green_bullet.png"/><a href="addressEntryHome.action">Address Management</a>
     	 </td>
    </tr>
    <tr>
    	 <td style="text-align: left;padding-left: 10px;vertical-align: top;padding-top: 10px;">
     		User Management
     	 </td>
     	 <td style="text-align: left;padding-left: 5px;vertical-align: top;padding-top: 10px;">
     		<img src="/BMREG_WEB/resources/images/green_bullet.png"/><a href="userAdministrationHome.action">User Approval</a> <br/>
     		<img src="/BMREG_WEB/resources/images/green_bullet.png"/><a href="newUserForm.action">Create New User</a>
     	 </td>
    </tr>
    <tr>
    	 <td style="text-align: left;padding-left: 10px;vertical-align: top;padding-top: 10px;">
     		Drop down List Management
     	 </td>
     	 <td style="text-align: left;padding-left: 5px;vertical-align: top;padding-top: 10px;">
     		<img src="/BMREG_WEB/resources/images/green_bullet.png"/><a href="jobCategoryManagement.action">Job Category</a> <br/>
     		<img src="/BMREG_WEB/resources/images/green_bullet.png"/><a href="countryManagement.action">Country</a> <br/>
     		<img src="/BMREG_WEB/resources/images/green_bullet.png"/><a href="degreeNameManagement.action">Educational Qualifications</a> <br/>
     		<img src="/BMREG_WEB/resources/images/green_bullet.png"/><a href="passingYearManagement.action">Passing Year</a> <br/>
	     	<img src="/BMREG_WEB/resources/images/green_bullet.png"/><a href="languageManagement.action">Language</a>
     	 </td>
    </tr>
    <tr>
    	 <td style="text-align: left;padding-left: 10px;vertical-align: top;padding-top: 10px;">
     		RA Management
     	 </td>
     	 <td style="text-align: left;padding-left: 5px;vertical-align: top;padding-top: 10px;">
     		<img src="/BMREG_WEB/resources/images/green_bullet.png"/><a href="raEntryHome.action">Create New RA</a> <br/>
     		<img src="/BMREG_WEB/resources/images/green_bullet.png"/><a href="raListHome.action">RA List</a> <br/>
     		<img src="/BMREG_WEB/resources/images/green_bullet.png"/><a href="raSearchHome.action">Search RA</a>
     	 </td>
    </tr>
   <tr>
    	 <td style="text-align: left;padding-left: 10px;vertical-align: top;padding-top: 10px;">
     		TTC Management
     	 </td>
     	 <td style="text-align: left;padding-left: 5px;vertical-align: top;padding-top: 10px;">
     		<img src="/BMREG_WEB/resources/images/green_bullet.png"/><a href="ttcEntryHome.action">New TTC</a> <br/>
     		<img src="/BMREG_WEB/resources/images/green_bullet.png"/><a href="ttcListHome.action">TTC List</a> <br/>
     		<img src="/BMREG_WEB/resources/images/green_bullet.png"/><a href="tradeEntryHome.action">New Trade</a> <br/>
     		<img src="/BMREG_WEB/resources/images/green_bullet.png"/><a href="tradeListHome.action">Trade List</a> <br/>
     		<img src="/BMREG_WEB/resources/images/green_bullet.png"/><a href="ttcTradeMapHome.action">TTC-Trade Map</a>
     	 </td>
    </tr>
    <tr>
    	 <td style="text-align: left;padding-left: 10px;vertical-align: top;padding-top: 10px;">
     		Lottery Management
     	 </td>
     	 <td style="text-align: left;padding-left: 5px;vertical-align: top;padding-top: 10px;">
     		<img src="/BMREG_WEB/resources/images/green_bullet.png"/><a href="selectionReportSetting.action">Lottery Report Settings</a><br/>
     		<img src="/BMREG_WEB/resources/images/green_bullet.png"/><a href="selectionHome.action">Lottery Home</a><br/>
     		<img src="/BMREG_WEB/resources/images/green_bullet.png"/><a href="raLotteryHome.action">RA Lottery Home</a><br/>
     	 </td>
    </tr>
    <tr>
    	 <td style="text-align: left;padding-left: 10px;vertical-align: top;padding-top: 10px;">
     		Others
     	 </td>
     	 <td style="text-align: left;padding-left: 5px;vertical-align: top;padding-top: 10px;">
     		<img src="/BMREG_WEB/resources/images/green_bullet.png"/><a href="regCorrectionHome.action">Registration Correction</a> <br/>
     		<img src="/BMREG_WEB/resources/images/green_bullet.png"/><a href="reportHome.action">Report Home</a> <br/>
     		<img src="/BMREG_WEB/resources/images/green_bullet.png"/><a href="passwordChangeHome.action">Change Password</a> <br/>
     		<img src="/BMREG_WEB/resources/images/green_bullet.png"/><a href="logout.action">Logout</a>
     	 </td>
    </tr>
</table>
</form>    
    
  
</div>
<p style="height: 30px"></p>
</center>
<script type="text/javascript">
$('input').attr('autocomplete','off');   
</script>
<script type="text/javascript" src="/BMREG_WEB/resources/js/util/browserCloseDetect.js"></script>
</body>

</html>
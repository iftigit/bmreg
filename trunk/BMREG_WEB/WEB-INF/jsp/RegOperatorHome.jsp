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
<title>Reg. System - User Home</title>
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
     <td style="text-align: center">
     	<a href="regHomeAction.action">Registration Form</a>
     </td>
    </tr>
     <tr>
     <td style="text-align: center">
     	<a href="adminCardDownloadHome.action">Download Registration Card</a>
     </td>
    </tr>
    <!-- 
    <tr>
     <td style="text-align: center">
     	<a href="getRegisteredJobseekerList.action">Registered Jobseeker List</a>
     </td>
    </tr>
  -->
 	<tr>
     <td style="text-align: center">
     	<a href="technicalTeam.action">Contact your Technical Person</a>
     </td>
    </tr>
    
     <tr>
     <td style="text-align: center">
     	<a href="logout.action">Logout</a>
     </td>
    </tr>
    
     <tr>
     <td style="text-align: center" height="20px;">
     
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
</body>

</html>

<%@page import="org.apache.struts2.ServletActionContext"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>

<head>
<meta content="text/html;charset=utf-8" http-equiv="Content-Type">
<meta content="utf-8" http-equiv="encoding">
<title>Job Seeker Registration Form</title>
 <link rel="stylesheet" href="/BMREG_WEB/resources/css/style.css" />	

 <link type="text/css" rel="Stylesheet" href="/BMREG_WEB/resources/js/lib/jquery.validity.1.2.0/jquery.validity.css" />
  
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
<div id="Example_F" style="height: 580px;width: 1000px;margin-top: 10px;">
<div style="font-size: 25px;font-weight: bold;padding-top: 100px;color: blue;">Registration Id: <color style="color:green;">

<s:property value='registrationId' /></color></div>
<div style="font-size: 22px;font-weight: bold;margin-top: 50px;">
	Registration has successfully been received. Please click below button to download your Registration Card.
</div>
<div style="margin-top: 100px;text-align: center;">
<a href="downloadRegCard.action?registrationId=<s:property value='registrationId' />&requestType=A" style="text-decoration: none;">
<input type="button" class="submitButton" name="Downalod Card" value="Click Here to Download Registration Card" style="width: 300px;"  />
</a>

<br/><br/>
<a href="regCorrectionHome.action" style="text-decoration: none;">	
	<input type="submit" class="submitButton" name="backButton" value="Registration Correction Home" style="width: 230px;"   />
</a>

</div>
<div style=" margin-top: 50px;">
<b>Note :</b> <font style="color:red">Please Print your Registration Card and save it carefully.</font> 
</div>
</div>
</center>



<div id="footer">
&copy;BMET-2015. All right reserved.
</div>
</body>

</html>
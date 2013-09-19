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
 <script type="text/javascript">

var ajax_load="<br/><center><img src='/BMREG_WEB/resources/images/ajax-loader1.gif' border='0' /></center>";

function fetchPasswordInformation(userId)
{

if(userId=="")
{
 	jQuery("#passwordInfoDiv").html("<font color='red'>Please provide UserId.</font>");
 	return;
} 

 var loadUrl="fetchPasswordInfoByUserId.action?userId="+userId;
			jQuery("#passwordInfoDiv")  
				.html(ajax_load)  
				.load(loadUrl, {},function(responseText){  
					jQuery("#passwordInfoDiv").html(responseText);
									   
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
	 	<div style="font-size: 20px;margin-top: 10px;"></div>
	</div>
</div>
</center>
</div>
<center>
<br/>
<div class="box" style="margin-top: 20px;width: 1000px;text-align: center;">
    <h3>Password Enquire Form</h3>
    <table width="100%" border="0" cellspacing="1" class="infoTable">
    
    <tr>
     <td style="text-align: center">
     	<b>UserID :</b> <input type="text" id="userId" name="userId" onblur="fetchPasswordInformation(this.value)" maxlength="14" style="width: 250px;border: 2px solid black;height: 30px;font-size: 20px;color: maroon;text-align: center;" />
     </td>
    </tr>
     <tr>
     <td style="text-align: center">
     	
     </td>
    </tr>
    </table>
 <br/>     
<div id="passwordInfoDiv" style="width: 100%"></div>
 <br/> 
</div>
<p style="height: 20px"></p>
</center>
<script type="text/javascript">
$('input').attr('autocomplete','off');   
</script>
<br/>
<center><a href="homePage.action" style="text-decoration: none;"><< Go Home >></a></center>

</body>

</html>
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
<title></title>
 <link rel="stylesheet" href="/BMREG_WEB/resources/css/style.css" />	
  <script type="text/javascript" src="/BMREG_WEB/resources/js/lib/jquery-1.6.4.min.js"></script> 
<script type="text/javascript" src="/BMREG_WEB/resources/js/util/util.js"></script>
<script type="text/javascript" src="/BMREG_WEB/resources/js/util/numeric.js"></script>
 <script type="text/javascript" src="/BMREG_WEB/resources/js/util/util.js"></script>
 
 <link type="text/css" rel="Stylesheet" href="/BMREG_WEB/resources/js/lib/jquery.validity.1.2.0/jquery.validity.css" />
        <script type="text/javascript" src="/BMREG_WEB/resources/js/lib/jquery.validity.1.2.0/jQuery.validity.js"></script>
 
<script type="text/javascript">
var ajax_load="<br/><center><img src='/BMREG_WEB/resources/images/ajax-loader1.gif' border='0' /></center>";
function changePassword(){

    var oldPassword=$("#oldPassword").val();
    var newPassword=$("#newPassword").val();
    var confirmNewPassword=$("#confirmNewPassword").val();
    
    if(oldPassword==""){
    alert("Provide Old Password");return; }
    else if(newPassword==""){
    alert("Provide New Password");return; }
    else if(confirmNewPassword==""){
    alert("Provide confirm New Password");return; }
    else if(newPassword!=confirmNewPassword){
    alert("New Password and Confirm New Password must be equal.");return; }
    
	var loadUrl="changePassword.action?oldPassword="+oldPassword+"&newPassword="+newPassword+"&confirmNewPassword="+confirmNewPassword;
			jQuery("#msgDiv")  
				.html(ajax_load)  
				.load(loadUrl, {},function(responseText){  
					jQuery("#msgDiv").html(responseText);
					alert(responseText);
					if(responseText=="Password Successfully Updated.")
					{
					 $("#oldPassword").val("");
    				 $("#newPassword").val("");
    				 $("#confirmNewPassword").val("");    
					}
									   
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
<div class="box" style="margin-top: 100px;width: 700px;text-align: center;">
    <h3>User Password Change</h3>
    
    
    <table width="80%" border="1" cellspacing="0" cellpadding="0" class="infoTable" style="border: 1px solid grey;"  align="center" >
    <tr>
     <td style="text-align: left;padding-left: 10px;" width="50%">
     	Old Password 
     </td>
     <td style="text-align: left;padding-left: 10px;" width="50%">
     	 <input type="text" id="oldPassword" maxlength="10" style="width: 150px;border: 1px solid grey;" />
     </td>
    </tr>
    <tr>
     <td style="text-align: left;padding-left: 10px;" width="50%">
     	New Password 
     </td>
     <td style="text-align: left;padding-left: 10px;" width="50%">
     	 <input type="text" name="newPassword" id="newPassword" maxlength="10" style="width: 150px;border: 1px solid grey;" />
     </td>
    </tr>
    <tr>
     <td style="text-align: left;padding-left: 10px;" width="50%">
     	Confirm New Password 
     </td>
     <td style="text-align: left;padding-left: 10px;" width="50%">
     	 <input type="text" name="confirmNewPassword" id="confirmNewPassword" maxlength="10" style="width: 150px;border: 1px solid grey;" />
     </td>
    </tr>
    </table>
    <center>
    <input type="button" value="Change Password" onclick="changePassword()" />
    </center>
<br/>     
</div>
    <br/>
    <a href="homePage.action">Go Home</a>
    <br/>
<p style="height: 30px" id="msgDiv"></p>
</center>

</body>

</html>
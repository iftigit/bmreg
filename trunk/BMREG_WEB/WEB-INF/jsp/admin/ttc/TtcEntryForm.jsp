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
  <script type="text/javascript" src="/BMREG_WEB/resources/js/regValidation.js"></script>
  <script type="text/javascript">

var ajax_load="<br/><center><img src='/BMREG_WEB/resources/images/ajax-loader1.gif' border='0' /></center>";

function createNewTtc()
{
  var ttcName=$.trim(document.getElementById("ttcName").value);
  var ttcPrincipal=$.trim(document.getElementById("ttcPrincipal").value);
  var address=$.trim(document.getElementById("address").value);
  var phone=$.trim(document.getElementById("phone").value);
  var emailAddress=$.trim(document.getElementById("emailAddress").value);
  
  if(ttcName==""){
   	alert("Please provide TTC Name.");
   	return;
   }
  else if(ttcPrincipal==""){
   	alert("Please provide TTC Principal Name.");
   	return;
   }
   else if(address==""){
   	alert("Please provide Address.");
   	return;
   }   
   document.ttcForm.submit();
   
  
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
    <h3>TTC Entry Form</h3>
    <div style="padding-bottom: 30px;">
    <form action="createNewTtc.action" method="post" id="ttcForm" name="ttcForm">
    <table width="80%" align="center" border="0">
     	<tr>
     		<td width="30%" align="left">TTC Name</td>
     		<td width="70%" align="left"><input type="text" name="ttc.ttcName" id="ttcName" value="<s:property value='ttc.ttcName' />" style="border: 1px solid gray;width: 200px;" /></td>
        </tr>
        <tr>
     		<td align="left">Principal Name</td>
     		<td align="left"><input type="text" name="ttc.ttcPrincipal" id="ttcPrincipal" value="<s:property value='ttc.ttcPrincipal' />" style="border: 1px solid gray;width: 200px;" /></td>
        </tr>
        <tr>
     		<td align="left">Address</td>
     		<td align="left"><textarea name="ttc.address" id="address" style="border: 1px solid gray;" cols="22" ><s:property value='ttc.address' /></textarea></td>
        </tr>              
        <tr>
     		<td align="left">Phone</td>
     		<td align="left"><input type="text" name="ttc.phone" id="phone" value="<s:property value='ttc.phone' />" style="border: 1px solid gray;width: 200px;width: 200px;" /></td>
        </tr>
        <tr>
     		<td align="left">Email</td>
     		<td align="left"><input type="text" name="ttc.emailAddress" id="emailAddress" value="<s:property value='ttc.emailAddress' />" style="border: 1px solid gray;width: 200px;width: 200px;" /></td>        
        </tr>            
    </table>
<p style="padding-top: 40px;">     
<input type="button" name="save" value="Crete New TTC" style="width: 200px;height: 35px;"  onclick="createNewTtc()"/>
</p>
</form>
</div>
    
    <p style="padding-bottom: 20px;">
    <a href="systemAdminHome.action">Go Home</a>
    </p>

</div>
<p id="msgDiv"></p>
</center>
<script type="text/javascript">
<s:if test='msg != null'>
  alert("<s:property value='msg' />");  
</s:if>

</script>
</body>

</html>
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

function fetchPasswordInformation(unionId)
{

if(unionId=="select")
{
 	jQuery("#passwordInfoDiv").html("<font color='red'>Please select an Union.</font>");
 	return;
} 


 var loadUrl="fetchPasswordInfo.action?unionId="+unionId;
			jQuery("#passwordInfoDiv")  
				.html(ajax_load)  
				.load(loadUrl, {},function(responseText){  
					jQuery("#passwordInfoDiv").html(responseText);
									   
				});

 showAddressIds();				
}

function showAddressIds()
{
 jQuery("#addressIdDiv").html("<b>Division:</b> "+jQuery("#PASSWORD_DIV").val()+", <b>District:</b> "+jQuery("#PASSWORD_DIST").val()+", <b>Upz./Thana</b>: "+jQuery("#PASSWORD_THANA").val()+", <b>Union/Ward:</b> "+jQuery("#PASSWORD_UNION").val());
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
<form method="post" action="approveNewUserAndSendSms.action">
<center>
<br/>
<div class="box" style="margin-top: 50px;width: 900px;text-align: center;">
    <h3>User List Waiting for Approval</h3>
    <div style="padding-bottom: 30px;">
    Start Date : <input type="text" name="startDate" id="startDate" value="<s:property value='startDate' />" style="border: 1px solid gray;" />  End Date : <input type="text" name="endDate" id="endDate"  value="<s:property value='endDate' />" style="border: 1px solid gray;"/><br/>
     <font color="maroon"><b>Format:</b> [dd-MM-YYYY HH:MI:SS]. <br/>
     Valid Example : 11-11-2013 11:11:11 or 20-11-2013 20:40:50</font> 
    </div><br/>
    <font color="blue"><s:property value="message"/></font><br/>
    <table width="98%" align="center" border="0" cellpadding="2" cellspacing="0" style="border: 1px solid grey;">
     
     <tr bgcolor="#F1F1F1">
     
        <td width="5%" align="center" style="padding-left: 10px;" height="25"></td>
      	<td width="10%" align="left" style="padding-left: 10px;" height="25">UserId</td>
      	<td width="15%" align="left" style="padding-left: 10px;" height="25">Division</td>
      	<td width="15%" align="left" style="padding-left: 10px;" height="25">District</td>
      	<td width="15%" align="left" style="padding-left: 10px;" height="25">Upazila/Thana/City Corp./Pauroshova</td>
      	<td width="15%" align="left" style="padding-left: 10px;" height="25">Union/Ward</td>
      	<td width="10%" align="left" style="padding-left: 10px;" height="25">Requested By</td>
      	<td width="10%" align="left" style="padding-left: 10px;" height="25">Requested On</td>      	
      	<td width="5%" align="center" style="padding-left: 10px;" height="25">Delete</td>
      </tr>
      <s:iterator value="userList">
        <tr>
        <td align="left" style="padding-left: 10px;" height="25"><input type="checkbox" name="approveUserList" value="<s:property value='userId' />" /></td>
      	<td align="left" style="padding-left: 10px;" height="25"><s:property value="userId" /></td>
      	<td align="left" style="padding-left: 10px;" height="25"><s:property value="divisionName" /></td>
      	<td align="left" style="padding-left: 10px;" height="25"><s:property value="districtName" /></td>
      	<td align="left" style="padding-left: 10px;" height="25"><s:property value="upazilaName" /></td>
      	<td align="left" style="padding-left: 10px;" height="25"><s:property value="unionName" /></td>
      	<td align="left" style="padding-left: 10px;" height="25"><s:property value="requestedBy" /></td>
      	<td align="left" style="padding-left: 10px;" height="25"><s:property value="requestedOn" /></td>
      	<td align="left" style="padding-left: 10px;" height="25">Delete</td>      	
        </tr> 
      </s:iterator>
      
      
    </table>
    

<br/>     
<input type="submit" name="approve" value="Approve & Send SMS" style="width: 200px;height: 35px;" />
<br/>     
<br/>     

</div>
    <br/>
    <a href="systemAdminHome.action">Go Home</a>
    <br/>
<p style="height: 30px"></p>
</center>
</form>

</body>

</html>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="org.controller.registration.*" %>
<%@ page import="java.util.ArrayList" %>
<head>
<%
Calendar cal = Calendar.getInstance();
System.out.println("Today : " + cal.getTime());
// Subtract 15 days from the calendar
cal.add(Calendar.DATE, +30);

DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
String fromDate = df.format(new Date());
String toDate = df.format(cal.getTime());        
%>

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

function searchUser()
{
  var userId=$.trim(document.getElementById("userId").value);
  var division,district,upazila,union,userType;
  var blukSearch=false;
  
  if(userId==""){
  	division=$.trim(document.getElementById("PERMANENT_DIV").value);
  	district=$.trim(document.getElementById("PERMANENT_DIST").value);
  	upazila=$.trim(document.getElementById("PERMANENT_UPAZILLA_OR_THANA").value);
  	union=$.trim(document.getElementById("PERMANENT_UNION_OR_WARD").value);
  	userType=$.trim(document.getElementById("userType").value);
  	if(userType=="")
  	 {
  	  alert("You must select a userType.");
  	  return;
  	 }
  	blukSearch=true;   	   	 
  }
  if(blukSearch==false && userId=="")
  {
   alert("Please provide necessary data to search.");
   return false;
  }
  
   var loadUrl="searchUserForUpdate.action";
			jQuery("#userListDiv")  
				.html(ajax_load)  
				.load(loadUrl, {userId:userId,userType:userType,division:division,district:district,upazila:upazila,union:union},function(responseText){  
					jQuery("#userListDiv").html(responseText);
				});
  
}
function saveUser(userId,index)
{
    var startDate=document.getElementById("startDate"+index).value;
    var endDate=document.getElementById("endDate"+index).value;
   
   
    var loadUrl="saveUser.action";
			jQuery("#msgDiv")  
				.html(ajax_load)  
				.load(loadUrl, {userId:userId,startDate:startDate,endDate:endDate},function(responseText){  
					if(responseText=="success")
					{
					  jQuery("#msgDiv").html("<font color='green' style='font-weight:bold;'>Successfully Saved</font>"); 					  
					}
					else{
					  jQuery("#msgDiv").html("<font color='red' style='font-weight:bold;'>Error in Save Operation.</font>");
					  document.getElementById("userId").value="";
					}
					
					
									   
				});
   					
}
function saveUserAndSendSms(userId,index)
{
   var startDate=document.getElementById("startDate"+index).value;
   var endDate=document.getElementById("endDate"+index).value;
   
   
    var loadUrl="saveUserAndSendSms.action";
			jQuery("#msgDiv")  
				.html(ajax_load)  
				.load(loadUrl, {userId:userId,startDate:startDate,endDate:endDate},function(responseText){  
					if(responseText=="success")
					{
					  jQuery("#msgDiv").html("<font color='green' style='font-weight:bold;'>Successfully saved and sms send.</font>"); 					  
					}
					else{
					  jQuery("#msgDiv").html("<font color='red' style='font-weight:bold;'>Error in Update Operation.</font>");					  
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
<p id="msgDiv" style="height: 20px;"></p>
<div class="box" style="margin-top: 10px;width: 900px;text-align: center;">
    <h3>New User Entry Form</h3>    
    <div style="padding-bottom: 30px;">
    <form action="" method="post" id="raForm" name="raForm">
    <table width="95%" align="center" border="0">
        <tr>
     		<td align="left">Division</td>
     		<td align="left">
     			<select tabindex="19"  id="PERMANENT_DIV" class="addressSelectBox" onchange="fetchJSONData_Dist(this.value,'PERMANENT_DIST')">
			        <option value="" selected="selected">--Select Division--</option>
			     	<option value="10">BARISAL</option>
			     	<option value="20">CHITTAGONG</option>
			     	<option value="30">DHAKA</option>
			     	<option value="40">KHULNA</option>
			     	<option value="50">RAJSHAHI</option>
			     	<option value="55">RANGPUR</option>
			     	<option value="60">SYLHET</option>										     	
			    </select>
     		</td>
     		<td align="left">District</td>
     		<td align="left" id="PERMANENT_DIST_TD">
     			<select tabindex="20"  id="PERMANENT_DIST" class="addressSelectBox">
      				<option value=""></option>
     			</select>	
     		</td>
        </tr>
        <tr>
     		<td align="left">Upazilla/ Pourashava/&nbsp;&nbsp; City Corporation</td>
     		<td align="left" id="PERMANENT_UPAZILLA_OR_THANA_TD">
     			<select tabindex="21" name="addressDTO.pThana" id="PERMANENT_UPAZILLA_OR_THANA" class="addressSelectBox" onchange="fetchJSONData_Union(this.value,'PASSWORD_UNION')">
      				<option value=""></option>
    			</select>
     		</td>
     		<td align="left">Union/Ward</td>
     		<td align="left" id="PERMANENT_UNION_OR_WARD_TD">
     			<select tabindex="22" name="addressDTO.pUnion" id="PERMANENT_UNION_OR_WARD" class="addressSelectBox" >
      				<option value=""></option>
    			</select>	
     		</td>
        </tr>
        <tr>
     		<td align="left">User Type</td>
     		<td align="left">
     			<select tabindex="19"  id="userType" name="userType" style="border: 1px solid gray;width:250px;">
			        <option value="" selected="selected">--Select UserType--</option>
			     	<option value="DEMO_REG_ADMIN">DEMO_REG_ADMIN</option>
			     	<option value="DEMO_REG_OPERATOR">DEMO_REG_OPERATOR</option>
			     	<option value="UISC_REG_OPERATOR">UISC_REG_OPERATOR</option>
			     	<option value="A2I_OPERATOR">A2I_OPERATOR</option>
			     	<option value="OPEN_REG_OPERATOR">OPEN_REG_OPERATOR</option>
			     	<option value="SYSTEM_ADMIN">SYSTEM_ADMIN</option>
			    </select>
     		</td>
     		<td align="left"></td>
     		<td align="left">     		
     		</td>
        </tr>
        <tr>
        	<td colspan="4">
        	<hr size="1"/>        	
        	<b>OR</b>        	        
        	<hr size="1"/>
        	        	
        	</td>
        </tr>
        
             	<tr>
     		<td width="15%" align="left">User Id</td>
     		<td width="35%" align="left"><input type="text" name="userId" id="userId" value="<s:property value='userId' />" style="border: 1px solid gray;width: 245px;" /></td>
     		<td width="15%" align="left"></td>
     		<td width="35%" align="left"></td>
        </tr>

    </table>
<p style="padding-top: 15px;">     
	<input type="button" name="userSearch" value="Search User" style="width: 200px;height: 35px;"  onclick="searchUser()"/>
</p>
</form>
<div id="userListDiv"></div>
</div>
    
    <p style="padding-bottom: 20px;">
    <a href="systemAdminHome.action">Go Home</a>
    </p>

</div>

<script type="text/javascript">
/*
if(document.getElementById("startDate").value=="")
 document.getElementById("startDate").value="<%=fromDate%>";
if(document.getElementById("endDate").value=="")
 document.getElementById("endDate").value="<%=toDate%>";
 */
</script>
</center>
</body>

</html>
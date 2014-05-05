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

function createNewUser()
{
  var userId=$.trim(document.getElementById("userId").value);
  var password=$.trim(document.getElementById("password").value);
  var userType=$.trim(document.getElementById("userType").value);
  var division=$.trim(document.getElementById("PERMANENT_DIV").value);
  var district=$.trim(document.getElementById("PERMANENT_DIST").value);
  var upazila=$.trim(document.getElementById("PERMANENT_UPAZILLA_OR_THANA").value);
  var union=$.trim(document.getElementById("PERMANENT_UNION_OR_WARD").value);
  var startDate=$.trim(document.getElementById("startDate").value);
  var endDate=$.trim(document.getElementById("endDate").value);
  var userName=$.trim(document.getElementById("userName").value);
  var designation=$.trim(document.getElementById("designation").value);
  
  
  if(userId==""){
   	alert("Please provide User Id.");
   	return;
   }
  else if(password==""){
   	alert("Please provide password.");
   	return;
   }
  else if(userType==""){
   	alert("Please select user type.");
   	return;
   }

   var loadUrl="createNewUser.action";
			jQuery("#msgDiv")  
				.html(ajax_load)  
				.load(loadUrl, {userId:userId,password:password,userType:userType,division:division,district:district,upazila:upazila,union:union,startDate:startDate,endDate:endDate,userName:userName,designation:designation},function(responseText){  
					jQuery("#msgDiv").html(responseText);
					alert(responseText);
					if(responseText.indexOf("Successfully")>=0)
					{
					  /*document.getElementById("userId").value="";
					  document.getElementById("password").value="";
					  document.getElementById("startDate").value="";
					  document.getElementById("endDate").value="";
					  
					  document.getElementById("userType").value="";
					  document.getElementById("PERMANENT_DIV").value="";
					  document.getElementById("PERMANENT_DIST").value="";
					  document.getElementById("PERMANENT_UPAZILLA_OR_THANA").value="";
					  document.getElementById("PERMANENT_UNION_OR_WARD").value="";	
					  */
					  window.location="newUserForm.action";				  
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
<div class="box" style="margin-top: 30px;width: 900px;text-align: center;">
    <h3>New User Entry Form</h3>
    <div style="padding-bottom: 30px;">
    <form action="" method="post" id="raForm" name="raForm">
    <table width="80%" align="center" border="0">
     	<tr>
     		<td width="15%" align="left">User Id</td>
     		<td width="35%" align="left"><input type="text" name="userId" id="userId" value="<s:property value='userId' />" style="border: 1px solid gray;width: 245px;"/></td>
     		<td width="15%" align="left">Password</td>
     		<td width="35%" align="left"><input type="text" name="password" id="password" value="<s:property value='password' />" style="border: 1px solid gray;width: 245px;background-color: pink;" readonly="readonly" /></td>
        </tr>
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
     		<td align="left">Start Date</td>
     		<td align="left">
     			<input type="text" name="startDate" id="startDate" value="<s:property value='startDate' />" style="border: 1px solid gray;" />
     			<br/>
     			<font color="maroon">[dd-MM-YYYY HH:MI:SS].</font>
     		</td>
     		<td align="left">End Date</td>
     		<td align="left">
     			<input type="text" name="endDate" id="endDate"  value="<s:property value='endDate' />" style="border: 1px solid gray;"/>
     			<br/>
     			<font color="maroon">[dd-MM-YYYY HH:MI:SS].</font>	
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
			     	<option value="SYSTEM_ADMIN">SYSTEM_ADMIN</option>
			     	
			    </select>
     		</td>
     		<td align="left">User Name</td>
     		<td align="left">
     		<input type="text" name="userName" id="userName"  value="<s:property value='userName' />" style="border: 1px solid gray;width: 245px;"/>
     		</td>
        </tr>
        <tr>
     		<td align="left">Designation</td>
     		<td align="left">
     			<input type="text" name="designation" id="designation"  value="<s:property value='designation' />" style="border: 1px solid gray;width: 245px;"/>
     		</td>
     		<td align="left"></td>
     		<td align="left">
     		
     		</td>
        </tr>
        
    </table>
<p style="padding-top: 40px;">     
	<input type="button" name="Create New User" value="Crete New User" style="width: 200px;height: 35px;"  onclick="createNewUser()"/>
</p>
</form>
</div>
    
    <p style="padding-bottom: 20px;">
    <a href="systemAdminHome.action">Go Home</a>
    </p>

</div>
<p id="msgDiv"></p>
</center>
</body>

</html>
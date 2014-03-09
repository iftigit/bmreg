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

function updateYearInfo()
{

var fromYear=document.getElementById("fromYear").value;
var toYear=document.getElementById("toYear").value;
if(fromYear=="" || toYear=="")
{
 	jQuery("#msgDiv").html("<font color='red'>Please provide correct data.</font>");
 	return;
} 


 var loadUrl="savePassingYearInfo.action?fromYear="+fromYear+"&toYear="+toYear;
			jQuery("#msgDiv")  
				.html(ajax_load)  
				.load(loadUrl, {},function(responseText){  
					jQuery("#msgDiv").html(responseText);
									   
				});
				
}

function createNewRA()
{
  var agentId=$.trim(document.getElementById("agentId").value);
  var companyName=$.trim(document.getElementById("companyName").value);
  var address=$.trim(document.getElementById("address").value);
  var phone=$.trim(document.getElementById("phone").value);
  var licenseNumber=$.trim(document.getElementById("licenseNumber").value);
  var status=$.trim(document.getElementById("status").value);
  var companyType=$.trim(document.getElementById("companyType").value);
  var licenseDate=$.trim(document.getElementById("licenseDate").value);
  var licenseValidTill=$.trim(document.getElementById("licenseValidTill").value);
  var contactPerson=$.trim(document.getElementById("contactPerson").value);
  var designation=$.trim(document.getElementById("designation").value);
  
  if(agentId==""){
   	alert("Please provide Agent Id.");
   	return;
   }
  else if(companyName==""){
   	alert("Please provide Company Name.");
   	return;
   }
  else if(address==""){
   	alert("Please provide Address.");
   	return;
   }
   else if(phone==""){
   	alert("Please provide Phone Number.");
   	return;
   }
   else if(licenseNumber==""){
   	alert("Please provide License Number.");
   	return;
   }
   else if(status==""){
   	alert("Please provide status.");
   	return;
   }
   else if(companyType==""){
   	alert("Please provide Company Type.");
   	return;
   }
   else if(licenseDate==""){
   	alert("Please provide License Date.");
   	return;
   }
   else if(isValidDate(licenseDate)==false){
   	alert("Please provide valid license date.");
   	return;
   }
   
   else if(licenseValidTill==""){
   	alert("Please provide license end date.");
   	return;
   }
   else if(isValidDate(licenseValidTill)==false){
   	alert("Please provide valid license end date.");
   	return;
   }
   
   else if(contactPerson==""){
   	alert("Please provide Contact Person.");
   	return;
   }
   else if(designation==""){
   	alert("Please provide Designation.");
   	return;
   }
   document.raForm.submit();
   
  
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
    <h3>RA Entry Form</h3>
    <div style="padding-bottom: 30px;">
    <form action="createNewRa.action" method="post" id="raForm" name="raForm">
    <table width="80%" align="center" border="0">
     	<tr>
     		<td width="15%" align="left">Agent Id</td>
     		<td width="35%" align="left"><input type="text" name="rAgent.agentId" id="agentId" value="<s:property value='rAgent.agentId' />" style="border: 1px solid gray;width: 200px;" /></td>
     		<td width="15%" align="left">File Reference</td>
     		<td width="35%" align="left"><input type="text" name="rAgent.agentFileRef" id="agentFileRef" value="<s:property value='rAgent.agentFileRef' />" style="border: 1px solid gray;width: 200px;" /></td>
        </tr>
        <tr>
     		<td align="left">Company Name</td>
     		<td align="left"><input type="text" name="rAgent.companyName" id="companyName" value="<s:property value='rAgent.companyName' />" style="border: 1px solid gray;width: 200px;" /></td>
     		<td align="left">Address</td>
     		<td align="left"><textarea name="rAgent.address" id="address" style="border: 1px solid gray;" cols="22" ><s:property value='rAgent.address' /></textarea></td>
        </tr>
        <tr>
     		<td align="left">Phone</td>
     		<td align="left"><input type="text" name="rAgent.phone" id="phone" value="<s:property value='rAgent.phone' />" style="border: 1px solid gray;width: 200px;width: 200px;" /></td>
     		<td align="left">Email</td>
     		<td align="left"><input type="text" name="rAgent.emailAddress" id="emailAddress" value="<s:property value='rAgent.emailAddress' />" style="border: 1px solid gray;width: 200px;width: 200px;" /></td>
        </tr>
        <tr>
     		<td align="left">Fax</td>
     		<td align="left"><input type="text" name="rAgent.fax" id="fax" value="<s:property value='rAgent.fax' />" style="border: 1px solid gray;width: 200px;" /></td>
     		<td align="left">License No</td>
     		<td align="left"><input type="text" name="rAgent.licenseNumber" id="licenseNumber" value="<s:property value='rAgent.licenseNumber' />" style="border: 1px solid gray;width: 200px;" /></td>
        </tr>
        <tr>
     		<td align="left">Space</td>
     		<td align="left"><input type="text" name="rAgent.space" id="space" value="<s:property value='rAgent.space' />" style="border: 1px solid gray;width: 200px;" /></td>
     		<td align="left">Status</td>
     		<td align="left"><input type="text" name="rAgent.status" id="status" value="<s:property value='rAgent.status' />" style="border: 1px solid gray;width: 200px;" /></td>
        </tr>
        <tr>
     		<td align="left">Company Type</td>
     		<td align="left"><input type="text" name="rAgent.companyType" id="companyType" value="<s:property value='rAgent.companyType' />" style="border: 1px solid gray;width: 200px;" /></td>
     		<td align="left">License Date</td>
     		<td align="left"><input type="text" name="rAgent.licenseDate" id="licenseDate" value="<s:property value='rAgent.licenseDate' />" style="border: 1px solid gray;width: 100px;" />
     		     <font style='color:maroon;font-size: 13px;'>[DD-MM-YYYY]</font>     		
     		</td>
        </tr>
        <tr>
     		<td align="left">License Valid Up to</td>
     		<td align="left"><input type="text" name="rAgent.licenseValidTill" id="licenseValidTill" value="<s:property value='rAgent.licenseValidTill' />" style="border: 1px solid gray;width: 100px;" />
     		     <font style='color:maroon;font-size: 13px;'>[DD-MM-YYYY]</font>     		
     		</td>
     		<td align="left">Ministry Ref.</td>
     		<td align="left"><input type="text" name="rAgent.ministryRef" id="ministryRef" value="<s:property value='rAgent.ministryRef' />" style="border: 1px solid gray;width: 200px;" /></td>
        </tr>
         <tr>
     		<td align="left">Contact Person</td>
     		<td align="left"><input type="text" name="rAgent.contactPerson" id="contactPerson" value="<s:property value='rAgent.contactPerson' />" style="border: 1px solid gray;width: 200px;" /></td>
     		<td align="left">Designation</td>
     		<td align="left"><input type="text" name="rAgent.designation" id="designation" value="<s:property value='rAgent.designation' />" style="border: 1px solid gray;width: 200px;" /></td>
        </tr>
         <tr>
     		<td align="left">Comment</td>
     		<td align="left"><input type="text" name="rAgent.comments" id="comments" value="<s:property value='rAgent.comments' />" style="border: 1px solid gray;width: 200px;" /></td>
     		<td align="left">Branch</td>
     		<td align="left"><input type="text" name="rAgent.branch" id="branch" value="<s:property value='rAgent.branch' />" style="border: 1px solid gray;width: 200px;" /></td>
        </tr>
         <tr>
     		<td align="left">Training Center</td>
     		<td align="left"><input type="text" name="rAgent.trainingCenter" id="trainingCenter" value="<s:property value='rAgent.trainingCenter' />" style="border: 1px solid gray;width: 200px;" /></td>
     		<td align="left"></td>
     		<td align="left"></td>
        </tr>
         <tr>
     		<td align="left">CEO Name</td>
     		<td align="left"><input type="text" name="rAgent.ceoName" id="ceoName" value="<s:property value='rAgent.ceoName' />" style="border: 1px solid gray;width: 200px;" /></td>
     		<td align="left">CEO Contact Number</td>
     		<td align="left"><input type="text" name="rAgent.ceoContactInfo" id="ceoContactInfo"  value="<s:property value='rAgent.ceoContactInfo' />" style="border: 1px solid gray;width: 200px;" /></td>
        </tr>
    </table>
<p style="padding-top: 40px;">     
<input type="button" name="save" value="Crete New RA" style="width: 200px;height: 35px;"  onclick="createNewRA()"/>
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
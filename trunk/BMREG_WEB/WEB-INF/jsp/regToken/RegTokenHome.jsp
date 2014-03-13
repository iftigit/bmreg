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

function createRegToken()
{
/*
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
   */
   document.tokenForm.submit();
  
}

function calculateTotal()
{
 var totalAmount=document.getElementById("totalAmount").value;
 var regFee=document.getElementById("regFee").value;
 
 var totalToken=parseInt(totalAmount/regFee,10);
 document.getElementById("totalToken").value=totalToken;
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
    <h3>Registration Token Generation</h3>
    <div style="padding-bottom: 30px;">
    <form action="saveRegToken.action" method="post" id="tokenForm" name="tokenForm">
    <table width="80%" align="center" border="0">
     	<tr>
     		<td width="15%" align="left">Select User</td>
     		<td width="35%" align="left">
     		
     			<select name="regToken.assignedTo" id="assignedTo" style="width:200px;border:1px solid grey;" >
     				<s:iterator value="userList" status="status">
     					<option value='<s:property value="userId" />'><s:property value="userId" /></option>
     				</s:iterator>	 
     			</select>
     		
     		</td>
     		<td width="15%" align="left"></td>
     		<td width="35%" align="left"></td>
        </tr>
        <tr>
     		<td align="left">Bank/Payorder Number</td>
     		<td align="left"><input type="text" name="regToken.payOrderNumber" id="payOrderNumber" value="<s:property value='regToken.payOrderNumber' />" style="border: 1px solid gray;width: 200px;" /></td>
     		<td align="left">Bank/Payorder Date</td>
     		<td align="left"><input type="text" name="regToken.payOrderDate" id="payOrderDate" value="<s:property value='regToken.payOrderDate' />" style="border: 1px solid gray;width: 200px;" /></td>
        </tr>
        <tr>
     		<td align="left">Bank Name</td>
     		<td align="left"><input type="text" name="regToken.payOrderBank" id="payOrderBank" value="<s:property value='regToken.payOrderBank' />" style="border: 1px solid gray;width: 200px;width: 200px;" /></td>
     		<td align="left">Branch Name</td>
     		<td align="left"><input type="text" name="regToken.payOrderBranch" id="payOrderBranch" value="<s:property value='regToken.payOrderBranch' />" style="border: 1px solid gray;width: 200px;width: 200px;" /></td>
        </tr>
        <tr>
     		<td align="left">Total Amount</td>
     		<td align="left"><input type="text" name="regToken.totalAmount" id="totalAmount" value="<s:property value='regToken.totalAmount' />" style="border: 1px solid gray;width: 200px;" maxlength="5" onkeyup="calculateTotal()" /></td>
     		<td align="left">Registration Fee</td>
     		<td align="left"><input type="text" name="regToken.regFee" id="regFee" value="<s:property value='regFee' />" style="border: 1px solid gray;width: 200px;background-color: pink;" readonly="readonly" /></td>
        </tr>
        <tr>
     		<td align="left">Total Token</td>
     		
     		<td align="left"><input type="text" name="regToken.totalToken" id="totalToken" value="<s:property value='token.totalToken' />" style="border: 1px solid gray;width: 200px;" readonly="readonly" /></td>
     		<td align="left"></td>
     		<td align="left"></td>
        </tr>
    </table>
<p style="padding-top: 40px;">     
<input type="button" name="save" value="Crete New Token" style="width: 200px;height: 35px;"  onclick="createRegToken()"/>
</p>
</form>
</div>
    
    <p style="padding-bottom: 20px;">
    <a href="homePage.action">Go Home</a>
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
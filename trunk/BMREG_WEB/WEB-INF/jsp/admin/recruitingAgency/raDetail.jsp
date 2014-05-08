<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="org.controller.registration.*" %>
<%@ page import="java.util.ArrayList" %>
<script type="text/javascript" src="/BMREG_WEB/resources/js/lib/jquery-1.6.4.min.js"></script> 
<script type="text/javascript" src="/BMREG_WEB/resources/js/util/util.js"></script>
<script type="text/javascript" src="/BMREG_WEB/resources/js/util/numeric.js"></script>
 
 <link type="text/css" rel="Stylesheet" href="/BMREG_WEB/resources/js/lib/jquery.validity.1.2.0/jquery.validity.css" />
        <script type="text/javascript" src="/BMREG_WEB/resources/js/lib/jquery.validity.1.2.0/jQuery.validity.js"></script>
 
  <script type="text/javascript" src="/BMREG_WEB/resources/js/address.js"></script>
  <script type="text/javascript" src="/BMREG_WEB/resources/js/regValidation.js"></script>
<script type="text/javascript">
var ajax_load="<br/><center><img src='/BMREG_WEB/resources/images/ajax-loader1.gif' border='0' /></center>";

function updateRA()
{
 var agentFileRef=$.trim(document.getElementById("agentFileRef").value);
  var companyName=$.trim(document.getElementById("companyName").value);
  var address=$.trim(document.getElementById("address").value);
  var phone=$.trim(document.getElementById("phone").value);  
  var emailAddress=$.trim(document.getElementById("emailAddress").value);
  var licenseNumber=$.trim(document.getElementById("licenseNumber").value);  
  var fax=$.trim(document.getElementById("fax").value);
  var space=$.trim(document.getElementById("space").value);  
  var status=$.trim(document.getElementById("status").value);
  var statusComments=$.trim(document.getElementById("statusComments").value);  
  var pastStatus=$.trim(document.getElementById("pastStatus").value);
  var companyType=$.trim(document.getElementById("companyType").value);
  var licenseDate=$.trim(document.getElementById("licenseDate").value);
  var licenseValidTill=$.trim(document.getElementById("licenseValidTill").value);
  var contactPerson=$.trim(document.getElementById("contactPerson").value);
  var designation=$.trim(document.getElementById("designation").value);  
  var ministryRef=$.trim(document.getElementById("ministryRef").value);
  var comments=$.trim(document.getElementById("comments").value);
  var branch=$.trim(document.getElementById("branch").value);
  var trainingCenter=$.trim(document.getElementById("trainingCenter").value);
  var ceoName=$.trim(document.getElementById("ceoName").value);
  var ceoContactInfo=$.trim(document.getElementById("ceoContactInfo").value);  
  
  
  if(companyName==""){
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
   
   
  
    var loadUrl="updateRa.action";
			jQuery("#msgDiv")  
				.html(ajax_load)  
				.load(loadUrl, {agentFileRef:agentFileRef,companyName:companyName,address:address,phone:phone,emailAddress:emailAddress,licenseNumber:licenseNumber,
  fax:fax,space:space,status:status,statusComments:statusComments,pastStatus:pastStatus,companyType:companyType,licenseDate:licenseDate,
  licenseValidTill:licenseValidTill,contactPerson:contactPerson,designation:designation,ministryRef:ministryRef,comments:comments,
  branch:branch,trainingCenter:trainingCenter,ceoName:ceoName,ceoContactInfo:ceoContactInfo
  },function(responseText){  
					
					if(responseText=="Successfully Update RA Information.")
							jQuery("#singleAgencyDiv").html(responseText);
					else
					   		jQuery("#msgDiv").html(responseText);
									   
				});
   
   

}
</script>


<center>
<div class="box" style="margin-top: 30px;width: 880px;text-align: center;" id="">
    <h3>RA UPdate Form</h3>
    <div style="padding-bottom: 30px;">
    <form action="" method="post" id="raForm" name="raForm">
    <table width="80%" align="center" border="0">
     	<tr>
     		<td width="15%" align="left">File Reference</td>
     		<td width="35%" align="left"><input type="text" name="rAgent.agentFileRef" id="agentFileRef" value="<s:property value='rAgent.agentFileRef' />" style="border: 1px solid gray;width: 200px;" /></td>
     		<td width="15%" align="left"></td>
     		<td width="35%" align="left"></td>
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
     		<td align="left">
     			<input type="hidden" name="rAgent.licenseNumber" id="licenseNumber" value="<s:property value='rAgent.licenseNumber' />" style="border: 1px solid gray;width: 200px;" />
     			<font style="color: blue;font-weight: bold;"><s:property value='rAgent.licenseNumber' /></font>
     		</td>
        </tr>
        <tr>
     		<td align="left">Space</td>
     		<td align="left"><input type="text" name="rAgent.space" id="space" value="<s:property value='rAgent.space' />" style="border: 1px solid gray;width: 200px;" /></td>
     		<td align="left">Status</td>
     		<td align="left">
     			<select name="rAgent.status" id="status" style="border: 1px solid gray;width: 200px;">
     				<option  value="none">Select Status</option>
     				<option  value="j"  <s:if test='%{rAgent.status=="j"}'>selected="selected"</s:if>>j</option>
     				<option  value="SA" <s:if test='%{rAgent.status=="SA"}'>selected="selected"</s:if>>SA</option>
     				<option  value="L"  <s:if test='%{rAgent.status=="L"}'>selected="selected"</s:if>>L</option>
     				<option  value="S"  <s:if test='%{rAgent.status=="S"}'>selected="selected"</s:if>>S</option>
     			</select>     			
     			<input type="hidden" name="rAgent.pastStatus" id="pastStatus" value="<s:property value='rAgent.status' />"/>
     		</td>
        </tr>
        <tr>
        	<td align="left" valign="top">Status Change Reason</td>
        	<td align="left" valign="top">
        		<textarea id="statusComments" name="rAgent.statusComments" rows="4" cols="25" style="border: 1px solid grey;"></textarea>
        	</td>
        	<td></td>
        	<td></td>
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
<input type="button" name="save" value="Update RA Info" style="width: 200px;height: 35px;"  onclick="updateRA()"/>
</p>
<div id="msgDiv"></div>
</form>
</div>
</div>
</center>
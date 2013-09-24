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
<script type="text/javascript" src="/BMREG_WEB/resources/js/regValidation.js"></script>

 
 <link type="text/css" rel="Stylesheet" href="/BMREG_WEB/resources/js/lib/jquery.validity.1.2.0/jquery.validity.css" />
        <script type="text/javascript" src="/BMREG_WEB/resources/js/lib/jquery.validity.1.2.0/jQuery.validity.js"></script>
 
  <script type="text/javascript" src="/BMREG_WEB/resources/js/address.js"></script>
  
  <script type="text/javascript">

var ajax_load="<br/><center><img src='/BMREG_WEB/resources/images/ajax-loader1.gif' border='0' /></center>";

function saveNewUser()
{

    var userId=document.getElementById("userId").value;
    var divisionId=document.getElementById("PERMANENT_DIV").value;
    var districtId=document.getElementById("PERMANENT_DIST").value;
    var upazilaId=document.getElementById("PERMANENT_UPAZILLA_OR_THANA").value;
    var unionId=document.getElementById("PERMANENT_UNION_OR_WARD").value;
    
    if(validateMobileNumber(userId)==false) {
    alert("Please provide correct mobile number."); return false;
    }
    else if(divisionId==""){
    alert("Please select a Division."); return false;
    }
    else if(districtId==""){
    alert("Please select a District."); return false;
    }
    else if(upazilaId==""){
    alert("Please select a Upazila/Thana/City corp./Pauroshova."); return false;
    }
    else if(unionId==""){
    alert("Please select a Union/Ward."); return false;
    }
    
    var loadUrl="newUserRequest.action"
			jQuery("#waitingDiv")  
				.html(ajax_load)  
				.load(loadUrl, {userId:userId,divisionId:divisionId,districtId:districtId,upazilaId:upazilaId,unionId:unionId},function(responseText){  
					jQuery("#waitingDiv").html(responseText);
									   
				});
				
								
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
<center>
<br/>
<div class="box" style="margin-top: 100px;width: 700px;text-align: center;">
    <h3>User Administration</h3>
    
    <table width="80%" align="center" border="0" cellpadding="2" cellspacing="0" style="border: 1px solid grey;">
    
      <tr bgcolor="#F1F1F1">
      	<td width="50%" align="left" style="padding-left: 10px;" height="25">Mobile Number</td>
      	<td width="50%" align="left" style="padding-left: 10px;">
      			<input type="text" name="userId" id="userId" style="width: 246px;border: 1px solid grey;" maxlength="15" />
      	</td>
      </tr>      
      
      
      <tr bgcolor="#F1F1F1">
      	<td width="50%" align="left" style="padding-left: 10px;" height="25">User Type</td>
      	<td width="50%" align="left" style="padding-left: 10px;">
      			<select tabindex="19"  id="userType" class="addressSelectBox" >
			        <option value="UISC_REG_OPERATOR" selected="selected">UISC_REG_OPERATOR</option>			     	
			    </select>
      	</td>
      </tr>
      
      
      <tr>
      	<td width="50%" align="left" style="padding-left: 10px;" height="25">Division</td>
      	<td width="50%" align="left" style="padding-left: 10px;">
      			<select tabindex="19"  name="addressDTO.pDivision" id="PERMANENT_DIV" class="addressSelectBox" onchange="fetchJSONData_Dist(this.value,'PERMANENT_DIST')">
			        <option value="select" selected="selected">--Select Division--</option>
			     	<s:iterator value="%{#application.ALL_DIVISION}" id="divisionList">
			     	  <option value="<s:property value="divisionId" />"><s:property value="divisionName" /></option>
			     	</s:iterator>
			    </select>
      	</td>
      </tr>
      
      <tr bgcolor="#F1F1F1">
      	<td width="50%" align="left" style="padding-left: 10px;" height="25">District</td>
      	<td width="50%" align="left" style="padding-left: 10px;" id="PERMANENT_DIST_TD">
      			<select name="pAddress.districtId" id="PERMANENT_DIST" class="addressSelectBox">
     				<option value=""></option>	
     			</select>
      	</td>
      </tr>
      
      <tr>
      	<td width="50%" align="left" style="padding-left: 10px;" height="25">Upazilla/ Pourashava/&nbsp;&nbsp; City Corporation</td>
      	<td width="50%" align="left" style="padding-left: 10px;" id="PERMANENT_UPAZILLA_OR_THANA_TD">
      			<select tabindex="503" name="pAddress.upazillaOrThanaId" id="PERMANENT_UPAZILLA_OR_THANA" class="addressSelectBox">
        			<option value=""></option>
        		</select>
      	</td>
      </tr>
      
      <tr bgcolor="#F1F1F1">
      	<td width="50%" align="left" style="padding-left: 10px;" height="25">Union</td>
      	<td width="50%" align="left" style="padding-left: 10px;" id="PERMANENT_UNION_OR_WARD_TD">
      			<select tabindex="504" name="pAddress.unionOrWardId" id="PERMANENT_UNION_OR_WARD" class="addressSelectBox" >
       				<option value=""></option>
        		</select>
      	</td>
      </tr>
      
      <tr>
      	<td width="50%" align="left" style="padding-left: 10px;"></td>
      	<td width="50%" align="left" style="padding-left: 10px;">
      			<input type="button" name="Submit" value="Submit" onclick="saveNewUser()" />
      	</td>
      </tr>
      
      
      
    </table>
    

<br/>     

<div  id="waitingDiv" style="text-align: center;"></div>
<br/>
</div>
    <br/>
    <a href="homePage.action">Go Home</a>
    <br/>
<p style="height: 30px"></p>
</center>

</body>

</html>
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
<center>
<br/>
<div class="box" style="margin-top: 100px;width: 700px;text-align: center;">
    <h3>Password Enquire</h3>
    
    <table width="80%" align="center" border="0">
      <tr>
      	<td width="50%" align="left" style="padding-left: 10px;">Division</td>
      	<td width="50%" align="left" style="padding-left: 10px;">
      			<select tabindex="19"  name="addressDTO.pDivision" id="PASSWORD_DIV" class="addressSelectBox" onchange="fetchJSONData_Dist(this.value,'PASSWORD_DIST')">
			        <option value="select" selected="selected">--Select Division--</option>
			     	<s:iterator value="%{#application.ALL_DIVISION}" id="divisionList">
			     	  <option value="<s:property value="divisionId" />"><s:property value="divisionName" /></option>
			     	</s:iterator>
			    </select>
      	</td>
      </tr>
      
      <tr>
      	<td width="50%" align="left" style="padding-left: 10px;">District</td>
      	<td width="50%" align="left" style="padding-left: 10px;" id="PASSWORD_DIST_TD">
      			<select tabindex="20"  name="addressDTO.pDistrict" id="PASSWORD_DIST" class="addressSelectBox">
     			</select>
      	</td>
      </tr>
      
      <tr>
      	<td width="50%" align="left" style="padding-left: 10px;">Upazilla/ Pourashava/&nbsp;&nbsp; City Corporation</td>
      	<td width="50%" align="left" style="padding-left: 10px;" id="PASSWORD_THANA_TD">
      			<select tabindex="21" name="addressDTO.pThana" id="PASSWORD_THANA" class="addressSelectBox" onchange="fetchJSONData_Union(this.value,'PASSWORD_UNION')">
    			</select>
      	</td>
      </tr>
      
      <tr>
      	<td width="50%" align="left" style="padding-left: 10px;">Union</td>
      	<td width="50%" align="left" style="padding-left: 10px;" id="PASSWORD_UNION_TD">
      			<select tabindex="22" name="addressDTO.pUnion" id="PASSWORD_UNION" class="addressSelectBox" >
    			</select>
      	</td>
      </tr>
    </table>
    

<br/>     
<div id="passwordInfoDiv" style="width: 100%"></div>
<br/>
<div  id="addressIdDiv" style="text-align: center;"></div>
<br/>
</div>
    <br/>
    <a href="a2iOperatorHome.action">Go Home</a>
    <br/>
<p style="height: 30px"></p>
</center>

</body>

</html>
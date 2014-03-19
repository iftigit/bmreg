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
 <script type="text/javascript" src="/BMREG_WEB/resources/js/util/browserDetect.js"></script>
 <script type="text/javascript" src="/BMREG_WEB/resources/js/util/numeric.js"></script> 
 <script type="text/javascript" src="/BMREG_WEB/resources/js/util/xmlextras.js"></script>
 <link rel="stylesheet" type="text/css" href="/BMREG_WEB/resources/jqueryUi/ui-lightness/jquery-ui.css" />
 <script type="text/javascript" src="/BMREG_WEB/resources/jqueryUi/jquery-ui.min.js"></script> 
 <link href="/BMREG_WEB/resources/multiselect/jquery.multiselect.css" rel="stylesheet" type="text/css">
 <script type="text/javascript" src="/BMREG_WEB/resources/multiselect/jquery.multiselect.js"></script>
 <script type="text/javascript" src="/BMREG_WEB/resources/js/registration.js"></script>
 <script type="text/javascript">

var ajax_load="<br/><center><img src='/BMREG_WEB/resources/images/ajax-loader1.gif' border='0' /></center>";

function searchSelections()
{

var agentId=document.getElementById("agentId").value;
var workOrder=document.getElementById("workOrder").value;
if(agentId=="none")
{
 	alert("Please select an agent.");
 	return;
} 


 var loadUrl="searchSelection.action?agentId="+agentId+"&workOrder="+workOrder;
			jQuery("#searchResult")  
				.html(ajax_load)  
				.load(loadUrl, {},function(responseText){  
					jQuery("#searchResult").html(responseText);
									   
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


<div class="box" style="margin-top: 30px;width: 950px;text-align: center;">
    <h3>RA Selection Home</h3>
    <div style="padding-bottom: 30px;">

    <table width="100%" align="center" border="0" style="padding-left: 10px;">
     	<tr>
     		<td width="20%" align="left">Recruiting Agency</td>
     		<td width="80%" align="left">
     		<select style="width:300px;border:1px solid grey" name="selection.agentId" id="agentId">
     		    <option value='none'>Select Agency</option>
     		<s:iterator value="agentList" status="status">
     			<option value='<s:property value="licenseNumber" />'><s:property value="companyName" /></option>
     		</s:iterator>
     		</select>
        </tr>
        <tr>
     		<td align="left">Work Order</td>
     		<td align="left"><input type="text" name="selection.workOrder" id="workOrder" value="" style="border: 1px solid gray;width: 300px;" /></td>
        </tr>        
        
    </table>
    
	<p style="padding-top: 40px;">     
	<input type="button" name="search" value="Search" style="width: 150px;height: 35px;"  onclick="searchSelections()"/>
	</p>
	
</div>
<div id="searchResult"></div>
    
    <p style="padding-bottom: 20px;">
    	<a href="systemAdminHome.action">Go Home</a>
    </p>

</div>
<p id="msgDiv"></p>
</center>
<script type="text/javascript">
$(document).ready(function(){
	
});
 
</script>
</body>

</html>
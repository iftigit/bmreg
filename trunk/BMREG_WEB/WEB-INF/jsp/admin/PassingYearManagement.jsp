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
<div class="box" style="margin-top: 50px;width: 900px;text-align: center;">
    <h3>Passing Year Management</h3>
    <div style="padding-bottom: 30px;">
    From Year : <input type="text" name="fromYear" id="fromYear" value="<s:property value='fromYear' />" style="border: 1px solid gray;"  maxlength="4" />
    &nbsp;&nbsp;&nbsp;&nbsp;
    To Year : <input type="text" name="toYear" id="toYear"  value="<s:property value='toYear' />" style="border: 1px solid gray;" maxlength="4"/><br/>

<p style="padding-top: 40px;">     
<input type="button" name="save" value="Save Changes" style="width: 200px;height: 35px;"  onclick="updateYearInfo()"/>
</p>
</div>
    
    <p style="padding-bottom: 20px;">
    <a href="systemAdminHome.action">Go Home</a>
    </p>

</div>
<p id="msgDiv"></p>
</center>

</body>

</html>
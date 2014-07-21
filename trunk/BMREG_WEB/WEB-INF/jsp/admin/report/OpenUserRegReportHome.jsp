
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

function fetchReport()
{

var fromDate=document.getElementById("fromDate").value;
var toDate=document.getElementById("toDate").value;
var userId=document.getElementById("userId").value;

if(fromDate=="" || toDate=="")
{
 	jQuery("#msgDiv").html("<font color='red'>Please provide both From and To Date.</font>");
 	return;
} 
else if(userId=="")
{
    jQuery("#msgDiv").html("<font color='red'>Please select a valid User.</font>");
 	return;
}

document.openReport.submit();
				
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
<%
Calendar cal = Calendar.getInstance();
System.out.println("Today : " + cal.getTime());
// Subtract 15 days from the calendar
cal.add(Calendar.DATE, -15);

DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
String toDate = df.format(new Date());
String fromDate = df.format(cal.getTime());        
%>
<div class="box" style="margin-top: 30px;width: 900px;text-align: center;">
    <h3>DEMO User Registration Report</h3>
    <div style="padding-bottom: 30px;">
    <form action="openUserRegReport.action" method="post" id="openReport" name="openReport">
    <table width="80%" align="center" border="0">
     	<tr>
     		<td width="25%" align="left">From Date</td>
     		<td width="75%" align="left"><input type="text" name="fromDate" id="fromDate" value="<%=fromDate%>" style="border: 1px solid gray;width: 150px;" maxlength="10" />
     		<font style="font-size: 12px;color: maroon;">[DD-MM-YYYY]</font>
     		</td>
        </tr>
        <tr>
     		<td align="left">To Date</td>
     		<td align="left"><input type="text" name="toDate" id="toDate" value="<%=toDate%>" style="border: 1px solid gray;width: 150px;" maxlength="10" />
     		<font style="font-size: 12px;color: maroon;">[DD-MM-YYYY]</font>
     		</td>
        </tr>
        <tr>
     		<td align="left">User</td>
     		<td align="left">
     			<select style="border:1px solid grey;width:300px;" name="userId" id="userId">
     			 <option value="">Select User</option>     			 
     			 <s:iterator value="userList">
     			  <option value="<s:property value="userId" />"><s:property value="userName" /></option>
     			 </s:iterator>
     			 <option value="all">All User</option>
     			</select>
     		</td>
        </tr>
    </table>

<p style="padding-top: 40px;">     
<input type="button" name="generateReport" value="Generate Report" style="width: 200px;height: 35px;"  onclick="fetchReport()"/>
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


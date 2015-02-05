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
<script type="text/javascript">

var ajax_load="<br/><center><img src='/BMREG_WEB/resources/images/load.gif' border='0' /></center>";

function sendAck(divId,tmpRegId)
{

 var loadUrl="ackTmpRegId.action?tmpRegId="+tmpRegId;
			jQuery("#"+divId)  
				.html(ajax_load)  
				.load(loadUrl, {},function(responseText){  
					if(responseText=="Success"){
					 alert("Successfully Acknowledged.");location.reload();
					 }
					else
					 alert("Acknowledgement Failed. Please contact with Teletalk Contact Person.");
									   
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
<form method="post" action="approveNewUserAndSendSms.action">
<center>
<br/>
<div class="box" style="margin-top: 50px;width: 900px;text-align: center;">
    <h3>Non-Ack Registration List</h3>
    <table width="98%" align="center" border="0" cellpadding="2" cellspacing="0" style="border: 1px solid grey;">
     
     <tr bgcolor="#F1F1F1">
      	<td width="20%" align="left" style="padding-left: 10px;" height="25">Tmp-Reg. Id</td>
      	<td width="20%" align="left" style="padding-left: 10px;" height="25">Full Name</td>
      	<td width="20%" align="left" style="padding-left: 10px;" height="25">Father Name</td>
      	<td width="20%" align="left" style="padding-left: 10px;" height="25">Reg Date</td>
      	<td width="20%" align="left" style="padding-left: 10px;" height="25">Ack.</td>
      </tr>
      <s:iterator value="nonAckList" status="ind">
        <tr>
      	<td align="left" style="padding-left: 10px;" height="25"><s:property value="tmpRegId" /></td>
      	<td align="left" style="padding-left: 10px;" height="25"><s:property value="empGivenName" /> <s:property value="empLastName" /></td>
      	<td align="left" style="padding-left: 10px;" height="25"><s:property value="empFatherName" /></td>
      	<td align="left" style="padding-left: 10px;" height="25"><s:property value="regDateTime" /></td>
      	<td align="left" style="padding-left: 10px;" height="25">
      	<a href="#" onclick="sendAck('w<s:property value='#ind.index' />','<s:property value='tmpRegId' />')">Acknowledge</a>
      	<span id="w<s:property value='#ind.index' />"></span>
      	
       </tr> 
      </s:iterator>
      
      
    </table>
    


</div>
    <br/>
    <a href="#" onclick="window.history.back()">Go Back</a>
    <br/>
<p style="height: 30px"></p>
</center>
</form>

</body>

</html>
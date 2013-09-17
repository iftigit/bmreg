
<%@page import="org.apache.struts2.ServletActionContext"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>

<head>
<meta content="text/html;charset=utf-8" http-equiv="Content-Type">
<meta content="utf-8" http-equiv="encoding">
<title>G2G Quota Management</title>
 <link rel="stylesheet" href="/BMREG_WEB/resources/css/style.css" />	
<script type="text/javascript" src="/BMREG_WEB/resources/js/util/util.js"></script>
  
  
<script type="text/javascript">
	
function setcota()
{
	var rowcount=parseInt(document.getElementById("rowcount").value );
	var totalapplicant=parseInt(document.getElementById("totalapplicant").value);

	for(var index=0;index<rowcount;index++)
	{
		var discota = parseFloat(document.getElementById("distcota["+index+"].discota").value);
		
		var total = parseInt(Math.round(discota*totalapplicant/100));
		
		var str="<input dojotype='dijit.form.TextBox' name=\"distcota["+index+"].total\" value='"+total+"' style='width: 45%;text-align: center' />"
		
		//alert(discota);
		try{
	   	$find("tdTotal"+index).innerHTML = str;
	   	}
	   	catch(e){
	   	}	
			
	}
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
	 	<div style="font-size: 20px;margin-top: 10px;" align="center">Quota Management</div>
	</div>
</div>
</center>
</div>
<br>

<form id="myForm" method="post" action="cotaUpdate.action">
	<table border="0" width="50%" align="center">
		<tr>
			<th width="60%">
				Total Number of Applicant
			</th>
			<td width="40%">
				<input 
			type="text" name="totalapplicant" id="totalapplicant" 
			value="<s:property value="totalapplicant" />" style="width: 45%;text-align: center;border: 1px solid grey;" 
			onKeyUp="setcota()"/>
			
					
			<input type="hidden" name="projectid"
						value="<s:property value="projectid" />" />
			</td>
		</tr>
	</table>
	<br>
	<table border="1" width="60%" cellpadding="0" cellspacing="0"
		style="border-collapse: collapse" bordercolor="#888888" align="center">
		
		<tr>
			<td align="center" width="30%" height="35px" style="font-size: 14px">
				<b>District Name</b>
			</td>
			<td align="center" width="20%" style="font-size: 14px">
				<b>District Quota(%)</b>
			</td>
			<td align="center" width="20%" style="font-size: 14px">
				<b>Total</b>
			</td>
			
		</tr>
		 <%int i=0; %>
		<s:iterator value="cotaLst" status="idx">
		
		<tr>
			<td style="padding-left: 10px;">
				<s:property value="distname" />
				<input type="hidden" name="distcota[<%=i%>].distid"
						value="<s:property value="distid" />" />
				
			</td>
			
			<td align="center" height="27">
				<input 
			type="text" name="distcota[<%=i%>].discota"  id="distcota[<%=i%>].discota" 
			value="<s:property value="discota"  />" style="width: 45%;text-align: center;border: 1px solid grey;" />
			
			</td>
			
			<td align="center" id="tdTotal<%=i%>">
				<input 
			type="text" name="distcota[<%=i%>].total" id="distcota[<%=i%>].total" 
			value="<s:property value="total" />" style="width: 45%;text-align: center;border: 1px solid grey;" />
			
			</td>
		</tr>
		<%i++; %>
		</s:iterator>
		<input type="hidden" id="rowcount" value="<%=i %>">
	</table>
	
	<br>
	<table border="0" width="52%" align="center">
		<tr>
			<td width="60%">
             
            <input type="button" value="Back" />
             
             
			</td>
			<td width="40%" align="right">
			<!-- <button dojoType="dijit.form.Button" type="Submit"
				name="Submit" value="Update" >
				Update
			</button> 
				
	<input type="button" class="submitButton" name="Submit Application" value="Submit" style="margin-left: 100px;" onclick='window.location="cotaupdate.action"'/>
				
-->
<input type="Submit" value="submit" /> 
			</td>
		</tr>
	</table>
	<br>
	<br>
	
	
</form>


<div id="footer">
&copy;BMET-2012. All right reserved.
</div>
</body>

</html>
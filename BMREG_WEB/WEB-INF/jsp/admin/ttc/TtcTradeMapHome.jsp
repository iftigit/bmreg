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

function fetchTradeList(ttcId)
{

if(ttcId==""){
 alert("Please Select a TTC from the List");
 return;
}

 var loadUrl="fetchTradeListForTTC.action?ttcId="+ttcId;
			jQuery("#ttcTradeMapDiv")  
				.html(ajax_load)  
				.load(loadUrl, {},function(responseText){  
					jQuery("#ttcTradeMapDiv").html(responseText);
									   
				});
				
}
function updateMapping()
{

 var totalTrade=document.getElementById("totalTrade").value;
 var ttcId=document.getElementById("ttcId").value;

 var tradeListStr="";
 for(var i=0;i<totalTrade;i++)
 { 
    if(document.getElementById("isActive"+i).checked==true) 
  		tradeListStr+=document.getElementById("trade"+i).value+"#";
 }
 alert(tradeListStr);
 var loadUrl="updateTradeListForTTC.action";
			jQuery("#msgDiv")  
				.html(ajax_load)  
				.load(loadUrl, {tradeListStr:tradeListStr,ttcId:ttcId},function(responseText){  
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
<form method="post" action="">
<center>
<br/>
<div class="box" style="margin-top: 50px;width: 900px;text-align: center;">
    <h3>TTC-Trade Map Home</h3>

    <table width="80%" align="center" border="0">
        <tr>
     		<td align="left">TTC</td>
     		<td align="left">
     			<select style="border:1px solid grey; width:300px;" onchange="fetchTradeList(this.value)" id="ttcId" >
     			  <option value="">None</option>
     			  <s:iterator value="ttcList" status="status" > 
     			  	<option value="<s:property value='ttcId' />"><s:property value='ttcName' /></option>
     			  </s:iterator>
     			</select>
     		</td>
        </tr>
    </table>

<div id="ttcTradeMapDiv"></div>
<div id="msgDiv"></div>
<p style="padding-top: 40px;">     
	<input type="button" name="save" value="Update Mapping" style="width: 200px;height: 35px;"  onclick="updateMapping()"/>
</p>

</div>
    <br/>
    <a href="systemAdminHome.action">Go Home</a>
    <br/>
<p style="height: 30px"></p>
</center>
</form>

</body>

</html>
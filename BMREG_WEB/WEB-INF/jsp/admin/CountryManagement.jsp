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

function updateCountry(countryId,serial)
{

var visibility=0;
if(document.getElementById("visibility"+serial).checked==true)
 visibility=1;

 var loadUrl="updateCountry.action?countryId="+countryId+"&visibility="+visibility;
			jQuery("#msgDiv")  
				.html(ajax_load)  
				.load(loadUrl, {},function(responseText){  
					alert(responseText);
					jQuery("#msgDiv").html(responseText);
									   
				});
}

function setVisibilityValue(elementId)
{
 if(document.getElementById(elementId).checked==true)
  document.getElementById(elementId).value=1;
 else
 document.getElementById(elementId).value=0;
 
}

function checkUncheckAll(){
//visibility
if(document.getElementById("selectAll").checked==true)
 {
   for(var i=0;i<300;i++){
   		 if(document.getElementById("visibility"+i)){
   		   document.getElementById("visibility"+i).checked=true;
   		   document.getElementById("visibility"+i).value=1;
   		   }
   	}
 }
 else
 {
   for(var i=0;i<300;i++){
   		 if(document.getElementById("visibility"+i)){
   		   document.getElementById("visibility"+i).checked=false;
   		   document.getElementById("visibility"+i).value=0;
   		   }
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
	</div>
</div>
</center>
</div>
<form method="post" action="updateCountryList.action">
<center>
<br/>
<div class="box" style="margin-top: 20px;width: 900px;text-align: center;">
    <h3>Country Management</h3>
<div style="height: 400px;overflow: auto;">
    <table width="98%" align="center" border="0" cellpadding="2" cellspacing="0" style="border: 1px solid grey;">
     <tr bgcolor="#F1F1F1">
     
        <td width="10%" align="center" style="padding-left: 10px;" height="25"></td>
      	<td width="60%" align="left" style="padding-left: 10px;" height="25">Country Name</td>
      	<td width="20%" align="center" height="25">Visibility<br/>
      	<input type="checkbox" onchange="checkUncheckAll()" id="selectAll" />
      	</td>
      	<td width="10%" align="left" style="padding-left: 10px;" height="25">Edit</td>
      </tr>
      <s:iterator value="countryList" id="country" status="indx">

      	<s:if test="#indx.even == true">
       	  <tr bgcolor="#eeeeee"> 
     	</s:if>
     	<s:if test="abc.indx.odd == true">
       	  <tr>
        </s:if>
        <td align="left" style="padding-left: 10px;" height="25"><s:property value="%{#indx.count}" /></td>
      	<td align="left" style="padding-left: 10px;" height="25"><s:property value="countryName" /></td>
      	<td align="center" height="25">
      	 
      	<s:if test="#country.visibility==1">
		  <input type="checkbox" name="cList[<s:property value="%{#indx.index}" />].visibility" checked="checked" id="visibility<s:property value="%{#indx.count}" />" value="1" onclick="setVisibilityValue(this.id)" />
		</s:if>
		<s:elseif test="#country.visibility==0">
		  <input type="checkbox" name="cList[<s:property value="%{#indx.index}" />].visibility" id="visibility<s:property value="%{#indx.count}" />" value="0"  onclick="setVisibilityValue(this.id)" />
		</s:elseif> 
		<input type="hidden" name="cList[<s:property value="%{#indx.index}" />].countryId" value="<s:property value='countryId' />" /> 	
      		
      	</td>
      	<td align="left" style="padding-left: 10px;" height="25">
			<a href="#" onclick='updateCountry(<s:property value="countryId" />,<s:property value="%{#indx.count}" />)'  style="text-decoration: none;">
		      	<img src='/BMREG_WEB/resources/images/edit.png' border='0' width="20" height="20"/>
      		</a>
		</td>      	
        </tr> 
      </s:iterator>
      
      
    </table>
    
</div>
<br/>     
<input type="submit" name="save" value="Save Changes" style="width: 200px;height: 35px;" />
<br/>     
<div id="msgDiv"></div>
<br/>     

</div>
    <br/>
    <a href="systemAdminHome.action">Go Home</a>
    <br/>
<p style="height: 30px"></p>
</center>
</form>

</body>

</html>
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

function updateLanguage(languageName,serial)
{

var visibility=0;
if(document.getElementById("visibility"+serial).checked==true)
 visibility=1;

 var loadUrl="updateLanguage.action?languageName="+languageName+"&visibility="+visibility;
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
   		 if(document.getElementById("visibility"+i))
   		   document.getElementById("visibility"+i).checked=true;
   	}
 }
 else
 {
   for(var i=0;i<300;i++){
   		 if(document.getElementById("visibility"+i))
   		   document.getElementById("visibility"+i).checked=false;
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
<form method="post" action="updateGenderList.action">
<center>
<br/>
<div class="box" style="margin-top: 20px;width: 900px;text-align: center;">
    <h3>UISC Bulk User Create</h3>
<div style="height: 200px;overflow: auto;">

    <input type="file" name="bulkUserFile" />
    
</div>
<br/>     
<input type="submit" name="save" value="Create UISC Users" style="width: 200px;height: 35px;" />
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
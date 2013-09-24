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

function fetchAddressEntryForm(type)
{

 var loadUrl="fetchAddressEntryForm.action?requestType="+type;
			jQuery("#addressDiv")  
				.html(ajax_load)  
				.load(loadUrl, {},function(responseText){  
					jQuery("#addressDiv").html(responseText);
									   
				});
				
}

function saveAddress(type)
{
  var divisionId=document.getElementById("PERMANENT_DIV").value;
  var districtId=document.getElementById("PERMANENT_DIST").value;
  
  var upazilaId="";
   if(document.getElementById("PERMANENT_UPAZILLA_OR_THANA"))
      upazilaId=document.getElementById("PERMANENT_UPAZILLA_OR_THANA").value;
       
  var unionId="";
   if(document.getElementById("PERMANENT_UNION_OR_WARD"))
    unionId=document.getElementById("PERMANENT_UNION_OR_WARD").value;
  
  var mauzaId="";
   if(document.getElementById("PERMANENT_MAUZA_OR_MOHOLLA"))
  	mauzaId=document.getElementById("PERMANENT_MAUZA_OR_MOHOLLA").value;
  	
  var villageId="";
   if(document.getElementById("PERMANENT_VILLAGE"))	
      villageId=document.getElementById("PERMANENT_VILLAGE").value;
  
  if(type=="upazila")
   {
     var upazilaName=document.getElementById("PERMANENT_UPAZILA_THANA").value;
     
     if(divisionId=="") {alert("Please select division.");return;}
     if(districtId=="") {alert("Please select district.");return;}
     if(upazilaName=="") {alert("Please provide upazila name.");return;}
     
      var loadUrl="saveNewAddressInfo.action?requestType=upazila";
			jQuery("#waitingDiv")  
				.html(ajax_load)  
				.load(loadUrl, {divisionId:divisionId,districtId:districtId,upazilaName:upazilaName},function(responseText){  
					jQuery("#waitingDiv").html(responseText);
									   
				});
				
   }
   else if(type=="union")
   {
     var unionName=document.getElementById("PERMANENT_UNION_OR_WARD").value;
     
     if(divisionId=="") {alert("Please select division.");return;}
     if(districtId=="") {alert("Please select district.");return;}
     if(upazilaId=="") {alert("Please select upazila/thana/city. Corp/Pauroshova .");return;}
     if(unionName=="") {alert("Please provide union name.");return;}
     
      var loadUrl="saveNewAddressInfo.action?requestType=union";
			jQuery("#waitingDiv")  
				.html(ajax_load)  
				.load(loadUrl, {divisionId:divisionId,districtId:districtId,upazilaId:upazilaId,unionName:unionName},function(responseText){  
					jQuery("#waitingDiv").html(responseText);
									   
				});
				
   }
   else if(type=="mauza")
   {
     var mauzaName=document.getElementById("PERMANENT_MAUZA_OR_MOHOLLA").value;
     
     if(divisionId=="") {alert("Please select division.");return;}
     if(districtId=="") {alert("Please select district.");return;}
     if(upazilaId=="") {alert("Please select upazila/thana/city. Corp/Pauroshova .");return;}
     if(unionId=="") {alert("Please select union/ward.");return;}
     
     if(mauzaName=="") {alert("Please provide mauza/moholla name.");return;}
     
      var loadUrl="saveNewAddressInfo.action?requestType=mauza";
			jQuery("#waitingDiv")  
				.html(ajax_load)  
				.load(loadUrl, {divisionId:divisionId,districtId:districtId,upazilaId:upazilaId,unionId:unionId,mauzaName:mauzaName},function(responseText){  
					jQuery("#waitingDiv").html(responseText);
									   
				});
				
   }
   else if(type=="village")
   {
     var villageName=document.getElementById("PERMANENT_VILLAGE").value;
     
     if(divisionId=="") {alert("Please select division.");return;}
     if(districtId=="") {alert("Please select district.");return;}
     if(upazilaId=="") {alert("Please select upazila/thana/city. Corp/Pauroshova .");return;}
     if(unionId=="") {alert("Please select union/ward.");return;}
     if(mauzaId=="") {alert("Please select mauza/area.");return;}
     
     if(villageName=="") {alert("Please provide village name.");return;}
     
      var loadUrl="saveNewAddressInfo.action?requestType=village";
			jQuery("#waitingDiv")  
				.html(ajax_load)  
				.load(loadUrl, {divisionId:divisionId,districtId:districtId,upazilaId:upazilaId,unionId:unionId,mauzaId:mauzaId,villageName:villageName},function(responseText){  
					jQuery("#waitingDiv").html(responseText);
									   
				});
				
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
<center>
<br/>
I want to Insert a New -  
<a href="#" onclick="fetchAddressEntryForm(1)">Upazila/Thana/City Corp./Pourashava</a>  || 
<a href="#" onclick="fetchAddressEntryForm(2)">Union/Ward</a> ||  
<a href="#" onclick="fetchAddressEntryForm(3)">Mauza/Moholla</a> ||
<a href="#" onclick="fetchAddressEntryForm(4)">Village</a>

<div id="addressDiv" style="margin-top: 60px;width: 700px;text-align: center;"></div>

    <br/>
    <a href="homePage.action">Go Home</a>
    <br/>
<p style="height: 30px"></p>
</center>

</body>

</html>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>

<head>
<meta content="text/html;charset=utf-8" http-equiv="Content-Type">
<meta content="utf-8" http-equiv="encoding">
<title>Registration Statistics</title>

  <link rel="stylesheet" href="/BMREG_WEB/resources/css/style.css" />
  <script type="text/javascript" src="/BMREG_WEB/resources/js/lib/jquery-1.6.4.min.js"></script>
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
	 	<div style="font-size: 20px;margin-top: 10px;">Registration Statistics</div>
	</div>
</div>
</center>
</div>


<table align="center" width="96%" cellpadding="2" cellspacing="2" style="margin-left: 20px;">

 <tr>
   <td colspan="5" height="50" align="center" style="font-weight: bold;">
   
   <font style="color: maroon" >Select Division :</font>&nbsp;&nbsp;
   
   <a href="#" onclick="updateDivisionStatResult(1,'Dhaka')">Dhaka</a>&nbsp;&nbsp;
   <a href="#" onclick="updateDivisionStatResult(2,'Chittagong')">Chittagong</a>&nbsp;&nbsp;
   <a href="#" onclick="updateDivisionStatResult(3,'Khulna')">Khulna</a>&nbsp;&nbsp;
   <a href="#" onclick="updateDivisionStatResult(4,'Rajshahi')">Rajshahi</a>&nbsp;&nbsp;
   <a href="#" onclick="updateDivisionStatResult(5,'Barishal')">Barisal</a>&nbsp;&nbsp;
   <a href="#" onclick="updateDivisionStatResult(6,'Sylhet')">Sylhet</a>&nbsp;&nbsp;
   <a href="#" onclick="updateDivisionStatResult(7,'Rangpur')">Rangpur</a>&nbsp;&nbsp;
   


   </td>
 </tr>
 <tr>
   <td align="left" width="30%" id="divisionTd" style="min-height: 700px;vertical-align: top;">
   
	   
   
   </td>
  <td width="5%"></td>
   <td align="left" width="30%" id="districtTd" style="min-height: 700px;vertical-align: top;">
   
   
   </td>
   <td width="5%"></td>
   <td align="left" width="30%" id="thanaTd" style="min-height: 700px;vertical-align: top;">
   
   
   </td>
 </tr>
</table>


<script type="text/javascript">

var ajax_load="<img src='/BMREG_WEB/resources/images/loading.gif' border='0' /> " ;
var divId=0;
var divName=0;
var divisionTimerId = 0;
function updateDivisionStatResult(divisionId,divisionName) 
	{
		divId=divisionId;
		divName=divisionName;
	
		 //var loadUrl="divisionStat.action?divisionId="+$("#divisionId").val()+"&divisionName="+$("#divisionId").find('option:selected').text();
		 var loadUrl="divisionStat.action?divisionId="+divisionId+"&divisionName="+divisionName;	
	     
	     clearInterval(divisionTimerId);
    	 divisionTimerId=setInterval(refreshDivision, 300000);
    
	    
	     $("#divisionTd")  
					.html(ajax_load)  
					.load(loadUrl, {},function(responseText){  
					   
					   $("#divisionTd").html(responseText);
					   $("#districtTd").html("");
					   $("#thanaTd").html("");	

					     clearInterval(divisionTimerId);
				    	 divisionTimerId=setInterval(refreshDivision, 300000);
							 
					});
					
				
		
		
	}
 
 function loadDistrictStatistics(distId,distName) 
	{

		 
		 var loadUrl="districtStat.action";	
		 
	     $("#districtTd")  
					.html(ajax_load)  
					.load(loadUrl, {districtId: distId,districtName:distName},function(responseText){  
					   
					   $("#districtTd").html(responseText);
					    $("#thanaTd").html("");	
					    
					    clearInterval(divisionTimerId);
				    	divisionTimerId=setInterval(refreshDivision, 300000);
						
			
			 
					});
		
	}
 
  function loadThanaStatistics(thanaId ,thanaName) 
	{

		 
		 var loadUrl="thanaStat.action";	
		 
	     $("#thanaTd")  
					.html(ajax_load)  
					.load(loadUrl, {thanaId: thanaId,thanaName:thanaName},function(responseText){  
					   
					   $("#thanaTd").html(responseText);
						clearInterval(divisionTimerId);
				    	 divisionTimerId=setInterval(refreshDivision, 300000);
						
			 
					});
		
	}
 
  
   
   function refreshDivision()
   {
    if(divId!=0)
   		updateDivisionStatResult(divId,divName);
   }
   
    divisionTimerId=setInterval(refreshDivision, 300000); 
 
</script>
<br/><br/>
<center><a href="homePage.action" style="text-decoration: none;"><< Go Home >></a></center>
</body>
</html>
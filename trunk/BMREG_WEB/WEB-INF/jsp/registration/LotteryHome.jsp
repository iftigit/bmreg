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
<title>G2G Project - Lottery Home</title>
 <link rel="stylesheet" href="/BMREG_WEB/resources/css/style.css" />	
  <script type="text/javascript" src="/BMREG_WEB/resources/js/lib/jquery-1.6.4.min.js"></script> 
<script type="text/javascript" src="/BMREG_WEB/resources/js/util/util.js"></script>
<script type="text/javascript" src="/BMREG_WEB/resources/js/util/numeric.js"></script>
 <script type="text/javascript" src="/BMREG_WEB/resources/js/util/util.js"></script>
 
 <link type="text/css" rel="Stylesheet" href="/BMREG_WEB/resources/js/lib/jquery.validity.1.2.0/jquery.validity.css" />
        <script type="text/javascript" src="/BMREG_WEB/resources/js/lib/jquery.validity.1.2.0/jQuery.validity.js"></script>
 

</head>

<script type="text/javascript">
var ajax_load="<br/><br/><br/><center><img src='/BMREG_WEB/resources/images/ajax-loader.gif' border='0' /></center>";
 var table=document.getElementById("resultTable");
 var allJobseeker;
 var jobSeeker;
var globalIndex=0;
var refreshIntervalId;
function processLottery()
{
 document.getElementById("lotteryProcessButton").disabled=true;
 var loadUrl="processLottery.action";
			jQuery("#resultDiv")  
				.html(ajax_load)  
				.load(loadUrl, {},function(responseText){  
					jQuery("#resultDiv").html("");
					
					if(responseText!="error" && responseText!="duplicate")
					{
					    document.getElementById("lotteryButtonTd").innerHTML="";
						showResult(responseText);
					}
					else
					   {
					    document.getElementById("lotteryProcessButton").disabled=false;
					    jQuery("#resultDiv").html('<font style="color:red;font-size:18px"><br/><br/><br/>Server is busy now.Try again.</font>');
					   }				   
				}); 
				
}

function showResult(responseText)
{
 table=document.getElementById("resultTable");
 allJobseeker=responseText.split("NEWJOBSEEKERG2G");
 
 refreshIntervalId=setInterval(arrangeResult, 50);
 //setTimeout( arrangeResult, 2000 );
 
 document.getElementById("lotteryButtonTd").innerHTML="";
 
}

function arrangeResult()
{
 //for(var i=0;i<allJobseeker.length;i++)
  
  if(globalIndex==allJobseeker.length)
  {
   document.getElementById("LotteryResult").disabled=false;
   clearInterval(refreshIntervalId);
  }
  
  if(globalIndex!=allJobseeker.length)
   {
   jobSeeker=allJobseeker[globalIndex];
   var jobSeekerArr=jobSeeker.split("IICTG2GIFTI");
   var row=table.insertRow(0);
   var cell1=row.insertCell(0);
   var cell2=row.insertCell(1);
   var cell3=row.insertCell(2);
   var cell4=row.insertCell(3);
   var cell5=row.insertCell(4);
   var cell6=row.insertCell(5);
   
   cell1.innerHTML=(globalIndex+1);
   cell2.innerHTML=jobSeekerArr[0];
   cell3.innerHTML=jobSeekerArr[1];
   cell4.innerHTML=jobSeekerArr[2];
   cell5.innerHTML=jobSeekerArr[3];
   cell6.innerHTML=jobSeekerArr[4];
   //cell6.innerHTML="";
   globalIndex++;
   
  }
 
}

</script>
<body style="margin: 0px;">

<div style="width: 100%;height: 100px;border-bottom: 1px solid #006219;">
<center>
<div style="width: 1000px;height: 100px;border-right: 1px solid #006219;border-left: 1px solid #006219;">
	<div style="float: left; margin-top: 20px;width: 100px;">
	 <img src="/BMREG_WEB/resources/images/bagladesh_logo.gif" width="60" height="60" />
	</div>
	<div style="float: left;margin-left: 30px;color: black;margin-top: 15px;text-align: left;">
	 	<div style="font-size: 27px;font-weight: bold;">Bureau of Manpower, Employment & Training (BMET)</div>
	 	<div style="font-size: 20px;margin-top: 10px;">G2G Lottery Home</div>
	</div>
</div>
</center>
</div>
<center>
<br/>
<div class="box" style="margin-top: 20px;width: 1000px;text-align: center;">
    <h3>Lottery Home for <s:property value="districtName" /> District</h3>
    
    
<form id="loginForm" name="loginForm" method="post" action="checkValidity.action">    
    <table width="100%" border="0" cellspacing="1" class="infoTable">
    
    <tr>
    	<td width="50%" style="text-align: center;font-size: 18px;"><b>Total Jobseeker :</b> <s:property value="totalRegJobseeker" /></td>
    	<td width="50%" style="text-align: center;font-size: 18px;"><b>Total Quota :</b><s:property value="totalCotaNumber" /></td>
    </tr>
    <tr><td colspan="2"></td></tr>
    <tr>
     <td style="text-align: center" id="lotteryButtonTd">
       <s:if test="lotteryList.size==0">
        <input type="button" name="lotteryProcessButton" id="lotteryProcessButton" value="Process Lottery" style="width: 150px; height: 40px;font-weight: bold;" onclick="processLottery()"/>
       </s:if>
     	
     </td>
     <td style="text-align: center" >
      
       <s:if test="lotteryList.size==0">
     	  <input type="button" name="LotteryResult" id="LotteryResult" value="Download Lottery Result" style="width: 250px; height: 40px;font-weight: bold;" disabled="disabled" onclick="window.location='dcLotteryResultDownload.action'"/>
       </s:if>
       
      <s:if test="lotteryList.size!=0">
     	<input type="button" name="LotteryResult" id="LotteryResult" value="Download Lottery Result" style="width: 250px; height: 40px;font-weight: bold;" onclick="window.location='dcLotteryResultDownload.action'"/>
      </s:if>
      
     </td>
    </tr>
    <tr>
    <td colspan="2"></td>
    </tr>
    </table>
    <div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0">    	   
		 <tr>
    	    <td width="5%" align="center" style="background-color: #A2C1A2">SL</td>
    		<td width="25%" align="center" style="background-color: #8EBBB8">Reg. Number</td>
    		<td width="20%" align="center" style="background-color: #A2C1A2">Jobseeker Name</td>
    		<td width="20%" align="center" style="background-color: #8EBBB8">Father Name</td>
    		<td width="20%" align="center" style="background-color: #A2C1A2">Mother Name</td>    		
    		<td width="10%" align="center" style="background-color: #8EBBB8">Union</td>
    	</tr>   	
    </table>
    </div>
    <div id="resultDiv">
    </div>
    <div style="height: 500px;overflow: auto;clear: both;" id="resultDiv">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" id="resultTable">  
         <s:iterator value="lotteryList" status="idx">
            <tr >
    	    <td><s:property value="#idx.count" /></td>
    		<td align="left" style="padding-left: 3px;"><s:property value="jobseekerNumber" /></td>
    		<td align="left" style="padding-left: 5px;"><s:property value="jobseekerName" /></td>
    		<td align="left" style="padding-left: 3px;"><s:property value="fatherName" /></td>
    		<td align="left" style="padding-left: 3px;"><s:property value="motherName" /></td>    		
    		<td align="left" style="padding-left: 3px;"><s:property value="unionName" /></td>
    	</tr>
         </s:iterator>  	   
		 <tr>
    	    <td width="10%"></td>
    		<td width="20%"></td>
    		<td width="20%"></td>
    		<td width="20%"></td>
    		<td width="20%"></td>    		
    		<td width="10%"></td>
    	</tr>   	
    </table>
   </div>
</form>    
    
  
</div>
<p style="height: 30px"></p>
</center>

</body>

</html>
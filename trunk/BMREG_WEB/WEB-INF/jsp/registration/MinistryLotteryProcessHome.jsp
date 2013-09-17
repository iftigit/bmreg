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
//var ajax_load="<br/><br/><br/><center><img src='/BMREG_WEB/resources/images/ajax-loader.gif' border='0' /></center>";
var ajax_load="<br/><center><img src='/BMREG_WEB/resources/images/ajax-loader.gif' border='0' /></center>";
var lotteryDivision=0;

 var table=document.getElementById("resultTable");
 var allJobseeker;
 var jobSeeker;
var globalIndex=0;
var globalDivisionId=0;
var selectedDivTopPosition=0;
var refreshIntervalId;

function processLottery()
{
 document.getElementById("processLotteryBtn").disabled=true;
 

 
 var loadUrl="processDivisionLottery.action?divisionId="+globalDivisionId;
			jQuery("#resultDiv")  
				.html(ajax_load)  
				.load(loadUrl, {},function(responseText){  
					jQuery("#resultDiv").html("");
					
					if(responseText!="error" && responseText!="duplicate")
					{
					    //document.getElementById("lotteryButtonTd").innerHTML="";
						showResult(responseText);
					}
					else
					   {
					    document.getElementById("processLotteryBtn").disabled=false;
					    jQuery("#resultDiv").html('<font style="color:red;font-size:18px"><br/><br/><br/>Server is busy now.Try again.</font>');
					   }				   
				}); 
				
}

function showResult(responseText)
{
 table=document.getElementById("resultTable");
 allJobseeker=responseText.split("NEWJOBSEEKERG2G");
 
 refreshIntervalId= setInterval(arrangeResult, 50);

/* later */
 //setTimeout( arrangeResult, 2000 );
 
 //document.getElementById("lotteryButtonTd").innerHTML="";
 
}

function arrangeResult()
{
 //for(var i=0;i<allJobseeker.length;i++)
  
  if(globalIndex==allJobseeker.length)
  {
   //document.getElementById("LotteryResult").disabled=false;
   
   clearInterval(refreshIntervalId);
    $("#division"+globalDivisionId).animate(
            {"left": "+=-550px"},
            "slow");
 var top=$("#division1").offset().top;
    
    //alert(top+"===="+selectedDivTopPosition);
 var topDiff=selectedDivTopPosition-top;
 
 //alert(topDiff);
    
$("#division"+globalDivisionId).animate(
            {"top": "+="+topDiff+"px"},
            "slow");
 
 $("#division"+globalDivisionId).css("background-color", "#008040");  
 
 document.getElementById("selectDivisionBtn").disabled=false;  //Need to remove this; Only for testing
 $("#divPreLotterySummary").html(""); //Need to remove this... ; Only for Testing
 $("#division"+globalDivisionId).html($("#division"+globalDivisionId).html()+"&nbsp;&nbsp;&nbsp;<a href='ministryLotteryResultDownload.action?divisionId="+globalDivisionId+"'><img src='/BMREG_WEB/resources/images/pdf.png' border='0' /></a>");
    
    
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
   
   cell3.style.textAlign="left";
   cell4.style.textAlign="left";
   cell5.style.textAlign="left";
   cell6.style.textAlign="left";
   
   cell1.innerHTML=(globalIndex+1);
   cell2.innerHTML=jobSeekerArr[0];
   cell3.innerHTML=jobSeekerArr[1];
   cell4.innerHTML=jobSeekerArr[2];
   cell5.innerHTML=jobSeekerArr[3];
   cell6.innerHTML=jobSeekerArr[4];

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
<div class="box" style="margin-top: 5px;width: 1000px;text-align: center;">
    <h3>G2G Lottery Home</h3>
    
    
<form id="loginForm" name="loginForm" method="post" action="checkValidity.action">   


<STYLE>
.content {
    background-color:maroon;
    color:white;
    position:relative;
    width:200px;
    height:30px;
    padding:3px;
    margin-top:5px;
    left: 45px;
    text-align: center;
    vertical-align: middle;
    padding-top: 10px;
}
.content1 {
    background-color:#008040;
    color:white;
    position:relative;
    width:200px;
    height:30px;
    padding:3px;
    margin-top:5px;
    left: 45px;
    text-align: center;
    vertical-align: middle;
    padding-top: 10px;
}
</STYLE>
 <script type="text/javascript">
 (function($) {
    $.rand = function(arg) {
        if ($.isArray(arg)) {
            return arg[$.rand(arg.length)];
        } else if (typeof arg == "number") {
            return Math.floor(Math.random() * arg);
        } else {
            return 4;  // chosen by fair dice roll
        }
    };
})(jQuery);
var items = [<s:property value='pendingDivisionListString' />];


//var items = [4];



function removeA(arr) {
    var what, a = arguments, L = a.length, ax;
    while (L > 1 && arr.length) {
        what = a[--L];
        while ((ax= arr.indexOf(what)) !== -1) {
            arr.splice(ax, 1);
        }
    }
    return arr;
}
//http://stackoverflow.com/questions/3954438/remove-item-from-array-by-value


function selectDivisionForLottery()
{
  
  if(items.length==0)
  {
    alert("Sorry, lottery for all division are done. So, division selection option is not permitted now.");
  } 
  else
   {
	 document.getElementById("selectDivisionBtn").disabled=true;
     $("#divPreLotterySummary").html(ajax_load+"<br/><font style='color:blue;font-weight:bold'>Randomly Selecting a Division</font><br/><br/>Please Wait....");
  
     setTimeout("SearchingDivisionForLotery()", 500);
    }
}

function SearchingDivisionForLotery()
{  
  
  var selectedDiv=$.rand(items);
  globalDivisionId=selectedDiv;
  
  selectedDivTopPosition=$("#division"+selectedDiv).offset().top;
  
  items=removeA(items, selectedDiv);
  
    $("#division"+selectedDiv).animate(
            {"left": "+=550px"},
            "slow");
    
    var top=$("#division1").offset().top;
    
    var ownTop=$("#division"+selectedDiv).offset().top;
    
    var topDiff=ownTop-top;
    

    
     $("#division"+selectedDiv).animate(
            {"top": "-="+topDiff+"px"},
            "slow");
    
   
   //selectedDiv
var loadUrl="getDivisionWiseLotterySummary.action?divisionId="+selectedDiv;
			jQuery("#divPreLotterySummary")  
				.html(ajax_load)  
				.load(loadUrl, {},function(responseText){  
				
				    jQuery("#divPreLotterySummary").html(responseText);
				    document.getElementById("processLotteryBtn").disabled=false;
								   
				});    
    
      
      
   
    
    
            
  
} 

</script>
    <table width="900px" border="0" cellspacing="1" class="infoTable" align="center" style='border:1px solid grey;'>
    <tr>
      	<td width="400px" align="left" style="padding-top: 10px;padding-bottom: 10px;">
      	
      	  <s:iterator value="lotteryStatusList" status="idx">
      	    <s:if test="status=='completed'">      	    
	    		
	    		<div id="division<s:property value='#idx.count' />" class="content1"><s:property value="divisionName" />
	    	    &nbsp;&nbsp;&nbsp;<a href='ministryLotteryResultDownload.action?divisionId=<s:property value="divisionId" />'><img src='/BMREG_WEB/resources/images/pdf.png' border='0' /></a>
	    	    </div>
	    	</s:if>
	    	<s:else>
	    	    <div id="division<s:property value='#idx.count' />" class="content"><s:property value="divisionName" /> </div>
	    	</s:else>
	      </s:iterator>
	      
	   	</td >
	   	
    	<td width="100px" align="center" style="text-align: center;">
    		<input type="button" name="selectDivisionBtn" id="selectDivisionBtn" value="Select Division" onclick="selectDivisionForLottery()" style="text-align: center;width: 150px;height: 35px;"  />
    		<br/><br/>
    		<a href="ministryLotteryResultDashboard.action">
    		<img src="/BMREG_WEB/resources/images/stat.png" border="0" width="60" height="60" />
    		<br/>
    		Result Statistics
    		</a> 
    	</td>
    	<td width="400px" align="center" style="text-align: center; vertical-align: top;">
    	
    	  <div id="selectedDivisionDiv" style="width: 250px;height: 60px;margin-top: 20px;margin-left: 30px;">
    	  </div>
    	  <div style="margin-top: 20px;min-height: 150px;" id="divPreLotterySummary">
    	  </div>
    	  <div style="margin-top: 20px;">
    	  <input type="button" name="processLotteryBtn" value="Process Lottery"  id="processLotteryBtn"  style="width: 150px;height: 35px;" onclick="processLottery()"/>
    	  </div>	
    	</td>
    </tr>
    
    </table>
    <div style="margin-top: 10px;"></div>
    <div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0">    	   
		 <tr>
    	    <td width="5%" align="center" style="background-color: #A2C1A2" height="25">SL</td>
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
    <div style="height: 500px;overflow: auto;clear: both; background: url('/BMREG_WEB/resources/images/bangladesh.png'); background-repeat: no-repeat;background-position: center;" id="resultDiv">
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

<script type="text/javascript">
document.getElementById("processLotteryBtn").disabled=true;
</script>


</body>

</html>
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


function fetchJobList()
{
 var loadUrl="fetchJobList.action";
			jQuery("#jobListDiv")  
				.html(ajax_load)  
				.load(loadUrl, {},function(responseText){  
					jQuery("#jobListDiv").html(responseText);
				});
}
function changeImage(imgId,imgSrc){
document.getElementById(imgId).src=imgSrc;
}

function fetchJobCategory(parentJobId,level,componentIndex,waitingDiv,selectType)
{
	    
        document.getElementById(waitingDiv).innerHTML="";
 		var ajax_url="<img src='/BMREG_WEB/resources/images/ajax-loader.gif' alt='Loading ....' />"; 
		var url="/BMREG_WEB/fetchSubJob.action?etc="+new Date().getTime();
		
			$("#"+waitingDiv) 
			.html(ajax_url)  
			.load(url, {parentJobId: parentJobId,jobLevel: level,componentIndex:componentIndex,selectType:selectType,allOrActive:0},function(responseText){  
				if(responseText!="")
				$("#"+waitingDiv).innerHTML= responseText;
			});
}
function loadEditPanel(mainJobId,subJobId,subSubJobId)
{
	var loadUrl="getJobEditPanel.action";
			jQuery("#addEditPanel")  
				.html(ajax_load)  
				.load(loadUrl,{categoryId: mainJobId,subCategoryId: subJobId,subSubCategoryId: subSubJobId},function(responseText){  
					jQuery("#addEditPanel").html(responseText);
					setTimeout(function(){window.location.hash = 'listTopAnchor';}, 500);
				});
}

</script>
</head>
<body style="margin: 0px;">

<div style="width: 100%;height: 100px;border-bottom: 1px solid #006219;">
<center>
<div style="width: 1000px;height: 100px;border-right: 1px solid #006219;border-left: 1px solid #006219;">
	<div style="float: left; margin-top: 20px;width: 100px;">
	 &nbsp;&nbsp;&nbsp; <img src="/BMREG_WEB/resources/images/bagladesh_logo.gif" width="60" height="60" />
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
<div class="box" style="margin-top: 20px;width: 980px;text-align: center;">
    <h3>JobCategory-wise Registration Information</h3>
    <div style="padding-bottom: 10px;padding-left: 10px;" id="addEditPanel">
      <table width="98%" style="border: 1px dotted green;">
            <tr>
            	<td colspan="5" style="text-align: left;color: maroon;font-size: 15px;font-weight: bold;" height="30">
            		Select your desire job category
            	</td>
            </tr>
      		<tr>
      			<td width="8%" style="text-align: left;" height="30">Main Job</td>
      			<td width="22%">
      				<select style="border: 1px solid gray;width: 200px;" onchange="fetchJobCategory(this.value,2,0,'subJobDiv1','jobManagement')">
      				<option value="-99">Select Main Job</option>
      				<s:iterator value="mainJobList">
      				 <option value="<s:property value="jobId" />"><s:property value="jobTitle" /></option>
      				</s:iterator>
      				</select>
      			</td>
      			<td width="6%" style="text-align: left;">Sub Job</td>
      			<td width="22%" id="subJobDiv1">
      				<select style="border: 1px solid gray;width: 200px;" >
      					<option value="-99">Select Sub Job</option>
      				</select>
      			</td>
      			<td width="10%" style="text-align: left;">Sub-sub Job</td>
      			<td width="22%" id="subJobDiv2">
      				<select style="border: 1px solid gray;width: 200px;">
      					<option value="-99">Select Sub-Sub Job</option>
      				</select>
      			</td> 
      			<td width="10%" style="text-align: center;"><input type="button" value="Update"  name="editJob" /></td>
      		</tr>
      </table>

    </div><br/>
    <div id="resultDiv">
    </div>   
<br/>     

</div>
    <br/>
    <a href="systemAdminHome.action">Go Home</a>
    <br/>
<p style="height: 30px" id="msgDiv"></p>
</center>
</form>

</body>

</html>
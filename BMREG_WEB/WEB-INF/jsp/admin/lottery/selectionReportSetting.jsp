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

function addNewDegree()
{
 var degreeName=document.getElementById("degreeName").value;
 var viewSerial=document.getElementById("viewSerial").value;
 
 var loadUrl="addNewDegreeName.action?degreeName="+degreeName+"&viewSerial="+viewSerial;
			jQuery("#msgDiv")  
				.html(ajax_load)  
				.load(loadUrl, {},function(responseText){  
				    alert(responseText);
					jQuery("#msgDiv").html(responseText);
					document.getElementById("degreeName").value="";
					document.getElementById("viewSerial").value="";
					refreshDegreeListDiv();
									   
				});
 
 
}
function updateDegreeName(degreeId,degreeName,viewSerial,index){
var degreeName=document.getElementById("degreeName"+index).value;
 var viewSerial=document.getElementById("viewSerial"+index).value;
 
 var loadUrl="updateDegreeName.action?degreeId="+degreeId+"&degreeName="+degreeName+"&viewSerial="+viewSerial;
			jQuery("#msgDiv")  
				.html(ajax_load)  
				.load(loadUrl, {},function(responseText){  
				    alert(responseText);
					jQuery("#msgDiv").html(responseText);
									   
				});

}
function deleteDegreeName(degreeId){

var r=confirm("Are you sure you want to delete?");
if (r==true)
  {
 var loadUrl="deleteDegreeName.action?degreeId="+degreeId;
			jQuery("#msgDiv")  
				.html(ajax_load)  
				.load(loadUrl, {},function(responseText){
				    alert(responseText);  
					jQuery("#msgDiv").html(responseText);
					refreshDegreeListDiv();			   
				});
				
  				

  }
  

}

function refreshDegreeListDiv()
{
 var loadUrl="fetchDegreeNameList.action";
			jQuery("#degreeListDiv")  
				.html(ajax_load)  
				.load(loadUrl, {},function(responseText){  
					jQuery("#degreeListDiv").html(responseText);				   
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
<center>
<br/>
<div class="box" style="margin-top: 20px;width: 900px;text-align: center;">
    <h3>Degree Name Management</h3>
    <div style="padding-bottom: 30px;">
     <div id="degreeListDiv">
   <form action="saveSelectionReportSetting" method="post" >
    <table width="98%" align="center" border="0" cellpadding="2" cellspacing="0" style="border: 1px solid grey;">
     <tr bgcolor="#F1F1F1">
     
        <td width="8%" align="center" style="padding-left: 10px;" height="25">SL</td>
      	<td width="25%" align="left" style="padding-left: 10px;" height="25">Field Name</td>
      	<td width="8%" align="center" style="padding-left: 10px;" height="25">View Order</td>
      	<td width="10%" align="center" style="padding-left: 10px;" height="25">Visibility</td>
      	<td width="10%" align="center" style="padding-left: 10px;" height="25">Field Width</td>
      	<td width="25%" align="left" style="padding-left: 10px;" height="25">Caption</td>
      	<td width="10%" align="center" style="padding-left: 10px;" height="25">Alignment</td>
      </tr>
      <s:iterator value="fieldList" status="indx" id="fieldList">
        <tr>
        <td align="center"  height="25">
        <s:property value="%{#indx.count}" />.
        </td>
      	<td align="left" style="padding-left: 10px;" height="25">      		
      		<input type="text" readonly="readonly" value="<s:property value="fieldName" />" name="fieldList[<s:property value="%{#indx.index}" />].fieldName" id="fieldName<s:property value='%{#indx.count}' />"  style="border: 1px solid grey;width: 180px;background-color: #FDEEF4" />
      	</td>
      	<td align="center" style="padding-left: 10px;" height="25">      		
      		<input type="text" value="<s:property value="viewOrder" />" name="fieldList[<s:property value="%{#indx.index}" />].viewOrder"  id="viewOrder<s:property value='%{#indx.count}' />"  style="border: 1px solid grey;width: 50px;text-align: center;" />
      	</td>
      	
      	<td align="center" style="padding-left: 10px;" height="25">   
      	   		
      		<select style="border:1px solid grey;width:40px;" name="fieldList[<s:property value="%{#indx.index}" />].visibilityYN">      		
      		<option value="Y" <s:if test='%{#fieldList.visibilityYN=="Y"}'>selected="selected"</s:if>>Y</option>
      		<option value="N" <s:if test='%{#fieldList.visibilityYN=="N"}'>selected="selected"</s:if>>N</option>
      		</select>
      	</td>
      	
      	<td align="center" style="padding-left: 10px;" height="25">       		
      		<input type="text" value="<s:property value="fieldWidth" />" name="fieldList[<s:property value="%{#indx.index}" />].fieldWidth" id="fieldWidth<s:property value='%{#indx.count}' />"  style="border: 1px solid grey;width: 40px;text-align: center;" />%
      	</td>
      	<td align="left" style="padding-left: 10px;" height="25">      		
      		<input type="text" value="<s:property value="fieldCaption" />" name="fieldList[<s:property value="%{#indx.index}" />].fieldCaption" id="fieldCaption<s:property value='%{#indx.count}' />"  style="border: 1px solid grey;width: 180px;" />
      	</td>
      	<td align="left" style="padding-left: 10px;" height="25">      		
      		<select style="border:1px solid grey;width:80px;" name="fieldList[<s:property value="%{#indx.index}" />].alignment">      		
      		<option value="LEFT" <s:if test='%{#fieldList.alignment=="LEFT"}'>selected="selected"</s:if> >LEFT</option>
      		<option value="RIGHT" <s:if test='%{#fieldList.alignment=="RIGHT"}'>selected="selected"</s:if> >RIGHT</option>
      		<option value="CENTER" <s:if test='%{#fieldList.alignment=="CENTER"}'>selected="selected"</s:if> >CENTER</option>
      		</select>
      	</td>
        </tr> 
      </s:iterator>
      
      
    </table><br/><br/>
    <center><input type="submit" name="Save" value="Save" style="width: 80px;"/></center>
    </form>
     
     </div>
   
   
    
    <p style="padding-bottom: 20px;">
    <a href="systemAdminHome.action">Go Home</a>
    </p>

</div>
<p id="msgDiv"></p>
</center>

</body>

</html>
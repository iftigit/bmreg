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
         <table width="98%" align="center" border="0" cellpadding="2" cellspacing="0" style="border: 1px solid grey;">
     
     <tr bgcolor="#F1F1F1">
     
        <td width="10%" align="center" style="padding-left: 10px;" height="25">SL</td>
      	<td width="60%" align="left" style="padding-left: 10px;" height="25">Degree Name</td>
      	<td width="20%" align="center" style="padding-left: 10px;" height="25">View Order</td>
      	<td width="10%" align="center" style="padding-left: 10px;" height="25">Edit/Delete</td>
      </tr>
      <s:iterator value="degreeList" status="indx">
        <tr>
        <td align="center"  height="25">
        <s:property value="%{#indx.count}" />.
        </td>
      	<td align="left" style="padding-left: 10px;" height="25">
      		
      		<input type="text" value="<s:property value="degreeName" />" id="degreeName<s:property value='%{#indx.count}' />"  style="border: 1px solid grey;width: 400px;" />
      	</td>
      	<td align="center" style="" height="25">
      		<input type="text" value="<s:property value="viewSerial" />" id="viewSerial<s:property value='%{#indx.count}' />" style="border: 1px solid grey;width: 50px;text-align: center;" />
      	</td>
      	<td align="center"  height="25">
      	<a href="#" onclick='updateDegreeName(<s:property value="degreeId" />,"<s:property value='degreeName' />",<s:property value="viewSerial" />,<s:property value="%{#indx.count}" />)'  style="text-decoration: none;">
	      	<img src='/BMREG_WEB/resources/images/edit.png' border='0' width="20" height="20"/>
      	</a>
      	&nbsp;&nbsp;&nbsp;
      	<a href="#" onclick='deleteDegreeName(<s:property value="degreeId" />)'  style="text-decoration: none;">
      	 <img src='/BMREG_WEB/resources/images/delete.png' border='0'  width="20" height="20"/>
      	 </a>
      	</td>
        </tr> 
      </s:iterator>
      
      
    </table>
     
     </div>
   
    <br/>
    <div style="float: left;font-size: 18px;font-weight: bold;color: blue;padding-left: 10px;">New Degree Entry</div>
    <br style="clear: both;"/>
    <table width="98%" align="center" border="0" cellpadding="2" cellspacing="0" style="border: 1px solid grey;margin-top: 20px;">
    <tr>
    <td height="40px;">Degree Name : <input type="text" style="border: 1px solid grey;width:400px;"  id="degreeName"/></td>
    <td>View Order : <input type="text" style="border: 1px solid grey;width:50px;"  id="viewSerial"/></td>
    </tr>
    </table>
    
<p style="padding-top: 20px;">     
<input type="button" name="save" value="Add New Degree" style="width: 200px;height: 35px;"  onclick="addNewDegree()"/>
</p>
</div>
    
    <p style="padding-bottom: 20px;">
    <a href="systemAdminHome.action">Go Home</a>
    </p>

</div>
<p id="msgDiv"></p>
</center>

</body>

</html>
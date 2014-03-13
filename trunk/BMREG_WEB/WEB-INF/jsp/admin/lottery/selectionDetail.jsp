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
 <script type="text/javascript" src="/BMREG_WEB/resources/js/util/browserDetect.js"></script>
 <script type="text/javascript" src="/BMREG_WEB/resources/js/util/numeric.js"></script> 
 <script type="text/javascript" src="/BMREG_WEB/resources/js/util/xmlextras.js"></script>
 <link rel="stylesheet" type="text/css" href="/BMREG_WEB/resources/jqueryUi/ui-lightness/jquery-ui.css" />
 <script type="text/javascript" src="/BMREG_WEB/resources/jqueryUi/jquery-ui.min.js"></script> 
 <link href="/BMREG_WEB/resources/multiselect/jquery.multiselect.css" rel="stylesheet" type="text/css">
 <script type="text/javascript" src="/BMREG_WEB/resources/multiselect/jquery.multiselect.js"></script>
 <script type="text/javascript" src="/BMREG_WEB/resources/js/registration.js"></script>
 <script type="text/javascript">

var ajax_load="<br/><center><img src='/BMREG_WEB/resources/images/ajax-loader1.gif' border='0' /></center>";

function searchSelections()
{

var agentId=document.getElementById("agentId").value;
var workOrder=document.getElementById("workOrder").value;
if(agentId=="none")
{
 	alert("Please select an agent.");
 	return;
} 


 var loadUrl="searchSelection.action?agentId="+agentId+"&workOrder="+workOrder;
			jQuery("#searchResult")  
				.html(ajax_load)  
				.load(loadUrl, {},function(responseText){  
					jQuery("#searchResult").html(responseText);
									   
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
<div class="box" style="margin-top: 30px;width: 950px;text-align: center;">
    <h3>Selection Detail</h3>
    <div style="padding-bottom: 30px;">
    <table width="100%" border="0" style="padding-left: 10px;">
    	<tr>
    	 <td width="10%" align="left">Selection Id</td><td width="20%" align="left">: <s:property value="selection.selectionId" /></td>
    	 <td width="10%" align="left">Agency </td><td width="20%" align="left" style="font-size: 12px; ">: <s:property value="selection.agentCompanyName" /></td>
    	 <td width="10%" align="left">Work Order</td><td width="20%" align="left">: <s:property value="selection.workOrder" /></td>
    	</tr>
    	<tr>
    	 <td align="left">Country</td><td align="left">: <s:property value="selection.countryPreference" /></td>
    	 <td align="left">Gender </td><td align="left">: <s:property value="selection.gender" /></td>
    	 <td align="left">Language</td><td align="left">: <s:property value="selection.languages" /></td>
    	</tr>
    	<tr>
    	 <td align="left">Job Preference</td><td align="left">: <s:property value="selection.selectionId" /></td>
    	 <td align="left">Job Experience </td><td align="left">: <s:property value="" /></td>
    	 <td align="left">Exp. Year</td><td align="left">: <s:property value="selection.yearOfExperience" /></td>
    	</tr>
    </table>
     
    <form action="saveJobseekerSelection" method="post">
    <table width="98%" align="center" border="0" cellpadding="2" cellspacing="0" style="border: 1px solid grey;">     
     <tr bgcolor="#F1F1F1">
     
        <td width="5%" align="center" style="padding-left: 10px;" height="25">SL</td>
      	<td width="10%" align="left" style="padding-left: 10px;" height="25">Jobseeker Id</td>
      	<td width="20%" align="left" style="padding-left: 10px;" height="25">Name</td>
      	<td width="15%" align="left" style="padding-left: 10px;" height="25">Mobile</td>
      	<td width="35%" align="left" style="padding-left: 10px;" height="25">Address</td>
      	<td width="5%" align="center" style="padding-left: 10px;" height="25">Selected?</td>
      </tr>
      <s:iterator value="jobseekerList" status="indx" id="js">
        <tr>
       <s:if test='%{#js.selectedYN == "Y"}'>  
            	<tr bgcolor="#F4FFF4"> 
       </s:if>
       <s:if test='%{#js.selectedYN == "N"}'>
	       	<s:if test="#indx.even == true">
	        	<tr bgcolor="#eeeeee">
	      	</s:if>
	      	<s:if test="abc.indx.odd == true">
	        	<tr>
	       </s:if>
       </s:if>
        
        <td align="center"  style="padding-left: 10px;" height="25"><s:property value="%{#indx.count}" /></td>
      	<td align="left" style="padding-left: 10px;" height="25"><s:property value="jobseekerId" /></td>
      	<td align="left" style="padding-left: 10px;" height="25">
      		<s:property value="givenName" /> <s:property value="lastName" />
      	</td>
      	<td align="left" style="padding-left: 10px;" height="25"><s:property value="mobileNumber" /></td>
      	<td align="left" style="padding-left: 10px;" height="25">
      	    HouseNo :<s:property value="pHouseholdNumber" />, RoadNo :<s:property value="pRoadNumber" /><br/>
      	    P.Code :<s:property value="pPostCode" />, PO:<s:property value="pPostOffice" />,Vill:<s:property value="pVillageName" />,<br/>
      		Mauza:<s:property value="pMauzaName" />,Union:<s:property value="pUnionName" />,Thana:<s:property value="pThanaName" />,<br/>
      		Dist:<s:property value="pDistrictName" />,Division:<s:property value="pDivisionName" />
      	</td>
      	<td align="center" style="padding-left: 10px;" height="25">
      		<s:if test='%{#js.selectedYN == "Y"}'>
      		 	<input type="checkbox" checked="checked" name="selectStatusList" value="<s:property value='jobseekerId' />"/>
      		</s:if>
      		<s:if test='%{#js.selectedYN == "N"}'>
      			<input type="checkbox" name="selectStatusList" value="<s:property value='jobseekerId' />"/>
      		</s:if>
      	  
      	</td>
        </tr> 
      </s:iterator>           
</table>

    
	<p style="padding-top: 40px;">
	    <input type="hidden" name="selectionId" id="selectionId" value="<s:property value='selectionId' />" />     
		<input type="submit" name="search" value="Save" style="width: 150px;height: 35px;"  />
	</p>
	</form>
</div>
<div id="searchResult"></div>
    
    <p style="padding-bottom: 20px;">
    	<a href="systemAdminHome.action">Go Home</a>
    </p>

</div>
<p id="msgDiv"></p>
</center>

</body>

</html>
    

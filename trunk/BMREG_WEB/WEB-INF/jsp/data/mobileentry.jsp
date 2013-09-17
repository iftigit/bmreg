<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>

<head>
<meta content="text/html;charset=utf-8" http-equiv="Content-Type">
<meta content="utf-8" http-equiv="encoding">
<title>G2G Job Seeker Registration Form</title>
 <link rel="stylesheet" href="/BMREG_WEB/resources/css/style.css" />
  <script type="text/javascript" src="/BMREG_WEB/resources/js/lib/prototype-1.6.0.2.js"></script>	
  <script type="text/javascript" src="/BMREG_WEB/resources/js/lib/jquery-1.6.4.min.js"></script>
  <script type="text/javascript" src="/BMREG_WEB/resources/js/util/util.js"></script>
  <script type="text/javascript" src="/BMREG_WEB/resources/js/util/numeric.js"></script>
  <script type="text/javascript" src="/BMREG_WEB/resources/js/util/browserDetect.js"></script>
  <script type="text/javascript" src="/BMREG_WEB/resources/js/address.js"></script>
  <script type="text/javascript" src="/BMREG_WEB/resources/js/regValidation.js"></script>
  <script type="text/javascript" src="/BMREG_WEB/resources/js/util/util.js"></script>
  <script type="text/javascript" src="/BMREG_WEB/resources/js/lib/jquery.corner.js"></script>
  <link type="text/css" rel="Stylesheet" href="/BMREG_WEB/resources/js/lib/jquery.validity.1.2.0/jquery.validity.css" />
  <script type="text/javascript" src="/BMREG_WEB/resources/js/lib/jquery.validity.1.2.0/jQuery.validity.js"></script>
        
  <script type="text/javascript" src="/BMREG_WEB/resources/js/lib/jscalendar-1.0/calendar.js"></script>
  <script type="text/javascript" src="/BMREG_WEB/resources/js/lib/jscalendar-1.0/calendar-setup.js"></script>
  <script type="text/javascript" src="/BMREG_WEB/resources/js/lib/jscalendar-1.0/lang/calendar-en.js"></script>
  <style type="text/css"> @import url("/BMREG_WEB/resources/js/lib/jscalendar-1.0/skins/aqua/theme.css"); </style>
    
 
  
</head>

<body style="margin: 0px;">
<form id="empRegForm" name="empRegForm" method="post" action="previewRegFormAction.action">
<div style="width: 100%;height: 100px;border-bottom: 1px solid #006219;">
<center>
<div style="width: 1000px;height: 100px;border-right: 1px solid #006219;border-left: 1px solid #006219;">
	<div style="float: left; margin-top: 20px;width: 100px;">
	 <img src="/BMREG_WEB/resources/images/bagladesh_logo.gif" width="60" height="60" />
	</div>
	<div style="float: left;margin-left: 30px;color: black;margin-top: 15px;text-align: left;">
	 	<div style="font-size: 27px;font-weight: bold;">Bureau of Manpower, Employment & Training (BMET)</div>
	 	<div style="font-size: 20px;margin-top: 10px;">New User</div>
	</div>
</div>
</center>
</div>
<center>
<br/>

<p style="height: 30px"></p>
<div class="box">
    <h3>New User</h3>
    <p>
    
    <table width="100%" border="0" cellspacing="1" class="infoTable">
    <tr bgcolor="#F2F7E3">
     <td align="center" width="50%" colspan="2" style="text-align: center;font-weight: bold;">Select Address</td>
      </tr>
    <tr>
     <td width="15%">Division<font color="red">*</font></td>
     <td width="35%">
         
     <select tabindex="19" name="addressDTO.pDivision" id="PERMANENT_DIV" class="addressSelectBox" onchange="fetchJSONData_Dist(this.value,'PERMANENT_DIST')">
     	  <option value="select" selected="selected">--Select Division--</option>	
     	<s:iterator value="%{#application.ALL_DIVISION}" id="divisionList">
     	  <option value="<s:property value="division_id" />"><s:property value="division_name" /></option>
     	</s:iterator>
     </select>
     
     </td>
     
    </tr>
    <tr>
     <td>District<font color="red">*</font></td>
    <td valign="top" id="PERMANENT_DIST_TD">
     <select tabindex="31" name="addressDTO.pDistrict" id="PERMANENT_DIST" class="addressSelectBox" onchange="fetchJSONData_Thana(this.value,'PERMANENT_THANA')">
     </select>
     </td>
     
    </tr>
    
    <tr>
     <td>Upazilla<font color="red">*</font></td>
     <td valign="top" id="PERMANENT_THANA_TD">
        <select tabindex="32" name="addressDTO.pThana" id="PERMANENT_THANA" class="addressSelectBox" onchange="fetchJSONData_Union(this.value,'PERMANENT_UNION')" >
       </select>
     </td>   
    </tr>
    <tr>
     <td>Union<font color="red">*</font></td>
     <td valign="top" id="PERMANENT_UNION_TD">
        <select tabindex="22" name="addressDTO.pUnion" id="PERMANENT_UNION" class="addressSelectBox" >
       </select>
     </td>
     
    </tr>
    
    
    <tr>
     <td>Mobile/Phone<font color="red">*</font></td>
     <td valign="top">
     	<input type="text" tabindex="28" name="addressDTO.pMobile" maxlength="25" id="PERMANENT_MOBILE" class="textBox" value="<s:property value="addressDTO.pMobile"/>" onkeypress="return numericOnly(event)" />
     </td>
    
    </tr>
    </table>
    
    
   
</div>



<div style="margin-top: 20px;">
<input type="button" class="submitButton" name="goHomeButton" value="Go Home" onclick="window.location='homePage.action'" />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="submit" tabindex="46" class="submitButton" name="SubmitApplication" value="Preview Application" />


</div>
<p style="height: 30px"></p>

 
</center>
<%String form_error=(String) request.getSession().getAttribute("form_error");
 if(form_error==null)form_error="first_time";
 if(form_error.equalsIgnoreCase("form_error") || form_error.equalsIgnoreCase("edit_form")){
  %>
   <%@ include file="SetFormContent.jsp" %>
 <%} %>
 
<script type="text/javascript">
$('input').attr('autocomplete','off');

//refreshCaptcha('');   
</script>

</form>
</body>


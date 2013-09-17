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
<title>G2G Job Seeker Registration Form</title>
 <link rel="stylesheet" href="/BMREG_WEB/resources/css/style.css" />	
 <script type="text/javascript" src="/BMREG_WEB/resources/js/lib/prototype-1.6.0.2.js"></script>

 <script type="text/javascript" src="/BMREG_WEB/resources/js/util/util.js"></script>
 <script type="text/javascript" src="/BMREG_WEB/resources/js/lib/jquery.corner.js"></script>
 <script type="text/javascript" src="/BMREG_WEB/resources/js/address.js"></script> 
 <link type="text/css" rel="Stylesheet" href="/BMREG_WEB/resources/js/lib/jquery.validity.1.2.0/jquery.validity.css" />
 <script type="text/javascript" src="/BMREG_WEB/resources/js/lib/jquery.validity.1.2.0/jQuery.validity.js"></script>
        
    <style type="text/css"> @import url("/BMREG_WEB/resources/js/lib/jscalendar-1.0/skins/aqua/theme.css"); </style>
    
  
</head>
<body style="margin: 0px;">
<form id="empRegForm" name="empRegForm" method="post" action="previewRegFormAction.action">

<div class="box2">
    <h3>Lottery</h3>
    <p>
    
    <table width="100%" border="0" cellspacing="1" class="infoTable">

    <tr>
     <td width="15%">Division</td>
     <td width="35%">
     	
     <select tabindex="30" name="addressDTO.pDivision" id="PERMANENT_DIV" class="addressSelectBox" onchange="fetchJSONData_Dist(this.value,'PERMANENT_DIST')">
     	  <option value="select" selected="selected">--Select Division--</option>
     	<s:iterator value="%{#application.ALL_DIVISION}" id="divisionList">
     	  <option value="<s:property value="division_id" />"><s:property value="division_name" /></option>
     	</s:iterator>
     </select>
     </td>

    </tr>
    <tr>
     <td>District</td>
     <td valign="top" id="PERMANENT_DIST_TD">
     <select tabindex="31" name="addressDTO.pDistrict" id="PERMANENT_DIST" class="addressSelectBox" onchange="fetchJSONData_Thana(this.value,'PERMANENT_THANA')">
     </select>
     </td>

    </tr>
    <tr>
     <td>Thana/Upazilla</td>
     <td valign="top" id="PERMANENT_THANA_TD">
        <select tabindex="32" name="addressDTO.pThana" id="PERMANENT_THANA" class="addressSelectBox" onchange="fetchJSONData_Union(this.value,'PERMANENT_UNION')" >
       </select>
     </td>

    </tr>

    <tr>
     <td>Union</td>
     <td valign="top" id="PERMANENT_UNION_TD">
        <select tabindex="32" name="addressDTO.pUnion" id="PERMANENT_UNION" class="addressSelectBox" >
       </select>
     </td>

    </tr>
    

    
    
    </p>
</div>


</form>
</body>

</html>
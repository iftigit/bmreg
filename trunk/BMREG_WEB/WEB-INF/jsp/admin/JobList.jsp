<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="org.controller.registration.*" %>
<%@ page import="java.util.ArrayList" %>
<head></head>
<body>
    <table width="98%" align="center" border="0" cellpadding="2" cellspacing="0" style="border: 1px solid grey;">
     
     <tr bgcolor="#F1F1F1">
     
        <td width="5%" align="left" style="padding-left: 5px;" height="25">SL</td>
      	<td width="27%" align="left" style="padding-left: 2px;" height="25">Main Job</td>
      	<td width="27%" align="left" style="padding-left: 2px;" height="25">Sub Job</td>
      	<td width="27%" align="left" style="padding-left: 2px;" height="25">Sub-Sub Job</td>
      	<td width="14%" align="center" style="padding-left: 2px;" height="25">Edit</td>
      </tr>
      <s:iterator value="jobList" status="indx">
      <s:if test="#indx.even == true">
        <tr bgcolor="#eeeeee">
      </s:if>
      <s:if test="#indx.odd == true">
        <tr>
      </s:if>
        <td align="left" style="padding-left: 5px;" height="25"><s:property value="#indx.count" />.</td>
      	<td align="left" style="padding-left: 2px;" height="25">
      	&nbsp;&nbsp; 
      	<s:if test="mainJobVisibility==1">      	      		      	
	      	<img id="mainEnableImg<s:property value="#indx.count" />" src='/BMREG_WEB/resources/images/enableDark.png' border='0' height="18" width="18" />	      	 
	      	&nbsp;
	      	<a href="javascript:void(0)" onclick="saveJobStatus(<s:property value="categoryId" />,0)" style="text-decoration: none;"> 
	      	<img id="mainDisableImg<s:property value="#indx.count" />" src='/BMREG_WEB/resources/images/disableLight.png' border='0' height="18" width="18" onmouseover="changeImage('mainDisableImg<s:property value="#indx.count" />','/BMREG_WEB/resources/images/disableDark.png')" onmouseout="changeImage('mainDisableImg<s:property value="#indx.count" />','/BMREG_WEB/resources/images/disableLight.png')"/>
	      	</a>
	      	<s:property value="categoryName" />
	      	(<s:property value="categoryId" />)
      	</s:if>
      	<s:if test="mainJobVisibility==0">      	
	      	<a href="javascript:void(0)" onclick="saveJobStatus(<s:property value="categoryId" />,1)" style="text-decoration: none;">
	      	<img id="mainEnableImg<s:property value="#indx.count" />" src='/BMREG_WEB/resources/images/enableLight.png' border='0' height="18" width="18"  onmouseover="changeImage('mainEnableImg<s:property value="#indx.count" />','/BMREG_WEB/resources/images/enableDark.png')" onmouseout="changeImage('mainEnableImg<s:property value="#indx.count" />','/BMREG_WEB/resources/images/enableLight.png')"/>
	      	</a> 
	      	&nbsp; 
	      	<img id="mainDisableImg<s:property value="#indx.count" />" src='/BMREG_WEB/resources/images/disableDark.png' border='0' height="18" width="18" />
	      	<font color="#8E8E8E">
		      	<s:property value="categoryName" />
		      	(<s:property value="categoryId" />)
	      	</font>
      	</s:if>
      	      	
      	</td>
      	<td align="left" style="padding-left: 2px;" height="25">
      	&nbsp;&nbsp; 
      	<s:if test="subJobVisibility==1">      	      		      	
	      	<img id="subEnableImg<s:property value="#indx.count" />" src='/BMREG_WEB/resources/images/enableDark.png' border='0' height="18" width="18" />	      	 
	      	&nbsp;
	      	<a href="javascript:void(0)" onclick="saveJobStatus(<s:property value="subCategoryId" />,0)" style="text-decoration: none;"> 
	      	<img id="subDisableImg<s:property value="#indx.count" />" src='/BMREG_WEB/resources/images/disableLight.png' border='0' height="18" width="18" onmouseover="changeImage('subDisableImg<s:property value="#indx.count" />','/BMREG_WEB/resources/images/disableDark.png')" onmouseout="changeImage('subDisableImg<s:property value="#indx.count" />','/BMREG_WEB/resources/images/disableLight.png')"/>
	      	</a>
	      	<s:property value="subCategoryName" />
	      	(<s:property value="subCategoryId" />) 
      	</s:if>
      	<s:if test="subJobVisibility==0">      	
	      	<a href="javascript:void(0)" onclick="saveJobStatus(<s:property value="subCategoryId" />,1)" style="text-decoration: none;">
	      	<img id="subEnableImg<s:property value="#indx.count" />" src='/BMREG_WEB/resources/images/enableLight.png' border='0' height="18" width="18"  onmouseover="changeImage('subEnableImg<s:property value="#indx.count" />','/BMREG_WEB/resources/images/enableDark.png')" onmouseout="changeImage('subEnableImg<s:property value="#indx.count" />','/BMREG_WEB/resources/images/enableLight.png')"/>
	      	</a> 
	      	&nbsp; 
	      	<img id="subDisableImg<s:property value="#indx.count" />" src='/BMREG_WEB/resources/images/disableDark.png' border='0' height="18" width="18" />
	      	<font color="#8E8E8E"> 
		      	<s:property value="subCategoryName" />
		      	(<s:property value="subCategoryId" />)
	      	</font>
      	</s:if>
      	     	
      	</td>
      	<td align="left" style="padding-left: 2px;" height="25">
      	 
      	<s:if test="subSubCategoryId!=0">
      	 &nbsp;&nbsp; 
      	<s:if test="subSubJobVisibility==1">      	      		      	
	      	<img id="subSubEnableImg<s:property value="#indx.count" />" src='/BMREG_WEB/resources/images/enableDark.png' border='0' height="18" width="18" />	      	 
	      	&nbsp;
	      	<a href="javascript:void(0)" onclick="saveJobStatus(<s:property value="subSubCategoryId" />,0)" style="text-decoration: none;"> 
	      	<img id="subSubDisableImg<s:property value="#indx.count" />" src='/BMREG_WEB/resources/images/disableLight.png' border='0' height="18" width="18" onmouseover="changeImage('subSubDisableImg<s:property value="#indx.count" />','/BMREG_WEB/resources/images/disableDark.png')" onmouseout="changeImage('subSubDisableImg<s:property value="#indx.count" />','/BMREG_WEB/resources/images/disableLight.png')"/>
	      	</a>
	      	<s:property value="subSubCategoryName" />
	      	(<s:property value="subSubCategoryId" />) 
      	</s:if>
      	<s:if test="subSubJobVisibility==0">      	
	      	<a href="javascript:void(0)" onclick="saveJobStatus(<s:property value="subSubCategoryId" />,1)" style="text-decoration: none;">
	      	<img id="subSubEnableImg<s:property value="#indx.count" />" src='/BMREG_WEB/resources/images/enableLight.png' border='0' height="18" width="18"  onmouseover="changeImage('subSubEnableImg<s:property value="#indx.count" />','/BMREG_WEB/resources/images/enableDark.png')" onmouseout="changeImage('subSubEnableImg<s:property value="#indx.count" />','/BMREG_WEB/resources/images/enableLight.png')"/>
	      	</a> 
	      	&nbsp; 
	      	<img id="subSubDisableImg<s:property value="#indx.count" />" src='/BMREG_WEB/resources/images/disableDark.png' border='0' height="18" width="18" />
	      	<font color="#8E8E8E"> 
		      	<s:property value="subSubCategoryName" />
		      	(<s:property value="subSubCategoryId" />)
	      	</font>
      	</s:if>
      	</s:if>
      	 
      	</td>
      	
      	<td align="left" style="padding-left: 10px;text-align: center;" height="25">
      	<a href="javascript:void(0)" onclick="loadEditPanel('<s:property value="categoryId" />','<s:property value="subCategoryId" />','<s:property value="subSubCategoryId" />')">
	      	<img id="edit<s:property value="#indx.count" />" src='/BMREG_WEB/resources/images/edit.png' border='0' height="18" width="18" />
  		</a>   
      	</td>
      	      	
        </tr> 
      </s:iterator>
      
      
    </table>
</html>
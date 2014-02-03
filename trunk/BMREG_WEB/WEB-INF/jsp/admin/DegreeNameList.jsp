<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="org.controller.registration.*" %>
<%@ page import="java.util.ArrayList" %>
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

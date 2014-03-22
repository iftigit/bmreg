<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="org.controller.registration.*" %>
<%@ page import="java.util.ArrayList" %>
    
<table width="98%" align="center" border="0" cellpadding="2" cellspacing="0" style="border: 1px solid grey;">     
     <tr bgcolor="#F1F1F1">
     
        <td width="5%" align="center" style="padding-left: 10px;" height="25">SL</td>
      	<td width="35%" align="left" style="padding-left: 10px;" height="25">Company Name</td>
      	<td width="10%" align="left" style="padding-left: 10px;" height="25">License No</td>
      	<td width="10%" align="left" style="padding-left: 10px;" height="25">Company Type</td>
      	<td width="20%" align="left" style="padding-left: 10px;" height="25">CoE</td>
      	<td width="5%" align="center" style="padding-left: 10px;" height="25">View</td>
      	<td width="5%" align="center" style="padding-left: 10px;" height="25">Edit</td>
      </tr>
      <s:iterator value="agentList" status="status">
        <tr>
        <td align="left" style="padding-left: 10px;" height="25"><s:property value="%{#status.count}" /></td>
      	<td align="left" style="padding-left: 10px;" height="25"><s:property value="companyName" /></td>
      	<td align="left" style="padding-left: 10px;" height="25"><s:property value="licenseNumber" /></td>
      	<td align="left" style="padding-left: 10px;" height="25"><s:property value="companyType" /></td>
      	<td align="left" style="padding-left: 10px;" height="25"><s:property value="ceoName" /></td>
      	<td align="left" style="padding-left: 10px;" height="25"></td>
      	<td align="left" style="padding-left: 10px;" height="25"></td>      	
        </tr> 
      </s:iterator>           
</table>
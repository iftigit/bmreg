<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<br><%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="org.controller.registration.*" %>
<%@ page import="java.util.ArrayList" %>

<table width="98%" align="center" border="0" cellpadding="2" cellspacing="0" style="border: 1px solid grey;">     
     <tr bgcolor="#F1F1F1">
     
        <td width="10%" align="center" style="padding-left: 10px;" height="25">SL</td> 
      	<td width="80%" align="left" style="padding-left: 10px;" height="25">Trade Name</td>
      	<td width="10%" align="left" style="padding-left: 10px;" height="25">View</td>
      </tr>
      <s:iterator value="tradeList" status="status">
        <tr>
        <td align="left" style="padding-left: 10px;" height="25"><s:property value="%{#status.count}" /></td>
      	<td align="left" style="padding-left: 10px;" height="25"><s:property value="tradeName" /></td>
      	<td align="center" style="padding-left: 10px;" height="25">
      		<a onclick="fetchTrade('<s:property value="id" />')" href="#listTopAnchor">View</a>
      	</td>      	
        </tr> 
      </s:iterator>           
</table>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="org.controller.registration.*" %>
<%@ page import="java.util.ArrayList" %>
<table width="98%" align="center" border="0" cellpadding="2" cellspacing="0" style="border: 1px solid grey;">
     
     <tr bgcolor="#F1F1F1">     
        <td width="5%" align="center" style="padding-left: 10px;" height="25"></td>
      	<td width="10%" align="left" style="padding-left: 10px;" height="25">UserId</td>
      	<td width="15%" align="left" style="padding-left: 10px;" height="25">Password</td>
      	<td width="15%" align="left" style="padding-left: 10px;" height="25">From<br/>
      	<font style="font-size: 10px;color: maroon;">[dd-MM-YYYY HH:MI:SS]</font> 
      	</td>
      	<td width="15%" align="left" style="padding-left: 10px;" height="25">To<br/>
      	<font style="font-size: 10px;color: maroon;">[dd-MM-YYYY HH:MI:SS]</font> 
      	</td>
      	<td width="15%" align="center" style="padding-left: 10px;" height="25">Save</td>      	
      	<td width="15%" align="center" style="padding-left: 10px;" height="25">Save & SMS</td>
      </tr>
      <s:iterator value="updateUserList" status="status" >
        <tr>
        <td align="left" style="padding-left: 10px;" height="25">
        	<input type="checkbox" name="approveUserList" value="<s:property value='userId' />" />
        </td>
      	<td align="left" style="padding-left: 10px;" height="25"><s:property value="userId" /></td>
      	<td align="left" style="padding-left: 10px;" height="25"><s:property value="password" /></td>
      	<td align="left" style="padding-left: 10px;" height="25">
      		<input type="text" value="<s:property value='formDate' />" style="width: 120px;border: 1px solid grey;" id="startDate<s:property value='%{#status.index}' />" />      		
      	</td>
      	<td align="left" style="padding-left: 10px;" height="25">
      		<input type="text" value="<s:property value='toDate' />" style="width: 120px;border: 1px solid grey;" id="endDate<s:property value='%{#status.index}' />"/>
      	</td>
      	<td width="15%" align="center" style="padding-left: 10px;" height="25">
      	<a href="javascript:void" onclick="saveUser('<s:property value='userId' />','<s:property value="%{#status.index}" />')"><img src="/BMREG_WEB/resources/images/save.png?a=badsf" /></a>
      	</td>
      	<td align="center" style="padding-left: 10px;" height="25">
      	<a  href="javascript:void" onclick="saveUserAndSendSms('<s:property value='userId' />','<s:property value="%{#status.index}" />')"><img src="/BMREG_WEB/resources/images/sms.png?b=s" /></a>
      	</td>
        </tr> 
      </s:iterator>
      
      
    </table>
    

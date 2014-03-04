<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="org.controller.registration.*" %>
<%@ page import="java.util.ArrayList" %>
    
<table width="98%" align="center" border="0" cellpadding="2" cellspacing="0" style="border: 1px solid grey;">     
     <tr bgcolor="#F1F1F1">
     
        <td width="5%" align="left" style="padding-left: 10px;" height="25">SL</td>
      	<td width="10%" align="left" style="padding-left: 10px;" height="25">Work Order</td>
      	<td width="10%" align="left" style="padding-left: 10px;" height="25">Job Preference</td>
      	<td width="10%" align="center" height="25">Gender</td>
      	<td width="15%" align="left" style="padding-left: 10px;" height="25">Job Exp.</td>
      	<td width="5%" align="center"  height="25">Status</td>
      	<td width="5%" align="center"  height="25">View</td>
      	<td width="5%" align="center"  height="25">Download</td>
      </tr>
      <s:iterator value="selectionList" status="status">
        <tr>
        <td align="left" style="padding-left: 10px;" height="25"><s:property value="%{#status.count}" /></td>
      	<td align="left" style="padding-left: 10px;" height="25"><s:property value="workOrder" /></td>
      	<td align="left" style="padding-left: 10px;" height="25"><s:property value="jobPrefererence" /></td>
      	<td align="center" 							 height="25"><s:property value="gender" /></td>
      	<td align="left" style="padding-left: 10px;" height="25"><s:property value="jobkExperience" /></td>
      	<td align="center"                           height="25"><s:property value="status" /></td>
      	<td align="center"  						 height="25">
      	<a href="fetchSelectionDetail.action?selectionId=<s:property value='selectionId'/>">View</a>
      	</td>
      	<td width="5%" align="center"  height="25">
      		<a href="selectionReportAction.action?selectionId=<s:property value='selectionId'/>">
      			Download
      		</a>
      	</td>   	
        </tr> 
      </s:iterator>           
</table>
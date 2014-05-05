<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="org.controller.registration.*" %>
<%@ page import="java.util.ArrayList" %>
<script type="text/javascript">
function showPrintPreview(demoUserId,tokenId){
	window.open('tokenPrintPreview.action?demoUserId='+demoUserId+'&tokenId='+tokenId,'1397836688296','width=700,height=500,toolbar=0,menubar=0,location=0,status=1,scrollbars=1,resizable=1,left=0,top=0');
}
</script>    
    
<table width="98%" align="center" border="0" cellpadding="2" cellspacing="0" style="border: 1px solid grey;">     
      <tr bgcolor="#F1F1F1">     
        <td width="5%" align="left" style="padding-left: 10px;" height="25">SL</td>
      	<td width="10%" align="left" style="padding-left: 10px;" height="25">Token Id</td>
      	<td width="10%" align="left" style="padding-left: 10px;" height="25">Date</td>
      	<td width="60%" align="left" style="padding-left: 10px;" height="25">Tokens</td>
      	<td width="15%" align="center" height="25">Print<br/>Preview</td>
      </tr>
      <s:iterator value="tokenList" status="status">
        <s:if test="#status.even == true">
	        	<tr bgcolor="#eeeeee">
	      	</s:if>
	      	<s:if test="#indx.odd == true">
	        	<tr>
	       </s:if>
        <td align="left" style="padding-left: 10px;" height="25" valign="top"><s:property value="%{#status.count}" /></td>
      	<td align="left" style="padding-left: 10px;" height="25" valign="top"><s:property value="tokenId" /></td>
      	<td align="left" style="padding-left: 10px;" height="25" valign="top"><s:property value="insertedOn" /></td>
      	<td align="left" style="padding-left: 10px;" height="25" valign="top"><s:property value="tokenListString" /></td>
      	<td align="left" style="text-align: center;" height="25" valign="top">
      	 <a href="javascript:void" onclick="showPrintPreview('<s:property value="demoUserId" />',<s:property value="tokenId" />)">Preview</a>
      	</td>
        </tr> 
      </s:iterator>           
</table>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="org.controller.registration.*" %>
<%@ page import="java.util.ArrayList" %>
   <table width="98%" align="center" border="0" cellpadding="2" cellspacing="0" style="border: 1px solid grey;">     
     <tr bgcolor="#F1F1F1">
     
        <td width="10%" align="center"  height="25">SL</td>
      	<td width="10%" align="left"    height="25">Reg Suffix</td>
      	<td width="30%" align="left"  height="25">Type Name</td>
      	<td width="30%" align="left"  height="25">Type Desc</td>
      	<td width="10%" align="center"  height="25">Is Active</td>
      	<td width="10%" align="center"  height="25">Edit/Delete</td>
      </tr>            
      <s:iterator value="regTypeList" id="type" status="indx">
        <tr>
        <td align="center"  height="25">
        <s:property value="%{#indx.count}" />.
        </td>
      	<td align="left" style="padding-left: 10px;" height="25">      		
      		<input type="text" name="rList[<s:property value="%{#indx.index}" />].regIdSuffix" value="<s:property value="regIdSuffix" />" id="regIdSuffix<s:property value='%{#indx.count}' />"  style="border: 1px solid grey;width: 40px;" />
      	</td>
      	<td align="center" style="" height="25">
      		<input type="text" name="rList[<s:property value="%{#indx.index}" />].typeName" value="<s:property value="typeName" />" id="typeName<s:property value='%{#indx.count}' />" style="border: 1px solid grey;width: 180px;text-align: left;"/>
      	</td>
      	<td align="left" style="padding-left: 10px;" height="25">      		
      		<input type="text" name="rList[<s:property value="%{#indx.index}" />].typeDesc" value="<s:property value="typeDesc" />" id="typeDesc<s:property value='%{#indx.count}' />"  style="border: 1px solid grey;width: 180px;text-align: left;" />
      	</td>
      	<td align="center" height="25">      	 
	      	<s:if test="#type.isActive==1">
			  <input type="checkbox" name="rList[<s:property value="%{#indx.index}" />].isActive" checked="checked" id="isActive<s:property value="%{#indx.count}" />" value="1" onclick="setVisibilityValue(this.id)" />
			</s:if>
			<s:elseif test="#type.isActive==0">
			  <input type="checkbox" name="rList[<s:property value="%{#indx.index}" />].isActive" id="isActive<s:property value="%{#indx.count}" />" value="0"  onclick="setVisibilityValue(this.id)" />
			</s:elseif>
		</td>
      	<td align="center"  height="25">
      	<a href="#" onclick='updateRegType(<s:property value="typeId" />,<s:property value="%{#indx.count}" />)'  style="text-decoration: none;">
	      	<img src='/BMREG_WEB/resources/images/edit.png' border='0' width="20" height="20"/>
      	</a>
      	&nbsp;&nbsp;&nbsp;
      	<a href="#" onclick='deleteRegType(<s:property value="typeId" />)'  style="text-decoration: none;">
      	 <img src='/BMREG_WEB/resources/images/delete.png' border='0'  width="20" height="20"/>
      	 </a>
      	</td>
        </tr> 
      </s:iterator>
      
      
    </table>
    
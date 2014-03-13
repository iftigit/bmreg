<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="org.controller.registration.*" %>
<%@ page import="java.util.ArrayList" %>

<table width="85%" align="center" border="0" cellpadding="0" cellspacing="0" style="border: 1px solid grey;">
<tr>
	<td>Jobseeker Id</td>
	<td>Full Name</td>
	<td>Father Name</td>
	<td>Mother Name</td>
</tr>
<s:iterator value="registrationList" id="registration" status="indx">
<tr>
	<td align="left">
		<a href="editRegInfo">
			<s:property value="jobseekerNumber" />
		</a>
	</td>
	<td align="left"><s:property value="empFullName" /></td>
	<td align="left"><s:property value="empFatherName" /></td>
	<td align="left"><s:property value="empMotherName" /></td>
</tr>
</s:iterator>
</table>
</html>

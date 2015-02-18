
<%@page import="org.apache.struts2.ServletActionContext"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>

<head>
<meta content="text/html;charset=utf-8" http-equiv="Content-Type">
<meta content="utf-8" http-equiv="encoding">
<title>Job Seeker Registration Card</title>
 <link rel="stylesheet" href="/BMREG_WEB/resources/css/style.css" />	
<script type="text/javascript" src="/BMREG_WEB/resources/js/util/util.js"></script>
 <link type="text/css" rel="Stylesheet" href="/BMREG_WEB/resources/js/lib/jquery.validity.1.2.0/jquery.validity.css" />
  
</head>

<body style="margin: 0px;">
<div style="width: 100%;height: 100px;border-bottom: 1px solid #006219;">
<center>
<div style="width: 1000px;height: 100px;border-right: 1px solid #006219;border-left: 1px solid #006219;">
	<div style="float: left; margin-top: 20px;width: 100px;">
	 <img src="/BMREG_WEB/resources/images/bagladesh_logo.gif" width="60" height="60" />
	</div>
	<div style="float: left;margin-left: 30px;color: black;margin-top: 15px;text-align: left;">
	 	<div style="font-size: 27px;font-weight: bold;">Bureau of Manpower, Employment & Training (BMET)</div>
	</div>
</div>
</center>
</div>

<form action="downloadRegCard.action" method="post">
<center>
<div id="Example_F" style="height: 580px;width: 1000px;margin-top: 10px;">
<table width="80%" border="0" align="center" style="padding-top: 50px;">
<tr> 
  <td align="right" width="40%" style="font-size: 25px;font-weight: bold;color: blue;">
  Registration Id:
  </td>
  <td align="left" width="60%" style="padding-left: 10px;padding-top: 20px;">
   <input type="text" style="width: 300px;height: 30px;border: 1px solid grey;font-size: 20px;text-align: center;" name="registrationId" id="registrationId" value="<s:property value='registrationId' />"/>
   <br/>
   <div style="color: red"><s:label name="Err_regId" ></s:label></div>
  </td>
</tr>
<s:if test="%{#session.loggedInUser==null}">
<tr> 
  <td align="right" width="40%" style="font-size: 25px;font-weight: bold;color: blue;">
  Birth Date:
  </td>
  <td align="left" width="60%" style="padding-left: 10px;">
   <select name="day" style="width:60px;height:30px;border:1px solid grey;">
   <option value="">Day</option>
     <%
      for(int i=1;i<=31;i++)
       {
    %>
     <option value="<%=i%>"><%=i%></option>
    <%    
       }
    %>
   </select>
   &nbsp;&nbsp;&nbsp;
   <select name="month" style="width:60px;height:30px;border:1px solid grey;">
   <option value="">Month</option>
     <%
      for(int i=1;i<=12;i++)
       {
    %>
     <option value="<%=i%>"><%=i%></option>
    <%    
       }
    %>
   </select>
   &nbsp;&nbsp;&nbsp;
   <select name="year" style="width:100px;height:30px;border:1px solid grey;">
   <option value="">Year</option>
     <%
      for(int i=1970;i<=2000;i++)
       {
    %>
     <option value="<%=i%>"><%=i%></option>
    <%    
       }
    %>
   </select>
  </td>
</tr>
</s:if>

<tr> 
  <td align="right" width="40%" style="font-size: 25px;font-weight: bold;color: blue;padding-top: 20px;">
  Security Code:
  </td>
  <td align="left" width="60%" style="padding-left: 10px;padding-top: 20px;">  
   <input type="text" style="width: 300px;height: 30px;border: 1px solid grey;font-size: 20px;text-align: center;" name="captchaCode" id="captchaCode" />
  </td>
</tr>
<tr> 
  <td align="right" width="40%" style="font-size: 25px;font-weight: bold;color: blue;padding-top: 50px;">
  
  </td>
  <td align="left" width="60%" style="padding-left: 10px;padding-top: 50px;">
    <img src="BMREG_WEB/CaptchaServlet.cap" alt="no image" id="captchaImage" name="captchaImage" /> 
  <a href="#" onclick="refreshCaptcha()">
  	<img src="/BMREG_WEB/resources/images/refresh_icon.gif" />
  </a>
    <br/>
    <div style="color: red"><s:label name="Err_captchaError" ></s:label></div>
  </td>
</tr>

<tr> 
  <td align="right" width="40%" style="font-size: 25px;font-weight: bold;color: blue;padding-top: 50px;">
  
  </td>
  <td align="left" width="60%" style="padding-left: 10px;padding-top: 60px;">
    	
	<input type="submit" class="submitButton" name="Downalod_Card" value="Download Registration Card" style="width: 250px;"  />
	&nbsp;&nbsp;&nbsp;&nbsp;

	<s:if test="%{#session.loggedInUser!=null}">
	<input type="button" class="submitButton" name="Registratoin_Home" value="Registration Home" style="width: 170px;" onclick="window.location='regHomeAction.action'"  />
    </s:if>
    		
    	
  </td>
</tr>

</table>


</div>
</center>
</form>
<script type="text/javascript">
//refreshCaptcha('rc=<s:property value="rc"/>&ft=1');
 
</script>

<div id="footer">
&copy;BMET-2015. All right reserved.
</div>
</body>

</html>
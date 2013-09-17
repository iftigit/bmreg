<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="org.controller.registration.*" %>
<%@ page import="java.util.ArrayList" %>
<head>


<meta content="text/html;charset=utf-8" http-equiv="Content-Type">
<meta content="utf-8" http-equiv="encoding">
<title>G2G Project - User Home</title>
 <link rel="stylesheet" href="/BMREG_WEB/resources/css/style.css" />	
  <script type="text/javascript" src="/BMREG_WEB/resources/js/lib/jquery-1.6.4.min.js"></script> 
<script type="text/javascript" src="/BMREG_WEB/resources/js/util/util.js"></script>
<script type="text/javascript" src="/BMREG_WEB/resources/js/util/numeric.js"></script>
 <script type="text/javascript" src="/BMREG_WEB/resources/js/util/util.js"></script>
 
 <link type="text/css" rel="Stylesheet" href="/BMREG_WEB/resources/js/lib/jquery.validity.1.2.0/jquery.validity.css" />
        <script type="text/javascript" src="/BMREG_WEB/resources/js/lib/jquery.validity.1.2.0/jQuery.validity.js"></script>
 
<script type="text/javascript">

</script>
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
	 	<div style="font-size: 20px;margin-top: 10px;">Interview Report</div>
	</div>
</div>
</center>
</div>
<center>
<br/>
<div class="box" style="margin-top: 20px;width: 1000px;text-align: center;">
    <h3>Interview Report Home</h3>
    
<form id="jobSeekerForm" name="jobSeekerForm" method="post" action="fetchEmpInfoForInterview.action">    
    <table width="100%" border="0" cellspacing="1" class="infoTable">
    
    <tr>
     <td style="text-align: center">
     	<b>Job-Seeker Number :</b> <input type="text" value="<s:property value='interviewInfo.jobSeekerNumber'/>" id="jobSeekerNumber" name="jobSeekerNumber" maxlength="14" style="width: 250px;border: 2px solid black;height: 30px;font-size: 20px;color: maroon;text-align: center;" onblur="fetchMedicalInfo(this.value)" />
     	<input type="submit" value="Go" name="go" style="width: 80px;height: 30px;" />
     </td>
    </tr>
   
    </table>
    <br/><br/>
    <center><b><s:property value="message"/></b></center>
</form>    
    
    <table width="100%" border="0" cellspacing="1" class="infoTable">
    
    <tr>
     <td style="text-align: center">
     	
     </td>
    </tr>
    
     <tr>
     <td style="text-align: center;height: 400px;" valign="top">
       <s:if test="{interviewInfo.jobSeekerNumber==null}">
          <form id="vivaStatusForm" name="vivaStatusForm" method="post" action="saveVivaStatus.action">
     		<table border="0" width="98%" align="center" style="border: 1px solid gray;" cellpadding="0" cellspacing="0">
     		<tr>
     		 	<td colspan="4" style="background-color: #CCCCCC;color: blue;font-weight: bold;">Personal Information</td>
     		 </tr>
     		 <tr>
     		 	<td width="20%" align="left" style="font-weight: bold;">Job-Seeker Number</td>
     		 	<td width="30%" align="left"><s:property value='interviewInfo.jobSeekerNumber'/>
     		 	<input type="hidden" value="<s:property value='interviewInfo.jobSeekerNumber'/>" name="interviewInfo.jobSeekerNumber" />
     		 	</td>
     		 	<td width="20%" align="left" style="font-weight: bold;">Job-Seeker Name</td>
     		 	<td width="30%" align="left"><s:property value='interviewInfo.jobSeekerName'/></td>
     		 	
     		 </tr>
     		 <tr>
     		 	<td align="left" style="font-weight: bold;">Father Name</td>
     		 	<td align="left"><s:property value='interviewInfo.fatherName'/></td>
     		 	<td align="left" style="font-weight: bold;">Mother Name</td>
     		 	<td align="left"><s:property value='interviewInfo.motherName'/></td>
     		 	
     		 </tr>
     		 <tr>
     		 	<td align="left" style="font-weight: bold;">Birth Date</td>
     		 	<td align="left"><s:property value='interviewInfo.birthDate'/></td>
     		 	<td align="left" style="font-weight: bold;">Age</td>
     		 	<td align="left"><s:property value='interviewInfo.age'/> Years</td>
     		 	
     		 </tr>
     		 <tr>
     		 	<td align="left" style="font-weight: bold;">Gender</td>
     		 	<td align="left"><s:property value='interviewInfo.gender'/></td>
     		 	<td align="left" style="font-weight: bold;"></td>
     		 	<td align="left"></td>
     		 	
     		 </tr>
     		 <tr>
     		 	<td align="left" style="font-weight: bold;">Weight</td>
     		 	<td align="left"><s:property value='interviewInfo.weight'/> Kg</td>
     		 	<td align="left" style="font-weight: bold;">Height</td>
     		 	<td align="left"><s:property value='interviewInfo.height'/></td>
     		 	
     		 </tr>
     		 <tr>
     		 	<td colspan="4" style="background-color: #CCCCCC;color: blue;font-weight: bold;">Address Information</td>
     		 </tr>
     		 <tr>
     		 	<td align="left" style="font-weight: bold;">Division</td>
     		 	<td align="left"><s:property value='interviewInfo.perDivision'/></td>
     		 	<td align="left" style="font-weight: bold;">District</td>
     		 	<td align="left"><s:property value='interviewInfo.perDistrict'/></td>     		 	
     		 </tr>
     		 <tr>
     		 	<td align="left" style="font-weight: bold;">Upazilla</td>
     		 	<td align="left"><s:property value='interviewInfo.perUpazilla'/></td>
     		 	<td align="left" style="font-weight: bold;">Union</td>
     		 	<td align="left"><s:property value='interviewInfo.perUnion'/></td>     		 	
     		 </tr>
     		 <tr>
     		 	<td align="left" style="font-weight: bold;">Street</td>
     		 	<td align="left"><s:property value='interviewInfo.perStreet'/></td>
     		 	<td align="left" style="font-weight: bold;"></td>
     		 	<td align="left"></td>     		 	
     		 </tr>
     		 <tr>
     		 	<td align="left" style="font-weight: bold;">Post Code</td>
     		 	<td align="left"><s:property value='interviewInfo.perPostCode'/></td>
     		 	<td align="left" style="font-weight: bold;">Post Office</td>
     		 	<td align="left"><s:property value='interviewInfo.perPostOffice'/></td>     		 	
     		 </tr>
     		 
     		 <tr>
     		 	<td colspan="4" style="background-color: #CCCCCC;color: blue;font-weight: bold;">Viva Status</td>
     		 </tr>
     		 
     		 <!-- 
     		 <tr>
     		 	<td align="left" style="font-weight: bold;">Nationality</td>
     		 	<td align="left">
     		 		<table width="70%" align="left">
     		 			<tr>
     		 				<td width="50%" align="left" style="padding-left: 0px;background-color: green;color: white;font-weight: bold;" >
     		 					<input type="radio" name="nationality" /> Ok
     		 				</td>
     		 				<td width="50%" align="left" style="padding-left: 0px;background-color: red;color: white;font-weight: bold;">
     		 					<input type="radio" name="nationality" /> Not Ok
     		 				</td>
     		 			</tr>
     		 		</table>
     		 	</td>
     		 	<td align="left" style="font-weight: bold;">Willingness</td>
     		 	<td align="left">
     		 	<table width="70%" align="left">
     		 			<tr>
     		 				<td width="50%" align="left" style="padding-left: 0px;background-color: green;color: white;font-weight: bold;" >
     		 					<input type="radio" name="nationality" /> Ok
     		 				</td>
     		 				<td width="50%" align="left" style="padding-left: 0px;background-color: red;color: white;font-weight: bold;">
     		 					<input type="radio" name="nationality" /> Not Ok
     		 				</td>
     		 			</tr>
     		 		</table>
     		 	</td>     		 	
     		 </tr>
     		 
     		 <tr>
     		 	<td align="left" style="font-weight: bold;">Age Ok</td>
     		 	<td align="left">
     		 		<table width="70%" align="left">
     		 			<tr>
     		 				<td width="50%" align="left" style="padding-left: 0px;background-color: green;color: white;font-weight: bold;" >
     		 					<input type="radio" name="nationality" /> Ok
     		 				</td>
     		 				<td width="50%" align="left" style="padding-left: 0px;background-color: red;color: white;font-weight: bold;">
     		 					<input type="radio" name="nationality" /> Not Ok
     		 				</td>
     		 			</tr>
     		 		</table>
     		 	</td>
     		 	<td align="left" style="font-weight: bold;">Village People</td>
     		 	<td align="left">
     		 	<table width="70%" align="left">
     		 			<tr>
     		 				<td width="50%" align="left" style="padding-left: 0px;background-color: green;color: white;font-weight: bold;" >
     		 					<input type="radio" name="nationality" /> Ok
     		 				</td>
     		 				<td width="50%" align="left" style="padding-left: 0px;background-color: red;color: white;font-weight: bold;">
     		 					<input type="radio" name="nationality" /> Not Ok
     		 				</td>
     		 			</tr>
     		 		</table>
     		 	</td>     		 	
     		 </tr>
     		 
     		 <tr>
     		 	<td align="left" style="font-weight: bold;">Weight Lifting</td>
     		 	<td align="left">
     		 		<table width="70%" align="left">
     		 			<tr>
     		 				<td width="50%" align="left" style="padding-left: 0px;background-color: green;color: white;font-weight: bold;" >
     		 					<input type="radio" name="nationality" /> Ok
     		 				</td>
     		 				<td width="50%" align="left" style="padding-left: 0px;background-color: red;color: white;font-weight: bold;">
     		 					<input type="radio" name="nationality" /> Not Ok
     		 				</td>
     		 			</tr>
     		 		</table>
     		 	</td>
     		 	<td align="left" style="font-weight: bold;">Weight</td>
     		 	<td align="left">
     		 	<table width="70%" align="left">
     		 			<tr>
     		 				<td width="50%" align="left" style="padding-left: 0px;background-color: green;color: white;font-weight: bold;" >
     		 					<input type="radio" name="nationality" /> Ok
     		 				</td>
     		 				<td width="50%" align="left" style="padding-left: 0px;background-color: red;color: white;font-weight: bold;">
     		 					<input type="radio" name="nationality" /> Not Ok
     		 				</td>
     		 			</tr>
     		 		</table>
     		 	</td>     		 	
     		 </tr>
     		
     		<tr>
     		 	<td align="left" style="font-weight: bold;">Height</td>
     		 	<td align="left">
     		 		<table width="70%" align="left">
     		 			<tr>
     		 				<td width="50%" align="left" style="padding-left: 0px;background-color: green;color: white;font-weight: bold;" >
     		 					<input type="radio" name="nationality" /> Ok
     		 				</td>
     		 				<td width="50%" align="left" style="padding-left: 0px;background-color: red;color: white;font-weight: bold;">
     		 					<input type="radio" name="nationality" /> Not Ok
     		 				</td>
     		 			</tr>
     		 		</table>
     		 	</td>
     		 	<td align="left" style="font-weight: bold;">Weight</td>
     		 	<td align="left">
     		 	<table width="70%" align="left">
     		 			<tr>
     		 				<td width="50%" align="left" style="padding-left: 0px;background-color: green;color: white;font-weight: bold;" >
     		 					<input type="radio" name="nationality" /> Ok
     		 				</td>
     		 				<td width="50%" align="left" style="padding-left: 0px;background-color: red;color: white;font-weight: bold;">
     		 					<input type="radio" name="nationality" /> Not Ok
     		 				</td>
     		 			</tr>
     		 		</table>
     		 	</td>     		 	
     		 </tr>
     		 <tr>
     		 <td align="left" style="font-weight: bold;padding-top: 5px;">Comments</td>
     		 <td colspan="3" style="padding-top: 5px;">
     		 <textarea rows="5" cols="70" style="border: 1px solid grey;"></textarea>
     		 </td>
     		 </tr>
     		  -->
     		 <s:if test="interviewInfo.vivaStatus==null || interviewInfo.vivaStatus==''">
     		 <tr>
     		 <td style="font-weight: bold;font-size: 20px;"><input type="radio" name="interviewInfo.vivaStatus" value="Absent"  checked="checked"/> Absent  </td>
     		 <td style="font-weight: bold;font-size: 20px;"><input type="radio" name="interviewInfo.vivaStatus" value="Rejected" /> Rejected</td>
     		 </tr> 
     		 <tr>
     		 <td style="padding-top: 20px;">
     		 <input type="submit" name="Submit" value="Submit" style="width: 150px;height: 40px;color: green;font-weight: bold;"/>
     		 </td>
     		 <td align="center" colspan="2">
     		 	
     		 </td>
     		  <td align="left">
     		 	
     		 </td>
     		 
     		 </tr>
     		 </s:if>
     		 <s:else>
     		 <tr>
     		 <td style="font-weight: bold;font-size: 20px;">
     		 <s:property value="interviewInfo.vivaStatus"/>
     		 </td>
     		 <td style="font-weight: bold;font-size: 20px;"></td>
     		 </tr> 
     		 <tr>
     		 </s:else>
     		 
     		</table>
     		</form>
     </s:if>
     </td>
    </tr>
    </table>
    
    
   
    
  
</div>
<p style="height: 20px"></p>
</center>
<script type="text/javascript">
$('input').attr('autocomplete','off');   
</script>
<br/>
<center><a href="homePage.action" style="text-decoration: none;"><< Go Home >></a></center>

</body>

</html>
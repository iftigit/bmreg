<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>

<head>
<meta content="text/html;charset=utf-8" http-equiv="Content-Type">
<meta content="utf-8" http-equiv="encoding">
<title>G2G Job Seeker Registration Form</title>
 <link rel="stylesheet" href="/BMREG_WEB/resources/css/style.css" />
  <script type="text/javascript" src="/BMREG_WEB/resources/js/lib/prototype-1.6.0.2.js"></script>	
  <script type="text/javascript" src="/BMREG_WEB/resources/js/lib/jquery-1.6.4.min.js"></script>
  <script type="text/javascript" src="/BMREG_WEB/resources/js/util/util.js"></script>
  <script type="text/javascript" src="/BMREG_WEB/resources/js/util/numeric.js"></script>
  <script type="text/javascript" src="/BMREG_WEB/resources/js/util/browserDetect.js"></script>
  <script type="text/javascript" src="/BMREG_WEB/resources/js/address.js"></script>
  <script type="text/javascript" src="/BMREG_WEB/resources/js/regValidation.js"></script>
  <script type="text/javascript" src="/BMREG_WEB/resources/js/util/util.js"></script>
  <script type="text/javascript" src="/BMREG_WEB/resources/js/lib/jquery.corner.js"></script>
  <link type="text/css" rel="Stylesheet" href="/BMREG_WEB/resources/js/lib/jquery.validity.1.2.0/jquery.validity.css" />
  <script type="text/javascript" src="/BMREG_WEB/resources/js/lib/jquery.validity.1.2.0/jQuery.validity.js"></script>
        
  <script type="text/javascript" src="/BMREG_WEB/resources/js/lib/jscalendar-1.0/calendar.js"></script>
  <script type="text/javascript" src="/BMREG_WEB/resources/js/lib/jscalendar-1.0/calendar-setup.js"></script>
  <script type="text/javascript" src="/BMREG_WEB/resources/js/lib/jscalendar-1.0/lang/calendar-en.js"></script>
  <style type="text/css"> @import url("/BMREG_WEB/resources/js/lib/jscalendar-1.0/skins/aqua/theme.css"); </style>
    
        

 <script type="text/javascript">
 
   
   $.extend($.validity.patterns, {
    date:/^(0?[1-9]|[12][0-9]|3[01])[\-](0?[1-9]|1[012])[\-]\d{4}$/ 
   });
   
   $.extend($.validity.messages, {
    date:"<%= "#" %>{field} Date Value should be in DD-MM-YYYY format",
   });


   $(function() { 
  
                $("form").validity(function() {
                
                  

                    $("#FIRST_NAME")                      // The first input:    
        			.require("First Name is required.");                         // Required:
        			
        			//$("#LAST_NAME")                      // The first input:    
        			//.require("Last Name is required.");                         // Required:
        			
        			$("#FATHER_NAME")                      // The first input:    
        			.require("Father's Name is required.");                         // Required:

        			$("#MOTHER_NAME")                      // The first input:    
        			.require("Mother's Name is required.");                         // Required:
        			
        			$("#BIRTH_DATE")                      // The first input:    
        			.require("Birth Date is required.");                   
        			$("#BIRTH_DATE").match('date');      // Required:
        			
        			$("#WEIGHT")                      // The first input:    
        			.require("Weight is required.");                         // Required:
        			
        			$("#HEIGHT_FEET")                      // The first input:    
        			.require("Height Cannot be 0 feet.");                       // Required:
        			
        			$("#RELIGION").equalCheck("select","Select Religion.");
        			//$("#CATEGORY").equalCheck("select","Select Category.");
        			//$("#SUBCATEGORY").equalCheck("select","Select Sub Category.");
        			
        			$("#AGE").require().match("number").range(18, 45); 
        			$("#WEIGHT").match("number").greaterThanOrEqualTo(30, "Minimum Weight Requirement is 30 KG.");
    
    				$("#PASSPORT_ISSUE_DATE").match('date');
    				$("#PASSPORT_EXP_DATE").match('date'); 
        
        			
        			/*
        			$("#PERMANENT_POST").require("Post Office is required.");
        			$("#MAILING_POST").require("Post Office is required.");
        			$("#PERMANENT_POST_CODE").require("Post Code is required.");
        			$("#MAILING_POST_CODE").require("Post Code is required.");
        			*/
        			
        			$("#PERMANENT_MOBILE").require("Mobile Number required.");
        			//$("#MAILING_MOBILE").require("Mobile Number required.");
        			
        			
					$("#PERMANENT_DIV").equalCheck("select","Select Permanent Division.");
					$("#PERMANENT_DIV").equalCheck("","Select Permanent Division.");
					$("#MAILING_DIV").equalCheck("select","Select Mailing Division.");
					$("#MAILING_DIV").equalCheck("","Select Mailing Division.");

					$("#PERMANENT_DIST").equalCheck("select","Select Permanent District.");
					$("#PERMANENT_DIST").equalCheck("","Select Permanent District.");
					$("#MAILING_DIST").equalCheck("select","Select Mailing District.");
					$("#MAILING_DIST").equalCheck("","Select Mailing District.");


					$("#PERMANENT_THANA").equalCheck("select","Select Permanent Thana.");
					$("#PERMANENT_THANA").equalCheck("","Select Permanent Thana.");
					$("#MAILING_THANA").equalCheck("select","Select Mailing Thana.");
					$("#MAILING_THANA").equalCheck("","Select Mailing Thana.");
					
					$("#PERMANENT_UNION").equalCheck("select","Select Permanent Union.");
					$("#PERMANENT_UNION").equalCheck("","Select Permanent Union.");
					$("#MAILING_UNION").equalCheck("select","Select Mailing Union.");
					$("#MAILING_UNION").equalCheck("","Select Mailing Union.");
					
					
        			
        			$("#NOMINEE_NAME")                      // The first input:    
        			.require("Nominee Name is required.");                         // Required:
        			
        			$("#NOMINEE_RELATION")                      // The first input:    
        			.equalCheck("none","Nominee Relation is required.");
        			
        			$("#NOMINEE_ADDRESS")                      // The first input:    
        			.require("Nominee address is required.");                         // Required:
        			
        			$("#NOMINEE_PHONE")                      // The first input:    
        			.require("Nominee Phone Number required.");                         // Required:

        			$("#CONTACT_NAME")                      // The first input:    
        			.require("Contact Name required.");                         // Required:

        			$("#CONTACT_MOBILE")                      // The first input:    
        			.require("Contact Mobile required.");                         // Required:

        			
					//$("#captchaText").require("Please provide security code.");    
					
        			/*
        			$("#11c")
        			.assert(0,"The inputs can either have all even numbers or all odd numbers. Not mixed.");
        			*/
        			
                });
            
            /*    
            Calendar.setup({
              inputField    : "BIRTH_DATE",
              button        : "BIRTH_DATE_BTN",
              align         : "Tr",
              ifFormat    : '%d-%m-%Y'    // the date format
            });
               
            Calendar.setup({
              inputField    : "PASSPORT_ISSUE_DATE",
              button        : "PASSPORT_ISSUE_DATE_BTN",
              align         : "Tr",
              ifFormat    : '%d-%m-%Y'    // the date format
            });
            
               
            Calendar.setup({
              inputField    : "PASSPORT_EXP_DATE",
              button        : "PASSPORT_EXP_DATE_BTN",
              align         : "Tr",
              ifFormat    : '%d-%m-%Y'    // the date format
            });
            */
            
            });


 </script>
  
</head>
<!-- 
http://validity.thatscaptaintoyou.com/Demos/index.htm
 -->
<body style="margin: 0px;">
<form id="empRegForm" name="empRegForm" method="post" action="updateJobseekerDetail.action">
<div style="width: 100%;height: 100px;border-bottom: 1px solid #006219;">
<center>
<div style="width: 1000px;height: 100px;border-right: 1px solid #006219;border-left: 1px solid #006219;">
	<div style="float: left; margin-top: 20px;width: 100px;">
	 <img src="/BMREG_WEB/resources/images/bagladesh_logo.gif" width="60" height="60" />
	</div>
	<div style="float: left;margin-left: 30px;color: black;margin-top: 15px;text-align: left;">
	 	<div style="font-size: 27px;font-weight: bold;">Bureau of Manpower, Employment & Training (BMET)</div>
	 	<div style="font-size: 20px;margin-top: 10px;">Registration Information Update Form</div>
	</div>
</div>
</center>
</div>
<center>
<br/>
<font style="color:red"><s:label name="Common_Messages"></s:label></font>
<br/>
<div style="font-size: 20px;font-weight: bold;color: #FF00CC;">
 Job-Seeker Number : <font style="color: black"><s:property value="personalDTO.jobseekerNumber" /></font>
 <input type="hidden" value="<s:property value="personalDTO.jobseekerNumber" />" name="personalDTO.jobseekerNumber" />
</div>
<br/>
<div class="box">
    <h3>Personal Information</h3>
    <p>
    
    <table width="100%" border="0" cellspacing="1" class="infoTable">
    <tr>
     <td width="15%">Name<font color="red">*</font></td>
     <td width="85%" colspan="3" style="padding-bottom: 15px;">
     
     <div style="float:left;width: 250px;color: #4D4D4D;">First Name<br/>
     	<input type="text" tabindex="1" name="personalDTO.empFname" id="FIRST_NAME" maxlength="50"  value="<s:property value="personalDTO.empFname" />" class="nameTextBox" />
     </div>
     <div style="float:left;width: 250px;color: #4D4D4D">Middle Name<br/>
     	<input type="text" tabindex="2" name="personalDTO.empMname" id="MIDDLE_NAME"  maxlength="50" value="<s:property value="personalDTO.empMname" />"  class="nameTextBox" />
     </div>
     <div style="float:left;width: 250px;color: #4D4D4D">Last Name<br/>	
     	<input 	type="text" tabindex="3" name="personalDTO.empLname" id="LAST_NAME" maxlength="50" value="<s:property value="personalDTO.empLname" />"  class="nameTextBox" />
     </div>
     
     </td>
    </tr>
    <tr>
     <td width="15%">Father's Name<font color="red">*</font></td>
     <td width="35%">
     	<input type="text" tabindex="4" name="personalDTO.fatherName" id="FATHER_NAME" maxlength="100"  value="<s:property value="personalDTO.fatherName" />"  class="textBox"/>
     </td>
     <td width="15%">Mother's Name<font color="red">*</font></td>
     <td width="35%">
     	<input type="text" tabindex="5" name="personalDTO.motherName" id="MOTHER_NAME" maxlength="100" value="<s:property value="personalDTO.motherName" />" class="textBox"/>
     </td>
    </tr>
    <tr>
     <td>Spouse Name</td>
     <td><input type="text" tabindex="6" name="personalDTO.spousName" id="SPOUSE_NAME" maxlength="100" value="<s:property value="personalDTO.spousName" />" class="textBox"/></td>
     <td>National ID (If any)</td>
     <td><input type="text" tabindex="7" name="personalDTO.nationalId" id="NATIONALID" maxlength="20" value="<s:property value="personalDTO.nationalId" />" onkeypress="return numericOnly(event)" class="textBox"/></td>
    </tr>

 	<tr>
     <td>Birth Date<font color="red">*</font></td>
     <td>
     <input type="text" tabindex="8"  class="textBox" id="BIRTH_DATE"  value="<s:property value="personalDTO.birthDate" />"  name="personalDTO.birthDate" onchange="calculateAge('AGE','BIRTH_DATE')" style="width: 145px;" maxlength="10"/>
     <!--
     <img src="/BMREG_WEB/resources/images/calendar.png" id="BIRTH_DATE_BTN" border="0" />
     --> 
     <font style='color:maroon;font-size: 13px;'>[DD-MM-YYYY]</font>
    
     </td>
     <td>Age</td>
     <td>
     	<input type="text" class="textBox" id="AGE" name="personalDTO.age" value="<s:property value="personalDTO.age" />" />
     </td>
    </tr>

     <tr>
     <td>Marital Status</td>
     <td>
     <input type="radio" name="personalDTO.maritalStatus" value="Married" /> Married
     	<input type="radio" name="personalDTO.maritalStatus" value="Unmarried" checked="checked"  /> Unmarried
     </td>
     <td>Gender</td>
     <td>
     	<input type="radio" id="SEX" name="personalDTO.sex" value="Male" checked="checked" /> Male
     	<input type="radio" id="SEX" name="personalDTO.sex" value="Female"  /> Female
     </td>
    </tr>
    
    <tr>
     <td>Weight (kg)<font color="red">*</font></td>
     <td><input type="text" tabindex="9" name="personalDTO.weight" id="WEIGHT" maxlength="3"  onkeypress="return numericWithDot(event)" value="<s:property value="personalDTO.weight" />" class="textBox"/></td>
     <td>Height<font color="red">*</font></td>
     <td>
     <input type="text" tabindex="10" name="personalDTO.heightFeet" id="HEIGHT_FEET" maxlength="1" onkeypress="return numericOnly(event)" value="<s:property value="personalDTO.heightFeet" />" class="textBox" style="width: 50px;text-align: center;"/>
      Feet&nbsp;&nbsp;&nbsp;
     <input type="text" tabindex="11" name="personalDTO.heightInches" id="HEIGHT_INCHES" maxlength="3" onkeypress="return numericOnlyWithDot(event)" value="<s:property value="personalDTO.heightInches" />" class="textBox" style="width: 50px;text-align: center;"/>
      Inches
     </td>
    </tr>
  
    <tr>
     <td>MRP Passport No (If any)</td>
     <td><input type="text" tabindex="12" name="personalDTO.passportNo" id="PASSPORT_DATE" size="20" maxlength="10" value="<s:property value="personalDTO.passportNo" />" class="textBox"/></td>
     <td>Passport Issue Date</td>
     <td>
     <input type="text" tabindex="13" class="textBox" id="PASSPORT_ISSUE_DATE" name="personalDTO.passportIssueDate" value="<s:property value="personalDTO.passportIssueDate" />" onchange="checkFutureDate(this)" style="width: 145px;"/>
     <!--  
     <img src="/BMREG_WEB/resources/images/calendar.png" id="PASSPORT_ISSUE_DATE_BTN" border="0"/>
     -->
	 <font style='color:maroon;font-size: 13px;'>[DD-MM-YYYY]</font> 	
     </td>
    </tr>
    <tr>
     
   
     <td>Passport Exp. Date</td>
     <td>
     <input type="text" tabindex="14"  class="textBox" id="PASSPORT_EXP_DATE" name="personalDTO.passportExpireDate" value="<s:property value="personalDTO.passportExpireDate" />" style="width: 145px;" maxlength="10"/>
     <font style='color:maroon;font-size: 13px;'>[DD-MM-YYYY]</font>
     <!-- 
     	<img src="/BMREG_WEB/resources/images/calendar.png" id="PASSPORT_EXP_DATE_BTN" border="0" />
      -->
     </td>
     <td>Religion<font color="red">*</font></td>
     <td>
     	<select tabindex="15" name="personalDTO.religion" id="RELIGION" class="addressSelectBox">
     											<option value="select" selected="selected">--Select Religion--</option>
												<option value="Islam">Islam</option>
												<option value="Hinduism">Hinduism</option>
												<option value="Christianity">Christianity</option>
												<option value="Buddhism">Buddhism</option>
												<option value="Other">Other</option>
	</select>
     </td>
    </tr>
   
    
    <tr>
    	<td colspan="4" align="left" style="background-color: #CCCCCC">
    		Desired Job
    	</td>
    </tr>
    <tr>
     <td>Option 1</td>
     <td>
     	<select tabindex="16" name="personalDTO.desiredJob1" id="DESIREDJOB1" class="addressSelectBox" onchange="checkSubCategory(this.value)">
												<option value="Plantation">Plantation</option>
		</select>
     </td>
     <td></td>
     <td>
     	     
     </td>
    </tr>
    <tr>
     <td>Option 2</td>
     <td>
     	<select tabindex="17" name="personalDTO.desiredJobCat1" id="DESIREDJOBCAT1" class="addressSelectBox" onchange="checkSubCategory(this.value)">
     											<option value="select" selected="selected">--Select Category--</option>
												<option value="Agriculture">Agriculture</option>
												<option value="Manufacturing">Manufacturing</option>
												<option value="Construction">Construction</option>
												<option value="Service">Service</option>
		</select>
     </td>
     <td></td>
     <td>
     	     	<select tabindex="18" name="personalDTO.desiredJobSubcategory1" id="DESIREDJOBSUBCAT1" class="addressSelectBox">
     											<option value="select" selected="selected">--Select Sub Category--</option>
			    </select>
     </td>
    </tr>

    <tr>
    <td colspan="4" height="20px;"></td>
    </tr>
    <tr>
    <td colspan="4" align="left" style="padding-left: 20px;border-top: 1px solid #006219;">
      <b>Note :</b> <font color="red">*</font> indicates mandatory fields.
    </td>
    </tr>
    
    </table>
    
    
    </p>
</div>
<p style="height: 30px"></p>
<div class="box">
    <h3>Address</h3>
    <p>
    
    <table width="100%" border="0" cellspacing="1" class="infoTable">
    <tr bgcolor="#F2F7E3">
     <td align="center" width="50%" colspan="2" style="text-align: center;font-weight: bold;">Permanent Address</td>
     <td align="center" width="50%" colspan="2" style="text-align: center;font-weight: bold;">Mailing Address
     </td>
    </tr>
    <tr>
     <td width="15%">Division<font color="red">*</font></td>
     <td width="35%">
     	<s:property value="addressDTO.pDivisionName" />
     	
        <input type="hidden" name="addressDTO.pDivisionName" value="<s:property value="addressDTO.pDivisionName" />" />
    
     </td>
     <td width="15%">Division<font color="red">*</font></td>
     <td width="35%">
     <select tabindex="29"  name="addressDTO.mDivision" id="MAILING_DIV" class="addressSelectBox" onchange="fetchJSONData_Dist(this.value,'MAILING_DIST')">
        <option value="select" selected="selected">--Select Division--</option>
     	<s:iterator value="%{#application.ALL_DIVISION}" id="divisionList">
     	  <option value="<s:property value="division_id" />"><s:property value="division_name" /></option>
     	</s:iterator>
     </select>
     </td>
    </tr>
    <tr>
     <td>District<font color="red">*</font></td>
     <td valign="top" id="PERMANENT_DIST_TD">
     <s:property value="addressDTO.pDistrictName" />
     <input type="hidden" name="addressDTO.pDistrictName" value="<s:property value="addressDTO.pDistrictName" />" />
     </td>
     <td valign="top">District<font color="red">*</font></td>
     <td valign="top" id="MAILING_DIST_TD">
     <select tabindex="30" name="addressDTO.mDistrict" id="MAILING_DIST" class="addressSelectBox" onchange="fetchJSONData_Thana(this.value,'MAILING_THANA')">
     </select>

     </td>
    </tr>
    
    <tr>
     <td>Upazilla<font color="red">*</font></td>
     <td valign="top" id="PERMANENT_THANA_TD">
        <s:property value="addressDTO.pThanaName" />
       <input type="hidden" name="addressDTO.pThanaName" value="<s:property value="addressDTO.pThanaName" />" />
     </td>
     <td valign="top">Upazilla<font color="red">*</font></td>
     <td valign="top" id="MAILING_THANA_TD">
     	<select tabindex="31" name="addressDTO.mThana" id="MAILING_THANA" class="addressSelectBox">
        </select>
     </td>
    </tr>
    <tr>
     <td>Union/Pouroshova<font color="red">*</font></td>
     <td valign="top" id="PERMANENT_UNION_TD">
     <s:property value="addressDTO.PUnionName" />
       <input type="hidden" name="addressDTO.PUnionName" value="<s:property value="addressDTO.PUnionName" />" />
     </td>
     <td valign="top">Union/Pouroshova<font color="red">*</font></td>
     <td valign="top" id="MAILING_UNION_TD">
     	<select tabindex="32" name="addressDTO.mUnion" id="MAILING_UNION" class="addressSelectBox" >
        </select>
     </td>
    </tr>
    
    <tr>
     <td>Post Office</td>
     <td valign="top">
     	<input type="text" tabindex="23" name="addressDTO.pPost" maxlength="30" id="PERMANENT_POST"  class="textBox" value="<s:property value="addressDTO.pPost"/>" />
     </td>
     <td valign="top">Post Office</td>
     <td valign="top">
     	<input type="text" tabindex="33" name="addressDTO.mPost" maxlength="30" id="MAILING_POST" class="textBox" value="<s:property value="addressDTO.mPost"/>"  />
     </td>
    </tr>
    
     <tr>
     <td>Post Code</td>
     <td valign="top">
     	<input type="text" tabindex="24" name="addressDTO.pPostCode" maxlength="30" id="PERMANENT_POST_CODE"  class="textBox" value="<s:property value="addressDTO.pPostCode"/>" />
     </td>
     <td valign="top">Post Code</td>
     <td valign="top">
     	<input type="text" tabindex="34" name="addressDTO.mPostCode" maxlength="30" id="MAILING_POST_CODE"  class="textBox" value="<s:property value="addressDTO.mPostCode"/>" />     		
     </td>
    </tr>
    
     <tr>
     <td>Village/Street<font color="red">*</font></td>
     <td valign="top">
     	<input type="text" tabindex="25" name="addressDTO.pAddressLine1" maxlength="100" id="PERMANENT_ADDRESS_LINE1"  class="textBox" value="<s:property value="addressDTO.pAddressLine1"/>" />
     </td>
     <td valign="top">Village/Street<font color="red">*</font></td>
     <td valign="top">
     	<input type="text" tabindex="35" name="addressDTO.mAddressLine1" maxlength="100" id="MAILING_ADDRESS_LINE1"  class="textBox" value="<s:property value="addressDTO.mAddressLine1"/>" />     		
     </td>
    </tr>
    
     <tr>
     <td>House No.(if any)</td>
     <td valign="top">
     	<input type="text" tabindex="26" name="addressDTO.pAddressLine2" maxlength="100" id="PERMANENT_ADDRESS_LINE2"  class="textBox" value="<s:property value="addressDTO.pAddressLine2"/>" />
     </td>
     <td valign="top">House No.(if any)</td>
     <td valign="top">
     	<input type="text" tabindex="36" name="addressDTO.mAddressLine2" maxlength="100" id="MAILING_ADDRESS_LINE2"  class="textBox" value="<s:property value="addressDTO.mAddressLine2"/>" />     		
     </td>
    </tr>

    <tr>
     <td>Email (If any)</td>
     <td valign="top">
     	<input type="text" tabindex="27" name="addressDTO.pEmail" maxlength="50" id="PERMANENT_EMAIL"  class="textBox" value="<s:property value="addressDTO.pEmail"/>"  onblur="ValidateEmail(this)"/>       
     </td>
     <td valign="top">Email (If any)</td>
     <td valign="top">
     	<input type="text" tabindex="37" name="addressDTO.mEmail" maxlength="50" id="MAILING_EMAIL" class="textBox" value="<s:property value="addressDTO.mEmail"/>"   onblur="ValidateEmail(this)" />
     </td>
    </tr>
    
    <tr>
     <td>Mobile/Phone<font color="red">*</font></td>
     <td valign="top">
     	<input type="text" tabindex="28" name="addressDTO.pMobile" maxlength="11" id="PERMANENT_MOBILE" class="textBox" value="<s:property value="addressDTO.pMobile"/>" onkeypress="return numericOnly(event)" />
     </td>
     <td valign="top">
     <!-- Mobile/Phone<font color="red">*</font> --></td>
     <td valign="top">
     <!-- 
     	<input type="text" tabindex="38" maxlength="11" name="addressDTO.mMobile" id="MAILING_MOBILE" class="textBox"  value="<s:property value="addressDTO.mMobile"/>" onkeypress="return numericOnly(event)"  />
     	 -->
     </td>
    </tr>
    <tr>
    <td colspan="4" height="20px;"></td>
    </tr>
    <tr>
    <td colspan="4" align="left" style="padding-left: 20px;border-top: 1px solid #006219;">
      <b>Note :</b> <font color="red">*</font> indicates mandatory fields.
    </td>
    </tr>
    
    </table>
    
    
    </p>
</div>

<p style="height: 30px"></p>
<div class="box">
    <h3>Nominee Information</h3>
    <p>
    
    <table width="100%" border="0" cellspacing="1" class="infoTable">
   
    <tr>
     <td width="15%">Nominee Name<font color="red">*</font></td>
     <td width="35%">
     <input type="text" tabindex="39" name="personalDTO.nomineeName" id="NOMINEE_NAME" maxlength="100" class="textBox" value="<s:property value="personalDTO.nomineeName" />" />
     </td>
     <td width="15%">Relation<font color="red">*</font></td>
     <td width="35%">
     <!-- 
     <input type="text" class="textBox"/>
      -->
     <select tabindex="40" name="personalDTO.nomineeRelation" id="NOMINEE_RELATION"  class="addressSelectBox">
     <option value="none">--Select Relation--</option>
      <option value="Wife">Wife</option>
      <option value="Mother">Mother</option>
      <option value="Father">Father</option>
      <option value="Brother">Brother</option>
      <option value="Sister">Sister</option>
      <option value="Uncle">Uncle</option>
      <option value="Son/Daughter">Son/Daughter</option>
      <option value="Cousin">Cousin</option>
      <option value="Neighbour">Neighbour</option>
      <option value="Friend">Friend</option>
     </select>
     
     </td>
    </tr>
    <tr>
     <td valign="top">Address<font color="red">*</font></td>
     <td valign="top">
     <textarea tabindex="41" name="personalDTO.nomineeAddress"  maxlength="200" id="NOMINEE_ADDRESS" rows="5" cols="29" style="border: 1px solid grey;"><s:property value="personalDTO.nomineeAddress" /></textarea>
												
     </td>
     <td valign="top">Mobile/Phone<font color="red">*</font></td>
     <td valign="top">
     	<input type="text" tabindex="42" name="personalDTO.nomineePhone" id="NOMINEE_PHONE" maxlength="11" class="textBox" value="<s:property value="personalDTO.nomineePhone" />" onkeypress="return numericOnly(event)" />
   	  </td>
    </tr>
    
    <tr>
     <td valign="top">Contact Person Name<font color="red">*</font><br/>(in case of emergency)</td>
     <td valign="top">
		<input type="text" tabindex="43" name="personalDTO.contactName" id="CONTACT_NAME" maxlength="100" class="textBox" value="<s:property value="personalDTO.contactName" />"  />												
     </td>
     <td valign="top">Contact Person Mobile<font color="red">*</font></td>
     <td valign="top">
     	<input type="text" tabindex="44" name="personalDTO.contactMobile" id="CONTACT_MOBILE" maxlength="11" class="textBox"  onkeypress="return numericOnly(event)" value="<s:property value="personalDTO.contactMobile" />" />
   	  </td>
    </tr>    
    <tr>
    <td colspan="4" height="20px;"></td>
    </tr>
    <tr>
    <td colspan="4" align="left" style="padding-left: 20px;border-top: 1px solid #006219;">
      <b>Note :</b> <font color="red">*</font> indicates mandatory fields.
    </td>
    </tr>
    
    
    
    </table>
    
    
    </p>
</div>



<div style="margin-top: 20px;">
<input type="button" class="submitButton" name="goHomeButton" value="Edit Home" onclick="window.location='editHome.action'" />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="submit" tabindex="46" class="submitButton" name="UpdateApplication" value="Update Information" />


</div>
<p style="height: 30px"></p>

 
</center>

<%@ include file="SetFormContent.jsp" %>
 
<script type="text/javascript">
$('input').attr('autocomplete','off');  
</script>

</form>
</body>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>

<head>
<meta content="text/html;charset=utf-8" http-equiv="Content-Type">
<meta content="utf-8" http-equiv="encoding">
<title>Job Seeker Registration Form</title>
 <link rel="stylesheet" href="/BMREG_WEB/resources/css/style.css" />	
  <script type="text/javascript" src="/BMREG_WEB/resources/js/lib/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="/BMREG_WEB/resources/js/util/util.js"></script>
 <script type="text/javascript" src="/BMREG_WEB/resources/js/util/browserDetect.js"></script>
 <script type="text/javascript" src="/BMREG_WEB/resources/js/registration.js"></script>
  <script type="text/javascript" src="/BMREG_WEB/resources/js/address.js"></script>
  <script type="text/javascript" src="/BMREG_WEB/resources/js/regValidation.js"></script>
 

 <script type="text/javascript">
  function submitForm(actionName)
  {
   document.myForm.action=actionName;
   //document.myForm.submit();
   checkConnectivityAndSubmitForm("myForm");
  }
 </script>
  
</head>


<body style="margin: 0px;">
<form id="myForm" name=myForm method="post" action="">
<div style="width: 100%;height: 100px;border-bottom: 1px solid #006219;">
<center>
<div style="width: 1000px;height: 100px;border-right: 1px solid #006219;border-left: 1px solid #006219;">
	<div style="float: left; margin-top: 20px;width: 100px;">
	 <img src="/BMREG_WEB/resources/images/bagladesh_logo.gif" width="60" height="60" />
	</div>
	<div style="float: left;margin-left: 30px;color: black;margin-top: 15px;text-align: left;">
	 	<div style="font-size: 27px;font-weight: bold;">Bureau of Manpower, Employment & Training (BMET)</div>
	 	<div style="font-size: 20px;margin-top: 10px;">Job Seeker's Registration Form (Preview)</div>
	</div>
</div>
</center>
</div>
<center>

<div style="color: red;width: 900px;"><s:label name="Err_RegSubmit" ></s:label></div>
<br/>
<div class="boxPreview">
    <h3>Personal Information</h3>
    <p>
    
    <table width="100%" border="0" cellspacing="1" class="infoTable">
    <tr>
     <td width="15%" style="color:#7D2252;">Name</td>
     <td width="85%" colspan="3" style="padding-bottom: 15px;">
     
     <div style="float:left;width: 250px;color: #4D4D4D;">Given Name<br/>
     	<font color='black'/><s:property value="personalDTO.empGivenName" /></font>
     	<input type="hidden" name="personalDTO.empGivenName" value="<s:property value="personalDTO.empGivenName" />" />
     </div>
     <div style="float:left;width: 250px;color: #4D4D4D">Last Name(Sur Name)<br/>
     	<font color='black'/><s:property value="personalDTO.empLastName" /></font>
     	<input type="hidden" name="personalDTO.empLastName" value="<s:property value="personalDTO.empLastName" />" />
     </div>
     
     
     </td>
    </tr>
    <tr>
     <td width="15%" style="color:#7D2252;">Father's Name</td>
     <td width="35%">
     	<s:property value="personalDTO.empFatherName" />
     	<input type="hidden" name="personalDTO.empFatherName" value="<s:property value="personalDTO.empFatherName" />" />
     </td>
     <td width="15%" style="color:#7D2252;">Mother's Name</td>
     <td width="35%">
     	<s:property value="personalDTO.empMotherName" />
     	<input type="hidden" name="personalDTO.empMotherName" value="<s:property value="personalDTO.empMotherName" />" />
     </td>
    </tr>
    <tr>
     <td style="color:#7D2252;">Birth Date</td>
     <td><s:property value="personalDTO.empBirthDate" />
     	<input type="hidden" name="personalDTO.empBirthDate" value="<s:property value="personalDTO.empBirthDate" />" />
     </td>
     <td style="color:#7D2252;">Place of Birth</td>
     <td>
     	<input type="hidden" name="personalDTO.empBirthDistrict" value="<s:property value="personalDTO.empBirthDistrict" />" />
     	<input type="hidden" name="personalDTO.empBirthUpazilaOrThana" value="<s:property value="personalDTO.empBirthUpazilaOrThana" />" />
     	<s:property value="personalDTO.empBirthDistrictName" />, <s:property value="personalDTO.empBirthUpazilaOrThanaName" />
     </td>
    </tr>

     <tr>
     <td style="color:#7D2252;">Gender</td>
     <td>
     <s:property value="personalDTO.empGender" />
     <input type="hidden" name="personalDTO.empGender" value="<s:property value="personalDTO.empGender" />" />
     </td>
     <td style="color:#7D2252;">Religion</td>
     <td>
     	<s:property value="personalDTO.empReligion" />
     	<input type="hidden" name="personalDTO.empReligion" value="<s:property value="personalDTO.empReligion" />" />
     </td>
    </tr>

     <tr>
     <td style="color:#7D2252;">Marital Status</td>
     <td>
     
     <s:property value="personalDTO.empMaritalStatus" />
     <input type="hidden"  name="personalDTO.empMaritalStatus" value="<s:property value="personalDTO.empMaritalStatus" />" />
     </td>
     <td  style="color:#7D2252;">Do you have any children ?</td>
     <td>
      <s:property value="personalDTO.empChildYN" />
      <input type="hidden" name="personalDTO.empChildYN" value="<s:property value="personalDTO.empChildYN" />" />
     </td>
    </tr>
    
   
  
    <tr>
     <td style="color:#7D2252;">Total Son</td>
     <td><s:property value="personalDTO.empSonCount" />
     <input type="hidden" name="personalDTO.empSonCount" value="<s:property value="personalDTO.empSonCount" />" />
     </td>
     <td style="color:#7D2252;">Total Daughter</td>
     <td>
     	<s:property value="personalDTO.empDaughterCount" />
     	<input type="hidden" name="personalDTO.empDaughterCount" value="<s:property value="personalDTO.empDaughterCount" />" />
     </td>
    </tr>
    <tr>
    
     <td style="color:#7D2252;">Spouse Name</td>
     <td><s:property value="personalDTO.empSpouseName" />
     <input type="hidden" name="personalDTO.empSpouseName" value="<s:property value="personalDTO.empSpouseName" />" />
     </td>
     <td style="color:#7D2252;">Mobile</td>
     <td>
     	<s:property value="personalDTO.empMobileNumber" />
     	<input type="hidden" name="personalDTO.empMobileNumber" value="<s:property value="personalDTO.empMobileNumber" />" />
     </td>
    </tr>
    <tr>
   
     <td style="color:#7D2252;">Height</td>
     
     <td>
     <s:property value="personalDTO.heightFeet" />
     <input type="hidden" name="personalDTO.empHeightFeet" value="<s:property value="personalDTO.empHeightFeet" />" />
     <s:property value="personalDTO.empHeightFeet" /> Feet
     
     <s:property value="personalDTO.heightInches" />
     <input type="hidden" name="personalDTO.empHeightInches" value="<s:property value="personalDTO.empHeightInches" />" />
     <s:property value="personalDTO.empHeightInches" /> Inches
     
     </td>
     <td style="color:#7D2252;">Weight(KG)</td>
     <td>
     <s:property value="personalDTO.empWeight" />
     <input type="hidden" name="personalDTO.empWeight" value="<s:property value="personalDTO.empWeight" />" />
     </td>
    </tr>
    
    <tr>
    
     <td style="color:#7D2252;">Blood Group</td>
     <td><s:property value="personalDTO.empBloodGroup" />
     <input type="hidden" name="personalDTO.empBloodGroup" value="<s:property value="personalDTO.empBloodGroup" />" />
     </td>
     <td style="color:#7D2252;">Do you have any Disability?</td>
     <td>
     	<s:property value="personalDTO.empDisabilityYN" />
     	<input type="hidden" name="personalDTO.empDisabilityYN" value="<s:property value="personalDTO.empDisabilityYN" />" />
     </td>
    </tr>
    <tr>
    
     <td style="color:#7D2252;">Disability Detail</td>
     <td colspan="3"><s:property value="personalDTO.disabilityDetail" />
     <input type="hidden" name="personalDTO.empDisabilityDetail" value="<s:property value="personalDTO.disabilityDetail" />" />
     </td>
    </tr>
   
    </table>
    
    
    </p>
</div>
<p style="height: 30px"></p>
<div class="boxPreview">
    <h3>Identification and Nominee Detail</h3>
    <p>
    
    <table width="100%" border="0" cellspacing="1" class="infoTable" style="margin-top: -10px;">
    <tr>
     <td width="20%" style="color:#7D2252;">National Id</td>
     <td width="30%">     	
     	<s:property value="personalDTO.nationalId" />     	
     	<input type="hidden" name="personalDTO.nationalId" value="<s:property value="personalDTO.nationalId" />" />
     </td>
     <td width="20%" style="color:#7D2252;">Birth Reg Id</td>
     <td width="30%">
     	<s:property value="personalDTO.birthRegId" />
     	<input type="hidden" name="personalDTO.birthRegId" value="<s:property value="personalDTO.birthRegId" />" />
     </td>
    </tr>
    <tr>
     <td style="color:#7D2252;">Passport Number</td>
     <td valign="top">
     <s:property value="personalDTO.passportNo" />
     <input type="hidden" name="personalDTO.passportNo" value="<s:property value="personalDTO.passportNo" />" />
     </td>
     <td valign="top" style="color:#7D2252;">Previous Passport</td>
     <td valign="top">
     <s:property value="personalDTO.oldPassportNo" />
     <input type="hidden" name="personalDTO.oldPassportNo" value="<s:property value="personalDTO.oldPassportNo" />" />
     </td>
    </tr>
    
    <tr>
     <td style="color:#7D2252;">Passport Issue Date</td>
     <td valign="top">
        <s:property value="personalDTO.passportIssueDate" />
        <input type="hidden" name="personalDTO.passportIssueDate" value="<s:property value="personalDTO.passportIssueDate" />" />
     </td>
     <td valign="top" style="color:#7D2252;">Passport Exp. Date</td>
     <td valign="top">
     	<s:property value="personalDTO.passportExpDate" />
     	<input type="hidden" name="personalDTO.passportExpDate" value="<s:property value="personalDTO.passportExpDate" />" />
     </td>
    </tr>
    
    <tr>
     <td style="color:#7D2252;">Nominee Name</td>
     <td valign="top">
        <s:property value="nomineeDTO.nomineeName" />
        <input type="hidden" name="nomineeDTO.nomineeName" value="<s:property value="nomineeDTO.nomineeName" />" />
     </td>
     <td valign="top" style="color:#7D2252;">Relationship</td>
     <td valign="top">
     	<s:property value="nomineeDTO.nomineeRelationName" />
     	<input type="hidden" name="nomineeDTO.nomineeRelation" value="<s:property value="nomineeDTO.nomineeRelation" />" />
     </td>
    </tr>
    
    <tr>
     <td style="color:#7D2252;">Father Name</td>
     <td valign="top">
     	<s:property value="nomineeDTO.nomineeFatherName" />
     	<input type="hidden" name="nomineeDTO.nomineeFatherName" value="<s:property value="nomineeDTO.nomineeFatherName" />" />

     </td>
     <td valign="top" style="color:#7D2252;">Mother Name</td>
     <td valign="top">
     	<s:property value="nomineeDTO.nomineeMotherName" />
     	<input type="hidden" name="nomineeDTO.nomineeMotherName" value="<s:property value="nomineeDTO.nomineeMotherName" />" />
     </td>
    </tr>
    
     <tr>
     <td style="color:#7D2252;" valign="top">Address(Nominee)</td>
     <td valign="top"  colspan="3">
     	<table border="0" width="100%">
			<tr>
				<td width="30%" align="left">Division</td>
				<td width="70%" align="left">
					<s:property value="nomineeDTO.address.divisionName" />
     				<input type="hidden" name="nomineeDTO.address.divisionId" value="<s:property value="nomineeDTO.address.divisionId" />" />
				</td>				
			</tr>
			<tr>
				<td align="left">Zila/District</td>
				<td align="left">
					<s:property value="nomineeDTO.address.districtName" />
     				<input type="hidden" name="nomineeDTO.address.districtId" value="<s:property value="nomineeDTO.address.districtId" />" />
				</td>				
			</tr>
			<tr>
				<td align="left">Upazila/Thana</td>
				<td align="left">
					<s:property value="nomineeDTO.address.upazillaOrThanaName" />
     				<input type="hidden" name="nomineeDTO.address.upazillaOrThanaId" value="<s:property value="nomineeDTO.address.upazillaOrThanaId" />" />
				</td>				
			</tr>
			<tr>
				<td align="left">Union/Ward</td>
				<td align="left">
					<s:property value="nomineeDTO.address.unionOrWardName" />
     				<input type="hidden" name="nomineeDTO.address.unionOrWardId" value="<s:property value="nomineeDTO.address.unionOrWardId" />" />
				</td>				
			</tr>
			<tr>
				<td align="left">Mauza/Moholla</td>
				<td align="left">
					<s:property value="nomineeDTO.address.mauzaOrMohollaName" />
     				<input type="hidden" name="nomineeDTO.address.mauzaOrMohollaId" value="<s:property value="nomineeDTO.address.mauzaOrMohollaId" />" />
				</td>				
			</tr>
			<tr>
				<td align="left">Village</td>
				<td align="left">
					<s:property value="nomineeDTO.address.villageName" />
     				<input type="hidden" name="nomineeDTO.address.villageId" value="<s:property value="nomineeDTO.address.villageId" />" />
				</td>				
			</tr>
			<tr>
				<td align="left">Post Office</td>
				<td align="left">
					<s:property value="nomineeDTO.address.postOffice" />
     				<input type="hidden" name="nomineeDTO.address.postOffice" value="<s:property value="nomineeDTO.address.postOffice" />" />
				</td>				
			</tr>
			<tr>
				<td align="left">Post Code</td>
				<td align="left">
					<s:property value="nomineeDTO.address.postCode" />
     				<input type="hidden" name="nomineeDTO.address.postCode" value="<s:property value="nomineeDTO.address.postCode" />" />
				</td>				
			</tr>
			<tr>
				<td align="left">Road Number</td>
				<td align="left">
					<s:property value="nomineeDTO.address.roadNumber" />
     				<input type="hidden" name="nomineeDTO.address.roadNumber" value="<s:property value="nomineeDTO.address.roadNumber" />" />
				</td>				
			</tr>
			<tr>
				<td align="left">Household Number</td>
				<td align="left">
					<s:property value="nomineeDTO.address.houseHoldNumber" />
     				<input type="hidden" name="nomineeDTO.address.houseHoldNumber" value="<s:property value="nomineeDTO.address.houseHoldNumber" />" />
				</td>				
			</tr>
			
			
        </table>
     </td>     
    </tr>
    
    <tr>
     <td style="color:#7D2252;" valign="top">Nominee Phone/Mobile</td>
     <td valign="top">
     	<s:property value="nomineeDTO.nomineePhoneOrMobile" />
     	<input type="hidden" name="nomineeDTO.nomineePhoneOrMobile" value="<s:property value="nomineeDTO.nomineePhoneOrMobile" />" />
     </td>
     
     <td valign="top" style="color:#7D2252;"></td>
     <td valign="top">
     </td>
    </tr>
    
     <tr>
     <td style="color:#7D2252;" valign="top">Contact Persons</td>
     <td colspan="3" valign="top">
     	<table  width="98%" border="1" style="border-collapse: collapse;">
     		<tr>
     			<td width="25%"><b>Contact Person</b></td>
     			<td width="25%"><b>Name</b></td>
     			<td width="25%"><b>Mobile</b></td>
     			<td width="25%"><b>Relation</b></td>
     		</tr>
     		<tr>
     			<td>
     				First
     			</td>
     			<td>
     				<s:property value="nomineeDTO.contact1Name" />
			     	<input type="hidden" name="nomineeDTO.contact1Name" value="<s:property value="nomineeDTO.contact1Name" />" />
     			</td>
     			<td>
     				<s:property value="nomineeDTO.contact1Mobile" />
			     	<input type="hidden" name="nomineeDTO.contact1Mobile" value="<s:property value="nomineeDTO.contact1Mobile" />" />
     			</td>
     			<td>
     				<s:property value="nomineeDTO.contact1RelationName" />
			     	<input type="hidden" name="nomineeDTO.contact1Relation" value="<s:property value="nomineeDTO.contact1Relation" />" />	
     			</td>
     		</tr>
     		<tr>
     			<td>
     				Second
     			</td>
     			<td>
     				<s:property value="nomineeDTO.contact2Name" />
			     	<input type="hidden" name="nomineeDTO.contact2Name" value="<s:property value="nomineeDTO.contact2Name" />" />
     			</td>
     			<td>
     				<s:property value="nomineeDTO.contact2Mobile" />
			     	<input type="hidden" name="nomineeDTO.contact2Mobile" value="<s:property value="nomineeDTO.contact2Mobile" />" />
     			</td>
     			<td>
     				<s:property value="nomineeDTO.contact2RelationName" />
			     	<input type="hidden" name="nomineeDTO.contact2Relation" value="<s:property value="nomineeDTO.contact2Relation" />" />	
     			</td>
     		</tr>
     		<tr>
     			<td>
     				Third
     			</td>
     			<td>
     				<s:property value="nomineeDTO.contact3Name" />
			     	<input type="hidden" name="nomineeDTO.contact3Name" value="<s:property value="nomineeDTO.contact3Name" />" />
     			</td>
     			<td>
     				<s:property value="nomineeDTO.contact3Mobile" />
			     	<input type="hidden" name="nomineeDTO.contact3Mobile" value="<s:property value="nomineeDTO.contact3Mobile" />" />
     			</td>
     			<td>
     				<s:property value="nomineeDTO.contact3RelationName" />
			     	<input type="hidden" name="nomineeDTO.contact3Relation" value="<s:property value="nomineeDTO.contact3Relation" />" />	
     			</td>
     		</tr>
     	</table>
     </td>
    </tr>
    
    
    
    </table>
    
    
    </p>
</div>


<p style="height: 30px"></p>
<div class="boxPreview">
    <h3>Experience, Country and Job Preference</h3>
    <p>
    
    <table width="100%" border="0" cellspacing="1" class="infoPreviewTable">
   
    <tr>
     <td valign="top" style="color:#7D2252;">Experience (Local)</td>
     <td valign="top" colspan="3">
        <table  width="98%" border="1" style="border-collapse: collapse;">
        <tr>        	
        	<td width="25%" align="center"><b>Category</b></td>
        	<td width="25%" align="center"><b>Sub-Category</b></td>
        	<td width="25%" align="center"><b>Sub-Sub-Category</b></td>
        	<td width="25%" align="center"><b>Exp. Years</b></td>
        </tr>
		<s:iterator value="localExperienceList" id="exp">
		 <tr>		  
		  <td align="left"><s:property value="jobCategoryName"/></td>
		  <td align="left"><s:property value="jobSubCategoryName"/></td>
		  <td align="left"><s:property value="jobSubSubCategoryName"/></td>
		  <td align="left"><s:property value="totalYears"/></td>
		 </tr>
		</s:iterator>
		</table>
    	 <input type="hidden" name="localExperience" value="<s:property value="localExperience" />" />
     </td>
    </tr>
    
    
    <tr>
     <td valign="top" style="color:#7D2252;">Experience (Aborad)</td>
     <td valign="top" colspan="3">
        <table  width="98%" border="1" style="border-collapse: collapse;">
        <tr>
        	<td width="20%" align="center"><b>Country</b></td>
        	<td width="20%" align="center"><b>Category</b></td>
        	<td width="20%" align="center"><b>Sub-Category</b></td>
        	<td width="20%" align="center"><b>Sub-Sub-Category</b></td>
        	<td width="20%" align="center"><b>Exp. Years</b></td>
        </tr>
		<s:iterator value="abroadExperienceList" id="exp">
		 <tr>
		  <td align="left"><s:property value="countryName"/></td>
		  <td align="left"><s:property value="jobCategoryName"/></td>
		  <td align="left"><s:property value="jobSubCategoryName"/></td>
		  <td align="left"><s:property value="jobSubSubCategoryName"/></td>
		  <td align="left"><s:property value="totalYears"/></td>
		 </tr>
		</s:iterator>
		</table>
    	 <input type="hidden" name="abroadExperience" value="<s:property value="abroadExperience" />" />
     </td>
    </tr>
    <tr>
     <td style="color:#7D2252;">Country Preference</td>
     <td colspan="3">     	
     	<s:property value="countryPreferenceNames" />
     	<input type="hidden" name="countryPreferenceIds" value="<s:property value="countryPreferenceIds" />" />
     </td>
    </tr>
    
    <tr>
     <td style="color:#7D2252;" valign="top">Job Preference</td>
     <td colspan="3">     	
     	<table  width="80%" border="1" style="border-collapse: collapse;">
        <tr>
        	<td width="35%" align="center"><b>Category</b></td>
        	<td width="35%" align="center"><b>Sub Category</b></td>
        	<td width="30%" align="center"><b>Sub-sub Category</b></td>
        </tr>
		<s:iterator value="jobPreferenceList" id="jobPreference">
		 <tr>
		  <td align="left"><s:property value="categoryName"/></td>
		  <td align="left"><s:property value="subCategoryName"/></td>
		  <td align="left"><s:property value="subSubCategoryName"/></td>
		 </tr>
		</s:iterator>
		</table>
		<input type="hidden" name="jobPreference" value="<s:property value="jobPreference" />" />
     </td>
    </tr>
    
    </table>
    
    
    </p>
</div>

<p style="height: 30px"></p>
<div class="boxPreview">
    <h3>Education, Language and Training</h3>
    <p>
    
    <table width="100%" border="0" cellspacing="1" class="infoPreviewTable">
   
    <tr>
     <td width="20%" style="color:#7D2252;">Heighest Degree Earned</td>
     <td width="30%">     	
     	<s:property value="educationDTO.heighestDegreeName" />
     	<input type="hidden" name="educationDTO.heighestDegreeId" value="<s:property value="educationDTO.heighestDegreeId" />" />
     </td>
     <td width="20%"  style="color:#7D2252;"></td>
     <td width="30%">
     </td>
    </tr>
    <tr>
     <td style="color:#7D2252;">Name of the last attended Institute</td>
     <td colspan="3">     	
     	<s:property value="educationDTO.lastInstitute" />
     	<input type="hidden" name="educationDTO.lastInstitute" value="<s:property value="educationDTO.lastInstitute" />" />
     </td>
    </tr>
    <tr>
     <td style="color:#7D2252;">Year of Passing</td>
     <td colspan="3">     	
     	<s:property value="educationDTO.passingYear" />
     	<input type="hidden" name="educationDTO.passingYear" value="<s:property value="educationDTO.passingYear" />" />
     </td>
    </tr>
    
    <tr>
     <td valign="top" style="color:#7D2252;">Language</td>
     <td valign="top" colspan="3">
        <table  width="80%" border="1" style="border-collapse: collapse;">
        <tr>
        	<td width="35%" align="center"><b>Language</b></td>
        	<td width="35%" align="center"><b>Oral Skill</b></td>
        	<td width="30%" align="center"><b>Writing Skill</b></td>
        </tr>
		<s:iterator value="languageList" id="language">
		 <tr>
		  <td align="left"><s:property value="language"/></td>
		  <td align="left"><s:property value="oralSkill"/></td>
		  <td align="left"><s:property value="writingSkill"/></td>
		 </tr>
		</s:iterator>
		</table>
    	 <input type="hidden" name="languages" value="<s:property value="languages" />" />
     </td>
    </tr>
    
    
    <tr>
     <td style="color:#7D2252;" valign="top">Training</td>
     <td colspan="3">     	
     	<table  width="80%" border="1" style="border-collapse: collapse;">
        <tr>
        	<td width="30%" align="center"><b>Training Name</b></td>
        	<td width="30%" align="center"><b>Institute/Training Center</b></td>
        	<td width="10%" align="center"><b>Duration</b></td>
        	<td width="30%" align="center"><b>Description</b></td>
        </tr>
		<s:iterator value="trainingList" id="training">
		 <tr>
		  <td align="left"><s:property value="trainingName"/></td>
		  <td align="left"><s:property value="fromWhere"/></td>
		  <td align="left"><s:property value="duration"/></td>
		  <td align="left"><s:property value="description"/></td>
		 </tr>
		</s:iterator>
		</table>
     </td>
    </tr>
    
    </table>
    <input type="hidden" name="trainings" value="<s:property value="trainings" />" />
    
    
    </p>
</div>
<p style="height: 30px"></p>
<div class="boxPreview">
    <h3>Address</h3>
    
    
    <table width="100%" border="0" cellspacing="1" class="infoTable" style="margin-top: -10px;">
    <tr bgcolor="#E3DAD7">
     <td align="center" width="50%" colspan="2" style="text-align: center;font-weight: bold;">Permanent Address</td>
     <td align="center" width="50%" colspan="2" style="text-align: center;font-weight: bold;">Mailing Address
     <input type="hidden"  name="mAddress.sameAsPermanent" value="<s:property value="mAddress.sameAsPermanent" />"/>
     </td>
    </tr>
    <tr>
     <td width="15%" style="color:#7D2252;">Division</td>
     <td width="35%">     	
     	<s:property value="pAddress.divisionName" />     	
     	<input type="hidden" name="pAddress.divisionId" value="<s:property value="pAddress.divisionId" />" />
     	
     </td>
     <td width="15%" style="color:#7D2252;">Division</td>
     <td width="35%">
     	<s:property value="mAddress.divisionName" />
     	<input type="hidden" name="mAddress.divisionId" value="<s:property value="mAddress.divisionId" />" />
     </td>
    </tr>
    
    <tr>
     <td width="15%" style="color:#7D2252;">Zila/District</td>
     <td width="35%">     	
     	<s:property value="pAddress.districtName" />     	
     	<input type="hidden" name="pAddress.districtId" value="<s:property value="pAddress.districtId" />" />
     	
     </td>
     <td width="15%" style="color:#7D2252;">Zila/District</td>
     <td width="35%">
     	<s:property value="mAddress.districtName" />
     	<input type="hidden" name="mAddress.districtId" value="<s:property value="mAddress.districtId" />" />
     </td>
    </tr>
    
    <tr>
     <td width="15%" style="color:#7D2252;">Upazila/Thana</td>
     <td width="35%">     	
     	<s:property value="pAddress.upazillaOrThanaName" />     	
     	<input type="hidden" name="pAddress.upazillaOrThanaId" value="<s:property value="pAddress.upazillaOrThanaId" />" />
     	
     </td>
     <td width="15%" style="color:#7D2252;">Upazila/Thana</td>
     <td width="35%">
     	<s:property value="mAddress.upazillaOrThanaName" />
     	<input type="hidden" name="mAddress.upazillaOrThanaId" value="<s:property value="mAddress.upazillaOrThanaId" />" />
     </td>
    </tr>
    
    <tr>
     <td width="15%" style="color:#7D2252;">Union/Ward</td>
     <td width="35%">     	
     	<s:property value="pAddress.unionOrWardName" />     	
     	<input type="hidden" name="pAddress.unionOrWardId" value="<s:property value="pAddress.unionOrWardId" />" />
     	
     </td>
     <td width="15%" style="color:#7D2252;">Union/Ward</td>
     <td width="35%">
     	<s:property value="mAddress.unionOrWardName" />
     	<input type="hidden" name="mAddress.unionOrWardId" value="<s:property value="mAddress.unionOrWardId" />" />
     </td>
    </tr>
    
    <tr>
     <td width="15%" style="color:#7D2252;">Mauza/Moholla</td>
     <td width="35%">     	
     	<s:property value="pAddress.mauzaOrMohollaName" />     	
     	<input type="hidden" name="pAddress.mauzaOrMohollaId" value="<s:property value="pAddress.mauzaOrMohollaId" />" />
     	
     </td>
     <td width="15%" style="color:#7D2252;">Mauza/Moholla</td>
     <td width="35%">
     	<s:property value="mAddress.mauzaOrMohollaName" />
     	<input type="hidden" name="mAddress.mauzaOrMohollaId" value="<s:property value="mAddress.mauzaOrMohollaId" />" />
     </td>
    </tr>
    
    
    <tr>
     <td width="15%" style="color:#7D2252;">Village</td>
     <td width="35%">     	
     	<s:property value="pAddress.villageName" />     	
     	<input type="hidden" name="pAddress.villageId" value="<s:property value="pAddress.villageId" />" />
     	
     </td>
     <td width="15%" style="color:#7D2252;">Village</td>
     <td width="35%">
     	<s:property value="mAddress.villageName" />
     	<input type="hidden" name="mAddress.villageId" value="<s:property value="mAddress.villageId" />" />
     </td>
    </tr>
    
    <tr>
     <td width="15%" style="color:#7D2252;">Post Office</td>
     <td width="35%">     	
     	<s:property value="pAddress.postOffice" />     	
     	<input type="hidden" name="pAddress.postOffice" value="<s:property value="pAddress.postOffice" />" />
     	
     </td>
     <td width="15%" style="color:#7D2252;">Post Office</td>
     <td width="35%">
     	<s:property value="mAddress.postOffice" />
     	<input type="hidden" name="mAddress.postOffice" value="<s:property value="mAddress.postOffice" />" />
     </td>
    </tr>
    
    
    <tr>
     <td width="15%" style="color:#7D2252;">Post Code</td>
     <td width="35%">     	
     	<s:property value="pAddress.postCode" />     	
     	<input type="hidden" name="pAddress.postCode" value="<s:property value="pAddress.postCode" />" />
     	
     </td>
     <td width="15%" style="color:#7D2252;">Post Code</td>
     <td width="35%">
     	<s:property value="mAddress.postCode" />
     	<input type="hidden" name="mAddress.postCode" value="<s:property value="mAddress.postCode" />" />
     </td>
    </tr>
    
    <tr>
     <td width="15%" style="color:#7D2252;">Road Number</td>
     <td width="35%">     	
     	<s:property value="pAddress.roadNumber" />     	
     	<input type="hidden" name="pAddress.roadNumber" value="<s:property value="pAddress.roadNumber" />" />
     	
     </td>
     <td width="15%" style="color:#7D2252;">Road Number</td>
     <td width="35%">
     	<s:property value="mAddress.roadNumber" />
     	<input type="hidden" name="mAddress.roadNumber" value="<s:property value="mAddress.roadNumber" />" />
     </td>
    </tr>
    
    <tr>
     <td width="15%" style="color:#7D2252;">Household Number</td>
     <td width="35%">     	
     	<s:property value="pAddress.houseHoldNumber" />     	
     	<input type="hidden" name="pAddress.houseHoldNumber" value="<s:property value="pAddress.houseHoldNumber" />" />
     	
     </td>
     <td width="15%" style="color:#7D2252;">Household Number</td>
     <td width="35%">
     	<s:property value="mAddress.houseHoldNumber" />
     	<input type="hidden" name="mAddress.houseHoldNumber" value="<s:property value="mAddress.houseHoldNumber" />" />
     </td>
    </tr>
    
    
   </table>

</div>
<p style="height: 10px"></p>
<div style="margin-top: 10px;">
<input type="button" class="submitButton" name="Edit Application" value="Go Back" onclick="submitForm('editRegFormAction.action')"/>
<input type="button" class="submitButton" name="Submit Application" value="Submit Application" style="margin-left: 100px;" onclick="submitForm('submitRegistration.action')"/>
</div>
<p style="height: 30px"></p>
</center>

</form>

</body>
</html>
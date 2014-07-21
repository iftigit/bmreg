
<%@page import="org.table.PersonalInfoDTO"%>
<%@page import="org.table.NomineeDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.table.ExperienceDTO"%>
<%@page import="org.table.JobPreferenceDTO"%>
<%@page import="org.table.LanguageDTO"%>
<%@page import="org.table.TrainingDTO"%>
<%@page import="org.table.EducationDTO"%>
<%@page import="org.table.AddressDTO"%>
<DIV onMouseUp="return false"  onMouseMove="return false" onMouseDown="return false"
id="bid" onDblClick="return false" style="DISPLAY: none; Z-INDEX: 500; LEFT: 0px; POSITION: absolute; overflow:none;
TOP: 0px; BACKGROUND-COLOR: white;filter:alpha(opacity=70); opacity:0.7; height:100%; width:100%;" onclick="return false" >
<center>

<p style="margin-top: 260px;">
</p>
<div id="ifti" class="jquery-corner" style="background-color: #C1D979;height: 100px; width: 400px;padding-top: 10px;">
 <center>
 <div>
<img src="/BMREG_WEB/resources/images/ajax-loader.gif" border="0" />
</div>
<br/><br/>
 <font style="color: red; font-size: 15px; font-weight: bold;">Loading....</font>
</center>

</div>


</center>
</DIV>
<script type="text/javascript">
var pageHeight,pageWidth;
var xScroll, yScroll;

function getPageSize(){
if (window.innerHeight && window.scrollMaxY) {
xScroll = document.body.scrollWidth;
yScroll = window.innerHeight + window.scrollMaxY;
} else if (document.body.scrollHeight > document.body.offsetHeight){ // all but Explorer Mac
xScroll = document.body.scrollWidth;
yScroll = document.body.scrollHeight;
} else { // Explorer Mac...would also work in Explorer 6 Strict, Mozilla and Safari
xScroll = document.body.offsetWidth;
yScroll = document.body.offsetHeight;
}

var windowWidth, windowHeight;
if (self.innerHeight) { // all except Explorer
windowWidth = self.innerWidth;
windowHeight = self.innerHeight;
} else if (document.documentElement && document.documentElement.clientHeight) { // Explorer 6 Strict Mode
windowWidth = document.documentElement.clientWidth;
windowHeight =document.documentElement.clientHeight;
} else if (document.body) { // other Explorers
windowWidth = document.body.clientWidth;
windowHeight = document.body.clientHeight;
}

// for small pages with total height less then height of the viewport
if(yScroll < windowHeight){
pageHeight = windowHeight;
} else {
pageHeight = yScroll;
}

// for small pages with total width less then width of the viewport
if(xScroll < windowWidth){
pageWidth = windowWidth;
} else {
pageWidth = xScroll;
}

arrayPageSize = new Array(pageWidth,pageHeight,windowWidth,windowHeight)
return arrayPageSize;
}


getPageSize();

function loadingComplete()
{
 document.getElementById("bid").style.display = "none";
}

var tmp=document.getElementById("bid").style;
var heightPG=pageHeight;
var widthPG=document.body.scrollWidth;
tmp.height=heightPG+"px";tmp.width=widthPG+"px";
document.getElementById("bid").style.display = "block";

</script>

<%String reset_Gender=((PersonalInfoDTO) (request.getAttribute("personalDTO"))).getEmpGender(); %>
<%//String reset_Religion=((PersonalInfoDTO) (request.getAttribute("personalDTO"))).getEmpReligion(); %>
<%//String reset_MaritalStatus=((PersonalInfoDTO) (request.getAttribute("personalDTO"))).getEmpMaritalStatus(); %>
<%//String reset_BloodGroup=((PersonalInfoDTO) (request.getAttribute("personalDTO"))).getEmpBloodGroup(); %>
<%String reset_DisabilityYN=((PersonalInfoDTO) (request.getAttribute("personalDTO"))).getEmpDisabilityYN(); %>

<%String reset_birthDistrict=((PersonalInfoDTO) (request.getAttribute("personalDTO"))).getEmpBirthDistrict(); %>
<%String reset_birthUpazillaOrThana=((PersonalInfoDTO) (request.getAttribute("personalDTO"))).getEmpBirthUpazilaOrThana(); %>

<%String reset_nomineeRelation=((NomineeDTO) (request.getAttribute("nomineeDTO"))).getNomineeRelation(); %>
<%String reset_nomineeContact1Relation=((NomineeDTO) (request.getAttribute("nomineeDTO"))).getContact1Relation(); %>
<%String reset_nomineeContact2Relation=((NomineeDTO) (request.getAttribute("nomineeDTO"))).getContact2Relation(); %>
<%String reset_nomineeContact3Relation=((NomineeDTO) (request.getAttribute("nomineeDTO"))).getContact3Relation(); %>

<%String reset_religion=((PersonalInfoDTO) (request.getAttribute("personalDTO"))).getEmpReligion(); %>
<%String reset_maritalStatus=((PersonalInfoDTO) (request.getAttribute("personalDTO"))).getEmpMaritalStatus(); %>
<%String reset_bloodGroup=((PersonalInfoDTO) (request.getAttribute("personalDTO"))).getEmpBloodGroup(); %>
<%int reset_degree=((EducationDTO) (request.getAttribute("educationDTO"))).getHeighestDegreeId(); %>
<%String reset_passingYear=((EducationDTO) (request.getAttribute("educationDTO"))).getPassingYear(); %>



<%String reset_gender=((PersonalInfoDTO) (request.getAttribute("personalDTO"))).getEmpGender(); %>
<% if(reset_gender.equalsIgnoreCase("M")){ %>
<script type="text/javascript">
document.getElementById("sexMale").checked=true;
document.getElementById("sexFemale").checked=false;
</script>
<%} else if(reset_gender.equalsIgnoreCase("F")){ %>
<script type="text/javascript">
document.getElementById("sexMale").checked=false;
document.getElementById("sexFemale").checked=true;
</script>
<%} %>


<%String reset_disabilityYN=((PersonalInfoDTO) (request.getAttribute("personalDTO"))).getEmpDisabilityYN(); %>
<% if(reset_disabilityYN.equalsIgnoreCase("Y")){ %>
<script type="text/javascript">
document.getElementById("disablilityYes").checked=true;
document.getElementById("disablilityNo").checked=false;
document.getElementById("disabilityDiv").style.display="block";
</script>
<%} else if(reset_disabilityYN.equalsIgnoreCase("N")){ %>
<script type="text/javascript">
document.getElementById("disablilityYes").checked=false;
document.getElementById("disablilityNo").checked=true;
</script>
<%} %>

<%String reset_childYN=((PersonalInfoDTO) (request.getAttribute("personalDTO"))).getEmpChildYN(); %>
<% if(reset_childYN.equalsIgnoreCase("Y")){ %>
<script type="text/javascript">
document.getElementById("childYes").checked=true;
document.getElementById("childNo").checked=false;
document.getElementById("childDetailDiv").style.display="block";
</script>
<%} else if(reset_childYN.equalsIgnoreCase("N")){ %>
<script type="text/javascript">
document.getElementById("childYes").checked=false;
document.getElementById("childNo").checked=true;
</script>
<%} %>


<script type="text/javascript">
calculateAge('age','birthDate');

 for(var i=0;i<document.forms['empRegForm'].BIRTH_DIST.length;i++)
	  { 
        if(document.forms['empRegForm'].BIRTH_DIST.options[i].value=="<%=reset_birthDistrict%>")
		   document.forms['empRegForm'].BIRTH_DIST.selectedIndex=i;
      }
 fetchJSONData_UpazillaOrThana('<%=reset_birthDistrict%>','BIRTH_UPAZILLA_OR_THANA');
 setTimeout("functionSetBirthUpzillaOrThana()",1000);
 
 function functionSetBirthUpzillaOrThana(){
	for(var i=0;i<document.forms['empRegForm'].BIRTH_UPAZILLA_OR_THANA.length;i++)
	  { 
        if(document.forms['empRegForm'].BIRTH_UPAZILLA_OR_THANA.options[i].value=="<%=reset_birthUpazillaOrThana%>")
		   document.forms['empRegForm'].BIRTH_UPAZILLA_OR_THANA.selectedIndex=i;
      }
 }
 
for(var i=0;i<document.forms['empRegForm'].religion.length;i++) {
        if(document.forms['empRegForm'].religion.options[i].value=="<%=reset_religion%>")
		   document.forms['empRegForm'].religion.selectedIndex=i;
 }  
 
for(var i=0;i<document.forms['empRegForm'].maritalStatus.length;i++) {
        if(document.forms['empRegForm'].maritalStatus.options[i].value=="<%=reset_maritalStatus%>")
		   document.forms['empRegForm'].maritalStatus.selectedIndex=i;
 }  
 
for(var i=0;i<document.forms['empRegForm'].bloodGroup.length;i++) {
        if(document.forms['empRegForm'].bloodGroup.options[i].value=="<%=reset_bloodGroup%>")
		   document.forms['empRegForm'].bloodGroup.selectedIndex=i;
 }  
 
 for(var i=0;i<document.forms['empRegForm'].nomineeRelationship.length;i++) {
        if(document.forms['empRegForm'].nomineeRelationship.options[i].value=="<%=reset_nomineeRelation%>")
		   document.forms['empRegForm'].nomineeRelationship.selectedIndex=i;
 }
 for(var i=0;i<document.forms['empRegForm'].nomineeContactRelation1.length;i++) {
        if(document.forms['empRegForm'].nomineeContactRelation1.options[i].value=="<%=reset_nomineeContact1Relation%>")
		   document.forms['empRegForm'].nomineeContactRelation1.selectedIndex=i;
 }
 for(var i=0;i<document.forms['empRegForm'].nomineeContactRelation2.length;i++) {
        if(document.forms['empRegForm'].nomineeContactRelation2.options[i].value=="<%=reset_nomineeContact2Relation%>")
		   document.forms['empRegForm'].nomineeContactRelation2.selectedIndex=i;
 }
 for(var i=0;i<document.forms['empRegForm'].nomineeContactRelation3.length;i++) {
        if(document.forms['empRegForm'].nomineeContactRelation3.options[i].value=="<%=reset_nomineeContact3Relation%>")
		   document.forms['empRegForm'].nomineeContactRelation3.selectedIndex=i;
 }
 for(var i=0;i<document.forms['empRegForm'].heighestDegree.length;i++) {
        if(document.forms['empRegForm'].heighestDegree.options[i].value=="<%=reset_degree%>")
		   document.forms['empRegForm'].heighestDegree.selectedIndex=i;
 }    
 for(var i=0;i<document.forms['empRegForm'].passingYear.length;i++) {
        if(document.forms['empRegForm'].passingYear.options[i].value=="<%=reset_passingYear%>")
		   document.forms['empRegForm'].passingYear.selectedIndex=i;
 }    
  
</script>
<script type="text/javascript">
 if(document.getElementById("maritalStatus").value!="Single"){
	document.getElementById("childDiv").style.display="block";
	document.getElementById("spouseName").disabled=false;	
	}   
</script>

<script type="text/javascript">
	if(document.getElementById("passportNumber").value!=""){
		document.getElementById("prevPassportNo").disabled=false;
		document.getElementById("passportIssueDate").disabled=false;
		document.getElementById("passportExpireDate").disabled=false;
	}
</script>


<script type="text/javascript"> 
setTimeout("setLocalExperience()",200);
function setLocalExperience()
{
<%ArrayList<ExperienceDTO> localExp=(ArrayList<ExperienceDTO>) request.getAttribute("localExperienceList"); 
if(localExp.size()>=1)
 {
 for(int j=0;j<localExp.size();j++)
 {
  %>
 addMoreLocalExpLoad();
<%}
 }
%>
<%for(int i=0;i<localExp.size();i++) { %>
  $1('localExpYear'+'<%=i%>').value="<%=((ExperienceDTO) localExp.get(i)).getTotalYears()%>";
  
  for(var i=0;i<document.forms['empRegForm'].localExpJobCat<%=i%>.length;i++) {
        if(document.forms['empRegForm'].localExpJobCat<%=i%>.options[i].value=="<%=localExp.get(i).getJobCategoryId()%>"){
		   document.forms['empRegForm'].localExpJobCat<%=i%>.selectedIndex=i;
		   fetchJobCategory(<%=localExp.get(i).getJobCategoryId()%>,2,<%=i%>,'localExpJobSubCat<%=i%>','localJobCategory');
		   }
  }
<%}%>

setTimeout(function(){
<%for(int i=0;i<localExp.size();i++) { %>
  if(document.forms['empRegForm'].localSubJob_1_<%=i%>){
  for(var i=0;i<document.forms['empRegForm'].localSubJob_1_<%=i%>.length;i++) {
        if(document.forms['empRegForm'].localSubJob_1_<%=i%>.options[i].value=="<%=localExp.get(i).getJobSubCategoryId()%>"){
		   document.forms['empRegForm'].localSubJob_1_<%=i%>.selectedIndex=i;
		   fetchJobCategory(<%=localExp.get(i).getJobSubCategoryId()%>,3,<%=i%>,'localExpJobSubSubCat<%=i%>','localJobCategory');
		   }
    }
 }
<%}%>
},1000);

setTimeout(function(){
<%for(int i=0;i<localExp.size();i++) { %>
  if(document.forms['empRegForm'].localSubJob_2_<%=i%>) {
  for(var i=0;i<document.forms['empRegForm'].localSubJob_2_<%=i%>.length;i++) {
        if(document.forms['empRegForm'].localSubJob_2_<%=i%>.options[i].value=="<%=localExp.get(i).getJobSubSubCategoryId()%>"){
		   document.forms['empRegForm'].localSubJob_2_<%=i%>.selectedIndex=i;
		   }
    }
 }
<%}%>
},2000);

} 


 
setTimeout("setAbroadExperience()",200);
function setAbroadExperience()
{
<%ArrayList<ExperienceDTO> abroadExp=(ArrayList<ExperienceDTO>) request.getAttribute("abroadExperienceList"); 
if(abroadExp.size()>=1)
 {
 for(int j=0;j<abroadExp.size();j++)
 {
  %>
 addMoreAbroadExpLoad();
<%}
 }
%>
<%for(int i=0;i<abroadExp.size();i++) { %>
  $1('abroadExpYear'+'<%=i%>').value="<%=((ExperienceDTO) abroadExp.get(i)).getTotalYears()%>";
  
  for(var i=0;i<document.forms['empRegForm'].abroadExpCountry<%=i%>.length;i++) {
        if(document.forms['empRegForm'].abroadExpCountry<%=i%>.options[i].value=="<%=abroadExp.get(i).getCountryId()%>"){
		   document.forms['empRegForm'].abroadExpCountry<%=i%>.selectedIndex=i;
		   }
  }
  
  for(var i=0;i<document.forms['empRegForm'].abroadExpJobCat<%=i%>.length;i++) {
        if(document.forms['empRegForm'].abroadExpJobCat<%=i%>.options[i].value=="<%=localExp.get(i).getJobCategoryId()%>"){
		   document.forms['empRegForm'].abroadExpJobCat<%=i%>.selectedIndex=i;
		   fetchJobCategory(<%=abroadExp.get(i).getJobCategoryId()%>,2,<%=i%>,'abroadExpJobSubCat<%=i%>','abroadJobCategory');
		   }
  }
<%}%>

setTimeout(function(){
<%for(int i=0;i<abroadExp.size();i++) { %>
  if(document.forms['empRegForm'].abroadSubJob_1_<%=i%>){
  for(var i=0;i<document.forms['empRegForm'].abroadSubJob_1_<%=i%>.length;i++) {
        if(document.forms['empRegForm'].abroadSubJob_1_<%=i%>.options[i].value=="<%=abroadExp.get(i).getJobSubCategoryId()%>"){
		   document.forms['empRegForm'].abroadSubJob_1_<%=i%>.selectedIndex=i;
		   fetchJobCategory(<%=abroadExp.get(i).getJobSubCategoryId()%>,3,<%=i%>,'abroadExpJobSubSubCat<%=i%>','abroadJobCategory');
		   }
    }
 }
<%}%>
},1000);

setTimeout(function(){
<%for(int i=0;i<abroadExp.size();i++) { %>
  if(document.forms['empRegForm'].abroadSubJob_2_<%=i%>) {
  for(var i=0;i<document.forms['empRegForm'].abroadSubJob_2_<%=i%>.length;i++) {
        if(document.forms['empRegForm'].abroadSubJob_2_<%=i%>.options[i].value=="<%=abroadExp.get(i).getJobSubSubCategoryId()%>"){
		   document.forms['empRegForm'].abroadSubJob_2_<%=i%>.selectedIndex=i;
		   }
    }
 }
<%}%>
},2000);

}    

setTimeout("setJobPreference()",200);
function setJobPreference()
{
<%ArrayList<JobPreferenceDTO> jobPreferenceList=(ArrayList<JobPreferenceDTO>) request.getAttribute("jobPreferenceList"); 
if(jobPreferenceList.size()>=1)
 {
 for(int j=0;j<jobPreferenceList.size();j++)
 {
  %>
 addJobPreferenceDiv();
<%}
 }
%>
<%for(int i=0;i<jobPreferenceList.size();i++) { %>
  
  for(var i=0;i<document.forms['empRegForm'].mainJob_<%=i%>.length;i++) {
        if(document.forms['empRegForm'].mainJob_<%=i%>.options[i].value=="<%=jobPreferenceList.get(i).getCategoryId()%>"){
		   document.forms['empRegForm'].mainJob_<%=i%>.selectedIndex=i;
		   fetchJobCategory(<%=jobPreferenceList.get(i).getCategoryId()%>,2,<%=i%>,'leftDiv<%=i%>2','jobPreferenceJobCategory')
		   }
  }
<%}%>

setTimeout(function(){
<%for(int i=0;i<jobPreferenceList.size();i++) { %>
  if(document.forms['empRegForm'].subJob_1_<%=i%>){
  for(var i=0;i<document.forms['empRegForm'].subJob_1_<%=i%>.length;i++) {
        if(document.forms['empRegForm'].subJob_1_<%=i%>.options[i].value=="<%=jobPreferenceList.get(i).getSubCategoryId()%>"){
		   document.forms['empRegForm'].subJob_1_<%=i%>.selectedIndex=i;
		   fetchJobCategory(<%=jobPreferenceList.get(i).getSubCategoryId()%>,3,<%=i%>,'leftDiv<%=i%>3','jobPreferenceJobCategory')
		   }
    }
 }
<%}%>
},1000);

setTimeout(function(){
<%for(int i=0;i<jobPreferenceList.size();i++) { %>
  if(document.forms['empRegForm'].subJob_2_<%=i%>) {
  for(var i=0;i<document.forms['empRegForm'].subJob_2_<%=i%>.length;i++) {
        if(document.forms['empRegForm'].subJob_2_<%=i%>.options[i].value=="<%=jobPreferenceList.get(i).getSubSubCategoryId()%>"){
		   document.forms['empRegForm'].subJob_2_<%=i%>.selectedIndex=i;
		   }
    }
 }
<%}%>
},2000);

}   
</script>
<script type="text/javascript">
setLanguageTyped();
function setLanguageTyped()
{
<%ArrayList<LanguageDTO> language_list=(ArrayList<LanguageDTO>) request.getAttribute("empLanguageList"); 
if(language_list.size()>=1)
 {
 for(int j=0;j<language_list.size();j++)
 {
  %>
 addMoreLanguage();
<%}
 }
%>
<%for(int i=0;i<language_list.size();i++)
 {
%>
 $1('language'+'<%=i%>').value="<%=((LanguageDTO) language_list.get(i)).getLanguage()%>";
 $1('oral'+'<%=i%>').value="<%=((LanguageDTO) language_list.get(i)).getOralSkill()%>";
 $1('writing'+'<%=i%>').value="<%=((LanguageDTO) language_list.get(i)).getWritingSkill()%>";
<%}%>
}
</script>
<script type="text/javascript">

setTimeout("setTrainingTyped()",200);
function setTrainingTyped()
{
<%ArrayList<TrainingDTO> training_list=(ArrayList<TrainingDTO>) request.getAttribute("trainingList"); 
if(training_list.size()>=1)
 {
 for(int j=0;j<training_list.size();j++)
 {
  %>
 addMoreTraining();
<%}
 }
%>
<%for(int i=0;i<training_list.size();i++)
 {
%>
 $1('trainingName'+'<%=i%>').value="<%=((TrainingDTO) training_list.get(i)).getTrainingName()%>";
 $1('trainingFrom'+'<%=i%>').value="<%=((TrainingDTO) training_list.get(i)).getFromWhere()%>";
 $1('trainingDuration'+'<%=i%>').value="<%=((TrainingDTO) training_list.get(i)).getDuration()%>";
 $1('trainingDesc'+'<%=i%>').value="<%=((TrainingDTO) training_list.get(i)).getDescription()%>";
<%}%>
}
</script>
<script type="text/javascript">
/*!!!!!!!  Resetting Address Information of Nominee  !!!!!!!!!*/
 <% AddressDTO nomineeAddress=((AddressDTO) ((NomineeDTO)request.getAttribute("nomineeDTO")).getAddress());%>
 
 if("<%=nomineeAddress.getDivisionId()%>"!="")
  {
      for(var i=0;i<document.forms['empRegForm'].NOMINEE_DIV.length;i++)
	  { 
        if(document.forms['empRegForm'].NOMINEE_DIV.options[i].value=="<%=nomineeAddress.getDivisionId()%>")
		   {
		    document.forms['empRegForm'].NOMINEE_DIV.selectedIndex=i;
			fetchJSONData_Dist("<%=nomineeAddress.getDivisionId()%>","NOMINEE_DIST");
   		    funcSetNomineeDistrict();  
  	   }
      }
  
  }
							 
function funcSetNomineeDistrict(){
 <% String sub_nomineeDistrict=nomineeAddress.getDistrictId(); %>
     for(var i=0;i<document.forms['empRegForm'].NOMINEE_DIST.length;i++)
	  { 
        if(document.forms['empRegForm'].NOMINEE_DIST.options[i].value=="<%=sub_nomineeDistrict%>")
		   {
		    document.forms['empRegForm'].NOMINEE_DIST.selectedIndex=i;
		    fetchJSONData_UpazillaOrThana("<%=sub_nomineeDistrict%>","NOMINEE_UPAZILLA_OR_THANA");
		    funcSetNomineeUpazillaOrThana();
		   }
      }
 
}

function funcSetNomineeUpazillaOrThana(){
 <% String sub_nomineeUpazillaOrThana=nomineeAddress.getUpazillaOrThanaId(); %>
    for(var i=0;i<document.forms['empRegForm'].NOMINEE_UPAZILLA_OR_THANA.length;i++)
	  { 
        if(document.forms['empRegForm'].NOMINEE_UPAZILLA_OR_THANA.options[i].value=="<%=sub_nomineeUpazillaOrThana%>")
		   {
		    document.forms['empRegForm'].NOMINEE_UPAZILLA_OR_THANA.selectedIndex=i;
		    fetchJSONData_UnionOrWard("<%=sub_nomineeUpazillaOrThana%>","NOMINEE_UNION_OR_WARD");
		    funcSetNomineeUnionOrWard();
		   }
      }
 
}
function funcSetNomineeUnionOrWard(){
 <% String sub_nomineeUnionorWard=nomineeAddress.getUnionOrWardId(); %>
    for(var i=0;i<document.forms['empRegForm'].NOMINEE_UNION_OR_WARD.length;i++)
	  { 
        if(document.forms['empRegForm'].NOMINEE_UNION_OR_WARD.options[i].value=="<%=sub_nomineeUnionorWard%>")
		   {
		    document.forms['empRegForm'].NOMINEE_UNION_OR_WARD.selectedIndex=i;
		    fetchJSONData_MauzaOrMoholla("<%=sub_nomineeUnionorWard%>","NOMINEE_MAUZA_OR_MOHOLLA");
		    funcSetNomineeMauzaOrMoholla();
		   }
      }
 
}
function funcSetNomineeMauzaOrMoholla(){
 <% String sub_nomineeMauzaOrMoholla=nomineeAddress.getMauzaOrMohollaId(); %>
    for(var i=0;i<document.forms['empRegForm'].NOMINEE_MAUZA_OR_MOHOLLA.length;i++)
	  { 
        if(document.forms['empRegForm'].NOMINEE_MAUZA_OR_MOHOLLA.options[i].value=="<%=sub_nomineeMauzaOrMoholla%>")
		   {
		    document.forms['empRegForm'].NOMINEE_MAUZA_OR_MOHOLLA.selectedIndex=i;
		    fetchJSONData_Village("<%=sub_nomineeMauzaOrMoholla%>","NOMINEE_VILLAGE");
		    funcSetNomineeVillage();
		   }
      }
 
}
function funcSetNomineeVillage(){
 <% String sub_village=nomineeAddress.getVillageId(); %>
    for(var i=0;i<document.forms['empRegForm'].NOMINEE_VILLAGE.length;i++)
	  { 
        if(document.forms['empRegForm'].NOMINEE_VILLAGE.options[i].value=="<%=sub_village%>")
		   {
		    document.forms['empRegForm'].NOMINEE_VILLAGE.selectedIndex=i;
		   }
      }
 
}
/*!!!!!!!  Resetting Address Information of Jobseeker  !!!!!!!!!*/
 <% AddressDTO pAddress=((AddressDTO)request.getAttribute("pAddress"));%>
 
 if("<%=pAddress.getDivisionId()%>"!="")
  {
      for(var i=0;i<document.forms['empRegForm'].PERMANENT_DIV.length;i++)
	  { 
        if(document.forms['empRegForm'].PERMANENT_DIV.options[i].value=="<%=pAddress.getDivisionId()%>")
		   {
		    document.forms['empRegForm'].PERMANENT_DIV.selectedIndex=i;
			fetchJSONData_Dist("<%=pAddress.getDivisionId()%>","PERMANENT_DIST");
   		    funcSetPermanentDistrict();  
  	   }
      }
  
  }
							 
function funcSetPermanentDistrict(){
 <% String sub_pDistrict=pAddress.getDistrictId(); %>
     for(var i=0;i<document.forms['empRegForm'].PERMANENT_DIST.length;i++)
	  { 
        if(document.forms['empRegForm'].PERMANENT_DIST.options[i].value=="<%=sub_pDistrict%>")
		   {
		    document.forms['empRegForm'].PERMANENT_DIST.selectedIndex=i;
		    fetchJSONData_UpazillaOrThana("<%=sub_pDistrict%>","PERMANENT_UPAZILLA_OR_THANA");
		    funcSetPermanentUpazillaOrThana();
		   }
      }
 
}

function funcSetPermanentUpazillaOrThana(){
 <% String sub_permamentUpazillaOrThana=pAddress.getUpazillaOrThanaId(); %>
    for(var i=0;i<document.forms['empRegForm'].PERMANENT_UPAZILLA_OR_THANA.length;i++)
	  { 
        if(document.forms['empRegForm'].PERMANENT_UPAZILLA_OR_THANA.options[i].value=="<%=sub_permamentUpazillaOrThana%>")
		   {
		    document.forms['empRegForm'].PERMANENT_UPAZILLA_OR_THANA.selectedIndex=i;
		    fetchJSONData_UnionOrWard("<%=sub_permamentUpazillaOrThana%>","PERMANENT_UNION_OR_WARD");
		    funcSetPermanentUnionOrWard();
		   }
      }
 
}
function funcSetPermanentUnionOrWard(){
 <% String sub_permanentUnionorWard=pAddress.getUnionOrWardId(); %>
    for(var i=0;i<document.forms['empRegForm'].PERMANENT_UNION_OR_WARD.length;i++)
	  { 
        if(document.forms['empRegForm'].PERMANENT_UNION_OR_WARD.options[i].value=="<%=sub_permanentUnionorWard%>")
		   {
		    document.forms['empRegForm'].PERMANENT_UNION_OR_WARD.selectedIndex=i;
		    fetchJSONData_MauzaOrMoholla("<%=sub_permanentUnionorWard%>","PERMANENT_MAUZA_OR_MOHOLLA");
		    funcSetPermanentMauzaOrMoholla();
		   }
      }
 
}
function funcSetPermanentMauzaOrMoholla(){
 <% String sub_permanentMauzaOrMoholla=pAddress.getMauzaOrMohollaId(); %>
    for(var i=0;i<document.forms['empRegForm'].PERMANENT_MAUZA_OR_MOHOLLA.length;i++)
	  { 
        if(document.forms['empRegForm'].PERMANENT_MAUZA_OR_MOHOLLA.options[i].value=="<%=sub_permanentMauzaOrMoholla%>")
		   {
		    document.forms['empRegForm'].PERMANENT_MAUZA_OR_MOHOLLA.selectedIndex=i;
		    fetchJSONData_Village("<%=sub_permanentMauzaOrMoholla%>","PERMANENT_VILLAGE");
		    funcSetPermanentVillage();
		   }
      }
 
}
function funcSetPermanentVillage(){
 <% String sub_permanentVillage=pAddress.getVillageId(); %>
    for(var i=0;i<document.forms['empRegForm'].PERMANENT_VILLAGE.length;i++)
	  { 
        if(document.forms['empRegForm'].PERMANENT_VILLAGE.options[i].value=="<%=sub_permanentVillage%>")
		   {
		    document.forms['empRegForm'].PERMANENT_VILLAGE.selectedIndex=i;
		    setMailingAddress();
		   }
      }
 
}


function setMailingAddress(){
	<% String sameAsPermanent=((AddressDTO) (request.getAttribute("mAddress"))).getSameAsPermanent(); %>  
    if("<%=sameAsPermanent%>"=="on")
     {
      document.getElementById("mp").checked=true;
      copyPermanentAddress(true);
     }
     else
     {    
	<% String sub_MDiv=((AddressDTO) (request.getAttribute("mAddress"))).getDivisionId();%>
	 
	 if("<%=sub_MDiv%>"!="")
	  {
	      for(var i=0;i<document.forms['empRegForm'].MAILING_DIV.length;i++)
		  { 
	        if(document.forms['empRegForm'].MAILING_DIV.options[i].value=="<%=sub_MDiv%>")
			   {
			    document.forms['empRegForm'].MAILING_DIV.selectedIndex=i;
				fetchJSONData_Dist("<%=sub_MDiv%>","MAILING_DIST");
	   		    funcSetMailingDistrict();  
	  	   }
	      }
	  
	  }
} //End of Else

} //End of Function
<% AddressDTO mAddress=((AddressDTO) (request.getAttribute("mAddress")));%>
function funcSetMailingDistrict(){
 <% String sub_mDistrict=mAddress.getDistrictId(); System.out.println("----->>"+sub_mDistrict);%>
     for(var i=0;i<document.forms['empRegForm'].MAILING_DIST.length;i++)
	  { 
        if(document.forms['empRegForm'].MAILING_DIST.options[i].value=="<%=sub_mDistrict%>")
		   {
		    document.forms['empRegForm'].MAILING_DIST.selectedIndex=i;
		    fetchJSONData_UpazillaOrThana("<%=sub_mDistrict%>","MAILING_UPAZILLA_OR_THANA");
		    funcSetMailingUpazillaOrThana();
		   }
      }
 
}

function funcSetMailingUpazillaOrThana(){
 <% String sub_mailingUpazillaOrThana=mAddress.getUpazillaOrThanaId(); %>
    for(var i=0;i<document.forms['empRegForm'].MAILING_UPAZILLA_OR_THANA.length;i++)
	  { 
        if(document.forms['empRegForm'].MAILING_UPAZILLA_OR_THANA.options[i].value=="<%=sub_mailingUpazillaOrThana%>")
		   {
		    document.forms['empRegForm'].MAILING_UPAZILLA_OR_THANA.selectedIndex=i;
		    fetchJSONData_UnionOrWard("<%=sub_mailingUpazillaOrThana%>","MAILING_UNION_OR_WARD");
		    funcSetMailingUnionOrWard();
		   }
      }
 
}
function funcSetMailingUnionOrWard(){
 <% String sub_mailingUnionorWard=mAddress.getUnionOrWardId(); %>
    for(var i=0;i<document.forms['empRegForm'].MAILING_UNION_OR_WARD.length;i++)
	  { 
        if(document.forms['empRegForm'].MAILING_UNION_OR_WARD.options[i].value=="<%=sub_mailingUnionorWard%>")
		   {
		    document.forms['empRegForm'].MAILING_UNION_OR_WARD.selectedIndex=i;
		    fetchJSONData_MauzaOrMoholla("<%=sub_mailingUnionorWard%>","MAILING_MAUZA_OR_MOHOLLA");
		    funcSetMailingMauzaOrMoholla();
		   }
      }
 
}
function funcSetMailingMauzaOrMoholla(){
 <% String sub_mailingMauzaOrMoholla=mAddress.getMauzaOrMohollaId(); %>
    for(var i=0;i<document.forms['empRegForm'].MAILING_MAUZA_OR_MOHOLLA.length;i++)
	  { 
        if(document.forms['empRegForm'].MAILING_MAUZA_OR_MOHOLLA.options[i].value=="<%=sub_mailingMauzaOrMoholla%>")
		   {
		    document.forms['empRegForm'].MAILING_MAUZA_OR_MOHOLLA.selectedIndex=i;
		    fetchJSONData_Village("<%=sub_mailingMauzaOrMoholla%>","MAILING_VILLAGE");
		    funcSetMailingVillage();
		   }
      }
 
}
function funcSetMailingVillage(){
 <% String sub_mailingVillage=mAddress.getVillageId(); %>
    for(var i=0;i<document.forms['empRegForm'].MAILING_VILLAGE.length;i++)
	  { 
        if(document.forms['empRegForm'].MAILING_VILLAGE.options[i].value=="<%=sub_mailingVillage%>")
		   {
		    document.forms['empRegForm'].MAILING_VILLAGE.selectedIndex=i;
		   }
      }
 
}

</script>


<script type="text/javascript">
setTimeout("loadingComplete()",2000);
</script>

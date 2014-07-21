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
<title>BMET</title>
 <link rel="stylesheet" href="/BMREG_WEB/resources/css/style.css" />	
 <script type="text/javascript" src="/BMREG_WEB/resources/js/lib/prototype-1.6.0.2.js"></script>	
 <script type="text/javascript" src="/BMREG_WEB/resources/js/lib/jquery-1.6.4.min.js"></script> 
 <script type="text/javascript" src="/BMREG_WEB/resources/js/util/util.js"></script>
 <script type="text/javascript" src="/BMREG_WEB/resources/js/util/browserDetect.js"></script>
 <script type="text/javascript" src="/BMREG_WEB/resources/js/util/numeric.js"></script> 
 <script type="text/javascript" src="/BMREG_WEB/resources/js/util/xmlextras.js"></script>
 <link rel="stylesheet" type="text/css" href="/BMREG_WEB/resources/jqueryUi/ui-lightness/jquery-ui.css" />
 <script type="text/javascript" src="/BMREG_WEB/resources/jqueryUi/jquery-ui.min.js"></script> 
 <link href="/BMREG_WEB/resources/multiselect/jquery.multiselect.css" rel="stylesheet" type="text/css">
 <script type="text/javascript" src="/BMREG_WEB/resources/multiselect/jquery.multiselect.js"></script>
 <script type="text/javascript" src="/BMREG_WEB/resources/js/registration.js"></script>
 <script type="text/javascript">

var ajax_load="<br/><center><img src='/BMREG_WEB/resources/images/ajax-loader1.gif' border='0' /></center>";

function updateYearInfo()
{

var fromYear=document.getElementById("fromYear").value;
var toYear=document.getElementById("toYear").value;
if(fromYear=="" || toYear=="")
{
 	jQuery("#msgDiv").html("<font color='red'>Please provide correct data.</font>");
 	return;
} 


 var loadUrl="savePassingYearInfo.action?fromYear="+fromYear+"&toYear="+toYear;
			jQuery("#msgDiv")  
				.html(ajax_load)  
				.load(loadUrl, {},function(responseText){  
					jQuery("#msgDiv").html(responseText);
									   
				});
				
}
function validate(type)
{
var sugestedTotal=parseInt(document.getElementById("suggestedTotal").value,10)

 if(document.getElementById("agentId").value=="none"){
  alert("Please select an agency.");
  return;
 }
 else if(document.getElementById("workOrder").value==""){
  alert("Please provide Demand Note number.");
  return;
 }
 else if(sugestedTotal<=0){
  alert("Suggested total cannot be zero.");
  return;
 }
 
 
 var jobPreference="";
 var jobExp="";
 var l1="0"; var l2="0"; var l3="0";
 
 for(var i=0;i<20;i++)
 {
   if(document.getElementById("mainJob_"+i))
    {
       if(document.getElementById("jobP"+i).style.display!="none")
       {
         if(document.getElementById("mainJob_"+i) && document.getElementById("mainJob_"+i).style.display!="none")
 			l1=document.getElementById("mainJob_"+i).value;
         if(document.getElementById("subJob_1_"+i) && document.getElementById("subJob_1_"+i).style.display!="none")
 			l2=document.getElementById("subJob_1_"+i).value;
         if(document.getElementById("subJob_2_"+i) && document.getElementById("subJob_2_"+i).style.display!="none")
 			l3=document.getElementById("subJob_2_"+i).value;
         
         	jobPreference+=l1+"#"+l2+"#"+l3+"@";
       }
    }
    l1=0;l2=0;l3=0;
 } 

 document.getElementById("jobPreference").value=jobPreference;

if(document.getElementById("lotteryJobCat0") && document.getElementById("lotteryJobCat0").value!="")
 l1=document.getElementById("lotteryJobCat0").value;
if(document.getElementById("lotterySubJob_1_0") && document.getElementById("lotterySubJob_1_0").style.display!="none")
 l2=document.getElementById("lotterySubJob_1_0").value;
if(document.getElementById("lotterySubJob_2_0")){
 $( '#lotterySubJob_2_0 :selected' ).each( function( i, selected ) {
      l3+=$( selected ).val()+",";
  });
 }
  
 if(l3.length>0){
    l3=l3.substring(0,l3.length-1);
 }

  
 jobExp=l1+"#"+l2+"#"+l3;
 
 if(l1==0)
  jobExp="";
  
 var expYears=document.getElementById("yearOfExperience").value;
 if(expYears>1 && jobExp=="")
  {
   alert("Please provide proper data for work experiences. Or remove year of experience.")
   return;
  }
 else if(jobExp!="" && (expYears=="" || expYears<0)){
   alert("Please provide year of experience. Or remove experience information.")
   return;
 } 
 document.getElementById("jobExperience").value=jobExp;
 
 if(type=="searchCount")
 {
  fetchTotalExistingJobseekerCount(expYears,jobExp,jobPreference);
  return;
 }
 document.selectionForm.submit();
 
}

function setSuggestedTotal(total){
document.getElementById("suggestedTotal").value=parseInt(total*3,10);
}

function setAgency(licenseNo){

var flag=0;
 $('#agentId option').each(function(){
      if(this.value==licenseNo)
       {        
        $("select#agentId").find("option#"+licenseNo).attr("selected", true); 
        flag=1;       
       }
    });
    
    if(flag==0)
    {
     alert("Please provide a valid License No.");
    }
    
}
</script>
 <style type="text/css">
 .ui-multiselect-header ul {
    font-size: 0.9em !important;
}
.ui-multiselect-header ul {
    font-size: 0.8em !important;
}
.ui-multiselect-checkboxes li {
    font-size: 0.8em !important;
}
.ui-widget {
    font-size: 0.9em !important;
}
 </style>
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
<center>
<br/>
<div class="box" style="margin-top: 30px;width: 950px;text-align: center;">
    <h3>Lottery Home</h3>
    <div style="padding-bottom: 30px;">
    <form action="jobseekerSelection" method="post" id="selectionForm" name="selectionForm">
    <table width="100%" align="center" border="0" style="padding-left: 10px;">
     	<tr>
     		<td width="20%" align="left">License No</td>
     		<td width="80%" align="left">
     		  <input type="text" id="licenseNo" value="" style="border: 1px solid gray;width: 300px;" onblur="setAgency(this.value)" />
     		</td>
        </tr>
     	<tr>
     		<td align="left">Recruiting Agency</td>
     		<td align="left">
     		<select style="width:300px;border:1px solid grey" name="selection.agentId" id="agentId">
     		    <option value='none'>Select Agency</option>
     		<s:iterator value="agentList" status="status">
     			<option id='<s:property value="licenseNumber" />' value='<s:property value="licenseNumber" />'><s:property value="companyName" />&nbsp;&nbsp;[<s:property value="licenseNumber" />]</option>
     		</s:iterator>
     		</select>
        </tr>
        <tr>
     		<td align="left">Demand Note</td>
     		<td align="left"><input type="text" name="selection.workOrder" id="workOrder" value="" style="border: 1px solid gray;width: 300px;" /></td>
        </tr>        
        <tr>
     		<td align="left">Country Preference</td>
     		<td align="left">
     			<select style="width:300px;border:1px solid grey"  id="countryPreference" name="selection.countryPreference">
     			            <option value="">Select Country</option>
							<s:iterator value="countryList" id="countryList">
							  <s:if test='%{#countryList.isSelected == "Y"}'>
							   <option selected='selected' value="<s:property value="countryId" />"><s:property value="countryName" /></option>
							  </s:if>
							  <s:if test='%{#countryList.isSelected != "Y"}'>
							   <option value="<s:property value="countryId" />"><s:property value="countryName" /></option>
							  </s:if>
							  				     	  
					     	</s:iterator>
				</select>
			</td>
        </tr> 
        <tr>
     		<td align="left">Gender</td>
     		<td align="left">
     		<input type="radio"  name="selection.gender"  id="male"  value="M" /> Male &nbsp;&nbsp;&nbsp;
     		<input type="radio"  name="selection.gender"  id="female"  value="F" />Female &nbsp;&nbsp;&nbsp;
     		<input type="radio"  name="selection.gender"  id="all"  value="A" checked="checked" /> Both &nbsp;&nbsp;&nbsp; 
     		</td>
        </tr> 
        <tr>
     		<td align="left">Language</td>
     		<td align="left">
     		<select id="language" name="selection.languages" multiple="multiple" class="txtBox">
							<s:iterator value="languageList" id="languageList">
							   <option value="<s:property value="language" />"><s:property value="language" /></option>
					     	</s:iterator>
				</select>
     		</td>
        </tr>

        <tr>
     		<td align="left">Years of Experience</td>
     		<td align="left"><input type="text" name="selection.yearOfExperience" id="yearOfExperience" value="" style="border: 1px solid gray;" /></td>
        </tr> 
        <tr>
     		<td align="left" style="vertical-align: top;">Work Experience</td>
     		<td align="left">
     		<table align="center" border="0" cellpadding="3" cellspacing="0"
								width="100%" style="border: 1px solid #d1dcaf;">
								<tbody>
									
									<tr bgcolor="#F2F7E3">
										<td align="left" style="padding-left: 34px" width="33%">
											Job Category
										</td>
										<td align="left" style="padding-left: 34px" width="33%">
											Sub Category
										</td>
										<td align="left" style="padding-left: 34px" width="43%">
											Sub-Sub Category
										</td>
										
									</tr>

									<tr bgcolor="#FAFCF3">

										<td align="center"
											style="vertical-align: top; padding-top: 0px; padding-bottom: 0px"
											id="jExpAbroad_column1">
											<table width="100%" id="jobExpCategoryTable" style="margin: 0px;"></table>
										</td>
										<td align="center"
											style="vertical-align: top; padding-top: 0px; padding-bottom: 0px"
											id="jExpAbroad_column2">
											<table width="100%" id="jobExpSubCategoryTable" style="margin: 0px;"></table>
										</td>
										<td align="center"
											style="vertical-align: top; padding-top: 0px; padding-bottom: 0px"
											id="jExpAbroad_column3">
											<table width="100%" id="jobExpSubSubCategoryTable" style="margin: 0px;"></table>
										</td>
										
									</tr>

									
								</tbody>
							</table>
							<span id="msg_expAborad"></span>
							<font style="color:red"><s:label name="sMsg_expAborad"></s:label></font>
							<input type="hidden" id="jobExperience" name="selection.jobExperience" />
     		</td>
        </tr>
        
        <tr>
     		<td align="left" style="vertical-align: top;">Job Preference</td>
     		<td align="left" bgcolor="#F2F7E3" style="border: 1px solid #d1dcaf;">
     		<div style="float: left;padding-left: 5px;width:170px;">Job Category</div>
     		<div style="float: left;padding-left: 5px;width:170px;">Sub Category</div>
     		<div style="float: left;padding-left: 5px;width:170px;">Sub-Sub Category</div>
     		<div id="jobPreferenceDiv"></div>
                    	   <br/><br/>
                    	   <input name='abc' type='button' value='Add more(if needed)' onclick='addJobPreferenceDiv()' width='42' height='9' tabindex='299' />
                    	   <div id="msg_expJobPreference" style="clear: both"></div>
                    	   <input type="hidden" id="jobPreference" name="selection.jobPreference" value="" />
     		</td>
        </tr> 
        
        <tr>
     		<td align="left">Total Emp. Required</td>
     		<td align="left"><input type="text" name="selection.workOrderTotal" id="workOrderTotal" value="" style="border: 1px solid gray;" onkeyup="setSuggestedTotal(this.value)" /></td>
        </tr>
        <tr>
     		<td align="left">Total Emp. Suggested</td>
     		<td align="left"><input type="text" name="selection.suggestedTotal" id="suggestedTotal" value="" style="border: 1px solid gray;"  /></td>
        </tr>
        <tr>
     		<td align="left">Report Type</td>
     		<td align="left">
     		<input type="radio" name="selection.reportType" id="pdf" value="pdf" checked="checked" /> PDF
     		&nbsp;&nbsp;&nbsp;&nbsp;
     		<input type="radio" name="selection.reportType" id="csv" value="csv" /> CSV
     		</td>
        </tr>
        <tr>
     		<td align="left">Total Jobseeker<br/>(in Database)
     		<input type="button" onclick="validate('searchCount')" value='Search' />
     		</td>
     		<td align="left" id="expCount" style="margin-left: 10px;float: left;color: blue;font-size: 15px;font-weight: bold;">
     		</td>
        </tr>
        													
        
        
    </table>
    
	<p style="padding-top: 40px;">     
	<input type="button" name="doSelection" value="Do Selection" style="width: 200px;height: 35px;"  onclick="validate('lottery')"/>
	</p>
	
</form>

</div>
    <s:property value="msg"/><br/>
    <p style="padding-bottom: 20px;">
    	<a href="systemAdminHome.action">Go Home</a>
    </p>

</div>
<p id="msgDiv"></p>
</center>
<script type="text/javascript">
$(document).ready(function(){
   //$("#countryPreference").multiselect();

   $("#language").multiselect({
   		noneSelectedText: 'Select Language',
		selectedText: '# language selected',
});

});

var jobPreferenceCounter=0;
var cntAX=new Array(1);
cntAX[0]=0;

function fetchJobCategory(parentJobId,level,componentIndex,waitingDiv,selectType)
{
	    
		$(".buttonNext").addClass("buttonDisabled");
		
        if(level==2 && selectType=="lotteryJobCategory")
	         document.getElementById("lotteryJobSubSubCat"+componentIndex).innerHTML="";
	    

 		var ajax_url="<img src='/BMREG_WEB/resources/images/ajax-loader.gif' alt='Loading ....' />"; 
		var url="/BMREG_WEB/fetchSubJob.action?etc="+new Date().getTime();
		
			$("#"+waitingDiv) 
			.html(ajax_url)  
			.load(url, {parentJobId: parentJobId,jobLevel: level,componentIndex:componentIndex,selectType:selectType,allOrActive:0},function(responseText){  
				if(responseText!="")
				$("#"+waitingDiv).innerHTML= responseText;
				
				if(level==3){
				
					$("#lotterySubJob_2_"+componentIndex).multiselect({
	   						noneSelectedText: 'Select Sub Sub Job',
							selectedText: '# language Sub Sub Job'
					});
				
				}
				

			});
}

function fetchTotalExistingJobseekerCount(expYears,jobExp,jobPreference)
{
var gender=$('input:radio[name="selection.gender"]:checked').val();
var countryPrefernce=document.getElementById("countryPreference").value;
var language=$('#language').val();
if(language!=null ){
language=language+"";
language=language.replace(new RegExp(",", 'g'),"','");}

  var ajax_url="<img src='/BMREG_WEB/resources/images/ajax-loader.gif' alt='Loading ....' />"; 
  var url="/BMREG_WEB/fetchJobseekerCount.action?etc="+new Date().getTime();
		
			$("#expCount") 
			.html(ajax_url)  
			.load(url, {cExpYears: expYears,cJobExp: jobExp,cJobPreference:jobPreference,cGender:gender,cCountryPrefernce:countryPrefernce,cLanguage:language},function(responseText){  
				if(responseText!="")
					$("#expCount").innerHTML= responseText;
			});
}
function getJobPreferenceDiv(jobPreferenceCounter)
{
  
    var jobPreferenceStr="<div id='jobP"+jobPreferenceCounter+"' style='clear:both;padding-top:10px;'>"+
                            "<div id='leftDiv"+jobPreferenceCounter+"1' style='float:left;width:170px;'>"+
                               "<select name='mainJob_"+jobPreferenceCounter+"' id='mainJob_"+jobPreferenceCounter+"' style='width:150px;border:1px solid grey;' onchange=\"fetchJobCategory(this.value,2,"+jobPreferenceCounter+",'leftDiv"+jobPreferenceCounter+"2','jobPreferenceJobCategory')\">";
     	jobPreferenceStr+="<option value=''>Select</option>";
	<s:iterator value="%{#application.ALL_JOB_MAIN_CATEGORY}" id="jobCatList" status="stat">
		jobPreferenceStr+="<option value='"+<s:property value='jobId' />+"'><s:property value='jobTitle' /></option>";		 
	</s:iterator>
                                 
             jobPreferenceStr+="</select>"+
                            "</div>"+
                            "<div id='leftDiv"+jobPreferenceCounter+"2' style='float:left;width:170px;'>&nbsp;</div>"+
                            "<div id='leftDiv"+jobPreferenceCounter+"3' style='float:left;width:170px;'>&nbsp;</div>"+
                            "<div id='leftDiv"+jobPreferenceCounter+"4' style='float:left;width:50px;'><a href=# onclick='deletePreferredJob("+jobPreferenceCounter+")'>Delete</a></div>"+
                          "</div>";
                          
                   
    return jobPreferenceStr;
    
}

function addJobPreferenceDiv()
{
 	$("#jobPreferenceDiv").append(getJobPreferenceDiv(jobPreferenceCounter));
 	jobPreferenceCounter++;
}
function deletePreferredJob(id)
{
 document.getElementById("jobP"+id).style.display="none";
}
function addMoreAbroadExpLoad()
{
    row=0;
	
	var elementJobCat = document.createElement("select");
	elementJobCat.id = "lotteryJobCat"+cntAX[row];
	elementJobCat.name = cntAX[row];
	var tmpIndex=cntAX[row];
	elementJobCat.onchange=function(){fetchJobCategory(this.value,2,tmpIndex,'lotteryJobSubCat'+tmpIndex,'lotteryJobCategory')};	
	elementJobCat.options[0] = new Option("Select","");
	<s:iterator value="%{#application.ALL_JOB_MAIN_CATEGORY}" id="jobCatList" status="stat">
		elementJobCat.options[ <s:property value="#stat.count" />] = new Option("<s:property value='jobTitle' />","<s:property value='jobId' />"); 
	</s:iterator>
	elementJobCat.style.width = '120px';
	elementJobCat.style.border="1px solid grey";
	elementJobCat.style.textAlign = 'left';
	elementJobCat.tabIndex=aboradExpTab+cntAX[row]+2;

    var elementJobSubCat = document.createElement("div");
	elementJobSubCat.id = "lotteryJobSubCat"+cntAX[row];
	elementJobSubCat.style.width = '50px';
	elementJobSubCat.style.height = '20px';
	//elementJobSubCat.style.border="1px solid grey";
	elementJobSubCat.name = cntAX[row];
	elementJobSubCat.tabIndex=aboradExpTab+cntAX[row]+3;
	
	var elementJobSubSubCat = document.createElement("div");
	elementJobSubSubCat.id = "lotteryJobSubSubCat"+cntAX[row];
	elementJobSubSubCat.style.width = '50px';
	elementJobSubSubCat.style.height = '20px'
	//elementJobSubSubCat.style.border="1px solid grey";
	elementJobSubSubCat.name = cntAX[row];
	elementJobSubSubCat.tabIndex=aboradExpTab+cntAX[row]+4;
	
	
 
	table1 = document.getElementById("jobExpCategoryTable");
	table1.style.border = '0px';
	newRow = table1.insertRow(table1.rows.length);
	newRow.style.border = '0px';
	newCell = newRow.insertCell(0);
	newCell.style.border = '0px';
	newCell.appendChild(elementJobCat);
	newRow.id = "jExpAbroad_column1"+cntAX[row];
	
	table2 = document.getElementById("jobExpSubCategoryTable");
	table2.style.border = '0px';
	newRow = table2.insertRow(table2.rows.length);
	newRow.style.border = '0px';
	newCell = newRow.insertCell(0);
	newCell.style.border = '0px';
	newCell.appendChild(elementJobSubCat);
	newRow.id = "jExpAbroad_column2"+cntAX[row];
	
	
	table3 = document.getElementById("jobExpSubSubCategoryTable");
	table3.style.border = '0px';
	newRow = table3.insertRow(table3.rows.length);
	newRow.style.border = '0px';
	newCell = newRow.insertCell(0);
	newCell.style.border = '0px';
	newCell.appendChild(elementJobSubSubCat);
	newRow.id = "jExpAbroad_column3"+cntAX[row];
	  
	
	 
	 
	 cntAX[row]++;
	// document.getElementById("languageNumber").value=cnt[row];
	 return cntAX[row];
 
}
addMoreAbroadExpLoad(); 
</script>
</body>

</html>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>

<head>
<meta content="text/html;charset=utf-8" http-equiv="Content-Type">
<meta content="utf-8" http-equiv="encoding">
<title>BMET Registration Form</title>
 <link rel="stylesheet" href="/BMREG_WEB/resources/css/style.css" />
  <script type="text/javascript" src="/BMREG_WEB/resources/js/lib/prototype-1.6.0.2.js"></script>	
  <script type="text/javascript" src="/BMREG_WEB/resources/js/lib/jquery-1.6.4.min.js"></script>
  <script type="text/javascript" src="/BMREG_WEB/resources/js/util/util.js"></script>
  <script type="text/javascript" src="/BMREG_WEB/resources/js/util/numeric.js"></script>
  <script type="text/javascript" src="/BMREG_WEB/resources/js/util/browserDetect.js"></script>
  <script type="text/javascript" src="/BMREG_WEB/resources/js/address.js"></script>
  <script type="text/javascript" src="/BMREG_WEB/resources/js/regValidation.js"></script>
  <script type="text/javascript" src="/BMREG_WEB/resources/js/util/util.js"></script>
  <script type="text/javascript" src="/BMREG_WEB/resources/js/util/xmlextras.js"></script>
  <script type="text/javascript" src="/BMREG_WEB/resources/js/lib/jquery.corner.js"></script>
  <link type="text/css" rel="Stylesheet" href="/BMREG_WEB/resources/js/lib/jquery.validity.1.2.0/jquery.validity.css" />
  <script type="text/javascript" src="/BMREG_WEB/resources/js/lib/jquery.validity.1.2.0/jQuery.validity.js"></script>
        
 
  <link href="/BMREG_WEB/resources/wizard/styles/smart_wizard.css" rel="stylesheet" type="text/css">
  <script type="text/javascript" src="/BMREG_WEB/resources/wizard/js/jquery.smartWizard-2.0.js"></script>
  
  <link rel="stylesheet" type="text/css" href="/BMREG_WEB/resources/jqueryUi/ui-lightness/jquery-ui.css" />
  <script type="text/javascript" src="/BMREG_WEB/resources/jqueryUi/jquery-ui.min.js"></script>
  
 <link href="/BMREG_WEB/resources/multiselect/jquery.multiselect.css" rel="stylesheet" type="text/css">
  <script type="text/javascript" src="/BMREG_WEB/resources/multiselect/jquery.multiselect.js"></script>
  
  <script type="text/javascript" src="/BMREG_WEB/resources/js/registration.js"></script>
  
  <script type="text/javascript">
  var totalLanguageString="";
    
    <s:iterator value="languageList" id="languageList" status="stat">
		totalLanguageString+="<s:property value='language' />"+"#";		 
	</s:iterator>
	if(totalLanguageString.length>0)
		totalLanguageString=totalLanguageString.substring(0,totalLanguageString.length-1);
		
    $(document).ready(function(){
    	// Smart Wizard     	
  		$('#wizard').smartWizard({transitionEffect:'slideleft',onLeaveStep:leaveAStepCallback,onFinish:onFinishCallback,enableFinishButton:false});
  		
      function leaveAStepCallback(obj){
        var step_num= obj.attr('rel');
        return validateSteps(step_num);
      }
      
      function onFinishCallback(){
       if(validateAllSteps()){
        //$('form').submit();
        previewRegistrationForm();
       }
      }
		});
	   
    function validateAllSteps(){
       var isStepValid = true;
       
       if(validateStep1() == false){
         isStepValid = false;
         $('#wizard').smartWizard('setError',{stepnum:1,iserror:true});         
       }else{
         $('#wizard').smartWizard('setError',{stepnum:1,iserror:false});
       }
       
       if(validateStep2() == false){
         isStepValid = false;
         $('#wizard').smartWizard('setError',{stepnum:3,iserror:true});         
       }else{
         $('#wizard').smartWizard('setError',{stepnum:3,iserror:false});
       }
       
       if(validateStep3() == false){
         isStepValid = false;
         $('#wizard').smartWizard('setError',{stepnum:3,iserror:true});         
       }else{
         $('#wizard').smartWizard('setError',{stepnum:3,iserror:false});
       }
       
       if(validateStep4() == false){
         isStepValid = false;
         $('#wizard').smartWizard('setError',{stepnum:4,iserror:true});         
       }else{
         $('#wizard').smartWizard('setError',{stepnum:4,iserror:false});
       }
       
       if(validateStep5() == false){
         isStepValid = false;
         $('#wizard').smartWizard('setError',{stepnum:5,iserror:true});         
       }else{
         $('#wizard').smartWizard('setError',{stepnum:5,iserror:false});
       }
       
       if(!isStepValid){
          $('#wizard').smartWizard('showMessage','Please correct the errors in the steps and continue');
       }
              
       return isStepValid;
    } 	
		
	function clearMsgBox(){
		$(".msgBox").css("display", "none");
	};	
	function validateSteps(step){
	//return true;
		  var isStepValid = true;
      	// validate step 1
      if(step == 1){
        clearMsgBox();
        if(validateStep1() == false ){
          isStepValid = false; 
          $('#wizard').smartWizard('showMessage','<font color="red">Please correct the errors in step'+step+ ' and click next.</font>');
          $('#wizard').smartWizard('setError',{stepnum:step,iserror:true});         
        }else{
          $('#wizard').smartWizard('setError',{stepnum:step,iserror:false});
        }
      }
      // validate step 2
      if(step == 2){   
        clearMsgBox();     
        if(validateStep2() == false ){
          isStepValid = false; 
          $('#wizard').smartWizard('showMessage','<font color="red">Please correct the errors in step'+step+ ' and click next.</font>');
          $('#wizard').smartWizard('setError',{stepnum:step,iserror:true});         
        }else{
          $('#wizard').smartWizard('setError',{stepnum:step,iserror:false});
        }
      }
      
      // validate step3
      if(step == 3){
        clearMsgBox();
        if(validateStep3() == false ){
          isStepValid = false; 
          $('#wizard').smartWizard('showMessage','<font color="red">Please correct the errors in step'+step+ ' and click next.</font>');
          $('#wizard').smartWizard('setError',{stepnum:step,iserror:true});         
        }else{
          $('#wizard').smartWizard('setError',{stepnum:step,iserror:false});
        }
      }
      
      // validate step4
      if(step == 4){
        clearMsgBox();
        if(validateStep4() == false ){
          isStepValid = false; 
          $('#wizard').smartWizard('showMessage','<font color="red">Please correct the errors in step'+step+ ' and click next.</font>');
          $('#wizard').smartWizard('setError',{stepnum:step,iserror:true});         
        }else{
          $('#wizard').smartWizard('setError',{stepnum:step,iserror:false});
        }
      }
      
      // validate step5
      if(step == 5){
        clearMsgBox();
        if(validateStep5() == false ){
          isStepValid = false; 
          $('#wizard').smartWizard('showMessage','<font color="red">Please correct the errors in step'+step+ ' and click next.</font>');
          $('#wizard').smartWizard('setError',{stepnum:step,iserror:true});         
        }else{
          $('#wizard').smartWizard('setError',{stepnum:step,iserror:false});
        }
      }
      
      return isStepValid;
    }
		
	var alertImg="<div style='float:left'><img src='/BMREG_WEB/resources/images/redSmall.png'  /></div>";
	var preFix="<div style='float:left;margin-top:2px;margin-left:5px;'>";
	alertImg=alertImg+preFix;
	var postFix="<div>";
	
	function validateStep1(){
       var isValid = true; 
	   
	   <s:if test="%{#session.loggedInUser.userType=='DEMO_REG_OPERATOR'}">	   	
	   	var regToken = $('#regToken').val();
	   </s:if>
	   <s:if test="%{#session.loggedInUser.userType=='UISC_REG_OPERATOR'}">	   	
	   	var paymentPin = $('#paymentPin').val();
	   	var paymentOperator = $('#paymentOperator').val();
	   </s:if>
	   var givenName = $('#givenName').val();
	   var lastName = $('#lastName').val();	   
	   var fatherName = $('#fatherName').val();
	   var montherName = $('#montherName').val();
	   var birthDate = $('#birthDate').val();
	   var sexMale = $('#sexMale').checked;
	   var sexFemale = $('#sexFemale').checked;
	   var religion = $('#religion').val();
	   var maritalStatus = $('#maritalStatus').val();
	   var spouseName = $('#spouseName').val();
	   var mobileNumber = $('#mobileNumber').val();
	   
	   var heightFeet = $('#heightFeet').val();
	   var heightInches = $('#heightInches').val();
	   var weightKg = $('#weightKg').val();
	   
	   var disablilityYes = document.getElementById("disablilityYes").checked;
	   var disabilityDetail = $('#disabilityDetail').val();	
	   
	   
	   var totalSon = $('#totalSon').val();
	   var totalDaughter = $('#totalDaughter').val();
	   var childYes=document.getElementById("childYes").checked;

	   <s:if test="%{#session.loggedInUser.userType=='DEMO_REG_OPERATOR'}">	   	
	       if(regToken==""){
	         isValid = false;
	         $('#msg_regToken').html(alertImg+'Please Provide Reg Token'+postFix).show();
	       }else{
	         $('#msg_regToken').html('').hide();
	       }
	   </s:if>
	   <s:if test="%{#session.loggedInUser.userType=='UISC_REG_OPERATOR'}">	   	
	       if(paymentPin==""){
	         isValid = false;
	         $('#sMsg_paymentPin').html(alertImg+'Please Provide Payment Pin'+postFix).show();
	       }else{
	         $('#sMsg_paymentPin').html('').hide();
	       }
	       if(paymentOperator=="0"){
	         isValid = false;
	         $('#sMsg_paymentPin').html(alertImg+'Please Select Payment Operator'+postFix).show();
	       }else{
	         $('#sMsg_paymentPin').html('').hide();
	       }
	   </s:if>
	   	   
       if(givenName=="" && lastName==""){
         isValid = false;
         $('#msg_seekerName').html(alertImg+'Please fill Given Name or Last Name'+postFix).show();
       }else{
         $('#msg_seekerName').html('').hide();
       }
       if(fatherName==""){
         isValid = false;
         $('#msg_fatherName').html(alertImg+"Please fill Father's Name"+postFix).show();
       }else{
         $('#msg_fatherName').html('').hide();
       }
       if(montherName==""){
         isValid = false;
         $('#msg_montherName').html(alertImg+"Please fill Mother's Name"+postFix).show();
       }else{
         $('#msg_montherName').html('').hide();
       }
       if(birthDate==""){
         isValid = false;
         $('#msg_birthDate').html(alertImg+"Please fill Birth Date"+postFix).show();
       }else{
         	var dobArr=birthDate.split("-");
			var bDate = new Date(dobArr[2], dobArr[1] - 1, dobArr[0]);
			var age=ageCount(bDate);			
			if(age< <s:property value="%{#application.MIN_AGE}" /> || age> <s:property value="%{#application.MAX_AGE}" /> )
			{
			 	$('#msg_birthDate').html(alertImg+"Allowed Age Limit is <s:property value='%{#application.MIN_AGE}' /> - <s:property value='%{#application.MAX_AGE}' />"+postFix).show();
			}
			else
		    	$('#msg_birthDate').html('').hide();
        }       
        if(isValidDate(birthDate)==false){
         isValid = false;
         $('#msg_birthDate').html(alertImg+"Incorrect Birth Date"+postFix).show();
       }else{
         $('#msg_birthDate').html('').hide();
       }
       
       if(sexMale==false && sexFemale==false){
         isValid = false;
         $('#msg_sex').html(alertImg+"Please select Gender"+postFix).show();
       }else{
         $('#msg_sex').html('').hide();
       }
       if(maritalStatus==""){
         isValid = false;
         $('#msg_maritalStatus').html(alertImg+"Please select Marital Status"+postFix).show();
       }else{
         $('#msg_maritalStatus').html('').hide();
       }
       
       if(maritalStatus!="Single" && childYes==true)
	   {
	   
	     if(totalSon=="" || (totalSon==0 && totalDaughter==0) || IsNumber(totalSon)==false)
	     {
	      isValid = false;
          $('#msg_maritalStatus').html(alertImg+'Provide Correct value for Total Son and Daughter'+postFix).show();
	     }
	     if(totalDaughter=="" || (totalSon==0 && totalDaughter==0) || IsNumber(totalDaughter)==false)
	     {
	      isValid = false;
          $('#msg_maritalStatus').html(alertImg+'Provide Correct value for Total Son and Daughter'+postFix).show();
	     }
	   }
	   
              
       if(religion==""){
         isValid = false;
         $('#msg_religion').html(alertImg+"Please select Religion"+postFix).show();
       }else{
         $('#msg_religion').html('').hide();
       }
       if(validateMobileNumber(mobileNumber)==false){
         isValid = false;
         $('#msg_mobile').html(alertImg+"Invalid Mobile Number"+postFix).show();
       }else{
         $('#msg_mobile').html('').hide();
       }
       
       
      if(heightFeet=="" || heightFeet<4){
         isValid = false;
         $('#msg_height').html(alertImg+"Height Cannot be smaller than 4 Feet"+postFix).show();
       }else if(IsNumber(heightFeet)==false){
         isValid = false;
         $('#msg_height').html(alertImg+"Height Feet Should be a numeric value"+postFix).show();
       }else if((IsNumber(heightInches)==false && heightInches!="") || (heightInches!="" && heightInches>11)){
         isValid = false;
         $('#msg_height').html(alertImg+"Height Inches Should be a numeric value and smaller than 12."+postFix).show();
       }else{
         $('#msg_height').html('').hide();
       }
       
       if(weightKg=="" || weightKg<30 || weightKg>150){
         isValid = false;
         $('#msg_weight').html(alertImg+"Weight shold be between 30 to 150 Kg"+postFix).show();
       }else if(IsNumber(weightKg)==false){
         isValid = false;
         $('#msg_weight').html(alertImg+"Weight Should be a numeric value"+postFix).show();
       }else{
         $('#msg_weight').html('').hide();
       }
       
       if(disablilityYes==true && disabilityDetail==""){
         isValid = false;
         $('#msg_disabilityDetail').html(alertImg+"Provide disability detail."+postFix).show();
       }else{
         $('#msg_disabilityDetail').html('').hide();
       }
       //isValid=true;
            
       return isValid;
    }
    
     function validateStep2(){
      var isValid = true;    
      var nationalId = $('#nationalId').val();
      var birthRegId = $('#birthRegId').val();
	  var nomineeName = $('#nomineeName').val();
	  var nomineeBirthDate = $('#nomineeBirthDate').val();
	  var nomineePhone = $('#nomineePhone').val();
	  
	  var nomineeFatherName = $('#nomineeFatherName').val();
	  var nomineeMotherName = $('#nomineeMotherName').val();
	  
	  var nomineeName = $('#nomineeName').val();
	  var nomineeRelationship = $('#nomineeRelationship').val();
	  
	  var nomineeDivision = $('#NOMINEE_DIV').val();
	  var nomineeDistrict = $('#NOMINEE_DIST').val();
	  var nomineeUpazillaOrThana = $('#NOMINEE_UPAZILLA_OR_THANA').val();
	  var nomineeUnionOrWard = $('#NOMINEE_UNION_OR_WARD').val();
	  var nomineeMauzaOrMoholla = $('#NOMINEE_MAUZA_OR_MOHOLLA').val();
	  var nomineeVillage = $('#NOMINEE_VILLAGE').val();
	  var nomineePostOffice = $('#NOMINEE_POST_OFFICE').val();
	  var nomineePostCode = $('#NOMINEE_POST_CODE').val();
	  
	  
	  var nomineeContactName1 = $('#nomineeContactName1').val();
	  var nomineeContactMobile1 = $('#nomineeContactMobile1').val();
	  var nomineeContactRelation1 = $('#nomineeContactRelation1').val();

	  var nomineeContactName2 = $('#nomineeContactName2').val();
	  var nomineeContactMobile2 = $('#nomineeContactMobile2').val();
	  var nomineeContactRelation2 = $('#nomineeContactRelation2').val();
	  
	  var nomineeContactName3 = $('#nomineeContactName3').val();
	  var nomineeContactMobile3 = $('#nomineeContactMobile3').val();
	  var nomineeContactRelation3 = $('#nomineeContactRelation3').val();
	  
	  
	  var passportNumber = $('#passportNumber').val();
	  var prevPassportNo = $('#prevPassportNo').val();
	  var passportIssueDate = $('#passportIssueDate').val();
	  var passportExpireDate = $('#passportExpireDate').val();
	  
	  
	  if(nationalId=="" && birthRegId=="" && passportNumber==""){
         isValid = false;
         $('#msg_nationalId_birthReg').html(alertImg+"Provide National Id , BirthReg Id or Passport Number."+postFix).show();
       }else{
         $('#msg_nationalId_birthReg').html('').hide();
       }
      if(nationalId!="")
      { 
       if(nationalId.length<13 || nationalId.length>18)
         {
         	isValid = false;
         	$('#msg_nationalId_birthReg').html(alertImg+"National Id must be 13-18 digit long."+postFix).show();
         }
       } 
       
      if(passportNumber!=""){
      
         if(passportNumber.length!=8 && passportNumber.length!=9)
         {
         	isValid = false;
         	$('#msg_passportNumber').html(alertImg+"Wrong Passport Number format."+postFix).show();
         }
         else
         {
            if(passportNumber.length==8)
          		{
          		  //var part1=str.substr(0,1);
          		  //var part2=str.substr(1,7);
          		}
         }
        
         
         if(passportIssueDate=="")
         {
         	isValid = false;
         	$('#msg_passportIssueDate').html(alertImg+"Please provide issue date"+postFix).show();
         }
         else if(isValidDate(passportIssueDate)==false)
         {
         	isValid = false;
         	$('#msg_passportIssueDate').html(alertImg+"Incorrect issue date"+postFix).show();
         }
         else{
         $('#msg_passportIssueDate').html('').hide();
         }
         
         if(passportExpireDate=="")
         {
         	isValid = false;
         	$('#msg_passportExpDate').html(alertImg+"Please provide expire date"+postFix).show();
         }
         else if(isValidDate(passportExpireDate)==false)
         {
         	isValid = false;
         	$('#msg_passportExpDate').html(alertImg+"Incorrect expire date"+postFix).show();
         }
         else{
             $('#msg_passportExpDate').html('').hide();
         }
       }else{
         $('#msg_passportNumber').html('').hide();
       }
        
       
      if(nomineeName==""){
         isValid = false;
         $('#msg_nomineeName').html(alertImg+"Please fill Nominee Name"+postFix).show();
       }else{
         $('#msg_nomineeName').html('').hide();
       }
       /*
       if(nomineeBirthDate==""){
         isValid = false;
         $('#msg_nomineeBirthDate').html(alertImg+"Please fill Nominee Birth Date"+postFix).show();
       }else{
         $('#msg_nomineeBirthDate').html('').hide();
       }
       */
       if(nomineePhone==""){
         isValid = false;
         $('#msg_nomineePhone').html(alertImg+"Please fill Nominee Mobile"+postFix).show();
       }else{
         $('#msg_nomineePhone').html('').hide();
       }
	   if(nomineeRelationship==""){
         isValid = false;
         $('#msg_nomineeName_relationship').html(alertImg+"Please select relationship"+postFix).show();
       }else{
         /*---*/
         	if(nomineeName==""){
        	 isValid = false;
        	 $('#msg_nomineeName_relationship').html(alertImg+"Please fill Nominee Name"+postFix).show();
	       	}else{
	         $('#msg_nomineeName_relationship').html('').hide();
	        }
         /*---*/
       }
        if(nomineeMotherName==""){
         isValid = false;
         $('#msg_nomineeMother').html(alertImg+"Please fill Nominee's Mother Mobile"+postFix).show();
       }else{
         $('#msg_nomineeMother').html('').hide();
       }
       if(nomineeFatherName==""){
         isValid = false;
         $('#msg_nomineeFather').html(alertImg+"Please fill Nominee's Father Name"+postFix).show();
       }else{
         $('#msg_nomineeFather').html('').hide();
       }
       
       if(nomineeFatherName==""){
         isValid = false;
         $('#msg_nomineeFather').html(alertImg+"Please fill Nominee's Father Name"+postFix).show();
       }else{
         $('#msg_nomineeFather').html('').hide();
       }
       
       if(nomineeDivision==""){
         isValid = false;
         $('#msg_nomineeDivision').html(alertImg+"Select Division"+postFix).show();
       }else{
         $('#msg_nomineeDivision').html('').hide();
       }
       
       if(nomineeDistrict==""){
         isValid = false;
         $('#msg_nomineeDistrict').html(alertImg+"Select District"+postFix).show();
       }else{
         $('#msg_nomineeDistrict').html('').hide();
       }
       
        if(nomineeUpazillaOrThana==""){
         isValid = false;
         $('#msg_nomineeUpazillaOrThana').html(alertImg+"Select Upazilla Or Thana"+postFix).show();
       }else{
         $('#msg_nomineeUpazillaOrThana').html('').hide();
       }
       
       if(nomineeUnionOrWard==""){
         isValid = false;
         $('#msg_nomineeUnionOrWard').html(alertImg+"Select Union Or Ward"+postFix).show();
       }else{
         $('#msg_nomineeUnionOrWard').html('').hide();
       }
       
       if(nomineeMauzaOrMoholla==""){
         isValid = false;
         $('#msg_nomineeMauzaOrMoholla').html(alertImg+"Select Mauza Or Moholla"+postFix).show();
       }else{
         $('#msg_nomineeMauzaOrMoholla').html('').hide();
       }
       
       if(nomineeVillage==""){
         isValid = false;
         $('#msg_nomineeVillage').html(alertImg+"Select Village"+postFix).show();
       }else{
         $('#msg_nomineeVillage').html('').hide();
       }
       
       /*
        if(nomineePostOffice==""){
         isValid = false;
         $('#msg_nomineePostOffice').html(alertImg+"Please provide Post Office"+postFix).show();
       }else{
         $('#msg_nomineePostOffice').html('').hide();
       }
       
       if(nomineePostCode==""){
         isValid = false;
         $('#msg_nomineePostCode').html(alertImg+"Please provide Post Code"+postFix).show();
       }else{
         $('#msg_nomineePostCode').html('').hide();
       }
       */
       
       if(nomineeContactName1==""){
         isValid = false;
         $('#msg_nomineeContactPerson1').html(alertImg+"Name Required."+postFix).show();
       }else if(nomineeContactMobile1==""){
         isValid = false;
         $('#msg_nomineeContactPerson1').html(alertImg+"Mobile Required."+postFix).show();
       }else if(validateMobileNumber(nomineeContactMobile1)==false){
         isValid = false;
         $('#msg_nomineeContactPerson1').html(alertImg+"Mobile Number Incorrect."+postFix).show();
       }else if(nomineeContactRelation1==""){
         isValid = false;
         $('#msg_nomineeContactPerson1').html(alertImg+"Relation Required."+postFix).show();
       }else if(nomineeContactMobile1==nomineeContactMobile2 || nomineeContactMobile1==nomineeContactMobile3){
         isValid = false;
         $('#msg_nomineeContactPerson1').html(alertImg+"Duplicate Mobile Number Found."+postFix).show();
       }else{
         $('#msg_nomineeContactPerson1').html('').hide();
       }             

	   if(nomineeContactName2==""){
         isValid = false;
         $('#msg_nomineeContactPerson2').html(alertImg+"Name Required."+postFix).show();
       }else if(nomineeContactMobile2==""){
         isValid = false;
         $('#msg_nomineeContactPerson2').html(alertImg+"Mobile Required."+postFix).show();
       }else if(validateMobileNumber(nomineeContactMobile2)==false){
         isValid = false;
         $('#msg_nomineeContactPerson2').html(alertImg+"Mobile Number Incorrect."+postFix).show();
       }else if(nomineeContactRelation2==""){
         isValid = false;
         $('#msg_nomineeContactPerson2').html(alertImg+"Relation Required."+postFix).show();
       }else if(nomineeContactMobile2==nomineeContactMobile3 || nomineeContactMobile2==nomineeContactMobile1){
         isValid = false;
         $('#msg_nomineeContactPerson2').html(alertImg+"Duplicate Mobile Number Found."+postFix).show();
       }else{
         $('#msg_nomineeContactPerson2').html('').hide();
       }  
       
       if(nomineeContactName3==""){
         isValid = false;
         $('#msg_nomineeContactPerson3').html(alertImg+"Name Required."+postFix).show();
       }else if(nomineeContactMobile3==""){
         isValid = false;
         $('#msg_nomineeContactPerson3').html(alertImg+"Mobile Required."+postFix).show();
       }else if(validateMobileNumber(nomineeContactMobile3)==false){
         isValid = false;
         $('#msg_nomineeContactPerson3').html(alertImg+"Mobile Number Incorrect."+postFix).show();
       }else if(nomineeContactRelation3==""){
         isValid = false;
         $('#msg_nomineeContactPerson3').html(alertImg+"Relation Required."+postFix).show();
       }else if(nomineeContactMobile3==nomineeContactMobile1 || nomineeContactMobile3==nomineeContactMobile2){
         isValid = false;
         $('#msg_nomineeContactPerson3').html(alertImg+"Duplicate Mobile Number Found."+postFix).show();
       }else{
         $('#msg_nomineeContactPerson3').html('').hide();
       }   
       
      //isValid=true; 
      return isValid;
    }
    function validateStep3(){
      var isValid = true;   
      var selectedCountry=$("#countryPreference").val();
      
      var countryArr;
      if(selectedCountry!=null && selectedCountry!="")
       countryArr=String(selectedCountry).split(",");
       
      if(checkLocalExperience()==false){
         isValid = false;
         $('#msg_expLocal').html(alertImg+"One or more Experience Information is Blank."+postFix).show();
       }else{
         $('#msg_expLocal').html('').hide();
       }
       
      if(checkAbraodExperience()==false){
         isValid = false;
         $('#msg_expAborad').html(alertImg+"One or more Experience Information is Blank."+postFix).show();
       }else{
         $('#msg_expAborad').html('').hide();
       }
       
       if(selectedCountry==null || selectedCountry=="" || countryArr.length>7){
         isValid = false;
         $('#msg_countryPreference').html(alertImg+"Select one or at most seven country."+postFix).show();
       }else{
         $('#msg_countryPreference').html('').hide();
       }
       
       if(checkJobPreference()==false){
         isValid = false;
         $('#msg_expJobPreference').html(alertImg+"One or more Preferred Job is Blank."+postFix).show();
       }else{
         $('#msg_expJobPreference').html('').hide();
       }
       if(checkJobPreference()==false){
         isValid = false;
         $('#msg_expJobPreference').html(alertImg+"One or more Preferred Job is Blank."+postFix).show();
       }else{
        	if(document.getElementById("jobPreferenceHidden").value==""){
	         isValid = false;
	         $('#msg_expJobPreference').html(alertImg+"Select at least one desire job."+postFix).show();
	       }else{
	         $('#msg_expJobPreference').html('').hide();
	       }
       }
       
       
       //isValid=true;
       
              
      return isValid;
    }
    
    
    function validateStep4(){
      var isValid = true;   
      var heighestDegree=$("#heighestDegree").val();
      var lastInstitute=$("#lastInstitute").val();
      var passingYear=$("#passingYear").val();      
      
      if(heighestDegree==""){
         isValid = false;
         $('#msg_heighestDegree').html(alertImg+"Select Degree."+postFix).show();
       }else{
         $('#msg_heighestDegree').html('').hide();
       }
       
       if(heighestDegree!="7"  && lastInstitute==""){
         isValid = false;
         $('#msg_lastInstitute').html(alertImg+"Provide Institute Name."+postFix).show();
       }else{
         $('#msg_lastInstitute').html('').hide();
       }
       
       if(heighestDegree!="7"  && passingYear==""){
         isValid = false;
         $('#msg_passingYear').html(alertImg+"Select Passing Year."+postFix).show();
       }else{
         $('#msg_passingYear').html('').hide();
       }
       
        if(checkLanguageInformation()==false){
         isValid = false;
         $('#msg_language').html(alertImg+"One or More Language Information is Blank."+postFix).show();
       }else{
	         if(document.getElementById("languageHidden").value==""){
	       isValid = false;
	         $('#msg_language').html(alertImg+"Please select at least one language."+postFix).show();
	       }else{
	         $('#msg_language').html('').hide();
	       }
       }
       
       
       
       
       
       
        if(checkTrainingInformation()==false){
         isValid = false;
         $('#msg_training').html(alertImg+"One or More Experience is Blank."+postFix).show();
       }else{
         $('#msg_training').html('').hide();
       }
       
              
      return isValid;
    }
    
    function validateStep5(){
      var isValid = true;   
      var pDivision=$("#PERMANENT_DIV").val();
      var mDivision=$("#MAILING_DIV").val();      
      var pDistrict=$("#PERMANENT_DIST").val();
      var mDistrict=$("#MAILING_DIST").val();
      var pUpazillaOrThana=$("#PERMANENT_UPAZILLA_OR_THANA").val();
      var mUpazillaOrThana=$("#MAILING_UPAZILLA_OR_THANA").val();
      var pUnionOrWard=$("#PERMANENT_UNION_OR_WARD").val();
      var mUnionOrWard=$("#MAILING_UNION_OR_WARD").val();
      var pMauzaOrMoholla=$("#PERMANENT_MAUZA_OR_MOHOLLA").val();
      var mMauzaOrMoholla=$("#MAILING_MAUZA_OR_MOHOLLA").val();
      var pVillage=$("#PERMANENT_VILLAGE").val();
      var mVillage=$("#MAILING_VILLAGE").val();
      var pPostOffice=$("#PERMANENT_POST_OFFICE").val();
      var mPostOffice=$("#MAILING_POST_OFFICE").val();
      var pPostCode=$("#PERMANENT_POST_CODE").val();
      var mPostCode=$("#MAILING_POST_CODE").val();
      
            
      if(pDivision==""){
         isValid = false;
         $('#msg_pDivision').html(alertImg+""+postFix).show();
       }else{
         $('#msg_pDivision').html('').hide();
       }
      if(mDivision==""){
         isValid = false;
         $('#msg_mDivision').html(alertImg+""+postFix).show();
       }else{
         $('#msg_mDivision').html('').hide();
       }
      
       if(pDistrict==""){
         isValid = false;
         $('#msg_pDistrict').html(alertImg+""+postFix).show();
       }else{
         $('#msg_pDistrict').html('').hide();
       }
      if(mDistrict==""){
         isValid = false;
         $('#msg_mDistrict').html(alertImg+""+postFix).show();
       }else{
         $('#msg_mDistrict').html('').hide();
       }
       
       if(pUpazillaOrThana==""){
         isValid = false;
         $('#msg_pUpazillaOrThana').html(alertImg+""+postFix).show();
       }else{
         $('#msg_pUpazillaOrThana').html('').hide();
       }
      if(mUpazillaOrThana==""){
         isValid = false;
         $('#msg_mUpazillaOrThana').html(alertImg+""+postFix).show();
       }else{
         $('#msg_mUpazillaOrThana').html('').hide();
       }
       
       if(pUnionOrWard==""){
         isValid = false;
         $('#msg_pUnionOrWard').html(alertImg+""+postFix).show();
       }else{
         $('#msg_pUnionOrWard').html('').hide();
       }
      if(mUnionOrWard==""){
         isValid = false;
         $('#msg_mUnionOrWard').html(alertImg+""+postFix).show();
       }else{
         $('#msg_mUnionOrWard').html('').hide();
       }
       
       if(pMauzaOrMoholla==""){
         isValid = false;
         $('#msg_pMauzaOrMoholla').html(alertImg+""+postFix).show();
       }else{
         $('#msg_pMauzaOrMoholla').html('').hide();
       }
      if(mMauzaOrMoholla==""){
         isValid = false;
         $('#msg_mMauzaOrMoholla').html(alertImg+""+postFix).show();
       }else{
         $('#msg_mMauzaOrMoholla').html('').hide();
       }
      
      if(pVillage==""){
         isValid = false;
         $('#msg_pVillage').html(alertImg+""+postFix).show();
       }else{
         $('#msg_pVillage').html('').hide();
       }
      if(mVillage==""){
         isValid = false;
         $('#msg_mVillage').html(alertImg+""+postFix).show();
       }else{
         $('#msg_mVillage').html('').hide();
       }
      /*
      if(pPostOffice==""){
         isValid = false;
         $('#msg_pPostOffice').html(alertImg+""+postFix).show();
       }else{
         $('#msg_pPostOffice').html('').hide();
       }
      if(mPostOffice==""){
         isValid = false;
         $('#msg_mPostOffice').html(alertImg+""+postFix).show();
       }else{
         $('#msg_mPostOffice').html('').hide();
       }
      
      if(pPostCode==""){
         isValid = false;
         $('#msg_pPostCode').html(alertImg+""+postFix).show();
       }else{
         $('#msg_pPostCode').html('').hide();
       }
      if(mPostCode==""){
         isValid = false;
         $('#msg_mPostCode').html(alertImg+""+postFix).show();
       }else{
         $('#msg_mPostCode').html('').hide();
       }
       */
             
      return isValid;
    }
    
    // Email Validation
    function isValidEmailAddress(emailAddress) {
      var pattern = new RegExp(/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);
      return pattern.test(emailAddress);
    } 
		
	function calculateHeightinCM(){
	  var heightFeet=document.getElementById("heightFeet").value;
      var heightInches=document.getElementById("heightInches").value;
      
      if(heightFeet=="") heightFeet=0;
      if(heightInches=="") heightInches=0;
      
      var totalInches=parseFloat(heightFeet*12,10)+parseFloat(heightInches,10);
      var hightCm=parseFloat(totalInches*2.54,10);      
	  
	  document.getElementById("hightCm").value= hightCm;   
	  
	}
function disabler(event) {
    event.preventDefault();
    return false;
}
function validateRegToken(regToken)
{
   var ajax_load="<br/><center><img src='/BMREG_WEB/resources/images/ajax-loader.gif' border='0' /></center>";
   
    var loadUrl="validateRegToken.action?regToken="+regToken;
			jQuery("#msg_regToken")  
				.html(ajax_load)  
				.load(loadUrl, {},function(responseText){  
					if(responseText=="error")
					{
					 alert("Invalid Registration Token");
					 document.getElementById("regToken").value="";
				     $(".buttonNext").addClass("buttonDisabled"); 
					}
					else{
					if($(".buttonNext").hasClass('buttonDisabled')){
                       $(".buttonNext").removeClass("buttonDisabled");
                      }
					
					}
					jQuery("#msg_regToken").html("");
					
									   
				});
   					
}
function validatePaymentPin(paymentPin)
{
   var ajax_load="<br/><center><img src='/BMREG_WEB/resources/images/ajax-loader.gif' border='0' /></center>";
   var paymentOperator=$("#paymentOperator").val();
   var mobileNumber=$("#mobileNumber").val();
   if(mobileNumber=="")
   {
    alert("Please provide mobile number at first");
    return;
   }
   var loadUrl="validatePaymentPin.action?paymentPin="+paymentPin+"&paymentOperator="+paymentOperator+"&mobileNumber="+mobileNumber;
			jQuery("#sMsg_paymentPin")  
				.html(ajax_load)  
				.load(loadUrl, {},function(responseText){  
					if(responseText=="error")
					{
					 alert("Invalid Payment PIN");
					 document.getElementById("paymentPin").value="";
				     $(".buttonNext").addClass("buttonDisabled"); 
					}
					else{
					if($(".buttonNext").hasClass('buttonDisabled')){
                       $(".buttonNext").removeClass("buttonDisabled");
                      }
					
					}
					jQuery("#sMsg_paymentPin").html("");
					
									   
				});
   					
}

</script>  
</head>
<!-- 
http://validity.thatscaptaintoyou.com/Demos/index.htm
http://rishida.net/tools/conversion/
 -->
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
 </style>
<body style="margin: 0px;">
<div style="width: 100%;height: 100px;border-bottom: 1px solid #006219;">
<center>
<div style="width: 1000px;height: 100px;border-right: 1px solid #006219;border-left: 1px solid #006219;">
	<div style="float: left; margin-top: 20px;width: 100px;">
	 <img src="/BMREG_WEB/resources/images/bagladesh_logo.gif" width="60" height="60" />
	</div>
	<div style="float: left;margin-left: 30px;color: black;margin-top: 15px;text-align: left;">
	 	<div style="font-size: 27px;font-weight: bold;">Bureau of Manpower, Employment & Training (BMET)</div>
	 	<div style="font-size: 20px;margin-top: 10px;">Job Seeker's Registration Form</div>
	</div>
</div>
</center>
</div>

<table align="center" border="0" cellpadding="0" cellspacing="0">
<tr><td>
 
<form id="empRegForm" name="empRegForm" method="post" action="previewRegFormAction.action">
  <input type='hidden' name="issubmit" value="1">
<!-- Tabs -->
  		<div id="wizard" class="swMain">
  			<ul>
  				<li><a href="#step-1">
                <span class="stepNumber">1</span>
                <span class="stepDesc">
                   Personal Information
                </span>
            </a></li>
  				<li><a href="#step-2">
                <span class="stepNumber">2</span>
                <span class="stepDesc">
                     Identification & Nominee details
                </span>
            </a></li>
  				<li><a href="#step-3">
                <span class="stepNumber">3</span>
                <span class="stepDesc">
                   Experience & Preference
                </span>
             </a></li>
  				<li>
  				<a href="#step-4">
                <span class="stepNumber">4</span>
                <span class="stepDesc">
                   Education, Language & Training 
                </span>
            	</a>
            	</li>
            	<li>
  				<a href="#step-5">
                <span class="stepNumber">5</span>
                <span class="stepDesc">
                   Contact Information.
                </span>
            	</a>
            	</li>
            	<li>
  				
            	</li>
  			</ul>
  			<div id="step-1">	
            <h2 class="StepTitle">Step 1: Personal Information</h2> 
            <table cellspacing="3" cellpadding="3" align="left" width="100%">
          			<tr>
                    	<td align="center" colspan="3">&nbsp;
                    	 
                    	
                    	</td>
          			</tr>        
          			
          			<s:if test="%{#session.loggedInUser.userType=='DEMO_REG_OPERATOR'}">          			
          			<tr>
                    	<td align="left">Reg Token <font color="red">*</font></td>
                    	<td align="left">
                    	  <input type="text" id="regToken" name="personalDTO.regToken" value="<s:property value='personalDTO.regToken' />" class="txtBox" tabindex="1" onblur="validateRegToken(this.value)">
                    	  
                      </td>
                    	<td align="left">
                    	<span id="msg_regToken"></span>&nbsp;
                    		<font style="color:red"><s:label name="sMsg_regToken"></s:label></font>
                    	</td>
          			</tr>
          			</s:if> 
          			          			
          			<tr>
                    	<td align="left" width="20%" valign="top">Job-Seeker's Name <font color="red">*</font></td>
                    	<td align="left" width="50%" valign="top">
                    	
                    	  <input type="text" id="givenName" name="personalDTO.empGivenName" value="<s:property value='personalDTO.empGivenName' />" class="txtBox" style="width:208px;" tabindex="1">
                    	  <input type="text" id="lastName" name="personalDTO.empLastName" value="<s:property value='personalDTO.empLastName' />" class="txtBox" style="width:210px;" tabindex="2">
                    	  <br/>
                    	  <div style="float: left;width: 212px;font-size: 10px;">Given Name</div>
                    	  <div style="float: left;margin-left: 5px;font-size: 10px;">Last Name(Sur Name)</div>
                    	  
                      </td>
                    	<td align="left" width="30%" valign="middle">                    		                    		
                    		<span id="msg_seekerName"></span>&nbsp;
                    		<font style="color:red"><s:label name="sMsg_seekerName"></s:label></font>
                    	</td>
          			</tr>
          			<tr>
                    	<td align="left">Father's Name <font color="red">*</font></td>
                    	<td align="left">
                    	  <input type="text" id="fatherName" name="personalDTO.empFatherName" value="<s:property value='personalDTO.empFatherName' />" class="txtBox" tabindex="3">
                      </td>
                    	<td align="left"><span id="msg_fatherName"></span>&nbsp;
                    	<font style="color:red"><s:label name="sMsg_fatherName"></s:label></font>
                    	</td>
          			</tr> 
                	<tr>
                    	<td align="left">Mother's Name <font color="red">*</font></td>
                    	<td align="left">
                    	  <input type="text" id="montherName" name="personalDTO.empMotherName" value="<s:property value='personalDTO.empMotherName' />" class="txtBox" tabindex="4">
                      </td>
                    	<td align="left"><span id="msg_montherName"></span>&nbsp;
                    	<font style="color:red"><s:label name="sMsg_montherName"></s:label></font>
                    	</td>
          			</tr> 
          			<tr>
                    	<td align="left">Date of Birth <font color="red">*</font></td>
                    	<td align="left">
                    	   <input type="text" id="birthDate" name="personalDTO.empBirthDate" value="<s:property value='personalDTO.empBirthDate' />" class="txtBox" style="width: 150px;" onchange="calculateAge('age','birthDate')" tabindex="5">
                    	   <font style='color:maroon;font-size: 13px;'>[DD-MM-YYYY]</font>
                    	   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    	   Age
                    	   <input type="text" id="age" name="personalDTO.empAge" value="<s:property value='personalDTO.empAge' />" class="txtBox" style="width: 50px;text-align: center;" readonly="readonly">
                    	   &nbsp;Years
                      </td>
                    	<td align="left"><span id="msg_birthDate"></span>&nbsp;
                    	<font style="color:red"><s:label name="sMsg_birthDate"></s:label></font>
                    	</td>
          			</tr>  
          			<tr>
                    	<td align="left">Place of Birth</td>
                    	<td align="left">
                       <div style="float:left">	
                       <select tabindex="6" name="personalDTO.empBirthDistrict" id="BIRTH_DIST" class="addressSelectBox" onchange="fetchJSONData_UpazillaOrThana(this.value,'BIRTH_UPAZILLA_OR_THANA')" style="width:200px;" >
						        <option value="" selected="selected">--Select District--</option>
						     	<s:iterator value="%{#application.ALL_DISTRICT}" id="districtList">
						     	  <option value="<s:property value="districtId" />"><s:property value="districtName" /></option>
						     	</s:iterator>
						</select>
						</div>
						<div style="float:left;margin-left: 35px;" id="BIRTH_UPAZILLA_OR_THANA_TD">                    	 
                    	 <select style="width:200px;" class="addressSelectBox"  tabindex="7">
                    	  <option>--Select Upazilla/Thana/City Corp./Pourashava--</option>
                    	 </select>   
                    	</div>
                      	</td>
                    	<td align="left"><span id="msg_birthDate"></span>&nbsp;</td>
          			</tr>                
          			<tr>
                    	<td align="left">Gender <font color="red">*</font></td>
                    	<td align="left">
                    	<s:iterator value="%{#application.ALLOWED_GENDER}" id="genderList" status="stat">
							<input type="radio" id="<s:property value='fieldId' />" name="<s:property value='fieldName' />" value="<s:property value='genderId' />" <s:property value='extraAttribute' /> tabindex="<s:property value='tabIndex' />" /> <s:property value='caption' /> 
						</s:iterator>	                    	  
                      </td>
                    	<td align="left"><span id="msg_sex"></span>&nbsp;
                    	<font style="color:red"><s:label name="sMsg_sex"></s:label></font>
                    	</td>
          			</tr>  
          			<tr>
                    	<td align="left">Religion <font color="red">*</font></td>
                    	<td align="left">
                    	  <select tabindex="10" name="personalDTO.empReligion" id="religion" class="txtBox">
     											<option value="" selected="selected">--Select Religion--</option>
												<option value="Islam">Islam</option>
												<option value="Hinduism">Hinduism</option>
												<option value="Christianity">Christianity</option>
												<option value="Buddhism">Buddhism</option>
												<option value="Other">Other</option>
						  </select>
                      </td>
                    	<td align="left"><span id="msg_religion"></span>&nbsp;
                    	<font style="color:red"><s:label name="sMsg_religion"></s:label></font>
                    	</td>
          			</tr>                   		
          							
          			<tr>
                    	<td align="left" valign="top">Marital Status<font color="red">*</font></td>
                    	<td align="left" valign="top">
     					   <select tabindex="11" name="personalDTO.empMaritalStatus" id="maritalStatus" class="txtBox" onchange="controlChildDiv(this.value)">
												<option value="">--Select Marital Status--</option>
												<option value="Married">Married</option>
												<option value="Single">Single</option>
												<option value="Divorced">Divorced</option>
												<option value="Widowed">Widowed</option>
												<option value="Separated">Separated</option>
												<option value="Others">Others</option>
						   </select>
						  
						  <div id="childDiv" style="display:none;">
						    Do you have any children ?(&#2438;&#2474;&#2472;&#2494;&#2480; &#2453;&#2507;&#2472; &#2488;&#2472;&#2509;&#2468;&#2494;&#2472; &#2438;&#2459;&#2503; &#2453;&#2495; &#2472;&#2494;?)
						    <br/>
						    <input type="radio" tabindex="12" name="personalDTO.empChildYN" value="Y" id="childYes" onClick="controlChildDetailDiv(1)" /> &#2489;&#2509;&#2479;&#2494;
                    	  	&nbsp;&nbsp;&nbsp;
     					  	<input type="radio" tabindex="13" name="personalDTO.empChildYN" value="N" id="childNo" checked="checked" onClick="controlChildDetailDiv(0)"  /> &#2472;&#2494;     					    
						  </div>
     					  <div id="childDetailDiv" style="display:none;">
     					   <table  width="90%" border="0">
     					   <tr>
     					   	<td width="50%" align="left">&#2459;&#2503;&#2482;&#2503;&#2480; &#2488;&#2434;&#2454;&#2509;&#2479;&#2494;</td>
     					   	<td width="50%" align="left"><input type="text" tabindex="14" id="totalSon" name="personalDTO.empSonCount" value="<s:property value='personalDTO.empSonCount' />" class="txtBox" style="width: 100px;" maxlength="2"></td>
     					   </tr>
     					   <tr>
     					   	<td align="left">&#2478;&#2503;&#2527;&#2503;&#2480; &#2488;&#2434;&#2454;&#2509;&#2479;&#2494;</td>
     					   	<td align="left"><input type="text" id="totalDaughter" tabindex="15" name="personalDTO.empDaughterCount" value="<s:property value='personalDTO.empDaughterCount' />" class="txtBox" style="width: 100px;" maxlength="2"></td>
     					   </tr>
     					   </table>
     					  </div>
     					  <script type="text/javascript">
     					    function controlChildDiv(status)
     					    {
	     					     if(status!="Single" && status!="")
	     					     {
	     					      document.getElementById("childDiv").style.display="block";
	     					      
		     					     if(document.getElementById("childYes").checked==true)
		     					     {
		     					      document.getElementById("childDetailDiv").style.display="block";
		     					     }
		     					     else if(document.getElementById("childNo").checked==true)
		     					     {
		     					      document.getElementById("childDetailDiv").style.display="none";
		     					     }
		     					     
		     					     document.getElementById("spouseName").disabled=false;
		     					     
	     					     }
	     					     else
	     					     {
	     					      document.getElementById("childDiv").style.display="none";
	     					      document.getElementById("childDetailDiv").style.display="none";
	     					      document.getElementById("spouseName").disabled='true';
	     					      document.getElementById("spouseName").value="";
	     					     }
	     					     
	     					     
     					    }
     					    function controlChildDetailDiv(status)
     					    {
     					      if(status=="1")
	     					     {
	     					      document.getElementById("childDetailDiv").style.display="block";
	     					     }
	     					     else
	     					     {
	     					      document.getElementById("childDetailDiv").style.display="none";
	     					     }
     					    }
     					  </script>
                      </td>
                    	<td align="left">
                    	<span id="msg_maritalStatus"></span>&nbsp;
                    	<font style="color:red"><s:label name="sMsg_maritalStatus"></s:label></font>
                    	</td>
          			</tr>
          			<tr>
                    	<td align="left">Spouse Name</td>
                    	<td align="left">
                    	  <input type="text" tabindex="17" id="spouseName" name="personalDTO.empSpouseName" value="<s:property value='personalDTO.empSpouseName' />" class="txtBox" disabled="disabled">
                      </td>
                    	<td align="left"><span id="msg_spouseName"></span>&nbsp;
                    	<font style="color:red"><s:label name="sMsg_spouseName"></s:label></font>
                    	</td>
          			</tr> 
          			<!-- 
					<tr>
                    	<td align="left">Date of Birth(Spouse)</td>
                    	<td align="left">
                    	  <input type="text" id="spouseBirthDate" name="personalDTO.spousBirthDate" value="" class="txtBox" style="width: 150px;">
                    	  <font style='color:maroon;font-size: 13px;'>[DD-MM-YYYY]</font>
                      </td>
                    	<td align="left"><span id="msg_spouseBirthDate"></span>&nbsp;</td>
          			</tr>
          			 -->	
          			<tr>
                    	<td align="left">Mobile<font color="red">*</font></td>
                    	<td align="left">
                    	  <input type="text" id="mobileNumber" tabindex="18" name="personalDTO.empMobileNumber" value="<s:property value='personalDTO.empMobileNumber' />" class="txtBox">
                    	  
                      </td>
                    	<td align="left"><span id="msg_mobile"></span>&nbsp;
                    	<font style="color:red"><s:label name="sMsg_mobile"></s:label></font>
                    	</td>
          			</tr>
          			
          			<tr>
                    	<td align="left">Height<font color="red">*</font></td>
                    	<td align="left">
                    	  <input type="text" id="heightFeet" tabindex="19" name="personalDTO.empHeightFeet" value="<s:property value='personalDTO.empHeightFeet' />" class="txtBox" style="width: 70px;" maxlength="2" onkeyup="calculateHeightinCM()"> Feet
                    	  &nbsp;&nbsp;
                    	  <input type="text" id="heightInches" tabindex="20" name="personalDTO.empHeightInches" value="<s:property value='personalDTO.empHeightInches' />" class="txtBox" style="width: 70px;" maxlength="2" onkeyup="calculateHeightinCM()"> Inches                    	  
	                   	  <b>or</b>                    	  
                    	  <input type="text" id="hightCm" tabindex="20" readonly="readonly"  value="<s:property value='personalDTO.empHeightCM' />" class="txtBox" style="width: 50px;background-color: pink;" maxlength="2"> CM
                      </td>
                    	<td align="left"><span id="msg_height"></span>&nbsp;
                    	<font style="color:red"><s:label name="sMsg_height"></s:label></font>
                    	</td>
          			</tr>
          			<tr>
                    	<td align="left">Weight(KG)<font color="red">*</font></td>
                    	<td align="left">
                    	  <input type="text" tabindex="21" id="weightKg" name="personalDTO.empWeight" value="<s:property value='personalDTO.empWeight' />" class="txtBox" style="width: 150px;" maxlength="3">
                    	  
                      </td>
                    	<td align="left"><span id="msg_weight"></span>&nbsp;
                    	<font style="color:red"><s:label name="sMsg_weight"></s:label></font>
                    	</td>
          			</tr>
          			
          			<tr>
                    	<td align="left">Blood Group</td>
                    	<td align="left">
                    		<select tabindex="22"  name="personalDTO.empBloodGroup" id="bloodGroup" class="txtBox" >
												<option value="">--Select Blood Group--</option>
												<option value="O-">O-</option>
												<option value="O+">O+</option>
												<option value="A-">A-</option>
												<option value="A+">A+</option>
												<option value="B-">B-</option>
												<option value="B+">B+</option>
												<option value="AB-">AB-</option>
												<option value="AB+">AB+</option>
						    </select>  
                    	  
                        </td>
                    	<td align="left">&nbsp;</td>
          			</tr>
          			
          			<tr>
                    	<td align="left" valign="top">&#2438;&#2474;&#2472;&#2494;&#2480; &#2453;&#2495; &#2453;&#2507;&#2472; &#2437;&#2488;&#2494;&#2478;&#2480;&#2509;&#2469;&#2509;&#2479;&#2468;&#2494; &#2438;&#2459;&#2503;?</td>
                    	<td align="left" valign="top">
						    <input type="radio" tabindex="23" name="personalDTO.empDisabilityYN" value="Y" id="disablilityYes" onClick="controlDisabilityDiv(1)" /> &#2489;&#2509;&#2479;&#2494;
                    	  	&nbsp;&nbsp;&nbsp;
     					  	<input type="radio" tabindex="24" name="personalDTO.empDisabilityYN" value="N" id="disablilityNo" checked="checked" onClick="controlDisabilityDiv(0)"  /> &#2472;&#2494;     					    
                    		<div id="disabilityDiv" style="display: none;">
                    		&#2453;&#2495; &#2471;&#2480;&#2472;&#2503;&#2480; &#2437;&#2488;&#2494;&#2478;&#2480;&#2509;&#2469;&#2509;&#2479;&#2468;&#2494; &#2438;&#2459;&#2503; &#2468;&#2494;&#2480; &#2482;&#2495;&#2454;&#2497;&#2472; -
                    		<textarea rows="3" cols="55" tabindex="25" name="personalDTO.empDisabilityDetail" id="disabilityDetail" style="border: 1px solid grey;"><s:property value='personalDTO.empDisabilityDetail' /></textarea>
                    		</div>
                    		<script type="text/javascript">
                    		function controlDisabilityDiv(status)
                    		{
                    		 
     					      if(status=="1")
	     					     {
	     					      document.getElementById("disabilityDiv").style.display="block";
	     					     }
	     					     else
	     					     {
	     					      document.getElementById("disabilityDiv").style.display="none";
	     					     }
     					    
                    		}
                    		</script>
                        </td>
                    	<td align="left" valign="top">
                    	<span id="msg_disabilityDetail"></span>&nbsp;
                    	<font style="color:red"><s:label name="sMsg_disabilityDetail"></s:label></font>
                    	</td>
          			</tr>
          			
          			
          			
          			<tr><td colspan="3" height="40">&nbsp;</td>
          			</tr> 
          			<tr>          			
          			<td colspan="3" align="left" style="border-top: 1px solid #006219;" valign="bottom">
      					<b>Note :</b> <font color="red">*</font> indicates mandatory fields.
    				</td>
    				</tr>
  			   </table>          			
        </div>
  			<div id="step-2">
            <h2 class="StepTitle">Step 2: Identification and Nominee Information</h2>	
            <table cellspacing="3" cellpadding="3" align="left" width="100%">
          			<tr>
                    	<td align="center" colspan="3">&nbsp;</td>
          			</tr>        
          			<tr>
                    	<td align="left" width="23%">National Id</td>
                    	<td align="left" width="47%">
                    	  <input type="text" tabindex="26" id="nationalId" name="personalDTO.nationalId" value="<s:property value='personalDTO.nationalId' />" class="txtBox" style="width: 154px;">
                    	  &nbsp;&nbsp;
                    	  Birth Reg Id
                    	  &nbsp;&nbsp;&nbsp;&nbsp;
                    	  <input type="text" tabindex="27" id="birthRegId" name="personalDTO.birthRegId" value="<s:property value='personalDTO.birthRegId' />" class="txtBox" style="width: 152px;">
                      </td>
                    	<td align="left" width="30%" valign="middle"><span id="msg_nationalId_birthReg"></span>&nbsp;
                    	<font style="color:red"><s:label name="sMsg_nationalId_birthReg"></s:label></font>
                    	</td>
          			</tr>
          			<tr>
                    	<td align="left">Passport Number</td>
                    	<td align="left">
                    	  <input type="text" tabindex="28" id="passportNumber" name="personalDTO.passportNo" value="<s:property value='personalDTO.passportNo' />" class="txtBox" style="width: 154px;" onblur="controlPassportInfo(this.value)" maxlength="9">
                    	  &nbsp;&nbsp;
                    	  Old Passport(If any)
                    	  
                    	  <input type="text" tabindex="29" id="prevPassportNo" name="personalDTO.oldPassportNo" value="<s:property value='personalDTO.oldPassportNo' />" class="txtBox" style="width: 120px;margin-left: 2px;" disabled="disabled">
                    	  <br/>
                    	  
                    	  <font style='color:maroon;font-size: 13px;'>[XX9999999 or X9999999]</font>
                      </td>
                    	<td align="left"><span id="msg_passportNumber"></span>&nbsp;
                    	<font style="color:red"><s:label name="sMsg_passportNumber"></s:label></font>
                    	</td>
          			</tr> 
          			<tr>
                    	<td align="left">Passport Issue Date </td>
                    	<td align="left">
                    	  <input type="text" tabindex="30" id="passportIssueDate" name="personalDTO.passportIssueDate" value="<s:property value='personalDTO.passportIssueDate' />" class="txtBox" style="width: 150px;" disabled="disabled">
                    	  <font style='color:maroon;font-size: 13px;'>[DD-MM-YYYY]</font>
                      </td>
                    	<td align="left"><span id="msg_passportIssueDate"></span>&nbsp;
                    	<font style="color:red"><s:label name="sMsg_passportIssueDate"></s:label></font>
                    	</td>
          			</tr>   
          			<tr>
                    	<td align="left">Passport Exp Date </td>
                    	<td align="left">
                    	  <input type="text" tabindex="31" id="passportExpireDate" name="personalDTO.passportExpDate" value="<s:property value='personalDTO.passportExpDate' />" class="txtBox" style="width: 150px;" disabled="disabled">
                    	  <font style='color:maroon;font-size: 13px;'>[DD-MM-YYYY]</font>
                      </td>
                    	<td align="left"><span id="msg_passportExpDate"></span>&nbsp;
                    	<font style="color:red"><s:label name="sMsg_passportExpDate"></s:label></font>
                    	</td>
          			</tr>    
          			
          			<tr>
                    	<td align="left">Nominee's Name <font color="red">*</font></td>
                    	<td align="left">
                    	  <input type="text" tabindex="32" id="nomineeName" name="nomineeDTO.nomineeName" value="<s:property value='nomineeDTO.nomineeName' />" class="txtBox" style="width: 150px;">
                    	  &nbsp;
                    	  Relationship<font color="red">*</font>
                    	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                    	  
						  <select  tabindex="33" style="width:145px;" class="txtBox" name="nomineeDTO.nomineeRelation" id="nomineeRelationship">
					        <option value="" selected="selected">-Select Relation-</option>
					     	<s:iterator value="%{#application.ALL_RELATION}" id="relationList">
					     	  <option value="<s:property value="relationId" />"><s:property value="relationName" /></option>
					     	</s:iterator>
						  </select>
										
                      </td>
                    	<td align="left"><span id="msg_nomineeName_relationship"></span>&nbsp;
                    	<font style="color:red"><s:label name="sMsg_nomineeName_relationship"></s:label></font>
                    	</td>
          			</tr> 
          			<tr>
                    	<td align="left">Father's Name(Nominee's)<font color="red">*</font></td>
                    	<td align="left">
                    	  <input type="text" tabindex="34" id="nomineeFatherName" name="nomineeDTO.nomineeFatherName" value="<s:property value='nomineeDTO.nomineeFatherName' />" class="txtBox" style="width: 430px;">                    	  
                      	</td>
                    	<td align="left"><span id="msg_nomineeFather"></span>&nbsp;
                    	<font style="color:red"><s:label name="sMsg_nomineeFather"></s:label></font>
                    	</td>
          			</tr> 
          			 
          			<tr>
                    	<td align="left">Mother's Name(Nominee's)<font color="red">*</font></td>
                    	<td align="left">
                    	  <input type="text" tabindex="35" id="nomineeMotherName" name="nomineeDTO.nomineeMotherName" value="<s:property value='nomineeDTO.nomineeMotherName' />" class="txtBox" style="width: 430px;">
                      	</td>
                    	<td align="left"><span id="msg_nomineeMother"></span>&nbsp;
                    	<font style="color:red"><s:label name="sMsg_nomineeMother"></s:label></font>
                    	</td>
          			</tr>  
					<tr>
                    	<td align="left" valign="top">Address(Nominee) </td>
                    	<td align="left" valign="top" colspan="2">                    	  
                    		<table border="0" width="100%">
                    			<tr>
                    				<td width="20%">Division<font color="red">*</font></td>
                    				<td width="40%">
                    					<select tabindex="36" name="nomineeDTO.address.divisionId" id="NOMINEE_DIV" class="addressSelectBox" onchange="fetchJSONData_Dist(this.value,'NOMINEE_DIST')">
										        <option value="" selected="selected">--Select Division--</option>
										     	<s:iterator value="%{#application.ALL_DIVISION}" id="divisionList">
										     	  <option value="<s:property value="divisionId" />"><s:property value="divisionName" /></option>
										     	</s:iterator>
										</select>
                    				</td>
                    				<td width="40%"><span id="msg_nomineeDivision"></span>&nbsp;
                    				<font style="color:red"><s:label name="sMsg_nomineeDivision"></s:label></font>                    				
                    				</td>
                    			</tr>
                    			<tr>
                    				<td>Zila/District<font color="red">*</font></td>
                    				<td id="NOMINEE_DIST_TD">
                    					<select tabindex="37"  name="nomineeDTO.address.districtId" id="NOMINEE_DIST" class="addressSelectBox">
     									<option value=""></option>
										</select>
									</td>
									<td><span id="msg_nomineeDistrict"></span>&nbsp;
									<font style="color:red"><s:label name="sMsg_nomineeDistrict"></s:label></font>
									</td>
                    			</tr>
                    			
                    			<tr>
                    				<td>Upazila/Thana/City Corp./Pourashava<font color="red">*</font></td>
                    				<td id="NOMINEE_UPAZILLA_OR_THANA_TD">
                    					<select tabindex="38" name="nomineeDTO.address.upazillaOrThanaId" id="NOMINEE_UPAZILLA_OR_THANA" class="addressSelectBox">
										<option value=""></option>
										</select>										
                    				</td>
                    				<td><span id="msg_nomineeUpazillaOrThana"></span>&nbsp;
                    				<font style="color:red"><s:label name="sMsg_nomineeUpazillaOrThana"></s:label></font>
                    				</td>
                    			</tr>
                    			<tr>
                    				<td>Union/Ward<font color="red">*</font></td>
                    				<td id="NOMINEE_UNION_OR_WARD_TD">
                    					<select tabindex="39" name="nomineeDTO.address.unionOrWardId" id="NOMINEE_UNION_OR_WARD" class="addressSelectBox" >
										<option value=""></option>
										</select>										
                    				</td>
                    				<td><span id="msg_nomineeUnionOrWard"></span>&nbsp;
                    				<font style="color:red"><s:label name="sMsg_nomineeUnionOrWard"></s:label></font>
                    				</td>
                    			</tr>
                    			<tr>
                    				<td>Mauza/Moholla<font color="red">*</font></td>
                    				<td id="NOMINEE_MAUZA_OR_MOHOLLA_TD">
                    					<select tabindex="40" name="nomineeDTO.address.mauzaOrMohollaId" id="NOMINEE_MAUZA_OR_MOHOLLA" class="addressSelectBox">
										<option value=""></option>
										</select>
                    				</td>
                    				<td><span id="msg_nomineeMauzaOrMoholla"></span>&nbsp;
                    				<font style="color:red"><s:label name="sMsg_nomineeMauzaOrMoholla"></s:label></font>
                    				</td>
                    			</tr>
                    			<tr>
                    				<td>Village<font color="red">*</font></td>
                    				<td id="NOMINEE_VILLAGE_TD">
                    					<select tabindex="41" name="nomineeDTO.address.villageId" id="NOMINEE_VILLAGE" class="addressSelectBox">
										<option value=""></option>
										</select>		                    					
                    				</td>
                    				<td><span id="msg_nomineeVillage"></span>&nbsp;
                    				<font style="color:red"><s:label name="sMsg_nomineeVillage"></s:label></font>
                    				</td>
                    			</tr>
                    			<tr>
                    				<td>Post Office</td>
                    				<td>
                    					<input type="text" tabindex="42" name="nomineeDTO.address.postOffice" maxlength="100" id="NOMINEE_POST_OFFICE"  class="textBox" value="<s:property value="nomineeDTO.address.postOffice"/>" />
                    					
                    				</td>
                    				<td><span id="msg_nomineePostOffice"></span>&nbsp;
                    				<font style="color:red"><s:label name="sMsg_nomineePostOffice"></s:label></font>
                    				</td>
                    			</tr>
                    			<tr>
                    				<td>Post Code</td>
                    				<td>
                    				<input type="text" tabindex="43" name="nomineeDTO.address.postCode" maxlength="50" id="NOMINEE_POST_CODE"  class="textBox" value="<s:property value="nomineeDTO.address.postCode"/>" />
                    				
                    				</td>
                    				<td><span id="msg_nomineePostCode"></span>&nbsp;
                    				<font style="color:red"><s:label name="sMsg_nomineePostCode"></s:label></font>
                    				</td>
                    			</tr>
                    			<tr>
                    				<td>Road Number</td>
                    				<td height="30">
                    					<input type="text" tabindex="44" name="nomineeDTO.address.roadNumber" maxlength="100" id="NOMINEE_ROAD"  class="textBox" value="<s:property value="nomineeDTO.address.roadNumber"/>" />
                    					
                    				</td>
                    			</tr>
                    			<tr>
                    				<td>Household Number</td>
                    				<td height="30">
                    					<input type="text" tabindex="45" name="nomineeDTO.address.houseHoldNumber" maxlength="100" id="NOMINEE_HOUSEHOLD"  class="textBox" value="<s:property value="nomineeDTO.address.houseHoldNumber"/>" />
                    				</td>
                    			</tr>
                    			
                    		</table>  
                        </td>
          			</tr>   
          			<tr>
                    	<td align="left">Mobile/Land Phone(Nominee) <font color="red">*</font></td>
                    	<td align="left">
                    	  <input type="text" tabindex="46" id="nomineePhone" name="nomineeDTO.nomineePhoneOrMobile" value="<s:property value='nomineeDTO.nomineePhoneOrMobile'/>" class="txtBox" maxlength="15">
                      </td>
                    	<td align="left"><span id="msg_nomineePhone"></span>&nbsp;
                    	<font style="color:red"><s:label name="sMsg_nomineePhone"></s:label></font>
                    	</td>
          			</tr>    
          			<tr>
                    	<td align="left" valign="top">(First)Contact Person's Name<font color="red">*</font></td>
                    	<td align="left" colspan="2" valign="top">
                    	  
                    	  <input type="text" tabindex="47" id="nomineeContactName1" name="nomineeDTO.contact1Name" value="<s:property value='nomineeDTO.contact1Name'/>" class="txtBox" style="width: 150px">
                    	  &nbsp;&nbsp;
                    	  Mobile Number
                    	  &nbsp;&nbsp;
                    	  <input type="text" tabindex="48" id="nomineeContactMobile1" name="nomineeDTO.contact1Mobile" value="<s:property value='nomineeDTO.contact1Mobile'/>" class="txtBox" style="width: 130px;" onblur="getNomineeContactPhoneCount(this.id,this.value,'msg_nomineeContactPerson1')">
                      	  &nbsp;&nbsp;
                      	  Relation
                      	  &nbsp;&nbsp;
                      	  <select tabindex="49"  style="width:138px;" class="txtBox" id="nomineeContactRelation1" name="nomineeDTO.contact1Relation" >
					        <option value="" selected="selected">-Select Relation-</option>
					     	<s:iterator value="%{#application.ALL_RELATION}" id="relationList">
					     	  <option value="<s:property value="relationId" />"><s:property value="relationName" /></option>
					     	</s:iterator>
						  </select>
						  
						  	
                      	  <div id="msg_nomineeContactPerson1" style="clear:both;margin-top:5px;"></div>&nbsp;
                      	  <font style="color:red"><s:label name="sMsg_nomineeContactPerson1"></s:label></font>
                      	  	
                      </td>
                    	
          			</tr>   
          			
          			<tr>
                    	<td align="left" valign="top">(Second)Contact Person's Name<font color="red">*</font></td>
                    	<td align="left" colspan="2" valign="top">
                    	  <input type="text" tabindex="50" id="nomineeContactName2" name="nomineeDTO.contact2Name" value="<s:property value='nomineeDTO.contact2Name'/>" class="txtBox" style="width: 150px">
                    	  &nbsp;&nbsp;
                    	  Mobile Number
                    	  &nbsp;&nbsp;
                    	  <input type="text" tabindex="51" id="nomineeContactMobile2" name="nomineeDTO.contact2Mobile" value="<s:property value='nomineeDTO.contact2Mobile'/>" class="txtBox" style="width: 130px;" onblur="getNomineeContactPhoneCount(this.id,this.value,'msg_nomineeContactPerson2')">
                      	  &nbsp;&nbsp;
                      	  Relation
                      	  &nbsp;&nbsp;
                      	  <select  tabindex="52"  style="width:138px;" class="txtBox" name="nomineeDTO.contact2Relation" id="nomineeContactRelation2" >
					        <option value="" selected="selected">-Select Relation-</option>
					     	<s:iterator value="%{#application.ALL_RELATION}" id="relationList">
					     	  <option value="<s:property value="relationId" />"><s:property value="relationName" /></option>
					     	</s:iterator>
						  </select>
						  <div id="msg_nomineeContactPerson2" style="clear:both;margin-top:5px;"></div>&nbsp;
						  <font style="color:red"><s:label name="sMsg_nomineeContactPerson2"></s:label></font>
                      </td>
          			</tr> 
          			
          			<tr>
                    	<td align="left" valign="top">(Third)Contact Person's Name<font color="red">*</font></td>
                    	<td align="left" colspan="2" valign="top">
                    	  <input type="text" tabindex="53" id="nomineeContactName3" name="nomineeDTO.contact3Name" value="<s:property value='nomineeDTO.contact3Name'/>" class="txtBox" style="width: 150px">
                    	  &nbsp;&nbsp;
                    	  Mobile Number
                    	  &nbsp;&nbsp;
                    	  <input type="text" tabindex="54" id="nomineeContactMobile3" name="nomineeDTO.contact3Mobile" value="<s:property value='nomineeDTO.contact3Mobile'/>" class="txtBox" style="width: 130px;" onblur="getNomineeContactPhoneCount(this.id,this.value,'msg_nomineeContactPerson3')">
                      	  &nbsp;&nbsp;
                      	  Relation
                      	  &nbsp;&nbsp;
                      	  <select tabindex="55"  style="width:138px;" class="txtBox" name="nomineeDTO.contact3Relation" id="nomineeContactRelation3">
					        <option value="" selected="selected">-Select Relation-</option>
					     	<s:iterator value="%{#application.ALL_RELATION}" id="relationList">
					     	  <option value="<s:property value="relationId" />"><s:property value="relationName" /></option>
					     	</s:iterator>
						  </select>

						  <div id="msg_nomineeContactPerson3" style="clear:both;margin-top:5px;"></div>&nbsp;
						  <font style="color:red"><s:label name="sMsg_nomineeContactPerson3"></s:label></font>
                      </td>
                    	
          			</tr>   

          			
          			<tr><td colspan="3" height="15">&nbsp;</td>
          			</tr>
          			<tr>         			
          			<td colspan="3" align="left" style="border-top: 1px solid #006219;" valign="bottom">
      					<b>Note :</b> <font color="red">*</font> indicates mandatory fields.
    				</td>
    				</tr>            			
  			   </table>        
        </div>                      
  			<div id="step-3">
            <h2 class="StepTitle">Step 3: Experience & Preference Details</h2>	
            <table cellspacing="3" cellpadding="3" align="left" width="100%">
          			<tr>
                    	<td align="center" colspan="3">&nbsp;</td>
          			</tr>  
          			<tr>
	          		   <td colspan="3" valign="top">
	          		   	<b>Experiences in Bangladesh(&#2470;&#2503;&#2486;&#2503; &#2453;&#2494;&#2460;&#2503;&#2480; &#2437;&#2477;&#2495;&#2460;&#2509;&#2462;&#2468;&#2494;)</b>
	          		   </td>
          		   </tr>
          			<tr>
                    	<td align="left" valign="top" width="25%">&#2438;&#2474;&#2472;&#2495; &#2470;&#2503;&#2486;&#2503; &#2453;&#2507;&#2472; &#2474;&#2503;&#2486;&#2494;&#2527; &#2453;&#2468; &#2476;&#2459;&#2480; &#2453;&#2494;&#2460; &#2453;&#2480;&#2503;&#2459;&#2503;&#2472; &#2468;&#2494; &#2441;&#2482;&#2509;&#2482;&#2503;&#2454; &#2453;&#2480;&#2497;&#2472;</td>
                    	<td align="left" valign="top" colspan="2" width="75%">
                    	  <table align="center" border="0" cellpadding="3" cellspacing="0"
								width="100%" style="border: 1px solid #d1dcaf;">
								<tbody>
									
									<tr bgcolor="#F2F7E3">
										<td align="left" style="padding-left: 34px" width="30%">
											Job Category
										</td>
										<td align="left" style="padding-left: 34px" width="30%">
											Sub Category
										</td>
										<td align="left" style="padding-left: 34px" width="30%">
											Sub-Sub Category
										</td>
										<td align="left" style="padding-left: 34px" width="10%">
											Exp. Years
										</td>
										<td align="left" width="10%">
										</td>
									</tr>

									<tr bgcolor="#FAFCF3">

										<td align="center"
											style="vertical-align: top; padding-top: 0px; padding-bottom: 0px"
											id="jExpLocal_column1">
											<table width="100%" id="jobExpLocalCategoryTable" style="margin: 0px;"></table>
										</td>
										<td align="center"
											style="vertical-align: top; padding-top: 0px; padding-bottom: 0px"
											id="jExpLocal_column2">
											<table width="100%" id="jobExpLocalSubCategoryTable" style="margin: 0px;"></table>
										</td>
										<td align="center"
											style="vertical-align: top; padding-top: 0px; padding-bottom: 0px"
											id="jExpLocal_column3">
											<table width="100%" id="jobExpLocalSubSubCategoryTable" style="margin: 0px;"></table>
										</td>
										<td align="center"
											style="vertical-align: top; padding-top: 0px; padding-bottom: 0px"
											id="jExpLocal_column4">
											<table width="100%" id="jobExpLocalYearsTable" style="margin: 0px;"></table>
										</td>
										<td align="center" valign="top" style="vertical-align: top;"
											id="jExpLocal_column5">
											<div>
												<table width="100%" id="deleteJobExpLocalTable"></table>
											</div>
										</td>
									</tr>

									<tr>
										<td colspan="6" align="center">
											<input name="val" type="button" value="Add more(if needed)"
												onclick="addMoreLocalExpLoad()" width="42" height="9" tabindex="299" />
												<br/>												
										</td>
								</tbody>
							</table>
							<span id="msg_expLocal"></span>
							<font style="color:red"><s:label name="sMsg_expLocal"></s:label></font>
							<input type="hidden" id="localExpHidden" name="localExperience" />
                      </td>
                    	
          			</tr>
				    	          		         
          			
          		   <tr>
	          		   <td colspan="3" valign="top">
	          		   	<b>Experiences in Abroad(&#2476;&#2495;&#2470;&#2503;&#2486;&#2503; &#2453;&#2494;&#2460;&#2503;&#2480; &#2437;&#2477;&#2495;&#2460;&#2509;&#2462;&#2468;&#2494;)</b>
	          		   </td>
          		   </tr>
          			<tr>
                    	<td align="left" valign="top">&#2438;&#2474;&#2472;&#2495; &#2453;&#2507;&#2472; &#2470;&#2503;&#2486;&#2503; &#2476;&#2495;&#2470;&#2503;&#2486;&#2503; &#2453;&#2468; &#2476;&#2459;&#2480; &#2447;&#2476;&#2434; &#2453;&#2495; &#2474;&#2503;&#2486;&#2494;&#2527; &#2479;&#2497;&#2453;&#2509;&#2468; &#2459;&#2495;&#2482;&#2503;&#2472; &#2468;&#2494; &#2441;&#2482;&#2509;&#2482;&#2503;&#2454; &#2453;&#2480;&#2497;&#2472;</td>
                    	<td align="left" valign="top" colspan="2">
                    	  <table align="center" border="0" cellpadding="3" cellspacing="0"
								width="100%" style="border: 1px solid #d1dcaf;">
								<tbody>
									
									<tr bgcolor="#F2F7E3">
										<td align="left" style="padding-left: 34px" width="20%">
											Country
										</td>
										<td align="left" style="padding-left: 34px" width="23%">
											Job Category
										</td>
										<td align="left" style="padding-left: 34px" width="23%">
											Sub Category
										</td>
										<td align="left" style="padding-left: 34px" width="23%">
											Sub-Sub Category
										</td>
										<td align="left" style="padding-left: 34px" width="10%">
											Exp. Years
										</td>
										<td align="left" width="10%">
										</td>
									</tr>

									<tr bgcolor="#FAFCF3">

										<td align="center"
											style="vertical-align: top; padding-top: 0px; padding-bottom: 0px"
											id="jExpAbroad_column1">
											<table width="100%" align="center" id="jobExpCountryTable"></table>
										</td>
										<td align="center"
											style="vertical-align: top; padding-top: 0px; padding-bottom: 0px"
											id="jExpAbroad_column2">
											<table width="100%" id="jobExpCategoryTable" style="margin: 0px;"></table>
										</td>
										<td align="center"
											style="vertical-align: top; padding-top: 0px; padding-bottom: 0px"
											id="jExpAbroad_column3">
											<table width="100%" id="jobExpSubCategoryTable" style="margin: 0px;"></table>
										</td>
										<td align="center"
											style="vertical-align: top; padding-top: 0px; padding-bottom: 0px"
											id="jExpAbroad_column4">
											<table width="100%" id="jobExpSubSubCategoryTable" style="margin: 0px;"></table>
										</td>
										<td align="center"
											style="vertical-align: top; padding-top: 0px; padding-bottom: 0px"
											id="jExpAbroad_column5">
											<table width="100%" id="jobExpYearsTable" style="margin: 0px;"></table>
										</td>
										<td align="center" valign="top" style="vertical-align: top;"
											id="jExpAbroad_column6">
											<div>
												<table width="100%" id="deleteJobExpTable"></table>
											</div>
										</td>
									</tr>

									<tr>
										<td colspan="6" align="center">
											<input name="val" type="button" value="Add more(if needed)"
												onclick="addMoreAbroadExpLoad()" width="42" height="9" tabindex="299" />
												<br/>
												<font color="red"><s:label name="Err_JobExp"></s:label></font>
										</td>
								</tbody>
							</table>
							<span id="msg_expAborad"></span>
							<font style="color:red"><s:label name="sMsg_expAborad"></s:label></font>
							<input type="hidden" id="aboradExpHidden" name="abroadExperience" />
                      </td>
                    	
          			</tr>
          			         			
          		   <tr>
          		   <td colspan="3" valign="top">
          		   	<b>Country and Job Preference(&#2438;&#2474;&#2472;&#2495; &#2453;&#2507;&#2472; &#2470;&#2503;&#2486;&#2503; &#2453;&#2495; &#2474;&#2503;&#2486;&#2494;&#2527; &#2479;&#2503;&#2468;&#2503; &#2438;&#2455;&#2509;&#2480;&#2489;&#2496; &#2468;&#2494; &#2441;&#2482;&#2509;&#2482;&#2503;&#2454; &#2453;&#2480;&#2497;&#2472;)</b>
          		   </td>
          		   </tr>	
          			<tr>
                    	<td align="left" valign="top">Country Preference(&#2488;&#2480;&#2509;&#2476;&#2507;&#2458;&#2509;&#2458; &#2541; &#2463;&#2495; &#2470;&#2503;&#2486;&#2503;&#2480; &#2472;&#2494;&#2478; &#2441;&#2482;&#2509;&#2482;&#2503;&#2454; &#2453;&#2480;&#2497;&#2472;)</td>
                    	<td align="left" valign="top">
                            <select id="countryPreference" name="countryPreferenceIds" multiple="multiple" class="txtBox">
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
                    	<td align="left"  valign="top"><span id="msg_countryPreference"></span>&nbsp;
                    	<font style="color:red"><s:label name="sMsg_countryPreference"></s:label></font>
                    	</td>
          			</tr> 
          			<tr>
                    	<td align="left" valign="top">Job Preference(&#2458;&#2494;&#2453;&#2497;&#2480;&#2496; &#2453;&#2509;&#2479;&#2494;&#2463;&#2494;&#2455;&#2480;&#2496; &#2447;&#2476;&#2434; &#2488;&#2494;&#2476;&#2453;&#2509;&#2479;&#2494;&#2463;&#2494;&#2455;&#2480;&#2496; &#2437;&#2472;&#2497;&#2479;&#2494;&#2527;&#2496; &#2472;&#2495;&#2480;&#2509;&#2471;&#2494;&#2480;&#2472; &#2453;&#2480;&#2497;&#2472;)</td>
                    	<td align="left" valign="top" colspan="2">
                    	   <div id="jobPreferenceDiv"></div>
                    	   <br/><br/>
                    	   <input name='' type='button' value='Add more(if needed)' onclick='addJobPreferenceDiv()' width='42' height='9' tabindex='299' />
                    	   <div id="msg_expJobPreference" style="clear: both"></div>
                    	   <font style="color:red"><s:label name="sMsg_expJobPreference"></s:label></font>
                    	   <input type="hidden" id="jobPreferenceHidden" name="jobPreference" />
                      </td>
                    	
          			</tr> 
          			
          			<tr><td colspan="3" height="140">&nbsp;</td></tr>
          			<tr>          			
          			<td colspan="3" align="left" style="border-top: 1px solid #006219;" valign="bottom">
      					<b>Note :</b> <font color="red">*</font> indicates mandatory fields.
    				</td>
    				</tr>                                    			
  			   </table>               				          
        </div>
  			<div id="step-4">
  			<h2 class="StepTitle">Step 4: Academic and Language Qualification</h2>
        <table cellspacing="3" cellpadding="3" align="left" width="100%">
          			<tr>
                    	<td align="center" colspan="3">&nbsp;</td>
          			</tr>        
          			<tr>
                    	<td align="left" width="20%" valign="top">Educational Qualification(&#2486;&#2495;&#2453;&#2509;&#2487;&#2494;&#2455;&#2468; &#2479;&#2507;&#2455;&#2509;&#2479;&#2468;&#2494; &#2441;&#2482;&#2509;&#2482;&#2503;&#2454; &#2453;&#2480;&#2497;&#2472;)<br/>
                    	&#2476;&#2453;&#2509;&#2488;&#2503; &#2465;&#2495;&#2474;&#2509;&#2482;&#2507;&#2478;&#2494;&#2480; &#2456;&#2480;&#2463;&#2495; &#2441;&#2482;&#2509;&#2482;&#2503;&#2454; &#2453;&#2480;&#2497;&#2472;
                    	</td>
                    	<td align="left" width="50%" valign="top">
                    	  <select tabindex="401" name="educationDTO.heighestDegreeId" id="heighestDegree" class="txtBox" onchange="controlDegree(this.value)">
                    	      <option value="">--Select Degree--</option>
                    	  <s:iterator value="%{#application.ALL_DEGREE}" id="relationList">
					     	  <option value="<s:property value="degreeId" />"><s:property value="degreeName" /></option>
					     	</s:iterator>
						  </select>
                      </td>
                    	<td align="left" width="30%" valign="middle" valign="top"><span id="msg_heighestDegree"></span>&nbsp;
                    	<font style="color:red"><s:label name="sMsg_heighestDegree"></s:label></font>
                    	</td>
          			</tr>
          			<tr>
                    	<td align="left" valign="top">Name of the last attended Institute(&#2488;&#2480;&#2509;&#2476;&#2486;&#2503;&#2487; &#2486;&#2495;&#2453;&#2509;&#2487;&#2494; &#2474;&#2509;&#2480;&#2468;&#2495;&#2487;&#2509;&#2464;&#2494;&#2472;&#2503;&#2480; &#2472;&#2494;&#2478; &#2441;&#2482;&#2509;&#2482;&#2503;&#2454; &#2453;&#2480;&#2497;&#2472;)</td>
                    	<td align="left" valign="top">
                    	  <input type="text" tabindex="402" id="lastInstitute" name="educationDTO.lastInstitute" value="<s:property value="educationDTO.lastInstitute" />" class="txtBox" maxlength="100" />
                      </td>
                    	<td align="left" valign="top"><span id="msg_lastInstitute"></span>&nbsp;
                    	<font style="color:red"><s:label name="sMsg_lastInstitute"></s:label></font>
                    	</td>
          			</tr>
          			<tr>
                    	<td align="left">Year of Passing</td>
                    	<td align="left">
                    	  
                    	  <select tabindex="403" name="educationDTO.passingYear" id="passingYear" class="txtBox">
     											<option value="" selected="selected">--Select Year--</option>
     											<%
     												for(int i=2013; i>=1980;i--)
     												{
     											 %>
												<option value="<%=i%>"><%=i%></option>
												<% } %>
						  </select>
						  
                      </td>
                    	<td align="left"><span id="msg_passingYear"></span>&nbsp;
                    	<font style="color:red"><s:label name="sMsg_passingYear"></s:label></font>
                    	</td>
          			</tr>
          			
          			<tr>
                    	<td align="left" valign="top">Language<font color="red">*</font></td>
                    	<td align="left" valign="top" colspan="2">
                    	 
                    	  		<table align="center" border="0" cellpadding="3" cellspacing="0"
								width="100%" style="border: 1px solid #d1dcaf;">
								<tbody>
									
									<tr bgcolor="#F2F7E3">
										<td align="left" style="padding-left: 34px" width="30%">
											Language
										</td>
										<td align="left" style="padding-left: 34px" width="30%">
											Oral Skill
										</td>
										<td align="left" style="padding-left: 34px" width="30%">
											Writing Skill
										</td>
										<td align="left" width="10%">
										</td>
									</tr>

									<tr bgcolor="#FAFCF3">

										<td align="center"
											style="vertical-align: top; padding-top: 0px; padding-bottom: 0px"
											id="lan_column1">
											<table width="100%" align="center" id="languageTable"></table>
										</td>
										<td align="center"
											style="vertical-align: top; padding-top: 0px; padding-bottom: 0px"
											id="lan_column3">
											<table width="100%" id="oralTable" style="margin: 0px;"></table>
										</td>
										<td align="center"
											style="vertical-align: top; padding-top: 0px; padding-bottom: 0px"
											id="lan_column4">
											<table width="100%" id="writingTable" style="margin: 0px;"></table>
										</td>
										<td align="center" valign="top" style="vertical-align: top;"
											id="lan_column2">
											<div>
												<table width="100%" id="deleteLanguageTable"></table>
											</div>
										</td>
									</tr>

									<tr>
										<td colspan="4" align="center">
											<input name="val" type="button" value="Add more(if needed)"
												onclick="addMoreLanguage()" width="42" height="9" tabindex="299" />
																						</td>
								</tbody>
							</table>
							<input type="hidden" id="languageHidden" name="languages" />
							<span id="msg_language"></span>&nbsp;
							<font style="color:red"><s:label name="sMsg_language"></s:label></font>
                    	  
                        </td>

          			</tr>
          			<tr>
                    	<td align="left" valign="top">Training</td>
                    	<td align="left" colspan="2" valign="top">
                    	   <table align="center" border="0" cellpadding="3" cellspacing="0"
								width="100%" style="border: 1px solid #d1dcaf;">
								<tbody>
									
									<tr bgcolor="#F2F7E3">
										<td align="center" width="20%">
											Training Name<font color="red">*</font>
										</td>
										<td align="center"  width="15%">
											Institute/Training<br/>
											Center Name<font color="red">*</font>
										</td>
										<td align="center"  width="15%">
											Duration<font color="red">*</font>
										</td>
									    <td align="center"  width="30%">
											Description<font color="red">*</font>
										</td>
										<td align="center" >&nbsp;
										</td>
									</tr>

									<tr bgcolor="#FAFCF3">

										<td align="center"
											style="vertical-align: top; padding-top: 0px; padding-bottom: 0px"
											id="train_column1">
											<table width="100%" align="center" id="trainingNameTable"></table>
										</td>
										<td align="center"
											style="vertical-align: top; padding-top: 0px; padding-bottom: 0px"
											id="train_column2">
											<table width="100%" id="trainingFromTable" style="margin: 0px;"></table>
										</td>
										<td align="center"
											style="vertical-align: top; padding-top: 0px; padding-bottom: 0px"
											id="train_column3">
											<table width="100%" id="trainingDurationTable" style="margin: 0px;"></table>
										</td>
										<td align="center"
											style="vertical-align: top; padding-top: 0px; padding-bottom: 0px"
											id="train_column4">
											<table width="100%" id="trainingDescTable" style="margin: 0px;"></table>
										</td>
										
										<td align="center" valign="top" style="vertical-align: top;padding-top:0px; "
											id="train_column5">
												<table width="100%" id="deleteTrainingTable" style="margin: 0px;"></table>
										</td>
									</tr>

									<tr>
										<td colspan="5" align="center">
											<input name="val" type="button" value="Add more(if needed)"
												onclick="addMoreTraining()" width="42" height="9" tabindex="500" />
												
										</td>
								   </tr>
								   
								</tbody>
							</table>
							<input type="hidden" id="trainingHidden" name="trainings" value="" />
							<span id="msg_training"></span>&nbsp;
							<font style="color:red"><s:label name="sMsg_training"></s:label></font>
                        </td>
                    	
                    	
          			</tr>


          			
          			
          			<tr><td colspan="2">Have you any training from TTC? <input type="radio" name="yn" id="yn1" value="yes"> Yes <input type="radio" name="yn" id="yn2" value="no" checked="checked"> No</td></tr>
					
          			<tr id="ttctraining" style="display:none">
                    	<td align="left" valign="top">Training from TTC</td>
                    	<td align="left" colspan="2" valign="top">
                    	   <table align="center" border="0" cellpadding="3" cellspacing="0"
								width="100%" style="border: 1px solid #d1dcaf;">
								<tbody>
									
									<tr bgcolor="#F2F7E3">
										<td align="center" width="20%">
											Training Name<font color="red">*</font>
										</td>
										<td align="center"  width="15%">
											Institute/Training<br/>
											Center Name<font color="red">*</font>
										</td>
										<td align="center"  width="15%">
											Duration<font color="red">*</font>
										</td>
									    <td align="center"  width="30%">
											Description<font color="red">*</font>
										</td>
										<td align="center" >&nbsp;
										</td>
									</tr>

									<tr bgcolor="#FAFCF3">

										<td align="center"
											style="vertical-align: top; padding-top: 0px; padding-bottom: 0px"
											id="train_column1">
											<table width="100%" align="center" id="trainingNameTable1"></table>
										</td>
										<td align="center"
											style="vertical-align: top; padding-top: 0px; padding-bottom: 0px"
											id="train_column2">
											<table width="100%" id="trainingFromTable1" style="margin: 0px;"></table>
										</td>
										<td align="center"
											style="vertical-align: top; padding-top: 0px; padding-bottom: 0px"
											id="train_column3">
											<table width="100%" id="trainingDurationTable1" style="margin: 0px;"></table>
										</td>
										<td align="center"
											style="vertical-align: top; padding-top: 0px; padding-bottom: 0px"
											id="train_column4">
											<table width="100%" id="trainingDescTable1" style="margin: 0px;"></table>
										</td>
										
										<td align="center" valign="top" style="vertical-align: top;padding-top:0px; "
											id="train_column5">
												<table width="100%" id="deleteTrainingTable1" style="margin: 0px;"></table>
										</td>
									</tr>

									<tr>
										<td colspan="5" align="center">
											<input name="val" type="button" value="Add more(if needed)"
												onclick="addMoreTraining1()" width="42" height="9" tabindex="500" />
												
										</td>
								   </tr>
								   
								</tbody> 
							</table> 
							<!--  
							<input type="hidden" id="trainingHidden" name="trainings" value="" />
							<span id="msg_training"></span>&nbsp;
							<font style="color:red"><s:label name="sMsg_training"></s:label></font>
							-->
                        </td>
          			</tr>
          			

          			<tr>
                 	   		<td colspan="3" height="80">&nbsp;</td>
                	</tr>
                	
                <tr>
			    <td colspan="3" align="left" style="border-top: 1px solid #006219;">
			      <b>Note :</b> <font color="red">*</font> indicates mandatory fields.
			    </td>
			    </tr>
          			 
          </table>        
                          			
        </div>
        <div id="step-5">
            <h2 class="StepTitle">Step 5: Contact Information</h2>	
            <table width="100%" border="0" cellspacing="1" class="infoTable">
    <tr bgcolor="#F2F7E3">
     <td align="center" width="50%" colspan="2" style="text-align: center;font-weight: bold;">Permanent Address</td>
     <td align="center" width="50%" colspan="2" style="text-align: center;font-weight: bold;">Mailing Address
     [<input type="checkbox" tabindex="550" name="mAddress.sameAsPermanent" id="mp" onclick="copyPermanentAddress(this.checked)"  /> As Permanent Address]</td>
    </tr>
    <tr>
     <td width="15%">Division<font color="red">*</font></td>
     <td width="35%">
     <div style="float: left">	
     <select tabindex="501" name="pAddress.divisionId" id="PERMANENT_DIV" class="addressSelectBox" onchange="fetchJSONData_Dist(this.value,'PERMANENT_DIST')">
        <option value="" selected="selected">--Select Division--</option>
     	<s:iterator value="%{#application.ALL_DIVISION}" id="divisionList">
     	  <option value="<s:property value="divisionId" />"><s:property value="divisionName" /></option>
     	</s:iterator>
     </select>
     </div>
     <div id="msg_pDivision" style="float: left"></div>&nbsp;
     </td>
     <td width="15%">Division<font color="red">*</font></td>
     <td width="35%">
     <div style="float: left">
     <select tabindex="551"  name="mAddress.divisionId" id="MAILING_DIV" class="addressSelectBox" onchange="fetchJSONData_Dist(this.value,'MAILING_DIST')">
        <option value="" selected="selected">--Select Division--</option>
     	<s:iterator value="%{#application.ALL_DIVISION}" id="divisionList">
     	  <option value="<s:property value="divisionId" />"><s:property value="divisionName" /></option>
     	</s:iterator>
     </select>
     </div>
     <div id="msg_mDivision" style="float: left"></div>&nbsp;
     </td>
    </tr>
    <tr>
     <td>Zila/District<font color="red">*</font></td>
     <td valign="top" >
     <div style="float: left" id="PERMANENT_DIST_TD">
     <select tabindex="502"  name="pAddress.districtId" id="PERMANENT_DIST" class="addressSelectBox">
     <option value=""></option>	
     </select>
     </div>
     <div id="msg_pDistrict" style="float: left"></div>&nbsp;
     </td>
     <td valign="top">Zila/District<font color="red">*</font></td>
     <td valign="top">
     <div style="float: left"  id="MAILING_DIST_TD">
     <select tabindex="552" name="mAddress.districtId" id="MAILING_DIST" class="addressSelectBox" onchange="fetchJSONData_Thana(this.value,'MAILING_THANA')">
     <option value=""></option>
     </select>
     </div>
     <div id="msg_mDistrict" style="float: left"></div>&nbsp;

     </td>
    </tr>
    
  
    
    <tr>
     <td>Upazila/Thana/City Corp./Pourashava<font color="red">*</font></td>
     <td valign="top">
        <div style="float: left"  id="PERMANENT_UPAZILLA_OR_THANA_TD">
        <select tabindex="503" name="pAddress.upazillaOrThanaId" id="PERMANENT_UPAZILLA_OR_THANA" class="addressSelectBox">
        <option value=""></option>
        </select>
        </div>
        <div id="msg_pUpazillaOrThana" style="float: left"></div>&nbsp;
     </td>
     <td valign="top">Upazila/Thana/City Corp./Pourashava<font color="red">*</font></td>
     <td valign="top">
     	<div style="float: left"  id="MAILING_UPAZILLA_OR_THANA_TD">
     	<select tabindex="553" name="mAddress.upazillaOrThanaId" id="MAILING_UPAZILLA_OR_THANA" class="addressSelectBox">
        <option value=""></option>
        </select>
        </div>
        <div id="msg_mUpazillaOrThana" style="float: left"></div>&nbsp;
     </td>
    </tr>
    
    
    
    <tr>
     <td>Union/Ward<font color="red">*</font></td>
     <td valign="top">
       <div style="float: left"  id="PERMANENT_UNION_OR_WARD_TD">
       <select tabindex="504" name="pAddress.unionOrWardId" id="PERMANENT_UNION_OR_WARD" class="addressSelectBox" >
       <option value=""></option>
        </select>
        </div>
        <div id="msg_pUnionOrWard" style="float: left"></div>&nbsp;
     </td>
     <td valign="top">Union/Ward<font color="red">*</font></td>
     <td valign="top">
     	<div style="float: left"  id="MAILING_UNION_OR_WARD_TD">
     	<select tabindex="554" name="mAddress.unionOrWardId" id="MAILING_UNION_OR_WARD" class="addressSelectBox" >
        <option value=""></option>
        </select>
        </div>
        <div id="msg_mUnionOrWard" style="float: left"></div>&nbsp;
     </td>
    </tr>
    
    <tr>
     <td>Mauza/Moholla<font color="red">*</font></td>
     <td valign="top">
     	<div style="float: left"  id="PERMANENT_MAUZA_OR_MOHOLLA_TD">
        <select tabindex="505" name="pAddress.mauzaOrMohollaId" id="PERMANENT_MAUZA_OR_MOHOLLA" class="addressSelectBox">
        <option value=""></option>
        </select>
        </div>
        <div id="msg_pMauzaOrMoholla" style="float: left"></div>&nbsp;
     </td>
     <td valign="top">Mauza/Moholla<font color="red">*</font></td>
     <td valign="top">
     	<div style="float: left" id="MAILING_MAUZA_OR_MOHOLLA_TD">
     	<select tabindex="555" name="mAddress.mauzaOrMohollaId" id="MAILING_MAUZA_OR_MOHOLLA" class="addressSelectBox">
        <option value=""></option>
        </select>
        </div>
        <div id="msg_mMauzaOrMoholla" style="float: left"></div>&nbsp;
     </td>
    </tr>
    
    <tr>
     <td>Village<font color="red">*</font></td>
     <td valign="top">
     	<div style="float: left"  id="PERMANENT_VILLAGE_TD">
     	<select tabindex="506" name="pAddress.villageId" id="PERMANENT_VILLAGE" class="addressSelectBox">
        <option value=""></option>
        </select>
        </div>
        <div id="msg_pVillage" style="float: left"></div>&nbsp;
     </td>
     <td valign="top">Village<font color="red">*</font></td>
     <td valign="top">
     	<div style="float: left"  id="MAILING_VILLAGE_TD">
     	<select tabindex="556" name="mAddress.villageId" id="MAILING_VILLAGE" class="addressSelectBox">
        <option value=""></option>
        </select>
        </div>
        <div id="msg_mVillage" style="float: left"></div>&nbsp;		
     </td>
    </tr>
    
    <tr>
     <td>Post Office</td>
     <td valign="top">
     	<div style="float: left">
     		<input type="text" tabindex="507" name="pAddress.postOffice" maxlength="100" id="PERMANENT_POST_OFFICE"  class="textBox" value="<s:property value="pAddress.postOffice"/>"  />
     	</div>
     	<div id="msg_pPostOffice" style="float: left"></div>&nbsp;
     </td>
     <td valign="top">Post Office</td>
     <td valign="top">
     	<div style="float: left">
     		<input type="text" tabindex="557" name="mAddress.postOffice" maxlength="100" id="MAILING_POST_OFFICE" class="textBox" value="<s:property value="mAddress.postOffice"/>"  />
     	</div>
     	<div id="msg_mPostOffice" style="float: left"></div>&nbsp;
     </td>
    </tr>
    
     <tr>
     <td>Post Code</td>
     <td valign="top">
     	<div style="float: left">
     		<input type="text" tabindex="508" name="pAddress.postCode" maxlength="20" id="PERMANENT_POST_CODE"  class="textBox" value="<s:property value="pAddress.postCode"/>" />
     	</div>
     	<div id="msg_pPostCode" style="float: left"></div>&nbsp;
     </td>
     <td valign="top">Post Code</td>
     <td valign="top">
     <div style="float: left">
     	<input type="text" tabindex="558" name="mAddress.postCode" maxlength="20" id="MAILING_POST_CODE"  class="textBox" value="<s:property value="mAddress.postCode"/>" />
     </div>
     	<div id="msg_mPostCode" style="float: left"></div>&nbsp;     		
     </td>
    </tr>
    
    
    <tr>
     <td>Road Number</td>
     <td valign="top">
     	<input type="text" tabindex="509" name="pAddress.roadNumber" maxlength="100" id="PERMANENT_ROAD"  class="textBox" value="<s:property value="pAddress.roadNumber"/>" />
     </td>
     <td valign="top">Road Number</td>
     <td valign="top">
     	<input type="text" tabindex="559" name="mAddress.roadNumber" maxlength="100" id="MAILING_ROAD"  class="textBox" value="<s:property value="mAddress.roadNumber"/>" />     		
     </td>
    </tr>
    
    <tr>
     <td>Household Number</td>
     <td valign="top">
     	<input type="text" tabindex="510" name="pAddress.houseHoldNumber" maxlength="100" id="PERMANENT_HOUSEHOLD"  class="textBox" value="<s:property value="pAddress.houseHoldNumber"/>" />
     </td>
     <td valign="top">Household Number</td>
     <td valign="top">
     	<input type="text" tabindex="560" name="mAddress.houseHoldNumber" maxlength="100" id="MAILING_HOUSEHOLD"  class="textBox" value="<s:property value="mAddress.houseHoldNumber"/>" />     		
     </td>
    </tr>
    <tr>
    	<td colspan="2"><font style="color:red"><s:label name="sMsg_permanentAddress"></s:label></font></td>
    	<td colspan="2"><font style="color:red"><s:label name="sMsg_mailingAddress"></s:label></font></td>
    </tr>
    
    <tr>
    <td colspan="4" height="30">&nbsp;</td>
    </tr>
    <tr>
    <td colspan="4" align="left" style="padding-left: 20px;border-top: 1px solid #006219;">
      <b>Note :</b> <font color="red">*</font> indicates mandatory fields.
    </td>
    </tr>
    
    </table>        
        </div>
        
  		</div>
<!-- End SmartWizard Content -->  		
</form> 
  		
</td></tr>
</table> 

<script type="text/javascript">


$(document).ready(function(){
   $("#countryPreference").multiselect();
});

function controlPassportInfo(passport)
{
 if(passport=="")
  {
    document.getElementById("prevPassportNo").disabled='true';
    document.getElementById("passportIssueDate").disabled='true';
    document.getElementById("passportExpireDate").disabled='true';
    
    document.getElementById("prevPassportNo").value='';
    document.getElementById("passportIssueDate").value='';
    document.getElementById("passportExpireDate").value='';
  }
  else
  {
    document.getElementById("prevPassportNo").disabled=false;
    document.getElementById("passportIssueDate").disabled=false;
    document.getElementById("passportExpireDate").disabled=false;
  }
}



</script>
<script type="text/javascript">
var cntLX=new Array(1);
cntLX[0]=0;
var cntAX=new Array(1);
cntAX[0]=0;

function defaultLocalExpLoad()
{
    row=0;
 
	
	var elementJobCat = document.createElement("select");
	elementJobCat.id = "localExpJobCat"+cntLX[row];
	elementJobCat.name = cntLX[row];
	var tmpIndex=cntLX[row];
	elementJobCat.onchange=function(){fetchJobCategory(this.value,2,tmpIndex,'localExpJobSubCat'+tmpIndex,'localJobCategory')};
	elementJobCat.options[0] = new Option("Select","");
	
	<s:iterator value="%{#application.ACTIVE_JOB_MAIN_CATEGORY}" id="jobCatList" status="stat">
		elementJobCat.options[ <s:property value="#stat.count" />] = new Option("<s:property value='jobTitle' />","<s:property value='jobId' />"); 
	</s:iterator>
	
	elementJobCat.style.width = '120px';
	elementJobCat.style.border="1px solid grey";
	elementJobCat.style.textAlign = 'left';
	elementJobCat.tabIndex=localExpTab+cntLX[row]+2;

	var elementJobSubCat = document.createElement("div");
	elementJobSubCat.id = "localExpJobSubCat"+cntLX[row];
	elementJobSubCat.style.width = '50px';
	elementJobSubCat.style.height = '20px'
	elementJobSubCat.name = cntLX[row];
	elementJobSubCat.tabIndex=localExpTab+cntLX[row]+3;
	
	var elementJobSubSubCat = document.createElement("div");
	elementJobSubSubCat.id = "localExpJobSubSubCat"+cntLX[row];
	elementJobSubSubCat.style.width = '50px';
	elementJobSubSubCat.style.height = '20px'
	elementJobSubSubCat.name = cntLX[row];
	elementJobSubSubCat.tabIndex=localExpTab+cntLX[row]+4;
	
	var elementExpYear = document.createElement("input");
	elementExpYear.id = "localExpYear"+cntLX[row];
	elementExpYear.style.width = '50px';	
	elementExpYear.setAttribute('maxlength',100);
	elementExpYear.style.border="1px solid grey";
	elementExpYear.name = cntLX[row];
	elementExpYear.tabIndex=localExpTab+cntLX[row]+5;
	
	
		
	var elementDelete = document.createElement("a");
	elementDelete.id = "delete_expLocal"+cntLX[row];
	elementDelete.href = "javascript:void(0)";
	elementDelete.innerHTML = "<font size=2>Delete</font>";
	elementDelete.value = cntLX[row];
	elementDelete.tabIndex=aboradExpTab+cntLX[row]+6;
	
	if(window.addEventListener)
	{              //DOM compliant
	
	 elementDelete.addEventListener("click",function()
	                                        {
	                                          document.getElementById("delete_expLocal"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("jExpLocal_column1"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("jExpLocal_column2"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("jExpLocal_column3"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("jExpLocal_column4"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("jExpLocal_column5"+elementDelete.value).style.display = 'none';
	                                          return false;
	                                        },true);
	 }
	  else
	     {           //IE6 standards compliant mode
	     elementDelete.attachEvent("onclick",function()
	                                         {
	                                          document.getElementById("delete_expLocal"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("jExpLocal_column1"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("jExpLocal_column2"+elementDelete.value).style.display = 'none';                                          
	                                          document.getElementById("jExpLocal_column3"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("jExpLocal_column4"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("jExpLocal_column5"+elementDelete.value).style.display = 'none';
	                                          return false;
	                                         },true);
	     }
	
		
	


	 
	table2 = document.getElementById("jobExpLocalCategoryTable");
	table2.style.border = '0px';
	newRow = table2.insertRow(table2.rows.length);
	newRow.style.border = '0px';
	newCell = newRow.insertCell(0);
	newCell.style.border = '0px';
	newCell.appendChild(elementJobCat);
	newRow.id = "jExpLocal_column1"+cntLX[row];
	
	table3 = document.getElementById("jobExpLocalSubCategoryTable");
	table3.style.border = '0px';
	newRow = table3.insertRow(table3.rows.length);
	newRow.style.border = '0px';
	newCell = newRow.insertCell(0);
	newCell.style.border = '0px';
	newCell.appendChild(elementJobSubCat);
	newRow.id = "jExpLocal_column2"+cntLX[row];
	
	
	table4 = document.getElementById("jobExpLocalSubSubCategoryTable");
	table4.style.border = '0px';
	newRow = table4.insertRow(table4.rows.length);
	newRow.style.border = '0px';
	newCell = newRow.insertCell(0);
	newCell.style.border = '0px';
	newCell.appendChild(elementJobSubSubCat);
	newRow.id = "jExpLocal_column3"+cntLX[row];
	
	
	  
	table5 = document.getElementById("jobExpLocalYearsTable");
	table5.style.border = '0px';
	newRow = table5.insertRow(table5.rows.length);
	newRow.style.border = '0px';
	newCell = newRow.insertCell(0);
	newCell.style.border = '0px';
	newCell.appendChild(elementExpYear);
	newRow.id = "jExpLocal_column4"+cntLX[row];
	
	table6 = document.getElementById("deleteJobExpLocalTable");
	table6.style.border = '0px';
	newRow = table6.insertRow(table6.rows.length);
	newRow.style.border = '0px';
	newCell = newRow.insertCell(0);
	newCell.style.border = '0px';
	
	 
	newCell.appendChild(elementDelete);
	newRow.id = "jExpLocal_column5"+cntLX[row];
	 
	 
	 cntLX[row]++;
	// document.getElementById("languageNumber").value=cnt[row];
	 return cntLX[row];
 
}

function addMoreLocalExpLoad()
{
    row=0;
 
	
	var elementJobCat = document.createElement("select");
	elementJobCat.id = "localExpJobCat"+cntLX[row];
	elementJobCat.name = cntLX[row];
	var tmpIndex=cntLX[row];
	elementJobCat.onchange=function(){fetchJobCategory(this.value,2,tmpIndex,'localExpJobSubCat'+tmpIndex,'localJobCategory')};
	elementJobCat.options[0] = new Option("Select","");
	
	<s:iterator value="%{#application.ACTIVE_JOB_MAIN_CATEGORY}" id="jobCatList" status="stat">
		elementJobCat.options[ <s:property value="#stat.count" />] = new Option("<s:property value='jobTitle' />","<s:property value='jobId' />"); 
	</s:iterator>
	
	elementJobCat.style.width = '120px';
	elementJobCat.style.border="1px solid grey";
	elementJobCat.style.textAlign = 'left';
	elementJobCat.tabIndex=localExpTab+cntLX[row]+2;

	var elementJobSubCat = document.createElement("div");
	elementJobSubCat.id = "localExpJobSubCat"+cntLX[row];
	elementJobSubCat.style.width = '50px';
	elementJobSubCat.style.height = '20px'
	elementJobSubCat.name = cntLX[row];
	elementJobSubCat.tabIndex=localExpTab+cntLX[row]+3;
	
	var elementJobSubSubCat = document.createElement("div");
	elementJobSubSubCat.id = "localExpJobSubSubCat"+cntLX[row];
	elementJobSubSubCat.style.width = '50px';
	elementJobSubSubCat.style.height = '20px'
	elementJobSubSubCat.name = cntLX[row];
	elementJobSubSubCat.tabIndex=localExpTab+cntLX[row]+4;
	
	var elementExpYear = document.createElement("input");
	elementExpYear.id = "localExpYear"+cntLX[row];
	elementExpYear.style.width = '50px';	
	elementExpYear.setAttribute('maxlength',100);
	elementExpYear.style.border="1px solid grey";
	elementExpYear.name = cntLX[row];
	elementExpYear.tabIndex=localExpTab+cntLX[row]+5;
	
	
		
	var elementDelete = document.createElement("a");
	elementDelete.id = "delete_expLocal"+cntLX[row];
	elementDelete.href = "javascript:void(0)";
	elementDelete.innerHTML = "<font size=2>Delete</font>";
	elementDelete.value = cntLX[row];
	elementDelete.tabIndex=aboradExpTab+cntLX[row]+6;
	
	if(window.addEventListener)
	{              //DOM compliant
	
	 elementDelete.addEventListener("click",function()
	                                        {
	                                          document.getElementById("delete_expLocal"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("jExpLocal_column1"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("jExpLocal_column2"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("jExpLocal_column3"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("jExpLocal_column4"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("jExpLocal_column5"+elementDelete.value).style.display = 'none';
	                                          return false;
	                                        },true);
	 }
	  else
	     {           //IE6 standards compliant mode
	     elementDelete.attachEvent("onclick",function()
	                                         {
	                                          document.getElementById("delete_expLocal"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("jExpLocal_column1"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("jExpLocal_column2"+elementDelete.value).style.display = 'none';                                          
	                                          document.getElementById("jExpLocal_column3"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("jExpLocal_column4"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("jExpLocal_column5"+elementDelete.value).style.display = 'none';
	                                          return false;
	                                         },true);
	     }
	
		
	


	 
	table2 = document.getElementById("jobExpLocalCategoryTable");
	table2.style.border = '0px';
	newRow = table2.insertRow(table2.rows.length);
	newRow.style.border = '0px';
	newCell = newRow.insertCell(0);
	newCell.style.border = '0px';
	newCell.appendChild(elementJobCat);
	newRow.id = "jExpLocal_column1"+cntLX[row];
	
	table3 = document.getElementById("jobExpLocalSubCategoryTable");
	table3.style.border = '0px';
	newRow = table3.insertRow(table3.rows.length);
	newRow.style.border = '0px';
	newCell = newRow.insertCell(0);
	newCell.style.border = '0px';
	newCell.appendChild(elementJobSubCat);
	newRow.id = "jExpLocal_column2"+cntLX[row];
	
	
	table4 = document.getElementById("jobExpLocalSubSubCategoryTable");
	table4.style.border = '0px';
	newRow = table4.insertRow(table4.rows.length);
	newRow.style.border = '0px';
	newCell = newRow.insertCell(0);
	newCell.style.border = '0px';
	newCell.appendChild(elementJobSubSubCat);
	newRow.id = "jExpLocal_column3"+cntLX[row];
	
	
	  
	table5 = document.getElementById("jobExpLocalYearsTable");
	table5.style.border = '0px';
	newRow = table5.insertRow(table5.rows.length);
	newRow.style.border = '0px';
	newCell = newRow.insertCell(0);
	newCell.style.border = '0px';
	newCell.appendChild(elementExpYear);
	newRow.id = "jExpLocal_column4"+cntLX[row];
	
	table6 = document.getElementById("deleteJobExpLocalTable");
	table6.style.border = '0px';
	newRow = table6.insertRow(table6.rows.length);
	newRow.style.border = '0px';
	newCell = newRow.insertCell(0);
	newCell.style.border = '0px';
	
	 
	newCell.appendChild(elementDelete);
	newRow.id = "jExpLocal_column5"+cntLX[row];
	 
	 
	 cntLX[row]++;
	// document.getElementById("languageNumber").value=cnt[row];
	 return cntLX[row];
 
}
function defaultAbroadExpLoad()
{
    row=0;
 
	var elementCountry = document.createElement("select");
	elementCountry.id = "abroadExpCountry"+cntAX[row];
	elementCountry.name = cntAX[row];	
	elementCountry.options[0] = new Option("Select","");
	
	
	<s:iterator value="%{#application.ALL_COUNTRY}" id="countryList" status="stat">
		elementCountry.options[<s:property value="#stat.count" />] = new Option("<s:property value='countryName' />","<s:property value='countryId' />"); 
	</s:iterator>
	   
	elementCountry.style.width = '150px';
	elementCountry.style.textAlign = 'left';
	elementCountry.style.border="1px solid grey";
	elementCountry.tabIndex=aboradExpTab+cntAX[row]+1;
	
	var elementJobCat = document.createElement("select");
	elementJobCat.id = "abroadExpJobCat"+cntAX[row];
	elementJobCat.name = cntAX[row];
	var tmpIndex=cntAX[row];
	elementJobCat.onchange=function(){fetchJobCategory(this.value,2,tmpIndex,'abroadExpJobSubCat'+tmpIndex,'abroadJobCategory')};
	elementJobCat.options[0] = new Option("Select","");
	
	<s:iterator value="%{#application.ACTIVE_JOB_MAIN_CATEGORY}" id="jobCatList" status="stat">
		elementJobCat.options[ <s:property value="#stat.count" />] = new Option("<s:property value='jobTitle' />","<s:property value='jobId' />"); 
	</s:iterator>
	
	elementJobCat.style.width = '120px';
	elementJobCat.style.border="1px solid grey";
	elementJobCat.style.textAlign = 'left';
	elementJobCat.tabIndex=aboradExpTab+cntAX[row]+2;

	var elementJobSubCat = document.createElement("div");
	elementJobSubCat.id = "abroadExpJobSubCat"+cntAX[row];
	elementJobSubCat.style.width = '50px';
	elementJobSubCat.style.height = '20px'
	//elementJobSubCat.style.border="1px solid grey";
	elementJobSubCat.name = cntAX[row];
	elementJobSubCat.tabIndex=aboradExpTab+cntAX[row]+3;
	
	var elementJobSubSubCat = document.createElement("div");
	elementJobSubSubCat.id = "abroadExpJobSubSubCat"+cntAX[row];
	elementJobSubSubCat.style.width = '50px';
	elementJobSubSubCat.style.height = '20px'
	//elementJobSubSubCat.style.border="1px solid grey";
	elementJobSubSubCat.name = cntAX[row];
	elementJobSubSubCat.tabIndex=aboradExpTab+cntAX[row]+4;
	
	var elementExpYear = document.createElement("input");
	elementExpYear.id = "abroadExpYear"+cntAX[row];
	elementExpYear.style.width = '50px';	
	elementExpYear.setAttribute('maxlength',100);
	elementExpYear.style.border="1px solid grey";
	elementExpYear.name = cntAX[row];
	elementExpYear.tabIndex=aboradExpTab+cntAX[row]+5;
	
	
		
	var elementDelete = document.createElement("a");
	elementDelete.id = "delete_expAbroad"+cntAX[row];
	elementDelete.href = "javascript:void(0)";
	elementDelete.innerHTML = "<font size=2>Delete</font>";
	elementDelete.value = cntAX[row];
	elementDelete.tabIndex=aboradExpTab+cntAX[row]+6;
	
	if(window.addEventListener)
	{              //DOM compliant
	
	 elementDelete.addEventListener("click",function()
	                                        {
	                                          document.getElementById("delete_expAbroad"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("jExpAbroad_column1"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("jExpAbroad_column2"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("jExpAbroad_column3"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("jExpAbroad_column4"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("jExpAbroad_column5"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("jExpAbroad_column6"+elementDelete.value).style.display = 'none';
	                                          return false;
	                                        },true);
	 }
	  else
	     {           //IE6 standards compliant mode
	     elementDelete.attachEvent("onclick",function()
	                                         {
	                                          document.getElementById("delete_expAbroad"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("jExpAbroad_column1"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("jExpAbroad_column2"+elementDelete.value).style.display = 'none';                                          
	                                          document.getElementById("jExpAbroad_column3"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("jExpAbroad_column4"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("jExpAbroad_column5"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("jExpAbroad_column6"+elementDelete.value).style.display = 'none';
	                                          return false;
	                                         },true);
	     }
	
		
	

	table1 = document.getElementById("jobExpCountryTable");
	table1.style.border = '0px';
	newRow = table1.insertRow(table1.rows.length);
	newRow.style.border = '0px';
	newCell = newRow.insertCell(0);
	newCell.style.border = '0px';
	newCell.appendChild(elementCountry);
	newRow.id = "jExpAbroad_column1"+cntAX[row];

	 
	table2 = document.getElementById("jobExpCategoryTable");
	table2.style.border = '0px';
	newRow = table2.insertRow(table2.rows.length);
	newRow.style.border = '0px';
	newCell = newRow.insertCell(0);
	newCell.style.border = '0px';
	newCell.appendChild(elementJobCat);
	newRow.id = "jExpAbroad_column2"+cntAX[row];
	
	table3 = document.getElementById("jobExpSubCategoryTable");
	table3.style.border = '0px';
	newRow = table3.insertRow(table3.rows.length);
	newRow.style.border = '0px';
	newCell = newRow.insertCell(0);
	newCell.style.border = '0px';
	newCell.appendChild(elementJobSubCat);
	newRow.id = "jExpAbroad_column3"+cntAX[row];
	
	
	table4 = document.getElementById("jobExpSubSubCategoryTable");
	table4.style.border = '0px';
	newRow = table4.insertRow(table4.rows.length);
	newRow.style.border = '0px';
	newCell = newRow.insertCell(0);
	newCell.style.border = '0px';
	newCell.appendChild(elementJobSubSubCat);
	newRow.id = "jExpAbroad_column4"+cntAX[row];
	
	
	  
	table5 = document.getElementById("jobExpYearsTable");
	table5.style.border = '0px';
	newRow = table5.insertRow(table5.rows.length);
	newRow.style.border = '0px';
	newCell = newRow.insertCell(0);
	newCell.style.border = '0px';
	newCell.appendChild(elementExpYear);
	newRow.id = "jExpAbroad_column5"+cntAX[row];
	
	table6 = document.getElementById("deleteJobExpTable");
	table6.style.border = '0px';
	newRow = table6.insertRow(table6.rows.length);
	newRow.style.border = '0px';
	newCell = newRow.insertCell(0);
	newCell.style.border = '0px';
	
	 
	newCell.appendChild(elementDelete);
	newRow.id = "jExpAbroad_column6"+cntAX[row];
	 
	 
	 cntAX[row]++;
	// document.getElementById("languageNumber").value=cnt[row];
	 return cntAX[row];
 
}


function addMoreAbroadExpLoad()
{
    row=0;
 
	var elementCountry = document.createElement("select");
	elementCountry.id = "abroadExpCountry"+cntAX[row];
	elementCountry.name = cntAX[row];	
	elementCountry.options[0] = new Option("Select","");
	<s:iterator value="%{#application.ALL_COUNTRY}" id="countryList" status="stat">
		elementCountry.options[ <s:property value="#stat.count" />] = new Option("<s:property value='countryName' />","<s:property value='countryId' />"); 
	</s:iterator>  
	elementCountry.style.width = '150px';
	elementCountry.style.textAlign = 'left';
	elementCountry.style.border="1px solid grey";
	elementCountry.tabIndex=aboradExpTab+cntAX[row]+1;
	
	var elementJobCat = document.createElement("select");
	elementJobCat.id = "abroadExpJobCat"+cntAX[row];
	elementJobCat.name = cntAX[row];
	var tmpIndex=cntAX[row];
	elementJobCat.onchange=function(){fetchJobCategory(this.value,2,tmpIndex,'abroadExpJobSubCat'+tmpIndex,'abroadJobCategory')};	
	elementJobCat.options[0] = new Option("Select","");
	<s:iterator value="%{#application.ACTIVE_JOB_MAIN_CATEGORY}" id="jobCatList" status="stat">
		elementJobCat.options[ <s:property value="#stat.count" />] = new Option("<s:property value='jobTitle' />","<s:property value='jobId' />"); 
	</s:iterator>
	elementJobCat.style.width = '120px';
	elementJobCat.style.border="1px solid grey";
	elementJobCat.style.textAlign = 'left';
	elementJobCat.tabIndex=aboradExpTab+cntAX[row]+2;

    var elementJobSubCat = document.createElement("div");
	elementJobSubCat.id = "abroadExpJobSubCat"+cntAX[row];
	elementJobSubCat.style.width = '50px';
	elementJobSubCat.style.height = '20px';
	//elementJobSubCat.style.border="1px solid grey";
	elementJobSubCat.name = cntAX[row];
	elementJobSubCat.tabIndex=aboradExpTab+cntAX[row]+3;
	
	var elementJobSubSubCat = document.createElement("div");
	elementJobSubSubCat.id = "abroadExpJobSubSubCat"+cntAX[row];
	elementJobSubSubCat.style.width = '50px';
	elementJobSubSubCat.style.height = '20px'
	//elementJobSubSubCat.style.border="1px solid grey";
	elementJobSubSubCat.name = cntAX[row];
	elementJobSubSubCat.tabIndex=aboradExpTab+cntAX[row]+4;
	
	var elementExpYear = document.createElement("input");
	elementExpYear.id = "abroadExpYear"+cntAX[row];
	elementExpYear.style.width = '50px';
	elementExpYear.setAttribute('maxlength',100);
	elementExpYear.style.border="1px solid grey";
	elementExpYear.name = cntAX[row];
	elementExpYear.tabIndex=aboradExpTab+cntAX[row]+4;
	
	
		
	var elementDelete = document.createElement("a");
	elementDelete.id = "delete_expAbroad"+cntAX[row];
	elementDelete.href = "javascript:void(0)";
	elementDelete.innerHTML = "<font size=2>Delete</font>";
	elementDelete.value = cntAX[row];
	elementDelete.tabIndex=aboradExpTab+cntAX[row]+5;
	
	if(window.addEventListener)
	{              //DOM compliant
	
	 elementDelete.addEventListener("click",function()
	                                        {
	                                          document.getElementById("delete_expAbroad"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("jExpAbroad_column1"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("jExpAbroad_column2"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("jExpAbroad_column3"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("jExpAbroad_column4"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("jExpAbroad_column5"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("jExpAbroad_column6"+elementDelete.value).style.display = 'none';
	                                          return false;
	                                        },true);
	 }
	  else
	     {           //IE6 standards compliant mode
	     elementDelete.attachEvent("onclick",function()
	                                         {
	                                          document.getElementById("delete_expAbroad"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("jExpAbroad_column1"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("jExpAbroad_column2"+elementDelete.value).style.display = 'none';                                          
	                                          document.getElementById("jExpAbroad_column3"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("jExpAbroad_column4"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("jExpAbroad_column5"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("jExpAbroad_column6"+elementDelete.value).style.display = 'none';
	                                          return false;
	                                         },true);
	     }
	
		
	

	table1 = document.getElementById("jobExpCountryTable");
	table1.style.border = '0px';
	newRow = table1.insertRow(table1.rows.length);
	newRow.style.border = '0px';
	newCell = newRow.insertCell(0);
	newCell.style.border = '0px';
	newCell.appendChild(elementCountry);
	newRow.id = "jExpAbroad_column1"+cntAX[row];

	 
	table2 = document.getElementById("jobExpCategoryTable");
	table2.style.border = '0px';
	newRow = table2.insertRow(table2.rows.length);
	newRow.style.border = '0px';
	newCell = newRow.insertCell(0);
	newCell.style.border = '0px';
	newCell.appendChild(elementJobCat);
	newRow.id = "jExpAbroad_column2"+cntAX[row];
	
		table3 = document.getElementById("jobExpSubCategoryTable");
	table3.style.border = '0px';
	newRow = table3.insertRow(table3.rows.length);
	newRow.style.border = '0px';
	newCell = newRow.insertCell(0);
	newCell.style.border = '0px';
	newCell.appendChild(elementJobSubCat);
	newRow.id = "jExpAbroad_column3"+cntAX[row];
	
	
	table4 = document.getElementById("jobExpSubSubCategoryTable");
	table4.style.border = '0px';
	newRow = table4.insertRow(table4.rows.length);
	newRow.style.border = '0px';
	newCell = newRow.insertCell(0);
	newCell.style.border = '0px';
	newCell.appendChild(elementJobSubSubCat);
	newRow.id = "jExpAbroad_column4"+cntAX[row];
	  
	table3 = document.getElementById("jobExpYearsTable");
	table3.style.border = '0px';
	newRow = table3.insertRow(table3.rows.length);
	newRow.style.border = '0px';
	newCell = newRow.insertCell(0);
	newCell.style.border = '0px';
	newCell.appendChild(elementExpYear);
	newRow.id = "jExpAbroad_column5"+cntAX[row];
	
	table4 = document.getElementById("deleteJobExpTable");
	table4.style.border = '0px';
	newRow = table4.insertRow(table4.rows.length);
	newRow.style.border = '0px';
	newCell = newRow.insertCell(0);
	newCell.style.border = '0px';
	
	 
	newCell.appendChild(elementDelete);
	newRow.id = "jExpAbroad_column6"+cntAX[row];
	 
	 
	 cntAX[row]++;
	// document.getElementById("languageNumber").value=cnt[row];
	 return cntAX[row];
 
}
var jobPreferenceCounter=0;
function getJobPreferenceDiv(jobPreferenceCounter)
{
  
    var jobPreferenceStr="<div id='jobP"+jobPreferenceCounter+"' style='clear:both;padding-top:10px;'>"+
                            "<div id='leftDiv"+jobPreferenceCounter+"1' style='float:left;width:170px;'>"+
                               "<select name='mainJob_"+jobPreferenceCounter+"' id='mainJob_"+jobPreferenceCounter+"' style='width:150px;border:1px solid grey;' onchange=\"fetchJobCategory(this.value,2,"+jobPreferenceCounter+",'leftDiv"+jobPreferenceCounter+"2','jobPreferenceJobCategory')\">";
     	jobPreferenceStr+="<option value=''>Select</option>";
	<s:iterator value="%{#application.ACTIVE_JOB_MAIN_CATEGORY}" id="jobCatList" status="stat">
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
var ttcId=new Array();
var ttcName=new Array();
function getTtcDiv()
{
 	<s:iterator value="%{#application.ALL_TTC}" id="TtcList" status="stat">
 		ttcId[<s:property value="%{#stat.count}" />-1]='<s:property value='ttcId' />';
		ttcName[<s:property value="%{#stat.count}" />-1]='<s:property value='ttcName' />';	 
	</s:iterator>
 
}
getTtcDiv();
function fetchJobCategory(parentJobId,level,componentIndex,waitingDiv,selectType)
{
	    
		$(".buttonNext").addClass("buttonDisabled");
		
        if(level==2 && selectType=="abroadJobCategory")
	         document.getElementById("abroadExpJobSubSubCat"+componentIndex).innerHTML="";
	    if(level==2 && selectType=="localJobCategory")
	         document.getElementById("localExpJobSubSubCat"+componentIndex).innerHTML="";
	    

 		var ajax_url="<img src='/BMREG_WEB/resources/images/ajax-loader.gif' alt='Loading ....' />"; 
		var url="/BMREG_WEB/fetchSubJob.action?etc="+new Date().getTime();
		
			$("#"+waitingDiv) 
			.html(ajax_url)  
			.load(url, {parentJobId: parentJobId,jobLevel: level,componentIndex:componentIndex,selectType:selectType,allOrActive:1},function(responseText){  
				if(responseText!="")
				$("#"+waitingDiv).innerHTML= responseText;
				
				$(".buttonNext").removeClass("buttonDisabled");
			});
}


</script>
<%String form_error=(String) request.getSession().getAttribute("form_error");
 if(form_error==null)form_error="first_time";
 if(form_error.equalsIgnoreCase("form_error") || form_error.equalsIgnoreCase("edit_form") ){
  %>
   <%@ include file="SetFormContent.jsp" %>
 <%} 
 
 else{%>
 <script type="text/javascript">
$(document).ready(function(){
	var yn1 = document.getElementById('yn1');
	var yn2 = document.getElementById('yn2');
	yn1.onclick = handler;
    yn2.onclick = handler;
    defaultTrainingLoad();
    defaultTrainingLoad1();
	defaultLanguageLoad();
	defaultLocalExpLoad();
	defaultAbroadExpLoad();
	addJobPreferenceDiv();
	$("#heighestDegree").val("7");
	$("#language0").val("Bangla");
	
	document.getElementById("lastInstitute").disabled='true';
    document.getElementById("passingYear").disabled='true';
	
});
function handler() 
{
    if(document.getElementById('yn1').checked)
    	document.getElementById('ttctraining').style.display = "";
    else
    	document.getElementById('ttctraining').style.display = "none";
}
function controlDegree(degree)
{
  if(degree==7)
   {
   	document.getElementById("lastInstitute").disabled='true';
    document.getElementById("passingYear").disabled='true';	
   }
   else
   {
    document.getElementById("lastInstitute").disabled=false;
    document.getElementById("passingYear").disabled=false;
   }
}
</script>
 <%} %>
 
<script type="text/javascript">
	//$('input').attr('autocomplete','off');  
</script>
</body>
</html>
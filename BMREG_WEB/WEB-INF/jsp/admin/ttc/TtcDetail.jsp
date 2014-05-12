<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="org.controller.registration.*" %>
<%@ page import="java.util.ArrayList" %>
<script type="text/javascript" src="/BMREG_WEB/resources/js/lib/jquery-1.6.4.min.js"></script> 
<script type="text/javascript" src="/BMREG_WEB/resources/js/util/util.js"></script>
<script type="text/javascript" src="/BMREG_WEB/resources/js/util/numeric.js"></script>
 
 <link type="text/css" rel="Stylesheet" href="/BMREG_WEB/resources/js/lib/jquery.validity.1.2.0/jquery.validity.css" />
        <script type="text/javascript" src="/BMREG_WEB/resources/js/lib/jquery.validity.1.2.0/jQuery.validity.js"></script>
 
  <script type="text/javascript" src="/BMREG_WEB/resources/js/address.js"></script>
  <script type="text/javascript" src="/BMREG_WEB/resources/js/regValidation.js"></script>
<script type="text/javascript">
var ajax_load="<br/><center><img src='/BMREG_WEB/resources/images/ajax-loader1.gif' border='0' /></center>";

function updateTTC()
{
var ttcId=$.trim(document.getElementById("ttcId").value);
 var ttcName=$.trim(document.getElementById("ttcName").value);
  var ttcPrincipal=$.trim(document.getElementById("ttcPrincipal").value);
  var address=$.trim(document.getElementById("address").value);
  var phone=$.trim(document.getElementById("phone").value);
  var emailAddress=$.trim(document.getElementById("emailAddress").value);
  
  if(ttcName==""){
   	alert("Please provide TTC Name.");
   	return;
   }
  else if(ttcPrincipal==""){
   	alert("Please provide TTC Principal Name.");
   	return;
   }
   else if(address==""){
   	alert("Please provide Address.");
   	return;
   }   
   
  
    var loadUrl="updateTtc.action";
			jQuery("#msgDiv")  
				.html(ajax_load)  
				.load(loadUrl, {ttcId:ttcId,ttcName:ttcName,ttcPrincipal:ttcPrincipal,address:address,phone:phone,emailAddress:emailAddress
				},function(responseText){  
					
					if(responseText=="Successfully Update TTC Information.")
							jQuery("#singleTTCDiv").html(responseText);
					else
					   		jQuery("#msgDiv").html(responseText);
									   
				});
   
   

}
</script>


<center>
<div class="box" style="margin-top: 30px;width: 880px;text-align: center;" id="">
    <h3>TTC UPdate Form</h3>
    <div style="padding-bottom: 30px;">
    <form action="" method="post" id="ttcForm" name="ttcForm">
    <table width="80%" align="center" border="0">
     	<tr>
     		<td width="30%" align="left">TTC Name</td>
     		<td width="70%" align="left"><input type="text" name="ttc.ttcName" id="ttcName" value="<s:property value='ttc.ttcName' />" style="border: 1px solid gray;width: 200px;" />
     		<input type="hidden" name="ttc.ttcId" id="ttcId" value="<s:property value='ttc.ttcId' />"/>
     		</td>
        </tr>
        <tr>
     		<td align="left">Principal Name</td>
     		<td align="left"><input type="text" name="ttc.ttcPrincipal" id="ttcPrincipal" value="<s:property value='ttc.ttcPrincipal' />" style="border: 1px solid gray;width: 200px;" /></td>
        </tr>
        <tr>
     		<td align="left">Address</td>
     		<td align="left"><textarea name="ttc.address" id="address" style="border: 1px solid gray;" cols="22" ><s:property value='ttc.address' /></textarea></td>
        </tr>              
        <tr>
     		<td align="left">Phone</td>
     		<td align="left"><input type="text" name="ttc.phone" id="phone" value="<s:property value='ttc.phone' />" style="border: 1px solid gray;width: 200px;width: 200px;" /></td>
        </tr>
        <tr>
     		<td align="left">Email</td>
     		<td align="left"><input type="text" name="ttc.emailAddress" id="emailAddress" value="<s:property value='ttc.emailAddress' />" style="border: 1px solid gray;width: 200px;width: 200px;" /></td>        
        </tr>           
    </table>
<p style="padding-top: 40px;">     
<input type="button" name="save" value="Update TTC Info" style="width: 200px;height: 35px;"  onclick="updateTTC()"/>
</p>
<div id="msgDiv"></div>
</form>
</div>
</div>
</center>
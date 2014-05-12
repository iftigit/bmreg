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

function updateTrade()
{
var tradeName=$.trim(document.getElementById("tradeName").value);
var tradeId=$.trim(document.getElementById("tradeId").value);

  if(tradeName==""){
   	alert("Please provide Trade Name.");
   	return;
   }

    var loadUrl="updateTrade.action";
			jQuery("#msgDiv")  
				.html(ajax_load)  
				.load(loadUrl, {tradeId:tradeId,tradeName:tradeName
				},function(responseText){  
					
					if(responseText=="Successfully Update Trade Information.")
							jQuery("#singleTradeDiv").html(responseText);
					else
					   		jQuery("#msgDiv").html(responseText);
									   
				});
   
   

}
</script>


<center>
<div class="box" style="margin-top: 30px;width: 880px;text-align: center;" id="">
    <h3>Trade UPdate Form</h3>
    <div style="padding-bottom: 30px;">
    <form action="" method="post" id="ttcForm" name="ttcForm">
    <table width="80%" align="center" border="0">
     	<tr>
     		<td width="30%" align="left">Trade Name</td>
     		<td width="70%" align="left"><input type="text" name="trade.tradeName" id="tradeName" value="<s:property value='trade.tradeName' />" style="border: 1px solid gray;width: 200px;" />
     		<input type="hidden" name="trade.id" id="tradeId" value="<s:property value='trade.id' />"/>
     		</td>
        </tr>           
    </table>
<p style="padding-top: 40px;">     
<input type="button" name="save" value="Update Trade Info" style="width: 200px;height: 35px;"  onclick="updateTrade()"/>
</p>
<div id="msgDiv"></div>
</form>
</div>
</div>
</center>
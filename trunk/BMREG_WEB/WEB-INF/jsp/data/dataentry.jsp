
<%@page import="org.apache.struts2.ServletActionContext"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>

<head>
<meta content="text/html;charset=utf-8" http-equiv="Content-Type">
<meta content="utf-8" http-equiv="encoding">
<title>G2G Data Entry</title>
 <link rel="stylesheet" href="/BMREG_WEB/resources/css/style.css" />	
<script type="text/javascript" src="/BMREG_WEB/resources/js/util/util.js"></script>
<script type="text/javascript" src="/BMREG_WEB/resources/js/lib/jquery.js"></script> 
<script type="text/javascript" src="/BMREG_WEB/resources/js/util/numeric.js"></script>

<script type="text/javascript">



var distid=new Array();
var distname=new Array();
var dcount = 0;
  
	'<s:iterator id="i" value="distLst">'
      var dd='<s:property value="i"/>';      
      distid[dcount]=dd;
      distname[dcount]=dd;
      dcount++;  
 	'</s:iterator>'  
 
function loadDistrictList()
{
  var ind=document.getElementById("dist");
  if(ind==null) return;
  ind.options.length = 0;  
  ind.options[0]=new Option("--Select District--","0");
  
  for(var k=0; k < dcount; k++)
  {   
    ind.options[k+1]= new Option(distname[k],distid[k]); 
  }
  
}

function fetchThana(rowid,id)
{

if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
     document.getElementById("thanacol_"+rowid).innerHTML=xmlhttp.responseText;
    }
  }
   var url="GetThanaList.action?id="+id+"&index="+rowid;
   xmlhttp.open("GET",url,true);
   xmlhttp.send();
}

function loadPrimaryDetails(){
	var distid = document.getElementById("dist").value;
	var thanaid = document.getElementById("thanaid").value;
    
	$.get("fetchData.action", {distid:distid,thanaid:thanaid},populateDetails);
}



function populateDetails(html){
    
    $find("detailDiv").innerHTML = "";
    //alert(html);
    $find("detailDiv").innerHTML = html;
          
}

function conAuction()
{
		
	var mainform=document.getElementById("mainform");
	mainform.action="dataEntryContinue.action";
	mainform.submit();

}

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
	 	<div style="font-size: 27px;font-weight: bold;">Bureau of Manpower, Employment & Training(BMET)</div>
	 	<div style="font-size: 20px;margin-top: 10px;" align="center">Data Entry</div>
	</div>
</div>
</center>
</div>
<br>

<fieldset >
		<legend align="center">
					Search by District Name, Thana Name
		</legend>

<form id="mainform" name="mainform" action="" method="post">
	<table align="center" >
			
			<tr>
				<th align="left">
					District Name
				</th>
				<td align="left" colspan="3">
					<select name="dist" id="dist" style="width:140px" onchange="fetchThana(1,this.options[this.selectedIndex].value)" >
						<option></option>
					</select>
				</td>
			</tr>	
				<tr>
			
				<th>
					Thana Name 
					
				</th>
				<td id="thanacol_1" colspan="3">
					<select name="thanaid"  id="thanaid"  style="width: 140px">
					 <option value="0"> </option>
					 </select>
					 
				</td>		
				
			</tr>		
			
			<tr>
			<th colspan="4" align="right">
					<input type="button" value="Continue &gt;&gt;"
						onclick="loadPrimaryDetails()" />
				</th>
			</tr>			
		
	</table>
	
	<div id="detailDiv" align="center" >

	
	</div>
	</form>
	
	<div id="msg" ></div>	
	
</fieldset>

	

<script type="text/javascript"> 
$(document).ready(function() {
loadDistrictList();

   // put all your jQuery goodness in here.
 });

</script>


<div id="footer">
&copy;BMET-2012. All right reserved.
</div>
</body>

</html>
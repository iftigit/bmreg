<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>BMET Reg. System - Login Required</title>

<link rel="stylesheet" href="/BMREG_WEB/resources/css/login.css" />	
<!--[if lt ie 7]>
<style type="text/css">
.pulldownMenuDropShadowRight {
  width:3;
  background: url("/BMREG_WEB/resources/images/b-menudropshadow.gif") no-repeat top right
}
.pulldownMenuDropShadowBottom {
  height:3;
  background: url("/BMREG_WEB/resources/images/b-menudropshadow.gif") no-repeat bottom left
}
.pulldownMenuDropShadowCorner {
  width:3;
  height:3;
  background: url("/BMREG_WEB/resources/images/b-menudropshadow.gif") no-repeat bottom right
}
.pulldownMenuArrowDown {
  filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src="/BMREG_WEB/resources/images/b-menuarrowdown.png", sizingMethod="image");
  background: none
}
.pulldownMenuArrowForOldIE {
  filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src="/BMREG_WEB/resources/images/b-menuarrow.png", sizingMethod="image");
  background: none
}
</style>
<![endif]-->
<script type="text/javascript" src="/BMREG_WEB/resources/js/util/menu.js"></script>
</head>

<body onload="menuInitPosition();">


<table width="100%" cellpadding="0" cellspacing="0" border="0" class="titleBorderTop">
<colgroup>
<col width="30">
<col width="*">
<col width="275">
</colgroup>
<tr>
<td><a href="/cgi-bin/wa?INDEX"><img src="/BMREG_WEB/resources/images/UH_seal.jpg" alt="UH LISTSERV" title="UH LISTSERV" border="0"></a></td>
<td></td>
<td align="right">
<p>
</p>
</td>
</tr>
</table>
<table width="100%" cellpadding="0" cellspacing="0" border="0" class="pulldownMenuTopLevel" id="menuBar">
<tr>
<td id="sub.cell" onmouseover="menuItemIn(0, 'sub')" onmouseout="menuItemOut(0, 'sub')" class="pulldownMenuItem" nowrap>
<a href="http://www.bmet.gov.bd">
BMET Home
</a>
</td>
<td id="sampleReg.cell" onmouseover="menuItemIn(0, 'sampleReg')" onmouseout="menuItemOut(0, 'sampleReg')" class="pulldownMenuItem" nowrap>
<a href="/BMREG_WEB/resources/manuals/Sample_Registration_Form.pdf">
Sample Registration Form
</a>
</td>
<td id="regManual.cell" onmouseover="menuItemIn(0, 'regManual')" onmouseout="menuItemOut(0, 'regManual')" class="pulldownMenuItem" nowrap>
<a href="/BMREG_WEB/resources/manuals/Registration_User_Manual.pdf">
Registration Manual
</a>
</td>

<td width="100%"></td>
<td id="login.cell" onmouseover="menuItemIn(0, 'login')" onmouseout="menuItemOut(0, 'login')" class="pulldownMenuItem" nowrap>
<a href="/BMREG_WEB/login.action">
Log In
</a>
</td>

</tr>
</table>
<br/>

<table width="100%" cellpadding="5" cellspacing="0" align="center">
<tr>
<td width="100%" align="center">
<h2>Login Required
</h2>
</td>
</tr>
</table>

<br>
<table cellpadding="0" cellspacing="0" border="0" align="center">
<tr>
<td valign="bottom" align="right"><img src="/BMREG_WEB/resources/images/b-boxcorner_1.gif" width="16" height="16" alt=""></td>
<td valign="bottom" background="/BMREG_WEB/resources/images/b-boxtop.gif"><img src="/BMREG_WEB/resources/images/b-blank.gif" height="16" width="16" alt=""></td>
<td valign="bottom" align="left"><img src="/BMREG_WEB/resources/images/b-boxcorner_2.gif" width="16" height="16" alt=""></td>
</tr>
<tr>
<td background="/BMREG_WEB/resources/images/b-boxleft.gif" align="right"><img src="/BMREG_WEB/resources/images/b-blank.gif" height="16" width="16" alt=""></td>
<td valign="top" align="left">
<table width="500" cellspacing="0" cellpadding="5" border="0">
<tr><td valign="middle" height="29" class="boxtop"><h4>Login Required</h4></td></tr>
<tr><td valign="top" height="270" class="boxback">
<table cellpadding="10" cellspacing="0" border="0">
<tr>
<td>
<p>Please enter your Mobile Number and your  password and click on the "Log In" button. 
If this is the first time you see this dialog, or if you have forgotten your password, you will need to get a new password at first.</p>
<br><br>
<form method="post" action="checkValidity.action">    
<input type="hidden" name="LOGIN1" value="">
<table align="center">
<tr>
<td><p><b><LABEL FOR="Email Address">Mobile Number:</LABEL></b></p></td>
<td><p><input name="userId" size="30" id="Email Address" style="border:1px solid grey"
></p></td>
</tr>
<tr>
<td><p><b><LABEL FOR="Password">Password:</LABEL></b></p></td>
<td><p><input name="password" type="password" size="30" id="Password" style="border:1px solid grey"></p></td>
</tr>

<tr>
<td></td>
<td>
<input type="submit" name="e" value="Log In">
<input type="button" name="c" value="Reset">

</td>
</tr>

</table>
<input type="hidden" name="X" value="">
</form>
</td>
</tr>
</table>

<table width="450" align="center" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td><font size="1">Unauthorized access is prohibited by law.</font> 
    </td>
  </tr>
</table>
</td></tr>
</table>
</td>
<td background="/BMREG_WEB/resources/images/b-boxright.gif" align="left"><img src="/BMREG_WEB/resources/images/b-blank.gif" height="16" width="16" alt=""></td>
</tr>
<tr>
<td valign="top" align="right"><img src="/BMREG_WEB/resources/images/b-boxcorner_3.gif" width="16" height="16" alt=""></td>
<td background="/BMREG_WEB/resources/images/b-boxbottom.gif" valign="top"><img src="/BMREG_WEB/resources/images/b-blank.gif" width="16" height="16" alt=""></td>
<td valign="top" align="left"><img src="/BMREG_WEB/resources/images/b-boxcorner_4.gif" width="16" height="16" alt=""></td>
</tr>
</table>










</body>
</html>

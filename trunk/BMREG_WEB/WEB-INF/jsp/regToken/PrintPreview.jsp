<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="org.controller.registration.*" %>
<%@ page import="java.util.ArrayList" %>
<html>
<style type="text/css" media="print">
.printbutton {
  visibility: hidden;
  display: none;
}
</style>
<body>
          <s:property value="tokenListString"/>
<br/><br/>
 <center>
  <script>
document.write("<input type='button' " +
"onClick='window.print()' " +
"class='printbutton' " +
"value='Print This Page'/>");
</script>
 </center>
</body>
</html>
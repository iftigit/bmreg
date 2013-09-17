<%@ taglib prefix="s" uri="/struts-tags"%>



	<br/>
	<table align="center" id="tbindv"  width="100%" border="1">
		<caption>
			Information Found for Union
		</caption>
		
							
		<tr>
			<td align="center" width="30%">
			Union 
			</td>
			<td align="center">
			Name 1 
			</td>
			<td align="center">
			Mobile No 1 
			</td>
			<td align="center">
			Name 2
			</td>
			<td align="center">
			Mobile No 2  
			</td>			
		</tr>	
		 <%int i=0; %>
		<s:iterator id="auid" value="dataLst">
		<tr>
			<td height="40">
				<s:property value="union"/>
				
			
			<input type="hidden" name="distLst[<%=i%>].dist"
						value="<s:property value="dist" />" />
			
			<input type="hidden" name="distLst[<%=i%>].thana"
						value="<s:property value="thana" />" />
			
			<input type="hidden" name="distLst[<%=i%>].union"
						value="<s:property value="union" />" />
			
			
			</td>
		
			<td height="40">
				<input type="text" id="distLst[<%=i%>].name1" name="distLst[<%=i%>].name1" value="<s:property value="name1"/>" style="font-size: medium;">
			</td>
			<td height="40">
				<input type="text" id="distLst[<%=i%>].mob1" name="distLst[<%=i%>].mob1" value="<s:property value="mob1"/>" style="font-size: medium;" maxlength="11" onkeypress="return numericOnly(event)">
			</td>
			<td height="40">
				<input type="text" id="distLst[<%=i%>].name2" name="distLst[<%=i%>].name2" value="<s:property value="name2"/>" style="font-size: medium;">
			</td>
			<td height="40">
				<input type="text" id="distLst[<%=i%>].mob2" name="distLst[<%=i%>].mob2" value="<s:property value="mob2"/>" style="font-size: medium;" maxlength="11" onkeypress="return numericOnly(event)">
			</td>
			
		</tr>	
		<%i++; %>
		</s:iterator>
	</table>

	

	<br />
	
		<table >
		<tr>
		
			<th>
			<input type="button" value="Save &gt;&gt;" style="float:right"
							onclick="conAuction()" />
			</th>
		</tr>
		</table>				
	
	





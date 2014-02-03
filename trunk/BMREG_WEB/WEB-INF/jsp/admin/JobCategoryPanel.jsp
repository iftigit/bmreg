 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="org.controller.registration.*" %>
<%@ page import="java.util.ArrayList" %>
 
 <table width="98%" style="border: 1px dotted green;">
            <tr>
            	<td colspan="5" style="text-align: left;color: maroon;font-size: 15px;font-weight: bold;" height="30">
            		Add/Edit Job Mapping
            	</td>
            </tr>
      		<tr>
      			<td width="8%" style="text-align: left;" height="30">Main Job</td>
      			<td width="22%">
      				<select style="border: 1px solid gray;width: 200px;" onchange="fetchJobCategory(this.value,2,0,'subJobDiv1','jobManagement')">
      				<option value="-99">Select Main Job</option>
      				<s:iterator value="mainJobList" id="mainJob">
      				 <option value="<s:property value="jobId" />" <s:if test="#mainJob.jobId == jobDTO.categoryId">selected='selected'</s:if> ><s:property value="jobTitle" /></option>
      				</s:iterator>
      				</select>
      			</td>
      			<td width="6%" style="text-align: left;">Sub Job</td>
      			<td width="22%" id="subJobDiv1">
      				<select style="border: 1px solid gray;width: 200px;" onchange="fetchJobCategory(this.value,3,0,'subJobDiv2','jobManagement')">
      					<option value="-99">Select Sub Job</option>      					
      					<s:iterator value="subJobList" id="subJob">
      				 		<option value="<s:property value="jobId" />" <s:if test="#subJob.jobId == jobDTO.subCategoryId">selected='selected'</s:if> ><s:property value="jobTitle" /></option>
      					</s:iterator>
      				</select>
      			</td>
      			<td width="10%" style="text-align: left;">Sub-sub Job</td>
      			<td width="22%" id="subJobDiv2">
      				<select style="border: 1px solid gray;width: 200px;">
      					<option value="-99">Select Sub-Sub Job</option>      					
      					<s:iterator value="subSubJobList" id="subSubJobId">
      				 		<option value="<s:property value="jobId" />" <s:if test="#subSubJobId.jobId == jobDTO.subSubCategoryId">selected='selected'</s:if> ><s:property value="jobTitle" /></option>
      					</s:iterator>
      				</select>
      			</td> 
      			<td width="10%" style="text-align: center;"><input type="button" value="Update"  name="editJob" /></td>
      		</tr>
      </table>
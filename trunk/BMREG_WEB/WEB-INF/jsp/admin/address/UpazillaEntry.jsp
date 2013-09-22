<div class="box" style="margin-top: 100px;width: 700px;text-align: center;">
    <h3>Upazila/Thana/City Corp./Pourashava Entry Form</h3>
    
    <table width="80%" align="center" border="0">
      <tr>
      	<td width="50%" align="left" style="padding-left: 10px;">Division</td>
      	<td width="50%" align="left" style="padding-left: 10px;">
      			<select tabindex="19"  id="PERMANENT_DIV" class="addressSelectBox" onchange="fetchJSONData_Dist(this.value,'PERMANENT_DIST')">
			        <option value="" selected="selected">--Select Division--</option>
			     	<option value="10">BARISAL</option>
			     	<option value="20">CHITTAGONG</option>
			     	<option value="30">DHAKA</option>
			     	<option value="40">KHULNA</option>
			     	<option value="50">RAJSHAHI</option>
			     	<option value="55">RANGPUR</option>
			     	<option value="60">SYLHET</option>										     	
			    </select>
      	</td>
      </tr>
      
      <tr>
      	<td width="50%" align="left" style="padding-left: 10px;">District</td>
      	<td width="50%" align="left" style="padding-left: 10px;" id="PERMANENT_DIST_TD">
      			<select tabindex="20"  id="PERMANENT_DIST" class="addressSelectBox">
      			  <option value=""></option>
     			</select>
      	</td>
      </tr>
      
      <tr>
      	<td width="50%" align="left" style="padding-left: 10px;">Upazilla/Thana/Pourashava/City Corporation</td>
      	<td width="50%" align="left" style="padding-left: 10px;">
      			<input type="text" name="" id="PERMANENT_UPAZILA_THANA" value="" style="width: 250px;border: 1px solid grey;" maxlength="100" />
      	</td>
      </tr>
      
      <tr>
      	<td width="50%" align="left" style="padding-left: 10px;"></td>
      	<td width="50%" align="left" style="padding-left: 10px;">
      			<input type="button" name="Submit" value="Submit" onclick="saveAddress('upazila')" />
      	</td>
      </tr>
      
      <tr>
      	<td colspan="2" style="text-align: center;" id="waitingDiv">
      		
      	</td>
      </tr>
      
    </table>
    

<br/>     
<div id="passwordInfoDiv" style="width: 100%"></div>
<br/>
<div  id="addressIdDiv" style="text-align: center;"></div>
<br/>
</div>
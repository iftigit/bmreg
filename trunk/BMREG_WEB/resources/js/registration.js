var version=BrowserDetect.init();
var totalExpNum=0;
var cntL=new Array(1);
cntL[0]=0;
var cntT=new Array(1);
cntT[0]=0;
var table1;


var eduTab=100;
var lanTab=201;
var expTab=300;
var trainTab=401;
var localExpTab=501;
var aboradExpTab=601;

function addMoreLanguage()
{

    row=0;
 
	var elementLanguage = document.createElement("select");
	elementLanguage.id = "language"+cntL[row];
	elementLanguage.name = cntL[row];
	elementLanguage.options[0] = new Option("Select","select");
	elementLanguage.options[1] = new Option("Abenaki","Abenaki");
	elementLanguage.options[2] = new Option("Arabic","Arabic");
	elementLanguage.options[3] = new Option("Aramaic","Aramaic");
	elementLanguage.options[4] = new Option("Ayapathu","Ayapathu");
	elementLanguage.options[5] = new Option("Balinese","Balinese");
	elementLanguage.options[6] = new Option("Bangla","Bangla");
	elementLanguage.options[7] = new Option("Bobangi","Bobangi");
	elementLanguage.options[8] = new Option("Bulgarian","Bulgarian");
	elementLanguage.options[9] = new Option("Burmese","Burmese");
	elementLanguage.options[10] = new Option("Catalan","Catalan");
	elementLanguage.options[11] = new Option("Chinese","Chinese");
	elementLanguage.options[12] = new Option("Chinook","Chinook");
	elementLanguage.options[13] = new Option("Cree","Cree");
	elementLanguage.options[14] = new Option("Creole","Creole");
	elementLanguage.options[15] = new Option("Czech","Czech");
	elementLanguage.options[16] = new Option("Danish","Danish");
	elementLanguage.options[17] = new Option("Dutch","Dutch");
	elementLanguage.options[18] = new Option("Eggon","Eggon");
	elementLanguage.options[19] = new Option("English","English");
	elementLanguage.options[20] = new Option("Farsi","Farsi");
	elementLanguage.options[21] = new Option("French","French");
	elementLanguage.options[22] = new Option("Gaelic","Gaelic");
	elementLanguage.options[23] = new Option("German","German");
	elementLanguage.options[24] = new Option("Gothic","Gothic");
	elementLanguage.options[25] = new Option("Greek","Greek");
	elementLanguage.options[26] = new Option("Haida","Haida");
	elementLanguage.options[27] = new Option("Hindi","Hindi");
	elementLanguage.options[28] = new Option("Hungarian","Hungarian");
	elementLanguage.options[29] = new Option("Indonesian","Indonesian");
	elementLanguage.options[30] = new Option("Italian","Italian");
	
	elementLanguage.options[31] = new Option("Jangshung","Jangshung");
	elementLanguage.options[32] = new Option("Japanese","Japanese");
	elementLanguage.options[33] = new Option("Konkani","Konkani");
	elementLanguage.options[34] = new Option("Korean","Korean");
	elementLanguage.options[35] = new Option("Koyo","Koyo");
	elementLanguage.options[36] = new Option("Latin","Latin");
	elementLanguage.options[37] = new Option("Malagasy","Malagasy");
	elementLanguage.options[38] = new Option("Malay","Malay");
	elementLanguage.options[39] = new Option("Malayalam","Malayalam");
	
	
	elementLanguage.options[40] = new Option("Mayan","Mayan");
	elementLanguage.options[41] = new Option("Nahuatl","Nahuatl");
	elementLanguage.options[42] = new Option("Nande","Nande");
	elementLanguage.options[43] = new Option("Pidgin","Pidgin");
	elementLanguage.options[44] = new Option("Pirah","Pirah");
	elementLanguage.options[45] = new Option("Polish","Polish");
	elementLanguage.options[46] = new Option("Prussian","Prussian");
	elementLanguage.options[47] = new Option("Quechua","Quechua");
	elementLanguage.options[48] = new Option("Romanian","Romanian");
	elementLanguage.options[49] = new Option("Russian","Russian");
	elementLanguage.options[50] = new Option("Saanich","Saanich");
	elementLanguage.options[51] = new Option("Sign","Sign");
	elementLanguage.options[52] = new Option("Spanish","Spanish");
	
	
	elementLanguage.options[53] = new Option("Swedish","Swedish");
	elementLanguage.options[54] = new Option("Tagalog","Tagalog");
	elementLanguage.options[55] = new Option("Tamil","Tamil");
	elementLanguage.options[56] = new Option("Thai","Thai");
	elementLanguage.options[57] = new Option("Urdu","Urdu");
	elementLanguage.options[58] = new Option("Venda","Venda");
	elementLanguage.options[59] = new Option("Vietnamese","Vietnamese");
	elementLanguage.options[60] = new Option("Wagiman","Wagiman");
    elementLanguage.options[61] = new Option("Others","Others");


	elementLanguage.style.width = '200px';
	elementLanguage.style.textAlign = 'left';
	elementLanguage.style.border="1px solid grey";
	
	elementLanguage.tabIndex=lanTab+cntL[row]+1;
	
	var elementOral = document.createElement("select");
	elementOral.id = "oral"+cntL[row];
	elementOral.name = cntL[row];
	elementOral.options[0] = new Option("Select","select");
	elementOral.options[1] = new Option("Excellent","Excellent");
	elementOral.options[2] = new Option("Good","Good");
	elementOral.options[3] = new Option("Workable","Workable");
	elementOral.style.width = '180px';
	elementOral.style.textAlign = 'left';
	elementOral.style.border="1px solid grey";
	elementOral.tabIndex=lanTab+cntL[row]+2;


	var elementWriting = document.createElement("select");
	elementWriting.id = "writing"+cntL[row];
	elementWriting.name = cntL[row];
	elementWriting.options[0] = new Option("Select","select");
	elementWriting.options[1] = new Option("Excellent","Excellent");
	elementWriting.options[2] = new Option("Good","Good");
	elementWriting.options[3] = new Option("Workable","Workable");
	elementWriting.style.width = '180px';
	elementWriting.style.textAlign = 'left';
	elementWriting.style.border="1px solid grey";
	elementWriting.tabIndex=lanTab+cntL[row]+3;
	

var elementDelete = document.createElement("a");
elementDelete.id = "delete_lan"+cntL[row];
elementDelete.href = "javascript:void(0)";
elementDelete.innerHTML = "<font size=2>Delete</font>";
elementDelete.value = cntL[row];
elementDelete.tabIndex=lanTab+cntL[row]+4;

if(window.addEventListener)
{              //DOM compliant

 elementDelete.addEventListener("click",function()
                                        {
                                          document.getElementById("language"+elementDelete.value).style.display = 'none';
                                          document.getElementById("delete_lan"+elementDelete.value).style.display = 'none';
                                          document.getElementById("lan_column1"+elementDelete.value).style.display = 'none';
                                          document.getElementById("lan_column2"+elementDelete.value).style.display = 'none';
                                          document.getElementById("lan_column3"+elementDelete.value).style.display = 'none';
                                          document.getElementById("lan_column4"+elementDelete.value).style.display = 'none';
                                          return false;
                                        },true);
 }
  else
     {           //IE6 standards compliant mode
     elementDelete.attachEvent("onclick",function()
                                         {
                                          document.getElementById("language"+elementDelete.value).style.display = 'none';
                                          document.getElementById("delete_lan"+elementDelete.value).style.display = 'none';
                                          document.getElementById("lan_column1"+elementDelete.value).style.display = 'none';
                                          document.getElementById("lan_column2"+elementDelete.value).style.display = 'none';                                          
                                          document.getElementById("lan_column3"+elementDelete.value).style.display = 'none';
                                          document.getElementById("lan_column4"+elementDelete.value).style.display = 'none';
                                          return false;
                                         },true);
     }



 table1 = document.getElementById("languageTable");
 table1.style.border = '0px';
 newRow = table1.insertRow(table1.rows.length);
 newRow.style.border = '0px';
 newCell = newRow.insertCell(0);
 newCell.style.border = '0px';
 newCell.appendChild(elementLanguage);
 newRow.id = "lan_column1"+cntL[row];

 
table2 = document.getElementById("oralTable");
table2.style.border = '0px';
newRow = table2.insertRow(table2.rows.length);
newRow.style.border = '0px';
newCell = newRow.insertCell(0);
newCell.style.border = '0px';
newCell.appendChild(elementOral);
newRow.id = "lan_column2"+cntL[row];
  
table3 = document.getElementById("writingTable");
table3.style.border = '0px';
newRow = table3.insertRow(table3.rows.length);
newRow.style.border = '0px';
newCell = newRow.insertCell(0);
newCell.style.border = '0px';
newCell.appendChild(elementWriting);
newRow.id = "lan_column3"+cntL[row];

table4 = document.getElementById("deleteLanguageTable");
table4.style.border = '0px';
newRow = table4.insertRow(table4.rows.length);
newRow.style.border = '0px';
newCell = newRow.insertCell(0);
newCell.style.border = '0px';

 
newCell.appendChild(elementDelete);
newRow.id = "lan_column4"+cntL[row];
 
 
 cntL[row]++;
// document.getElementById("languageNumber").value=cnt[row];
 return cntL[row];
 
}



function defaultLanguageLoad()
{
    row=0;
 
	var elementLanguage = document.createElement("select");
	elementLanguage.id = "language"+cntL[row];
	elementLanguage.name = cntL[row];
	
	elementLanguage.options[0] = new Option("Select","select");
	elementLanguage.options[1] = new Option("Abenaki","Abenaki");
	elementLanguage.options[2] = new Option("Arabic","Arabic");
	elementLanguage.options[3] = new Option("Aramaic","Aramaic");
	elementLanguage.options[4] = new Option("Ayapathu","Ayapathu");
	elementLanguage.options[5] = new Option("Balinese","Balinese");
	elementLanguage.options[6] = new Option("Bangla","Bangla");
	elementLanguage.options[7] = new Option("Bobangi","Bobangi");
	elementLanguage.options[8] = new Option("Bulgarian","Bulgarian");
	elementLanguage.options[9] = new Option("Burmese","Burmese");
	elementLanguage.options[10] = new Option("Catalan","Catalan");
	elementLanguage.options[11] = new Option("Chinese","Chinese");
	elementLanguage.options[12] = new Option("Chinook","Chinook");
	elementLanguage.options[13] = new Option("Cree","Cree");
	elementLanguage.options[14] = new Option("Creole","Creole");
	elementLanguage.options[15] = new Option("Czech","Czech");
	elementLanguage.options[16] = new Option("Danish","Danish");
	elementLanguage.options[17] = new Option("Dutch","Dutch");
	elementLanguage.options[18] = new Option("Eggon","Eggon");
	elementLanguage.options[19] = new Option("English","English");
	elementLanguage.options[20] = new Option("Farsi","Farsi");
	elementLanguage.options[21] = new Option("French","French");
	elementLanguage.options[22] = new Option("Gaelic","Gaelic");
	elementLanguage.options[23] = new Option("German","German");
	elementLanguage.options[24] = new Option("Gothic","Gothic");
	elementLanguage.options[25] = new Option("Greek","Greek");
	elementLanguage.options[26] = new Option("Haida","Haida");
	elementLanguage.options[27] = new Option("Hindi","Hindi");
	elementLanguage.options[28] = new Option("Hungarian","Hungarian");
	elementLanguage.options[29] = new Option("Indonesian","Indonesian");
	elementLanguage.options[30] = new Option("Italian","Italian");
	
	elementLanguage.options[31] = new Option("Jangshung","Jangshung");
	elementLanguage.options[32] = new Option("Japanese","Japanese");
	elementLanguage.options[33] = new Option("Konkani","Konkani");
	elementLanguage.options[34] = new Option("Korean","Korean");
	elementLanguage.options[35] = new Option("Koyo","Koyo");
	elementLanguage.options[36] = new Option("Latin","Latin");
	elementLanguage.options[37] = new Option("Malagasy","Malagasy");
	elementLanguage.options[38] = new Option("Malay","Malay");
	elementLanguage.options[39] = new Option("Malayalam","Malayalam");
	
	
	elementLanguage.options[40] = new Option("Mayan","Mayan");
	elementLanguage.options[41] = new Option("Nahuatl","Nahuatl");
	elementLanguage.options[42] = new Option("Nande","Nande");
	elementLanguage.options[43] = new Option("Pidgin","Pidgin");
	elementLanguage.options[44] = new Option("Pirah","Pirah");
	elementLanguage.options[45] = new Option("Polish","Polish");
	elementLanguage.options[46] = new Option("Prussian","Prussian");
	elementLanguage.options[47] = new Option("Quechua","Quechua");
	elementLanguage.options[48] = new Option("Romanian","Romanian");
	elementLanguage.options[49] = new Option("Russian","Russian");
	elementLanguage.options[50] = new Option("Saanich","Saanich");
	elementLanguage.options[51] = new Option("Sign","Sign");
	elementLanguage.options[52] = new Option("Spanish","Spanish");
	
	
	elementLanguage.options[53] = new Option("Swedish","Swedish");
	elementLanguage.options[54] = new Option("Tagalog","Tagalog");
	elementLanguage.options[55] = new Option("Tamil","Tamil");
	elementLanguage.options[56] = new Option("Thai","Thai");
	elementLanguage.options[57] = new Option("Urdu","Urdu");
	elementLanguage.options[58] = new Option("Venda","Venda");
	elementLanguage.options[59] = new Option("Vietnamese","Vietnamese");
	elementLanguage.options[60] = new Option("Wagiman","Wagiman");
    elementLanguage.options[61] = new Option("Others","Others");
    
	elementLanguage.style.width = '200px';
	elementLanguage.style.textAlign = 'left';
	elementLanguage.style.border="1px solid grey";
	elementLanguage.tabIndex=lanTab+cntL[row]+1;
	
	var elementOral = document.createElement("select");
	elementOral.id = "oral"+cntL[row];
	elementOral.name = cntL[row];
	elementOral.options[0] = new Option("Select","select");
	elementOral.options[1] = new Option("Excellent","Excellent");
	elementOral.options[2] = new Option("Good","Good");
	elementOral.options[3] = new Option("Workable","Workable");
	elementOral.style.width = '180px';
	elementOral.style.border="1px solid grey";
	elementOral.style.textAlign = 'left';
	elementOral.tabIndex=lanTab+cntL[row]+2;


	var elementWriting = document.createElement("select");
	elementWriting.id = "writing"+cntL[row];
	elementWriting.name = cntL[row];
	elementWriting.options[0] = new Option("Select","select");
	elementWriting.options[1] = new Option("Excellent","Excellent");
	elementWriting.options[2] = new Option("Good","Good");
	elementWriting.options[3] = new Option("Workable","Workable");
	elementWriting.style.width = '180px';
	elementWriting.style.border="1px solid grey";
	elementWriting.style.textAlign = 'left';
	elementWriting.tabIndex=lanTab+cntL[row]+3;
	
		
	var elementDelete = document.createElement("a");
	elementDelete.id = "delete_lan"+cntL[row];
	elementDelete.href = "javascript:void(0)";
	elementDelete.innerHTML = "<font size=2>Delete</font>";
	elementDelete.value = cntL[row];
	elementDelete.tabIndex=lanTab+cntL[row]+4;
	
	if(window.addEventListener)
	{              //DOM compliant
	
	 elementDelete.addEventListener("click",function()
	                                        {
	                                          document.getElementById("language"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("delete_lan"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("lan_column1"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("lan_column2"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("lan_column3"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("lan_column4"+elementDelete.value).style.display = 'none';
	                                          return false;
	                                        },true);
	 }
	  else
	     {           //IE6 standards compliant mode
	     elementDelete.attachEvent("onclick",function()
	                                         {
	                                          document.getElementById("language"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("delete_lan"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("lan_column1"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("lan_column2"+elementDelete.value).style.display = 'none';                                          
	                                          document.getElementById("lan_column3"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("lan_column4"+elementDelete.value).style.display = 'none';
	                                          return false;
	                                         },true);
	     }
	
		
	

	table1 = document.getElementById("languageTable");
	table1.style.border = '0px';
	newRow = table1.insertRow(table1.rows.length);
	newRow.style.border = '0px';
	newCell = newRow.insertCell(0);
	newCell.style.border = '0px';
	newCell.appendChild(elementLanguage);
	newRow.id = "lan_column1"+cntL[row];

	 
	table2 = document.getElementById("oralTable");
	table2.style.border = '0px';
	newRow = table2.insertRow(table2.rows.length);
	newRow.style.border = '0px';
	newCell = newRow.insertCell(0);
	newCell.style.border = '0px';
	newCell.appendChild(elementOral);
	newRow.id = "lan_column2"+cntL[row];
	  
	table3 = document.getElementById("writingTable");
	table3.style.border = '0px';
	newRow = table3.insertRow(table3.rows.length);
	newRow.style.border = '0px';
	newCell = newRow.insertCell(0);
	newCell.style.border = '0px';
	newCell.appendChild(elementWriting);
	newRow.id = "lan_column3"+cntL[row];
	
	table4 = document.getElementById("deleteLanguageTable");
	table4.style.border = '0px';
	newRow = table4.insertRow(table4.rows.length);
	newRow.style.border = '0px';
	newCell = newRow.insertCell(0);
	newCell.style.border = '0px';
	
	 
	newCell.appendChild(elementDelete);
	newRow.id = "lan_column4"+cntL[row];
	 
	 
	 cntL[row]++;
	// document.getElementById("languageNumber").value=cnt[row];
	 return cntL[row];
 
}


function defaultTrainingLoad()
{
    row=0;
 
 
	var elementTrainingName = document.createElement("input");
	elementTrainingName.id = "trainingName"+cntT[row];
	elementTrainingName.style.width = '120px';
	elementTrainingName.setAttribute('maxlength',40);
	elementTrainingName.style.border="1px solid grey";
	elementTrainingName.name = cntT[row];
	elementTrainingName.tabIndex=trainTab+cntT[row]+1;
	
	var elementTrainingFrom = document.createElement("input");
	elementTrainingFrom.id = "trainingFrom"+cntT[row];
	elementTrainingFrom.style.width = '100px';
	elementTrainingFrom.setAttribute('maxlength',80);
	elementTrainingFrom.style.border="1px solid grey";
	elementTrainingFrom.name = cntT[row];
	elementTrainingFrom.tabIndex=trainTab+cntT[row]+2;
	
	var elementTrainingDuration = document.createElement("input");
	elementTrainingDuration.id = "trainingDuration"+cntT[row];
	elementTrainingDuration.style.width = '80px';
	elementTrainingDuration.setAttribute('maxlength',10);
	elementTrainingDuration.style.border="1px solid grey";
	elementTrainingDuration.name = cntT[row];
	elementTrainingDuration.tabIndex=trainTab+cntT[row]+3;
	
	var elementTrainingDesc = document.createElement("input");
	elementTrainingDesc.id = "trainingDesc"+cntT[row];
	elementTrainingDesc.style.width = '300px';
	elementTrainingDesc.style.border="1px solid grey";
	elementTrainingDesc.setAttribute('maxlength',200);
	elementTrainingDesc.name = cntT[row];
	elementTrainingDesc.tabIndex=trainTab+cntT[row]+4;
	
	var elementDelete = document.createElement("a");
	elementDelete.id = "train_delete"+cntT[row];
	elementDelete.href = "javascript:void(0)";
	elementDelete.innerHTML = "<font size=2>Delete</font>";
	elementDelete.value = cntT[row];
	elementDelete.tabIndex=trainTab+cntT[row]+5;

	
	if(window.addEventListener)
	{              //DOM compliant
	
	 elementDelete.addEventListener("click",function()
	                                        {
	                                          document.getElementById("trainingName"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("train_delete"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("train_column1"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("train_column2"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("train_column3"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("train_column4"+elementDelete.value).style.display = 'none';
	                                          return false;
	                                        },true);
	 }
	  else
	     {           //IE6 standards compliant mode
	     elementDelete.attachEvent("onclick",function()
	                                         {
	                                          document.getElementById("trainingName"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("train_delete"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("train_column1"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("train_column2"+elementDelete.value).style.display = 'none';                                          
	                                          document.getElementById("train_column3"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("train_column4"+elementDelete.value).style.display = 'none';
	                                          return false;
	                                         },true);
	     }
	
		
	

	table1 = document.getElementById("trainingNameTable");
	table1.style.border = '0px';
	newRow = table1.insertRow(table1.rows.length);
	newRow.style.border = '0px';
	newCell = newRow.insertCell(0);
	newCell.style.border = '0px';
	newCell.appendChild(elementTrainingName);
	newRow.id = "train_column1"+cntT[row];

//  alert(document.getElementById("trainingName0"));
	 
	table2 = document.getElementById("trainingFromTable");
	table2.style.border = '0px';
	newRow = table2.insertRow(table2.rows.length);
	newRow.style.border = '0px';
	newCell = newRow.insertCell(0);
	newCell.style.border = '0px';
	newCell.appendChild(elementTrainingFrom);
	newRow.id = "train_column2"+cntT[row];
	  
	table3 = document.getElementById("trainingDurationTable");
	table3.style.border = '0px';
	newRow = table3.insertRow(table3.rows.length);
	newRow.style.border = '0px';
	newCell = newRow.insertCell(0);
	newCell.style.border = '0px';
	newCell.appendChild(elementTrainingDuration);
	newRow.id = "train_column3"+cntT[row];
	
	table4 = document.getElementById("trainingDescTable");
	table4.style.border = '0px';
	newRow = table4.insertRow(table4.rows.length);
	newRow.style.border = '0px';
	newCell = newRow.insertCell(0);
	newCell.style.border = '0px';
	newCell.appendChild(elementTrainingDesc);
	newRow.id = "train_column4"+cntT[row];
	

	table5 = document.getElementById("deleteTrainingTable");
	table5.style.border = '0px';
	newRow = table5.insertRow(table5.rows.length);
	newRow.style.border = '0px';
	newCell = newRow.insertCell(0);
	newCell.style.border = '0px';
	newCell.style.textAlign = 'center';
	
	 
	newCell.appendChild(elementDelete);
	newRow.id = "train_column5"+cntT[row];
	 
	 
	 cntT[row]++;
	// document.getElementById("languageNumber").value=cnt[row];
	 return cntT[row];
 
}

function addMoreTraining()
{
    row=0;
 
 
	var elementTrainingName = document.createElement("input");
	elementTrainingName.id = "trainingName"+cntT[row];
	elementTrainingName.style.width = '120px';
	elementTrainingName.setAttribute('maxlength',100);
	elementTrainingName.style.border="1px solid grey";
	elementTrainingName.name = cntT[row];
	elementTrainingName.tabIndex=trainTab+cntT[row]+1;
	
	var elementTrainingFrom = document.createElement("input");
	elementTrainingFrom.id = "trainingFrom"+cntT[row];
	elementTrainingFrom.style.width = '100px';
	elementTrainingFrom.setAttribute('maxlength',100);
	elementTrainingFrom.style.border="1px solid grey";
	elementTrainingFrom.name = cntT[row];
	elementTrainingFrom.tabIndex=trainTab+cntT[row]+2;
	
	var elementTrainingDuration = document.createElement("input");
	elementTrainingDuration.id = "trainingDuration"+cntT[row];
	elementTrainingDuration.style.width = '80px';
	elementTrainingDuration.setAttribute('maxlength',10);
	elementTrainingDuration.style.border="1px solid grey";
	elementTrainingDuration.name = cntT[row];
	elementTrainingDuration.tabIndex=trainTab+cntT[row]+3;
	
	var elementTrainingDesc = document.createElement("input");
	elementTrainingDesc.id = "trainingDesc"+cntT[row];
	elementTrainingDesc.style.width = '300px';
	elementTrainingDesc.setAttribute('maxlength',150);
	elementTrainingDesc.style.border="1px solid grey";
	elementTrainingDesc.name = cntT[row];
	elementTrainingDesc.tabIndex=trainTab+cntT[row]+4;
	

	
	var elementDelete = document.createElement("a");
	elementDelete.id = "train_delete"+cntT[row];
	elementDelete.href = "javascript:void(0)";
	elementDelete.innerHTML = "<font size=2>Delete</font>";
	elementDelete.value = cntT[row];
	elementDelete.tabIndex=trainTab+cntT[row]+5;


	if(window.addEventListener)
	{              //DOM compliant
	
	 elementDelete.addEventListener("click",function()
	                                        {
	                                          document.getElementById("trainingName"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("train_delete"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("train_column1"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("train_column2"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("train_column3"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("train_column4"+elementDelete.value).style.display = 'none';
	                                          return false;
	                                        },true);
	 }
	  else
	     {           //IE6 standards compliant mode
	     elementDelete.attachEvent("onclick",function()
	                                         {
	                                          document.getElementById("trainingName"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("train_delete"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("train_column1"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("train_column2"+elementDelete.value).style.display = 'none';                                          
	                                          document.getElementById("train_column3"+elementDelete.value).style.display = 'none';
	                                          document.getElementById("train_column4"+elementDelete.value).style.display = 'none';
	                                          return false;
	                                         },true);
	     }
	
		
	

	table1 = document.getElementById("trainingNameTable");
	table1.style.border = '0px';
	newRow = table1.insertRow(table1.rows.length);
	newRow.style.border = '0px';
	newCell = newRow.insertCell(0);
	newCell.style.border = '0px';
	newCell.appendChild(elementTrainingName);
	newRow.id = "train_column1"+cntT[row];

//  alert(document.getElementById("trainingName0"));
	 
	table2 = document.getElementById("trainingFromTable");
	table2.style.border = '0px';
	newRow = table2.insertRow(table2.rows.length);
	newRow.style.border = '0px';
	newCell = newRow.insertCell(0);
	newCell.style.border = '0px';
	newCell.appendChild(elementTrainingFrom);
	newRow.id = "train_column2"+cntT[row];
	  
	table3 = document.getElementById("trainingDurationTable");
	table3.style.border = '0px';
	newRow = table3.insertRow(table3.rows.length);
	newRow.style.border = '0px';
	newCell = newRow.insertCell(0);
	newCell.style.border = '0px';
	newCell.appendChild(elementTrainingDuration);
	newRow.id = "train_column3"+cntT[row];
	
	table4 = document.getElementById("trainingDescTable");
	table4.style.border = '0px';
	newRow = table4.insertRow(table4.rows.length);
	newRow.style.border = '0px';
	newCell = newRow.insertCell(0);
	newCell.style.border = '0px';
	newCell.appendChild(elementTrainingDesc);
	newRow.id = "train_column4"+cntT[row];
	

	table5 = document.getElementById("deleteTrainingTable");
	table5.style.border = '0px';
	newRow = table5.insertRow(table5.rows.length);
	newRow.style.border = '0px';
	newCell = newRow.insertCell(0);
	newCell.style.border = '0px';
	newCell.style.textAlign = 'center';
	newCell.appendChild(elementDelete);
	newRow.id = "train_column5"+cntT[row];

	 
	 
	cntT[row]++;
	return cntT[row];
 
}


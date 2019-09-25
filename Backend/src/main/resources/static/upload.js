//filebrowser voor bootstrap
var tabellengte = 1 //intal voor eerste regel tabel en daarna voor volgende regels
var data2 //data voor tabel
var b //regel teller
var allesOk = false //boolean voor alles oke
var urlsendorcheck = 'http://localhost:8080/checkdata'
var max = true //volledig upload false is 4 kolomen
var gekozenvalue = '' //scheidingsteken

//kies upload 4 regels of 10
function Selectedsoortupload(){
//function GetSelectedValue(){
				var e = document.getElementById("Selectedsoortupload");
				var z = e.options[e.selectedIndex].value;
        console.log(z)
				max = JSON.parse(z); //maak van stirng boolean
				if(max == false){
// $('#tabel').html('<form id="updatetabel" class= "" action="isdfndex.html" method="post"><table id="datatabel"><tr id="tr"><div class="grid-container2"><div><input class="form-data" type="text" value="socialsecurity_id" readonly="readonly"> </div><div><input class="form-data" type="text" value="employer_id" readonly="readonly"></div><div><input class="form-data" type="text" value="invoice_id" readonly="readonly"> </div><div><input class="form-data" type="text" value="salary" readonly="readonly"> </div><div><input class="form-data" type="text" value="parttime_factor" readonly="readonly"> </div><div></div></tr></table></form>')

$('#tabel').html('<form id="updatetabel" class= "grid-container6" action="isdfndex.html" method="post"><table id="datatabel"><tr id="tr"><class="grid-container6"><input class="form-data" type="text" value="socialsecurity_id" readonly="readonly"><input class="form-data" type="text" value="employer_id" readonly="readonly"><input class="form-data" type="text" value="invoice_id" readonly="readonly"><input class="form-data" type="text" value="salary" readonly="readonly"><input class="form-data" type="text" value="parttime_factor" readonly="readonly"></tr></table></form>')

				}else{
					$('#tabel').html('<form id="updatetabel" class= "grid-container6" action="isdfndex.html" method="post"><table id="datatabel"><tr id="tr"><class="grid-container6"><input class="form-data" type="text" value="socialsecurity_id" readonly="readonly"><input class="form-data" type="text" value="employer_id" readonly="readonly"><input class="form-data" type="text" value="invoice_id" readonly="readonly"><input class="form-data" type="text" value="salary" readonly="readonly"><input class="form-data" type="text" value="parttime_factor" readonly="readonly"><input class="form-data" type="text" value="first_name" readonly="readonly"><input class="form-data" type="text" value="last_name" readonly="readonly"><input class="form-data" type="text" value="date_of_birth" readonly="readonly"><input class="form-data" type="text" value="status" readonly="readonly"><input class="form-data" type="text" value="gender" readonly="readonly"><input class="form-data"  type="text" value="adress_id" readonly="readonly"><input class="form-data" type="text" value="communication_type" readonly="readonly"><input class="form-data" type="text" value="hire_date" readonly="readonly"></tr></table></form>')
}
// 					$('#tabel').html('<form id="updatetabel" class= "" action="isdfndex.html" method="post"><table id="datatabel"><tr id="tr"><div class="grid-container2"><div><input class="form-data" type="text" value="socialsecurity_id" readonly="readonly"> </div><div><input class="form-data" type="text" value="employer_id" readonly="readonly"></div><div><input class="form-data" type="text" value="invoice_id" readonly="readonly"> </div><div><input class="form-data" type="text" value="salary" readonly="readonly"> </div><div><input class="form-data" type="text" value="parttime_factor" readonly="readonly"> </div><div><input class="form-data" type="text" value="first_name" readonly="readonly"> </div><div><input class="form-data" type="text" value="last_name" readonly="readonly"> </div><div><input class="form-data" type="text" value="date_of_birth" readonly="readonly"> </div><div><input class="form-data" type="text" value="status" readonly="readonly"> </div><div><input class="form-data" type="text" value="gender" readonly="readonly"> </div><div><input class="form-data"  type="text" value="adress_id" readonly="readonly"> </div><div><input class="form-data" type="text" value="communication_type" readonly="readonly"> </div><div><input class="form-data" type="text" value="hire_date" readonly="readonly"> </div><div></div></tr></table></form>')
// }

}

//get scheidingsteken
function GetSelectedValue(){
//function GetSelectedValue(){
				var e = document.getElementById("ScheidingstekenGekozen1");
				var x = e.options[e.selectedIndex].value;
        console.log(x)
gekozenvalue = x;

  switch(x) {
    case "1":
      var z = "tap"
      break;
    case "2":
      var z = "komma"
      break;
    case "3":
      var z = "punt"
      break;
    case "4":
      var z = "Dubbelepunt"
      break;
    case "5":
      var z = "Puntkomma"
      break;
    case "6":
      var z = "spatie"
      break;
    default:
      var z = "geen keuze gemaakt"
}
//alert op scherm tonen
$('#ScheidingstekenGekozen').html('<div class="alert alert-success alert-dismissible"><a href="#"class="close" data-dismiss="alert" aria-label="close">&times;</a>Type gekozen keuze is: ' + z + '<button type="button" class="close" data-dismiss="alert" aria-label="Close"></div>');
//});
// });
}
//document ophalen van gebruiker
function uploadFormData(){
var oMyForm = new FormData();
oMyForm.append("file", inputGroupFile01.files[0]);
//haal document naam op:
var tekst = document.getElementById("inputGroupFile01").value //get file path
var pos1 = tekst.indexOf("\\");          	// zoek eerste /
var pos2 = tekst.indexOf("\\", pos1 + 1); // zoek 2e /
var len = tekst.length; //pak lengte string
var last = tekst.substring(pos2 +1, len) //haal de tekst na de 2e / op.
if (allesOk == true) {
$('#uploadCompleteAlert').html('')
if(max == true){
urlsendorcheck = 'http://localhost:8080/checkdata'
}else{
urlsendorcheck = 'http://localhost:8080/salarismutatiecheck'
}
allesOk = false} //bij nieuwe uplaod knop weer origineel
//document en scheidingsteken en filename versturen naar backend
console.log("wat is max " + max)
  $.ajax({
    url: 'http://localhost:8080/upload?Scheidingsteken=' + gekozenvalue + "&filename=" + last,
    data: oMyForm,
    dataType: 'text',
    processData: false,
    contentType: false,
    type: 'POST',
    success: function(data){

//gooi oude tabel weg
    var x = document.getElementsByTagName("tr");
    for (var i = tabellengte; i < x.length ;i++) {
      document.getElementById("datatabel").deleteRow(i);
   }
   tabellengte =  x.length

    MakeTabel()
  }
});
}
    //functie om data uit json in table te zetten
function MakeTabel() {

//retour data op scherm in tabel zetten
  var q = 0 //begin bij 0
if(max == true){
  var r = 13 //r = aantal kolomen
}else {
  var r = 5
}
  var aantalregels = (data2.length / r);  //delen door aantal kolomen
	console.log(data2.length)
  console.log(aantalregels)
  //loop door een  regel
    for (b = 0; b < aantalregels; b++) {
      var tabel = "";
    //loop door items van een  regel
        for (var l = q; l < r; l++) {
  var colom = '<td id="test"><input id="' + data2[l].type + b + '" type="text" name="' + data2[l].goedfout + '"value="' + data2[l].waarde + '"span title="' + data2[l].format + '"></td>'
          var tabel = tabel + colom  //maak van cellen een regel
        }
      var $newListItem = $('<tr>' + tabel + '<tr>'); $('tr:last').after($newListItem);   //zet de regels in tabel..
      $newListItem.show();
    var q = l;  //waarde voor de volgende regel
		if(max == true){
    var r = r + 13; //waarde voor volgene regel
	}else {
		var r = r + 5
	}
    }
//toevoegen van knopje rechts onder de tabel
//checkalles oke voor juiste knopje
checkAllesOke()
if(max == true){
if (allesOk == false) {
    var knopje = '<td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td><div class="knopje">  <button class="btn btn-outline-secondary" type="button" onclick="checkFormData()" id="checkformdata">Checkdata</button></div></td>'
  } else {
    var knopje = '<td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td><div class="knopje">  <button class="btn btn-outline-secondary" type="button" onclick="checkFormData()" id="checkformdata">Verstuurdata</button></div></td>'
  }
}
  if(max == false){
  if (allesOk == false) {
  var knopje = '<td></td><td></td><td></td><td></td><td><div class="knopje">  <button class="btn btn-outline-secondary" type="button" onclick="checkFormData()" id="checkformdata">Checkdata</button></div></td>'
}else{
  var knopje = '<td></td><td></td><td></td><td></td><td><div class="knopje">  <button class="btn btn-outline-secondary" type="button" onclick="checkFormData()" id="checkformdata">Verstuurdata</button></div></td>'
}
}

    var $newListItem = $('<tr>' + knopje + '<tr>'); $('tr:last').after($newListItem);   //zet de regels in tabel.
    var $newListItem = "";
}
//aangepast te data uitlezen
function checkFormData(){
//  var input = document.getElementById('socialsecurity_id');
var datarows =[]
     for (var c = 0; c < b; c++) {
 let arr1 = $('#socialsecurity_id' + c.toString()).serializeArray()
 let cel1 = arr1[0].value
  let arr2 = $("#employer_id" + c.toString()).serializeArray()
    let cel2 = arr2[0].value
    let arr3 = $( "#invoice_id"  + c.toString()).serializeArray()
      let cel3 = arr3[0].value
      let arr4 = $( "#salary"  + c.toString()).serializeArray()
      let cel4 = arr4[0].value
			let arr5 = $( "#parttime_factor"  + c.toString()).serializeArray()
			let cel5 = arr5[0].value
      if(max == true){
				let arr6 = $( "#first_name"  + c.toString()).serializeArray()
					let cel6 = arr6[0].value
					let arr7 = $( "#last_name"  + c.toString()).serializeArray()
					let cel7 = arr7[0].value
        let arr8 = $( "#date_of_birth"  + c.toString()).serializeArray()
          let cel8 = arr8[0].value
          let arr9 = $( "#status"  + c.toString()).serializeArray()
            let cel9 = arr9[0].value
            let arr10 = $( "#gender"  + c.toString()).serializeArray()
              let cel10 = arr10[0].value
              let arr11 = $( "#adress_id"  + c.toString()).serializeArray()
              let cel11 = arr11[0].value
                let arr12 = $( "#communication_type"  + c.toString()).serializeArray()
                  let cel12 = arr12[0].value
                  let arr13 = $( "#hire_date"  + c.toString()).serializeArray()
                    let cel13 = arr13[0].value
var row = cel1 + ";" + cel2 + ";" + cel3 + ";" + cel4 + ";" + cel5 + ";" + cel6 + ";" + cel7 + ";" + cel8 + ";" + cel9 + ";" + cel10 + ";" + cel11 + ";" + cel12 + ";" + cel13
}else{
var row = cel1 + ";" + cel2 + ";" + cel3 + ";" + cel4 + ";" + cel5
}

var rowtrim = row.trim()
datarows.push(rowtrim)
}
final = datarows
//data verzenden naar backend
 $.ajax({
     url: urlsendorcheck,
     data: final,
     dataType: 'text',
     processData: false,
     contentType: false,
     type: 'POST',
     //2 extra opties als test die haal ik hederstatus op nog uitzoeken
     success: function(data, textStatus, xhr) {
        console.log("xhr " + xhr.getResponseHeader);
  console.log("tekst status " + textStatus);
    console.log("data status " + data);
    //einde test
     data2 = JSON.parse(data);
     var x = document.getElementsByTagName("tr");
     for (var i = tabellengte; i < x.length ;i++) {
       document.getElementById("datatabel").deleteRow(i);
    }
    tabellengte =  x.length

       //if dat is 200 dan doorgaan anders 202 melding on screen nog maken
			  var eerstedata = data.substring(0, 3)
				 var aantal_wijzigingen = data.substring(3, 9)
				 console.log(eerstedata + " eerstedata");
				 console.log(aantal_wijzigingen + " aantal_wijzigingen");
    if (eerstedata == "202"){
      console.log(" door if statument")
      $('#uploadCompleteAlert').html('<div class="alert alert-success alert-dismissible"><a href="#"class="close" data-dismiss="alert" aria-label="close">&times;</a>Upload gelukt aantal ingevoerde regels = ' + aantal_wijzigingen + '<button type="button" class="close" data-dismiss="alert" aria-label="Close"></div>');
    }else{
MakeTabel()
}
  }
});
}

function checkAllesOke() {
  var allestrue = $("input[name='false']").val();
console.log(allestrue)
    if(max == true){
  if (allestrue == undefined) {
    allesOk = true
    urlsendorcheck = 'http://localhost:8080/senddata'
  }else{

  }
}else {
  if (allestrue == undefined) {
    allesOk = true
    urlsendorcheck = 'http://localhost:8080/verstuursalarismutatiecheck'
}
  console.log(allesOk)
}
}
///Sv-loon en parttimefactor.


//socialsecurity_id 	double
//employer_id					double
	//invoice_id    		double

//salary							floot
//parttime_factor 	 floot

//first_name     			String
//last_name					 String
//date_of_birth  			Date
//status							String
//gender							String
//adress_id						int
//communication_type	String
//hire_date  						 Date

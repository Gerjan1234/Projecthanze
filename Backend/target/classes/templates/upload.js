//filebrowser voor bootstrap
var tabellengte = 1 //intal voor eerste regel tabel en daarna voor volgende regels
var data2 //data voor tabel
var b //regel teller
var allesOk = false //boolean voor alles oke
var urlsendorcheck = 'http://localhost:8080/checkdata'
var max = true //volledig upload false is 4 kolomen
<<<<<<< HEAD
=======

>>>>>>> 04f5bbd4227a242e2d48f0c4793dc6609ff0475b

//
// $(document).ready(function(){
//   $(document).on('click', '.dropdown-menu', function() {
// var e = document.getElementById("demolistx").value;
// console.log("demolist " + e)
//
//    console.log($('#demolist > li > a:selected').html())
//
//    var e = document.getElementById("demolistx");
// 	var result = e.options[e.selectedIndex].value;
// 	alert(result); //ID002

//
// });
// });
function GetSelectedValue(){
//function GetSelectedValue(){
				var e = document.getElementById("ScheidingstekenGekozen1");
				var x = e.options[e.selectedIndex].value;
        console.log(x)

//        $('#ScheidingstekenGekozen').html('<div class="alert alert-success alert-dismissible"><a href="#"class="close" data-dismiss="alert" aria-label="close">&times;</a>Type gekozen keuze is: ' + result + '<button type="button" class="close" data-dismiss="alert" aria-label="Close"></div>');




// $(document).ready(function(){
//   bsCustomFileInput.init()  //file upload knopje
//   //radio buttton uitlezen
// $('input[type=radio]').click(function(){
//var x = (this.value);
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
//get gekozen scheidingsteken voor de backend
function uploadFormData(){
    $('#result').html('');
    var gekozenvalue = ''
      var ele = document.getElementsByClassName('form-check-input');
      for(i = 0; i < ele.length; i++) {
         if(ele[i].checked)
            gekozenvalue = ele[i].value
}
//document ophalen van gebruiker
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
  $.ajax({
    url: 'http://localhost:8080/upload?Scheidingsteken=' + gekozenvalue + "&filename=" + last,
    data: oMyForm,
    dataType: 'text',
    processData: false,
    contentType: false,
    type: 'POST',
    success: function(data){
    data2 = JSON.parse(data);
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
  var r = 10 //r = aantal kolomen
}else {
  var r = 4
}
  var aantalregels = (data2.length / r);  //delen door aantal kolomen
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
    var r = r + 10; //waarde voor volgene regel
    }
//toevoegen van knopje rechts onder de tabel
//checkalles oke voor juiste knopje
checkAllesOke()
if(max == true){
if (allesOk == false) {
    var knopje = '<td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td><div class="knopje">  <button class="btn btn-outline-secondary" type="button" onclick="checkFormData()" id="checkformdata">Checkdata</button></div></td>'
  } else {
    var knopje = '<td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td><div class="knopje">  <button class="btn btn-outline-secondary" type="button" onclick="checkFormData()" id="checkformdata">Verstuurdata</button></div></td>'
  }
}
  if(max == false){
  if (allesOk == false) {
  var knopje = '<td></td><td></td><td></td><td><div class="knopje">  <button class="btn btn-outline-secondary" type="button" onclick="checkFormData()" id="checkformdata">Checkdata</button></div></td>'
}else{
  var knopje = '<td></td><td></td><td></td><td><div class="knopje">  <button class="btn btn-outline-secondary" type="button" onclick="checkFormData()" id="checkformdata">Verstuurdata</button></div></td>'
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
    let arr3 = $( "#first_name"  + c.toString()).serializeArray()
      let cel3 = arr3[0].value
      let arr4 = $( "#last_name"  + c.toString()).serializeArray()
      let cel4 = arr4[0].value
      if(max == true){
        let arr5 = $( "#date_of_birth"  + c.toString()).serializeArray()
          let cel5 = arr5[0].value
          let arr6 = $( "#status"  + c.toString()).serializeArray()
            let cel6 = arr6[0].value
            let arr7 = $( "#gender"  + c.toString()).serializeArray()
              let cel7 = arr7[0].value
              let arr8 = $( "#adress_id"  + c.toString()).serializeArray()
              let cel8 = arr8[0].value
                let arr9 = $( "#communication_type"  + c.toString()).serializeArray()
                  let cel9 = arr9[0].value
                  let arr10 = $( "#hire_date"  + c.toString()).serializeArray()
                    let cel10 = arr10[0].value
var row = cel1 + ";" + cel2 + ";" + cel3 + ";" + cel4 + ";" + cel5 + ";" + cel6 + ";" + cel7 + ";" + cel8 + ";" + cel9 + ";" + cel10
}else{
var row = cel1 + ";" + cel2 + ";" + cel3 + ";" + cel4
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
    if (data == "202"){
      console.log(" door if statument")
      $('#uploadCompleteAlert').html('<div class="alert alert-success alert-dismissible"><a href="#"class="close" data-dismiss="alert" aria-label="close">&times;</a>Upload gelukt<button type="button" class="close" data-dismiss="alert" aria-label="Close"></div>');
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

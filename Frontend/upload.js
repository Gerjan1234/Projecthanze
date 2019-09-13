//filebrowser voor bootstrap

//testje

//
var tabellengte = 1
var data2
var b
$(document).ready(function(){
  bsCustomFileInput.init()  //file upload knopje
  //radio buttton uitlezen
$('input[type=radio]').click(function(){
  var x = (this.value);
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
$('#result1').html('<div class="alert alert-success alert-dismissible"><a href="#"class="close" data-dismiss="alert" aria-label="close">&times;</a>Type gekozen keuze is: ' + z + '<button type="button" class="close" data-dismiss="alert" aria-label="Close"></div>');
});
});

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
    //data parameters zetten
    MakeTabel()
  }
});
}

function MakeTabel() {

//retour data op scherm in tabel zetten
  var q = 0 //begin bij 0
  var r = 10 //r = aantal kolomen
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
    //  var $newListItem = "";
    var q = l;  //waarde voor de volgende regel
    var r = r + 10; //waarde voor volgene regel
    }
//toevoegen van knopje rechts onder de tabel
    var knopje = '<td>  <input type="text" name="dbFlag" value="N" /></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td><div class="knopje">  <button class="btn btn-outline-secondary" type="button" onclick="checkFormData()" id="checkformdata">Checkdata</button></div></td>'
    var $newListItem = $('<tr>' + knopje + '<tr>'); $('tr:last').after($newListItem);   //zet de regels in tabel.
    var $newListItem = "";
}




//aangepast te data verzenden naar backend.
function checkFormData(){
  var input = document.getElementById('socialsecurity_id');
//console.log(input.id);
//console.log(input.value);
  //console.log(form.input[name="true"])

 //$('#updatetabel').on('change', 'input, select, textarea', function(){
//      $('#updatetabel').each('input, select, textarea', function(){
//console.log(this.value)
    //  $(this).closest('tr').find('input[name="dbFlag"]').val(this);
//  });
var datarows =[]
var data = []
var arrtest = []
var arrs = []
var res = {}
var res1 = {}
var res2 = []
var res3 = []
var arr11 = []
var arr12 = []
var arr15 =[];
     for (var c = 0; c < b; c++) {
 let arr1 = $('#socialsecurity_id' + c.toString()).serializeArray()

 var vrr1 = res3['"socialsecurity_id"'] = arr1[0].value
 var vrrr1 = "\"socialsecurity_id\":\"" + vrr1 + "\""
  let arr2 = $("#employer_id" + c.toString()).serializeArray()
 var vrr2 = res3['"employer_id"'] = arr2[0].value
 var vrrr2 = "\"employer_id\":\"" + vrr2 + "\""
    let arr3 = $( "#first_name"  + c.toString()).serializeArray()
    var vrr3 = res3['"first_name"'] = arr3[0].value
    var vrrr3 = "\"first_name\":\"" + vrr3 + "\""
      let arr4 = $( "#last_name"  + c.toString()).serializeArray()
      var vrr4 = res3['"last_name"'] = arr4[0].value
      var vrrr4 = "\"last_name\":\"" + vrr4 + "\""
        let arr5 = $( "#date_of_birth"  + c.toString()).serializeArray()
        var vrr5 = res3['"date_of_birth"'] = arr5[0].value
        var vrrr5 = "\"date_of_birth\":\"" + vrr5 + "\""
          let arr6 = $( "#status"  + c.toString()).serializeArray()
          var vrr6 = res3['"status"'] = arr6[0].value
          var vrrr6 = "\"status\":\"" + vrr6 + "\""
            let arr7 = $( "#gender"  + c.toString()).serializeArray()
            var vrr7 = res3['"gender"'] = arr7[0].value
            var vrrr7 = "\"gender\":\"" + vrr7 + "\""
              let arr8 = $( "#adress_id"  + c.toString()).serializeArray()
              var vrr8 = res3['"adress_id"'] = arr8[0].value
              var vrrr8 = "\"adress_id\":\"" + vrr8 + "\""
                let arr9 = $( "#communication_type"  + c.toString()).serializeArray()
                var vrr9 = res3['"communication_type"'] = arr9[0].value
                var vrrr9 = "\"communication_type\":\"" + vrr9 + "\""
                  let arr10 = $( "#hire_date"  + c.toString()).serializeArray()
                  var vrr10 = res3['"hire_date"'] = arr10[0].value
                  var vrrr10 = "\"hire_date\":\"" + vrr10 + "\""

let cel1 = arr1[0].value
let cel2 = arr2[0].value
let cel3 = arr3[0].value
let cel4 = arr4[0].value
let cel5 = arr5[0].value
let cel6 = arr6[0].value
let cel7 = arr7[0].value
let cel8 = arr8[0].value
let cel9 = arr9[0].value
let cel10 = arr10[0].value

var row = cel1 + ";" + cel2 + ";" + cel3 + ";" + cel4 + ";" + cel5 + ";" + cel6 + ";" + cel7 + ";" + cel8 + ";" + cel9 + ";" + cel10
var rowtrim = row.trim()
datarows.push(rowtrim)

arrtest = [vrrr1,vrrr2,vrrr3,vrrr4,vrrr5,vrrr6,vrrr7,vrrr8,vrrr9,vrrr10]
data.push(arrtest)
//console.log(arrtest)
    // arr1.forEach ( (el, idx) => res['socialsecurity_id' + c.toString()] = el.value)
    // console.log(res)
    // arr2.forEach ( (el, idx) => res["employer_id" + c.toString()]  = el.value)
    // arr3.forEach ( (el, idx) => res["first_name" + c.toString()]  = el.value)
    // arr4.forEach ( (el, idx) => res["last_name" + c.toString()]  = el.value)
    // arr5.forEach ( (el, idx) => res["date_of_birth" + c.toString()]  = el.value)
    // arr6.forEach ( (el, idx) => res["status" + c.toString()]  = el.value)
    // arr7.forEach ( (el, idx) => res["gender" + c.toString()]  = el.value)
    // arr8.forEach ( (el, idx) => res["adress_id" + c.toString()]  = el.value)
    // arr9.forEach ( (el, idx) => res["communication_type" + c.toString()]  = el.value)
    // arr10.forEach ( (el, idx) => res["hire_date" + c.toString()]  = el.value)
    // arr11 = [res]
//  console.log(arr11)

}

//final = "{" + data + "}"
final = datarows
 console.log("loopt het script")
 //data3 = JSON.stringify(arrtest)
 console.log(final)
   //data = JSON.stringify(res)
   //console.log(data)

   // $.ajax({
   //   data: final,
   //   dataType: 'text',
   //   processData: false,
   //   contentType: false,
   //  type:'post',
   //  dataType:'json',
   //  contentType: 'application/json;charset=utf-8',
   // url:'http://localhost:8080/checkdata',
   // success: function(dataxx){
   // var data2 = JSON.parse(dataxx);

   $.ajax({
     url: 'http://localhost:8080/checkdata',
     data: final,
     dataType: 'text',
     processData: false,
     contentType: false,
     type: 'POST',
     success: function(data){
     data2 = JSON.parse(data);

//$('#datatabel').remove();



     var x = document.getElementsByTagName("tr");
    //   document.getElementById("datatabel").innerHTML = "";

    console.log(x.length)
     for (var i = tabellengte; i < x.length ;i++) {
       console.log(i)

       document.getElementById("datatabel").deleteRow(i);

    }
    tabellengte =  x.length

    document.getElementById('updatetabel').reset();
MakeTabel()
//  })
//dubbel tijdelijk!!!!!!!!!!!!!!!!
//retour data op scherm in tabel zetten



  }
});
}
// data: oMyForm,
// dataType: 'text',
// processData: false,
// contentType: false,
// type: 'POST',
// success: function(data){
// var data2 = JSON.parse(data);

  //event.preventDefault()
  // var res = {}
  // let arr = $( "#updatetabel" ).serializeArray()
  // arr.forEach ( (el, idx) => res[el.name]  = el.value)
  // console.log("loopt het script")
  // console.log(JSON.stringify(res))
  // data = JSON.stringify(res)



//   var res = {}
//   let arr = $( "#updatetabel" ).serializeArray()
// //  arr.forEach ( (el, idx) => res[el.format]  = el.waarde)
//   console.log(arr)
//   console.log(JSON.stringify(arr))
//   data = JSON.stringify(res)
//
//     $.ajax({
//   url: 'http://localhost:8080/upload?Ceckdata',
//   data: oMyForm,
//   dataType: 'text',
//   processData: false,
//   contentType: false,
//   type: 'POST',
//   success: function(data){
//   var data2 = JSON.parse(data);
// }
// });

// </script>
//
// <script>
// $.fn.setNow = function (onlyBlank) {
// var now = new Date($.now())
//   , year
//   , month
//   , date
//   , hours
//   , minutes
//   , seconds
//   , formattedDateTime
//   ;
//
// year = now.getFullYear();
// month = now.getMonth().toString().length === 1 ? '0' + (now.getMonth() + 1).toString() : now.getMonth() + 1;
// date = now.getDate().toString().length === 1 ? '0' + (now.getDate()).toString() : now.getDate();
// hours = now.getHours().toString().length === 1 ? '0' + now.getHours().toString() : now.getHours();
// minutes = now.getMinutes().toString().length === 1 ? '0' + now.getMinutes().toString() : now.getMinutes();
// seconds = now.getSeconds().toString().length === 1 ? '0' + now.getSeconds().toString() : now.getSeconds();
//
// formattedDateTime = year + '-' + month + '-' + date + 'T' + hours + ':' + minutes + ':' + seconds;
//
// if ( onlyBlank === true && $(this).val() ) {
//   return this;
// }
//
// $(this).val(formattedDateTime);
//
// return this;

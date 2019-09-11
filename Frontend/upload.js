//filebrowser voor bootstrap

//testje

//
var res = {}
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
    var data2 = JSON.parse(data);
    //data parameters zetten



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
});
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



 //var res = {}
     for (c = 0; c < b; c++) {





 let arr1 = $('#socialsecurity_id' + c.toString()).serializeArray()
 console.log(arr1)
  var arr2 = $("#employer_id" + c.toString()).serializeArray()
    let arr3 = $( "#first_name"  + c.toString()).serializeArray()
      let arr4 = $( "#last_name"  + c.toString()).serializeArray()
        let arr5 = $( "#date_of_birth"  + c.toString()).serializeArray()
          let arr6 = $( "#status"  + c.toString()).serializeArray()
            let arr7 = $( "#gender"  + c.toString()).serializeArray()
              let arr8 = $( "#adress_id"  + c.toString()).serializeArray()
                let arr9 = $( "#communication_type"  + c.toString()).serializeArray()
                  let arr10 = $( "#hire_date"  + c.toString()).serializeArray()
    arr1.forEach ( (el, idx) => res['socialsecurity_id' + c.toString()]  = el.value)
    arr2.forEach ( (el, idx) => res["employer_id" + c.toString()]  = el.value)
    arr3.forEach ( (el, idx) => res["first_name" + c.toString()]  = el.value)
    arr4.forEach ( (el, idx) => res["last_name" + c.toString()]  = el.value)
    arr5.forEach ( (el, idx) => res["date_of_birth" + c.toString()]  = el.value)
    arr6.forEach ( (el, idx) => res["status" + c.toString()]  = el.value)
    arr7.forEach ( (el, idx) => res["gender" + c.toString()]  = el.value)
    arr8.forEach ( (el, idx) => res["adress_id" + c.toString()]  = el.value)
    arr9.forEach ( (el, idx) => res["communication_type" + c.toString()]  = el.value)
    arr10.forEach ( (el, idx) => res["hire_date" + c.toString()]  = el.value)
}
 console.log("loopt het script")
   data = JSON.stringify(res)
   console.log(data)
   $.ajax({
    type:'post',
    dataType:'json',
    contentType: 'application/json;charset=utf-8',
  //  url:'http://143.176.115.180:8080/ontlasting',
   url:'localhost/checkdata',
    data
  })

}
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

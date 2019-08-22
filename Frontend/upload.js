//filebrowser voor bootstrap
$(document).ready(function () {
  bsCustomFileInput.init()
})


//uploader met ajax
function uploadFormData(){
    $('#result').html('');

    var gekozenvalue = ''
      var ele = document.getElementsByClassName('form-check-input');
      for(i = 0; i < ele.length; i++) {
         if(ele[i].checked)
            gekozenvalue = ele[i].value
}
var oMyForm = new FormData();
oMyForm.append("file", inputGroupFile01.files[0]);
//haal document naam op:
var tekst = document.getElementById("inputGroupFile01").value //get file path
var pos1 = tekst.indexOf("\\");          	// zoek eerste /
var pos2 = tekst.indexOf("\\", pos1 + 1); // zoek 2e /
var len = tekst.length; //pak lengte string
var last = tekst.substring(pos2 +1, len) //haal de tekst na de 2e / op.
console.log(last); //

  $.ajax({
    url: 'http://localhost:8080/upload?Scheidingsteken=' + gekozenvalue + "&filename=" + last,
    data: oMyForm,
    dataType: 'text',
    processData: false,
    contentType: false,
    type: 'POST',
    success: function(data){

  //  $('#result').html(data);
//console.log(data)
    var data2 = JSON.parse(data);
    console.log(data2.length)
  for (var i = 0; i < data2.length; (i=i+10)) {  //maak output aan
  var $newListItem = $('<tr>' + '<td>' + data2[i].waarde + '</td>' + '<tr>');
  $('tr:last').after($newListItem);
  }
  for (var i = 1; i < data2.length; (i=i+10)) {  //maak output aan
  var $newListItem = $('<td>' + data2[i].waarde + '</td>');
  $('tr:last').near($newListItem);
  }



  //var $newListItem = $('<li>' + data2[i].type + '</li>');

//var $newListItem = $('<tr>' + '<td>' + data2[i].type + '</td>' + '<td>' + data2[i].goedfout + '</td>' + '<td>' + data2[i].waarde + '</td>' + '</tr>');

//var $newListItem = $('<tr>' + '<td>' + data2[i].waarde + '</td>' + '<td>employer_id</td>' + '<td>first_name</td>' + '<td>last_name</td>' + '<td>date_of_birth</td>' + '<td>status</td>' + '<td>gender</td>' + '<td>adress_id</td>' + '<td>communication_type</td>' + '<td>hire_date</td>' + '</tr>');



  }
});
  $('ul').before('<p>Data geladen</p>');
}




/*
//getdata voor de scheider
function displayRadioValue() {
  var gekozenvalue = ''
    var ele = document.getElementsByClassName('form-check-input');
    for(i = 0; i < ele.length; i++) {
       if(ele[i].checked)
          gekozenvalue = ele[i].value
        document.getElementById("result").innerHTML = gekozenvalue;
    }
    console.log(gekozenvalue)
//post data tabel scheider
var res = {}
let arr = ["",""]
arr.forEach ( (el, idx) => res["Scheidingsteken"]  = gekozenvalue)
console.log("loopt het script")
console.log(JSON.stringify(res))
data = JSON.stringify(res)
$.ajax({
  type:'post',
  dataType:'json',
  contentType: 'application/json;charset=utf-8',
  url:'http://localhost:8080/scheidingsteken',
  data
})
}



//get data van server en zet het in tabel
$("#showintabel").on("submit", (event) => {
  event.preventDefault()
  //input van form is naar text gezet type="text"
  var res = $('input:text').val();
  //waarde naar console als test
  console.log(res)
//request naar server
  let request = new XMLHttpRequest();
  request.open('GET', 'http://localhost:8080/upload' + res, true);
  request.onload = function () {
  // Convert JSON data to an object
  let data = JSON.parse(this.response);
  //lege variable als werkt innerHTML niet
  let data1 = '';
  let data2 = '';
  let data3 = '';
  let data4 = '';
  let data5 = '';
  let data6 = '';
  let data7 = '';
  //let test = '';
  let test = '<tr><th>data1</th><th>data1</th><th>data1</th><th>data1</th><th>data1</th></tr>';
  //loop over json files
  for (var i = 0; i < data.length; i++) {
    //maak output aan
console.log(data[i].data);
datatabel += '<tr>' + '<td>' + data[i].datbaseentry + '</td>' + '<td>' + data[i].datbaseentry + '</td>' + '<td>' + data[i].datbaseentry + '</td>' + '<td>' + data[i].datbaseentry + '</td>' + '<td>' + data[i].datbaseentry; + '</td>' + '</tr>'
}
//zet output om naar HTML
//
document.getElementById('datatabel').innerHTML = datatabel;
}
request.send();
})
*/

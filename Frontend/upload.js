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
    var data2 = JSON.parse(data);
    console.log(data);

  console.log(data2.length);//=30
  var q = 0 //begin bij 0
  var r = 10 //r = aantal kolomen
  var aantalregels = (data2.length / r);  //delen door aantal kolomen
  //loop door een  regel
    for (var b = 0; b < aantalregels; b++) {
      var tabel = "";
    //loop door items van een  regel
        for (var l = q; l < r; l++) {
          var colom = '<td id=' + data2[l].goedfout + ' title=' + data2[l].format + '>' + data2[l].waarde + '</td>'
          var tabel = tabel + colom  //maak van cellen een regel
        }
      var $newListItem = $('<tr>' + tabel + '<tr>'); $('tr:last').after($newListItem);   //zet de regels in tabel.
      var $newListItem = "";
    var q = l;  //waarde voor de volgende regel
    var r = r + 10; //waarde voor volgene regel
    }

//loop voor de tabel van kleur te voor zien
  var table = document.getElementById("datatabel"); // Get the table
  for (var i = 1, row; row = table.rows[i]; i++) {        //loop door de rijen begin  in rij 2
      for (var j = 0, cell; cell = row.cells[j]; j++) {   //loop door de cellen
        console.log(cell.id);
          console.log(cell.title);
  if (cell.id == "true"){ //bij id true dan groen
          cell.style.backgroundColor = 'green';
        }
        else {
          cell.style.backgroundColor = 'red';
        }
      }
  }
  }
});
}





//document.getElementById("false").addEventListener("click",

//function () {
  //  switchTDBackgroundColor(this.id);
//});

//document.getElementById("true").addEventListener("click",

//function () {
  //  switchTDBackgroundColor(this.id);
//});

  //document.getElementById("true").style.backgroundColor = "green";
  //document.getElementById("false").style.backgroundColor = "red";
  //document.getElementById("null").style.backgroundColor = "blue";





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

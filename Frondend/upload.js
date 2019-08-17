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
  console.log("werkt dit" + document.getElementById("inputGroupFile01").value)
var tekst = document.getElementById("inputGroupFile01").value
  var tst2 = tekst.substring(1, tekst.indexOf('\\'));
  console.log(tst2)
  $.ajax({
    url: 'http://localhost:8080/upload?Scheidingsteken=' + gekozenvalue,
    data: oMyForm,
    dataType: 'text',
    processData: false,
    contentType: false,
    type: 'POST',
    success: function(data){
      $('#result').html(data);
    }

  });
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

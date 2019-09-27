//$(document).ready(function() {
//    $.ajax({
//        type: 'GET',
//        url: 'http://localhost:8080/werkgevers',
//        dataType: "json",
//        async:false,
//        success: function(data) {
//
//            console.log(data);
//        },
//        error: function (jqXHR, exception) {
//                     console.log("fout:" + exception)
//          }
//    });
//
//});

function getAanspraken() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/werkgevers',
        dataType: "json",
        async:false,
        success: function(data) {

            console.log(data);

            document.getElementById("aansprakenOutput").value = JSON.stringify(data);

        },
        error: function (jqXHR, exception) {
                     console.log("fout:" + exception)
          }
    });
}


function MakeTabel() {

}
    var $newListItem = $('<tr>' + tabel + '<tr>'); $('tr:last').after($newListItem);   //zet de regels in tabel..
      $newListItem.show();
//retour data op scherm in tabel zetten
  var q = 0 //begin bij 0
if(max == true){
  var r = 13 //r = aantal kolomen
}else {
  var r = 5
}


$(document).ready(function() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/werkgevers',
        dataType: "json",
        async:false,
        success: function(data) {

            console.log(data);

   	//var txt = [data]; //zet data in een array
            var aanspraken_data = '';
                $.each(data, function(key, value){
                  aanspraken_data += '<tr>';
                  aanspraken_data += '<td>'+value.employerId+'</td>';
                  aanspraken_data += '<td>'+value.companyName+'</td>';
                  aanspraken_data += '<tr>';
                });
                $('#aanspraken_table').append(aanspraken_data);
         		}
         });
     })






























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



//
//function MakeTabel() {
//
//}
//    var $newListItem = $('<tr>' + tabel + '<tr>'); $('tr:last').after($newListItem);   //zet de regels in tabel..
//      $newListItem.show();
////retour data op scherm in tabel zetten
//  var q = 0 //begin bij 0
//if(max == true){
//  var r = 13 //r = aantal kolomen
//}else {
//  var r = 5
//}


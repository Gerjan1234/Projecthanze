
function getPremies() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/premies',
        dataType: "json",
        async:false,
        success: function(data) {

            console.log(data);

            document.getElementById("premiesOutput").value = JSON.stringify(data);

        },
        error: function (jqXHR, exception) {
                     console.log("fout:" + exception)
          }
    });
}


    function chkPSW() {

    document.getElementById('id01');
    var resultaat;

    var User = new Object();
        User.usr = '2369017.0';
        User.psw = 'X2369017';

    var InlogGeg = JSON.parse(JSON.stringify(User));

    console.log('gebruikersnaam is: ' + User.usr);
    console.log("Gebruiker: " + InlogGeg.usr + " en wachtwoord :" + User.psw);
    console.log("InlogGeg usr : " + InlogGeg.usr);
    console.log("InlogGeg psw : " + InlogGeg.psw);
    console.log("User usr : " + User.usr);
    console.log("User psw : " + User.psw);

    $.ajax({
        url: 'http://localhost:8080/login',
        data: InlogGeg,
        dataType: 'json',
        processData: true,
        contentType: 'text',
        type: 'GET',
        success: function(data){
//        data2 = JSON.stringify(data);
//        		console.log("dit is data : " + data);
//        		console.log("dit is data2 : " + data2);
//        		var obj = JSON.parse(data2);
//        		console.log("dit is obj : " + obj);
//        		resultaat = obj.answer;
//        		console.log("resultaat = " + resultaat);
        		var txt = [data];
var premie_data = '';
    $.each(txt, function(key, value){
      premie_data += '<tr>';
      premie_data += '<td>'+value.answer+'</td>';
      premie_data += '<td>'+value.username+'</td>';
      premie_data += '<td>'+value.user+'</td>';
      premie_data += '<tr>';
    });
    $('#premie_table').append(premie_data);
        		}
        });
    }

    function hideTBL() {
    $('#premie_table').hide();
    }

    function showTBL() {
    $('#premie_table').show();
    }

//$(document).ready(function(){
//  $.getJSON("http://localhost:8080/login", function(data){
//    var premie_data = '';
//    $.each(data, function(key, value){
//      premie_data += '<tr>';
//      premie_data += '<td>'+value.name+'</td>';
//      premie_data += '<td>'+value.username+'</td>';
//      premie_data += '<td>'+value.email+'</td>';
//      premie_data += '<td>'+value.address+'</td>';
//      premie_data += '<td>'+value.geo+'</td>';
//      premie_data += '<td>'+value.phone+'</td>';
//      premie_data += '<td>'+value.website+'</td>';
//      premie_data += '<td>'+value.company+'</td>';
//      premie_data += '<tr>';
//    });
//    $('#premie_table').append(premie_data);
//  });

//});
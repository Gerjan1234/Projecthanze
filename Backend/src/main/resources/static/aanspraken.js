$(document).ready(function() {

var gebrID = 99999999.9999;

// opvragen wie is ingelogd
$.ajax({
        url: 'http://localhost:8080/getlogin',
            type: 'GET',
            success: function(data){

            console.log(data);

            data2 = JSON.stringify(data);
                		console.log(data2);
                		var obj = JSON.parse(data2);
            gebrID = obj.user;
            var GebrNaam = obj.username;


            console.log("gebrID = "+gebrID);
            console.log("obj.user = "+obj.usr);
            console.log("GebrNaam = "+obj.username);

            if(GebrNaam == "nietingelogd"){
            location.replace("http://localhost:8080/login.html");
            }else{
            makeList();
            }; //als inlog is oke dan uitvoeren anders naar inlogscherm

         		},//einde function(data)
         		error: function (jqXHR, exception) {
                console.log("fout:" + exception)}
         });

function makeList() {
var User = new Object();
    User.usr = gebrID;

var HuidigeGebruiker = JSON.parse(JSON.stringify(User));

    $.ajax({
        url: 'http://localhost:8080/aanspraken',
            data: HuidigeGebruiker,
            dataType: 'json',
            processData: true,
            type: 'GET',
            success: function(data){

            console.log(data);

   	            var aanspraken_data;
                var rij = "R1"; //nodig voor rijen om en om een andere backgroundcolor te geven

                $.each(data, function(key, value){
                  aanspraken_data += '<tr id="'+rij+'">';
                  aanspraken_data += '<td> '+' '+value.socialsecurity_id+'</td>';
                  aanspraken_data += '<td> '+' '+value.calculating_date+'</td>';
                  aanspraken_data += '<td> '+' '+value.first_name+'</td>';
                  aanspraken_data += '<td> '+' '+value.last_name+'</td>';
                  aanspraken_data += '<td> '+' '+value.date_of_birth+'</td>';
                  aanspraken_data += '<td> '+' '+value.street_name+'</td>';
                  aanspraken_data += '<td> '+' '+value.street_number+'</td>';
                  aanspraken_data += '<td> '+' '+value.postal_code+'</td>';
                  aanspraken_data += '<td> '+' '+value.city+'</td>';
                  aanspraken_data += '<td> '+' '+value.salary+'</td>';
                  aanspraken_data += '<td> '+' '+value.parttime_factor+'</td>';
                  aanspraken_data += '<td> '+' '+value.franchise+'</td>';
                  aanspraken_data += '<td> '+' '+value.grondslag+'</td>';
                  if(value.aanspraak <0) {aanspraken_data += '<td> '+' '+0+'</td>'}else{aanspraken_data += '<td> '+' '+value.aanspraak+'</td>'};
                  aanspraken_data += '<tr>';

                if(rij == "R1") {rij = "R2"} else {rij = "R1"};


                });
                $('#aanspraken_table').append(aanspraken_data);
         		},
         		error: function (jqXHR, exception) {
                console.log("fout:" + exception)}
         });
     }
})
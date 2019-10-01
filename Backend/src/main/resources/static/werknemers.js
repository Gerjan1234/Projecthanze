$(document).ready(function() {

var gebrID = 9191919.19;
var GebrNaam = "";

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
            GebrNaam = obj.username;


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

document.getElementById("LoggedAs").innerHTML = "Ingelogd als: "+gebrID+ " - "+ GebrNaam;

var User = new Object();
    User.usr = gebrID;

var HuidigeGebruiker = JSON.parse(JSON.stringify(User));

    $.ajax({
        url: 'http://localhost:8080/werknemers',
            data: HuidigeGebruiker,
            dataType: 'json',
            processData: true,
            type: 'GET',
            success: function(data){

            console.log(data);

   	            var werknemers_data;
                var rij = "R1"; //nodig voor rijen om en om een andere backgroundcolor te geven

                $.each(data, function(key, value){
                  werknemers_data += '<tr id="'+rij+'">';
                  werknemers_data += '<td> '+' '+value.socialsecurity_id+'</td>';
                  werknemers_data += '<td> '+' '+value.first_name+'</td>';
                  werknemers_data += '<td> '+' '+value.last_name+'</td>';
                  werknemers_data += '<td> '+' '+value.date_of_birth+'</td>';
                  werknemers_data += '<td> '+' '+value.status+'</td>';
                  werknemers_data += '<td> '+' '+value.gender+'</td>';
                  werknemers_data += '<td> '+' '+value.communication_type+'</td>';
                  werknemers_data += '<td> '+' '+value.hire_date+'</td>';
                  werknemers_data += '<td> '+' '+value.street_name+'</td>';
                  werknemers_data += '<td> '+' '+value.street_number+'</td>';
                  werknemers_data += '<td> '+' '+value.postal_code+'</td>';
                  werknemers_data += '<td> '+' '+value.city+'</td>';
                  werknemers_data += '<tr>';

                if(rij == "R1") {rij = "R2"} else {rij = "R1"};


                });
                $('#werknemers_table').append(werknemers_data);
         		},
         		error: function (jqXHR, exception) {
                console.log("fout:" + exception)}
         });
     }
})

function logout(){
       $.ajax({url: 'http://localhost:8080/resetlogin'});
       location.replace("http://localhost:8080/login.html");
       }
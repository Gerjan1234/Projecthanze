$(document).ready(function() {

var gebrID = 9191919.19;
var GebrNaam = "";
var WGjaarpremie = 0.00;
var vandaag = new Date();
var HuidigJaar = vandaag.getFullYear();
var PremieList = new Array();


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
        url: 'http://localhost:8080/premies',
            data: HuidigeGebruiker,
            dataType: 'json',
            processData: true,
            type: 'GET',
            success: function(data){

            console.log(data);

   	            var premies_data;
                var rij = "R1"; //nodig voor rijen om en om een andere backgroundcolor te geven

                $.each(data, function(key, value){
                  premies_data += '<tr id="'+rij+'">';
                  premies_data += '<td> '+' '+value.socialsecurity_id+'</td>';
                  premies_data += '<td> '+' '+value.calculating_date+'</td>';
                  premies_data += '<td> '+' '+value.first_name+'</td>';
                  premies_data += '<td> '+' '+value.last_name+'</td>';
                  premies_data += '<td> '+' '+value.date_of_birth+'</td>';
                  premies_data += '<td> '+' '+value.street_name+'</td>';
                  premies_data += '<td> '+' '+value.street_number+'</td>';
                  premies_data += '<td> '+' '+value.postal_code+'</td>';
                  premies_data += '<td> '+' '+value.city+'</td>';
                  premies_data += '<td> '+' '+value.salary+'</td>';
                  premies_data += '<td> '+' '+value.parttime_factor+'</td>';
                  premies_data += '<td> '+' '+value.franchise+'</td>';
                  if(value.jaarpremie <0) {premies_data += '<td> '+' '+0+'</td>'}else{premies_data += '<td> '+' '+value.jaarpremie+'</td>'};
                  if(value.maandpremie <0) {premies_data += '<td> '+' '+0+'</td>'}else{premies_data += '<td> '+' '+value.maandpremie+'</td>'};
                  premies_data += '<tr>';

                if(rij == "R1") {rij = "R2"} else {rij = "R1"};

                //hier totalen lijst maken voor optellen jaarpremie werkgever

                if(getJaar(value.calculating_date) == HuidigJaar){
                    if(value.jaarpremie <0) {WGjaarpremie += 0;
                    }else{

                    WGjaarpremie += value.jaarpremie;
//                    console.log(WGjaarpremie)
                    };
                };

                });

                $('#premies_table').append(premies_data);
                document.getElementById("Totalen").innerHTML = "Werkgever totalen " + HuidigJaar + " : &nbsp&nbsp&nbsp Jaarpremie - € " + WGjaarpremie.toFixed(2) + "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"  + "Maandpremie - € " + (WGjaarpremie/12).toFixed(2);
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

function getJaar(date)    {
var vandaag = new Date(date);
var Rjaar = vandaag.getFullYear();
return Rjaar;
}
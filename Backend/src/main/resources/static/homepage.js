       var melding = "Directie leden:</br></br>Piet Geldverkwisting</br>Rene Oplichting</br>Hans Gat in de Hand";

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
            document.getElementById("LoggedAs").innerHTML = "Ingelogd als: "+gebrID+ " - "+ GebrNaam;
            }; //als inlog is oke dan uitvoeren anders naar inlogscherm

         		},//einde function(data)
         		error: function (jqXHR, exception) {
                console.log("fout:" + exception)}
         });
         })

function logout(){
       $.ajax({url: 'http://localhost:8080/resetlogin'});
       location.replace("http://localhost:8080/login.html");


       }





       function pop(varmelding) {

                  document.getElementById("Poptxt").innerHTML = varmelding;
                             $('.pop_scherm').show();

                      $('.pop_scherm').click(function(){
                          $('.pop_scherm').hide();
                      });
                      $('.popupCloseButton').click(function(){
                          $('.pop_scherm').hide();
                      });
                  };

var attempt = 3;
var resultaat;
var melding = "<I>Om te registreren stuur dan een mail naar info@hetspaarvarken.fonds</br></br>" +
               "Volgende gegevens zijn nodig: </br>" +
               "Bedrijfsnaam</br>" +
               "Adres</br>" +
               "Postcode</br>" +
               "Woonplaats</br>" +
               "Naam contactpersoon</br>" +
               "Telefoonnummer contactpersoon</br>" +
               "Uittreksel van de KvK</br></br>" +
               "Na verificatie ontvangt u per post uw inlogcode en wachtwoord.</I>";

var WWmelding = "<I>Er is een mail met nieuwe nieuwe inlogcode aan U verzonden.</I>";

function start(){
chkPSW();

};


function validate(){
if ( resultaat == "Combinatie gebruiker en wachtwoord is correct"){
window.location = "http://localhost:8080/homepage.html"; // als accoord ga verder naar ......
return false;
}
else{
attempt --;// poging met 1 verlagen
if(attempt < 0 ) {attempt = 0};
pop(resultaat + "</br>U heeft nog "+attempt+" pogingen over");
// velden uitschakelen na het aantal pogingen
if( attempt == 0){
pop("Te veel pogingen !!</br></br>Deze site is voor u geblokkeerd</br>probeer het na 15 minuten nogmaals");
document.getElementById("usr").disabled = true;
document.getElementById("psw").disabled = true;
document.getElementById("btn").disabled = true;
document.getElementById("btn2").disabled = true;
return false;
}
}
};

function chkPSW() {

document.getElementById('id01');

var User = new Object();
    User.usr = usr.value;
    User.psw = psw.value;

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
    data2 = JSON.stringify(data);
    		console.log(data2);
    		var obj = JSON.parse(data2);
    		resultaat = obj.answer;
    		console.log("resultaat = " + resultaat);
    		validate();
        }
    })

};

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

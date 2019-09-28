var attempt = 3;
var resultaat;

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
alert(resultaat + "U heeft nog "+attempt+" pogingen over;");
// velden uitschakelen na het aantal pogingen
if( attempt == 0){
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


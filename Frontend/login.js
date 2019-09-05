function showlogin(form) {
     $('#result').html('');

var User = new Object();
    User.usr = form.usr.value;
    User.psw = form.psw.value;


var InlogGeg = JSON.parse(JSON.stringify(User));

console.log('gebruikersnaam is: ' + User.usr);
console.log("Gebruiker: " + InlogGeg.usr + " en wachtwoord :" + User.psw);
console.log("InlogGeg usr : " + InlogGeg.usr);
console.log("InlogGeg psw : " + InlogGeg.psw);
console.log("User usr : " + User.usr);
console.log("User psw : " + User.psw);

alert ("Gebruiker: " + InlogGeg.usr + " en wachtwoord :" + User.psw);
alert("InlogGeg usr : " + InlogGeg.usr);
alert("InlogGeg psw : " + InlogGeg.psw);
alert("User usr : " + User.usr);
alert("User psw : " + User.psw);


//  $.ajax({
//   url:'/login?',
//   type:"POST",
//   data:InlogGeg,
//   contentType:"application/json",
//   success:function(response){
//  alert(response);
//  },
//  error:function(error){
//  alert(error);
//  }
}

function field_focus(field, usr)
  {
    if(field.value == email)
    {
      field.value = '';
    }
  }


  function field_blur(field, usr)
  {
    if(field.value == '')
    {
      field.value = email;
    }
  }


//Fade in dashboard box
$(document).ready(function(){
    $('.box').hide().fadeIn(1000);
    });

//Stop click event
$('a').click(function(event){
    event.preventDefault();
    //showlogin(this.form);
	});


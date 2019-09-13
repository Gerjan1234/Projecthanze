function showlogin(form) {
     $('#result').html('');

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

alert ("Gebruiker: " + InlogGeg.usr + " en wachtwoord :" + User.psw);
//alert("InlogGeg usr : " + InlogGeg.usr);
//alert("InlogGeg psw : " + InlogGeg.psw);
//alert("User usr : " + User.usr);
//alert("User psw : " + User.psw);

$(document).ready(function(){

$('.box').hide().fadeIn(1000);

  //   $.post("demo_test_post.asp",
//$.get('http://localhost:8080/login.html',
//     {
//       InlogGeg
//     },
//     function(data,status){
//       alert("Data: " + data + "\nStatus: " + status);
//     });
//   });

<!---->
$.ajax({
    url: 'http://localhost:8080/login',
    data: InlogGeg,
    dataType: 'json',
    processData: false,
    contentType: 'text',
    type: 'GET',
    success: function(data){
    //console.log('ajaxmelding' + data);
    }
    });
});
};
function field_focus(field, email)
  {
    if(field.value == email)
    {
      field.value = '';
    }
  }


  function field_blur(field, email)
  {
    if(field.value == '')
    {
      field.value = email;
    }
  }


////Fade in dashboard box
//$(document).ready(function(){
//    $('.box').hide().fadeIn(1000);
//    });


//Stop click event
$('a').click(function(event){
    event.preventDefault();
    showlogin(this.form);
	});


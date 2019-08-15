//filebrowser voor bootstrap
$(document).ready(function () {
  bsCustomFileInput.init()
})


//uploader met ajax
function uploadFormData(){
    $('#result').html('');

  var oMyForm = new FormData();
  oMyForm.append("file", inputGroupFile01.files[0]);

  $.ajax({
    url: 'http://localhost:8080/upload',
    data: oMyForm,
    dataType: 'text',
    processData: false,
    contentType: false,
    type: 'POST',
    success: function(data){
      $('#result').html(data);
    }
  });
}

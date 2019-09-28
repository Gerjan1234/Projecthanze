


var aanspraken = JSON.parse(JSON.stringify(User));

      $.ajax({ 
      url: 'http://localhost:8080/werkgevers', 
      data: aanspraken, 
      dataType: 'text', 
      processData: false, 
      contentType: false, 
      type: 'GET', 
      success: function(data){ 
      console.log(data)}
})

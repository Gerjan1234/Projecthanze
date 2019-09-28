$(document).ready(function() {

var User = new Object();
    User.usr = 2777536;

var HuidigeGebruiker = JSON.parse(JSON.stringify(User));


<<<<<<< HEAD
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
=======
    $.ajax({
        url: 'http://localhost:8080/aanspraken',
            data: HuidigeGebruiker,
            dataType: 'json',
            processData: true,
//            contentType: 'text',
            type: 'GET',
            success: function(data){

            console.log(data);

   	//var txt = [data]; //zet data in een array
            var aanspraken_data;

                $.each(data, function(key, value){
                  aanspraken_data += '<tr>';
                  aanspraken_data += '<td>'+value.socialsecurity_id+'</td>';
                  aanspraken_data += '<td>'+value.calculating_date+'</td>';
                  aanspraken_data += '<td>'+value.first_name+'</td>';
                  aanspraken_data += '<td>'+value.last_name+'</td>';
                  aanspraken_data += '<td>'+value.date_of_birth+'</td>';
                  aanspraken_data += '<td>'+value.street_name+'</td>';
                  aanspraken_data += '<td>'+value.street_number+'</td>';
                  aanspraken_data += '<td>'+value.postal_code+'</td>';
                  aanspraken_data += '<td>'+value.city+'</td>';
                  aanspraken_data += '<td>'+value.salary+'</td>';
                  aanspraken_data += '<td>'+value.parttime_factor+'</td>';
                  aanspraken_data += '<td>'+value.franchise+'</td>';
                  aanspraken_data += '<td>'+value.grondslag+'</td>';
                  aanspraken_data += '<td>'+value.aanspraak+'</td>';
                  aanspraken_data += '<tr>';
                  

                  
                  
                });
                $('#aanspraken_table').append(aanspraken_data);
         		},
         		error: function (jqXHR, exception) {
                console.log("fout:" + exception)}
         });
     })

>>>>>>> 479f4ccf27168594defee4866c20f580acdde01f

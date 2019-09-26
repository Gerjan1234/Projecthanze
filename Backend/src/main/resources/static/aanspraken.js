//$(document).ready(function() {
//    $.ajax({
//        type: 'GET',
//        url: 'http://localhost:8080/werkgevers',
//        dataType: "json",
//        async:false,
//        success: function(data) {
//
//            console.log(data);
//        },
//        error: function (jqXHR, exception) {
//                     console.log("fout:" + exception)
//          }
//    });
//
//});

function getAanspraken() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/werkgevers',
        dataType: "json",
        async:false,
        success: function(data) {

            console.log(data);

            document.getElementById("aansprakenOutput").value = JSON.stringify(data);

        },
        error: function (jqXHR, exception) {
                     console.log("fout:" + exception)
          }
    });
}

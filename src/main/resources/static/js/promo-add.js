/**
 * Função para capturar as metatags
 */
$("#linkPromocao").on('change', function() {
    var url = $(this).val();
    //console.log(url);
    //alert("oi");

    if (url.length > 7) {
        $.ajax({
            method:"POST",
            url: "/meta/info?url=" + url,
            cache: false,
            success: function(data){
                console.log(data);
            }
        });
    }



});
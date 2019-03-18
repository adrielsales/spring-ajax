var pagerNumber = 0;

$(document).ready(function() {
    $("#loader-img").hide();
    $("#fim-btn").hide();
});

/** Efeito Infinity Scroll */
$(window).scroll(function() {

    var scrollTop = $(this).scrollTop();
    var conteudo = $(document).height() - $(window).height();

    //console.log('scrollTop: ', scrollTop, ' | ', 'Conteúdo', conteudo);

    if (scrollTop >= conteudo) {
        pagerNumber++;
        setTimeout(function(){
            loadByScrollBar(pagerNumber);
        }, 200);
    }

});

function loadByScrollBar(pagerNumber) {

    $.ajax({
        method: "GET",
        url: "/promocao/list/ajax",
        data: {
            page: pagerNumber
        },
        beforeSend: function() {
            $("#loader-img").show();
        },
        success: function( response ){
            //console.log("Resposta > ", response);

            $(".row").fadeIn(800, function() {
                $(this).append( response );
            });
        },
        error: function(xhr) {
            alert("Ops, ocorreu um erro: " + xhr.status + " - " + xhr.statusText);
        },
        complete: function() {
            $("#loader-img").hide();
        }
    });

}
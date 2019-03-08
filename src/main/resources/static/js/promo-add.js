/**
 * Função para capturar as metatags
 */
$("#linkPromocao").on('change', function() {
    var url = $(this).val();

    if (url.length > 7) {
        $.ajax({
            method:"POST",
            url: "/meta/info?url=" + url,
            cache: false,
            beforeSend: function(){
            	$("#alert").removeClass("alert alert-danger").text('');
            	$("#titulo").val("");
            	$("#site").text("");
            	$("#linkImagem").attr("src", "");
            	$("#loader-img").addClass("loader");
            },
            success: function(data){
                console.log(data);
                //$("#id_do_imput_no_formulario").val(data.nome_do_atributo_da_Classe_que_esta_enviando_os_dados); 
                $("#titulo").val(data.title);
                $("#site").text(data.site.replace("@", "")); //no form, o nome do site está em um h5, por isso está inserindo por um .text() - poderia usar html() - e não pelo .val().
                $("#linkImagem").attr("src", data.image);
            },
            statusCode: {
            	404 : function(){
            		$("#alert").addClass("alert alert-danger").text("Nenhuma informação pode ser recuperada na Url informada. (Erro 404)");
            		$("#linkImagem").attr("src", "/images/promo-dark.png");
            	}
            },
            error: function(){
            	$("#alert").addClass("alert alert-danger").text("Confira se a Url informada está correta. (Erro 500)");
            	$("#linkImagem").attr("src", "/images/promo-dark.png");
            },
            complete: function(){
            	$("#loader-img").removeClass("loader");
            }
        });
    }



});
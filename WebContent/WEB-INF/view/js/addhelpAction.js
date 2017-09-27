$(document).ready(function () {
    
	//action="/supportSys/addhelp"
	
	
    $('form.addhelpForm')[0].reset();
    $('button#back').click(function(){        
        window.location.href = '/supportSys';
    });
    
    /*
     * 
		formMode
		client
		idAtividade
		helpLabel
		cat
		department_id
		desc
		botão: id: send
     * 
     * 
     * 
     * */
    
    
    $('#send').click(function () {       
        var formMode = 		$("input#formMode").val();
        var client = 		$("input#desc").val();
        var idAtividade = 	$("input#idAtividade").val();
        var helpLabel = 	$("input#helpLabel").val();
        var category = 		$("input#cat").val();
        var department_id = $("select#department_id").val();
        var description = 	$("textarea#desc").val();
//        var helpLabel = $('input[name="situacao"]:checked').val();
        
//        if(helpLabel == '' || description == '' || category == 0 || department_id == 0){                
//            $('span.message-danger').append('<div class="alert alert-danger" role="alert"><strong>ATENÇÃO!</strong> Todos os campos são obrigatórios!</div>');
//            return false;
//        }
        
        $("button.btn-send").attr("disabled","disabled");
        $('span.loader').append('<img src="/resources/images/loader.gif">');
        
        var setJson = JSON.stringify({'formMode': formMode, 'client': client, 'idAtividade': idAtividade,'helpLabel': helpLabel, 'category': category, 'department_id': department_id, 'description': description});
        
        $.ajax({
            type: 'POST',
            dataType: 'json',
            url: '/addhelp/?jsonStr' + setJson,
            data: setJson,
            success: function (data) {
               console.log(data)
               $('span.loader').remove();
               $('span.message-danger').remove();
               $("form.addhelpForm").fadeOut('fast');
               $('span.message').append('<div class="alert alert-success" role="alert">Atividade registrada com sucesso! <a href="/">(Clique para voltar)</a></div>');
                return true;
            },
            error: function (data) {
                console.log(data);
                return false;
            }
        });
    });
    // --final documentReady --
});
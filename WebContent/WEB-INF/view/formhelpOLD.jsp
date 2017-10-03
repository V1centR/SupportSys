<jsp:include page="header.jsp" />
<body>
<div style="position:relative; float:left; height:100%; width:250px; background-color:#1C1C1C; margin-right:20px; color:#fff; padding:15px;">
<span class="glyphicon glyphicon-home"></span> New Help</div>
<div style="position:relative; float:left;">

<span class="message-danger"></span>
<span class="message"></span>
<script>
$(document).ready(function () {
	
    $('form.addhelpForm')[0].reset();
    $('button#back').click(function(){        
        window.location.href = '/supportSys';
    });
    
    $('#send').click(function () {       
        var formMode = 		$("input#formMode").val();
        var client = 		$("input#client").val();
        var idAtividade = 	$("input#idAtividade").val();
        var helpLabel = 	$("input#helpLabel").val();
        var category = 		$("select#cat").val();
        var dept = 			$("select#dept").val();
        var description = 	$("textarea#desc").val();
        var email = "imprensa@asspm.org.br";
//        var helpLabel = $('input[name="situacao"]:checked').val();
        
//        if(helpLabel == '' || description == '' || category == 0 || department_id == 0){                
//            $('span.message-danger').append('<div class="alert alert-danger" role="alert"><strong>ATENÇÃO!</strong> Todos os campos são obrigatórios!</div>');
//            return false;
//        }
        
	    $("button.btn-send").attr("disabled","disabled");
	    $('span.loader').append('<img src="resources/images/loader.gif">');
      
      var strFormJson = "{\"formMode\":\"" + formMode + "\",\"client\":\""+ client + "\",\"idAtividade\":\""+ idAtividade + "\",\"helpLabel\":\""+ helpLabel + "\",\"category\":\""+ category + "\",\"dept\":\""+ dept + "\",\"description\":\""+ description + "\"}";  
      
      var setJson = JSON.stringify(strFormJson);
      $("form.addhelpForm :input").attr("disabled", true);
        $.ajax({        	
            type: 'POST',
            dataType: 'json',
            url: 'addhelp',
            data: setJson,
            contentType : 'application/json; charset=utf-8',
            headers: { 
                'Accept': 'application/json',
                'Content-Type': 'application/json' 
            },
            success: function (data) {
               console.log(data);
               
               $('span.loader').remove();
               $('span.message-danger').remove();
               $("form.addhelpForm").fadeOut('fast');
               
               if(data == 201){
            	   $('span.message').append('<div class="alert alert-success" role="alert">Atividade registrada com sucesso! <a href="/">(Clique para voltar)</a></div>');
               }
               
               if(data == 500){
            	   $('span.message').append('<div class="alert alert-danger" role="alert">Houve um erro de procesamento, seu chamado não foi registrado! <a href="/">(Clique para voltar)</a></div>');
               }
              
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


</script>
<div style="margin-top: 20px;"></div>
<br><br>
<div class="row">
        <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box">
            <span class="info-box-icon bg-aqua"><i class="ion ion-ios-gear-outline"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Tempo de espera</span>
              <span class="info-box-number">90<small>seg</small></span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        <!-- /.col -->
        <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box">
            <span class="info-box-icon bg-red"><i class="fa fa-google-plus"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Likes</span>
              <span class="info-box-number">41,410</span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        <!-- /.col -->

        <!-- fix for small devices only -->
        <div class="clearfix visible-sm-block"></div>

        <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box">
            <span class="info-box-icon bg-green"><i class="ion ion-ios-cart-outline"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Sales</span>
              <span class="info-box-number">760</span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        <!-- /.col -->
        <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box">
            <span class="info-box-icon bg-yellow"><i class="ion ion-ios-people-outline"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Total de chamados</span>
              <span class="info-box-number">19</span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        <!-- /.col -->
      </div>





<div style="margin-top: 20px; margin-bottom: 20px;"><span style="font-weight: bold; font-size: 24px;">Suporte Técnico</span> | Novo chamado</div>



<form name="addHelp" class="addhelpForm" method="post">
                <input type="hidden" id="formMode" name="formMode" value="add">
                <input type="hidden" id="client" name="client" value="1">
                <input type="hidden" id="idAtividade" name="idAtividade" value="0">
                <table class="table" border="0">
                    <tbody>
                        <tr>
                            <td colspan="2">                                
                                <label>*Título</label>                                
                                <input type="text" name="helpLabel" id="helpLabel" value="Miguel AAA" class="form-control" style="font-size: 14px; font-weight: bold; width: 500px;">                               
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                        	<td>
								<div style="position: relative; float: left; margin-right: 12px;">                                    
                                    <label>*Categoria</label>
                                    <select name="cat" id="cat" class="form-control" style="width:250px;">
	                                    <option value="0">...</option>
	                                    <option value="1" selected="selected">Backup</option>
	                                    <option value="2">E-mail</option>
	                                    <option value="3">Hardware</option>
	                                    <option value="4">Impressora/Scanner</option>
	                                    <option value="5">Internet*</option>
	                                    <option value="6">Rede</option>
	                                    <option value="7">S.O</option>
	                                    <option value="8">Sistema</option>
	                                    <option value="9">Site</option>
	                                    <option value="10">Software</option>
	                                    <option value="11">Particular</option>
                                    </select>
                                </div>
							</td>
							<td>
								<div style="position: relative; float: left; margin-right: 12px;">                                    
                                    <label>*Setor</label>
                                    <select name="dept" id="dept" class="form-control" style="max-width:250px; padding:10px;">
	        							<option value="0" selected="selected">...</option>
	        							<option value="1">T.I</option>
	        							<option value="1" selected="selected">Assist Social Assist</option>
	        							<option value="1">Colônia</option>
	        							<option value="1">Cons Deliberativo</option>
	        							<option value="1">Cons Fiscal</option>
	        							<option value="1">Despachante</option>
	        							<option value="1">Ensino</option>
	        							<option value="1">Jurídico</option>
	        							<option value="1">Patrimônio</option>
	        							<option value="1">Pensionistas</option>
	        							<option value="1">Portaria</option>
	        							<option value="1">Relações Públicas</option>
	        							<option value="1">Salão de eventos</option>
	        							<option value="1">Secretaria</option>
	        							<option value="1">Tesouraria</option>
	        							<option value="1">Presidência</option>
	        							<option value="1">Vice Presidência</option>
        							</select>
                                </div>
							</td>
                        </tr>
                        <tr>
                            <td colspan="3">
                                <label>*Descrição</label><br>
                                <textarea class="form-control" name="desc" id="desc" maxlength="600" style="width: 600px; height: 150px; font-size: 12px; padding: 8px;">Teste texto descrição</textarea>
                            </td>
                        </tr>
                        <tr>
                           <td colspan="2" style="text-align:right">
                               <button id="back" type="button" name="btn" class="btn btn-primary btn-lg btn-voltar"><span class="glyphicon glyphicon-chevron-left"></span> Voltar</button>
                               <button id="send" type="button" name="btn" class="btn btn-primary btn-lg btn-send"><span class="glyphicon glyphicon-fire"></span> Enviar</button>                                
                               <span class="loader"></span>
                           </td>
                        </tr>
                    </tbody>
                </table>
            </form>
            </div>
            
            <div class="col-md-4">
                  <p class="text-center">
                    <strong>Ranking de chamados</strong>
                  </p>

                  <div class="progress-group">
                    <span class="progress-text">Impressora</span>
                    <span class="progress-number"><b>160</b>/200</span>

                    <div class="progress sm">
                      <div class="progress-bar progress-bar-aqua" style="width: 80%"></div>
                    </div>
                  </div>
                  <!-- /.progress-group -->
                  <div class="progress-group">
                    <span class="progress-text">Rede</span>
                    <span class="progress-number"><b>310</b>/400</span>

                    <div class="progress sm">
                      <div class="progress-bar progress-bar-red" style="width: 80%"></div>
                    </div>
                  </div>
                  <!-- /.progress-group -->
                  <div class="progress-group">
                    <span class="progress-text">Sistema</span>
                    <span class="progress-number"><b>480</b>/800</span>

                    <div class="progress sm">
                      <div class="progress-bar progress-bar-green" style="width: 80%"></div>
                    </div>
                  </div>
                  <!-- /.progress-group -->
                  <div class="progress-group">
                    <span class="progress-text">Software</span>
                    <span class="progress-number"><b>250</b>/500</span>

                    <div class="progress sm">
                      <div class="progress-bar progress-bar-yellow" style="width: 80%"></div>
                    </div>
                  </div>
                  <!-- /.progress-group -->
                </div>
            

</body>
</html>
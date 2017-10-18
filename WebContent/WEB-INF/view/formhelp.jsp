<jsp:include page="header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="<c:url value="/resources/js/addhelpAction.js"/>"></script>
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
	    $('span.loader').append('<img src="../resources/images/loader.gif">');
      
      var strFormJson = "{\"formMode\":\"" + formMode + "\",\"client\":\""+ client + "\",\"idAtividade\":\""+ idAtividade + "\",\"helpLabel\":\""+ helpLabel + "\",\"category\":\""+ category + "\",\"dept\":\""+ dept + "\",\"description\":\""+ description + "\"}";  
      
      var setJson = JSON.stringify(strFormJson);
      $("form.addhelpForm :input").attr("disabled", true);
        $.ajax({        	
            type: 'POST',
            dataType: 'json',
            url: '../addhelp',
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
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
	<jsp:include page="horizontal-bar.jsp" />
  
  <!-- Left side column. contains the logo and sidebar -->
  <jsp:include page="sidemenu.jsp" />

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    
    

    <!-- Main content -->
    <section class="content">
      
      <section class="content-header">
      <h1>
        Intranet
        <small>Novo chamado</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Intranet</li>
        <li class="active">Chamados</li>
        <li class="active">Novo Chamado</li>
      </ol>
    </section>
      <div class="row">
      
        <!--CONTENT ##################### Left col -->
        <section class="col-lg-7 connectedSortable">
      		<div style="margin-top: 20px;"></div>
			<span class="message-danger"></span>
			<span class="message"></span>
			
			<form name="addHelp" class="addhelpForm" method="post">
                <input type="hidden" id="formMode" name="formMode" value="add">
                <input type="hidden" id="client" name="client" value="1">
                <input type="hidden" id="idAtividade" name="idAtividade" value="0">
                <table class="table" border="0">
                    <tbody>
                        <tr>
                            <td colspan="2">                                
                                <label>*Assunto</label>                                
                                <input type="text" name="helpLabel" id="helpLabel" value="Miguel AAA" class="form-control" style="font-size: 14px; font-weight: bold; width: 500px;">                               
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                        	<td>
								<div style="position: relative; float: left; margin-right: 8px;">                                    
                                    <label>*Categoria</label>
                                    <select name="cat" id="cat" class="form-control" style="width:250px;">                                    
                                    	<option value="0">Selecione</option>
	                                    <c:forEach items="${listTypes}" var="itemsType">
	                                    	<option value="${itemsType.id}">${itemsType.name}</option>
	                                    </c:forEach>
                                    </select>
                                </div>
                                
                                <div style="position: relative; float: left;">                                    
                                    <label>*Setor</label>
                                    <select name="dept" id="dept" class="form-control" style="max-width:250px;">
                                    	<option value="0">Selecione</option>
	        							<c:forEach items="${listDept}" var="itemsDepartment">
	        								<option value="${itemsDepartment.id}">${itemsDepartment.name}</option>
	        							</c:forEach>
        							</select>
                                </div>
                                
                                
							</td>
							<td>
								
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

        </section>
        <!-- /.Left col -->
        
        
        
        
        
        
        
        <!-- right col (We are only adding the ID to make the widgets sortable)-->
        <section class="col-lg-5 connectedSortable">
        <!-- Calendar -->
          <div class="box box-solid bg-green-gradient">
            <div class="box-header">
              <i class="fa fa-calendar"></i>

              <h3 class="box-title">Calendar</h3>
              <!-- tools box -->
              <div class="pull-right box-tools">
                <!-- button with a dropdown -->
                <div class="btn-group">
                  <button type="button" class="btn btn-success btn-sm dropdown-toggle" data-toggle="dropdown">
                    <i class="fa fa-bars"></i></button>
                  <ul class="dropdown-menu pull-right" role="menu">
                    <li><a href="#">Add new event</a></li>
                    <li><a href="#">Clear events</a></li>
                    <li class="divider"></li>
                    <li><a href="#">View calendar</a></li>
                  </ul>
                </div>
                <button type="button" class="btn btn-success btn-sm" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
                <button type="button" class="btn btn-success btn-sm" data-widget="remove"><i class="fa fa-times"></i>
                </button>
              </div>
              <!-- /. tools -->
            </div>
            <!-- /.box-header -->
            <div class="box-body no-padding">
              <!--The calendar -->
              <div id="calendar" style="width: 100%"></div>
            </div>
            <!-- /.box-body -->
            <div class="box-footer text-black">
              <div class="row">
                <div class="col-sm-6">
                  <!-- Progress bars -->
                  <div class="clearfix">
                    <span class="pull-left">Task #1</span>
                    <small class="pull-right">90%</small>
                  </div>
                  <div class="progress xs">
                    <div class="progress-bar progress-bar-green" style="width: 90%;"></div>
                  </div>

                  <div class="clearfix">
                    <span class="pull-left">Task #2</span>
                    <small class="pull-right">70%</small>
                  </div>
                  <div class="progress xs">
                    <div class="progress-bar progress-bar-green" style="width: 70%;"></div>
                  </div>
                </div>
                <!-- /.col -->
                <div class="col-sm-6">
                  <div class="clearfix">
                    <span class="pull-left">Task #3</span>
                    <small class="pull-right">60%</small>
                  </div>
                  <div class="progress xs">
                    <div class="progress-bar progress-bar-green" style="width: 60%;"></div>
                  </div>

                  <div class="clearfix">
                    <span class="pull-left">Task #4</span>
                    <small class="pull-right">40%</small>
                  </div>
                  <div class="progress xs">
                    <div class="progress-bar progress-bar-green" style="width: 40%;"></div>
                  </div>
                </div>
                <!-- /.col -->
              </div>
              <!-- /.row -->
            </div>
          </div>
          <!-- /.box -->

        </section>
        <!-- right col -->
      </div>
      <!-- /.row (main row) -->

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <jsp:include page="footer.jsp" />

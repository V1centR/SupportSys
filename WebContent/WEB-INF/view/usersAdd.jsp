<jsp:include page="header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
$(document).ready(function () {
	    
    $('form#addUser')[0].reset();
    $('button#back').click(function(){        
        window.location.href = '/supportSys';
        return false;
    });
    
    /*
    nameUser
    sNameUser
    gender
    emailUser
    selectClient
    userGroup */
    
    
    $("select#selectClient").change(function(){
        
        console.log("Client ID Selected:: " + this.value);
        
        
        
        
        $.ajax({        	
            type: 'GET',
            dataType: 'json',
            url: '<c:url value="/getdepartmentlist/'+ this.value +'"/>',
            contentType : 'application/json; charset=utf-8',
            headers: { 
                'Accept': 'application/json',
                'Content-Type': 'application/json' 
            },
            success: function (data) {
               console.log(data);
               
               $('select#userGroup').html('');
               $.each(data, function(key, value) {   
                   $('select#userGroup')
                       .append($("<option></option>")
                                  .attr("value",key)
                                  .text(value.name)); 
              });
               
             
               return true;
            },
            error: function (data) {
                console.log(data);
                return false;
            }
        });
        
        
        
        
    });
    
    
    $('button#addUserExec').click(function () {

        $('span.message-danger').html("");
        var idItem = 		$("input#idItem").val();
        var hashItem = 		$("input#hashItemu").val();
       
        var nameUser = 		$("input#nameUser").val();
        var sNameUser = 	$("input#sNameUser").val();
        var gender = 		$('input[name=gender]:checked', '#addUser').val();
        var emailUser = 	$("input#emailUser").val();
        var selectClient = 	$("select#selectClient").val();
        var userGroup = 	$("select#userGroup").val();
        var loaderSmall = 'Processando... <img src="<c:url value="/resources/images/loader.gif"/>" style="">';
        
        //var helpLabel = $('input[name="situacao"]:checked').val();
        
        if(emailUser == ''){                
            $('span.message-danger').append('<div class="alert alert-danger" role="alert"><strong>ATENÇÃO!</strong> Todos os campos são obrigatórios!</div>');
            return false;
        }
        
	  $("button#addUserExec").attr("disabled","disabled");
	  $("form#addUser :input").attr("disabled", true);
	  $('span#proccessloader').append(loaderSmall);
      
      var strFormJson = "{\"idItem\":\"" + idItem + "\",\"hashItem\":\""+ hashItem + "\",\"nameUser\":\""+ nameUser + "\",\"sNameUser\":\""+ sNameUser + "\",\"gender\":\""+ gender + "\",\"emailUser\":\""+ emailUser + "\",\"selectClient\":\""+ selectClient + "\",\"userGroup\":\""+ userGroup + "\"}"; 
      var setJson = JSON.stringify(strFormJson);
      
      console.log("String Json" + setJson);
      
        $.ajax({        	
            type: 'POST',
            dataType: 'json',
            url: '<c:url value="/users/exec"/>',
            data: setJson,
            contentType : 'application/json; charset=utf-8',
            headers: { 
                'Accept': 'application/json',
                'Content-Type': 'application/json' 
            },
            success: function (data) {
               console.log(data);
               
               $('span#proccessloader').remove();
               $('span.message-danger').remove();
               $("form#addUser").fadeOut('fast');
               
               if(data == 201){
            	   $('span.message').append('<div class="alert alert-success" role="alert">Usuário registrado com sucesso! <a href="/">(Clique para voltar)</a></div>');
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
});

</script>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">

		<jsp:include page="horizontal-bar.jsp" />
		<!-- Left side column. contains the logo and sidebar -->
		<jsp:include page="sidemenu.jsp" />

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					Usuários <small>novo usuário</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
					<li><a href="#">Examples</a></li>
					<li class="active">Blank page</li>
				</ol>
			</section>
			<!-- Main content -->
			<section class="content">

				<!-- Default box -->
				<div class="box">
					<div class="box-body">
					<span class="message"></span>
					<span class="message-danger"></span>
					<form class="form-horizontal" id="addUser">
						<fieldset>
						<input type="hidden" name="idItem" id="idItem" value="0" />
						<input type="hidden" name="hashItem" id="hashItem" value="888" />
						<!-- Form Name -->
						<legend>Novo usuário</legend>
						
						<!-- Text input-->
						<div class="form-group">
						  <label class="col-md-4 control-label" for="textinput">Nome</label>  
						  <div class="col-md-4">
						  <input id="nameUser" name="textinput" type="text" placeholder="name" class="form-control" style="font-size:16px; font-weight: bold;" value="Wagner">
						  </div>
						</div>
						
						<!-- Text input-->
						<div class="form-group">
						  <label class="col-md-4 control-label" for="textinput">Sobrenome</label>  
						  <div class="col-md-4">
						  <input id="sNameUser" name="textinput" type="text" placeholder="sobrenome" class="form-control" style="font-size:16px; font-weight: bold;" value="Ribeiro">
						  </div>
						</div>
						
						<!-- Multiple Radios -->
						<div class="form-group">
						  <label class="col-md-4 control-label" for="radios">Sexo</label>
						  <div class="col-md-4">
						  <div class="radio">
						    <label for="radios-0">
						      <input type="radio" name="gender" class="gender" id="radios-0" value="1" checked="checked">
						      Masculino
						    </label>
							</div>
						  <div class="radio">
						    <label for="radios-1">
						      <input type="radio" name="gender" class="gender" id="radios-1" value="2">
						      Feminino
						    </label>
							</div>
						  </div>
						</div>
						
						<!-- Text input-->
						<div class="form-group">
						  <label class="col-md-4 control-label" for="textinput">E-mail</label>  
						  <div class="col-md-4">
						  <input id="emailUser" name="emailUser" type="text" placeholder="email" class="form-control input-md" value="test@test.com">
						  <span class="help-block">this e-mail has been registered!</span>  
						  </div>
						</div>
						
						<!-- Select Basic -->
						<div class="form-group">
						  <label class="col-md-4 control-label" for="selectbasic">Cliente</label>
						  <div class="col-md-4">
						    <select id="selectClient" name="selectClient" class="form-control">
						      <option value="0">Selecione</option>
	                              <c:forEach items="${clientList}" var="listClients">
	                              	<option value="${listClients.id}">${listClients.name}</option>
	                              </c:forEach>
						    </select>
						  </div>
						</div>
						
						<!-- Select Basic -->
						<div class="form-group">
						  <label class="col-md-4 control-label" for="selectbasic">Grupo</label>
						  <div class="col-md-4">
						    <select id="userGroup" name="userGroup" class="form-control">
						      <option value="0">Selecione</option>
						    </select>
						  </div>
						</div>
						<!-- Button -->
						<div class="form-group">
						  <label class="col-md-4 control-label" for="singlebutton">Novo usuário</label>
						  <div class="col-md-4">
						  	<button id="back" name="singlebutton" class="btn btn-primary"><i class="fa fa-close"></i> Cancelar</button>
						    <button id="addUserExec" name="singlebutton" class="btn btn-primary"><i class="fa fa-user-plus"></i> Add new user</button>
						    <span id="proccessloader"></span>
						  </div>
						</div>
						
						</fieldset>
					</form>

					</div>
					<!-- /.box-body -->
					<div class="box-footer">Footer</div>
					<!-- /.box-footer-->
				</div>
				<!-- /.box -->

			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
		<jsp:include page="footer.jsp" />
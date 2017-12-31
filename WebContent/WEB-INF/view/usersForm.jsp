<jsp:include page="header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
$(document).ready(function () {
	    
    
    var mode = '${mode}';
    console.log("Mode:: " + mode);
    
    if(mode == 'edit')
    {
        var clientId = '${userInfo.department.clientBean.id}';
        var departmentId = '${userInfo.department.id}';
        var groupUser = '${userInfo.userGroupBean.id}';
        var gender = '${userInfo.gender}';
        var active = '${userInfo.active}';
        
        var genderInput = $('input:radio[name=gender]');
        var activeInput = $('input:radio[name=active]');
        
        if(gender == 'M'){
            genderInput.filter('[value=M]').attr('checked', 'checked');
        }else {
            genderInput.filter('[value=F]').attr('checked', 'checked');
        }
        
        if(active == 1){
            activeInput.filter('[value=1]').attr('checked', 'checked');
        }else {
            activeInput.filter('[value=0]').attr('checked', 'checked');
        }
              
        getDepartment(clientId);
        $('select#selectClient option[value=' + clientId +']').attr('selected','selected');
        $('select#selectUserGroup option[value=' + groupUser +']').attr('selected','selected');
    }
    
    $('form#addUser')[0].reset();
    $('button#back').click(function(){        
        window.location.href = '/supportSys';
        return false;
    });

    function getDepartment(clientId){
        
        var mode = '${mode}';
        
        $.ajax({        	
            type: 'GET',
            dataType: 'json',
            url: '<c:url value="/getdepartmentlist/'+ clientId +'"/>',
            contentType : 'application/json; charset=utf-8',
            headers: { 
                'Accept': 'application/json',
                'Content-Type': 'application/json' 
            },
            success: function (data) {
               
               $('select#department').html('');
               $.each(data, function(key, value) {   
                   $('select#department')
                       .append($("<option></option>")
                                  .attr("value",key)
                                  .text(value.name)); 
              });
               
               if(mode == 'edit'){
                   
                   $('select#department option[value=' + departmentId +']').attr('selected','selected');
               }
             
               return true;
            },
            error: function (data) {
                return false;
            }
        });
    }
    
    $("select#selectClient").change(function(){

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
               
               $('select#department').html('');
               $.each(data, function(key, value) {   
                   $('select#department')
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
      var mode = '${mode}';
      var urlExec = "";
      var idItem = 		$("input#idItem").val();
      var hashItem = 	$("input#hashItemu").val();
      var hashSec = 	$("input#hashSec").val();
     
      var nameUser = 	$("input#nameUser").val();
      var sNameUser = 	$("input#sNameUser").val();
      var gender = 		$('input[name=gender]:checked', '#addUser').val();
      var active = 		$('input[name=active]:checked', '#addUser').val();
      var emailUser = 	$("input#emailUser").val();
      var selectClient = 	$("select#selectClient").val();
      var userGroup = 	$("select#selectUserGroup").val();
      var department = 	$("select#department").val();
      var resetPassword = 	$("input#resetPassword").val();
      var loaderSmall = 'Processando... <img src="<c:url value="/resources/images/loader.gif"/>" style="">';

      
		if(mode == "edit")
		{
			urlExec = '<c:url value="/users/edit"/>';
		}else{
		    
		    urlExec = '<c:url value="/users/add"/>';
		}
      
      if(emailUser == ''){                
          $('span.message-danger').append('<div class="alert alert-danger" role="alert"><strong>ATENÇÃO!</strong> Todos os campos são obrigatórios!</div>');
          return false;
      }
        
	  $("button#addUserExec").attr("disabled","disabled");
	  $("form#addUser :input").attr("disabled", true);
	  $('span#proccessloader').append(loaderSmall);
      
      var strFormJson = "{\"idItem\":\"" + idItem + "\",\"hashItem\":\""+ hashItem + "\",\"nameUser\":\""+ nameUser + "\",\"sNameUser\":\""+ sNameUser + "\",\"gender\":\""+ gender + "\",\"emailUser\":\""+ emailUser + "\",\"selectClient\":\""+ selectClient + "\",\"userGroup\":\""+ userGroup + "\",\"department\":\""+ department + "\",\"hashSec\":\""+ hashSec + "\",\"resetPassword\":\""+ resetPassword + "\",\"active\":\""+ active + "\"}"; 
      var setJson = JSON.stringify(strFormJson);
      
      console.log("String Json" + setJson);
      
        $.ajax({        	
            type: 'POST',
            dataType: 'json',
            url: urlExec,
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
            	   $('span.message').append('<div class="alert alert-danger" role="alert">Houve um erro de procesamento, usuário não foi registrado! <a href="/">(Clique para voltar)</a></div>');
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
						<input type="hidden" name="idItem" id="idItem" value="${userInfo.id}" />
						<input type="hidden" name="hashItem" id="hashItem" value="888" />
						<input type="hidden" name="hashSec" id="hashSec" value="${userInfo.idConfEmail}" />
						<!-- Form Name -->
						<legend>Novo usuário</legend>
						Formulário: ${mode} <br>
						
						<c:set var="mode" scope="session" value="${mode}" />
						<c:choose>
						<c:when test="${mode == 'edit'}">
							<c:set var="nomeUser" scope="session" value="${userInfo.name}" />
							<c:set var="snomeUser" scope="session" value="${userInfo.sname}" />
							<c:set var="gender" scope="session" value="${userInfo.gender}" />
							<c:set var="emailUser" scope="session" value="${userInfo.email}" />
							<c:set var="client" scope="session" value="${userInfo.department.clientBean.id}" />
							<c:set var="department" scope="session" value="${userInfo.department.id}" />
							<c:set var="groupUser" scope="session" value="${userInfo.userGroupBean.id}" />
							<option value="${listGroup.id}">${listGroup.name}</option>
						</c:when>
						<c:otherwise>
							<c:set var="nomeUser" scope="session" value="" />
							<c:set var="snomeUser" scope="session" value="" />
							<c:set var="gender" scope="session" value="" />
							<c:set var="emailUser" scope="session" value="" />
							<c:set var="client" scope="session" value="" />
							<c:set var="department" scope="session" value="" />
							<c:set var="groupUser" scope="session" value="" />
						</c:otherwise>
						</c:choose>
						
						<!-- Text input-->
						<div class="form-group">
						  <label class="col-md-4 control-label" for="textinput">Nome</label>  
						  <div class="col-md-4">
						  <input id="nameUser" name="textinput" type="text" placeholder="name" class="form-control" style="font-size:16px; font-weight: bold;" value="${nomeUser}">
						  </div>
						</div>
						
						<!-- Text input-->
						<div class="form-group">
						  <label class="col-md-4 control-label" for="textinput">Sobrenome</label>  
						  <div class="col-md-4">
						  <input id="sNameUser" name="textinput" type="text" placeholder="sobrenome" class="form-control" style="font-size:16px; font-weight: bold;" value="${snomeUser}">
						  </div>
						</div>
						
						<!-- Multiple Radios -->
						<div class="form-group">
						  <label class="col-md-4 control-label" for="radios">Sexo</label>
						  <div class="col-md-4">
						  <div class="radio">
						    <label for="radios-0">
						      <input type="radio" name="gender" class="gender" id="radios-0" value="M">
						      Masculino
						    </label>
							</div>
						  <div class="radio">
						    <label for="radios-1">
						      <input type="radio" name="gender" class="gender" id="radios-1" value="F">
						      Feminino
						    </label>
							</div>
						  </div>
						</div>
						
						<!-- Text input-->
						<div class="form-group">
						  <label class="col-md-4 control-label" for="textinput">E-mail</label>  
						  <div class="col-md-4">
						  <input id="emailUser" name="emailUser" type="text" placeholder="email" class="form-control input-md" value="${emailUser}">
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
						  <label class="col-md-4 control-label" for="selectbasic">Departamento</label>
						  <div class="col-md-4">
						    <select id="department" name="department" class="form-control">
						      <option value="0">Selecione</option>
						    </select>
						  </div>
						</div>
						
						<div class="form-group">
						  <label class="col-md-4 control-label" for="selectbasic">Grupo</label>
						  <div class="col-md-4">
						    <select id="selectUserGroup" name="selectUserGroup" class="form-control">
						      <option value="0">Selecione</option>
	                              <c:forEach items="${groupList}" var="listGroup">
	                              	<option value="${listGroup.id}">${listGroup.name}</option>
	                              </c:forEach>
						    </select>
						  </div>
						</div>
						<div class="form-group">
						<label class="col-md-4 control-label">Reset Password?</label>
							<div class="col-md-4" style="padding-top: 10px;">
								<input type="checkbox" id="resetPassword" value="true"> <span data-toggle="tooltip" title="" style="position:relative; top:-3px;" class="badge bg-blue" data-original-title="Marked checkbox reset password to default">?</span>
							</div>
						</div>
						<!-- Multiple Radios -->
						<div class="form-group">
						  <label class="col-md-4 control-label" for="radios">Status</label>
						  <div class="col-md-4">
						  <div class="radio">
						    <label for="radios-3">
						      <input type="radio" name="active" class="active" id="radios-3" value="1">
						      Ativo
						    </label>
							</div>
						  <div class="radio">
						    <label for="radios-4">
						      <input type="radio" name="active" class="active" id="radios-4" value="0">
						      Inativo
						    </label>
							</div>
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
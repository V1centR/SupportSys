<jsp:include page="header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
$(document).ready(function () {
	    
    
    var mode = '${mode}';
    var urlExec = '';
    console.log("Mode:: " + mode);
    
    if(mode == 'edit')
    {
        
        var stateSelected = '${clientInfo.ufBean.id}';
        var levelSelect = '${clientInfo.level}';
        var activeClient = '${clientInfo.active}';
        var activeInput = $('input:radio[name=active]');
        
        if(activeClient == '1'){
            activeInput.filter('[value=1]').attr('checked', 'checked');
        }else {
            
            activeInput.filter('[value=0]').attr('checked', 'checked');
        }
        
        $('select#selectState option[value=' + stateSelected +']').attr('selected','selected');
        $('select#levelSelect option[value=' + levelSelect +']').attr('selected','selected');
    }
    
   // $('form#addUser')[0].reset();
    $('button#back').click(function(){        
        window.location.href = '/supportSys';
        return false;
    });

    
    $('button#addClientExec').click(function () {

      $('span.message-danger').html("");
      
      /*
      	nameClient
		addressClient
		districtClient
		selectState
		cnpjClient
		phoneClient
		logoImage
		emailClient
		levelSelect
		active
		resetPassword
		description
	*/
      
      var idItem = 		$("input#idItem").val();
      var clientName = 		$("input#nameClient").val();
      var hashSec = 	$("input#hashSec").val();     
      var addressClient = 	$("input#addressClient").val();
      var districtClient = 	$("input#districtClient").val();
      var clientCity = 	$("input#clientCity").val();
      var selectState = 	$("select#selectState").val();
      var cnpjClient = 	$("input#clientCnpj").val();
      var phoneClient = 	$("input#phoneClient").val();
      var logoImage = 	$("input#logoImage").val();
      var emailClient = 	$("input#emailClient").val();
      var levelSelect = 	$("input#levelSelect").val();
      var active = 	$('input[name=active]:checked', '#active').val();
      var resetPassword = 	$('input[name=resetPassword]:checked', '#resetPassword').val();
      var description = 	$("textarea#description").val();

      var loaderSmall = 'Processando... <img src="<c:url value="/resources/images/loader.gif"/>" style="">';


	  $("button#addClientExec").attr("disabled","disabled");
	  $("form#addClient :input").attr("disabled", true);
	  $('span#proccessloader').append(loaderSmall);
      
      var strFormJson = "{\"idItem\":\"" + idItem + "\",\"clientName\":\""+ clientName + "\",\"addressClient\":\""+ addressClient + "\",\"districtClient\":\""+ districtClient + "\",\"clientCity\":\""+ clientCity + "\",\"selectState\":\""+ selectState + "\",\"cnpjClient\":\""+ cnpjClient + "\",\"phoneClient\":\""+ phoneClient + "\",\"emailClient\":\""+ emailClient + "\",\"logoImage\":\""+ logoImage + "\",\"levelSelect\":\""+ levelSelect + "\",\"resetPassword\":\""+ resetPassword + "\",\"active\":\""+ active + "\",\"description\":\""+ description + "\",\"resetPassword\":\""+ resetPassword + "\"}"; 
      var setJson = JSON.stringify(strFormJson);
      
      console.log("String Json" + setJson);
      
		if(mode == "edit")
		{
			urlExec = '<c:url value="/client/edit"/>';
		}else{
		    
		    urlExec = '<c:url value="/client/add"/>';
		}
      
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
              
               return false;
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
					Clientes <small>novo cliente</small>
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
					<form class="form-horizontal" id="addClient">
						<fieldset>
						<input type="hidden" name="idItem" id="idItem" value="${clientInfo.id}" />
						<input type="hidden" name="hashItem" id="hashItem" value="888" />
						<input type="hidden" name="hashSec" id="hashSec" value="" />
						<!-- Form Name -->
						<legend>Cadastrar Clientes</legend>
						Formulário: ${mode} <br>
						
						<c:set var="mode" scope="session" value="${mode}" />
						<c:choose>
						<c:when test="${mode == 'edit'}">
							<c:set var="clientName" scope="session" value="${clientInfo.name}" />
							<c:set var="clientPhone" scope="session" value="${clientInfo.phone}" />
							<c:set var="clientPhoneB" scope="session" value="${userInfo.phoneB}" />
							<c:set var="clientCnpj" scope="session" value="${clientInfo.cnpj}" />
							<c:set var="clientEmail" scope="session" value="${clientInfo.email}" />
							<c:set var="clientAddress" scope="session" value="${clientInfo.address}" />
							<c:set var="clientDistrict" scope="session" value="${clientInfo.bairro}" />
							<c:set var="clientCity" scope="session" value="${clientInfo.city}" />
							<c:set var="clientStateId" scope="session" value="${clientInfo.ufBean.id}" />
							<c:set var="clientState" scope="session" value="${clientInfo.ufBean.sign}" />
							<c:set var="clientLevel" scope="session" value="${clientInfo.level}" />
							<c:set var="clientDescription" scope="session" value="${clientInfo.description}" />
							<c:set var="clientLogo" scope="session" value="${clientInfo.image.id}'.'${clientInfo.image.ext}" />
							<c:set var="clientActive" scope="session" value="${clientInfo.active}" />
						</c:when>
						<c:otherwise>
							<c:set var="clientName" scope="session" value="" />
							<c:set var="clientPhone" scope="session" value="" />
							<c:set var="clientPhoneB" scope="session" value="" />
							<c:set var="clientEmail" scope="session" value="" />
							<c:set var="clientAddress" scope="session" value="" />
							<c:set var="clientDistrict" scope="session" value="" />
							<c:set var="clientCity" scope="session" value="" />
							<c:set var="clientStateId" scope="session" value="" />
							<c:set var="clientState" scope="session" value="" />
							<c:set var="clientLevel" scope="session" value="" />
							<c:set var="clientDescription" scope="session" value="" />
							<c:set var="clientLogo" scope="session" value="" />
							<c:set var="clientActive" scope="session" value="" />
						</c:otherwise>
						</c:choose>
						
						<!-- Text input-->
						<div class="form-group">
						  <label class="col-md-4 control-label" for="textinput">Nome (Razão Social)</label>  
						  <div class="col-md-4">
						  <input id="nameClient" name="textinput" type="text" placeholder="name" class="form-control" style="font-size:16px; font-weight: bold;" value="${clientName}">
						  </div>
						</div>
						
						<!-- Text input-->
						<div class="form-group">
						  <label class="col-md-4 control-label" for="textinput">Endereço</label>  
						  <div class="col-md-4">
						  <input id="addressClient" name="textinput" type="text" placeholder="sobrenome" class="form-control" value="${clientAddress}">
						  </div>
						</div>
						<!-- Text input-->
						<div class="form-group">
						  <label class="col-md-4 control-label" for="textinput">Bairro</label>  
						  <div class="col-md-4">
						  <input id="districtClient" name="textinput" type="text" placeholder="Bairro" class="form-control" value="${clientDistrict}">
						  </div>
						</div>
						<!-- Text input-->
						<div class="form-group">
						  <label class="col-md-4 control-label" for="textinput">Cidade</label>  
						  <div class="col-md-4">
						  <input id="clientCity" name="textinput" type="text" placeholder="Bairro" class="form-control" value="${clientCity}">
						  </div>
						</div>
						
						<!-- Select Basic -->
						<div class="form-group">
						  <label class="col-md-4 control-label" for="selectbasic">Estado</label>
						  <div class="col-md-4">
						    <select id="selectState" name="selectState" class="form-control">
						      <option value="0">Selecione</option>
	                              <c:forEach items="${statesList}" var="stList">
	                              	<option value="${stList.id}">${stList.sign}</option>
	                              </c:forEach>
						    </select>
						  </div>
						</div>
						
						
						<!-- Text input-->
						<div class="form-group">
						  <label class="col-md-4 control-label" for="textinput">CNPJ</label>  
						  <div class="col-md-4">
						  <input id="clientCnpj" name="textinput" type="text" placeholder="sobrenome" class="form-control" style="font-size:16px;" value="${clientCnpj}">
						  </div>
						</div>
						
						<!-- Text input-->
						<div class="form-group">
						  <label class="col-md-4 control-label" for="textinput">Telephone</label>  
						  <div class="col-md-4">
						  <input id="phoneClient" name="textinput" type="text" placeholder="sobrenome" class="form-control" value="${clientPhone}">
						  </div>
						</div>
						
						<!-- Text input-->
						<div class="form-group">
						  <label class="col-md-4 control-label" for="textinput">Logotipo</label> <span data-toggle="tooltip" title="" style="position:relative; top:7px;" class="badge bg-blue" data-original-title="Images: JPG, JPEG, PNG, GIF">?</span>
						  <div class="col-md-4">
						  <input id="logoImage" name="fileinput" type="file" placeholder="logo" style="font-size:16px; font-weight: bold;" value="${snomeUser}">
						  </div>
						</div>
						
						<!-- Text input-->
						<div class="form-group">
						  <label class="col-md-4 control-label" for="textinput">E-mail responsável</label>  
						  <div class="col-md-4">
						  <input id="emailClient" name="emailUser" type="text" placeholder="email" class="form-control input-md" value="${clientEmail}">
						  </div>
						</div>
						
						<!-- Select Basic -->
						<div class="form-group">
						  <label class="col-md-4 control-label" for="selectbasic">Prioridade</label><span data-toggle="tooltip" title="" style="position:relative; top:7px;" class="badge bg-blue" data-original-title="Priority of client by level 1 - 10">?</span>
						  <div class="col-md-4">
						    <select id="levelSelect" name="levelSelect" class="form-control">
						      <option value="0">Selecione</option>
	                          <option value="1">1</option>
	                          <option value="2">2</option>
	                          <option value="3">3</option>
	                          <option value="4">4</option>
	                          <option value="5">5</option>
	                          <option value="6">6</option>
	                          <option value="7">7</option>
	                          <option value="8">8</option>
	                          <option value="9">9</option>
	                          <option value="10">10</option>
						    </select>
						    
						  </div>
						</div>
						
						<!-- Multiple Radios -->
						<div class="form-group">
						  <label class="col-md-4 control-label" for="radios">Empresa Ativa?</label>
						  <div class="col-md-4">
						  <div class="radio">
						    <label for="radios-0">
						      <input type="radio" name="active" class="gender" id="radios-0" value="1">
						      Sim
						    </label>
						    <label for="radios-1">
						      <input type="radio" name="active" class="gender" id="radios-1" value="0">
						      Não
						    </label>
							</div>
						  </div>
						</div>
						<div class="form-group">
						<label class="col-md-4 control-label">Reset Password?</label>
							<div class="col-md-4" style="padding-top: 10px;">
								<input type="checkbox" id="resetPassword" value="true"> <span data-toggle="tooltip" title="" style="position:relative; top:-3px;" class="badge bg-blue" data-original-title="Marked checkbox reset password to default">?</span>
							</div>
						</div>
						<!-- Text input-->
						<div class="form-group">
						  <label class="col-md-4 control-label" for="textinput">Descrição</label>  
						  <div class="col-md-4">
						  <textarea id="description" name="textinput" type="text" placeholder="sobrenome" class="form-control">${clientDescription}</textarea>
						  </div>
						</div>
						
						<!-- Button -->
						<div class="form-group">
						  <label class="col-md-4 control-label" for="singlebutton">Novo Cliente</label>
						  <div class="col-md-4">
						  	<button id="back" name="singlebutton" class="btn btn-primary"><i class="fa fa-close"></i> Cancelar</button>
						    <button id="addClientExec" name="singlebutton" class="btn btn-primary"><i class="fa fa-user-plus"></i> Add new client</button>
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
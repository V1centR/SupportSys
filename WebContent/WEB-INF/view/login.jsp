<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>SupportSys :: Java, JPA, Hibernate</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">

<!-- Bootstrap 3.3.7 -->
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/bower_components/bootstrap/dist/css/bootstrap.min.css"/>">

<!-- Font Awesome -->
<link rel="stylesheet"
	href="<c:url value="/resources/bower_components/font-awesome/css/font-awesome.min.css"/>">
<!-- Ionicons -->
<link rel="stylesheet"
	href="<c:url value="/resources/bower_components/Ionicons/css/ionicons.min.css"/>">
<link rel="stylesheet"
	href="<c:url value="/resources/plugins/iCheck/square/blue.css"/>">
<link rel="stylesheet"
	href="<c:url value="/resources/dist/css/AdminLTE.min.css"/>">
	<script src="<c:url value="/resources/bower_components/jquery/dist/jquery.min.js"/>"></script>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

<!-- Google Font -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">

<script>
$(document).ready(function () {
	
    $('form.login')[0].reset();
    /*
    $('button#back').click(function(){        
        window.location.href = '/supportSys';
    }); */
    
    $('#send').click(function () {       
        var formMode = 		$("input#formMode").val();
        var client = 		$("input#client").val();
        
        var email = $("input#email").val();
        var pass = 		$("input#pass").val();
        var token = 		$("input#token").val();
        var setLoggedIn = 	$('input[name="setLoggedIn"]:checked').val();
        
        
//        var helpLabel = $('input[name="situacao"]:checked').val();
        
//        if(helpLabel == '' || description == '' || category == 0 || department_id == 0){                
//            $('span.message-danger').append('<div class="alert alert-danger" role="alert"><strong>ATENÇÃO!</strong> Todos os campos são obrigatórios!</div>');
//            return false;
//        }
        
	    $("button.btn-send").attr("disabled","disabled");
	    $('span.loader').append('<img src="../resources/images/loader.gif">');
      
      var strFormJson = "{\"email\":\"" + email + "\",\"pass\":\""+ pass + "\",\"setLoggedIn\":\""+ setLoggedIn + "\",\"token\":\""+ token + "\"}";  
      
      console.log(strFormJson);
      
      var setJson = JSON.stringify(strFormJson);
      $("form.login :input").attr("disabled", true);
        $.ajax({        	
            type: 'POST',
            dataType: 'json',
            url: './authuser',
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
            	   $('span.message').append('<div class="alert alert-success" role="alert">Login efetuado com sucesso!</div>');
               }
               if(data == 500){
            	   $('span.message').append('<div class="alert alert-danger" role="alert">Login ou senha inválidos</div>');
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

</head>
<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
			<a href="../../index2.html"><b>Support:</b>:Sys</a>
		</div>
		<!-- /.login-logo -->
		<div class="login-box-body">
			<p class="login-box-msg">Acesso ao sistema</p>
			<span class="message"></span>
			<form class="login" method="POST">
				<div class="form-group has-feedback">
					<input type="hidden" name="token" id="token" value="000888">
					<input type="email" name="email" id="email" class="form-control" placeholder="Email" value="laurabct@asspm.org.br">
					<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="password" name="pass" id="pass" class="form-control" placeholder="Password" value="123123">
					<span class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div class="row">
					<div class="col-xs-8">
						<div class="checkbox icheck">
							<label> <input type="checkbox" name="setLoggedIn" checked="checked"> Manter logado
							</label>
						</div>
					</div>
					<!-- /.col -->
					<div class="col-xs-4">
						<button type="button" id="send" class="btn btn-primary btn-block btn-flat">Sign
							In</button>
					</div>
					<!-- /.col -->
				</div>
			</form>

			<div class="social-auth-links text-center">
				<p>- Ou -</p>
				<a href="#" class="btn btn-block btn-social btn-facebook btn-flat"><i
					class="fa fa-facebook"></i> Logar com Facebook</a> <a href="#"
					class="btn btn-block btn-social btn-google btn-flat"><i
					class="fa fa-google-plus"></i> Logar com Google+</a>
			</div>
			<!-- /.social-auth-links -->
			<a href="#">Esqueci a senha</a><br>
			

		</div>
		<!-- /.login-box-body -->
	</div>
	<!-- /.login-box -->
	<!-- Bootstrap 3.3.7 -->
	<script
		src="<c:url value="/resources/bower_components/bootstrap/dist/js/bootstrap.min.js"/>"></script>
	<!-- iCheck -->
	<script src="<c:url value="/resources/plugins/iCheck/icheck.min.js"/>"></script>
	<script>
  $(function () {
    $('input').iCheck({
      checkboxClass: 'icheckbox_square-blue',
      radioClass: 'iradio_square-blue',
      increaseArea: '20%' // optional
    });
  });
</script>
</body>
</html>
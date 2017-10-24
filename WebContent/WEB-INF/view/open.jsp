<%@page import="java.sql.ResultSet"%>
<jsp:include page="header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script>
$(document).ready(function () {
	
	
	$("span#updateButton").append('<button id="send" type="button" name="btn-update" id="btnUpdate" class="btn btn-primary btn-lg btn-send"><i class="fa fa-floppy-o"></i> Atualizar</button>');
	
	$("select#statusHelp").change(function(){
		
		if($(this).val() != 3){
			$("span.textAreaSolution").html("");
			$("span#cancelButton").html("");
			$("span#updateButton").html("");
			$("span#updateButton").append('<button id="send" type="button" name="btn-update" id="btnUpdate" class="btn btn-primary btn-lg btn-send"><i class="fa fa-floppy-o"></i> Atualizar</button>');
		}
		
		if($(this).val() == 3){
			$("span#updateButton").html("");
			$("span.textAreaSolution").html("");
			$("span#cancelButton").html("");
			$("span#updateButton").append('<button id="send" type="button" name="btn-update" id="btnUpdate" class="btn btn-primary btn-lg btn-send"><i class="fa fa-floppy-o"></i> Atualizar</button>');
			$("span.textAreaSolution").append('<label>Solução:</label><br><textarea style="width: 100%; height: 100px;" id="textAreaSolution"></textarea>');
		}
		
		//4 default value cancel help
		if($(this).val() == 4){
			$("span.textAreaSolution").html("");
			alert("Atenção! Cancelar um chamado ocorre perca de score");
			$("span#updateButton").html("");
			$("span#cancelButton").append('<button id="send" type="button" name="btn-cancel" id="btnCancel" class="btn btn-danger btn-lg btn-send"><i class="fa fa-times"></i> Cancelar</button>');
		}
	});
	
	$("select#statusHelp").change(function(){
		
		console.log("Item selected");
	});
	
	var statusTEste = 		$("select#statusHelp option:selected").val();
	console.log(statusTEste);
	
	
	$("button#send").click(function(){
		
		
		
		//var statusHelp = 		$("select#statusHelp option:selected").val();
		//var supportUser = 		$("select#supportUser option:selected").val();
	    //var solutionTxt = 		$("textarea#textAreaSolution").val();
	   // var idItem = 	$("input[type=hidden]#idItem").val();
	    var hashItem = 	$("input:hidden[id=hashItem]").val();
	       
	   // console.log(idItem);
	    console.log(hashItem);
      	
      	console.log("values catched");
      	
      	/*
      	
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
        }); //final $.ajax */
		
	}); //final .click()
	
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
					Blank page <small>it all starts here</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
					<li><a href="#">Examples</a></li>
					<li class="active">Blank page</li>
				</ol>
			</section>


	<!-- Main content -->
	<section class="content">
	
		<div class="col-md-6">
		<div class="box box-widget">
            <div class="box-header with-border">
              <div class="user-block">
              <c:forEach items="${dataItem}" var="helpItem">
              	<img class="img-circle" src="../dist/img/user1-128x128.jpg" alt="User Image">
              	<span class="username"><a href="#">${helpItem.user.name} ${helpItem.user.sname}</a></span>
              	<div style="margin-left: 50px;"><i class="fa fa-map-marker"></i> <span font-weight: bold;">${helpItem.department.clientBean.name} - ${helpItem.department.name}</span></div>
                <span class="description">
					Aberto em: <fmt:formatDate value="${helpItem.dateHelp}" pattern="dd/MM/yyyy HH:mm"/> 
					<i class="fa fa-star" style="color:#FFBF00;"></i>
					<i class="fa fa-bomb" style="color:#000;"></i>
					<i class="fa fa-birthday-cake" style="color:#f00;"></i>
					<i class="fa fa-hourglass-end" style="color:#FF4000;"></i> 
				</span>
					<hr>
					<i class="fa fa-warning"></i> Assunto: <span style="font-size: 18px; font-weight: bold;">${helpItem.helpLabel}</span> <br>
					<i class="fa fa-file-text"></i> Descrição: <br>
					
					<div class="box-body">
						<span style="font-size: 18px;">${helpItem.helpTxt} </span>
					</div>
						
					</c:forEach>
              </div>
              
            </div>
            
            <hr>
            
            
            <!-- /.box-body -->
            <div class="box-footer box-comments">
              <div class="box-comment">
                <!-- User image -->
                <img class="img-circle img-sm" src="../dist/img/user3-128x128.jpg" alt="User Image">

                <div class="comment-text">
                      <span class="username">
                        Maria Gonzales
                        <span class="text-muted pull-right">8:03 PM Today</span>
                      </span>
                  <!-- /.username -->
                  It is a long established fact that a reader will be distracted
                  by the readable content of a page when looking at its layout.
                </div>
                <!-- /.comment-text -->
              </div>
              <!-- /.box-comment -->
              <div class="box-comment">
                <!-- User image -->
                <img class="img-circle img-sm" src="../dist/img/user5-128x128.jpg" alt="User Image">

                <div class="comment-text">
                      <span class="username">
                        Nora Havisham
                        <span class="text-muted pull-right">8:03 PM Today</span>
                      </span><!-- /.username -->
                  The point of using Lorem Ipsum is that it has a more-or-less
                  normal distribution of letters, as opposed to using
                  'Content here, content here', making it look like readable English.
                </div>
                <!-- /.comment-text -->
              </div>
              <!-- /.box-comment -->
            </div>
            <!-- /.box-footer -->
            <div class="box-footer">
              <form action="#" method="post">
                <img class="img-responsive img-circle img-sm" src="../dist/img/user4-128x128.jpg" alt="Alt Text">
                <!-- .img-push is used to add margin to elements next to floating images -->
                <div class="img-push">
                  <input type="text" class="form-control input-sm" placeholder="Press enter to post comment">
                </div>
              </form>
            </div>
            <!-- /.box-footer -->
          </div>
	</div>
		<div class="col-md-6">
          <!-- Box Comment -->
          <div class="box box-widget">
            <div class="box-header with-border">
              <h3 class="box-title">Alterar chamado</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
				<form name="alterHelp">
				<input type="hidden" name="idItem" id="idItem" value="${idItem}" />
				<input type="hidden" name="hashItem" id="hashItem" value="${hashItem}" />
					<label>Status</label>
					<select name="statusHelp" id="statusHelp" class="form-control">
						<option value="0">Selecione</option>
						<c:forEach items="${statusList}" var="statusItem">
							<option value="${statusItem.id}">${statusItem.name}</option>
						</c:forEach>
					</select>
					
					<label>Analista</label>
					<select name="supportUser" id="supportUser" class="form-control">
						<option value="0">Selecione</option>
						<c:forEach items="${listSupportUsers}" var="listSupport">
							<option value="${listSupport.id}">${listSupport.name}</option>
						</c:forEach>
					</select><br>
					
					
					<textarea style="width: 100%; height: 100px;" id="textAreaSolution"></textarea>
					
					<div style="margin-top: 8px; text-align: right;">
					<button id="send" type="button" name="btn-back" class="btn btn-primary btn-lg btn-send"><i class="fa fa-arrow-left"></i>  Voltar</button>
					<span id="loader"></span>
					
					<span id="updateButtonAAA"></span>
					<button id="send" type="button" name="btn-update" id="btnUpdate" class="btn btn-primary btn-lg btn-send"><i class="fa fa-floppy-o"></i> Atualizar Fixo</button>
					<span id="cancelButton"></span>
					</div>
				</form>

            </div>
            <!-- /.box-body -->
            
            <!-- /.box-footer -->
            <div class="box-footer">
              

            </div>
            <!-- /.box-footer -->
          </div>
          <!-- /.box -->
        </div>
		
			
	<!-- Chamado ##################### -->
			
	</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
		<jsp:include page="footer.jsp" />
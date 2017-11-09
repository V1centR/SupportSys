<%@page import="java.sql.ResultSet"%>
<jsp:include page="header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script>
$(document).ready(function () {
    
    getChat(false,0);
    $('div#divChat').scrollTop($('#divChat')[0].scrollHeight);
    var loaderBig = '<div style="width:100%; text-align:center;">Carregando chat...<br><img src="<c:url value="/resources/images/loaderBig.gif"/>" style="width:100px; height:100px;"></div>';
    $('span#loader').append(loaderBig);
    
	$("span#textAreaSolutionHideShow").hide();
	$("span#textAreaCancelReportHideShow").hide();
	$("span#btnSendSw").show();
	$("span#btnCancel").hide();
	$("select#statusHelp").change(function(){
		
		if($(this).val() != 3){
			$("span.textAreaSolution").html("");
			$("span#btnCancel").hide();
			$("span#updateButton").html("");
			$("span#btnSendSw").show();
			$("span#textAreaSolutionHideShow").hide();
			$("span#textAreaCancelReportHideShow").hide();
		}

		if($(this).val() == 3){
			$("span#updateButton").html("");
			$("span.textAreaSolution").html("");
			$("span#btnCancel").hide();
			$("span#btnSendSw").show();
			$("span#textAreaSolutionHideShow").show();
			$("span#textAreaCancelReportHideShow").hide();
		}
		
		if($(this).val() == 4){
			$("span.textAreaSolution").html("");
			alert("Atenção! Cancelar um chamado ocorre perca de score");
			$("span#btnSendSw").hide();
			$("span#btnCancel").show();
			$("span#textAreaCancelReportHideShow").show();
		}
	});
	
	var statusTEste = $("select#statusHelp option:selected").val();
	$("button#send").click(function(){
		
		var statusHelp = 		$("select#statusHelp option:selected").val();
		var supportUser = 		$("select#supportUser option:selected").val();
	    var solutionTxt = 		$("textarea#textAreaSolution").val();
	    var idItem = 	$("input[type=hidden]#idItem").val();
	    var hashItem = 	$("input:hidden[id=hashItem]").val();
	    var cancelTxt = $("textarea#textAreaCancelReport").val();    
	    var setTxt = "";
	    
	    if(statusHelp == 4){
	    	var canceled = true;
	    	setTxt = cancelTxt;
	    }else{
	    	var canceled = false;
	    	setTxt = solutionTxt;
	    }
	    
		$("form.addhelpForm :input").attr("disabled", true);
		$("button.btn-send").attr("disabled","disabled");
		
    
		var strFormJson = "{\"statusHelp\":\"" + statusHelp + "\",\"supportUser\":\""+ supportUser + "\",\"setTxt\":\""+ setTxt + "\",\"idItem\":\""+ idItem + "\",\"hashItem\":\""+ hashItem + "\",\"canceled\":\""+ canceled + "\"}";  
    
    	var setJson = JSON.stringify(strFormJson);
    	      
        $.ajax({        	
            type: 'POST',
            dataType: 'json',
            url: '<c:url value="/help/update"/>',
            data: setJson,
            contentType : 'application/json; charset=utf-8',
            headers: { 
                'Accept': 'application/json',
                'Content-Type': 'application/json' 
            },
            success: function (data) {              
               $('span#loader').hide();
               $('#modal-success').modal('show');
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
	
	$("input[type=text]#postChat").keypress(function(e){
	    if(e.which == 13){	        
	        var msgTxt = $("input[type=text]#postChat").val();
	        $("input[type=text]#postChat").val('');
	        getChat(msgTxt,1);
	        return false;
	    }
	});

	function getChat(msgTxt,setMode)
	{
        var idItem = $("input[type=hidden]#idItem").val();
        var hashItem = $("input[type=hidden]#hashItem").val();
        var setMsgJson = "{\"msgTxt\":\"" + msgTxt + "\",\"idItem\":\""+ idItem + "\",\"hashItem\":\""+ hashItem + "\",\"setMode\":\""+ setMode + "\"}";
        var setJson = JSON.stringify(setMsgJson);
       
	    $.ajax({        	
            type: 'POST',
            dataType: 'xml',
            url: '<c:url value="/chat/'+ idItem +'/'+ hashItem +'"/>',
            data: setJson,
            contentType : 'application/json; charset=utf-8',
            headers: { 
                'Accept': 'application/json',
                'Content-Type': 'application/json' 
            },
            success: function (data) {
               var xmlFile = data;
               $("span#boxComment").html('');
               chating(xmlFile);
               return true;
            },
            error: function (data) {
                console.log(data);
                return false;
            }
        });
	}
	
	function chating(xmlData)
	{
	    var baseUrl = '<c:url value="/"/>';
	    
	    $(xmlData).find("userMsg").each(function(){
            var userName = $(this).find("userName").text();
            var avatarUser = $(this).find("avatar").text();
            var msgUser = $(this).find("msg").text();
            var dateMsg = $(this).find("date").text();
            
            $("span#boxComment").append('\
                    <div class="box-comment">\
                    <img class="img-circle img-sm" src="<c:url value="/resources/images/'+avatarUser+'"/>" alt="User Image">\
                    <div class="comment-text">\
                          <span class="username">'+ userName +'<span class="text-muted">&nbsp;&nbsp; '+ dateMsg +'</span></span>\
                      '+ msgUser +'\
                    </div>\
                  </div>');
	    });
	    $('span#loader').html('');
	    $('div#divChat').scrollTop($('#divChat')[0].scrollHeight);
	}
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
		<!-- modal success  -->
		<div class="modal fade" id="modal-success" style="display: none;">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">×</span></button>
                <h4 class="modal-title">Chamado atualizado</h4>
              </div>
              <div class="modal-body">
                <p>Chamado atualizado com sucesso</p>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal">Fechar</button>
              </div>
            </div>
            <!-- /.modal-content -->
          </div>
          <!-- /.modal-dialog -->
        </div>
        <!-- / modal success  -->
		
		
		<div class="box box-widget">
            <div class="box-header with-border">
              <div class="user-block">
              
              	<img class="img-circle" src="../dist/img/user1-128x128.jpg" alt="User Image">
              	<span class="username"><a href="#">${dataItem.user.name} ${dataItem.user.sname}</a></span>
              	<div style="margin-left: 50px;"><i class="fa fa-map-marker"></i> <span font-weight: bold;">${dataItem.department.clientBean.name} - ${dataItem.department.name}</span></div>
                <span class="description">
					Aberto em: <fmt:formatDate value="${dataItem.dateHelp}" pattern="dd/MM/yyyy HH:mm"/> 
					<i class="fa fa-star" style="color:#FFBF00;"></i>
					<i class="fa fa-bomb" style="color:#000;"></i>
					<i class="fa fa-birthday-cake" style="color:#f00;"></i>
					<i class="fa fa-hourglass-end" style="color:#FF4000;"></i> 
				</span>
					<hr>
					<i class="fa fa-warning"></i> Assunto: <span style="font-size: 15px; font-weight: bold;">${dataItem.helpLabel}</span> <br>
					<i class="fa fa-check-circle-o"></i> Tipo: <span style="font-size: 15px; font-weight: bold;">${dataItem.typeHelpBean.name}</span> <br><br>
					<i class="fa fa-file-text"></i> Descrição: <span style="font-size: 18px;">${dataItem.helpTxt} </span>
					<div class="box-body">
						
					</div>
						
              </div>
            </div>
            <hr>
            <!-- /.box-body -->
            <span class="description">
            	 &nbsp;&nbsp; &nbsp;<i class="fa fa-comments"></i> Acompanhamento:
            </span>
            
            <div class="box-footer box-comments" id="divChat" style="height:300px; overflow-y: scroll;">
	            <!-- chat box ############## -->
	            <span id="boxComment"></span>
	            <span id="loader"></span>
            </div>
            
            <!-- /.box-footer -->
            <div class="box-footer">
              <form action="#" method="post">
                <img class="img-responsive img-circle img-sm" src="../dist/img/user4-128x128.jpg" alt="Alt Text">
                <!-- .img-push is used to add margin to elements next to floating images -->
                <div class="img-push">
                  <input type="text" id="postChat" class="form-control input-sm" placeholder="Press enter to post comment">
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
					<label>Analista</label>
					<c:set var="supportUserAdded" scope="session" value="${supportUserAdded}"/>
					<select name="supportUser" id="supportUser" class="form-control">
						<option value="0">Selecione</option>
						<c:forEach items="${listSupportUsers}" var="listSupport">
							<c:if test="${supportUserAdded == listSupport.id }">
								<c:set var="setUser" scope="session" value="selected"/>
							</c:if>
							<c:if test="${supportUserAdded == 0 }">
								<c:set var="setUser" scope="session" value=""/>
							</c:if>
							<option value="${listSupport.id}" ${setUser}>${listSupport.name} ${listSupport.sname}</option>
							<c:set var="setUser" scope="session" value=""/>
						</c:forEach>
					</select>
					
					<label>Status</label>
					<c:set var="statusAdded" scope="session" value="${statusAdded}"/>
					<select name="statusHelp" id="statusHelp" class="form-control">
						<option value="0">Selecione</option>
						<c:forEach items="${statusList}" var="statusItem">
							<c:if test="${statusItem.id == statusAdded}">
								<c:set var="setStatus" scope="session" value="selected"/>
							</c:if>
							<option value="${statusItem.id}" ${setStatus}>${statusItem.name}</option>
							<c:set var="setStatus" scope="session" value=""/>
						</c:forEach>
					</select><br>
					
					<span id="textAreaSolutionHideShow" style="display:none;">
						<label>Solução:</label>
						<textarea style="width: 100%; height: 100px;" id="textAreaSolution"></textarea>
					</span>
					
					<span id="textAreaCancelReportHideShow" style="display:none;">
					<label>Motivo do cancelamento:</label>
						<textarea style="width: 100%; height: 100px; color:#f00;" id="textAreaCancelReport"></textarea>
					</span>
					
					<div style="margin-top: 8px; text-align: right;">
						<button id="back" type="button" name="btn-back" class="btn btn-primary btn-lg btn-send"><i class="fa fa-arrow-left"></i>  Voltar</button>
						<span id="loader"></span>
						<span id="btnSendSw" style="display:none;">
							<button id="send" type="button" name="btn-update" class="btn btn-primary btn-lg btn-send"><i class="fa fa-floppy-o"></i> Atualizar Fixo</button>
						</span>
						<span id="btnCancel" style="display:none;">
							<button type="button" name="btn-cancel" id="send" class="btn btn-danger btn-lg btn-send"><i class="fa fa-times"></i> Cancelar</button>
						</span>
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
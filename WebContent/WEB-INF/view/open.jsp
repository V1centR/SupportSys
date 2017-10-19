<%@page import="java.sql.ResultSet"%>
<jsp:include page="header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
					<i class="fa fa-star"></i>
					<i class="fa fa-bomb" style="color:#000;"></i>
					<i class="fa fa-birthday-cake" style="color:#f00;"></i>
					<i class="fa fa-hourglass-end"></i> 
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
					<label>Status</label>
					<select name="alterHelp" id="alterHelp">
						<option>Em execução</option>
						<option>Em espera</option>
						<option>Concluído</option>
						<option>Cancelado !ATENÇÃO</option>
					</select>
					
					<label>Analista</label>
					<select name="alterHelp" id="alterHelp">
						<option>Vicente .R</option>
						<option>Eliezer Souza</option>
						<option>Felipe .A</option>
						<option>Gildo .PN</option>
					</select><br>
					
					<label>Solução:</label><br>
					<textarea style="width: 100%; height: 100px;"></textarea>
					
					<div style="margin-top: 8px; text-align: right;">
					<button id="send" type="button" name="btn" class="btn btn-primary btn-lg btn-send"><i class="fa fa-arrow-left"></i>  Voltar</button>
					<button id="send" type="button" name="btn" class="btn btn-primary btn-lg btn-send"><i class="fa fa-floppy-o"></i> Atualizar</button>
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
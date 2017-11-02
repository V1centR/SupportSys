<jsp:include page="header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="<c:url value="/resources/js/style.min.js"/>"></script>
<style>
.table a
{
    display:block;
    text-decoration:none;
}

tbody#result tr{
margin: 0;
padding: 0;
}
/*
tbody#result tr:hover{

	background-color: #CED8F6;
}*/

tbody#result tr td{
	height: 40px;

}

tbody#result tr.negative{
	background-color: #F2F2F2;
}

tbody#result tr.canceled{
	background-color: #F5A9A9;
}


tbody#result tr td a:link{
	text-decoration: none;
	color: #303030;

}

tbody#result tr td a{
	height: 100%;
	display: block;
	text-decoration: none;
}

tbody#result tr td div.cellSpace{
	padding-top: 9px;
	padding-right:8px;
}
</style>
<script type="text/javascript">
$(document).ready(function(){function e(e){var l='<div style="width:100%; text-align:center;"><img src="<c:url value="/resources/images/loaderBig.gif"/>" style="width:100px; height:100px;"></div>';localStorage.setItem("stc",e),$("button.btn").prop("disabled",!0),$("span#loader").append(l),$.ajax({type:"GET",dataType:"json",url:s+"gethelplist/"+e,data:"token="+a,success:function(e){$("span#loader").html(""),t(e),$("button.btn").prop("disabled",!1)},error:function(e){return!1}})}function t(e){var t='<c:url value="/"/>',s=0,a="";$.each(e,function(){1==this.statusId&&(console.log("Todos:: "+this.statusId),console.log("Hash:: "+this.hashSecure)),a=s%2==0?"negative":"";var e="",l=!1;switch(this.statusId){case 1:e="danger";break;case 2:e="primary";break;case 3:e="success";break;case 4:l=!0}1==l&&(a="canceled");var i="";i=0==l?'<div style="position:relative; min-width:50px;line-height:10px; top:8px;"><div class="progress progress-sm active" style="display:block; width:100%; -moz-transform: scaleX(-1); -o-transform: scaleX(-1); -webkit-transform: scaleX(-1); transform: scaleX(-1); ">						<div class="progress-bar progress-bar-'+e+' progress-bar-striped" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%;"></div>					    </div></div>':'<div style="position:relative; left:40px;"><i class="fa fa-remove" style="font-size: 26px; color: #f00;"></i></div>';var r=t+"help/open/"+this.id+"/"+this.hashSecure,c='<div style="position:relative; padding-top:2px; margin-left:4px;">				    <div class="user-block">                    <img src="<c:url value="/resources/images/user20171003.jpg"/>" style="width:36px; height:36px;" class="img-circle img-bordered-sm" alt="User Image">                    <span class="username" style="color:#4e98c3">'+this.userIdName+'</span><span class="description">ASSPM - '+this.departmentName+"</span></div></div>";$("tbody#result").append('	                    <tr class="'+a+'">						<td><a href="'+r+'">'+c+'</td>						<td><a href="'+r+'"><div class="cellSpace">'+this.typeHelpName+'</div></a></td>						<td><a href="'+r+'"><div class="cellSpace">'+this.helpLabel+'</div></a></td>						<td><a href="'+r+'"><div class="cellSpace">'+this.dateHelp+'</div></a></td>						<td><a href="'+r+'"><div class="cellSpace">'+i+"</div></a></td>					</tr>	                    "),s++})}var s='<c:url value="/"/>',a="75004f149038473757da0be07ef76dd4a9bdbc8d",l=localStorage.getItem("stc");e(l),$("button#btnAll").click(function(){$("tbody#result").html(""),e("all")}),$("button#btnPending").click(function(){$("tbody#result").html(""),e("pending")}),$("button#btnExec").click(function(){$("tbody#result").html(""),e("exec")}),$("button#btnFinal").click(function(){$("tbody#result").html(""),e("done")}),$("button#btnCanceled").click(function(){$("tbody#result").html(""),e("canceled")})});
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

				<!-- Default box -->
				<div class="box">
					
				<div class="content">
					<!-- buttons  -->
					<div class="row">
				        <div class="col-xs-12">
				          <h2 class="page-header">
				            <i class="fa fa-fire"></i> Chamados
				            <small class="pull-right">
				            <jsp:useBean id="now" class="java.util.Date"/>
				            	<fmt:formatDate value="${now}" pattern="d/M/yyyy H:mm"/>
				            </small>
				          </h2>
				          <form name="type_chamado" method="post" action="#">
				                <input type="hidden" id="editMode" name="edit" value="false">
				                <button type="button" class="btn typeAtividade" id="btnAll" value="0"><span class="glyphicon glyphicon-alert"></span> Todos</button>        
				                <button type="button" class="btn btn-danger typeAtividade" id="btnPending" value="4">Pendente</button>
				                <button type="button" class="btn btn-primary typeAtividade" id="btnExec" value="3">Executando</button>
				                &nbsp;&nbsp;
				                <button type="button" class="btn btn-success typeAtividade" id="btnFinal" value="1">Concluído</button>
				                <button type="button" class="btn typeAtividade" id="btnCanceled" value="1"><i class="fa fa-remove" style="font-size: 20px; color: #f00;"></i> <span style="color:#f00; font-weight: bold;">Cancelados</span></button>
				            </form>
				            
				        </div>
	        		<!-- /.col -->
	      			</div>
	      			
				    <table style="width: 100%;" border="0">
						<tbody id="result"><span id="loader"></span></tbody>
					</table>
	      			
				</div><!-- Content ##### -->
					

				</div>
				<!-- /.box -->

			</section>
			<!-- /.content -->
		</div>
<jsp:include page="footer.jsp" />
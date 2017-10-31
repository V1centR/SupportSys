<jsp:include page="header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

  $(document).ready(function () {
  	 
      var base_url = '<c:url value="/"/>';
      var token = "75004f149038473757da0be07ef76dd4a9bdbc8d";
      
      orderAtividades(0);
	 //cons
      console.log("");
	
      
      $("button#btn_todos").click(function(){	  
	  	alert("Todos");
      });
      
      function orderAtividades(type) {
	        $.ajax({
	            type: 'GET',
	            dataType: 'json',
	            url: base_url + 'gethelplist/all',
	            data: 'token=' + token,
	            success: function (data) {
	        		console.log(data);
	                //$('tbody').html('');
	               // var ambient = localStorage.setItem('ambient', type);
	                makeList(data);
	            },
	            error: function (data) {
	                return false;
	            }
	        });
	    }
      
      
      
	    function makeList(data) {
		
	        var setStripe = 0;
	        var setNegative = '';
	        
	        $.each(data, function () {

	            if (this.statusId == 1) {
	                console.log("Todos:: " + this.statusId);
	                console.log("Hash:: " + this.hashSecure);
	            }
	            
	            if(setStripe%2 == 0){
	                setNegative = 'negative';
	            }else{
	                setNegative = '';
	            }
	            
	            var statusBar = '<div style="position:relative; min-width:50px;line-height:10px;"><div class="progress progress-sm active" style="display:block; width:100%; -moz-transform: scaleX(-1); -o-transform: scaleX(-1); -webkit-transform: scaleX(-1); transform: scaleX(-1); ">\
					<div class="progress-bar progress-bar-primary progress-bar-striped" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%;"></div>\
				    </div></div>';
	            
	            $('tbody#result').append('\
	                    <tr class="'+setNegative+'">\
						<td><a href="#"><div class="cellSpace">' + this.userIdName + '</div></a></td>\
						<td><a href="#"><div class="cellSpace">' + this.departmentName +'</div></a></td>\
						<td><a href="#"><div class="cellSpace">' + this.helpLabel +'</div></a></td>\
						<td><a href="#"><div class="cellSpace">' + this.typeHelpName +'</div></a></td>\
						<td><a href="#"><div class="cellSpace">' + this.dateHelp +'</div></a></td>\
						<td><a href="#"><div class="cellSpace" style="position:relative; top:8px;">' + statusBar + '</div></a></td>\
					</tr>\
	                    ');
	            
	            setStripe++;
	           
	        });
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
				                <button type="button" class="btn typeAtividade" id="btn_todos" value="0"><span class="glyphicon glyphicon-alert"></span> Todos</button>        
				                <button type="button" class="btn btn-danger typeAtividade" id="btn_pendente" value="4">Pendente</button>
				                <button type="button" class="btn btn-primary typeAtividade" id="btn_exec" value="3">Executando</button>
				                &nbsp;&nbsp;
				                <button type="button" class="btn btn-success typeAtividade" id="btn_final" value="1">Conclu�do</button>
				            </form>
				            
				        </div>
	        		<!-- /.col -->
	      			</div>
				    
				    <table style="width: 100%;" border="0">
						<tbody id="result"></tbody>
					</table>
	      			
				</div><!-- Content ##### -->
					

				</div>
				<!-- /.box -->

			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
		<jsp:include page="footer.jsp" />
<jsp:include page="header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
$(document).ready(function () {
    
    $("select#selectClient").change(function(){
        
        //autenticar!
        var strFormJson = "{\"idClient\":\"" + this.value + "\"}";
        
        var setJson = JSON.stringify(strFormJson);
        
        $.ajax({
            type: 'POST',
            dataType: 'json',
            url: '<c:url value="/users/byClient/'+ this.value +'"/>',
            data: setJson,
            contentType : 'application/json; charset=utf-8',
            headers: { 
                'Accept': 'application/json',
                'Content-Type': 'application/json' 
            },
            success: function (data) {
                
               $('table.serviceUserList').html('');
               $.each(data, function () {
                  
               $('table.serviceUserList').append('<tr>\
							<td>\
							<img src="<c:url value="/resources/images/' + this.avatar +'"/>" style="width:36px; height:36px;" class="img-circle img-bordered-sm" alt="User Image">\
						</td>\
						<td>' + this.nameUser +' &nbsp;</td>\
						<td>' + this.deptUser +' &nbsp;&nbsp;</td>\
						<td>' + this.groupUser +' &nbsp;&nbsp;</td>\
					</tr>');
               });
             
               return true;
            },
            error: function (data) {
                
                if(data.responseText == "empty")
                {
                    $('table.serviceUserList').html('');
                    $('table.serviceUserList').append('<tr><td>Não há usuários cadastrados para este cliente.</td></tr>');
                    
                    return false;
                }
                
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
					Gerenciamento <small>usuários</small>
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
					<div style=" padding: 10px;">
						<form id="searchByUser">
							<label>Buscar usuário: </label>
							<input type="text" id="searchByUser" name="searchByUser" placeholder="press enter to search" class="form-control">
						</form>
						
						<form id="searchByClient">
							<label>Listar por cliente: </label>
							<select class="form-control" id="selectClient">
								<option value="0">Todos</option>
								<c:forEach items="${listClients}" var="clientList">
									<option value="${clientList.id}">${clientList.name} | ${clientList.bairro} - ${clientList.city}</option>
								</c:forEach>
							</select><br>
						</form>
					</div>
					<table class="table table-bordered table-hover dataTable serviceUserList">
					</table>
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
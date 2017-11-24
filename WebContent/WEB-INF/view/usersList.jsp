<jsp:include page="header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
$(document).ready(function () {
    
    
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
					<form id="search">
					<label>Buscar usuário: </label>
						<input type="text" name="search" placeholder="search">
					</form>
				
				</div>
				
					<table class="table table-bordered table-hover dataTable">
						<div class="box-body">
							<c:forEach items="${listUsers}" var="userList">
								<tr>
									<td>
										<img src="<c:url value="/resources/images/${userList.image.id}.${userList.image.ext}"/>" style="width:36px; height:36px;" class="img-circle img-bordered-sm" alt="User Image">
									</td>
									<td>${userList.name} ${userList.sname} &nbsp;</td>
									<td>${userList.department.name} &nbsp;&nbsp;</td>
									<td>${userList.userGroupBean.name} &nbsp;&nbsp;</td>
								</tr>
							
							
							</c:forEach>
						
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
<jsp:include page="header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
$(document).ready(function () {
   
    var token = "facbcf23daa88e0c359722b1abd3da1b5b59b6e2";
    getClientList("",token);
    
    $("form#searchClient").submit(function(e){
        var searchInput = $("input#searchByKey").val();
        getClientList(searchInput,token);
        
        return false;
    });
    
    function getClientList(searchInput,token)
    {
        if(searchInput == "")
        {
            searchInput = "null";
        }
        
        var baseUrl = '<c:url value="/"/>';
        var loaderBig = '<div style="width:100%; text-align:center;"><img src="<c:url value="/resources/images/loaderBig.gif"/>" style="width:100px; height:100px;"></div>';
       
        $('tbody.serviceClientList').html('');
        $('span#loader').append(loaderBig);

        $.ajax({
            type: 'GET',
            dataType: 'json',
            url: '<c:url value="/clients/'+ searchInput +'/'+token+'"/>',
            //data: setJson,
            contentType : 'application/json; charset=utf-8',
            headers: { 
                'Accept': 'application/json',
                'Content-Type': 'application/json' 
            },
            success: function (data) {
                
               $('span#loader').html('');
               $('tbody.serviceClientList').html('');
               
               $.each(data, function () {
                  
               $('tbody.serviceClientList').append('<tr>\
							<td>\
							<img src="<c:url value="/resources/images/' + this.avatar +'"/>" style="width:32px; height:32px;" class="img-circle img-bordered-sm" alt="User Image">\
						</td>\
						<td><a href="<c:url value="/users/edit/' + this.clientId +'"/>"><div class="cellSpace">' + this.clientName +' &nbsp;</div></a></td>\
						<td>' + this.clientCity +' - '+this.clientUf+' &nbsp;&nbsp;</td>\
						<td>' + this.clientDescription +' &nbsp;&nbsp;</td>\
					</tr>');
               });
             
               return false;
            },
            error: function (data) {
                $('span#loader').html("");
                if(data.responseText == "empty")
                {
                    $('tbody.serviceClientList').html('');
                    $('tbody.serviceClientList').append('<tr><td>Não há usuários cadastrados para este cliente.</td></tr>');
                    
                    return false;
                }
                
                return false;
            }
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
					<div class="box-header with-border">
						<h3 class="box-title">Title</h3>						
					</div>
					
					<div class="box-body">
					
						<form id="searchClient">
							<label>Buscar cliente: </label> <span data-toggle="tooltip" title="" style="position:relative; top:-3px;" class="badge bg-blue" data-original-title="Blank search: Full list">?</span>
							<input type="text" id="searchByKey" name="searchByKey" placeholder="type to search" class="form-control">
						</form>
						
						<table class="table table-hover">
							<tr>
			                  <th>Logo</th>
			                  <th>Nome</th>
			                  <th>Cidade</th>
			                  <th>Descrição</th>
		                	</tr>
							<tbody class="serviceClientList"></tbody>
						</table>
						<span id="loader"></span>

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
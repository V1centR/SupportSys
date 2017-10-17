<%@page import="java.text.SimpleDateFormat"%>
<jsp:include page="header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script>
        
     $(document).ready(function () {
     	
     	 $('tr.item').click(function () {
              window.location = $(this).attr('href');
              return false;
          });
     });

</script>
<style>
tr.item{cursor: pointer;}
</style>
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
        Intranet
        <small>Painel de controle</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Intranet</li>
      </ol>
    </section>
    

    <section class="invoice">
      <!-- title row -->
      <div class="row">
        <div class="col-xs-12">
          <h2 class="page-header">
            <i class="fa fa-globe"></i> Chamados
            <small class="pull-right">
            <jsp:useBean id="now" class="java.util.Date"/>
            	<fmt:formatDate value="${now}" pattern="d/M/yyyy H:mm"/>
            	
            </small>
          </h2>
        </div>
        <!-- /.col -->
      </div>
      <!-- info row -->
      <div class="row invoice-info">
		<section class="content">
		    <table style="width: 80%;" border="0" cellpadding="10" class="table table-striped">
				<tbody style="font-size: 15px;">
					
		    <c:forEach items="${dataHelp}" var="itemsHelp">
		    	<tr class="item">
					<td>${itemsHelp.user.name} ${itemsHelp.user.sname}</td>
					<td>${itemsHelp.department.name}</td>
					<td>${itemsHelp.helpLabel}</td>
					<td width="18%"><fmt:formatDate value="${itemsHelp.dateHelp}" pattern="dd/MM/yyyy HH:mm"/></td>
					<td align="center">
					<div style="position:relative;height:10px; line-height:10px; width: 100%; min-width: 30px;">
						<div class="progress progress-sm active" style="width:100%; -moz-transform: scaleX(-1); -o-transform: scaleX(-1); -webkit-transform: scaleX(-1); transform: scaleX(-1); ">
					    	<div class="progress-bar progress-bar-danger progress-bar-striped" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%;"></div>
					  	</div>
					</div>
					</td>
				</tr>
		    </c:forEach>
		    		
				</tbody>
			</table>
    </section>        




        <!-- /.col -->
        
        <!-- /.col -->
        
        <!-- /.col -->
      </div>

      
      <!-- /.row -->

      <!-- this row will not appear when printing -->
      <div class="row no-print">
        <div class="col-xs-12">
          <a href="invoice-print.html" target="_blank" class="btn btn-default"><i class="fa fa-print"></i> Print</a>
          <button type="button" class="btn btn-success pull-right"><i class="fa fa-credit-card"></i> Submit Payment
          </button>
          <button type="button" class="btn btn-primary pull-right" style="margin-right: 5px;">
            <i class="fa fa-download"></i> Generate PDF
          </button>
        </div>
      </div>
    </section>
    
    
    

    <!-- Main content -->
    
        <!-- /.Left col -->
        <!-- right col (We are only adding the ID to make the widgets sortable)-->
        <section class="col-lg-5 connectedSortable">
        <!-- Calendar -->
          
          <!-- /.box -->

        </section>
        </div>
        <!-- right col -->
      </div>
     
    </section>
    <!-- /.content -->
  <!-- /.content-wrapper -->
  <jsp:include page="footer.jsp" />

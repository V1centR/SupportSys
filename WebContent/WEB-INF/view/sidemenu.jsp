<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar" style="height: auto;">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="/supportSys/resources/images/user20171003.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>Vicente Ramos</p>
          <a href="#"><i class="fa fa-circle" style="color:00ff00;"></i> Online</a>
        </div>
      </div>
      <!-- search form -->
      <form action="#" method="get" class="sidebar-form">
        <div class="input-group">
          <input type="text" name="q" class="form-control" placeholder="Search...">
          <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
        </div>
        
      </form>
      <!-- /.search form -->
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu tree" data-widget="tree">
        <li class="active treeview menu-open">
          <a href="#">
            <i class="fa fa-fire"></i> <span>Chamados</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
          	<li class="active">
            	<a href="<c:url value="/chamados/list/all"/>"><i class="fa fa-flag-o"></i> Abertos
            	<span class="pull-right-container">
              		<span class="label label-danger pull-right">18</span>
            	</span>
            	</a>
            	<a href="<c:url value="/chamados/list"/>"><i class="fa fa-wrench"></i> Executando
            	<span class="pull-right-container">
              		<span class="label label-primary pull-right">4</span>
            	</span>
            	</a>
            </li>
            
            <li class="active">
            <a href="<c:url value="/chamados/novo-chamado"/>"><i class="fa fa-circle-o"></i> Novo Chamado</a>
            </li>
            
            <li class="active">
            <a href="<c:url value="/chamados/novo-chamado"/>"><i class="fa fa-circle-o"></i> Meus chamados</a>
            </li>
          </ul>
        </li>
        
        <li class="treeview">
          <a href="#">
            <i class="fa fa-users"></i> <span>Usu�rios</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li class="active">
            <a href="<c:url value="/chamados/novo-chamado"/>"><i class="fa fa-circle-o"></i> Gerenciar</a>
            </li>
            
            <li class="active">
            <a href="<c:url value="/chamados/novo-chamado"/>"><i class="fa fa-circle-o"></i> Novo</a>
            </li>
          </ul>
        </li>
        
        <li class="treeview">
          <a href="#">
            <i class="fa fa-cube"></i> <span>Clientes</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li class="active">
            <a href="<c:url value="/chamados/novo-chamado"/>"><i class="fa fa-circle-o"></i> Gerenciar clientes</a>
            </li>
            
            <li class="active">
            <a href="<c:url value="/chamados/novo-chamado"/>"><i class="fa fa-circle-o"></i>Cadastrar setores</a>
            </li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-pie-chart"></i>
            <span>Relat�rios</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="pages/charts/chartjs.html"><i class="fa fa-circle-o"></i> ChartJS</a></li>
            <li><a href="pages/charts/morris.html"><i class="fa fa-circle-o"></i> Morris</a></li>
            <li><a href="pages/charts/flot.html"><i class="fa fa-circle-o"></i> Flot</a></li>
            <li><a href="pages/charts/inline.html"><i class="fa fa-circle-o"></i> Inline charts</a></li>
          </ul>
        </li>
        
        <li class="treeview">
          <a href="#">
            <i class="fa fa-gears"></i>
            <span>Configura��es</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="pages/charts/chartjs.html"><i class="fa fa-circle-o"></i> ChartJS</a></li>
            <li><a href="pages/charts/morris.html"><i class="fa fa-circle-o"></i> Morris</a></li>
            <li><a href="pages/charts/flot.html"><i class="fa fa-circle-o"></i> Flot</a></li>
            <li><a href="pages/charts/inline.html"><i class="fa fa-circle-o"></i> Inline charts</a></li>
          </ul>
        </li>
        
        <li class="treeview">
          <a href="#">
            <i class="fa fa-barcode"></i>
            <span>Estoque</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="pages/charts/chartjs.html"><i class="fa fa-circle-o"></i> Centro de custos</a></li>
            <li><a href="pages/charts/morris.html"><i class="fa fa-circle-o"></i> Equipamentos</a></li>
            <li><a href="pages/charts/flot.html"><i class="fa fa-circle-o"></i> Baixas</a></li>
            <li><a href="pages/charts/inline.html"><i class="fa fa-circle-o"></i> Compras</a></li>
          </ul>
        </li>
        
        <li class="treeview">
          <a href="#">
            <i class="fa fa-files-o"></i>
            <span>Layout Options</span>
            <span class="pull-right-container">
              <span class="label label-primary pull-right">4</span>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="pages/layout/top-nav.html"><i class="fa fa-circle-o"></i> Top Navigation</a></li>
            <li><a href="pages/layout/boxed.html"><i class="fa fa-circle-o"></i> Boxed</a></li>
            <li><a href="pages/layout/fixed.html"><i class="fa fa-circle-o"></i> Fixed</a></li>
            <li><a href="pages/layout/collapsed-sidebar.html"><i class="fa fa-circle-o"></i> Collapsed Sidebar</a></li>
          </ul>
        </li>
        <li>
          <a href="pages/widgets.html">
            <i class="fa fa-th"></i> <span>Widgets</span>
            <span class="pull-right-container">
              <small class="label pull-right bg-green">new</small>
            </span>
          </a>
        </li>
        
         <li class="treeview">
          <a href="pages/calendar.html">
            <i class="fa fa-calendar"></i> <span>Calendar</span>
            <span class="pull-right-container">
              <small class="label pull-right bg-red">3</small>
              <small class="label pull-right bg-blue">17</small>
            </span>
          </a>
        </li>
        <li>
          <a href="pages/mailbox/mailbox.html">
            <i class="fa fa-envelope"></i> <span>Mailbox</span>
            <span class="pull-right-container">
              <small class="label pull-right bg-yellow">12</small>
              <small class="label pull-right bg-green">16</small>
              <small class="label pull-right bg-red">5</small>
            </span>
          </a>
        </li>
        
        <li class="header">LABELS</li>
        <li><a href="#"><i class="fa fa-circle-o text-red"></i> <span>Important</span></a></li>
        <li><a href="#"><i class="fa fa-circle-o text-yellow"></i> <span>Warning</span></a></li>
        <li><a href="#"><i class="fa fa-circle-o text-aqua"></i> <span>Information</span></a></li>
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>
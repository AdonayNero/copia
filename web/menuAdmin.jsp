<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="${pageContext.request.contextPath}/indexAdmin.jsp">La Cuponera SV</a>
    </div>

    <ul class="nav navbar-top-links navbar-right">
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
            </a>
            <ul class="dropdown-menu dropdown-user">
                <li><a href="#"><i class="fa fa-user fa-fw"></i> Perfil</a>
                </li>
                <li><a href="#"><i class="fa fa-gear fa-fw"></i> Ajustes</a>
                </li>
                <li class="divider"></li>
                <li><a href="log.jsp"><i class="fa fa-sign-out fa-fw"></i> Cerrar Sesion</a>
                </li>
            </ul>
        </li>
    </ul>

    <div class="navbar-default sidebar" role="navigation">
        <div class="sidebar-nav navbar-collapse">
            <ul class="nav" id="side-menu">

                <li>
                    <a href="${pageContext.request.contextPath}/indexAdmin.jsp"><i class="fa fa-dashboard fa-fw"></i> Inicio</a>
                </li>

                <li>
                    <a href="#"><i class="fa fa-building fa-fw"></i> Empresa Ofertante<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="${pageContext.request.contextPath}/empresas.do?op=nuevo">Registra Empresa</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/empresas.do?op=listar">Listar Empresas</a>
                        </li>
                    </ul>

                </li>
                <li>
                    <a href="#"><i class="fa fa-briefcase  fa-fw"></i> Gestion de Rubros<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="${pageContext.request.contextPath}/rubros.do?op=nuevo">Registrar Rubro</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/rubros.do?op=listar">Listar Rubro</a>
                        </li>
                    </ul>

                </li>

                <li>
                 <a href="#"><i class="fa fa-cog"></i> Aprobaciones<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="${pageContext.request.contextPath}/estado.do?op=listar">En espera</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/estado.do?op=aprobadas">Aprobadas</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/estado.do?op=rechazadas">Rechazadas</a>
                        </li>
                    </ul>

                </li>
                 <li>
                    <a href="#"><i class="fa fa fa-user-plus  fa-fw"></i> Administradores<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="${pageContext.request.contextPath}/admins.do?op=nuevo">Registrar Administrador</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admins.do?op=listar">Listar Administradores</a>
                        </li>
                    </ul>

                </li>
            </ul>
        </div>

    </div>

</nav>
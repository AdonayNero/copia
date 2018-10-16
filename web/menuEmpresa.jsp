

    <nav class="navbar navbar-inverse">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" 
                  data-toggle="collapse" data-target="#navbar" 
                  aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Desplegar navegación</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/indexEmpresa.jsp">La Cuponera SV</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
              <li class="active"><a href="${pageContext.request.contextPath}/indexEmpresa.jsp">Inicio</a></li> 
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" 
                 role="button" aria-haspopup="true" 
                 aria-expanded="false"><i class="fa fa-users"></i> Dependientes <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="${pageContext.request.contextPath}/dependientes.do?op=nuevo">Registrar dependiente</a></li>
                <li><a href="${pageContext.request.contextPath}/dependientes.do?op=listar">Listar dependientes</a></li>
              </ul>
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" 
                 role="button" aria-haspopup="true" 
                 aria-expanded="false"><i class="fa fa-briefcase  fa-fw"></i> Promociones<span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="${pageContext.request.contextPath}/promociones.do?op=nuevo">Registrar promocion</a></li>
                <li><a href="${pageContext.request.contextPath}/promociones.do?op=listar">Listar promociones</a></li>
              </ul>
            </li>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
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
          
        </div>
      </div>
    </nav>
        

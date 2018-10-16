<jsp:useBean id="usuario" class="sv.edu.udb.www.beans.Cliente" scope="session"/>
<div id='cssmenu' style="z-index: 2;">
    <ul>
        <li><a href='${pageContext.request.contextPath}/index.do'><span>Inicio</span></a></li>
        <li><a href='#'><span>Categorias</span></a>
            <ul>
                <li><a  href='${pageContext.request.contextPath}/index.do?op=restaurante'><span>Restaurantes</span></a>
                    <!--<ul>
                        <li><a href='#'><span>Sub Product</span></a></li>
                        <li class='last'><a href='#'><span>Sub Product</span></a></li>
                    </ul>-->
                </li>
                <li><a href='${pageContext.request.contextPath}/index.do?op=moda'><span>Moda</span></a></li>
                <li><a href='${pageContext.request.contextPath}/index.do?op=salud'><span>Salud</span></a></li>
                <li><a href='${pageContext.request.contextPath}/index.do?op=automotriz'><span>Automotriz</span></a></li>
                <li><a href='${pageContext.request.contextPath}/index.do?op=tecnologia'><span>Tecnologia</span></a></li>
                <li><a href='${pageContext.request.contextPath}/index.do?op=diversion'><span>Diversion</span></a></li>
                <li><a href='${pageContext.request.contextPath}/index.do?op=accesorios'><span>Accesorios</span></a></li>
            </ul>
        </li>
        <li><a href='${pageContext.request.contextPath}/acercade.jsp'><span>Sobre nosotros</span></a></li>
        <li class='last'><a href='${pageContext.request.contextPath}/contacto.jsp'><span>Contacto</span></a></li>

        <li class='last navbar-right'><a href='${pageContext.request.contextPath}/log.jsp'><span>Logout</span></a></li>
        <!--<li class="last navbar-right active"><a href='${pageContext.request.contextPath}/clientes/login.jsp'><span>Login</span></a></li>-->
        <li class='last navbar-right'><a href='#'><span><jsp:getProperty name="usuario" property="correo" /></span></a></li>
    </ul>
</div>
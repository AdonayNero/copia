<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="base" value="${pageContext.request.contextPath}"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Iniciar sesion</title>
        <link href="${base}/assets/css/bootstrap.min.css" rel="stylesheet">
        <jsp:include page="/cabecera.jsp"/>
    </head>
    <body>
        <br><br>
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-4 col-sm-offset-4">
                    <h2>Inicio de sesión</h2>
                    <c:if test="${not empty sessionScope.exito}">
                        <div class="alert alert-success">
                            <p>${sessionScope.exito}</p>
                            <c:set var="exito" value="" scope="session"/>
                        </div>
                    </c:if>
                    <c:if test="${not empty sessionScope.fracaso}">
                        <div class="alert alert-danger">
                            <p>${sessionScope.fracaso}</p>
                            <c:set var="fracaso" value="" scope="session"/>
                        </div>
                    </c:if>
                    <form action="${base}/clientes.do" method="POST">
                        <input type="hidden" name="operacion" value="check_login"/>
                        <div class="form-group">
                            <label for="usuario">Correo Electronico:</label>
                            <input type="text" name="correo" id="correo" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input type="password" name="clave" id="clave" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-lg btn-default btn-block">Iniciar sesion</button>
                        </div>
                    </form>
                    <a href="${base}/clientes.do?operacion=registro">Registrarse</a>
                    <a href="#">Olvidaste tu contraseña</a>
                </div>
            </div>
        </div>
    </body>
    <jsp:include page="/footer.jsp"/>
</html>

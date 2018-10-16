<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Iniciar sesion</title>
      <jsp:include page="/cabecera.jsp" />
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-4 col-sm-offset-4">
                    <h2>Inicio de Administrador</h2>
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
                    <form action="${pageContext.request.contextPath}/admins.do" method="POST">
                        <input type="hidden" name="op" value="check_login"/>
                        <div class="form-group">
                            <label for="usuario">Usuario</label>
                            <input type="text" name="usuario" id="usuario" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input type="password" name="password" id="password" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-lg btn-primary btn-block">Iniciar sesion</button> 
                        </div>
                    </form>
                    <a href="#">Recuperar contraseña</a>
                </div>
            </div>
        </div>
    </body>
</html>
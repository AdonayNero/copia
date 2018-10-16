<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="base" value="${pageContext.request.contextPath}"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>La Cuponera | Registro de Empresas</title>
        <link href="${base}/assets/css/bootstrap.min.css" rel="stylesheet">
        <jsp:include page="/cabecera.jsp"/>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <h3 class="text-center">Registro de Clientes</h3>
            </div>
            <div class="row col-md-4"></div>
            <div class="row col-md-8" >
                <c:if test="${not empty requestScope.listaErrores}">
                    <div class="alert alert-danger">
                        <ul>
                            <c:forEach var="error" items="${requestScope.listaErrores}">
                                <li>${error}</li>
                                </c:forEach>
                        </ul>
                    </div>
                </c:if>
                <form action="${base}/clientes.do" method="POST">
                    <input type="hidden" name="operacion" value="insertar"/>
                    <div class="col-md-6">
                        <div class="well well-sm"><strong><span class="glyphicon glyphicon-asterisk"></span>Campos requeridos</strong></div>
                        <div class="form-group">
                            <label for="dui">DUI Usuario:</label>
                            <div class="input-group">
                                <input type="text" name="dui" id="dui" class="form-control" value="${cliente.dui}"/>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="nombre">Nombre Usuario:</label>
                            <div class="input-group">
                                <input type="text" name="nombres" id="nombres" class="form-control" value="${cliente.nombres}"/>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="apellido">Apellido Usuario:</label>
                            <div class="input-group">
                                <input type="text" name="apellidos" class="form-control" id="apellidos" value="${cliente.apellidos}"/>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="telefono">Telefono Usuario:</label>
                            <div class="input-group">
                                <input type="text" name="telefono" class="form-control" id="telefono" value="${cliente.telefono}"/>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="correo">Correo Usuario:</label>
                            <div class="input-group">
                                <input type="email" name="correo" id="correo" class="form-control" value="${cliente.correo}"/>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="clave">Contraseña Usuario:</label>
                            <div class="input-group">
                                <input type="password" name="clave" class="form-control" id="clave" value="${cliente.contraseña}"/>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-default">Registrar</button>
                        <button type="reset" class="btn btn-danger">Limpiar</button>
                    </div>
                    <div></div>
                </form>
            </div>
            <div class="col col-md-4"></div>
        </div> <!-- /container -->
    </body>
    <jsp:include page="/footer.jsp"/>
</html>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Nuevo Administrador</title>
        <jsp:include page="/cabeceraAdmin.jsp" />
    </head>
    <body>
        <div id="wrapper">
            <jsp:include page="/menuAdmin.jsp" />
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Nuevo Administrador</h1>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                Ingrese los datos del nuevo administrador
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <div class=" col-md-7">
                                        <c:if test="${not empty requestScope.listaErrores}">
                                            <div class="alert alert-danger">
                                                <ul>
                                                    <c:forEach items="${requestScope.listaErrores}"  var="error">
                                                        <li>${error}</li>
                                                        </c:forEach>
                                                </ul>
                                            </div>
                                        </c:if> 
                                        <div class="panel panel-success">
                                            <div class="panel-heading"><strong><span class="glyphicon glyphicon-asterisk"></span> Campos requeridos</strong></div>
                                            <div class="panel-body">
                                               <form role="form" action="${pageContext.request.contextPath}/admins.do" method="POST">
                                                    <input type="hidden" name="op" value="insertar"/>
                                                    <div class="form-group">
                                                        <label for="codigo">Codigo del administrador:</label>
                                                        <div class="input-group">
                                                            <input type="text" class="form-control" name="codigo" id="codigo" value="${administrador.idAdmin}" placeholder="Ingresa el codigo del administrador" >
                                                            <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                                        </div>
                                                    </div>    
                                                    <div class="form-group">
                                                        <label for="nombre">Nombre del administrador:</label>
                                                        <div class="input-group">
                                                            <input type="text" class="form-control" name="nombre" id="nombre" value="${administrador.nombre}" placeholder="Ingresa el nombre del administrador" >
                                                            <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                                        </div>
                                                    </div>
                                                            <div class="form-group">
                                                        <label for="apellido">Apellido del administrador:</label>
                                                        <div class="input-group">
                                                            <input type="text" class="form-control" name="apellido" id="apellido" value="${administrador.apellido}" placeholder="Ingresa el apellido del administrador" >
                                                            <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                                        </div>
                                                    </div>      
                                                    <div class="form-group">
                                                        <label for="usuario">Usuario del administrador:</label>
                                                        <div class="input-group">
                                                            <input type="text" class="form-control" name="usuario" id="usuario" value="${administrador.usuario}" placeholder="Ingresa el usuario del administrador" >
                                                            <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                                        </div>
                                                    </div>        
                                                    <div class="form-group">
                                                        <label for="email">Email del administrador:</label>
                                                        <div class="input-group">
                                                            <input type="text" class="form-control" name="email" id="email" value="${administrador.correo}" placeholder="Ingresa el email del administrador" >
                                                            <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="password">Password del administrador:</label>
                                                        <div class="input-group">
                                                            <input type="password" class="form-control" name="pass" id="pass" value="${administrador.clave}" placeholder="Ingresa el password del administrador" >
                                                            <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                                        </div>
                                                    </div>
                                                            
                                                    <input type="submit" class="btn btn-info" value="Guardar" name="Guardar">
                                                    <a class="btn btn-danger" href="${pageContext.request.contextPath}/admins.do?op=listar">Cancelar</a>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>  
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

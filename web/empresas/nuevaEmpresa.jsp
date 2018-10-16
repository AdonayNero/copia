<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Nueva Empresa</title>
        <jsp:include page="/cabeceraAdmin.jsp" />
        <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>
    </head>
    <body>
        <div id="wrapper">
            <jsp:include page="/menuAdmin.jsp" />
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Nueva Empresa</h1>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                Ingrese los datos de la nueva empresa
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
                                                <form role="form" action="${pageContext.request.contextPath}/empresas.do" method="POST">
                                                    <input type="hidden" name="op" value="insertar"/>
                                                    <div class="form-group">
                                                        <label for="codigo">Codigo de la empresa:</label>
                                                        <div class="input-group">
                                                            <input type="text" class="form-control" name="codigo" id="codigo" value="${empresa.codigoEmpresa}"placeholder="Ingresa el codigo de la empresa">
                                                            <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="nombre">Nombre de la empresa:</label>
                                                        <div class="input-group">
                                                            <input type="text" class="form-control" name="nombre" id="nombre" value="${empresa.nombre}" placeholder="Ingresa el nombre de la empresa">
                                                            <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="direccion">Direccion de la empresa:</label>
                                                        <div class="input-group">
                                                            <input type="text" class="form-control" id="direccion" name="direccion" value="${empresa.direccion}" placeholder="Ingresa la direccion de la empresa">
                                                            <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="contacto">Contacto de la empresa:</label>
                                                        <div class="input-group">
                                                            <input type="text" class="form-control" id="contacto" name="contacto" value="${empresa.contacto}" placeholder="Ingresa el contacto de la empresa" >
                                                            <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="telefono">Telefono de la empresa:</label>
                                                        <div class="input-group">
                                                            <span class="input-group-addon" id="basic-addon3"><strong>#</strong></span>
                                                            <input type="tel" class="form-control" id="telefono" name="telefono" value="${empresa.telefono}" placeholder="Ingresa el telefono de la empresa" >
                                                            <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="correo">Correo de la empresa:</label>
                                                        <div class="input-group">
                                                            <span class="input-group-addon" id="basic-addon3"><strong>@</strong></span>
                                                            <input type="text" class="form-control" id="correo" name="correo" value="${empresa.correo}" placeholder="Ingresa el correo de la empresa" >
                                                            <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="comision">Comision de la empresa:</label>
                                                        <div class="input-group">
                                                            <span class="input-group-addon"><span class="glyphicon glyphicon-usd"></span></span>
                                                            <input type="number" min="0" step="0.1" class="form-control" id="comision" name="comision" value="${empresa.comision}" placeholder="Ingresa la comision de la empresa" >
                                                            <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="clave">Password de la empresa:</label>
                                                        <div class="input-group">
                                                            <input type="password" class="form-control" id="clave" name="clave" value="${empresa.clave}" placeholder="Ingresa el password de la empresa" >
                                                            <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                                        </div>
                                                    </div>
                                                    <div>
                                                        <label for="idRubro">Rubro de la empresa:</label>
                                                        <div class="input-group">
                                                            <select name="rubro" id="idRubro" class="form-control"> 
                                                                <c:forEach items="${requestScope.listaRubros}" var="rubro">
                                                                    
                                                                    <c:choose>
                                                                        <c:when test="${rubro.idRubro eq empresa.idRubro}">
                                                                            <option value="${rubro.idRubro}" selected="true" >${rubro.nombreRubro}</option>
                                                                        </c:when>
                                                                            
                                                                            <c:otherwise>
                                                                                <option value="${rubro.idRubro}">${rubro.nombreRubro}</option>
                                                                            </c:otherwise>    
                                                                    </c:choose>
                    
                                                                </c:forEach>
                                                            </select>  
                                                            <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                                        </div>
                                                    </div><br>

                                                    <input type="submit" class="btn btn-info" value="Guardar" name="Guardar">
                                                    <a class="btn btn-danger" href="${pageContext.request.contextPath}/empresas.do?op=listar">Cancelar</a>
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
                <script>
                    $('#idRubro').select2();
                </script>
    </body>
</html>

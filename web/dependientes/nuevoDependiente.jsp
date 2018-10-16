<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Nuevo dependiente</title>
        <jsp:include page="/cabeceraAdmin.jsp" />
         <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>
    </head>
    <body>
        <div id="wrapper">
            <jsp:include page="/menuEmpresa.jsp" />
            <div id="page-wrapper" style=" margin: 0 50px;">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Nuevo Dependiente</h1>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                Ingrese los datos del nuevo dependiente
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
                                                <form role="form" action="${pageContext.request.contextPath}/dependientes.do" method="POST">
                                                    <input type="hidden" name="op" value="insertar"/>
                                                    <div class="form-group">
                                                        <label for="codigo">Codigo del dependiente:</label>
                                                        <div class="input-group">
                                                            <input type="text" class="form-control" name="codigo" id="codigo" value="${dependiente.idDependiente}" placeholder="Ingresa el codigo del dependiente" >
                                                            <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="nombreDep">Nombre del dependiente:</label>
                                                        <div class="input-group">
                                                            <input type="text" class="form-control" name="nombreDep" id="nombreDep" value="${dependiente.nombreDep}" placeholder="Ingresa el nombre del dependiente" >
                                                            <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                                        </div>
                                                    </div>
                                                     <div class="form-group">
                                                        <label for="apellido">Apellido del dependiente:</label>
                                                        <div class="input-group">
                                                            <input type="text" class="form-control" name="apellido" id="apellido" value="${dependiente.apellido}" placeholder="Ingresa el apellido del dependiente" >
                                                            <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                                        </div>
                                                    </div>
                                                     <div class="form-group">
                                                        <label for="correo">Email del dependiente:</label>
                                                        <div class="input-group">
                                                            <input type="text" class="form-control" name="correo" id="correo" value="${dependiente.correo}" placeholder="Ingresa el correo del dependiente" >
                                                            <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                                        </div>
                                                    </div>
                                                     <div class="form-group">
                                                        <label for="clave">Password del dependiente:</label>
                                                        <div class="input-group">
                                                            <input type="password" class="form-control" name="clave" id="clave" value="${dependiente.clave}" placeholder="Ingresa el password del dependiente" >
                                                            <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                                        </div>
                                                     </div>
                                                       <div>
                                                        <label for="codigoEmpresa">Codigo de la empresa asociado:</label>
                                                        <div class="input-group">
                                                            <select name="codigoEmpresa" id="codigoEmpresa" class="form-control"> 
                                                                <c:forEach items="${requestScope.listaEmpresas}" var="empresa">
                                                                    
                                                                 <c:choose>
                                                                        <c:when test="${empresa.codigoEmpresa eq dependiente.codigoEmpresa}">
                                                                            <option value="${empresa.codigoEmpresa}" selected="true" >${empresa.nombre}</option>
                                                                        </c:when>
                                                                            
                                                                            <c:otherwise>
                                                                                <option value="${empresa.codigoEmpresa}">${empresa.nombre}</option>
                                                                            </c:otherwise>    
                                                                    </c:choose>
                          
                                                                </c:forEach>
                                                            </select>  
                                                            <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                                        </div>
                                                    </div><br>
                                                            

                                                    <input type="submit" class="btn btn-info" value="Guardar" name="Guardar">
                                                    <a class="btn btn-danger" href="${pageContext.request.contextPath}/dependientes.do?op=listar">Cancelar</a>
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
                    $('#codigoEmpresa').select2();
                </script>
    </body>
</html>

<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Listar Empresa</title>
        <jsp:include page="/cabeceraAdmin.jsp" />
    </head>
    <body>
        <div id="wrapper">
            <jsp:include page="/menuAdmin.jsp" />
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Lista de empresas</h1>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <a type="button" class="btn btn-success" href="${pageContext.request.contextPath}/empresas.do?op=nuevo"> Nueva Empresa</a><br><br>
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                Empresas Ofertantes
                            </div>

                            <div class="panel-body">
                                <table class="table table-striped table-bordered table-hover" id="TablaCuponera">
                                    <thead>
                                        <tr>
                                            <th>Codigo</th>
                                            <th>Nombre</th>
                                            <th>Direccion</th>
                                            <th>Contacto</th>
                                            <th>Telefono</th>
                                            <th>Correo</th>
                                            <th>Comision</th>
                                            <th>Rubro</th>
                                            <th>Operaciones</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.listaEmpresas}" var="empresa">
                                        <tr>
                                           <td>${empresa.codigoEmpresa}</td>
                                            <td>${empresa.nombre}</td>
                                            <td>${empresa.direccion}</td>
                                            <td>${empresa.contacto}</td>
                                            <td>${empresa.telefono}</td>
                                            <td>${empresa.correo}</td>
                                            <td>${empresa.comision}</td>
                                            <td>${empresa.rubro.nombreRubro}</td>
                                            
                                            <td>
                                                <a class="btn btn-warning" href="${pageContext.request.contextPath}/empresas.do?op=obtener&id=${empresa.codigoEmpresa}"><span class="glyphicon glyphicon-edit"></span></a>
                                                <a  class="btn btn-danger" href="javascript:eliminar('${empresa.codigoEmpresa}')"><span class="glyphicon glyphicon-trash"></span></a>
                                            </td>
                                        </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            $(document).ready(function () {
                $('#TablaCuponera').DataTable({
                    responsive: true
                });
            });
            
            
            <c:if test="${not empty exito}">
                alertify.success('${exito}');
              <c:set var="exito" value="" scope="session"/>
            </c:if>    
             <c:if test="${not empty fracaso}">
                alertify.error('${fracaso}');
                <c:set var="fracaso" value="" scope="session"/>
            </c:if>    
                
                
             function eliminar(id){
                   alertify.confirm('¿Realmente desea eliminar esta empresa?', function(e){
                       if(e){
                          location.href="empresas.do?op=eliminar&id=" + id; 
                       }
                   });
           }   
        </script>
    </body>
</html>

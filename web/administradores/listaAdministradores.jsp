<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Lista de Administradores</title>
        <jsp:include page="/cabeceraAdmin.jsp" />
    </head>
    <body>
        <div id="wrapper">
            <jsp:include page="/menuAdmin.jsp" />
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Lista de Administradores</h1>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <a type="button" class="btn btn-success" href="${pageContext.request.contextPath}/admins.do?op=nuevo" > Nuevo Administrador</a><br><br>
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                Administradores de la cuponera
                            </div>

                            <div class="panel-body">
                                <table class="table table-striped table-bordered table-hover" id="TablaCuponera">
                                    <thead>
                                        <tr>
                                            <th>Codigo del administrador</th>
                                            <th>Nombre del administrador</th>
                                            <th>Apellido del administrador</th>
                                            <th>Usuario del administrador</th>
                                            <th>Email del administrador</th>
                                            
                                        </tr>
                                    </thead>
                                    <tbody>
                                         <c:forEach items="${requestScope.listaAdministradores}" var="admin">
                                        <tr>
                                            <td>${admin.idAdmin}</td>
                                            <td>${admin.nombre}</td>
                                            <td>${admin.apellido}</td>
                                            <td>${admin.usuario}</td>
                                            <td>${admin.correo}</td>
                                            
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
                   alertify.confirm('¿Realmente desea eliminar este rubro?', function(e){
                       if(e){
                          location.href="rubros.do?op=eliminar&id=" + id; 
                       }
                   });
           }         
        </script>
        
    </body>
</html>

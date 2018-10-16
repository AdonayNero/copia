<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Lista de dependientes</title>
        
        <jsp:include page="/cabeceraAdmin.jsp" />
    </head>
    <body>
        <div id="wrapper">
            <jsp:include page="/menuEmpresa.jsp" />
            <div id="page-wrapper" style=" margin: 0 50px;">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Lista de dependientes</h1>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <a type="button" class="btn btn-success" href="${pageContext.request.contextPath}/dependientes.do?op=nuevo"> Nuevo Dependiente</a><br><br>
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                Dependientes Empresa
                            </div>

                            <div class="panel-body">
                                <table class="table table-striped table-bordered table-hover" id="TablaCuponera">
                                    <thead>
                                        <tr>
                                            <th>Codigo del dependiente</th>
                                            <th>Nombre del dependiente</th>
                                            <th>Apellido del dependiente</th>
                                            <th>Correo del dependiente</th>
                                            <th>Empresa asociado</th>
                                            <th>Operaciones</th>
                                        </tr>
                                    </thead>
                                    <tbody>   
                                        <c:forEach items="${requestScope.listaDependientes}" var="dependiente">
                                        <tr>
                                            <td>${dependiente.idDependiente}</td>
                                            <td>${dependiente.nombreDep}</td>
                                            <td>${dependiente.apellido}</td>
                                            <td>${dependiente.correo}</td>
                                            <td>${dependiente.empresa.nombre}</td>
                                            <td>
                                                <a class="btn btn-warning" href="${pageContext.request.contextPath}/dependientes.do?op=obtener&id=${dependiente.idDependiente}"><span class="glyphicon glyphicon-edit"></span></a>
                                                <a  class="btn btn-danger" href="javascript:eliminar('${dependiente.idDependiente}')"><span class="glyphicon glyphicon-trash"></span></a>
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
                   alertify.confirm('¿Realmente desea eliminar este dependiente?', function(e){
                       if(e){
                          location.href="dependientes.do?op=eliminar&id=" + id; 
                       }
                   });
           }         
        </script>
    </body>
</html>

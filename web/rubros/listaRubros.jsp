<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Lista de rubros</title>
        <jsp:include page="/cabeceraAdmin.jsp" />
    </head>
    <body>
        <div id="wrapper">
            <jsp:include page="/menuAdmin.jsp" />
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Lista de rubros</h1>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <a type="button" class="btn btn-success" href="${pageContext.request.contextPath}/rubros.do?op=nuevo"> Nuevo Rubro</a><br><br>
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                Rubros Ofertantes
                            </div>

                            <div class="panel-body">
                                <table class="table table-striped table-bordered table-hover" id="TablaCuponera">
                                    <thead>
                                        <tr>
                                            <th>Codigo del rubro</th>
                                            <th>Nombre del rubro</th>
                                            <th>Operaciones</th>
                                        </tr>
                                    </thead>
                                    <tbody>   
                                        <c:forEach items="${requestScope.listaRubros}" var="rubro">
                                        <tr>
                                            <td>${rubro.idRubro}</td>
                                            <td>${rubro.nombreRubro}</td>
                                            <td>
                                                <a class="btn btn-warning" href="${pageContext.request.contextPath}/rubros.do?op=obtener&id=${rubro.idRubro}"><span class="glyphicon glyphicon-edit"></span></a>
                                                <a  class="btn btn-danger" href="javascript:eliminar('${rubro.idRubro}')"><span class="glyphicon glyphicon-trash"></span></a>
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
                   alertify.confirm('¿Realmente desea eliminar este rubro?', function(e){
                       if(e){
                          location.href="rubros.do?op=eliminar&id=" + id; 
                       }
                   });
           }         
        </script>
    </body>
</html>

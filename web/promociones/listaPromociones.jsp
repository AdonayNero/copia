<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Listar Promociones</title>
        <jsp:include page="/cabeceraAdmin.jsp" />
    </head>
    <body>
        <div id="wrapper">
            <jsp:include page="/menuEmpresa.jsp" />
            <div id="page-wrapper" style=" margin: 0 60px;">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Lista de promociones</h1>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <a type="button" class="btn btn-success" href="${pageContext.request.contextPath}/promociones.do?op=nuevo"> Nueva Promocion</a><br><br>
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                Promociones
                            </div>

                            <div class="panel-body">
                                <table class="table table-striped table-bordered table-hover" id="TablaCuponera">
                                    <thead>
                                        <tr>
                                            <th>Codigo</th>
                                            <th>Titulo</th>
                                            <th>Precio Regular</th>
                                            <th>Precio Oferta</th>
                                            <th>Fecha Inicio</th>
                                            <th>Fecha Fin</th>
                                            <th>Fecha Limite</th>                                
                                             <th>Justificacion</th>
                                             <th>Foto</th>
                                             <th>Estado</th>
                                            <!--  <th>Empresa</th> -->
                                            <th>Operaciones</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.listaPromociones}" var="promocion">
                                        <tr>
                                           <td>${promocion.idPromocion}</td>
                                           <td>${promocion.titulo}</td>
                                           <td>${promocion.precioRegular}</td>
                                           <td>${promocion.precioOferta}</td>
                                           <td>${promocion.fechaInicio}</td>
                                           <td>${promocion.fechaFin}</td>
                                           <td>${promocion.fechaLimite}</td>
                                           <td>${promocion.justificacion}</td>
                                           <td>${promocion.img}</td>
                                           <td><span class="label label-danger">${promocion.estado.estado}</span></td>
                                          <!-- <td></td>-->
                                            <td>
                                                <a class="btn btn-warning" href="${pageContext.request.contextPath}/promociones.do?op=obtener&id=${promocion.idPromocion}"><span class="glyphicon glyphicon-edit"></span></a>
                                                <a  class="btn btn-danger" href="javascript:eliminar('${promocion.idPromocion}')"><span class="glyphicon glyphicon-trash"></span></a>
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
                   alertify.confirm('¿Realmente desea eliminar esta promocion?', function(e){
                       if(e){
                          location.href="promociones.do?op=eliminar&id=" + id; 
                       }
                   });
           }   
        </script>
    </body>
</html>

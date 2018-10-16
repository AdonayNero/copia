<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Listar Esperas</title>
        <jsp:include page="/cabeceraAdmin.jsp" />
    </head>
    <body>
        <div id="wrapper">
            <jsp:include page="/menuAdmin.jsp" />
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Lista de promociones <span class="label label-success">Aprobadas</span></h1>
                    </div>
                </div>
               <div class="row">
                    <div class="col-lg-12">
                      
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                Promociones espera
                            </div>

                            <div class="panel-body">
                                <table class="table table-striped table-bordered table-hover" id="TablaCuponera">
                                    <thead>
                                        <tr>
                                            <th>ID Promocion</th>
                                            <th>Titulo</th>
                                            <th>Precio regular</th>
                                            <th>Fecha inicio</th>
                                            <th>Empresa</th>
                                            <th>Estado</th>
                                        </tr>
                                    </thead>
                                    <tbody>   
                                        <c:forEach items="${requestScope.listaEsperas}" var="espera">
                                        <tr>
                                            <td>${espera.idPromocion}</td>
                                            <td>${espera.titulo}</td>
                                            <td>${espera.precioRegular}</td>
                                            <td>${espera.fechaInicio}</td>
                                            <td>${espera.empresa.nombre}</td>
                                            <td><span class="label label-success">${espera.estado.estado}</span></td>
                                            
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
                
                
             function aprobar(id){
                   alertify.confirm('¿Realmente desea aprobar esta promocion?', function(e){
                       if(e){
                          location.href="estado.do?op=aprobar&id=" + id; 
                       }
                   });
           }   
        </script>
    </body>
</html>

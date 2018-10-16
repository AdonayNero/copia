<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Editar Promocion</title>
        <jsp:include page="/cabeceraAdmin.jsp" />
        <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>
    </head>
    <body>
        <div id="wrapper">
            <jsp:include page="/menuEmpresa.jsp" />
            <div id="page-wrapper" style=" margin: 0 60px;">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Editar Promocion</h1>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                Ingrese los datos de la promocion
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
                                                <form role="form" action="${pageContext.request.contextPath}/promociones.do" method="POST">
                                                    <input type="hidden" name="op" value="modificar"/>
                                                    <div class="form-group">
                                                        <label for="idPromocion">Codigo de la promocion:</label>
                                                        <div class="input-group">
                                                            <input type="text" class="form-control" name="idPromocion" id="idPromocion" value="${promocion.idPromocion}" readonly="true" placeholder="Ingresa el codigo de la promocion">
                                                            <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="titulo">Titulo de la promocion:</label>
                                                        <div class="input-group">
                                                            <input type="text" class="form-control" name="titulo" id="titulo" value="${promocion.titulo}" placeholder="Ingresa el titulo de la promocion">
                                                            <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                                        </div>
                                                    </div>
                                                     <div class="form-group">
                                                        <label for="precioRegular">Precio regular de la promocion:</label>
                                                        <div class="input-group">
                                                            <span class="input-group-addon"><span class="glyphicon glyphicon-usd"></span></span>
                                                            <input type="number" min="0" step="0.1" class="form-control" id="precioRegular" name="precioRegular" value="${promocion.precioRegular}" placeholder="Ingresa el precio regular de la promocion" >
                                                            <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="precioOferta">Precio oferta de la promocion:</label>
                                                        <div class="input-group">
                                                            <span class="input-group-addon"><span class="glyphicon glyphicon-usd"></span></span>
                                                            <input type="number" min="0" step="0.1" class="form-control" id="precioOferta" name="precioOferta" value="${promocion.precioOferta}" placeholder="Ingresa el precio oferta de la promocion" >
                                                            <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                                        </div>
                                                    </div>   
                                                    
                                                    <div class="form-group">
                                                          <label class="control-label" for="fechaInicio">Fecha inicio</label>
                                                          <div class="input-group">
                                                              <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                                             <input class="form-control" id="fechas" name="fechaInicio" placeholder="YYY-MM-DD" value="${promocion.fechaInicio}" type="text"/>
                                                          <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                                          </div>
                                                    </div>
                                                    <div class="form-group">
                                                          <label class="control-label" for="fechaFin">Fecha fin</label>
                                                          <div class="input-group">
                                                              <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                                             <input class="form-control" id="fechas" name="fechaFin" placeholder="YYY-MM-DD" value="${promocion.fechaFin}" type="text"/>
                                                          <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                                          </div>
                                                    </div>   
                                                    
                                                    <div class="form-group">
                                                           <label class="control-label" for="fechaLimite">Fecha limite</label>
                                                             <div class="input-group">
                                                               <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                                                 <input class="form-control" id="fechas" name="fechaLimite" placeholder="YYY-MM-DD" value="${promocion.fechaLimite}" type="text"/>
                                                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                                             </div>
                                                    </div>   
                                                    
                                                    <div class="form-group">
                                                        <label for="descripcion">Descripcion de la promocion:</label>
                                                        <div class="input-group">
                                                            <textarea name="descripcion" id="descripcion" class="form-control" rows="2" cols="100" value="${promocion.descripcion}">
                                                                
                                                            </textarea>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="img">Img de la promocion:</label>
                                                        <div class="input-group">
                                                            <input type="text" class="form-control" id="img" name="img" value="${promocion.img}" placeholder="Ingresa img" >
                                                            <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                                        </div>
                                                    </div>
                                                            <div class="form-group">
                                                        <label for="estado">Estado de la promocion:</label>
                                                        <div class="input-group">
                                                            <input type="text" class="form-control" id="idEstado" name="idEstado" value="${promocion.idEstado}" readonly="true" placeholder="" >
                                                            <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                                        </div>
                                                    </div>   
                                                                
                                                     <div>
                                                        <label for="codigoEmpresa">Promocion de la empresa:</label>
                                                        <div class="input-group">
                                                            <select name="codigoEmpresa" id="codigoEmpresa" class="form-control"> 
                                                                <c:forEach items="${requestScope.listaEmpresas}" var="empresa">
                                                                           
                                                                    <option value="${empresa.codigoEmpresa}">${empresa.nombre}</option>
                                
                                                                </c:forEach>
                                                            </select>  
                                                            <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                                        </div>
                                                    </div><br>           
                                                                

                                                    <input type="submit" class="btn btn-info" value="Guardar" name="Guardar">
                                                    <a class="btn btn-danger" href="${pageContext.request.contextPath}/promociones.do?op=listar">Cancelar</a>
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

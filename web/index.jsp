<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="usuario" class="sv.edu.udb.www.beans.Cliente" scope="session"/>
<jsp:setProperty name="usuario" property="correo" value="${sessionScope.correo}"/>
<jsp:setProperty name="usuario" property="contraseña" value="${sessionScope.password}"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>La Cuponera</title>
        <jsp:include page="/cabecera.jsp"/>
    </head>
    <body>
        
        <jsp:include page="/menu.jsp"/>
        <img src="img/6.jpg" style="width: 100%; height: auto;">       
        
        <div class="container">
            
            <div style="padding: 2%;" class="text-center">Promociones Destacadas</div>
    
            <table border="0" width="100%" align="center">
                
            <br>
            <c:forEach items="${requestScope.listaPromociones}" var="promociones">
                <div class="col-sm-4">
                    <div>
                        <h5 class="text-center" style="padding: 3%;">${promociones.titulo}</h5>
                        <img src="img/promociones/libros.png" style="width: 100%;"><br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <strong class="btn btn-default">Precio Regular: $${promociones.precioRegular}</strong>
                        <strong class="btn btn-success">Precio Oferta: $${promociones.precioOferta}</strong>
                        <p style="text-align: justify; padding: 5%;">${promociones.descripcion}</p>
                        <a class="ghost-button-semi-transparent" href='${pageContext.request.contextPath}/compra.do?id=${promociones.idPromocion}'>Comprar Cupon</a>
                    </div>
                </div>
            </c:forEach>    
        </div>
        <hr>
        <jsp:include page="/footer.jsp"/>
    </body>
</html>

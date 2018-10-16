<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>La Cuponera | Accesorios</title>
         <jsp:include page="/cabecera.jsp"/>
    </head>
    <body>
         <jsp:include page="/menu.jsp"/>

        <!-- FIN MENU -->

        <h1 class="text-center">La Cuponera - Salud</h1>
        <div class="container">
            
            <c:forEach items="${requestScope.listaPromociones}" var="promociones">
                <div class="col-sm-4">
                    <div>
                        <h5 class="text-center" style="padding: 3%;">${promociones.titulo}</h5>
                        <img src="img/promociones/libros.png" style="width: 100%;"><br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <strong class="btn btn-default">Precio Regular: $${promociones.precioRegular}</strong>
                        <strong class="btn btn-success">Precio Oferta: $${promociones.precioOferta}</strong>
                        <p style="text-align: justify; padding: 5%;">${promociones.descripcion}</p>
                        <a class="ghost-button-semi-transparent " href="#">Comprar Cupon</a>
                    </div>
                </div>
            </c:forEach> 
        </div>
         <jsp:include page="/footer.jsp"/>
</html>
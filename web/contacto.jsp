

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>La Cuponera | Contacto</title>
        <jsp:include page="/cabecera.jsp"/>
    </head>
    <body>
    <jsp:include page="/menu.jsp"/>
    <div class="container">   
        <div class="col-sm-8">
            <div>
                <h4 class="text-center" style="padding: 3%;">Visitanos</h4>
                    <img src="img/promociones/libros.png" style="width: 100%;">
                    
                </div>
        </div>
        <div class="col-sm-4">
            <div>
                <h4 class="text-center" style="padding: 3%;">Escribenos</h4>
                <label style="padding: 2%;">Nombres</label>
                <input type="text" class="ghost-button-semi-transparent">
                <label style="padding: 2%;">Apellidos</label>
                <input type="text" class="ghost-button-semi-transparent">
                <label style="padding: 2%;">Correo</label>
                <input type="text" class="ghost-button-semi-transparent">
                <label style="padding: 2%;">Mensaje</label>
                <textarea class="ghost-button-semi-transparent"></textarea>
                <hr>
                <a class="ghost-button-semi-transparent " href="#">Enviar</a>
            </div>
        </div>
        
    </div>
    </body>
    <jsp:include page="/footer.jsp"/>
</html>

<%-- 
    Document   : Logins
    Created on : 07-sep-2018, 23:16:11
    Author     : Teyker
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <title>La Cuponera</title>
        <jsp:include page="/cabecera.jsp"/>
    </head>
    <style > body {
        background-image: url(img/wall.jpg);
        
             background-size: 101%;
    background-repeat: no-repeat;
}
    </style>
    <body>
        

        <div >
                        
                    <img src="img/ima.png" style="margin-left: 28%;margin-top: 9%;     margin-bottom: 6%;">       
                    <center> 
                    <a class="btn btn-info" href="administradores/loginAdmin.jsp"> Inicio de sesion Administrador</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <a class="btn btn-success" href="${pageContext.request.contextPath}/clientes/login.jsp"> Inicio de sesion Clientes</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <a class="btn btn-primary" href="empresas/loginEmpresa.jsp"> Inicio de sesion Empresas</a>
              </center>
             <jsp:include page="/footer.jsp"/>
        </div>
    </body>
  
</html>

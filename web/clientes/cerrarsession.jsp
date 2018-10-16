<%--<jsp:useBean id="usuario" class="sv.edu.udb.www.beans.Cliente" scope="session"/>
<jsp:getProperty name="usuario" property="Session"/>--%>

<%
    HttpSession sesion = request.getSession();
    sesion.invalidate();   
%>
<jsp:forward page="login.jsp">
    <jsp:param name="exito" value="sesión cerrada  satisfactoriamente "/>
</jsp:forward>

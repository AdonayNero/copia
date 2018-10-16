/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sv.edu.udb.www.beans.Cliente;
import sv.edu.udb.www.model.ClientesModel;
import sv.edu.udb.www.utils.Correo;
import sv.edu.udb.www.utils.Validaciones;

/**
 *
 * @author Usuario
 */
@WebServlet(name = "ClientesController", urlPatterns = {"/clientes.do"})
public class ClientesController extends HttpServlet {

    //Crenado una instancia del modelo de Clientes
    ClientesModel bd = new ClientesModel();
    //Preparando un ArrayList para el manejo de errores
    ArrayList<String> listaErrores = new ArrayList<>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String operacion = request.getParameter("operacion");

        switch (operacion) {
            case "login":
                request.getRequestDispatcher("/clientes/login.jsp").forward(request, response);
                break;

            case "registro":
                request.getRequestDispatcher("/clientes/registro.jsp").forward(request, response);
                break;

            case "insertar":
                insertar(request, response);
                break;

            case "val":
                confirmar(request, response);
                break;

            case "check_login":
                verificarSesion(request, response);
                break;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void insertar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        PrintWriter out = response.getWriter();
        listaErrores.clear();

        try {
            Cliente auxCliente = new Cliente();
            auxCliente.setDui(request.getParameter("dui"));
            auxCliente.setNombres(request.getParameter("nombres"));
            auxCliente.setApellidos(request.getParameter("apellidos"));
            auxCliente.setCorreo(request.getParameter("correo"));
            auxCliente.setTelefono(request.getParameter("telefono"));
            auxCliente.setContraseña(request.getParameter("clave"));

            if (Validaciones.isEmpty(auxCliente.getDui())) {
                listaErrores.add("El campo de DUI es obligatorio");
            }
            if (Validaciones.isEmpty(auxCliente.getNombres())) {
                listaErrores.add("El nombre es obligatorio");
            }
            if (Validaciones.isEmpty(auxCliente.getApellidos())) {
                listaErrores.add("El apellido es obligatorio");
            }

            if (Validaciones.isEmpty(auxCliente.getTelefono())) {
                listaErrores.add("El telefono es obligatorio");
            }

            if (Validaciones.isEmpty(auxCliente.getContraseña())) {
                listaErrores.add("La contraseña es obligatoria");
            }

            if (Validaciones.isEmpty(auxCliente.getCorreo())) {
                listaErrores.add("El correo es obligatorio");
            } else if (!Validaciones.esCorreo(auxCliente.getCorreo())) {
                listaErrores.add("El correo electronico no tiene formato correcto");
            }

            if (listaErrores.size() > 0) {
                //Hay errores
                request.setAttribute("listaErrores", listaErrores);
                request.setAttribute("cliente", auxCliente);
                request.getRequestDispatcher("/clientes.do?operacion=registro").forward(request, response);
            } else {
                //La validacion paso
                //Generando cadena aleatoria para la confirmacion de la cuenta
                String cadenaAleatoria = UUID.randomUUID().toString();
                //Inserto los datos del cliente
                if (bd.insertarCliente(auxCliente, cadenaAleatoria) > 0) {
                    request.getSession().setAttribute("exito", "Cliente registrado "
                            + "exitosamente. Se te ha enviado un correo para que "
                            + "confirmes tu cuenta");
                    //Preparo el texto del correo
                    String texto = "Te has registrado exitosamente.<br>";
                    texto += "Para confirmar tu cuenta debes dar click ";
                    //Poner enfasis en como se envia el id de confirmacion
                    //Generado aleatoriamente
                    String enlace = request.getRequestURL().toString()
                            + "?operacion=val&id=" + cadenaAleatoria;
                    texto += "<a target='a_blank' "
                            + "href='" + enlace + "'>aqui</a>";

                    //Creo un objeto de tipo Correo
                    Correo correo = new Correo();
                    //Defino los valores de los atributos del correo
                    correo.setAsunto("Confirmacion de registro");
                    correo.setMensaje(texto);
                    correo.setDestinatario(auxCliente.getCorreo());
                    //Obteniendo la url de la carpeta assets/pdf
                    String directorio = getServletContext().getRealPath("assets/pdf");
                    correo.setRutaAdjunto(directorio + "\\Proyecto.pdf");
                    correo.setNombreAdjunto("Especificaciones del proyecto de catedra.pdf");
                    //Finalmente envio el correo
                    correo.enviarCorreo();
                    response.sendRedirect(request.getContextPath() + "/clientes.do?operacion=login");
                } else {
                    //No se pudo insertar el usuario
                    listaErrores.add("Este nombre de usuario ya esta registrado");
                    request.setAttribute("listaErrores", listaErrores);
                    request.setAttribute("cliente", auxCliente);
                    request.getRequestDispatcher("/clientes.do?operacion=regristro").forward(request, response);

                }
            }

        } catch (ServletException | SQLException ex) {
            Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        out.close();

    }

    private void confirmar(HttpServletRequest request, HttpServletResponse response) {
        try {
            bd.confirmarCuenta(request.getParameter("id"));
            request.getSession().setAttribute("exito", "Cuenta verificada exitosamente, "
                    + "ya puedes iniciar sesion");

            response.sendRedirect(request.getContextPath() + "/clientes.do?operacion=login");
        } catch (SQLException | IOException ex) {
            Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void verificarSesion(HttpServletRequest request, HttpServletResponse response) {
        try {
            Cliente miCliente = new Cliente();
            miCliente.setCorreo(request.getParameter("correo"));
            miCliente.setContraseña(request.getParameter("clave"));
            int estado = bd.verificarSesion(miCliente);

            switch (estado) {
                case -1:
                    request.getSession().setAttribute("fracaso", "Correo electronico y/o contraseña incorrecta");
                    response.sendRedirect(request.getContextPath() + "/clientes.do?operacion=login");
                    break;

                case 0:
                    request.getSession().setAttribute("fracaso", "Cuenta no verificada");
                    response.sendRedirect(request.getContextPath() + "/clientes.do?operacion=login");
                    break;

                case 1:
                    //request.getSession().setAttribute("exito", "CREDENCIALES CORRECTAS");
                    request.getSession().setAttribute("correo", miCliente.getCorreo());
                    request.getSession().setAttribute("password", miCliente.getContraseña());
                    response.sendRedirect(request.getContextPath() + "/index.do");
                    break;

            }
            //response.sendRedirect(request.getContextPath() + "/clientes.do?operacion=login");

        } catch (SQLException | IOException ex) {
            Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

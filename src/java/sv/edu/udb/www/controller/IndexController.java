package sv.edu.udb.www.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sv.edu.udb.www.beans.Promocion;
import sv.edu.udb.www.model.IndexModel;
import sv.edu.udb.www.utils.Validaciones;
/**
 *
 * @author Kevin
 */
@WebServlet(name = "IndexController", urlPatterns = {"/index.do"})
public class IndexController extends HttpServlet {

     

    IndexModel modelo =  new IndexModel();
    ArrayList<String> listaErrores = new ArrayList<>();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if(request.getParameter("op")==null){
                listar(request, response);
                return;
            }
            String categoria = request.getParameter("op");
            switch(categoria){
                case "accesorios":
                    listarAccesorios(request, response);
                    break;
                case "restaurante":
                    listarRestaurante(request, response);
                    break;
                case "moda":
                    listarModa(request, response);
                    break;
                case "salud":
                    listarSalud(request, response);
                    break;
                case "automotriz":
                    listarAutomotriz(request, response);
                    break;
                case "tecnologia":
                    listarTecnologia(request, response);
                    break;
                case "diversion":
                    listarDiversion(request, response);
                    break;
                default:
                    request.getRequestDispatcher("/error404.jsp").forward(request, response);
            }

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
    
     private void listar(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("listaPromociones", modelo.listarPromociones());
            //Redirecciones en el cliente
            // response.sendRedirect("ruta");
            //Redirecciones en el servidor
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void listarAccesorios(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("listaPromociones", modelo.listarPromociones());
            //Redirecciones en el cliente
            // response.sendRedirect("ruta");
            //Redirecciones en el servidor
            request.getRequestDispatcher("/categorias/accesorios.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void listarModa(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("listaPromociones", modelo.listarPromociones());
            //Redirecciones en el cliente
            // response.sendRedirect("ruta");
            //Redirecciones en el servidor
            request.getRequestDispatcher("/categorias/moda.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void listarSalud(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("listaPromociones", modelo.listarPromociones());
            //Redirecciones en el cliente
            // response.sendRedirect("ruta");
            //Redirecciones en el servidor
            request.getRequestDispatcher("/categorias/salud.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void listarTecnologia(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("listaPromociones", modelo.listarPromociones());
            //Redirecciones en el cliente
            // response.sendRedirect("ruta");
            //Redirecciones en el servidor
            request.getRequestDispatcher("/categorias/tecnologia.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        private void listarRestaurante(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("listaPromociones", modelo.listarPromociones());
            //Redirecciones en el cliente
            // response.sendRedirect("ruta");
            //Redirecciones en el servidor
            request.getRequestDispatcher("/categorias/restaurantes.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void listarDiversion(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("listaPromociones", modelo.listarPromociones());
            //Redirecciones en el cliente
            // response.sendRedirect("ruta");
            //Redirecciones en el servidor
            request.getRequestDispatcher("/categorias/diversion.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void listarAutomotriz(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("listaPromociones", modelo.listarPromociones());
            //Redirecciones en el cliente
            // response.sendRedirect("ruta");
            //Redirecciones en el servidor
            request.getRequestDispatcher("/categorias/automotriz.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
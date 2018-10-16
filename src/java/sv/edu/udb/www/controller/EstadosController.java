
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
import sv.edu.udb.www.model.EmpresasModel;
import sv.edu.udb.www.model.EsperaModel;



@WebServlet(name = "EstadosController", urlPatterns = {"/estado.do"})
public class EstadosController extends HttpServlet {

    EsperaModel modelo =  new EsperaModel();
    EmpresasModel modeloEmpresas = new EmpresasModel();
    ArrayList<String> listaErrores = new ArrayList<>();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
               if(request.getParameter("op")==null){
                listar(request, response);
                return;
            }
            String operacion = request.getParameter("op");
            switch(operacion){
              
                case "listar":
                    listar(request, response);
                    break;
                case "aprobar":
                  aprobar(request, response);
                    break;
                case "aprobadas":
                   aprobadas(request, response);     
               case "rechazada":
                    rechazada(request, response);
                    break;
                case "rechazadas":
                  rechazadas(request, response);    
               
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
            request.setAttribute("listaEsperas", modelo.listarEspera());
            //Redirecciones en el cliente
            // response.sendRedirect("ruta");
            //Redirecciones en el servidor
            request.getRequestDispatcher("/estados/listaEspera.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     /************************* METODO APROBAR *********************************/
    private void aprobar(HttpServletRequest request, HttpServletResponse response) {
        try {
            String codigo = request.getParameter("id");
            if(modelo.aprobarPromocion(codigo)>0){
                request.getSession().setAttribute("exito", "Promocion aprobada correctamente");
            }
            else{
                request.getSession().setAttribute("fracaso", "No se puede aprobar esta promocion");
            }
            response.sendRedirect(request.getContextPath() + "/estado.do?op=listar");
            //request.getRequestDispatcher("/rubros.do?op=listar").forward(request, response);
        } catch (SQLException | IOException ex) {
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /***************************************************************************/
    
    /************************************************************************************/
     private void aprobadas(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("listaEsperas", modelo.listarAprobada());
            //Redirecciones en el cliente
            // response.sendRedirect("ruta");
            //Redirecciones en el servidor
            request.getRequestDispatcher("/estados/listaAprobada.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     
    /************************* METODO rechazar *********************************/
    private void rechazada(HttpServletRequest request, HttpServletResponse response) {
        try {
            String codigo = request.getParameter("id");
            if(modelo.rechazarPromocion(codigo)>0){
                request.getSession().setAttribute("exito", "Promocion rechazada correctamente");
            }
            
            response.sendRedirect(request.getContextPath() + "/estado.do?op=listar");
            //request.getRequestDispatcher("/rubros.do?op=listar").forward(request, response);
        } catch (SQLException | IOException ex) {
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /***************************************************************************/ 
     
    /************************************************************************************/
     private void rechazadas(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("listaEsperas", modelo.listarRechazada());
            //Redirecciones en el cliente
            // response.sendRedirect("ruta");
            //Redirecciones en el servidor
            request.getRequestDispatcher("/estados/listaRechazada.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


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
import sv.edu.udb.www.beans.Rubro;
import sv.edu.udb.www.model.RubrosModel;
import sv.edu.udb.www.utils.Validaciones;


@WebServlet(name = "RubrosController", urlPatterns = {"/rubros.do"})
public class RubrosController extends HttpServlet {

    RubrosModel modelo =  new RubrosModel();
    ArrayList<String> listaErrores = new ArrayList<>();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try{
            if(request.getParameter("op")==null){
                listar(request, response);
                return;
            }
            String operacion = request.getParameter("op");
            switch(operacion){
                case "listar":
                    listar(request, response);
                    break;
                case "nuevo":
                    request.getRequestDispatcher("/rubros/nuevoRubro.jsp").forward(request, response);
                    break;
                case "insertar":
                   insertar(request, response);
                    break;
                case "obtener":
                    obtener(request, response);
                    break;
                case "modificar":
                    modificar(request, response);
                    break;
                case "eliminar":
                    eliminar(request, response);
                    break;
                default:
                    request.getRequestDispatcher("/error404.jsp").forward(request, response);
            }
        
        } finally{
            out.close();
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
    
    
    /********************** METODO LISTAR *************************************/
    private void listar(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("listaRubros", modelo.listarRubros());
            //Redirecciones en el cliente
            // response.sendRedirect("ruta");
            //Redirecciones en el servidor
            request.getRequestDispatcher("/rubros/listaRubros.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(RubrosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(RubrosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RubrosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*************************************************************************/
    
    /******************* METODO NUEVO ****************************************/
    private void insertar(HttpServletRequest request, HttpServletResponse response) {
       try{
           listaErrores.clear();
       Rubro miRubro = new Rubro();//parametros del formulario
       miRubro.setIdRubro(request.getParameter("codigo"));
       miRubro.setNombreRubro(request.getParameter("nombreRubro"));
       
       if(Validaciones.isEmpty(miRubro.getIdRubro())){
               listaErrores.add("El codigo del rubro es obligatorio");
       }
       else if(!Validaciones.esCodigoRubro(miRubro.getIdRubro())){
               listaErrores.add("El codigo del rubro debe tener el formato RU0000");
       }
       if(Validaciones.isEmpty(miRubro.getNombreRubro())){
               listaErrores.add("El nombre del rubro es obligatorio");
       }
       
       if(listaErrores.size()>0){// hay errores de validacion
             request.setAttribute("rubro", miRubro);
             request.setAttribute("listaErrores", listaErrores);
             request.getRequestDispatcher("/rubros.do?op=nuevo").forward(request, response);
       }
       else{//no hay errores de validacion
           if(modelo.insertarRubro(miRubro)>0){//se inserto correctamente
              request.getSession().setAttribute("exito", "Rubro registrado correctamente");
              response.sendRedirect(request.getContextPath() + "/rubros.do?op=listar");
           }
           else{
              request.getSession().setAttribute("fracaso", "ERROR!! No se pudo registrar este rubro. " 
                      + "Ya existe otro rubro con este codigo");
               response.sendRedirect(request.getContextPath() + "/rubros.do?op=listar");
           };
       }
 
       } catch (ServletException ex) {
            Logger.getLogger(RubrosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RubrosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RubrosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**************************************************************************/
    
    /******************** METODO OBTENER ID ******************************************/
    private void obtener(HttpServletRequest request, HttpServletResponse response) {
        try {
            String codigo= request.getParameter("id");
            Rubro miRubro = modelo.obtenerRubro(codigo);
            if (miRubro != null) {
                request.setAttribute("rubro", miRubro);
                request.getRequestDispatcher("/rubros/editarRubro.jsp").forward(request, response);
            }
            else{
                response.sendRedirect(request.getContextPath() + "/error404.jsp");
           
            }
        } catch (ServletException | SQLException | IOException ex ) {
            Logger.getLogger(RubrosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /***************************************************************************/
    
    /************************* METODO ELIMINAR *********************************/
    private void eliminar(HttpServletRequest request, HttpServletResponse response) {
        try {
            String codigo = request.getParameter("id");
            if(modelo.eliminarRubro(codigo)>0){
                request.getSession().setAttribute("exito", "Rubro eliminado correctamente");
            }
            else{
                request.getSession().setAttribute("fracaso", "No se puede eliminar este Rubro");
            }
            response.sendRedirect(request.getContextPath() + "/rubros.do?op=listar");
            //request.getRequestDispatcher("/rubros.do?op=listar").forward(request, response);
        } catch (SQLException | IOException ex) {
            Logger.getLogger(RubrosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /***************************************************************************/
    
    /******************* METODO MOFIFICAR ***************************************/
    private void modificar(HttpServletRequest request, HttpServletResponse response) {
       try{
           listaErrores.clear();
       Rubro miRubro = new Rubro();
       miRubro.setIdRubro(request.getParameter("codigo"));
       miRubro.setNombreRubro(request.getParameter("nombreRubro"));
       
       if(Validaciones.isEmpty(miRubro.getIdRubro())){
               listaErrores.add("El codigo del rubro es obligatorio");
       }
       else if(!Validaciones.esCodigoRubro(miRubro.getIdRubro())){
               listaErrores.add("El codigo del rubro debe tener el formato EDI0000");
       }
       if(Validaciones.isEmpty(miRubro.getNombreRubro())){
               listaErrores.add("El nombre del rubro es obligatorio");
       }
       
       if(listaErrores.size()>0){// hay errores de validacion
             request.setAttribute("rubro", miRubro);
             request.setAttribute("listaErrores", listaErrores);
             request.getRequestDispatcher("/rubros/editarRubro.jsp").forward(request, response);
       }
       else{//no hay errores de validacion
           if(modelo.modificarRubro(miRubro)>0){//se modifico correctamente
              request.getSession().setAttribute("exito", "Rubro actualizado correctamente");
              response.sendRedirect(request.getContextPath() + "/rubros.do?op=listar");
           }
           else{
              request.getSession().setAttribute("fracaso", "No se pudo actualizar los datos del rubro.");
               response.sendRedirect(request.getContextPath() + "/rubros.do?op=listar");
           };
       }
 
       } catch (ServletException ex) {
            Logger.getLogger(RubrosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RubrosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RubrosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /******************************************************************************/


}

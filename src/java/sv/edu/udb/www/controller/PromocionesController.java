
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
import sv.edu.udb.www.model.PromocionesModel;
import sv.edu.udb.www.utils.Validaciones;

@WebServlet(name = "PromocionesController", urlPatterns = {"/promociones.do"})
public class PromocionesController extends HttpServlet {
    

    PromocionesModel modelo =  new PromocionesModel();
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
                case "nuevo":
                   nuevo(request, response);
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
            request.getRequestDispatcher("/promociones/listaPromociones.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(PromocionesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(PromocionesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PromocionesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    private void nuevo(HttpServletRequest request, HttpServletResponse response){
        try {
            request.setAttribute("listaEmpresas", modeloEmpresas.listarEmpresas());
            request.getRequestDispatcher("/promociones/nuevaPromocion.jsp").forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(PromocionesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /******************* METODO NUEVO ****************************************/
    private void insertar(HttpServletRequest request, HttpServletResponse response) {
       try{
           listaErrores.clear();
       Promocion miPromocion = new Promocion();//parametros del formulario
       miPromocion.setIdPromocion(request.getParameter("idPromocion"));
       miPromocion.setTitulo(request.getParameter("titulo"));
       
       miPromocion.setFechaInicio(request.getParameter("fechaInicio"));
       miPromocion.setFechaFin(request.getParameter("fechaFin"));
       miPromocion.setFechaLimite(request.getParameter("fechaLimite"));
       miPromocion.setDescripcion(request.getParameter("descripcion"));
       miPromocion.setImg(request.getParameter("img"));
       miPromocion.setCodigoEmpresa(request.getParameter("codigoEmpresa"));
       
       if(Validaciones.isEmpty(miPromocion.getIdPromocion())){
               listaErrores.add("El codigo de la promocion es obligatorio");
       }
       else if(!Validaciones.esCodigoPromocion(miPromocion.getIdPromocion())){
               listaErrores.add("El codigo de la promocion debe tener el formato PRO000");
       }
       if(Validaciones.isEmpty(miPromocion.getTitulo())){
               listaErrores.add("El titulo de la promocion es obligatorio");
       }
        if(Validaciones.isEmpty(miPromocion.getFechaInicio())){
               listaErrores.add("La fecha inicio de la promocion es obligatorio");
       }
        if(Validaciones.isEmpty(miPromocion.getFechaFin())){
               listaErrores.add("La fecha fin de la promocion es obligatorio");
       }
        if(Validaciones.isEmpty(miPromocion.getFechaLimite())){
               listaErrores.add("La fecha limite de la promocion es obligatorio");
       }
        if(Validaciones.isEmpty(miPromocion.getDescripcion().trim())){
               listaErrores.add("La descripcion de la promocion es obligatorio");
       }
        if(Validaciones.isEmpty(miPromocion.getImg())){
               listaErrores.add("La img de la promocion es obligatorio");
       }
       if(Validaciones.esDecimalPositivo(request.getParameter("precioRegular"))){
        miPromocion.setPrecioRegular(Double.parseDouble(request.getParameter("precioRegular")));
       }
       else{
          listaErrores.add("El precio debe ser un numero mayor o igual a cero");
       }
       if(Validaciones.esDecimalPositivo(request.getParameter("precioOferta"))){
        miPromocion.setPrecioOferta(Double.parseDouble(request.getParameter("precioOferta")));
       }
       else{
          listaErrores.add("El precio debe ser un numero mayor o igual a cero");
       }
       
       if(listaErrores.size()>0){// hay errores de validacion
             request.setAttribute("promocion", miPromocion);
             request.setAttribute("listaErrores", listaErrores);
             request.getRequestDispatcher("/promociones.do?op=nuevo").forward(request, response);
       }
       else{//no hay errores de validacion
           if(modelo.insertarPromocion(miPromocion)>0){//se inserto correctamente
              request.getSession().setAttribute("exito", "Promocion registrado correctamente");
              response.sendRedirect(request.getContextPath() + "/promociones.do?op=listar");
           }
           else{
              request.getSession().setAttribute("fracaso", "ERROR!! No se pudo registrar esta promocion. " 
                      + "Ya existe otra promocion con este codigo");
               response.sendRedirect(request.getContextPath() + "/promociones.do?op=listar");
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
             Promocion miPromocion = modelo.obtenerPromocion(codigo);
             
             
            if (miPromocion != null) {
                request.setAttribute("listaEmpresas", modeloEmpresas.listarEmpresas());
                request.setAttribute("promocion", miPromocion);
                request.getRequestDispatcher("/promociones/editarPromocion.jsp").forward(request, response);
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
            if(modelo.eliminarPromocion(codigo)>0){
                request.getSession().setAttribute("exito", "Promocion eliminada correctamente");
            }
            else{
                request.getSession().setAttribute("fracaso", "No se puede eliminar esta promocion");
            }
            response.sendRedirect(request.getContextPath() + "/promociones.do?op=listar");
            //request.getRequestDispatcher("/rubros.do?op=listar").forward(request, response);
        } catch (SQLException | IOException ex) {
            Logger.getLogger(RubrosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /***************************************************************************/
    
     /******************* METODO MODFICAR ****************************************/
    private void modificar(HttpServletRequest request, HttpServletResponse response) {
       try{
           listaErrores.clear();
       Promocion miPromocion = new Promocion();//parametros del formulario
       miPromocion.setIdPromocion(request.getParameter("idPromocion"));
       miPromocion.setTitulo(request.getParameter("titulo"));
       
       miPromocion.setFechaInicio(request.getParameter("fechaInicio"));
       miPromocion.setFechaFin(request.getParameter("fechaFin"));
       miPromocion.setFechaLimite(request.getParameter("fechaLimite"));
       miPromocion.setDescripcion(request.getParameter("descripcion"));
       miPromocion.setImg(request.getParameter("img"));
       miPromocion.setCodigoEmpresa(request.getParameter("codigoEmpresa"));
       miPromocion.setIdEstado(Integer.parseInt(request.getParameter("idEstado")));
       if(Validaciones.isEmpty(miPromocion.getIdPromocion())){
               listaErrores.add("El codigo de la promocion es obligatorio");
       }
       else if(!Validaciones.esCodigoPromocion(miPromocion.getIdPromocion())){
               listaErrores.add("El codigo de la promocion debe tener el formato PRO000");
       }
       if(Validaciones.isEmpty(miPromocion.getTitulo())){
               listaErrores.add("El titulo de la promocion es obligatorio");
       }
        if(Validaciones.isEmpty(miPromocion.getFechaInicio())){
               listaErrores.add("La fecha inicio de la promocion es obligatorio");
       }
        if(Validaciones.isEmpty(miPromocion.getFechaFin())){
               listaErrores.add("La fecha fin de la promocion es obligatorio");
       }
        if(Validaciones.isEmpty(miPromocion.getFechaLimite())){
               listaErrores.add("La fecha limite de la promocion es obligatorio");
       }
        if(Validaciones.isEmpty(miPromocion.getDescripcion().trim())){
               listaErrores.add("La descripcion de la promocion es obligatorio");
       }
        if(Validaciones.isEmpty(miPromocion.getImg())){
               listaErrores.add("La img de la promocion es obligatorio");
       }
       if(Validaciones.esDecimalPositivo(request.getParameter("precioRegular"))){
        miPromocion.setPrecioRegular(Double.parseDouble(request.getParameter("precioRegular")));
       }
       else{
          listaErrores.add("El precio debe ser un numero mayor o igual a cero");
       }
       if(Validaciones.esDecimalPositivo(request.getParameter("precioOferta"))){
        miPromocion.setPrecioOferta(Double.parseDouble(request.getParameter("precioOferta")));
       }
       else{
          listaErrores.add("El precio debe ser un numero mayor o igual a cero");
       }
       
       if(listaErrores.size()>0){// hay errores de validacion
             request.setAttribute("promocion", miPromocion);
             request.setAttribute("listaErrores", listaErrores);
             request.getRequestDispatcher("/promociones.do?op=nuevo").forward(request, response);
       }
       else{//no hay errores de validacion
           if(modelo.modificarPromocion(miPromocion)>0){//se inserto correctamente
              request.getSession().setAttribute("exito", "Promocion actualizado correctamente");
              response.sendRedirect(request.getContextPath() + "/promociones.do?op=listar");
           }
           else{
              request.getSession().setAttribute("fracaso", "ERROR!! No se pudo editar esta promocion. ");
               response.sendRedirect(request.getContextPath() + "/promociones.do?op=listar");
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

}

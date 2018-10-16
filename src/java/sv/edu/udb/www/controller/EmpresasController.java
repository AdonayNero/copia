
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
import sv.edu.udb.www.beans.Empresa;
import sv.edu.udb.www.model.EmpresasModel;
import sv.edu.udb.www.model.RubrosModel;
import sv.edu.udb.www.utils.Correo;
import sv.edu.udb.www.utils.Validaciones;


@WebServlet(name = "EmpresasController", urlPatterns = {"/empresas.do"})
public class EmpresasController extends HttpServlet {

    EmpresasModel modelo =  new EmpresasModel();
    RubrosModel modeloRubro = new RubrosModel();
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
                case "login":
                       request.getRequestDispatcher("/empresas/loginEmpresa.jsp").forward(request, response);
                    break;
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
                case "val":
                     confirmar(request, response);
                    break;
                case "check_login":
                      verificarSesion(request, response);
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
            request.setAttribute("listaEmpresas", modelo.listarEmpresas());
            //Redirecciones en el cliente
            // response.sendRedirect("ruta");
            //Redirecciones en el servidor
            request.getRequestDispatcher("/empresas/listaEmpresas.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void nuevo(HttpServletRequest request, HttpServletResponse response){
        try {
            request.setAttribute("listaRubros", modeloRubro.listarRubros());
            request.getRequestDispatcher("/empresas/nuevaEmpresa.jsp").forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /******************* METODO NUEVO ****************************************/
    private void insertar(HttpServletRequest request, HttpServletResponse response) {
       try{
           listaErrores.clear();
       Empresa miEmpresa = new Empresa();//parametros del formulario
       miEmpresa.setCodigoEmpresa(request.getParameter("codigo"));
       miEmpresa.setNombre(request.getParameter("nombre"));
       miEmpresa.setDireccion(request.getParameter("direccion"));
       miEmpresa.setContacto(request.getParameter("contacto"));
       miEmpresa.setTelefono(request.getParameter("telefono"));
       miEmpresa.setCorreo(request.getParameter("correo"));
       miEmpresa.setClave(request.getParameter("clave"));
       miEmpresa.setIdRubro(request.getParameter("rubro"));
       
       if(Validaciones.isEmpty(miEmpresa.getCodigoEmpresa())){
               listaErrores.add("El codigo de la empresa es obligatorio");
       }
       else if(!Validaciones.esCodigoEmpresa(miEmpresa.getCodigoEmpresa())){
               listaErrores.add("El codigo de la empresa debe tener el formato EMP000");
       }
       if(Validaciones.isEmpty(miEmpresa.getNombre())){
               listaErrores.add("El nombre de la empresa es obligatorio");
       }
       if(Validaciones.isEmpty(miEmpresa.getDireccion())){
               listaErrores.add("La direccion de la empresa es obligatorio");
       }
       if(Validaciones.isEmpty(miEmpresa.getContacto())){
               listaErrores.add("El contacto de la empresa es obligatorio");
       }
       if(Validaciones.isEmpty(miEmpresa.getTelefono())){
               listaErrores.add("El telefono de la empresa es obligatorio");
       }
       else if(!Validaciones.esTelefono(miEmpresa.getTelefono())){
               listaErrores.add("El telefono no cumple el formato 0000-0000");
       }
       if(Validaciones.isEmpty(miEmpresa.getCorreo())){
               listaErrores.add("El correo de la empresa es obligatorio");
       }
       else if(!Validaciones.esCorreo(miEmpresa.getCorreo())){
               listaErrores.add("El correo no cumple el formato");
       }
       if(Validaciones.isEmpty(miEmpresa.getClave())){
               listaErrores.add("El password de la empresa es obligatorio");
       }
       if(Validaciones.esDecimalPositivo(request.getParameter("comision"))){
        miEmpresa.setComision(Double.parseDouble(request.getParameter("comision")));
       }
       else{
          listaErrores.add("La comision debe ser un numero mayor o igual a cero");
       }
       
       
       if(listaErrores.size()>0){// hay errores de validacion
             request.setAttribute("empresa", miEmpresa);
             request.setAttribute("listaErrores", listaErrores);
             request.getRequestDispatcher("/empresas.do?op=nuevo").forward(request, response);
       }
       else{//no hay errores de validacion
           String cadenaAleatoria = UUID.randomUUID().toString();
           if(modelo.insertarEmpresa(miEmpresa, cadenaAleatoria)>0){//se inserto correctamente
                request.getSession().setAttribute("exito", "Administrador registrado"
                   + " exitosamente. Se le ha enviado un correo para que"
                   + " confirme su cuenta");
                 //preparo el texto del correo
                  String texto = "Bienvenido a la cuponera, Registro exitosamente.<br>";
                  texto += "Por Favor, Para confirmar tu cuenta debes dar click ";
                  //poner enfasis en como se envia el id de confirmacion
                  //generado aleatoriamente
                  String enlace=request.getRequestURL().toString() + 
                          "?op=val&id=" + cadenaAleatoria;
                  texto += "<a target='a_blank' "
                          + "href='" + enlace + "'>aqui</a>";
                  
                  //creo un objeto de tipo correo
                  Correo correo = new Correo();
                  //defino las valores de los atributos del correo
                  correo.setAsunto("Confirmacion de registro");
                  correo.setMensaje(texto);
                  correo.setDestinatario(miEmpresa.getCorreo());
                  //obteniendo la url fisica de la carpeta assets/pdf
                  String directorio = getServletContext().getRealPath("assets/pdf");
                  correo.setRutaAdjunto(directorio + "\\proyecto.pdf");
                  correo.setNombreAdjunto("Especificaciones del proyecto de catedra.pdf");
                  //finalmente envio el correo
                  correo.enviarCorreo();
                  response.sendRedirect(request.getContextPath() + "/empresas.do?op=listar");
 
           }
           else{
                listaErrores.add("Este nombre correo ya esta registrado");
                    request.setAttribute("listaErrores", listaErrores);
                    request.setAttribute("empresa", listaErrores);
              request.getSession().setAttribute("fracaso", "ERROR!! No se pudo registrar esta empresa. " 
                      + "Ya existe otro empresa con este codigo");
               response.sendRedirect(request.getContextPath() + "/empresas.do?op=listar");
           };
       }
 
       } catch (ServletException ex) {
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**************************************************************************/
    
    
     /***************** CONFIRMAR *************************************************/
    private void confirmar(HttpServletRequest request, HttpServletResponse response){
          try{
             modelo.confirmarCuenta(request.getParameter("id"));
             request.getSession().setAttribute("exito", " Cuenta verificada exitosamente, "
             + " ya puedes iniciar sesion");
             
             response.sendRedirect(request.getContextPath() + "/empresas.do?op=login");
          }catch(SQLException | IOException ex){
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    /*****************************************************************************/
    
    /****************** METODO VERIFICACION ************************************/
     private void verificarSesion(HttpServletRequest request, HttpServletResponse response){
         try{
            Empresa miEmpresa = new Empresa();
            miEmpresa.setCorreo(request.getParameter("correo"));
            miEmpresa.setClave(request.getParameter("password"));
            int estado = modelo.verificarSesion(miEmpresa);
            
            switch(estado){
                case -1://si las credencias son correctas
                    request.getSession().setAttribute("fracaso", "Usuario y/o contraseña incorrecta");
                      response.sendRedirect(request.getContextPath() + "/empresas.do?op=login");
                    break;
                case 0://si la cuenta no esta validada
                     request.getSession().setAttribute("fracaso", "Cuenta no verificada");
                       response.sendRedirect(request.getContextPath() + "/empresas.do?op=login");
                    break;
                case 1://se las credenciales son correctas y las cuenta es validada
                      
                        response.sendRedirect(request.getContextPath() + "/indexEmpresa.jsp");
                    break;
            }
            
         
         }catch(SQLException | IOException ex){
           Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
         } 
     
     }
     /****************************************************************************/

     /************************* METODO ELIMINAR *********************************/
    private void eliminar(HttpServletRequest request, HttpServletResponse response) {
        try {
            String codigo = request.getParameter("id");
            if(modelo.eliminarEmpresa(codigo)>0){
                request.getSession().setAttribute("exito", "Empresa eliminado correctamente");
            }
            else{
                request.getSession().setAttribute("fracaso", "No se puede eliminar esta empresa");
            }
            response.sendRedirect(request.getContextPath() + "/empresas.do?op=listar");
            //request.getRequestDispatcher("/rubros.do?op=listar").forward(request, response);
        } catch (SQLException | IOException ex) {
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /***************************************************************************/
    
    /******************** METODO OBTENER ID ******************************************/
    private void obtener(HttpServletRequest request, HttpServletResponse response) {
        try {
            String codigo= request.getParameter("id");
            Empresa miEmpresa = modelo.obtenerEmpresa(codigo);
             
          
            if (miEmpresa != null) {
                request.setAttribute("listaRubros", modeloRubro.listarRubros());
                request.setAttribute("empresa", miEmpresa);
                request.getRequestDispatcher("/empresas/editarEmpresa.jsp").forward(request, response);
            }
            else{
                response.sendRedirect(request.getContextPath() + "/error404.jsp");
           
            }
        } catch (ServletException | SQLException | IOException ex ) {
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /***************************************************************************/
    
     /******************* METODO MOFIFICAR ***************************************/
    private void modificar(HttpServletRequest request, HttpServletResponse response) {
        try {
            listaErrores.clear();

            Empresa miEmpresa = new Empresa();//parametros del formulario
            miEmpresa.setCodigoEmpresa(request.getParameter("codigo"));
            miEmpresa.setNombre(request.getParameter("nombre"));
            miEmpresa.setDireccion(request.getParameter("direccion"));
            miEmpresa.setContacto(request.getParameter("contacto"));
            miEmpresa.setTelefono(request.getParameter("telefono"));
            miEmpresa.setCorreo(request.getParameter("correo"));
            // miEmpresa.setClave(request.getParameter("clave"));
            miEmpresa.setIdRubro(request.getParameter("rubro"));

            if (Validaciones.isEmpty(miEmpresa.getCodigoEmpresa())) {
                listaErrores.add("El codigo de la empresa es obligatorio");
            } else if (!Validaciones.esCodigoEmpresa(miEmpresa.getCodigoEmpresa())) {
                listaErrores.add("El codigo de la empresa debe tener el formato EMP000");
            }
            if (Validaciones.isEmpty(miEmpresa.getNombre())) {
                listaErrores.add("El nombre de la empresa es obligatorio");
            }
            if (Validaciones.isEmpty(miEmpresa.getDireccion())) {
                listaErrores.add("La direccion de la empresa es obligatorio");
            }
            if (Validaciones.isEmpty(miEmpresa.getContacto())) {
                listaErrores.add("El contacto de la empresa es obligatorio");
            }
            if (Validaciones.isEmpty(miEmpresa.getTelefono())) {
                listaErrores.add("El telefono de la empresa es obligatorio");
            } else if (!Validaciones.esTelefono(miEmpresa.getTelefono())) {
                listaErrores.add("El telefono no cumple el formato 0000-0000");
            }
            if (Validaciones.isEmpty(miEmpresa.getCorreo())) {
                listaErrores.add("El correo de la empresa es obligatorio");
            } else if (!Validaciones.esCorreo(miEmpresa.getCorreo())) {
                listaErrores.add("El correo no cumple el formato");
            }
            //if(Validaciones.isEmpty(miEmpresa.getClave())){
            //         listaErrores.add("El password de la empresa es obligatorio");
            //  }
            if (Validaciones.esDecimalPositivo(request.getParameter("comision"))) {
                miEmpresa.setComision(Double.parseDouble(request.getParameter("comision")));
            } else {
                listaErrores.add("La comision debe ser un numero mayor o igual a cero");
            }

            if (listaErrores.size() > 0) {// hay errores de validacion
                request.setAttribute("empresa", miEmpresa);
                request.setAttribute("listaErrores", listaErrores);
                request.getRequestDispatcher("/empresas.do?op=nuevo").forward(request, response);
            } else {//no hay errores de validacion
                if (modelo.modificarEmpresa(miEmpresa) > 0) {//se modifico correctamente
                    request.getSession().setAttribute("exito", "Empresa actualizado correctamente");
                    response.sendRedirect(request.getContextPath() + "/empresas.do?op=listar");
                } else {
                    request.getSession().setAttribute("fracaso", "No se pudo actualizar los datos de la empresa.");
                    response.sendRedirect(request.getContextPath() + "/empresas.do?op=listar");
                };
            }

        } catch (ServletException ex) {
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * ***************************************************************************
     */

}

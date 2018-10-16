
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
import sv.edu.udb.www.beans.Dependiente;
import sv.edu.udb.www.model.DependientesModel;
import sv.edu.udb.www.model.EmpresasModel;
import sv.edu.udb.www.utils.Correo;
import sv.edu.udb.www.utils.Validaciones;

@WebServlet(name = "DependientesController", urlPatterns = {"/dependientes.do"})
public class DependientesController extends HttpServlet {

    DependientesModel modelo = new DependientesModel();
    EmpresasModel modeloEmpresa =  new EmpresasModel();
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
                       request.getRequestDispatcher("/login.jsp").forward(request, response);
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
                    request.getRequestDispatcher("/errores/error404.jsp").forward(request, response);
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
            request.setAttribute("listaDependientes", modelo.listarDependientes());
            //Redirecciones en el cliente
            // response.sendRedirect("ruta");
            //Redirecciones en el servidor
            request.getRequestDispatcher("/dependientes/listaDependientes.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void nuevo(HttpServletRequest request, HttpServletResponse response) {
       try {
            request.setAttribute("listaEmpresas", modeloEmpresa.listarEmpresas());
            request.getRequestDispatcher("/dependientes/nuevoDependiente.jsp").forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     /******************* METODO NUEVO ****************************************/
    private void insertar(HttpServletRequest request, HttpServletResponse response) {
       try{
           listaErrores.clear();
       Dependiente miDependiente = new Dependiente();//parametros del formulario
       miDependiente.setIdDependiente(request.getParameter("codigo"));
       miDependiente.setNombreDep(request.getParameter("nombreDep"));
       miDependiente.setApellido(request.getParameter("apellido"));
       miDependiente.setCorreo(request.getParameter("correo"));
       miDependiente.setClave(request.getParameter("clave"));
       miDependiente.setCodigoEmpresa(request.getParameter("codigoEmpresa"));
       
       if(Validaciones.isEmpty(miDependiente.getIdDependiente())){
               listaErrores.add("El codigo del dependiente es obligatorio");
       }
       else if(!Validaciones.esCodigoDependiente(miDependiente.getIdDependiente())){
               listaErrores.add("El codigo del dependiente debe tener el formato DEP000");
       }
       if(Validaciones.isEmpty(miDependiente.getNombreDep())){
               listaErrores.add("El nombre del dependiente es obligatorio");
       }
       if(Validaciones.isEmpty(miDependiente.getApellido())){
               listaErrores.add("El apellido del dependiente es obligatorio");
       }
       if(Validaciones.isEmpty(miDependiente.getCorreo())){
               listaErrores.add("El correo del dependiente es obligatorio");
       }
       else if(!Validaciones.esCorreo(miDependiente.getCorreo())){
               listaErrores.add("El correo del dependiente no cumple el formato");
       }
       if(Validaciones.isEmpty(miDependiente.getClave())){
               listaErrores.add("El password del dependiente es obligatorio");
       }
       
       if(listaErrores.size()>0){// hay errores de validacion
             request.setAttribute("dependiente", miDependiente);
             request.setAttribute("listaErrores", listaErrores);
             request.getRequestDispatcher("/dependientes.do?op=nuevo").forward(request, response);
       }
       else{//no hay errores de validacion
           String cadenaAleatoria = UUID.randomUUID().toString();
           if(modelo.insertarDependiente(miDependiente, cadenaAleatoria)>0){//se inserto correctamente
               request.getSession().setAttribute("exito", "Dependiente registrado"
                   + " exitosamente. Se le ha enviado un correo para que"
                   + " confirme su cuenta");
                 //preparo el texto del correo
                  String texto = "Bienvenido a la cuponera dependiente, Registro exitosamente.<br>";
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
                  correo.setDestinatario(miDependiente.getCorreo());
                  //obteniendo la url fisica de la carpeta assets/pdf
                  String directorio = getServletContext().getRealPath("assets/pdf");
                  correo.setRutaAdjunto(directorio + "\\proyecto.pdf");
                  correo.setNombreAdjunto("Especificaciones del proyecto de catedra.pdf");
                  //finalmente envio el correo
                  correo.enviarCorreo();
              response.sendRedirect(request.getContextPath() + "/dependientes.do?op=listar");
           }
           else{
                listaErrores.add("Este nombre correo ya esta registrado");
                    request.setAttribute("listaErrores", listaErrores);
                    request.setAttribute("dependiente", listaErrores);
              request.getSession().setAttribute("fracaso", "ERROR!! No se pudo registrar este dependiente. " 
                      + "Ya existe otro dependiente con este codigo");
               response.sendRedirect(request.getContextPath() + "/dependientes.do?op=listar");
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
    
     /***************** CONFIRMAR *************************************************/
    private void confirmar(HttpServletRequest request, HttpServletResponse response){
          try{
             modelo.confirmarCuenta(request.getParameter("id"));
             request.getSession().setAttribute("exito", " Cuenta verificada exitosamente, "
             + " ya puedes iniciar sesion");
             
             response.sendRedirect(request.getContextPath() + "/dependientes.do?op=login");
          }catch(SQLException | IOException ex){
            Logger.getLogger(AdministradoresController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    /*****************************************************************************/
    
    /****************** METODO VERIFICACION ************************************/
     private void verificarSesion(HttpServletRequest request, HttpServletResponse response){
         try{
            Dependiente miDependiente = new Dependiente();
            miDependiente.setCorreo(request.getParameter("usuario"));
            miDependiente.setClave(request.getParameter("password"));
            int estado = modelo.verificarSesion(miDependiente);
            
            switch(estado){
                case -1://si las credencias son correctas
                    request.getSession().setAttribute("fracaso", "Usuario y/o contraseña incorrecta");
                    break;
                case 0://si la cuenta no esta validada
                     request.getSession().setAttribute("fracaso", "Cuenta no verificada");
                    break;
                case 1://se las credenciales son correctas y las cuenta es validada
                      request.getRequestDispatcher("/index.jsp").forward(request, response);
                    break;
            }
            response.sendRedirect(request.getContextPath() + "/admins.do?op=login");
         
         }catch(SQLException | IOException ex){
           Logger.getLogger(AdministradoresController.class.getName()).log(Level.SEVERE, null, ex);
         } catch (ServletException ex) {
            Logger.getLogger(AdministradoresController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
     }
     /****************************************************************************/
   
    /**************************** METODO ELIMINAR *********************************/
    private void eliminar(HttpServletRequest request, HttpServletResponse response) {
        try {
            String codigo = request.getParameter("id");
            if(modelo.eliminarDependiente(codigo)>0){
                request.getSession().setAttribute("exito", "Dependiente eliminado correctamente");
            }
            else{
                request.getSession().setAttribute("fracaso", "No se puede eliminar este dependiente");
            }
            response.sendRedirect(request.getContextPath() + "/dependientes.do?op=listar");
            //request.getRequestDispatcher("/rubros.do?op=listar").forward(request, response);
        } catch (SQLException | IOException ex) {
            Logger.getLogger(RubrosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /***************************************************************************/
    
    /******************** METODO OBTENER ID ******************************************/
    private void obtener(HttpServletRequest request, HttpServletResponse response) {
        try {
            String codigo= request.getParameter("id");
            Dependiente miDependiente = modelo.obtenerDependiente(codigo);
             
          
            if (miDependiente != null) {
                request.setAttribute("listaEmpresas", modeloEmpresa.listarEmpresas());
                request.setAttribute("dependiente", miDependiente);
                request.getRequestDispatcher("/dependientes/editarDependiente.jsp").forward(request, response);
            }
            else{
                response.sendRedirect(request.getContextPath() + "/error404.jsp");
           
            }
        } catch (ServletException | SQLException | IOException ex ) {
            Logger.getLogger(RubrosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /***************************************************************************/
    
      /******************* METODO MODIFICAR ****************************************/
    private void modificar(HttpServletRequest request, HttpServletResponse response) {
       try{
           listaErrores.clear();
       Dependiente miDependiente = new Dependiente();//parametros del formulario
       miDependiente.setIdDependiente(request.getParameter("codigo"));
       miDependiente.setNombreDep(request.getParameter("nombreDep"));
       miDependiente.setApellido(request.getParameter("apellido"));
       miDependiente.setCorreo(request.getParameter("correo"));
       miDependiente.setCodigoEmpresa(request.getParameter("codigoEmpresa"));
       
       if(Validaciones.isEmpty(miDependiente.getIdDependiente())){
               listaErrores.add("El codigo del dependiente es obligatorio");
       }
       else if(!Validaciones.esCodigoDependiente(miDependiente.getIdDependiente())){
               listaErrores.add("El codigo del dependiente debe tener el formato DEP000");
       }
       if(Validaciones.isEmpty(miDependiente.getNombreDep())){
               listaErrores.add("El nombre del dependiente es obligatorio");
       }
       if(Validaciones.isEmpty(miDependiente.getApellido())){
               listaErrores.add("El apellido del dependiente es obligatorio");
       }
       if(Validaciones.isEmpty(miDependiente.getCorreo())){
               listaErrores.add("El correo del dependiente es obligatorio");
       }
       else if(!Validaciones.esCorreo(miDependiente.getCorreo())){
               listaErrores.add("El correo del dependiente no cumple el formato");
       }
       
       
       if(listaErrores.size()>0){// hay errores de validacion
             request.setAttribute("dependiente", miDependiente);
             request.setAttribute("listaErrores", listaErrores);
             request.getRequestDispatcher("/dependientes.do?op=nuevo").forward(request, response);
       }
       else{//no hay errores de validacion
           if(modelo.modificarDependiente(miDependiente)>0){//se inserto correctamente
              request.getSession().setAttribute("exito", "Dependiente modificado correctamente");
              response.sendRedirect(request.getContextPath() + "/dependientes.do?op=listar");
           }
           else{
              request.getSession().setAttribute("fracaso", "ERROR!! No se pudo modificar este dependiente. " 
                      + "Ya existe otro dependiente con este codigo");
               response.sendRedirect(request.getContextPath() + "/dependientes.do?op=listar");
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

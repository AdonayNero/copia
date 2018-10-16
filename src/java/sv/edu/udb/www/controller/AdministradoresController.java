
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
import sv.edu.udb.www.beans.Administrador;
import sv.edu.udb.www.model.AdministradoresModel;
import sv.edu.udb.www.utils.Correo;
import sv.edu.udb.www.utils.Validaciones;

@WebServlet(name = "AdministradoresController", urlPatterns = {"/admins.do"})
public class AdministradoresController extends HttpServlet {
    
    //creando una instancia del modelo de adminsitradores
    AdministradoresModel bd = new AdministradoresModel();
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
        switch (operacion) {
            case "login":
                request.getRequestDispatcher("/administradores/loginAdmin.jsp").forward(request, response);
                break;
            case "nuevo":
                request.getRequestDispatcher("/administradores/nuevoAdministrador.jsp").forward(request,
                        response);
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
            case "listar":
                listar(request, response);
                break;
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

    
    private void insertar(HttpServletRequest request, HttpServletResponse response) 
        throws IOException{
       
        try{
             listaErrores.clear();
            Administrador auxAdministrador = new Administrador();
            auxAdministrador.setIdAdmin(request.getParameter("codigo"));
            auxAdministrador.setNombre(request.getParameter("nombre"));
            auxAdministrador.setApellido(request.getParameter("apellido"));
            auxAdministrador.setUsuario(request.getParameter("usuario"));
            auxAdministrador.setCorreo(request.getParameter("email"));
            auxAdministrador.setClave(request.getParameter("pass"));
            
            if(Validaciones.isEmpty(auxAdministrador.getIdAdmin())){
                listaErrores.add("El codigo del administrador es obligatorio");
            }else if (!Validaciones.esCodigoAdmin(auxAdministrador.getIdAdmin())) {
                listaErrores.add("El codigo del administrador debe tener el formato AD0000");
            }
            if(Validaciones.isEmpty(auxAdministrador.getNombre())){
                listaErrores.add("El nombre del administrador es obligatorio");
            }
            if(Validaciones.isEmpty(auxAdministrador.getApellido())){
                listaErrores.add("El apellido del administrador es obligatorio");
            }
            if(Validaciones.isEmpty(auxAdministrador.getUsuario())){
                listaErrores.add("El usuario del administrador es obligatorio");
            }
            if(Validaciones.isEmpty(auxAdministrador.getCorreo())){
                listaErrores.add("El correo es obligatorio");
            }else if(!Validaciones.esCorreo(auxAdministrador.getCorreo())){
                listaErrores.add("El correo electronico no tiene el fortmato correcto");
            }
            if(Validaciones.isEmpty(auxAdministrador.getClave())){
                listaErrores.add("La contraseña es obligatoria");
            }else if(!Validaciones.esContraseña(auxAdministrador.getClave())){
               listaErrores.add("La contraseña debe tener una longitud minima de "
                              + "8 caracteres y debe contener al menos una mayuscula, "
                              + " una minuscula y un numero o caracter especial");
            }
           
            if(listaErrores.size() > 0){
                //hay errores
                request.setAttribute("listaErrores", listaErrores);
                request.setAttribute("administrador", auxAdministrador);
                request.getRequestDispatcher("/admins.do?op=nuevo").forward(request, response);
            }
            else{
                //la vlidacion paso
                //generadno la cadena aleatoria para la confirmacion de la cuenta
                String cadenaAleatoria = UUID.randomUUID().toString();
                //inserto los datos del usuarios
                if(bd.insertarAdministrador(auxAdministrador, cadenaAleatoria) > 0){
                   request.getSession().setAttribute("exito", "Administrador registrado"
                   + " exitosamente. Se le ha enviado un correo para que"
                   + " confirme su cuenta");
                  //preparo el texto del correo
                  String texto = "Bienvenido!!! eres un nuevo administrador de la cuponera.<br>";
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
                  correo.setDestinatario(auxAdministrador.getCorreo());
                  //obteniendo la url fisica de la carpeta assets/pdf
                  String directorio = getServletContext().getRealPath("assets/pdf");
                  correo.setRutaAdjunto(directorio + "\\proyecto.pdf");
                  correo.setNombreAdjunto("Especificaciones del proyecto de catedra.pdf");
                  //finalmente envio el correo
                  correo.enviarCorreo();
                  response.sendRedirect(request.getContextPath() + "/admins.do?op=listar");
                }else{//no se pudo insertar el usuario
                    listaErrores.add("Este nombre de usuario ya esta registrado");
                    request.setAttribute("listaErrores", listaErrores);
                    request.setAttribute("administrador", listaErrores);
                    request.getRequestDispatcher("/admins.do?op=listar").forward(request, response);
                }
            }
        
        }catch(ServletException | SQLException ex){
             Logger.getLogger(AdministradoresController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /***************** CONFIRMAR *************************************************/
    private void confirmar(HttpServletRequest request, HttpServletResponse response){
          try{
             bd.confirmarCuenta(request.getParameter("id"));
             request.getSession().setAttribute("exito", " Cuenta verificada exitosamente, "
             + " ya puedes iniciar sesion");
             
             response.sendRedirect(request.getContextPath() + "/admins.do?op=login");
          }catch(SQLException | IOException ex){
            Logger.getLogger(AdministradoresController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    /*****************************************************************************/
    
    /****************** METODO VERIFICACION ************************************/
     private void verificarSesion(HttpServletRequest request, HttpServletResponse response){
         try{
            Administrador miAdministrador = new Administrador();
            miAdministrador.setUsuario(request.getParameter("usuario"));
            miAdministrador.setClave(request.getParameter("password"));
            int estado = bd.verificarSesion(miAdministrador);
            
            switch(estado){
                case -1://si las credencias son correctas
                    request.getSession().setAttribute("fracaso", "Usuario y/o contraseña incorrecta");
                    response.sendRedirect(request.getContextPath() + "/admins.do?op=login");
                    break;
                case 0://si la cuenta no esta validada
                     request.getSession().setAttribute("fracaso", "Cuenta no verificada");
                     response.sendRedirect(request.getContextPath() + "/admins.do?op=login");
                    break;
                case 1://se las credenciales son correctas y las cuenta es validada
                   response.sendRedirect(request.getContextPath() + "/indexAdmin.jsp");
                    //request.getRequestDispatcher("/nuevoAdministrador.jsp").forward(request, response);
                    break;
            }
            
         
         }catch(SQLException | IOException ex){
           Logger.getLogger(AdministradoresController.class.getName()).log(Level.SEVERE, null, ex);
         } /*catch (ServletException ex) {
            Logger.getLogger(AdministradoresController.class.getName()).log(Level.SEVERE, null, ex); /*catch (ServletException ex) {
            Logger.getLogger(AdministradoresController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
     
     }
     /****************************************************************************/
     
     /********************** METODO LISTAR *************************************/
    private void listar(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("listaAdministradores", bd.listarAdministradores());
            //Redirecciones en el cliente
            // response.sendRedirect("ruta");
            //Redirecciones en el servidor
            request.getRequestDispatcher("/administradores/listaAdministradores.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(RubrosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(RubrosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RubrosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*************************************************************************/
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.beans;

/**
 *
 * @author Usuario
 */
public class Cliente {
    private String nombres;
    private String apellidos;
    private String dui;
    private String correo;
    private String contraseña;
    private String telefono;
    
    public Cliente(){
        this.nombres = "";
        this.apellidos = "";
        this.contraseña = "";
        this.correo = "";
        this.dui = "";
        this.telefono = "";
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getLogin() {
        String verificar = "";
        if(getCorreo() != "") {
            System.out.println("ENTRO PRIMERO!!!");
            System.out.println(getCorreo());
            //<jsp:forward page = "mantenimiento.jsp" > < / jsp:forward>
            //verificar = "<meta http-equiv=\"Refresh\" content=\"0.1;url=mantenimiento.jsp\"/>";
        }else if(getCorreo() == ""){
            System.out.println("ENTRO SEGUNDOOOOO!!!");
            verificar = "<meta http-equiv=\"Refresh\" content=\"0.1;url=clientes/login.jsp\"/>";
        }
        return verificar;
    }
}

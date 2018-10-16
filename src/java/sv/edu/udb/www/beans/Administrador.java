
package sv.edu.udb.www.beans;


public class Administrador {
    private String idAdmin;
    private String nombre;
    private String apellido;
    private String usuario;
    private String correo;
    private String clave;
    
    public Administrador(){
       this.idAdmin = "";
       this.nombre = "";
       this.apellido = "";
       this.usuario = "";
       this.correo = "";
       this.clave = "";
       
    }

    public String getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(String idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
}

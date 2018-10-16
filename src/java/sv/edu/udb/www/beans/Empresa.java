
package sv.edu.udb.www.beans;


public class Empresa {
    private String codigoEmpresa;
    private String nombre;
    private String direccion;
    private String contacto;
    private String telefono;
    private String correo;
    private double comision;
    private String clave;
    private String idRubro;
    private Rubro rubro;
    
    
    public Empresa(){
       this.codigoEmpresa = "";
       this.nombre = "";
       this.direccion = "";
       this.contacto = "";
       this.telefono = "";
       this.correo = "";
       this.comision = 0;
       this.rubro = null;
    }

    public Empresa(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(String codigoEmp) {
        this.codigoEmpresa = codigoEmp;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
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

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getIdRubro() {
        return idRubro;
    }

    public void setIdRubro(String idRubro) {
        this.idRubro = idRubro;
    }
    

    public Rubro getRubro() {
        return rubro;
    }

    public void setRubro(Rubro rubro) {
        this.rubro = rubro;
    }
    
}

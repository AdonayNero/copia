
package sv.edu.udb.www.beans;


public class Dependiente {
    private String idDependiente;
    private String nombreDep;
    private String apellido;
    private String correo;
    private String clave;
    private Empresa empresa;
    private String codigoEmpresa;
    
    public Dependiente(){
      this.idDependiente = "";
      this.nombreDep = "";
      this.apellido = "";
      this.correo = "";
      this.clave = "";
      this.empresa = null;
    }

    public String getIdDependiente() {
        return idDependiente;
    }

    public void setIdDependiente(String idDependiente) {
        this.idDependiente = idDependiente;
    }

    public String getNombreDep() {
        return nombreDep;
    }

    public void setNombreDep(String nombreDep) {
        this.nombreDep = nombreDep;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(String codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }
    
    
   
}

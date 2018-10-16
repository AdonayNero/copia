
package sv.edu.udb.www.beans;


public class Rubro {
    private String idRubro;
    private String nombreRubro;
    
    
    public Rubro(){
       this.idRubro = "";
       this.nombreRubro = "";
    }

    public Rubro(String nombreRubro) {
        this.nombreRubro = nombreRubro;
    }

    
    public String getIdRubro() {
        return idRubro;
    }

    public void setIdRubro(String idRubro) {
        this.idRubro = idRubro;
    }

    public String getNombreRubro() {
        return nombreRubro;
    }

    public void setNombreRubro(String nombre) {
        this.nombreRubro = nombre;
    }
    
}

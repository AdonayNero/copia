
package sv.edu.udb.www.beans;


public class Promocion {
    private String idPromocion;
    private String titulo;
    private double precioRegular;
    private double precioOferta;
    private String fechaInicio;
    private String fechaFin;
    private String fechaLimite;
    private String descripcion;
    private String justificacion;
    private String img;
    private Estado estado;
    private int idEstado;
    private String codigoEmpresa;
    private Empresa empresa;
    
    
    
    public Promocion(){
        this.idPromocion = "";
        this.titulo = "";
        this.precioRegular = 0;
        this.precioOferta = 0;
        this.fechaInicio = "";
        this.fechaFin = "";
        this.fechaLimite = "";
        this.descripcion = "";
        this.justificacion = "";
        this.img = "";
        this.estado = null;
        this.empresa = null;
    }

    
    
    public String getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(String idPromocion) {
        this.idPromocion = idPromocion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getPrecioRegular() {
        return precioRegular;
    }

    public void setPrecioRegular(double precioRegular) {
        this.precioRegular = precioRegular;
    }

    public double getPrecioOferta() {
        return precioOferta;
    }

    public void setPrecioOferta(double precioOferta) {
        this.precioOferta = precioOferta;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
       public String getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(String codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }
    
    
    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    
}

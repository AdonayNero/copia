package sv.edu.udb.www.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.www.beans.Promocion;
import sv.edu.udb.www.beans.Empresa;
import sv.edu.udb.www.beans.Estado;
import static sv.edu.udb.www.model.Conexion.conexion;

public class IndexModel extends Conexion {
    
    public List<Promocion> listarPromociones() throws SQLException{
        try {
            List<Promocion> lista = new ArrayList<>();
            String sql = "call sp_listarPromociones()";
            this.conectar();
            cs= conexion.prepareCall(sql);
            rs = cs.executeQuery();
            while(rs.next()){
                Promocion promocion = new Promocion();
                promocion.setIdPromocion(rs.getString("idPromocion"));
                promocion.setTitulo(rs.getString("titulo"));
                promocion.setPrecioRegular(rs.getDouble("PrecioRegular"));
                promocion.setPrecioOferta(rs.getDouble("precioOferta"));
                promocion.setFechaInicio(rs.getString("fechaInicio"));
                promocion.setFechaFin(rs.getString("fechaFin"));
                promocion.setFechaLimite(rs.getString("fechaLimite"));
                promocion.setDescripcion(rs.getString("descripcion").trim());
                promocion.setJustificacion(rs.getString("justificacion"));
                promocion.setImg(rs.getString("img"));
                promocion.setEstado(new Estado(rs.getString("estado")));
                promocion.setEmpresa(new Empresa(rs.getString("nombre")));
                lista.add(promocion);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(IndexModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }
    public List<Promocion> listarPromocionesAccesorios() throws SQLException{
        try {
            List<Promocion> lista = new ArrayList<>();
            String sql = "call sp_listarPromociones()";
            this.conectar();
            cs= conexion.prepareCall(sql);
            rs = cs.executeQuery();
            while(rs.next()){
                Promocion promocion = new Promocion();
                promocion.setIdPromocion(rs.getString("idPromocion"));
                promocion.setTitulo(rs.getString("titulo"));
                promocion.setPrecioRegular(rs.getDouble("PrecioRegular"));
                promocion.setPrecioOferta(rs.getDouble("precioOferta"));
                promocion.setFechaInicio(rs.getString("fechaInicio"));
                promocion.setFechaFin(rs.getString("fechaFin"));
                promocion.setFechaLimite(rs.getString("fechaLimite"));
                promocion.setDescripcion(rs.getString("descripcion").trim());
                promocion.setJustificacion(rs.getString("justificacion"));
                promocion.setImg(rs.getString("img"));
                promocion.setEstado(new Estado(rs.getString("estado")));
                promocion.setEmpresa(new Empresa(rs.getString("nombre")));
                lista.add(promocion);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(IndexModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }
    public List<Promocion> listarPromocionesModa() throws SQLException{
        try {
            List<Promocion> lista = new ArrayList<>();
            String sql = "call sp_listarPromociones()";
            this.conectar();
            cs= conexion.prepareCall(sql);
            rs = cs.executeQuery();
            while(rs.next()){
                Promocion promocion = new Promocion();
                promocion.setIdPromocion(rs.getString("idPromocion"));
                promocion.setTitulo(rs.getString("titulo"));
                promocion.setPrecioRegular(rs.getDouble("PrecioRegular"));
                promocion.setPrecioOferta(rs.getDouble("precioOferta"));
                promocion.setFechaInicio(rs.getString("fechaInicio"));
                promocion.setFechaFin(rs.getString("fechaFin"));
                promocion.setFechaLimite(rs.getString("fechaLimite"));
                promocion.setDescripcion(rs.getString("descripcion").trim());
                promocion.setJustificacion(rs.getString("justificacion"));
                promocion.setImg(rs.getString("img"));
                promocion.setEstado(new Estado(rs.getString("estado")));
                promocion.setEmpresa(new Empresa(rs.getString("nombre")));
                lista.add(promocion);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(IndexModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }
    public List<Promocion> listarPromocionesSalud() throws SQLException{
        try {
            List<Promocion> lista = new ArrayList<>();
            String sql = "call sp_listarPromociones()";
            this.conectar();
            cs= conexion.prepareCall(sql);
            rs = cs.executeQuery();
            while(rs.next()){
                Promocion promocion = new Promocion();
                promocion.setIdPromocion(rs.getString("idPromocion"));
                promocion.setTitulo(rs.getString("titulo"));
                promocion.setPrecioRegular(rs.getDouble("PrecioRegular"));
                promocion.setPrecioOferta(rs.getDouble("precioOferta"));
                promocion.setFechaInicio(rs.getString("fechaInicio"));
                promocion.setFechaFin(rs.getString("fechaFin"));
                promocion.setFechaLimite(rs.getString("fechaLimite"));
                promocion.setDescripcion(rs.getString("descripcion").trim());
                promocion.setJustificacion(rs.getString("justificacion"));
                promocion.setImg(rs.getString("img"));
                promocion.setEstado(new Estado(rs.getString("estado")));
                promocion.setEmpresa(new Empresa(rs.getString("nombre")));
                lista.add(promocion);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(IndexModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }
    public List<Promocion> listarPromocionesRestaurante() throws SQLException{
        try {
            List<Promocion> lista = new ArrayList<>();
            String sql = "call sp_listarPromociones()";
            this.conectar();
            cs= conexion.prepareCall(sql);
            rs = cs.executeQuery();
            while(rs.next()){
                Promocion promocion = new Promocion();
                promocion.setIdPromocion(rs.getString("idPromocion"));
                promocion.setTitulo(rs.getString("titulo"));
                promocion.setPrecioRegular(rs.getDouble("PrecioRegular"));
                promocion.setPrecioOferta(rs.getDouble("precioOferta"));
                promocion.setFechaInicio(rs.getString("fechaInicio"));
                promocion.setFechaFin(rs.getString("fechaFin"));
                promocion.setFechaLimite(rs.getString("fechaLimite"));
                promocion.setDescripcion(rs.getString("descripcion").trim());
                promocion.setJustificacion(rs.getString("justificacion"));
                promocion.setImg(rs.getString("img"));
                promocion.setEstado(new Estado(rs.getString("estado")));
                promocion.setEmpresa(new Empresa(rs.getString("nombre")));
                lista.add(promocion);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(IndexModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }
    public List<Promocion> listarPromocionesDiversion() throws SQLException{
        try {
            List<Promocion> lista = new ArrayList<>();
            String sql = "call sp_listarPromociones()";
            this.conectar();
            cs= conexion.prepareCall(sql);
            rs = cs.executeQuery();
            while(rs.next()){
                Promocion promocion = new Promocion();
                promocion.setIdPromocion(rs.getString("idPromocion"));
                promocion.setTitulo(rs.getString("titulo"));
                promocion.setPrecioRegular(rs.getDouble("PrecioRegular"));
                promocion.setPrecioOferta(rs.getDouble("precioOferta"));
                promocion.setFechaInicio(rs.getString("fechaInicio"));
                promocion.setFechaFin(rs.getString("fechaFin"));
                promocion.setFechaLimite(rs.getString("fechaLimite"));
                promocion.setDescripcion(rs.getString("descripcion").trim());
                promocion.setJustificacion(rs.getString("justificacion"));
                promocion.setImg(rs.getString("img"));
                promocion.setEstado(new Estado(rs.getString("estado")));
                promocion.setEmpresa(new Empresa(rs.getString("nombre")));
                lista.add(promocion);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(IndexModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }
    public List<Promocion> listarPromocionesAutomotriz() throws SQLException{
        try {
            List<Promocion> lista = new ArrayList<>();
            String sql = "call sp_listarPromociones()";
            this.conectar();
            cs= conexion.prepareCall(sql);
            rs = cs.executeQuery();
            while(rs.next()){
                Promocion promocion = new Promocion();
                promocion.setIdPromocion(rs.getString("idPromocion"));
                promocion.setTitulo(rs.getString("titulo"));
                promocion.setPrecioRegular(rs.getDouble("PrecioRegular"));
                promocion.setPrecioOferta(rs.getDouble("precioOferta"));
                promocion.setFechaInicio(rs.getString("fechaInicio"));
                promocion.setFechaFin(rs.getString("fechaFin"));
                promocion.setFechaLimite(rs.getString("fechaLimite"));
                promocion.setDescripcion(rs.getString("descripcion").trim());
                promocion.setJustificacion(rs.getString("justificacion"));
                promocion.setImg(rs.getString("img"));
                promocion.setEstado(new Estado(rs.getString("estado")));
                promocion.setEmpresa(new Empresa(rs.getString("nombre")));
                lista.add(promocion);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(IndexModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }
    public List<Promocion> listarPromocionesTecnologia() throws SQLException{
        try {
            List<Promocion> lista = new ArrayList<>();
            String sql = "call sp_listarPromociones()";
            this.conectar();
            cs= conexion.prepareCall(sql);
            rs = cs.executeQuery();
            while(rs.next()){
                Promocion promocion = new Promocion();
                promocion.setIdPromocion(rs.getString("idPromocion"));
                promocion.setTitulo(rs.getString("titulo"));
                promocion.setPrecioRegular(rs.getDouble("PrecioRegular"));
                promocion.setPrecioOferta(rs.getDouble("precioOferta"));
                promocion.setFechaInicio(rs.getString("fechaInicio"));
                promocion.setFechaFin(rs.getString("fechaFin"));
                promocion.setFechaLimite(rs.getString("fechaLimite"));
                promocion.setDescripcion(rs.getString("descripcion").trim());
                promocion.setJustificacion(rs.getString("justificacion"));
                promocion.setImg(rs.getString("img"));
                promocion.setEstado(new Estado(rs.getString("estado")));
                promocion.setEmpresa(new Empresa(rs.getString("nombre")));
                lista.add(promocion);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(IndexModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }
    
}


package sv.edu.udb.www.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.www.beans.Empresa;
import sv.edu.udb.www.beans.Estado;
import sv.edu.udb.www.beans.Promocion;



public class EsperaModel extends Conexion{
    
     public List<Promocion> listarEspera() throws SQLException{
        try {
            List<Promocion> lista = new ArrayList<>();
            String sql = "call sp_listarEspera()";
            this.conectar();
            cs= conexion.prepareCall(sql);
            rs = cs.executeQuery();
            while(rs.next()){
                Promocion promocion = new Promocion();
                promocion.setIdPromocion(rs.getString("idPromocion"));
                promocion.setTitulo(rs.getString("titulo"));
                promocion.setPrecioRegular(rs.getDouble("PrecioRegular"));
                promocion.setFechaInicio(rs.getString("fechaInicio"));
                promocion.setEmpresa(new Empresa(rs.getString("nombre")));
                promocion.setEstado(new Estado(rs.getString("estado")));
                lista.add(promocion);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(EsperaModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }
    
     public int aprobarPromocion(String codigo) throws SQLException{
        try {
            int filasAfectadas=0;
            String sql = "UPDATE promocion SET idEstado=2 WHERE idPromocion =  ?";
            this.conectar();
            st= conexion.prepareStatement(sql);
            st.setString(1, codigo);
            filasAfectadas= st.executeUpdate();
            this.desconectar();
            return filasAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
    }
     
     public int rechazarPromocion(String codigo) throws SQLException{
        try {
            int filasAfectadas=0;
            String sql = "UPDATE promocion SET idEstado=3 WHERE idPromocion =  ?";
            this.conectar();
            st= conexion.prepareStatement(sql);
            st.setString(1, codigo);
            filasAfectadas= st.executeUpdate();
            this.desconectar();
            return filasAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
    } 
     
     
    public List<Promocion> listarAprobada() throws SQLException{
        try {
            List<Promocion> lista = new ArrayList<>();
            String sql = "call sp_listarAprobada()";
            this.conectar();
            cs= conexion.prepareCall(sql);
            rs = cs.executeQuery();
            while(rs.next()){
                Promocion promocion = new Promocion();
                promocion.setIdPromocion(rs.getString("idPromocion"));
                promocion.setTitulo(rs.getString("titulo"));
                promocion.setPrecioRegular(rs.getDouble("PrecioRegular"));
                promocion.setFechaInicio(rs.getString("fechaInicio"));
                promocion.setEmpresa(new Empresa(rs.getString("nombre")));
                promocion.setEstado(new Estado(rs.getString("estado")));
                lista.add(promocion);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(EsperaModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }
    
    public List<Promocion> listarRechazada() throws SQLException{
        try {
            List<Promocion> lista = new ArrayList<>();
            String sql = "call sp_listarRechazada()";
            this.conectar();
            cs= conexion.prepareCall(sql);
            rs = cs.executeQuery();
            while(rs.next()){
                Promocion promocion = new Promocion();
                promocion.setIdPromocion(rs.getString("idPromocion"));
                promocion.setTitulo(rs.getString("titulo"));
                promocion.setPrecioRegular(rs.getDouble("PrecioRegular"));
                promocion.setFechaInicio(rs.getString("fechaInicio"));
                promocion.setEmpresa(new Empresa(rs.getString("nombre")));
                promocion.setEstado(new Estado(rs.getString("estado")));
                lista.add(promocion);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(EsperaModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }
    
}

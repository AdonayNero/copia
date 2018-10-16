
package sv.edu.udb.www.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.www.beans.Promocion;
import sv.edu.udb.www.beans.Empresa;
import sv.edu.udb.www.beans.Estado;

public class PromocionesModel extends Conexion {
    
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
            Logger.getLogger(PromocionesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }
    
    public int insertarPromocion(Promocion promocion) throws SQLException{
        try {
            int filaAfectadas=0;
            String sql="INSERT INTO promocion VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, promocion.getIdPromocion());
            st.setString(2, promocion.getTitulo());
            st.setDouble(3, promocion.getPrecioRegular());
            st.setDouble(4, promocion.getPrecioOferta());
            st.setString(5, promocion.getFechaInicio());
            st.setString(6, promocion.getFechaFin());
            st.setString(7, promocion.getFechaLimite());
            st.setString(8, promocion.getDescripcion());
            st.setString(9, null);
            st.setString(10, promocion.getImg());
            st.setBoolean(11, true);
            st.setString(12, promocion.getCodigoEmpresa());
            
            filaAfectadas=st.executeUpdate();
            this.desconectar();
            return filaAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(PromocionesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }

     }
    
     public int eliminarPromocion(String codigo) throws SQLException{
        try {
            int filasAfectadas=0;
            String sql = "DELETE FROM promocion WHERE idPromocion=?";
            this.conectar();
            st= conexion.prepareStatement(sql);
            st.setString(1, codigo);
            filasAfectadas= st.executeUpdate();
            this.desconectar();
            return filasAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(PromocionesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
    }
     
    public Promocion obtenerPromocion(String codigo) throws SQLException{
        try {
            String sql = "SELECT * FROM promocion WHERE idPromocion=?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, codigo);
            rs = st.executeQuery();
            if(rs.next()){
                Promocion promocion = new Promocion();
                promocion.setIdPromocion(rs.getString("idPromocion"));
                promocion.setTitulo(rs.getString("titulo"));
                promocion.setPrecioRegular(rs.getDouble("precioRegular"));
                promocion.setPrecioOferta(rs.getDouble("precioOferta"));
                promocion.setFechaInicio(rs.getString("fechaInicio"));
                promocion.setFechaFin(rs.getString("fechaFin"));
                promocion.setFechaLimite(rs.getString("fechaLimite"));
                promocion.setDescripcion(rs.getString("descripcion"));
                promocion.setImg(rs.getString("img"));
                 promocion.setIdEstado(rs.getInt("idEstado"));
                this.desconectar();
                return promocion;
            }
            this.desconectar();
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(PromocionesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }      
    }
    
    public int modificarPromocion(Promocion promocion) throws SQLException{
         try {
            int filasAfectadas=0;
            String sql="UPDATE promocion SET titulo=?, precioRegular=?, precioOferta=?, fechaInicio=?, fechaFin=?, fechaLimite=?, descripcion=?, justificacion=?, img=?, idEstado=?, codigoEmpresa=? WHERE idPromocion=?";
            this.conectar();
            st= conexion.prepareStatement(sql);
            st.setString(1, promocion.getTitulo());
            st.setDouble(2, promocion.getPrecioRegular());
            st.setDouble(3, promocion.getPrecioOferta());
            st.setString(4, promocion.getFechaInicio());
            st.setString(5, promocion.getFechaFin());
            st.setString(6, promocion.getFechaLimite());
            st.setString(7, promocion.getDescripcion());
            st.setString(8, null);
            st.setString(9, promocion.getImg());
            st.setInt(10, promocion.getIdEstado());
            st.setString(11, promocion.getCodigoEmpresa());
            st.setString(12, promocion.getIdPromocion());
            filasAfectadas= st.executeUpdate();
            this.desconectar();
            return filasAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(PromocionesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
    } 
}


package sv.edu.udb.www.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.www.beans.Rubro;

public class RubrosModel extends Conexion {
    
    public List<Rubro> listarRubros() throws SQLException{
        try {
            List<Rubro> lista = new ArrayList<>();
            String sql= "SELECT * FROM rubro ORDER BY nombreRubro";
            this.conectar();
            st= conexion.prepareStatement(sql);
            rs= st.executeQuery();
            while(rs.next()){
                Rubro rubro = new Rubro();
                rubro.setIdRubro(rs.getString("idRubro"));
                rubro.setNombreRubro(rs.getString("nombreRubro"));
                lista.add(rubro);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(RubrosModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }
    
    public int insertarRubro(Rubro rubro) throws SQLException{
        try {
            int filasAfectadas=0;
            String sql="INSERT INTO rubro VALUES(?,?)";
            this.conectar();
            st= conexion.prepareStatement(sql);
            st.setString(1, rubro.getIdRubro());
            st.setString(2, rubro.getNombreRubro());
            filasAfectadas= st.executeUpdate();
            this.desconectar();
            return filasAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(RubrosModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
    }
    
    public Rubro obtenerRubro(String codigo) throws SQLException{
        try {
            String sql = "SELECT * FROM rubro WHERE idRubro=?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, codigo);
            rs = st.executeQuery();
            if(rs.next()){
                Rubro rubro = new Rubro();
                rubro.setIdRubro(rs.getString("idRubro"));
                rubro.setNombreRubro(rs.getString("nombreRubro"));
                this.desconectar();
                return rubro;
            }
            this.desconectar();
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(RubrosModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }      
    }
    
    public int modificarRubro(Rubro rubro) throws SQLException{
         try {
            int filasAfectadas=0;
            String sql="UPDATE rubro SET nombreRubro=? WHERE idRubro=?";
            this.conectar();
            st= conexion.prepareStatement(sql);
            st.setString(1, rubro.getNombreRubro());
            st.setString(2, rubro.getIdRubro());
            filasAfectadas= st.executeUpdate();
            this.desconectar();
            return filasAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(RubrosModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
    }
   
    public int eliminarRubro(String codigo) throws SQLException{
        try {
            int filasAfectadas=0;
            String sql = "DELETE FROM rubro WHERE idRubro=?";
            this.conectar();
            st= conexion.prepareStatement(sql);
            st.setString(1, codigo);
            filasAfectadas= st.executeUpdate();
            this.desconectar();
            return filasAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(RubrosModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
    } 
}

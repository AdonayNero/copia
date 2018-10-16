
package sv.edu.udb.www.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.www.beans.Dependiente;
import sv.edu.udb.www.beans.Empresa;

public class DependientesModel extends Conexion{
    
      public List<Dependiente> listarDependientes() throws SQLException{
        try {
            List<Dependiente> lista = new ArrayList<>();
            String sql = "call sp_listarDependientes()";
            this.conectar();
            cs= conexion.prepareCall(sql);
            rs = cs.executeQuery();
            while(rs.next()){
                Dependiente dependiente = new Dependiente();
                dependiente.setIdDependiente(rs.getString("idDependiente"));
                dependiente.setNombreDep(rs.getString("nombreDep"));
                dependiente.setApellido(rs.getString("apellido"));
                dependiente.setCorreo(rs.getString("correo"));
                dependiente.setEmpresa(new Empresa(rs.getString("nombre")));
                lista.add(dependiente);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DependientesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }
      
      public int insertarDependiente(Dependiente dependiente, String idConfirmacion) throws SQLException{
        try {
            int filaAfectadas=0;
            String sql="INSERT INTO dependiente VALUES (?,?,?,?,SHA2(?,256),?,?,?)";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, dependiente.getIdDependiente());
            st.setString(2, dependiente.getNombreDep());
            st.setString(3, dependiente.getApellido());
            st.setString(4, dependiente.getCorreo());
            st.setString(5, dependiente.getClave());
            st.setString(6, dependiente.getCodigoEmpresa());
             st.setBoolean(7, false);
            st.setString(8, idConfirmacion);

            filaAfectadas=st.executeUpdate();
            this.desconectar();
            return filaAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(DependientesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
     }
     
       public void confirmarCuenta(String id) throws SQLException {
        try {
            sql = "UPDATE dependiente SET confirmado=true WHERE idConfirmacion =  ? ";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasModel.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        this.desconectar();
    }
    //Devuelve -1 si las credenciales son incorrectas
    // 0 si la cuenta no está validada
    // 1 si las credenciales son correctas y la cuenta esta validada

    public int verificarSesion(Dependiente miCorreo) throws SQLException {
        try {
            sql = "SELECT confirmado FROM dependiente WHERE correo=? AND clave = SHA2( ?, 256)";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, miCorreo.getCorreo());
            st.setString(2, miCorreo.getClave());
            rs = st.executeQuery();
            if (rs.next()) {
                if (rs.getBoolean("confirmado")) {
                    this.desconectar();
                    return 1;
                } else {
                    this.desconectar();
                    return 0;
                }
            } else {
                this.desconectar();
                return -1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasModel.class.getName()).log(Level.SEVERE,
                    null, ex);
            this.desconectar();
            return -1;
        }
    }
    
      
      public int eliminarDependiente(String codigo) throws SQLException{
        try {
            int filasAfectadas=0;
            String sql = "DELETE FROM dependiente WHERE idDependiente=?";
            this.conectar();
            st= conexion.prepareStatement(sql);
            st.setString(1, codigo);
            filasAfectadas= st.executeUpdate();
            this.desconectar();
            return filasAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(DependientesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
    } 
      
    public Dependiente obtenerDependiente(String codigo) throws SQLException{
        try {
            String sql = "SELECT * FROM dependiente WHERE idDependiente=?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, codigo);
            rs = st.executeQuery();
            if(rs.next()){
                Dependiente dependiente = new Dependiente();
                dependiente.setIdDependiente(rs.getString("idDependiente"));
                dependiente.setNombreDep(rs.getString("nombreDep"));
                dependiente.setApellido(rs.getString("apellido"));
                dependiente.setCorreo(rs.getString("correo"));
                this.desconectar();
                return dependiente;
            }
            this.desconectar();
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(DependientesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }      
    }
    
    public int modificarDependiente(Dependiente dependiente) throws SQLException{
         try {
            int filasAfectadas=0;
            String sql="UPDATE dependiente SET nombreDep=?, apellido=?, correo=?, codigoEmpresa=? WHERE idDependiente=?";
            this.conectar();
            st= conexion.prepareStatement(sql);
            st.setString(1, dependiente.getNombreDep());
            st.setString(2, dependiente.getApellido());
            st.setString(3, dependiente.getCorreo());
            st.setString(4, dependiente.getCodigoEmpresa());
            st.setString(5, dependiente.getIdDependiente());
            filasAfectadas= st.executeUpdate();
            this.desconectar();
            return filasAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(DependientesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
    } 
     
}

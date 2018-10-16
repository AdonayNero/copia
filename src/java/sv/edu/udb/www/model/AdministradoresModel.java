
package sv.edu.udb.www.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.www.beans.Administrador;

public class AdministradoresModel extends Conexion {
    
    public int insertarAdministrador(Administrador usu, String idConfirmacion) throws
            SQLException {
        try {
            int filasAfectadas = 0;
            sql = "INSERT INTO administrador VALUES(?,?,?,?,?,SHA2(?,256),?,?)";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, usu.getIdAdmin());
            st.setString(2, usu.getNombre());
            st.setString(3, usu.getApellido());
            st.setString(4, usu.getUsuario());
            st.setString(5, usu.getCorreo());
            st.setString(6, usu.getClave());
            st.setBoolean(7, false);
            st.setString(8, idConfirmacion);
            filasAfectadas = st.executeUpdate();
            this.desconectar();
            return filasAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(AdministradoresModel.class.getName()).log(Level.SEVERE,
                    null, ex);
            this.desconectar();
            return 0;
        }
    }

    public void confirmarCuenta(String id) throws SQLException {
        try {
            sql = "UPDATE administrador SET confirmado=true WHERE idConfirmacion =  ? ";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdministradoresModel.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        this.desconectar();
    }
    //Devuelve -1 si las credenciales son incorrectas
    // 0 si la cuenta no está validada
    // 1 si las credenciales son correctas y la cuenta esta validada

    public int verificarSesion(Administrador miUsuario) throws SQLException {
        try {
            sql = "SELECT confirmado FROM administrador WHERE usuario=? AND clave = SHA2( ?, 256)";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, miUsuario.getUsuario());
            st.setString(2, miUsuario.getClave());
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
            Logger.getLogger(AdministradoresModel.class.getName()).log(Level.SEVERE,
                    null, ex);
            this.desconectar();
            return -1;
        }
    }
    
    public List<Administrador> listarAdministradores() throws SQLException{
        try {
            List<Administrador> lista = new ArrayList<>();
            String sql = "call sp_listarAdministradores()";
            this.conectar();
            cs = conexion.prepareCall(sql);
            rs = cs.executeQuery();
            while(rs.next()){
                Administrador admin = new Administrador();
                admin.setIdAdmin(rs.getString("idAdmin"));
                admin.setNombre(rs.getString("nombre"));
                admin.setApellido(rs.getString("apellido"));
                admin.setUsuario(rs.getString("usuario"));
                admin.setCorreo(rs.getString("correo"));
                lista.add(admin);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(AdministradoresModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }
}

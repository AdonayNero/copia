/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.model;

/**
 *
 * @author Usuario
 */
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.Logger;
import sv.edu.udb.www.beans.Cliente;

public class ClientesModel extends Conexion {

    public int insertarCliente(Cliente clie, String idConfirmacion) throws SQLException {
        try {
            int filasAfectadas = 0;
            sql = "INSERT INTO cliente VALUES(?,?,?,?,?,SHA2(?,256),?,?,?)";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, clie.getDui());
            st.setString(2, clie.getNombres());
            st.setString(3, clie.getApellidos());
            st.setString(4, clie.getTelefono());
            st.setString(5, clie.getCorreo());
            st.setString(6, clie.getContraseña());
            st.setString(7, "imgestatica.jpg");
            st.setInt(8, 0);
            st.setString(9, idConfirmacion);
            filasAfectadas = st.executeUpdate();
            this.desconectar();
            return filasAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
    }

    public void confirmarCuenta(String id) throws SQLException {
        try {
            sql = "UPDATE cliente SET confirmado=true  WHERE idConfirmacion=?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClientesModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.desconectar();
    }

    //Devuelve -1 si las credenciales son incorrectas
    // 0 si la cuenta no está validada
    // 1 si las credenciales son correctas y la cuenta esta validada
    public int verificarSesion(Cliente miCliente) throws SQLException {
        try {
            sql = "SELECT confirmado FROM cliente WHERE correo=? AND clave=SHA2(?,256)";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, miCliente.getCorreo());
            st.setString(2, miCliente.getContraseña());
            rs = st.executeQuery();
            if(rs.next()){
                if(rs.getBoolean("confirmado")){
                    this.desconectar();
                    return 1;
                }else{
                    this.desconectar();
                    return 0;
                }                
            }else{
                this.desconectar();
                return -1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return -1;
        }
    }
}

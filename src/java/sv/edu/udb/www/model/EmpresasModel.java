
package sv.edu.udb.www.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.www.beans.Empresa;
import sv.edu.udb.www.beans.Rubro;

public class EmpresasModel extends Conexion {
    
    public List<Empresa> listarEmpresas() throws SQLException{
        try {
            List<Empresa> lista = new ArrayList<>();
            String sql = "call sp_listarEmpresas()";
            this.conectar();
            cs= conexion.prepareCall(sql);
            rs = cs.executeQuery();
            while(rs.next()){
                Empresa empresa = new Empresa();
                empresa.setCodigoEmpresa(rs.getString("codigoEmpresa"));
                empresa.setNombre(rs.getString("nombre"));
                empresa.setDireccion(rs.getString("direccion"));
                empresa.setContacto(rs.getString("contactoNom"));
                empresa.setTelefono(rs.getString("telefono"));
                empresa.setCorreo(rs.getString("correo"));
                empresa.setComision(rs.getDouble("comision"));
                empresa.setRubro(new Rubro(rs.getString("nombreRubro")));
                lista.add(empresa);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
        
    }
    
    public int insertarEmpresa(Empresa empresa, String idConfirmacion) throws SQLException{
        try {
            int filaAfectadas=0;
            String sql="INSERT INTO empresa VALUES (?,?,?,?,?,?,?,SHA2(?,256),?,?,?)";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, empresa.getCodigoEmpresa());
            st.setString(2, empresa.getNombre());
            st.setString(3, empresa.getDireccion());
            st.setString(4, empresa.getContacto());
            st.setString(5, empresa.getTelefono());
            st.setString(6, empresa.getCorreo());
            st.setDouble(7, empresa.getComision());
            st.setString(8, empresa.getClave());
            st.setString(9, empresa.getIdRubro());
            st.setBoolean(10, false);
            st.setString(11, idConfirmacion);
            filaAfectadas=st.executeUpdate();
            this.desconectar();
            return filaAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }

     }
    
    public void confirmarCuenta(String id) throws SQLException {
        try {
            sql = "UPDATE empresa SET confirmado=true WHERE idConfirmacion =  ? ";
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

    public int verificarSesion(Empresa miCorreo) throws SQLException {
        try {
            sql = "SELECT confirmado FROM empresa WHERE correo=? AND clave = SHA2( ?, 256)";
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
    
    
    public int eliminarEmpresa(String codigo) throws SQLException{
        try {
            int filasAfectadas=0;
            String sql = "DELETE FROM empresa WHERE codigoEmpresa=?";
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
    
     public Empresa obtenerEmpresa(String codigo) throws SQLException{
        try {
            String sql = "SELECT * FROM empresa WHERE codigoEmpresa=?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, codigo);
            rs = st.executeQuery();
            if(rs.next()){
                Empresa empresa = new Empresa();
                empresa.setCodigoEmpresa(rs.getString("codigoEmpresa"));
                empresa.setNombre(rs.getString("nombre"));
                empresa.setDireccion(rs.getString("direccion"));
                empresa.setContacto(rs.getString("contactoNom"));
                empresa.setTelefono(rs.getString("telefono"));
                empresa.setCorreo(rs.getString("correo"));
                empresa.setComision(rs.getDouble("comision"));
                this.desconectar();
                return empresa;
            }
            this.desconectar();
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }      
    }
     
    public int modificarEmpresa(Empresa empresa) throws SQLException{
         try {
            int filasAfectadas=0;
            String sql="UPDATE empresa SET nombre=?, direccion=?, contactoNom=?, telefono=?, correo=?, comision=?, idRubro=? WHERE codigoEmpresa=?";
            this.conectar();
            st= conexion.prepareStatement(sql);
            st.setString(1, empresa.getNombre());
            st.setString(2, empresa.getDireccion());
            st.setString(3, empresa.getContacto());
            st.setString(4, empresa.getTelefono());
            st.setString(5, empresa.getCorreo());
            st.setDouble(6, empresa.getComision());
            st.setString(7, empresa.getIdRubro());
            st.setString(8, empresa.getCodigoEmpresa());
            filasAfectadas= st.executeUpdate();
            this.desconectar();
            return filasAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
    } 
}

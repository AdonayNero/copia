/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.utils;

import sv.edu.udb.www.model.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConexionDB {

    public static Connection getConexion(){
        Connection cn = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost/cuponera", "root", "");
            System.out.println("Conexion Satisfactoria");
        }catch(SQLException ex){
            System.out.println("Error de conexion" + ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cn;
    }
    
    public static void main(String[] args){
        ConexionDB.getConexion();
    }
    
}

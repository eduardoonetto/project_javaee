/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conf;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eduardo.Onetto
 */
public final class Conexion {
    public static final String USERNAME = "root";
    public static final String PASSWORD = "Pelusa12";
    public static final String HOST = "localhost";
    public static final String PORT = "3306";
    public static final String DATABASE = "PRU";
    public static final String CLASSNAME = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE+"?zeroDateTimeBehavior=CONVERT_TO_NULL";
    public java.sql.Connection con;
    
    //Constructor:  
    public Conexion() {
        //Aqui podria definir si trabajaramos con otras BD
        this.con = (this.conectar() == null) ? null : this.conectar();
        System.out.println("Status=" + con);
    }
    
    public Connection conectar(){
        try {
            Class.forName(CLASSNAME);
            con= DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Conexion exitosa");
        } catch (ClassNotFoundException e) {
            System.out.println("Error driver no encontrado= " + e);
        }catch(SQLException e){
            System.out.println("Error en SQL= "+e);
        }
        
        return con;
    }

       public void desconectar() {
        try {

            con.close();
            System.err.println("Desconexion exitosa");

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
             System.err.println("Desconexion fallida");
        }
    }
       
    public static void main(String[] args) {
        Conexion cn = new Conexion();
        try {
           cn.conectar(); 
           cn.desconectar();
        } catch (Exception e) {
            System.out.println("ERROR");
        }  
        
    }
   
}

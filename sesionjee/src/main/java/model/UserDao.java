package model;

import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import conf.Conexion;

public class UserDao
{
    Conexion cn;
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public UserDao() {
        this.cn = new Conexion();
    }
    
    public boolean insert(final String nombre, final String apellido, final String email, final String password) {
        final String insert = "INSERT INTO `usuarios` (`nombre`,`apellido`,`email`, `pass`) VALUES(?,?,?,?)";
        try {
            this.con = this.cn.conectar();
            this.ps = this.con.prepareStatement(insert);
            this.ps.setString(1, nombre);
            this.ps.setString(2, apellido);
            this.ps.setString(3, email);
            this.ps.setString(4, password);
            this.ps.executeUpdate();
            this.cn.desconectar();
            return true;
        }
        catch (SQLException e) {
            System.err.println("error " + e);
            return false;
        }
        finally {
            if (this.cn == null) {
                this.cn.desconectar();
            }
        }
    }
    
    public User validar(final String email, final String password) {
        final String validar = "SELECT * FROM usuarios where email=? and pass=?";
        final User u = new User();
        try {
            this.con = this.cn.conectar();
            this.ps = this.con.prepareStatement(validar);
            this.ps.setString(1, email);
            this.ps.setString(2, password);
            this.rs = this.ps.executeQuery();
            while (this.rs.next()) {
                u.setId(this.rs.getInt("id"));
                u.setNombre(this.rs.getString("nombre"));
                u.setApellido(this.rs.getString("apellido"));
                u.setEmail(this.rs.getString("email"));
                u.setPassword(this.rs.getString("pass"));
            }
            this.cn.desconectar();
            return u;
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
        finally {
            if (this.cn == null) {
                this.cn.desconectar();
            }
        }
    }
    
    public List<User> list() {
        final List<User> listUsers = new ArrayList<User>();
        final String list = "SELECT * FROM usuarios";
        try {
            this.con = this.cn.conectar();
            this.ps = this.con.prepareStatement(list);
            this.rs = this.ps.executeQuery();
            while (this.rs.next()) {
                final int id = this.rs.getInt(1);
                final String nombre = this.rs.getString(2);
                final String apellido = this.rs.getString(3);
                final String email = this.rs.getString(4);
                final String password = this.rs.getString(5);
                final User u = new User(id, nombre, apellido, email, password);
                System.out.println(u.id + u.nombre + u.apellido + u.email + u.password);
                listUsers.add(u);
            }
            this.cn.desconectar();
        }
        catch (SQLException ex) {}
        finally {
            if (this.cn == null) {
                this.cn.desconectar();
            }
        }
        return listUsers;
    }
    
    public boolean delete(final int id) {
        final String del = "DELETE FROM usuarios where id=?";
        try {
            this.con = this.cn.conectar();
            this.ps = this.con.prepareStatement(del);
            this.ps.setInt(1, id);
            this.ps.executeUpdate();
            this.cn.desconectar();
            return true;
        }
        catch (SQLException e) {
            System.out.println("ERROR= " + e);
            return false;
        }
        finally {
            if (this.cn == null) {
                this.cn.desconectar();
            }
        }
    }
    
    public boolean edit(final int id, final String new_email, final String new_pass , final String new_name , final String new_apellido) {
        final String del = "UPDATE usuarios SET nombre=?, apellido=?, email=?, pass=? where id=?";
        try {
            this.con = this.cn.conectar();
            this.ps = this.con.prepareStatement(del);
            this.ps.setString(1, new_name);
            this.ps.setString(2, new_apellido);
            this.ps.setString(3, new_email);
            this.ps.setString(4, new_pass);
            this.ps.setInt(5, id);
            this.ps.executeUpdate();
            this.cn.desconectar();
            return true;
        }
        catch (SQLException e) {
            System.out.println("ERROR= " + e);
            return false;
        }
        finally {
            if (this.cn == null) {
                this.cn.desconectar();
            }
        }
    }
    
    public User getById(final int id) {
        final User u = new User();
        final String getById = "select * from usuarios where id=?";
        try {
            this.con = this.cn.conectar();
            this.ps = this.con.prepareStatement(getById);
            this.ps.setInt(1, id);
            this.rs = this.ps.executeQuery();
            while (this.rs.next()) {
                u.id = this.rs.getInt("id");
                u.nombre = this.rs.getString("nombre");
                u.apellido = this.rs.getString("apellido");
                u.email = this.rs.getString("email");
                u.password = this.rs.getString("pass");
            }
            this.cn.desconectar();
            return u;
        }
        catch (SQLException e) {
            return null;
        }
        finally {
            if (this.cn == null) {
                this.cn.desconectar();
            }
        }
    }
}
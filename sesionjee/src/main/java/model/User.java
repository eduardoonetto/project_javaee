
package model;

public class User
{
    int id;
    String email;
    String password;
    String nombre;
    String apellido;
    
    public User() {
    }
    
    public User(final String email, final String password) {
        this.email = email;
        this.password = password;
    }
    
    public User(final int id, final String nombre, final String apellido, final String email, final String password) {
        this.id = id;
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(final int id) {
        this.id = id;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(final String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(final String password) {
        this.password = password;
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }
    
    public String getApellido() {
        return this.apellido;
    }
    
    public void setApellido(final String apellido) {
        this.apellido = apellido;
    }
}
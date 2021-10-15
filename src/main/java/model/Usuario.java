package model;

public class Usuario {
    private String nombre;
    private String apellido;
    private String username;
    private String password;
    private boolean activo;

    public Usuario(){
        nombre = "";
        apellido = "";
        username = "";
        password = "";
        activo = true;
    }
    
    public Usuario(String nombre, String apellido, String username, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.password = password;
        this.activo = true;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean isActivo() {
        return activo;
    }

    public void setEstado(boolean estado) {
        this.activo = estado;
    }
    
    public void desactivarCuenta(){
        this.activo = false;
    }
    
    public void reactivarCuenta(){
        this.activo = true;
    }
    
    public String toString(){
        return this.nombre+" "+this.apellido+" ("+this.username+") (Estado: "+this.activo+")\n";
    }
    
}

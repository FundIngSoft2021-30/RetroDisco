package model;

/**
 * Clase que representa un usuario del sistema.
 */
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
    
    /**
     * Constructor de la clase Usuario.
     * @param nombre Nombre del usuario.
     * @param apellido Apellido del usuario.
     * @param username Username del usuario.
     * @param password Contraseña del usuario.
     * @param activo Indica si el usuario está activo o no.
     * @return Usuario con los datos ingresados.
     */
    public Usuario(String nombre, String apellido, String username, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.password = password;
        this.activo = true;
    }

    /**
     * @return Nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     * @param nombre Nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return Apellido del usuario.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Establece el apellido del usuario.
     * @param apellido Apellido del usuario.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return Username del usuario.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el username del usuario.
     * @param username Username del usuario.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return Contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     * @param password Contraseña del usuario.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * @return True si el usuario está activo, false en caso contrario.
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * Establece si el usuario está activo o no.
     * @param estado True si el usuario está activo, false en caso contrario.
     */
    public void setEstado(boolean estado) {
        this.activo = estado;
    }
    
    /**
     * Desactiva una cuenta de usuario.
     */
    public void desactivarCuenta(){
        this.activo = false;
    }
    
    /**
     * Activa una cuenta de usuario.
     */
    public void reactivarCuenta(){
        this.activo = true;
    }
    
    /**
     * Resumen de información del usuario.
     * @return String con información del usuario.
     */
    public String toString(){
        return this.nombre+" "+this.apellido+" ("+this.username+") (Estado: "+this.activo+")\n";
    }
    
}

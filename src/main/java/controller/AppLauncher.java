package controller;
import model.*;

public class AppLauncher {

    private static Usuario usuarioActual;
    private static Disco discoActual;
    private static Carrito carritoActual;

    
    public static void main(String[] args) {
        App.main(args);
    }
    /**
     * Encuentra a un usuario
     * @return usuario
     */
    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }
    /**
     * Establece el usuario que eligió
     * @param usuarioActual el usuario establecido
     */
    public static void setUsuarioActual(Usuario usuarioActual) {
        AppLauncher.usuarioActual = usuarioActual;
    }
    /**
     * 
     * @return el disco escogido
     */
    public static Disco getDiscoActual() {
        return discoActual;
    }
    /**
     * Establece la informacion del disco
     * @param discoActual disco escogido
     */
    public static void setDiscoActual(Disco discoActual) {
        AppLauncher.discoActual = discoActual;
    }
    /**
     * Encuentra el carrito solicitado
     * @return carrito escogido
     */
    public static Carrito getCarritoActual() {
        return carritoActual;
    }
    /**
     * Establece la informacion del carrito
     * @param carritoActual El carrito establecido
     */
    public static void setCarritoActual(Carrito carritoActual) {
        AppLauncher.carritoActual = carritoActual;
    }
    
    
}

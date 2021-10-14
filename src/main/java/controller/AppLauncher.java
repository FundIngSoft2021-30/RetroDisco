package controller;
import model.*;

public class AppLauncher {

    private static Usuario usuarioActual;
    private static Disco discoActual;

    public static void main(String[] args) {
        App.main(args);
    }

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public static void setUsuarioActual(Usuario usuarioActual) {
        AppLauncher.usuarioActual = usuarioActual;
    }

    public static Disco getDiscoActual() {
        return discoActual;
    }

    public static void setDiscoActual(Disco discoActual) {
        AppLauncher.discoActual = discoActual;
    }
}

package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.fxml.Initializable;

public class VerInformacionController implements Initializable{

    @FXML
    private Button agregarCarrito;

    @FXML
    private Label anio;

    @FXML
    private ImageView ayuda;

    @FXML
    private Spinner<Integer> cantidad;

    @FXML
    private ImageView carrito;

    @FXML
    private ImageView cuenta;

    @FXML
    private Label formato;

    @FXML
    private Label genero;

    @FXML
    private Label nombreArtista;

    @FXML
    private Label nombreDisco;

    @FXML
    private ImageView notificaciones;

    @FXML
    private Label precio;

    @FXML
    private Button regresar;

    @FXML
    void agregarCarrito(ActionEvent event) {
        
    }

    @FXML
    void regresarBusqueda(ActionEvent event) {

    }

    @FXML
    void verAyuda(MouseEvent event) {

    }

    @FXML
    void verCarrito(MouseEvent event) {

    }

    @FXML
    void verCuenta(MouseEvent event) {

    }

    @FXML
    void verNotificaciones(MouseEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nombreDisco.setText(AppLauncher.getDiscoActual().getNombre());
        nombreArtista.setText(AppLauncher.getDiscoActual().getArtista());
        anio.setText(Integer.toString(AppLauncher.getDiscoActual().getPublicacion()));
        formato.setText(AppLauncher.getDiscoActual().getFormato());
        genero.setText(AppLauncher.getDiscoActual().getGenero());
        precio.setText(Double.toString(AppLauncher.getDiscoActual().getPrecio()));
        cantidad = new Spinner<Integer>(1,AppLauncher.getDiscoActual().getCantidad(),1);
    }

}

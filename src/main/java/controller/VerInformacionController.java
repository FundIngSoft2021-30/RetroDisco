package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import database.CRUD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DetalleOrden;

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
        int unidades = cantidad.getValue();
        String idDisco = AppLauncher.getDiscoActual().getId();
        String disco = AppLauncher.getDiscoActual().getNombre();
        
        DetalleOrden detalle = new DetalleOrden(idDisco, disco,unidades,Double.parseDouble(precio.getText()));
        AppLauncher.getCarritoActual().agregarDisco(detalle);
        
        CRUD.actualizarCarrito(AppLauncher.getCarritoActual(), AppLauncher.getUsuarioActual().getUsername(),AppLauncher.getCarritoActual());
        
        Stage stage= (Stage)regresar.getScene().getWindow();
        stage.close();
    }

    @FXML
    void regresarBusqueda(ActionEvent event) throws IOException {
        Stage stage= (Stage)regresar.getScene().getWindow();
        stage.close();
    }

    @FXML
    void verAyuda(MouseEvent event) {

    }

    @FXML
    void verCarrito(MouseEvent event) throws IOException {
        if(AppLauncher.getUsuarioActual()!=null)
        {
            Stage stage= (Stage)regresar.getScene().getWindow();
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("../view/CarritoCompras.fxml"));
            stage.close();
            Scene scene = new Scene(root);
            stage.setTitle("Carrito de compras - RetroDisco");
            stage.setScene(scene);
            stage.show();
        }
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
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 0, 1, 1);
        if(AppLauncher.getDiscoActual().getCantidad()>0){
            valueFactory= new SpinnerValueFactory.IntegerSpinnerValueFactory(1, AppLauncher.getDiscoActual().getCantidad(), 1, 1);
        }
        cantidad.setValueFactory(valueFactory);
        if(AppLauncher.getUsuarioActual()==null){
            agregarCarrito.setDisable(true);
        }else{
            agregarCarrito.setDisable(false);
        }
    }

}

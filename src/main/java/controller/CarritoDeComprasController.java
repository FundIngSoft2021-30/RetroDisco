package controller;

import database.CRUD;
import model.Carrito;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.fxml.Initializable;
import java.net.URL;
import javafx.scene.control.Alert;
import java.util.*;

public class CarritoDeComprasController implements Initializable {

    @FXML
    private Button regresarAbusqeda;

    @FXML
    private Button pagar;

    @FXML
    private Button eliminarDiscos;

    @FXML
    private Button VaciarCarrito;

    @FXML
    private Label total;

    @FXML
    private ListView<Carrito> resultados;

    @FXML
    void RegresaInterfazBusqueda(ActionEvent event) {

    }

    @FXML
    void eliminarDiscos(ActionEvent event) {

    }

    @FXML
    void pagarCarrito(ActionEvent event) {

    }

    @FXML
    void vaciarCarrito(ActionEvent event) {
        Carrito carrito = new Carrito();
        carrito.setDiscos(null);
        CRUD.actualizarCarrito(carrito, AppLauncher.getUsuarioActual().getUsername());
        resultados.getItems().clear();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alerta!");
        alert.setHeaderText("Carrito vaciado");
        alert.showAndWait();

    }

    /*
     * @FXML void verDisco(MouseEvent event) {
     * 
     * }
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Carrito carrito = CRUD.obtenerCarrito(AppLauncher.getUsuarioActual().getUsername());
        resultados.getItems().add(carrito);

    }

}

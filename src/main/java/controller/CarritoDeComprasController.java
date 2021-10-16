package controller;

import database.CRUD;
import model.Carrito;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import javafx.scene.control.Alert;
import java.util.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
    void RegresaInterfazBusqueda(ActionEvent event) throws IOException {
        Stage stage=(Stage) regresarAbusqeda.getScene().getWindow();
        stage.close();
        Parent root=FXMLLoader.load(getClass().getResource("../view/BuscarDisco.fxml"));
        stage.close();
        Scene scene = new Scene(root);
        stage.setTitle("Buscador - RetroDisco");
        stage.setScene(scene);
        stage.show();
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
        carrito.getDiscos().clear();
        CRUD.actualizarCarrito(carrito, AppLauncher.getUsuarioActual().getUsername());
        AppLauncher.setCarritoActual(CRUD.obtenerCarrito(AppLauncher.getUsuarioActual().getUsername()));
        resultados.getItems().clear();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alerta!");
        alert.setHeaderText("Carrito vaciado");
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AppLauncher.setCarritoActual(CRUD.obtenerCarrito(AppLauncher.getUsuarioActual().getUsername()));
        resultados.getItems().add(AppLauncher.getCarritoActual());
    }

}

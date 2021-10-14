package controller;

import java.io.IOException;
import database.CRUD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.Disco;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BuscarDiscoController {

    @FXML
    private Button CerrarSesion;

    @FXML
    private ImageView ayuda;

    @FXML
    private ImageView buscar;

    @FXML
    private ImageView carrito;

    @FXML
    private ComboBox<String> filtro;

    @FXML
    private TextField nombreDisco;

    @FXML
    private ImageView notificaciones;

    @FXML
    private ListView<Disco> resultados;

    @FXML
    private ImageView usuario;

    @FXML
    private Button vender;

    @FXML
    void buscarDisco(MouseEvent event) {
    }

    @FXML
    void cerrarSesion(ActionEvent event) throws IOException {
        Stage stage= (Stage)CerrarSesion.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../view/IniciarSesion.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Iniciar Sesion  - RetroDisco");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void venderDisco(ActionEvent event) throws IOException {
        Stage stage= (Stage)vender.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../view/PublicarDisco.fxml"));
        stage.close();
        Scene scene = new Scene(root);
        stage.setTitle("Publicar Disco - RetroDisco");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void verAyuda(MouseEvent event) {

    }

    @FXML
    void verCarrito(MouseEvent event) {

    }

    @FXML
    void verDisco(MouseEvent event) {

    }

    @FXML
    void verNotificaciones(MouseEvent event) {

    }

    @FXML
    void verUsuario(MouseEvent event) {

    }

}

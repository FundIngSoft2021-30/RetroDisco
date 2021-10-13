package controller;

import java.io.IOException;

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

public class BuscarSinLoginController {

    @FXML
    private ImageView ayuda;

    @FXML
    private ImageView buscar;

    @FXML
    private Button crearCuenta;

    @FXML
    private ComboBox<String> filtro;

    @FXML
    private Button iniciarSesion;

    @FXML
    private TextField nombreDisco;

    @FXML
    private ListView<Disco> resultados;

    @FXML
    void buscarDisco(MouseEvent event) {
        /*Prueba de buscador y resultados.
        resultados.getItems().add(new Disco("august", "LuisM-cpu", 2021, "CD", 20000, 1, "LuisM-cpu", "Folk"));
        resultados.getItems().toString();*/
    }

    @FXML
    void crearCuenta(ActionEvent event) throws IOException {
        Stage stage= (Stage)crearCuenta.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../view/CrearCuenta.fxml"));
        stage.close();
        Scene scene = new Scene(root);
        stage.setTitle("Crear Cuenta - RetroDisco");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void iniciarSesion(ActionEvent event) throws IOException {
        Stage stage= (Stage)iniciarSesion.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../view/IniciarSesion.fxml"));
        stage.close();
        Scene scene = new Scene(root);
        stage.setTitle("Iniciar Sesión - RetroDisco");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void verAyuda(MouseEvent event) {

    }

    @FXML
    void verDisco(MouseEvent event) {

    }

}

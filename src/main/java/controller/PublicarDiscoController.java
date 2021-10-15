package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PublicarDiscoController {

    @FXML
    private Button cancelar;

    @FXML
    private TextField cantidad;

    @FXML
    private TextField formato;

    @FXML
    private TextField genero;

    @FXML
    private TextField nombreartista;

    @FXML
    private TextField nombredisco;

    @FXML
    private TextField precio;

    @FXML
    private TextField publicacion;

    @FXML
    private Button publicar;

    @FXML
    void VolverInicio(ActionEvent event) throws IOException {
        Stage stage=(Stage) cancelar.getScene().getWindow();
        stage.close();
        Parent root=FXMLLoader.load(getClass().getResource("../view/BuscarDisco.fxml"));
        stage.close();
        Scene scene = new Scene(root);
        stage.setTitle("Buscador - RetroDisco");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void publicarDisco(ActionEvent event) {

    }

}

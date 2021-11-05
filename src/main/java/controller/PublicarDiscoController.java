package controller;

import database.CRUD;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Disco;

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
    /**
     * Permite regresar al inicio de la aplicación 
     * @param event 
     * @throws IOException
     */
    @FXML
    void VolverInicio(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancelar.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../view/BuscarDisco.fxml"));
        stage.close();
        Scene scene = new Scene(root);
        stage.setTitle("Buscador - RetroDisco");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Permite publicar un disco que no exista 
     * @param event
     */
    @FXML
    void publicarDisco(ActionEvent event) {

        if (cantidad.getText().isEmpty() || formato.getText().isEmpty() || genero.getText().isEmpty()
                || nombreartista.getText().isEmpty() || nombredisco.getText().isEmpty() || precio.getText().isEmpty()
                || publicacion.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Datos Incompletos");
            alert.setHeaderText("Es necesario llenar todos los campos!");
            alert.showAndWait();
            return;
        } else {
            int cant = Integer.parseInt(cantidad.getText().trim());
            int pub = Integer.parseInt(publicacion.getText().trim());
            double pre = Double.parseDouble(precio.getText().trim());
            int cantidadDiscosUsuario = CRUD.obtenerDiscosUsuario(AppLauncher.getUsuarioActual().getUsername()).size();
            String idDisco = "Disco-" + AppLauncher.getUsuarioActual().getUsername() + "-" + cantidadDiscosUsuario;

            Disco disco = new Disco(idDisco, nombredisco.getText().trim().toUpperCase(), nombreartista.getText().trim().toUpperCase(), pub,
                    formato.getText().trim().toUpperCase(), pre, cant, AppLauncher.getUsuarioActual().getUsername(),
                    genero.getText().trim().toUpperCase());
            CRUD.agregarDisco(disco);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Disco Publicado!");
            alert.setHeaderText("El disco se publicó correctamente.");            
            alert.showAndWait();
            return;
        }

    }

}

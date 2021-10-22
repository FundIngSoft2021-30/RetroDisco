package controller;

import java.io.IOException;

import javafx.scene.image.Image;
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
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.*;

public class BuscarSinLoginController implements Initializable{

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
    void buscarDisco(MouseEvent event) throws InterruptedException, ExecutionException {
        resultados.getItems().clear();
        String c = filtro.getSelectionModel().getSelectedItem();
        if (nombreDisco.getText().isEmpty()) {
            resultados.getItems().clear();
        } else {
            Map<String, Disco> resultadoBusqueda = new HashMap<>();
            if (filtro.getSelectionModel().getSelectedItem() == null || filtro.getValue().equalsIgnoreCase("[Seleccione opcion]")) {
                resultadoBusqueda = CRUD.busquedaGeneral(nombreDisco.getText());
            } else {
                resultadoBusqueda = CRUD.buscarDiscoCategoria(nombreDisco.getText(), c);
            }
            ArrayList<Disco> discos = new ArrayList<Disco>(resultadoBusqueda.values());
            if (discos.isEmpty()) {
                // Mensaje de error

            } else {
                resultados.getItems().clear();
                for (Disco discoA : discos) {
                    resultados.getItems().add(discoA);
                }
            }
        }
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
        Scene scene = new Scene(root);
        stage.setTitle("Iniciar Sesión - RetroDisco");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void verAyuda(MouseEvent event) {

    }

    @FXML
    void verDisco(MouseEvent event) throws IOException {
        if(resultados.getSelectionModel().getSelectedItem()!=null)
        {
            AppLauncher.setDiscoActual(resultados.getSelectionModel().getSelectedItem());
            Stage stage= new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../view/VerInfo.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle(AppLauncher.getDiscoActual().toString());
            stage.getIcons().add(new Image("file:src\\main\\java\\view\\iconos\\Buscar_i.png"));
            stage.setScene(scene);
            stage.show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        filtro.getItems().add("[Seleccione opcion]");
        filtro.getItems().add("artista");
        filtro.getItems().add("genero");
        filtro.getItems().add("formato");
        filtro.getItems().add("nombre");
        filtro.getItems().add("vendedor");
        filtro.setValue("[Seleccione opcion]");
    }
}

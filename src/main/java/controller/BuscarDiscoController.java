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
import javafx.scene.image.Image;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.*;

public class BuscarDiscoController implements Initializable {

    @FXML
    private Button salir;

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

    /**
     * Busca el disco despues de haber utilizado el cursor y haber seleccionado la opcion
     * @param event Despliega los resultados 
     * @throws InterruptedException
     * @throws ExecutionException
     */
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
    /**
     * Cierra la sesión después de haber seleccionado el botón
     * @param event Se activa la animación al cerrar sesión
     * @throws IOException
     */
    @FXML
    void cerrarSesion(ActionEvent event) throws IOException {
        Stage stage = (Stage) salir.getScene().getWindow();
        stage.close();
        AppLauncher.setUsuarioActual(null);
        AppLauncher.setCarritoActual(null);
        Parent root = FXMLLoader.load(getClass().getResource("../view/IniciarSesion.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Iniciar Sesion  - RetroDisco");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Vende el disco al haber presionado el botón
     * @param event Hace la animación después de haber presionado el botón
     * @throws IOException
     */
    @FXML
    void venderDisco(ActionEvent event) throws IOException {
        Stage stage = (Stage) vender.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../view/PublicarDisco.fxml"));
        stage.close();
        Scene scene = new Scene(root);
        stage.setTitle("Publicar Disco - RetroDisco");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * 
     * @param event
     */
    @FXML
    void verAyuda(MouseEvent event) {

    }
    /**
     * El usuario puede ver el carrito después de haber presionado el botón 
     * @param event Muestra el carrito cuando se acciona el evento
     * @throws IOException
     */
    @FXML
    void verCarrito(MouseEvent event) throws IOException {
        Stage stage = (Stage) carrito.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../view/CarritoCompras.fxml"));
        stage.close();
        Scene scene = new Scene(root);
        stage.setTitle("Carrito de Compras - RetroDisco");
        stage.setScene(scene);
        stage.show();

    }
    /**
     * Se puede ver la información del disco después de haber presionado el botón
     * @param event Despliega la animación al elegir el disco
     * @throws IOException
     */
    @FXML
    void verDisco(MouseEvent event) throws IOException {
        if (resultados.getSelectionModel().getSelectedItem() != null) {
            AppLauncher.setDiscoActual(resultados.getSelectionModel().getSelectedItem());
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../view/VerInfo.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle(AppLauncher.getDiscoActual().toString());
            stage.getIcons().add(new Image("file:src\\main\\java\\view\\iconos\\Buscar_i.png"));
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void verNotificaciones(MouseEvent event) {

    }

    @FXML
    void verUsuario(MouseEvent event) {

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

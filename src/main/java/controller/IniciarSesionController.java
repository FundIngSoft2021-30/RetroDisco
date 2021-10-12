package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class IniciarSesionController {

    @FXML
    private Button Cuenta;

    @FXML
    private Button Ingresar;

    @FXML
    private Button Invitado;

    @FXML
    private VBox containerLeft;

    @FXML
    private VBox containerRight;

    @FXML
    private PasswordField txtContraseña;

    @FXML
    private TextField txtUsuario;

    @FXML
    void IniciarSesion(ActionEvent event) {

    }

    @FXML
    void crearCuenta(ActionEvent event) throws IOException {
        Stage stage= (Stage)Cuenta.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../view/CrearCuenta.fxml"));
        stage.close();
        Scene scene = new Scene(root);
        stage.setTitle("Crear Cuenta - RetroDisco");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void iniciarInvitado(ActionEvent event) {

    }

}

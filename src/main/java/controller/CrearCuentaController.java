package controller;

import database.CRUD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.Usuario;

public class CrearCuentaController {

    @FXML
    private Button botonRegresar;

    @FXML
    private VBox containerRight;

    @FXML
    private Button crearCuenta;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtNombre;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private PasswordField txtUsername;

    @FXML
    void CrearCuenta(ActionEvent event) {
        Usuario usuario1=new Usuario(txtNombre.getText(), txtApellido.getText(), txtUsername.getText(), txtPassword.getText());
        CRUD.agregarUsuario(usuario1);
    }

    @FXML
    void regresarInicio(ActionEvent event) {

    }

}

package controller;

import java.io.IOException;

import database.CRUD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.Usuario;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        boolean agregado=CRUD.agregarUsuario(usuario1);
        if(agregado)
        {
            System.out.println("El usuario "+usuario1.getUsername()+" se agrego correctamente.");
        }else{
            System.out.println("El username "+usuario1.getUsername()+" ya se encuentra en uso.");
        }
    }

    @FXML
    void regresarInicio(ActionEvent event) throws IOException {
        Stage stage= (Stage)botonRegresar.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../view/IniciarSesion.fxml"));
        stage.close();
        Scene scene = new Scene(root);
        stage.setTitle("Iniciar Sesión - RetroDisco");
        stage.setScene(scene);
        stage.show();
    }

}

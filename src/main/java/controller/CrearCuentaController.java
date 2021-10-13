package controller;

import java.io.IOException;

import database.CRUD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import model.Usuario;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;

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
    private TextField txtUsername;

    @FXML
    private Label errorCrearCuenta;

    @FXML
    void CrearCuenta(ActionEvent event) {
        if(txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty()
         || txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty()){
            errorCrearCuenta.setTextFill(Paint.valueOf("#ef2121"));
            errorCrearCuenta.setText("Ingrese todos los datos para poder crear su cuenta.");
            if(txtNombre.getText().isEmpty())
            {
                txtNombre.setPromptText("Ingrese su nombre.");
            }
            if(txtApellido.getText().isEmpty())
            {
                txtApellido.setPromptText("Ingrese su apellido.");
            }
            if(txtUsername.getText().isEmpty())
            {
                txtUsername.setPromptText("Ingrese su username.");
            }
            if(txtPassword.getText().isEmpty())
            {
                txtPassword.setPromptText("Ingrese su contraseña.");
            }
            return;
        }
        Usuario usuario1=new Usuario(txtNombre.getText(), txtApellido.getText(), txtUsername.getText(), txtPassword.getText());
        boolean agregado=CRUD.agregarUsuario(usuario1);
        if(agregado)
        {
            errorCrearCuenta.setTextFill(Paint.valueOf("#40d222"));
            errorCrearCuenta.setText("Usuario "+txtUsername.getText()+" creado correctamente.");
        }else{
            errorCrearCuenta.setTextFill(Paint.valueOf("#ef2121"));
            errorCrearCuenta.setText("El username "+txtUsername.getText()+" ya se encuentra en uso.");
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

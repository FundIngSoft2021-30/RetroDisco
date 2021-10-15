package controller;

import database.CRUD;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
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
    private Label errorLogin;

    @FXML
    void IniciarSesion(ActionEvent event) throws IOException{
        String username = txtUsuario.getText().trim();
        String password = txtContraseña.getText().trim();
        if(txtUsuario.getText().isEmpty()||txtContraseña.getText().isEmpty()){
            errorLogin.setTextFill(Paint.valueOf("#ef2121"));
            errorLogin.setText("ESP Complete todos los espacios");
        }else{
            if(CRUD.existeUsername(username)){
                if(CRUD.autenticarPassword(username, password)){
                    
                    AppLauncher.setUsuarioActual(CRUD.obtenerUsuario(username));
                    Stage stage=(Stage) Ingresar.getScene().getWindow();
                    stage.close();
                    Parent root=FXMLLoader.load(getClass().getResource("../view/BuscarDisco.fxml"));
                    stage.close();
                    Scene scene = new Scene(root);
                    stage.setTitle("Buscador - RetroDisco");
                    stage.setScene(scene);
                    stage.show();
                    
                }else{
                    errorLogin.setTextFill(Paint.valueOf("#ef2121"));
                    errorLogin.setText("C.I La contraseña es incorrecta");
                }
            }
            else{
                errorLogin.setTextFill(Paint.valueOf("#ef2121"));
                errorLogin.setText("USU El usuario no existe");
            }
        }/*
        Stage stage=(Stage) Ingresar.getScene().getWindow();
        stage.close();
        Parent root=FXMLLoader.load(getClass().getResource("../view/IniciarSesion.fxml"));
        stage.close();
        Scene scene = new Scene(root);
        stage.setTitle("Sesion iniciada - RetroDisco");
        stage.setScene(scene);
        stage.show();*/
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
    void iniciarInvitado(ActionEvent event) throws IOException {
        AppLauncher.setUsuarioActual(null);
        Stage stage= (Stage)Invitado.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../view/BuscarDiscoSinLogin.fxml"));
        stage.close();
        Scene scene = new Scene(root);
        stage.setTitle("Buscador - RetroDisco");
        stage.setScene(scene);
        stage.show();
    }

}

package model;

import database.CRUD;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        new CRUD();
        Parent root = FXMLLoader.load(getClass().getResource("../view/IniciarSesion.fxml"));
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image("file:src\\main\\java\\view\\iconos\\LogoRD.png"));
        stage.setTitle("Iniciar Sesion  - RetroDisco");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    
    
}

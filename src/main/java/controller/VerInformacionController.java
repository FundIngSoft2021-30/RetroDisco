package controller;

import database.CRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Carrito;
import model.DetalleOrden;
import model.Disco;

public class VerInformacionController implements Initializable{

    @FXML
    private Button agregarCarrito;

    @FXML
    private Label anio;

    @FXML
    private ImageView ayuda;

    @FXML
    private Spinner<Integer> cantidad;

    @FXML
    private ImageView carrito;

    @FXML
    private ImageView cuenta;

    @FXML
    private Label formato;

    @FXML
    private Label genero;

    @FXML
    private Label nombreArtista;

    @FXML
    private Label nombreDisco;

    @FXML
    private ImageView notificaciones;

    @FXML
    private Label precio;

    @FXML
    private Button regresar;

    @FXML
    void agregarCarrito(ActionEvent event) {
        if(AppLauncher.getDiscoActual().getCantidad()<1){
            return;
        }
        if(AppLauncher.getUsuarioActual() == null){
            return;
        }
        int unidades = cantidad.getValue();
        Disco disco = AppLauncher.getDiscoActual();
        Carrito c = AppLauncher.getCarritoActual();
        DetalleOrden detalle = new DetalleOrden(disco,unidades,disco.getPrecio());
        if(c!=null && detalle!=null){
            System.out.println("Ni el carrito c es nulo, ni el detalle es nulo");
            c.agregarDisco(detalle);
            CRUD.actualizarCarrito(c, AppLauncher.getUsuarioActual().getUsername());
            AppLauncher.setCarritoActual(c);
            System.out.println("CRUD actualizada");
            
        }else{
            System.out.println("El carrito c es nulo");
        }
        
        
        
        //DetalleOrden detalle = new DetalleOrden(disco,unidades,disco.getPrecio());
        
//        c.agregarDisco(detalle);
  //      System.out.println("agregar Disco a c");
        
        
            //c.agregarDisco( new DetalleOrden(disco,unidades,disco.getPrecio()));
            //System.out.println("Agregar disco a carrito actual");
        
    //        CRUD.actualizarCarrito(AppLauncher.getCarritoActual(), AppLauncher.getUsuarioActual().getUsername());
      //      System.out.println("crud actualizada");
        Stage stage= (Stage)regresar.getScene().getWindow();
        stage.close();
        
    }

    @FXML
    void regresarBusqueda(ActionEvent event) throws IOException {
        Stage stage= (Stage)regresar.getScene().getWindow();
        stage.close();
    }

    @FXML
    void verAyuda(MouseEvent event) {

    }

    @FXML
    void verCarrito(MouseEvent event) throws IOException {
        Stage stage= (Stage)regresar.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../view/CarritoCompras.fxml"));
        stage.close();
        Scene scene = new Scene(root);
        stage.setTitle("Carrito de compras - RetroDisco");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void verCuenta(MouseEvent event) {

    }

    @FXML
    void verNotificaciones(MouseEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nombreDisco.setText(AppLauncher.getDiscoActual().getNombre());
        nombreArtista.setText(AppLauncher.getDiscoActual().getArtista());
        anio.setText(Integer.toString(AppLauncher.getDiscoActual().getPublicacion()));
        formato.setText(AppLauncher.getDiscoActual().getFormato());
        genero.setText(AppLauncher.getDiscoActual().getGenero());
        precio.setText(Double.toString(AppLauncher.getDiscoActual().getPrecio()));
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 0, 1, 1);
        if(AppLauncher.getDiscoActual().getCantidad()>0){
            valueFactory= new SpinnerValueFactory.IntegerSpinnerValueFactory(1, AppLauncher.getDiscoActual().getCantidad(), 1, 1);
        }
        
        cantidad.setValueFactory(valueFactory);
    }

}

package model;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Clase que representa una orden de compra.
 */
public class Orden {
    private Usuario comprador;
    private String fecha;
    private ArrayList<DetalleOrden> articulos;
    
    public Orden(){
        comprador = new Usuario();
        fecha = "";
        articulos = new ArrayList<>();
    }
    
    /**
     * Constructor de la clase Orden.
     * @param comprador Usuario que realiza la orden.
     * @param articulos Articulos de la orden.
     * @return Orden con los datos pasados por parámetro.
     */
    public Orden(Usuario comprador,  ArrayList<DetalleOrden> articulos) {
        this.comprador = comprador;
        Timestamp f = new Timestamp(System.currentTimeMillis());
        this.fecha = f.toString();
        this.articulos = articulos;
    }

    /**
     * @return Usuario que realiza la orden.
     */
    public Usuario getComprador() {
        return comprador;
    }

    /**
     * Establece el usuario que realiza la orden.
     * @param comprador Usuario que realiza la orden.
     */
    public void setComprador(Usuario comprador) {
        this.comprador = comprador;
    }

    /**
     * @return Articulos de la orden.
     */
    public ArrayList<DetalleOrden> getArticulos() {
        return articulos;
    }

    /**
     * Establece los articulos de la orden.
     * @param articulos Articulos de la orden.
     */
    public void setArticulos(ArrayList<DetalleOrden> articulos) {
        this.articulos = articulos;
    }

    /**
     * @return Fecha de la orden.
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha de la orden.
     * @param fecha Fecha de la orden.
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    /**
     * Resumen del contenido de la orden.
     * @return String con el contenido de la orden.
     */
    public String toString(){
        String retorno = "";
        String user = "";
        double totalOrden = 0;
        if(this.comprador!=null){
            user = this.comprador.toString();
        }
        //String user = this.comprador.toStr
        retorno += user + "(Orden Fecha : "+this.getFecha()+") \n";
        for(DetalleOrden detalle: articulos){
            retorno += detalle.toString();
            totalOrden += (detalle.getUnidades()*detalle.getPrecioUnidad());
        }
        retorno += "Total compra : $"+totalOrden;
        return retorno;
    }
    
}

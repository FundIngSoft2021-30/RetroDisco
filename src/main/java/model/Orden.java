package model;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Orden {
    private Usuario comprador;
    private String fecha;
    private ArrayList<DetalleOrden> articulos;
    
    public Orden(){
        comprador = new Usuario();
        fecha = "";
        articulos = new ArrayList<>();
    }
    
    public Orden(Usuario comprador,  ArrayList<DetalleOrden> articulos) {
        this.comprador = comprador;
        Timestamp f = new Timestamp(System.currentTimeMillis());
        this.fecha = f.toString();
        this.articulos = articulos;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public void setComprador(Usuario comprador) {
        this.comprador = comprador;
    }

    public ArrayList<DetalleOrden> getArticulos() {
        return articulos;
    }

    public void setArticulos(ArrayList<DetalleOrden> articulos) {
        this.articulos = articulos;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public String toString(){
        double totalCompra = 0;
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

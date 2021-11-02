package model;

import java.util.ArrayList;

/**
 * Clase que representa un carrito de compras.
 */
public class Carrito {
    private ArrayList<DetalleOrden> discos;
    
    public Carrito() {
        this.discos = new ArrayList<DetalleOrden>();
    }
    
    /**
     * Agrega una lista de discos al carrito.
     * @param discos Lista de discos a agregar.
     */
    public Carrito(ArrayList<DetalleOrden> discos) {
        this.discos =discos;
    }
    
    /**
     * Devuelve los discos en el carrito.
     * @return Lista de discos.
     */
    public ArrayList<DetalleOrden> getDiscos() {
        return discos;
    }

    /**
     * Establece la lista de discos en el carrito.
     * @param discos Lista de discos.
     */
    public void setDiscos(ArrayList<DetalleOrden> discos) {
        this.discos = discos;
    }
    
    /**
     * Agrega el {@code DetalleOrden} de un disco nuevo al carrito.
     * @param detalle {@code DetalleOrden} de un disco a agregar.
     */
    public void agregarDisco(DetalleOrden detalle){
        this.discos.add(detalle);
        /*if(discos.contains(detalle)){
            return false;
        }
        else{
            this.discos.add(detalle);
            return true;
        }*/        
    }
    
    /**
     * Elimina un disco del carrito.
     * @param detalle {@code DetalleOrden} del disco a eliminar.
     * @return {@value true} si se elimina correctamente, {@value false} en caso contrario.
     */
    public boolean quitarDisco (DetalleOrden detalle){
        return this.discos.remove(detalle);
    }
    
    /**
     * Elimina todos los artículos del carrito de compras.
     */
    public void vaciarCarrito (){
        discos.clear();
    }
    
    /**
     * Devuelve el total a pagar de la compra.
     * @return Total de la compra.
     */
    public double getTotalPagar() {
        double total = 0;
        for(DetalleOrden d: this.getDiscos()){
            total += (d.getPrecioUnidad()*d.getUnidades());
        }
        return total;
    }
    
    /**
     * Retorna la cantidad de discos contenida en el carrito.
     * @return Cantidad de discos.
     */
    public int size(){
        return this.discos.size();
    }
    
    /**
     * Retorna un {@code String} con los datos del carrito.
     */
    public String toString(){
        String retorno = "";
        for(DetalleOrden disco: discos){
            retorno += disco.toString();
        }
        return retorno;
    }
}

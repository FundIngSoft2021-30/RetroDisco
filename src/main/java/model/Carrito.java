package model;

import java.util.ArrayList;

public class Carrito {
    private ArrayList<DetalleOrden> discos;
    
    public Carrito() {
        this.discos = new ArrayList<DetalleOrden>();
    }
    
    public Carrito(ArrayList<DetalleOrden> discos) {
        this.discos =discos;
    }
    
    public ArrayList<DetalleOrden> getDiscos() {
        return discos;
    }
    public void setDiscos(ArrayList<DetalleOrden> discos) {
        this.discos = discos;
    }
    
    public boolean agregarDisco(DetalleOrden detalle){
        if(discos.contains(detalle)){
            return false;
        }
        else{
            this.discos.add(detalle);
            return true;
        }        
    }
    
    public boolean quitarDisco (DetalleOrden detalle){
        return this.discos.remove(detalle);
    }
    
    public void vaciarCarrito (){
        discos.clear();
    }
    
    public boolean contieneDisco(Disco disco){
        for(DetalleOrden detalle: this.discos){
            if(detalle.getDisco().getId().equals(disco.getId())){
                return true;
            }
        }
        return false;
    }
    
    public double getTotalPagar() {
        double total = 0;
        for(DetalleOrden d: this.getDiscos()){
            total += (d.getPrecioUnidad()*d.getUnidades());
        }
        return total;
    }
    
    public int size(){
        return this.discos.size();
    }
    
    public String toString(){
        String retorno = "";
        for(DetalleOrden disco: discos){
            retorno += disco.toString();
        }
        return retorno;
    }
}

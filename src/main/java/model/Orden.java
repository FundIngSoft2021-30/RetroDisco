package model;

import java.util.ArrayList;

public class Orden {
    private Usuario comprador;
    private long total;
    private ArrayList<Disco> articulos;
    
    public Orden(Usuario comprador, long total, ArrayList<Disco> articulos) {
        this.comprador = comprador;
        this.total = total;
        this.articulos = articulos;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public void setComprador(Usuario comprador) {
        this.comprador = comprador;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public ArrayList<Disco> getArticulos() {
        return articulos;
    }

    public void setArticulos(ArrayList<Disco> articulos) {
        this.articulos = articulos;
    }
    
}

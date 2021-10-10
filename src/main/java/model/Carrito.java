package model;

import java.util.ArrayList;

public class Carrito {
    private ArrayList<Disco> discos;
    private long totalPagar;
    public Carrito() {
        this.discos.clear();
        this.totalPagar = 0;
    }
    public ArrayList<Disco> getDiscos() {
        return discos;
    }
    public void setDiscos(ArrayList<Disco> discos) {
        this.discos = discos;
    }
    public long getTotalPagar() {
        return totalPagar;
    }
    public void setTotalPagar(long totalPagar) {
        this.totalPagar = totalPagar;
    }
}

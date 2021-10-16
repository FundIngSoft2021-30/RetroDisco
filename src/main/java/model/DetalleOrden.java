/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class DetalleOrden {
    private Disco disco;
    private int unidades;
    private double precioUnidad;

    public DetalleOrden() {
        disco = null;
        unidades = 0;
        precioUnidad = 0;
    }

    public DetalleOrden(Disco disco, int unidades, double precioUnidad) {
        this.disco = disco;
        this.unidades = unidades;
        this.precioUnidad = precioUnidad;
    }

    public Disco getDisco() {
        return disco;
    }

    public int getUnidades() {
        return unidades;
    }

    public double getPrecioUnidad() {
        return precioUnidad;
    }

    public void setDisco(Disco disco) {
        this.disco = disco;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public void setPrecioUnidad(double precioUnidad) {
        this.precioUnidad = precioUnidad;
    }

    public String toString() {
        if (disco != null) {
            return this.disco.getNombre() + " - " + this.precioUnidad + "$\n";
        } else {
            return "La información de este disco no está disponible\n";
        }

    }

}

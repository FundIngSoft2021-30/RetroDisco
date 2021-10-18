/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class DetalleOrden {
    private String idDisco;
    private String disco;
    private int unidades;
    private double precioUnidad;

    public DetalleOrden() {
        idDisco = "";
        disco = "";
        unidades = 0;
        precioUnidad = 0;
    }

    public DetalleOrden(String idDisco, String disco, int unidades, double precioUnidad) {
        this.idDisco = idDisco;
        this.disco = disco;
        this.unidades = unidades;
        this.precioUnidad = precioUnidad;
    }

    public String getIdDisco() {
        return idDisco;
    }

    public String getDisco() {
        return disco;
    }

    public int getUnidades() {
        return unidades;
    }

    public double getPrecioUnidad() {
        return precioUnidad;
    }

    public void setIdDisco(String idDisco) {
        this.idDisco = idDisco;
    }

    public void setDisco(String disco) {
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
            
            return this.disco + " - $" + this.precioUnidad+" - Cantidad: "+this.unidades;
        } else {
            return "La información de este disco no está disponible\n";
        }

    }

}

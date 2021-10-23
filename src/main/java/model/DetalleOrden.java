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
        return capitalizar(this.disco.toLowerCase()) + " - $" + this.precioUnidad+" - Cantidad: "+this.unidades;
    }

    public String capitalizar(String message){
        char[] charArray = message.toCharArray();
        boolean foundSpace = true;
        for(int i = 0; i < charArray.length; i++) {
            if(Character.isLetter(charArray[i])) {
                if(foundSpace) {
                    charArray[i] = Character.toUpperCase(charArray[i]);
                    foundSpace = false;
                }
            }
            else {
                foundSpace = true;
            }
        }
        message = String.valueOf(charArray);
        return message;
    }
}

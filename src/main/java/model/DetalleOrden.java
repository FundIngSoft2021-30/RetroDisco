/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Clase que contiene parte de una orden.
 */
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

    /**
     * Constructor de la clase {@code DetalleOrden}.
     * @param idDisco ID del disco.
     * @param disco Nombre del disco.
     * @param unidades Cantidad de unidades.
     * @param precioUnidad Precio por unidad.
     */
    public DetalleOrden(String idDisco, String disco, int unidades, double precioUnidad) {
        this.idDisco = idDisco;
        this.disco = disco;
        this.unidades = unidades;
        this.precioUnidad = precioUnidad;
    }

    /**
     * Devuelve el ID del disco.
     * @return ID del disco.
     */
    public String getIdDisco() {
        return idDisco;
    }

    /**
     * Devuelve el nombre del disco.
     * @return Nombre del disco.
     */
    public String getDisco() {
        return disco;
    }

    /**
     * Devuelve la cantidad de unidades.
     * @return Cantidad de unidades.
     */
    public int getUnidades() {
        return unidades;
    }

    /**
     * Devuelve el precio por unidad.
     * @return Precio por unidad.
     */
    public double getPrecioUnidad() {
        return precioUnidad;
    }

    /**
     * Establece el ID del disco.
     * @param idDisco ID del disco.
     */
    public void setIdDisco(String idDisco) {
        this.idDisco = idDisco;
    }

    /**
     * Establece el nombre del disco.
     * @param disco Nombre del disco.
     */
    public void setDisco(String disco) {
        this.disco = disco;
    }

    /**
     * Establece la cantidad de unidades.
     * @param unidades Cantidad de unidades.
     */
    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    /**
     * Establece el precio por unidad.
     * @param precioUnidad Precio por unidad.
     */
    public void setPrecioUnidad(double precioUnidad) {
        this.precioUnidad = precioUnidad;
    }

    /**
     * {@code String} con información del detalle de la orden.
     * @return Detalle de orden.
     */
    public String toString() {
        return capitalizar(this.disco.toLowerCase()) + " - $" + this.precioUnidad+" - Cantidad: "+this.unidades;
    }

    /**
     * Capitaliza la primera letra de cada palabra.
     * @param cadena Cadena a capitalizar.
     * @return Cadena capitalizada.
     */
    public String capitalizar(String cadena){
        char[] charArray = cadena.toCharArray();
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
        cadena = String.valueOf(charArray);
        return cadena;
    }
}

package model;

public class Disco {
    private String nombre;
    private String artista;
    private int publicacion;
    private String formato;
    private double precio;
    private int cantidad;
    private String vendedor;
    private String genero;
    
    public Disco(String nombre, String artista, int publicacion, String formato, double precio, int cantidad,
            String vendedor, String genero) {
        this.nombre = nombre;
        this.artista = artista;
        this.publicacion = publicacion;
        this.formato = formato;
        this.precio = precio;
        this.cantidad = cantidad;
        this.vendedor = vendedor;
        this.genero = genero;
    }

    public Disco()
    {
        nombre="";
        artista="";
        publicacion=0;
        formato="";
        precio=0;
        cantidad=0;
        vendedor="";
        genero="";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public int getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(int publicacion) {
        this.publicacion = publicacion;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }
    
    public String getGenero(){
        return genero;
    }

    public void setGenero(String genero){
        this.genero = genero;
    }
}

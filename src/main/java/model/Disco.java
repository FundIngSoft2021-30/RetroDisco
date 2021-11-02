package model;

/**
 * Clase que representa un disco.
 * @author SapoYRana
 */
public class Disco {
    private String id;
    private String nombre;
    private String artista;
    private int publicacion;
    private String formato;
    private double precio;
    private int cantidad;
    private String vendedor;
    private String genero;
    
    /**
     * Constructor de la clase Disco.
     * @param id Identificador del disco.
     * @param nombre Nombre del disco.
     * @param artista Nombre del artista.
     * @param publicacion Año de publicación.
     * @param formato Formato del disco.
     * @param precio Precio del disco.
     * @param cantidad Cantidad de unidades disponibles.
     * @param vendedor Nombre del vendedor.
     * @param genero Género del disco.
     * @return Un objeto de la clase Disco.
     */
    public Disco(String id,String nombre, String artista, int publicacion, String formato, double precio, int cantidad,
            String vendedor, String genero) {
        this.id = id;
        this.nombre = nombre;
        this.artista = artista;
        this.publicacion = publicacion;
        this.formato = formato;
        this.precio = precio;
        this.cantidad = cantidad;
        this.vendedor = vendedor;
        this.genero = genero;
    }

    /**
     * Constructor de un disco vacío.
     */
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

    /**
     * @return ID del disco.
     */
    public String getId() {
        return id;
    }

    /**
     * @param id ID del disco.
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * 
     * @return Nombre del disco.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre Nombre del disco.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return Artista del disco.
     */
    public String getArtista() {
        return artista;
    }

    /**
     * @param artista Artista del disco.
     */
    public void setArtista(String artista) {
        this.artista = artista;
    }

    /**
     * @return Año de publicación del disco.
     */
    public int getPublicacion() {
        return publicacion;
    }

    /**
     * @param publicacion Año de publicación del disco.
     */
    public void setPublicacion(int publicacion) {
        this.publicacion = publicacion;
    }

    /**
     * @return Formato del disco.
     */
    public String getFormato() {
        return formato;
    }

    /**
     * @param formato Formato del disco.
     */
    public void setFormato(String formato) {
        this.formato = formato;
    }

    /**
     * @return Precio del disco.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @param precio Precio del disco.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * @return Cantidad de unidades disponibles.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad Cantidad de unidades disponibles.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return Vendedor del disco.
     */
    public String getVendedor() {
        return vendedor;
    }

    /**
     * @param vendedor Vendedor del disco.
     */
    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }
    
    /**
     * @return Genero del disco.
     */
    public String getGenero(){
        return genero;
    }

    /**
     * @param genero Genero del disco.
     */
    public void setGenero(String genero){
        this.genero = genero;
    }

    /**
     * @return String con los datos del disco.
     */
    public String toString(){
        return capitalizar(this.artista.toLowerCase())+" - "+capitalizar(this.nombre.toLowerCase())+"["+this.formato+"]";
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

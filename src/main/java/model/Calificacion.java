package model;

/**
 * Clase que representa la calificación brindada a un disco.
 */
public class Calificacion {
    private String comentario;
    private Disco disco;
    private int nota;
    private Usuario usuario;
    
    /**
     * Constructor de la clase {@code Calificacion}.
     * @param comentario Comentario textual de la calificación.
     * @param disco {@code Disco} al que se le califica.
     * @param nota Calificación brindada al disco, va de 1 a 5.
     * @param usuario {@code Usuario} que otorga la calificación.
     */
    public Calificacion(String comentario, Disco disco, int nota, Usuario usuario) {
        this.comentario = comentario;
        this.disco = disco;
        this.nota = nota;
        this.usuario = usuario;
    }

    /**
     * Retorna el comentario textual de una calificación.
     * @return Comentario de la calificación.
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Establece o modifica el comentario textual de una calificación.
     * @param comentario
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * Retorna el {@code Disco} al que se le califica.
     * @return {@code Disco} calificado.
     */
    public Disco getDisco() {
        return disco;
    }

    /**
     * Establece o modifica el {@code Disco} al que se le califica.
     * @param disco
     */
    public void setDisco(Disco disco) {
        this.disco = disco;
    }

    /**
     * Retorna la calificación brindada al disco.
     * @return Calificación brindada al disco.
     */
    public int getNota() {
        return nota;
    }

    /**
     * Establece o modifica la calificación brindada al disco.
     * @param nota
     */
    public void setNota(int nota) {
        this.nota = nota;
    }

    /**
     * Retorna el {@code Usuario} que otorga la calificación.
     * @return {@code Usuario} que otorga la calificación.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece o modifica el {@code Usuario} que otorga la calificación.
     * @param usuario
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    } 
}

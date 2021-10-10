package model;

public class Calificacion {
    private String comentario;
    private Disco disco;
    private int nota;
    private Usuario usuario;
    
    public Calificacion(String comentario, Disco disco, int nota, Usuario usuario) {
        this.comentario = comentario;
        this.disco = disco;
        this.nota = nota;
        this.usuario = usuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Disco getDisco() {
        return disco;
    }

    public void setDisco(Disco disco) {
        this.disco = disco;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
}

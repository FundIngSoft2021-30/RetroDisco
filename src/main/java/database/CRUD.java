package database;

import model.*;
import java.util.*;
import java.util.concurrent.ExecutionException;

import com.google.cloud.firestore.WriteResult;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

public class CRUD {

    private static Firestore bd=null;

    public CRUD() {
        ConexionFB conexion = new ConexionFB();
        bd=conexion.iniciarFirebase();
    }

    //Agrega un usuario a la BD.
    public boolean agregarFB()
    {
        Map<String, Object> docUsuario = new HashMap<>();
        docUsuario.put("nombre", "Prueba");
        docUsuario.put("apellido", "Base");
        docUsuario.put("username", "test1");
        docUsuario.put("password", "genericpw");
        ApiFuture<WriteResult> future = bd.collection("Usuarios").document(UUID.randomUUID().toString()).set(docUsuario);
        try {
            System.out.println("Hora de update : " + future.get().getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            //e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean agregarUsuario(Usuario user)
    {
        Map<String, Object> docUsuario = new HashMap<>();
        docUsuario.put("nombre", user.getNombre());
        docUsuario.put("apellido", user.getApellido());
        docUsuario.put("username", user.getUsername());
        docUsuario.put("password", user.getPassword());
        ApiFuture<WriteResult> future = bd.collection("Usuarios").document(UUID.randomUUID().toString()).set(docUsuario);
        try {
            System.out.println("usuario agregado : " + future.get().getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            //e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean agregarDisco(Disco disc)
    {
        Map<String, Object> docDisco = new HashMap<>();
        docDisco.put("nombre", disc.getNombre());
        docDisco.put("artista", disc.getArtista());
        docDisco.put("publicacion", disc.getPublicacion());
        docDisco.put("formato", disc.getFormato());
        docDisco.put("precio", disc.getPrecio());
        docDisco.put("cantidad", disc.getCantidad());
        docDisco.put("vendedor", disc.getVendedor());
        ApiFuture<WriteResult> future = bd.collection("Discos").document(UUID.randomUUID().toString()).set(docDisco);
        try {
            System.out.println("Disco agregado : " + future.get().getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            //e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean agregarCalificacion(Calificacion review)
    {
        Map<String, Object> docCalificacion = new HashMap<>();
        docCalificacion.put("comentario", review.getComentario());
        docCalificacion.put("disco", review.getDisco().getNombre());
        docCalificacion.put("nota", review.getNota());
        docCalificacion.put("usuario", review.getUsuario().getUsername());
        ApiFuture<WriteResult> future = bd.collection("Calificaciones").document(UUID.randomUUID().toString()).set(docCalificacion);
        try {
            System.out.println("Calificacion agregada : " + future.get().getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            //e.printStackTrace();
            return false;
        }
        return true;
    }

    public void obtenerUnDisco(String documentoID) throws InterruptedException, ExecutionException
    {
        DocumentReference docRef = bd.collection("Discos").document(documentoID);
        // asynchronously retrieve the document
        ApiFuture<DocumentSnapshot> future = docRef.get();
        // ...
        // future.get() blocks on response
        DocumentSnapshot documento = future.get();
        if (documento.exists()) {
        System.out.println("Document data: " + documento.getData());
        } else {
        System.out.println("No such document!");
        }
    }

    public void obtenerColeccionDiscos()
    {
        //asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future = bd.collection("Discos").get();
        // future.get() blocks on response
        try{
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                System.out.println(document.getId() + " => " + document.toObject(Disco.class));
                obtenerUnDisco(document.getId());
            }
        } catch(ExecutionException | InterruptedException e){
            e.printStackTrace();
        }
    }

}

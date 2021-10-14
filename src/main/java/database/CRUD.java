package database;

import model.*;

import java.text.Normalizer;
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

    
    public static boolean agregarUsuario(Usuario user)
    {
        Map<String, Object> docUsuario = new HashMap<>();
        docUsuario.put("nombre", user.getNombre());
        docUsuario.put("apellido", user.getApellido());
        docUsuario.put("username", user.getUsername());
        docUsuario.put("password", user.getPassword());
        if(existeUsername(user.getUsername()))
        {
            return false;
        }
        ApiFuture<WriteResult> future = bd.collection("Usuarios").document(user.getUsername()).set(docUsuario);
        try {
            System.out.println("Usuarios Update : " + future.get().getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            //e.printStackTrace();
            return false;
        }
        return true;
    }



    public static boolean existeUsername(String username)
    {
        boolean existe = false;
        DocumentReference docRef = bd.collection("Usuarios").document(username);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        
        try {
            DocumentSnapshot document = future.get();
            if (document.exists()) {
                //System.out.println("El usuario existe ");
                existe = true;
              }
        } catch (InterruptedException | ExecutionException e1) {
            e1.printStackTrace();
        }
        return existe;
    }

    public static boolean autenticarPassword(String username, String password)
    {
        boolean retorno = false;

        DocumentReference docRef = bd.collection("Usuarios").document(username);
        ApiFuture<DocumentSnapshot> future = docRef.get();

        try {
            DocumentSnapshot document = future.get();
            if (document.exists()) {
                retorno =password.equals(document.getData().get("password"));                
              } else {
                System.out.println("El usuario no fue encontrado");
              }
        } catch (InterruptedException | ExecutionException e1) {
            e1.printStackTrace();
        }

        return retorno;
    }

    public static boolean agregarDisco(Disco disc)
    {
        Map<String, Object> docDisco = new HashMap<>();
        docDisco.put("nombre", disc.getNombre());
        docDisco.put("artista", disc.getArtista());
        docDisco.put("publicacion", disc.getPublicacion());
        docDisco.put("formato", disc.getFormato());
        docDisco.put("precio", disc.getPrecio());
        docDisco.put("cantidad", disc.getCantidad());
        docDisco.put("vendedor", disc.getVendedor());
        docDisco.put("genero", disc.getGenero());
        ApiFuture<WriteResult> future = bd.collection("Discos").document(UUID.randomUUID().toString()).set(docDisco);
        try {
            System.out.println("Discos update : " + future.get().getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            //e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static boolean agregarCalificacion(Calificacion review)
    {
        Map<String, Object> docCalificacion = new HashMap<>();
        docCalificacion.put("comentario", review.getComentario());
        docCalificacion.put("disco", review.getDisco().getNombre());
        docCalificacion.put("nota", review.getNota());
        docCalificacion.put("usuario", review.getUsuario().getUsername());
        ApiFuture<WriteResult> future = bd.collection("Calificaciones").document(UUID.randomUUID().toString()).set(docCalificacion);
        try {
            System.out.println("Calificaciones update : " + future.get().getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            //e.printStackTrace();
            return false;
        }
        return true;
    }

    public static Disco obtenerUnDisco(String documentoID)
    {
        Disco retorno = null;
        DocumentReference docRef = bd.collection("Discos").document(documentoID);
        // asynchronously retrieve the document
        ApiFuture<DocumentSnapshot> future = docRef.get();
        // ...
        // future.get() blocks on response
        DocumentSnapshot documento;
        try {
            documento = future.get();
            if (documento.exists()) {
                retorno = documento.toObject(Disco.class);
                //System.out.println("Document data: " + documento.getData());
            } else {
                System.out.println("No such document!");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return retorno;
        
    }

    public static Map<String, Disco> obtenerColeccionDiscos()
    {
        //asynchronously retrieve all documents
        Map<String, Disco> coleccionDiscos = new HashMap<String, Disco>();
        ApiFuture<QuerySnapshot> future = bd.collection("Discos").get();
        // future.get() blocks on response
        try{
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                //System.out.println(document.getId() + " => " + document.toObject(Disco.class));
                coleccionDiscos.put(document.getId(), document.toObject(Disco.class));
                obtenerUnDisco(document.getId());
            }
        } catch(ExecutionException | InterruptedException e){
            e.printStackTrace();
        }
        return coleccionDiscos;
    }

    
    public static String arreglarCadena(String cadena){
        cadena = Normalizer.normalize(cadena, Normalizer.Form.NFD);
        cadena = cadena.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        String retorno = cadena.toUpperCase();
        return retorno;
    }

    public static boolean contenidas(String A, String B)
    {
        boolean retorno = false;
        if(A.indexOf(B)!=-1){
            retorno = true;
        }else if(B.indexOf(A) != -1){
            retorno = true;
        }
        return retorno;
    }


    public static Set<String> compararStrMap(String termino, Map<String, String> mapa)
    {
        Set<String> Coincidencias = new HashSet<String>();
        Set<String> keys = mapa.keySet();
        String terminoArreglado, categoriaArreglada;
        terminoArreglado = arreglarCadena(termino);        
        for (String key : keys) {
            categoriaArreglada = arreglarCadena(mapa.get(key));
            if(terminoArreglado.equals(categoriaArreglada)==true)
            {
                if (Coincidencias.contains(key)==false){
                    Coincidencias.add(key);
                }                
            }
            else if(contenidas(terminoArreglado, categoriaArreglada)==true){
                if (Coincidencias.contains(key)==false){
                    Coincidencias.add(key);
                }
            }
        }
        return Coincidencias;       
    }


    public static Map<String, Disco> buscarDiscoCategoria(String terminoBusqueda, String categoria){
        Map<String, Disco> resultados = new HashMap<>();
        Set<String> idResultados = new HashSet<>();

        Map<String, Disco> coleccionDiscos = obtenerColeccionDiscos();
        Set<String> idDiscos = coleccionDiscos.keySet();

        Map<String, String> mapaComparar = new HashMap<String, String>();
        
        switch(categoria){
            case "nombre" :
                for (String key : idDiscos) {
                    mapaComparar.put(key, coleccionDiscos.get(key).getNombre());
                }
                break;
            case "artista" :
                for (String key : idDiscos) {
                    mapaComparar.put(key, coleccionDiscos.get(key).getArtista());
                }
                break;
            case "formato" :
                for (String key : idDiscos) {
                    mapaComparar.put(key, coleccionDiscos.get(key).getFormato());
                }
                break;
            case "vendedor" :
                for (String key : idDiscos) {
                    mapaComparar.put(key, coleccionDiscos.get(key).getVendedor());
                }
                break;
            case "genero" :
                for (String key : idDiscos) {
                    mapaComparar.put(key, coleccionDiscos.get(key).getGenero());
                }
                break;
            default: System.out.println("categoría invalida");
        }
        
        idResultados = compararStrMap(terminoBusqueda, mapaComparar);
        for (String id : idResultados) {
            resultados.put(id, coleccionDiscos.get(id));
        }

        return resultados;

    }

	public static Map<String, Disco> busquedaGeneral(String terminoBusqueda){
        Map<String, Disco> resultados = new HashMap<>();

        Map<String, Disco> resultadosNombre = buscarDiscoCategoria(terminoBusqueda, "nombre");
        Map<String, Disco> resultadosArtista = buscarDiscoCategoria(terminoBusqueda, "artista");
        Map<String, Disco> resultadosFormato = buscarDiscoCategoria(terminoBusqueda, "formato");
        Map<String, Disco> resultadosVendedor = buscarDiscoCategoria(terminoBusqueda, "vendedor");
        Map<String, Disco> resultadosGenero = buscarDiscoCategoria(terminoBusqueda, "genero");

        Set<String> idResultados = new HashSet<>();
        if(resultadosNombre.size()>0){
            idResultados = resultadosNombre.keySet();
            for (String id : idResultados) {
                if(resultados.containsKey(id)==false){
                    resultados.put(id, resultadosNombre.get(id));
                }
            }
        }

        if(resultadosArtista.size()>0){
            idResultados = resultadosArtista.keySet();
            for (String id : idResultados) {
                if(resultados.containsKey(id)==false){
                    resultados.put(id, resultadosArtista.get(id));
                }
            }
        }

        if(resultadosFormato.size()>0){
            idResultados = resultadosFormato.keySet();
            for (String id : idResultados) {
                if(resultados.containsKey(id)==false){
                    resultados.put(id, resultadosFormato.get(id));
                }
            }
        }

        if(resultadosVendedor.size()>0){
            idResultados = resultadosVendedor.keySet();
            for (String id : idResultados) {
                if(resultados.containsKey(id)==false){
                    resultados.put(id, resultadosVendedor.get(id));
                }
            }
        }

        if(resultadosGenero.size()>0){
            idResultados = resultadosGenero.keySet();
            for (String id : idResultados) {
                if(resultados.containsKey(id)==false){
                    resultados.put(id, resultadosGenero.get(id));
                }
            }
        }

        return resultados;
    }
    

}

package database;

import model.*;

import java.text.Normalizer;
import java.util.*;
import java.util.concurrent.ExecutionException;

import com.google.cloud.firestore.WriteResult;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    
    /*
     * * * * * * * METODOS USUARIO * * * * * * * * * * * * *
     */
    public static boolean agregarUsuario(Usuario user)
    {
        Map<String, Object> docUsuario = new HashMap<>();
        docUsuario.put("nombre", user.getNombre());
        docUsuario.put("apellido", user.getApellido());
        docUsuario.put("username", user.getUsername());
        docUsuario.put("password", user.getPassword());
        docUsuario.put("activo", user.isActivo());
        if(existeUsername(user.getUsername()))
        {
            return false;
        }
        ApiFuture<WriteResult> future = bd.collection("Usuarios").document(user.getUsername()).set(docUsuario);
        try {
            System.out.println("Usuarios Update : " + future.get().getUpdateTime());
            agregarCarrito(user.getUsername());
        } catch (InterruptedException | ExecutionException e) {
            //e.printStackTrace();
            return false;
        }
        return true;
    }

    public static Usuario obtenerUsuario(String username){
        DocumentReference docRef = bd.collection("Usuarios").document(username);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        
        Usuario usuario = null;
        
        try {
            DocumentSnapshot document = future.get();
            if (document.exists()) {
                //System.out.println("El usuario existe ");
                usuario = document.toObject(Usuario.class);
              }
        } catch (InterruptedException | ExecutionException e1) {
            e1.printStackTrace();
            return null;
        }
        
        return usuario;
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
    
    public static boolean desactivarUsuarioBD(String username){
        boolean r;
        DocumentReference docRef = bd.collection("Usuarios").document(username);
        ApiFuture<WriteResult> future = docRef.update("activo", false);
        
        try {
            WriteResult result = future.get();
            System.out.println("Write result: " + result);            
        } catch (InterruptedException |ExecutionException ex) {
            return false;
        }
        return true;
    }

    /*
    * * * * * * * METODOS DISCO * * * * * * * * * * *
    */
    public static boolean agregarDisco(Disco disc)
    {   
        Map<String, Object> docDisco = new HashMap<>();
        docDisco.put("id", disc.getId());
        docDisco.put("nombre", disc.getNombre());
        docDisco.put("artista", disc.getArtista());
        docDisco.put("publicacion", disc.getPublicacion());
        docDisco.put("formato", disc.getFormato());
        docDisco.put("precio", disc.getPrecio());
        docDisco.put("cantidad", disc.getCantidad());
        docDisco.put("vendedor", disc.getVendedor());
        docDisco.put("genero", disc.getGenero());
        
        ApiFuture<WriteResult> future = bd.collection("Discos").document(disc.getId()).set(docDisco);
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
        docCalificacion.put("disco", review.getDisco().getId());
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
    
    public static ArrayList<Disco> obtenerDiscosUsuario(String username){
        ArrayList<Disco> discos = new ArrayList<>();
        Query query = bd.collection("Discos").whereEqualTo("vendedor", username);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        try{
            List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
            for(QueryDocumentSnapshot d: documents){
                discos.add(obtenerUnDisco(d.getId()));
            }
        }
        catch(ExecutionException | InterruptedException e){
            e.printStackTrace();
            discos.clear();
        }
        
        return discos;        
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
    
        /*
    * * * * * * * METODOS ORDEN * * * * * * 
    */
    public static boolean agregarOrden(Orden orden){ 
        Map<String, Object> docOrden = new HashMap<>();
        docOrden.put("comprador", orden.getComprador().getUsername());
        docOrden.put("fecha", orden.getFecha());
        
        String idOrden = UUID.randomUUID().toString();
        ApiFuture<WriteResult> future = bd.collection("Ordenes").document(idOrden).set(docOrden);
        
        ArrayList<DetalleOrden> articulos = orden.getArticulos();
        for(int i = 0; i<articulos.size(); i++){
            DetalleOrden detalleActual = articulos.get(i);
            Map<String, Object> docDetalle = new HashMap<>();
            docDetalle.put("disco", detalleActual.getDisco().getId());
            docDetalle.put("cantidad", detalleActual.getUnidades());
            docDetalle.put("precioUnidad", detalleActual.getPrecioUnidad());
            bd.collection("Ordenes").document(idOrden).collection("detallesOrden").document("detalle_"+i).set(docDetalle);
        }
        
        try {
            System.out.println("Ordenes Update : " + future.get().getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            //e.printStackTrace();
            return false;
        }
        
        return true;
        
    }   
    
    public static void probarObtenerOrdenes(){
        ArrayList<Orden> ordenesJuanito = obtenerOrdenesUsuario("Juanito");
        if(ordenesJuanito.isEmpty()){
            System.out.println("No lee las ordenes de Juanito");
        }
        else{
            for(Orden o: ordenesJuanito){
                System.out.println(o.toString());
            }
        }
    }
    
    public static ArrayList<Orden> obtenerOrdenesUsuario(String username){
        Query query = bd.collection("Ordenes").whereEqualTo("comprador", username);
        ArrayList<Orden> ordenes = new ArrayList<>();
        
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        
        
        
        try {
            List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
            for(QueryDocumentSnapshot document: documents){
                Usuario u = obtenerUsuario(document.getData().get("comprador").toString());
                String f = document.getData().get("fecha").toString();
                
                ApiFuture<QuerySnapshot> future = bd.collection("Ordenes").document(document.getId()).collection("detallesOrden").get();
                ArrayList<DetalleOrden> detallesOrden = new ArrayList<>();
                List<QueryDocumentSnapshot> detalles = future.get().getDocuments();
                
                for(QueryDocumentSnapshot detalle: detalles){
                    Disco disco = obtenerUnDisco(detalle.getData().get("disco").toString());
                    String unidadesString = detalle.getData().get("cantidad").toString();
                    int unidades = Integer.parseInt(unidadesString) ;
                    String precioUString = detalle.getData().get("precioUnidad").toString();
                    double precioUnidad = Double.parseDouble(precioUString);
                    DetalleOrden detalleActual = new DetalleOrden(disco,unidades, precioUnidad);
                    detallesOrden.add(detalleActual);
                }
                
                
                Orden orden = new Orden();
                orden.setComprador(u);
                orden.setFecha(f);
                orden.setArticulos(detallesOrden);
                
                ordenes.add(orden);
                
            }
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ordenes;
    }
    
    /*
    * * * * * * METODOS CARRITO* * * * * * 
    */
    public static boolean agregarCarrito(String username){
        Map<String, Object> docData = new HashMap<>();
        docData.put("idCarrito", username);
        docData.put("vacio", true);
        ApiFuture<WriteResult> future = bd.collection("Carritos").document(username).set(docData);
        
        try {
            System.out.println("Update time : " + future.get().getUpdateTime());
        } catch (InterruptedException|ExecutionException ex) {
            Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public static Carrito obtenerCarrito(String username){
        Carrito carrito = null;
        DocumentReference docRef = bd.collection("Carritos").document(username);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        try {
            DocumentSnapshot document = future.get();
            if(document.exists()){            
                if(Boolean.parseBoolean(document.getData().get("vacio").toString())){
                    carrito = new Carrito();
                }else{
                    ApiFuture<QuerySnapshot> futureDetalles = bd.collection("Carritos").document(username).collection("detallesOrden").get();
                    ArrayList<DetalleOrden> detallesOrden = new ArrayList<>();
                    List<QueryDocumentSnapshot> detalles = futureDetalles.get().getDocuments();

                    for(QueryDocumentSnapshot detalle: detalles){
                        Disco disco = obtenerUnDisco(detalle.getData().get("disco").toString());
                        String unidadesString = detalle.getData().get("cantidad").toString();
                        int unidades = Integer.parseInt(unidadesString) ;
                        String precioUString = detalle.getData().get("precioUnidad").toString();
                        double precioUnidad = Double.parseDouble(precioUString);
                        DetalleOrden detalleActual = new DetalleOrden(disco,unidades, precioUnidad);
                        detallesOrden.add(detalleActual);
                    }

                    carrito = new Carrito();
                    carrito.setDiscos(detallesOrden);
                }

            }else{

                return null;
            }
        } catch (InterruptedException|ExecutionException ex) {
            Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        return null;
    }
    
    public static boolean actualizarCarrito(Carrito carrito, String username){
        Map<String, Object> docCarrito = new HashMap<>();
        docCarrito.put("idCarrito", username);
        boolean vacio = true;
        if(carrito.getDiscos().size()>0){
            vacio = false;
            docCarrito.put("vacio", false);
        }else{
            docCarrito.put("vacio", true);
        }
        ApiFuture<WriteResult> future = bd.collection("Carritos").document(username).set(docCarrito);
        if(!vacio){
            ArrayList<DetalleOrden> discos = carrito.getDiscos();
            for(int i = 0; i<discos.size(); i++){
                DetalleOrden detalleActual = discos.get(i);
                Map<String, Object> docDetalle = new HashMap<>();
                docDetalle.put("disco", detalleActual.getDisco().getId());
                docDetalle.put("cantidad", detalleActual.getUnidades());
                docDetalle.put("precioUnidad", detalleActual.getPrecioUnidad());
                bd.collection("Carritos").document(username).collection("detallesOrden").document("detalle_"+i).set(docDetalle);
            }
        }
        
        try {
            System.out.println("Carrito Update : " + future.get().getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            //e.printStackTrace();
            return false;
        }
        return true;
    }
}
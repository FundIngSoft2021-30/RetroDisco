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
    /**
     * Agrega un nuevo usuario a la base de datos
     * @param user llega un nuevo usuario
     * @return rectifica si fue añadido o no
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
    /**
     * Busca el username de un usuario
     * @param username nombre del usuario
     * @return un usuario
     */
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
    /**
     * Se asegura que exista el usuario
     * @param username nombre del usuario
     * @return si existe o no el usuario
     */
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
    /**
     * Se autentica que la contraseña sea la del usuario
     * @param username nombre del usuario
     * @param password contraseña del usuario
     * @return si la contraseña fue autenticada
     */
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
    /**
     * Desactiva al usuario de la base de datos
     * @param username nombre del usuario
     * @returnn si el usuario se desactivó o no
     */
    public static boolean desactivarUsuarioBD(String username){

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
    /**
     * Añade un nuevo disco a la base de datos
     * @param disc Un nuevo disco
     * @return Si fue añadido a la base de datos o no
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
    
    /**
     * Califica el disco
     * @param review Información de la calificación
     * @return Si se realizó o no la calificación
     */
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
    /**
     * Busca un disco 
     * @param documentoID Numero del disco que se desea buscar
     * @return El disco segun el ID
     */
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
    /**
     * Obtiene una colección de discos
     * @return Discos Encontrados
     */
    public static Map<String, Disco> obtenerColeccionDiscos()
    {
        //asynchronously retrieve all documents
        ArrayList<Disco> discos = new ArrayList<>();
        Map<String, Disco> coleccionDiscos = new HashMap<String, Disco>();
        ApiFuture<QuerySnapshot> future = bd.collection("Discos").get();
        // future.get() blocks on response
        try{
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                //System.out.println(document.getId() + " => " + document.toObject(Disco.class));
                discos.add(document.toObject(Disco.class));
                coleccionDiscos.put(document.getId(), document.toObject(Disco.class));
                obtenerUnDisco(document.getId());
            }
        } catch(ExecutionException | InterruptedException e){
            e.printStackTrace();
        }
        return coleccionDiscos;
    }
    /**
     * Obtiene una lista con los discos del usuario
     * @param username un usuario registrado
     * @return la lista con los discos 
     */
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

    /**
     * Arregla la cadena y pone todo en mayúsculas
     * @param cadena una cadena de caracteres cualquiera
     * @return la cadena arreglada
     */
    public static String arreglarCadena(String cadena){
        cadena = Normalizer.normalize(cadena, Normalizer.Form.NFD);
        cadena = cadena.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        String retorno = cadena.toUpperCase();
        return retorno;
    }
    /**
     * 
     * @param A
     * @param B
     * @return
     */
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

    /**
     * Se encarga de buscar la categoria del disco
     * @param terminoBusqueda Algún dato del disco, nombre, artista...
     * @param categoria Tipo de categoria 
     * @return Las categorias encontradas segun las encontradas
     * @throws InterruptedException Si se queda 'pensando' o si se detiene
     * @throws ExecutionException Se detiene si no devuelve el resultado
     */
    public static Map<String, Disco> buscarDiscoCategoria(String terminoBusqueda, String categoria) throws InterruptedException, ExecutionException{
               
        Map<String, Disco> resultados = new HashMap<>();
        CollectionReference discs = bd.collection("Discos");
        Query query;
        ApiFuture<QuerySnapshot> querySnapshot;
        switch(categoria){
            case "nombre" :
                query = discs.whereEqualTo("nombre", terminoBusqueda.toUpperCase());
                querySnapshot = query.get();
                for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
                    resultados.put(document.getId(), document.toObject(Disco.class));
                }
                break;
            case "artista" :
                query = discs.whereEqualTo("artista", terminoBusqueda.toUpperCase());
                querySnapshot = query.get();
                for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
                    resultados.put(document.getId(), document.toObject(Disco.class));
                }
                break;
            case "formato" :
                query = discs.whereEqualTo("formato", terminoBusqueda.toUpperCase());
                querySnapshot = query.get();
                for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
                    resultados.put(document.getId(), document.toObject(Disco.class));
                }
                break;
            case "vendedor" :
                query = discs.whereEqualTo("vendedor", terminoBusqueda);
                querySnapshot = query.get();
                for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
                    resultados.put(document.getId(), document.toObject(Disco.class));
                }
                break;
            case "genero" :
                query = discs.whereEqualTo("genero", terminoBusqueda.toUpperCase());
                querySnapshot = query.get();
                for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
                    resultados.put(document.getId(), document.toObject(Disco.class));
                }
                break;
        }
        return resultados;

    }
    /**
     * Hace una busqueda global en los discos con el parametro recibido
     * @param terminoBusqueda Alguna especificación que compone el disco
     * @return Mapa con la busqueda recibida
     * @throws InterruptedException Si se queda 'pensando' o si se detiene
     * @throws ExecutionExceptionse Detiene si no devuelve el resultado
     */
	public static Map<String, Disco> busquedaGeneral(String terminoBusqueda) throws InterruptedException, ExecutionException{
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
        
    public static void descontarDiscos(String disco, int cantidad){
        DocumentReference docRef = bd.collection("Discos").document(disco);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        try {
            DocumentSnapshot document = future.get();
            int cantidadDisco = Integer.parseInt(document.getData().get("cantidad").toString());
            cantidadDisco = cantidadDisco - cantidad;
            docRef.update("cantidad",cantidadDisco);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return;
        }
        
        
    }
    
        /*
    * * * * * * * METODOS ORDEN * * * * * * 
    */
    /**
     * Agrega el orden segun la fecha realizada por el comprador
     * @param orden Recibe una fecha o un comprador
     * @return Si se realizó la orden de manera correcta o no se realizó
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
            docDetalle.put("idDisco", detalleActual.getIdDisco());
            docDetalle.put("disco", detalleActual.getDisco());
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
    /**
     * Prueba si se estan llegando las ordenes del usuario
     */
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
    /**
     * Obtiene una lista con las ordenes de los usuarios
     * @param username Un usuario resgitrado 
     * @return una lista con las ordenes realizadas por el usuario
     */
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
                    String idDisco = detalle.getData().get("idDisco").toString();
                    String disco = detalle.getData().get("disco").toString();
                    String unidadesString = detalle.getData().get("cantidad").toString();
                    int unidades = Integer.parseInt(unidadesString) ;
                    String precioUString = detalle.getData().get("precioUnidad").toString();
                    double precioUnidad = Double.parseDouble(precioUString);
                    DetalleOrden detalleActual = new DetalleOrden(idDisco, disco,unidades, precioUnidad);
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
    /**
     * Añade al carrito los discos seleccionados por un usuario
     * @param username Un usuario registrado
     * @return devuelve si se añadió o no en el carrito el disco 
     */
    public static boolean agregarCarrito(String username){
        Map<String, Object> docData = new HashMap<>();
        ApiFuture<WriteResult> future = bd.collection("Carritos").document(username).set(docData);
        try {
            System.out.println("Carritos update : " + future.get().getUpdateTime());
        } catch (InterruptedException|ExecutionException ex) {
            Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    /**
     * Obtiene el carrito de un usuario 
     * @param username Un usuario registrado
     * @return Un carrito segun lo que haya elegido el usuario
     */
    public static Carrito obtenerCarrito(String username){
        Carrito carrito = new Carrito();
        DocumentReference docRef = bd.collection("Carritos").document(username);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        try {
            DocumentSnapshot document = future.get();
            if(document.exists()){            
                ApiFuture<QuerySnapshot> futureDetalles = bd.collection("Carritos").document(username).collection("detallesOrden").get();
                ArrayList<DetalleOrden> detallesOrden = new ArrayList<>();
                List<QueryDocumentSnapshot> detalles = futureDetalles.get().getDocuments();
                for(QueryDocumentSnapshot detalle: detalles){
                    String idDisco = detalle.getData().get("idDisco").toString();
                    String disco = detalle.getData().get("disco").toString();
                    String unidadesString = detalle.getData().get("cantidad").toString();
                    int unidades = Integer.parseInt(unidadesString) ;
                    String precioUString = detalle.getData().get("precioUnidad").toString();
                    double precioUnidad = Double.parseDouble(precioUString);
                    DetalleOrden detalleActual = new DetalleOrden(idDisco,disco,unidades, precioUnidad);
                    detallesOrden.add(detalleActual);
                }
                carrito.setDiscos(detallesOrden);
            }else{
                return carrito;
            }
        } catch (InterruptedException|ExecutionException ex) {
            Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return carrito;
    }
    /**
     * Se hace la actualización del contenido del carrito
     * @param carrito valores del carrito
     * @param username Usuario registrado
     * @param actual valores del carrito antes de ser actualizado
     * @return informa si fue actualizado o no el carrito
     */
    public static boolean actualizarCarrito(Carrito carrito, String username, Carrito actual){
        vaciarCarrito(username, actual);
        Map<String, Object> docCarrito = new HashMap<>();
        ApiFuture<WriteResult> future = bd.collection("Carritos").document(username).set(docCarrito);
        ArrayList<DetalleOrden> discos = carrito.getDiscos();
        for(int i = 0; i<discos.size(); i++){
            DetalleOrden detalleActual = discos.get(i);
            Map<String, Object> docDetalle = new HashMap<>();
            docDetalle.put("idDisco", detalleActual.getIdDisco());
            docDetalle.put("disco", detalleActual.getDisco());
            docDetalle.put("cantidad", detalleActual.getUnidades());
            docDetalle.put("precioUnidad", detalleActual.getPrecioUnidad());
            ApiFuture<WriteResult> apifdetalle = bd.collection("Carritos").document(username).collection("detallesOrden").document("detalle_"+i).set(docDetalle);
            try{
                System.out.println("Detalle agregado : "+apifdetalle.get().getUpdateTime());
            }catch(InterruptedException | ExecutionException e) {
                
                System.out.println("No se pudo ingresar doc detalle_"+i);
            }
        }
        try {
            System.out.println("Carritos Update : " + future.get().getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            //e.printStackTrace();
            return false;
        }
        return true;
    }
    /**
     * Borra un disco que esta contenida en el carrito 
     * @param username Usuario al que esta asignado el carrito
     * @param idDisco ID del disco que se desea eliminar
     */
    public static void borrarDelCarrito(String username, String idDisco){
        ApiFuture<DocumentSnapshot> future = bd.collection("Carritos").document(username).get();
        String detalleId;
        try {
            DocumentSnapshot document = future.get();
            if(document.exists()){            
                ApiFuture<QuerySnapshot> futureDetalles = bd.collection("Carritos").document(username).collection("detallesOrden").get();
                List<QueryDocumentSnapshot> detalles = futureDetalles.get().getDocuments();
                for(QueryDocumentSnapshot detalle: detalles){
                    if(idDisco.equals(detalle.getData().get("idDisco").toString())){
                        detalleId = detalle.getId();
                        bd.collection("Carritos").document(username).collection("detallesOrden").document(detalleId).delete();
                    }                    
                }
                
                
            }
            else{
                return;
            }
        } catch (InterruptedException|ExecutionException ex) {
            Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
    }
    /**
     * Elimina el contenido que esta en el carrito
     * @param username Usario relacionado con el carrito
     * @param carrito informacion del carrito que se desea eliminar
     */
    public static void vaciarCarrito(String username,Carrito carrito) {
        ApiFuture<DocumentSnapshot> future = bd.collection("Carritos").document(username).get();
        
        try {
            DocumentSnapshot document = future.get();
            if(document.exists()){            
                ApiFuture<QuerySnapshot> futureDetalles = bd.collection("Carritos").document(username).collection("detallesOrden").get();
                List<QueryDocumentSnapshot> detalles = futureDetalles.get().getDocuments();
                for(QueryDocumentSnapshot detalle: detalles){
                    bd.collection("Carritos").document(username).collection("detallesOrden").document(detalle.getId()).delete();                  
                }
            }else{
                return;
            } 
        } catch (InterruptedException|ExecutionException ex) {
            Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
    }
}
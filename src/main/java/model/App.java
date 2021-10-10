package model;

import database.CRUD;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //Crea la conexión a la base de datos.
        CRUD bd=new CRUD();
        /*boolean agregado=bd.agregarFB();
        if(agregado)
        {
            System.out.println("Se agregó el usuario correctamente.");
        }*/
        bd.obtenerColeccionDiscos();
    }
}

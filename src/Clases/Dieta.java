package Clases;

import Clases.Padres.Archivos;
import Persistencia.DietaDAO;
import java.util.ArrayList;

/**
 * @author keiss
 */
public class Dieta extends Archivos{

    public Dieta() {}

    public Dieta(int id, String nombre, String archivo, int rutaArchivo, String ruta) {
        super(id, nombre, archivo, rutaArchivo, ruta);
    }

    public Dieta(int id, String nombre, String archivo, int rutaArchivo) {
        super(id, nombre, archivo, rutaArchivo);
    }

    public Dieta(String nombre, String archivo, int rutaArchivo) {
        super(nombre, archivo, rutaArchivo);
    }
    /*Metodos*/
    @Override
    public int ingresar(){
        return new DietaDAO().ingresarDieta(this);
    }

    public ArrayList<Dieta> buscarDietas(boolean tipoBusqueda,String nombre){
        return new DietaDAO().buscarDietas(tipoBusqueda, nombre);
    }
    
    public Dieta obtenerDieta(){
        return new DietaDAO().obtenerDieta(this);
    }
    
    @Override
    public int modificar(){
        return new DietaDAO().modificarDieta(this);
    }
    
    @Override
    public int borrar(){
        return new DietaDAO().borrarDieta(this);
    }
}

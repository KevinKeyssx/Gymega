package Clases;

import Clases.Padres.Archivos;
import Persistencia.RutinaDAO;
import java.util.ArrayList;

/**
 * @author keiss
 */
public class Rutina extends Archivos{

    public Rutina() {}

    public Rutina(int id, String nombre, String archivo, int rutaArchivo, String ruta) {
        super(id, nombre, archivo, rutaArchivo, ruta);
    }

    public Rutina(String nombre, String archivo, int rutaArchivo) {
        super(nombre, archivo, rutaArchivo);
    }
    /*Metodos*/
    @Override
    public int ingresar(){
        return new RutinaDAO().ingresarRutina(this);
    }
    
    @Override
    public int modificar(){
        return new RutinaDAO().modificarRutina(this);
    }

    @Override
    public int borrar() {
        return new RutinaDAO().eliminarRutina(this); 
    }
    
    public ArrayList<Rutina> buscarRutinas(boolean tipoBusqueda,String nombre){
        return new RutinaDAO().buscarRutinas(tipoBusqueda, nombre);
    }
    
    public Rutina obtenerRutina(){
        return new RutinaDAO().obtenerRutina(this);
    }
}

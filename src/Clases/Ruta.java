package Clases;

import Clases.Padres.RutaArchivo;
import Persistencia.RutaDAO;

/**
 * @author keiss
 */
public class Ruta extends RutaArchivo{

    public Ruta() {}

    public Ruta(int idArchivo, String rutaArchivo) {
        super(idArchivo, rutaArchivo);
    }

    public Ruta(String rutaArchivo) {
        super(rutaArchivo);
    }

    public Ruta obtenerRuta(){
        RutaDAO ruta = new RutaDAO();
        return ruta.obtenerRuta(this);
    }
}

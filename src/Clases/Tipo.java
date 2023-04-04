package Clases;

import Persistencia.TipoDAO;
import java.util.ArrayList;

/**
 * @author keiss
 */
public class Tipo {
    
    private int id;
    private String nombre;
    private int monto;
    
    public Tipo() {}

    public Tipo(int id, String nombre, int monto) {
        this.id = id;
        this.nombre = nombre;
        this.monto = monto;
    }

    public Tipo(String nombre, Integer monto) {
        this.nombre = nombre;
        this.monto = monto;
    }

    public Tipo(int id) {
        this.id = id;
    }

    public Tipo(String nombre) {
        this.nombre = nombre;
    }

    public Tipo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    public boolean ingresarTipo(){
        return new TipoDAO().ingresarTipo(this);
    }
    
    public boolean modificar() {
        return new TipoDAO().modificarTipo(this);
    }
    
    public int eliminarTipo() {
        return new TipoDAO().eliminarTipo(this);
    }
    
    public Tipo obtenerTipo(){
        return new TipoDAO().obtenerTipo(this);
    }
    
    public int obtenerIdMaximo() {
        return new TipoDAO().obtenerIdMaximo();
    }
    
    public ArrayList<Tipo> buscarTipo(boolean busqueda, String dato) {
        return new TipoDAO().buscarTipo(busqueda, dato);
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

}

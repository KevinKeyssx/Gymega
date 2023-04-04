package Clases;

import Persistencia.GrupoDAO;
import java.util.ArrayList;

/**
 * @author KevinKeyss
 */
public class Grupo {
    private int id;
    private String nombre;

    public Grupo() {
    }

    public Grupo(int id) {
        this.id = id;
    }

    public Grupo(String nombre) {
        this.nombre = nombre;
    }

    public Grupo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int ingresarGrupo(){
        return new GrupoDAO().ingresarGrupo();
    }
    
    public int modificarGrupo() {
        return new GrupoDAO().modificarGrupo(this);
    }
    
    public int eliminarGrupo() {
        return new GrupoDAO().eliminarGrupo(this);
    }
    
    public ArrayList<Grupo> buscarGrupos(boolean busqueda) {
        return new GrupoDAO().buscarGrupos(this, busqueda);
    }
    
    public Grupo obtenerGrupo(){
        return new GrupoDAO().obtenerGrupo(this);
    }
    
    public int obtenerPrecioGrupo(){
        return new GrupoDAO().obtenerPrecioGrupo(this);
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




 
}

package Clases;

import Persistencia.FisicoDAO;
import java.util.ArrayList;

/**
 * @author keiss
 */
public class Fisico {
    private int id;
    private String fisico;

    public Fisico() {}
        
    public Fisico(int id) {
        this.id = id;
    }
    
    public Fisico(String nombre){
        this.fisico = nombre;
    }

    public Fisico(int id, String fisico) {
        this.id = id;
        this.fisico = fisico;
    }
    
    /*METODOS*/
    public boolean ingresarFisico(int idAlumno, String llamado){
        return new FisicoDAO().ingresarFisico(this, idAlumno, llamado);
    }
    
    public boolean modificarFisico(int id){
        return new FisicoDAO().modificarFisico(this, id);
    }
    
    public boolean eliminar(String name, int idAlumno) {
        return new FisicoDAO().eliminarFisico(name, this, idAlumno);
    }
    
    public ArrayList<Fisico> obtenerFisico(boolean busqueda, String llamado, int id){
        return new FisicoDAO().obtenerFisico(busqueda, llamado, id);
    }
    
    public Fisico buscarFisico(String llamado){
        return new FisicoDAO().buscarFisico(this, llamado);
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFisico() {
        return fisico;
    }

    public void setFisico(String fisico) {
        this.fisico = fisico;
    }


    
}

package Clases;

import Persistencia.RecursoDAO;
import java.util.ArrayList;

/**
 * @author KevinKeyss
 */
public class Recurso {
    private int idRecurso;
    private Alumno alumno;
    private Grupo grupo;

    public Recurso() {}

    public Recurso(Grupo grupo, Alumno alumno) {
        this.alumno = alumno;
        this.grupo = grupo;
    }

    public Recurso(Alumno alumno) {
        this.alumno = alumno;
    }

    public Recurso(Grupo grupo) {
        this.grupo = grupo;
    }
    
    public int getIdRecurso() {
        return idRecurso;
    }

    public int ingresarRecurso(){
        return new RecursoDAO().ingresarRecurso(this);
    }
    
    public int eliminarRecurso(){
        return new RecursoDAO().eliminarRecurso(this);
    }
    
    public ArrayList<Recurso> buscarRecursos() {
        return new RecursoDAO().buscarRecursos(this);
    }
    
    public int obtenerProcRecurso(String proc, String grupo){
        return new RecursoDAO().obtenerProcRecurso(proc, grupo);
    }
    
    public void setIdRecurso(int idRecurso) {
        this.idRecurso = idRecurso;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
    
    
}

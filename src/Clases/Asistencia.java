package Clases;

import Persistencia.AsistenciaDAO;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author KevinKeyss
 */
public class Asistencia {
    private int idAsistencia;
    private Alumno alumno;
    private Date fecha;
    private String hora;
    private String descripcion;

    public Asistencia(Alumno alumno) {
        this.alumno = alumno;
    }

    public Asistencia() {}

    public Asistencia(int idAsistencia, Alumno alumno, Date fecha, String hora, String descripcion) {
        this.idAsistencia = idAsistencia;
        this.alumno = alumno;
        this.fecha = fecha;
        this.hora = hora;
        this.descripcion = descripcion;
    }

    public int getIdAsistencia() {
        return idAsistencia;
    }
    
    public int ingresarAsistencia(){
        return new AsistenciaDAO().ingresarAlumno(this);
    }

    public ArrayList<Asistencia> buscarAsistencias(boolean todo){
        return new AsistenciaDAO().buscarAsistencias(todo);
    }
    

    
    public void setIdAsistencia(int idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

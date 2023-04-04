package Clases;

import Persistencia.HistorialAsistenciaDAO;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author KevinKeyss
 */
public class HistorialAsistencia {
    private int semana;
    private int lunes;
    private int martes;
    private int miercoles;
    private int jueves;
    private int viernes;
    private int sabado;
    private int domigo;
    
    private Date fechaInicio;
    private int diasRestantes;
    private Date fechaTermino;
    private int montoPago; 

    public HistorialAsistencia(int semana, int lunes, int martes, int miercoles, int jueves, int viernes, int sabado, int domigo) {
        this.semana = semana;
        this.lunes = lunes;
        this.martes = martes;
        this.miercoles = miercoles;
        this.jueves = jueves;
        this.viernes = viernes;
        this.sabado = sabado;
        this.domigo = domigo;
    }

    public HistorialAsistencia(Date fechaInicio, int diasRestantes, Date fechaTermino, int montoPago) {
        this.fechaInicio = fechaInicio;
        this.diasRestantes = diasRestantes;
        this.fechaTermino = fechaTermino;
        this.montoPago = montoPago;
    }

    public HistorialAsistencia() {}
    
    public ArrayList<HistorialAsistencia> asistenciasAlumno(int idAlumno, int mes, int año){
        return new HistorialAsistenciaDAO().asistenciasAlumno(idAlumno, mes, año);
    }
    
    public HistorialAsistencia detalleGrupo(int idAlumno, String nombreGrupo){
        return new HistorialAsistenciaDAO().detalleGrupo(idAlumno, nombreGrupo);
    }

    public int getSemana() {
        return semana;
    }

    public void setSemana(int semana) {
        this.semana = semana;
    }

    public int getLunes() {
        return lunes;
    }

    public void setLunes(int lunes) {
        this.lunes = lunes;
    }

    public int getMartes() {
        return martes;
    }

    public void setMartes(int martes) {
        this.martes = martes;
    }

    public int getMiercoles() {
        return miercoles;
    }

    public void setMiercoles(int miercoles) {
        this.miercoles = miercoles;
    }

    public int getJueves() {
        return jueves;
    }

    public void setJueves(int jueves) {
        this.jueves = jueves;
    }

    public int getViernes() {
        return viernes;
    }

    public void setViernes(int viernes) {
        this.viernes = viernes;
    }

    public int getSabado() {
        return sabado;
    }

    public void setSabado(int sabado) {
        this.sabado = sabado;
    }

    public int getDomigo() {
        return domigo;
    }

    public void setDomigo(int domigo) {
        this.domigo = domigo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public int getDiasRestantes() {
        return diasRestantes;
    }

    public void setDiasRestantes(int diasRestantes) {
        this.diasRestantes = diasRestantes;
    }

    public Date getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(Date fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public int getMontoPago() {
        return montoPago;
    }

    public void setMontoPago(int montoPago) {
        this.montoPago = montoPago;
    }

}

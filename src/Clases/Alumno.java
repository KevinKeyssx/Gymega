package Clases;

import Clases.Padres.Persona;
import Persistencia.AlumnoDAO;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author keiss
 */
public class Alumno extends Persona{
    private double estatura;
    private double peso;
    private String nombreEmergencia;
    private String telefonoEmergencia;
    private String observacion;
    private int idPersonalizado;

    public Alumno() {}

    public Alumno(int id) {
        super(id);
    }

    public Alumno(int idPersonalizado, int id) {
        super(id);
        this.idPersonalizado = idPersonalizado;
    }

    public Alumno(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Alumno(double estatura, double peso, String nombreEmergencia, String telefonoEmergencia, String observacion, int idPersonalizado, int id, String rut, int ruta, String foto, String nombre, String apellidoP, String apellidoM, String direccion, String correo, String fono, Date fechaNacimiento, String tipo, String extension) {
        super(id, rut, ruta, foto, nombre, apellidoP, apellidoM, direccion, correo, fono, fechaNacimiento, tipo, extension);
        this.estatura = estatura;
        this.peso = peso;
        this.nombreEmergencia = nombreEmergencia;
        this.telefonoEmergencia = telefonoEmergencia;
        this.observacion = observacion;
        this.idPersonalizado = idPersonalizado;
    }

    public Alumno(String rut) {
        this.rut = rut;
    }
    
    public boolean modificarPersonalizado(){
        return new AlumnoDAO().modificarPersonalizado(this);
    }
    
    @Override
    public int ingresar(){
        return new AlumnoDAO().IngresarAlumno(this);
    }

    @Override
    public int modificar() {
        return new AlumnoDAO().ModificarAlumno(this);
    }

    public ArrayList<Alumno> buscarAlumnos(boolean busqueda, String dato, boolean huella){
        return new AlumnoDAO().buscarAlumnos(busqueda, dato, huella);
    }
    
    public Alumno obtenerAlumno(){
        return new AlumnoDAO().obtenerAlumno(this);
    }
    
    public Alumno obtenerIdAlumno(){
        return new AlumnoDAO().buscarIdAlumno(this);
    }

    public double getEstatura() {
        return estatura;
    }

    public void setEstatura(double estatura) {
        this.estatura = estatura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getNombreEmergencia() {
        return nombreEmergencia;
    }

    public void setNombreEmergencia(String nombreEmergencia) {
        this.nombreEmergencia = nombreEmergencia;
    }

    public String getTelefonoEmergencia() {
        return telefonoEmergencia;
    }

    public void setTelefonoEmergencia(String telefonoEmergencia) {
        this.telefonoEmergencia = telefonoEmergencia;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getIdPersonalizado() {
        return idPersonalizado;
    }

    public void setIdPersonalizado(int idPersonalizado) {
        this.idPersonalizado = idPersonalizado;
    }
    

}

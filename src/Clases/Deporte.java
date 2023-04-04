package Clases;

import java.util.ArrayList;

/**
 * @author KevinKeyss
 */
public class Deporte {
    private int id;
    private int idAlumno;
    private String establecimiento;
    private String direccion;
    private int idDeporte;
    private String deporte;
    private String nivel;
    private String tiempo;
    private int cantidad;
    
    /*METODOS*/
    public boolean ingresarDeporte(boolean ingreso){
        return new DeporteDAO().ingresarDeporte(this, ingreso);
    }

    public boolean modificarDeporte(){
        return new DeporteDAO().modificarDeporte(this);
    }
    
    public boolean eliminarDeporte(){
        return new DeporteDAO().eliminarDeporte(this);
    }
    
    public Deporte obtenerDeporte(boolean busqueda){
        return new DeporteDAO().obtenerDeporte(busqueda, this);
    }
    public ArrayList<Deporte> buscarDeporte(boolean busqueda){
        return new DeporteDAO().buscarDeporte(busqueda, this);
    }
    
    public ArrayList<Deporte> buscarDeportes(int id) {
        return new DeporteDAO().buscarDeportes(id);
    }
    
    /*CONSTRUCTOR*/
    public Deporte() {}

    public Deporte(String deporte) {
        this.deporte = deporte;
    }

    public Deporte(int idAlumno) {
        this.idAlumno = idAlumno;
    }
    
    public Deporte(int idDeporte, int idAlumno){
        this.idDeporte = idDeporte;
        this.idAlumno = idAlumno;
    }

    public Deporte(int idDeporte, String deporte) {
        this.idDeporte = idDeporte;
        this.deporte = deporte;
    }

    public Deporte(int idAlumno, int idDeporte, String establecimiento, String direccion, String nivel, String tiempo, int cantidad) {
        this.idAlumno = idAlumno;
        this.idDeporte = idDeporte;
        this.establecimiento = establecimiento;
        this.direccion = direccion;
        this.nivel = nivel;
        this.tiempo = tiempo;
        this.cantidad = cantidad;
    }

    public Deporte(int id, String deporte, String establecimiento, String direccion, String nivel, String tiempo, int cantidad) {
        this.id = id;
        this.deporte = deporte;
        this.establecimiento = establecimiento;
        this.direccion = direccion;
        this.nivel = nivel;
        this.tiempo = tiempo;
        this.cantidad = cantidad;
    }

    /*GETTER AND SETTER*/
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(String establecimiento) {
        this.establecimiento = establecimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getIdDeporte() {
        return idDeporte;
    }

    public void setIdDeporte(int idDeporte) {
        this.idDeporte = idDeporte;
    }

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}

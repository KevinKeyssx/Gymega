package Clases.Padres;

import java.util.Date;

/**
 * @author keiss
 */
public class Persona {
    protected int id;
    protected String rut;
    protected int ruta;
    protected String foto;
    protected String nombre;
    protected String apellidoP;
    protected String apellidoM;
    protected String direccion;
    protected String correo;
    protected String fono;
    protected java.util.Date fechaNacimiento;
    protected String tipo;
    protected String extension;

    public Persona() {}

    public Persona(int id) {
        this.id = id;
    }

    public Persona(int id, String rut, int ruta, String foto, String nombre, String apellidoP, String apellidoM, String direccion, String correo, String fono, Date fechaNacimiento, String tipo, String extension) {
        this.id = id;
        this.rut = rut;
        this.ruta = ruta;
        this.foto = foto;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.direccion = direccion;
        this.correo = correo;
        this.fono = fono;
        this.fechaNacimiento = fechaNacimiento;
        this.tipo = tipo;
        this.extension = extension;
    }

    public Persona(String rut) {
        this.rut = rut;
    }

    /*Métodos*/
    public int ingresar(){
        return 0;
    }
    public int modificar(){
       return 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRuta() {
        return ruta;
    }

    public void setRuta(int ruta) {
        this.ruta = ruta;
    }
    
    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFono() {
        return fono;
    }

    public void setFono(String fono) {
        this.fono = fono;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }   

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
    
}

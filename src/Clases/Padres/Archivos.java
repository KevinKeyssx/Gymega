package Clases.Padres;

/**
 * @author keiss
 */
public class Archivos {
    protected int id;
    protected String nombre;
    protected String archivo;
    protected int rutaArchivo;
    protected String ruta;

    /*Constructores*/
    public Archivos(){}

    public Archivos(int id, String nombre, String archivo, int rutaArchivo, String ruta) {
        this.id = id;
        this.nombre = nombre;
        this.archivo = archivo;
        this.rutaArchivo = rutaArchivo;
        this.ruta = ruta;
    }

    public Archivos(String nombre, String archivo, int rutaArchivo) {
        this.nombre = nombre;
        this.archivo = archivo;
        this.rutaArchivo = rutaArchivo;
    }

    public Archivos(int id, String nombre, String archivo, int rutaArchivo) {
        this.id = id;
        this.nombre = nombre;
        this.archivo = archivo;
        this.rutaArchivo = rutaArchivo;
    }
    
    /*Métodos*/
    public int ingresar(){
        return 0;
    }
    public int modificar(){
       return 0;
    }
    public int borrar(){
        return 0;
    }
    /*Getter and Setter*/
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

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }
    
    public int getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(int rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
}

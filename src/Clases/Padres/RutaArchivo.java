package Clases.Padres;

/**
 * @author keiss
 */
public class RutaArchivo {
    
    protected int idArchivo;
    protected String rutaArchivo;

    public RutaArchivo() {}

    public RutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public RutaArchivo(int idArchivo, String rutaArchivo) {
        this.idArchivo = idArchivo;
        this.rutaArchivo = rutaArchivo;
    }
    
    /*Métodos*/
    public boolean ingresar(){
        return false;
    }
    
    public boolean modificar(){
       return false;
    }
    
    public String obterRuta(){
       return "";
    }

    public int getIdArchivo() {
        return idArchivo;
    }

    public void setIdArchivo(int idArchivo) {
        this.idArchivo = idArchivo;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }
    
}

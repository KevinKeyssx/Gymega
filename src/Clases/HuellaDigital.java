package Clases;

import Persistencia.HuellaDigitalDAO;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import java.awt.HeadlessException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * @author keiss
 */
public class HuellaDigital {
    
    private int id;
    private int idUser;
    private String nombre;
    private Object huella;
    private Integer tamañoHuella;

    public HuellaDigital() {}

    public HuellaDigital(int idUser) {
        this.idUser = idUser;
    }

    public HuellaDigital(int idUser, String nombre) {
        this.idUser = idUser;
        this.nombre = nombre;
    }

    public HuellaDigital(int id, int idUser, String nombre, Object huella) {
        this.id = id;
        this.idUser = idUser;
        this.nombre = nombre;
        this.huella = huella;
    }
    
    /*Metodos*/
    public int ingresar(String tipo){
        return new HuellaDigitalDAO().ingresarHuellaDigital(this, tipo);
    }
    
    public int eliminarHuellaDigital(String tipo){
        return new HuellaDigitalDAO().eliminarHuellaDigital(this, tipo);
    }
    
    public ArrayList<HuellaDigital> obtenerHuellaDigital (String tipo){
        return new HuellaDigitalDAO().obtenerHuellaDigital(tipo);
    }
    
    public ArrayList<HuellaDigital> buscarHuellaDigital (String tipo){
        return new HuellaDigitalDAO().buscarHuellaDigital(this, tipo);
    }
    
    public HuellaDigital(int id, int idUser, String nombre, Object huella, Integer tamañoHuella) {
        this.id = id;
        this.idUser = idUser;
        this.nombre = nombre;
        this.huella = huella;
        this.tamañoHuella = tamañoHuella;
    }
    
    public void Start(DPFPCapture Lector) {
        Lector.startCapture(); 
    }    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Object getHuella() {
        return huella;
    }

    public void setHuella(Object huella) {
        this.huella = huella;
    }

    public Integer getTamañoHuella() {
        return tamañoHuella;
    }

    public void setTamañoHuella(Integer tamañoHuella) {
        this.tamañoHuella = tamañoHuella;
    }
 
}

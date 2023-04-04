package Clases;

import Persistencia.IniciarSesionDAO;

/**
 * @author KevinKeyss
 */
public class IniciarSesion {
    private String usuario;
    private String contraseña;
    private boolean recordar;

    public IniciarSesion() {
    }

    public IniciarSesion(String usuario, String contraseña, boolean recordar) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.recordar = recordar;
    }
    
    public int iniciarSesion(){
        return new IniciarSesionDAO().iniciarSesion(this);
    }
    
    public IniciarSesion obtenerIniciarSesion(){
        return new IniciarSesionDAO().obtenerIniciarSesion();
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public boolean isRecordar() {
        return recordar;
    }

    public void setRecordar(boolean recordar) {
        this.recordar = recordar;
    }
}

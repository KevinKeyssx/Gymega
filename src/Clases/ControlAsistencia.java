package Clases;

import Persistencia.ControlAsistenciaDAO;
import java.util.Date;

/**
 * @author KevinKeyss
 */
public class ControlAsistencia {
    private int idControlAsistencia;
    private int idpago;
    private Date fechaAsistencia;
    private boolean asistencia;

    public ControlAsistencia(int idpago, Date fechaAsistencia, boolean asistencia) {
        this.idpago = idpago;
        this.fechaAsistencia = fechaAsistencia;
        this.asistencia = asistencia;
    }

    public ControlAsistencia(int idControlAsistencia) {
        this.idControlAsistencia = idControlAsistencia;
    }

    public ControlAsistencia() {}
    
    public int ingresarAsistencia(){
        return new ControlAsistenciaDAO().ingresarAsistencia(this);
    }

    public int getIdControlAsistencia() {
        return idControlAsistencia;
    }

    public void setIdControlAsistencia(int idControlAsistencia) {
        this.idControlAsistencia = idControlAsistencia;
    }

    public int getIdpago() {
        return idpago;
    }

    public void setIdpago(int idpago) {
        this.idpago = idpago;
    }

    public Date getFechaAsistencia() {
        return fechaAsistencia;
    }

    public void setFechaAsistencia(Date fechaAsistencia) {
        this.fechaAsistencia = fechaAsistencia;
    }

    public boolean isAsistencia() {
        return asistencia;
    }

    public void setAsistencia(boolean asistencia) {
        this.asistencia = asistencia;
    }
}

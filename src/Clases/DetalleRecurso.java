package Clases;
import Persistencia.DetalleRecursoDAO;
import java.util.ArrayList;
/**
 * @author KevinKeyss
 */
public class DetalleRecurso {
    private int idDetalle;
    private Grupo grupo;
    private Tipo tipo;

    public DetalleRecurso() {}

    public DetalleRecurso(Tipo tipo) {
        this.tipo = tipo;
    }

    public DetalleRecurso(Grupo grupo) {
        this.grupo = grupo;
    }

    public DetalleRecurso(Grupo grupo, Tipo tipo) {
        this.grupo = grupo;
        this.tipo = tipo;
    }

    public DetalleRecurso(int idDetalle, Grupo grupo, Tipo tipo) {
        this.idDetalle = idDetalle;
        this.grupo = grupo;
        this.tipo = tipo;
    }
    
    public int ingresarDetalleRecurso(){
        return new DetalleRecursoDAO().ingresarDetalleRecurso(this);
    }
    
    public int elminarDetalleRecurso() {
        return new DetalleRecursoDAO().elminarDetalleRecurso(this);
    }
    
    public ArrayList<DetalleRecurso> buscarDetalleRecurso() {
        return new DetalleRecursoDAO().buscarDetalleRecurso(this);
    }
    
    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }


}

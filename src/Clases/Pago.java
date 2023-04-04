package Clases;

import Persistencia.PagoDAO;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author keiss
 */
public class Pago {
    private int idPago;
    private Alumno alumno;
    private Grupo grupo;
    private int agregado;
    private int meses;
    private int descuento;
    private boolean porcentaje;
    private Date fechaInicio;
    private Date fechaTermino;
    private String formaPago;
    private int cantidad;
    private int total;
    private boolean todos;
    
    public Pago(Alumno alumno) {
        this.alumno = alumno;
    }

    public Pago() {}

    public Pago(int idPago) {
        this.idPago = idPago;
    }

    public Pago(Alumno alumno, Grupo grupo, int agregado, int meses, int descuento, boolean porcentaje, Date fechaInicio, Date fechaTermino, String formaPago, int cantidad, int total, boolean todos) {
        this.alumno = alumno;
        this.grupo = grupo;
        this.agregado = agregado;
        this.meses = meses;
        this.descuento = descuento;
        this.porcentaje = porcentaje;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
        this.formaPago = formaPago;
        this.cantidad = cantidad;
        this.total = total;
        this.todos = todos;
    }

    public Pago(int idPago, Grupo grupo, int meses, int cantidad, int total, Date fechaInicio, Date fechaTermino, String formaPago, int agregado, int descuento, boolean porcentaje) {
        this.idPago = idPago;
        this.grupo = grupo;
        this.meses = meses;
        this.cantidad = cantidad;
        this.total = total;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
        this.formaPago = formaPago;
        this.agregado = agregado;
        this.descuento = descuento;
        this.porcentaje = porcentaje;
    }
    
    public int ingresarPago(){
        return new PagoDAO().ingresarPago(this);
    }
    
    public int eliminarPago(){
        return new PagoDAO().eliminarPago(this);
    }
    
    public ArrayList<Pago> buscarPagos(){
        return new PagoDAO().buscarPagos(this);
    }
    
    public int obtenerUltimoId(){
        return new PagoDAO().obtenerUltimoId();
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public int getAgregado() {
        return agregado;
    }

    public void setAgregado(int agregado) {
        this.agregado = agregado;
    }

    public int getMeses() {
        return meses;
    }

    public void setMeses(int meses) {
        this.meses = meses;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public boolean isPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(boolean porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(Date fechaTermino) {
        this.fechaTermino = fechaTermino;
    }
    
    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isTodos() {
        return todos;
    }

    public void setTodos(boolean todos) {
        this.todos = todos;
    }
    
}

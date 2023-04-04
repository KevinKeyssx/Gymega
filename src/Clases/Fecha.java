package Clases;

import Extra.Validacion;
import Persistencia.FechaDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * @author KevinKeyss
 */
public class Fecha {
    private boolean diaLaborable;
    private String nombreDia;
    private Date feriado;
    private ArrayList<Fecha> listaFeriados;
    private ArrayList<Date> listaClases = new ArrayList<>();
    
    private int meses;
    private String fechaPago;
    private int feriados = 0;
    
    public Fecha(Date feriado) {
        this.feriado = feriado;
    }

    public Fecha(String nombreDia, boolean diaLaborable) {
        this.nombreDia = nombreDia;
        this.diaLaborable = diaLaborable;
    }

    public Fecha(int meses, String fechaPago) {
        this.meses = meses;
        this.fechaPago = fechaPago;
    }

    public Fecha() {}
    
    public int ingresarFeriado(){
        return new FechaDAO().ingresarFeriado(this);
    }
    
    public int eliminarFeriado() {
        return new FechaDAO().eliminarFeriado(this);
    }
    
    public boolean modificarDiasLaborables() {
        return new FechaDAO().modificarDiasLaborables(this);
    }
    
    public ArrayList<Fecha> buscarDiasLaborables() {
        return new FechaDAO().buscarDiasLaborables();
    }

    public ArrayList<Fecha> obtenerDiaFeriado(){
        return new FechaDAO().obtenerDiasFeriados();
    }

    private boolean isFeriado(Date fechaRecorrido){
        for (int i = 0; i < listaFeriados.size(); i++) {
            if (listaFeriados.get(i).getFeriado().equals(fechaRecorrido)) {
                listaFeriados.remove(i);//REMOVEMOS EL FERIADO QUE YA PASO
                feriados++;
                return true;
            }
        }
        
        return false;
    }
    
    public String getClases(){
        Calendar fecha = Calendar.getInstance();
        Date fechaInicio = formatoFecha(this.fechaPago);
        listaFeriados = obtenerDiaFeriado(); //Cargamos los dias feriados
        ArrayList<Fecha> dias = buscarDiasLaborables();

        int clases=0;
        Date fechaFinal = null;
        if (fechaPago != null){
            fecha.setTime(fechaInicio);
            //Comenzar a contar desde los dias que se paganm
            int mes = (fechaInicio.getMonth() + 1), año = fechaInicio.getYear(),
            numDias = fecha.getActualMaximum(Calendar.DAY_OF_MONTH),
            diaInicio = fechaInicio.getDate(), diaPago = diaInicio;
            //RECORREMOS LOS MESES PAGADOS
            for (int i = 0; i <= meses; i++) {
                //SETIAMOS PARA OBTENER LA INFORMACION DE LA FECHA
                fecha.set(obtenerAño(mes, fechaInicio.getYear()), obtenerMes(mes), 0);
                //OBTENEMOS LA CANTIDAD DE DIAS DEL MES
                numDias = fecha.getActualMaximum(Calendar.DAY_OF_MONTH);
                //SI LLEGAMOS AL ULTIMO MES DE PAGO, EL DIA FINAL SERA EL PRIMER DIA DEL PAGO, PARA OBTENER UN MES COMPLETO.
                if (i == meses) {
                    //VERIFICAMOS SI EL DIA DE PAGO ES UN 31, TENEMOS QUE VALIDAR SI EL PROXIMO MES ES 30 PARA QUE CUENTE UN DIA MAS
                    if (diaPago == 31 && numDias == 30) {
                        numDias = 30;
                    }else
                        numDias = diaPago;
                }
                //RECORREMOS LOS DIAS HASTA EL ULTIMO DIA DEL MES
                for (diaInicio = diaInicio; diaInicio <= numDias; diaInicio++) {
                    //CREAMOS LA NUEVA FECHA CON DIAS MES Y AÑOS
                    fechaInicio = formatoFecha((diaInicio) + "/" + obtenerMes(mes) + "/" + obtenerAño(mes, getAño(año)));
                    fecha.setTime(fechaInicio);
                    
                    //OBTENEMOS EL VALOR GUARDADO Y VALIDAMOS SI ES LABORABLE PARA CONTAR UN DIA LABORABLE 
                    if (dias.get((fecha.get(Calendar.DAY_OF_WEEK) - 1)).isDiaLaborable()) {
                        clases++; //Suamamos las clases
                        fechaFinal = fechaInicio; //Guardamos la ultima fecha real para luego enviarlo a ver si hay feriados
                        isFeriado(fechaInicio); //Sumamos los feriados
                        
                        listaClases.add(fechaFinal);//Guardamo las fechas que representaran la clases en la que puede asistir
                    }
                }
                //REINICIAMOS PARA CONTAR DESDE EL DIA 1 DEL PROXIMO MES
                if (diaInicio == numDias + 1) {
                    diaInicio = 1;
                }
                
                mes++;//Sumamos los meses
            }
        }
        //CONCATENAMOS CON UNA COMA , PARA OBTENERLO CON SPLIT LUEGO
        return (clases) + "," + getFechaFinal(fechaFinal, feriados);
    }
    
    private String getFechaFinal(Date ultimaFecha, int feriados){
        Date fechaFinal = ultimaFecha;
        this.feriados = 0;
        if (feriados > 0) {
            ArrayList<Fecha> dias = buscarDiasLaborables();
            Calendar fecha = Calendar.getInstance();
            listaFeriados = obtenerDiaFeriado(); //Cargamos los dias feriados
            int diaInicio = ultimaFecha.getDate(), mes = (ultimaFecha.getMonth() + 1), año = ultimaFecha.getYear();
            //VERIFICAMOS SI LA FECHA TERMINA EN UN 30 O 31 TENEMOS QUE COMENZAR DESDE EL PROXIMO MES
            if (diaInicio >= 30) {
                diaInicio = 1;
                mes++;
            }
            else diaInicio++;//SI NO CONTINUAMOS NORMAlMENTE DESDE EL SIGUIENTE DIA
           //RECORRER X VECES MAS POR LOS FERIADOS QUE SE ENCONTRARON
            while (feriados > 0) {
                ultimaFecha = formatoFecha(diaInicio + "/" + obtenerMes(mes) + "/" + obtenerAño(mes, getAño(año)));//CREAMO LA NUEVA FECHA
                fecha.setTime(ultimaFecha);

                if (dias.get((fecha.get(Calendar.DAY_OF_WEEK) - 1)).isDiaLaborable() /*&& !isFeriado(ultimaFecha)*/) {
                    fechaFinal = ultimaFecha; //Guardamos la ultima fecha
                    feriados--;
                    isFeriado(fechaFinal);//VOLVEMOS A PREGUNTAR SI ESTA FECHA ES FERIADO D:!!
                    listaClases.add(fechaFinal);//Guardamo las fechas que representaran la clases en la que puede asistir
                }

                diaInicio++;
            }
        }
        
        if (this.feriados > 0) { //SI HAY FERIADOS TENEMOS QUE VOLVER A LLAMAR AL METODO D:!!
            getFechaFinal(ultimaFecha, this.feriados);
        }
        
        return fechaFinal.getDate() + "-" + (fechaFinal.getMonth() + 1) + "-" + getAño(fechaFinal.getYear()) ;
    }    
    
    public Date formatoFecha(String fecha) {     
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
        } 
        catch (ParseException ex) {
            System.out.println("Ha ocurrido un error con la siguiente fecha:\n" + ex.toString());
            return null;
        }
    }
    
    private int obtenerMes(int mes){
        return mes%12 == 0? 12:mes%12;
    }
    
    private int obtenerAño(int mes,int año){
        return obtenerMes(mes)==12? año--:año + (mes/12);
    }

    public int getAño(int year) {
        return year + 1900;
    }
    
    /*GETTER AND SETTER*/
    public int getMeses() {
        return meses;
    }

    public void setMeses(int meses) {
        this.meses = meses;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public boolean isDiaLaborable() {
        return diaLaborable;
    }

    public void setDiaLaborable(boolean diaLaborable) {
        this.diaLaborable = diaLaborable;
    }

    public String getNombreDia() {
        return nombreDia;
    }

    public void setNombreDia(String nombreDia) {
        this.nombreDia = nombreDia;
    }

    public Date getFeriado() {
        return feriado;
    }

    public void setFeriado(Date feriado) {
        this.feriado = feriado;
    }

    public ArrayList<Date> getListaClases() {
        return listaClases;
    }

    public void setListaClases(ArrayList<Date> listaClases) {
        this.listaClases = listaClases;
    }

    
}
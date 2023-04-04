package Clases;

import Clases.Padres.Persona;
import Persistencia.PersonalTrainingDAO;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author keiss
 */
public class PersonalTraining extends Persona{
    private String pin;
    

    public PersonalTraining() {
    }

    public PersonalTraining(int id) {
        super(id);
    }

    public PersonalTraining(String rut) {
        super(rut);
    }

    public PersonalTraining(String pin, int id, String rut, int ruta, String foto, String nombre, String apellidoP, String apellidoM, String direccion, String correo, String fono, Date fechaNacimiento, String tipo, String extension) {
        super(id, rut, ruta, foto, nombre, apellidoP, apellidoM, direccion, correo, fono, fechaNacimiento, tipo, extension);
        this.pin = pin;
    }
    
    /*Metodos*/
    @Override
    public int ingresar(){
        return new PersonalTrainingDAO().ingresarPersonalTraining(this);
    }
    
    @Override
    public int modificar(){
        return new PersonalTrainingDAO().modificarPersonalTraining(this);
    }
    
    public int modificarPin(String pinNuevo) {
        return new PersonalTrainingDAO().modificarPin(this, pinNuevo);
    }
    
    public ArrayList<PersonalTraining> buscarPersonalTraining(boolean tipoBusqueda, String dato, boolean huella){
        return new PersonalTrainingDAO().buscarPersonalTraining(tipoBusqueda, dato, huella);
    }
    
    public PersonalTraining obtenerPersonalTraining(){
        return new PersonalTrainingDAO().obtenerPersonalTraining(this);
    }
    
    public PersonalTraining obtenerPersonalTrainingID(){
        return new PersonalTrainingDAO().obtenerPersonalTrainingID(this);
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

}

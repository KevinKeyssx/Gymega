package Clases;

import Persistencia.PersonalizadoDAO;

/**
 * @author KevinKeyss
 */
public class Personalizado {
    private int id;
    private String sexo;
    private double tricepts;
    private double subescapular;
    private double biceps;
    private double supraespinal;
    private double abdominal;
    private double muslofrontal;
    private double pantorrilla;

    public Personalizado() {}

    public Personalizado(int id, String sexo, double tricepts, double subescapular, double biceps, double supraespinal, double abdominal, double muslofrontal, double pantorrilla) {
        this.id = id;
        this.sexo = sexo;
        this.tricepts = tricepts;
        this.subescapular = subescapular;
        this.biceps = biceps;
        this.supraespinal = supraespinal;
        this.abdominal = abdominal;
        this.muslofrontal = muslofrontal;
        this.pantorrilla = pantorrilla;
    }
    
    public boolean gestionarPersonalizado(){
        return new PersonalizadoDAO().gestionarPersonalizado(this);
    }
    
    public Personalizado obtenerPersonalizado(int idAlumno){
        return new PersonalizadoDAO().obtenerPersonalizado(idAlumno);
    }
    
    public int obtenerPersonalizado(){
        return new PersonalizadoDAO().obtenerPersonalizado();
    }
    
    public int isPersonalizado(int idAlumno){
        return new PersonalizadoDAO().isPersonalizado(idAlumno);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public double getTricepts() {
        return tricepts;
    }

    public void setTricepts(double tricepts) {
        this.tricepts = tricepts;
    }

    public double getSubescapular() {
        return subescapular;
    }

    public void setSubescapular(double subescapular) {
        this.subescapular = subescapular;
    }

    public double getBiceps() {
        return biceps;
    }

    public void setBiceps(double biceps) {
        this.biceps = biceps;
    }

    public double getSupraespinal() {
        return supraespinal;
    }

    public void setSupraespinal(double supraespinal) {
        this.supraespinal = supraespinal;
    }

    public double getAbdominal() {
        return abdominal;
    }

    public void setAbdominal(double abdominal) {
        this.abdominal = abdominal;
    }

    public double getMuslofrontal() {
        return muslofrontal;
    }

    public void setMuslofrontal(double muslofrontal) {
        this.muslofrontal = muslofrontal;
    }

    public double getPantorrilla() {
        return pantorrilla;
    }

    public void setPantorrilla(double pantorrilla) {
        this.pantorrilla = pantorrilla;
    }

}

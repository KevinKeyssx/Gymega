package Extra;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;

/**
 * @author keiss
 */
public class Validacion {
    
    public boolean maximoLargo(String cadena, int max){
        return cadena.length() < max;
    }
    
    public boolean formatoCorreo(String correo){
        Pattern pat = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
        Matcher mat = pat.matcher(correo);
        
        return mat.find();
    }
    
    public boolean pin (String pin1, String pin2){
        return (pin1 == null ? pin2 != null : !pin1.equals(pin2));
    }
    
    public boolean rut (String rut){
        boolean aux = false;

        for (int i = 0; i < rut.length(); i++) {
            if (rut.charAt(i) == '-') {
                aux = true;
                break; 
            }
        }
        
        if (!aux) 
            return false;
        
        String RUT="";
        
        //Obtener el DV
        char dv = rut.charAt(rut.length()-1);
        //Obtener rut antes del guión
        int i = 0;
        while (rut.charAt(i) != '-') {
            RUT = RUT+rut.charAt(i);//Coonstruye el rut
            i++;
        }
        
        rut = RUT;
        int DV=0;
        
        String digi = ""+dv;

        try{
            DV =Integer.parseInt(digi);
        }
        catch (NumberFormatException ex){
            if (dv == 'k' || dv == 'K')
                DV = 10;
        }

        int cont;
        int contar = 2;
        int acumulador = 0;
        int divisor;
        int dig;
        
        int Rut;
        try{
            Rut = Integer.parseInt(rut);
        }
        catch(NumberFormatException e){
            return false;
        }
        
        do{
            cont = ((Rut % 10) * contar);
            acumulador = acumulador + cont;
            Rut = (Rut / 10);
            contar++;
            if (contar == 8)
                contar = 2;
        } while (Rut != 0);

        divisor = acumulador % 11;

        if (divisor == 0)
            divisor = 11;

        dig = 11 - divisor;

        return dig == DV;
    }
    
    public Color textoColorVacio(String texto){
        return texto.isEmpty()==true? Color.RED : Color.GREEN;
    }
    
    public Color textoColorVacio(double valor){
        return valor == 0? Color.RED : Color.GREEN;
    }
    
    public boolean validarFecha(JDateChooser fecha){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat(fecha.getDateFormatString());
            fecha.setBorder(BorderFactory.createLineBorder(textoColorVacio(String.valueOf(sdf.format(fecha.getDate()))), 1));
            return true;
        }
        catch (Exception e) {
            fecha.setBorder(BorderFactory.createLineBorder(textoColorVacio(""), 1));
            new Obtener().reproducirSonido("Denegado");
            return false;
        }
    }
    
    public Date DateValueOf(String intentoFecha){
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
        try {
           return formatoDelTexto.parse(intentoFecha);
        } catch (ParseException ex) {
            return null;
        }
    }
    
    public String formatoEspañol(Date fecha) {
        return new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("ES")).format(fecha);
   }

    public boolean rut(TextPlaceholder txt_Rut) {
        if (rut(new Obtener().reemplazo(txt_Rut.getText(), ".") )) {
            txt_Rut.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));
            return true;
        }
        else{
            txt_Rut.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
            return false;
        }
    }
    
    public boolean correo(TextPlaceholder txt_Correo) {
        if (formatoCorreo(txt_Correo.getText())) {
            txt_Correo.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));
            return true;
        }
        else{
            txt_Correo.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
            return false;
        }
    }
    
    public String horaAmPm(String hora){
        try {
            int hh = Integer.valueOf(hora.substring(0, 2));
            return hh <= 12  && hh >= 0 ? hora + " AM" : hora + " PM"; 
        } catch (NumberFormatException e) {
            return hora;
        }
    }
}

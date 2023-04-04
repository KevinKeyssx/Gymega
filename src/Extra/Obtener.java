package Extra;

import Interfaz.VistaDyR;
import com.bolivia.label.CLabel;
import java.awt.HeadlessException;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Date;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.codec.binary.Base64;
import rojerusan.RSTableMetro;

/**
 * @author keiss
 */
public class Obtener {
    
    public String fechaActual(String separador){
        Calendar cal = Calendar.getInstance();
        return (cal.get(Calendar.DATE)+separador+(cal.get(Calendar.MONTH) + 1 )+separador+cal.get(Calendar.YEAR));
    }
    
    public String añoActual(){
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR) + "";
    }
    /*USAMOS ESTE METODO PARA CONCATENAR EL PUNTO A LOS DIGITOS ANTES DEL DV Y GUION*/
    public String rutReal(String rut){
        String RUT = rut.substring(0, rut.length() - 2);
        String dv = rut.substring(rut.length() - 2, rut.length());
        return concatenaCaracter(RUT, 3, '.') + dv;
    }
    /*METODO PARA QUE EL USUARIO PUEDA ESCRIBIR SU RUT*/
    public String escribirRut(String rut , TextPlaceholder txt_Rut){
        String reemplazos = reemplazo(reemplazo(rut, "."), "-");

        if (rut.length() == 11) {
            String newRut = reemplazo(txt_Rut.getText(), ".");
            newRut = newRut.substring(0, 8) + "-" + newRut.substring(8, 9);
            txt_Rut.setText(newRut);
            new Validacion().rut(txt_Rut);
        }

        return reemplazo(reemplazo(rut, "."), "-").length() == 9 ? concatenaCaracter(reemplazo(rut, ".").substring(0, 8), 3, '.') + "-" + reemplazos.substring(8, 9) : concatenaCaracter(reemplazos, 3, '.');
    }
    
    public String concatenaCaracter (String cadena, int largo, char caracter){
        String totalreal = "";

        if (cadena.length() > largo) {          
            int suma = 0, pts = 0;
            //Recorre cada latra de atrás hacia adelante
            for (int i = cadena.length() - 1; i >=0; i--) {
                //Crea la nueva palabra
                totalreal = cadena.charAt(i) + totalreal;
                //Contador de puntos
                suma++;
                //Compara los largos para no imprimir el caracter al principio
                if (totalreal.length() - pts != cadena.length()) {
                    //Imprime los caracteres
                    if (suma == largo) {
                        totalreal = caracter + totalreal; //Agrega el caracter
                        suma = 0; //Reinicia la suma
                        pts++; //Suma el punto para luego comparar
                    }//Llave if compara largo
                }//Llave if compara largos
            }//Llave for
            
            return totalreal;
        }
        else
            return cadena;
    }
    
    public String enteroReal (double entero){
        String numero = String.valueOf(entero), palabra="", palabra2="";
        int i = numero.length()-1; //Obtenemos el largo
        
        while (numero.charAt(i) != '.') { //Recorremos de reversa mientras no haya un "."
            palabra = numero.charAt(i) + palabra; //Creamos la palabra hasta antes del "."
            i--; //Restamos seguir recorriendo en reversa
        }
        
        if (palabra.equals("0")) { //Si la palabra teminó en "0"
            i = 0; //Reiniciamos el contador para recorredor normalmente
            
            while (numero.charAt(i) != '.') { //Recorremos normalmente
                palabra2 = palabra2 + numero.charAt(i); //Creamos la nueva palabra hasta antes del "."
                i++; //Sumamos para recorremos normalmente
            }
            numero = palabra2; //Igualamos para retornar
        }
        return numero; //Retornamos
    }
    
    public String reemplazo(String cadena, String reemplazar) {
        try {
            Pattern p = Pattern.compile("["+reemplazar+"]");
            Matcher m = p.matcher(cadena);

            if(m.find())
                return m.replaceAll("");
            else
                return cadena;
        } catch (Exception e) {
            return cadena;
        }
    }
    
    public String extensionArchivo(String archivo){
        int i = archivo.length() - 1;
        String ext = "";
        
        while (archivo.charAt(i) != '.') {
            ext = archivo.charAt(i) + ext;
            i--; 
        }
        
        return ext;
    }
    
    public String extensionFoto(String extension){
        switch (extension.toLowerCase()) {
            case "doc":
            case "docx":
            case "docm":
            case "dotx":
                return "WORD.png";
  
            case "xlsx":
            case "xls":
            case "xlsm":
            case "xlsb":
                return "EXCEL.png";
            
            case "jpg":
            case "jpeg":
                return "JPG.png";
                
            case "pdf":
                return "PDF.png";
                
            case "txt":
                return "TXT.png"; 
                
            default:
                return "UNKNOWN.png";
        }
    }
    
    public void abrirArchivo(String ruta) {
        ProcessBuilder p = new ProcessBuilder();
        p.command("cmd.exe","/c", ruta);
        try {
            p.start();          
        } catch (IOException ex) {
            Logger.getLogger(VistaDyR.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void reproducirSonido(String sonido) {
        java.applet.Applet.newAudioClip(getClass().getResource("/Sonidos/" + sonido + ".wav")).play();
    }

    public String cantidadConcatenar(char concatenar,int i, String string) {
        String palabra = string;
        
        if (string.length() > i) 
            return string;

        for (int j = 0; j < (i - string.length()); j++) 
            palabra = concatenar + palabra;
        
        return palabra;
    }
    
    public int valorEntero(String valor){
        try {
            return valor.isEmpty() || valor == null ? 0 : Integer.valueOf(valor);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    
    public double valorDecimal(String valor){
        return valor.isEmpty() || valor == null ? 0 : Double.valueOf(valor);
    }
    
    public int calculaEdad(Date nacimiento){ 
        if (nacimiento == null || nacimiento.equals("")) 
            return 0;
        else {
            System.out.println("Fecha: " + nacimiento);
            LocalDate fechaNac = LocalDate.parse(String.valueOf(nacimiento));//Cargamos la fecha de nacimiento
            return Period.between(fechaNac, LocalDate.now()).getYears(); //Retornamos la diferencia entre las fechas
        }
    }

    public void remueveFilas(DefaultTableModel modelo, JTable table) {
        for (int i = table.getRowCount() -1; i >= 0; i--)
            modelo.removeRow(i); 
    }

    public void moverColumnaTabla(RSTableMetro tabla, boolean b) {
        tabla.getTableHeader().setReorderingAllowed(b) ;
    }

    public void nuevoIngreso(int TIPO, String nombre) {
        switch (TIPO) {
            case 1:
                JOptionPane.showMessageDialog(null, nombre + " creado correctamente :)", "Información :D", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 2:
                JOptionPane.showMessageDialog(null, nombre + " ya se encuentra en estre grupo, favor selecciona otro! :(", "Precaución D:", JOptionPane.WARNING_MESSAGE);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Ocurrió un error en la creación :(\nIntenta crear el grupo más tarde.", "Error inesperado!", JOptionPane.ERROR_MESSAGE);
            break;
        }
    }

    public void modificar(int tipo, String nombre) {
        switch (tipo) {
            case 1:
                JOptionPane.showMessageDialog(null, nombre + " modificado correctamente :)", "Información :D", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "El nombre de éste " + nombre + " ya existe, favor ingresa otro! :(", "Precaución D:", JOptionPane.WARNING_MESSAGE);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Ocurrió un error en la modificación :(\nIntenta crear el grupo más tarde.", "Error inesperado!", JOptionPane.ERROR_MESSAGE);
            break;
        }
    }
    
    public void cargarFoto(String ruta, CLabel lbl_Foto) {
        ImageIcon imagen = new ImageIcon(ruta); 
        lbl_Foto.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(lbl_Foto.getWidth(),lbl_Foto.getHeight(),Image.SCALE_DEFAULT)));
    }

    public boolean generarArchivo(String ruta, String direccion, File archivo){
        try {
            new File(System.getProperty("user.dir") + "\\" + ruta).mkdir(); //Creamos la carpeta si no existe
            try {
                new File(direccion).delete();
                Path origen = Paths.get(archivo.getAbsolutePath());//Guardamos la ruta original del archivo
                Path destino = Paths.get(direccion);//Copiamos el nunevo archivo en la nueva ruta
                Files.copy(origen, destino); //Copia el archivo
                return true;
            } 
            catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Ocurrió un error al tratar de crear el archivo! :(\n" + e.getMessage(),"Error! x.x",JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al tratar de crear el archivo! :(\n" + e.getMessage(),"Error! x.x",JOptionPane.ERROR_MESSAGE);
            return false;
        }        
    }
    
    
    public boolean isMatch(String input){
        return input.matches("[a-zA-Z0-9*-+/._, ]");
    }
}

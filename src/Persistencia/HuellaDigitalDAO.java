package Persistencia;

import Clases.HuellaDigital;
import Conexion.Conexion;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * @author keiss
 */
public class HuellaDigitalDAO {

    private Conexion con = null;

    public HuellaDigitalDAO() {
        con = new Conexion();
    }
    
    public int ingresarHuellaDigital(HuellaDigital hd, String tipo){
        return ejecutaProc(hd, "{call ingresarHuellaDigital" + tipo + "(?, ?, ?, ?)}", true);
    }
    
    public int eliminarHuellaDigital(HuellaDigital hd, String tipo) {
        return ejecutaProc(hd, "{call eliminarHuellaDigital" + tipo + "(?, ?, ?)}", false);
    }
    
    private int ejecutaProc(HuellaDigital hd, String procedimiento, boolean ingreso){
        try{
            CallableStatement cst = con.getCon().prepareCall(procedimiento);
            int salida;
            cst.setInt(1, hd.getIdUser());
            cst.setString(2, hd.getNombre());
            
            if (ingreso) {//si es un ingreso pasamos la huella digital
                cst.setBinaryStream(3, (InputStream) hd.getHuella(),hd.getTamañoHuella());//registramos la huella digital
                cst.registerOutParameter(4, Types.INTEGER); //Respuesta
                salida = 4;
            }
            else{//De lo contrario es una eliminacion
                cst.registerOutParameter(3, Types.INTEGER); //Respuesta
                salida = 3;
            }
            
            cst.execute();

            return cst.getInt(salida);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar ingresar la Huella Digital! :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return 0;
        }finally{
            con.closeConnection();
        }
    }

    public ArrayList<HuellaDigital> obtenerHuellaDigital(String tipo) {
        ArrayList<HuellaDigital> huellaDigital = new ArrayList<>();
        try{
            CallableStatement cst = con.getCon().prepareCall("{call obtenerHuellaDigital" + tipo + "()}");
            cst.execute();
            final ResultSet rs = cst.getResultSet();
            
            try {
                while(rs.next()){
                    HuellaDigital huella = new HuellaDigital();
                    huella.setId(rs.getInt(1));
                    huella.setIdUser(rs.getInt(2));
                    huella.setNombre(rs.getString(3));
                    huella.setHuella(rs.getBytes(4));

                    huellaDigital.add(huella);
                }
            } catch (SQLException e) {}

            return huellaDigital;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar buscar la dieta :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return null;
        }finally{
            con.closeConnection();
        }
    }

    public ArrayList<HuellaDigital> buscarHuellaDigital(HuellaDigital hd, String tipo) {
        return ejecutaBusqueda(hd, "{call buscarHuellaDigital" + tipo + "(?)}", true);
    }
    
    private ArrayList<HuellaDigital> ejecutaBusqueda(HuellaDigital hd, String procedimiento, boolean buscar){
        ArrayList<HuellaDigital> huellaDigital = new ArrayList<>();
        try{
            CallableStatement cst = con.getCon().prepareCall(procedimiento);
            
            if (buscar) {
                cst.setInt(1, hd.getIdUser());
            }

            cst.execute();
            final ResultSet rs = cst.getResultSet();
            
            try {
                while(rs.next())
                    huellaDigital.add(cargarHuella(rs));
                
            } catch (SQLException e) {}

            return huellaDigital;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar buscar la dieta :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return null;
        }finally{
            con.closeConnection();
        }
    }

    private HuellaDigital cargarHuella(ResultSet rs){
        try {
            return new HuellaDigital(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getBytes(4));
        } catch (SQLException ex) {
            return null;
        }
    }
}

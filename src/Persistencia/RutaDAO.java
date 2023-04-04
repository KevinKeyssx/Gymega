package Persistencia;

import Clases.Ruta;
import Conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * @author keiss
 */
public class RutaDAO {
    
    private Conexion con = null;

    public RutaDAO() {con = new Conexion();}
    
    public Ruta obtenerRuta(Ruta r){
        try{
            CallableStatement cst = con.getCon().prepareCall("{call obtenerRuta(?)}");
            cst.setString(1, r.getRutaArchivo());
            cst.execute();
            final ResultSet rs = cst.getResultSet();

            while(rs.next()){
                r.setIdArchivo(rs.getInt(1));
                r.setRutaArchivo(rs.getString(2));
            }
            
            return r;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar buscar la ruta :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return null;
        }finally{
            con.closeConnection();
        }
    }
    
}

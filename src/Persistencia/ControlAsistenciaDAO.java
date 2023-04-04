package Persistencia;

import Conexion.Conexion;
import Clases.ControlAsistencia;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import javax.swing.JOptionPane;

/**
 * @author KevinKeyss
 */
public class ControlAsistenciaDAO {
    private Conexion con = null;

    public ControlAsistenciaDAO() {
        con = new Conexion();
    }
    
    private int ejecutaProcSQL(ControlAsistencia ctrlAsistencia, String ejecutaProcedimineto){
        try {
            CallableStatement cst = con.getCon().prepareCall(ejecutaProcedimineto);
            
            cst.setInt(1, ctrlAsistencia.getIdpago());//Enviamos el id del pago
            cst.setDate(2, new java.sql.Date(ctrlAsistencia.getFechaAsistencia().getTime()) );//Cargamos la fecha
            cst.setBoolean(3, ctrlAsistencia.isAsistencia());
            cst.registerOutParameter(4, Types.INTEGER); //Respuesta
            cst.execute();

            return cst.getInt(4);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar ingresar! :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return 0;
        }finally{
            con.closeConnection();
        }
    }
    
    public int ingresarAsistencia(ControlAsistencia ctrlAsistencia) {
        return ejecutaProcSQL(ctrlAsistencia, "{call ingresarControlAsistencia(?, ?, ?, ?)}");
    }
}

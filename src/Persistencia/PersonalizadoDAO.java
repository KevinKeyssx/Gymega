package Persistencia;

import Clases.Personalizado;
import Conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.swing.JOptionPane;

/**
 * @author Kevin
 */
public class PersonalizadoDAO {
    private Conexion con = null;

    public PersonalizadoDAO() {
        con = new Conexion();
    }

    public boolean gestionarPersonalizado(Personalizado personal) {
        return ejecutaProcSQL(personal, "{call gestionaPersonalizado(?, ? , ?, ?, ?, ?, ?, ?, ?, ?)}");
    }
    
    private boolean ejecutaProcSQL(Personalizado personal, String procedimiento) {
        try {
            CallableStatement cst = con.getCon().prepareCall(procedimiento);

            cst.setInt(1, personal.getId());
            cst.setString(2, personal.getSexo());
            cst.setDouble(3, personal.getTricepts());
            cst.setDouble(4, personal.getSubescapular());
            cst.setDouble(5, personal.getBiceps());
            cst.setDouble(6, personal.getSupraespinal());
            cst.setDouble(7, personal.getAbdominal());
            cst.setDouble(8, personal.getMuslofrontal());
            cst.setDouble(9, personal.getPantorrilla());
            cst.registerOutParameter(10, Types.BOOLEAN); //Respuesta
            cst.execute();
            
            return cst.getInt(10) == 1;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar gestionar el personalizado! :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return false;
        }finally{
            con.closeConnection();
        }
    }
    
    public int isPersonalizado(int idAlumno){
        try {
            CallableStatement cst = con.getCon().prepareCall("{call isPersonalizado(? , ?)}");
            
            cst.setInt(1, idAlumno);
            cst.registerOutParameter(2, Types.INTEGER); //Respuesta
            cst.execute();
            
            return cst.getInt(2);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar conectar con el servidor! :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return 0;
        }finally{
            con.closeConnection();
        }
    }
    
    public Personalizado obtenerPersonalizado(int idAlumno){
        try {
            CallableStatement cst = con.getCon().prepareCall("{call obtenerPersonalizado(?)}");
            cst.setInt(1, idAlumno);
            cst.execute();
            final ResultSet rs = cst.getResultSet();

            if(rs.next())
                return cargaPersonalizada(rs);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar buscar los datos personalizados del alumno :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return null;
        }
        finally{
            con.closeConnection();
        }
        return null;
    }

    private Personalizado cargaPersonalizada(ResultSet rs) {
        try {
            return new Personalizado(
                    rs.getInt(1), 
                    rs.getString(2), 
                    rs.getDouble(3), 
                    rs.getDouble(4), 
                    rs.getDouble(5), 
                    rs.getDouble(6), 
                    rs.getDouble(7), 
                    rs.getDouble(8), 
                    rs.getDouble(9)
            );
        } catch (SQLException e) {
            return null;
        }
    }

    public int obtenerPersonalizado() {
        try {
            CallableStatement cst = con.getCon().prepareCall("{call obtenerMaximoPersonalizado()}");
            cst.execute();
            final ResultSet rs = cst.getResultSet();

            if(rs.next())
                return rs.getInt(1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar buscar los datos personalizados del alumno :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return 0;
        }
        finally{
            con.closeConnection();
        }
        return 0;
    }
}

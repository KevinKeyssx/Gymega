package Persistencia;

import Clases.IniciarSesion;
import Conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.swing.JOptionPane;

/**
 * @author KevinKeyss
 */
public class IniciarSesionDAO {
    private Conexion con = null;

    public IniciarSesionDAO() {
        con = new Conexion();
    }

    public int iniciarSesion(IniciarSesion inise) {
        try {
            CallableStatement cst = con.getCon().prepareCall("{call iniciarSesionPT(?, ?, ?, ?)}");
            
            cst.setString(1, inise.getUsuario());
            cst.setString(2, inise.getContraseña());
            cst.setBoolean(3, inise.isRecordar());
            cst.registerOutParameter(4, Types.INTEGER); //Respuesta
            cst.execute();
            
            return cst.getInt(4);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar iniciar sesión! :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return 0;
        }finally{
            con.closeConnection();
        }
    }

    public IniciarSesion obtenerIniciarSesion() {
        try {
            CallableStatement cst = con.getCon().prepareCall("{call obtenerInicioSesion()}");

            cst.execute();
            final ResultSet rs = cst.getResultSet();

            if(rs.next())
                return cargarInicioSesion(rs);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado, esto no efectará con tu inicio de sesión\nEstamos intentando solucionarlo\n" + e, "Error x.x", JOptionPane.ERROR_MESSAGE);
            return null;
        }finally{
            con.closeConnection();
        }
        return null;
    }

    private IniciarSesion cargarInicioSesion(ResultSet rs) throws SQLException {
        return new IniciarSesion(
            rs.getString(1), 
            rs.getString(2), 
            rs.getBoolean(3)
        );
    }

}

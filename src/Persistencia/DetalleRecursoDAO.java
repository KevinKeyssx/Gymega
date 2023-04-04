package Persistencia;

import Clases.DetalleRecurso;
import Clases.Tipo;
import Conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * @author KevinKeyss
 */
public class DetalleRecursoDAO {
    private Conexion con = null;

    public DetalleRecursoDAO() {
        con = new Conexion();
    }

    public int ingresarDetalleRecurso(DetalleRecurso drecurso) {
        return ejecutaProcSQL(drecurso, "{call ingresarDetalleRecurso(?, ?, ?)}");
    }
    
    public int elminarDetalleRecurso(DetalleRecurso drecurso) {
        return ejecutaProcSQL(drecurso, "{call eliminarDetalleRecurso(?, ?, ?)}");
    }

    private int ejecutaProcSQL(DetalleRecurso drecurso, String procedimiento) {
        try {
            CallableStatement cst = con.getCon().prepareCall(procedimiento);

            cst.setInt(1, drecurso.getGrupo().getId());
            cst.setInt(2, drecurso.getTipo().getId());
            cst.registerOutParameter(3, Types.INTEGER); //Respuesta
            cst.execute();

            return cst.getInt(3);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar de asiganr el tipo al grupo! :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return 0;
        }finally{
            con.closeConnection();
        }
    }

    public ArrayList<DetalleRecurso> buscarDetalleRecurso(DetalleRecurso drecurso) {
        ArrayList<DetalleRecurso> listaDetalleRecurso = new ArrayList<>();
        try {
            CallableStatement cst = con.getCon().prepareCall("{call obtenerDetalleRecurso(?)}");

            cst.setString(1, drecurso.getGrupo().getNombre());
            cst.execute();
            
            final ResultSet rs = cst.getResultSet();
            
            while(rs.next())
                listaDetalleRecurso.add(cargarGrupos(rs));
            
            return listaDetalleRecurso;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar encontrar los alumnos del grupo:(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private DetalleRecurso cargarGrupos(ResultSet rs) {
        try {
            return new DetalleRecurso(new Tipo(rs.getInt(1), rs.getString(2), rs.getInt(3)));
        } catch (SQLException e) {
            return null;
        }
    }
}
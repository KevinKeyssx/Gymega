package Persistencia;

import Clases.HistorialAsistencia;
import Conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * @author KevinKeyss
 */
public class HistorialAsistenciaDAO {
    private Conexion con = null;

    public HistorialAsistenciaDAO() {
        con = new Conexion();
    }
    
    public ArrayList<HistorialAsistencia> asistenciasAlumno(int idAlumno, int mes, int año) {
        ArrayList<HistorialAsistencia> listaAsistencia = new ArrayList<>();
        
        try {
            CallableStatement cst = con.getCon().prepareCall("{call obtenerAsistenciasxAlumno(?, ?, ?)}");
            
            cst.setInt(1, idAlumno);
            cst.setInt(2, mes);
            cst.setInt(3, año);
            cst.execute();
            
            final ResultSet rs = cst.getResultSet();
            
            while(rs.next()){
                listaAsistencia.add(cargarAsistencia(rs));
            }
            
            return listaAsistencia;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar buscar a la asistencia del alumno :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private HistorialAsistencia cargarAsistencia(ResultSet rs) {
        try {
            return new HistorialAsistencia(
                rs.getInt(1),   //Semana
                rs.getInt(2),   //Lunes
                rs.getInt(3),   //Martes
                rs.getInt(4),   //Miércoles
                rs.getInt(5),   //Jueves
                rs.getInt(6),   //Viernes
                rs.getInt(7),   //Sábado
                rs.getInt(8)    //domingo
            );
        } catch (SQLException e) {
            return null;
        }
    }

    public HistorialAsistencia detalleGrupo(int idAlumno, String nombreGrupo) {
        try {
            CallableStatement cst = con.getCon().prepareCall("{call detalleDeGrupo(?, ?)}");

            cst.setInt(1, idAlumno);
            cst.setString(2, nombreGrupo);
            
            cst.execute();
            final ResultSet rs = cst.getResultSet();

            if(rs.next())
                return cargarGrupo(rs);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar buscar el detalle del grupo:(\n" + e, "Error x.x", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return null;
    }

    private HistorialAsistencia cargarGrupo(ResultSet rs) throws SQLException {
        return new HistorialAsistencia(
            rs.getDate(1), 
            rs.getInt(2), 
            rs.getDate(3), 
            rs.getInt(4)
        );
    }
}

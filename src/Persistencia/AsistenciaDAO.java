package Persistencia;

import Clases.Alumno;
import Clases.Asistencia;
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
public class AsistenciaDAO {
    private Conexion con = null;
    
    public AsistenciaDAO() {
        con = new Conexion();
    }
    
    public int ingresarAlumno(Asistencia asistencia){
        return ejecutaProcSQL(asistencia, "{call ingresarAsistencia(?, ?)}");
    }
    
    private int ejecutaProcSQL(Asistencia asistencia, String ejecutaProcedimineto){
        try {
            CallableStatement cst = con.getCon().prepareCall(ejecutaProcedimineto);
            
            cst.setInt(1, asistencia.getAlumno().getId());
            cst.registerOutParameter(2, Types.INTEGER); //Respuesta
            cst.execute();
            
            return cst.getInt(2);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar ingresar la asistencia! :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return 0;
        }finally{
            con.closeConnection();
        }
    }

    public ArrayList<Asistencia> buscarAsistencias(boolean todo) {
        ArrayList<Asistencia> listaAsistencia = new ArrayList<>();
        
        try {
            CallableStatement cst = con.getCon().prepareCall("{call obtenerAsistencias(?)}");
            
            cst.setBoolean(1, todo);
            cst.execute();
            
            final ResultSet rs = cst.getResultSet();
            
            while(rs.next()){
                listaAsistencia.add(cargarAsistencia(rs));
            }
            
            return listaAsistencia;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar buscar a los alumnos :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    private Asistencia cargarAsistencia(ResultSet rs){
        try {
            return new Asistencia(
                rs.getInt(1),       //Id asistencia
                cargarAlumno(rs),   //Alumno
                rs.getDate(2),      //Fecha
                rs.getString(3),    //Hora
                rs.getString(6)        //Descripcion
            );
            
        } catch (SQLException e) {
            return null;
        }
    }
    
    private Alumno cargarAlumno(ResultSet rs) throws SQLException{
        Alumno alumno = new Alumno();
        alumno.setRut(rs.getString(4));
        alumno.setNombre(rs.getString(5));
        alumno.setId(rs.getInt(7));
        return alumno;
    }
}

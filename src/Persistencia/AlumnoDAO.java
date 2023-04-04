package Persistencia;

import Clases.Alumno;
import Conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * @author keiss
 */
public class AlumnoDAO {
    private Conexion con = null;

    public AlumnoDAO() {
        con = new Conexion();
    }
    
    private int ejecutaProcSQL(Alumno alumno, String ejecutaProcedimineto){
        try {
            CallableStatement cst = con.getCon().prepareCall(ejecutaProcedimineto);
            
            cst.setString(1, alumno.getRut());
            cst.setInt(2, alumno.getRuta());
            cst.setString(3, alumno.getNombre());
            cst.setString(4, alumno.getApellidoP());
            cst.setString(5, alumno.getApellidoM());
            cst.setString(6, alumno.getDireccion());
            cst.setString(7, alumno.getCorreo());
            cst.setString(8, alumno.getFono());
            cst.setDate(9, new java.sql.Date(alumno.getFechaNacimiento().getTime()));
            cst.setDouble(10, alumno.getEstatura());
            cst.setDouble(11, alumno.getPeso());
            cst.setString(12, alumno.getNombreEmergencia());
            cst.setString(13, alumno.getTelefonoEmergencia());
            cst.setString(14, alumno.getObservacion());
            cst.setString(15, alumno.getExtension());
            cst.setInt(16, alumno.getIdPersonalizado());
            cst.registerOutParameter(17, Types.INTEGER); //Respuesta
            cst.execute();
            
            return cst.getInt(17);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar ingresar al Alumno! :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return 0;
        }finally{
            con.closeConnection();
        }
    }
     
    public int IngresarAlumno(Alumno alumno){
        return ejecutaProcSQL(alumno, "{call ingresarAlumno(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
    }
    
    public int ModificarAlumno(Alumno alumno){
        return ejecutaProcSQL(alumno, "{call modificarAlumno(? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
    }
    
    private Alumno cargarAlumno(ResultSet rs){
        Alumno alumno = new Alumno();
        try {
            alumno.setId(rs.getInt(1));
            alumno.setRut(rs.getString(2));
            alumno.setFoto(rs.getString(3));
            alumno.setNombre(rs.getString(4));
            alumno.setApellidoP(rs.getString(5));
            alumno.setApellidoM(rs.getString(6));
            alumno.setDireccion(rs.getString(7));
            alumno.setCorreo(rs.getString(8));
            alumno.setFono(rs.getString(9));
            alumno.setFechaNacimiento(rs.getDate(10));
            alumno.setEstatura(rs.getDouble(11));
            alumno.setPeso(rs.getDouble(12));
            alumno.setNombreEmergencia(rs.getString(13));
            alumno.setTelefonoEmergencia(rs.getString(14));
            alumno.setObservacion(rs.getString(15));
            alumno.setIdPersonalizado(rs.getInt(16));
            alumno.setExtension(rs.getString(17));
            return alumno;
        } catch (SQLException e) {
            return null;
        }
    }
    
    public ArrayList<Alumno> buscarAlumnos(boolean busqueda, String dato, boolean huella){
        ArrayList<Alumno> listaAlumnos = new ArrayList<>();
        
        try {
            CallableStatement cst = con.getCon().prepareCall("{call buscarAlumnos(?, ?, ?)}");
            
            cst.setBoolean(1, busqueda);
            cst.setString(2, dato);
            cst.setBoolean(3, huella);
            cst.execute();
            
            final ResultSet rs = cst.getResultSet();
            
            while(rs.next()){
                listaAlumnos.add(cargarAlumno(rs));
            }
            
            return listaAlumnos;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar buscar a los alumnos :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public Alumno obtenerAlumno(Alumno alumno){
        return conectarBusqueda(alumno, true, "{call obtenerAlumno(?)}");
    }
    
    public Alumno buscarIdAlumno(Alumno alumno) {
        return conectarBusqueda(alumno, false, "{call obtenerIdAlumno(?)}");
    }
    
    private Alumno conectarBusqueda(Alumno alumno, boolean rut, String procedimiento){
        try {
            CallableStatement cst = con.getCon().prepareCall(procedimiento);
            
            if (rut) 
                cst.setString(1, alumno.getRut());
            else
                cst.setInt(1, alumno.getId());
            
            cst.execute();
            final ResultSet rs = cst.getResultSet();

            if(rs.next())
                return cargarAlumno(rs);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar buscar al alumno :(\n" + e, "Error x.x", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return null;
    }

    public boolean modificarPersonalizado(Alumno alumno) {
        try {
            CallableStatement cst = con.getCon().prepareCall("{call modificarIdPersonalizado(? , ?, ?)}");
            
            cst.setInt(1, alumno.getId());
            cst.setInt(2, alumno.getIdPersonalizado());
            cst.registerOutParameter(3, Types.BOOLEAN); //Respuesta
            cst.execute();
            
            return cst.getInt(3) == 1;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar modificar el personalizado del Alumno! :(\n" + e, "Error x.x", JOptionPane.ERROR_MESSAGE);
            return false;
        }finally{
            con.closeConnection();
        }
    }
}

package Clases;

import Clases.Deporte;
import Conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author KevinKeyss
 */
public class DeporteDAO {
     private Conexion con = null;

    public DeporteDAO() {
        con = new Conexion();
    }

     

    boolean ingresarDeporte(Deporte deporte, boolean ingresarDeporte) {
        if (ingresarDeporte) 
            return ejecutaProcSQL(deporte, "{call ingresarDeporte(? , ?)}", ingresarDeporte);
        else
            return ejecutaProcSQL(deporte, "{call ingresarDeporteAlumno(? , ?, ?, ?, ?, ?, ?, ?)}", ingresarDeporte);
    }
    
//    boolean ingresarDeporteAlumno(Deporte deporte) {
//        return ejecutaProcSQL(deporte, "{call ingresarDeporteAlumno(? , ?, ?, ?, ?, ?, ?, ?)}");
//    }

    private boolean ejecutaProcSQL(Deporte deporte, String procedimiento, boolean ingreso) {
        try {
            CallableStatement cst = con.getCon().prepareCall(procedimiento);
            
            int num;
            
            if (ingreso){
                num = 2;
                cst.setString(1, deporte.getDeporte());
                cst.registerOutParameter(2, Types.BOOLEAN); //Respuesta
            }
            else{
                num = 8;
                cst.setInt(1, deporte.getIdAlumno());
                cst.setInt(2, deporte.getIdDeporte());
                cst.setString(3, deporte.getEstablecimiento());
                cst.setString(4, deporte.getDireccion());
                cst.setString(5, deporte.getNivel());
                cst.setString(6, deporte.getTiempo());
                cst.setInt(7, deporte.getCantidad());
                cst.registerOutParameter(8, Types.BOOLEAN); //Respuesta
            }
            cst.execute();
            return cst.getInt(num) == 1;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar ingresar el deporte! :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return false;
        }finally{
            con.closeConnection();
        }
    }

    boolean modificarDeporte(Deporte aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    Deporte obtenerDeporte(boolean busqueda, Deporte dato){
        try {
            CallableStatement cst = con.getCon().prepareCall("{call obtenerDeporte(?, ?)}");
            
            cst.setBoolean(1, busqueda);
            cst.setString(2, dato.getDeporte());
            cst.execute();

            final ResultSet rs = cst.getResultSet();
            
            if(rs.next())
                return cargarDeporte(rs);
            else
                return null;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar buscar los deportes :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    ArrayList<Deporte> buscarDeporte(boolean busqueda, Deporte dato){
        ArrayList<Deporte> listaDeporte = new ArrayList<>();
        
        try {
            CallableStatement cst = con.getCon().prepareCall("{call obtenerDeporte(?, ?)}");
            
            cst.setBoolean(1, busqueda);
            cst.setString(2, dato.getDeporte());
            cst.execute();
            
            final ResultSet rs = cst.getResultSet();
            
            while(rs.next())
                listaDeporte.add(cargarDeporte(rs));
            
            return listaDeporte;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar buscar los deportes :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private Deporte cargarDeporte(ResultSet rs) {
        try {
            return new Deporte(rs.getInt(1), rs.getString(2));
        } catch (SQLException e) {
            return null;
        }
    }
    
    private Deporte cargarDeportes(ResultSet rs) {
        try {
            return new Deporte(
                rs.getInt(1), 
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getString(6),
                rs.getInt(7)
            );
        } catch (SQLException e) {
            return null;
        }
    }

    ArrayList<Deporte> buscarDeportes(int id) {
        ArrayList<Deporte> listaDeporte = new ArrayList<>();
        
        try {
            CallableStatement cst = con.getCon().prepareCall("{call obtenerDeportes(?)}");
            
            cst.setInt(1, id);
            cst.execute();
            
            final ResultSet rs = cst.getResultSet();
            
            while(rs.next())
                listaDeporte.add(cargarDeportes(rs));
            
            return listaDeporte;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar buscar los deportes :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    boolean eliminarDeporte(Deporte deporte) {
        try {
            CallableStatement cst = con.getCon().prepareCall("{call eliminarDeporte(?, ?, ?)}");
            
            cst.setInt(1, deporte.getIdDeporte());
            cst.setInt(2, deporte.getIdAlumno());
            cst.registerOutParameter(3, Types.BOOLEAN); //Respuesta
            cst.execute();

            return cst.getInt(3) == 1;
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar eliminar el deporte del alumno! :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return false;
        }finally{
            con.closeConnection();
        }
    }
}

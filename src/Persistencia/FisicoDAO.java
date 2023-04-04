package Persistencia;

import Clases.Fisico;
import Conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * @author Kevin
 */
public class FisicoDAO {
    private Conexion con = null;

    public FisicoDAO() {
        con = new Conexion();
    }
    
    private boolean ejecutaProcSQL(Fisico fisico, String ejecutaProcedimineto, int idAlumno){
        try {
            CallableStatement cst = con.getCon().prepareCall(ejecutaProcedimineto);
            
            cst.setInt(1, fisico.getId());
            
            if (idAlumno == 0) //Si es cero ingresamo un fisico
                cst.setString(2, fisico.getFisico());
            else 
                cst.setInt(2, idAlumno);//Si tiene algun valor cargado ingresamos un detalle de fisico
            
            cst.registerOutParameter(3, Types.BOOLEAN); //Respuesta
            cst.execute();

            return cst.getInt(3) == 1;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar ingresar! :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return false;
        }finally{
            con.closeConnection();
        }
    }
    
    public boolean ingresarFisico(Fisico fisico, int idAlumno, String llamado) {
        return ejecutaProcSQL(fisico, "{call ingresar" + llamado + "(? , ?, ?)}", idAlumno);
    }
    
    public boolean modificarFisico(Fisico fisico, int id) {
        return ejecutaProcSQL(fisico, "{call modificarFisico(? , ?, ?)}", id);
    }

    public ArrayList<Fisico> obtenerFisico(boolean busqueda, String llamado, int id) {
        ArrayList<Fisico> listaFisico = new ArrayList<>();
        
        try {
            CallableStatement cst = con.getCon().prepareCall("{call obtener" + llamado + "(?, ?)}");
            
            cst.setBoolean(1, busqueda);
            cst.setInt(2, id);
            cst.execute();
            
            final ResultSet rs = cst.getResultSet();
            
            while(rs.next()){
                Fisico fisico = cargarFisico(rs);
                listaFisico.add(fisico);
            }
            
            return listaFisico;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar buscar a los alumnos :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private Fisico cargarFisico(ResultSet rs) {
        try {
            return new Fisico(rs.getInt(1),rs.getString(2));
        } catch (SQLException e) {
            return null;
        }
    }

    public Fisico buscarFisico(Fisico fisico, String llamado) {
        try {
            CallableStatement cst = con.getCon().prepareCall("{call obtener" + llamado + "(?)}");
            cst.setString(1, fisico.getFisico());
            cst.execute();
            final ResultSet rs = cst.getResultSet();

            if(rs.next())
                return cargarFisico(rs);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar buscar al fisico del alumno :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return null;
    }

    public boolean eliminarFisico(String name, Fisico fisico, int idAlumno) {
        try {
            CallableStatement cst = con.getCon().prepareCall("{call eliminarDetalle" + name + "(?, ?, ?)}");
            
            cst.setInt(1, fisico.getId());
            cst.setInt(2, idAlumno);
            cst.registerOutParameter(3, Types.BOOLEAN); //Respuesta
            cst.execute();

            return cst.getInt(3) == 1;
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar eliminar (" + name + ")! :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return false;
        }finally{
            con.closeConnection();
        }
    }
}

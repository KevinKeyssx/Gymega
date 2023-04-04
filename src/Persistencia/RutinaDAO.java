package Persistencia;

import Clases.Rutina;
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
public class RutinaDAO {
    
    private Conexion con = null;

    public RutinaDAO() {con=new Conexion();}
    
    public int ingresarRutina(Rutina r){
        try{
            CallableStatement cst = con.getCon().prepareCall("{call ingresarRutina(?, ?, ?, ?)}");
            cst.setString(1, r.getNombre());
            cst.setString(2, r.getArchivo());
            cst.setInt(3, r.getRutaArchivo());
            cst.registerOutParameter(4, Types.INTEGER); //Respuesta
            cst.execute();
            
            return cst.getInt(4);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar ingresar la rutina! :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return 0;
        }finally{
            con.closeConnection();
        }
    }
    
    public int modificarRutina(Rutina d){
        try{
            CallableStatement cst = con.getCon().prepareCall("{call modificarRutina(?, ?, ?, ?)}");
            
            cst.setString(1, d.getNombre());
            cst.setString(2, d.getArchivo());
            cst.setInt(3, d.getRutaArchivo());
            cst.registerOutParameter(4, Types.INTEGER); //Respuesta
            cst.execute();
            
            return cst.getInt(4);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar modifiacr la rutina! :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return 0;
        }finally{
            con.closeConnection();
        }
    }
        
    public ArrayList<Rutina> buscarRutinas(boolean tipoBusqueda,  String nombre){
        ArrayList<Rutina> rutina = new ArrayList<>();
        try{
            CallableStatement cst = con.getCon().prepareCall("{call buscarRutinas(?, ?)}");
            cst.setBoolean(1, tipoBusqueda);
            cst.setString(2, nombre);
            
            cst.execute();
            
            final ResultSet rs = cst.getResultSet();
            
            while(rs.next()){
                Rutina r = new Rutina();
                r.setId(rs.getInt(1));
                r.setArchivo(rs.getString(2));
                r.setNombre(rs.getString(3));
                rutina.add(r);
            }
            return rutina;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar buscar las rutinas :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return null;
        }finally{
            con.closeConnection();
        }
    }
    
    public Rutina obtenerRutina(Rutina r){
        try{
            CallableStatement cst = con.getCon().prepareCall("{call obtenerRutina(?)}");
            cst.setString(1, r.getNombre());
            cst.execute();
            final ResultSet rs = cst.getResultSet();
            
            while(rs.next()){
                r.setId(rs.getInt(1));
                r.setArchivo(rs.getString(2));
                r.setNombre(rs.getString(3));
                r.setRuta(rs.getString(4));
            }
            return r;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar buscar la rutina :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return null;
        }finally{
            con.closeConnection();
        }
    }

    public int eliminarRutina(Rutina r) {
        try{
            CallableStatement cst = con.getCon().prepareCall("{call borrarRutina(?, ?)}");

            cst.setString(1, r.getNombre());
            cst.registerOutParameter(2, Types.INTEGER); //Respuesta
            cst.execute();
            
            return cst.getInt(2);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar eliminar la rutina! :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return 0;
        }finally{
            con.closeConnection();
        }
    }
}

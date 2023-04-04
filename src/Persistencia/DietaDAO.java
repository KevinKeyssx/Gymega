package Persistencia;

import Clases.Dieta;
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
public class DietaDAO {
    
    private Conexion con = null;

    public DietaDAO() {con=new Conexion();}
    
    public int ingresarDieta(Dieta d){
        try{
            CallableStatement cst = con.getCon().prepareCall("{call ingresarDieta(?, ?, ?, ?)}");
            cst.setString(1, d.getNombre());
            cst.setString(2, d.getArchivo());
            cst.setInt(3, d.getRutaArchivo());
            cst.registerOutParameter(4, Types.INTEGER); //Respuesta
            cst.execute();
            
            return cst.getInt(4);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar ingresar la dieta! :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return 0;
        }finally{
            con.closeConnection();
        }
    }
    
    public int modificarDieta(Dieta d){
        try{
            CallableStatement cst = con.getCon().prepareCall("{call modificarDieta(?, ?, ?, ?, ?)}");

            cst.setInt(1, d.getId());
            cst.setString(2, d.getNombre());
            cst.setString(3, d.getArchivo());
            cst.setInt(4, d.getRutaArchivo());
            cst.registerOutParameter(5, Types.INTEGER); //Respuesta
            cst.execute();
            
            return cst.getInt(5);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar modificar la dieta! :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return 0;
        }finally{
            con.closeConnection();
        }
    }
    
    public int borrarDieta(Dieta d){
        try{
            CallableStatement cst = con.getCon().prepareCall("{call borrarDieta(?, ?)}");

            cst.setString(1, d.getNombre());
            cst.registerOutParameter(2, Types.INTEGER); //Respuesta
            cst.execute();
            
            return cst.getInt(2);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar eliminar la dieta! :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return 0;
        }finally{
            con.closeConnection();
        }
    } 

    public ArrayList<Dieta> buscarDietas(boolean tipoBusqueda,  String nombre){
        ArrayList<Dieta> dieta = new ArrayList<>();
        try{
            CallableStatement cst = con.getCon().prepareCall("{call buscarDieta(?, ?)}");
            cst.setBoolean(1, tipoBusqueda);
            cst.setString(2, nombre);
            
            cst.execute();
            
            final ResultSet rs = cst.getResultSet();
            
            while(rs.next()){
                Dieta d = new Dieta();
                d.setId(rs.getInt(1));
                d.setArchivo(rs.getString(2));
                d.setNombre(rs.getString(3));
                dieta.add(d);
            }
            return dieta;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar buscar la dieta :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return null;
        }finally{
            con.closeConnection();
        }
    }
    
    public Dieta obtenerDieta(Dieta d){
        try{
            CallableStatement cst = con.getCon().prepareCall("{call obtenerDieta(?)}");
            cst.setString(1, d.getNombre());
            cst.execute();
            final ResultSet rs = cst.getResultSet();
            
            while(rs.next()){
                d.setId(rs.getInt(1));
                d.setArchivo(rs.getString(2));
                d.setNombre(rs.getString(3));
                d.setRuta(rs.getString(4));
            }
            return d;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar buscar la dieta :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return null;
        }finally{
            con.closeConnection();
        }
    }
    
}

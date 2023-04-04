package Persistencia;

import Clases.Tipo;
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
public class TipoDAO {
    
    Conexion con = null;

    public TipoDAO() {con = new Conexion();}
    
    public boolean ingresarTipo(Tipo tipo){
        try{
            CallableStatement cst = con.getCon().prepareCall("{call ingresarTipo( ?, ?, ?)}");

            cst.setString(1, tipo.getNombre());
            cst.setInt(2, tipo.getMonto());
            cst.registerOutParameter(3, Types.BOOLEAN); //Respuesta
            cst.execute();
            
            return cst.getInt(3) == 1;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar crear el nuevo Tipo! :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return false;
        }finally{
            con.closeConnection();
        }
    }

    public boolean modificarTipo(Tipo tipo) {
        try{
            CallableStatement cst = con.getCon().prepareCall("{call modificarTipo(? , ?, ?, ?)}");
            cst.setInt(1, tipo.getId());
            cst.setString(2, tipo.getNombre());
            cst.setInt(3, tipo.getMonto());
            cst.registerOutParameter(4, Types.BOOLEAN); //Respuesta
            cst.execute();
            
            return cst.getInt(4) == 1;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar modificar el Tipo! :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return false;
        }finally{
            con.closeConnection();
        }
    }

    public int obtenerIdMaximo() {
        try{
            CallableStatement cst = con.getCon().prepareCall("{call ObtenerFuncion(?, ?)}");
            cst.setInt(1, 1);
            cst.registerOutParameter(2, Types.INTEGER); //Respuesta
            cst.execute();
            
            return cst.getInt(2);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar realizar la busqueda del último de número! :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return 0;
        }finally{
            con.closeConnection();
        }
    }
    
    public Tipo obtenerTipo(Tipo tipo) {
        try {
            CallableStatement cst = con.getCon().prepareCall("{call obtenerTipo(?)}");
            cst.setString(1, tipo.getNombre());
            cst.execute();
            final ResultSet rs = cst.getResultSet();

            if(rs.next())
                return cargarTipo(rs);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar el monto total del grupo :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return null;
        }
        finally{
            con.closeConnection();
        }
        return null;
    }

    public ArrayList<Tipo> buscarTipo(boolean busqueda, String dato) {
        ArrayList<Tipo> listaTipo = new ArrayList<>();
        try{
            CallableStatement cst = con.getCon().prepareCall("{call buscarTipo(?, ?)}");
            cst.setBoolean(1, busqueda);
            cst.setString(2, dato);
            
            cst.execute();
            
            final ResultSet rs = cst.getResultSet();
            
            while(rs.next())
                listaTipo.add(cargarTipo(rs));

            return listaTipo;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar buscar los tipos :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return null;
        }finally{
            con.closeConnection();
        }
    }

    public int eliminarTipo(Tipo tipo) {
        try{
            CallableStatement cst = con.getCon().prepareCall("{call eliminarTipo(?, ?)}");

            cst.setInt(1, tipo.getId());
            cst.registerOutParameter(2, Types.INTEGER); //Respuesta
            cst.execute();
            
            return cst.getInt(2);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar eliminar el Tipo! :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return 0;
        }finally{
            con.closeConnection();
        }
    }

    private Tipo cargarTipo(ResultSet rs) {
        try {
            return new Tipo(rs.getInt(1), rs.getString(2), rs.getInt(3));
        } catch (SQLException e) {
            return null;
        }
    }
}

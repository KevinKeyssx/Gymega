package Persistencia;

import Clases.Grupo;
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
public class GrupoDAO {
    private Conexion con = null;

    public GrupoDAO() {
        con = new Conexion();
    }

    public int ingresarGrupo() {
        return ejecutaProcSQL("{call ingresarGrupo(?)}");
    }
    
    public int modificarGrupo(Grupo grupo) {
        return ejecutaProcSQL(grupo ,"{call modificarGrupo(?, ?, ?)}", "modificar");
    }
    
    public int eliminarGrupo(Grupo grupo) {
        return ejecutaProcSQL(grupo ,"{call eliminarGrupo(?, ?)}", "eliminar");
    }

    private int ejecutaProcSQL(String procedimiento) {
        try {
            CallableStatement cst = con.getCon().prepareCall(procedimiento);
            cst.registerOutParameter(1, Types.INTEGER); //Respuesta
            cst.execute();

            return cst.getInt(1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar ingresar el grupo! :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return 0;
        }finally{
            con.closeConnection();
        }
    }
    
    private int ejecutaProcSQL(Grupo grupo, String procedimiento, String ejecucion) {
        try {
            CallableStatement cst = con.getCon().prepareCall(procedimiento);
            cst.setInt(1, grupo.getId());
            
            int salida;
            
            if ("modificar".equals(ejecucion)) {
                salida = 3;
                cst.setString(2, grupo.getNombre());
                cst.registerOutParameter(salida, Types.INTEGER); //Respuesta
            }else{
                salida = 2;
                cst.registerOutParameter(salida, Types.INTEGER); //Respuesta
            }
            cst.execute();
            return cst.getInt(salida);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al " + ejecucion + " el grupo! :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return 0;
        }finally{
            con.closeConnection();
        }
    }

    public ArrayList<Grupo> buscarGrupos(Grupo grupo, boolean busqueda) {
        ArrayList<Grupo> listaGrupo = new ArrayList<>();
        
        try {
            CallableStatement cst = con.getCon().prepareCall("{call buscarGrupo(?, ?)}");

            cst.setString(1, grupo.getNombre());
            cst.setBoolean(2, busqueda);
            cst.execute();
            
            final ResultSet rs = cst.getResultSet();
            
            while(rs.next())
                listaGrupo.add(cargarGrupos(rs));
            
            return listaGrupo;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar buscar los grupos :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private Grupo cargarGrupos(ResultSet rs) {
        try {
            return new Grupo(rs.getInt(1), rs.getString(2));
        } catch (SQLException e) {
            return null;
        }
    }

    public Grupo obtenerGrupo(Grupo grupo) {
        try {
            CallableStatement cst = con.getCon().prepareCall("{call obtenerGrupo(?)}");
            cst.setString(1, grupo.getNombre());
            cst.execute();
            final ResultSet rs = cst.getResultSet();

            if(rs.next())
                return cargarGrupos(rs);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar el monto total del grupo :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return null;
        }
        finally{
            con.closeConnection();
        }
        return null;
    }
    
    public int obtenerPrecioGrupo(Grupo grupo){
        try{
            CallableStatement cst = con.getCon().prepareCall("{call obtenerPrecioGrupo(?, ?)}");
            cst.setInt(1, grupo.getId());
            cst.registerOutParameter(2, Types.INTEGER); //Respuesta
            cst.execute();
            
            return cst.getInt(2);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar obtener el monto total del grupo! :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return 0;
        }finally{
            con.closeConnection();
        }
    }

}
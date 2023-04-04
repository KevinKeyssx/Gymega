package Persistencia;

import Clases.Fecha;
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
public class FechaDAO {
    private Conexion con = null;

    public FechaDAO() {
        con = new Conexion();
    }
    
    public int ingresarFeriado(Fecha fecha) {
        return ejecutaProcSQLFeriado("{call ingresarFeriado(?, ?)}", fecha, "ingresar");
    }
    
    public int eliminarFeriado(Fecha fecha) {
        return ejecutaProcSQLFeriado("{call eliminarFeriado(?, ?)}", fecha, "eliminar");
    }
    
    public boolean modificarDiasLaborables(Fecha fecha){
        return ejecutaProcSQL(fecha ,"{call modificarDiaLaborable(?, ?, ?)}");
    }
    
    public ArrayList<Fecha> obtenerDiasFeriados(){
        ArrayList<Fecha> listaFeriados = new ArrayList<>();
        
        try {
            CallableStatement cst = con.getCon().prepareCall("{call obtenerDiasFeriados()}");
            cst.execute();
            
            final ResultSet rs = cst.getResultSet();
            
            while(rs.next())
                listaFeriados.add(cargarFeriados(rs));
            
            return listaFeriados;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar buscar los días feriados :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public ArrayList<Fecha> buscarDiasLaborables() {
        ArrayList<Fecha> listaDias = new ArrayList<>();
        
        try {
            CallableStatement cst = con.getCon().prepareCall("{call buscarDiasLaborables()}");
            cst.execute();
            
            final ResultSet rs = cst.getResultSet();
            
            while(rs.next())
                listaDias.add(cargarDias(rs));
            
            return listaDias;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar buscar los días laborables :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    private Fecha cargarDias(ResultSet rs) {
        try {
            return new Fecha(rs.getString(2), rs.getBoolean(3));
        } catch (SQLException e) {
            return null;
        }
    }
    
    private Fecha cargarFeriados(ResultSet rs) {
        try {
            return new Fecha(rs.getDate(1));
        } catch (SQLException e) {
            return null;
        }
    }

    private boolean ejecutaProcSQL(Fecha fecha, String call) {
        try {
            CallableStatement cst = con.getCon().prepareCall(call);
            cst.setString(1, fecha.getNombreDia());
            cst.setBoolean(2, fecha.isDiaLaborable());
            cst.registerOutParameter(3, Types.BOOLEAN); //Respuesta

            cst.execute();
            return cst.getBoolean(3);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al tratar de modificar los días! :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return false;
        }finally{
            con.closeConnection();
        }
    }

    private int ejecutaProcSQLFeriado(String procedimiento, Fecha fecha, String proceso) {
        try {
            CallableStatement cst = con.getCon().prepareCall(procedimiento);
            
            cst.setDate(1, new java.sql.Date(fecha.getFeriado().getTime()));
            cst.registerOutParameter(2, Types.INTEGER); //Respuesta
            cst.execute();

            return cst.getInt(2);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar " + proceso + " el día feriado! :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return 0;
        }finally{
            con.closeConnection();
        }
    }
}

package Persistencia;

import Clases.Grupo;
import Clases.Pago;
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
public class PagoDAO {
    private Conexion con = null;

    public PagoDAO() {
        con = new Conexion();
    }

    public int ingresarPago(Pago pago) {
        return ejecutaProcSQL("{call ValidarPago(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}", pago);
    }

    public int eliminarPago(Pago pago) {
        return ejecutaProcSQLEliminar("{call eliminarPago(?, ?)}", pago);
    }
    
    public int obtenerUltimoId() {
        return ejecutaFuncion("{call obtenerUltimoIdPAgo(?)}");
    }
    
    public ArrayList<Pago> buscarPagos(Pago pago){
        ArrayList<Pago> listaPago = new ArrayList<>();
        try {
            CallableStatement cst = con.getCon().prepareCall("{call buscarPagos(?)}");

            cst.setInt(1, pago.getAlumno().getId());
            cst.execute();
            
            final ResultSet rs = cst.getResultSet();
            
            while(rs.next())
                listaPago.add(cargarPagos(rs));

            return listaPago;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar encontrar los alumnos del grupo:(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    private int ejecutaProcSQLEliminar(String procedimiento, Pago pago) {
        try {
            CallableStatement cst = con.getCon().prepareCall(procedimiento);
            cst.setInt(1, pago.getIdPago());
            cst.registerOutParameter(2, Types.INTEGER); //Respuesta
            cst.execute();

            return cst.getInt(2);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar eliminar el pago! :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return 0;
        }finally{
            con.closeConnection();
        }
    }
    
    private int ejecutaProcSQL(String procedimiento, Pago pago) {
        try {
            CallableStatement cst = con.getCon().prepareCall(procedimiento);
            cst.setInt(1, pago.getAlumno().getId());
            cst.setInt(2, pago.getGrupo().getId());
            cst.setInt(3, pago.getAgregado());
            cst.setInt(4, pago.getMeses());
            cst.setInt(5, pago.getDescuento());
            cst.setBoolean(6, pago.isPorcentaje());
            cst.setDate(7, new java.sql.Date(pago.getFechaInicio().getTime()));
            cst.setDate(8, new java.sql.Date(pago.getFechaTermino().getTime()));
            cst.setString(9, pago.getFormaPago());
            cst.setInt(10, pago.getCantidad());
            cst.setInt(11, pago.getTotal());
            cst.setBoolean(12, pago.isTodos());
            cst.registerOutParameter(13, Types.INTEGER); //Respuesta
            cst.execute();

            return cst.getInt(13);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar ingresar el pago! :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return 0;
        }finally{
            con.closeConnection();
        }
    }

    private Pago cargarPagos(ResultSet rs) {
        try {
            return new Pago(
                    rs.getInt(1),                      //ID PAGO
                    new Grupo(rs.getString(2)),           //GRUPO (NOMBRE)
                    rs.getInt(3),                      //MESES
                    rs.getInt(4),                     //CANTIDAD
                    rs.getInt(5),                     //TOTAL
                    rs.getDate(6),                     //FECHA INICIO
                    rs.getDate(7),                     //FECHA TERMINO
                    rs.getString(8),                   //FORMA DE PAGO
                    rs.getInt(9),                      //AGREGADO
                    rs.getInt(10),                      //DESCUENTO
                    rs.getBoolean(11)                  //PORCENTAJE 
            );
        } catch (SQLException e) {
            return null;
        }
    }

    private int ejecutaFuncion(String procedimiento) {
        try {
            CallableStatement cst = con.getCon().prepareCall(procedimiento);
            
            cst.registerOutParameter(1, Types.INTEGER); //Respuesta
            cst.execute();

            return cst.getInt(1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar comunicarse con el servidor! :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return 0;
        }finally{
            con.closeConnection();
        }
    }


}

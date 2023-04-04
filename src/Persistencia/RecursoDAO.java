package Persistencia;

import Clases.Alumno;
import Clases.Recurso;
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
public class RecursoDAO {
    private Conexion con = null;

    public RecursoDAO() {
        con = new Conexion();
    }

    public int ingresarRecurso(Recurso recurso) {
        return ejecutaProcSQL(recurso, "{call ingresarRecurso(?, ?, ?)}", "de asignar");
    }
    
    public int eliminarRecurso(Recurso recurso) {
        return ejecutaProcSQL(recurso, "{call eliminarRecurso(?, ?, ?)}", "eliminar");
    }

    private int ejecutaProcSQL(Recurso recurso, String procedimiento, String metodo) {
        try {
            CallableStatement cst = con.getCon().prepareCall(procedimiento);
            
            cst.setInt(1, recurso.getGrupo().getId());
            cst.setInt(2, recurso.getAlumno().getId());
            cst.registerOutParameter(3, Types.INTEGER); //Respuesta
            cst.execute();

            return cst.getInt(3);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar " + metodo + " el Alumno al grupo! :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return 0;
        }finally{
            con.closeConnection();
        }
    }

    public ArrayList<Recurso> buscarRecursos(Recurso recurso) {
        ArrayList<Recurso> listaRecurso = new ArrayList<>();
        try {
            CallableStatement cst = con.getCon().prepareCall("{call obtenerRecurso(?)}");

            cst.setString(1, recurso.getGrupo().getNombre());
            cst.execute();
            
            final ResultSet rs = cst.getResultSet();
            
            while(rs.next())
                listaRecurso.add(cargarGrupos(rs));
            
            return listaRecurso;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar encontrar los alumnos del grupo:(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private Recurso cargarGrupos(ResultSet rs) {
        try {
            return new Recurso(new Alumno(rs.getInt(2), rs.getString(3)));
        } catch (SQLException e) {
            return null;
        }
    }

    public int obtenerProcRecurso(String proc, String grupo) {
        try{
            CallableStatement cst = con.getCon().prepareCall("{call obtenerProcRecurso(?, ?, ?)}");
            cst.setString(1, proc);
            cst.setString(2, grupo);
            cst.registerOutParameter(3, Types.INTEGER); //Respuesta
            cst.execute();
            
            return cst.getInt(3);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar contar los alumnos del grupo! :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return 0;
        }finally{
            con.closeConnection();
        }
    }



}

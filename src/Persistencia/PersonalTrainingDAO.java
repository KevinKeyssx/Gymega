package Persistencia;

import Clases.PersonalTraining;
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
public class PersonalTrainingDAO {
    
    private Conexion con = null;

    public PersonalTrainingDAO() {con=new Conexion();}
    
    private int cargarPersonalSQL(PersonalTraining ps, String ejecutaProcedimineto){
        try {
            CallableStatement cst = con.getCon().prepareCall(ejecutaProcedimineto);

            cst.setString(1, ps.getRut());
            cst.setString(2, ps.getPin());
            cst.setInt(3, ps.getRuta());
            cst.setString(4, ps.getNombre());
            cst.setString(5, ps.getApellidoP());
            cst.setString(6, ps.getApellidoM());
            cst.setString(7, ps.getDireccion());
            cst.setString(8, ps.getCorreo());
            cst.setString(9, ps.getFono());
            cst.setString(10, ps.getTipo());
            cst.setDate(11, new java.sql.Date(ps.getFechaNacimiento().getTime()));
            cst.setString(12, ps.getExtension());
            cst.registerOutParameter(13, Types.INTEGER); //Respuesta
            cst.execute();
            
            return cst.getInt(13);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar ejecutar la consulta a la base de datos(\n" + e.getMessage(),"Error x.x",JOptionPane.ERROR_MESSAGE);
            return 0;
        }finally{
            con.closeConnection();
        }
    }
    
    public int ingresarPersonalTraining(PersonalTraining ps){
        return cargarPersonalSQL(ps, "{call ingresarPersonalTraining(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
    }
    
    public int modificarPersonalTraining(PersonalTraining ps){
        return cargarPersonalSQL(ps, "{call modificarPersonalTraining(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
    }
    
    public int modificarPin(PersonalTraining ps, String nuevoPin) {
        try {
            CallableStatement cst = con.getCon().prepareCall("{call modificarPIN(?, ?, ?, ?)}");

            cst.setInt(1, ps.getId());
            cst.setString(2, ps.getPin());
            cst.setString(3, nuevoPin);
            cst.registerOutParameter(4, Types.INTEGER); //Respuesta
            cst.execute();
            
            return cst.getInt(4);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar ejecutar la consulta a la base de datos(\n" + e.getMessage(),"Error x.x",JOptionPane.ERROR_MESSAGE);
            return 0;
        }finally{
            con.closeConnection();
        }
    }
    
    public ArrayList<PersonalTraining> buscarPersonalTraining(boolean tipoBusqueda,  String dato, boolean huella){
        ArrayList<PersonalTraining> training = new ArrayList<>();
        try{
            CallableStatement cst = con.getCon().prepareCall("{call buscarPersonalTraining(?, ?, ?)}");
            cst.setBoolean(1, tipoBusqueda);
            cst.setString(2, dato);
            cst.setBoolean(3, huella);
            cst.execute();
            
            final ResultSet rs = cst.getResultSet();
            
            while(rs.next())
                training.add(cargarPersonal(rs));
            
            return training;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar buscar a los Personal Training :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return null;
        }finally{
            con.closeConnection();
        }
    }
    
    public PersonalTraining obtenerPersonalTraining(PersonalTraining ps){
        try{
            CallableStatement cst = con.getCon().prepareCall("{call obtenerPersonalTraining(?)}");
            
            cst.setString(1, ps.getRut());
            cst.execute();
            final ResultSet rs = cst.getResultSet();

            if(rs.next())
                return cargarPersonal(rs);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar buscar el Personal Training :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return null;
        }finally{
            con.closeConnection();
        }
        
        return ps;
    }
    
    public PersonalTraining obtenerPersonalTrainingID(PersonalTraining ps){
        try{
            CallableStatement cst = con.getCon().prepareCall("{call obtenerPersonalTrainingId(?)}");
            cst.setInt(1, ps.getId());
            cst.execute();
            final ResultSet rs = cst.getResultSet();

            if(rs.next())
                return cargarPersonal(rs);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar buscar el Personal Training :(\n"+e,"Error x.x",JOptionPane.ERROR_MESSAGE);
            return null;
        }finally{
            con.closeConnection();
        }
        
        return ps;
    }

    private PersonalTraining cargarPersonal(ResultSet rs) throws SQLException {
        PersonalTraining pt = new PersonalTraining();
        pt.setId(rs.getInt(1));
        pt.setRut(rs.getString(2));
        pt.setPin(rs.getString(3));
        pt.setFoto(rs.getString(4));
        pt.setNombre(rs.getString(5));
        pt.setApellidoP(rs.getString(6));
        pt.setApellidoM(rs.getString(7));
        pt.setDireccion(rs.getString(8));
        pt.setCorreo(rs.getString(9));
        pt.setFono(rs.getString(10));
        pt.setTipo(rs.getString(11));
        pt.setFechaNacimiento(rs.getDate(12));
        pt.setExtension(rs.getString(13));
        return pt;
    }

}

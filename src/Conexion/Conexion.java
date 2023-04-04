package Conexion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 * @author keiss
 */
public final class Conexion {   
    private Connection con = null;
    
    public Conexion(){
        this.getCon();
    }
 /******Metodo Abrir Conexion BBD
     * @return D*****/   
    public Connection getCon() {
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = (Connection) DriverManager.getConnection("jdbc:sqlserver://;databaseName=PortMedics;user=sa; password=1234;");
        }
        catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, "Ocurrió un error! "+e.getMessage(),"Error de conexión",JOptionPane.ERROR_MESSAGE);
        }
        return con;
    }
  /******Metodo para Cerrar Conexion BBDD*****/  
    public void closeConnection(){
        try{
            con.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Ocurrió un error! "+e.getMessage(),"Error de conexión",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public CallableStatement ejecutaPA(String query){
        try {
            CallableStatement cst = con.prepareCall(query);
            cst.execute(query);
            return cst;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error! "+e.getMessage(),"Error de conexión",JOptionPane.ERROR_MESSAGE);
            return null;
        }  
    }
  /******Busca registros en la BBD
     * @param sql 
     * @return ****/ 
//    public boolean ejecutaSQLinstruccion(String sql){
//        try{
//            Statement s = (Statement) con.createStatement();
//            s.execute(sql);
//            return true;
//        }catch(SQLException e){
//            JOptionPane.showMessageDialog(null, "Está registrado, favor ingresa otro.","Objeto ya registrado!",JOptionPane.ERROR_MESSAGE);
//            return false;
//        }
//    }
  /******Genera consulta a la  BBD
     * @param query  
     * @return ****/  
//    public ResultSet ejecutaConsultaQuery(String query){
//        try{
//            Statement s = (Statement) con.createStatement();
//            ResultSet rs = s.executeQuery(query);
//            return rs;
//        }catch(SQLException e){
//            JOptionPane.showMessageDialog(null, "Ocurrió un error! "+e.getMessage(),"Error de conexión",JOptionPane.ERROR_MESSAGE);
//            return null;
//        }
//    }
    
}
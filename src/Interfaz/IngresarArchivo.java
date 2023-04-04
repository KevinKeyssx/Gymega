package Interfaz;

import Clases.Dieta;
//import Clases.Foto;
import Clases.Ruta;
import Clases.Rutina;
import Extra.Obtener;
import Extra.Validacion;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * @author keiss
 */
public class IngresarArchivo extends javax.swing.JDialog {

    /**
     * Creates new form IngresarDieta
     * @param parent
     * @param modal
     * @param insert
     */
    
    Obtener get = new Obtener();
    Validacion valida = new Validacion();
    static boolean insert;
    JFileChooser seleccionar = new JFileChooser();
    File archivo;
    static boolean inser, modi, inmo;
    String extension, direccion, imagen, ruta_;//Obtenemos la extension del arvivo
    int id;
    
    public IngresarArchivo(javax.swing.JDialog parent, boolean modal, boolean insert) {
        super(parent, modal);
        initComponents();
        this.insert = insert;
        iniciarComponentes(insert);
        inmo = false;
    }
    
    public IngresarArchivo(javax.swing.JDialog parent, boolean modal, boolean modifica, String nombre) {
        super(parent, modal);
        initComponents();
        modi = modifica;
        
        iniciarComponentes(modifica);
        
        if (modifica) 
            modificaDieta(nombre);
        else
            modificaRutina(nombre);
        
        inmo = true;
    }
    
    public IngresarArchivo(javax.swing.JDialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    private void iniciarComponentes(boolean insert) {
        setLocationRelativeTo(null); //Centrar el JFrame al centro de la pantalla
        //setIconImage(new ImageIcon(getClass().getResource("/Complementos/Logo1.png")).getImage()); // Icono del programa
        this.setResizable(false);
        setSize(720, 510);
        
        if (insert) 
            setTitle("Archivo Dieta - PortMedics");
        else
            setTitle("Archivo Rutina - PortMedics");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        txt_Ruta = new Extra.TextPlaceholder();
        btn_BuscarArchivo = new javax.swing.JButton();
        lbl_Ruta = new javax.swing.JLabel();
        txt_Nombre = new Extra.TextPlaceholder();
        btn_RevisarArchivo = new javax.swing.JButton();
        lbl_FormatoArchivo = new javax.swing.JLabel();
        lbl_Nombre = new javax.swing.JLabel();
        lbl_Logo = new javax.swing.JLabel();
        btn_Volver = new javax.swing.JButton();
        btn_Aceptar = new javax.swing.JButton();
        lbl_Fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLayeredPane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_Ruta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        txt_Ruta.setForeground(new java.awt.Color(0, 204, 255));
        txt_Ruta.setToolTipText("Busca la ruta del archivo...");
        txt_Ruta.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        txt_Ruta.setPlaceholder("Ruta");
        txt_Ruta.setSelectedTextColor(new java.awt.Color(255, 0, 0));
        txt_Ruta.setSelectionColor(new java.awt.Color(255, 255, 51));
        txt_Ruta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_RutaFocusLost(evt);
            }
        });
        txt_Ruta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_RutaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_RutaKeyTyped(evt);
            }
        });
        jLayeredPane1.add(txt_Ruta, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 430, -1));

        btn_BuscarArchivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Lupa.png"))); // NOI18N
        btn_BuscarArchivo.setBorderPainted(false);
        btn_BuscarArchivo.setContentAreaFilled(false);
        btn_BuscarArchivo.setFocusPainted(false);
        btn_BuscarArchivo.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Lupa Mouse.png"))); // NOI18N
        btn_BuscarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_BuscarArchivoActionPerformed(evt);
            }
        });
        jLayeredPane1.add(btn_BuscarArchivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 80, 40, 50));

        lbl_Ruta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Ruta.png"))); // NOI18N
        jLayeredPane1.add(lbl_Ruta, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, 30));

        txt_Nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        txt_Nombre.setForeground(new java.awt.Color(0, 204, 255));
        txt_Nombre.setToolTipText("Ingresa el nombre del archivo...");
        txt_Nombre.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        txt_Nombre.setPlaceholder("Nombre del Archivo");
        txt_Nombre.setSelectedTextColor(new java.awt.Color(255, 0, 0));
        txt_Nombre.setSelectionColor(new java.awt.Color(255, 255, 51));
        jLayeredPane1.add(txt_Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 320, -1));

        btn_RevisarArchivo.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        btn_RevisarArchivo.setForeground(new java.awt.Color(0, 153, 255));
        btn_RevisarArchivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Revisar.png"))); // NOI18N
        btn_RevisarArchivo.setText("Revisar");
        btn_RevisarArchivo.setBorderPainted(false);
        btn_RevisarArchivo.setContentAreaFilled(false);
        btn_RevisarArchivo.setFocusPainted(false);
        btn_RevisarArchivo.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Revisar Mouse.png"))); // NOI18N
        btn_RevisarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RevisarArchivoActionPerformed(evt);
            }
        });
        jLayeredPane1.add(btn_RevisarArchivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 160, 70));

        lbl_FormatoArchivo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_FormatoArchivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/UNKNOWN-3.png"))); // NOI18N
        lbl_FormatoArchivo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));
        jLayeredPane1.add(lbl_FormatoArchivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 590, 90));

        lbl_Nombre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Nombre.png"))); // NOI18N
        jLayeredPane1.add(lbl_Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, 30));

        getContentPane().add(jLayeredPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 650, 320));

        lbl_Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/1.png"))); // NOI18N
        getContentPane().add(lbl_Logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, -1));

        btn_Volver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Volver.png"))); // NOI18N
        btn_Volver.setBorderPainted(false);
        btn_Volver.setContentAreaFilled(false);
        btn_Volver.setFocusPainted(false);
        btn_Volver.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Volver Mouse.png"))); // NOI18N
        btn_Volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_VolverActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Volver, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 410, 70, 70));

        btn_Aceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Aceptar.png"))); // NOI18N
        btn_Aceptar.setBorderPainted(false);
        btn_Aceptar.setContentAreaFilled(false);
        btn_Aceptar.setFocusPainted(false);
        btn_Aceptar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Aceptar Press2.png"))); // NOI18N
        btn_Aceptar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Aceptar Mouse.png"))); // NOI18N
        btn_Aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AceptarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 420, 180, 40));

        lbl_Fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Menu.jpg"))); // NOI18N
        lbl_Fondo.setText("jLabel1");
        getContentPane().add(lbl_Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 490));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_RutaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_RutaFocusLost
//        System.out.println("Perdio el foco");
    }//GEN-LAST:event_txt_RutaFocusLost

    private void txt_RutaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_RutaKeyPressed

    }//GEN-LAST:event_txt_RutaKeyPressed

    private void txt_RutaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_RutaKeyTyped
        if(!Character.isDigit(evt.getKeyChar()))
            evt.consume();
    }//GEN-LAST:event_txt_RutaKeyTyped

    private void btn_VolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_VolverActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btn_VolverActionPerformed

    private void btn_AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AceptarActionPerformed
        guardar();
    }//GEN-LAST:event_btn_AceptarActionPerformed

    private void btn_BuscarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BuscarArchivoActionPerformed
        if (seleccionar.showDialog(null, null) == JFileChooser.APPROVE_OPTION) {
            archivo = seleccionar.getSelectedFile();
            if (archivo.canRead()) {
                if (!archivo.getName().endsWith("png")){
                    txt_Ruta.setText(String.valueOf(archivo.getAbsolutePath()));
                    imagen = System.getProperty("user.dir") + "\\Ext";
                    cargarFoto(imagen + "\\" + get.extensionFoto(get.extensionArchivo(archivo.getName())));
                }else
                    JOptionPane.showMessageDialog(null, "Archivo no compatible.","Precaución",JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_BuscarArchivoActionPerformed

    private void btn_RevisarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RevisarArchivoActionPerformed
        get.abrirArchivo(txt_Ruta.getText());
    }//GEN-LAST:event_btn_RevisarArchivoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IngresarArchivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IngresarArchivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IngresarArchivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IngresarArchivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                IngresarArchivo dialog = new IngresarArchivo(new javax.swing.JDialog(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Aceptar;
    private javax.swing.JButton btn_BuscarArchivo;
    private javax.swing.JButton btn_RevisarArchivo;
    private javax.swing.JButton btn_Volver;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLabel lbl_Fondo;
    private javax.swing.JLabel lbl_FormatoArchivo;
    private javax.swing.JLabel lbl_Logo;
    private javax.swing.JLabel lbl_Nombre;
    private javax.swing.JLabel lbl_Ruta;
    private Extra.TextPlaceholder txt_Nombre;
    private Extra.TextPlaceholder txt_Ruta;
    // End of variables declaration//GEN-END:variables

    private boolean vacio() {
        txt_Ruta.setBorder(BorderFactory.createLineBorder(valida.textoColorVacio(txt_Ruta.getText()), 1));
        return txt_Ruta.getText().isEmpty();
    }
    
    private void guardar() {
        if (!vacio()) {
            if (!inmo) {
                if (insert)
                    ingresarDieta();
                else
                    ingresarRutina();
            }else{
                if (modi) 
                    modificarDieta();
                else
                    modificarRutina();
            }
        }
    }
    
    private void cargarExtension(String nombre){
        try {
            extension = get.extensionArchivo(archivo.getName());//Obtenemos la extension del archivo
            ruta_ = System.getProperty("user.dir") + "\\" + nombre;
        } catch (Exception e) {
            extension = txt_Ruta.getText();
//            Dieta d = new Dieta();
//            d.setNombre(txt_Nombre.getText());
            ruta_ = System.getProperty("user.dir") + "\\" + nombre;
        }   
    }
    
    private void ingresarDieta(){
        cargarExtension("Dietas");

        switch (cargarDieta().ingresar()) {
            case 1:
                if (generarArchivo("Dietas", extension, ruta_, false)) {
                    JOptionPane.showMessageDialog(null, "Se ha creado el nuevo archivo con exito!", "Información :)",JOptionPane.INFORMATION_MESSAGE);
                }
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "El nombre de este archivo ya existe!", "Precaución!",JOptionPane.WARNING_MESSAGE);
                break;
        }
    }

    private void ingresarRutina() {
        cargarExtension("Rutinas");

        switch (cargarRutina().ingresar()) {
            case 1:
                if (generarArchivo("Rutinas", extension, ruta_, false)) {
                    JOptionPane.showMessageDialog(null, "Se ha creado el archivo con exito!", "Información :)",JOptionPane.INFORMATION_MESSAGE);
                }
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "El nombre de este archivo ya existe!", "Precaución!",JOptionPane.WARNING_MESSAGE);
                break;
        }
    }
    
    private Dieta cargarDieta() {
        return new Dieta(
            txt_Nombre.getText(),
            extension,
            new Ruta(ruta_).obtenerRuta().getIdArchivo()
        );
    }
    
    private Dieta cargarDieta(int id) {
        System.out.println("Extension: " + extension);
        return new Dieta(
            id,
            txt_Nombre.getText(),
            extension,
            new Ruta(ruta_).obtenerRuta().getIdArchivo()
        );
    }
    
    private Rutina cargarRutina(){
        return new Rutina(
            txt_Nombre.getText(),
            extension,
            new Ruta(ruta_).obtenerRuta().getIdArchivo()    
        );
    }
    
    private boolean generarArchivo(String ruta, String extension, String ruta_, boolean modificar){
        try {
            new File(System.getProperty("user.dir") + "\\" + ruta).mkdir(); //Creamos la carpeta si no existe
            direccion = ruta_ + "\\" + txt_Nombre.getText() + "." + extension;//Guardamos la nueva ruta del archivo
            
            if (modificar) {
                if (new File(txt_Ruta.getText()).delete()) {
                    JOptionPane.showMessageDialog(null, "Se borro" ,"Error! x.x",JOptionPane.ERROR_MESSAGE);
                }
                
            }
            
            Path origen = Paths.get(txt_Ruta.getText());//Guardamos la ruta original del archivo
            Path destino = Paths.get(direccion);//Copiamos el nunevo archivo en la nueva ruta
            Files.copy(origen, destino); //Copia el archivo
            return true;
        } catch (IOException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al tratar de crear el archivo! :(\n" + e.getMessage(),"Error! x.x",JOptionPane.ERROR_MESSAGE);
            return false;
        }        
    }
    
    private void cargarFoto(String ruta) {
        ImageIcon img = new ImageIcon(ruta); 
        lbl_FormatoArchivo.setIcon(new ImageIcon(img.getImage(), ruta));
    }

    private void modificaDieta(String nombre) {
        Dieta dieta = new Dieta();
        
        dieta.setNombre(nombre);
        dieta.obtenerDieta();
        id = dieta.getId();
        txt_Nombre.setText(dieta.getNombre());
        txt_Ruta.setText(dieta.getRuta());
    }

    private void modificaRutina(String nombre) {
        Rutina rutina = new Rutina();
        rutina.setNombre(nombre);
        rutina.obtenerRutina();
        id = rutina.getId();
        txt_Nombre.setText(rutina.getNombre());
        txt_Ruta.setText(rutina.getRuta());
    }

    private void modificarDieta() {
        cargarExtension("Dietas");
        switch (cargarDieta(id).modificar()) {
            case 1:
                if (generarArchivo("Dietas", extension, ruta_, true)) {
                    JOptionPane.showMessageDialog(null, "La dieta se ha modificado correctamente","Información :D",JOptionPane.INFORMATION_MESSAGE);
                }
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "El nombre de la dieta no existe!","Precaución D:",JOptionPane.WARNING_MESSAGE);
                break;
            case 3:
                JOptionPane.showMessageDialog(null, "La dieta no existe!","Precaución D:",JOptionPane.WARNING_MESSAGE);
                break;
        }
    }

    private void modificarRutina() {
        cargarExtension("Rutinas");
        switch (cargarRutina().modificar()) {
            case 1:
                if (generarArchivo("Rutinas", extension, ruta_, true)) {
                    JOptionPane.showMessageDialog(null, "La rutina se ha modificado correctamente","Información :D",JOptionPane.INFORMATION_MESSAGE);
                }
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "El nombre de la rutina no existe!","Error! x.x",JOptionPane.WARNING_MESSAGE);
                break;
        }
    }
}
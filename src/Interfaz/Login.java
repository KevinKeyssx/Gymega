package Interfaz;

import Clases.HuellaDigital;
import Clases.IniciarSesion;
import Clases.PersonalTraining;
import Extra.Obtener;
import Extra.Validacion;
import static Interfaz.IDHuellaDigital.TEMPLATE_PROPERTY;
import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.event.DPFPDataAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.capture.event.DPFPErrorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPErrorEvent;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import com.digitalpersona.onetouch.verification.DPFPVerification;
import com.digitalpersona.onetouch.verification.DPFPVerificationResult;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * @author keiss
 */
public class Login extends javax.swing.JFrame {

    Validacion validar = new Validacion();
    Obtener get = new Obtener();
    int posx, posy;
    
    public Login() {
        initComponents();
        setLocationRelativeTo(null);
        IniciarSesion iniciar = new IniciarSesion().obtenerIniciarSesion();

        if (iniciar.isRecordar()){
            ckb_Recordar.setSelected(true);
            txt_Usuario.setText(iniciar.getUsuario());
            txt_Contraseña.setText(iniciar.getContraseña());
            txt_Contraseña.requestFocus();
        }
    }
    
    private final DPFPCapture Lector = DPFPGlobal.getCaptureFactory().createCapture();
    private final DPFPVerification Verificador = DPFPGlobal.getVerificationFactory().createVerification();
    private DPFPTemplate template;
    public DPFPFeatureSet featureinscripcion;
    public DPFPFeatureSet featureverificacion;   
    
    protected void Iniciar(){
        Lector.addDataListener(new DPFPDataAdapter(){
            @Override
            public void dataAcquired(final DPFPDataEvent e){
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        get.reproducirSonido("Exitoso");              
                        procesarCaptura(e.getSample());
                    }
                });
            }
        });
        
        Lector.addReaderStatusListener(new DPFPReaderStatusAdapter() {
            @Override
            public void readerConnected(DPFPReaderStatusEvent dpfprs) {
                SwingUtilities.invokeLater(() -> {
                    get.reproducirSonido("Exitoso");
                });
            }

            @Override
            public void readerDisconnected(DPFPReaderStatusEvent dpfprs) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        //get.reproducirSonido("Denegado");
                    }
                });
            }
        });

        Lector.addErrorListener(new DPFPErrorAdapter(){
            public void errorReader(final DPFPErrorEvent e){
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        //get.reproducirSonido("Denegado");
                    }
                });
            }
        });
    }
    
    public DPFPFeatureSet extraerCaracteristicas(DPFPSample sample, DPFPDataPurpose purpose){
        DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
        try { return extractor.createFeatureSet(sample, purpose);} 
        catch (DPFPImageQualityException e) { return null;}
    }
    
    public DPFPTemplate getTemplate(){
        return template;
    }
    
    public void setTemplate(DPFPTemplate template){
        DPFPTemplate old = this.template;
        this.template = template;
        firePropertyChange(TEMPLATE_PROPERTY, old, template);
    }
    
    public void start(){
        Lector.startCapture(); 
    }
    
    public void stop(){
        Lector.stopCapture();
    }
    
    public void procesarCaptura(DPFPSample sample){
        featureinscripcion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);
        featureverificacion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);

        if(featureinscripcion != null){
            //Cargamos el array de las huellas segun la venta abierta
            ArrayList<HuellaDigital> huellaDigital = new HuellaDigital().obtenerHuellaDigital("PersonalTraining");
           
            try {
                for (int i = 0; i < huellaDigital.size(); i++) {
                    DPFPTemplate referenceTemplate = DPFPGlobal.getTemplateFactory().createTemplate((byte[]) huellaDigital.get(i).getHuella());
                    setTemplate(referenceTemplate);
                    DPFPVerificationResult resul = Verificador.verify(featureverificacion, getTemplate());
                    
                    if (resul.isVerified()){
                        PersonalTraining pts = new PersonalTraining(huellaDigital.get(i).getIdUser()).obtenerPersonalTrainingID();
                        txt_Usuario.setText(pts.getRut());
                        txt_Contraseña.setText(pts.getPin());
                        //iniciarSesion();
                    }
                }
            } catch (IllegalArgumentException | HeadlessException e) {
                JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado :(!\n" + e.getMessage(),"Error! x.x",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_Usuario = new Extra.TextPlaceholder();
        txt_Contraseña = new javax.swing.JPasswordField();
        ckb_Recordar = new Extra.MiCheckBox();
        btn_Cerrar = new javax.swing.JButton();
        btn_Minimizar = new javax.swing.JButton();
        lbl_imgFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Mairy Black Oblicua", 2, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 204, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Contraseña.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 230, 120, 30));

        jLabel1.setFont(new java.awt.Font("Mairy Black Oblicua", 2, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Bienvenido.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, 280, -1));

        jLabel3.setFont(new java.awt.Font("Mairy Black Oblicua", 2, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 204, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Usuario.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 170, 90, 30));

        txt_Usuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txt_Usuario.setForeground(new java.awt.Color(0, 204, 255));
        txt_Usuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_Usuario.setToolTipText("Ingresa el usuario...");
        txt_Usuario.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        txt_Usuario.setPlaceholder("Ingrese Usuario");
        txt_Usuario.setSelectedTextColor(new java.awt.Color(255, 0, 0));
        txt_Usuario.setSelectionColor(new java.awt.Color(255, 255, 51));
        txt_Usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_UsuarioKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_UsuarioKeyTyped(evt);
            }
        });
        getContentPane().add(txt_Usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 170, 140, -1));

        txt_Contraseña.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_Contraseña.setForeground(new java.awt.Color(0, 204, 255));
        txt_Contraseña.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_Contraseña.setToolTipText("Ingrese la contraseña...");
        txt_Contraseña.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txt_Contraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_ContraseñaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_ContraseñaKeyTyped(evt);
            }
        });
        getContentPane().add(txt_Contraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 230, 140, 30));

        ckb_Recordar.setForeground(new java.awt.Color(0, 204, 255));
        ckb_Recordar.setText("Recuérdame");
        ckb_Recordar.setFocusPainted(false);
        ckb_Recordar.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        getContentPane().add(ckb_Recordar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 270, -1, 30));

        btn_Cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Botones/Btn-Close.png"))); // NOI18N
        btn_Cerrar.setBorderPainted(false);
        btn_Cerrar.setContentAreaFilled(false);
        btn_Cerrar.setFocusPainted(false);
        btn_Cerrar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Botones/Btn-CloseMouse.png"))); // NOI18N
        btn_Cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CerrarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Cerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 40, 40, -1));

        btn_Minimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Botones/Btn-Minimizar.png"))); // NOI18N
        btn_Minimizar.setBorderPainted(false);
        btn_Minimizar.setContentAreaFilled(false);
        btn_Minimizar.setFocusPainted(false);
        btn_Minimizar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Botones/Btn-MinimizarMouse.png"))); // NOI18N
        btn_Minimizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_MinimizarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Minimizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 40, 30, -1));

        lbl_imgFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Fodos/fondo.png"))); // NOI18N
        lbl_imgFondo.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                lbl_imgFondoMouseDragged(evt);
            }
        });
        lbl_imgFondo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbl_imgFondoMousePressed(evt);
            }
        });
        getContentPane().add(lbl_imgFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_UsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_UsuarioKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            iniciarSesion();
    }//GEN-LAST:event_txt_UsuarioKeyPressed

    private void txt_ContraseñaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_ContraseñaKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            iniciarSesion();
    }//GEN-LAST:event_txt_ContraseñaKeyPressed

    private void txt_UsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_UsuarioKeyTyped
        if (!validar.maximoLargo(txt_Usuario.getText(), 10))
            evt.consume();

        if(Character.isLetter(evt.getKeyChar()) && !String.valueOf(evt.getKeyChar()).equals("k")) 
            evt.consume(); 
    }//GEN-LAST:event_txt_UsuarioKeyTyped

    private void txt_ContraseñaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_ContraseñaKeyTyped
        if (!validar.maximoLargo(txt_Contraseña.getText(), 4))
            evt.consume();
    }//GEN-LAST:event_txt_ContraseñaKeyTyped

    private void btn_MinimizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_MinimizarActionPerformed
        setExtendedState(JFrame.CROSSHAIR_CURSOR);
    }//GEN-LAST:event_btn_MinimizarActionPerformed

    private void btn_CerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CerrarActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btn_CerrarActionPerformed

    private void lbl_imgFondoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_imgFondoMousePressed
        posx = evt.getX();
        posy = evt.getY();
    }//GEN-LAST:event_lbl_imgFondoMousePressed

    private void lbl_imgFondoMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_imgFondoMouseDragged
        this.setLocation(evt.getXOnScreen() - posx, evt.getYOnScreen() - posy);
    }//GEN-LAST:event_lbl_imgFondoMouseDragged

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Iniciar();
        start();
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        stop();
    }//GEN-LAST:event_formWindowClosed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Cerrar;
    private javax.swing.JButton btn_Minimizar;
    private Extra.MiCheckBox ckb_Recordar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lbl_imgFondo;
    private javax.swing.JPasswordField txt_Contraseña;
    private Extra.TextPlaceholder txt_Usuario;
    // End of variables declaration//GEN-END:variables

    private IniciarSesion cargarInicio(){
        return new IniciarSesion(
            txt_Usuario.getText(), 
            txt_Contraseña.getText(), 
            ckb_Recordar.isSelected()
        );
    }

    private void iniciarSesion() {
        switch (cargarInicio().iniciarSesion()) {
            case 1:
                stop();
                this.setVisible(false);
                new Menu(txt_Usuario.getText()).setVisible(true);
            break;
                
            case 2:
                JOptionPane.showMessageDialog(null, "Usuario y/o contraseña son incorrectos!", "Precaución", JOptionPane.WARNING_MESSAGE);
            break;
        }
    }
}
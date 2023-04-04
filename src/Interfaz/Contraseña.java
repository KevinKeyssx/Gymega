package Interfaz;

import Clases.PersonalTraining;
import Extra.Validacion;
import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

/**
 * @author KevinKeyss
 */
public class Contraseña extends javax.swing.JDialog {

    Validacion validar = new Validacion();
    private int idPersonal;
    
    public Contraseña(javax.swing.JDialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public Contraseña(javax.swing.JDialog parent, boolean modal, int idPersonal) {
        super(parent, modal);
        initComponents();
        this.setResizable(false);
        this.setSize(250, 450); 
        setLocationRelativeTo(null);
        setTitle("Cambiar PIN - SportMedics");
        this.idPersonal = idPersonal;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_Pin2 = new javax.swing.JPasswordField();
        txt_Pin1 = new javax.swing.JPasswordField();
        txt_PinActual = new javax.swing.JPasswordField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btn_Volver = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_Pin2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_Pin2.setForeground(new java.awt.Color(0, 204, 255));
        txt_Pin2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_Pin2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        txt_Pin2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_Pin2FocusLost(evt);
            }
        });
        txt_Pin2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_Pin2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_Pin2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_Pin2KeyTyped(evt);
            }
        });
        getContentPane().add(txt_Pin2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, 140, 30));

        txt_Pin1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_Pin1.setForeground(new java.awt.Color(0, 204, 255));
        txt_Pin1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_Pin1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        txt_Pin1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_Pin1FocusLost(evt);
            }
        });
        txt_Pin1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_Pin1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_Pin1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_Pin1KeyTyped(evt);
            }
        });
        getContentPane().add(txt_Pin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 140, 30));

        txt_PinActual.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_PinActual.setForeground(new java.awt.Color(0, 204, 255));
        txt_PinActual.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_PinActual.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        txt_PinActual.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_PinActualFocusLost(evt);
            }
        });
        txt_PinActual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_PinActualKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_PinActualKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_PinActualKeyTyped(evt);
            }
        });
        getContentPane().add(txt_PinActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 140, 30));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 180, 20));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Textos/Cambiar PIN.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 190, -1));

        jLabel3.setFont(new java.awt.Font("Mairy Oblicua", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 204, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Textos/Pin Actual.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, -1, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Nuevo Pin.png"))); // NOI18N
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Confirmar Pin.png"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, -1, -1));

        btn_Volver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Botones/Btn-Volver.png"))); // NOI18N
        btn_Volver.setBorderPainted(false);
        btn_Volver.setContentAreaFilled(false);
        btn_Volver.setFocusPainted(false);
        btn_Volver.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Botones/Btn-VolverMouse.png"))); // NOI18N
        btn_Volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_VolverActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Volver, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Menu.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 440));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_Pin2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_Pin2FocusLost
        txt_Pin2.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(txt_Pin2.getText()), 1));
    }//GEN-LAST:event_txt_Pin2FocusLost

    private void txt_Pin2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Pin2KeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            aceptar();
    }//GEN-LAST:event_txt_Pin2KeyPressed

    private void txt_Pin2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Pin2KeyReleased
        cambiaPin();
    }//GEN-LAST:event_txt_Pin2KeyReleased

    private void txt_Pin2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Pin2KeyTyped
        if (!validar.maximoLargo(txt_Pin2.getText(), 4))
            evt.consume();

        if(Character.isLetter(evt.getKeyChar()))
            evt.consume();
    }//GEN-LAST:event_txt_Pin2KeyTyped

    private void txt_Pin1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_Pin1FocusLost
        txt_Pin1.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(txt_Pin1.getText()), 1));
    }//GEN-LAST:event_txt_Pin1FocusLost

    private void txt_Pin1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Pin1KeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            aceptar();
    }//GEN-LAST:event_txt_Pin1KeyPressed

    private void txt_Pin1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Pin1KeyReleased
        cambiaPin();
         
        if (txt_Pin1.getText().length() == 4) 
            txt_Pin2.grabFocus();
    }//GEN-LAST:event_txt_Pin1KeyReleased

    private void txt_Pin1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Pin1KeyTyped
        if (!validar.maximoLargo(txt_Pin1.getText(), 4))
            evt.consume();

        if(Character.isLetter(evt.getKeyChar())) 
            evt.consume(); 
    }//GEN-LAST:event_txt_Pin1KeyTyped

    private void txt_PinActualFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_PinActualFocusLost
        txt_PinActual.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(txt_PinActual.getText()), 1));
    }//GEN-LAST:event_txt_PinActualFocusLost

    private void txt_PinActualKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_PinActualKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            aceptar();
    }//GEN-LAST:event_txt_PinActualKeyPressed

    private void txt_PinActualKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_PinActualKeyReleased
        cambiaPin();
         
        if (txt_PinActual.getText().length() == 4) 
            txt_Pin1.grabFocus();
    }//GEN-LAST:event_txt_PinActualKeyReleased

    private void txt_PinActualKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_PinActualKeyTyped
        if (!validar.maximoLargo(txt_PinActual.getText(), 4))
            evt.consume();

        if(Character.isLetter(evt.getKeyChar())) 
            evt.consume(); 
    }//GEN-LAST:event_txt_PinActualKeyTyped

    private void btn_VolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_VolverActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btn_VolverActionPerformed

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
            java.util.logging.Logger.getLogger(Contraseña.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Contraseña.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Contraseña.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Contraseña.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Contraseña dialog = new Contraseña(new javax.swing.JDialog(), true);
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
    private javax.swing.JButton btn_Volver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPasswordField txt_Pin1;
    private javax.swing.JPasswordField txt_Pin2;
    private javax.swing.JPasswordField txt_PinActual;
    // End of variables declaration//GEN-END:variables

    private boolean cambiaPin() {
         if (validar.pin(txt_Pin1.getText(), txt_Pin2.getText())){
            txt_Pin1.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
            txt_Pin2.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
            return false;
        }
        else{
            txt_Pin1.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));
            txt_Pin2.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));
            return true;
        }
    }

    private void aceptar() {
        if (!txt_PinActual.getText().equals("")) {
            if (txt_Pin1.getText().equals(txt_Pin2.getText())) {
                switch (cargarPersonal().modificarPin(txt_Pin1.getText())) {
                    case 1:
                        JOptionPane.showMessageDialog(null, "Pin actualizado correctamente! :D","Información :D",JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case -1:
                        JOptionPane.showMessageDialog(null, "Ocurrió un problema al tratar de modificar el PIN, intenta más tarde!", "Precaución D:",JOptionPane.WARNING_MESSAGE);
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(null, "El PIN ingresado es incorrecto!", "Precaución D:",JOptionPane.WARNING_MESSAGE);
                        break;
                }
            }
        }
    }

    private PersonalTraining cargarPersonal() {
        PersonalTraining pt = new PersonalTraining();
        pt.setId(idPersonal);
        pt.setPin(txt_PinActual.getText());
        return pt;
    }
}

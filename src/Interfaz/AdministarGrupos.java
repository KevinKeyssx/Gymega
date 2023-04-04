package Interfaz;

import Clases.Grupo;
import Extra.Obtener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * @author KevinKeyss
 */
public class AdministarGrupos extends javax.swing.JDialog {
    
    Obtener get = new Obtener();
    DefaultTableModel mGrupo;
    
    public AdministarGrupos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        this.setResizable(false);
        this.setSize(586, 679);
        setLocationRelativeTo(null); //Centrar el JFrame al centro de la pantalla
        
        get.moverColumnaTabla(tb_Grupo, false);
        setWidthColumn();
        setHighTable(30);
        tablaGrupo(false);
    }
    
    private void tablaGrupo(boolean busqueda) {
        ArrayList <Grupo> cargarDatosGrupo = new Grupo(txt_Grupo.getText()).buscarGrupos(busqueda);
        mGrupo = (DefaultTableModel)tb_Grupo.getModel();    
        get.remueveFilas(mGrupo, tb_Grupo);
        
        if (cargarDatosGrupo != null || !cargarDatosGrupo.isEmpty()) {
            new DefaultTableCellRenderer().setHorizontalAlignment(SwingConstants.CENTER);//alinea  en el centro datos de la tabla             
            //datos que se cargaran en la tabla
            Object[] fila = new Object[mGrupo.getColumnCount()];
            
            for (int i = 0; i <cargarDatosGrupo.size(); i++) {
                fila[0] = cargarDatosGrupo.get(i).getId();
                fila[1] = cargarDatosGrupo.get(i).getNombre();
                fila[2] = get.concatenaCaracter("" + new Grupo(new Grupo(cargarDatosGrupo.get(i).getNombre()).obtenerGrupo().getId()).obtenerPrecioGrupo(), 3, '.');
                mGrupo.addRow(fila);
            }  
            tb_Grupo.setModel(mGrupo);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane4 = new javax.swing.JScrollPane();
        tb_Grupo = new rojerusan.RSTableMetro();
        txt_Grupo = new Extra.TextPlaceholder();
        txt_EliminarTipo = new javax.swing.JButton();
        lbl_Lupa = new javax.swing.JLabel();
        btn_CrearGrupo = new javax.swing.JButton();
        btn_Volver = new javax.swing.JButton();
        btn_Aceptar = new javax.swing.JButton();
        lbl_PortMedics = new javax.swing.JLabel();
        lbl_Fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tb_Grupo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tb_Grupo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nro", "Grupo", "Monto"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_Grupo.setColorBackgoundHead(new java.awt.Color(0, 153, 204));
        tb_Grupo.setColorBordeFilas(new java.awt.Color(0, 255, 255));
        tb_Grupo.setColorBordeHead(new java.awt.Color(255, 255, 255));
        tb_Grupo.setColorFilasBackgound2(new java.awt.Color(204, 255, 255));
        tb_Grupo.setColorFilasForeground1(new java.awt.Color(0, 153, 255));
        tb_Grupo.setColorFilasForeground2(new java.awt.Color(0, 153, 255));
        tb_Grupo.setColorSelBackgound(new java.awt.Color(0, 102, 204));
        tb_Grupo.setFillsViewportHeight(true);
        tb_Grupo.setFuenteFilas(new java.awt.Font("Mairy Oblicua", 0, 21)); // NOI18N
        tb_Grupo.setFuenteFilasSelect(new java.awt.Font("Mairy Oblicua", 0, 24)); // NOI18N
        tb_Grupo.setFuenteHead(new java.awt.Font("Mairy Bold", 1, 24)); // NOI18N
        tb_Grupo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_GrupoMouseClicked(evt);
            }
        });
        tb_Grupo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tb_GrupoKeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(tb_Grupo);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 490, 380));

        txt_Grupo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txt_Grupo.setForeground(new java.awt.Color(0, 204, 255));
        txt_Grupo.setToolTipText("Busca un Grupo");
        txt_Grupo.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        txt_Grupo.setPlaceholder("Busca un Grupo");
        txt_Grupo.setSelectedTextColor(new java.awt.Color(255, 102, 102));
        txt_Grupo.setSelectionColor(new java.awt.Color(255, 255, 0));
        txt_Grupo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_GrupoKeyReleased(evt);
            }
        });
        getContentPane().add(txt_Grupo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 340, -1));

        txt_EliminarTipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Eliminar Normal.png"))); // NOI18N
        txt_EliminarTipo.setBorderPainted(false);
        txt_EliminarTipo.setContentAreaFilled(false);
        txt_EliminarTipo.setFocusPainted(false);
        txt_EliminarTipo.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Eliminar Normal Pequeño.png"))); // NOI18N
        txt_EliminarTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_EliminarTipoActionPerformed(evt);
            }
        });
        getContentPane().add(txt_EliminarTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 580, 60, 60));

        lbl_Lupa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Lupa.png"))); // NOI18N
        getContentPane().add(lbl_Lupa, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 120, 40, -1));

        btn_CrearGrupo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Botones/Boton Nuevo Grupo.png"))); // NOI18N
        btn_CrearGrupo.setToolTipText("Crear Nuevo Grupo");
        btn_CrearGrupo.setBorderPainted(false);
        btn_CrearGrupo.setContentAreaFilled(false);
        btn_CrearGrupo.setFocusPainted(false);
        btn_CrearGrupo.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Botones/Boton Nuevo Grupo Mouse.png"))); // NOI18N
        btn_CrearGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CrearGrupoActionPerformed(evt);
            }
        });
        getContentPane().add(btn_CrearGrupo, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 110, -1, 50));

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
        getContentPane().add(btn_Volver, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 580, 70, 60));

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
        getContentPane().add(btn_Aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 590, 170, 40));

        lbl_PortMedics.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/1.png"))); // NOI18N
        getContentPane().add(lbl_PortMedics, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        lbl_Fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Menu.jpg"))); // NOI18N
        lbl_Fondo.setText("jLabel1");
        getContentPane().add(lbl_Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 580, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tb_GrupoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_GrupoMouseClicked
        if (evt.getClickCount() == 2)
            abrirGrupo();
    }//GEN-LAST:event_tb_GrupoMouseClicked

    private void txt_GrupoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_GrupoKeyReleased
        tablaGrupo(true);
    }//GEN-LAST:event_txt_GrupoKeyReleased

    private void btn_CrearGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CrearGrupoActionPerformed
        crearGrupo();
    }//GEN-LAST:event_btn_CrearGrupoActionPerformed

    private void btn_VolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_VolverActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btn_VolverActionPerformed

    private void btn_AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AceptarActionPerformed
        abrirGrupo();
    }//GEN-LAST:event_btn_AceptarActionPerformed

    private void txt_EliminarTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_EliminarTipoActionPerformed
        eliminaGrupo();
    }//GEN-LAST:event_txt_EliminarTipoActionPerformed

    private void tb_GrupoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_GrupoKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            modificarGrupo();
    }//GEN-LAST:event_tb_GrupoKeyPressed

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
            java.util.logging.Logger.getLogger(AdministarGrupos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdministarGrupos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdministarGrupos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdministarGrupos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                AdministarGrupos dialog = new AdministarGrupos(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btn_CrearGrupo;
    private javax.swing.JButton btn_Volver;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lbl_Fondo;
    private javax.swing.JLabel lbl_Lupa;
    private javax.swing.JLabel lbl_PortMedics;
    private rojerusan.RSTableMetro tb_Grupo;
    private javax.swing.JButton txt_EliminarTipo;
    private Extra.TextPlaceholder txt_Grupo;
    // End of variables declaration//GEN-END:variables
    //***************************CREA UN NUEVO GRUPO AUTOMATICAMENTE**************
    private void crearGrupo() {
        get.nuevoIngreso(new Grupo().ingresarGrupo(), "Grupo");
        tablaGrupo(false);
    }
    //*****************************MODIFICA EL GRUPO SELECCIONADO****************
    private void modificarGrupo() {
        if( JOptionPane.showConfirmDialog(null, "¿Seguro que desea modificar el Grupo " + cargarGrupo().getId() + " como " + cargarGrupo().getNombre() + " ?" , "Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0){
            if (!cargarGrupo().getNombre().equals("")) {
                get.modificar(cargarGrupo().modificarGrupo(), "Grupo");
                tablaGrupo(false);
            }
            else
                JOptionPane.showMessageDialog(null, "El nombre del grupo no peude quedar vacío :(", "Precaución! D:", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void eliminaGrupo() {
        if (tb_Grupo.getSelectedRow() >= 0){
            if( JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar al grupo *" + cargarGrupo().getNombre()
                    + "* ? Si elimina,\nse borrarán todos los recursos asociados como los alumnos y clases de éste.\n" +
                    "Una vez eliminado el grupo, no se puede recuperar."
                    , "Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0){
                
                switch (cargarGrupo().eliminarGrupo()) {
                    case 1:
                        JOptionPane.showMessageDialog(null, "Grupo eliminado correctamente :)!", "Información! :D", JOptionPane.INFORMATION_MESSAGE);
                        tablaGrupo(false);
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(null, "Este grupo tiene un pago asociado, no se puede eliminar este grupo!", "Precaución! D:", JOptionPane.WARNING_MESSAGE);
                        break;

                    case 3:
                        JOptionPane.showMessageDialog(null, "Este grupo ya no existe D:!", "Precaución! D:", JOptionPane.WARNING_MESSAGE);
                        break;
                }
            }
        }
        else
            JOptionPane.showMessageDialog(null, "Favor, selecciona un grupo :(", "Precaución! D:", JOptionPane.WARNING_MESSAGE);
    }
    
    private Grupo cargarGrupo(){
        return new Grupo(
            Integer.valueOf(tb_Grupo.getValueAt(tb_Grupo.getSelectedRow(), 0).toString()) , tb_Grupo.getValueAt(tb_Grupo.getSelectedRow(), 1).toString()
        );
    }

    private void abrirGrupo() {
        if (tb_Grupo.getSelectedRow() >= 0)
            new AsignarRecursos(this, true, tb_Grupo.getValueAt(tb_Grupo.getSelectedRow(), 1).toString()).setVisible(true);
        else
            JOptionPane.showMessageDialog(null, "Favor, selecciona un grupo :(", "Precaución! D:", JOptionPane.WARNING_MESSAGE);
    }

    private void setWidthColumn() {
        tb_Grupo.getColumnModel().getColumn(0).setPreferredWidth(100); //NUMERO
        tb_Grupo.getColumnModel().getColumn(1).setPreferredWidth(250); //NOMBRE
        tb_Grupo.getColumnModel().getColumn(2).setPreferredWidth(160); //MONTO
    }

    private void setHighTable(int i) {
        tb_Grupo.setRowHeight(i);
    }
}
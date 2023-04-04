package Interfaz;

import Clases.Tipo;
import Extra.Obtener;
import Extra.Validacion;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * @author keiss
 */
public class CrearTipo extends javax.swing.JDialog {

    /**
     * Creates new form CrearTipo
     * @param parent
     * @param modal
     */
    
    Validacion validar = new Validacion();
    Obtener get = new Obtener();
    DefaultTableModel mTipo;
        
    public CrearTipo(javax.swing.JDialog parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setResizable(false);
        this.setSize(690, 545);
        setLocationRelativeTo(null); //Centrar el JFrame al centro de la pantalla
        
        txt_Id.setText(get.cantidadConcatenar('0', 4, new Tipo().obtenerIdMaximo() + ""));
        tablaTipo(false);
        
        tb_Tipo.getColumnModel().getColumn(0).setPreferredWidth(10);//Rut
        tb_Tipo.setRowHeight(30);
    }
    
    private void tablaTipo(boolean busqueda) {
        ArrayList <Tipo> cargarDatosTipo = new Tipo().buscarTipo(busqueda, txt_BuscarTipo.getText());
        mTipo = (DefaultTableModel)tb_Tipo.getModel();    
        get.remueveFilas(mTipo, tb_Tipo);
        
        if (cargarDatosTipo != null || !cargarDatosTipo.isEmpty() && busqueda) {
            new DefaultTableCellRenderer().setHorizontalAlignment(SwingConstants.CENTER);//alinea  en el centro datos de la tabla             
            //datos que se cargaran en la tabla
            Object[] fila = new Object[mTipo.getColumnCount()];
            
            for (int i = 0; i <cargarDatosTipo.size(); i++) {
                fila[0] = get.cantidadConcatenar('0', 4, cargarDatosTipo.get(i).getId() + "") ;
                fila[1] = cargarDatosTipo.get(i).getNombre();
                fila[2] = get.concatenaCaracter(cargarDatosTipo.get(i).getMonto()+"", 3, '.') ;
                mTipo.addRow(fila);
            }    
            tb_Tipo.setModel(mTipo);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_PortMedics = new javax.swing.JLabel();
        lbl_Titulo = new javax.swing.JLabel();
        btn_Volver = new javax.swing.JButton();
        btn_Aceptar = new javax.swing.JButton();
        lbl_Lupa = new javax.swing.JLabel();
        btn_EliminarTipo = new javax.swing.JButton();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        lbl_NombreAlumno = new javax.swing.JLabel();
        lbl_NombreAlumno1 = new javax.swing.JLabel();
        lbl_FechaInicio = new javax.swing.JLabel();
        ckb_AbrirPersonalTraining = new Extra.MiCheckBox();
        btn_AdministrarTipos = new javax.swing.JButton();
        txt_Monto = new Extra.TextPlaceholder();
        txt_Id = new Extra.TextPlaceholder();
        txt_Nombre = new Extra.TextPlaceholder();
        jScrollPane4 = new javax.swing.JScrollPane();
        tb_Tipo = new rojerusan.RSTableMetro();
        txt_BuscarTipo = new Extra.TextPlaceholder();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_PortMedics.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/1.png"))); // NOI18N
        getContentPane().add(lbl_PortMedics, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        lbl_Titulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Crea un nuevo Tipo.png"))); // NOI18N
        getContentPane().add(lbl_Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, 300, 60));

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
        getContentPane().add(btn_Volver, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, 70, 60));

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
        getContentPane().add(btn_Aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 440, 170, 40));

        lbl_Lupa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Lupa.png"))); // NOI18N
        getContentPane().add(lbl_Lupa, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 110, 40, -1));

        btn_EliminarTipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Eliminar Normal.png"))); // NOI18N
        btn_EliminarTipo.setBorderPainted(false);
        btn_EliminarTipo.setContentAreaFilled(false);
        btn_EliminarTipo.setFocusPainted(false);
        btn_EliminarTipo.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Eliminar Normal Pequeño.png"))); // NOI18N
        btn_EliminarTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EliminarTipoActionPerformed(evt);
            }
        });
        getContentPane().add(btn_EliminarTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 430, 60, 60));

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lbl_NombreAlumno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Nro.png"))); // NOI18N
        jLayeredPane1.add(lbl_NombreAlumno);
        lbl_NombreAlumno.setBounds(20, 0, 46, 25);

        lbl_NombreAlumno1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Nombre.png"))); // NOI18N
        jLayeredPane1.add(lbl_NombreAlumno1);
        lbl_NombreAlumno1.setBounds(20, 80, 83, 27);

        lbl_FechaInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Monto.png"))); // NOI18N
        jLayeredPane1.add(lbl_FechaInicio);
        lbl_FechaInicio.setBounds(20, 160, 78, 30);

        ckb_AbrirPersonalTraining.setForeground(new java.awt.Color(0, 204, 255));
        ckb_AbrirPersonalTraining.setText("Clase activa");
        ckb_AbrirPersonalTraining.setFocusPainted(false);
        ckb_AbrirPersonalTraining.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        ckb_AbrirPersonalTraining.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckb_AbrirPersonalTrainingActionPerformed(evt);
            }
        });
        jLayeredPane1.add(ckb_AbrirPersonalTraining);
        ckb_AbrirPersonalTraining.setBounds(20, 250, 105, 27);

        btn_AdministrarTipos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Botones/Boton Nuevo Tipo.png"))); // NOI18N
        btn_AdministrarTipos.setToolTipText("Administar Clases");
        btn_AdministrarTipos.setBorderPainted(false);
        btn_AdministrarTipos.setContentAreaFilled(false);
        btn_AdministrarTipos.setFocusPainted(false);
        btn_AdministrarTipos.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Botones/Boton Nuevo Tipo Mouse.png"))); // NOI18N
        btn_AdministrarTipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AdministrarTiposActionPerformed(evt);
            }
        });
        jLayeredPane1.add(btn_AdministrarTipos);
        btn_AdministrarTipos.setBounds(160, 240, 60, 50);

        txt_Monto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        txt_Monto.setForeground(new java.awt.Color(0, 204, 255));
        txt_Monto.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_Monto.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        txt_Monto.setPlaceholder("Rut");
        txt_Monto.setSelectedTextColor(new java.awt.Color(255, 0, 0));
        txt_Monto.setSelectionColor(new java.awt.Color(255, 255, 51));
        txt_Monto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_MontoFocusLost(evt);
            }
        });
        txt_Monto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_MontoKeyReleased(evt);
            }
        });
        jLayeredPane1.add(txt_Monto);
        txt_Monto.setBounds(20, 200, 180, 30);

        txt_Id.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        txt_Id.setForeground(new java.awt.Color(0, 204, 255));
        txt_Id.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_Id.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        txt_Id.setPlaceholder("Rut");
        txt_Id.setSelectedTextColor(new java.awt.Color(255, 0, 0));
        txt_Id.setSelectionColor(new java.awt.Color(255, 255, 51));
        txt_Id.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_IdFocusLost(evt);
            }
        });
        txt_Id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_IdKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_IdKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_IdKeyTyped(evt);
            }
        });
        jLayeredPane1.add(txt_Id);
        txt_Id.setBounds(20, 30, 180, 30);

        txt_Nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        txt_Nombre.setForeground(new java.awt.Color(0, 204, 255));
        txt_Nombre.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_Nombre.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        txt_Nombre.setPlaceholder("Rut");
        txt_Nombre.setSelectedTextColor(new java.awt.Color(255, 0, 0));
        txt_Nombre.setSelectionColor(new java.awt.Color(255, 255, 51));
        jLayeredPane1.add(txt_Nombre);
        txt_Nombre.setBounds(20, 120, 180, 30);

        getContentPane().add(jLayeredPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 240, 310));

        tb_Tipo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tb_Tipo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nro", "Nombre", "Monto"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_Tipo.setColorBackgoundHead(new java.awt.Color(0, 153, 204));
        tb_Tipo.setColorBordeFilas(new java.awt.Color(0, 255, 255));
        tb_Tipo.setColorBordeHead(new java.awt.Color(255, 255, 255));
        tb_Tipo.setColorFilasBackgound2(new java.awt.Color(204, 255, 255));
        tb_Tipo.setColorFilasForeground1(new java.awt.Color(0, 153, 255));
        tb_Tipo.setColorFilasForeground2(new java.awt.Color(0, 153, 255));
        tb_Tipo.setColorSelBackgound(new java.awt.Color(0, 102, 204));
        tb_Tipo.setFillsViewportHeight(true);
        tb_Tipo.setFuenteFilas(new java.awt.Font("Mairy Oblicua", 0, 21)); // NOI18N
        tb_Tipo.setFuenteFilasSelect(new java.awt.Font("Mairy Oblicua", 0, 24)); // NOI18N
        tb_Tipo.setFuenteHead(new java.awt.Font("Mairy Bold", 1, 24)); // NOI18N
        jScrollPane4.setViewportView(tb_Tipo);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, 350, 260));

        txt_BuscarTipo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txt_BuscarTipo.setForeground(new java.awt.Color(0, 204, 255));
        txt_BuscarTipo.setToolTipText("Busca un Grupo");
        txt_BuscarTipo.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        txt_BuscarTipo.setPlaceholder("Busca un Grupo");
        txt_BuscarTipo.setSelectedTextColor(new java.awt.Color(255, 102, 102));
        txt_BuscarTipo.setSelectionColor(new java.awt.Color(255, 255, 0));
        txt_BuscarTipo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_BuscarTipoKeyReleased(evt);
            }
        });
        getContentPane().add(txt_BuscarTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 110, 300, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Menu.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 520));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_VolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_VolverActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btn_VolverActionPerformed

    private void btn_EliminarTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarTipoActionPerformed
        eliminarTipo();
    }//GEN-LAST:event_btn_EliminarTipoActionPerformed

    private void btn_AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AceptarActionPerformed
        if (!camposVacios()) 
            ingresarTipo();
        if (tb_Tipo.getRowCount() > 0) {
            modificarTipo();
        }
    }//GEN-LAST:event_btn_AceptarActionPerformed

    private void btn_AdministrarTiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AdministrarTiposActionPerformed
        aceptar();
    }//GEN-LAST:event_btn_AdministrarTiposActionPerformed

    private void ckb_AbrirPersonalTrainingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckb_AbrirPersonalTrainingActionPerformed

    }//GEN-LAST:event_ckb_AbrirPersonalTrainingActionPerformed

    private void txt_MontoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_MontoFocusLost
        txt_Monto.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(txt_Monto.getText()), 1));
        validar.rut(txt_Monto);
    }//GEN-LAST:event_txt_MontoFocusLost

    private void txt_MontoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_MontoKeyReleased
        validar.rut(txt_Monto);
        txt_Monto.setText(get.escribirRut(txt_Monto.getText(), txt_Monto));
    }//GEN-LAST:event_txt_MontoKeyReleased

    private void txt_NombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_Rut1FocusLost
        txt_Monto.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(txt_Monto.getText()), 1));
        validar.rut(txt_Monto);
    }//GEN-LAST:event_txt_Rut1FocusLost

    private void txt_NombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Rut1KeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            aceptar();
    }//GEN-LAST:event_txt_Rut1KeyPressed

    private void txt_NombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Rut1KeyTyped
        if (!validar.maximoLargo(get.reemplazo(txt_Monto.getText(), "."), 9))
            evt.consume();

        if(Character.isLetter(evt.getKeyChar()) && !String.valueOf(evt.getKeyChar()).equals("k"))
            evt.consume();
    }//GEN-LAST:event_txt_Rut1KeyTyped

    private void txt_MontoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Rut2KeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            aceptar();
    }//GEN-LAST:event_txt_Rut2KeyPressed

    private void txt_BuscarTipoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_BuscarTipoKeyReleased
        tablaTipo(false);
    }//GEN-LAST:event_txt_BuscarTipoKeyReleased

    private void tb_TipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_GrupoMouseClicked
        if (evt.getClickCount() == 2)
            eliminarTipo();
    }//GEN-LAST:event_tb_GrupoMouseClicked

    private void tb_TipoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_GrupoKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            modificarTipo();
    }//GEN-LAST:event_tb_GrupoKeyPressed

    private void txt_IdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_IdFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_IdFocusLost

    private void txt_IdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_IdKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_IdKeyPressed

    private void txt_IdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_IdKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_IdKeyReleased

    private void txt_IdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_IdKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_IdKeyTyped

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
            java.util.logging.Logger.getLogger(CrearTipo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CrearTipo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CrearTipo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CrearTipo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CrearTipo dialog = new CrearTipo(new javax.swing.JDialog(), true);
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
    private javax.swing.JButton btn_AdministrarTipos;
    private javax.swing.JButton btn_EliminarTipo;
    private javax.swing.JButton btn_Volver;
    private Extra.MiCheckBox ckb_AbrirPersonalTraining;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lbl_FechaInicio;
    private javax.swing.JLabel lbl_Lupa;
    private javax.swing.JLabel lbl_NombreAlumno;
    private javax.swing.JLabel lbl_NombreAlumno1;
    private javax.swing.JLabel lbl_PortMedics;
    private javax.swing.JLabel lbl_Titulo;
    private rojerusan.RSTableMetro tb_Tipo;
    private Extra.TextPlaceholder txt_BuscarTipo;
    private Extra.TextPlaceholder txt_Id;
    private Extra.TextPlaceholder txt_Monto;
    private Extra.TextPlaceholder txt_Nombre;
    // End of variables declaration//GEN-END:variables

    private void aceptar() {
        if (!camposVacios()) 
            ingresarTipo();
        else
            JOptionPane.showMessageDialog(null, "Algunos campos se encuentran vacíos :(","Precaución D:",JOptionPane.WARNING_MESSAGE);
    }

    private void ingresarTipo(){      
        if (cargarTipo().ingresarTipo()) {
            JOptionPane.showMessageDialog(null, "Tipo creado correctamente :D!","Información :)",JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
            txt_Monto.setText(get.cantidadConcatenar('0', 4, new Tipo().obtenerIdMaximo() + ""));
            tablaTipo(false);
        }
        else
            JOptionPane.showMessageDialog(null, "El tipo que tratas de crear, ya existe :(","Precaución D:",JOptionPane.WARNING_MESSAGE);
    }

    private void limpiarCampos() {
        txt_Nombre.setText("");
        txt_Monto.setText("");
    }

    private void modificarTipo() {
        if (tb_Tipo.getSelectedRow() > -1) {
            if (cargarTipoTabla().modificar()) {
                JOptionPane.showMessageDialog(null, "Tipo modificado correctamente :D!", "Información :)", JOptionPane.INFORMATION_MESSAGE);
                tablaTipo(false);
            }
        }
        else
            JOptionPane.showMessageDialog(null, "Favor selecciona una clase!", "¡Precaución!", JOptionPane.WARNING_MESSAGE);
    }

    private void eliminarTipo() {
        if (tb_Tipo.getSelectedRow() > -1){
            if( JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar ésta clase?, es posible que no se elimine si un alumno ya realizo un pago por esta clase.", "Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == 0){
                switch (cargarTipoTabla().eliminarTipo()) {
                    case 1:
                        JOptionPane.showMessageDialog(null, "Clase eliminada correctamente :D!","Información :)",JOptionPane.INFORMATION_MESSAGE);
                        tablaTipo(false);
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(null, "La clase ingresada ya existe D:!","¡Precaución!",JOptionPane.WARNING_MESSAGE);
                        break;
                    case 3:
                        JOptionPane.showMessageDialog(null, "La clase se encuantra asignada a un grupo, imposible eliminar ésta clase :(", "Precaución!",JOptionPane.WARNING_MESSAGE);
                        break;
                }
            }
        }
        else
            JOptionPane.showMessageDialog(null, "Favor selecciona una clase en la lista D:","Precaución :)",JOptionPane.WARNING_MESSAGE);
    }

    private boolean camposVacios() {
        txt_Nombre.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(txt_Nombre.getText()), 1));
        txt_Monto.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(txt_Monto.getText()), 1));
        return txt_Nombre.getText().isEmpty() || txt_Monto.getText().isEmpty();
    }

    private Tipo cargarTipoTabla() {
        return new Tipo(
            Integer.valueOf(tb_Tipo.getValueAt(tb_Tipo.getSelectedRow(), 0).toString()), 
            (String) tb_Tipo.getValueAt(tb_Tipo.getSelectedRow(), 1),
            Integer.valueOf(get.reemplazo(tb_Tipo.getValueAt(tb_Tipo.getSelectedRow(), 2).toString(), ".") ) 
        );
    }
    
    private Tipo cargarTipo(){
        return new Tipo(
            txt_Nombre.getText(), 
            Integer.valueOf(txt_Monto.getText()) 
        );
    }
}

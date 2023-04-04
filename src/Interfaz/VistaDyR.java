package Interfaz;

import Clases.Dieta;
import Clases.Rutina;
import Extra.Obtener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * @author keiss
 */
public class VistaDyR extends javax.swing.JDialog {

    /**
     * Creates new form VistaDyR
     * @param parent
     * @param modal
     */
    
    static String ruta_;
    DefaultTableModel mDieta;
    DefaultTableModel mRutina;
    Obtener get = new Obtener();
    
    public VistaDyR(javax.swing.JDialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        setLocationRelativeTo(null); //Centrar el JFrame al centro de la pantalla
        //setIconImage(new ImageIcon(getClass().getResource("/Complementos/Logo1.png")).getImage()); // Icono del programa
        this.setResizable(false);
        setSize(690, 688); 
        setTitle("Ingresar Personal Training - PortMedics");
        
        tb_Dieta.setRowHeight(40);
        tb_Rutina.setRowHeight(40);
        
        tb_Dieta.getColumnModel().getColumn(0).setPreferredWidth(20);//ID Dieta
        tb_Dieta.getColumnModel().getColumn(1).setPreferredWidth(350);//Nombre Dieta
        tb_Rutina.getColumnModel().getColumn(0).setPreferredWidth(20);//ID Rutina
        tb_Rutina.getColumnModel().getColumn(1).setPreferredWidth(350);//Nombre Rutina
        
        panelVisible();
        
        tablaDieta(false);
        tablaRutina(false);
    }
    
    private void tablaDieta(boolean busqueda) {
        mDieta = (DefaultTableModel)tb_Dieta.getModel();    
        ArrayList <Dieta> cargarDatosDieta = new Dieta().buscarDietas(busqueda, txt_Dieta.getText());
        
        get.remueveFilas(mDieta, tb_Dieta);
        if (cargarDatosDieta != null || !cargarDatosDieta.isEmpty() && busqueda) {
            new DefaultTableCellRenderer().setHorizontalAlignment(SwingConstants.CENTER);//alinea  en el centro datos de la tabla             
            //datos que se cargaran en la tabla
            Object[] fila = new Object[mDieta.getColumnCount()];
            
            for (int i = 0; i <cargarDatosDieta.size(); i++) {
                fila[0] = "." + cargarDatosDieta.get(i).getArchivo();
                fila[1] = cargarDatosDieta.get(i).getNombre();
                mDieta.addRow(fila);
            }  
                
            tb_Dieta.setModel(mDieta);
        }
    }
    
    private void tablaRutina(boolean busqueda) {
        mRutina = (DefaultTableModel)tb_Rutina.getModel();    
        ArrayList <Rutina> cargarDatosRutina = new Rutina().buscarRutinas(busqueda, txt_Rutinas.getText());

        get.remueveFilas(mRutina, tb_Rutina);
        if (cargarDatosRutina != null || !cargarDatosRutina.isEmpty() && busqueda) {
            new DefaultTableCellRenderer().setHorizontalAlignment(SwingConstants.CENTER);//alinea  en el centro datos de la tabla             
            //datos que se cargaran en la tabla
            Object[] fila = new Object[mRutina.getColumnCount()];
            
            for (int i = 0; i <cargarDatosRutina.size(); i++) {
                fila[0] = "." + cargarDatosRutina.get(i).getArchivo();
                fila[1] = cargarDatosRutina.get(i).getNombre();
                mRutina.addRow(fila);
            }  
                
            tb_Rutina.setModel(mRutina);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        btn_Enviar = new javax.swing.JButton();
        btn_Volver = new javax.swing.JButton();
        btn_Agregar = new javax.swing.JButton();
        pnl_Dieta = new javax.swing.JLayeredPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        tb_Dieta = new rojerusan.RSTableMetro();
        txt_Dieta = new Extra.TextPlaceholder();
        lbl_Lupa = new javax.swing.JLabel();
        lbl_BuscarPersonalTraining = new javax.swing.JLabel();
        pnl_Rutina = new javax.swing.JLayeredPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        tb_Rutina = new rojerusan.RSTableMetro();
        lbl_BuscarPersonalTraining1 = new javax.swing.JLabel();
        txt_Rutinas = new Extra.TextPlaceholder();
        lbl_Lupa1 = new javax.swing.JLabel();
        btn_Modificar = new javax.swing.JButton();
        lbl_Dieta = new javax.swing.JLabel();
        lbl_Rutinas = new javax.swing.JLabel();
        tgl_DyR = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/1.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, -1, -1));

        btn_Enviar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Correo.png"))); // NOI18N
        btn_Enviar.setBorderPainted(false);
        btn_Enviar.setContentAreaFilled(false);
        btn_Enviar.setFocusable(false);
        btn_Enviar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Correo Mouse.png"))); // NOI18N
        btn_Enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EnviarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Enviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 590, 50, -1));

        btn_Volver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Volver.png"))); // NOI18N
        btn_Volver.setBorderPainted(false);
        btn_Volver.setContentAreaFilled(false);
        btn_Volver.setFocusPainted(false);
        btn_Volver.setFocusable(false);
        btn_Volver.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Volver Mouse.png"))); // NOI18N
        btn_Volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_VolverActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Volver, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 590, 80, 60));

        btn_Agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Botones/Btn-AddFile.png"))); // NOI18N
        btn_Agregar.setBorderPainted(false);
        btn_Agregar.setContentAreaFilled(false);
        btn_Agregar.setFocusPainted(false);
        btn_Agregar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Botones/Btn-AddFile Mouse.png"))); // NOI18N
        btn_Agregar.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Botones/Btn-AddFile Mouse.png"))); // NOI18N
        btn_Agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AgregarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 590, 70, -1));

        pnl_Dieta.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tb_Dieta.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tb_Dieta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Archivo", "Dietas"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_Dieta.setColorBackgoundHead(new java.awt.Color(0, 153, 204));
        tb_Dieta.setColorBordeFilas(new java.awt.Color(0, 255, 255));
        tb_Dieta.setColorBordeHead(new java.awt.Color(255, 255, 255));
        tb_Dieta.setColorFilasBackgound2(new java.awt.Color(204, 255, 255));
        tb_Dieta.setColorFilasForeground1(new java.awt.Color(51, 153, 255));
        tb_Dieta.setColorFilasForeground2(new java.awt.Color(51, 153, 255));
        tb_Dieta.setColorSelBackgound(new java.awt.Color(51, 153, 255));
        tb_Dieta.setFillsViewportHeight(true);
        tb_Dieta.setFuenteFilas(new java.awt.Font("Mairy Oblicua", 1, 18)); // NOI18N
        tb_Dieta.setFuenteFilasSelect(new java.awt.Font("Mairy Oblicua", 1, 18)); // NOI18N
        tb_Dieta.setFuenteHead(new java.awt.Font("Mairy Bold", 1, 24)); // NOI18N
        tb_Dieta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_DietaMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tb_Dieta);

        pnl_Dieta.add(jScrollPane5);
        jScrollPane5.setBounds(40, 90, 530, 380);

        txt_Dieta.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txt_Dieta.setForeground(new java.awt.Color(0, 204, 255));
        txt_Dieta.setToolTipText("Busca una Dieta..");
        txt_Dieta.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        txt_Dieta.setPlaceholder("Busca una Dieta");
        txt_Dieta.setSelectedTextColor(new java.awt.Color(255, 102, 102));
        txt_Dieta.setSelectionColor(new java.awt.Color(255, 255, 0));
        txt_Dieta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_DietaKeyReleased(evt);
            }
        });
        pnl_Dieta.add(txt_Dieta);
        txt_Dieta.setBounds(170, 40, 340, 30);
        txt_Dieta.getAccessibleContext().setAccessibleDescription("Busca una Dieta...");

        lbl_Lupa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Lupa.png"))); // NOI18N
        pnl_Dieta.add(lbl_Lupa);
        lbl_Lupa.setBounds(530, 30, 40, 50);

        lbl_BuscarPersonalTraining.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Buscar.png"))); // NOI18N
        pnl_Dieta.add(lbl_BuscarPersonalTraining);
        lbl_BuscarPersonalTraining.setBounds(70, 30, 70, 50);

        getContentPane().add(pnl_Dieta, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 610, 490));

        pnl_Rutina.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tb_Rutina.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tb_Rutina.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Archivos", "Rutinas"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_Rutina.setColorBackgoundHead(new java.awt.Color(0, 153, 204));
        tb_Rutina.setColorBordeFilas(new java.awt.Color(0, 255, 255));
        tb_Rutina.setColorBordeHead(new java.awt.Color(255, 255, 255));
        tb_Rutina.setColorFilasBackgound2(new java.awt.Color(204, 255, 255));
        tb_Rutina.setColorFilasForeground1(new java.awt.Color(51, 153, 255));
        tb_Rutina.setColorFilasForeground2(new java.awt.Color(51, 153, 255));
        tb_Rutina.setColorSelBackgound(new java.awt.Color(51, 153, 255));
        tb_Rutina.setFillsViewportHeight(true);
        tb_Rutina.setFuenteFilas(new java.awt.Font("Mairy Oblicua", 1, 18)); // NOI18N
        tb_Rutina.setFuenteFilasSelect(new java.awt.Font("Mairy Oblicua", 1, 18)); // NOI18N
        tb_Rutina.setFuenteHead(new java.awt.Font("Mairy Bold", 1, 24)); // NOI18N
        tb_Rutina.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_RutinaMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tb_Rutina);

        pnl_Rutina.add(jScrollPane6);
        jScrollPane6.setBounds(40, 90, 530, 380);

        lbl_BuscarPersonalTraining1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Buscar.png"))); // NOI18N
        pnl_Rutina.add(lbl_BuscarPersonalTraining1);
        lbl_BuscarPersonalTraining1.setBounds(70, 30, 70, 50);

        txt_Rutinas.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txt_Rutinas.setForeground(new java.awt.Color(0, 204, 255));
        txt_Rutinas.setToolTipText("Busca una Rutina....");
        txt_Rutinas.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        txt_Rutinas.setPlaceholder("Busca una Rutina");
        txt_Rutinas.setSelectedTextColor(new java.awt.Color(255, 102, 102));
        txt_Rutinas.setSelectionColor(new java.awt.Color(255, 255, 0));
        txt_Rutinas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_RutinasKeyReleased(evt);
            }
        });
        pnl_Rutina.add(txt_Rutinas);
        txt_Rutinas.setBounds(170, 40, 340, 30);
        txt_Rutinas.getAccessibleContext().setAccessibleDescription("Busca una Rutina");

        lbl_Lupa1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Lupa.png"))); // NOI18N
        pnl_Rutina.add(lbl_Lupa1);
        lbl_Lupa1.setBounds(530, 30, 40, 50);

        getContentPane().add(pnl_Rutina, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 610, 490));

        btn_Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Botones/Btn-DeleteFile.png"))); // NOI18N
        btn_Modificar.setBorderPainted(false);
        btn_Modificar.setContentAreaFilled(false);
        btn_Modificar.setFocusable(false);
        btn_Modificar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Botones/Btn_DeleteFile Mouse.png"))); // NOI18N
        btn_Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ModificarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 590, 70, -1));

        lbl_Dieta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Textos/Dietas.png"))); // NOI18N
        getContentPane().add(lbl_Dieta, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 60, 20));

        lbl_Rutinas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Textos/Rutinas.png"))); // NOI18N
        getContentPane().add(lbl_Rutinas, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, -1, 20));

        tgl_DyR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Botones/Btn-Off.png"))); // NOI18N
        tgl_DyR.setToolTipText("Cambia para visualizar Dietas o Rutinas");
        tgl_DyR.setBorderPainted(false);
        tgl_DyR.setContentAreaFilled(false);
        tgl_DyR.setFocusPainted(false);
        tgl_DyR.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Botones/Btn-On.png"))); // NOI18N
        tgl_DyR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_DyRActionPerformed(evt);
            }
        });
        getContentPane().add(tgl_DyR, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Menu.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 660));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_EnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EnviarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_EnviarActionPerformed

    private void btn_VolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_VolverActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btn_VolverActionPerformed

    private void btn_AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AgregarActionPerformed
        if (pnl_Dieta.isVisible()){ 
            new IngresarArchivo(new javax.swing.JDialog(), true, true).setVisible(true);
            tablaDieta(false);
        }
        else if (pnl_Rutina.isVisible()) {
            new IngresarArchivo(new javax.swing.JDialog(), true, false).setVisible(true);
            tablaRutina(false);
        }
    }//GEN-LAST:event_btn_AgregarActionPerformed

    private void tb_DietaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_DietaMouseClicked
        if (evt.getClickCount() == 2)
            obtenerRutaDieta();
    }//GEN-LAST:event_tb_DietaMouseClicked

    private void txt_DietaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_DietaKeyReleased
        tablaDieta(true);
    }//GEN-LAST:event_txt_DietaKeyReleased

    private void tb_RutinaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_RutinaMouseClicked
        if (evt.getClickCount() == 2)
            obtenerRutaRutina();
    }//GEN-LAST:event_tb_RutinaMouseClicked

    private void txt_RutinasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_RutinasKeyReleased
        tablaRutina(true);
    }//GEN-LAST:event_txt_RutinasKeyReleased

    private void btn_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ModificarActionPerformed
        eliminarDieta();
        //modificarDieta();
    }//GEN-LAST:event_btn_ModificarActionPerformed

    private void tgl_DyRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_DyRActionPerformed
        panelVisible();
    }//GEN-LAST:event_tgl_DyRActionPerformed

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
            java.util.logging.Logger.getLogger(VistaDyR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaDyR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaDyR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaDyR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VistaDyR dialog = new VistaDyR(new javax.swing.JDialog(), true);
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
    private javax.swing.JButton btn_Agregar;
    private javax.swing.JButton btn_Enviar;
    private javax.swing.JButton btn_Modificar;
    private javax.swing.JButton btn_Volver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lbl_BuscarPersonalTraining;
    private javax.swing.JLabel lbl_BuscarPersonalTraining1;
    private javax.swing.JLabel lbl_Dieta;
    private javax.swing.JLabel lbl_Lupa;
    private javax.swing.JLabel lbl_Lupa1;
    private javax.swing.JLabel lbl_Rutinas;
    private javax.swing.JLayeredPane pnl_Dieta;
    private javax.swing.JLayeredPane pnl_Rutina;
    private rojerusan.RSTableMetro tb_Dieta;
    private rojerusan.RSTableMetro tb_Rutina;
    private javax.swing.JToggleButton tgl_DyR;
    private Extra.TextPlaceholder txt_Dieta;
    private Extra.TextPlaceholder txt_Rutinas;
    // End of variables declaration//GEN-END:variables

    private void desactivarPaneles() {
        pnl_Dieta.setVisible(false);
        pnl_Rutina.setVisible(false);
    }
    
    private void panelVisible() {
        desactivarPaneles();
        textoShow();
        pnl_Dieta.setVisible(!tgl_DyR.isSelected());
        pnl_Rutina.setVisible(tgl_DyR.isSelected());
    }
    
    private void obtenerRutaDieta() {
        Dieta d = new Dieta();  
        d.setNombre(tb_Dieta.getValueAt(tb_Dieta.getSelectedRow(), 1).toString());
        get.abrirArchivo(d.obtenerDieta().getRuta()); 
    }

    private void obtenerRutaRutina() {
        Rutina r = new Rutina();
        r.setNombre(tb_Rutina.getValueAt(tb_Rutina.getSelectedRow(), 1).toString());
        get.abrirArchivo(r.obtenerRutina().getRuta()); 
    }

//    private void modificarDieta() {
//        if (tb_Dieta.getRowCount() > 0 || tb_Rutina.getRowCount() > 0) {//Verificamos si hay filas
//            if (tb_Dieta.getSelectedRow() >= 0 || tb_Rutina.getSelectedRow() > -1) {//Verificamos si selecciono una fila
//                if (!tgl_DyR.isSelected()){ 
//                    new IngresarArchivo(new javax.swing.JDialog(), true, true, tb_Dieta.getValueAt(tb_Dieta.getSelectedRow(), 1).toString()).setVisible(true);
//                    tablaDieta(false);
//                }
//                else{
//                    new IngresarArchivo(new javax.swing.JDialog(), true, false, tb_Rutina.getValueAt(tb_Rutina.getSelectedRow(), 1).toString()).setVisible(true);
//                    tablaRutina(false);
//                }
//            }
//        }
//    }

    private void eliminarDieta() {
        if (verificaSeleccion()) {
            if (!tgl_DyR.isSelected()){ 
                Dieta dieta = new Dieta();
                
                dieta.setNombre(tb_Dieta.getValueAt(tb_Dieta.getSelectedRow(), 1).toString());
                dieta.obtenerDieta();
                switch (dieta.borrar()) {
                    case 1:
                        JOptionPane.showMessageDialog(null, "La dieta se eliminó correctamente","Información :D",JOptionPane.INFORMATION_MESSAGE);
                        new File(dieta.getRuta()).delete();
                        tablaDieta(false);
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(null, "La dieta seleccioada ya no existe!","Precaución D:",JOptionPane.WARNING_MESSAGE);
                        break;
                }
            }
            else{
                Rutina rutina = new Rutina();
                
                rutina.setNombre(tb_Rutina.getValueAt(tb_Rutina.getSelectedRow(), 1).toString());
                rutina.obtenerRutina();
                switch (rutina.borrar()) {
                    case 1:
                        JOptionPane.showMessageDialog(null, "La rutina se eliminó correctamente","Información :D",JOptionPane.INFORMATION_MESSAGE);
                        new File(rutina.getRuta()).delete();
                        tablaRutina(false);
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(null, "La rutina seleccionada da ya no existe!","Precaución D:",JOptionPane.WARNING_MESSAGE);
                        break;
                }
            }
        }
    }
    
    private boolean verificaSeleccion(){
        if (tb_Dieta.getRowCount() > 0 || tb_Rutina.getRowCount() > 0) {//Verificamos si hay filas
            if (tb_Dieta.getSelectedRow() >= 0 || tb_Rutina.getSelectedRow() > -1) {//Verificamos si selecciono una fila
                return true;
            }
            else{
                JOptionPane.showMessageDialog(null, "Favor selecciona una rutina", "Precaución D:",JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Favor selecciona una rutina", "Precaución", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    private void textoShow() {
        lbl_Dieta.setVisible(!tgl_DyR.isSelected());
        lbl_Rutinas.setVisible(tgl_DyR.isSelected());
    }
}
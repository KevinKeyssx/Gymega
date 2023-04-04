package Interfaz;

import Clases.Fecha;
import Extra.Obtener;
import Extra.Validacion;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * @author KevinKeyss
 */
public class AdmnistarFechas extends javax.swing.JDialog {
    
    DefaultTableModel mFeriado;
    Obtener get = new Obtener();
    Validacion validar = new Validacion();
    
    public AdmnistarFechas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        this.setResizable(false);
        this.setSize(360, 550); 
        setLocationRelativeTo(null);
        setTitle("Administrar Fechas - SportMedics");
        
        diasLaborables(new Fecha().buscarDiasLaborables());
        tablaFeriado();
        setFilaAlto(25);
    }
    
    private void tablaFeriado() {
        ArrayList <Fecha> cargarDatos = new Fecha().obtenerDiaFeriado();
        mFeriado = (DefaultTableModel)tb_Feriado.getModel();   
        get.remueveFilas(mFeriado, tb_Feriado);

        if (cargarDatos != null || !cargarDatos.isEmpty()) {
            new DefaultTableCellRenderer().setHorizontalAlignment(SwingConstants.CENTER);//alinea  en el centro datos de la tabla             
            //datos que se cargaran en la tabla
            Object[] fila = new Object[mFeriado.getColumnCount()];

            for (int i = 0; i < cargarDatos.size(); i++) {
                fila[0] = agregaCero(cargarDatos.get(i).getFeriado().getDate());
                fila[1] = agregaCero(cargarDatos.get(i).getFeriado().getMonth() + 1);
                fila[2] = new Fecha().getAño(cargarDatos.get(i).getFeriado().getYear());
                mFeriado.addRow(fila);
            }                
            tb_Feriado.setModel(mFeriado);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dc_DiaFeriado = new com.toedter.calendar.JDateChooser();
        btn_AgregarFeriado = new javax.swing.JButton();
        lbl_DiasFeriados = new javax.swing.JLabel();
        spt_Separador = new javax.swing.JSeparator();
        jScrollPane8 = new javax.swing.JScrollPane();
        tb_Feriado = new rojerusan.RSTableMetro();
        lbl_DiasLaborables = new javax.swing.JLabel();
        ckb_Lunes = new Extra.MiCheckBox();
        ckb_Domingo = new Extra.MiCheckBox();
        ckb_Martes = new Extra.MiCheckBox();
        ckb_Miercoles = new Extra.MiCheckBox();
        ckb_Jueves = new Extra.MiCheckBox();
        ckb_Viernes = new Extra.MiCheckBox();
        ckb_Sabado = new Extra.MiCheckBox();
        lbl_Fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dc_DiaFeriado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        dc_DiaFeriado.setForeground(new java.awt.Color(0, 153, 204));
        dc_DiaFeriado.setFont(new java.awt.Font("Ebrima", 0, 14)); // NOI18N
        getContentPane().add(dc_DiaFeriado, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 230, 30));

        btn_AgregarFeriado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Botones/Btn-IngresarFeriado.png"))); // NOI18N
        btn_AgregarFeriado.setBorderPainted(false);
        btn_AgregarFeriado.setContentAreaFilled(false);
        btn_AgregarFeriado.setFocusPainted(false);
        btn_AgregarFeriado.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Botones/Btn-IngresarFeriadoMouse.png"))); // NOI18N
        btn_AgregarFeriado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AgregarFeriadoActionPerformed(evt);
            }
        });
        getContentPane().add(btn_AgregarFeriado, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, 60, 50));

        lbl_DiasFeriados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Textos/Dias Feriados.png"))); // NOI18N
        getContentPane().add(lbl_DiasFeriados, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, 50));
        getContentPane().add(spt_Separador, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 280, 10));

        tb_Feriado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Día", "Mes", "Año"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class
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
        tb_Feriado.setColorBackgoundHead(new java.awt.Color(0, 153, 204));
        tb_Feriado.setColorBordeFilas(new java.awt.Color(0, 255, 255));
        tb_Feriado.setColorBordeHead(new java.awt.Color(255, 255, 255));
        tb_Feriado.setColorFilasBackgound2(new java.awt.Color(204, 255, 255));
        tb_Feriado.setFillsViewportHeight(true);
        tb_Feriado.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        tb_Feriado.setFuenteFilas(new java.awt.Font("Mairy Oblicua", 1, 18)); // NOI18N
        tb_Feriado.setFuenteFilasSelect(new java.awt.Font("Mairy Black", 1, 18)); // NOI18N
        tb_Feriado.setFuenteHead(new java.awt.Font("Mairy Bold", 1, 24)); // NOI18N
        tb_Feriado.setName("Enfermedad"); // NOI18N
        tb_Feriado.setPreferredSize(new java.awt.Dimension(150, 80));
        tb_Feriado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_FeriadoMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tb_Feriado);

        getContentPane().add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 280, 300));

        lbl_DiasLaborables.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Textos/Dias Laborables.png"))); // NOI18N
        getContentPane().add(lbl_DiasLaborables, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, 40));

        ckb_Lunes.setForeground(new java.awt.Color(0, 204, 255));
        ckb_Lunes.setText("Lu");
        ckb_Lunes.setFocusPainted(false);
        ckb_Lunes.setFont(new java.awt.Font("Mairy Oblicua", 0, 16)); // NOI18N
        ckb_Lunes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckb_LunesActionPerformed(evt);
            }
        });
        getContentPane().add(ckb_Lunes, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        ckb_Domingo.setForeground(new java.awt.Color(0, 204, 255));
        ckb_Domingo.setText("Do");
        ckb_Domingo.setFocusPainted(false);
        ckb_Domingo.setFont(new java.awt.Font("Mairy Oblicua", 0, 16)); // NOI18N
        ckb_Domingo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckb_DomingoActionPerformed(evt);
            }
        });
        getContentPane().add(ckb_Domingo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, -1, -1));

        ckb_Martes.setForeground(new java.awt.Color(0, 204, 255));
        ckb_Martes.setText("Ma");
        ckb_Martes.setFocusPainted(false);
        ckb_Martes.setFont(new java.awt.Font("Mairy Oblicua", 0, 16)); // NOI18N
        ckb_Martes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckb_MartesActionPerformed(evt);
            }
        });
        getContentPane().add(ckb_Martes, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, -1, -1));

        ckb_Miercoles.setForeground(new java.awt.Color(0, 204, 255));
        ckb_Miercoles.setText("Mi");
        ckb_Miercoles.setFocusPainted(false);
        ckb_Miercoles.setFont(new java.awt.Font("Mairy Oblicua", 0, 16)); // NOI18N
        ckb_Miercoles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckb_MiercolesActionPerformed(evt);
            }
        });
        getContentPane().add(ckb_Miercoles, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, -1, -1));

        ckb_Jueves.setForeground(new java.awt.Color(0, 204, 255));
        ckb_Jueves.setText("Ju");
        ckb_Jueves.setFocusPainted(false);
        ckb_Jueves.setFont(new java.awt.Font("Mairy Oblicua", 0, 16)); // NOI18N
        ckb_Jueves.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckb_JuevesActionPerformed(evt);
            }
        });
        getContentPane().add(ckb_Jueves, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, -1, -1));

        ckb_Viernes.setForeground(new java.awt.Color(0, 204, 255));
        ckb_Viernes.setText("Vi");
        ckb_Viernes.setFocusPainted(false);
        ckb_Viernes.setFont(new java.awt.Font("Mairy Oblicua", 0, 16)); // NOI18N
        ckb_Viernes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckb_ViernesActionPerformed(evt);
            }
        });
        getContentPane().add(ckb_Viernes, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, -1, -1));

        ckb_Sabado.setForeground(new java.awt.Color(0, 204, 255));
        ckb_Sabado.setText("Sá");
        ckb_Sabado.setFocusPainted(false);
        ckb_Sabado.setFont(new java.awt.Font("Mairy Oblicua", 0, 16)); // NOI18N
        ckb_Sabado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckb_SabadoActionPerformed(evt);
            }
        });
        getContentPane().add(ckb_Sabado, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, -1, -1));

        lbl_Fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Menu.jpg"))); // NOI18N
        lbl_Fondo.setText("jLabel1");
        getContentPane().add(lbl_Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tb_FeriadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_FeriadoMouseClicked
        if (evt.getClickCount() == 2)
            eliminarFeriado();
    }//GEN-LAST:event_tb_FeriadoMouseClicked

    private void btn_AgregarFeriadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AgregarFeriadoActionPerformed
        ingresarDiaFeriado();
    }//GEN-LAST:event_btn_AgregarFeriadoActionPerformed

    private void ckb_LunesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckb_LunesActionPerformed
        modificarDiasLaborables(ckb_Lunes.getToolTipText(), ckb_Lunes.isSelected());
    }//GEN-LAST:event_ckb_LunesActionPerformed

    private void ckb_MartesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckb_MartesActionPerformed
        modificarDiasLaborables(ckb_Martes.getToolTipText(), ckb_Martes.isSelected());
    }//GEN-LAST:event_ckb_MartesActionPerformed

    private void ckb_MiercolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckb_MiercolesActionPerformed
        modificarDiasLaborables(ckb_Miercoles.getToolTipText(), ckb_Miercoles.isSelected());
    }//GEN-LAST:event_ckb_MiercolesActionPerformed

    private void ckb_JuevesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckb_JuevesActionPerformed
        modificarDiasLaborables(ckb_Jueves.getToolTipText(), ckb_Jueves.isSelected());
    }//GEN-LAST:event_ckb_JuevesActionPerformed

    private void ckb_ViernesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckb_ViernesActionPerformed
        modificarDiasLaborables(ckb_Viernes.getToolTipText(), ckb_Viernes.isSelected());
    }//GEN-LAST:event_ckb_ViernesActionPerformed

    private void ckb_SabadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckb_SabadoActionPerformed
        modificarDiasLaborables(ckb_Sabado.getToolTipText(), ckb_Sabado.isSelected());
    }//GEN-LAST:event_ckb_SabadoActionPerformed

    private void ckb_DomingoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckb_DomingoActionPerformed
        modificarDiasLaborables(ckb_Domingo.getToolTipText(), ckb_Domingo.isSelected());
    }//GEN-LAST:event_ckb_DomingoActionPerformed

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
            java.util.logging.Logger.getLogger(AdmnistarFechas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdmnistarFechas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdmnistarFechas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdmnistarFechas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AdmnistarFechas dialog = new AdmnistarFechas(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btn_AgregarFeriado;
    private Extra.MiCheckBox ckb_Domingo;
    private Extra.MiCheckBox ckb_Jueves;
    private Extra.MiCheckBox ckb_Lunes;
    private Extra.MiCheckBox ckb_Martes;
    private Extra.MiCheckBox ckb_Miercoles;
    private Extra.MiCheckBox ckb_Sabado;
    private Extra.MiCheckBox ckb_Viernes;
    private com.toedter.calendar.JDateChooser dc_DiaFeriado;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JLabel lbl_DiasFeriados;
    private javax.swing.JLabel lbl_DiasLaborables;
    private javax.swing.JLabel lbl_Fondo;
    private javax.swing.JSeparator spt_Separador;
    private rojerusan.RSTableMetro tb_Feriado;
    // End of variables declaration//GEN-END:variables

    private void setFilaAlto(int alto) {
        tb_Feriado.setRowHeight(alto);
    }
    
    private void diasLaborables(ArrayList<Fecha> buscarDiasLaborables) {
        Extra.MiCheckBox ckbblue[] = new Extra.MiCheckBox[]{ckb_Domingo, ckb_Lunes , ckb_Martes, ckb_Miercoles, ckb_Jueves, ckb_Viernes, ckb_Sabado};
        
        for (int i = 0; i < buscarDiasLaborables.size(); i++) {
            ckbblue[i].setSelected(buscarDiasLaborables.get(i).isDiaLaborable());
            ckbblue[i].setToolTipText(buscarDiasLaborables.get(i).getNombreDia());
        }
    }
    
    private void modificarDiasLaborables(String nombreDia, boolean isLaborable){
        if (new Fecha(nombreDia, isLaborable).modificarDiasLaborables()) {
            System.out.println("Se ingreso correctamente");
        }
    }

    private void ingresarDiaFeriado() {
        if (validar.validarFecha(dc_DiaFeriado)) {
            if (new Fecha(dc_DiaFeriado.getDate()).ingresarFeriado() == 1) {
                tablaFeriado();
            }
            else if (new Fecha(dc_DiaFeriado.getDate()).ingresarFeriado() == 2) {
                JOptionPane.showMessageDialog(null, "La fecha ingresada ya existe, intenta ingresar otra! :(", "Precaución D:", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private Object agregaCero(int num) {
        return String.valueOf(num).length() == 1? "0" + num: num;
    }

    private void eliminarFeriado() {
        if (tb_Feriado.countComponents() > 0) {
            if (tb_Feriado.getSelectedRowCount() > 0) {
                if( JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar éste día como feriado?", "Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == 0){
                    if (cargarFeriado().eliminarFeriado() == 1) {
                        JOptionPane.showMessageDialog(null, "El día feriado se ha eliminado correctamente", "Informacion! :D", JOptionPane.INFORMATION_MESSAGE);
                        tablaFeriado();
                    }
                    else
                        JOptionPane.showMessageDialog(null, "La fecha seleccionada no se pudo eliminar, intenta más tarde.", "Precaución D:", JOptionPane.WARNING_MESSAGE);
                }
            }
            else
                JOptionPane.showMessageDialog(null, "Favor selecciona una fila para poder eliminar un fediado.", "Precaución D:", JOptionPane.WARNING_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null, "No exiten dias feriados.", "Precaución D:", JOptionPane.WARNING_MESSAGE);
    }

    private Fecha cargarFeriado() {
        return new Fecha(
            new Fecha().formatoFecha(tb_Feriado.getValueAt(tb_Feriado.getSelectedRow(), 0) + "/" + //DIA
                                     tb_Feriado.getValueAt(tb_Feriado.getSelectedRow(), 1) + "/" + //MES
                                     tb_Feriado.getValueAt(tb_Feriado.getSelectedRow(), 2))        //AÑO
        );
    }
}

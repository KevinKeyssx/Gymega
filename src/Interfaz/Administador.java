package Interfaz;

/**
 * @author Usuario
 */
public class Administador extends javax.swing.JDialog {

    public Administador(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane8 = new javax.swing.JScrollPane();
        tb_Enfermedad = new rojerusan.RSTableMetro();
        jScrollPane9 = new javax.swing.JScrollPane();
        tb_Lesiones = new rojerusan.RSTableMetro();
        jScrollPane10 = new javax.swing.JScrollPane();
        tb_Medicamentos = new rojerusan.RSTableMetro();
        jScrollPane5 = new javax.swing.JScrollPane();
        tb_Alergias = new rojerusan.RSTableMetro();
        txt_Nombre = new Extra.TextPlaceholder();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tb_Enfermedad.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tb_Enfermedad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Enfermedades"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_Enfermedad.setColorBackgoundHead(new java.awt.Color(0, 153, 204));
        tb_Enfermedad.setColorBordeFilas(new java.awt.Color(0, 255, 255));
        tb_Enfermedad.setColorBordeHead(new java.awt.Color(255, 255, 255));
        tb_Enfermedad.setColorFilasBackgound2(new java.awt.Color(204, 255, 255));
        tb_Enfermedad.setFillsViewportHeight(true);
        tb_Enfermedad.setFuenteFilas(new java.awt.Font("Mairy Oblicua", 1, 18)); // NOI18N
        tb_Enfermedad.setFuenteFilasSelect(new java.awt.Font("Mairy Oblicua", 1, 18)); // NOI18N
        tb_Enfermedad.setFuenteHead(new java.awt.Font("Mairy Bold", 1, 24)); // NOI18N
        tb_Enfermedad.setName("Enfermedad"); // NOI18N
        tb_Enfermedad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_EnfermedadMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tb_Enfermedad);

        getContentPane().add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 190, 250));

        tb_Lesiones.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tb_Lesiones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Lesiones"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_Lesiones.setColorBackgoundHead(new java.awt.Color(0, 153, 204));
        tb_Lesiones.setColorBordeFilas(new java.awt.Color(0, 255, 255));
        tb_Lesiones.setColorBordeHead(new java.awt.Color(255, 255, 255));
        tb_Lesiones.setColorFilasBackgound2(new java.awt.Color(204, 255, 255));
        tb_Lesiones.setFillsViewportHeight(true);
        tb_Lesiones.setFuenteFilas(new java.awt.Font("Mairy Oblicua", 1, 18)); // NOI18N
        tb_Lesiones.setFuenteFilasSelect(new java.awt.Font("Mairy Oblicua", 1, 18)); // NOI18N
        tb_Lesiones.setFuenteHead(new java.awt.Font("Mairy Bold", 1, 24)); // NOI18N
        tb_Lesiones.setName("Lesion"); // NOI18N
        tb_Lesiones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_LesionesMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tb_Lesiones);

        getContentPane().add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 230, 190, 250));

        tb_Medicamentos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tb_Medicamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Medicamentos"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_Medicamentos.setColorBackgoundHead(new java.awt.Color(0, 153, 204));
        tb_Medicamentos.setColorBordeFilas(new java.awt.Color(0, 255, 255));
        tb_Medicamentos.setColorBordeHead(new java.awt.Color(255, 255, 255));
        tb_Medicamentos.setColorFilasBackgound2(new java.awt.Color(204, 255, 255));
        tb_Medicamentos.setFillsViewportHeight(true);
        tb_Medicamentos.setFuenteFilas(new java.awt.Font("Mairy Oblicua", 1, 18)); // NOI18N
        tb_Medicamentos.setFuenteFilasSelect(new java.awt.Font("Mairy Oblicua", 1, 18)); // NOI18N
        tb_Medicamentos.setFuenteHead(new java.awt.Font("Mairy Bold", 1, 24)); // NOI18N
        tb_Medicamentos.setName("Medicamento"); // NOI18N
        tb_Medicamentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_MedicamentosMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tb_Medicamentos);

        getContentPane().add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 230, 190, 250));

        tb_Alergias.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tb_Alergias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Alergias"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_Alergias.setColorBackgoundHead(new java.awt.Color(0, 153, 204));
        tb_Alergias.setColorBordeFilas(new java.awt.Color(0, 255, 255));
        tb_Alergias.setColorBordeHead(new java.awt.Color(255, 255, 255));
        tb_Alergias.setColorFilasBackgound2(new java.awt.Color(204, 255, 255));
        tb_Alergias.setFillsViewportHeight(true);
        tb_Alergias.setFuenteFilas(new java.awt.Font("Mairy Oblicua", 1, 18)); // NOI18N
        tb_Alergias.setFuenteFilasSelect(new java.awt.Font("Mairy Oblicua", 1, 18)); // NOI18N
        tb_Alergias.setFuenteHead(new java.awt.Font("Mairy Bold", 1, 24)); // NOI18N
        tb_Alergias.setName("Alergia"); // NOI18N
        tb_Alergias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_AlergiasMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tb_Alergias);

        getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 230, 190, 250));

        txt_Nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        txt_Nombre.setForeground(new java.awt.Color(0, 204, 255));
        txt_Nombre.setToolTipText("Ingresa el nombre del alumno...");
        txt_Nombre.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        txt_Nombre.setPlaceholder("Nombre");
        txt_Nombre.setSelectedTextColor(new java.awt.Color(255, 0, 0));
        txt_Nombre.setSelectionColor(new java.awt.Color(255, 255, 51));
        getContentPane().add(txt_Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, 530, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tb_EnfermedadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_EnfermedadMouseClicked
//        if (evt.getClickCount() == 2)
//            eliminarFisico(tb_Enfermedad);
    }//GEN-LAST:event_tb_EnfermedadMouseClicked

    private void tb_LesionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_LesionesMouseClicked
//        if (evt.getClickCount() == 2)
//            eliminarFisico(tb_Lesiones);
    }//GEN-LAST:event_tb_LesionesMouseClicked

    private void tb_MedicamentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_MedicamentosMouseClicked
//        if (evt.getClickCount() == 2)
//            eliminarFisico(tb_Medicamentos);
    }//GEN-LAST:event_tb_MedicamentosMouseClicked

    private void tb_AlergiasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_AlergiasMouseClicked
//        if (evt.getClickCount() == 2)
//            eliminarFisico(tb_Alergias);
    }//GEN-LAST:event_tb_AlergiasMouseClicked

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
            java.util.logging.Logger.getLogger(Administador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Administador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Administador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Administador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Administador dialog = new Administador(new javax.swing.JFrame(), true);
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
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private rojerusan.RSTableMetro tb_Alergias;
    private rojerusan.RSTableMetro tb_Enfermedad;
    private rojerusan.RSTableMetro tb_Lesiones;
    private rojerusan.RSTableMetro tb_Medicamentos;
    private Extra.TextPlaceholder txt_Nombre;
    // End of variables declaration//GEN-END:variables
}

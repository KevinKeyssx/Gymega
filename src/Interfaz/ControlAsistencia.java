package Interfaz;

import Clases.Alumno;
import Clases.Grupo;
import Clases.HistorialAsistencia;
import Extra.MiRender;
import Extra.Obtener;
import Extra.Validacion;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 * @author keiss
 */
public class ControlAsistencia extends javax.swing.JDialog {
    /**
     * Creates new form Pagos
     * @param parent
     * @param modal
     */
    DefaultTableModel mAsistencia;
    Obtener get = new Obtener();
    Validacion validar = new Validacion();
    private int idAlumno;
    
    public ControlAsistencia(javax.swing.JDialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null); //Centrar el JFrame al centro de la pantalla
        //setIconImage(new ImageIcon(getClass().getResource("/Complementos/Logo1.png")).getImage()); // Icono del programa
        this.setResizable(false);
        setSize(1045, 638); 
        setTitle("Historial de Asistencias - PortMedics");

        tb_Asistencia.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tb_Asistencia.doLayout();
        /**modificar Tamaño de las columnas de la tabla Dieta**/
        sizeColumn(100);
        setTableHeight(40);
        cargarCMBAlumno();
        cargarCMBGrupo();
        tablaAsistencia();
        tb_Asistencia.setDefaultRenderer(Object.class, new MiRender());
    }
    
    public ControlAsistencia(javax.swing.JDialog parent, boolean modal, String rut) {
        super(parent, modal);
        initComponents();
        
        setLocationRelativeTo(null); //Centrar el JFrame al centro de la pantalla
        //setIconImage(new ImageIcon(getClass().getResource("/Complementos/Logo1.png")).getImage()); // Icono del programa
        this.setResizable(false);
        setSize(1045, 638); 
        setTitle("Historial de Asistencias - PortMedics");
        cargarIdxRut(rut);
        
        tb_Asistencia.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tb_Asistencia.doLayout();
        /**modificar Tamaño de las columnas de la tabla Dieta**/
        sizeColumn(100);
        setTableHeight(40);
        cargarCMBAlumno();
        
        tablaAsistencia();
        tb_Asistencia.setDefaultRenderer(Object.class, new MiRender());
        
        for (int i = 0; i < cmb_BuscarAlumno.getItemCount(); i++) {
            if (cmb_BuscarAlumno.getItemAt(i).equals(rut)) 
                cmb_BuscarAlumno.setSelectedIndex(i);
        }
        
        cargarCMBGrupo();
        cargarDetalleGrupo();
    }
    
    private void tablaAsistencia() {
        ArrayList <HistorialAsistencia> cargarAsistencia = new HistorialAsistencia().asistenciasAlumno(idAlumno, (jmc_Mes.getMonth() + 1), jyc_Año.getValue());
        mAsistencia = (DefaultTableModel)tb_Asistencia.getModel();   
        get.remueveFilas(mAsistencia, tb_Asistencia);

        if (cargarAsistencia != null || !cargarAsistencia.isEmpty()) {
            new DefaultTableCellRenderer().setHorizontalAlignment(SwingConstants.CENTER);//alinea  en el centro datos de la tabla             
            //datos que se cargaran en la tabla
            Object[] fila = new Object[mAsistencia.getColumnCount()];
            
            for (int i = 0; i < cargarAsistencia.size(); i++) {
                fila[0] = cargarAsistencia.get(i).getSemana();      //Semana
                fila[1] = cargarAsistencia.get(i).getLunes();       //Lunes
                fila[2] = cargarAsistencia.get(i).getMartes();      //Martes
                fila[3] = cargarAsistencia.get(i).getMiercoles();   //Miércoles
                fila[4] = cargarAsistencia.get(i).getJueves();      //Jueves
                fila[5] = cargarAsistencia.get(i).getViernes();     //Viernes
                fila[6] = cargarAsistencia.get(i).getSabado();      //Sábado
                fila[7] = cargarAsistencia.get(i).getDomigo();      //Domingo
                mAsistencia.addRow(fila);
            }  
                
            tb_Asistencia.setModel(mAsistencia);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_Foto1 = new com.bolivia.label.CLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_Asistencia = new rojerusan.RSTableMetro();
        lbl_DiasRestantes = new javax.swing.JLabel();
        lbl_FechaTermino = new javax.swing.JLabel();
        lbl_MontoPago = new javax.swing.JLabel();
        lbl_FechaInicio = new javax.swing.JLabel();
        lbl_Icono = new javax.swing.JLabel();
        lbl_Inicio = new javax.swing.JLabel();
        lbl_Restantes = new javax.swing.JLabel();
        lbl_Termino = new javax.swing.JLabel();
        lbl_Pago = new javax.swing.JLabel();
        btn_Volver = new javax.swing.JButton();
        lbl_Titulo = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cmb_BuscarAlumno = new javax.swing.JComboBox<>();
        cmb_Grupo = new javax.swing.JComboBox<>();
        jmc_Mes = new com.toedter.calendar.JMonthChooser();
        jyc_Año = new com.toedter.calendar.JYearChooser();
        lbl_Fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_Foto1.setText("Subir Foto");
        lbl_Foto1.setToolTipText("");
        lbl_Foto1.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        lbl_Foto1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbl_Foto1.setLineBorder(5);
        lbl_Foto1.setLineColor(new java.awt.Color(51, 153, 255));
        lbl_Foto1.setOpaque(false);
        getContentPane().add(lbl_Foto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 210, 210));

        tb_Asistencia.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tb_Asistencia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Semana", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_Asistencia.setColorBackgoundHead(new java.awt.Color(0, 153, 153));
        tb_Asistencia.setColorBordeFilas(new java.awt.Color(0, 255, 255));
        tb_Asistencia.setColorBordeHead(new java.awt.Color(255, 255, 255));
        tb_Asistencia.setColorFilasBackgound2(new java.awt.Color(204, 255, 255));
        tb_Asistencia.setFillsViewportHeight(true);
        tb_Asistencia.setFuenteFilas(new java.awt.Font("Mairy Oblicua", 1, 18)); // NOI18N
        tb_Asistencia.setFuenteFilasSelect(new java.awt.Font("Mairy Oblicua", 1, 18)); // NOI18N
        tb_Asistencia.setFuenteHead(new java.awt.Font("Mairy Bold", 1, 24)); // NOI18N
        jScrollPane3.setViewportView(tb_Asistencia);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 230, 700, 300));

        lbl_DiasRestantes.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        lbl_DiasRestantes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Dias Restantes.png"))); // NOI18N
        getContentPane().add(lbl_DiasRestantes, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, -1, 40));

        lbl_FechaTermino.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        lbl_FechaTermino.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Fecha Termino.png"))); // NOI18N
        getContentPane().add(lbl_FechaTermino, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, -1, 40));

        lbl_MontoPago.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        lbl_MontoPago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Monto de Pago.png"))); // NOI18N
        getContentPane().add(lbl_MontoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 470, -1, 40));

        lbl_FechaInicio.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        lbl_FechaInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Fecha Inicio.png"))); // NOI18N
        getContentPane().add(lbl_FechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, -1, 40));

        lbl_Icono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/1.png"))); // NOI18N
        getContentPane().add(lbl_Icono, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 30, -1, -1));

        lbl_Inicio.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        lbl_Inicio.setForeground(new java.awt.Color(0, 204, 255));
        lbl_Inicio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Inicio.setText("29/03/2018");
        getContentPane().add(lbl_Inicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 350, 90, 40));

        lbl_Restantes.setFont(new java.awt.Font("DS-Digital", 1, 18)); // NOI18N
        lbl_Restantes.setForeground(new java.awt.Color(255, 0, 0));
        lbl_Restantes.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Restantes.setText("20");
        getContentPane().add(lbl_Restantes, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 390, 90, 40));

        lbl_Termino.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        lbl_Termino.setForeground(new java.awt.Color(0, 204, 255));
        lbl_Termino.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Termino.setText("29/04/2018");
        getContentPane().add(lbl_Termino, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 430, 90, 40));

        lbl_Pago.setFont(new java.awt.Font("DS-Digital", 1, 18)); // NOI18N
        lbl_Pago.setForeground(new java.awt.Color(0, 102, 102));
        lbl_Pago.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_Pago.setText("$17.500");
        getContentPane().add(lbl_Pago, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 470, 90, 40));

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
        getContentPane().add(btn_Volver, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 520, 70, 70));

        lbl_Titulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Historial de Asistencias.png"))); // NOI18N
        getContentPane().add(lbl_Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 360, 50));

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 204, 255));
        jLabel2.setText("Buscar Alumno:");
        jLayeredPane1.add(jLabel2);
        jLabel2.setBounds(30, 10, 101, 30);

        jLabel3.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 204, 255));
        jLabel3.setText("Selecciona Grupo:");
        jLayeredPane1.add(jLabel3);
        jLabel3.setBounds(260, 10, 117, 30);

        jLabel1.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 255));
        jLabel1.setText("Selecciona Fecha:");
        jLayeredPane1.add(jLabel1);
        jLabel1.setBounds(480, 10, 117, 30);

        cmb_BuscarAlumno.setEditable(true);
        cmb_BuscarAlumno.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        cmb_BuscarAlumno.setForeground(new java.awt.Color(51, 153, 255));
        cmb_BuscarAlumno.setToolTipText("Busca a una persona");
        cmb_BuscarAlumno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        cmb_BuscarAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_BuscarAlumnoActionPerformed(evt);
            }
        });
        jLayeredPane1.add(cmb_BuscarAlumno);
        cmb_BuscarAlumno.setBounds(30, 50, 200, 30);

        cmb_Grupo.setEditable(true);
        cmb_Grupo.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        cmb_Grupo.setForeground(new java.awt.Color(51, 153, 255));
        cmb_Grupo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        cmb_Grupo.setFocusable(false);
        cmb_Grupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_GrupoActionPerformed(evt);
            }
        });
        jLayeredPane1.add(cmb_Grupo);
        cmb_Grupo.setBounds(260, 50, 190, 30);

        jmc_Mes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));
        jmc_Mes.setForeground(new java.awt.Color(0, 204, 255));
        jmc_Mes.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        jmc_Mes.setRequestFocusEnabled(false);
        jmc_Mes.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jmc_MesPropertyChange(evt);
            }
        });
        jLayeredPane1.add(jmc_Mes);
        jmc_Mes.setBounds(480, 50, 126, 30);

        jyc_Año.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        jyc_Año.setFont(new java.awt.Font("DS-Digital", 0, 14)); // NOI18N
        jyc_Año.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jyc_AñoPropertyChange(evt);
            }
        });
        jLayeredPane1.add(jyc_Año);
        jyc_Año.setBounds(620, 50, 50, 30);

        getContentPane().add(jLayeredPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 110, 700, 100));

        lbl_Fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Menu.jpg"))); // NOI18N
        getContentPane().add(lbl_Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1040, 610));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_VolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_VolverActionPerformed
       this.setVisible(false);
    }//GEN-LAST:event_btn_VolverActionPerformed

    private void cmb_GrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_GrupoActionPerformed
        cargarDetalleGrupo();
    }//GEN-LAST:event_cmb_GrupoActionPerformed

    private void cmb_BuscarAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_BuscarAlumnoActionPerformed
        cargarCMBGrupo();
        cargarDetalleGrupo();
        
        if (cmb_BuscarAlumno.getSelectedItem() != null)  {
            if (!cmb_BuscarAlumno.getSelectedItem().toString().isEmpty()) {
                if (cmb_BuscarAlumno.getItemCount() > 0) {
                    cargarIdxRut(String.valueOf(cmb_BuscarAlumno.getSelectedItem()));
                }
            }
        }
        
        tablaAsistencia();
    }//GEN-LAST:event_cmb_BuscarAlumnoActionPerformed

    private void jmc_MesPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jmc_MesPropertyChange
        tablaAsistencia();
    }//GEN-LAST:event_jmc_MesPropertyChange

    private void jyc_AñoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jyc_AñoPropertyChange
        tablaAsistencia();
    }//GEN-LAST:event_jyc_AñoPropertyChange

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
            java.util.logging.Logger.getLogger(ControlAsistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ControlAsistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ControlAsistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ControlAsistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ControlAsistencia dialog = new ControlAsistencia(new javax.swing.JDialog(), true);
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
    private javax.swing.JComboBox<String> cmb_BuscarAlumno;
    private javax.swing.JComboBox<String> cmb_Grupo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private com.toedter.calendar.JMonthChooser jmc_Mes;
    private com.toedter.calendar.JYearChooser jyc_Año;
    private javax.swing.JLabel lbl_DiasRestantes;
    private javax.swing.JLabel lbl_FechaInicio;
    private javax.swing.JLabel lbl_FechaTermino;
    private javax.swing.JLabel lbl_Fondo;
    private com.bolivia.label.CLabel lbl_Foto1;
    private javax.swing.JLabel lbl_Icono;
    private javax.swing.JLabel lbl_Inicio;
    private javax.swing.JLabel lbl_MontoPago;
    private javax.swing.JLabel lbl_Pago;
    private javax.swing.JLabel lbl_Restantes;
    private javax.swing.JLabel lbl_Termino;
    private javax.swing.JLabel lbl_Titulo;
    private rojerusan.RSTableMetro tb_Asistencia;
    // End of variables declaration//GEN-END:variables

    private void sizeColumn(int size) {
        for (int i = 0; i < tb_Asistencia.getColumnCount(); i++) 
            tb_Asistencia.getColumnModel().getColumn(i).setPreferredWidth(size);//Rut
    }

    private void setTableHeight(int i) {
        tb_Asistencia.setRowHeight(i);
    }

    private void cargarCMBGrupo() {
        cmb_Grupo.removeAllItems();
        if (cmb_BuscarAlumno.getItemCount() > 0) {
            ArrayList<Grupo> grupo = new Grupo(String.valueOf(cmb_BuscarAlumno.getSelectedItem())).buscarGrupos(true);
        
            for (int i = 0; i < grupo.size(); i++)
                cmb_Grupo.addItem(grupo.get(i).getNombre());
        }
    }

    private void cargarCMBAlumno() {
        cmb_BuscarAlumno.removeAllItems();
        ArrayList<Alumno> alumno = new Alumno().buscarAlumnos(false, null, false);
        
        for (int i = 0; i < alumno.size(); i++) 
            cmb_BuscarAlumno.addItem(alumno.get(i).getRut());
        
        AutoCompleteDecorator.decorate(cmb_BuscarAlumno);//Auto Completa en el combobox
        cmb_BuscarAlumno.setSelectedIndex(0);
    }

    private void cargarDetalleGrupo() {
        if (cmb_Grupo.getItemCount() > 0) {
            HistorialAsistencia hs = new HistorialAsistencia().detalleGrupo(idAlumno, String.valueOf(cmb_Grupo.getSelectedItem()));
            try {
                lbl_Inicio.setText(hs.getFechaInicio().toString());
                lbl_Restantes.setText(String.valueOf(hs.getDiasRestantes()));
                lbl_Termino.setText(hs.getFechaTermino().toString());
                lbl_Pago.setText(String.valueOf(hs.getMontoPago()));
            } catch (Exception e) {
                lbl_Inicio.setText("--");
                lbl_Restantes.setText("0");
                lbl_Termino.setText("--");
                lbl_Pago.setText("0");
            }
        }
    }

    private void cargarIdxRut(String rut) {
        idAlumno = new Alumno(rut).obtenerAlumno().getId();
    }
}

package Interfaz;

import Clases.Alumno;
import Clases.Grupo;
import Clases.HuellaDigital;
import Clases.PersonalTraining;
import Extra.Obtener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 * @author KevinKeyss
 */
public class AdministrarHuellaDigital extends javax.swing.JDialog {
    /**
     * Creates new form AdministrarHuellaDigital
     * @param parent
     * @param modal
     */
    private final Obtener get = new Obtener();
    private DefaultTableModel mHuella;
    private int id;
    /*Constructor para busqueda general*/
    
    private void constructor(){
        initComponents();
        this.setResizable(false);
        this.setSize(920, 660); 
        this.setLocationRelativeTo(null);
        tb_Huellas.setRowHeight(30);
    }
    
    public AdministrarHuellaDigital(javax.swing.JDialog parent, boolean modal) {
        super(parent, modal);
        constructor();
        cargarCMBPersona();
    }
    /*Constrtucos para persona unica*/
    public AdministrarHuellaDigital(javax.swing.JDialog parent, boolean modal, boolean persona, String rut) {
        super(parent, modal);
        constructor();
        tgl_Persona.setSelected(persona);
        cargarCMBPersona();
        
        for (int i = 0; i < cmb_BuscarPersona.getItemCount(); i++) {
            if (cmb_BuscarPersona.getItemAt(i).equals(rut)) 
                cmb_BuscarPersona.setSelectedIndex(i);
        }
        
        buscarHuellaPesona();
        textShow(tgl_Persona.isSelected());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupo_AlumnoPersonal = new javax.swing.ButtonGroup();
        btn_EliminarHuella = new javax.swing.JButton();
        panel_NombreHuellas = new javax.swing.JLayeredPane();
        jScrollPane8 = new javax.swing.JScrollPane();
        tb_Huellas = new rojerusan.RSTableMetro();
        txt_CantidadHuellas = new Extra.TextPlaceholder();
        lbl_Cantidad = new javax.swing.JLabel();
        btn_Volver = new javax.swing.JButton();
        lbl_Logo = new javax.swing.JLabel();
        pnl_Buscador = new javax.swing.JLayeredPane();
        cmb_BuscarPersona = new javax.swing.JComboBox<>();
        tgl_Persona = new javax.swing.JToggleButton();
        lbl_Alumnos = new javax.swing.JLabel();
        lbl_PersonalTraining = new javax.swing.JLabel();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        btn_MeñiqueDerecho = new javax.swing.JButton();
        txt_HuellaDigital = new Extra.TextPlaceholder();
        btn_IngresarHuella = new javax.swing.JButton();
        btn_MeñiqueIzquierdo = new javax.swing.JButton();
        btn_AnularIzquierdo = new javax.swing.JButton();
        btn_MedioIzquierdo = new javax.swing.JButton();
        btn_IndiceIzquierdo = new javax.swing.JButton();
        btn_PulgarIzquierdo = new javax.swing.JButton();
        btn_PulgarDerecho = new javax.swing.JButton();
        btn_IndiceDerecho = new javax.swing.JButton();
        btn_MedioDerecho = new javax.swing.JButton();
        btn_AnularDerecho = new javax.swing.JButton();
        lbl_Palmas = new javax.swing.JLabel();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        lbl_Rut = new javax.swing.JLabel();
        txt_RutPersona = new Extra.TextPlaceholder();
        lbl_Nombre = new javax.swing.JLabel();
        txt_NombrePersona = new Extra.TextPlaceholder();
        btn_InfoPersona = new javax.swing.JButton();
        lbl_Foto = new com.bolivia.label.CLabel();
        jLabel2 = new javax.swing.JLabel();
        lbl_background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_EliminarHuella.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Botones/Btn-BorrarHuella.png"))); // NOI18N
        btn_EliminarHuella.setToolTipText("Eliminar huella digital");
        btn_EliminarHuella.setBorderPainted(false);
        btn_EliminarHuella.setContentAreaFilled(false);
        btn_EliminarHuella.setFocusable(false);
        btn_EliminarHuella.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Botones/Btn-BorrarHuellaMouse.png"))); // NOI18N
        btn_EliminarHuella.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EliminarHuellaActionPerformed(evt);
            }
        });
        getContentPane().add(btn_EliminarHuella, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 520, 90, 60));

        panel_NombreHuellas.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Huellas Registradas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Mairy Oblicua", 0, 18), new java.awt.Color(0, 204, 255))); // NOI18N

        tb_Huellas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Huellas"
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
        tb_Huellas.setColorBackgoundHead(new java.awt.Color(0, 153, 204));
        tb_Huellas.setColorBordeFilas(new java.awt.Color(0, 255, 255));
        tb_Huellas.setColorBordeHead(new java.awt.Color(255, 255, 255));
        tb_Huellas.setColorFilasBackgound2(new java.awt.Color(204, 255, 255));
        tb_Huellas.setFillsViewportHeight(true);
        tb_Huellas.setFuenteFilas(new java.awt.Font("Mairy Oblicua", 1, 18)); // NOI18N
        tb_Huellas.setFuenteFilasSelect(new java.awt.Font("Mairy Oblicua", 1, 18)); // NOI18N
        tb_Huellas.setFuenteHead(new java.awt.Font("Mairy Bold", 1, 24)); // NOI18N
        tb_Huellas.setName("Enfermedad"); // NOI18N
        tb_Huellas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_HuellasMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tb_Huellas);
        if (tb_Huellas.getColumnModel().getColumnCount() > 0) {
            tb_Huellas.getColumnModel().getColumn(0).setResizable(false);
        }

        panel_NombreHuellas.add(jScrollPane8);
        jScrollPane8.setBounds(30, 30, 200, 240);

        txt_CantidadHuellas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        txt_CantidadHuellas.setForeground(new java.awt.Color(0, 204, 255));
        txt_CantidadHuellas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_CantidadHuellas.setText("0");
        txt_CantidadHuellas.setToolTipText("Cantidad de huellas registradas");
        txt_CantidadHuellas.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        txt_CantidadHuellas.setPlaceholder("Nombre");
        txt_CantidadHuellas.setSelectedTextColor(new java.awt.Color(255, 0, 0));
        txt_CantidadHuellas.setSelectionColor(new java.awt.Color(255, 255, 51));
        txt_CantidadHuellas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_CantidadHuellasFocusLost(evt);
            }
        });
        txt_CantidadHuellas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_CantidadHuellasKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_CantidadHuellasKeyTyped(evt);
            }
        });
        panel_NombreHuellas.add(txt_CantidadHuellas);
        txt_CantidadHuellas.setBounds(150, 290, 70, 32);

        lbl_Cantidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Cantidad.png"))); // NOI18N
        panel_NombreHuellas.add(lbl_Cantidad);
        lbl_Cantidad.setBounds(30, 290, 90, 30);

        getContentPane().add(panel_NombreHuellas, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 260, 350));

        btn_Volver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Volver.png"))); // NOI18N
        btn_Volver.setToolTipText("Volver");
        btn_Volver.setBorderPainted(false);
        btn_Volver.setContentAreaFilled(false);
        btn_Volver.setFocusPainted(false);
        btn_Volver.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Volver Mouse.png"))); // NOI18N
        btn_Volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_VolverActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Volver, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 520, 70, 70));

        lbl_Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/1.png"))); // NOI18N
        getContentPane().add(lbl_Logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 20, -1, -1));

        pnl_Buscador.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Buscar", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Mairy Oblicua", 0, 18), new java.awt.Color(0, 204, 255))); // NOI18N
        pnl_Buscador.setForeground(new java.awt.Color(0, 204, 255));

        cmb_BuscarPersona.setEditable(true);
        cmb_BuscarPersona.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        cmb_BuscarPersona.setToolTipText("Busca a una persona");
        cmb_BuscarPersona.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        cmb_BuscarPersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_BuscarPersonaActionPerformed(evt);
            }
        });
        pnl_Buscador.add(cmb_BuscarPersona);
        cmb_BuscarPersona.setBounds(30, 40, 450, 30);

        tgl_Persona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Botones/Btn-Off.png"))); // NOI18N
        tgl_Persona.setToolTipText("Cambia para visualizar alumnos o personal training");
        tgl_Persona.setBorderPainted(false);
        tgl_Persona.setContentAreaFilled(false);
        tgl_Persona.setFocusPainted(false);
        tgl_Persona.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Botones/Btn-On.png"))); // NOI18N
        tgl_Persona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_PersonaActionPerformed(evt);
            }
        });
        pnl_Buscador.add(tgl_Persona);
        tgl_Persona.setBounds(500, 30, 60, 50);

        lbl_Alumnos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Textos/Alumno.png"))); // NOI18N
        lbl_Alumnos.setName("Alumno"); // NOI18N
        pnl_Buscador.add(lbl_Alumnos);
        lbl_Alumnos.setBounds(30, 10, 90, 27);

        lbl_PersonalTraining.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Textos/Personal Trainings.png"))); // NOI18N
        lbl_PersonalTraining.setName("PT"); // NOI18N
        pnl_Buscador.add(lbl_PersonalTraining);
        lbl_PersonalTraining.setBounds(30, 10, 160, 27);

        getContentPane().add(pnl_Buscador, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 580, 90));

        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Identificador Huella", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Mairy Oblicua", 0, 18), new java.awt.Color(0, 204, 255))); // NOI18N

        btn_MeñiqueDerecho.setBackground(new java.awt.Color(255, 255, 255));
        btn_MeñiqueDerecho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Huellas/Meñique Derecho.png"))); // NOI18N
        btn_MeñiqueDerecho.setToolTipText("Meñique Derecho");
        btn_MeñiqueDerecho.setBorderPainted(false);
        btn_MeñiqueDerecho.setContentAreaFilled(false);
        btn_MeñiqueDerecho.setFocusPainted(false);
        btn_MeñiqueDerecho.setName("Meñique Derecho"); // NOI18N
        btn_MeñiqueDerecho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_MeñiqueDerechoActionPerformed(evt);
            }
        });
        jLayeredPane2.add(btn_MeñiqueDerecho);
        btn_MeñiqueDerecho.setBounds(240, 70, 20, 70);

        txt_HuellaDigital.setEditable(false);
        txt_HuellaDigital.setBackground(new java.awt.Color(255, 255, 255));
        txt_HuellaDigital.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        txt_HuellaDigital.setForeground(new java.awt.Color(0, 204, 255));
        txt_HuellaDigital.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_HuellaDigital.setToolTipText("Nombre de la huella registrada");
        txt_HuellaDigital.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        txt_HuellaDigital.setPlaceholder("Selecciona Huella Digital");
        txt_HuellaDigital.setSelectedTextColor(new java.awt.Color(255, 0, 0));
        txt_HuellaDigital.setSelectionColor(new java.awt.Color(255, 255, 51));
        txt_HuellaDigital.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_HuellaDigitalFocusLost(evt);
            }
        });
        txt_HuellaDigital.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_HuellaDigitalKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_HuellaDigitalKeyTyped(evt);
            }
        });
        jLayeredPane2.add(txt_HuellaDigital);
        txt_HuellaDigital.setBounds(60, 210, 190, 30);

        btn_IngresarHuella.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Manos/Btn-AddHuella.png"))); // NOI18N
        btn_IngresarHuella.setToolTipText("Agregar una nueva huella digital");
        btn_IngresarHuella.setBorderPainted(false);
        btn_IngresarHuella.setContentAreaFilled(false);
        btn_IngresarHuella.setFocusPainted(false);
        btn_IngresarHuella.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Manos/Btn-AddHuellaMouse.png"))); // NOI18N
        btn_IngresarHuella.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_IngresarHuellaActionPerformed(evt);
            }
        });
        jLayeredPane2.add(btn_IngresarHuella);
        btn_IngresarHuella.setBounds(240, 10, 40, 50);

        btn_MeñiqueIzquierdo.setBackground(new java.awt.Color(255, 255, 255));
        btn_MeñiqueIzquierdo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Huellas/Meñique Izquierdo.png"))); // NOI18N
        btn_MeñiqueIzquierdo.setToolTipText("Meñique Izquierdo");
        btn_MeñiqueIzquierdo.setBorderPainted(false);
        btn_MeñiqueIzquierdo.setContentAreaFilled(false);
        btn_MeñiqueIzquierdo.setFocusable(false);
        btn_MeñiqueIzquierdo.setName("Meñique Izquierdo"); // NOI18N
        btn_MeñiqueIzquierdo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_MeñiqueIzquierdoActionPerformed(evt);
            }
        });
        jLayeredPane2.add(btn_MeñiqueIzquierdo);
        btn_MeñiqueIzquierdo.setBounds(50, 70, 20, 70);

        btn_AnularIzquierdo.setBackground(new java.awt.Color(255, 255, 255));
        btn_AnularIzquierdo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Huellas/Anular Izquierdo.png"))); // NOI18N
        btn_AnularIzquierdo.setToolTipText("Anular Izquierdo");
        btn_AnularIzquierdo.setBorderPainted(false);
        btn_AnularIzquierdo.setContentAreaFilled(false);
        btn_AnularIzquierdo.setFocusPainted(false);
        btn_AnularIzquierdo.setName("Anular Izquierdo"); // NOI18N
        btn_AnularIzquierdo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AnularIzquierdoActionPerformed(evt);
            }
        });
        jLayeredPane2.add(btn_AnularIzquierdo);
        btn_AnularIzquierdo.setBounds(70, 50, 20, 90);

        btn_MedioIzquierdo.setBackground(new java.awt.Color(255, 255, 255));
        btn_MedioIzquierdo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Huellas/Medio Izquierdo.png"))); // NOI18N
        btn_MedioIzquierdo.setToolTipText("Medio Izquierdo");
        btn_MedioIzquierdo.setBorderPainted(false);
        btn_MedioIzquierdo.setContentAreaFilled(false);
        btn_MedioIzquierdo.setFocusPainted(false);
        btn_MedioIzquierdo.setName("Medio Izquierdo"); // NOI18N
        btn_MedioIzquierdo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_MedioIzquierdoActionPerformed(evt);
            }
        });
        jLayeredPane2.add(btn_MedioIzquierdo);
        btn_MedioIzquierdo.setBounds(90, 40, 20, 100);

        btn_IndiceIzquierdo.setBackground(new java.awt.Color(255, 255, 255));
        btn_IndiceIzquierdo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Huellas/Indice Izquierdo.png"))); // NOI18N
        btn_IndiceIzquierdo.setToolTipText("Indice Izquierdo");
        btn_IndiceIzquierdo.setBorderPainted(false);
        btn_IndiceIzquierdo.setContentAreaFilled(false);
        btn_IndiceIzquierdo.setFocusPainted(false);
        btn_IndiceIzquierdo.setName("Indice Izquierdo"); // NOI18N
        btn_IndiceIzquierdo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_IndiceIzquierdoActionPerformed(evt);
            }
        });
        jLayeredPane2.add(btn_IndiceIzquierdo);
        btn_IndiceIzquierdo.setBounds(110, 50, 20, 90);

        btn_PulgarIzquierdo.setBackground(new java.awt.Color(255, 255, 255));
        btn_PulgarIzquierdo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Huellas/Pulgar Izquierdo.png"))); // NOI18N
        btn_PulgarIzquierdo.setToolTipText("Pulgar Izquierdo");
        btn_PulgarIzquierdo.setBorderPainted(false);
        btn_PulgarIzquierdo.setContentAreaFilled(false);
        btn_PulgarIzquierdo.setFocusPainted(false);
        btn_PulgarIzquierdo.setName("Pulgar Izquierdo"); // NOI18N
        btn_PulgarIzquierdo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_PulgarIzquierdoActionPerformed(evt);
            }
        });
        jLayeredPane2.add(btn_PulgarIzquierdo);
        btn_PulgarIzquierdo.setBounds(130, 90, 20, 70);

        btn_PulgarDerecho.setBackground(new java.awt.Color(255, 255, 255));
        btn_PulgarDerecho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Huellas/Pulgar Derecho.png"))); // NOI18N
        btn_PulgarDerecho.setToolTipText("Pulgar Derecho");
        btn_PulgarDerecho.setBorderPainted(false);
        btn_PulgarDerecho.setContentAreaFilled(false);
        btn_PulgarDerecho.setFocusPainted(false);
        btn_PulgarDerecho.setName("Pulgar Derecho"); // NOI18N
        btn_PulgarDerecho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_PulgarDerechoActionPerformed(evt);
            }
        });
        jLayeredPane2.add(btn_PulgarDerecho);
        btn_PulgarDerecho.setBounds(160, 90, 20, 70);

        btn_IndiceDerecho.setBackground(new java.awt.Color(255, 255, 255));
        btn_IndiceDerecho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Huellas/Indice Derecho.png"))); // NOI18N
        btn_IndiceDerecho.setToolTipText("Indice Derecho");
        btn_IndiceDerecho.setBorderPainted(false);
        btn_IndiceDerecho.setContentAreaFilled(false);
        btn_IndiceDerecho.setFocusPainted(false);
        btn_IndiceDerecho.setName("Indice Derecho"); // NOI18N
        btn_IndiceDerecho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_IndiceDerechoActionPerformed(evt);
            }
        });
        jLayeredPane2.add(btn_IndiceDerecho);
        btn_IndiceDerecho.setBounds(180, 50, 20, 90);

        btn_MedioDerecho.setBackground(new java.awt.Color(255, 255, 255));
        btn_MedioDerecho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Huellas/Medio Derecho.png"))); // NOI18N
        btn_MedioDerecho.setToolTipText("Medio Derecho");
        btn_MedioDerecho.setBorderPainted(false);
        btn_MedioDerecho.setContentAreaFilled(false);
        btn_MedioDerecho.setFocusPainted(false);
        btn_MedioDerecho.setName("Medio Derecho"); // NOI18N
        btn_MedioDerecho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_MedioDerechoActionPerformed(evt);
            }
        });
        jLayeredPane2.add(btn_MedioDerecho);
        btn_MedioDerecho.setBounds(200, 40, 20, 100);

        btn_AnularDerecho.setBackground(new java.awt.Color(255, 255, 255));
        btn_AnularDerecho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Huellas/Anular Derecho.png"))); // NOI18N
        btn_AnularDerecho.setToolTipText("Anular Derecho");
        btn_AnularDerecho.setBorderPainted(false);
        btn_AnularDerecho.setContentAreaFilled(false);
        btn_AnularDerecho.setFocusPainted(false);
        btn_AnularDerecho.setName("Anular Derecho"); // NOI18N
        btn_AnularDerecho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AnularDerechoActionPerformed(evt);
            }
        });
        jLayeredPane2.add(btn_AnularDerecho);
        btn_AnularDerecho.setBounds(220, 50, 20, 90);

        lbl_Palmas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Manos/PalmasP.png"))); // NOI18N
        jLayeredPane2.add(lbl_Palmas);
        lbl_Palmas.setBounds(50, 50, 210, 140);

        getContentPane().add(jLayeredPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 240, 300, 270));

        jLayeredPane3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Información", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Mairy Oblicua", 0, 18), new java.awt.Color(0, 204, 255))); // NOI18N

        lbl_Rut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Rut.png"))); // NOI18N
        jLayeredPane3.add(lbl_Rut);
        lbl_Rut.setBounds(30, 260, 47, 27);

        txt_RutPersona.setEditable(false);
        txt_RutPersona.setBackground(new java.awt.Color(255, 255, 255));
        txt_RutPersona.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        txt_RutPersona.setForeground(new java.awt.Color(0, 204, 255));
        txt_RutPersona.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_RutPersona.setToolTipText("Rut de la persona");
        txt_RutPersona.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        txt_RutPersona.setPlaceholder("Rut");
        txt_RutPersona.setSelectedTextColor(new java.awt.Color(255, 0, 0));
        txt_RutPersona.setSelectionColor(new java.awt.Color(255, 255, 51));
        txt_RutPersona.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_RutPersonaFocusLost(evt);
            }
        });
        txt_RutPersona.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_RutPersonaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_RutPersonaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_RutPersonaKeyTyped(evt);
            }
        });
        jLayeredPane3.add(txt_RutPersona);
        txt_RutPersona.setBounds(30, 300, 180, 32);

        lbl_Nombre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Nombre.png"))); // NOI18N
        jLayeredPane3.add(lbl_Nombre);
        lbl_Nombre.setBounds(30, 350, 83, 27);

        txt_NombrePersona.setEditable(false);
        txt_NombrePersona.setBackground(new java.awt.Color(255, 255, 255));
        txt_NombrePersona.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        txt_NombrePersona.setForeground(new java.awt.Color(0, 204, 255));
        txt_NombrePersona.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_NombrePersona.setToolTipText("Nombre completo de la persona");
        txt_NombrePersona.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        txt_NombrePersona.setPlaceholder("Nombre");
        txt_NombrePersona.setSelectedTextColor(new java.awt.Color(255, 0, 0));
        txt_NombrePersona.setSelectionColor(new java.awt.Color(255, 255, 51));
        txt_NombrePersona.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_NombrePersonaFocusLost(evt);
            }
        });
        txt_NombrePersona.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_NombrePersonaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_NombrePersonaKeyTyped(evt);
            }
        });
        jLayeredPane3.add(txt_NombrePersona);
        txt_NombrePersona.setBounds(30, 390, 180, 32);

        btn_InfoPersona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Botones/Btn-Info.png"))); // NOI18N
        btn_InfoPersona.setToolTipText("Ver la información de la persona");
        btn_InfoPersona.setBorder(null);
        btn_InfoPersona.setContentAreaFilled(false);
        btn_InfoPersona.setFocusable(false);
        btn_InfoPersona.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Botones/Btn-InfoMouse.png"))); // NOI18N
        btn_InfoPersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_InfoPersonaActionPerformed(evt);
            }
        });
        jLayeredPane3.add(btn_InfoPersona);
        btn_InfoPersona.setBounds(160, 240, 50, 50);

        lbl_Foto.setText("");
        lbl_Foto.setToolTipText("");
        lbl_Foto.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        lbl_Foto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbl_Foto.setLineBorder(5);
        lbl_Foto.setLineColor(new java.awt.Color(51, 153, 255));
        lbl_Foto.setOpaque(false);
        jLayeredPane3.add(lbl_Foto);
        lbl_Foto.setBounds(20, 30, 200, 200);

        getContentPane().add(jLayeredPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 130, 240, 460));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Textos/Administra Huellas Digitales.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, -1));

        lbl_background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Menu.jpg"))); // NOI18N
        lbl_background.setText("jLabel3");
        getContentPane().add(lbl_background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tb_HuellasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_HuellasMouseClicked
        if (evt.getClickCount() == 1)
            txt_HuellaDigital.setText(tb_Huellas.getValueAt(tb_Huellas.getSelectedRow(), 0).toString());
    }//GEN-LAST:event_tb_HuellasMouseClicked

    private void btn_VolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_VolverActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btn_VolverActionPerformed

    private void txt_HuellaDigitalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_HuellaDigitalFocusLost
//        txt_Nombre.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(txt_Nombre.getText()), 1));
    }//GEN-LAST:event_txt_HuellaDigitalFocusLost

    private void txt_HuellaDigitalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_HuellaDigitalKeyPressed
//        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
//        aceptar();
    }//GEN-LAST:event_txt_HuellaDigitalKeyPressed

    private void txt_HuellaDigitalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_HuellaDigitalKeyTyped
//        if (!validar.maximoLargo(txt_Nombre.getText(), 40))
//        evt.consume();
//
//        if(Character.isDigit(evt.getKeyChar()))
//        evt.consume();
//
//        txt_Nombre.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(txt_Nombre.getText()), 1));
    }//GEN-LAST:event_txt_HuellaDigitalKeyTyped

    private void txt_RutPersonaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_RutPersonaFocusLost
//        txt_Rut.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(txt_Rut.getText()), 1));
//        validar.rut(txt_Rut);
    }//GEN-LAST:event_txt_RutPersonaFocusLost

    private void txt_RutPersonaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_RutPersonaKeyPressed
//        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
//        aceptar();
    }//GEN-LAST:event_txt_RutPersonaKeyPressed

    private void txt_RutPersonaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_RutPersonaKeyReleased
//        validar.rut(txt_Rut);
//        txt_Rut.setText(get.escribirRut(txt_Rut.getText(), txt_Rut));
    }//GEN-LAST:event_txt_RutPersonaKeyReleased

    private void txt_RutPersonaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_RutPersonaKeyTyped
//        if (!validar.maximoLargo(get.reemplazo(txt_Rut.getText(), "."), 9))
//        evt.consume();
//
//        if(Character.isLetter(evt.getKeyChar()) && !String.valueOf(evt.getKeyChar()).equals("k"))
//        evt.consume();
    }//GEN-LAST:event_txt_RutPersonaKeyTyped

    private void txt_NombrePersonaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_NombrePersonaFocusLost
//        txt_Nombre.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(txt_Nombre.getText()), 1));
    }//GEN-LAST:event_txt_NombrePersonaFocusLost

    private void txt_NombrePersonaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_NombrePersonaKeyPressed
//        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
//        aceptar();
    }//GEN-LAST:event_txt_NombrePersonaKeyPressed

    private void txt_NombrePersonaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_NombrePersonaKeyTyped
//        if (!validar.maximoLargo(txt_Nombre.getText(), 40))
//        evt.consume();
//
//        if(Character.isDigit(evt.getKeyChar()))
//        evt.consume();
//
//        txt_Nombre.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(txt_Nombre.getText()), 1));
    }//GEN-LAST:event_txt_NombrePersonaKeyTyped

    private void txt_CantidadHuellasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_CantidadHuellasFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_CantidadHuellasFocusLost

    private void txt_CantidadHuellasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_CantidadHuellasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_CantidadHuellasKeyPressed

    private void txt_CantidadHuellasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_CantidadHuellasKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_CantidadHuellasKeyTyped

    private void tgl_PersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_PersonaActionPerformed
        cargarCMBPersona();
    }//GEN-LAST:event_tgl_PersonaActionPerformed

    private void btn_IngresarHuellaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_IngresarHuellaActionPerformed
        new IDHuellaDigital(new javax.swing.JDialog(), true, id, tgl_Persona.isSelected()).setVisible(true);
        buscarHuellaPesona();
    }//GEN-LAST:event_btn_IngresarHuellaActionPerformed

    private void cmb_BuscarPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_BuscarPersonaActionPerformed
        buscarHuellaPesona();
    }//GEN-LAST:event_cmb_BuscarPersonaActionPerformed

    private void btn_IndiceDerechoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_IndiceDerechoActionPerformed
        if (btn_IndiceDerecho.getRolloverIcon() != null) 
            txt_HuellaDigital.setText(btn_IndiceDerecho.getName());
    }//GEN-LAST:event_btn_IndiceDerechoActionPerformed

    private void btn_MeñiqueIzquierdoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_MeñiqueIzquierdoActionPerformed
        if (btn_MeñiqueIzquierdo.getRolloverIcon() != null) 
            txt_HuellaDigital.setText(btn_MeñiqueIzquierdo.getName());
    }//GEN-LAST:event_btn_MeñiqueIzquierdoActionPerformed

    private void btn_AnularIzquierdoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AnularIzquierdoActionPerformed
        if (btn_AnularIzquierdo.getRolloverIcon() != null) 
            txt_HuellaDigital.setText(btn_AnularIzquierdo.getName());
    }//GEN-LAST:event_btn_AnularIzquierdoActionPerformed

    private void btn_MedioIzquierdoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_MedioIzquierdoActionPerformed
        if (btn_MedioIzquierdo.getRolloverIcon() != null) 
            txt_HuellaDigital.setText(btn_MedioIzquierdo.getName());
    }//GEN-LAST:event_btn_MedioIzquierdoActionPerformed

    private void btn_IndiceIzquierdoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_IndiceIzquierdoActionPerformed
        if (btn_IndiceIzquierdo.getRolloverIcon() != null) 
            txt_HuellaDigital.setText(btn_IndiceIzquierdo.getName());
    }//GEN-LAST:event_btn_IndiceIzquierdoActionPerformed

    private void btn_PulgarIzquierdoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_PulgarIzquierdoActionPerformed
        if (btn_PulgarIzquierdo.getRolloverIcon() != null) 
            txt_HuellaDigital.setText(btn_PulgarIzquierdo.getName());
    }//GEN-LAST:event_btn_PulgarIzquierdoActionPerformed

    private void btn_PulgarDerechoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_PulgarDerechoActionPerformed
        if (btn_PulgarDerecho.getRolloverIcon() != null) 
            txt_HuellaDigital.setText(btn_PulgarDerecho.getName());
    }//GEN-LAST:event_btn_PulgarDerechoActionPerformed

    private void btn_MedioDerechoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_MedioDerechoActionPerformed
        if (btn_MedioDerecho.getRolloverIcon() != null) 
            txt_HuellaDigital.setText(btn_MedioDerecho.getName());
    }//GEN-LAST:event_btn_MedioDerechoActionPerformed

    private void btn_AnularDerechoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AnularDerechoActionPerformed
        if (btn_AnularDerecho.getRolloverIcon() != null) 
            txt_HuellaDigital.setText(btn_AnularDerecho.getName());
    }//GEN-LAST:event_btn_AnularDerechoActionPerformed

    private void btn_MeñiqueDerechoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_MeñiqueDerechoActionPerformed
        if (btn_MeñiqueDerecho.getRolloverIcon() != null) 
            txt_HuellaDigital.setText(btn_MeñiqueDerecho.getName());
    }//GEN-LAST:event_btn_MeñiqueDerechoActionPerformed

    private void btn_EliminarHuellaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarHuellaActionPerformed
        eliminarHuella();
    }//GEN-LAST:event_btn_EliminarHuellaActionPerformed

    private void btn_InfoPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_InfoPersonaActionPerformed
        if (tgl_Persona.isSelected())
            new IngresarPersonalTraining(new javax.swing.JDialog(), true, get.reemplazo(txt_RutPersona.getText(), "."), false).setVisible(true);
        else
            new IngresarAlumno(new javax.swing.JDialog(), true, get.reemplazo(txt_RutPersona.getText(), "."), false).setVisible(true);
    }//GEN-LAST:event_btn_InfoPersonaActionPerformed

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
            java.util.logging.Logger.getLogger(AdministrarHuellaDigital.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdministrarHuellaDigital.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdministrarHuellaDigital.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdministrarHuellaDigital.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AdministrarHuellaDigital dialog = new AdministrarHuellaDigital(new javax.swing.JDialog(), true);
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
    private javax.swing.JButton btn_AnularDerecho;
    private javax.swing.JButton btn_AnularIzquierdo;
    private javax.swing.JButton btn_EliminarHuella;
    private javax.swing.JButton btn_IndiceDerecho;
    private javax.swing.JButton btn_IndiceIzquierdo;
    private javax.swing.JButton btn_InfoPersona;
    private javax.swing.JButton btn_IngresarHuella;
    private javax.swing.JButton btn_MedioDerecho;
    private javax.swing.JButton btn_MedioIzquierdo;
    private javax.swing.JButton btn_MeñiqueDerecho;
    private javax.swing.JButton btn_MeñiqueIzquierdo;
    private javax.swing.JButton btn_PulgarDerecho;
    private javax.swing.JButton btn_PulgarIzquierdo;
    private javax.swing.JButton btn_Volver;
    private javax.swing.JComboBox<String> cmb_BuscarPersona;
    private javax.swing.ButtonGroup grupo_AlumnoPersonal;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JLabel lbl_Alumnos;
    private javax.swing.JLabel lbl_Cantidad;
    private com.bolivia.label.CLabel lbl_Foto;
    private javax.swing.JLabel lbl_Logo;
    private javax.swing.JLabel lbl_Nombre;
    private javax.swing.JLabel lbl_Palmas;
    private javax.swing.JLabel lbl_PersonalTraining;
    private javax.swing.JLabel lbl_Rut;
    private javax.swing.JLabel lbl_background;
    private javax.swing.JLayeredPane panel_NombreHuellas;
    private javax.swing.JLayeredPane pnl_Buscador;
    private rojerusan.RSTableMetro tb_Huellas;
    private javax.swing.JToggleButton tgl_Persona;
    private Extra.TextPlaceholder txt_CantidadHuellas;
    private Extra.TextPlaceholder txt_HuellaDigital;
    private Extra.TextPlaceholder txt_NombrePersona;
    private Extra.TextPlaceholder txt_RutPersona;
    // End of variables declaration//GEN-END:variables
    
    private void cargarCMBPersona(){
        cmb_BuscarPersona.removeAllItems();
        textShow(tgl_Persona.isSelected());
        
        ArrayList<Alumno> alumno = new ArrayList<>();
        ArrayList<PersonalTraining> personal = new ArrayList<>();
        //Preguntamos que array linst cargaremos
        if (!tgl_Persona.isSelected()) {
            alumno = new Alumno().buscarAlumnos(false, null, false);
        
            for (int i = 0; i < alumno.size(); i++) 
                cmb_BuscarPersona.addItem(alumno.get(i).getRut());
        }
        else{
            personal = new PersonalTraining().buscarPersonalTraining(false, null, false);
            
            for (int i = 0; i < personal.size(); i++) 
                cmb_BuscarPersona.addItem(personal.get(i).getRut());
        }
        
        AutoCompleteDecorator.decorate(cmb_BuscarPersona);//Auto Completa en el combobox
        cmb_BuscarPersona.setSelectedIndex(0);
    }
    
    private void descargarHuella(){
        cargarHuellaDigital(btn_MeñiqueIzquierdo, btn_MeñiqueIzquierdo.getName(), null);
        cargarHuellaDigital(btn_AnularIzquierdo, btn_AnularIzquierdo.getName(), null);
        cargarHuellaDigital(btn_MedioIzquierdo, btn_MedioIzquierdo.getName(), null);
        cargarHuellaDigital(btn_IndiceIzquierdo, btn_IndiceIzquierdo.getName(), null);
        cargarHuellaDigital(btn_PulgarIzquierdo, btn_PulgarIzquierdo.getName(), null);
        cargarHuellaDigital(btn_PulgarDerecho, btn_PulgarDerecho.getName(), null);
        cargarHuellaDigital(btn_IndiceDerecho, btn_IndiceDerecho.getName(), null);
        cargarHuellaDigital(btn_MedioDerecho, btn_MedioDerecho.getName(), null);
        cargarHuellaDigital(btn_AnularDerecho, btn_AnularDerecho.getName(), null);
        cargarHuellaDigital(btn_MeñiqueDerecho, btn_MeñiqueDerecho.getName(), null);
    }
    
    private void cargarHuellaDigital(JButton btnHuella, String huella, String huellaMouse) {
        btnHuella.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Huellas/" + huella + ".png"))); 
        
        if (huellaMouse != null) 
            btnHuella.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Huellas/" + huellaMouse + ".png")));
        else
            btnHuella.setRolloverIcon(null);
    }
    
    private void cargarHuellaDigital(ArrayList<HuellaDigital> huella ){
        descargarHuella();
        for (int i = 0; i < huella.size(); i++) {
//          Si el nombre de la huella coincide con el nombre del boton, setiamos el icono del boton.
            switch (huella.get(i).getNombre()) {
                case "Pulgar Izquierdo":
                    cargarHuellaDigital(btn_PulgarIzquierdo, "Pulgar Izquierdo Huella", "Pulgar Izquierdo Huella Mouse");
                break;
                case "Indice Izquierdo":
                    cargarHuellaDigital(btn_IndiceIzquierdo, "Indice Izquierdo Huella", "Indice Izquierdo Huella Mouse");
                break;
                case "Medio Izquierdo":
                    cargarHuellaDigital(btn_MedioIzquierdo, "Medio Izquierdo Huella", "Medio Izquierdo Huella Mouse");
                break;
                case "Anular Izquierdo":
                    cargarHuellaDigital(btn_AnularIzquierdo, "Anular Izquierdo Huella", "Anular Izquierdo Huella Mouse");
                break;
                case "Meñique Izquierdo":
                    cargarHuellaDigital(btn_MeñiqueIzquierdo, "Meñique Izquierdo Huella", "Meñique Izquierdo Huella Mouse");
                break;
                case "Pulgar Derecho":
                    cargarHuellaDigital(btn_PulgarDerecho, "Pulgar Derecho Huella", "Pulgar Derecho Huella Mouse");
                break;
                case "Indice Derecho":
                    cargarHuellaDigital(btn_IndiceDerecho, "Indice Derecho Huella", "Indice Derecho Huella Mouse");
                break;
                case "Medio Derecho":
                    cargarHuellaDigital(btn_MedioDerecho, "Medio Derecho Huella", "Medio Derecho Huella Mouse");
                break;
                case "Anular Derecho":
                    cargarHuellaDigital(btn_AnularDerecho, "Anular Derecho Huella", "Anular Derecho Huella Mouse");
                break;
                case "Meñique Derecho":
                    cargarHuellaDigital(btn_MeñiqueDerecho, "Meñique Derecho Huella", "Meñique Derecho Huella Mouse");
                break;
            }
        }
    }
    
    private void cargarPersona() {
        String tipo;
        
        if (!tgl_Persona.isSelected()) {
            //buscamos por alumnos con el rut cargado del cmb persona
            Alumno alumno = new Alumno(String.valueOf(cmb_BuscarPersona.getSelectedItem())).obtenerAlumno();
            get.cargarFoto(alumno.getFoto(), lbl_Foto);
            txt_RutPersona.setText(get.rutReal(alumno.getRut()));
            txt_NombrePersona.setText(alumno.getNombre() + " " + alumno.getApellidoP() + " " + alumno.getApellidoM());
            id = alumno.getId();
            tipo = "Alumno";
        }
        else{ 
            //De lo contrario buscamos por personal trainig
            PersonalTraining personal = new PersonalTraining(cmb_BuscarPersona.getSelectedItem().toString()).obtenerPersonalTraining();
            get.cargarFoto(personal.getFoto(), lbl_Foto);
            txt_RutPersona.setText(get.rutReal(personal.getRut()));
            txt_NombrePersona.setText(personal.getNombre() + " " + personal.getApellidoP() + " " + personal.getApellidoM());
            id = personal.getId();
            tipo = "PersonalTraining";
        }
        txt_HuellaDigital.setText("");
        
        ArrayList<HuellaDigital> huella = new HuellaDigital(id).buscarHuellaDigital(tipo);
        cargarHuellaDigital(huella);
        tablaHuellas(huella);
        cantidadHuellas(huella.size());
    }
    
    private void tablaHuellas(ArrayList<HuellaDigital> huella ){
        mHuella = (DefaultTableModel)tb_Huellas.getModel();   
        get.remueveFilas(mHuella, tb_Huellas);

        if (huella != null || !huella.isEmpty()) {
            new DefaultTableCellRenderer().setHorizontalAlignment(SwingConstants.CENTER);//alinea  en el centro datos de la tabla             
            //datos que se cargaran en la tabla
            Object[] fila = new Object[mHuella.getColumnCount()];
            
            for (int i = 0; i < huella.size(); i++) {
                fila[0] = huella.get(i).getNombre();  //NOMBRE
                mHuella.addRow(fila);
            }   
            tb_Huellas.setModel(mHuella);
        }
    }

    private void cantidadHuellas(int size) {
        txt_CantidadHuellas.setText(String.valueOf(size));
    }

    private HuellaDigital cargarHuella() {
        return new HuellaDigital(id, txt_HuellaDigital.getText());
    }

    private void eliminarHuella() {       
        if (!txt_HuellaDigital.getText().isEmpty()) {
            if( JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar la huella digital " + txt_HuellaDigital.getText() + "?", "Alerta!", 
                    JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == 0){
                switch (cargarHuella().eliminarHuellaDigital(!tgl_Persona.isSelected() ? lbl_Alumnos.getName() : lbl_PersonalTraining.getName())) {
                case 1:
                    JOptionPane.showMessageDialog(null, "Huella eliminada correctamente :D", "Información :D", JOptionPane.INFORMATION_MESSAGE);
                    cargarPersona();
                break;

                case 2:
                    JOptionPane.showMessageDialog(null, "La huella digital ingresada no existe. :(", "Precaución D:", JOptionPane.WARNING_MESSAGE);
                break;

                case 3:
                    JOptionPane.showMessageDialog(null, "La persona ingresada no tiene huellas registradas. :(", "Precaución D:", JOptionPane.WARNING_MESSAGE);
                break;

                case 4:
                    JOptionPane.showMessageDialog(null, "La persona ingresada no existe. :(", "Precaución D:", JOptionPane.WARNING_MESSAGE);
                break;

                case -1:
                    JOptionPane.showMessageDialog(null, "Ocurrió un problema al intentar borrar la huella, intente más tarde! :(", "Precaución D:", JOptionPane.WARNING_MESSAGE);
                break;
                }
            }
        }
        else
            JOptionPane.showMessageDialog(null, "Favor selecciona una huella que deseas eliminar. :(", "Precaución D:", JOptionPane.WARNING_MESSAGE);
    }

    private void buscarHuellaPesona() {
        if (cmb_BuscarPersona.getSelectedItem() != null)  {
            if (!cmb_BuscarPersona.getSelectedItem().toString().isEmpty()) {
                cargarPersona();
            }
        }
    }
    
    private void textShow(boolean visible){
        lbl_PersonalTraining.setVisible(visible);
        lbl_Alumnos.setVisible(!visible);
    }
}
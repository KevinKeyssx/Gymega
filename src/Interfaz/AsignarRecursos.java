package Interfaz;

import Clases.Alumno;
import Clases.DetalleRecurso;
import Clases.Grupo;
import Clases.Recurso;
import Clases.Tipo;
import Extra.Obtener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 * @author keiss
 */
public class AsignarRecursos extends javax.swing.JDialog {

    /**
     * Creates new form AsignarRecursos
     * @param parent
     * @param modal
     */
    DefaultTableModel mAlumno, mTipo, mGrupoAlumno, mGrupoTipo;
    Obtener get = new Obtener();
    
    //**************************CONSTRUCTOR LLAMADO DE UN HERMANO CON GRUPO CARGADO y alumno cargado**********************************
    public AsignarRecursos(javax.swing.JDialog parent, boolean modal, String grupo) {
        super(parent, modal);
        iniciarComponentes();
        cmb_Grupo.setSelectedItem(grupo);
        actualizarGrupo();
    }
    
    public AsignarRecursos(javax.swing.JDialog parent, boolean modal, String grupo, String alumno) {
        super(parent, modal);
        iniciarComponentes();
        cmb_Grupo.setSelectedItem(grupo);
        txt_Alumno.setText(alumno);
        actualizarGrupo();
        tablaAlumno(true, false);
    }
    //************************CONSTRUCTOR LLAMADO DE HERMANO VACIO***************************************************
    public AsignarRecursos(javax.swing.JDialog parent, boolean modal) {
        super(parent, modal);
        iniciarComponentes();
    }
    
    private void iniciarComponentes(){
        initComponents();
        this.setResizable(false);
        this.setSize(1355, 679);
        setLocationRelativeTo(null); //Centrar el JFrame al centro de la pantalla
        setWidthColumn();
        setHighTable(30);
        
        get.moverColumnaTabla(tb_Alumno, false);
        get.moverColumnaTabla(tb_GrupoAlummno, false);
        get.moverColumnaTabla(tb_Tipo, false);

        cargarCMBGrupo();
        tablaAlumno(false, false);
        tablaTipo(false);
        actualizarGrupo();
        addKeyListener();
    }
    
    private void actualizarGrupo() {
        tablaGrupoAlumno();
        tablaGrupoTipo();
    }
    
    private void tablaAlumno(boolean busqueda, boolean huella) {
        ArrayList <Alumno> cargarDatosAlumnos = new Alumno().buscarAlumnos(busqueda, txt_Alumno.getText(), huella);
        mAlumno = (DefaultTableModel)tb_Alumno.getModel();   
        get.remueveFilas(mAlumno, tb_Alumno);

        if (cargarDatosAlumnos != null || !cargarDatosAlumnos.isEmpty() && busqueda) {
            new DefaultTableCellRenderer().setHorizontalAlignment(SwingConstants.CENTER);//alinea  en el centro datos de la tabla             
            //datos que se cargaran en la tabla
            Object[] fila = new Object[mAlumno.getColumnCount()];
            
            for (int i = 0; i < cargarDatosAlumnos.size(); i++) {
                fila[0] = get.rutReal(cargarDatosAlumnos.get(i).getRut());                           //RUT
                fila[1] = cargarDatosAlumnos.get(i).getNombre() + " " +                              //NOMBRE +
                          cargarDatosAlumnos.get(i).getApellidoP() + " " +                           //APELLID PATERNO +
                          cargarDatosAlumnos.get(i).getApellidoM();                                  //APELLIDO MATERNO
                mAlumno.addRow(fila);
            }   
            tb_Alumno.setModel(mAlumno);
        }
    }

    private void tablaGrupoAlumno() {
        ArrayList <Recurso> cargarDatosRecurso = new Recurso(new Grupo(cmb_Grupo.getSelectedItem().toString())).buscarRecursos();
        mGrupoAlumno = (DefaultTableModel)tb_GrupoAlummno.getModel();    
        get.remueveFilas(mGrupoAlumno, tb_GrupoAlummno);
        
        if (cargarDatosRecurso != null || !cargarDatosRecurso.isEmpty()) {
            new DefaultTableCellRenderer().setHorizontalAlignment(SwingConstants.CENTER);//alinea  en el centro datos de la tabla             
            //datos que se cargaran en la tabla
            Object[] fila = new Object[mGrupoAlumno.getColumnCount()];
            
            for (int i = 0; i <cargarDatosRecurso.size(); i++) {
                fila[0] = cargarDatosRecurso.get(i).getAlumno().getId();
                fila[1] = cargarDatosRecurso.get(i).getAlumno().getNombre();
                mGrupoAlumno.addRow(fila);
            }  
            tb_GrupoAlummno.setModel(mGrupoAlumno);
        }
        
        calcularRecursoGrupo("Contar");
    }
    
    private void tablaGrupoTipo() {
        ArrayList <DetalleRecurso> cargarDatosDetalleRecurso = new DetalleRecurso(new Grupo(cmb_Grupo.getSelectedItem().toString())).buscarDetalleRecurso();
        mGrupoTipo = (DefaultTableModel)tb_GrupoTipo.getModel();    
        get.remueveFilas(mGrupoTipo, tb_GrupoTipo);
        
        if (cargarDatosDetalleRecurso != null || !cargarDatosDetalleRecurso.isEmpty()) {
            new DefaultTableCellRenderer().setHorizontalAlignment(SwingConstants.CENTER);//alinea  en el centro datos de la tabla             
            //datos que se cargaran en la tabla
            Object[] fila = new Object[mGrupoTipo.getColumnCount()];
            
            for (int i = 0; i <cargarDatosDetalleRecurso.size(); i++) {
                fila[0] = cargarDatosDetalleRecurso.get(i).getTipo().getNombre();
                mGrupoTipo.addRow(fila);
            }  
            tb_GrupoTipo.setModel(mGrupoTipo);
        }
        
        calcularDetallerecurso();
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
                fila[2] = cargarDatosTipo.get(i).getMonto();
                mTipo.addRow(fila);
            }  
            tb_Tipo.setModel(mTipo);
        }
    }

    private void addKeyListener(){
        cmb_Grupo.getEditor().getEditorComponent().addKeyListener(new KeyListener() { 
            @Override
            public void keyPressed(KeyEvent e){ 
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                    actualizarGrupo();
            } 
            @Override
            public void keyReleased(KeyEvent e){} 
            @Override
            public void keyTyped(KeyEvent e) {} 
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_PortMedics = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tb_Alumno = new rojerusan.RSTableMetro();
        txt_Alumno = new Extra.TextPlaceholder();
        lbl_Lupa = new javax.swing.JLabel();
        btn_Aceptar = new javax.swing.JButton();
        btn_Volver = new javax.swing.JButton();
        lbl_Titulo = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_GrupoTipo = new rojerusan.RSTableMetro();
        jSeparator1 = new javax.swing.JSeparator();
        lbl_Total = new javax.swing.JLabel();
        txt_Total = new Extra.TextPlaceholder();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        tb_GrupoAlummno = new rojerusan.RSTableMetro();
        jScrollPane8 = new javax.swing.JScrollPane();
        tb_Tipo = new rojerusan.RSTableMetro();
        txt_BuscarTipo = new Extra.TextPlaceholder();
        lbl_Lupa1 = new javax.swing.JLabel();
        btn_CrearGrupo = new javax.swing.JButton();
        cmb_Grupo = new javax.swing.JComboBox<>();
        txt_Cantidad = new Extra.TextPlaceholder();
        lbl_Cantidad = new javax.swing.JLabel();
        btn_AdministrarTipos = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_PortMedics.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/1.png"))); // NOI18N
        getContentPane().add(lbl_PortMedics, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        tb_Alumno.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tb_Alumno.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Rut", "Nombre"
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
        tb_Alumno.setColorBackgoundHead(new java.awt.Color(0, 153, 204));
        tb_Alumno.setColorBordeFilas(new java.awt.Color(0, 255, 255));
        tb_Alumno.setColorBordeHead(new java.awt.Color(255, 255, 255));
        tb_Alumno.setColorFilasBackgound2(new java.awt.Color(204, 255, 255));
        tb_Alumno.setColorSelBackgound(new java.awt.Color(0, 102, 204));
        tb_Alumno.setFillsViewportHeight(true);
        tb_Alumno.setFuenteFilas(new java.awt.Font("Mairy Oblicua", 0, 21)); // NOI18N
        tb_Alumno.setFuenteFilasSelect(new java.awt.Font("Mairy Oblicua", 0, 24)); // NOI18N
        tb_Alumno.setFuenteHead(new java.awt.Font("Mairy Bold", 1, 24)); // NOI18N
        tb_Alumno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_AlumnoMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tb_Alumno);

        getContentPane().add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 430, 410));

        txt_Alumno.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txt_Alumno.setForeground(new java.awt.Color(0, 204, 255));
        txt_Alumno.setToolTipText("Busca un Alumno");
        txt_Alumno.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        txt_Alumno.setPlaceholder("Busca a un Alumno");
        txt_Alumno.setSelectedTextColor(new java.awt.Color(255, 102, 102));
        txt_Alumno.setSelectionColor(new java.awt.Color(255, 255, 0));
        txt_Alumno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_AlumnoKeyReleased(evt);
            }
        });
        getContentPane().add(txt_Alumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 340, -1));

        lbl_Lupa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Lupa.png"))); // NOI18N
        getContentPane().add(lbl_Lupa, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 110, 40, -1));

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
        getContentPane().add(btn_Aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 590, 180, 40));

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

        lbl_Titulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Administar Grupos.png"))); // NOI18N
        getContentPane().add(lbl_Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 30, -1, -1));

        tb_GrupoTipo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tb_GrupoTipo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Clase"
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
        tb_GrupoTipo.setColorBackgoundHead(new java.awt.Color(0, 153, 204));
        tb_GrupoTipo.setColorBordeFilas(new java.awt.Color(0, 255, 255));
        tb_GrupoTipo.setColorBordeHead(new java.awt.Color(255, 255, 255));
        tb_GrupoTipo.setColorFilasBackgound2(new java.awt.Color(204, 255, 255));
        tb_GrupoTipo.setColorFilasForeground1(new java.awt.Color(0, 153, 255));
        tb_GrupoTipo.setColorFilasForeground2(new java.awt.Color(0, 153, 255));
        tb_GrupoTipo.setColorSelBackgound(new java.awt.Color(0, 102, 204));
        tb_GrupoTipo.setFillsViewportHeight(true);
        tb_GrupoTipo.setFuenteFilas(new java.awt.Font("Mairy Oblicua", 0, 21)); // NOI18N
        tb_GrupoTipo.setFuenteFilasSelect(new java.awt.Font("Mairy Oblicua", 0, 24)); // NOI18N
        tb_GrupoTipo.setFuenteHead(new java.awt.Font("Mairy Bold", 1, 24)); // NOI18N
        tb_GrupoTipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_GrupoTipoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tb_GrupoTipo);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 160, 170, 370));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 110, 20, 460));

        lbl_Total.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Total.png"))); // NOI18N
        getContentPane().add(lbl_Total, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 540, 60, 30));

        txt_Total.setEditable(false);
        txt_Total.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        txt_Total.setForeground(new java.awt.Color(0, 51, 204));
        txt_Total.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_Total.setToolTipText("Monto Total...");
        txt_Total.setFont(new java.awt.Font("DS-Digital", 0, 18)); // NOI18N
        txt_Total.setPlaceholder("Total");
        txt_Total.setSelectedTextColor(new java.awt.Color(255, 0, 0));
        txt_Total.setSelectionColor(new java.awt.Color(255, 255, 51));
        getContentPane().add(txt_Total, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 540, 140, 30));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 100, 20, 470));

        tb_GrupoAlummno.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tb_GrupoAlummno.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Alumno"
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
        tb_GrupoAlummno.setColorBackgoundHead(new java.awt.Color(0, 153, 204));
        tb_GrupoAlummno.setColorBordeFilas(new java.awt.Color(0, 255, 255));
        tb_GrupoAlummno.setColorBordeHead(new java.awt.Color(255, 255, 255));
        tb_GrupoAlummno.setColorFilasBackgound2(new java.awt.Color(204, 255, 255));
        tb_GrupoAlummno.setColorFilasForeground1(new java.awt.Color(0, 153, 255));
        tb_GrupoAlummno.setColorFilasForeground2(new java.awt.Color(0, 153, 255));
        tb_GrupoAlummno.setColorSelBackgound(new java.awt.Color(0, 102, 204));
        tb_GrupoAlummno.setFillsViewportHeight(true);
        tb_GrupoAlummno.setFuenteFilas(new java.awt.Font("Mairy Oblicua", 0, 21)); // NOI18N
        tb_GrupoAlummno.setFuenteFilasSelect(new java.awt.Font("Mairy Oblicua", 0, 24)); // NOI18N
        tb_GrupoAlummno.setFuenteHead(new java.awt.Font("Mairy Bold", 1, 24)); // NOI18N
        tb_GrupoAlummno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_GrupoAlummnoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tb_GrupoAlummno);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 160, 270, 370));

        tb_Tipo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tb_Tipo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nro.", "Nombre", "Monto"
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
        tb_Tipo.setFillsViewportHeight(true);
        tb_Tipo.setFuenteFilas(new java.awt.Font("Mairy Oblicua", 1, 18)); // NOI18N
        tb_Tipo.setFuenteFilasSelect(new java.awt.Font("Mairy Oblicua", 1, 18)); // NOI18N
        tb_Tipo.setFuenteHead(new java.awt.Font("Mairy Bold", 1, 24)); // NOI18N
        tb_Tipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_TipoMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tb_Tipo);

        getContentPane().add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 160, 360, 410));

        txt_BuscarTipo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txt_BuscarTipo.setForeground(new java.awt.Color(0, 204, 255));
        txt_BuscarTipo.setToolTipText("Busca un Tipo...");
        txt_BuscarTipo.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        txt_BuscarTipo.setPlaceholder("Busca un Tipo");
        txt_BuscarTipo.setSelectedTextColor(new java.awt.Color(255, 102, 102));
        txt_BuscarTipo.setSelectionColor(new java.awt.Color(255, 255, 0));
        txt_BuscarTipo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_BuscarTipoKeyReleased(evt);
            }
        });
        getContentPane().add(txt_BuscarTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 110, 250, -1));

        lbl_Lupa1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Lupa.png"))); // NOI18N
        getContentPane().add(lbl_Lupa1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 110, 40, -1));

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
        getContentPane().add(btn_CrearGrupo, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 100, -1, 50));

        cmb_Grupo.setEditable(true);
        cmb_Grupo.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        cmb_Grupo.setForeground(new java.awt.Color(0, 204, 255));
        cmb_Grupo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Grupo 1", "Grupo 2", "Grupo 3", "Grupo 4" }));
        cmb_Grupo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        cmb_Grupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_GrupoActionPerformed(evt);
            }
        });
        getContentPane().add(cmb_Grupo, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 110, 340, 30));

        txt_Cantidad.setEditable(false);
        txt_Cantidad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        txt_Cantidad.setForeground(new java.awt.Color(0, 51, 204));
        txt_Cantidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_Cantidad.setToolTipText("Cantidad de Alumnos en el Grupo");
        txt_Cantidad.setFont(new java.awt.Font("DS-Digital", 0, 18)); // NOI18N
        txt_Cantidad.setPlaceholder("Cantidad");
        txt_Cantidad.setSelectedTextColor(new java.awt.Color(255, 0, 0));
        txt_Cantidad.setSelectionColor(new java.awt.Color(255, 255, 51));
        getContentPane().add(txt_Cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 540, 60, 30));

        lbl_Cantidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Cantidad.png"))); // NOI18N
        getContentPane().add(lbl_Cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 540, -1, 30));

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
        getContentPane().add(btn_AdministrarTipos, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 100, 50, 60));

        jButton1.setText("jButton1");
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 590, -1, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Menu.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1350, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tb_AlumnoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_AlumnoMouseClicked
        if (evt.getClickCount() == 2)
            ingresarRecurso();
    }//GEN-LAST:event_tb_AlumnoMouseClicked

    private void txt_AlumnoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_AlumnoKeyReleased
        tablaAlumno(true, false);
    }//GEN-LAST:event_txt_AlumnoKeyReleased

    private void btn_VolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_VolverActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btn_VolverActionPerformed

    private void btn_AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AceptarActionPerformed
        ingresarRecurso();
        ingresarTipoRecurso();
    }//GEN-LAST:event_btn_AceptarActionPerformed

    private void tb_GrupoTipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_GrupoTipoMouseClicked
        if (evt.getClickCount() == 2)
            eliminarRecursoTipo();
    }//GEN-LAST:event_tb_GrupoTipoMouseClicked

    private void tb_GrupoAlummnoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_GrupoAlummnoMouseClicked
        if (evt.getClickCount() == 2)
            eliminarRecurso();
    }//GEN-LAST:event_tb_GrupoAlummnoMouseClicked

    private void tb_TipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_TipoMouseClicked
        if (evt.getClickCount() == 2)
            ingresarTipoRecurso();
    }//GEN-LAST:event_tb_TipoMouseClicked

    private void txt_BuscarTipoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_BuscarTipoKeyReleased
        tablaTipo(true);
    }//GEN-LAST:event_txt_BuscarTipoKeyReleased

    private void btn_CrearGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CrearGrupoActionPerformed
        get.nuevoIngreso(new Grupo().ingresarGrupo(), "Grupo");
        cargarCMBGrupo();
    }//GEN-LAST:event_btn_CrearGrupoActionPerformed

    private void btn_AdministrarTiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AdministrarTiposActionPerformed
        new CrearTipo(new javax.swing.JDialog(), true).setVisible(true);
        tablaTipo(false);
    }//GEN-LAST:event_btn_AdministrarTiposActionPerformed

    private void cmb_GrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_GrupoActionPerformed
        if (cmb_Grupo.getSelectedItem() != null) 
            actualizarGrupo();
    }//GEN-LAST:event_cmb_GrupoActionPerformed

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
            java.util.logging.Logger.getLogger(AsignarRecursos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AsignarRecursos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AsignarRecursos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AsignarRecursos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AsignarRecursos dialog = new AsignarRecursos(new javax.swing.JDialog(), true);
                
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
    private javax.swing.JButton btn_CrearGrupo;
    private javax.swing.JButton btn_Volver;
    private javax.swing.JComboBox<String> cmb_Grupo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbl_Cantidad;
    private javax.swing.JLabel lbl_Lupa;
    private javax.swing.JLabel lbl_Lupa1;
    private javax.swing.JLabel lbl_PortMedics;
    private javax.swing.JLabel lbl_Titulo;
    private javax.swing.JLabel lbl_Total;
    private rojerusan.RSTableMetro tb_Alumno;
    private rojerusan.RSTableMetro tb_GrupoAlummno;
    private rojerusan.RSTableMetro tb_GrupoTipo;
    private rojerusan.RSTableMetro tb_Tipo;
    private Extra.TextPlaceholder txt_Alumno;
    private Extra.TextPlaceholder txt_BuscarTipo;
    private Extra.TextPlaceholder txt_Cantidad;
    private Extra.TextPlaceholder txt_Total;
    // End of variables declaration//GEN-END:variables
    //*****************INGRESO DE LOS RECURSOS
    private void ingresarRecurso() {
        if (tb_Alumno.getSelectedRow() >= 0) {
            if( JOptionPane.showConfirmDialog(null, "¿Seguro que desea añadir a " + cargarAlumno().getNombre() + " en el " + cmb_Grupo.getSelectedItem().toString() + "?", "Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == 0){
                get.nuevoIngreso(new Recurso(cargarGrupo(), cargarAlumno()).ingresarRecurso(), "Alumno");
                tablaGrupoAlumno();
            }
        }
        else
            JOptionPane.showMessageDialog(null, "Favor, selecciona un alumno :(","Precaución! D:",JOptionPane.WARNING_MESSAGE);
    }
    
    private void ingresarTipoRecurso() {
        if (tb_Tipo.getSelectedRow() >= 0) {
            if( JOptionPane.showConfirmDialog(null, "¿Seguro que desea ingresar la clase *" + cargarTipo().getNombre() + "* al " + cmb_Grupo.getSelectedItem().toString() + "?\nSi acepta se añadirán cargos de pago al grupo.", "Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == 0){
                get.nuevoIngreso(new DetalleRecurso(cargarGrupo(), cargarTipo()).ingresarDetalleRecurso(), "Tipo");
                tablaGrupoTipo();
            }
        }else
            JOptionPane.showMessageDialog(null, "Favor, selecciona una clase :(","Precaución! D:",JOptionPane.WARNING_MESSAGE);
    }
    //****************************ELIMINACION DE LOS RECURSOS
    private void eliminarRecurso() {
        if( JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar a " + cargarIdAlumno().getNombre() + " del " + cmb_Grupo.getSelectedItem().toString() + "?", "Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == 0){
            if (new Recurso(cargarGrupo(), cargarIdAlumno()).eliminarRecurso() == 1) 
                tablaGrupoAlumno();
        }
    }
    
    private void eliminarRecursoTipo() {
        if( JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar a " + cargarRecursoTipo().getNombre() + " del " + cmb_Grupo.getSelectedItem().toString() + "?\nSi elimina del grupo, se modificarán los cargor de pago.", "Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == 0){
            if (new DetalleRecurso(cargarGrupo(), cargarRecursoTipo()).elminarDetalleRecurso() == 1) 
                tablaGrupoTipo();
        }
    }
    ///*****************************CARGAR LOS RECURSOS*************************
    private Grupo cargarGrupo() {
        return new Grupo(
            new Grupo(cmb_Grupo.getSelectedItem().toString()).buscarGrupos(true).get(0).getId()//OBTENEMOS EL ID DEL GRUPO POR EL NOMBRE DEL CMB
        );
    }

    private Alumno cargarAlumno() {
        //Carga el RUT y el nombre del alumno para ingresarlo de la tabla alumno
        return new Alumno(
            new Alumno(get.reemplazo(tb_Alumno.getValueAt(tb_Alumno.getSelectedRow(), 0).toString(), ".")).obtenerAlumno().getId(),
            tb_Alumno.getValueAt(tb_Alumno.getSelectedRow(), 1).toString()
        );
    }
    
    private Alumno cargarIdAlumno(){
        //CArga el id del alumno para eliminuarlo de la tabla GRUPO
        return new Alumno(
            Integer.valueOf(tb_GrupoAlummno.getValueAt(tb_GrupoAlummno.getSelectedRow(), 0).toString()),
            tb_GrupoAlummno.getValueAt(tb_GrupoAlummno.getSelectedRow(), 1).toString()
        );
    }

    private Tipo cargarTipo() {
        //CARGAMOS TODOS LOS DATOS DE LA TABLA TIPO
        return new Tipo(
            Integer.valueOf(tb_Tipo.getValueAt(tb_Tipo.getSelectedRow(), 0).toString()), //CARGAMOS EL ID
            tb_Tipo.getValueAt(tb_Tipo.getSelectedRow(), 1).toString(),                  //CARGAMOS EL NOMBRE
            Integer.valueOf(tb_Tipo.getValueAt(tb_Tipo.getSelectedRow(), 2).toString())  //CARGAMOS EL MONTO
        );
    }
    
    private Tipo cargarRecursoTipo() {
        //CARGAMOS EL ID DEL TIPO Y EL NOMBRE DE LA TABLA GRUPO RECURSO
        return new Tipo(
            new Tipo(tb_GrupoTipo.getValueAt(tb_GrupoTipo.getSelectedRow(), 0).toString()).obtenerTipo().getId(), //Bucams el id con el nombre del tipo
            tb_GrupoTipo.getValueAt(tb_GrupoTipo.getSelectedRow(), 0).toString() //Cargamos el nombre
        );
    }
    //********************CAMBIAMOS LOS TAMAÑOS DE LAS COLUMNAS Y FILAS DE TODAS LAS TABLAS
    private void setHighTable(int i) {
        tb_Alumno.setRowHeight(i);
        tb_GrupoAlummno.setRowHeight(i);
        tb_GrupoTipo.setRowHeight(i);
        tb_Tipo.setRowHeight(i);
    }
    
    private void setWidthColumn() {
        //TABLA ALUMNO
        tb_Alumno.getColumnModel().getColumn(0).setPreferredWidth(110);//Rut
        tb_Alumno.getColumnModel().getColumn(1).setPreferredWidth(290);//Nombre
        //TABLA GRUPO
        tb_GrupoAlummno.getColumnModel().getColumn(0).setPreferredWidth(80);//Nombre
        tb_GrupoAlummno.getColumnModel().getColumn(1).setPreferredWidth(290);//Nombre
        //TABLA TIPO
        tb_Tipo.getColumnModel().getColumn(0).setPreferredWidth(100);//Nro
        tb_Tipo.getColumnModel().getColumn(1).setPreferredWidth(250);//Nombre
        tb_Tipo.getColumnModel().getColumn(2).setPreferredWidth(130);//Monto
    }
    //**********************CARGAMOS EL COMBO BOX DEL GRUPO********************
    private void cargarCMBGrupo() {
        cmb_Grupo.removeAllItems();
        ArrayList<Grupo> grupo = new Grupo().buscarGrupos(false);
        
        for (int i = 0; i < grupo.size(); i++) 
            cmb_Grupo.addItem(grupo.get(i).getNombre());

        AutoCompleteDecorator.decorate(cmb_Grupo);//Auto Completa en el combobox
        cmb_Grupo.setSelectedIndex(grupo.size() - 1);
    }
    //****************CALCULAMOS LA CANTIDAD DE LOS ALUMNOS EN EL GRUPO*********
    private void calcularRecursoGrupo(String proc){
        txt_Cantidad.setText("" + new Recurso().obtenerProcRecurso(proc, cmb_Grupo.getSelectedItem().toString()));
    }
    //****************CALCULAMOS EL MONTO TOTAL DE PAGO DE LOS ALUMNOS EN EL GRUPO*********
    private void calcularDetallerecurso(){
        if (!cmb_Grupo.getSelectedItem().toString().isEmpty() || cmb_Grupo.getSelectedItem().toString() != null) {
            int id = new Grupo(cmb_Grupo.getSelectedItem().toString()).obtenerGrupo().getId();
            
            if (id > 0) {
                txt_Total.setText(get.concatenaCaracter("" + new Grupo(id).obtenerPrecioGrupo(), 3, '.'));
            }
        }
    }
}

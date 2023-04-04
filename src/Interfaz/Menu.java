package Interfaz;

import Clases.Alumno;
import Clases.Asistencia;
import Clases.HuellaDigital;
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
import com.digitalpersona.onetouch.capture.event.DPFPSensorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPSensorEvent;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import com.digitalpersona.onetouch.verification.DPFPVerification;
import com.digitalpersona.onetouch.verification.DPFPVerificationResult;
import com.sun.awt.AWTUtilities;
import com.sun.xml.internal.ws.client.ContentNegotiation;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javafx.scene.paint.Stop;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * @author Keyss
 */
public class Menu extends javax.swing.JFrame {

    DefaultTableModel mPTraining, mAlumno, mAsistencia;
    Obtener get = new Obtener();
    Validacion validar = new Validacion();
    private int idPersonal;
    
    private void cerrar(){
        try {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e){
                    ejecucion();
                }
            });
            
            this.setVisible(true);
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void ejecucion(){
        Object[] options = { "Cerrar sesión", "Salir", "Cancelar" };
        int seleccion = JOptionPane.showOptionDialog(null, "¿Está seguro que desea salir?", "Aviso!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
   
        switch (seleccion) {
            case 1:
                System.exit(0);
            break;
                
            case 0:
                cerrarSesion();
            break;            
        }
    }
    
    private void cerrarSesion() {
        stop();
        this.setVisible(false);
        Login login = new Login();
        AWTUtilities.setWindowOpaque(login, false);
        login.setVisible(true);
    }
    
    private void iniciarComponentes(String rut){
        //Ocultar el Panel Principal
        pnl_Menu.setVisible(false);
        PersonalTraining pt = new PersonalTraining(rut).obtenerPersonalTraining();
        lbl_Nombre.setText(pt.getTipo() + " " + pt.getNombre() + " " + pt.getApellidoP() + " " + pt.getApellidoM());
        idPersonal = pt.getId();
        //Editar el tamaño de las columnas de las tablas
        /*Columnas Tabla Asistencia*/
        tb_Asistencia.getColumnModel().getColumn(0).setPreferredWidth(220);     //Fecha
        tb_Asistencia.getColumnModel().getColumn(1).setPreferredWidth(150);     //Hora
        tb_Asistencia.getColumnModel().getColumn(2).setPreferredWidth(150);     //Rut
        tb_Asistencia.getColumnModel().getColumn(3).setPreferredWidth(300);     //Nombre
        tb_Asistencia.getColumnModel().getColumn(4).setPreferredWidth(250);     //Descripcion
        /***Columnas Tabla Personal training***/
        tb_PersonalTraining.getColumnModel().getColumn(0).setPreferredWidth(100);//Rut
        tb_PersonalTraining.getColumnModel().getColumn(1).setPreferredWidth(300);//Nombre
        tb_PersonalTraining.getColumnModel().getColumn(2).setPreferredWidth(250);//Dirección
        tb_PersonalTraining.getColumnModel().getColumn(3).setPreferredWidth(70);//Teléfono
        tb_PersonalTraining.getColumnModel().getColumn(4).setPreferredWidth(50);//Edad       
        /***Columnas Tabla Alumno***/
        tb_Alumno.getColumnModel().getColumn(0).setPreferredWidth(100);//Rut
        tb_Alumno.getColumnModel().getColumn(1).setPreferredWidth(300);//Nombre
        tb_Alumno.getColumnModel().getColumn(2).setPreferredWidth(200);//Dirección
        tb_Alumno.getColumnModel().getColumn(4).setPreferredWidth(80);//Teléfono
        tb_Alumno.getColumnModel().getColumn(4).setPreferredWidth(80);//Pago
        tb_Alumno.getColumnModel().getColumn(5).setPreferredWidth(150);//Correo
        
        setFilaAlto(30);
        desactivaPanel();
        this.setExtendedState(MAXIMIZED_BOTH); //Para dejar la ventana en tamaño completo por defecto
        
        tablaPersonaltraining(false, false);
        tablaAlumno(false, false);
        asistencia();
        cerrar();
    }
    
    public Menu(String rut) {
        initComponents();
        iniciarComponentes(rut);
    }
    
    public Menu() {
        initComponents();
    }
    
    private void setFilaAlto(int i) {
        tb_Asistencia.setRowHeight(i);
        tb_PersonalTraining.setRowHeight(i);
        tb_Alumno.setRowHeight(i);
    }
    
    private void tablaPersonaltraining(boolean busqueda, boolean huella) {
        ArrayList <PersonalTraining> cargarDatosPersonalTraining = new PersonalTraining().buscarPersonalTraining(busqueda, txt_PersonalTraining.getText(), huella);
        mPTraining = (DefaultTableModel)tb_PersonalTraining.getModel();   
        get.remueveFilas(mPTraining, tb_PersonalTraining);
        
        if (cargarDatosPersonalTraining != null || !cargarDatosPersonalTraining.isEmpty() && busqueda) {
            new DefaultTableCellRenderer().setHorizontalAlignment(SwingConstants.CENTER);//alinea  en el centro datos de la tabla             
            //datos que se cargaran en la tabla
            Object[] fila = new Object[mPTraining.getColumnCount()];
            
            for (int i = 0; i <cargarDatosPersonalTraining.size(); i++) {
                fila[0] = get.rutReal(cargarDatosPersonalTraining.get(i).getRut()) ;                        //RUT
                fila[1] = cargarDatosPersonalTraining.get(i).getNombre() + " " +                            //NOMBRE +
                          cargarDatosPersonalTraining.get(i).getApellidoP() + " " +                         //APELLIDO PATERNO +
                          cargarDatosPersonalTraining.get(i).getApellidoM();                                //APELLIDO MATERNO
                fila[2] = cargarDatosPersonalTraining.get(i).getCorreo();                                   //CORREO
                fila[3] = get.concatenaCaracter(cargarDatosPersonalTraining.get(i).getFono() + "", 4, ' '); //TELEFONO
                fila[4] = get.calculaEdad(cargarDatosPersonalTraining.get(i).getFechaNacimiento()) ;        //EDAD
                fila[5] = cargarDatosPersonalTraining.get(i).getTipo();                                     //TIPO
                
                mPTraining.addRow(fila);
            }  
                
            tb_PersonalTraining.setModel(mPTraining);
        }
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
                fila[2] = cargarDatosAlumnos.get(i).getDireccion();                                  //DIRECCION
                fila[3] = get.concatenaCaracter(cargarDatosAlumnos.get(i).getFono() + "", 4, ' ');   //TELEFONO
                fila[4] = "0" ;                                                                      //PAGO
                fila[5] = cargarDatosAlumnos.get(i).getCorreo();                                     //CORREO
                
                mAlumno.addRow(fila);
            }  
                
            tb_Alumno.setModel(mAlumno);
        }
    }
    
    private void tablaAsistencia() {
        ArrayList <Asistencia> cargarAsistencia = new Asistencia().buscarAsistencias(tgl_Asistencia.isSelected());
        mAsistencia = (DefaultTableModel)tb_Asistencia.getModel();   
        get.remueveFilas(mAsistencia, tb_Asistencia);

        if (cargarAsistencia != null || !cargarAsistencia.isEmpty()) {
            new DefaultTableCellRenderer().setHorizontalAlignment(SwingConstants.CENTER);//alinea  en el centro datos de la tabla             
            //datos que se cargaran en la tabla
            Object[] fila = new Object[mAsistencia.getColumnCount()];
            
            for (int i = 0; i < cargarAsistencia.size(); i++) {
                fila[0] = validar.formatoEspañol(cargarAsistencia.get(i).getFecha());               //Fecha
                fila[1] = validar.horaAmPm(cargarAsistencia.get(i).getHora().substring(0, 8));      //Hora
                fila[2] = get.rutReal(cargarAsistencia.get(i).getAlumno().getRut());                //Rut
                fila[3] = cargarAsistencia.get(i).getAlumno().getNombre();                          //Nombre
                fila[4] = cargarAsistencia.get(i).getDescripcion();                                 //Descripción
                mAsistencia.addRow(fila);
            }  
                
            tb_Asistencia.setModel(mAsistencia);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_Menu = new javax.swing.JPanel();
        btn_PersonalTraining = new javax.swing.JButton();
        btn_Asistencia = new javax.swing.JButton();
        btn_Alumno = new javax.swing.JButton();
        btn_Registros = new javax.swing.JButton();
        lbl_Panel = new javax.swing.JLabel();
        pnl_Asistencia = new javax.swing.JLayeredPane();
        lbl_Asistencia = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_Asistencia = new rojerusan.RSTableMetro();
        btn_DetalleAsistencia = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txt_RegistrarAsistenciaRut = new Extra.TextPlaceholder();
        tgl_Asistencia = new javax.swing.JToggleButton();
        lbl_Todo = new javax.swing.JLabel();
        lbl_Solohoy = new javax.swing.JLabel();
        btn_Menu = new javax.swing.JButton();
        lbl_Nombre = new javax.swing.JLabel();
        pnl_PersonalTraining = new javax.swing.JLayeredPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_PersonalTraining = new rojerusan.RSTableMetro();
        lbl_PersonalTraining = new javax.swing.JLabel();
        lbl_Lupa = new javax.swing.JLabel();
        lbl_BuscarPersonalTraining = new javax.swing.JLabel();
        txt_PersonalTraining = new Extra.TextPlaceholder();
        btn_AgregarPersonalTraining = new javax.swing.JButton();
        btn_EditarPersonalTraining = new javax.swing.JButton();
        btn_InhabilitarPersonalTraining = new javax.swing.JButton();
        ckb_AbrirPersonalTraining = new Extra.MiCheckBox();
        pnl_Alumno = new javax.swing.JLayeredPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        tb_Alumno = new rojerusan.RSTableMetro();
        lbl_Alumnos = new javax.swing.JLabel();
        btn_AgregarAlumno = new javax.swing.JButton();
        btn_EditarAlumno = new javax.swing.JButton();
        btn_InhabilitarAlumno = new javax.swing.JButton();
        lbl_Buscar = new javax.swing.JLabel();
        txt_Alumno = new Extra.TextPlaceholder();
        lbl_Lupa1 = new javax.swing.JLabel();
        ckb_AbrirAlumno = new Extra.MiCheckBox();
        lbl_Logo = new javax.swing.JLabel();
        lbl_LogoInicio = new javax.swing.JLabel();
        lbl_imgfondo = new javax.swing.JLabel();
        menu_Principal = new javax.swing.JMenuBar();
        menu_Usuario = new javax.swing.JMenu();
        menu_PesonalTrainnig = new javax.swing.JMenuItem();
        menu_CerrarSesion = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        menu_CerrarSportMedics = new javax.swing.JMenuItem();
        menu_Ver = new javax.swing.JMenu();
        menu_Asistencia = new javax.swing.JMenuItem();
        menu_PersonalTrainning = new javax.swing.JMenuItem();
        menu_Alumnos = new javax.swing.JMenuItem();
        menu_VistaDyR = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenu1 = new javax.swing.JMenu();
        menu_AdministrarGrupos = new javax.swing.JMenuItem();
        menu_AsignarRecursos = new javax.swing.JMenuItem();
        menu_Tipo = new javax.swing.JMenuItem();
        menu_HuellaDigital = new javax.swing.JMenuItem();
        Menu_Configuracion = new javax.swing.JMenu();
        menu_Contraseña = new javax.swing.JMenuItem();
        menu_AdministrarDeportes = new javax.swing.JMenuItem();
        menu_AdministrarFisicos = new javax.swing.JMenuItem();
        menu_CFecha = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnl_Menu.setOpaque(false);
        pnl_Menu.setRequestFocusEnabled(false);
        pnl_Menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_PersonalTraining.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Personal Training Normal.png"))); // NOI18N
        btn_PersonalTraining.setBorderPainted(false);
        btn_PersonalTraining.setContentAreaFilled(false);
        btn_PersonalTraining.setFocusable(false);
        btn_PersonalTraining.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Personal Training Mouse.png"))); // NOI18N
        btn_PersonalTraining.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_PersonalTrainingActionPerformed(evt);
            }
        });
        pnl_Menu.add(btn_PersonalTraining, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 100, 90));

        btn_Asistencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Asistencia.png"))); // NOI18N
        btn_Asistencia.setBorderPainted(false);
        btn_Asistencia.setContentAreaFilled(false);
        btn_Asistencia.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Asistencia Click.png"))); // NOI18N
        btn_Asistencia.setRequestFocusEnabled(false);
        btn_Asistencia.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Asistencia Mouse.png"))); // NOI18N
        btn_Asistencia.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Asistencia Click.png"))); // NOI18N
        btn_Asistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AsistenciaActionPerformed(evt);
            }
        });
        pnl_Menu.add(btn_Asistencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 100, 100));

        btn_Alumno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Alumno Normal.png"))); // NOI18N
        btn_Alumno.setBorderPainted(false);
        btn_Alumno.setContentAreaFilled(false);
        btn_Alumno.setFocusable(false);
        btn_Alumno.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Alumno Mouse.png"))); // NOI18N
        btn_Alumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AlumnoActionPerformed(evt);
            }
        });
        pnl_Menu.add(btn_Alumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 100, 90));

        btn_Registros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Registros Normal.png"))); // NOI18N
        btn_Registros.setBorderPainted(false);
        btn_Registros.setContentAreaFilled(false);
        btn_Registros.setFocusable(false);
        btn_Registros.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Registros Mouse.png"))); // NOI18N
        btn_Registros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RegistrosActionPerformed(evt);
            }
        });
        pnl_Menu.add(btn_Registros, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 100, 100));

        lbl_Panel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Panel.png"))); // NOI18N
        pnl_Menu.add(lbl_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 580));

        getContentPane().add(pnl_Menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 140, -1));

        pnl_Asistencia.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lbl_Asistencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Asistencias.png"))); // NOI18N
        pnl_Asistencia.add(lbl_Asistencia);
        lbl_Asistencia.setBounds(110, 10, 300, 90);

        tb_Asistencia.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tb_Asistencia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Hora", "Rut", "Nombre", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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
        tb_Asistencia.setColorFilasForeground1(new java.awt.Color(0, 153, 255));
        tb_Asistencia.setColorFilasForeground2(new java.awt.Color(0, 153, 255));
        tb_Asistencia.setFillsViewportHeight(true);
        tb_Asistencia.setFuenteFilas(new java.awt.Font("Mairy Oblicua", 0, 21)); // NOI18N
        tb_Asistencia.setFuenteFilasSelect(new java.awt.Font("Mairy Oblicua", 0, 24)); // NOI18N
        tb_Asistencia.setFuenteHead(new java.awt.Font("Mairy Bold", 1, 24)); // NOI18N
        tb_Asistencia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_AsistenciaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tb_Asistencia);

        pnl_Asistencia.add(jScrollPane2);
        jScrollPane2.setBounds(140, 100, 1100, 410);

        btn_DetalleAsistencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Informacion.png"))); // NOI18N
        btn_DetalleAsistencia.setBorderPainted(false);
        btn_DetalleAsistencia.setContentAreaFilled(false);
        btn_DetalleAsistencia.setFocusPainted(false);
        btn_DetalleAsistencia.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Informacion Mouse.png"))); // NOI18N
        btn_DetalleAsistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DetalleAsistenciaActionPerformed(evt);
            }
        });
        pnl_Asistencia.add(btn_DetalleAsistencia);
        btn_DetalleAsistencia.setBounds(30, 390, 90, 70);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Ordenar.png"))); // NOI18N
        jLabel1.setText("Ordenar:");
        pnl_Asistencia.add(jLabel1);
        jLabel1.setBounds(30, 150, 80, 30);

        txt_RegistrarAsistenciaRut.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txt_RegistrarAsistenciaRut.setForeground(new java.awt.Color(0, 204, 255));
        txt_RegistrarAsistenciaRut.setToolTipText("Busca un Personal Training...");
        txt_RegistrarAsistenciaRut.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        txt_RegistrarAsistenciaRut.setPlaceholder("Ingresa la asistencia del alumno manualmente");
        txt_RegistrarAsistenciaRut.setSelectedTextColor(new java.awt.Color(255, 102, 102));
        txt_RegistrarAsistenciaRut.setSelectionColor(new java.awt.Color(255, 255, 0));
        txt_RegistrarAsistenciaRut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_RegistrarAsistenciaRutKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_RegistrarAsistenciaRutKeyReleased(evt);
            }
        });
        pnl_Asistencia.add(txt_RegistrarAsistenciaRut);
        txt_RegistrarAsistenciaRut.setBounds(750, 40, 390, 30);
        txt_RegistrarAsistenciaRut.getAccessibleContext().setAccessibleDescription("Ingresa la asistencia del alumno manualmente");

        tgl_Asistencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Botones/Btn-Off.png"))); // NOI18N
        tgl_Asistencia.setToolTipText("Cambia para visualizar alumnos o personal training");
        tgl_Asistencia.setBorderPainted(false);
        tgl_Asistencia.setContentAreaFilled(false);
        tgl_Asistencia.setFocusPainted(false);
        tgl_Asistencia.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Botones/Btn-On.png"))); // NOI18N
        tgl_Asistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_AsistenciaActionPerformed(evt);
            }
        });
        pnl_Asistencia.add(tgl_Asistencia);
        tgl_Asistencia.setBounds(20, 210, 60, 50);

        lbl_Todo.setFont(new java.awt.Font("Mairy Oblicua", 0, 20)); // NOI18N
        lbl_Todo.setForeground(new java.awt.Color(0, 204, 255));
        lbl_Todo.setText("Todo");
        pnl_Asistencia.add(lbl_Todo);
        lbl_Todo.setBounds(30, 190, 50, 20);

        lbl_Solohoy.setFont(new java.awt.Font("Mairy Oblicua", 0, 20)); // NOI18N
        lbl_Solohoy.setForeground(new java.awt.Color(0, 204, 255));
        lbl_Solohoy.setText("Solo hoy");
        pnl_Asistencia.add(lbl_Solohoy);
        lbl_Solohoy.setBounds(30, 190, 70, 20);

        getContentPane().add(pnl_Asistencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 1270, 540));

        btn_Menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Menú Normal.png"))); // NOI18N
        btn_Menu.setBorderPainted(false);
        btn_Menu.setContentAreaFilled(false);
        btn_Menu.setFocusPainted(false);
        btn_Menu.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Menú Mouse.png"))); // NOI18N
        btn_Menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_MenuActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 50, 40));

        lbl_Nombre.setFont(new java.awt.Font("Mairy Black Oblicua", 0, 30)); // NOI18N
        lbl_Nombre.setForeground(new java.awt.Color(0, 204, 255));
        lbl_Nombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Nombre.setText("-------");
        getContentPane().add(lbl_Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 20, 800, 60));

        pnl_PersonalTraining.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tb_PersonalTraining.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tb_PersonalTraining.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Rut", "Nombre", "Correo", "Teléfono", "Edad", "Tipo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_PersonalTraining.setColorBackgoundHead(new java.awt.Color(0, 153, 153));
        tb_PersonalTraining.setColorBordeFilas(new java.awt.Color(0, 255, 255));
        tb_PersonalTraining.setColorBordeHead(new java.awt.Color(255, 255, 255));
        tb_PersonalTraining.setColorFilasBackgound2(new java.awt.Color(204, 255, 255));
        tb_PersonalTraining.setColorFilasForeground1(new java.awt.Color(0, 153, 255));
        tb_PersonalTraining.setColorFilasForeground2(new java.awt.Color(0, 153, 255));
        tb_PersonalTraining.setColorSelBackgound(new java.awt.Color(0, 102, 204));
        tb_PersonalTraining.setFillsViewportHeight(true);
        tb_PersonalTraining.setFuenteFilas(new java.awt.Font("Mairy Oblicua", 0, 21)); // NOI18N
        tb_PersonalTraining.setFuenteFilasSelect(new java.awt.Font("Mairy Oblicua", 0, 24)); // NOI18N
        tb_PersonalTraining.setFuenteHead(new java.awt.Font("Mairy Bold", 1, 24)); // NOI18N
        tb_PersonalTraining.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_PersonalTrainingMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tb_PersonalTraining);

        pnl_PersonalTraining.add(jScrollPane3);
        jScrollPane3.setBounds(110, 100, 1120, 410);

        lbl_PersonalTraining.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Personal Training.png"))); // NOI18N
        pnl_PersonalTraining.add(lbl_PersonalTraining);
        lbl_PersonalTraining.setBounds(110, 10, 370, 90);

        lbl_Lupa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Lupa.png"))); // NOI18N
        pnl_PersonalTraining.add(lbl_Lupa);
        lbl_Lupa.setBounds(1160, 30, 40, 50);

        lbl_BuscarPersonalTraining.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Buscar.png"))); // NOI18N
        pnl_PersonalTraining.add(lbl_BuscarPersonalTraining);
        lbl_BuscarPersonalTraining.setBounds(660, 30, 70, 50);

        txt_PersonalTraining.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txt_PersonalTraining.setForeground(new java.awt.Color(0, 204, 255));
        txt_PersonalTraining.setToolTipText("Busca un Personal Training...");
        txt_PersonalTraining.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        txt_PersonalTraining.setPlaceholder("Busca un Personal Training");
        txt_PersonalTraining.setSelectedTextColor(new java.awt.Color(255, 102, 102));
        txt_PersonalTraining.setSelectionColor(new java.awt.Color(255, 255, 0));
        txt_PersonalTraining.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_PersonalTrainingKeyReleased(evt);
            }
        });
        pnl_PersonalTraining.add(txt_PersonalTraining);
        txt_PersonalTraining.setBounds(750, 40, 390, 30);

        btn_AgregarPersonalTraining.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/AñadirE.png"))); // NOI18N
        btn_AgregarPersonalTraining.setBorderPainted(false);
        btn_AgregarPersonalTraining.setContentAreaFilled(false);
        btn_AgregarPersonalTraining.setFocusPainted(false);
        btn_AgregarPersonalTraining.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Añadir Mouse.png"))); // NOI18N
        btn_AgregarPersonalTraining.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AgregarPersonalTrainingActionPerformed(evt);
            }
        });
        pnl_PersonalTraining.add(btn_AgregarPersonalTraining);
        btn_AgregarPersonalTraining.setBounds(10, 160, 90, 70);

        btn_EditarPersonalTraining.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/ModificarE.png"))); // NOI18N
        btn_EditarPersonalTraining.setBorderPainted(false);
        btn_EditarPersonalTraining.setContentAreaFilled(false);
        btn_EditarPersonalTraining.setFocusPainted(false);
        btn_EditarPersonalTraining.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Modificar Mouse.png"))); // NOI18N
        btn_EditarPersonalTraining.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EditarPersonalTrainingActionPerformed(evt);
            }
        });
        pnl_PersonalTraining.add(btn_EditarPersonalTraining);
        btn_EditarPersonalTraining.setBounds(10, 270, 90, 70);

        btn_InhabilitarPersonalTraining.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/EliminarE.png"))); // NOI18N
        btn_InhabilitarPersonalTraining.setBorderPainted(false);
        btn_InhabilitarPersonalTraining.setContentAreaFilled(false);
        btn_InhabilitarPersonalTraining.setFocusPainted(false);
        btn_InhabilitarPersonalTraining.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Eliminar Mouse.png"))); // NOI18N
        pnl_PersonalTraining.add(btn_InhabilitarPersonalTraining);
        btn_InhabilitarPersonalTraining.setBounds(10, 390, 90, 70);

        ckb_AbrirPersonalTraining.setForeground(new java.awt.Color(0, 204, 255));
        ckb_AbrirPersonalTraining.setText("Abrir Automáticamente");
        ckb_AbrirPersonalTraining.setFocusPainted(false);
        ckb_AbrirPersonalTraining.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        ckb_AbrirPersonalTraining.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckb_AbrirPersonalTrainingActionPerformed(evt);
            }
        });
        pnl_PersonalTraining.add(ckb_AbrirPersonalTraining);
        ckb_AbrirPersonalTraining.setBounds(980, 510, 177, 30);

        getContentPane().add(pnl_PersonalTraining, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 1280, 540));

        pnl_Alumno.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tb_Alumno.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tb_Alumno.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Rut", "Nombre", "Dirección", "Teléfono", "Pago", "Correo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_Alumno.setColorBackgoundHead(new java.awt.Color(0, 153, 153));
        tb_Alumno.setColorBordeFilas(new java.awt.Color(0, 255, 255));
        tb_Alumno.setColorBordeHead(new java.awt.Color(255, 255, 255));
        tb_Alumno.setColorFilasBackgound2(new java.awt.Color(204, 255, 255));
        tb_Alumno.setColorFilasForeground1(new java.awt.Color(0, 153, 255));
        tb_Alumno.setColorFilasForeground2(new java.awt.Color(0, 153, 255));
        tb_Alumno.setFillsViewportHeight(true);
        tb_Alumno.setFuenteFilas(new java.awt.Font("Mairy Oblicua", 0, 21)); // NOI18N
        tb_Alumno.setFuenteFilasSelect(new java.awt.Font("Mairy Oblicua", 0, 21)); // NOI18N
        tb_Alumno.setFuenteHead(new java.awt.Font("Mairy Bold", 1, 24)); // NOI18N
        tb_Alumno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_AlumnoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tb_Alumno);

        pnl_Alumno.add(jScrollPane4);
        jScrollPane4.setBounds(110, 100, 1130, 410);

        lbl_Alumnos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Alumnos.png"))); // NOI18N
        pnl_Alumno.add(lbl_Alumnos);
        lbl_Alumnos.setBounds(110, 10, 170, 90);

        btn_AgregarAlumno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/AñadirE.png"))); // NOI18N
        btn_AgregarAlumno.setBorderPainted(false);
        btn_AgregarAlumno.setContentAreaFilled(false);
        btn_AgregarAlumno.setFocusPainted(false);
        btn_AgregarAlumno.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Añadir Mouse.png"))); // NOI18N
        btn_AgregarAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AgregarAlumnoActionPerformed(evt);
            }
        });
        pnl_Alumno.add(btn_AgregarAlumno);
        btn_AgregarAlumno.setBounds(10, 140, 90, 70);

        btn_EditarAlumno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/ModificarE.png"))); // NOI18N
        btn_EditarAlumno.setBorderPainted(false);
        btn_EditarAlumno.setContentAreaFilled(false);
        btn_EditarAlumno.setFocusPainted(false);
        btn_EditarAlumno.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Modificar Mouse.png"))); // NOI18N
        btn_EditarAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EditarAlumnoActionPerformed(evt);
            }
        });
        pnl_Alumno.add(btn_EditarAlumno);
        btn_EditarAlumno.setBounds(10, 260, 90, 70);

        btn_InhabilitarAlumno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/EliminarE.png"))); // NOI18N
        btn_InhabilitarAlumno.setBorderPainted(false);
        btn_InhabilitarAlumno.setContentAreaFilled(false);
        btn_InhabilitarAlumno.setFocusPainted(false);
        btn_InhabilitarAlumno.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Eliminar Mouse.png"))); // NOI18N
        pnl_Alumno.add(btn_InhabilitarAlumno);
        btn_InhabilitarAlumno.setBounds(10, 380, 90, 70);

        lbl_Buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Buscar.png"))); // NOI18N
        pnl_Alumno.add(lbl_Buscar);
        lbl_Buscar.setBounds(660, 30, 70, 50);

        txt_Alumno.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txt_Alumno.setForeground(new java.awt.Color(0, 204, 255));
        txt_Alumno.setToolTipText("Busca un Alumno...");
        txt_Alumno.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        txt_Alumno.setPlaceholder("Busca un Alumno");
        txt_Alumno.setSelectedTextColor(new java.awt.Color(255, 102, 102));
        txt_Alumno.setSelectionColor(new java.awt.Color(255, 255, 0));
        txt_Alumno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_AlumnoKeyReleased(evt);
            }
        });
        pnl_Alumno.add(txt_Alumno);
        txt_Alumno.setBounds(750, 40, 390, 30);

        lbl_Lupa1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Lupa.png"))); // NOI18N
        pnl_Alumno.add(lbl_Lupa1);
        lbl_Lupa1.setBounds(1160, 30, 40, 50);

        ckb_AbrirAlumno.setForeground(new java.awt.Color(0, 204, 255));
        ckb_AbrirAlumno.setText("Abrir Automáticamente");
        ckb_AbrirAlumno.setFocusPainted(false);
        ckb_AbrirAlumno.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        ckb_AbrirAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckb_AbrirAlumnoActionPerformed(evt);
            }
        });
        pnl_Alumno.add(ckb_AbrirAlumno);
        ckb_AbrirAlumno.setBounds(980, 510, 177, 30);

        getContentPane().add(pnl_Alumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 1290, 540));

        lbl_Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/1.png"))); // NOI18N
        getContentPane().add(lbl_Logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 250, 70));

        lbl_LogoInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Logo1.png"))); // NOI18N
        getContentPane().add(lbl_LogoInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 820, 670));

        lbl_imgfondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Menu.jpg"))); // NOI18N
        getContentPane().add(lbl_imgfondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 690));

        menu_Principal.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        menu_Principal.setForeground(new java.awt.Color(0, 153, 255));
        menu_Principal.setFont(new java.awt.Font("Mairy Oblicua", 0, 14)); // NOI18N

        menu_Usuario.setForeground(new java.awt.Color(0, 204, 255));
        menu_Usuario.setText("Usuario");
        menu_Usuario.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N

        menu_PesonalTrainnig.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        menu_PesonalTrainnig.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        menu_PesonalTrainnig.setForeground(new java.awt.Color(0, 204, 255));
        menu_PesonalTrainnig.setText("Ver Personal Trainnig");
        menu_PesonalTrainnig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_PesonalTrainnigActionPerformed(evt);
            }
        });
        menu_Usuario.add(menu_PesonalTrainnig);

        menu_CerrarSesion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        menu_CerrarSesion.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        menu_CerrarSesion.setForeground(new java.awt.Color(0, 204, 255));
        menu_CerrarSesion.setText("Cerrar Sesión");
        menu_CerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_CerrarSesionActionPerformed(evt);
            }
        });
        menu_Usuario.add(menu_CerrarSesion);
        menu_Usuario.add(jSeparator3);

        menu_CerrarSportMedics.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menu_CerrarSportMedics.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        menu_CerrarSportMedics.setForeground(new java.awt.Color(0, 204, 255));
        menu_CerrarSportMedics.setText("Cerrar SportMedics");
        menu_CerrarSportMedics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_CerrarSportMedicsActionPerformed(evt);
            }
        });
        menu_Usuario.add(menu_CerrarSportMedics);

        menu_Principal.add(menu_Usuario);

        menu_Ver.setForeground(new java.awt.Color(0, 204, 255));
        menu_Ver.setText("Ver");
        menu_Ver.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N

        menu_Asistencia.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        menu_Asistencia.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        menu_Asistencia.setForeground(new java.awt.Color(0, 204, 255));
        menu_Asistencia.setText("Asistencia");
        menu_Asistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_AsistenciaActionPerformed(evt);
            }
        });
        menu_Ver.add(menu_Asistencia);

        menu_PersonalTrainning.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        menu_PersonalTrainning.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        menu_PersonalTrainning.setForeground(new java.awt.Color(0, 204, 255));
        menu_PersonalTrainning.setText("Personal Trainning");
        menu_PersonalTrainning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_PersonalTrainningActionPerformed(evt);
            }
        });
        menu_Ver.add(menu_PersonalTrainning);

        menu_Alumnos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        menu_Alumnos.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        menu_Alumnos.setForeground(new java.awt.Color(0, 204, 255));
        menu_Alumnos.setText("Alumnos");
        menu_Alumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_AlumnosActionPerformed(evt);
            }
        });
        menu_Ver.add(menu_Alumnos);

        menu_VistaDyR.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        menu_VistaDyR.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        menu_VistaDyR.setForeground(new java.awt.Color(0, 204, 255));
        menu_VistaDyR.setText("Rutinas y Dietas");
        menu_VistaDyR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_VistaDyRActionPerformed(evt);
            }
        });
        menu_Ver.add(menu_VistaDyR);
        menu_Ver.add(jSeparator2);

        jSeparator1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        menu_Ver.add(jSeparator1);

        menu_Principal.add(menu_Ver);

        jMenu1.setForeground(new java.awt.Color(0, 204, 255));
        jMenu1.setText("Administrar");
        jMenu1.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N

        menu_AdministrarGrupos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        menu_AdministrarGrupos.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        menu_AdministrarGrupos.setForeground(new java.awt.Color(0, 204, 255));
        menu_AdministrarGrupos.setText("Adminstar Grupos");
        menu_AdministrarGrupos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_AdministrarGruposActionPerformed(evt);
            }
        });
        jMenu1.add(menu_AdministrarGrupos);

        menu_AsignarRecursos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menu_AsignarRecursos.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        menu_AsignarRecursos.setForeground(new java.awt.Color(0, 204, 255));
        menu_AsignarRecursos.setText("Asignar Recursos");
        menu_AsignarRecursos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_AsignarRecursosActionPerformed(evt);
            }
        });
        jMenu1.add(menu_AsignarRecursos);

        menu_Tipo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        menu_Tipo.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        menu_Tipo.setForeground(new java.awt.Color(0, 204, 255));
        menu_Tipo.setText("Crear Tipo");
        menu_Tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_TipoActionPerformed(evt);
            }
        });
        jMenu1.add(menu_Tipo);

        menu_HuellaDigital.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        menu_HuellaDigital.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        menu_HuellaDigital.setForeground(new java.awt.Color(0, 204, 255));
        menu_HuellaDigital.setText("Administar Huellas Digitales");
        menu_HuellaDigital.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_HuellaDigitalActionPerformed(evt);
            }
        });
        jMenu1.add(menu_HuellaDigital);

        menu_Principal.add(jMenu1);

        Menu_Configuracion.setForeground(new java.awt.Color(0, 204, 255));
        Menu_Configuracion.setText("Configuración");
        Menu_Configuracion.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N

        menu_Contraseña.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        menu_Contraseña.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        menu_Contraseña.setForeground(new java.awt.Color(0, 204, 255));
        menu_Contraseña.setText("Cambiar Contraseña");
        menu_Contraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_ContraseñaActionPerformed(evt);
            }
        });
        Menu_Configuracion.add(menu_Contraseña);

        menu_AdministrarDeportes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        menu_AdministrarDeportes.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        menu_AdministrarDeportes.setForeground(new java.awt.Color(0, 204, 255));
        menu_AdministrarDeportes.setText("Configurar Deportes");
        menu_AdministrarDeportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_AdministrarDeportesActionPerformed(evt);
            }
        });
        Menu_Configuracion.add(menu_AdministrarDeportes);

        menu_AdministrarFisicos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        menu_AdministrarFisicos.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        menu_AdministrarFisicos.setForeground(new java.awt.Color(0, 204, 255));
        menu_AdministrarFisicos.setText("Configurar Fisicos");
        menu_AdministrarFisicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_AdministrarFisicosActionPerformed(evt);
            }
        });
        Menu_Configuracion.add(menu_AdministrarFisicos);

        menu_CFecha.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menu_CFecha.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        menu_CFecha.setForeground(new java.awt.Color(0, 204, 255));
        menu_CFecha.setText("Configurar Fechas");
        menu_CFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_CFechaActionPerformed(evt);
            }
        });
        Menu_Configuracion.add(menu_CFecha);

        menu_Principal.add(Menu_Configuracion);

        setJMenuBar(menu_Principal);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_MenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_MenuActionPerformed
        if (pnl_Menu.isVisible()) 
            pnl_Menu.setVisible(false);
        else
            pnl_Menu.setVisible(true);
    }//GEN-LAST:event_btn_MenuActionPerformed

    private void btn_AsistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AsistenciaActionPerformed
        activarAsistencia();
    }//GEN-LAST:event_btn_AsistenciaActionPerformed

    private void btn_PersonalTrainingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_PersonalTrainingActionPerformed
        activarPersonalTrainning();
    }//GEN-LAST:event_btn_PersonalTrainingActionPerformed

    private void btn_AlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AlumnoActionPerformed
        activarAlumno();
    }//GEN-LAST:event_btn_AlumnoActionPerformed

    private void btn_AgregarPersonalTrainingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AgregarPersonalTrainingActionPerformed
        nuevoPersonalTraining();
    }//GEN-LAST:event_btn_AgregarPersonalTrainingActionPerformed

    private void btn_RegistrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RegistrosActionPerformed
        new VistaDyR(new javax.swing.JDialog(), true).setVisible(true);
    }//GEN-LAST:event_btn_RegistrosActionPerformed

    private void btn_DetalleAsistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DetalleAsistenciaActionPerformed
        detalleAsistencia();    
    }//GEN-LAST:event_btn_DetalleAsistenciaActionPerformed

    private void btn_AgregarAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AgregarAlumnoActionPerformed
        nuevoAlumno();
    }//GEN-LAST:event_btn_AgregarAlumnoActionPerformed

    private void txt_PersonalTrainingKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_PersonalTrainingKeyReleased
        tablaPersonaltraining(true, false);
    }//GEN-LAST:event_txt_PersonalTrainingKeyReleased

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Iniciar();
        start();
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        stop();
    }//GEN-LAST:event_formWindowClosed

    private void tb_PersonalTrainingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_PersonalTrainingMouseClicked
       if (evt.getClickCount() == 2)
           modificarPersonalTraining();
    }//GEN-LAST:event_tb_PersonalTrainingMouseClicked

    private void btn_EditarPersonalTrainingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EditarPersonalTrainingActionPerformed
        if (tb_PersonalTraining.getSelectedRow() >= 0) 
            modificarPersonalTraining();
        else
            JOptionPane.showMessageDialog(null, "Favor, selecciona un personal training :(","Precaución! D:",JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_btn_EditarPersonalTrainingActionPerformed

    private void tb_AlumnoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_AlumnoMouseClicked
        if (evt.getClickCount() == 2)
            modificarAlumno();
    }//GEN-LAST:event_tb_AlumnoMouseClicked

    private void menu_ContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_ContraseñaActionPerformed
        new Contraseña(new javax.swing.JDialog(), true, idPersonal).setVisible(true);
    }//GEN-LAST:event_menu_ContraseñaActionPerformed

    private void menu_AdministrarDeportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_AdministrarDeportesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menu_AdministrarDeportesActionPerformed

    private void menu_AsignarRecursosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_AsignarRecursosActionPerformed
        new AsignarRecursos(new javax.swing.JDialog(), true).setVisible(true);
    }//GEN-LAST:event_menu_AsignarRecursosActionPerformed

    private void menu_AdministrarGruposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_AdministrarGruposActionPerformed
        new AdministarGrupos(this, true).setVisible(true);
    }//GEN-LAST:event_menu_AdministrarGruposActionPerformed

    private void menu_TipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_TipoActionPerformed
        new CrearTipo(new javax.swing.JDialog(), true).setVisible(true);
    }//GEN-LAST:event_menu_TipoActionPerformed

    private void menu_PesonalTrainnigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_PesonalTrainnigActionPerformed
        
    }//GEN-LAST:event_menu_PesonalTrainnigActionPerformed

    private void menu_CFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_CFechaActionPerformed
        new AdmnistarFechas(this, true).setVisible(true);
    }//GEN-LAST:event_menu_CFechaActionPerformed

    private void menu_AsistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_AsistenciaActionPerformed
        activarAsistencia();
    }//GEN-LAST:event_menu_AsistenciaActionPerformed

    private void menu_PersonalTrainningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_PersonalTrainningActionPerformed
        activarPersonalTrainning();
    }//GEN-LAST:event_menu_PersonalTrainningActionPerformed

    private void menu_AlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_AlumnosActionPerformed
        activarAlumno();
    }//GEN-LAST:event_menu_AlumnosActionPerformed

    private void menu_VistaDyRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_VistaDyRActionPerformed
        new VistaDyR(new javax.swing.JDialog(), true).setVisible(true);
    }//GEN-LAST:event_menu_VistaDyRActionPerformed

    private void menu_HuellaDigitalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_HuellaDigitalActionPerformed
        stop();
        new AdministrarHuellaDigital(new javax.swing.JDialog(), true).setVisible(true);
        start();
    }//GEN-LAST:event_menu_HuellaDigitalActionPerformed

    private void txt_RegistrarAsistenciaRutKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_RegistrarAsistenciaRutKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_RegistrarAsistenciaRutKeyReleased

    private void ckb_AbrirPersonalTrainingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckb_AbrirPersonalTrainingActionPerformed
        
    }//GEN-LAST:event_ckb_AbrirPersonalTrainingActionPerformed

    private void ckb_AbrirAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckb_AbrirAlumnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ckb_AbrirAlumnoActionPerformed

    private void txt_AlumnoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_AlumnoKeyReleased
        tablaAlumno(true, false);
    }//GEN-LAST:event_txt_AlumnoKeyReleased

    private void tgl_AsistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_AsistenciaActionPerformed
        asistencia();
    }//GEN-LAST:event_tgl_AsistenciaActionPerformed

    private void txt_RegistrarAsistenciaRutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_RegistrarAsistenciaRutKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            try {
                ingresarAsistencia(new Alumno(txt_RegistrarAsistenciaRut.getText()).obtenerAlumno().getId());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "El rut ingresado no existe o es inválido D:","Precaución",JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_txt_RegistrarAsistenciaRutKeyPressed

    private void btn_EditarAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EditarAlumnoActionPerformed
        modificarAlumno();
    }//GEN-LAST:event_btn_EditarAlumnoActionPerformed

    private void menu_CerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_CerrarSesionActionPerformed
        cerrarSesion();
    }//GEN-LAST:event_menu_CerrarSesionActionPerformed

    private void menu_CerrarSportMedicsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_CerrarSportMedicsActionPerformed
        System.exit(0);
    }//GEN-LAST:event_menu_CerrarSportMedicsActionPerformed

    private void menu_AdministrarFisicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_AdministrarFisicosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menu_AdministrarFisicosActionPerformed

    private void tb_AsistenciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_AsistenciaMouseClicked
        if (evt.getClickCount() == 2)
            detalleAsistencia();
    }//GEN-LAST:event_tb_AsistenciaMouseClicked

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Menu_Configuracion;
    private javax.swing.JButton btn_AgregarAlumno;
    private javax.swing.JButton btn_AgregarPersonalTraining;
    private javax.swing.JButton btn_Alumno;
    private javax.swing.JButton btn_Asistencia;
    private javax.swing.JButton btn_DetalleAsistencia;
    private javax.swing.JButton btn_EditarAlumno;
    private javax.swing.JButton btn_EditarPersonalTraining;
    private javax.swing.JButton btn_InhabilitarAlumno;
    private javax.swing.JButton btn_InhabilitarPersonalTraining;
    private javax.swing.JButton btn_Menu;
    private javax.swing.JButton btn_PersonalTraining;
    private javax.swing.JButton btn_Registros;
    private Extra.MiCheckBox ckb_AbrirAlumno;
    private Extra.MiCheckBox ckb_AbrirPersonalTraining;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JLabel lbl_Alumnos;
    private javax.swing.JLabel lbl_Asistencia;
    private javax.swing.JLabel lbl_Buscar;
    private javax.swing.JLabel lbl_BuscarPersonalTraining;
    private javax.swing.JLabel lbl_Logo;
    private javax.swing.JLabel lbl_LogoInicio;
    private javax.swing.JLabel lbl_Lupa;
    private javax.swing.JLabel lbl_Lupa1;
    private javax.swing.JLabel lbl_Nombre;
    private javax.swing.JLabel lbl_Panel;
    private javax.swing.JLabel lbl_PersonalTraining;
    private javax.swing.JLabel lbl_Solohoy;
    private javax.swing.JLabel lbl_Todo;
    private javax.swing.JLabel lbl_imgfondo;
    private javax.swing.JMenuItem menu_AdministrarDeportes;
    private javax.swing.JMenuItem menu_AdministrarFisicos;
    private javax.swing.JMenuItem menu_AdministrarGrupos;
    private javax.swing.JMenuItem menu_Alumnos;
    private javax.swing.JMenuItem menu_AsignarRecursos;
    private javax.swing.JMenuItem menu_Asistencia;
    private javax.swing.JMenuItem menu_CFecha;
    private javax.swing.JMenuItem menu_CerrarSesion;
    private javax.swing.JMenuItem menu_CerrarSportMedics;
    private javax.swing.JMenuItem menu_Contraseña;
    private javax.swing.JMenuItem menu_HuellaDigital;
    private javax.swing.JMenuItem menu_PersonalTrainning;
    private javax.swing.JMenuItem menu_PesonalTrainnig;
    private javax.swing.JMenuBar menu_Principal;
    private javax.swing.JMenuItem menu_Tipo;
    private javax.swing.JMenu menu_Usuario;
    private javax.swing.JMenu menu_Ver;
    private javax.swing.JMenuItem menu_VistaDyR;
    private javax.swing.JLayeredPane pnl_Alumno;
    private javax.swing.JLayeredPane pnl_Asistencia;
    private javax.swing.JPanel pnl_Menu;
    private javax.swing.JLayeredPane pnl_PersonalTraining;
    private rojerusan.RSTableMetro tb_Alumno;
    private rojerusan.RSTableMetro tb_Asistencia;
    private rojerusan.RSTableMetro tb_PersonalTraining;
    private javax.swing.JToggleButton tgl_Asistencia;
    private Extra.TextPlaceholder txt_Alumno;
    private Extra.TextPlaceholder txt_PersonalTraining;
    private Extra.TextPlaceholder txt_RegistrarAsistenciaRut;
    // End of variables declaration//GEN-END:variables

    private void desactivaPanel() {
        pnl_Asistencia.setVisible(false);
        pnl_PersonalTraining.setVisible(false);
        pnl_Alumno.setVisible(false);
    }
    
    private void activarAsistencia(){
        desactivaPanel();
        pnl_Asistencia.setVisible(true);
        pnl_Menu.setVisible(false);
    }
    
    private void activarPersonalTrainning(){
        desactivaPanel();
        pnl_PersonalTraining.setVisible(true);
        pnl_Menu.setVisible(false);
    }
    
    private void activarAlumno(){
        desactivaPanel();
        pnl_Alumno.setVisible(true);
        pnl_Menu.setVisible(false);
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
                SwingUtilities.invokeLater(() -> {
                    get.reproducirSonido("Exitoso");
                    procesarCaptura(e.getSample());
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
                SwingUtilities.invokeLater(() -> {
                    get.reproducirSonido("Denegado");
                });
            }
        });

        Lector.addErrorListener(new DPFPErrorAdapter(){
            public void errorReader(final DPFPErrorEvent e){
                SwingUtilities.invokeLater(() -> {
                    get.reproducirSonido("Denegado");
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
            int panel = panelVisible();
            ArrayList<HuellaDigital> huellaDigital = new HuellaDigital().obtenerHuellaDigital(cargaHuella(panel));
           
            try {
                for (int i = 0; i < huellaDigital.size(); i++) {
                    DPFPTemplate referenceTemplate = DPFPGlobal.getTemplateFactory().createTemplate((byte[]) huellaDigital.get(i).getHuella());
                    setTemplate(referenceTemplate);
                    DPFPVerificationResult resul = Verificador.verify(featureverificacion, getTemplate());
                    
                    if (resul.isVerified()){
                        switch (panel) {
                            case 1:
                                busquedaAutomaticaHuellaAlumno(huellaDigital.get(i).getIdUser() + "", false, true);
                                
                                if (ckb_AbrirAlumno.isSelected()) 
                                    modificarAlumno(0);
                            break;
                                
                            case 2:
                                busquedaAutomaticaHuellaPersonalTraining(huellaDigital.get(i).getIdUser() + "", false, true);
                                
                                if (ckb_AbrirPersonalTraining.isSelected()) 
                                    modificarPersonalTraining(0);
                            break;
                            
                            case 3:
                                ingresarAsistencia(huellaDigital.get(i).getIdUser());
                            break;
                        }
                        break;
                    }
                    else{
                        switch (panel) {
                            case 1:
                                busquedaAutomaticaHuellaAlumno("", false, true);
                                break;
                            case 2:
                                 busquedaAutomaticaHuellaPersonalTraining("", false, true);
                                break;
                        }
                    }
                }

            } catch (IllegalArgumentException | HeadlessException e) {
                JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado :(!\n" + e.getMessage(),"Error! x.x",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void modificarPersonalTraining(int fila) { 
        stop();
        new IngresarPersonalTraining(new javax.swing.JDialog(), true, get.reemplazo(tb_PersonalTraining.getValueAt(fila, 0).toString(), "."), true).setVisible(true);
        busquedaAutomaticaHuellaPersonalTraining(txt_PersonalTraining.getText(), false, true);
        start();
    }
    
    private void modificarPersonalTraining() { 
        if (tb_PersonalTraining.getSelectedRow() > -1) {
            stop();
            new IngresarPersonalTraining(new javax.swing.JDialog(), true, get.reemplazo(tb_PersonalTraining.getValueAt(tb_PersonalTraining.getSelectedRow(), 0).toString(), "."), true).setVisible(true);
            busquedaAutomaticaHuellaPersonalTraining(txt_PersonalTraining.getText(), true, false);
            start();
        }
        else
            JOptionPane.showMessageDialog(null, "Favor selecciona una fila D:", "Precaución D:",JOptionPane.WARNING_MESSAGE);
    }
    
    private void modificarAlumno(int fila) {
        stop();
        new IngresarAlumno(new javax.swing.JDialog(), true, get.reemplazo(tb_Alumno.getValueAt(fila, 0).toString(), "."), true).setVisible(true);
        busquedaAutomaticaHuellaAlumno(txt_Alumno.getText(), false, true);
        start();
    }

    private void modificarAlumno() {
        if (tb_Alumno.getSelectedRow() > -1) {
            stop();
            new IngresarAlumno(new javax.swing.JDialog(), true, get.reemplazo(tb_Alumno.getValueAt(tb_Alumno.getSelectedRow(), 0).toString(), "."), true).setVisible(true);
            busquedaAutomaticaHuellaAlumno(txt_Alumno.getText(), true, false);
            start();
        }
        else
            JOptionPane.showMessageDialog(null, "Favor selecciona una fila D:", "Precaución D:",JOptionPane.WARNING_MESSAGE);
    }

    private int panelVisible() {
        if (pnl_Alumno.isVisible())
            return 1;
        else if (pnl_PersonalTraining.isVisible())
            return 2;
        else if (pnl_Asistencia.isVisible())
            return 3;
        else 
            return 0;
    }
    
    private String cargaHuella(int panel){
        switch (panel) {
            case 1:
            case 3:    
                return "Alumno";
                
            case 2:
                return "PersonalTraining";
                
            default:
                return "";
        }
    }

    private void nuevoAlumno() {
        new IngresarAlumno(new javax.swing.JDialog(), true, null, true).setVisible(true);
        tablaAlumno(false, false);
    }

    private void nuevoPersonalTraining() {
        new IngresarPersonalTraining(new javax.swing.JDialog(), true, null, true).setVisible(true);
        tablaPersonaltraining(false, false);
    }

    private void busquedaAutomaticaHuellaAlumno(String busqueda, boolean buscar, boolean huella) {
        txt_Alumno.setText(busqueda);
        tablaAlumno(buscar, huella);
    }

    private void busquedaAutomaticaHuellaPersonalTraining(String busqueda, boolean buscar, boolean huella) {
        txt_PersonalTraining.setText(busqueda);
        tablaPersonaltraining(buscar, huella);
    }

    private Asistencia cargarAsistencia(int idAlumno) {
        return new Asistencia(
            new Alumno(idAlumno)
        );
    }

    private void ingresarAsistencia(int idUser) {
        switch (cargarAsistencia(idUser).ingresarAsistencia()) {
            case 1:
                tablaAsistencia();
                //JOptionPane.showMessageDialog(null, "Favor selecciona una fila D:", "Precaución D:",JOptionPane.WARNING_MESSAGE);
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "El Alumno no existe!", "Precaución D:",JOptionPane.WARNING_MESSAGE);
                break;
            case 3:
                //JOptionPane.showMessageDialog(null, "Favor selecciona una fila D:", "Precaución D:",JOptionPane.WARNING_MESSAGE);
                break;
            case 4:
                JOptionPane.showMessageDialog(null, "El alumno no ha realizado ningún pago!", "Precaución D:",JOptionPane.WARNING_MESSAGE);
                break;
        }
    }

    private void asistencia() {
        lbl_Todo.setVisible(tgl_Asistencia.isSelected());
        lbl_Solohoy.setVisible(!tgl_Asistencia.isSelected());
        tablaAsistencia();
    }

    private void detalleAsistencia() {
        if (tb_Asistencia.getSelectedRow() > -1) 
            new ControlAsistencia(new javax.swing.JDialog(), true, get.reemplazo(tb_Asistencia.getValueAt(tb_Asistencia.getSelectedRow(), 2).toString(), ".")).setVisible(true);
        else
            JOptionPane.showMessageDialog(null, "Favor selecciona una asistencia! D:", "Precaución D:",JOptionPane.WARNING_MESSAGE);
    }
}

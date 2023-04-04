package Interfaz;

import Clases.PersonalTraining;
import Clases.Ruta;
import Extra.Obtener;
import Extra.Validacion;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * @author keiss
 */
public class IngresarPersonalTraining extends javax.swing.JDialog {

    private JFileChooser seleccionar = new JFileChooser();
    private File archivo;
    private final Obtener get = new Obtener();
    private final Validacion validar = new Validacion();
    private boolean ingresar, cambiaFoto;
//    private int id;
    private Date fecha;
    
    private void iniciarIngreso() {
        setTitle("Ingresar Personal Training - SportMedics");
        get.cargarFoto(System.getProperty("user.dir") + "\\PersonalTraining\\User.png", lbl_Foto);
        txt_Rut.requestFocus();
        ingresar = true;
        btn_IngresarHuella.setEnabled(false);
        dc_FechaNacimiento.setVisible(true);
    }
    
    private void iniciarModificacion(String rut, boolean huella) {
        setTitle("Modificar Personal Training - SportMedics");
        ingresar = false;
        //Setiamos el rut del personal para obtener todos sus datos
        PersonalTraining personal = new PersonalTraining();
        personal.setRut(rut);
        personal = personal.obtenerPersonalTraining();
        //Cargamos todos los datos obtenidos en la base de datos
//        id = personal.getId();
        txt_Rut.setText(get.rutReal(personal.getRut()));
        txt_Nombre.setText(personal.getNombre());
        txt_Apellido.setText(personal.getApellidoP());
        txt_ApellidoMaterno.setText(personal.getApellidoM());
        txt_Direccion.setText(personal.getDireccion());
        txt_Correo.setText(personal.getCorreo());
        txt_Telefono.setText(personal.getFono() + "");
        txt_Pin.setText(personal.getPin());
        txt_Pin2.setText(personal.getPin());
        cmb_Tipo.setSelectedItem(personal.getTipo());
        fecha = personal.getFechaNacimiento();
        txt_Edad.setText(String.valueOf(get.calculaEdad(fecha)));
        txt_Edad.setVisible(true);
        dc_FechaNacimiento.setDate(fecha);
        
        //Cargamos la foto que esta cargada
        get.cargarFoto(personal.getFoto(), lbl_Foto);
        if (!personal.getExtension().equals("User.png")) 
            lbl_Foto.setText("");
        
        //Elementos iniciales no modificables
        txt_Rut.setEditable(false);
        btn_IngresarHuella.setEnabled(huella);
    }
    
    //javax.swing.JFrame padre;

    public IngresarPersonalTraining(javax.swing.JDialog parent, boolean modal, String rut, boolean huella) {
        super(parent, modal);
        initComponents();
        //Cabiaremos el tamaño 
        this.setResizable(false);
        this.setSize(895, 718); 
        this.setLocationRelativeTo(null); //Centrar el JFrame al centro de la pantalla
        //setIconImage(new ImageIcon(getClass().getResource("/Complementos/Logo1.png")).getImage()); // Icono del programa
        dc_FechaNacimiento.setVisible(false);
        txt_Edad.setVisible(false);
        
        if (rut == null)
            iniciarIngreso();
        else
            iniciarModificacion(rut, huella);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        btn_Aceptar = new javax.swing.JButton();
        btn_Cancelar = new javax.swing.JButton();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        txt_Rut = new Extra.TextPlaceholder();
        txt_Nombre = new Extra.TextPlaceholder();
        txt_Apellido = new Extra.TextPlaceholder();
        txt_ApellidoMaterno = new Extra.TextPlaceholder();
        txt_Direccion = new Extra.TextPlaceholder();
        txt_Correo = new Extra.TextPlaceholder();
        txt_Telefono = new Extra.TextPlaceholder();
        btn_Foto = new javax.swing.JButton();
        txt_Pin = new javax.swing.JPasswordField();
        txt_Pin2 = new javax.swing.JPasswordField();
        cmb_Tipo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        lbl_Foto = new com.bolivia.label.CLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btn_IngresarHuella = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        lbl_Correo = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        dc_FechaNacimiento = new com.toedter.calendar.JDateChooser();
        txt_Edad = new Extra.TextPlaceholder();
        btn_Cambio = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLayeredPane3 = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/1.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

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
        getContentPane().add(btn_Aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 630, 180, 40));

        btn_Cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Volver.png"))); // NOI18N
        btn_Cancelar.setBorderPainted(false);
        btn_Cancelar.setContentAreaFilled(false);
        btn_Cancelar.setFocusPainted(false);
        btn_Cancelar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Volver Mouse.png"))); // NOI18N
        btn_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 620, 70, 70));

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txt_Rut.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        txt_Rut.setForeground(new java.awt.Color(0, 204, 255));
        txt_Rut.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_Rut.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        txt_Rut.setPlaceholder("Rut");
        txt_Rut.setSelectedTextColor(new java.awt.Color(255, 0, 0));
        txt_Rut.setSelectionColor(new java.awt.Color(255, 255, 51));
        txt_Rut.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_RutFocusLost(evt);
            }
        });
        txt_Rut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_RutKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_RutKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_RutKeyTyped(evt);
            }
        });
        jLayeredPane1.add(txt_Rut);
        txt_Rut.setBounds(230, 100, 140, 30);

        txt_Nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        txt_Nombre.setForeground(new java.awt.Color(0, 204, 255));
        txt_Nombre.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        txt_Nombre.setPlaceholder("Nombre");
        txt_Nombre.setSelectedTextColor(new java.awt.Color(255, 0, 0));
        txt_Nombre.setSelectionColor(new java.awt.Color(255, 255, 51));
        txt_Nombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_NombreFocusLost(evt);
            }
        });
        txt_Nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_NombreKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_NombreKeyTyped(evt);
            }
        });
        jLayeredPane1.add(txt_Nombre);
        txt_Nombre.setBounds(230, 160, 200, 30);

        txt_Apellido.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        txt_Apellido.setForeground(new java.awt.Color(0, 204, 255));
        txt_Apellido.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        txt_Apellido.setPlaceholder("Apellido Paterno");
        txt_Apellido.setSelectedTextColor(new java.awt.Color(255, 0, 0));
        txt_Apellido.setSelectionColor(new java.awt.Color(255, 255, 51));
        txt_Apellido.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_ApellidoFocusLost(evt);
            }
        });
        txt_Apellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_ApellidoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_ApellidoKeyTyped(evt);
            }
        });
        jLayeredPane1.add(txt_Apellido);
        txt_Apellido.setBounds(230, 220, 200, 30);

        txt_ApellidoMaterno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        txt_ApellidoMaterno.setForeground(new java.awt.Color(0, 204, 255));
        txt_ApellidoMaterno.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        txt_ApellidoMaterno.setPlaceholder("Apellido Materno");
        txt_ApellidoMaterno.setSelectedTextColor(new java.awt.Color(255, 0, 0));
        txt_ApellidoMaterno.setSelectionColor(new java.awt.Color(255, 255, 51));
        txt_ApellidoMaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_ApellidoMaternoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_ApellidoMaternoKeyTyped(evt);
            }
        });
        jLayeredPane1.add(txt_ApellidoMaterno);
        txt_ApellidoMaterno.setBounds(230, 280, 200, 30);

        txt_Direccion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        txt_Direccion.setForeground(new java.awt.Color(0, 204, 255));
        txt_Direccion.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        txt_Direccion.setPlaceholder("Dirección");
        txt_Direccion.setSelectedTextColor(new java.awt.Color(255, 0, 0));
        txt_Direccion.setSelectionColor(new java.awt.Color(255, 255, 51));
        txt_Direccion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_DireccionFocusLost(evt);
            }
        });
        txt_Direccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_DireccionKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_DireccionKeyTyped(evt);
            }
        });
        jLayeredPane1.add(txt_Direccion);
        txt_Direccion.setBounds(230, 340, 200, 30);

        txt_Correo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        txt_Correo.setForeground(new java.awt.Color(0, 204, 255));
        txt_Correo.setToolTipText("Ingresa el correo...");
        txt_Correo.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        txt_Correo.setPlaceholder("Correo");
        txt_Correo.setSelectedTextColor(new java.awt.Color(255, 0, 0));
        txt_Correo.setSelectionColor(new java.awt.Color(255, 255, 51));
        txt_Correo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_CorreoFocusLost(evt);
            }
        });
        txt_Correo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_CorreoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_CorreoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_CorreoKeyTyped(evt);
            }
        });
        jLayeredPane1.add(txt_Correo);
        txt_Correo.setBounds(230, 400, 200, 30);

        txt_Telefono.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        txt_Telefono.setForeground(new java.awt.Color(0, 204, 255));
        txt_Telefono.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_Telefono.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        txt_Telefono.setPlaceholder("Teléfono");
        txt_Telefono.setSelectedTextColor(new java.awt.Color(255, 0, 0));
        txt_Telefono.setSelectionColor(new java.awt.Color(255, 255, 51));
        txt_Telefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_TelefonoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_TelefonoKeyTyped(evt);
            }
        });
        jLayeredPane1.add(txt_Telefono);
        txt_Telefono.setBounds(300, 460, 130, 30);

        btn_Foto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Camara.png"))); // NOI18N
        btn_Foto.setBorderPainted(false);
        btn_Foto.setContentAreaFilled(false);
        btn_Foto.setFocusPainted(false);
        btn_Foto.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Camara Mouse.png"))); // NOI18N
        btn_Foto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_FotoActionPerformed(evt);
            }
        });
        jLayeredPane1.add(btn_Foto);
        btn_Foto.setBounds(650, 60, 60, 60);

        txt_Pin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_Pin.setForeground(new java.awt.Color(0, 204, 255));
        txt_Pin.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_Pin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        txt_Pin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_PinFocusLost(evt);
            }
        });
        txt_Pin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_PinKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_PinKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_PinKeyTyped(evt);
            }
        });
        jLayeredPane1.add(txt_Pin);
        txt_Pin.setBounds(620, 360, 70, 30);

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
        jLayeredPane1.add(txt_Pin2);
        txt_Pin2.setBounds(620, 410, 70, 30);

        cmb_Tipo.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        cmb_Tipo.setForeground(new java.awt.Color(51, 153, 255));
        cmb_Tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Empleado", "Administrador", "Inhabilitar" }));
        cmb_Tipo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));
        cmb_Tipo.setFocusable(false);
        jLayeredPane1.add(cmb_Tipo);
        cmb_Tipo.setBounds(470, 460, 220, 30);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Ingresar Personal Training.png"))); // NOI18N
        jLayeredPane1.add(jLabel4);
        jLabel4.setBounds(30, 30, 380, 40);

        lbl_Foto.setText("Subir Foto");
        lbl_Foto.setToolTipText("");
        lbl_Foto.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        lbl_Foto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbl_Foto.setLineBorder(5);
        lbl_Foto.setLineColor(new java.awt.Color(51, 153, 255));
        lbl_Foto.setOpaque(false);
        jLayeredPane1.add(lbl_Foto);
        lbl_Foto.setBounds(480, 50, 240, 230);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Nombre.png"))); // NOI18N
        jLayeredPane1.add(jLabel3);
        jLabel3.setBounds(130, 160, 83, 30);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Apellido Paterno.png"))); // NOI18N
        jLayeredPane1.add(jLabel5);
        jLabel5.setBounds(50, 220, 164, 30);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Edad.png"))); // NOI18N
        jLayeredPane1.add(jLabel6);
        jLabel6.setBounds(470, 310, 59, 30);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Apellido Materno.png"))); // NOI18N
        jLayeredPane1.add(jLabel7);
        jLabel7.setBounds(50, 280, 164, 30);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Rut.png"))); // NOI18N
        jLayeredPane1.add(jLabel11);
        jLabel11.setBounds(160, 100, 60, 30);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Confirmar Pin.png"))); // NOI18N
        jLayeredPane1.add(jLabel8);
        jLabel8.setBounds(470, 410, 130, 30);

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Telefono.png"))); // NOI18N
        jLayeredPane1.add(jLabel12);
        jLabel12.setBounds(120, 460, 90, 27);

        btn_IngresarHuella.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Huella Digital Normal.png"))); // NOI18N
        btn_IngresarHuella.setBorderPainted(false);
        btn_IngresarHuella.setContentAreaFilled(false);
        btn_IngresarHuella.setFocusPainted(false);
        btn_IngresarHuella.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Huella Digital Mouse.png"))); // NOI18N
        btn_IngresarHuella.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_IngresarHuellaActionPerformed(evt);
            }
        });
        jLayeredPane1.add(btn_IngresarHuella);
        btn_IngresarHuella.setBounds(690, 370, 80, 81);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/+569.png"))); // NOI18N
        jLayeredPane1.add(jLabel10);
        jLabel10.setBounds(230, 460, 60, 30);

        lbl_Correo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Correo.png"))); // NOI18N
        jLayeredPane1.add(lbl_Correo);
        lbl_Correo.setBounds(140, 400, 80, 30);

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Direccion.png"))); // NOI18N
        jLayeredPane1.add(jLabel13);
        jLabel13.setBounds(110, 340, 100, 30);

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Nuevo Pin.png"))); // NOI18N
        jLayeredPane1.add(jLabel14);
        jLabel14.setBounds(470, 360, 100, 30);

        dc_FechaNacimiento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        dc_FechaNacimiento.setFont(new java.awt.Font("Ebrima", 0, 14)); // NOI18N
        jLayeredPane1.add(dc_FechaNacimiento);
        dc_FechaNacimiento.setBounds(540, 310, 120, 30);

        txt_Edad.setEditable(false);
        txt_Edad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        txt_Edad.setForeground(new java.awt.Color(0, 204, 255));
        txt_Edad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_Edad.setToolTipText("Edad Actual");
        txt_Edad.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        txt_Edad.setPlaceholder("Edad");
        txt_Edad.setSelectedTextColor(new java.awt.Color(255, 0, 0));
        txt_Edad.setSelectionColor(new java.awt.Color(255, 255, 51));
        txt_Edad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_EdadKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_EdadKeyTyped(evt);
            }
        });
        jLayeredPane1.add(txt_Edad);
        txt_Edad.setBounds(580, 310, 80, 30);

        btn_Cambio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Botones/Btn-CambiaFecha.png"))); // NOI18N
        btn_Cambio.setBorderPainted(false);
        btn_Cambio.setContentAreaFilled(false);
        btn_Cambio.setFocusPainted(false);
        btn_Cambio.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Botones/Btn-CambiaFechaMouse.png"))); // NOI18N
        btn_Cambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CambioActionPerformed(evt);
            }
        });
        jLayeredPane1.add(btn_Cambio);
        btn_Cambio.setBounds(660, 300, 60, 50);

        getContentPane().add(jLayeredPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 800, 520));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Menu.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 690));
        getContentPane().add(jLayeredPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 370, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_FotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_FotoActionPerformed
        if (seleccionar.showDialog(null, null) == JFileChooser.APPROVE_OPTION) {
            archivo = seleccionar.getSelectedFile();
            if (archivo.canRead()) {
                if (archivo.getName().endsWith("jpg") || archivo.getName().endsWith("jpeg") || archivo.getName().endsWith("gif")){
                    lbl_Foto.setText("");
                    get.cargarFoto(seleccionar.getSelectedFile().toString(), lbl_Foto);
                    cambiaFoto = true;
                }
                else
                    JOptionPane.showMessageDialog(null, "Archivo no compatible.","Precaución",JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_FotoActionPerformed

    private void btn_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CancelarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btn_CancelarActionPerformed

    private void btn_IngresarHuellaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_IngresarHuellaActionPerformed
        new AdministrarHuellaDigital(new javax.swing.JDialog(), true, true, get.reemplazo(txt_Rut.getText(), ".")).setVisible(true);
    }//GEN-LAST:event_btn_IngresarHuellaActionPerformed

    private void txt_RutKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_RutKeyTyped
        if (!validar.maximoLargo(get.reemplazo(txt_Rut.getText(), "."), 9))
            evt.consume();
        
        if(Character.isLetter(evt.getKeyChar()) && !String.valueOf(evt.getKeyChar()).equals("k")) 
            evt.consume(); 
    }//GEN-LAST:event_txt_RutKeyTyped

    private void btn_AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AceptarActionPerformed
        aceptar();
    }//GEN-LAST:event_btn_AceptarActionPerformed

    private void txt_NombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_NombreKeyTyped
        if (!validar.maximoLargo(txt_Nombre.getText(), 40))
            evt.consume();

        if(Character.isDigit(evt.getKeyChar())) 
            evt.consume(); 
        
        txt_Nombre.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(txt_Nombre.getText()), 1));
    }//GEN-LAST:event_txt_NombreKeyTyped

    private void txt_ApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_ApellidoKeyTyped
        if (!validar.maximoLargo(txt_Apellido.getText(), 40))
            evt.consume();

        if(Character.isDigit(evt.getKeyChar())) 
            evt.consume(); 
        
        if (String.valueOf(evt.getKeyChar()).equals(" ")) 
            evt.consume(); 
    }//GEN-LAST:event_txt_ApellidoKeyTyped

    private void txt_ApellidoMaternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_ApellidoMaternoKeyTyped
        if (!validar.maximoLargo(txt_ApellidoMaterno.getText(), 40))
            evt.consume();

        if(Character.isDigit(evt.getKeyChar())) 
            evt.consume(); 
        
        if (String.valueOf(evt.getKeyChar()).equals(" ")) 
            evt.consume(); 
    }//GEN-LAST:event_txt_ApellidoMaternoKeyTyped

    private void txt_DireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_DireccionKeyTyped
        if (!validar.maximoLargo(txt_Direccion.getText(), 70))
            evt.consume();
    }//GEN-LAST:event_txt_DireccionKeyTyped

    private void txt_CorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_CorreoKeyTyped
        if (!validar.maximoLargo(txt_Correo.getText(), 60))
            evt.consume();
        
         if (String.valueOf(evt.getKeyChar()).equals(" ")) 
            evt.consume(); 
    }//GEN-LAST:event_txt_CorreoKeyTyped

    private void txt_TelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_TelefonoKeyTyped
        if (!validar.maximoLargo(txt_Telefono.getText(), 8))
            evt.consume();

        if(Character.isLetter(evt.getKeyChar())) 
            evt.consume(); 
    }//GEN-LAST:event_txt_TelefonoKeyTyped

    private void txt_RutKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_RutKeyReleased
        validar.rut(txt_Rut);
        txt_Rut.setText(get.escribirRut(txt_Rut.getText(), txt_Rut));
    }//GEN-LAST:event_txt_RutKeyReleased

    private void txt_CorreoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_CorreoKeyReleased
        validar.correo(txt_Correo);
    }//GEN-LAST:event_txt_CorreoKeyReleased

    private void txt_PinKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_PinKeyTyped
        if (!validar.maximoLargo(txt_Pin.getText(), 4))
            evt.consume();

        if(Character.isLetter(evt.getKeyChar())) 
            evt.consume(); 
    }//GEN-LAST:event_txt_PinKeyTyped

    private void txt_Pin2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Pin2KeyTyped
        if (!validar.maximoLargo(txt_Pin2.getText(), 4))
            evt.consume();

        if(Character.isLetter(evt.getKeyChar())) 
            evt.consume(); 
    }//GEN-LAST:event_txt_Pin2KeyTyped

    private void txt_PinKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_PinKeyReleased
        cambiaPin();
         
        if (txt_Pin.getText().length() == 4) 
            txt_Pin2.grabFocus();
    }//GEN-LAST:event_txt_PinKeyReleased

    private void txt_Pin2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Pin2KeyReleased
        cambiaPin();
    }//GEN-LAST:event_txt_Pin2KeyReleased

    private void txt_NombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_NombreFocusLost
        txt_Nombre.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(txt_Nombre.getText()), 1));
    }//GEN-LAST:event_txt_NombreFocusLost

    private void txt_ApellidoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_ApellidoFocusLost
        txt_Apellido.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(txt_Apellido.getText()), 1));
    }//GEN-LAST:event_txt_ApellidoFocusLost

    private void txt_DireccionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_DireccionFocusLost
        txt_Direccion.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(txt_Direccion.getText()), 1));
    }//GEN-LAST:event_txt_DireccionFocusLost

    private void txt_CorreoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_CorreoFocusLost
        txt_Correo.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(txt_Correo.getText()), 1));
        validar.correo(txt_Correo);
    }//GEN-LAST:event_txt_CorreoFocusLost

    private void txt_RutFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_RutFocusLost
        txt_Rut.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(txt_Rut.getText()), 1));
        validar.rut(txt_Rut);
    }//GEN-LAST:event_txt_RutFocusLost

    private void txt_PinFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_PinFocusLost
        txt_Pin.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(txt_Pin.getText()), 1));
    }//GEN-LAST:event_txt_PinFocusLost

    private void txt_Pin2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_Pin2FocusLost
        txt_Pin2.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(txt_Pin2.getText()), 1));
    }//GEN-LAST:event_txt_Pin2FocusLost

    private void txt_NombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_NombreKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            aceptar();
    }//GEN-LAST:event_txt_NombreKeyPressed

    private void txt_ApellidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_ApellidoKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            aceptar();
    }//GEN-LAST:event_txt_ApellidoKeyPressed

    private void txt_ApellidoMaternoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_ApellidoMaternoKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            aceptar();
    }//GEN-LAST:event_txt_ApellidoMaternoKeyPressed

    private void txt_DireccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_DireccionKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            aceptar();
    }//GEN-LAST:event_txt_DireccionKeyPressed

    private void txt_CorreoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_CorreoKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            aceptar();
    }//GEN-LAST:event_txt_CorreoKeyPressed

    private void txt_TelefonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_TelefonoKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            aceptar();
    }//GEN-LAST:event_txt_TelefonoKeyPressed

    private void txt_PinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_PinKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            aceptar();
    }//GEN-LAST:event_txt_PinKeyPressed

    private void txt_Pin2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Pin2KeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            aceptar();
    }//GEN-LAST:event_txt_Pin2KeyPressed

    private void txt_RutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_RutKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            aceptar();
    }//GEN-LAST:event_txt_RutKeyPressed

    private void txt_EdadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_EdadKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_EdadKeyPressed

    private void txt_EdadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_EdadKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_EdadKeyTyped

    private void btn_CambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CambioActionPerformed
        cambiaEdad(txt_Edad.isVisible());
    }//GEN-LAST:event_btn_CambioActionPerformed

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
            java.util.logging.Logger.getLogger(IngresarPersonalTraining.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IngresarPersonalTraining.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IngresarPersonalTraining.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IngresarPersonalTraining.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                IngresarPersonalTraining dialog = new IngresarPersonalTraining(new javax.swing.JDialog(), true, "", false);
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
    private javax.swing.JButton btn_Cambio;
    private javax.swing.JButton btn_Cancelar;
    private javax.swing.JButton btn_Foto;
    private javax.swing.JButton btn_IngresarHuella;
    private javax.swing.JComboBox<String> cmb_Tipo;
    private com.toedter.calendar.JDateChooser dc_FechaNacimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JLabel lbl_Correo;
    private com.bolivia.label.CLabel lbl_Foto;
    private Extra.TextPlaceholder txt_Apellido;
    private Extra.TextPlaceholder txt_ApellidoMaterno;
    private Extra.TextPlaceholder txt_Correo;
    private Extra.TextPlaceholder txt_Direccion;
    private Extra.TextPlaceholder txt_Edad;
    private Extra.TextPlaceholder txt_Nombre;
    private javax.swing.JPasswordField txt_Pin;
    private javax.swing.JPasswordField txt_Pin2;
    private Extra.TextPlaceholder txt_Rut;
    private Extra.TextPlaceholder txt_Telefono;
    // End of variables declaration//GEN-END:variables

    private PersonalTraining cargarPersonalTrining() {
        PersonalTraining pt = new PersonalTraining(); 
        Ruta ruta = new Ruta(System.getProperty("user.dir") + "\\PersonalTraining").obtenerRuta();
        String extension;

        if (cambiaFoto) 
            extension = get.reemplazo(txt_Rut.getText(), ".") + "." + new Obtener().extensionArchivo(archivo.getName());
        else
            extension = "User.png"; 
        //SI esta modificando y no cambia la foto mantenga la misma foto
        if (!ingresar && !cambiaFoto) {
            String ex = new PersonalTraining(get.reemplazo(txt_Rut.getText(), ".")).obtenerPersonalTraining().getExtension();
            if (!ex.equals("User.png")) 
                extension =  ex;
        }
        
        pt.setRut(get.reemplazo(txt_Rut.getText(), "."));                                        //RUT
        pt.setPin(txt_Pin.getText());                                                            //PIN
        pt.setRuta(ruta.obtenerRuta().getIdArchivo());                                           //RUTA INT
        pt.setNombre(txt_Nombre.getText());                                                      //NOMBRE
        pt.setApellidoP(txt_Apellido.getText());                                                 //APELLIDO PATERNO
        pt.setApellidoM(txt_ApellidoMaterno.getText());                                          //APELLIDO MATERNO
        pt.setDireccion(txt_Direccion.getText());                                                //DIRECCION
        pt.setCorreo(txt_Correo.getText());                                                      //CORREO
        pt.setFono(txt_Telefono.getText());                                                      //TELEFONO
        pt.setTipo(cmb_Tipo.getSelectedItem().toString());                                       //TIPO
        pt.setFechaNacimiento(dc_FechaNacimiento.getDate());                                     //FECHA NACIMIENTO
        pt.setExtension(extension);                                                              //EXTENSION
//        id = pt.getId();
        return pt;
    }

    private void ingresarPersonalTraining() {
        if (cargarPersonalTrining().ingresar() == 1){
            if (cambiaFoto) 
                get.generarArchivo("PersonalTraining", cargarPersonalTrining().obtenerPersonalTraining().getFoto(), archivo);
            
            JOptionPane.showMessageDialog(null, "Personal Training ingresado correctamente :D!\nYa puedes ingresar su huella digital","Información :)",JOptionPane.INFORMATION_MESSAGE);
            btn_IngresarHuella.setEnabled(true);
            txt_Edad.setText(String.valueOf(get.calculaEdad(fecha)));
            cambiaEdad(false);
        }
        else
            JOptionPane.showMessageDialog(null, "Personal Training ingresado ya existe!\n Favor ingresa otra perona :(","Precaución D:",JOptionPane.WARNING_MESSAGE);
    }

    private void modificarPersonalTraining() {
        if (cargarPersonalTrining().modificar() == 1) {
            if (cambiaFoto) 
                get.generarArchivo("PersonalTraining", cargarPersonalTrining().obtenerPersonalTraining().getFoto(), archivo);
            
            JOptionPane.showMessageDialog(null, "Personal Training modificado correctamente :D!","Información :)",JOptionPane.INFORMATION_MESSAGE);
            txt_Edad.setText(String.valueOf(get.calculaEdad(fecha)));
        }
    }
    
    private void limpiarDatos() {
        txt_Rut.setText("");
        txt_Nombre.setText("");
        txt_Apellido.setText("");
        txt_ApellidoMaterno.setText("");
        txt_Direccion.setText("");
        txt_Correo.setText("");
        txt_Pin.setText("");
        txt_Pin2.setText("");
        txt_Telefono.setText("");
        cmb_Tipo.setSelectedIndex(0);
        dc_FechaNacimiento.setDateFormatString("");
        btn_IngresarHuella.setEnabled(false);
    }

    private boolean cambiaPin() {
         if (validar.pin(txt_Pin.getText(), txt_Pin2.getText())){
            txt_Pin.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
            txt_Pin2.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
            return false;
        }
        else{
            txt_Pin.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));
            txt_Pin2.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));
            return true;
        }
    }

    private boolean camposVacios() {
        txt_Rut.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(txt_Rut.getText()), 1));
        txt_Nombre.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(txt_Nombre.getText()), 1));
        txt_Apellido.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(txt_Apellido.getText()), 1));
        txt_Direccion.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(txt_Direccion.getText()), 1));
        txt_Correo.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(txt_Correo.getText()), 1));
        txt_Pin.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(txt_Pin.getText()), 1));
        txt_Pin2.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(txt_Pin2.getText()), 1));
        dc_FechaNacimiento.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(dc_FechaNacimiento.getDateFormatString()), 1));

        return txt_Rut.getText().isEmpty() || txt_Nombre.getText().isEmpty() || txt_Apellido.getText().isEmpty() || 
               txt_Direccion.getText().isEmpty() || txt_Correo.getText().isEmpty() || 
               txt_Pin.getText().isEmpty() || 
               txt_Pin2.getText().isEmpty() || 
               dc_FechaNacimiento.getDateFormatString().isEmpty();
    }
    
    private void aceptar() {
        if (!camposVacios()) {
            if (validar.rut(txt_Rut) && cambiaPin() && validar.correo(txt_Correo)) {
                if (ingresar) 
                    ingresarPersonalTraining();
                else
                    modificarPersonalTraining();
            }
            else
                JOptionPane.showMessageDialog(null, "Algunos campos se encuentran inválidos! :(", "Precaución D:", JOptionPane.WARNING_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null, "Algunos campos se encuentran vacíos! :(", "Precaución D:", JOptionPane.WARNING_MESSAGE);
    }

    private void cambiaEdad(boolean b) {
        txt_Edad.setVisible(!b);
        dc_FechaNacimiento.setVisible(b);
    }
}

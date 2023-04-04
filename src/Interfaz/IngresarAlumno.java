package Interfaz;

import Clases.Alumno;
import Clases.Deporte;
import Clases.Fecha;
import Clases.Fisico;
import Clases.Grupo;
import Clases.Pago;
import Clases.Personalizado;
import Clases.Recurso;
import Clases.Ruta;
import Extra.Obtener;
import Extra.Validacion;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import rojerusan.RSTableMetro;
import Clases.ControlAsistencia;
import com.toedter.calendar.JDateChooser;
/**
 * @author keiss
 */
public class IngresarAlumno extends javax.swing.JDialog {
    /**
     * Creates new form IngresarAlumno
     */
    private JFileChooser seleccionar = new JFileChooser();
    private File archivo;
    private final Validacion validar = new Validacion();
    private final Obtener get = new Obtener();
    private DefaultTableModel mEnfermedad, mLesion, mMedicamento, mAlergico, mDeporte, mPago;
    private int id = 0;
    private boolean ingresar, cambiaFoto = false;
    private ArrayList<Date> listaClases = new ArrayList<>();
    
    public IngresarAlumno(javax.swing.JDialog parent, boolean modal, String rut, boolean huella){
        super(parent, modal);
        initComponents();
        //Cambiar el tamaño y centrar el dialog
        this.setResizable(false);
        this.setSize(920, 720);
        this.setLocationRelativeTo(null); //Centrar el JFrame al centro de la pantalla
        //Cambiar valores por defecto

        setWidthColumnDeporte();// Cambia los tamaños de la tabla Deporte
        txt_Edad.setVisible(false);
        dc_FechaNacimiento.setVisible(false);
        seteaSpinner();//Cambia los valores de los spinner del jdialog

        calculaTotal();
        cargarFechaActual();
        setFilaAlto(30);
        cargaCMBFisico(cmb_Enfermedades);
        cargaCMBFisico(cmb_Lesiones);
        cargaCMBFisico(cmb_Medicamentos);
        cargaCMBFisico(cmb_Alergias);
        cargaCMBDeporte();
        
        tablaPago(tb_Pago);
        
        if (rut == null) 
            iniciaIngreso();
        else
            iniciaModificacion(rut, huella);
    }
    
    private void iniciaIngreso() {
        setTitle("Ingresar Alumno - SportMedics");
        get.cargarFoto(System.getProperty("user.dir") + "\\Alumnos\\User.png", lbl_Foto);
        ingresar = true;
        calculaDensidad(); //Calcula los valores de personalizado
        dc_FechaNacimiento.setVisible(true);
        cargarFisico();
        tabAlumno(false);
        btn_IngresarHuella.setEnabled(false);
    }
    
    private void iniciaModificacion(String rut, boolean huella) {
        setTitle("Modificar Alumno - SportMedics");
        btn_IngresarHuella.setEnabled(huella);
        ingresar = false;
        txt_Edad.setVisible(true);
        txt_Rut.setEditable(false);
        //calculaEdad("06/03/1989"); //Cambiar con la fecha guardada del alumno
        Alumno alumno = new Alumno(rut).obtenerAlumno();
        id = alumno.getId();
        txt_Rut.setText(get.rutReal(alumno.getRut()));
        txt_Nombre.setText(alumno.getNombre());
        txt_Apellido.setText(alumno.getApellidoP());
        txt_ApellidoMaterno.setText(alumno.getApellidoM());
        txt_Direccion.setText(alumno.getDireccion());
        txt_Correo.setText(alumno.getCorreo());
        txt_Telefono.setText(alumno.getFono());
        spr_Estatura.setValue(alumno.getEstatura());
        spr_Peso.setValue(alumno.getPeso());
        dc_FechaNacimiento.setDate(alumno.getFechaNacimiento());
        txt_Edad.setText(String.valueOf(get.calculaEdad(alumno.getFechaNacimiento())));
        txt_NombreEmergencia.setText(alumno.getNombreEmergencia());
        txt_TelefonoEmergencia.setText(alumno.getTelefonoEmergencia());
        txa_Observacion.setText(alumno.getObservacion());
        //Cargamos la foto que esta guardada en el servidor
        get.cargarFoto(alumno.getFoto(), lbl_Foto);
        if (!alumno.getExtension().equals("User.png")) 
            lbl_Foto.setText("");
        //Habilitamos el tab personalizado si el alumno esta en un grupo con la clase personalizada
        cargarPersonalizadoDefecto(alumno.getId());
        cargarCmbGrupo(alumno.getRut());//Cargamos el combo box con los grupo que asiste el alumno
        cargarFisico();//Cargamos las tabla donde el alumno tiene enfermedades lesiones etc 
        tablaDeporte();//Cargamos la tabla con los deportes que realiza el alumno
        tablaPago();//Cargamos los pagos que ha realizado el alumno
        //Si el combo boz eta vacio es porque el alumno aun no tiene un grupo asignado, por lo tanto no puede realizar un pago
        if (cmb_Grupo.getItemCount() == 0) 
            btn_AgregarPago.setEnabled(false);
    }
    
    private void cargarFisico() {
        tablaFisico(mEnfermedad, tb_Enfermedad, "Enfermedades");
        tablaFisico(mLesion, tb_Lesiones, "Lesiones");
        tablaFisico(mMedicamento, tb_Medicamentos, "Medicamentos");
        tablaFisico(mAlergico, tb_Alergias, "Alergias");
    }
    
    private void cargaInteligenteFisico(JComboBox<String> comBox, String ingreso, String detalle, int id, DefaultTableModel mModelo, RSTableMetro tb_fisico, String busqueda) {
        if (!comBox.getSelectedItem().toString().isEmpty()) {
            new Fisico(comBox.getSelectedItem().toString()).ingresarFisico(0, ingreso); //Insertamos solo si no existe la enfermedad
            new Fisico(new Fisico(comBox.getSelectedItem().toString()).buscarFisico(ingreso).getId()).ingresarFisico(id, detalle); //Insertamos la nueva enfermedad
            tablaFisico(mModelo, tb_fisico, busqueda); //Recargamos la tabla
            cargaCMBFisico(comBox);
        }
        else
            JOptionPane.showMessageDialog(null, "El campo se encuentra vacío! D:", "Precaución D:", JOptionPane.WARNING_MESSAGE);
    }
    
    private void cargaInteligenteDeporte() {
        if (!cmb_Deporte.getSelectedItem().toString().isEmpty()) {
            new Deporte(cmb_Deporte.getSelectedItem().toString()).ingresarDeporte(true);//Si el doperte escrito no existe lo ingresa
            ingresarDeporte();
            cargaCMBDeporte();
        }
        else
            JOptionPane.showMessageDialog(null, "El deporte se encuentra vacío! D:", "Precaución D:", JOptionPane.WARNING_MESSAGE);
    }

    private void addKeyListener(){
        cmb_Enfermedades.getEditor().getEditorComponent().addKeyListener(new KeyListener() { 
            @Override
            public void keyPressed(KeyEvent e){ 
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                    cargaInteligenteFisico(cmb_Enfermedades, "Enfermedad", "DetalleEnfermedad", id, mEnfermedad, tb_Enfermedad, "Enfermedades");
            } 

            @Override
            public void keyReleased(KeyEvent e){} 
            @Override
            public void keyTyped(KeyEvent e) {} 
        });
        
        cmb_Lesiones.getEditor().getEditorComponent().addKeyListener(new KeyListener() { 
            @Override
            public void keyPressed(KeyEvent e){ 
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                    cargaInteligenteFisico(cmb_Lesiones, "Lesion", "DetalleLesion", id, mLesion, tb_Lesiones, "Lesiones");
            } 

            @Override
            public void keyReleased(KeyEvent e){} 
            @Override
            public void keyTyped(KeyEvent e) {} 
        });
        
        cmb_Medicamentos.getEditor().getEditorComponent().addKeyListener(new KeyListener() { 
            @Override
            public void keyPressed(KeyEvent e){ 
                if(e.getKeyCode() == KeyEvent.VK_ENTER) 
                    cargaInteligenteFisico(cmb_Medicamentos, "Medicamento", "DetalleMedicamento", id, mMedicamento, tb_Medicamentos, "Medicamentos");
            } 

            @Override
            public void keyReleased(KeyEvent e){} 
            @Override
            public void keyTyped(KeyEvent e) {} 
        });
        
        cmb_Alergias.getEditor().getEditorComponent().addKeyListener(new KeyListener() { 
            @Override
            public void keyPressed(KeyEvent e){ 
                if(e.getKeyCode() == KeyEvent.VK_ENTER) 
                    cargaInteligenteFisico(cmb_Alergias, "Alergia", "DetalleAlergia", id, mAlergico, tb_Alergias, "Alergias");
            } 

            @Override
            public void keyReleased(KeyEvent e){} 
            @Override
            public void keyTyped(KeyEvent e) {} 
        });
        
        cmb_Deporte.getEditor().getEditorComponent().addKeyListener(new KeyListener() { 
            @Override
            public void keyPressed(KeyEvent e){ 
                if(e.getKeyCode() == KeyEvent.VK_ENTER) 
                    cargaInteligenteDeporte();
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

        jCheckBox1 = new javax.swing.JCheckBox();
        lbl_Logo = new javax.swing.JLabel();
        tbp_Alumno = new javax.swing.JTabbedPane();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        lbl_Rut = new javax.swing.JLabel();
        txt_Rut = new Extra.TextPlaceholder();
        lbl_NombreAlumno = new javax.swing.JLabel();
        txt_Nombre = new Extra.TextPlaceholder();
        lbl_ApellidoP = new javax.swing.JLabel();
        txt_Apellido = new Extra.TextPlaceholder();
        lbl_ApellidoM = new javax.swing.JLabel();
        txt_ApellidoMaterno = new Extra.TextPlaceholder();
        lbl_Direccion = new javax.swing.JLabel();
        txt_Direccion = new Extra.TextPlaceholder();
        lbl_Correo = new javax.swing.JLabel();
        txt_Correo = new Extra.TextPlaceholder();
        lbl_telefono = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_Telefono = new Extra.TextPlaceholder();
        jpane_Foto = new javax.swing.JLayeredPane();
        btn_Foto = new javax.swing.JButton();
        lbl_Foto = new com.bolivia.label.CLabel();
        lbl_Edad = new javax.swing.JLabel();
        txt_Edad = new Extra.TextPlaceholder();
        dc_FechaNacimiento = new com.toedter.calendar.JDateChooser();
        btn_Cambiar = new javax.swing.JButton();
        lbl_Estatura1 = new javax.swing.JLabel();
        spr_Estatura = new Extra.MiSpinner();
        lbl_Peso1 = new javax.swing.JLabel();
        spr_Peso = new Extra.MiSpinner();
        btn_IngresarHuella = new javax.swing.JButton();
        lbl_FondoPersonal = new javax.swing.JLabel();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        tb_Alergias = new rojerusan.RSTableMetro();
        btn_IngresarFisico = new javax.swing.JButton();
        lbl_Lesiones = new javax.swing.JLabel();
        lbl_Enfermedades = new javax.swing.JLabel();
        lbl_Medicamentos = new javax.swing.JLabel();
        lbl_Alergias = new javax.swing.JLabel();
        cmb_Enfermedades = new javax.swing.JComboBox<>();
        cmb_Medicamentos = new javax.swing.JComboBox<>();
        cmb_Lesiones = new javax.swing.JComboBox<>();
        jScrollPane8 = new javax.swing.JScrollPane();
        tb_Enfermedad = new rojerusan.RSTableMetro();
        jScrollPane9 = new javax.swing.JScrollPane();
        tb_Lesiones = new rojerusan.RSTableMetro();
        jScrollPane10 = new javax.swing.JScrollPane();
        tb_Medicamentos = new rojerusan.RSTableMetro();
        cmb_Alergias = new javax.swing.JComboBox<>();
        btn_ConfigurarFisico = new javax.swing.JButton();
        lbl_FondoFisico = new javax.swing.JLabel();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        tb_Deporte = new rojerusan.RSTableMetro();
        txt_Establecimiento = new Extra.TextPlaceholder();
        txt_DireccionDeporte = new Extra.TextPlaceholder();
        btn_IngresarDeporte = new javax.swing.JButton();
        lbl_DireccionDeporte = new javax.swing.JLabel();
        lbl_Establecimiento = new javax.swing.JLabel();
        lbl_Deporte = new javax.swing.JLabel();
        lbl_Tiempo = new javax.swing.JLabel();
        lbl_Nivel = new javax.swing.JLabel();
        lbl_Cantidad = new javax.swing.JLabel();
        cmb_Nivel = new javax.swing.JComboBox<>();
        cmb_Tiempo = new javax.swing.JComboBox<>();
        spr_Tiempo = new Extra.MiSpinner();
        spr_vecesxSemana = new Extra.MiSpinner();
        cmb_Deporte = new javax.swing.JComboBox<>();
        btn_ConfiguracionDeporte = new javax.swing.JButton();
        lbl_FondoDeporte = new javax.swing.JLabel();
        jLayeredPane5 = new javax.swing.JLayeredPane();
        lbl_NombreEmergencia = new javax.swing.JLabel();
        txt_NombreEmergencia = new Extra.TextPlaceholder();
        lbl_Observaciones = new javax.swing.JLabel();
        txt_TelefonoEmergencia = new Extra.TextPlaceholder();
        lbl_TelefonoEmergencia = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txa_Observacion = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        lbl_Emergencia = new javax.swing.JLabel();
        lbl_FondoEmergencia = new javax.swing.JLabel();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        lbl_NombreAlumno3 = new javax.swing.JLabel();
        lbl_Sexo = new javax.swing.JLabel();
        cmb_Sexo = new javax.swing.JComboBox<>();
        lbl_Supraespinal = new javax.swing.JLabel();
        lbl_Abdominal = new javax.swing.JLabel();
        lbl_MusloFrontal = new javax.swing.JLabel();
        lbl_Pantoriilla = new javax.swing.JLabel();
        lbl_Subescapular = new javax.swing.JLabel();
        lbl_Pl7 = new javax.swing.JLabel();
        lbl_Densidad = new javax.swing.JLabel();
        txt_Densidad = new Extra.TextPlaceholder();
        lbl_PorcGrasa = new javax.swing.JLabel();
        txt_PorcentajeGrasa = new Extra.TextPlaceholder();
        lbl_Triceps = new javax.swing.JLabel();
        lbl_Pl1 = new javax.swing.JLabel();
        lbl_Pl2 = new javax.swing.JLabel();
        lbl_Pl3 = new javax.swing.JLabel();
        lbl_Pl4 = new javax.swing.JLabel();
        lbl_Pl5 = new javax.swing.JLabel();
        lbl_Pl6 = new javax.swing.JLabel();
        spr_Triceps = new Extra.MiSpinner();
        spr_Biceps = new Extra.MiSpinner();
        spr_Abdominal = new Extra.MiSpinner();
        spr_Pantorrilla = new Extra.MiSpinner();
        spr_Subescapular = new Extra.MiSpinner();
        spr_Supraespinal = new Extra.MiSpinner();
        spr_Muslofrontal = new Extra.MiSpinner();
        btn_IngresarPersonalizado = new javax.swing.JButton();
        lbl_FondoPersonalizado = new javax.swing.JLabel();
        jLayeredPane6 = new javax.swing.JLayeredPane();
        txt_Descuento = new Extra.TextPlaceholder();
        btn_AgregarPago = new javax.swing.JButton();
        dc_FechaInicio = new com.toedter.calendar.JDateChooser();
        jScrollPane7 = new javax.swing.JScrollPane();
        tb_Pago = new rojerusan.RSTableMetro();
        lbl_Pago = new javax.swing.JLabel();
        lbl_Descuento = new javax.swing.JLabel();
        lbl_FechaInicio = new javax.swing.JLabel();
        lbl_FormaPago = new javax.swing.JLabel();
        cmb_FormaPago = new javax.swing.JComboBox<>();
        spr_Meses = new Extra.MiSpinner();
        lbl_Meses = new javax.swing.JLabel();
        lbl_Total = new javax.swing.JLabel();
        txt_Total = new Extra.TextPlaceholder();
        txt_Agregado = new Extra.TextPlaceholder();
        lbl_Agregado = new javax.swing.JLabel();
        cmb_Grupo = new javax.swing.JComboBox<>();
        btn_Grupo = new javax.swing.JButton();
        txt_Pago = new Extra.TextPlaceholder();
        jLabel2 = new javax.swing.JLabel();
        ckb_Porcentaje = new Extra.MiCheckBox();
        ckb_grupoCompleto = new Extra.MiCheckBox();
        lbl_FondoPago = new javax.swing.JLabel();
        lbl_Titulo = new javax.swing.JLabel();
        btn_Volver = new javax.swing.JButton();
        btn_Aceptar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jCheckBox1.setText("jCheckBox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/1.png"))); // NOI18N
        getContentPane().add(lbl_Logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 30, -1, -1));

        tbp_Alumno.setForeground(new java.awt.Color(0, 204, 255));
        tbp_Alumno.setFocusable(false);
        tbp_Alumno.setFont(new java.awt.Font("Mairy Oblicua", 0, 16)); // NOI18N

        lbl_Rut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Rut.png"))); // NOI18N
        jLayeredPane1.add(lbl_Rut);
        lbl_Rut.setBounds(170, 30, 60, 30);

        txt_Rut.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        txt_Rut.setForeground(new java.awt.Color(0, 204, 255));
        txt_Rut.setToolTipText("Ingresa el rut...");
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
        txt_Rut.setBounds(250, 30, 130, 30);

        lbl_NombreAlumno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Nombre.png"))); // NOI18N
        jLayeredPane1.add(lbl_NombreAlumno);
        lbl_NombreAlumno.setBounds(140, 90, 83, 30);

        txt_Nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        txt_Nombre.setForeground(new java.awt.Color(0, 204, 255));
        txt_Nombre.setToolTipText("Ingresa el nombre del alumno...");
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
        txt_Nombre.setBounds(250, 90, 200, 30);

        lbl_ApellidoP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Apellido Paterno.png"))); // NOI18N
        jLayeredPane1.add(lbl_ApellidoP);
        lbl_ApellidoP.setBounds(60, 150, 164, 30);

        txt_Apellido.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        txt_Apellido.setForeground(new java.awt.Color(0, 204, 255));
        txt_Apellido.setToolTipText("Ingresa el apellido paterno del alumno...");
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
        });
        jLayeredPane1.add(txt_Apellido);
        txt_Apellido.setBounds(250, 150, 200, 30);

        lbl_ApellidoM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Apellido Materno.png"))); // NOI18N
        jLayeredPane1.add(lbl_ApellidoM);
        lbl_ApellidoM.setBounds(60, 210, 164, 30);

        txt_ApellidoMaterno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        txt_ApellidoMaterno.setForeground(new java.awt.Color(0, 204, 255));
        txt_ApellidoMaterno.setToolTipText("Ingresa el apellido materno del alumno...");
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
        txt_ApellidoMaterno.setBounds(250, 210, 200, 30);

        lbl_Direccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Direccion.png"))); // NOI18N
        jLayeredPane1.add(lbl_Direccion);
        lbl_Direccion.setBounds(120, 270, 100, 30);

        txt_Direccion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        txt_Direccion.setForeground(new java.awt.Color(0, 204, 255));
        txt_Direccion.setToolTipText("Ingresa la dirección del alumno...");
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
        txt_Direccion.setBounds(250, 270, 200, 30);

        lbl_Correo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Correo.png"))); // NOI18N
        jLayeredPane1.add(lbl_Correo);
        lbl_Correo.setBounds(150, 330, 80, 30);

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
        txt_Correo.setBounds(250, 330, 200, 30);

        lbl_telefono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Telefono.png"))); // NOI18N
        jLayeredPane1.add(lbl_telefono);
        lbl_telefono.setBounds(130, 390, 90, 27);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/+569.png"))); // NOI18N
        jLayeredPane1.add(jLabel10);
        jLabel10.setBounds(240, 390, 60, 30);

        txt_Telefono.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        txt_Telefono.setForeground(new java.awt.Color(0, 204, 255));
        txt_Telefono.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_Telefono.setToolTipText("Ingresa el teléfono del alumno...");
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
        txt_Telefono.setBounds(320, 390, 130, 30);

        jpane_Foto.setBackground(new java.awt.Color(255, 255, 255));
        jpane_Foto.setOpaque(true);

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
        jpane_Foto.add(btn_Foto);
        btn_Foto.setBounds(170, 30, 60, 60);

        lbl_Foto.setText("Subir Foto");
        lbl_Foto.setToolTipText("");
        lbl_Foto.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        lbl_Foto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbl_Foto.setLineBorder(5);
        lbl_Foto.setLineColor(new java.awt.Color(51, 153, 255));
        lbl_Foto.setOpaque(false);
        jpane_Foto.add(lbl_Foto);
        lbl_Foto.setBounds(20, 20, 220, 210);

        jLayeredPane1.add(jpane_Foto);
        jpane_Foto.setBounds(490, 10, 260, 240);

        lbl_Edad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Edad.png"))); // NOI18N
        jLayeredPane1.add(lbl_Edad);
        lbl_Edad.setBounds(510, 270, 59, 30);

        txt_Edad.setEditable(false);
        txt_Edad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        txt_Edad.setForeground(new java.awt.Color(0, 204, 255));
        txt_Edad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_Edad.setToolTipText("Edad Actual");
        txt_Edad.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        txt_Edad.setPlaceholder("Edad");
        txt_Edad.setSelectedTextColor(new java.awt.Color(255, 0, 0));
        txt_Edad.setSelectionColor(new java.awt.Color(255, 255, 51));
        jLayeredPane1.add(txt_Edad);
        txt_Edad.setBounds(610, 270, 70, 30);

        dc_FechaNacimiento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        dc_FechaNacimiento.setForeground(new java.awt.Color(0, 204, 255));
        dc_FechaNacimiento.setToolTipText("Ingresa la fecha de inicio...");
        dc_FechaNacimiento.setFont(new java.awt.Font("Ebrima", 0, 14)); // NOI18N
        jLayeredPane1.add(dc_FechaNacimiento);
        dc_FechaNacimiento.setBounds(610, 270, 120, 30);

        btn_Cambiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Botones/Btn-CambiaFecha.png"))); // NOI18N
        btn_Cambiar.setBorderPainted(false);
        btn_Cambiar.setContentAreaFilled(false);
        btn_Cambiar.setFocusPainted(false);
        btn_Cambiar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Botones/Btn-CambiaFechaMouse.png"))); // NOI18N
        btn_Cambiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CambiarActionPerformed(evt);
            }
        });
        jLayeredPane1.add(btn_Cambiar);
        btn_Cambiar.setBounds(730, 260, 60, 50);

        lbl_Estatura1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Estatura.png"))); // NOI18N
        jLayeredPane1.add(lbl_Estatura1);
        lbl_Estatura1.setBounds(510, 330, 91, 27);

        spr_Estatura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        spr_Estatura.setColorTexto(new java.awt.Color(0, 204, 255));
        spr_Estatura.setFont(new java.awt.Font("DS-Digital", 0, 18)); // NOI18N
        spr_Estatura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                spr_EstaturaKeyPressed(evt);
            }
        });
        jLayeredPane1.add(spr_Estatura);
        spr_Estatura.setBounds(610, 330, 70, 30);

        lbl_Peso1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Peso.png"))); // NOI18N
        jLayeredPane1.add(lbl_Peso1);
        lbl_Peso1.setBounds(510, 390, 59, 27);

        spr_Peso.setForeground(new java.awt.Color(0, 204, 255));
        spr_Peso.setColorTexto(new java.awt.Color(0, 204, 255));
        spr_Peso.setFont(new java.awt.Font("DS-Digital", 0, 18)); // NOI18N
        spr_Peso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                spr_PesoKeyPressed(evt);
            }
        });
        jLayeredPane1.add(spr_Peso);
        spr_Peso.setBounds(610, 390, 70, 30);

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
        btn_IngresarHuella.setBounds(710, 330, 70, 90);

        lbl_FondoPersonal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/azul-blanco.jpg"))); // NOI18N
        jLayeredPane1.add(lbl_FondoPersonal);
        lbl_FondoPersonal.setBounds(0, 0, 820, 450);

        tbp_Alumno.addTab("Personal", jLayeredPane1);

        jLayeredPane2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tb_Alergias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

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
        if (tb_Alergias.getColumnModel().getColumnCount() > 0) {
            tb_Alergias.getColumnModel().getColumn(0).setResizable(false);
        }

        jLayeredPane2.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 180, 190, 240));

        btn_IngresarFisico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Añadir Normal.png"))); // NOI18N
        btn_IngresarFisico.setBorderPainted(false);
        btn_IngresarFisico.setContentAreaFilled(false);
        btn_IngresarFisico.setFocusPainted(false);
        btn_IngresarFisico.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Añadir Mouse.png"))); // NOI18N
        btn_IngresarFisico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_IngresarFisicoActionPerformed(evt);
            }
        });
        jLayeredPane2.add(btn_IngresarFisico, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 70, 90, 80));

        lbl_Lesiones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Lesiones.png"))); // NOI18N
        jLayeredPane2.add(lbl_Lesiones, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, -1, -1));

        lbl_Enfermedades.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Enfermedades.png"))); // NOI18N
        jLayeredPane2.add(lbl_Enfermedades, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, -1, -1));

        lbl_Medicamentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Medicamentos.png"))); // NOI18N
        jLayeredPane2.add(lbl_Medicamentos, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, -1, -1));

        lbl_Alergias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Alergico.png"))); // NOI18N
        jLayeredPane2.add(lbl_Alergias, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 110, -1, -1));

        cmb_Enfermedades.setEditable(true);
        cmb_Enfermedades.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        cmb_Enfermedades.setForeground(new java.awt.Color(0, 204, 255));
        cmb_Enfermedades.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        cmb_Enfermedades.setName("Enfermedades"); // NOI18N
        jLayeredPane2.add(cmb_Enfermedades, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, 150, 30));

        cmb_Medicamentos.setEditable(true);
        cmb_Medicamentos.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        cmb_Medicamentos.setForeground(new java.awt.Color(0, 204, 255));
        cmb_Medicamentos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        cmb_Medicamentos.setName("Medicamentos"); // NOI18N
        jLayeredPane2.add(cmb_Medicamentos, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 110, 150, 30));

        cmb_Lesiones.setEditable(true);
        cmb_Lesiones.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        cmb_Lesiones.setForeground(new java.awt.Color(0, 204, 255));
        cmb_Lesiones.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        cmb_Lesiones.setName("Lesiones"); // NOI18N
        jLayeredPane2.add(cmb_Lesiones, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 40, 150, 30));

        tb_Enfermedad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

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
        if (tb_Enfermedad.getColumnModel().getColumnCount() > 0) {
            tb_Enfermedad.getColumnModel().getColumn(0).setResizable(false);
        }

        jLayeredPane2.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 190, 240));

        tb_Lesiones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

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
        if (tb_Lesiones.getColumnModel().getColumnCount() > 0) {
            tb_Lesiones.getColumnModel().getColumn(0).setResizable(false);
        }

        jLayeredPane2.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 180, 190, 240));

        tb_Medicamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

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
        if (tb_Medicamentos.getColumnModel().getColumnCount() > 0) {
            tb_Medicamentos.getColumnModel().getColumn(0).setResizable(false);
        }

        jLayeredPane2.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 180, 190, 240));

        cmb_Alergias.setEditable(true);
        cmb_Alergias.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        cmb_Alergias.setForeground(new java.awt.Color(0, 153, 204));
        cmb_Alergias.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));
        cmb_Alergias.setName("Alergias"); // NOI18N
        jLayeredPane2.add(cmb_Alergias, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 110, 150, 30));

        btn_ConfigurarFisico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Botones/Boton Configuracion.png"))); // NOI18N
        btn_ConfigurarFisico.setBorderPainted(false);
        btn_ConfigurarFisico.setContentAreaFilled(false);
        btn_ConfigurarFisico.setFocusPainted(false);
        btn_ConfigurarFisico.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Botones/Boton Configuracion Mouse.png"))); // NOI18N
        jLayeredPane2.add(btn_ConfigurarFisico, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 10, 50, 40));

        lbl_FondoFisico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/azul-blanco.jpg"))); // NOI18N
        jLayeredPane2.add(lbl_FondoFisico, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 450));

        tbp_Alumno.addTab("Físico", jLayeredPane2);
        addKeyListener();

        jLayeredPane3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tb_Deporte.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tb_Deporte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Deporte", "Nivel", "Tiempo", "Cantidad", "Establecimiento", "Dirección"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class
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
        tb_Deporte.setColorBackgoundHead(new java.awt.Color(0, 153, 204));
        tb_Deporte.setColorBordeFilas(new java.awt.Color(0, 255, 255));
        tb_Deporte.setColorBordeHead(new java.awt.Color(255, 255, 255));
        tb_Deporte.setColorFilasBackgound2(new java.awt.Color(204, 255, 255));
        tb_Deporte.setFillsViewportHeight(true);
        tb_Deporte.setFuenteFilas(new java.awt.Font("Mairy Oblicua", 1, 18)); // NOI18N
        tb_Deporte.setFuenteFilasSelect(new java.awt.Font("Mairy Oblicua", 1, 18)); // NOI18N
        tb_Deporte.setFuenteHead(new java.awt.Font("Mairy Bold", 1, 24)); // NOI18N
        tb_Deporte.setName("Deporte"); // NOI18N
        tb_Deporte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_DeporteMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tb_Deporte);

        jLayeredPane3.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 760, 230));

        txt_Establecimiento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        txt_Establecimiento.setForeground(new java.awt.Color(0, 204, 255));
        txt_Establecimiento.setToolTipText("Ingresa el establecimiento...");
        txt_Establecimiento.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        txt_Establecimiento.setPlaceholder("Establecimiento");
        txt_Establecimiento.setSelectedTextColor(new java.awt.Color(255, 0, 0));
        txt_Establecimiento.setSelectionColor(new java.awt.Color(255, 255, 51));
        txt_Establecimiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_EstablecimientoKeyPressed(evt);
            }
        });
        jLayeredPane3.add(txt_Establecimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 150, 30));

        txt_DireccionDeporte.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        txt_DireccionDeporte.setForeground(new java.awt.Color(0, 204, 255));
        txt_DireccionDeporte.setToolTipText("Ingresa lla dirección en donde lo practica...");
        txt_DireccionDeporte.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        txt_DireccionDeporte.setPlaceholder("Dirección");
        txt_DireccionDeporte.setSelectedTextColor(new java.awt.Color(255, 0, 0));
        txt_DireccionDeporte.setSelectionColor(new java.awt.Color(255, 255, 51));
        txt_DireccionDeporte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_DireccionDeporteKeyPressed(evt);
            }
        });
        jLayeredPane3.add(txt_DireccionDeporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 30, 150, 30));

        btn_IngresarDeporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Añadir Normal.png"))); // NOI18N
        btn_IngresarDeporte.setBorderPainted(false);
        btn_IngresarDeporte.setContentAreaFilled(false);
        btn_IngresarDeporte.setFocusPainted(false);
        btn_IngresarDeporte.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Añadir Mouse.png"))); // NOI18N
        btn_IngresarDeporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_IngresarDeporteActionPerformed(evt);
            }
        });
        jLayeredPane3.add(btn_IngresarDeporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 80, 90, 60));

        lbl_DireccionDeporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Direccion.png"))); // NOI18N
        jLayeredPane3.add(lbl_DireccionDeporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 30, 100, 30));

        lbl_Establecimiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Establecimiento.png"))); // NOI18N
        jLayeredPane3.add(lbl_Establecimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, 30));

        lbl_Deporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Deporte.png"))); // NOI18N
        jLayeredPane3.add(lbl_Deporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, -1, 30));

        lbl_Tiempo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Tiempo que lo Practica.png"))); // NOI18N
        jLayeredPane3.add(lbl_Tiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, -1, 70));

        lbl_Nivel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Nivel.png"))); // NOI18N
        jLayeredPane3.add(lbl_Nivel, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 80, -1, -1));

        lbl_Cantidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Veces por Semana.png"))); // NOI18N
        jLayeredPane3.add(lbl_Cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 100, 100, 80));

        cmb_Nivel.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        cmb_Nivel.setForeground(new java.awt.Color(51, 153, 255));
        cmb_Nivel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Amateur", "Intermedio", "Profesional" }));
        cmb_Nivel.setToolTipText("Dias, meses o años");
        cmb_Nivel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));
        cmb_Nivel.setFocusable(false);
        jLayeredPane3.add(cmb_Nivel, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 80, 150, 30));

        cmb_Tiempo.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        cmb_Tiempo.setForeground(new java.awt.Color(51, 153, 255));
        cmb_Tiempo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Día (s)", "Mese (s)", "Año (s)" }));
        cmb_Tiempo.setToolTipText("Dia(s), mese(s) o año(s)");
        cmb_Tiempo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));
        cmb_Tiempo.setFocusable(false);
        jLayeredPane3.add(cmb_Tiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 130, 80, 30));

        spr_Tiempo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        spr_Tiempo.setColorTexto(new java.awt.Color(0, 204, 255));
        spr_Tiempo.setFont(new java.awt.Font("DS-Digital", 0, 18)); // NOI18N
        jLayeredPane3.add(spr_Tiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, 60, 30));

        spr_vecesxSemana.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        spr_vecesxSemana.setToolTipText("Veces que lo practica...");
        spr_vecesxSemana.setColorTexto(new java.awt.Color(0, 204, 255));
        spr_vecesxSemana.setFont(new java.awt.Font("DS-Digital", 0, 18)); // NOI18N
        jLayeredPane3.add(spr_vecesxSemana, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 130, 150, 30));

        cmb_Deporte.setEditable(true);
        cmb_Deporte.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        cmb_Deporte.setForeground(new java.awt.Color(0, 204, 255));
        cmb_Deporte.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        cmb_Deporte.setName("Enfermedades"); // NOI18N
        jLayeredPane3.add(cmb_Deporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, 150, 30));

        btn_ConfiguracionDeporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Botones/Boton Configuracion.png"))); // NOI18N
        btn_ConfiguracionDeporte.setBorderPainted(false);
        btn_ConfiguracionDeporte.setContentAreaFilled(false);
        btn_ConfiguracionDeporte.setFocusPainted(false);
        btn_ConfiguracionDeporte.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Botones/Boton Configuracion Mouse.png"))); // NOI18N
        jLayeredPane3.add(btn_ConfiguracionDeporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 0, 70, 60));

        lbl_FondoDeporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/azul-blanco.jpg"))); // NOI18N
        jLayeredPane3.add(lbl_FondoDeporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 450));

        tbp_Alumno.addTab("Deporte", jLayeredPane3);

        jLayeredPane5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_NombreEmergencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Nombre.png"))); // NOI18N
        jLayeredPane5.add(lbl_NombreEmergencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, 30));

        txt_NombreEmergencia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        txt_NombreEmergencia.setForeground(new java.awt.Color(0, 204, 255));
        txt_NombreEmergencia.setToolTipText("Ingresa el nombre de emergencia...");
        txt_NombreEmergencia.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        txt_NombreEmergencia.setPlaceholder("Nombre de Emergencia");
        txt_NombreEmergencia.setSelectedTextColor(new java.awt.Color(255, 0, 0));
        txt_NombreEmergencia.setSelectionColor(new java.awt.Color(255, 255, 51));
        jLayeredPane5.add(txt_NombreEmergencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, -1, -1));

        lbl_Observaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Observaciones.png"))); // NOI18N
        jLayeredPane5.add(lbl_Observaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, 40));

        txt_TelefonoEmergencia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        txt_TelefonoEmergencia.setForeground(new java.awt.Color(0, 204, 255));
        txt_TelefonoEmergencia.setToolTipText("Ingresa el teléfono de emergencia...");
        txt_TelefonoEmergencia.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        txt_TelefonoEmergencia.setPlaceholder("Teléfono de Emergencia");
        txt_TelefonoEmergencia.setSelectedTextColor(new java.awt.Color(255, 0, 0));
        txt_TelefonoEmergencia.setSelectionColor(new java.awt.Color(255, 255, 51));
        jLayeredPane5.add(txt_TelefonoEmergencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 100, -1, -1));

        lbl_TelefonoEmergencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Telefono.png"))); // NOI18N
        jLayeredPane5.add(lbl_TelefonoEmergencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 100, -1, 30));

        txa_Observacion.setColumns(20);
        txa_Observacion.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        txa_Observacion.setForeground(new java.awt.Color(0, 204, 204));
        txa_Observacion.setRows(5);
        txa_Observacion.setToolTipText("Indica las observaciones del alumno...");
        txa_Observacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        txa_Observacion.setSelectedTextColor(new java.awt.Color(255, 0, 0));
        txa_Observacion.setSelectionColor(new java.awt.Color(255, 255, 51));
        jScrollPane1.setViewportView(txa_Observacion);

        jLayeredPane5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 720, 180));
        jLayeredPane5.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 720, 20));

        lbl_Emergencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/En caso de emergencia.png"))); // NOI18N
        jLayeredPane5.add(lbl_Emergencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        lbl_FondoEmergencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/azul-blanco.jpg"))); // NOI18N
        jLayeredPane5.add(lbl_FondoEmergencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 450));

        tbp_Alumno.addTab("Emergencia", jLayeredPane5);

        jLayeredPane4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_NombreAlumno3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Biceps.png"))); // NOI18N
        jLayeredPane4.add(lbl_NombreAlumno3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, -1, 30));

        lbl_Sexo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Sexo.png"))); // NOI18N
        jLayeredPane4.add(lbl_Sexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 260, -1, -1));

        cmb_Sexo.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        cmb_Sexo.setForeground(new java.awt.Color(51, 153, 255));
        cmb_Sexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hombre", "Mujer", "Otro" }));
        cmb_Sexo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        cmb_Sexo.setFocusable(false);
        jLayeredPane4.add(cmb_Sexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 260, 150, 30));

        lbl_Supraespinal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Supraespinal.png"))); // NOI18N
        jLayeredPane4.add(lbl_Supraespinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 120, -1, 30));

        lbl_Abdominal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Abdominal.png"))); // NOI18N
        jLayeredPane4.add(lbl_Abdominal, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, -1, 30));

        lbl_MusloFrontal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Muslo frontal.png"))); // NOI18N
        jLayeredPane4.add(lbl_MusloFrontal, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 190, -1, 30));

        lbl_Pantoriilla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Pantorrilla.png"))); // NOI18N
        jLayeredPane4.add(lbl_Pantoriilla, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, -1, 30));

        lbl_Subescapular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Subescapular.png"))); // NOI18N
        jLayeredPane4.add(lbl_Subescapular, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 50, -1, 30));

        lbl_Pl7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Pl.png"))); // NOI18N
        jLayeredPane4.add(lbl_Pl7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, 30, 30));

        lbl_Densidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Densidad.png"))); // NOI18N
        jLayeredPane4.add(lbl_Densidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 360, -1, 30));

        txt_Densidad.setEditable(false);
        txt_Densidad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        txt_Densidad.setForeground(new java.awt.Color(255, 0, 0));
        txt_Densidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_Densidad.setToolTipText("Indíca la densidad...");
        txt_Densidad.setFont(new java.awt.Font("DS-Digital", 0, 18)); // NOI18N
        txt_Densidad.setPlaceholder("Densidad");
        txt_Densidad.setSelectedTextColor(new java.awt.Color(255, 0, 0));
        txt_Densidad.setSelectionColor(new java.awt.Color(255, 255, 51));
        jLayeredPane4.add(txt_Densidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 360, 140, -1));

        lbl_PorcGrasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/% graso.png"))); // NOI18N
        jLayeredPane4.add(lbl_PorcGrasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 360, -1, 30));

        txt_PorcentajeGrasa.setEditable(false);
        txt_PorcentajeGrasa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        txt_PorcentajeGrasa.setForeground(new java.awt.Color(255, 0, 0));
        txt_PorcentajeGrasa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_PorcentajeGrasa.setToolTipText("Indíca el porcenaje de grasa....");
        txt_PorcentajeGrasa.setFont(new java.awt.Font("DS-Digital", 0, 18)); // NOI18N
        txt_PorcentajeGrasa.setPlaceholder("% de Grasa");
        txt_PorcentajeGrasa.setSelectedTextColor(new java.awt.Color(255, 0, 0));
        txt_PorcentajeGrasa.setSelectionColor(new java.awt.Color(255, 255, 51));
        jLayeredPane4.add(txt_PorcentajeGrasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 360, 140, -1));

        lbl_Triceps.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Triceps.png"))); // NOI18N
        jLayeredPane4.add(lbl_Triceps, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, -1, 30));

        lbl_Pl1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Pl.png"))); // NOI18N
        jLayeredPane4.add(lbl_Pl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 30, 30));

        lbl_Pl2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Pl.png"))); // NOI18N
        jLayeredPane4.add(lbl_Pl2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 50, 30, 30));

        lbl_Pl3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Pl.png"))); // NOI18N
        jLayeredPane4.add(lbl_Pl3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 30, 30));

        lbl_Pl4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Pl.png"))); // NOI18N
        jLayeredPane4.add(lbl_Pl4, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 120, 30, 30));

        lbl_Pl5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Pl.png"))); // NOI18N
        jLayeredPane4.add(lbl_Pl5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 30, 30));

        lbl_Pl6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Pl.png"))); // NOI18N
        jLayeredPane4.add(lbl_Pl6, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 190, 30, 30));

        spr_Triceps.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        spr_Triceps.setColorTexto(new java.awt.Color(0, 204, 255));
        spr_Triceps.setFont(new java.awt.Font("DS-Digital", 0, 18)); // NOI18N
        spr_Triceps.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spr_TricepsStateChanged(evt);
            }
        });
        jLayeredPane4.add(spr_Triceps, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 90, 30));

        spr_Biceps.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        spr_Biceps.setColorTexto(new java.awt.Color(0, 204, 255));
        spr_Biceps.setFont(new java.awt.Font("DS-Digital", 0, 18)); // NOI18N
        spr_Biceps.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spr_BicepsStateChanged(evt);
            }
        });
        jLayeredPane4.add(spr_Biceps, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 120, 90, 30));

        spr_Abdominal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        spr_Abdominal.setColorTexto(new java.awt.Color(0, 204, 255));
        spr_Abdominal.setFont(new java.awt.Font("DS-Digital", 0, 18)); // NOI18N
        spr_Abdominal.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spr_AbdominalStateChanged(evt);
            }
        });
        jLayeredPane4.add(spr_Abdominal, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 190, 90, 30));

        spr_Pantorrilla.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        spr_Pantorrilla.setColorTexto(new java.awt.Color(0, 204, 255));
        spr_Pantorrilla.setFont(new java.awt.Font("DS-Digital", 0, 18)); // NOI18N
        spr_Pantorrilla.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spr_PantorrillaStateChanged(evt);
            }
        });
        jLayeredPane4.add(spr_Pantorrilla, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 260, 90, 30));

        spr_Subescapular.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        spr_Subescapular.setColorTexto(new java.awt.Color(0, 204, 255));
        spr_Subescapular.setFont(new java.awt.Font("DS-Digital", 0, 18)); // NOI18N
        spr_Subescapular.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spr_SubescapularStateChanged(evt);
            }
        });
        jLayeredPane4.add(spr_Subescapular, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 50, 90, 30));

        spr_Supraespinal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        spr_Supraespinal.setColorTexto(new java.awt.Color(0, 204, 255));
        spr_Supraespinal.setFont(new java.awt.Font("DS-Digital", 0, 18)); // NOI18N
        spr_Supraespinal.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spr_SupraespinalStateChanged(evt);
            }
        });
        jLayeredPane4.add(spr_Supraespinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 120, 90, 30));

        spr_Muslofrontal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        spr_Muslofrontal.setColorTexto(new java.awt.Color(0, 204, 255));
        spr_Muslofrontal.setFont(new java.awt.Font("DS-Digital", 0, 18)); // NOI18N
        spr_Muslofrontal.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spr_MuslofrontalStateChanged(evt);
            }
        });
        jLayeredPane4.add(spr_Muslofrontal, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 190, 90, 30));

        btn_IngresarPersonalizado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Añadir Normal.png"))); // NOI18N
        btn_IngresarPersonalizado.setBorderPainted(false);
        btn_IngresarPersonalizado.setContentAreaFilled(false);
        btn_IngresarPersonalizado.setFocusPainted(false);
        btn_IngresarPersonalizado.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Añadir Mouse.png"))); // NOI18N
        btn_IngresarPersonalizado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_IngresarPersonalizadoActionPerformed(evt);
            }
        });
        jLayeredPane4.add(btn_IngresarPersonalizado, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 330, 90, 80));

        lbl_FondoPersonalizado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/azul-blanco.jpg"))); // NOI18N
        jLayeredPane4.add(lbl_FondoPersonalizado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 450));

        tbp_Alumno.addTab("Personalizado", jLayeredPane4);

        jLayeredPane6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_Descuento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        txt_Descuento.setForeground(new java.awt.Color(0, 204, 255));
        txt_Descuento.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_Descuento.setToolTipText("Ingresa el descuento...");
        txt_Descuento.setFont(new java.awt.Font("DS-Digital", 0, 18)); // NOI18N
        txt_Descuento.setPlaceholder("Descuento");
        txt_Descuento.setSelectedTextColor(new java.awt.Color(255, 0, 0));
        txt_Descuento.setSelectionColor(new java.awt.Color(255, 255, 51));
        txt_Descuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_DescuentoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_DescuentoKeyTyped(evt);
            }
        });
        jLayeredPane6.add(txt_Descuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, 110, 30));

        btn_AgregarPago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Botones/Boton Nuevo Pago.png"))); // NOI18N
        btn_AgregarPago.setToolTipText("Ingresar Pago");
        btn_AgregarPago.setBorderPainted(false);
        btn_AgregarPago.setContentAreaFilled(false);
        btn_AgregarPago.setFocusPainted(false);
        btn_AgregarPago.setFocusable(false);
        btn_AgregarPago.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Botones/Boton Nuevo Pago Mouse.png"))); // NOI18N
        btn_AgregarPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AgregarPagoActionPerformed(evt);
            }
        });
        jLayeredPane6.add(btn_AgregarPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 110, 70, 70));

        dc_FechaInicio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        dc_FechaInicio.setForeground(new java.awt.Color(0, 204, 255));
        dc_FechaInicio.setToolTipText("Ingresa la fecha de inicio...");
        dc_FechaInicio.setFont(new java.awt.Font("Ebrima", 0, 14)); // NOI18N
        jLayeredPane6.add(dc_FechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 80, 120, 30));
        dc_FechaInicio.getDateEditor().addPropertyChangeListener(new java.beans.PropertyChangeListener(){
            public void propertyChange(java.beans.PropertyChangeEvent e) {
                //Aquí agregaremos la funcionalidad que queremos
                //por ejemplo al seleccionar una fecha le mostrare un diálogo con la fecha de hoy
                //System.out.println("Presionado");
            }
        });

        tb_Pago.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tb_Pago.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nro", "Grupo", "Meses", "Clases", "Total", "Fecha Inicio", "Fecha Término", "Forma de Pago", "Agregado", "Descuento", "Porcentaje"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_Pago.setColorBackgoundHead(new java.awt.Color(0, 153, 204));
        tb_Pago.setColorBordeFilas(new java.awt.Color(0, 255, 255));
        tb_Pago.setColorBordeHead(new java.awt.Color(255, 255, 255));
        tb_Pago.setColorFilasBackgound2(new java.awt.Color(204, 255, 255));
        tb_Pago.setFillsViewportHeight(true);
        tb_Pago.setFuenteFilas(new java.awt.Font("Mairy Oblicua", 1, 18)); // NOI18N
        tb_Pago.setFuenteFilasSelect(new java.awt.Font("Mairy Oblicua", 1, 18)); // NOI18N
        tb_Pago.setFuenteHead(new java.awt.Font("Mairy Bold", 1, 24)); // NOI18N
        tb_Pago.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_PagoMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tb_Pago);

        jLayeredPane6.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 760, 230));

        lbl_Pago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Pago.png"))); // NOI18N
        jLayeredPane6.add(lbl_Pago, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, -1, -1));

        lbl_Descuento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Descuento.png"))); // NOI18N
        jLayeredPane6.add(lbl_Descuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, -1, 30));

        lbl_FechaInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Fecha Inicio.png"))); // NOI18N
        jLayeredPane6.add(lbl_FechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 80, -1, 30));

        lbl_FormaPago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Forma de Pago.png"))); // NOI18N
        jLayeredPane6.add(lbl_FormaPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, 140, 30));

        cmb_FormaPago.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        cmb_FormaPago.setForeground(new java.awt.Color(51, 153, 255));
        cmb_FormaPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Efectivo", "Tarjeta", "Cheque", "Transferencia" }));
        cmb_FormaPago.setToolTipText("Efectivo, Tarjeta, Cheque o Transferencia");
        cmb_FormaPago.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));
        cmb_FormaPago.setFocusable(false);
        jLayeredPane6.add(cmb_FormaPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 130, 130, 30));

        spr_Meses.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        spr_Meses.setColorTexto(new java.awt.Color(0, 204, 255));
        spr_Meses.setFont(new java.awt.Font("DS-Digital", 0, 18)); // NOI18N
        spr_Meses.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spr_MesesStateChanged(evt);
            }
        });
        jLayeredPane6.add(spr_Meses, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 60, 30));

        lbl_Meses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Meses.png"))); // NOI18N
        jLayeredPane6.add(lbl_Meses, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 70, -1));

        lbl_Total.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Total.png"))); // NOI18N
        jLayeredPane6.add(lbl_Total, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 60, 30));

        txt_Total.setEditable(false);
        txt_Total.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        txt_Total.setForeground(new java.awt.Color(0, 204, 255));
        txt_Total.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_Total.setToolTipText("Indica el Total");
        txt_Total.setFont(new java.awt.Font("DS-Digital", 0, 18)); // NOI18N
        txt_Total.setPlaceholder("Total");
        txt_Total.setSelectedTextColor(new java.awt.Color(255, 0, 0));
        txt_Total.setSelectionColor(new java.awt.Color(255, 255, 51));
        jLayeredPane6.add(txt_Total, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 120, 30));

        txt_Agregado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        txt_Agregado.setForeground(new java.awt.Color(0, 204, 255));
        txt_Agregado.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_Agregado.setToolTipText("Indica el Total");
        txt_Agregado.setFont(new java.awt.Font("DS-Digital", 0, 18)); // NOI18N
        txt_Agregado.setPlaceholder("Agregado");
        txt_Agregado.setSelectedTextColor(new java.awt.Color(255, 0, 0));
        txt_Agregado.setSelectionColor(new java.awt.Color(255, 255, 51));
        txt_Agregado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_AgregadoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_AgregadoKeyTyped(evt);
            }
        });
        jLayeredPane6.add(txt_Agregado, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 30, 120, 30));

        lbl_Agregado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Agregado.png"))); // NOI18N
        jLayeredPane6.add(lbl_Agregado, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 30, -1, 30));

        cmb_Grupo.setEditable(true);
        cmb_Grupo.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        cmb_Grupo.setForeground(new java.awt.Color(51, 153, 255));
        cmb_Grupo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));
        cmb_Grupo.setFocusable(false);
        cmb_Grupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_GrupoActionPerformed(evt);
            }
        });
        jLayeredPane6.add(cmb_Grupo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 150, 30));

        btn_Grupo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Botones/Boton Admin Grupo.png"))); // NOI18N
        btn_Grupo.setToolTipText("Visualizar detalle del grupo");
        btn_Grupo.setBorderPainted(false);
        btn_Grupo.setContentAreaFilled(false);
        btn_Grupo.setFocusPainted(false);
        btn_Grupo.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Botones/Boton Admin Grupo Mouse.png"))); // NOI18N
        btn_Grupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GrupoActionPerformed(evt);
            }
        });
        jLayeredPane6.add(btn_Grupo, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, 70, 50));

        txt_Pago.setEditable(false);
        txt_Pago.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        txt_Pago.setForeground(new java.awt.Color(0, 204, 255));
        txt_Pago.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_Pago.setToolTipText("Indica el total a pagar del Grupo");
        txt_Pago.setFont(new java.awt.Font("DS-Digital", 0, 18)); // NOI18N
        txt_Pago.setPlaceholder("Pago Grupo");
        txt_Pago.setSelectedTextColor(new java.awt.Color(255, 0, 0));
        txt_Pago.setSelectionColor(new java.awt.Color(255, 255, 51));
        jLayeredPane6.add(txt_Pago, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 30, 120, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Textos/Grupo.png"))); // NOI18N
        jLayeredPane6.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, 30));

        ckb_Porcentaje.setForeground(new java.awt.Color(0, 204, 255));
        ckb_Porcentaje.setText("Porc %");
        ckb_Porcentaje.setToolTipText("Descuento en porcentaje");
        ckb_Porcentaje.setFocusPainted(false);
        ckb_Porcentaje.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        ckb_Porcentaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckb_PorcentajeActionPerformed(evt);
            }
        });
        jLayeredPane6.add(ckb_Porcentaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 80, -1, -1));

        ckb_grupoCompleto.setForeground(new java.awt.Color(0, 204, 255));
        ckb_grupoCompleto.setText("Pago completo");
        ckb_grupoCompleto.setToolTipText("Todo el grupo paga");
        ckb_grupoCompleto.setFocusPainted(false);
        ckb_grupoCompleto.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        ckb_grupoCompleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckb_grupoCompletoActionPerformed(evt);
            }
        });
        jLayeredPane6.add(ckb_grupoCompleto, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 130, -1, 30));

        lbl_FondoPago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/azul-blanco.jpg"))); // NOI18N
        jLayeredPane6.add(lbl_FondoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 450));

        tbp_Alumno.addTab("Pago", jLayeredPane6);

        getContentPane().add(tbp_Alumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, -1, 480));

        lbl_Titulo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_Titulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Ingresar Nuevo Alumno.png"))); // NOI18N
        getContentPane().add(lbl_Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 330, 90));

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
        getContentPane().add(btn_Volver, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 620, 70, 60));

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
        getContentPane().add(btn_Aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 630, 180, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Menu.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_FotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_FotoActionPerformed
        if (seleccionar.showDialog(null, null) == JFileChooser.APPROVE_OPTION) {
            archivo = seleccionar.getSelectedFile();
            if (archivo.canRead()) {
                if (archivo.getName().endsWith("jpg") || archivo.getName().endsWith("jpeg") || archivo.getName().endsWith("gif")){
                    get.cargarFoto(seleccionar.getSelectedFile().toString(), lbl_Foto);
                    lbl_Foto.setText("");
                    cambiaFoto = true;
                }
                else
                    JOptionPane.showMessageDialog(null, "Archivo no compatible.","Precaución",JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_FotoActionPerformed

    private void btn_IngresarHuellaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_IngresarHuellaActionPerformed
        new AdministrarHuellaDigital(new javax.swing.JDialog(), true, false, get.reemplazo(txt_Rut.getText(), ".")).setVisible(true);
    }//GEN-LAST:event_btn_IngresarHuellaActionPerformed

    private void btn_VolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_VolverActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btn_VolverActionPerformed

    private void spr_TricepsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spr_TricepsStateChanged
        calculaDensidad();
    }//GEN-LAST:event_spr_TricepsStateChanged

    private void spr_BicepsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spr_BicepsStateChanged
        calculaDensidad();
    }//GEN-LAST:event_spr_BicepsStateChanged

    private void spr_AbdominalStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spr_AbdominalStateChanged
        calculaDensidad();
    }//GEN-LAST:event_spr_AbdominalStateChanged

    private void spr_PantorrillaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spr_PantorrillaStateChanged
        calculaDensidad();
    }//GEN-LAST:event_spr_PantorrillaStateChanged

    private void spr_SubescapularStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spr_SubescapularStateChanged
        calculaDensidad();
    }//GEN-LAST:event_spr_SubescapularStateChanged

    private void spr_SupraespinalStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spr_SupraespinalStateChanged
        calculaDensidad();
    }//GEN-LAST:event_spr_SupraespinalStateChanged

    private void spr_MuslofrontalStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spr_MuslofrontalStateChanged
        calculaDensidad();
    }//GEN-LAST:event_spr_MuslofrontalStateChanged

    private void cmb_GrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_GrupoActionPerformed
        cargarPagoAlumno();
    }//GEN-LAST:event_cmb_GrupoActionPerformed

    private void btn_GrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GrupoActionPerformed
        //si el combo box no tiene elementos en su interior llamamos la gestionador de grupo vacio
        if (cmb_Grupo.getItemCount() == 0) 
            new AsignarRecursos(this, true).setVisible(true);
        else //Si no llamamos al gestionador que busque el grupo seleccionado
            new AsignarRecursos(this, true, cmb_Grupo.getSelectedItem().toString(), get.reemplazo(txt_Rut.getText(), ".")).setVisible(true);
        
        cargarPagoAlumno();
        cargarCmbGrupo(new Alumno(id).obtenerIdAlumno().getRut());
        cargarPersonalizadoDefecto(id);
        //Si el combo box del grupo aun sigue vacio el boton de pago se inhabilita
        if (cmb_Grupo.getItemCount() == 0) 
            btn_AgregarPago.setEnabled(false);
        else//Delo contrario habilitamos el boton de pago
            btn_AgregarPago.setEnabled(true);
    }//GEN-LAST:event_btn_GrupoActionPerformed

    private void spr_MesesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spr_MesesStateChanged
        calculaTotal();
    }//GEN-LAST:event_spr_MesesStateChanged
    
    private void txt_AgregadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_AgregadoKeyReleased
        calculaTotal();
    }//GEN-LAST:event_txt_AgregadoKeyReleased

    private void txt_DescuentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_DescuentoKeyReleased
        calculaTotal();
    }//GEN-LAST:event_txt_DescuentoKeyReleased

    private void txt_DescuentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_DescuentoKeyTyped
        if (!validar.maximoLargo(txt_Descuento.getText(), 8))
            evt.consume();

        if(Character.isLetter(evt.getKeyChar())) 
            evt.consume(); 
    }//GEN-LAST:event_txt_DescuentoKeyTyped

    private void txt_AgregadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_AgregadoKeyTyped
        if (!validar.maximoLargo(txt_Agregado.getText(), 8))
            evt.consume();

        if(Character.isLetter(evt.getKeyChar())) 
            evt.consume(); 
    }//GEN-LAST:event_txt_AgregadoKeyTyped

    private void btn_AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AceptarActionPerformed
        aceptar();
    }//GEN-LAST:event_btn_AceptarActionPerformed

    private void btn_CambiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CambiarActionPerformed
        cambiaEdad(txt_Edad.isVisible());
    }//GEN-LAST:event_btn_CambiarActionPerformed

    private void btn_IngresarFisicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_IngresarFisicoActionPerformed
        if (!cmb_Enfermedades.getSelectedItem().equals("")) 
            cargaInteligenteFisico(cmb_Enfermedades, "Enfermedad", "DetalleEnfermedad", id, mEnfermedad, tb_Enfermedad, "Enfermedades");
        
        if (!cmb_Lesiones.getSelectedItem().equals("")) 
            cargaInteligenteFisico(cmb_Lesiones, "Lesion", "DetalleLesion", id, mLesion, tb_Lesiones, "Lesiones");
        
        if (!cmb_Medicamentos.getSelectedItem().equals("")) 
            cargaInteligenteFisico(cmb_Medicamentos, "Medicamento", "DetalleMedicamento", id, mMedicamento, tb_Medicamentos, "Medicamentos");
        
        if (!cmb_Alergias.getSelectedItem().equals("")) 
            cargaInteligenteFisico(cmb_Alergias, "Alergia", "DetalleAlergia", id, mAlergico, tb_Alergias, "Alergias");
    }//GEN-LAST:event_btn_IngresarFisicoActionPerformed

    private void btn_IngresarDeporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_IngresarDeporteActionPerformed
        ingresarDeporte();
    }//GEN-LAST:event_btn_IngresarDeporteActionPerformed

    private void btn_IngresarPersonalizadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_IngresarPersonalizadoActionPerformed
        gestionarPersonalizado();
    }//GEN-LAST:event_btn_IngresarPersonalizadoActionPerformed

    private void tb_EnfermedadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_EnfermedadMouseClicked
        if (evt.getClickCount() == 2)
            eliminarFisico(tb_Enfermedad);
    }//GEN-LAST:event_tb_EnfermedadMouseClicked

    private void tb_LesionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_LesionesMouseClicked
        if (evt.getClickCount() == 2)
            eliminarFisico(tb_Lesiones);
    }//GEN-LAST:event_tb_LesionesMouseClicked

    private void tb_MedicamentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_MedicamentosMouseClicked
        if (evt.getClickCount() == 2)
            eliminarFisico(tb_Medicamentos);
    }//GEN-LAST:event_tb_MedicamentosMouseClicked

    private void tb_AlergiasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_AlergiasMouseClicked
        if (evt.getClickCount() == 2)
            eliminarFisico(tb_Alergias);
    }//GEN-LAST:event_tb_AlergiasMouseClicked

    private void txt_EstablecimientoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_EstablecimientoKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            cargaInteligenteDeporte();
    }//GEN-LAST:event_txt_EstablecimientoKeyPressed

    private void txt_DireccionDeporteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_DireccionDeporteKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            cargaInteligenteDeporte();
    }//GEN-LAST:event_txt_DireccionDeporteKeyPressed

    private void tb_DeporteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_DeporteMouseClicked
        if (evt.getClickCount() == 2)
            eliminarDeporte();
    }//GEN-LAST:event_tb_DeporteMouseClicked

    private void btn_AgregarPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AgregarPagoActionPerformed
        ingresarPago();
    }//GEN-LAST:event_btn_AgregarPagoActionPerformed

    private void ckb_PorcentajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckb_PorcentajeActionPerformed
        calculaTotal();
    }//GEN-LAST:event_ckb_PorcentajeActionPerformed

    private void ckb_grupoCompletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckb_grupoCompletoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ckb_grupoCompletoActionPerformed

    private void txt_RutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_RutKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            aceptar();
    }//GEN-LAST:event_txt_RutKeyPressed

    private void txt_RutKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_RutKeyReleased
        validar.rut(txt_Rut);
        txt_Rut.setText(get.escribirRut(txt_Rut.getText(), txt_Rut));
    }//GEN-LAST:event_txt_RutKeyReleased

    private void txt_RutKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_RutKeyTyped
        if (!validar.maximoLargo(get.reemplazo(txt_Rut.getText(), "."), 9))
            evt.consume();
        
        if(Character.isLetter(evt.getKeyChar()) && !String.valueOf(evt.getKeyChar()).equals("k")) 
            evt.consume(); 
    }//GEN-LAST:event_txt_RutKeyTyped

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

    private void spr_PesoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_spr_PesoKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            aceptar();
    }//GEN-LAST:event_spr_PesoKeyPressed

    private void spr_EstaturaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_spr_EstaturaKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            aceptar();
    }//GEN-LAST:event_spr_EstaturaKeyPressed

    private void txt_NombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_NombreKeyTyped
        if (!validar.maximoLargo(txt_Nombre.getText(), 40))
            evt.consume();

        if(Character.isDigit(evt.getKeyChar())) 
            evt.consume(); 
        
        txt_Nombre.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(txt_Nombre.getText()), 1));
    }//GEN-LAST:event_txt_NombreKeyTyped

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

    private void txt_CorreoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_CorreoKeyReleased
        validar.correo(txt_Correo);
    }//GEN-LAST:event_txt_CorreoKeyReleased

    private void txt_TelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_TelefonoKeyTyped
        if (!validar.maximoLargo(txt_Telefono.getText(), 8))
            evt.consume();

        if(Character.isLetter(evt.getKeyChar())) 
            evt.consume(); 
    }//GEN-LAST:event_txt_TelefonoKeyTyped

    private void tb_PagoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_PagoMouseClicked
        if (evt.getClickCount() == 2){
            if( JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar éste pago?\nNota: Si lo elimina, es posible que al realizar la cuadratura se vea afectada.", "Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == 0){
                int idPago = Integer.valueOf(tb_Pago.getValueAt(tb_Pago.getSelectedRow(), 0).toString());
                
                switch (new Pago(idPago).eliminarPago()) {
                    case 1:
                        JOptionPane.showMessageDialog(null, "El pago ha sido eliminado correctamente", "Información :)", JOptionPane.INFORMATION_MESSAGE);
                        tablaPago();
                        break;
                    case -1:
                        JOptionPane.showMessageDialog(null, "El pago no se pudo eliminar correctamente, favor intente más tarde", "Información :)", JOptionPane.WARNING_MESSAGE);
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(null, "El pago que desea eliminar, no existe, favor seleccione otro", "Precaución D:", JOptionPane.WARNING_MESSAGE);
                        break;
                }
            }
        }
    }//GEN-LAST:event_tb_PagoMouseClicked

    private void txt_NombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_NombreFocusLost
        txt_Nombre.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(txt_Nombre.getText()), 1));
    }//GEN-LAST:event_txt_NombreFocusLost

    private void txt_RutFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_RutFocusLost
        txt_Rut.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(txt_Rut.getText()), 1));
        validar.rut(txt_Rut);
    }//GEN-LAST:event_txt_RutFocusLost

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
            java.util.logging.Logger.getLogger(IngresarAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IngresarAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IngresarAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IngresarAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                IngresarAlumno dialog = new IngresarAlumno(new javax.swing.JDialog(), true, "", true);
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
    private javax.swing.JButton btn_AgregarPago;
    private javax.swing.JButton btn_Cambiar;
    private javax.swing.JButton btn_ConfiguracionDeporte;
    private javax.swing.JButton btn_ConfigurarFisico;
    private javax.swing.JButton btn_Foto;
    private javax.swing.JButton btn_Grupo;
    private javax.swing.JButton btn_IngresarDeporte;
    private javax.swing.JButton btn_IngresarFisico;
    private javax.swing.JButton btn_IngresarHuella;
    private javax.swing.JButton btn_IngresarPersonalizado;
    private javax.swing.JButton btn_Volver;
    private Extra.MiCheckBox ckb_Porcentaje;
    private Extra.MiCheckBox ckb_grupoCompleto;
    private javax.swing.JComboBox<String> cmb_Alergias;
    private javax.swing.JComboBox<String> cmb_Deporte;
    private javax.swing.JComboBox<String> cmb_Enfermedades;
    private javax.swing.JComboBox<String> cmb_FormaPago;
    private javax.swing.JComboBox<String> cmb_Grupo;
    private javax.swing.JComboBox<String> cmb_Lesiones;
    private javax.swing.JComboBox<String> cmb_Medicamentos;
    private javax.swing.JComboBox<String> cmb_Nivel;
    private javax.swing.JComboBox<String> cmb_Sexo;
    private javax.swing.JComboBox<String> cmb_Tiempo;
    private com.toedter.calendar.JDateChooser dc_FechaInicio;
    private com.toedter.calendar.JDateChooser dc_FechaNacimiento;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JLayeredPane jLayeredPane5;
    private javax.swing.JLayeredPane jLayeredPane6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLayeredPane jpane_Foto;
    private javax.swing.JLabel lbl_Abdominal;
    private javax.swing.JLabel lbl_Agregado;
    private javax.swing.JLabel lbl_Alergias;
    private javax.swing.JLabel lbl_ApellidoM;
    private javax.swing.JLabel lbl_ApellidoP;
    private javax.swing.JLabel lbl_Cantidad;
    private javax.swing.JLabel lbl_Correo;
    private javax.swing.JLabel lbl_Densidad;
    private javax.swing.JLabel lbl_Deporte;
    private javax.swing.JLabel lbl_Descuento;
    private javax.swing.JLabel lbl_Direccion;
    private javax.swing.JLabel lbl_DireccionDeporte;
    private javax.swing.JLabel lbl_Edad;
    private javax.swing.JLabel lbl_Emergencia;
    private javax.swing.JLabel lbl_Enfermedades;
    private javax.swing.JLabel lbl_Establecimiento;
    private javax.swing.JLabel lbl_Estatura1;
    private javax.swing.JLabel lbl_FechaInicio;
    private javax.swing.JLabel lbl_FondoDeporte;
    private javax.swing.JLabel lbl_FondoEmergencia;
    private javax.swing.JLabel lbl_FondoFisico;
    private javax.swing.JLabel lbl_FondoPago;
    private javax.swing.JLabel lbl_FondoPersonal;
    private javax.swing.JLabel lbl_FondoPersonalizado;
    private javax.swing.JLabel lbl_FormaPago;
    private com.bolivia.label.CLabel lbl_Foto;
    private javax.swing.JLabel lbl_Lesiones;
    private javax.swing.JLabel lbl_Logo;
    private javax.swing.JLabel lbl_Medicamentos;
    private javax.swing.JLabel lbl_Meses;
    private javax.swing.JLabel lbl_MusloFrontal;
    private javax.swing.JLabel lbl_Nivel;
    private javax.swing.JLabel lbl_NombreAlumno;
    private javax.swing.JLabel lbl_NombreAlumno3;
    private javax.swing.JLabel lbl_NombreEmergencia;
    private javax.swing.JLabel lbl_Observaciones;
    private javax.swing.JLabel lbl_Pago;
    private javax.swing.JLabel lbl_Pantoriilla;
    private javax.swing.JLabel lbl_Peso1;
    private javax.swing.JLabel lbl_Pl1;
    private javax.swing.JLabel lbl_Pl2;
    private javax.swing.JLabel lbl_Pl3;
    private javax.swing.JLabel lbl_Pl4;
    private javax.swing.JLabel lbl_Pl5;
    private javax.swing.JLabel lbl_Pl6;
    private javax.swing.JLabel lbl_Pl7;
    private javax.swing.JLabel lbl_PorcGrasa;
    private javax.swing.JLabel lbl_Rut;
    private javax.swing.JLabel lbl_Sexo;
    private javax.swing.JLabel lbl_Subescapular;
    private javax.swing.JLabel lbl_Supraespinal;
    private javax.swing.JLabel lbl_TelefonoEmergencia;
    private javax.swing.JLabel lbl_Tiempo;
    private javax.swing.JLabel lbl_Titulo;
    private javax.swing.JLabel lbl_Total;
    private javax.swing.JLabel lbl_Triceps;
    private javax.swing.JLabel lbl_telefono;
    private Extra.MiSpinner spr_Abdominal;
    private Extra.MiSpinner spr_Biceps;
    private Extra.MiSpinner spr_Estatura;
    private Extra.MiSpinner spr_Meses;
    private Extra.MiSpinner spr_Muslofrontal;
    private Extra.MiSpinner spr_Pantorrilla;
    private Extra.MiSpinner spr_Peso;
    private Extra.MiSpinner spr_Subescapular;
    private Extra.MiSpinner spr_Supraespinal;
    private Extra.MiSpinner spr_Tiempo;
    private Extra.MiSpinner spr_Triceps;
    private Extra.MiSpinner spr_vecesxSemana;
    private rojerusan.RSTableMetro tb_Alergias;
    private rojerusan.RSTableMetro tb_Deporte;
    private rojerusan.RSTableMetro tb_Enfermedad;
    private rojerusan.RSTableMetro tb_Lesiones;
    private rojerusan.RSTableMetro tb_Medicamentos;
    private rojerusan.RSTableMetro tb_Pago;
    private javax.swing.JTabbedPane tbp_Alumno;
    private javax.swing.JTextArea txa_Observacion;
    private Extra.TextPlaceholder txt_Agregado;
    private Extra.TextPlaceholder txt_Apellido;
    private Extra.TextPlaceholder txt_ApellidoMaterno;
    private Extra.TextPlaceholder txt_Correo;
    private Extra.TextPlaceholder txt_Densidad;
    private Extra.TextPlaceholder txt_Descuento;
    private Extra.TextPlaceholder txt_Direccion;
    private Extra.TextPlaceholder txt_DireccionDeporte;
    private Extra.TextPlaceholder txt_Edad;
    private Extra.TextPlaceholder txt_Establecimiento;
    private Extra.TextPlaceholder txt_Nombre;
    private Extra.TextPlaceholder txt_NombreEmergencia;
    private Extra.TextPlaceholder txt_Pago;
    private Extra.TextPlaceholder txt_PorcentajeGrasa;
    private Extra.TextPlaceholder txt_Rut;
    private Extra.TextPlaceholder txt_Telefono;
    private Extra.TextPlaceholder txt_TelefonoEmergencia;
    private Extra.TextPlaceholder txt_Total;
    // End of variables declaration//GEN-END:variables
    /***************************ADMINISTRAR ALUMNOS****************************/
    private void ingresar() {
        Alumno alumno = cargarAlumno();
        switch (alumno.ingresar()) {
            case 1:
                id = new Alumno(alumno.getRut()).obtenerAlumno().getId();
                
                if (cambiaFoto) 
                    get.generarArchivo("Alumnos", alumno.obtenerAlumno().getFoto(), archivo);

                JOptionPane.showMessageDialog(null, "Alumno ingresado correctamente :D!","Información :)",JOptionPane.INFORMATION_MESSAGE);
                tabAlumno(true);
                tbp_Alumno.setEnabledAt(4, true);
                btn_IngresarHuella.setEnabled(true);
                //txt_Edad.setText(String.valueOf(get.calculaEdad(alumno.getFechaNacimiento())));
                //cambiaEdad(false);
            break;

            case 2:
                JOptionPane.showMessageDialog(null, "Este alumno ya se encuentra registrado, favor ingresa otro D:!","Precuación",JOptionPane.WARNING_MESSAGE);
            break;

            case -1:
                JOptionPane.showMessageDialog(null, "Ocurrió un problema en el ingreso, favor intenta más tarde!","Precuación :)",JOptionPane.WARNING_MESSAGE);
            break;

            case 0:
                JOptionPane.showMessageDialog(null, "Error en la comunicacipin con la base de datos","Error!",JOptionPane.ERROR_MESSAGE);
            break;
        }
    }
    
    private void modificar(){
        if (cargarAlumno().modificar() == 1) {
            if (cambiaFoto) 
                get.generarArchivo("Alumnos", cargarAlumno().obtenerAlumno().getFoto(), archivo);
            
            JOptionPane.showMessageDialog(null, "Alumno modificado correctamente :D!", "Información :)", JOptionPane.INFORMATION_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null, "Orrió un problema inesperado.", "Precaución D:", JOptionPane.WARNING_MESSAGE);  
    }

    private Alumno cargarAlumno() {
        Alumno alumno = new Alumno();
        Ruta ruta = new Ruta(System.getProperty("user.dir") + "\\Alumnos").obtenerRuta();
        String extension;
        
        if (cambiaFoto) 
            extension = get.reemplazo(txt_Rut.getText(), ".") + "." + new Obtener().extensionArchivo(archivo.getName());
        else
            extension = "User.png"; 
        //SI esta modificando y no cambia la foto mantenga la misma foto
        if (!ingresar && !cambiaFoto) {
            if (!new Alumno(id).obtenerIdAlumno().getExtension().equals("User.png")) {
                extension =  new Alumno(id).obtenerIdAlumno().getExtension();
            }
        }

        alumno.setRut(get.reemplazo(txt_Rut.getText(), "."));
        alumno.setRuta(ruta.obtenerRuta().getIdArchivo());
        alumno.setNombre(txt_Nombre.getText());
        alumno.setApellidoP(txt_Apellido.getText());
        alumno.setApellidoM(txt_ApellidoMaterno.getText());
        alumno.setDireccion(txt_Direccion.getText());
        alumno.setCorreo(txt_Correo.getText());
        alumno.setFono(txt_Telefono.getText());
        alumno.setFechaNacimiento(dc_FechaNacimiento.getDate());
        alumno.setEstatura(Double.valueOf(spr_Estatura.getValue().toString()));
        alumno.setPeso(Double.valueOf(spr_Peso.getValue().toString()));
        alumno.setNombreEmergencia(txt_NombreEmergencia.getName());
        alumno.setTelefonoEmergencia(txt_TelefonoEmergencia.getText());
        alumno.setObservacion(txa_Observacion.getText());
        alumno.setExtension(extension);
        alumno.setIdPersonalizado(0);
        return alumno;
    }
    /***************************ADMINISTRAR FISICOS****************************/
    private void calculaDensidad() {
        double suma =  Double.valueOf(spr_Triceps.getValue().toString()) +
                    Double.valueOf(spr_Subescapular.getValue().toString()) +
                    Double.valueOf(spr_Biceps.getValue().toString()) +
                    Double.valueOf(spr_Supraespinal.getValue().toString()) +
                    Double.valueOf(spr_Abdominal.getValue().toString()) +
                    Double.valueOf(spr_Muslofrontal.getValue().toString()) +
                    Double.valueOf(spr_Pantorrilla.getValue().toString());      
        txt_Densidad.setText(String.valueOf(1.0988 - (0.0004 * suma)));
        calculaPorcGrasa();
    }

    private void calculaPorcGrasa() {
        String resultado = ((495 / Double.valueOf(txt_Densidad.getText())) - 450) + "";
        txt_PorcentajeGrasa.setText(resultado.substring(0, 5));
    }
    
    private void seteaSpinner(){
        //CArgamos valores decimales para la estatura y el peso
        spr_Estatura.setModel(miSpinner(0, 3.00, 1.0, 0.01));
        spr_Peso.setModel(miSpinner(15.00, 500.000, 40, 0.01));
        //Cargamos valores enteros por defecto
        
        spr_Triceps.setModel(miSpinner(0, 100, 0, 0.5));
        spr_Subescapular.setModel(miSpinner(0, 100, 0, 0.5));
        spr_Biceps.setModel(miSpinner(0, 100, 0, 0.5));
        spr_Supraespinal.setModel(miSpinner(0, 100, 0, 0.5));
        spr_Abdominal.setModel(miSpinner(0, 100, 0, 0.5));
        spr_Muslofrontal.setModel(miSpinner(0, 100, 0, 0.5));
        spr_Pantorrilla.setModel(miSpinner(0, 100, 0, 0.5));
        
        spr_Tiempo.setModel(miSpinner(0, 31, 1));
        spr_vecesxSemana.setModel(miSpinner(1, 7, 1));
        spr_Meses.setModel(miSpinner(1, 12, 1));
    }
    
    private SpinnerNumberModel miSpinner(int min, int max, int valor){
        return new SpinnerNumberModel(valor, min, max, 1);
    }
    
    private SpinnerNumberModel miSpinner(double min, double max, double value, double paso){
        return new SpinnerNumberModel(value, min, max, paso);
    }
    
    private int calculaPago(){
        return ((get.valorEntero(get.reemplazo(txt_Pago.getText(), ".")) +
                get.valorEntero(get.reemplazo(txt_Agregado.getText(), "."))) *
                Integer.valueOf(spr_Meses.getValue().toString()));
    }

    private void calculaTotal(){
        int descuento = 0;
        
        if (ckb_Porcentaje.isSelected()) 
            descuento = (int) (calculaPago() * (get.valorDecimal(get.reemplazo(txt_Descuento.getText(), ".")) /100));
        else
            descuento = get.valorEntero(get.reemplazo(txt_Descuento.getText(), "."));
        
        txt_Total.setText(get.concatenaCaracter((calculaPago() - descuento) + "", 3, '.'));
        
        if (Integer.valueOf(get.reemplazo(txt_Total.getText(), ".") ) < 1000 ) //CAMBIAR CON LA CONFIGURACION EL VALOR DE 1.000
            txt_Total.setForeground(Color.RED);
        else
            txt_Total.setForeground(Color.BLUE);
    }

    private void setWidthColumnDeporte() {
        tb_Deporte.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tb_Deporte.doLayout();
        tb_Deporte.getColumnModel().getColumn(0).setPreferredWidth(150);  //DEPORTE
        tb_Deporte.getColumnModel().getColumn(1).setPreferredWidth(150);  //NIVEL
        tb_Deporte.getColumnModel().getColumn(2).setPreferredWidth(150);  //TIEMPO
        tb_Deporte.getColumnModel().getColumn(3).setPreferredWidth(150);  //CANTIDAD
        tb_Deporte.getColumnModel().getColumn(4).setPreferredWidth(200);  //ESTABLECIMIENTO
        tb_Deporte.getColumnModel().getColumn(5).setPreferredWidth(200);  //DIRECCION
    }

    private void cargarFechaActual() {
        try {dc_FechaInicio.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(get.fechaActual("/")));} 
        catch (ParseException e) {}
    }

    private void setFilaAlto(int alto) {
        tb_Deporte.setRowHeight(alto);
        tb_Pago.setRowHeight(alto);        
        tb_Enfermedad.setRowHeight(alto);
        tb_Lesiones.setRowHeight(alto);
        tb_Medicamentos.setRowHeight(alto);
        tb_Alergias.setRowHeight(alto);
    }
    
    private boolean validaCamposImportantes(){
        return validar.rut(txt_Rut) && validar.correo(txt_Correo) && validarFechaPago(dc_FechaNacimiento);
    }
    
    private void aceptar() {
        if (!usuarioVacio() ) {
            if (validaCamposImportantes()) {
                if (ingresar) 
                    ingresar();
                else
                    modificar();
            }
        }
    }

    private void cambiaEdad(boolean b) {
        txt_Edad.setVisible(!b);
        dc_FechaNacimiento.setVisible(b);
    }

    private void cargaCMBFisico(javax.swing.JComboBox comboBox) {
        comboBox.removeAllItems();
        ArrayList<Fisico> cargaFisico = new Fisico().obtenerFisico(false, comboBox.getName(), 0);

        for (int i = 0; i < cargaFisico.size(); i++) 
            comboBox.addItem(cargaFisico.get(i).getFisico());

        AutoCompleteDecorator.decorate(comboBox);//Auto Completa en el combobox
        comboBox.setSelectedIndex(cargaFisico.size() - 1);  
    }   
    
    private void cargarCmbGrupo(String nombre) {
        cmb_Grupo.removeAllItems();
        ArrayList<Grupo> grupo = new Grupo(nombre).buscarGrupos(true);
        
        for (int i = 0; i < grupo.size(); i++)
            cmb_Grupo.addItem(grupo.get(i).getNombre());
    }

    private void cargarPagoAlumno() {
        if (cmb_Grupo.getSelectedItem() != null) {
            int cantidad = new Recurso().obtenerProcRecurso("Contar", cmb_Grupo.getSelectedItem().toString());
            int monto = new Grupo(new Grupo(cmb_Grupo.getSelectedItem().toString()).obtenerGrupo().getId()).obtenerPrecioGrupo();
            txt_Pago.setText(get.concatenaCaracter("" + (monto/cantidad), 3, '.'));
            calculaTotal();
        }
    }
    
    private void tablaFisico(DefaultTableModel modelo, javax.swing.JTable table, String detalle) {
        ArrayList <Fisico> cargarDatos = new Fisico().obtenerFisico(true, detalle, id);
        modelo = (DefaultTableModel)table.getModel();   
        get.remueveFilas(modelo, table);

        if (cargarDatos != null || !cargarDatos.isEmpty()) {
            new DefaultTableCellRenderer().setHorizontalAlignment(SwingConstants.CENTER);//alinea  en el centro datos de la tabla             
            //datos que se cargaran en la tabla
            Object[] fila = new Object[modelo.getColumnCount()];
            
            for (int i = 0; i < cargarDatos.size(); i++) {
                fila[0] = cargarDatos.get(i).getFisico();
                modelo.addRow(fila);
            }     
            table.setModel(modelo);
        }
    }

    private void tabAlumno(boolean activar) {
        for (int i = 0; i < tbp_Alumno.getComponentCount(); i++) 
            tbp_Alumno.setEnabledAt(i, activar);
        
        tbp_Alumno.setEnabledAt(0, true);
    }
    
    private boolean deporteValido() {
        txt_Establecimiento.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(txt_Establecimiento.getText()), 1));
        txt_DireccionDeporte.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(txt_DireccionDeporte.getText()), 1));
        
        return !txt_Establecimiento.getText().isEmpty() || !txt_DireccionDeporte.getText().isEmpty(); 
    }

    private void ingresarDeporte() {
        if (deporteValido()) {
            if (cargarDeporte().ingresarDeporte(false)) {
                JOptionPane.showMessageDialog(null, "Deporte ingresado correctamente :)","Información :D",JOptionPane.INFORMATION_MESSAGE);
                tablaDeporte();
            }
        }
    }

    private Deporte cargarDeporte() {
        return new Deporte(
            id,
            new Deporte(cmb_Deporte.getSelectedItem().toString()).obtenerDeporte(true).getIdDeporte(),
            txt_Establecimiento.getText(),
            txt_DireccionDeporte.getText(),
            cmb_Nivel.getSelectedItem().toString(),
            spr_Tiempo.getValue() + " " + cmb_Tiempo.getSelectedItem().toString(),
            (int) spr_vecesxSemana.getValue()
        );
    }

    private void cargaCMBDeporte() {
        cmb_Deporte.removeAllItems();
        ArrayList<Deporte> deporte = new Deporte().buscarDeporte(false);
        
        for (int i = 0; i < deporte.size(); i++) 
            cmb_Deporte.addItem(deporte.get(i).getDeporte());
        
        AutoCompleteDecorator.decorate(cmb_Deporte);//Auto Completa en el combobox
        cmb_Deporte.setSelectedIndex(deporte.size() - 1);
    }

    private void tablaDeporte() {
        ArrayList <Deporte> cargarDatos = new Deporte().buscarDeportes(id);
        mDeporte = (DefaultTableModel)tb_Deporte.getModel();   
        get.remueveFilas(mDeporte, tb_Deporte);

        if (cargarDatos != null || !cargarDatos.isEmpty()) {
            new DefaultTableCellRenderer().setHorizontalAlignment(SwingConstants.CENTER);//alinea  en el centro datos de la tabla             
            //datos que se cargaran en la tabla
            Object[] fila = new Object[mDeporte.getColumnCount()];
            
            for (int i = 0; i < cargarDatos.size(); i++) {
                fila[0] = cargarDatos.get(i).getDeporte();
                fila[1] = cargarDatos.get(i).getNivel();
                fila[2] = cargarDatos.get(i).getTiempo();
                fila[3] = cargarDatos.get(i).getCantidad();
                fila[4] = cargarDatos.get(i).getEstablecimiento();
                fila[5] = cargarDatos.get(i).getDireccion();
                mDeporte.addRow(fila);
            }                
            tb_Deporte.setModel(mDeporte);
        }
    }
    
    private boolean personalizadoValido(){
        spr_Triceps.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(Double.valueOf(spr_Triceps.getValue().toString())), 1));
        spr_Subescapular.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(Double.valueOf(spr_Subescapular.getValue().toString())), 1));
        spr_Biceps.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(Double.valueOf(spr_Biceps.getValue().toString())), 1));
        spr_Supraespinal.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(Double.valueOf(spr_Supraespinal.getValue().toString())), 1));
        spr_Abdominal.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(Double.valueOf(spr_Abdominal.getValue().toString())), 1));
        spr_Muslofrontal.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(Double.valueOf(spr_Muslofrontal.getValue().toString())), 1));
        spr_Pantorrilla.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(Double.valueOf(spr_Pantorrilla.getValue().toString())), 1));

        return Double.valueOf(spr_Triceps.getValue().toString()) == 0 || Double.valueOf(spr_Subescapular.getValue().toString()) == 0 ||
               Double.valueOf(spr_Biceps.getValue().toString()) == 0 || Double.valueOf(spr_Supraespinal.getValue().toString()) == 0 ||
               Double.valueOf(spr_Abdominal.getValue().toString()) == 0 || Double.valueOf(spr_Muslofrontal.getValue().toString()) == 0 ||
               Double.valueOf(spr_Pantorrilla.getValue().toString()) == 0 ;
    }

    private void gestionarPersonalizado() {
        if (!personalizadoValido()) {
            if (cargarPersonalizado().gestionarPersonalizado()){
                //Modificamos el idpersonalizado de alumno ya que acaba de crear uno
                new Alumno(new Personalizado().obtenerPersonalizado(), id).modificarPersonalizado(); 
                JOptionPane.showMessageDialog(null, "Datos ingresado correctamente :)", "Información :D", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else
            JOptionPane.showMessageDialog(null, "Algunos campos se encuentran vacios! D:", "Precaución! :D", JOptionPane.WARNING_MESSAGE);
    }
    
    private Personalizado cargarPersonalizado() {
        return new Personalizado(
            id,
            cmb_Sexo.getSelectedItem().toString(), 
            Double.valueOf(spr_Triceps.getValue().toString()),
            Double.valueOf(spr_Subescapular.getValue().toString()),
            Double.valueOf(spr_Biceps.getValue().toString()),
            Double.valueOf(spr_Supraespinal.getValue().toString()),
            Double.valueOf(spr_Abdominal.getValue().toString()),
            Double.valueOf(spr_Muslofrontal.getValue().toString()),
            Double.valueOf(spr_Pantorrilla.getValue().toString())
        );
    }

    private void cargarPersonalizadoDefecto(int idAlumno) {
        //Si el alumno esta en un grupo con clase personalizada
        if (new Personalizado().isPersonalizado(idAlumno) == 1) {
            tbp_Alumno.setEnabledAt(4,true);  
            
            Personalizado perso = new Personalizado().obtenerPersonalizado(idAlumno);
            spr_Triceps.setValue(perso.getTricepts());
            spr_Subescapular.setValue(perso.getSubescapular());
            spr_Biceps.setValue(perso.getBiceps());
            spr_Supraespinal.setValue(perso.getSupraespinal());
            spr_Abdominal.setValue(perso.getAbdominal());
            spr_Muslofrontal.setValue(perso.getMuslofrontal());
            spr_Pantorrilla.setValue(perso.getPantorrilla());
            cmb_Sexo.setSelectedIndex(sexoAlumno(perso.getSexo()));
            calculaDensidad();
        }
        else
            tbp_Alumno.setEnabledAt(4,false);
    }

    private int sexoAlumno(String sexo) {
        switch (sexo) {
            case "Hombre":
                return 0;
            case "Mujer":
                return 1;
            case "Otro":
                return 2;
            default:
                return 3;
        }
    }

    private void eliminarFisico(RSTableMetro tb_Fisico) {
        if (new Fisico(new Fisico(tb_Fisico.getValueAt(tb_Fisico.getSelectedRow(), 0).toString()).buscarFisico(tb_Fisico.getName()).getId()).eliminar(tb_Fisico.getName(), id)) {
            cargarFisico();
        }
    }

    private void eliminarDeporte() {
        if(JOptionPane.showConfirmDialog(null, "¿Seguro que lo desea eliminar?", "Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == 0){
            if (new Deporte(new Deporte(tb_Deporte.getValueAt(tb_Deporte.getSelectedRow(), 0).toString()).buscarDeporte(true).get(0).getIdDeporte(), id).eliminarDeporte()) {
                tablaDeporte();
            }
        }
    }

    private Pago cargarPago(){
        String fechaPago = dc_FechaInicio.getDate().getDate() + "/" + (dc_FechaInicio.getDate().getMonth() + 1) + "/" + new Fecha().getAño(dc_FechaInicio.getDate().getYear());
        Fecha fecha = new Fecha(Integer.valueOf(spr_Meses.getValue().toString()), fechaPago);
        String[] clases = fecha.getClases().split(",");
//        System.out.println("Clases: " + clases[0]);
//        System.out.println("Fecha término: " + clases[1]);
        //Obtenemos la lista completa da las classes por asiste del alumno
        listaClases = fecha.getListaClases();
        
        int agregado = 0, descuento = 0;
        boolean desc = ckb_Porcentaje.isSelected();
        
        if (!txt_Descuento.getText().isEmpty())
            descuento = Integer.valueOf(get.reemplazo(txt_Descuento.getText(), "."));
        
        if (!txt_Agregado.getText().isEmpty()) {
            agregado = Integer.valueOf(get.reemplazo(txt_Agregado.getText(), "."));
            desc = false;
        }

        return new Pago(
                new Alumno(id),                                                              //ID ALUMNO
                new Grupo(cmb_Grupo.getSelectedItem().toString()).buscarGrupos(true).get(0), //ID GRUPO
                agregado,                                                                    //VALOR AGREGADO
                Integer.valueOf(spr_Meses.getValue().toString()),                            //CANTIDAD DE MESES
                descuento,                                                                   //VALOR DEL DESCUENTO
                desc,                                                                        //DESCUENTO EN PORCENTAJE
                dc_FechaInicio.getDate(),                                                    //FECHA DE INICIO
                validar.DateValueOf(clases[1]),                                             //FECHA DE TERMINO
                cmb_FormaPago.getSelectedItem().toString(),                                  //FORMA DE PAGO  
                Integer.valueOf(clases[0]),                                                  //CANTIDAD DE CLASES
                Integer.valueOf(get.reemplazo(txt_Total.getText(), ".")),                    //CANTIDAD TOTAL A PAGAR
                ckb_grupoCompleto.isSelected()                                               //SABER SI TODOS PAGAN EL GRUPO 
        );
    }

    private boolean usuarioVacio() {
        txt_Rut.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(txt_Rut.getText()), 1));
        txt_Nombre.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(txt_Nombre.getText()), 1));
        txt_Apellido.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(txt_Apellido.getText()), 1));
        txt_Direccion.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(txt_Direccion.getText()), 1));
        txt_Correo.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(txt_Correo.getText()), 1));
        spr_Estatura.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(String.valueOf(spr_Estatura.getValue())), 1));
        spr_Peso.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(String.valueOf(spr_Peso.getValue())), 1));
        dc_FechaNacimiento.setBorder(BorderFactory.createLineBorder(validar.textoColorVacio(dc_FechaNacimiento.getDateFormatString()), 1));

        return txt_Rut.getText().isEmpty() || txt_Nombre.getText().isEmpty() || txt_Apellido.getText().isEmpty() || 
               txt_Direccion.getText().isEmpty() || txt_Correo.getText().isEmpty() || 
               spr_Estatura.getValue().equals("") || 
               spr_Peso.getValue().equals("") || 
               dc_FechaNacimiento.getDateFormatString().isEmpty();
    }

    private void ingresarPago() {
        if (validarFechaPago(dc_FechaInicio)) {
            switch (cargarPago().ingresarPago()) {
                case 1://PAGO REALIZADO CORRECTAMETE
                    ingresarAsistencia(new Pago().obtenerUltimoId());
                    JOptionPane.showMessageDialog(null, "Pago realizado correctamente :)","Información :D",JOptionPane.INFORMATION_MESSAGE);
                    tablaPago();
                break;
                //LA FECHA DE PAGO AUN ESTA ACTIVA O EN VIGENCIA   
                case 2:
                    JOptionPane.showMessageDialog(null, "El pago que intenta realizar se encuentra en vigencia D:!","Precaución D:!",JOptionPane.WARNING_MESSAGE);
                break;
                    
                case 3:
                    JOptionPane.showMessageDialog(null, "Actualmente un alumno(s) de este grupo ya realizo un pago anteriormente\n"
                            + "Pero el pago que quiere realizar no coindice con el pago del actual grupo.\n"
                            + "NOTA: Para solucionar esto ingresa el pago con las mismas catasterísticas del anterior pago.\n"
                            + "(Verifica que alumno pago y revisa en la fecha de incio y cuantos meses pago)\n"
                            + "NOTA2: Esto es debido a que al final en la cuadratura final no se vará reflejado la ganancia real del pago de lo alumnos.",
                            "Información :D",JOptionPane.WARNING_MESSAGE);
                break;
                
                case 4:
                    JOptionPane.showMessageDialog(null, "El grupo que intenta pagar no existe, o fue eliminado D:!","Precaución D:!",JOptionPane.WARNING_MESSAGE);
                break;
                
                case 5:
                    JOptionPane.showMessageDialog(null, "El alumno que intenta pagar no existe, o fue eliminado D:!","Precaución D:!",JOptionPane.WARNING_MESSAGE);
                break;
                
                case 6:
                    JOptionPane.showMessageDialog(null, "Todos los alumnos de este grupo ya pagaron este grupo!","Precaución D:!",JOptionPane.WARNING_MESSAGE);
                break;
                    
                case -1:
                    JOptionPane.showMessageDialog(null, "Ocurrió un problema al intentar ingresar, favor intenta más tarde","Precaución D:!",JOptionPane.WARNING_MESSAGE);
                break;
                    
                default:
                    JOptionPane.showMessageDialog(null, "Ocurrio un error en la conexion realizar el pago D:","Error!",JOptionPane.ERROR_MESSAGE);
            }    
        }
    }

    private boolean validarFechaPago(JDateChooser dc) {
        return validar.validarFecha(dc);
    }

    private void tablaPago() {
        ArrayList <Pago> cargarPagos = new Pago(new Alumno(id)).buscarPagos();
        mPago = (DefaultTableModel)tb_Pago.getModel();   
        get.remueveFilas(mPago, tb_Pago);

        if (cargarPagos != null || !cargarPagos.isEmpty()) {
            new DefaultTableCellRenderer().setHorizontalAlignment(SwingConstants.CENTER);//alinea  en el centro datos de la tabla             
            //datos que se cargaran en la tabla
            Object[] fila = new Object[mPago.getColumnCount()];
            
            for (int i = 0; i < cargarPagos.size(); i++) {
                fila[0] = cargarPagos.get(i).getIdPago();                       
                fila[1] = cargarPagos.get(i).getGrupo().getNombre();
                fila[2] = cargarPagos.get(i).getMeses();
                fila[3] = cargarPagos.get(i).getCantidad();
                fila[4] = get.concatenaCaracter(String.valueOf(cargarPagos.get(i).getTotal()), 3, '.') ;
                fila[5] = validar.formatoEspañol(cargarPagos.get(i).getFechaInicio());
                fila[6] = validar.formatoEspañol(cargarPagos.get(i).getFechaTermino());
                fila[7] = cargarPagos.get(i).getFormaPago();
                fila[8] = cargarPagos.get(i).getAgregado();
                fila[9] = cargarPagos.get(i).getDescuento();
                fila[10] = cargarPagos.get(i).isPorcentaje();
                mPago.addRow(fila);
            }                
            tb_Pago.setModel(mPago);
        }
    }

    private void tablaPago(RSTableMetro tb_Pago) {
        tb_Pago.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tb_Pago.doLayout();
        tb_Pago.getColumnModel().getColumn(0).setPreferredWidth(70);  //Nro
        tb_Pago.getColumnModel().getColumn(1).setPreferredWidth(200);  //Grupo
        tb_Pago.getColumnModel().getColumn(2).setPreferredWidth(100);  //Meses
        tb_Pago.getColumnModel().getColumn(3).setPreferredWidth(100);  //Clases
        tb_Pago.getColumnModel().getColumn(4).setPreferredWidth(100);  //TOTAL
        tb_Pago.getColumnModel().getColumn(5).setPreferredWidth(200);  //Fecha Inicio
        tb_Pago.getColumnModel().getColumn(6).setPreferredWidth(200);  //Fecha Término
        tb_Pago.getColumnModel().getColumn(7).setPreferredWidth(180);  //FORMA DE PAGO
        tb_Pago.getColumnModel().getColumn(8).setPreferredWidth(150);  //AGREGADO
        tb_Pago.getColumnModel().getColumn(9).setPreferredWidth(150);  //DESCUENTO
        tb_Pago.getColumnModel().getColumn(10).setPreferredWidth(150);  //PORCENTAJE
    }

    private void ingresarAsistencia(int idPago) {
        for (int i = 0; i < listaClases.size(); i++) 
            cargarAsistencia(idPago, i).ingresarAsistencia();
    }

    private ControlAsistencia cargarAsistencia(int idPago, int i) {
        return new ControlAsistencia(idPago, listaClases.get(i), false);
    }
}
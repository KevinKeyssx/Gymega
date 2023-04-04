package Interfaz;

import Clases.HuellaDigital;
import Extra.Obtener;
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
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusListener;
import com.digitalpersona.onetouch.capture.event.DPFPSensorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPSensorEvent;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import com.digitalpersona.onetouch.verification.DPFPVerification;
import com.digitalpersona.onetouch.verification.DPFPVerificationResult;
import java.awt.HeadlessException;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

/**
 * @author keyss
 */

public final class IDHuellaDigital extends javax.swing.JDialog {
//    Variables que ayudan a capturar, enrolar y verificar
    private DPFPCapture Lector = DPFPGlobal.getCaptureFactory().createCapture();
    private DPFPEnrollment Reclutador = DPFPGlobal.getEnrollmentFactory().createEnrollment();
    private DPFPVerification Verificador = DPFPGlobal.getVerificationFactory().createVerification();
    private DPFPTemplate template;
    public static String TEMPLATE_PROPERTY = "template";
    public DPFPFeatureSet featureinscripcion;
    public DPFPFeatureSet featureverificacion;   
    private final int id;
    private final boolean persona;
    Obtener get = new Obtener();
    
    //javax.swing.JFrame padre;
    
    public IDHuellaDigital(javax.swing.JDialog parent, boolean modal, int idPersona, boolean persona) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        cargaFoto(System.getProperty("user.dir") + "\\src\\Complementos\\Huella.gif");
        id = idPersona;
        this.persona = persona;
    }
    
//    public void stopMenu(){
//        padre.getClass().notify();
//    }
    
//    public IDHuellaDigital(javax.swing.JDialog parent, boolean modal) {
//        super(parent, modal);
//        initComponents();
//        setLocationRelativeTo(null);
//        cargaFoto(System.getProperty("user.dir") + "\\src\\Complementos\\Huella.gif");
//    }
//    
    protected void Iniciar(){
        Lector.addDataListener(new DPFPDataAdapter(){
            @Override
            public void dataAcquired(final DPFPDataEvent e){
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        EnviarTexto("Huella Capturada correctamente", false);
                        procesarCaptura(e.getSample());
                    }
                });
            }
        });
        
        Lector.addReaderStatusListener(new DPFPReaderStatusAdapter() {

            @Override
            public void readerConnected(DPFPReaderStatusEvent dpfprs) {
                SwingUtilities.invokeLater(() -> {
                    cambiaCirculo(1);
                    EnviarTexto("***Sensor de Huella Activado***", false);    
                });
            }

            @Override
            public void readerDisconnected(DPFPReaderStatusEvent dpfprs) {
                SwingUtilities.invokeLater(() -> {
                    txtArea.setText("");
                    cambiaNivel(-1);
                    cambiaCirculo(3);
                    EnviarTexto("---Sensor de Huella desactivado---", true);
                });
            }
        });
        
        Lector.addSensorListener(new DPFPSensorAdapter(){
            @Override
            public void fingerTouched(final DPFPSensorEvent e){
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        cambiaCirculo(2);
                        EnviarTexto("El dedo ha sido colocado sobre el lector de huella", false);
                    }
                });
            }
            
            public void fingerGone(final DPFPSensorAdapter e){
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        cambiaCirculo(3);
                        EnviarTexto("El dedo ha sido quitado del lector de huella", true);
                    }
                });
            }
        });
        
        Lector.addErrorListener(new DPFPErrorAdapter(){
            public void errorReader(final DPFPErrorEvent e){
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        cambiaCirculo(3);
                        EnviarTexto("Error: "+e.getError(), true);
                    }
                });
            }
        }); 
    }
    
    public DPFPFeatureSet extraerCaracteristicas(DPFPSample sample, DPFPDataPurpose purpose){
        DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
        
        try {return extractor.createFeatureSet(sample, purpose);} 
        catch (DPFPImageQualityException e) {return null;}
    }
    
    public void procesarCaptura(DPFPSample sample){
        featureinscripcion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);
        featureverificacion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);

        if (dedoSeleccionado()) {
            if (!verificarHuella()) {
                if(featureinscripcion != null){
                    try {
                        if (Reclutador.getFeaturesNeeded() > 1) 
                            get.reproducirSonido("Exitoso");

                        radioSeleccionado(grupoDedos, false);
                        cambiaCirculo(1);
                        lbl_Foto.setText("");
                        Reclutador.addFeatures(featureinscripcion);
                        Image image = CrearImagenHuella(sample);
                        DibujarHuella(image);
                    } catch (DPFPImageQualityException ex) {
                        get.reproducirSonido("Denegado");
                        cambiaCirculo(3);
                        EnviarTexto("Se ha producido un error! " + ex.getMessage(), true);
                    }finally{
                        EstadoHuellas();

                        switch(Reclutador.getTemplateStatus()){
                            case TEMPLATE_STATUS_READY:
                                stop();
                                get.reproducirSonido("Listo");
                                setTemplate(Reclutador.getTemplate());
                                EnviarTexto("***La plantilla de la huella ha sido creada, ya puede registrarla***", false);
                                btn_Aceptar.grabFocus();
                                btn_Aceptar.setEnabled(true);
                                btn_Volver.setEnabled(true);
                                break;
                            case TEMPLATE_STATUS_FAILED:
                                reiniciarHuella();
                                JOptionPane.showMessageDialog(null, "No se pudo registrar la huella! :(","Error! x.x",JOptionPane.ERROR_MESSAGE);
                                EnviarTexto("---Error al crear huella---", true);
                                break;
                        }
                    }
                }
            
            }else{
                EnviarTexto("---La huella ya se encuentra registrada, favor ingresa otra---", true);
                get.reproducirSonido("Denegado");
                reiniciarHuella();
            }
        }
    }   
    
    public Image CrearImagenHuella(DPFPSample sample){
        return DPFPGlobal.getSampleConversionFactory().createImage(sample);
    }
    
    public void DibujarHuella(Image image){
        lbl_Foto.setIcon(new ImageIcon(image.getScaledInstance(lbl_Foto.getWidth(), lbl_Foto.getHeight(), Image.SCALE_DEFAULT)));
        repaint();
    }
    
    public void EstadoHuellas(){
        EnviarTexto("*Intentos restantes para guardar huella: " + Reclutador.getFeaturesNeeded(), false);
        cambiaNivel(Reclutador.getFeaturesNeeded());
    }
    
    public void EnviarTexto(String string, boolean error){
        if (error) 
            txtArea.setForeground(new java.awt.Color(255, 0, 0)); 
        else
            txtArea.setForeground(new java.awt.Color(0,204,255)); 
        
        txtArea.append(string + "\n");
    }
    
    public void start(){
        try {Lector.startCapture();} 
        catch (Exception e) {
            cambiaCirculo(3);
            EnviarTexto("Error, no se ha completado correctamente la secuencia " + e.getMessage(), true);
        }
    }
    
    public void stop(){
        Lector.stopCapture();
    }
    
    public DPFPTemplate getTemplate(){
        return template;
    }
    
    public void setTemplate(DPFPTemplate template){
        DPFPTemplate old = this.template;
        this.template = template;
        firePropertyChange(TEMPLATE_PROPERTY, old, template);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoDedos = new javax.swing.ButtonGroup();
        lbl_Logo = new javax.swing.JLabel();
        pnl_Central = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        panel_Niveles = new javax.swing.JLayeredPane();
        lbl_Nivel1 = new javax.swing.JLabel();
        lbl_Nivel2 = new javax.swing.JLabel();
        lbl_Nivel3 = new javax.swing.JLabel();
        lbl_Nivel4 = new javax.swing.JLabel();
        lbl_Nivel6 = new javax.swing.JLabel();
        lbl_Nivel5 = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        lbl_Verde = new javax.swing.JLabel();
        lbl_Rojo = new javax.swing.JLabel();
        lbl_Azul = new javax.swing.JLabel();
        lbl_Foto = new com.bolivia.label.CLabel();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        ckb_MeñiqueI = new javax.swing.JRadioButton();
        ckb_AnularI = new javax.swing.JRadioButton();
        ckb_MedioI = new javax.swing.JRadioButton();
        ckb_IndiceI = new javax.swing.JRadioButton();
        ckb_PulgarI = new javax.swing.JRadioButton();
        ckb_PulgarD = new javax.swing.JRadioButton();
        ckb_IndiceD = new javax.swing.JRadioButton();
        ckb_MedioD = new javax.swing.JRadioButton();
        ckb_AnularD = new javax.swing.JRadioButton();
        ckb_MeñiqueD = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        btn_Limpiar = new javax.swing.JButton();
        btn_Volver = new javax.swing.JButton();
        lbl_Titulo = new javax.swing.JLabel();
        btn_Aceptar = new javax.swing.JButton();
        btn_Reiniciar = new javax.swing.JButton();
        lbl_background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/1.png"))); // NOI18N
        getContentPane().add(lbl_Logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 10, -1, -1));

        pnl_Central.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnl_Central.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtArea.setEditable(false);
        txtArea.setColumns(20);
        txtArea.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        txtArea.setForeground(new java.awt.Color(0, 204, 255));
        txtArea.setRows(5);
        txtArea.setSelectedTextColor(new java.awt.Color(255, 0, 0));
        txtArea.setSelectionColor(new java.awt.Color(255, 255, 0));
        jScrollPane1.setViewportView(txtArea);

        pnl_Central.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, 650, 90));

        panel_Niveles.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Nivel", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Mairy Oblicua", 0, 18), new java.awt.Color(0, 204, 255))); // NOI18N

        lbl_Nivel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Nivel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lbl_Nivel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        lbl_Nivel1.setOpaque(true);
        panel_Niveles.add(lbl_Nivel1);
        lbl_Nivel1.setBounds(10, 180, 90, 30);

        lbl_Nivel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Nivel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lbl_Nivel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        lbl_Nivel2.setOpaque(true);
        panel_Niveles.add(lbl_Nivel2);
        lbl_Nivel2.setBounds(10, 150, 90, 30);

        lbl_Nivel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Nivel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lbl_Nivel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        lbl_Nivel3.setOpaque(true);
        panel_Niveles.add(lbl_Nivel3);
        lbl_Nivel3.setBounds(10, 120, 90, 30);

        lbl_Nivel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Nivel4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lbl_Nivel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        lbl_Nivel4.setOpaque(true);
        panel_Niveles.add(lbl_Nivel4);
        lbl_Nivel4.setBounds(10, 90, 90, 30);

        lbl_Nivel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Nivel6.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lbl_Nivel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        lbl_Nivel6.setOpaque(true);
        panel_Niveles.add(lbl_Nivel6);
        lbl_Nivel6.setBounds(10, 30, 90, 30);

        lbl_Nivel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Nivel5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lbl_Nivel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        lbl_Nivel5.setOpaque(true);
        panel_Niveles.add(lbl_Nivel5);
        lbl_Nivel5.setBounds(10, 60, 90, 30);

        pnl_Central.add(panel_Niveles, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 110, 230));

        jLayeredPane1.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Huella Digital", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Mairy Oblicua", 0, 18), new java.awt.Color(0, 204, 255))); // NOI18N
        jLayeredPane1.setOpaque(true);

        lbl_Verde.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Circulo Verde Normal.png"))); // NOI18N
        lbl_Verde.setEnabled(false);
        jLayeredPane1.add(lbl_Verde);
        lbl_Verde.setBounds(130, 210, 32, 32);

        lbl_Rojo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Circulo Rojo Normal.png"))); // NOI18N
        lbl_Rojo.setEnabled(false);
        jLayeredPane1.add(lbl_Rojo);
        lbl_Rojo.setBounds(170, 210, 32, 32);

        lbl_Azul.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Circulo Azul Normal.png"))); // NOI18N
        lbl_Azul.setEnabled(false);
        jLayeredPane1.add(lbl_Azul);
        lbl_Azul.setBounds(90, 210, 40, 32);

        lbl_Foto.setBackground(new java.awt.Color(255, 255, 255));
        lbl_Foto.setForeground(new java.awt.Color(255, 255, 255));
        lbl_Foto.setText("Leer Huella");
        lbl_Foto.setToolTipText("");
        lbl_Foto.setFont(new java.awt.Font("Mairy Oblicua", 0, 18)); // NOI18N
        lbl_Foto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbl_Foto.setLineBorder(5);
        lbl_Foto.setLineColor(new java.awt.Color(51, 153, 255));
        lbl_Foto.setOpaque(false);
        jLayeredPane1.add(lbl_Foto);
        lbl_Foto.setBounds(40, 30, 210, 200);

        pnl_Central.add(jLayeredPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 290, 250));

        jLayeredPane2.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Seleccionar Dedo", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Mairy Oblicua", 0, 18), new java.awt.Color(0, 204, 255))); // NOI18N

        grupoDedos.add(ckb_MeñiqueI);
        ckb_MeñiqueI.setToolTipText("Meñique Izquierdo");
        ckb_MeñiqueI.setOpaque(false);
        ckb_MeñiqueI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckb_MeñiqueIActionPerformed(evt);
            }
        });
        jLayeredPane2.add(ckb_MeñiqueI);
        ckb_MeñiqueI.setBounds(30, 60, 21, 21);

        grupoDedos.add(ckb_AnularI);
        ckb_AnularI.setToolTipText("Anular Izquierdo");
        ckb_AnularI.setOpaque(false);
        ckb_AnularI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckb_AnularIActionPerformed(evt);
            }
        });
        jLayeredPane2.add(ckb_AnularI);
        ckb_AnularI.setBounds(50, 50, 21, 21);

        grupoDedos.add(ckb_MedioI);
        ckb_MedioI.setToolTipText("Medio Izquierdo");
        ckb_MedioI.setOpaque(false);
        ckb_MedioI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckb_MedioIActionPerformed(evt);
            }
        });
        jLayeredPane2.add(ckb_MedioI);
        ckb_MedioI.setBounds(70, 40, 40, 21);

        grupoDedos.add(ckb_IndiceI);
        ckb_IndiceI.setToolTipText("Indice Izquierdo");
        ckb_IndiceI.setName(""); // NOI18N
        ckb_IndiceI.setOpaque(false);
        ckb_IndiceI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckb_IndiceIActionPerformed(evt);
            }
        });
        jLayeredPane2.add(ckb_IndiceI);
        ckb_IndiceI.setBounds(90, 50, 21, 21);

        grupoDedos.add(ckb_PulgarI);
        ckb_PulgarI.setToolTipText("Pulgar Izquierdo");
        ckb_PulgarI.setOpaque(false);
        ckb_PulgarI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckb_PulgarIActionPerformed(evt);
            }
        });
        jLayeredPane2.add(ckb_PulgarI);
        ckb_PulgarI.setBounds(110, 100, 21, 21);

        grupoDedos.add(ckb_PulgarD);
        ckb_PulgarD.setToolTipText("Pulgar Derecho");
        ckb_PulgarD.setOpaque(false);
        ckb_PulgarD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckb_PulgarDActionPerformed(evt);
            }
        });
        jLayeredPane2.add(ckb_PulgarD);
        ckb_PulgarD.setBounds(130, 100, 21, 21);

        grupoDedos.add(ckb_IndiceD);
        ckb_IndiceD.setToolTipText("Indice Derecho");
        ckb_IndiceD.setOpaque(false);
        ckb_IndiceD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckb_IndiceDActionPerformed(evt);
            }
        });
        jLayeredPane2.add(ckb_IndiceD);
        ckb_IndiceD.setBounds(150, 50, 21, 21);

        grupoDedos.add(ckb_MedioD);
        ckb_MedioD.setToolTipText("Medio Derecho");
        ckb_MedioD.setOpaque(false);
        ckb_MedioD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckb_MedioDActionPerformed(evt);
            }
        });
        jLayeredPane2.add(ckb_MedioD);
        ckb_MedioD.setBounds(170, 40, 30, 21);

        grupoDedos.add(ckb_AnularD);
        ckb_AnularD.setToolTipText("Anular Derecho");
        ckb_AnularD.setOpaque(false);
        ckb_AnularD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckb_AnularDActionPerformed(evt);
            }
        });
        jLayeredPane2.add(ckb_AnularD);
        ckb_AnularD.setBounds(190, 50, 20, 21);

        grupoDedos.add(ckb_MeñiqueD);
        ckb_MeñiqueD.setToolTipText("Meñique Derecho");
        ckb_MeñiqueD.setOpaque(false);
        ckb_MeñiqueD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckb_MeñiqueDActionPerformed(evt);
            }
        });
        jLayeredPane2.add(ckb_MeñiqueD);
        ckb_MeñiqueD.setBounds(210, 60, 21, 21);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Manos/Manos ID Huella Digital.png"))); // NOI18N
        jLayeredPane2.add(jLabel1);
        jLabel1.setBounds(20, 60, 210, 140);

        pnl_Central.add(jLayeredPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, 260, 230));

        btn_Limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Botones/Btn-Limpiar.png"))); // NOI18N
        btn_Limpiar.setBorderPainted(false);
        btn_Limpiar.setContentAreaFilled(false);
        btn_Limpiar.setFocusPainted(false);
        btn_Limpiar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Botones/Btn-LimpiarMouse.png"))); // NOI18N
        btn_Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LimpiarActionPerformed(evt);
            }
        });
        pnl_Central.add(btn_Limpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 350, 40, 40));

        getContentPane().add(pnl_Central, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 770, 430));

        btn_Volver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Volver.png"))); // NOI18N
        btn_Volver.setBorderPainted(false);
        btn_Volver.setContentAreaFilled(false);
        btn_Volver.setEnabled(false);
        btn_Volver.setFocusPainted(false);
        btn_Volver.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Volver Mouse.png"))); // NOI18N
        btn_Volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_VolverActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Volver, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 550, 90, 60));

        lbl_Titulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Identificación de Huella Digital.png"))); // NOI18N
        getContentPane().add(lbl_Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, 50));

        btn_Aceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Aceptar.png"))); // NOI18N
        btn_Aceptar.setBorderPainted(false);
        btn_Aceptar.setContentAreaFilled(false);
        btn_Aceptar.setEnabled(false);
        btn_Aceptar.setFocusPainted(false);
        btn_Aceptar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Aceptar Press2.png"))); // NOI18N
        btn_Aceptar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Aceptar Mouse.png"))); // NOI18N
        btn_Aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AceptarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 550, 180, 60));

        btn_Reiniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Reiniciar Normal.png"))); // NOI18N
        btn_Reiniciar.setToolTipText("Reiniciar capturas de huellas...");
        btn_Reiniciar.setBorderPainted(false);
        btn_Reiniciar.setContentAreaFilled(false);
        btn_Reiniciar.setFocusPainted(false);
        btn_Reiniciar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Reiniciar Mouse.png"))); // NOI18N
        btn_Reiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ReiniciarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Reiniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 550, 60, 60));

        lbl_background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Menu.jpg"))); // NOI18N
        lbl_background.setText("jLabel3");
        getContentPane().add(lbl_background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 630));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_VolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_VolverActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btn_VolverActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
//        new Menu().stop();
        Iniciar();
        start();
        EstadoHuellas();
        
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        stop();
//        new Menu().start();
    }//GEN-LAST:event_formWindowClosed

    private void ckb_MeñiqueDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckb_MeñiqueDActionPerformed
        EnviarTexto("*Se ha seleccionado el dedo Meñique Derecho", false);
        cambiaCirculo(1);
    }//GEN-LAST:event_ckb_MeñiqueDActionPerformed

    private void btn_AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AceptarActionPerformed
        ingresarHuellaDigital();
    }//GEN-LAST:event_btn_AceptarActionPerformed

    private void ckb_IndiceDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckb_IndiceDActionPerformed
        EnviarTexto("*Se ha seleccionado el dedo Indice Derecho", false);
        cambiaCirculo(1);
    }//GEN-LAST:event_ckb_IndiceDActionPerformed

    private void ckb_MedioDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckb_MedioDActionPerformed
        EnviarTexto("*Se ha seleccionado el dedo Medio Derecho", false);
        cambiaCirculo(1);
    }//GEN-LAST:event_ckb_MedioDActionPerformed

    private void ckb_AnularDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckb_AnularDActionPerformed
        EnviarTexto("*Se ha seleccionado el dedo Anular Derecho", false);
        cambiaCirculo(1);
    }//GEN-LAST:event_ckb_AnularDActionPerformed

    private void ckb_PulgarDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckb_PulgarDActionPerformed
        EnviarTexto("*Se ha seleccionado el dedo Pulgar Derecho", false);
        cambiaCirculo(1);
    }//GEN-LAST:event_ckb_PulgarDActionPerformed

    private void ckb_PulgarIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckb_PulgarIActionPerformed
        EnviarTexto("*Se ha seleccionado el dedo Pulgar Izquierdo", false);
        cambiaCirculo(1);
    }//GEN-LAST:event_ckb_PulgarIActionPerformed

    private void ckb_IndiceIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckb_IndiceIActionPerformed
        EnviarTexto("*Se ha seleccionado el dedo Indice Izquierdo", false);
        cambiaCirculo(1);
    }//GEN-LAST:event_ckb_IndiceIActionPerformed

    private void ckb_MedioIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckb_MedioIActionPerformed
        EnviarTexto("*Se ha seleccionado el dedo Medio Izquierdo", false);
        cambiaCirculo(1);
    }//GEN-LAST:event_ckb_MedioIActionPerformed

    private void ckb_AnularIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckb_AnularIActionPerformed
        EnviarTexto("*Se ha seleccionado el dedo Anular Izquierdo", false);
        cambiaCirculo(1);
    }//GEN-LAST:event_ckb_AnularIActionPerformed

    private void ckb_MeñiqueIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckb_MeñiqueIActionPerformed
        EnviarTexto("*Se ha seleccionado el dedo Meñique Izquierdo", false);
        cambiaCirculo(1);
    }//GEN-LAST:event_ckb_MeñiqueIActionPerformed

    private void btn_ReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ReiniciarActionPerformed
        reiniciarHuella();
    }//GEN-LAST:event_btn_ReiniciarActionPerformed

    private void btn_LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LimpiarActionPerformed
        txtArea.setText("");
        EnviarTexto("--Limpieza realizada--", true);
    }//GEN-LAST:event_btn_LimpiarActionPerformed

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
            java.util.logging.Logger.getLogger(IDHuellaDigital.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IDHuellaDigital.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IDHuellaDigital.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IDHuellaDigital.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                IDHuellaDigital dialog = new IDHuellaDigital(new javax.swing.JDialog(), true, 0, true);
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
    private javax.swing.JButton btn_Limpiar;
    private javax.swing.JButton btn_Reiniciar;
    private javax.swing.JButton btn_Volver;
    private javax.swing.JRadioButton ckb_AnularD;
    private javax.swing.JRadioButton ckb_AnularI;
    private javax.swing.JRadioButton ckb_IndiceD;
    private javax.swing.JRadioButton ckb_IndiceI;
    private javax.swing.JRadioButton ckb_MedioD;
    private javax.swing.JRadioButton ckb_MedioI;
    private javax.swing.JRadioButton ckb_MeñiqueD;
    private javax.swing.JRadioButton ckb_MeñiqueI;
    private javax.swing.JRadioButton ckb_PulgarD;
    private javax.swing.JRadioButton ckb_PulgarI;
    private javax.swing.ButtonGroup grupoDedos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_Azul;
    private com.bolivia.label.CLabel lbl_Foto;
    private javax.swing.JLabel lbl_Logo;
    private javax.swing.JLabel lbl_Nivel1;
    private javax.swing.JLabel lbl_Nivel2;
    private javax.swing.JLabel lbl_Nivel3;
    private javax.swing.JLabel lbl_Nivel4;
    private javax.swing.JLabel lbl_Nivel5;
    private javax.swing.JLabel lbl_Nivel6;
    private javax.swing.JLabel lbl_Rojo;
    private javax.swing.JLabel lbl_Titulo;
    private javax.swing.JLabel lbl_Verde;
    private javax.swing.JLabel lbl_background;
    private javax.swing.JLayeredPane panel_Niveles;
    private javax.swing.JLayeredPane pnl_Central;
    private javax.swing.JTextArea txtArea;
    // End of variables declaration//GEN-END:variables

    private void cargaFoto(String ruta) {
        ImageIcon imagen = new ImageIcon(ruta); 
        lbl_Foto.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(lbl_Foto.getWidth(),lbl_Foto.getHeight(),Image.SCALE_DEFAULT)));
        repaint();
    }   
    
    private void cambiaNivel(int nivel){
        switch (nivel) {
            case 3: //Nivel 1
                lbl_Nivel1.setBackground(new java.awt.Color(230, 255, 230));
                lbl_Nivel2.setBackground(new java.awt.Color(192, 255, 192));
                break;
            case 2: //Nivel 2
                lbl_Nivel3.setBackground(new java.awt.Color(141, 255, 141));
                lbl_Nivel4.setBackground(new java.awt.Color(83, 255, 84));
                break;
            case 1: //Nivel 3
                lbl_Nivel5.setBackground(new java.awt.Color(55, 255, 55));
                lbl_Nivel6.setBackground(new java.awt.Color(0, 255, 0));
                break;
            case 0: //Nivel 4
                lbl_Nivel1.setBackground(new java.awt.Color(0, 255, 0));
                lbl_Nivel2.setBackground(new java.awt.Color(0, 255, 0));
                lbl_Nivel3.setBackground(new java.awt.Color(0, 255, 0));
                lbl_Nivel4.setBackground(new java.awt.Color(0, 255, 0));
                lbl_Nivel5.setBackground(new java.awt.Color(0, 255, 0));
                lbl_Nivel6.setBackground(new java.awt.Color(0, 255, 0));
                break;
            default:
                lbl_Nivel1.setBackground(null);
                lbl_Nivel2.setBackground(null);
                lbl_Nivel3.setBackground(null);
                lbl_Nivel4.setBackground(null);
                lbl_Nivel5.setBackground(null);
                lbl_Nivel6.setBackground(null);
                break;
        }
    }

    private void cambiaCirculo(int color){
        switch (color) {
            case 1: //DISPONIBLE
                lbl_Azul.setEnabled(true);
                lbl_Verde.setEnabled(false);
                lbl_Rojo.setEnabled(false);
                break;
            case 2: //EXITOSO
                lbl_Azul.setEnabled(false);
                lbl_Verde.setEnabled(true);
                lbl_Rojo.setEnabled(false);
                break;
            case 3: //FALLA
                lbl_Azul.setEnabled(false);
                lbl_Verde.setEnabled(false);
                lbl_Rojo.setEnabled(true);
                break;
            default: //REINICIAR
                lbl_Azul.setEnabled(false);
                lbl_Verde.setEnabled(false);
                lbl_Rojo.setEnabled(false);
                break;
        }
    }
    
    private boolean dedoSeleccionado(){
        if (ckb_MeñiqueI.isSelected() || ckb_AnularI.isSelected()  || ckb_MedioI.isSelected()  || 
                ckb_IndiceI.isSelected()  || ckb_PulgarI.isSelected() || ckb_PulgarD.isSelected()  || ckb_IndiceD.isSelected() ||
                ckb_MedioD.isSelected() || ckb_AnularD.isSelected()  || ckb_MeñiqueD.isSelected()) {
            
            cambiaCirculo(2);
            return true;
        }
        else{
            cambiaCirculo(3);
            EnviarTexto("Favor selecciona un dedo a identificar", true);
            return false;
        }
    }

    private void ingresarHuellaDigital() {
        switch (cargarHuellaDigital().ingresar(!persona ?  "Alumno" : "PT")) {
            case 1:
                JOptionPane.showMessageDialog(null, "Huella Digital registrada correctamente! :)","Información! :D",JOptionPane.INFORMATION_MESSAGE);
                reiniciarHuella();
            break;
            
            case 2:
                JOptionPane.showMessageDialog(null, "La huella digital ingresada ya se encuentra registrada ):","Precaución! D:",JOptionPane.WARNING_MESSAGE);
                reiniciarHuella();
            break;
                
            case 3:
                JOptionPane.showMessageDialog(null, "La persona ya tiene 3 huellas ingresadas, elimine una para crear una nueva","Precaución! D:",JOptionPane.WARNING_MESSAGE);
                reiniciarHuella();
            break;
                
            case 4:
                JOptionPane.showMessageDialog(null, "La persona ingresada no existe!","Precaución! D:",JOptionPane.WARNING_MESSAGE);
                reiniciarHuella();
            break;
        }
    }

    private HuellaDigital cargarHuellaDigital() {
        HuellaDigital huella = new HuellaDigital();
        huella.setIdUser(id);
        huella.setNombre(radioSeleccionado(grupoDedos, false).getToolTipText());
        huella.setHuella(new ByteArrayInputStream(template.serialize()));
        huella.setTamañoHuella(template.serialize().length);
        return huella;
    }
    
    private JRadioButton radioSeleccionado(ButtonGroup group, boolean habilita) {
        JRadioButton radio = null;
        for (Enumeration e = group.getElements(); e.hasMoreElements(); ){
            JRadioButton b = (JRadioButton)e.nextElement();
            
            if (b.getModel() == group.getSelection()){
                b.setEnabled(habilita);
                radio = b;
            }
            else
                b.setEnabled(habilita);
        }

        return radio;
    }

    private void reiniciarHuella() {
        btn_Aceptar.setEnabled(false);
        btn_Volver.setEnabled(false);
        Reclutador.clear();
        stop();
        EstadoHuellas();
        cambiaNivel(-1);
        setTemplate(null);
        cargaFoto(System.getProperty("user.dir") + "\\src\\Complementos\\Huella.gif");
        lbl_Foto.setText("Leer Huella");
        radioSeleccionado(grupoDedos, true);
        start();
    }
        
    private boolean verificarHuella() {
        ArrayList<HuellaDigital> huellaDigital = new HuellaDigital().obtenerHuellaDigital(persona? "PersonalTraining" : "Alumno");
        boolean existe = false;
        
        try {
            for (int i = 0; i < huellaDigital.size(); i++) {
                DPFPTemplate referenceTemplate = DPFPGlobal.getTemplateFactory().createTemplate((byte[]) huellaDigital.get(i).getHuella());
                setTemplate(referenceTemplate);
                
                DPFPVerificationResult resul = Verificador.verify(featureverificacion, getTemplate());

                if (resul.isVerified()) 
                    existe = true;
            }

        } catch (IllegalArgumentException | HeadlessException e) {
             JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado :(!\n" + e.getMessage(),"Error! x.x",JOptionPane.ERROR_MESSAGE);
             existe = false;
        }
        return existe;
    }
    
}

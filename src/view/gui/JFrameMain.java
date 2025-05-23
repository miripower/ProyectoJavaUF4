/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.gui;

import enums.*;
import exceptions.DniInvalidoException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import model.*;
import model.validations.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author polmi
 */
public class JFrameMain extends javax.swing.JFrame {

    private Avion avion;
    private Vuelo vuelo;
    private List<Asiento> asientos;
    double precioBase;

    /**
     * Creates new form JFrameMain
     */
    public JFrameMain() {
        initComponents();
        cargarComboBoxOrigenDestino();
        InicioApp();
        cargarComboBoxClaseAsiento();
        crearAsientosYVuelo();
    }

    public void enviarDatosUsuario() {
        try {
            // Crear el objeto JSON con los datos
            String jsonInputString = String.format(
                    "{"
                    + "\"nombre\":\"%s\","
                    + "\"apellidos\":\"%s\","
                    + "\"email\":\"%s\","
                    + "\"dni\":\"%s\","
                    + "\"telefono\":\"%s\","
                    + "\"direccion\":\"%s\""
                    + "}",
                    jTextFieldNombre.getText(),
                    jTextFieldApellido.getText(),
                    jTextFieldEmail.getText(),
                    jTextFieldDni.getText(),
                    jTextFieldTelefono.getText(),
                    jTextFieldDireccion.getText()
            );

            // Crear conexión
            URL url = new URL("http://localhost:8080/api/usuarios");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setDoOutput(true);

            // Enviar datos
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
                JOptionPane.showMessageDialog(null, "Usuario creado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error al crear usuario. Código: " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    private void crearAsientosYVuelo() {
        asientos = new ArrayList<>();
//        asientos = DataClass.asientos;
        Random random = new Random();

        // Crear avión
        avion = new Avion(1, "Boeing 737", 15);

        // Crear vuelo (sin origen/destino vuelo = new Vuelo(1, null, null, null, avion);
        // Crear 5 asientos económicos
        for (int i = 1; i <= 5; i++) {
            asientos.add(new Asiento(i, ClaseAsiento.ECONOMICA, random.nextBoolean()));
        }

        // Crear 5 asientos premium
        for (int i = 6; i <= 10; i++) {
            asientos.add(new Asiento(i, ClaseAsiento.PREMIUM, random.nextBoolean()));
        }

        // Crear 5 asientos primera clase
        for (int i = 11; i <= 15; i++) {
            asientos.add(new Asiento(i, ClaseAsiento.PRIMERA_CLASE, random.nextBoolean()));
        }
        System.out.println("Asientos jframemain: " + asientos);
        vuelo = new Vuelo(1, null, null, null, avion, asientos);
        System.out.println(vuelo);
    }

//    public List<Integer> obtenerAsientosDisponibles(ClaseAsiento clase) {
//        List<Integer> asientosDisponibles = new ArrayList<>();
//        for (Asiento asiento : vuelo.getAsientos()) {
//            if (asiento.getClase() == clase && !asiento.isOcupado()) {
//                asientosDisponibles.add(asiento.getNumAsiento());
//            }
//        }
//        return asientosDisponibles;
//    }
    private void cargarComboBoxOrigenDestino() {
        jComboBoxOrigen.setModel(new javax.swing.DefaultComboBoxModel<>(convertirEnumAString(OrigenDestino.values())));
        jComboBoxDestino.setModel(new javax.swing.DefaultComboBoxModel<>(convertirEnumAString(OrigenDestino.values())));
    }

    private void cargarComboBoxClaseAsiento() {
        jComboBoxClaseAsiento.setModel(new javax.swing.DefaultComboBoxModel<>(convertirEnumAStringClase(ClaseAsiento.values())));
    }

    private String[] convertirEnumAString(OrigenDestino[] origenDestinos) {
        String[] nombres = new String[origenDestinos.length];
        for (int i = 0; i < origenDestinos.length; i++) {
            nombres[i] = origenDestinos[i].name();
        }
        return nombres;
    }

    private String[] convertirEnumAStringClase(ClaseAsiento[] claseAsientos) {
        String[] clases = new String[claseAsientos.length];
        for (int i = 0; i < claseAsientos.length; i++) {
            clases[i] = claseAsientos[i].name();
        }
        return clases;
    }

    private void InicioApp() {
        jComboBoxClaseAsiento.setEnabled(false);
        jTextFieldNombre.setEnabled(false);
        jTextFieldApellido.setEnabled(false);
        jTextFieldEmail.setEnabled(false);
        jTextFieldDni.setEnabled(false);
        jTextFieldTelefono.setEnabled(false);
        jTextFieldDireccion.setEnabled(false);
        jButtonVerificarDatos.setEnabled(false);
        jButtonSiguiente.setEnabled(false);
        jTextFieldPrecio.setEditable(false);
        jTextFieldPrecio.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jLabelTitle1 = new javax.swing.JLabel();
        jLabelIcon1 = new javax.swing.JLabel();
        jLabelTitle = new javax.swing.JLabel();
        jLabelIcon = new javax.swing.JLabel();
        jLabelOrigen = new javax.swing.JLabel();
        jComboBoxOrigen = new javax.swing.JComboBox<>();
        jLabelDestino = new javax.swing.JLabel();
        jComboBoxDestino = new javax.swing.JComboBox<>();
        jButtonConfirmarOrigenDestino = new javax.swing.JButton();
        jLabelClaseAsiento = new javax.swing.JLabel();
        jComboBoxClaseAsiento = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldNombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldApellido = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldDni = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldTelefono = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldDireccion = new javax.swing.JTextField();
        jButtonVerificarDatos = new javax.swing.JButton();
        jButtonClear = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldPrecio = new javax.swing.JTextField();
        jButtonSiguiente = new javax.swing.JButton();

        jFrame1.setPreferredSize(new java.awt.Dimension(621, 566));

        jLabelTitle1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabelTitle1.setText("Monlau Airlines");

        jLabelIcon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/images/monlau.png"))); // NOI18N

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelIcon1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(259, Short.MAX_VALUE))
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(478, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelTitle.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabelTitle.setText("Monlau Airlines");

        jLabelIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/images/monlau.png"))); // NOI18N

        jLabelOrigen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelOrigen.setText("Origen:");

        jComboBoxOrigen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxOrigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxOrigenActionPerformed(evt);
            }
        });

        jLabelDestino.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelDestino.setText("Destino:");

        jComboBoxDestino.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxDestinoActionPerformed(evt);
            }
        });

        jButtonConfirmarOrigenDestino.setText("CONFIRMAR");
        jButtonConfirmarOrigenDestino.setToolTipText("");
        jButtonConfirmarOrigenDestino.setActionCommand("");
        jButtonConfirmarOrigenDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfirmarOrigenDestinoActionPerformed(evt);
            }
        });

        jLabelClaseAsiento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelClaseAsiento.setText("Clase Asiento:");

        jComboBoxClaseAsiento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxClaseAsiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxClaseAsientoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Rellena el formulario:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Selecciona Origen y Destino:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Selecciona Clase del Asiento:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Nombre:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Apellido:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Email:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("DNI:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Telefono:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Direccion:");

        jButtonVerificarDatos.setText("VERIFICAR DATOS");
        jButtonVerificarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerificarDatosActionPerformed(evt);
            }
        });

        jButtonClear.setText("BORRAR");
        jButtonClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Precio:");

        jButtonSiguiente.setText("SIGUIENTE");
        jButtonSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSiguienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelIcon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonVerificarDatos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonSiguiente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonClear))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonConfirmarOrigenDestino)
                            .addComponent(jLabelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabelOrigen)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBoxOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(17, 17, 17)
                                        .addComponent(jLabelDestino))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabelClaseAsiento)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jComboBoxClaseAsiento, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel4)
                                                .addComponent(jLabel5))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jTextFieldApellido)
                                                .addComponent(jTextFieldNombre)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabel8)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextFieldTelefono))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextFieldEmail))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel7)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextFieldDni))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel9)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextFieldDireccion))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 57, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelOrigen)
                    .addComponent(jLabelDestino)
                    .addComponent(jComboBoxDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jButtonConfirmarOrigenDestino)
                .addGap(24, 24, 24)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelClaseAsiento)
                    .addComponent(jComboBoxClaseAsiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextFieldTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextFieldDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextFieldPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonVerificarDatos)
                    .addComponent(jButtonClear)
                    .addComponent(jButtonSiguiente))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxDestinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxDestinoActionPerformed

    private void jComboBoxOrigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxOrigenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxOrigenActionPerformed

    private void jButtonConfirmarOrigenDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfirmarOrigenDestinoActionPerformed
        OrigenDestino origen = OrigenDestino.valueOf(jComboBoxOrigen.getSelectedItem().toString());
        OrigenDestino destino = OrigenDestino.valueOf(jComboBoxDestino.getSelectedItem().toString());

        if (origen == destino) {
            JOptionPane.showMessageDialog(this, "No puedes elegir el mismo Origen y Destino", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Configurar origen, destino y hora del vuelo
            LocalDateTime ahora = LocalDateTime.now();
            LocalDateTime fechaHoraVuelo = ahora.plusHours(2); // Hora actual + 2 horas
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String fechaHoraStr = fechaHoraVuelo.format(formatter);

            vuelo.setOrigen(origen);
            vuelo.setDestino(destino);
            vuelo.setFechaHora(fechaHoraStr);

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this, "Origen y Destino Confirmados", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // Habilitar selección de clase de asiento y formulario
            jComboBoxClaseAsiento.setEnabled(true);
            jTextFieldNombre.setEnabled(true);
            jTextFieldApellido.setEnabled(true);
            jTextFieldEmail.setEnabled(true);
            jTextFieldDni.setEnabled(true);
            jTextFieldTelefono.setEnabled(true);
            jTextFieldDireccion.setEnabled(true);
            jButtonVerificarDatos.setEnabled(true);

            // Deshabilitar origen y destino
            jComboBoxOrigen.setEnabled(false);
            jComboBoxDestino.setEnabled(false);
            jButtonConfirmarOrigenDestino.setEnabled(false);
        }
    }//GEN-LAST:event_jButtonConfirmarOrigenDestinoActionPerformed

    private void jComboBoxClaseAsientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxClaseAsientoActionPerformed
        String claseSeleccionada = (String) jComboBoxClaseAsiento.getSelectedItem();
        ClaseAsiento clase = ClaseAsiento.valueOf(claseSeleccionada.toUpperCase());
        DataClass.clase = clase;
    }//GEN-LAST:event_jComboBoxClaseAsientoActionPerformed

    private void jButtonVerificarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerificarDatosActionPerformed
        boolean isValid = true;

        // Validar datos del usuario
        if (!UserDataValidations.checkName(jTextFieldNombre.getText())) {
            isValid = false;
            JOptionPane.showMessageDialog(this, "Nombre no válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
        if (!UserDataValidations.checkName(jTextFieldApellido.getText())) {
            isValid = false;
            JOptionPane.showMessageDialog(this, "Apellido no válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
        if (!UserDataValidations.checkEmail(jTextFieldEmail.getText())) {
            isValid = false;
            JOptionPane.showMessageDialog(this, "Email no válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
        if (!UserDataValidations.checkId(jTextFieldDni.getText())) {
            isValid = false;
            JOptionPane.showMessageDialog(this, "DNI no válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
        if (!UserDataValidations.isNumeric(jTextFieldTelefono.getText())) {
            isValid = false;
            JOptionPane.showMessageDialog(this, "Teléfono no válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
        if (jTextFieldDireccion.getText().isEmpty()) {
            isValid = false;
            JOptionPane.showMessageDialog(this, "Dirección no válida", "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (isValid) {
            // Crear usuario
            Usuario usuario = null;
            try {
                usuario = new Usuario(
                        1,
                        jTextFieldNombre.getText(),
                        jTextFieldApellido.getText(),
                        jTextFieldEmail.getText(),
                        jTextFieldDni.getText(),
                        jTextFieldTelefono.getText(),
                        jTextFieldDireccion.getText()
                );
            } catch (DniInvalidoException ex) {
                Logger.getLogger(JFrameMain.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Obtener clase de asiento seleccionada
            ClaseAsiento claseSeleccionada = ClaseAsiento.valueOf(jComboBoxClaseAsiento.getSelectedItem().toString());

            // Buscar asiento disponible
            Asiento asientoSeleccionado = null;
            for (Asiento asiento : asientos) {
                if (asiento.getClase().equals(claseSeleccionada) && !asiento.isOcupado()) {
                    asientoSeleccionado = asiento;
                    break;
                }
            }

            if (asientoSeleccionado == null) {
                JOptionPane.showMessageDialog(this, "No hay asientos disponibles en esta clase", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Marcar asiento como ocupado
            asientoSeleccionado.setOcupado(true);

            // Calcular precio según la clase de asiento
            precioBase = calcularPrecioBase(claseSeleccionada);

            // Redondear precioBase a dos decimales
            precioBase = Math.round(precioBase * 100.0) / 100.0;

            // Crear reserva con el precio calculado
            Reserva reserva = new Reserva(
                    1,
                    usuario,
                    vuelo,
                    asientoSeleccionado,
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                    precioBase
            );

            // Mostrar precio
            JOptionPane.showMessageDialog(this, "El precio de tu reserva es: €" + precioBase, "Precio", JOptionPane.INFORMATION_MESSAGE);

            // Deshabilitar campos
            jButtonVerificarDatos.setEnabled(false);
            jTextFieldNombre.setEnabled(false);
            jTextFieldApellido.setEnabled(false);
            jTextFieldEmail.setEnabled(false);
            jTextFieldDni.setEnabled(false);
            jTextFieldTelefono.setEnabled(false);
            jTextFieldDireccion.setEnabled(false);
            jComboBoxClaseAsiento.setEnabled(false);

            // Habilitar botón Siguiente
            jButtonSiguiente.setEnabled(true);

            // Convertir precioBase a String y mostrarlo en el campo de texto
            String precioString = String.format("%.2f", precioBase); // Formatear con dos decimales
            jTextFieldPrecio.setText(precioString);
        }
    }//GEN-LAST:event_jButtonVerificarDatosActionPerformed

    private double calcularPrecioBase(ClaseAsiento clase) {
        Random random = new Random();
        switch (clase) {
            case ECONOMICA:
                return 20 + (80 - 20) * random.nextDouble(); // Precio entre 20 y 80
            case PREMIUM:
                return calcularPrecioBase(ClaseAsiento.ECONOMICA) + 50; // Base económica + 50
            case PRIMERA_CLASE:
                return calcularPrecioBase(ClaseAsiento.PREMIUM) + 100; // Base premium + 100
            default:
                return 0;
        }
    }

    private void jButtonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearActionPerformed
        // Limpiar todos los campos
        jTextFieldNombre.setText("");
        jTextFieldApellido.setText("");
        jTextFieldEmail.setText("");
        jTextFieldDni.setText("");
        jTextFieldTelefono.setText("");
        jTextFieldDireccion.setText("");
        jComboBoxClaseAsiento.setSelectedIndex(0);

        // Habilitar campos de origen y destino
        jComboBoxOrigen.setEnabled(true);
        jComboBoxDestino.setEnabled(true);
        jButtonConfirmarOrigenDestino.setEnabled(true);

        // Deshabilitar campos de reserva
        jComboBoxClaseAsiento.setEnabled(false);
        jTextFieldNombre.setEnabled(false);
        jTextFieldApellido.setEnabled(false);
        jTextFieldEmail.setEnabled(false);
        jTextFieldDni.setEnabled(false);
        jTextFieldTelefono.setEnabled(false);
        jTextFieldDireccion.setEnabled(false);
        jButtonVerificarDatos.setEnabled(false);
        jButtonSiguiente.setEnabled(false);
        jTextFieldPrecio.setText("");
    }//GEN-LAST:event_jButtonClearActionPerformed

    private void jButtonSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSiguienteActionPerformed
        DataClass.v = vuelo;
        System.out.println(vuelo);
        enviarDatosUsuario();
        DataClass.JFA.actualizarListaAsientos(DataClass.clase);
        DataClass.JFM.setVisible(false);
        DataClass.JFA.setVisible(true);
    }//GEN-LAST:event_jButtonSiguienteActionPerformed

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
            java.util.logging.Logger.getLogger(JFrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DataClass.JFM = new JFrameMain();
                DataClass.JFM.setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClear;
    private javax.swing.JButton jButtonConfirmarOrigenDestino;
    private javax.swing.JButton jButtonSiguiente;
    private javax.swing.JButton jButtonVerificarDatos;
    private javax.swing.JComboBox<String> jComboBoxClaseAsiento;
    private javax.swing.JComboBox<String> jComboBoxDestino;
    private javax.swing.JComboBox<String> jComboBoxOrigen;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelClaseAsiento;
    private javax.swing.JLabel jLabelDestino;
    private javax.swing.JLabel jLabelIcon;
    private javax.swing.JLabel jLabelIcon1;
    private javax.swing.JLabel jLabelOrigen;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JLabel jLabelTitle1;
    private javax.swing.JTextField jTextFieldApellido;
    private javax.swing.JTextField jTextFieldDireccion;
    private javax.swing.JTextField jTextFieldDni;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldPrecio;
    private javax.swing.JTextField jTextFieldTelefono;
    // End of variables declaration//GEN-END:variables

}

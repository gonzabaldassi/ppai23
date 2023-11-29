package pantalla;
import controlador.GestorEnc;
import datos.Llamada;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public class AdmPantEnc extends javax.swing.JFrame {

    private Date fechaDesde;
    private Date fechaHasta;
    private String formaVisualizar;
    private String grillaDatosLlamadaEncuestada; //Datos llamada Seleccionada
    private Llamada llamadaSeleccionada; //objeto foraneo
    private GestorEnc gestor = new GestorEnc();
    private String datosEncuestaConRespuesta;
    private ArrayList<Llamada> grillaLlamadas;

    
   //Metodo habilitar ventana//Metodo1
    public void opConsultarEncuesta(){
        habilitarPant();
        setVisible(true);
    }
    
    //Metodo para habilitar la pantalla//Metodo2
    public void habilitarPant(){
        gestor.nuevaConsulta();
    }
    
    //Metodo para solicitar la fecha inicio y fecha fin del periodo//Metodo5
    public void solicitarPeriodo(){
        jDateChooserFechaInicio.setVisible(true);
        jDateChooserFechaFin.setVisible(true);
    }
     
    //Metodo para tomar la fecha desde//Metodo6
    public Date tomarFechaDesde(){
        fechaDesde= jDateChooserFechaInicio.getDate(); 
        return fechaDesde;
    }
    
    //Metodo para tomar la fecha hasta//Metodo7
    public Date tomarFechaHasta(){
        fechaHasta = jDateChooserFechaFin.getDate();
        return fechaHasta;
    }
    
    //Metodo13
    public void mostrarLlamadasParaSeleccion(){
        grillaLlamadas = gestor.getLlamadasDePeriodo();
        
        if (grillaLlamadas.size()>0){
            ComboBox_Llamada.setEnabled(true); 
            jButtonConfirmarSeleccion.setEnabled(true);
            textAreaSalidaDatos.setEnabled(true);
            jComboBoxVisualizacion.setEnabled(true);
            lblDatosCliente.setEnabled(true);
            jLabelFormaVisualizacion.setEnabled(true);
            BotonFormaVisualizacion.setEnabled(true);
        }else{
            JOptionPane.showMessageDialog(null, "No hay llamadas en el periodo Seleccionado");
            ComboBox_Llamada.setEnabled(false); 
            jButtonConfirmarSeleccion.setEnabled(false);
            textAreaSalidaDatos.setEnabled(false);
            jComboBoxVisualizacion.setEnabled(false);
            lblDatosCliente.setEnabled(false);
            jLabelFormaVisualizacion.setEnabled(false);
            BotonFormaVisualizacion.setEnabled(false);
        }

        ComboBox_Llamada.removeAllItems();
        for(int i=0; i < grillaLlamadas.size();i++){
            ComboBox_Llamada.addItem(grillaLlamadas.get(i));
        }
    }
    
    //Metodo14
    public void tomarLlamada(){
        int indiceSeleccionado=ComboBox_Llamada.getSelectedIndex();
        
        //If para controlar que el indice del combobox no sea -1, sino da error.
        if(indiceSeleccionado != -1){ 
            llamadaSeleccionada = grillaLlamadas.get(indiceSeleccionado);
        }else{
            llamadaSeleccionada = null;
        }
        gestor.tomarLlamada(llamadaSeleccionada);
    }
    
    //Metodo32
    public void mostrarDatosLlamadaEncuestada(){
        tomarLlamada();

        if(llamadaSeleccionada!=null){
            grillaDatosLlamadaEncuestada = gestor.buscarDatosLlamada(llamadaSeleccionada);
            datosEncuestaConRespuesta=gestor.obtenerDatosEncuesta(llamadaSeleccionada);
            textAreaSalidaDatos.setText(grillaDatosLlamadaEncuestada + "\n" + datosEncuestaConRespuesta);
        }else{
            JOptionPane.showMessageDialog(null, "No se selecciono ninguna llamada");
            }
    }  
    
    //Metodo33
    public void solicitarFormaDeVisualizar(){}

    //Metodo34
    
    //METODO MODIFICADO PARA EL PATRON SINGLETON 
    
    public void tomarSelCSV(){
        formaVisualizar=jComboBoxVisualizacion.getSelectedItem().toString();
        if (formaVisualizar == "CSV"){
            gestor.tomarSelCSV(formaVisualizar);
        } else {
            gestor.tomarSelImpresion(formaVisualizar);
        }
    }
    
    
    
    public AdmPantEnc() {
        initComponents();
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        jLabelPeriodoLlamada = new javax.swing.JLabel();
        lblFechaInicio = new javax.swing.JLabel();
        lblFechaFin = new javax.swing.JLabel();
        jButton1Confirmar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jDateChooserFechaInicio = new com.toedter.calendar.JDateChooser();
        jDateChooserFechaFin = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        ComboBox_Llamada = new javax.swing.JComboBox<>();
        PanelDatos = new javax.swing.JPanel();
        lblDatosCliente = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaSalidaDatos = new javax.swing.JTextArea();
        jLabelFormaVisualizacion = new javax.swing.JLabel();
        jComboBoxVisualizacion = new javax.swing.JComboBox<>();
        jButtonConfirmarSeleccion = new javax.swing.JButton();
        BotonFormaVisualizacion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg.setBackground(new java.awt.Color(255, 255, 255));

        jLabelPeriodoLlamada.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelPeriodoLlamada.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPeriodoLlamada.setText("Periodo de Llamada ");
        jLabelPeriodoLlamada.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblFechaInicio.setText("Fecha de inicio");

        lblFechaFin.setText("Fecha de fin");

        jButton1Confirmar.setText("Confirmar");
        jButton1Confirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ConfirmarActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(jLabelPeriodoLlamada)
                        .addGap(0, 273, Short.MAX_VALUE))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFechaInicio))
                        .addGap(36, 36, 36)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(jButton1Confirmar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                                .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jDateChooserFechaInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooserFechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(79, 79, 79))))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelPeriodoLlamada)
                .addGap(32, 32, 32)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooserFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooserFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1Confirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setEnabled(false);

        ComboBox_Llamada.setEnabled(false);
        ComboBox_Llamada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBox_LlamadaActionPerformed(evt);
            }
        });

        PanelDatos.setEnabled(false);

        lblDatosCliente.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDatosCliente.setText("Datos de llamada");
        lblDatosCliente.setEnabled(false);

        textAreaSalidaDatos.setEditable(false);
        textAreaSalidaDatos.setColumns(20);
        textAreaSalidaDatos.setRows(5);
        textAreaSalidaDatos.setEnabled(false);
        jScrollPane1.setViewportView(textAreaSalidaDatos);

        javax.swing.GroupLayout PanelDatosLayout = new javax.swing.GroupLayout(PanelDatos);
        PanelDatos.setLayout(PanelDatosLayout);
        PanelDatosLayout.setHorizontalGroup(
            PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(PanelDatosLayout.createSequentialGroup()
                        .addComponent(lblDatosCliente)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PanelDatosLayout.setVerticalGroup(
            PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDatosCliente)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jLabelFormaVisualizacion.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelFormaVisualizacion.setText("Forma de visualización");
        jLabelFormaVisualizacion.setEnabled(false);

        jComboBoxVisualizacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Seleccione--", "CSV", "PDF" }));
        jComboBoxVisualizacion.setEnabled(false);
        jComboBoxVisualizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxVisualizacionActionPerformed(evt);
            }
        });

        jButtonConfirmarSeleccion.setText("Seleccionar");
        jButtonConfirmarSeleccion.setEnabled(false);
        jButtonConfirmarSeleccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfirmarSeleccionActionPerformed(evt);
            }
        });

        BotonFormaVisualizacion.setText("Seleccionar");
        BotonFormaVisualizacion.setEnabled(false);
        BotonFormaVisualizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonFormaVisualizacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PanelDatos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ComboBox_Llamada, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButtonConfirmarSeleccion)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabelFormaVisualizacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBoxVisualizacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(BotonFormaVisualizacion))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ComboBox_Llamada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonConfirmarSeleccion)
                .addGap(15, 15, 15)
                .addComponent(PanelDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelFormaVisualizacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBoxVisualizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BotonFormaVisualizacion)
                .addContainerGap(93, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Botones
    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
       System.exit(0);
    }//GEN-LAST:event_jButtonCancelarActionPerformed
    //Boton tomar Fecha Desde y Hasta para los periodos
    private void jButton1ConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ConfirmarActionPerformed
        //Condicional para que no guarde la llamada seleccionada anterior. 
        if (ComboBox_Llamada.getItemCount()!=0){
            ComboBox_Llamada.removeAllItems();
        }
        grillaDatosLlamadaEncuestada="";
        textAreaSalidaDatos.setText("");
        
        fechaDesde= tomarFechaDesde();
        fechaHasta= tomarFechaHasta();
        
        gestor.tomarFechaDesdeHasta(fechaDesde,fechaHasta);
        gestor.validarFecha(fechaDesde,fechaHasta);
       
        //Método self para mostrar las llamadas//Metodo13
        mostrarLlamadasParaSeleccion();
        
    }//GEN-LAST:event_jButton1ConfirmarActionPerformed
    private void ComboBox_LlamadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBox_LlamadaActionPerformed

    }//GEN-LAST:event_ComboBox_LlamadaActionPerformed

    private void jButtonConfirmarSeleccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfirmarSeleccionActionPerformed
    //  Metodo self para tomar la llamada seleccionada
        mostrarDatosLlamadaEncuestada();
    }//GEN-LAST:event_jButtonConfirmarSeleccionActionPerformed

    private void BotonFormaVisualizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonFormaVisualizacionActionPerformed
    //Metodo para tomar la seleccion del archivo tipo CSV
        tomarSelCSV();
    }//GEN-LAST:event_BotonFormaVisualizacionActionPerformed

    private void jComboBoxVisualizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxVisualizacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxVisualizacionActionPerformed

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
            java.util.logging.Logger.getLogger(AdmPantEnc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdmPantEnc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdmPantEnc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdmPantEnc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdmPantEnc().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonFormaVisualizacion;
    private javax.swing.JComboBox<Llamada> ComboBox_Llamada;
    private javax.swing.JPanel PanelDatos;
    private javax.swing.JPanel bg;
    private javax.swing.JButton jButton1Confirmar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonConfirmarSeleccion;
    private javax.swing.JComboBox<String> jComboBoxVisualizacion;
    private com.toedter.calendar.JDateChooser jDateChooserFechaFin;
    private com.toedter.calendar.JDateChooser jDateChooserFechaInicio;
    private javax.swing.JLabel jLabelFormaVisualizacion;
    private javax.swing.JLabel jLabelPeriodoLlamada;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDatosCliente;
    private javax.swing.JLabel lblFechaFin;
    private javax.swing.JLabel lblFechaInicio;
    private javax.swing.JTextArea textAreaSalidaDatos;
    // End of variables declaration//GEN-END:variables
}

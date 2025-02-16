package servicos;

import java.awt.Label;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class reserva extends javax.swing.JFrame {
    
    public Label lbl = new Label();
    public Label lbl1 = new Label();
    public Label lbl2 = new Label();
    
    public reserva() {
        initComponents();
        exibir();
        exibirusuarios();
        exibirlivros();
        exibirrevistas();
        BTNinserir.setEnabled(false);
        TXTid.setEnabled(false);
    }
    public void limpar ()
    {
        TXTid.setEnabled(false);
        TXTid.setText(null);
        TXTdataentrega.setText(null);
        TXTdatadevolucao.setText(null);
        exibirusuarios();
        exibirlivros();
        exibirrevistas();
    }
    public void exibir()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root","")) {
                    java.sql.Statement st = conn.createStatement();
                        ResultSet rs = st.executeQuery("SELECT \n" +
                                                        "    reservas.idreservas,\n" +
                                                        "    livros.titulo AS livro,\n" +
                                                        "    revistas.titulo AS revista,\n" +
                                                        "    usuarios.nome AS usuario,\n" +
                                                        "    reservas.dataEntrega,\n" +
                                                        "    reservas.dataDevolucao\n" +
                                                        "FROM reservas\n" +
                                                        "INNER JOIN livros ON livros.idlivros = reservas.idlivros\n" +
                                                        "INNER JOIN revistas ON revistas.idrevistas = reservas.idrevistas\n" +
                                                        "INNER JOIN usuarios ON usuarios.idusuarios = reservas.idusuarios;");
                DefaultTableModel dtm=(DefaultTableModel)tbldados.getModel();
                dtm.setNumRows(0);

                while (rs.next())
            {
                dtm.addRow(new String[]{ 
                    rs.getString("idreservas"), 
                    rs.getString("livro"),  // Alias dado para livros.titulo na query
                    rs.getString("revista"),  // Alias dado para revistas.titulo na query
                    rs.getString("usuario"),  // Alias dado para usuarios.nome na query
                    rs.getString("dataEntrega"), 
                    rs.getString("dataDevolucao")
                });
            }
            rs.close();
        }
     }
         catch(Exception e)
    {
        JOptionPane.showMessageDialog(null, "Erro de Conexão: " + e.getMessage());
        e.printStackTrace();
        }
    }
    public void exibirusuarios()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root","")) {
                java.sql.Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("select idusuarios, nome from usuarios order by nome ");

                CMBusuario.removeAllItems();
                while (rs.next())
                {
                CMBusuario.addItem(rs.getString(2));
                }
                rs.close();
                }
            }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Erro de Conexão!");
        }
    }
    public void exibirlivros()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root","")) {
                java.sql.Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("select idlivros, titulo from livros order by titulo ");

                CMBlivro.removeAllItems();
                while (rs.next())
                {
                CMBlivro.addItem(rs.getString(2));
                }
                rs.close();
                }
            }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Erro de Conexão!");
        }
    }
    public void exibirrevistas()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root","")) {
                java.sql.Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("select idrevistas, titulo from revistas order by titulo ");

                CMBrevista.removeAllItems();
                while (rs.next())
                {
                CMBrevista.addItem(rs.getString(2));
                }
                rs.close();
                }
            }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Erro de Conexão!");
        }
    }
    public void capturarusuario ()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root","")) {
                java.sql.Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("select idusuarios, nome from usuarios where nome = '"+CMBusuario.getSelectedItem()+"' ");

                CMBusuario.removeAllItems();
                while (rs.next()){
                    lbl2.setText(rs.getString(1));
                }
                rs.close();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Erro de Conexão!");
        }
    }
    public void capturarlivro ()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root","")) {
                java.sql.Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("select idlivros, titulo from livros where titulo = '"+CMBlivro.getSelectedItem()+"' ");

                CMBlivro.removeAllItems();
                while (rs.next()){
                    lbl.setText(rs.getString(1));
                }
                rs.close();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Erro de Conexão!");
        }
    }
    public void capturarrevista ()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root","")) {
                java.sql.Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("select idrevistas, titulo from revistas where titulo = '"+CMBrevista.getSelectedItem()+"' ");

                CMBrevista.removeAllItems();
                while (rs.next()){
                    lbl1.setText(rs.getString(1));
                }
                rs.close();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Erro de Conexão!");
        }
    }
    public void inserir()
    {
        if (TXTdataentrega.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Por favor, preencha a data de entrega!");
            return;
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");           
                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root","")) {
                    java.sql.Statement st = conn.createStatement();
                    
                    capturarrevista();
                    capturarusuario();
                    capturarlivro();
                    
                    st.executeUpdate("insert into reservas (idlivros, idrevistas, idusuarios, dataEntrega, dataDevolucao) VALUES ('"+lbl.getText()+"', '"+lbl1.getText() + "', '"
                                        + lbl2.getText() + "', '"+ TXTdataentrega.getText() + "', '"+ TXTdatadevolucao.getText() + "')");
                    JOptionPane.showMessageDialog(this, "Reserva concluida com sucesso");
                    exibir();
                    limpar();
                    BTNinserir.setEnabled(false);
                }

              }catch(Exception e){
                    JOptionPane.showMessageDialog(this, "Não foi possível inserir esta reserva","Inserir", JOptionPane.ERROR_MESSAGE);
              }

        } 
    public void atualizar()
    {
        if (TXTdataentrega.getText().equals("") ){
            JOptionPane.showMessageDialog(this, "Por favor, preencha a data de entrega!");
            return;
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");           
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root","")) {
                java.sql.Statement st = conn.createStatement();
                    capturarusuario();
                    capturarlivro();
                    capturarrevista();

                    st.executeUpdate("update reservas set  idlivros = '"+lbl.getText()+"', idrevistas = '"+lbl1.getText()+"', "
                            + "idusuarios = '"+lbl2.getText()+"', dataEntrega =  '"+TXTdataentrega.getText()+"', "
                            + "dataDevolucao =  '"+TXTdatadevolucao.getText()+"'  where idreservas = '"+TXTid.getText()+"' ");
                            JOptionPane.showMessageDialog(this, "Dado atualizado com sucesso");
                            exibir();
                            limpar();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Não foi possível atualizar!","Atualizar", JOptionPane.ERROR_MESSAGE);
        }
    } 
    public void excluir()
    {
        if (TXTid.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Escolha uma reserva para ser excluída!");
            return;
       }   
        try {
            Class.forName("com.mysql.jdbc.Driver");           
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root","")) {
                java.sql.Statement st = conn.createStatement();
                st.executeUpdate("delete from reservas where idreservas = '"+TXTid.getText()+"' ");
                      
                JOptionPane.showMessageDialog(this, "Reserva excluída com sucesso");
                exibir();
                limpar();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Não foi possível excluir esta reserva!");
        }   
    } 
      
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbldados = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        TXTdataentrega = new javax.swing.JFormattedTextField();
        TXTdatadevolucao = new javax.swing.JFormattedTextField();
        CMBlivro = new javax.swing.JComboBox<>();
        CMBusuario = new javax.swing.JComboBox<>();
        BTNinserir = new javax.swing.JButton();
        BTNatualizar = new javax.swing.JButton();
        BTNcancelar = new javax.swing.JButton();
        BTNexcluir = new javax.swing.JButton();
        BTNnovo = new javax.swing.JButton();
        BTNsair = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        TXTid = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        CMBrevista = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Livro:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("RESERVAS CADASTRADAS");

        tbldados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id_reserva", "Livro", "Revista", "Usuario", "Data Entrega", "Data Devolução"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbldados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbldadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbldados);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("RESERVAS");

        jLabel4.setText("Usuário:");

        jLabel5.setText("Data de entrega:");

        jLabel6.setText("Data de devolução:");

        try {
            TXTdataentrega.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            TXTdatadevolucao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        CMBlivro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Escolha" }));
        CMBlivro.setToolTipText("");

        CMBusuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Escolha" }));

        BTNinserir.setText("Inserir");
        BTNinserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNinserirActionPerformed(evt);
            }
        });

        BTNatualizar.setText("Atualizar");
        BTNatualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNatualizarActionPerformed(evt);
            }
        });

        BTNcancelar.setText("Cancelar");
        BTNcancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNcancelarActionPerformed(evt);
            }
        });

        BTNexcluir.setText("Excluir");
        BTNexcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNexcluirActionPerformed(evt);
            }
        });

        BTNnovo.setText("Novo");
        BTNnovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNnovoActionPerformed(evt);
            }
        });

        BTNsair.setText("Sair");
        BTNsair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNsairActionPerformed(evt);
            }
        });

        jLabel7.setText("Id:");

        jLabel8.setText("Revista:");

        CMBrevista.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Escolha" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(224, 224, 224)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(BTNinserir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel2))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(BTNatualizar)
                                        .addGap(18, 18, 18)
                                        .addComponent(BTNnovo)
                                        .addGap(18, 18, 18)
                                        .addComponent(BTNexcluir)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(BTNcancelar))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(243, 243, 243)
                                .addComponent(BTNsair))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(116, 116, 116)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TXTid, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(CMBlivro, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(CMBusuario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TXTdataentrega))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TXTdatadevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(CMBrevista, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(TXTid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(CMBlivro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(CMBrevista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(CMBusuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(TXTdataentrega, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(TXTdatadevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTNinserir)
                    .addComponent(BTNatualizar)
                    .addComponent(BTNnovo)
                    .addComponent(BTNexcluir)
                    .addComponent(BTNcancelar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BTNsair)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTNcancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNcancelarActionPerformed
        limpar();
        exibir(); 
        TXTid.setEnabled(false);
        
        BTNinserir.setEnabled(false);
        BTNexcluir.setEnabled(true);
        BTNatualizar.setEnabled(true);
        BTNnovo.setEnabled(true);
    }//GEN-LAST:event_BTNcancelarActionPerformed

    private void tbldadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbldadosMouseClicked
        int linha = tbldados.getSelectedRow();
        if (linha>=0){
            DefaultTableModel dtm= (DefaultTableModel) tbldados.getModel();
            
            TXTid.setText(dtm.getValueAt(linha,0).toString());
            CMBlivro.setSelectedItem(dtm.getValueAt(linha,1).toString());
            CMBrevista.setSelectedItem(dtm.getValueAt(linha,2).toString());
            CMBusuario.setSelectedItem(dtm.getValueAt(linha,3).toString());
            TXTdataentrega.setText(dtm.getValueAt(linha,4).toString());
            TXTdatadevolucao.setText(dtm.getValueAt(linha,5).toString());
            
            BTNinserir.setEnabled(false);
            BTNatualizar.setEnabled(true);
            BTNexcluir.setEnabled(true);
            TXTid.setEnabled(false);

        }
    }//GEN-LAST:event_tbldadosMouseClicked

    private void BTNnovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNnovoActionPerformed
        limpar();
        BTNnovo.setEnabled(true);
        BTNexcluir.setEnabled(false);
        BTNatualizar.setEnabled(false);
        BTNinserir.setEnabled(true);
        TXTid.setEnabled(false);
    }//GEN-LAST:event_BTNnovoActionPerformed

    private void BTNsairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNsairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_BTNsairActionPerformed

    private void BTNinserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNinserirActionPerformed
       inserir();
    }//GEN-LAST:event_BTNinserirActionPerformed

    private void BTNatualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNatualizarActionPerformed
        atualizar();
    }//GEN-LAST:event_BTNatualizarActionPerformed

    private void BTNexcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNexcluirActionPerformed
        excluir();
    }//GEN-LAST:event_BTNexcluirActionPerformed

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
            java.util.logging.Logger.getLogger(reserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(reserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(reserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(reserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new reserva().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNatualizar;
    private javax.swing.JButton BTNcancelar;
    private javax.swing.JButton BTNexcluir;
    private javax.swing.JButton BTNinserir;
    private javax.swing.JButton BTNnovo;
    private javax.swing.JButton BTNsair;
    private javax.swing.JComboBox<String> CMBlivro;
    private javax.swing.JComboBox<String> CMBrevista;
    private javax.swing.JComboBox<String> CMBusuario;
    private javax.swing.JFormattedTextField TXTdatadevolucao;
    private javax.swing.JFormattedTextField TXTdataentrega;
    private javax.swing.JTextField TXTid;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbldados;
    // End of variables declaration//GEN-END:variables
}

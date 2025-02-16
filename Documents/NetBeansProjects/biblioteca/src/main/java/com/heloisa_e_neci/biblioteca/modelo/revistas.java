package com.heloisa_e_neci.biblioteca.modelo;
import java.awt.Label;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

public class revistas extends javax.swing.JFrame {

    public revistas() {
        initComponents();
        exibir();
        BTNinserir.setEnabled(false);
    }

    // Método para limpar os campos da interface gráfica
    public void limpar() {
        TXTid.setText("");
        TXTtitulo.setText("");
        TXTautor.setText("");
        TXTeditora.setText("");
        TXTdatalancamento.setText("");
        TXTedicao.setText("");

        TXTid.setEnabled(false);
        TXTid.requestFocus();
    }
    
    // Método fictício, apenas como exemplo (deve ser implementado)
    private void exibir() {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver"); // Atualizado para o driver mais recente
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root", "");
        java.sql.Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT idrevista, titulo, autor, editora, dataLancamento, edicao FROM revistas;");

        DefaultTableModel dtm = (DefaultTableModel) tbldados.getModel();
        dtm.setNumRows(0); // Limpa a tabela antes de adicionar novos dados

        while (rs.next()) {
            dtm.addRow(new String[]{
                rs.getString("idrevista"),
                rs.getString("titulo"),
                rs.getString("autor"),
                rs.getString("editora"),
                rs.getString("dataLancamento"),
                rs.getString("edicao")
            });
        }

        rs.close();
        conn.close(); // Fecha a conexão para evitar consumo desnecessário de recursos
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao carregar revistas: " + e.getMessage());
        e.printStackTrace();
    }
}

public void inserir() {
    // Verifica se todos os campos estão preenchidos
    if (TXTtitulo.getText().trim().isEmpty() || 
        TXTautor.getText().trim().isEmpty() || 
        TXTeditora.getText().trim().isEmpty() || 
        TXTdatalancamento.getText().trim().isEmpty() || 
        TXTedicao.getText().trim().isEmpty()) {

        JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos!");
        return;
    }

    try {
        Class.forName("com.mysql.cj.jdbc.Driver"); // Driver atualizado
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root", "");
        
        // Query para inserção na tabela revistas
        String sql = "INSERT INTO revistas (titulo, autor, editora, dataLancamento, edicao) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pst = conn.prepareStatement(sql);

        // Substitui os '?' pelos valores inseridos na interface
        pst.setString(1, TXTtitulo.getText());
        pst.setString(2, TXTautor.getText());
        pst.setString(3, TXTeditora.getText());
        pst.setString(4, TXTdatalancamento.getText());
        pst.setString(5, TXTedicao.getText());

        // Executa a inserção
        int resultado = pst.executeUpdate();

        if (resultado > 0) {
            JOptionPane.showMessageDialog(this, "Revista inserida com sucesso!");
            exibir(); // Atualiza a tabela
            BTNinserir.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao inserir a revista.", "Inserir", JOptionPane.ERROR_MESSAGE);
        }

        // Fecha recursos
        pst.close();
        conn.close();
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Não foi possível inserir esta revista!\nErro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}

public void atualizar() {
    // Verifica se todos os campos estão preenchidos
    if (TXTid.getText().trim().isEmpty() ||
        TXTtitulo.getText().trim().isEmpty() || 
        TXTautor.getText().trim().isEmpty() || 
        TXTeditora.getText().trim().isEmpty() || 
        TXTdatalancamento.getText().trim().isEmpty() || 
        TXTedicao.getText().trim().isEmpty()) {

        JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos!");
        return;
    }

    try {
        // Registra o driver do MySQL
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Conecta ao banco de dados
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root", "")) {

            // Query para atualizar a tabela revistas
            String sql = "UPDATE revistas SET titulo = ?, autor = ?, editora = ?, dataLancamento = ?, edicao = ? WHERE idrevista = ?";
            PreparedStatement pst = conn.prepareStatement(sql);

            // Substitui os '?' pelos valores inseridos na interface
            pst.setString(1, TXTtitulo.getText());
            pst.setString(2, TXTautor.getText());
            pst.setString(3, TXTeditora.getText());
            pst.setString(4, TXTdatalancamento.getText());
            pst.setString(5, TXTedicao.getText());
            pst.setInt(6, Integer.parseInt(TXTid.getText())); // ID deve ser um número inteiro

            // Executa a atualização
            int resultado = pst.executeUpdate();

            if (resultado > 0) {
                JOptionPane.showMessageDialog(this, "Revista atualizada com sucesso!");
                exibir(); // Atualiza a tabela
            } else {
                JOptionPane.showMessageDialog(this, "Nenhuma revista encontrada com esse ID.", "Erro", JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro no banco de dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    } catch (ClassNotFoundException e) {
        JOptionPane.showMessageDialog(this, "Erro ao carregar o driver do MySQL: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "O ID deve ser um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}

public void excluir()
    {
        if (TXTid.getText().equals("") || TXTtitulo.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Escolha uma revista para ser excluída!");
            TXTid.requestFocus();
            return;
        }   
        try {
            Class.forName("com.mysql.jdbc.Driver");           
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root","")) {
                java.sql.Statement st = conn.createStatement();
                st.executeUpdate("delete from revistas where idrevistas = '"+TXTid.getText()+"' ");
                      
                JOptionPane.showMessageDialog(this, "Revista excluída com sucesso");
                exibir();
            }    
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Não foi possível excluir essa revista!");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        TXTtitulo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        TXTautor = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        TXTeditora = new javax.swing.JTextField();
        TXTdatalancamento = new javax.swing.JFormattedTextField();
        BTNinserir = new javax.swing.JButton();
        BTNatualizar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        BTNexcluir = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbldados = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        TXTedicao = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        BTNnovo = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        TXTid = new javax.swing.JTextField();
        BTNsair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));

        jLabel4.setText("Ano Lançamento:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel6.setText("REVISTAS CADASTRADAS");

        TXTautor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTautorActionPerformed(evt);
            }
        });

        jLabel5.setText("Editora:");

        TXTeditora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTeditoraActionPerformed(evt);
            }
        });

        try {
            TXTdatalancamento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

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

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("REVISTAS");

        jLabel2.setText("Título:");

        BTNexcluir.setText("Excluir");
        BTNexcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNexcluirActionPerformed(evt);
            }
        });

        jLabel3.setText("Autor:");

        tbldados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "idrevista", "Titulo", "Autor", "Editora", "Ano de lancamento", "Edição"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbldados);

        jLabel7.setText("Edição:");

        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        BTNnovo.setText("Novo");
        BTNnovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNnovoActionPerformed(evt);
            }
        });

        jLabel8.setText("Id");

        BTNsair.setText("Sair");
        BTNsair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNsairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BTNinserir)
                        .addGap(18, 18, 18)
                        .addComponent(BTNatualizar)
                        .addGap(15, 15, 15)
                        .addComponent(BTNnovo)
                        .addGap(18, 18, 18)
                        .addComponent(BTNexcluir)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TXTeditora))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(TXTtitulo))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(TXTautor))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(9, 9, 9)
                                        .addComponent(TXTdatalancamento))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TXTedicao, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(TXTid))))
                            .addGap(105, 105, 105))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(183, 183, 183))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap()))))
            .addGroup(layout.createSequentialGroup()
                .addGap(196, 196, 196)
                .addComponent(BTNsair)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(TXTid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TXTtitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(TXTautor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(TXTeditora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(TXTedicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(TXTdatalancamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BTNinserir)
                        .addComponent(BTNatualizar)
                        .addComponent(BTNexcluir)
                        .addComponent(jButton1))
                    .addComponent(BTNnovo, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BTNsair)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void TXTautorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTautorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTautorActionPerformed

    private void TXTeditoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTeditoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTeditoraActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        limpar();
        exibir(); 
        TXTid.setEnabled(false);
        
        BTNinserir.setEnabled(false);
        BTNexcluir.setEnabled(true);
        BTNatualizar.setEnabled(true);
        BTNnovo.setEnabled(true);        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void BTNatualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNatualizarActionPerformed
        atualizar();
    }//GEN-LAST:event_BTNatualizarActionPerformed

    private void BTNexcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNexcluirActionPerformed
       excluir();
    }//GEN-LAST:event_BTNexcluirActionPerformed

    private void BTNnovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNnovoActionPerformed
       TXTid.setText(null);
       TXTtitulo.setText(null);
       TXTautor.setText(null);
       TXTeditora.setText(null);
       TXTdatalancamento.setText(null);
       TXTedicao.setText(null);
        
        BTNnovo.setEnabled(true);
        BTNexcluir.setEnabled(false);
        BTNatualizar.setEnabled(false);
        BTNinserir.setEnabled(true);
        
        TXTid.setEnabled(false);        
    }//GEN-LAST:event_BTNnovoActionPerformed

    private void BTNinserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNinserirActionPerformed
        inserir();
    }//GEN-LAST:event_BTNinserirActionPerformed

    private void BTNsairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNsairActionPerformed
        this.dispose();
    }//GEN-LAST:event_BTNsairActionPerformed

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
            java.util.logging.Logger.getLogger(revistas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(revistas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(revistas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(revistas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new revistas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNatualizar;
    private javax.swing.JButton BTNexcluir;
    private javax.swing.JButton BTNinserir;
    private javax.swing.JButton BTNnovo;
    private javax.swing.JButton BTNsair;
    private javax.swing.JTextField TXTautor;
    private javax.swing.JFormattedTextField TXTdatalancamento;
    private javax.swing.JTextField TXTedicao;
    private javax.swing.JTextField TXTeditora;
    private javax.swing.JTextField TXTid;
    private javax.swing.JTextField TXTtitulo;
    private javax.swing.JButton jButton1;
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

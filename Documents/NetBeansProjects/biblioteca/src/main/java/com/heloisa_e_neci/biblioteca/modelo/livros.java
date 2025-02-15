package com.heloisa_e_neci.biblioteca.modelo;
import java.awt.Label;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class livros extends javax.swing.JFrame {

    public livros() {
        initComponents();
        exibir();
        BTNinserir.setEnabled(false);
    }

    public void limpar ()
    {
        TXTid.setText(null);
        TXTtitulo.setText(null);
        TXTautor.setText(null);
        TXTeditora.setText(null);
        TXTdatalancamento.requestFocus();
        TXTnumpag.setText(null);
        
        TXTdatalancamento.setEnabled(true);
        
    }
    
    public void exibir()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root","")) {
                    java.sql.Statement st = conn.createStatement();
                        ResultSet rs = st.executeQuery("SELECT livros.idlivros,livros.titulo,livros.autor,livros.anoPublicacao,livros.editora, \n" +
                                                        "livros.numPag FROM livros; ");
                DefaultTableModel dtm=(DefaultTableModel)tbldados.getModel();
                dtm.setNumRows(0);

                while (rs.next())
            {
            dtm.addRow(new String[]{ rs.getString("livros.idlivros"), rs.getString("livros.titulo"),rs.getString("livros.autor"),rs.getString("livros.anoPublicacao"),rs.getString("livros.editora"),rs.getString("livros.numPag")});
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
 
    public void inserir()
    {
        if (TXTtitulo.getText().equals("") || TXTautor.getText().equals("") || TXTeditora.getText().equals("")
                || TXTdatalancamento.getText().equals("")|| TXTnumpag.getText().equals("")){
                      JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos!");
                       return;
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");           
                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root","")) {
                    java.sql.Statement st = conn.createStatement();

                    String se;
                    
                    st.executeUpdate("insert into livros (titulo, autor, anoPublicacao, editora, numPag) values ('"+TXTtitulo.getText()+"','"+TXTautor.getText()+"','"+TXTdatalancamento.getText()+"', '"+TXTeditora.getText()+"', '"+TXTnumpag.getText()+"')");
                        JOptionPane.showMessageDialog(this, "Livro inserido com sucesso");
                        exibir();
                        BTNinserir.setEnabled(false);
                }

              }catch(Exception e){
                    JOptionPane.showMessageDialog(this, "Não foi possível inserir este livro!","Inserir", JOptionPane.ERROR_MESSAGE);
              }

        } 
    
    public void atualizar()
    {
        if (TXTtitulo.getText().equals("") || TXTautor.getText().equals("") || TXTeditora.getText().equals("")|| TXTdatalancamento.getText().equals("")|| TXTnumpag.getText().equals("")){
               JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos!");
               return;
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");           
                     try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root","")) {
                         java.sql.Statement st = conn.createStatement();
                         
                        st.executeUpdate("update livros set  titulo = '"+TXTtitulo.getText()+"', autor = '"+TXTautor.getText()+"',  anoPublicacao = '"+TXTdatalancamento.getText()+"', editora = '"+TXTeditora.getText()+"', numPag = '"+TXTnumpag.getText()+"' where idlivros = '"+TXTid.getText()+"' ");
                        JOptionPane.showMessageDialog(this, "Dado atualizado com sucesso");
                        exibir();
                     }
           }catch(Exception e){
                JOptionPane.showMessageDialog(this, "Não foi possível atualizar!","Atualizar", JOptionPane.ERROR_MESSAGE);
            }
        } 
    
    public void excluir()
    {
        if (TXTid.getText().equals("") || TXTtitulo.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Escolha uma aluno para ser excluído!");
            TXTid.requestFocus();
            return;
        }   
        try {
            Class.forName("com.mysql.jdbc.Driver");           
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root","")) {
                java.sql.Statement st = conn.createStatement();
                st.executeUpdate("delete from livros where idlivros = '"+TXTid.getText()+"' ");
                      
                JOptionPane.showMessageDialog(this, "Livro excluído com sucesso");
                exibir();
            }    
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Não foi possível excluir este livro!");
        }
    } 
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        TXTtitulo = new javax.swing.JTextField();
        TXTautor = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        TXTeditora = new javax.swing.JTextField();
        TXTdatalancamento = new javax.swing.JFormattedTextField();
        BTNinserir = new javax.swing.JButton();
        BTNatualizar = new javax.swing.JButton();
        BTNnovo = new javax.swing.JButton();
        BTNexcluir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbldados = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        TXTnumpag = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        TXTid = new javax.swing.JTextField();
        BTNcancelar = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("LIVROS");

        jLabel2.setText("Título:");

        jLabel3.setText("Autor:");

        jLabel4.setText("Data de Lançamento:");

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
            TXTdatalancamento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
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

        BTNnovo.setText("Novo");
        BTNnovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNnovoActionPerformed(evt);
            }
        });

        BTNexcluir.setText("Excluir");
        BTNexcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNexcluirActionPerformed(evt);
            }
        });

        tbldados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "idlivro", "Titulo", "Autor", "Ano de lancamento", "Editora", "Num. Pag."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel6.setText("LIVROS CADASTRADOS");

        jLabel7.setText("Nº Pág.");

        TXTnumpag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTnumpagActionPerformed(evt);
            }
        });

        jLabel8.setText("Id:");

        BTNcancelar.setText("Cancelar");
        BTNcancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNcancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(91, 91, 91)))
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(176, 176, 176))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(TXTnumpag))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(TXTeditora))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(TXTautor, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(TXTdatalancamento, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel2)
                                                    .addComponent(jLabel8))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(TXTid)
                                                    .addComponent(TXTtitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(55, 55, 55))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(BTNinserir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BTNatualizar)
                                .addGap(18, 18, 18)
                                .addComponent(BTNnovo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BTNexcluir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BTNcancelar)))
                        .addGap(47, 47, 47))))
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
                    .addComponent(jLabel4)
                    .addComponent(TXTdatalancamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(TXTnumpag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTNinserir)
                    .addComponent(BTNatualizar)
                    .addComponent(BTNnovo)
                    .addComponent(BTNexcluir)
                    .addComponent(BTNcancelar))
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TXTautorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTautorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTautorActionPerformed

    private void TXTeditoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTeditoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTeditoraActionPerformed

    private void TXTnumpagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTnumpagActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTnumpagActionPerformed

    private void BTNinserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNinserirActionPerformed
        inserir();
    }//GEN-LAST:event_BTNinserirActionPerformed

    private void BTNatualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNatualizarActionPerformed
        atualizar();
    }//GEN-LAST:event_BTNatualizarActionPerformed

    private void tbldadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbldadosMouseClicked
        int linha = tbldados.getSelectedRow();
        if (linha>=0){
            DefaultTableModel dtm= (DefaultTableModel) tbldados.getModel();
            
            TXTid.setText(dtm.getValueAt(linha,0).toString());
            TXTtitulo.setText(dtm.getValueAt(linha,1).toString());
            TXTautor.setText(dtm.getValueAt(linha,2).toString());
            TXTdatalancamento.setText(dtm.getValueAt(linha,3).toString());
            TXTeditora.setText(dtm.getValueAt(linha,4).toString());
            TXTnumpag.setText(dtm.getValueAt(linha,5).toString());
            
            
            TXTid.setEnabled(false);
            BTNinserir.setEnabled(false);
            BTNatualizar.setEnabled(true);
            BTNexcluir.setEnabled(true);
        }
    }//GEN-LAST:event_tbldadosMouseClicked

    private void BTNnovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNnovoActionPerformed
       TXTid.setText(null);
       TXTtitulo.setText(null);
       TXTautor.setText(null);
       TXTeditora.setText(null);
       TXTdatalancamento.setText(null);
       TXTnumpag.setText(null);
        
        BTNnovo.setEnabled(true);
        BTNexcluir.setEnabled(false);
        BTNatualizar.setEnabled(false);
        BTNinserir.setEnabled(true);
        
        TXTid.setEnabled(false);
    }//GEN-LAST:event_BTNnovoActionPerformed

    private void BTNcancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNcancelarActionPerformed
        limpar();
        exibir(); 
        TXTid.setEnabled(false);
        
        BTNinserir.setEnabled(false);
        BTNexcluir.setEnabled(true);
        BTNatualizar.setEnabled(true);
        BTNnovo.setEnabled(true);
    }//GEN-LAST:event_BTNcancelarActionPerformed

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
            java.util.logging.Logger.getLogger(livros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(livros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(livros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(livros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new livros().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNatualizar;
    private javax.swing.JButton BTNcancelar;
    private javax.swing.JButton BTNexcluir;
    private javax.swing.JButton BTNinserir;
    private javax.swing.JButton BTNnovo;
    private javax.swing.JTextField TXTautor;
    private javax.swing.JFormattedTextField TXTdatalancamento;
    private javax.swing.JTextField TXTeditora;
    private javax.swing.JTextField TXTid;
    private javax.swing.JTextField TXTnumpag;
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

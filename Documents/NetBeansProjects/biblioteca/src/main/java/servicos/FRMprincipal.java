package servicos;
import com.heloisa_e_neci.biblioteca.modelo.livros;
import com.heloisa_e_neci.biblioteca.modelo.revistas;
import com.heloisa_e_neci.biblioteca.modelo.usuarios;
import servicos.reserva;
/**
 *
 * @author Vida
 */
public class FRMprincipal extends javax.swing.JFrame {

    /**
     * Creates new form FRMprincipal
     */
    public FRMprincipal() {
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        Itemlivro = new javax.swing.JMenuItem();
        Itemrevista = new javax.swing.JMenuItem();
        Itemusuario = new javax.swing.JMenuItem();
        Itemreserva = new javax.swing.JMenuItem();
        Itemsair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Vida\\Pictures\\biblioteca2.png")); // NOI18N
        getContentPane().add(jLabel1);

        jMenu1.setText("Menu");

        Itemlivro.setText("Livros");
        Itemlivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemlivroActionPerformed(evt);
            }
        });
        jMenu1.add(Itemlivro);

        Itemrevista.setText("Revistas");
        Itemrevista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemrevistaActionPerformed(evt);
            }
        });
        jMenu1.add(Itemrevista);

        Itemusuario.setText("Usuários");
        Itemusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemusuarioActionPerformed(evt);
            }
        });
        jMenu1.add(Itemusuario);

        Itemreserva.setText("Reservas");
        Itemreserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemreservaActionPerformed(evt);
            }
        });
        jMenu1.add(Itemreserva);

        Itemsair.setText("Sair");
        Itemsair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemsairActionPerformed(evt);
            }
        });
        jMenu1.add(Itemsair);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ItemlivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemlivroActionPerformed
        livros frmc = new livros();
        frmc.setVisible(true);
    }//GEN-LAST:event_ItemlivroActionPerformed

    private void ItemrevistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemrevistaActionPerformed
       revistas frmc = new revistas();
        frmc.setVisible(true);
    }//GEN-LAST:event_ItemrevistaActionPerformed

    private void ItemusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemusuarioActionPerformed
        usuarios frmc = new usuarios();
        frmc.setVisible(true);
    }//GEN-LAST:event_ItemusuarioActionPerformed

    private void ItemreservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemreservaActionPerformed
        reserva frmc = new reserva();
        frmc.setVisible(true);
    }//GEN-LAST:event_ItemreservaActionPerformed

    private void ItemsairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemsairActionPerformed
       System.exit(0);
    }//GEN-LAST:event_ItemsairActionPerformed

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
            java.util.logging.Logger.getLogger(FRMprincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FRMprincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FRMprincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FRMprincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FRMprincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Itemlivro;
    private javax.swing.JMenuItem Itemreserva;
    private javax.swing.JMenuItem Itemrevista;
    private javax.swing.JMenuItem Itemsair;
    private javax.swing.JMenuItem Itemusuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables
}

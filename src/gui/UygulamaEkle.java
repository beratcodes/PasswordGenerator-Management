package gui;

import gui.ayarlar.ButonAyarları;
import gui.ayarlar.IDuzenleyici;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.util.List;
import java.sql.SQLException;
import gui.AnaMenu;

public class UygulamaEkle extends javax.swing.JFrame implements IDuzenleyici {

    private final AnaMenu parent;
    private final int aktifKullaniciId;

    public UygulamaEkle(AnaMenu parent, int aktifKullaniciId) {
        this.parent            = parent;
        this.aktifKullaniciId  = aktifKullaniciId;
        initComponents();
        getEdits();
        setLocationRelativeTo(parent);
    }
    
    @Override
    public void getEdits() {
        uygulamaEklePanel.setFocusable(true);
        getRootPane().setDefaultButton(btnUygulamaEkle);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        uygulamaEklePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtUygulamaAdi = new javax.swing.JTextField();
        btnUygulamaEkle = new javax.swing.JButton();

        setTitle("Uygulama Ekle");

        uygulamaEklePanel.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("UYGULAMA EKLEYİNİZ");

        txtUygulamaAdi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        btnUygulamaEkle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnUygulamaEkle.setText("EKLE");
        btnUygulamaEkle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnUygulamaEkleMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnUygulamaEkleMouseExited(evt);
            }
        });
        btnUygulamaEkle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUygulamaEkleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout uygulamaEklePanelLayout = new javax.swing.GroupLayout(uygulamaEklePanel);
        uygulamaEklePanel.setLayout(uygulamaEklePanelLayout);
        uygulamaEklePanelLayout.setHorizontalGroup(
            uygulamaEklePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(uygulamaEklePanelLayout.createSequentialGroup()
                .addGroup(uygulamaEklePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(uygulamaEklePanelLayout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addGroup(uygulamaEklePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtUygulamaAdi)))
                    .addGroup(uygulamaEklePanelLayout.createSequentialGroup()
                        .addGap(254, 254, 254)
                        .addComponent(btnUygulamaEkle, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(153, Short.MAX_VALUE))
        );
        uygulamaEklePanelLayout.setVerticalGroup(
            uygulamaEklePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(uygulamaEklePanelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addGap(36, 36, 36)
                .addComponent(txtUygulamaAdi, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(btnUygulamaEkle, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(uygulamaEklePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(uygulamaEklePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUygulamaEkleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUygulamaEkleMouseEntered
        ButonAyarları.setBg(btnUygulamaEkle, Color.black, Color.white);
    }//GEN-LAST:event_btnUygulamaEkleMouseEntered

    private void btnUygulamaEkleMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUygulamaEkleMouseExited
        ButonAyarları.setOriginalBg(btnUygulamaEkle, getForeground());
    }//GEN-LAST:event_btnUygulamaEkleMouseExited

    private void btnUygulamaEkleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUygulamaEkleActionPerformed
        String ad = txtUygulamaAdi.getText().trim();
        if (ad.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Lütfen bir isim gir.", "Uyarı",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Veritabanından mevcut uygulamaları al ve kontrol et
            List<String> liste = database.transaction.UygulamaEkle.getList(aktifKullaniciId);
            for (String existing : liste) {
                if (existing.equalsIgnoreCase(ad)) {
                    JOptionPane.showMessageDialog(this,
                        "Bu uygulama zaten ekli.", "Uyarı",
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            // Yeni uygulamayı ekle
            database.transaction.UygulamaEkle.ekle(aktifKullaniciId, ad);

            // AnaMenu combobox'u güncelle
            parent.loadUygulamalar();

            JOptionPane.showMessageDialog(this,
                "Uygulama başarıyla eklendi.");
            dispose();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                "Uygulamalar yüklenirken hata: " + ex.getMessage(),
                "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnUygulamaEkleActionPerformed

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
            java.util.logging.Logger.getLogger(UygulamaEkle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UygulamaEkle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UygulamaEkle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UygulamaEkle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new UygulamaEkle().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUygulamaEkle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField txtUygulamaAdi;
    private javax.swing.JPanel uygulamaEklePanel;
    // End of variables declaration//GEN-END:variables
}

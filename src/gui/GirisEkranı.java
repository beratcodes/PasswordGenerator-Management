package gui;

import database.DbConnection;
import gui.ayarlar.ButonAyarları;
import gui.ayarlar.IDuzenleyici;
import gui.ayarlar.TextAyarları;
import java.awt.Color;
import database.transaction.KullaniciGiris;

import gui.ayarlar.Dialogs;


public final class GirisEkranı extends javax.swing.JFrame implements IDuzenleyici {

    DbConnection db = new DbConnection();
    
    private final String KULLANICI_TEXT = "Kullanıcı Adı";
    private final String SIFRE_TEXT = "Şifre";
    
    private int aktifKullaniciId; 
    
    public GirisEkranı() {
        initComponents();
        getEdits();
    }
    
    public GirisEkranı(int aktifKullaniciId) {
        this();                   // tek satır ekle
        this.aktifKullaniciId = aktifKullaniciId; 
    }
    
    public void setKullaniciAdi(String kullaniciAdi) {
        txtKullanici.setText(kullaniciAdi);
    }

    @Override
    public void getEdits() {
        this.setLocationRelativeTo(null);
        girisEkranPanel.setFocusable(true);
        txtKullanici.setText(KULLANICI_TEXT);
        txtSifre.setText(SIFRE_TEXT);
        getRootPane().setDefaultButton(btnGiris);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        girisEkranPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtSifre = new javax.swing.JPasswordField();
        txtKullanici = new javax.swing.JTextField();
        btnGiris = new javax.swing.JButton();
        btnKayitOl = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        sifremiUnuttumLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Giriş Ekranı");
        setResizable(false);

        girisEkranPanel.setBackground(new java.awt.Color(8, 79, 140));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Hoşgeldiniz");

        txtSifre.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtSifre.setForeground(new java.awt.Color(153, 153, 153));
        txtSifre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSifreFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSifreFocusLost(evt);
            }
        });

        txtKullanici.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtKullanici.setForeground(new java.awt.Color(153, 153, 153));
        txtKullanici.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtKullaniciFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtKullaniciFocusLost(evt);
            }
        });

        btnGiris.setBackground(new java.awt.Color(255, 255, 255));
        btnGiris.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnGiris.setText("Giriş Yap");
        btnGiris.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGiris.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGirisMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGirisMouseExited(evt);
            }
        });
        btnGiris.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGirisActionPerformed(evt);
            }
        });

        btnKayitOl.setBackground(new java.awt.Color(255, 255, 255));
        btnKayitOl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnKayitOl.setText("Kayıt OL");
        btnKayitOl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnKayitOl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnKayitOlMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnKayitOlMouseExited(evt);
            }
        });
        btnKayitOl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKayitOlActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Şifre Oluşturucu");

        sifremiUnuttumLabel.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        sifremiUnuttumLabel.setForeground(new java.awt.Color(255, 255, 255));
        sifremiUnuttumLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sifremiUnuttumLabel.setText("Şifremi Unuttum");
        sifremiUnuttumLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sifremiUnuttumLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sifremiUnuttumLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout girisEkranPanelLayout = new javax.swing.GroupLayout(girisEkranPanel);
        girisEkranPanel.setLayout(girisEkranPanelLayout);
        girisEkranPanelLayout.setHorizontalGroup(
            girisEkranPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(girisEkranPanelLayout.createSequentialGroup()
                .addContainerGap(105, Short.MAX_VALUE)
                .addGroup(girisEkranPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtKullanici, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                    .addComponent(txtSifre)
                    .addComponent(sifremiUnuttumLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGiris, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKayitOl, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(0, 104, Short.MAX_VALUE))
        );
        girisEkranPanelLayout.setVerticalGroup(
            girisEkranPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(girisEkranPanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91)
                .addComponent(txtKullanici, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(txtSifre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sifremiUnuttumLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnGiris, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(btnKayitOl, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(93, Short.MAX_VALUE))
        );

        girisEkranPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, txtKullanici, txtSifre});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(girisEkranPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(girisEkranPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Buton Rengi Ayarları
    
    private void btnGirisMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGirisMouseEntered
        ButonAyarları.setBg(btnGiris, Color.black, Color.white);
    }//GEN-LAST:event_btnGirisMouseEntered

    private void btnGirisMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGirisMouseExited
        ButonAyarları.setOriginalBg(btnGiris, btnGiris.getForeground());
    }//GEN-LAST:event_btnGirisMouseExited

    private void btnKayitOlMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKayitOlMouseEntered
        ButonAyarları.setBg(btnKayitOl, Color.black, Color.white);
    }//GEN-LAST:event_btnKayitOlMouseEntered

    private void btnKayitOlMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKayitOlMouseExited
        ButonAyarları.setOriginalBg(btnKayitOl, btnKayitOl.getForeground());
    }//GEN-LAST:event_btnKayitOlMouseExited

    // Text Ayarları
    
    private void txtKullaniciFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtKullaniciFocusGained
        TextAyarları.checkTheTextFocusGained(txtKullanici, KULLANICI_TEXT);
    }//GEN-LAST:event_txtKullaniciFocusGained

    private void txtKullaniciFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtKullaniciFocusLost
        TextAyarları.checkTheTextFocusLost(txtKullanici);
    }//GEN-LAST:event_txtKullaniciFocusLost

    private void txtSifreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSifreFocusGained
        TextAyarları.checkTheTextFocusGained(txtSifre, SIFRE_TEXT);
    }//GEN-LAST:event_txtSifreFocusGained

    private void txtSifreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSifreFocusLost
        TextAyarları.checkTheTextFocusLost(txtSifre);
    }//GEN-LAST:event_txtSifreFocusLost

    private void btnGirisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGirisActionPerformed
        
    String kullanici = txtKullanici.getText().trim();
    String sifre = new String(txtSifre.getPassword()).trim();

    // KullaniciGiris sınıfından dönen ID
    int id = new KullaniciGiris().girisYap(kullanici, sifre);
    
    if (kullanici.isEmpty() || kullanici.equals(KULLANICI_TEXT)) {
        Dialogs.ozelMesajGoster(this, "Kullanıcı adı boş olamaz!");
        return;
    }
    if (sifre.isEmpty() || sifre.equals(SIFRE_TEXT)) {
        Dialogs.ozelMesajGoster(this, "Şifre alanı boş olamaz!");
        return;
    }

    if (id > 0) {
        // ► Üçüncü parametreyi false veriyoruz, böylece fromSifreler=false
        AnaMenu ana = new AnaMenu(id, kullanici, false);
        ana.setVisible(true);
        this.dispose();
    } else {
        Dialogs.ozelMesajGoster(this, "Kullanıcı adı ya da şifre yanlış");
    }
        
    }//GEN-LAST:event_btnGirisActionPerformed

    private void btnKayitOlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKayitOlActionPerformed
        KayıtOlEkranı kayıt = new KayıtOlEkranı(aktifKullaniciId);
        dispose();
        kayıt.setVisible(true);
    }//GEN-LAST:event_btnKayitOlActionPerformed

    private void sifremiUnuttumLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sifremiUnuttumLabelMouseClicked
         // txtKullanici içindeki "Kullanıcı Adı" placeholder’ı varsa, boş string ata
            String girilen = txtKullanici.getText().trim();
            if (girilen.equals(KULLANICI_TEXT)) {
                girilen = "";
            }
            new SifremiUnuttumEkranı(aktifKullaniciId, girilen).setVisible(true);
            this.dispose();
    }//GEN-LAST:event_sifremiUnuttumLabelMouseClicked

    
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
            java.util.logging.Logger.getLogger(GirisEkranı.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GirisEkranı.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GirisEkranı.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GirisEkranı.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GirisEkranı().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGiris;
    private javax.swing.JButton btnKayitOl;
    private javax.swing.JPanel girisEkranPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel sifremiUnuttumLabel;
    private javax.swing.JTextField txtKullanici;
    private javax.swing.JPasswordField txtSifre;
    // End of variables declaration//GEN-END:variables
}

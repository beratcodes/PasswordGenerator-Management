package gui;

import database.transaction.KullaniciKayit;
import gui.ayarlar.ButonAyarları;
import gui.ayarlar.Dialogs;
import gui.ayarlar.IDuzenleyici;
import gui.ayarlar.TextAyarları;
import java.awt.Color;


public final class KayıtOlEkranı extends javax.swing.JFrame implements IDuzenleyici {

    private int aktifKullaniciId;
    private final String KULLANICI_TEXT = "Kullanıcı Adı";
    private final String SIFRE_TEXT = "Şifre";
    private final String SIFRE_TEKRAR_TEXT = "Şifre";
    private final String GUVENLIK_CEVAP_TEXT = "Cevap";
    
    
    public KayıtOlEkranı() {
        initComponents();
        getEdits();
    }
    
    public KayıtOlEkranı(int aktifKullaniciId) {
        this();
        getEdits();// tek satır ekle
        this.aktifKullaniciId = aktifKullaniciId;
    }

    @Override
    public void getEdits() {
        this.setLocationRelativeTo(null);
        kayıtPanel.setFocusable(true);
        txtKullanici.setText(KULLANICI_TEXT);
        txtSifre.setText(SIFRE_TEXT);
        txtSifreTekrar.setText(SIFRE_TEKRAR_TEXT);
        txtGuvenlikCevap.setText(GUVENLIK_CEVAP_TEXT);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        kayıtPanel = new javax.swing.JPanel();
        txtKullanici = new javax.swing.JTextField();
        txtSifre = new javax.swing.JPasswordField();
        btnKayitOl = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtSifreTekrar = new javax.swing.JPasswordField();
        geriIcon = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cmbGuvenlikSorusu = new javax.swing.JComboBox<>();
        txtGuvenlikCevap = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Kayıt OL");
        setResizable(false);

        kayıtPanel.setBackground(new java.awt.Color(8, 79, 140));

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

        btnKayitOl.setBackground(new java.awt.Color(255, 255, 255));
        btnKayitOl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnKayitOl.setForeground(new java.awt.Color(0, 0, 0));
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

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("KAYIT OL");

        txtSifreTekrar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtSifreTekrar.setForeground(new java.awt.Color(153, 153, 153));
        txtSifreTekrar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSifreTekrarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSifreTekrarFocusLost(evt);
            }
        });

        geriIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/iconlar/undo.png"))); // NOI18N
        geriIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        geriIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                geriIconMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Kişisel Bilgiler");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Güvenlik Bilgileri");

        cmbGuvenlikSorusu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cmbGuvenlikSorusu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Evcil hayvanınızın adı nedir?", "Öğretmeninizin soyadı nedir?", "Yaşamak istediğiniz yer neresidir?", "Ya da rastgele cümle girin (önerilir)" }));

        txtGuvenlikCevap.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtGuvenlikCevap.setForeground(new java.awt.Color(153, 153, 153));
        txtGuvenlikCevap.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtGuvenlikCevapFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtGuvenlikCevapFocusLost(evt);
            }
        });

        javax.swing.GroupLayout kayıtPanelLayout = new javax.swing.GroupLayout(kayıtPanel);
        kayıtPanel.setLayout(kayıtPanelLayout);
        kayıtPanelLayout.setHorizontalGroup(
            kayıtPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kayıtPanelLayout.createSequentialGroup()
                .addGroup(kayıtPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kayıtPanelLayout.createSequentialGroup()
                        .addGap(444, 444, 444)
                        .addComponent(btnKayitOl, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(kayıtPanelLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(geriIcon))
                    .addGroup(kayıtPanelLayout.createSequentialGroup()
                        .addGap(317, 317, 317)
                        .addGroup(kayıtPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtGuvenlikCevap, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(kayıtPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(txtSifreTekrar, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtSifre, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtKullanici, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4)
                                .addComponent(cmbGuvenlikSorusu, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)))))
                .addContainerGap(306, Short.MAX_VALUE))
        );
        kayıtPanelLayout.setVerticalGroup(
            kayıtPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kayıtPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(geriIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(142, 142, 142)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtKullanici, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(txtSifre, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(txtSifreTekrar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(cmbGuvenlikSorusu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(txtGuvenlikCevap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(btnKayitOl, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );

        kayıtPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel3, jLabel4});

        kayıtPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cmbGuvenlikSorusu, txtGuvenlikCevap});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kayıtPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kayıtPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void btnKayitOlMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKayitOlMouseEntered
        ButonAyarları.setBg(btnKayitOl, Color.black, Color.white);
    }//GEN-LAST:event_btnKayitOlMouseEntered

    private void btnKayitOlMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKayitOlMouseExited
        ButonAyarları.setOriginalBg(btnKayitOl, btnKayitOl.getForeground());
    }//GEN-LAST:event_btnKayitOlMouseExited

    private void btnKayitOlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKayitOlActionPerformed
        String kullanici        = txtKullanici.getText().trim();
        String sifre            = String.valueOf(txtSifre.getPassword());
        String sifreTekrar      = String.valueOf(txtSifreTekrar.getPassword());
        String guvenlikSorusu   = String.valueOf(cmbGuvenlikSorusu.getSelectedItem());
        String guvenlikCevap    = txtGuvenlikCevap.getText().trim();

        // 1) Zorunlu alanlar dolu mu?
        if (kullanici.isEmpty() || sifre.isEmpty() || sifreTekrar.isEmpty() || guvenlikCevap.isEmpty()) {
            Dialogs.ozelMesajGoster(this, "Tüm alanlar dolu olmalıdır.");
            return;
        }

        // 2) Kullanıcı adı placeholder mı?
        if (kullanici.equals(KULLANICI_TEXT)) {
            Dialogs.ozelMesajGoster(this, "Lütfen geçerli bir kullanıcı adı giriniz.");
            return;
        }

        // 3) Şifreler uyuşuyor mu?
        if (!sifre.equals(sifreTekrar)) {
            Dialogs.ozelMesajGoster(this, "Şifreler uyuşmuyor.");
            return;
        }

        // 4) Diğer placeholder’lar boş geçilmiş mi?
        if (sifre.equals(SIFRE_TEXT)
                || sifreTekrar.equals(SIFRE_TEKRAR_TEXT)
                || guvenlikCevap.equals(GUVENLIK_CEVAP_TEXT)) {
            Dialogs.bosOlamazMesajıGoster(this);
            return;
        }

    // 5) Kayıt işlemini gerçekleştir
    KullaniciKayit kayitIslemi = new KullaniciKayit();
    boolean sonuc = kayitIslemi.kayitYap(kullanici, sifre, guvenlikSorusu, guvenlikCevap);

    if (sonuc) {
        Dialogs.ozelMesajGoster(this, "Kayıt Başarılı! Giriş ekranına yönlendiriliyorsunuz.");
        GirisEkranı giris = new GirisEkranı(aktifKullaniciId);
        this.dispose();
        giris.setVisible(true);
    } else {
        Dialogs.ozelMesajGoster(this, "Kayıt sırasında bir hata oluştu.");
    }
    }//GEN-LAST:event_btnKayitOlActionPerformed

    private void txtSifreTekrarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSifreTekrarFocusGained
        TextAyarları.checkTheTextFocusGained(txtSifreTekrar, SIFRE_TEKRAR_TEXT);
    }//GEN-LAST:event_txtSifreTekrarFocusGained

    private void txtSifreTekrarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSifreTekrarFocusLost
        TextAyarları.checkTheTextFocusLost(txtSifreTekrar);
    }//GEN-LAST:event_txtSifreTekrarFocusLost

    private void geriIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_geriIconMouseClicked
        GirisEkranı giris = new GirisEkranı(aktifKullaniciId);
        this.dispose();
        giris.setVisible(true);
    }//GEN-LAST:event_geriIconMouseClicked

    private void txtGuvenlikCevapFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGuvenlikCevapFocusGained
        TextAyarları.checkTheTextFocusGained(txtGuvenlikCevap, GUVENLIK_CEVAP_TEXT);
    }//GEN-LAST:event_txtGuvenlikCevapFocusGained

    private void txtGuvenlikCevapFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGuvenlikCevapFocusLost
        TextAyarları.checkTheTextFocusLost(txtGuvenlikCevap);
    }//GEN-LAST:event_txtGuvenlikCevapFocusLost

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
            java.util.logging.Logger.getLogger(KayıtOlEkranı.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KayıtOlEkranı.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KayıtOlEkranı.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KayıtOlEkranı.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KayıtOlEkranı().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKayitOl;
    private javax.swing.JComboBox<String> cmbGuvenlikSorusu;
    private javax.swing.JLabel geriIcon;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel kayıtPanel;
    private javax.swing.JTextField txtGuvenlikCevap;
    private javax.swing.JTextField txtKullanici;
    private javax.swing.JPasswordField txtSifre;
    private javax.swing.JPasswordField txtSifreTekrar;
    // End of variables declaration//GEN-END:variables
}

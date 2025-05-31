package gui;

import gui.ayarlar.ButonAyarları;               // Buton renk ve hover ayarları
import gui.ayarlar.IDuzenleyici;               // Ekran düzenleme sözleşmesi için
import java.awt.Color;                         // Renk sınıfı

// **Yeni import’lar**
import database.DbConnection;                  // Veritabanı bağlantısı için
import database.transaction.BilgileriGuncelle; // Hesap bilgisi çekme/güncelleme işlemleri
import database.transaction.HesapSil;          // Hesap silme işlemi için
import gui.ayarlar.Dialogs;                    // Uyarı ve onay mesajları
import gui.ayarlar.IconAyarları;               // İkon değiştirme kontrolleri
import java.sql.Connection;                    // JDBC Connection sınıfı
import java.sql.Statement;                     // JDBC Statement sınıfı
import java.sql.SQLException;                  // SQL hataları için
import javax.swing.JOptionPane;                // Swing dialog pencereleri

public class HesapYonetimEkranı extends javax.swing.JFrame implements IDuzenleyici {

   private int aktifKullaniciId;               // Oturum açmış kullanıcı ID’si
    private final String aktifKullaniciAdi;     // Oturum açmış kullanıcı adı
    private String originalUsername;             // İlk tıklamada saklanacak orijinal kullanıcı adı
    private final DbConnection db = new DbConnection();  // Veritabanı bağlantısı objesi

    // Parametresiz constructor: placeholder vs. ayarları yap
    public HesapYonetimEkranı() {
        initComponents();                       // UI bileşenlerini başlat
        getEdits();                             // Ortak düzenlemeleri uygula
        this.aktifKullaniciAdi = "";           // Kullanıcı adı boş olarak başlat
    }

    // ID ve kullanıcı adıyla çağırılan constructor
    public HesapYonetimEkranı(int aktifKullaniciId, String aktifKullaniciAdi) {
        initComponents();                       // UI bileşenlerini başlat
        getEdits();                             // Ortak düzenlemeleri uygula
        this.aktifKullaniciId  = aktifKullaniciId;   // Gelen ID’yi ata
        this.aktifKullaniciAdi = aktifKullaniciAdi;  // Gelen kullanıcı adını ata

        // <<< Burayı ekliyoruz >>>
        try {
            // 1) Yeni DbConnection ile bağlantı oluştur
            DbConnection dbConn = new DbConnection();
            Connection conn    = dbConn.getConnection();
            Statement stmt     = conn.createStatement();

            // 2) Veritabanından mevcut kullanıcı adı ve şifreyi çek
            String dbAdi   = BilgileriGuncelle.getKullaniciAdi(stmt, aktifKullaniciId);
            String dbSifre = BilgileriGuncelle.getSifre(stmt, aktifKullaniciId);

            // 3) Eğer DB’den boş dönmediyse, çekilen değerleri ekranda göster
            if (!dbAdi.isEmpty()) {
                txtKullaniciAdi.setText(dbAdi);
                txtSifre.setText(dbSifre);
            } else {
                // DB boş döndüyse, parametreyle gelen kullanıcı adını göster
                txtKullaniciAdi.setText(this.aktifKullaniciAdi);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();               // Hata detayını konsola yaz
            // Hata durumunda da parametreyle gelen adı kullan
            txtKullaniciAdi.setText(this.aktifKullaniciAdi);
        }
        // <<< Ekleme sonu >>>
    }

    @Override
    public void getEdits() {
        this.setLocationRelativeTo(null);       // Pencereyi ekranın ortasına yerleştir
        HesapYonetimPanel.setFocusable(true);   // Panelin odaklanabilir olmasını sağla
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        HesapYonetimPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtKullaniciAdi = new javax.swing.JTextField();
        txtSifre = new javax.swing.JPasswordField();
        btnHesapSil = new javax.swing.JButton();
        geriIcon = new javax.swing.JLabel();
        updateIcon = new javax.swing.JLabel();
        updateIcon1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hesap Yönetim Ekranı");
        setResizable(false);

        HesapYonetimPanel.setBackground(new java.awt.Color(8, 79, 140));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Hesap Ayarları");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Kişisel Bilgiler");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Kullanıcı Adı :");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Şifre :");

        txtKullaniciAdi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtKullaniciAdi.setEnabled(false);

        txtSifre.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtSifre.setEnabled(false);

        btnHesapSil.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnHesapSil.setText("Hesabımı Sil");
        btnHesapSil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHesapSil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHesapSilMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHesapSilMouseExited(evt);
            }
        });
        btnHesapSil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHesapSilActionPerformed(evt);
            }
        });

        geriIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        geriIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/iconlar/previous (1).png"))); // NOI18N
        geriIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        geriIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                geriIconMouseClicked(evt);
            }
        });

        updateIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        updateIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/iconlar/update.png"))); // NOI18N
        updateIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        updateIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateIconMouseClicked(evt);
            }
        });

        updateIcon1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        updateIcon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/iconlar/update.png"))); // NOI18N
        updateIcon1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        updateIcon1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateIcon1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout HesapYonetimPanelLayout = new javax.swing.GroupLayout(HesapYonetimPanel);
        HesapYonetimPanel.setLayout(HesapYonetimPanelLayout);
        HesapYonetimPanelLayout.setHorizontalGroup(
            HesapYonetimPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HesapYonetimPanelLayout.createSequentialGroup()
                .addGroup(HesapYonetimPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HesapYonetimPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(geriIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(120, 120, 120)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(HesapYonetimPanelLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(HesapYonetimPanelLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(HesapYonetimPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(HesapYonetimPanelLayout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtKullaniciAdi, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(HesapYonetimPanelLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(HesapYonetimPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnHesapSil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtSifre, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(HesapYonetimPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updateIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        HesapYonetimPanelLayout.setVerticalGroup(
            HesapYonetimPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HesapYonetimPanelLayout.createSequentialGroup()
                .addGroup(HesapYonetimPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HesapYonetimPanelLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(HesapYonetimPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(geriIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(63, 63, 63)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(113, 113, 113)
                .addGroup(HesapYonetimPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HesapYonetimPanelLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(updateIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(HesapYonetimPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtKullaniciAdi, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(54, 54, 54)
                .addGroup(HesapYonetimPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(updateIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(HesapYonetimPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSifre, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37)
                .addComponent(btnHesapSil, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(207, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(HesapYonetimPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(HesapYonetimPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHesapSilMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHesapSilMouseEntered
        ButonAyarları.setBg(btnHesapSil, Color.red, Color.white);
    }//GEN-LAST:event_btnHesapSilMouseEntered

    private void btnHesapSilMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHesapSilMouseExited
        ButonAyarları.setOriginalBg(btnHesapSil, getForeground());
    }//GEN-LAST:event_btnHesapSilMouseExited

    private void geriIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_geriIconMouseClicked
        AnaMenu form = new AnaMenu(aktifKullaniciId, aktifKullaniciAdi);
        this.dispose();
        form.setVisible(true);
    }//GEN-LAST:event_geriIconMouseClicked
    
    private int clickCounter = 0;
    private void updateIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateIconMouseClicked
        if (clickCounter == 0) { 
        // 1. tıklama: sadece düzenlemeyi aç ve orijinali sakla
        originalUsername = txtKullaniciAdi.getText().trim();
        txtKullaniciAdi.setEnabled(true);
        IconAyarları.changeIcon(updateIcon, "accept");
        clickCounter++;
        
    } else { 
        // 2. tıklama: önce edit’i kapat, ikon’u eski haline getir
        txtKullaniciAdi.setEnabled(false);
        IconAyarları.setOriginalIcon(updateIcon);

        // 2a) Eğer değişiklik yoksa sadece bilgi ver, sıfırla ve çık
        String yeniAdi = txtKullaniciAdi.getText().trim();
        if (yeniAdi.equals(originalUsername)) {
            Dialogs.ozelMesajGoster(this, "Kullanıcı adı değiştirilmedi.");
            clickCounter = 0;
            return;
        }

        // 2b) Gerçekten değiştiyse veritabanına kaydet
        try {
            // 1) Connection/Statement al
            Connection conn = new database.DbConnection().getConnection();
            Statement stmt = conn.createStatement();

            // 2) Güncelle
            boolean ok = database.transaction.KullaniciAdiDegistir
                              .kullaniciAdiGuncelle(stmt, aktifKullaniciId, yeniAdi);

            // 3) Sonucu göster
            if (ok) {
                Dialogs.ozelMesajGoster(
                    this, 
                    "Kullanıcı adı güncellendi."
                );
            } else {
                Dialogs.ozelMesajGoster(this, "Güncelleme başarısız.");
            }

            // 4) Kaynakları kapat
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            Dialogs.ozelMesajGoster(this, "Veritabanı hatası: " + ex.getMessage());
        }

        // 5) Son olarak counter’ı sıfırla
        clickCounter = 0;
    }
    }//GEN-LAST:event_updateIconMouseClicked

    private int clickCounterSifre = 0;
    private void updateIcon1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateIcon1MouseClicked
        if (clickCounterSifre == 0) {
        //… txtSifre.enable falan vs.
        IconAyarları.changeIcon(updateIcon1, "accept");

        // <<< işte burayı değiştiriyoruz >>>
        SifremiUnuttumEkranı sifreUnuttum = new SifremiUnuttumEkranı(
            aktifKullaniciId,
            aktifKullaniciAdi,
            true
        );
        this.dispose();
        sifreUnuttum.setVisible(true);

        clickCounterSifre++;
    } else {
        txtSifre.setEnabled(false);
        IconAyarları.setOriginalIcon(updateIcon1);
        clickCounterSifre = 0;
    }
    }//GEN-LAST:event_updateIcon1MouseClicked

    private void btnHesapSilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHesapSilActionPerformed
         // ── A) Önce onay sor ────────────────────────────────────────────
    int secim = JOptionPane.showConfirmDialog(
        this,
        "Bu işlem geri getirilemez. Hesabınızı silmek istediğinize emin misiniz?",
        "Hesap Silme Onayı",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.WARNING_MESSAGE
    );
    if (secim != JOptionPane.YES_OPTION) {
        return; // kullanıcı “Hayır” dedi, işlem iptal.
    }

    // ── B) Onaylandıysa silme işlemleri ─────────────────────────────
    try {
        // 1) Varolan DbConnection objenizden Connection / Statement alın
        Connection conn = db.getConnection();
        Statement stmt = conn.createStatement();

        // 2) [YENİ] Bu kullanıcıya ait tüm uygulamaları sil
        stmt.executeUpdate(
            "DELETE FROM uygulamalar WHERE kullanici_id = " + aktifKullaniciId
        );

        // 3) Orijinal hesap silme metodunu çağır
        boolean ok = HesapSil.hesapSil(stmt, aktifKullaniciId);

        // 4) Sonucu kullanıcıya göster
        if (ok) {
            Dialogs.ozelMesajGoster(this, "Hesabınız başarıyla silindi.");
            new GirisEkranı().setVisible(true);
            this.dispose();
        } else {
            Dialogs.ozelMesajGoster(this, "Hesap silme işlemi başarısız.");
        }

        // 5) Kaynakları kapat
        stmt.close();
        conn.close();

    } catch (SQLException ex) {
        ex.printStackTrace();
        Dialogs.ozelMesajGoster(this, "Veritabanı hatası: " + ex.getMessage());
    }
    }//GEN-LAST:event_btnHesapSilActionPerformed

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
            java.util.logging.Logger.getLogger(HesapYonetimEkranı.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HesapYonetimEkranı.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HesapYonetimEkranı.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HesapYonetimEkranı.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HesapYonetimEkranı().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel HesapYonetimPanel;
    private javax.swing.JButton btnHesapSil;
    private javax.swing.JLabel geriIcon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtKullaniciAdi;
    private javax.swing.JPasswordField txtSifre;
    private javax.swing.JLabel updateIcon;
    private javax.swing.JLabel updateIcon1;
    // End of variables declaration//GEN-END:variables
}

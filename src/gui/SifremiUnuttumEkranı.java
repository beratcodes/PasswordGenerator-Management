package gui;

import database.transaction.SifreYenile;
import gui.ayarlar.ButonAyarları;
import gui.ayarlar.Dialogs;
import gui.ayarlar.IDuzenleyici;
import gui.ayarlar.TextAyarları;
import java.awt.Color;
import java.sql.*;


public class SifremiUnuttumEkranı extends javax.swing.JFrame implements IDuzenleyici {

    private int     aktifKullaniciId;           // Oturum açmış kullanıcı ID’si
    private final String aktifKullaniciAdi;     // Oturum açmış kullanıcı adı
    private final boolean fromYonetim;          // Yönetim ekranından gelip gelmediği bayrağı

    // Parametresiz constructor: varsayılan yönlendirme false
    public SifremiUnuttumEkranı() {
        this(0, "", false);                     // ID=0, ad="", yönetim flag=false
    }

    // Kullanıcı ve ad bilgisi ile çağırılan constructor: fromYonetim=false
    public SifremiUnuttumEkranı(int aktifKullaniciId, String aktifKullaniciAdi) {
        this(aktifKullaniciId, aktifKullaniciAdi, false);
    }

    // Asıl constructor: tüm parametreler
    public SifremiUnuttumEkranı(int aktifKullaniciId, String aktifKullaniciAdi, boolean fromYonetim) {
        initComponents();                       // UI bileşenlerini başlat
        getEdits();                             // Placeholder ve düzenlemeleri uygula
        this.aktifKullaniciId  = aktifKullaniciId;  // Gelen ID’yi ata
        this.aktifKullaniciAdi = aktifKullaniciAdi; // Gelen kullanıcı adını ata
        this.fromYonetim       = fromYonetim;       // Bayrağı ata

        // Eğer geçerli bir kullanıcı adı geldiyse placeholder yerine yaz
        if (aktifKullaniciAdi != null && !aktifKullaniciAdi.trim().isEmpty()) {
            txtKullaniciAdi.setText(aktifKullaniciAdi);
        } else {
            txtKullaniciAdi.setText("");         // Boşsa alanı temizle
        }
    }

    @Override
    public void getEdits() {
        this.setLocationRelativeTo(null);        // Pencereyi ekranın ortasına yerleştir
        SifremiUnuttumPanel.setFocusable(true);  // Paneli odaklanabilir yap
        TextAyarları.setMaximumLimit(txtGuvenlikCevap, 75); // Güvenlik cevabına 75 karakter limiti
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SifremiUnuttumPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtYeniSifre = new javax.swing.JPasswordField();
        txtSifreTekrar = new javax.swing.JPasswordField();
        txtGuvenlikCevap = new javax.swing.JTextField();
        btnSifreYenile = new javax.swing.JButton();
        geriIcon = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtKullaniciAdi = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Şifre Yenile");
        setResizable(false);

        SifremiUnuttumPanel.setBackground(new java.awt.Color(8, 79, 140));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Şifre Yenile");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Yeni Şifre :");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Yeni Şifre Tekrar :");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Güvenlik Sorusu Cevabı :");

        txtYeniSifre.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        txtSifreTekrar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        txtGuvenlikCevap.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        btnSifreYenile.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSifreYenile.setText("Şifreyi Yenile");
        btnSifreYenile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSifreYenile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSifreYenileMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSifreYenileMouseExited(evt);
            }
        });
        btnSifreYenile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSifreYenileActionPerformed(evt);
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

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Kullanıcı Adı :");

        txtKullaniciAdi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtKullaniciAdi.setEnabled(false);

        javax.swing.GroupLayout SifremiUnuttumPanelLayout = new javax.swing.GroupLayout(SifremiUnuttumPanel);
        SifremiUnuttumPanel.setLayout(SifremiUnuttumPanelLayout);
        SifremiUnuttumPanelLayout.setHorizontalGroup(
            SifremiUnuttumPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SifremiUnuttumPanelLayout.createSequentialGroup()
                .addGroup(SifremiUnuttumPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SifremiUnuttumPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(geriIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(129, 129, 129)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(SifremiUnuttumPanelLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(SifremiUnuttumPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(SifremiUnuttumPanelLayout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtSifreTekrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(SifremiUnuttumPanelLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addGroup(SifremiUnuttumPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnSifreYenile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtGuvenlikCevap, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)))
                            .addGroup(SifremiUnuttumPanelLayout.createSequentialGroup()
                                .addGroup(SifremiUnuttumPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(SifremiUnuttumPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtYeniSifre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtKullaniciAdi, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(116, Short.MAX_VALUE))
        );

        SifremiUnuttumPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel2, jLabel3, jLabel4, txtGuvenlikCevap, txtKullaniciAdi, txtSifreTekrar, txtYeniSifre});

        SifremiUnuttumPanelLayout.setVerticalGroup(
            SifremiUnuttumPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SifremiUnuttumPanelLayout.createSequentialGroup()
                .addGroup(SifremiUnuttumPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SifremiUnuttumPanelLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(SifremiUnuttumPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(geriIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addGroup(SifremiUnuttumPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtKullaniciAdi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addGroup(SifremiUnuttumPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtYeniSifre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62)
                .addGroup(SifremiUnuttumPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSifreTekrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64)
                .addGroup(SifremiUnuttumPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGuvenlikCevap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(75, 75, 75)
                .addComponent(btnSifreYenile, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114))
        );

        SifremiUnuttumPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel2, jLabel3, jLabel4, txtGuvenlikCevap, txtKullaniciAdi, txtSifreTekrar, txtYeniSifre});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SifremiUnuttumPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SifremiUnuttumPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSifreYenileMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSifreYenileMouseEntered
        ButonAyarları.setBg(btnSifreYenile, Color.black, Color.white);
    }//GEN-LAST:event_btnSifreYenileMouseEntered

    private void btnSifreYenileMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSifreYenileMouseExited
        ButonAyarları.setOriginalBg(btnSifreYenile, getForeground());
    }//GEN-LAST:event_btnSifreYenileMouseExited

    private void geriIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_geriIconMouseClicked
       if (fromYonetim) {
            // HesapYönetim’den geldiysek oraya döner
            new HesapYonetimEkranı(aktifKullaniciId, aktifKullaniciAdi)
                .setVisible(true);
        } else {
            // Giriş ekranından geldiysek girişe döner
            GirisEkranı giris = new GirisEkranı(aktifKullaniciId);
            giris.setKullaniciAdi(aktifKullaniciAdi);
            giris.setVisible(true);
        }
        this.dispose();
    }//GEN-LAST:event_geriIconMouseClicked

    private void btnSifreYenileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSifreYenileActionPerformed
    String kullaniciAdi    = txtKullaniciAdi.getText().trim();
    String yeniSifre       = new String(txtYeniSifre.getPassword());
    String yeniSifreTekrar = new String(txtSifreTekrar.getPassword());
    String guvenlikCevap   = txtGuvenlikCevap.getText().trim();

    if (kullaniciAdi.isEmpty() || yeniSifre.isEmpty() || yeniSifreTekrar.isEmpty() || guvenlikCevap.isEmpty()) {
        Dialogs.ozelMesajGoster(this, "Lütfen tüm alanları doldurunuz.");
        return;
    }
    if (!yeniSifre.equals(yeniSifreTekrar)) {
        Dialogs.ozelMesajGoster(this, "Şifreler uyuşmuyor.");
        return;
    }

    try {
        SifreYenile sy = new SifreYenile(kullaniciAdi);
        boolean success = sy.sifreYenile(guvenlikCevap, yeniSifre);
        if (success) {
            Dialogs.ozelMesajGoster(this, "Şifreniz başarıyla güncellendi.");
            GirisEkranı giris = new GirisEkranı();
            giris.setKullaniciAdi(kullaniciAdi);
            this.dispose();
            giris.setVisible(true);
        } else {
            Dialogs.ozelMesajGoster(this, "Güvenlik cevabı yanlış veya güncelleme başarısız.");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        Dialogs.ozelMesajGoster(this, "Veritabanı hatası: " + ex.getMessage());
    }
    }//GEN-LAST:event_btnSifreYenileActionPerformed

    
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
            java.util.logging.Logger.getLogger(SifreYenile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SifreYenile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SifreYenile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SifreYenile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SifremiUnuttumEkranı().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel SifremiUnuttumPanel;
    private javax.swing.JButton btnSifreYenile;
    private javax.swing.JLabel geriIcon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtGuvenlikCevap;
    private javax.swing.JTextField txtKullaniciAdi;
    private javax.swing.JPasswordField txtSifreTekrar;
    private javax.swing.JPasswordField txtYeniSifre;
    // End of variables declaration//GEN-END:variables
}

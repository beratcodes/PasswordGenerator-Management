package gui;

import database.DbConnection;                           // Veritabanı bağlantısı için
import gui.ayarlar.ButonAyarları;                       // Buton renk-içi ayarları için
import gui.ayarlar.Dialogs;                             // Mesaj kutuları için
import gui.ayarlar.IDuzenleyici;                         // Ekran düzenleme metodunu tanımlar
import gui.ayarlar.IconAyarları;                        // İkon değiştirme kontrolleri için
import gui.ayarlar.TextAyarları;                        // TextField doğrulama ve placeholder için
import java.awt.Color;                                  // Renk ayarları için
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import util.AESUtil;                                    // Şifreleme işlemleri için
import database.transaction.SifreGuncelle;               // Şifre güncelleme sorgusu için
import database.transaction.UygulamaSil;                 // Uygulama silme sorgusu için
import java.util.List;
import javax.swing.JOptionPane;
import java.util.Random;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class AnaMenu extends javax.swing.JFrame implements IDuzenleyici {

    private final int aktifKullaniciId;                   // Oturum açmış kullanıcı ID’si
    private final String aktifKullaniciAdi;               // Oturum açmış kullanıcı adı
    private Integer secilenSifreId = null;                // Güncellenecek şifre kaydı ID’si
    private final String SIFRE_UZUNLUGU_TEXT = "Şifre Uzunluğu Giriniz MAX: 30"; // Placeholder
    private final String SIFRE_TEXT           = "Oluşturulan Şifre";              // Placeholder
    private final String KULLANICI_ADI_TEXT   = "Kullanıcı adınızı giriniz";      // Placeholder
    private final boolean fromSifreler;                   // Şifreler ekranından gelip gelmediği

    DbConnection db = new DbConnection();                 // Veritabanı bağlantı objesi
    private int sifreUzunluk = 0;                         // Girilen uzunluk değeri

    // Ana constructor: ID, kullanıcı adı ve yönlendirme bayrağı
    public AnaMenu(int kullaniciId, String kullaniciAdi, boolean fromSifreler) {
        initComponents();                                 // UI bileşenlerini başlat
        this.aktifKullaniciId   = kullaniciId;           // Aktif ID ata
        this.aktifKullaniciAdi  = kullaniciAdi;          // Aktif ad ata
        this.fromSifreler       = fromSifreler;          // Bayrağı ata

        getEdits();                                       // Placeholder ve limit ayarları
        LblKullaniciAdi.setText("Hoşgeldin! " + kullaniciAdi); // Hoş geldin mesajı

        loadUygulamalar();                                // Uygulama listesini doldur
        // Tarih spinner modelini ayarla
        spinnerTarih.setModel(new javax.swing.SpinnerDateModel());
        spinnerTarih.setEditor(new javax.swing.JSpinner.DateEditor(spinnerTarih, "yyyy-MM-dd HH:mm:ss"));
        spinnerTarih.setValue(new java.util.Date());     // Şu anki tarihi ata
        spinnerTarih.setEnabled(false);                   // Düzenlemeyi kapat
    }

    // İki parametreli eski constructor: fromSifreler=false olarak yönlendir
    public AnaMenu(int kullaniciId, String kullaniciAdi) {
        this(kullaniciId, kullaniciAdi, false);
    }

    // Tek parametreli eski constructor: kullanıcı adı boş
    public AnaMenu(int kullaniciId) {
        this(kullaniciId, "", false);
    }

    @Override
    public void getEdits() {
        this.setLocationRelativeTo(null);                  // Pencereyi ortala
        anaMenuPanel.setFocusable(true);                   // Panel odaklanabilir olsun
        txtSifreUzunluk.setText(SIFRE_UZUNLUGU_TEXT);      // Uzunluk placeholder
        txtSifre.setText(SIFRE_TEXT);                      // Şifre placeholder
        txtKullaniciAdi.setText(KULLANICI_ADI_TEXT);       // Kullanıcı adı placeholder
        TextAyarları.setOnlyNumber(txtSifreUzunluk);       // Sadece rakam girilebilir
        TextAyarları.setMaximumLimit(txtSifreUzunluk, 2);  // En fazla 2 hane
        TextAyarları.setMaximumLimit(txtSifre, 30);        // Şifre max 30 karakter
    }

    // Uygulama isimlerini veritabanından çekip ComboBox'a ekler
    public void loadUygulamalar() {
        cmbUygulamaAdi.removeAllItems();                   // Önce temizle
        try {
            List<String> liste = database.transaction.UygulamaEkle.getList(aktifKullaniciId);
            for (String ad : liste) {
                cmbUygulamaAdi.addItem(ad);                // Her ismi ekle
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                "Uygulamaları yüklerken hata: " + ex.getMessage(),
                "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Setter metodları: dışarıdan formu doldurmak için
    public void setSelectedSifreId(int id) {
        this.secilenSifreId = id; // Güncellenecek ID’yi sakla
    }

    public void setUygulamaAdi(String app) {
        cmbUygulamaAdi.setSelectedItem(app); // ComboBox seçimini ayarla
    }

    public void setKullaniciAdi(String acc) {
        txtKullaniciAdi.setText(acc);                     // TextField’e kullanıcı adını yaz
    }

    public void setSifre(String sifre) {
        txtSifre.setText(sifre);                           // Oluşturulan şifreyi göster
    }

    public void setSifreUzunluk(String len) {
        txtSifreUzunluk.setText(len);                      // Uzunluk alanını doldur
    }

    // Güncelleme moduna geçer: buton metnini değiştirir
    public void enterGuncellemeModu() {
        btnSifreyiKaydet.setText("ŞİFREYİ GÜNCELLE");      // Butonu güncelle yaz
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        anaMenuPanel = new javax.swing.JPanel();
        txtSifre = new javax.swing.JTextField();
        txtSifreUzunluk = new javax.swing.JTextField();
        btnBuyukHarf = new javax.swing.JToggleButton();
        btnKucukHarf = new javax.swing.JToggleButton();
        btnSembol = new javax.swing.JToggleButton();
        btnRakam = new javax.swing.JToggleButton();
        jLabel2 = new javax.swing.JLabel();
        btnSifreOlustur = new javax.swing.JButton();
        kopyalaIcon = new javax.swing.JLabel();
        btnSifreyiKaydet = new javax.swing.JButton();
        LblSifreUzunlukBilgi = new javax.swing.JLabel();
        LblSifreDurum = new javax.swing.JLabel();
        geriIcon = new javax.swing.JLabel();
        txtKullaniciAdi = new javax.swing.JTextField();
        pbSifreBar = new javax.swing.JProgressBar();
        spinnerTarih = new javax.swing.JSpinner();
        btnSifreYoneticisi = new javax.swing.JButton();
        hesapAyarlarıIcon = new javax.swing.JLabel();
        cmbUygulamaAdi = new javax.swing.JComboBox<>();
        addItemIcon = new javax.swing.JLabel();
        LblKullaniciAdi = new javax.swing.JLabel();
        btnTercih = new javax.swing.JToggleButton();
        trashIcon = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Şifre Oluşturucu");
        setResizable(false);

        anaMenuPanel.setBackground(new java.awt.Color(8, 79, 140));

        txtSifre.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtSifre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSifreFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSifreFocusLost(evt);
            }
        });
        txtSifre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSifreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSifreKeyTyped(evt);
            }
        });

        txtSifreUzunluk.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtSifreUzunluk.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSifreUzunlukFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSifreUzunlukFocusLost(evt);
            }
        });
        txtSifreUzunluk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSifreUzunlukKeyReleased(evt);
            }
        });

        btnBuyukHarf.setBackground(new java.awt.Color(255, 204, 204));
        btnBuyukHarf.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnBuyukHarf.setText("BÜYÜK HARF");
        btnBuyukHarf.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuyukHarf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBuyukHarfMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBuyukHarfMouseExited(evt);
            }
        });

        btnKucukHarf.setBackground(new java.awt.Color(255, 255, 204));
        btnKucukHarf.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnKucukHarf.setText("KÜÇÜK HARF");
        btnKucukHarf.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnKucukHarf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnKucukHarfMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnKucukHarfMouseExited(evt);
            }
        });

        btnSembol.setBackground(new java.awt.Color(204, 255, 255));
        btnSembol.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSembol.setText("SEMBOL");
        btnSembol.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSembol.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSembolMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSembolMouseExited(evt);
            }
        });

        btnRakam.setBackground(new java.awt.Color(204, 255, 204));
        btnRakam.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnRakam.setText("RAKAM");
        btnRakam.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRakam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRakamMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRakamMouseExited(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Şifre Oluşturucu");

        btnSifreOlustur.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSifreOlustur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/iconlar/password.png"))); // NOI18N
        btnSifreOlustur.setText("ŞİFRE OLUŞTUR");
        btnSifreOlustur.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSifreOlustur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSifreOlusturMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSifreOlusturMouseExited(evt);
            }
        });
        btnSifreOlustur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSifreOlusturActionPerformed(evt);
            }
        });

        kopyalaIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        kopyalaIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/iconlar/paste.png"))); // NOI18N
        kopyalaIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        kopyalaIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kopyalaIconMouseClicked(evt);
            }
        });

        btnSifreyiKaydet.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSifreyiKaydet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/iconlar/save.png"))); // NOI18N
        btnSifreyiKaydet.setText("ŞİFREYİ KAYDET");
        btnSifreyiKaydet.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSifreyiKaydet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSifreyiKaydetMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSifreyiKaydetMouseExited(evt);
            }
        });
        btnSifreyiKaydet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSifreyiKaydetActionPerformed(evt);
            }
        });

        LblSifreUzunlukBilgi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LblSifreUzunlukBilgi.setForeground(new java.awt.Color(255, 255, 255));
        LblSifreUzunlukBilgi.setText("Şifre Uzunluğu Giriniz MAX: 30 karakter");

        LblSifreDurum.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LblSifreDurum.setForeground(new java.awt.Color(255, 255, 255));
        LblSifreDurum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        geriIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        geriIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/iconlar/left-arrow.png"))); // NOI18N
        geriIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        geriIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                geriIconMouseClicked(evt);
            }
        });

        txtKullaniciAdi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtKullaniciAdi.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtKullaniciAdiFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtKullaniciAdiFocusLost(evt);
            }
        });

        spinnerTarih.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        spinnerTarih.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.darkGray, java.awt.Color.lightGray, java.awt.Color.white, java.awt.Color.black));

        btnSifreYoneticisi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSifreYoneticisi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/iconlar/management.png"))); // NOI18N
        btnSifreYoneticisi.setText("Şifre Yöneticisi");
        btnSifreYoneticisi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSifreYoneticisi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSifreYoneticisiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSifreYoneticisiMouseExited(evt);
            }
        });
        btnSifreYoneticisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSifreYoneticisiActionPerformed(evt);
            }
        });

        hesapAyarlarıIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hesapAyarlarıIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/iconlar/profile (2).png"))); // NOI18N
        hesapAyarlarıIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hesapAyarlarıIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hesapAyarlarıIconMouseClicked(evt);
            }
        });

        cmbUygulamaAdi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        addItemIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addItemIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/iconlar/add.png"))); // NOI18N
        addItemIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addItemIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addItemIconMouseClicked(evt);
            }
        });

        LblKullaniciAdi.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        LblKullaniciAdi.setForeground(new java.awt.Color(255, 255, 255));
        LblKullaniciAdi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblKullaniciAdi.setText("[Kullanıcı adı]");

        btnTercih.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTercih.setText("Kendim Şifre Oluşturmak İstiyorum");
        btnTercih.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTercih.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnTercihMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnTercihMouseExited(evt);
            }
        });
        btnTercih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTercihActionPerformed(evt);
            }
        });

        trashIcon.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        trashIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/iconlar/bin.png"))); // NOI18N
        trashIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        trashIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                trashIconMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout anaMenuPanelLayout = new javax.swing.GroupLayout(anaMenuPanel);
        anaMenuPanel.setLayout(anaMenuPanelLayout);
        anaMenuPanelLayout.setHorizontalGroup(
            anaMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(anaMenuPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(geriIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(375, 375, 375)
                .addComponent(jLabel2)
                .addGap(345, 345, 345)
                .addComponent(hesapAyarlarıIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, anaMenuPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(anaMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, anaMenuPanelLayout.createSequentialGroup()
                        .addGroup(anaMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(anaMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(anaMenuPanelLayout.createSequentialGroup()
                                    .addGroup(anaMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(anaMenuPanelLayout.createSequentialGroup()
                                            .addGroup(anaMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtKullaniciAdi, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtSifre, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(6, 6, 6)
                                            .addComponent(kopyalaIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(pbSifreBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(LblSifreDurum, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(spinnerTarih, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(anaMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(btnTercih, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtSifreUzunluk, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)))
                                    .addGap(91, 91, 91))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, anaMenuPanelLayout.createSequentialGroup()
                                    .addGap(28, 28, 28)
                                    .addComponent(LblSifreUzunlukBilgi)))
                            .addGroup(anaMenuPanelLayout.createSequentialGroup()
                                .addComponent(cmbUygulamaAdi, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(addItemIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(trashIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(anaMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(anaMenuPanelLayout.createSequentialGroup()
                                .addComponent(btnBuyukHarf, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(btnKucukHarf, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(anaMenuPanelLayout.createSequentialGroup()
                                .addGap(124, 124, 124)
                                .addComponent(btnSifreYoneticisi, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(anaMenuPanelLayout.createSequentialGroup()
                                .addGroup(anaMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnRakam, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(anaMenuPanelLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(btnSifreOlustur)))
                                .addGap(44, 44, 44)
                                .addGroup(anaMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnSifreyiKaydet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnSembol, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(79, 79, 79))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, anaMenuPanelLayout.createSequentialGroup()
                        .addComponent(LblKullaniciAdi, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(270, 270, 270))))
        );
        anaMenuPanelLayout.setVerticalGroup(
            anaMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(anaMenuPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(anaMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(geriIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(anaMenuPanelLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(hesapAyarlarıIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(LblKullaniciAdi, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTercih, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(anaMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(anaMenuPanelLayout.createSequentialGroup()
                        .addComponent(LblSifreUzunlukBilgi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSifreUzunluk, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addGroup(anaMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbUygulamaAdi, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addItemIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(trashIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(anaMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(anaMenuPanelLayout.createSequentialGroup()
                                .addComponent(txtKullaniciAdi, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(txtSifre, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(anaMenuPanelLayout.createSequentialGroup()
                                .addGap(90, 90, 90)
                                .addComponent(kopyalaIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10)
                        .addComponent(pbSifreBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(LblSifreDurum, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(spinnerTarih, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(anaMenuPanelLayout.createSequentialGroup()
                        .addGroup(anaMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBuyukHarf, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnKucukHarf, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(anaMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnRakam, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSembol, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(62, 62, 62)
                        .addGroup(anaMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSifreOlustur, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSifreyiKaydet, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(71, 71, 71)
                        .addComponent(btnSifreYoneticisi, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(93, Short.MAX_VALUE))
        );

        anaMenuPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {addItemIcon, trashIcon});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(anaMenuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(anaMenuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Buton Renk Ayarları
    
    private void btnBuyukHarfMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuyukHarfMouseEntered
        ButonAyarları.setBgToggle(btnBuyukHarf, Color.black, Color.white);
    }//GEN-LAST:event_btnBuyukHarfMouseEntered

    private void btnBuyukHarfMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuyukHarfMouseExited
        ButonAyarları.setOriginalToggle(btnBuyukHarf, getForeground());
    }//GEN-LAST:event_btnBuyukHarfMouseExited

    private void btnKucukHarfMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKucukHarfMouseEntered
        ButonAyarları.setBgToggle(btnKucukHarf, Color.black, Color.white);
    }//GEN-LAST:event_btnKucukHarfMouseEntered

    private void btnKucukHarfMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKucukHarfMouseExited
        ButonAyarları.setOriginalToggle(btnKucukHarf, getForeground());
    }//GEN-LAST:event_btnKucukHarfMouseExited

    private void btnRakamMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRakamMouseEntered
        ButonAyarları.setBgToggle(btnRakam, Color.black, Color.white);
    }//GEN-LAST:event_btnRakamMouseEntered

    private void btnRakamMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRakamMouseExited
        ButonAyarları.setOriginalToggle(btnRakam, getForeground());
    }//GEN-LAST:event_btnRakamMouseExited

    private void btnSembolMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSembolMouseEntered
        ButonAyarları.setBgToggle(btnSembol, Color.black, Color.white);
    }//GEN-LAST:event_btnSembolMouseEntered

    private void btnSembolMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSembolMouseExited
        ButonAyarları.setOriginalToggle(btnSembol, getForeground());
    }//GEN-LAST:event_btnSembolMouseExited

    private void btnSifreOlusturMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSifreOlusturMouseEntered
        ButonAyarları.setBg(btnSifreOlustur, Color.black, Color.white);
    }//GEN-LAST:event_btnSifreOlusturMouseEntered

    private void btnSifreOlusturMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSifreOlusturMouseExited
        ButonAyarları.setOriginalBg(btnSifreOlustur, getForeground());
    }//GEN-LAST:event_btnSifreOlusturMouseExited

    private void btnSifreOlusturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSifreOlusturActionPerformed
        // 1) Seçili karakter setlerini birleştiriyoruz
        String pool = "";
        if (btnBuyukHarf.isSelected())  pool += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";      // Büyük harf havuzu
        if (btnKucukHarf.isSelected())  pool += "abcdefghijklmnopqrstuvwxyz";      // Küçük harf havuzu
        if (btnRakam.isSelected())      pool += "0123456789";                      // Rakam havuzu
        if (btnSembol.isSelected())     pool += "!@#$%^&*()-_=+[]{};:,.<>?";       // Sembol havuzu

        // 2) Şifre uzunluğunu TextField'dan al ve geçerli bir sayı mı kontrol et
        int length;
    try {
        length = Integer.parseInt(txtSifreUzunluk.getText());
    } catch (NumberFormatException ex) {
        Dialogs.ozelMesajGoster(this, "Lütfen geçerli bir uzunluk girin!");
        return;  // Geçersiz girişse işlemi durdur
    }
    if (length < 1) {
        Dialogs.ozelMesajGoster(this, "Uzunluk en az 1 olmalı!");
        return;  // Negatif ya da sıfırsa durdur
    }
    if (pool.isEmpty()) {
        Dialogs.ozelMesajGoster(this, "Karakter tipi seçmediniz!");
        return;  // Hiçbir tip seçilmemişse durdur
    }

    // 3) Havuzdan rastgele karakterler seçerek şifre oluştur
    StringBuilder sb = new StringBuilder(length);
    Random rnd = new Random();
    for (int i = 0; i < length; i++) {
        int idx = rnd.nextInt(pool.length());      // Havuzun içinden rastgele pozisyon
        sb.append(pool.charAt(idx));               // Karakteri ekle
    }
    String pwd = sb.toString();
    txtSifre.setText(pwd);                         // Oluşturulan şifreyi TextField'a yaz

    // 4) Şifre gücünü basitçe ölçmek için:
    //    - Kaç farklı karakter tipi seçilmiş?
    //    - Uzunluk ne kadar?
    int types = 0;
    if (btnBuyukHarf.isSelected()) types++;
    if (btnKucukHarf.isSelected()) types++;
    if (btnRakam.isSelected())     types++;
    if (btnSembol.isSelected())    types++;

    String durum;
    int barValue;
    if (types == 4 && length >= 15) {
        // Tüm tipler + uzunluk ≥12 → Çok Güçlü
        durum = "Çok Güçlü";
        barValue = 100;
    }
    else if (types >= 3 && length >= 12) {
        // 3 tip + uzunluk ≥8 → Güçlü
        durum = "Güçlü";
        barValue = 80;
    }
    else if (types >= 2 && length >= 8) {
        // 2 tip + uzunluk ≥6 → Orta
        durum = "Orta";
        barValue = 50;
    }
    else {
        // Diğer tüm durumlar → Zayıf
        durum = "Zayıf";
        barValue = 25;
    }
    
    if(length < 8)
    {
        Dialogs.ozelMesajGoster(this, "Minimum 8 karakter girmelisiniz!");
        txtSifre.setText("");
        barValue = 0;
        durum = "";
        txtSifreUzunluk.setText("");
    }
        // 5) Durumu etiket ve progress bar ile güncelle
        LblSifreDurum.setText(durum);
        pbSifreBar.setMaximum(100);
        pbSifreBar.setValue(barValue);
    }//GEN-LAST:event_btnSifreOlusturActionPerformed

    private void btnSifreyiKaydetMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSifreyiKaydetMouseEntered
        ButonAyarları.setBg(btnSifreyiKaydet, Color.black, Color.white);
    }//GEN-LAST:event_btnSifreyiKaydetMouseEntered

    private void btnSifreyiKaydetMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSifreyiKaydetMouseExited
        ButonAyarları.setOriginalBg(btnSifreyiKaydet, getForeground());
    }//GEN-LAST:event_btnSifreyiKaydetMouseExited

    private void btnSifreyiKaydetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSifreyiKaydetActionPerformed
         // 1) Formdan alanları oku
    String uygulamaAdi   = cmbUygulamaAdi.getSelectedItem().toString().trim();
    String hesapAdi      = txtKullaniciAdi.getText().trim();
    String plainSifre    = txtSifre.getText().trim();
    String sifreUzunluk  = txtSifreUzunluk.getText().trim();
    java.util.Date date  = (java.util.Date) spinnerTarih.getValue();

    // 1a) Mevcut şifre durumu etiketinden oku
    String durum = LblSifreDurum.getText();
    if ("Zayıf".equalsIgnoreCase(durum)) {
        Dialogs.ozelMesajGoster(this,
            "Bu şifre zayıf, lütfen daha güçlü bir şifre oluşturunuz.");
        return;
    }

    // Uzunluk kontrolü (sadece enabled ise)
    int belirlenenUzunluk = -1;
    if (txtSifreUzunluk.isEnabled()) {
        try {
            belirlenenUzunluk = Integer.parseInt(sifreUzunluk);
        } catch (NumberFormatException e) {
            Dialogs.ozelMesajGoster(this,
                "Lütfen şifre uzunluğu için geçerli bir sayı girin!");
            return;
        }
    }

    // 1b) Zorunlu alan kontrolleri
    final String PH_HESAP = "Kullanıcı adınızı giriniz";

    if (txtSifreUzunluk.isEnabled()
        && (sifreUzunluk.equals(SIFRE_UZUNLUGU_TEXT) || sifreUzunluk.isEmpty())) {
        Dialogs.ozelMesajGoster(this, "Şifre uzunluğu boş olamaz!");
        return;
    }

    if (hesapAdi.isEmpty() || hesapAdi.equals(PH_HESAP)) {
        Dialogs.ozelMesajGoster(this, "Lütfen hesap adınızı girin!");
        return;
    }
    if (plainSifre.isEmpty()) {
        Dialogs.ozelMesajGoster(this, "Lütfen bir şifre oluşturun!");
        return;
    }
    if (btnTercih.isSelected() && plainSifre.length() < 8) {
        Dialogs.ozelMesajGoster(this, "Minimum 8 karakter girmelisiniz!");
        return;
    }

    // 2) AES ile encrypt, tarih, uzunluk
    String cipher = AESUtil.encrypt(plainSifre);
    String tarih  = new java.sql.Timestamp(date.getTime()).toString();
    int length    = txtSifreUzunluk.isEnabled()
                    ? belirlenenUzunluk
                    : plainSifre.length();

    Connection conn = db.connection;
    try {
        // 3) Aynı uygulama+hesap için zaten bir kayıt var mı?
        Integer varolanId = null;
        String checkSql = "SELECT sifre_id FROM sifreler " +
                          "WHERE kullanici_id=? AND uygulama_adi=? AND hesap_adi=?";
        try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
            checkStmt.setInt(1, aktifKullaniciId);
            checkStmt.setString(2, uygulamaAdi);
            checkStmt.setString(3, hesapAdi);
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next()) {
                    varolanId = rs.getInt("sifre_id");
                }
            }
        }

        if (varolanId != null) {
            int secim = JOptionPane.showConfirmDialog(
                this,
                "Bu hesap için zaten bir şifreniz var.\n" +
                "Şifrenizi güncellemek ister misiniz?",
                "Şifre Zaten Var",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );
            if (secim == JOptionPane.YES_OPTION) {
                // ── YENİ: Aynı şifre başka bir hesapta var mı?
                String dupSql = "SELECT sifre_id FROM sifreler " +
                                "WHERE kullanici_id=? AND sifre_icerigi=? AND sifre_id<>?";
                try (PreparedStatement dupStmt = conn.prepareStatement(dupSql)) {
                    dupStmt.setInt(1, aktifKullaniciId);
                    dupStmt.setString(2, cipher);
                    dupStmt.setInt(3, varolanId);
                    try (ResultSet rsDup = dupStmt.executeQuery()) {
                        if (rsDup.next()) {
                            Dialogs.ozelMesajGoster(
                                this,
                                "Bu şifre başka bir hesapta zaten kullanılıyor,\n" +
                                "lütfen farklı bir şifre giriniz."
                            );
                            return;
                        }
                    }
                }

                // ── Mevcut güncelleme akışı ──
                SifreGuncelle.guncelle(
                    varolanId,
                    uygulamaAdi,
                    hesapAdi,
                    plainSifre,
                    length
                );
                Dialogs.ozelMesajGoster(this, "Şifreniz güncellendi!");
                new AnaMenu(aktifKullaniciId, aktifKullaniciAdi, true)
                    .setVisible(true);
                this.dispose();
            }
            return;
        }

        // ── YENİ EKLENDİ: Aynı şifre başka bir hesapta kayıtlı mı? ──
        String dupSql2 = "SELECT sifre_id FROM sifreler " +
                         "WHERE kullanici_id=? AND sifre_icerigi=?";
        try (PreparedStatement dupStmt2 = conn.prepareStatement(dupSql2)) {
            dupStmt2.setInt(1, aktifKullaniciId);
            dupStmt2.setString(2, cipher);
            try (ResultSet rsDup2 = dupStmt2.executeQuery()) {
                if (rsDup2.next()) {
                    Dialogs.ozelMesajGoster(
                        this,
                        "Bu şifre başka bir hesapta zaten kullanılıyor,\n" +
                        "lütfen farklı bir şifre giriniz."
                    );
                    return;
                }
            }
        }

        // 4b) Yeni kayıt (INSERT)
        String insertSql = "INSERT INTO sifreler " +
                           "(kullanici_id, uygulama_adi, hesap_adi, sifre_icerigi, sifre_uzunluk, kayit_tarihi) " +
                           "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
            insertStmt.setInt(1, aktifKullaniciId);
            insertStmt.setString(2, uygulamaAdi);
            insertStmt.setString(3, hesapAdi);
            insertStmt.setString(4, cipher);
            insertStmt.setInt(5, length);
            insertStmt.setString(6, tarih);
            insertStmt.executeUpdate();
            Dialogs.ozelMesajGoster(this, "Şifre başarıyla kaydedildi!");
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
        Dialogs.ozelMesajGoster(this, "Şifre kaydetme hatası!");
    }
    // NOT: artık bağlantıyı burada kapatmıyoruz, DbConnection ömrü boyunca açık kalsın.

    // Kaydettikten sonra yine ŞifrelerEkranı’na dön
    SifrelerEkranı ekran = new SifrelerEkranı(aktifKullaniciId, aktifKullaniciAdi);
    this.dispose();
    ekran.setVisible(true);
    }//GEN-LAST:event_btnSifreyiKaydetActionPerformed

    // Panoya Kopyalama Ayarı
    private void kopyalaIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kopyalaIconMouseClicked
        // TextField’den şifreyi al
        String sifre = txtSifre.getText();
        if (sifre.isEmpty() || sifre.equals(SIFRE_TEXT)) {
            Dialogs.ozelMesajGoster(this, "Kopyalanacak şifre yok!");
        return;
        }

        // Panoya kopyala
        StringSelection selection = new StringSelection(sifre);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);

        // Kullanıcıya bilgi ver
        IconAyarları.changeIcon(kopyalaIcon, "accept");
        Dialogs.ozelMesajGoster(this, "Şifre panoya kopyalandı.");
        IconAyarları.setOriginalIcon(kopyalaIcon);
    }//GEN-LAST:event_kopyalaIconMouseClicked

    private void txtSifreUzunlukFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSifreUzunlukFocusGained
        TextAyarları.checkTheTextFocusGained(txtSifreUzunluk, SIFRE_UZUNLUGU_TEXT);
    }//GEN-LAST:event_txtSifreUzunlukFocusGained

    private void txtSifreUzunlukFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSifreUzunlukFocusLost
        TextAyarları.checkTheTextFocusLost(txtSifreUzunluk);
    }//GEN-LAST:event_txtSifreUzunlukFocusLost

    private void txtSifreUzunlukKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSifreUzunlukKeyReleased
        this.sifreUzunluk = TextAyarları.checkTheTextKeyReleased(txtSifreUzunluk, 30);
    }//GEN-LAST:event_txtSifreUzunlukKeyReleased

    private void txtSifreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSifreFocusGained
        TextAyarları.checkTheTextFocusGained(txtSifre, SIFRE_TEXT);
    }//GEN-LAST:event_txtSifreFocusGained

    private void txtSifreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSifreFocusLost
        TextAyarları.checkTheTextFocusLost(txtSifre);
    }//GEN-LAST:event_txtSifreFocusLost

    private void geriIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_geriIconMouseClicked
        if (fromSifreler) {
            // ŞifrelerEkranı’ndan geldiysek oraya dön
            new SifrelerEkranı(aktifKullaniciId, aktifKullaniciAdi)
                .setVisible(true);
        } else {
            // Normal akışta Giriş ekranına dön
            GirisEkranı giris = new GirisEkranı(aktifKullaniciId);
            giris.setKullaniciAdi(aktifKullaniciAdi);
            giris.setVisible(true);
        }
        this.dispose();
    }//GEN-LAST:event_geriIconMouseClicked

    private void txtKullaniciAdiFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtKullaniciAdiFocusGained
        TextAyarları.checkTheTextFocusGained(txtKullaniciAdi, KULLANICI_ADI_TEXT);
    }//GEN-LAST:event_txtKullaniciAdiFocusGained

    private void txtKullaniciAdiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtKullaniciAdiFocusLost
        TextAyarları.checkTheTextFocusLost(txtKullaniciAdi);
    }//GEN-LAST:event_txtKullaniciAdiFocusLost

    private void btnSifreYoneticisiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSifreYoneticisiMouseEntered
        ButonAyarları.setBg(btnSifreYoneticisi, Color.black, Color.white);
    }//GEN-LAST:event_btnSifreYoneticisiMouseEntered

    private void btnSifreYoneticisiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSifreYoneticisiMouseExited
        ButonAyarları.setOriginalBg(btnSifreYoneticisi, getForeground());
    }//GEN-LAST:event_btnSifreYoneticisiMouseExited

    private void btnSifreYoneticisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSifreYoneticisiActionPerformed
        SifrelerEkranı ekran = new SifrelerEkranı(aktifKullaniciId, aktifKullaniciAdi);
        this.dispose();
        ekran.setVisible(true);
    }//GEN-LAST:event_btnSifreYoneticisiActionPerformed

    private void txtSifreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSifreKeyTyped
        // Eğer kullanıcı kendi şifresini yazıyorsa, sınırlama yapma
    if (btnTercih.isSelected()) {
        return; // serbest yazmasına izin ver
    }

    // Otomatik oluşturma modunda kullanıcı yazıyorsa sınırla
    String uzunlukTxt = txtSifreUzunluk.getText().trim();
    if (uzunlukTxt.isEmpty()) {
        evt.consume();
        return;
    }

    int limit;
    try {
        limit = Integer.parseInt(uzunlukTxt);
    } catch (NumberFormatException ex) {
        evt.consume();
        return;
    }

    if (txtSifre.getText().length() >= limit) {
        evt.consume();
    }
    }//GEN-LAST:event_txtSifreKeyTyped

    private void txtSifreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSifreKeyReleased
        String pwd = txtSifre.getText();
        int barValue;

    // 1) Alan boşsa her şeyi temizle
    if (pwd.isEmpty()) {
        LblSifreDurum.setText("");
        pbSifreBar.setValue(0);
        txtSifreUzunluk.setText(""); // Uzunluk da sıfırlansın
        return;
    }

    // 2) Maksimum 30 karakterle sınırla
    if (pwd.length() > 30) {
        pwd = pwd.substring(0, 30);
        txtSifre.setText(pwd);
    }

    // 3) Şifre uzunluğunu otomatik yaz (eğer kullanıcı manuel oluşturuyorsa)
    if (btnTercih.isSelected()) {
        txtSifreUzunluk.setText(String.valueOf(pwd.length()));
    }

    // 4) Karakter tiplerini tespit et
    boolean hasUpper = false;
    boolean hasLower = false;
    boolean hasDigit = false;
    boolean hasSymbol = false;

    for (char c : pwd.toCharArray()) {
        if (Character.isUpperCase(c)) {
            hasUpper = true;
        } else if (Character.isLowerCase(c)) {
            hasLower = true;
        } else if (Character.isDigit(c)) {
            hasDigit = true;
        } else {
            hasSymbol = true;
        }
    }

    // 5) Karakter tipi sayısı
    int types = 0;
    if (hasUpper)  types++;
    if (hasLower)  types++;
    if (hasDigit)  types++;
    if (hasSymbol) types++;

    // 6) Şifre gücünü belirle
    String durum;
    int length = pwd.length();

    if (types == 4 && length >= 12) {
        durum = "Çok Güçlü";
        barValue = 100;
    } else if (types >= 3 && length >= 8) {
        durum = "Güçlü";
        barValue = 80;
    } else if (types >= 2 && length >= 6) {
        durum = "Orta";
        barValue = 50;
    } else {
        durum = "Zayıf";
        barValue = 20;
    }

    // 7) Arayüzü güncelle
    LblSifreDurum.setText(durum);
    pbSifreBar.setMaximum(100);
    pbSifreBar.setValue(barValue);
    }//GEN-LAST:event_txtSifreKeyReleased

    private void hesapAyarlarıIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hesapAyarlarıIconMouseClicked
        HesapYonetimEkranı ekran = new HesapYonetimEkranı(aktifKullaniciId, aktifKullaniciAdi);
        this.dispose();
        ekran.setVisible(true);
    }//GEN-LAST:event_hesapAyarlarıIconMouseClicked

    private void addItemIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addItemIconMouseClicked

        // AnaMenu’den formu parent ve kullanıcı ID’si ile açıyoruz
        gui.UygulamaEkle form = new gui.UygulamaEkle(this, aktifKullaniciId);
        form.setLocationRelativeTo(this);
        form.setVisible(true);
    }//GEN-LAST:event_addItemIconMouseClicked

    private void btnTercihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTercihActionPerformed
        // Kendim şifre oluşturmak istiyorum
        if(btnTercih.isSelected())
        {
            btnTercih.setText("Otomatik şifre oluşturmak istiyorum");
            txtSifreUzunluk.setEnabled(false);
            btnBuyukHarf.setEnabled(false);
            btnKucukHarf.setEnabled(false);
            btnRakam.setEnabled(false);
            btnSembol.setEnabled(false);
            btnSifreOlustur.setEnabled(false);
            LblSifreUzunlukBilgi.setText("Şifre Uzunluğu 8-30 arası karakter");
            txtSifre.setEnabled(true);
        }
        // Otomatik Şifre Oluşturmak istiyorum
        if(btnTercih.isSelected() == false)
        {
            btnTercih.setText("Kendim şifre oluşturmak istiyorum");
            txtSifreUzunluk.setEnabled(true);
            btnBuyukHarf.setEnabled(true);
            btnKucukHarf.setEnabled(true);
            btnRakam.setEnabled(true);
            btnSembol.setEnabled(true);
            btnSifreOlustur.setEnabled(true);
            LblSifreUzunlukBilgi.setText("Şifre Uzunluğu MAX: 30 karakter");
            txtSifre.setEnabled(false);
        }
    }//GEN-LAST:event_btnTercihActionPerformed

    private void btnTercihMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTercihMouseEntered
        ButonAyarları.setBgToggle(btnTercih, Color.black, Color.white);
    }//GEN-LAST:event_btnTercihMouseEntered

    private void btnTercihMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTercihMouseExited
        ButonAyarları.setOriginalToggle(btnTercih, getForeground());
    }//GEN-LAST:event_btnTercihMouseExited

    private void trashIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trashIconMouseClicked
         // 1) Seçili uygulamayı al
        String seciliUygulama = (String) cmbUygulamaAdi.getSelectedItem();
        if (seciliUygulama == null) {
            JOptionPane.showMessageDialog(
            this,
            "Lütfen önce bir uygulama seçin.",
            "Uyarı",
            JOptionPane.WARNING_MESSAGE
        );
        return;
        }

    // 2) Onay sor
    int onay = JOptionPane.showConfirmDialog(
        this,
        "Seçtiğiniz uygulamayı silmek ister misiniz?\nBu işlem geri alınamaz!",
        "Uygulama Silme Onayı",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.WARNING_MESSAGE
    );
    if (onay != JOptionPane.YES_OPTION) {
        return;
    }

// 3) UygulamaSil sınıfını kullanarak DB’den sil ve combobox’tan kaldır
    try (
        Connection conn = db.getConnection();
        Statement stmt = conn.createStatement()
    ) {
        boolean ok = UygulamaSil.sil(stmt, aktifKullaniciId, seciliUygulama);
        if (ok) {
            JOptionPane.showMessageDialog(
                this,
                "Uygulama başarıyla silindi.",
                "Bilgi",
                JOptionPane.INFORMATION_MESSAGE
            );
            cmbUygulamaAdi.removeItem(seciliUygulama);
            cmbUygulamaAdi.setSelectedItem(null);
        } else {
            JOptionPane.showMessageDialog(
                this,
                "Silme işlemi sırasında bir hata oluştu.",
                "Hata",
                JOptionPane.ERROR_MESSAGE
            );
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(
            this,
            "Veritabanı hatası: " + ex.getMessage(),
            "Hata",
            JOptionPane.ERROR_MESSAGE
        );
    }
    }//GEN-LAST:event_trashIconMouseClicked

    
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
            java.util.logging.Logger.getLogger(AnaMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AnaMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AnaMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AnaMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               //new AnaMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LblKullaniciAdi;
    private javax.swing.JLabel LblSifreDurum;
    private javax.swing.JLabel LblSifreUzunlukBilgi;
    private javax.swing.JLabel addItemIcon;
    private javax.swing.JPanel anaMenuPanel;
    private javax.swing.JToggleButton btnBuyukHarf;
    private javax.swing.JToggleButton btnKucukHarf;
    private javax.swing.JToggleButton btnRakam;
    private javax.swing.JToggleButton btnSembol;
    private javax.swing.JButton btnSifreOlustur;
    private javax.swing.JButton btnSifreYoneticisi;
    private javax.swing.JButton btnSifreyiKaydet;
    private javax.swing.JToggleButton btnTercih;
    private javax.swing.JComboBox<String> cmbUygulamaAdi;
    private javax.swing.JLabel geriIcon;
    private javax.swing.JLabel hesapAyarlarıIcon;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel kopyalaIcon;
    private javax.swing.JProgressBar pbSifreBar;
    private javax.swing.JSpinner spinnerTarih;
    private javax.swing.JLabel trashIcon;
    private javax.swing.JTextField txtKullaniciAdi;
    private javax.swing.JTextField txtSifre;
    private javax.swing.JTextField txtSifreUzunluk;
    // End of variables declaration//GEN-END:variables
}

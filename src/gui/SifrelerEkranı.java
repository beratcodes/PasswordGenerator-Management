package gui;

import gui.ayarlar.IDuzenleyici;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import util.AESUtil;           // AESUtil.decrypt() için
import database.DbConnection; // DbConnection kullanıyorsan
import database.transaction.SifreSil;
import gui.ayarlar.ButonAyarları;
import gui.ayarlar.Dialogs;
import gui.ayarlar.IconAyarları;
import gui.ayarlar.TextAyarları;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;

public class SifrelerEkranı extends javax.swing.JFrame implements IDuzenleyici{
    
    // Placeholder metinler
    private final String UYGULAMA_ADI_TEXT = "Uygulama Adı";
    private final String HESAP_ADI_TEXT    = "Hesap Adı";
    private final String SIFRE_TEXT        = "Şifreniz";

    private final int    aktifKullaniciId;   // Oturum açmış kullanıcı ID’si
    private final String aktifKullaniciAdi;  // Oturum açmış kullanıcı adı

    public SifrelerEkranı(int kullaniciId, String aktifKullaniciAdi) {
        this.aktifKullaniciId   = kullaniciId;       // final alan ataması
        this.aktifKullaniciAdi  = aktifKullaniciAdi; // final alan ataması
        initComponents();                             // UI bileşenlerini yükle
        getEdits();                                   // Ortak düzenlemeleri uygula
        aramaFiltreleme();                            // Arama kutusuna filtre dinleyici ekle

        // Tablo sütun başlıkları: ID’ler de modele eklenir ama görünümden çıkarılacak
        String[] kolonlar = {
            "Şifre ID", "Kullanıcı ID", "Uygulama Adı",
            "Hesap Adı", "Şifre", "Uzunluk", "Kayıt Tarihi"
        };

        // Hücre düzenlenmesini engelleyen model
        DefaultTableModel model = new DefaultTableModel(kolonlar, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;  // Tüm hücreler salt-okunur
            }
        };
        sifrelerTablo.setModel(model);  // Modeli tabloya ata
        loadSifreler();                 // Veritabanından şifreleri yükle

        // ─── Görünümden ID sütunlarını kaldır (modelde hâlâ durur)
        TableColumnModel cm = sifrelerTablo.getColumnModel();
        cm.removeColumn(cm.getColumn(1)); // “Kullanıcı ID”
        cm.removeColumn(cm.getColumn(0)); // “Şifre ID”
    }

    @Override
    public void getEdits() {
        this.setLocationRelativeTo(null);       // Pencereyi ortala
        sifrelerPanel.setFocusable(true);       // Paneli odaklanabilir yap
        txtUygulamaAdi.setText(UYGULAMA_ADI_TEXT);  // Placeholder ata
        txtHesapAdi.setText(HESAP_ADI_TEXT);        // Placeholder ata
        txtSifre.setText(SIFRE_TEXT);               // Placeholder ata
    }

    // Arama kutusuna yazdıkça tabloyu filtreleyen dinleyici
    private void aramaFiltreleme() {
        txtArama.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { filtrele(); }
            public void removeUpdate(DocumentEvent e) { filtrele(); }
            public void changedUpdate(DocumentEvent e) { filtrele(); }

            // Uygulama Adı sütununda regex ile arama
            private void filtrele() {
                String arama = txtArama.getText().toLowerCase();
                TableRowSorter<DefaultTableModel> sorter =
                    new TableRowSorter<>((DefaultTableModel) sifrelerTablo.getModel());
                sifrelerTablo.setRowSorter(sorter);
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + arama, 2));
            }
        });
    }

    // Veritabanından şifreleri çekip tablo modeline ekleyen metod
    private void loadSifreler() {
        DefaultTableModel model = (DefaultTableModel) sifrelerTablo.getModel();
        model.setRowCount(0);  // Önce temizle

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // Tarih formatı

        String query = "SELECT sifre_id, kullanici_id, uygulama_adi, hesap_adi,"
                     + " sifre_icerigi, sifre_uzunluk, kayit_tarihi"
                     + " FROM sifreler"
                     + " WHERE kullanici_id = " + aktifKullaniciId;

        try (ResultSet rs = new DbConnection().statement.executeQuery(query)) {
            while (rs.next()) {
                int      id     = rs.getInt("sifre_id");
                int      uid    = rs.getInt("kullanici_id");
                String   app    = rs.getString("uygulama_adi");
                String   acc    = rs.getString("hesap_adi");
                String   cipher = rs.getString("sifre_icerigi");
                String   plain  = AESUtil.decrypt(cipher);           // Şifreyi çözüp göster
                int      length = rs.getInt("sifre_uzunluk");
                Timestamp ts    = rs.getTimestamp("kayit_tarihi");
                String   tarih  = sdf.format(ts);

                // Satır olarak modele ekle
                model.addRow(new Object[] {
                    id, uid, app, acc, plain, length, tarih
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Dialogs.ozelMesajGoster(this, "Şifreler yüklenirken hata oluştu!");
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        sifrelerPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        sifrelerTablo = new javax.swing.JTable();
        geriIcon = new javax.swing.JLabel();
        txtUygulamaAdi = new javax.swing.JTextField();
        txtHesapAdi = new javax.swing.JTextField();
        txtSifre = new javax.swing.JTextField();
        btnGuncelle = new javax.swing.JButton();
        btnSil = new javax.swing.JButton();
        sifreKopyalaIcon = new javax.swing.JLabel();
        hesapAdiKopyalaIcon = new javax.swing.JLabel();
        txtArama = new javax.swing.JTextField();
        deleteIcon = new javax.swing.JLabel();
        updateIcon = new javax.swing.JLabel();
        searchIcon = new javax.swing.JLabel();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Şifreler");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        sifrelerPanel.setBackground(new java.awt.Color(8, 79, 140));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Şifre Yönetim Ekranı");

        sifrelerTablo.setBackground(new java.awt.Color(204, 204, 204));
        sifrelerTablo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        sifrelerTablo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Şifre ID", "Kullanıcı ID", "Uygulama Adı", "Hesap Adı", "Şifre", "Uzunluk", "Kayıt Tarihi"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        sifrelerTablo.setRowHeight(30);
        sifrelerTablo.setSelectionBackground(new java.awt.Color(0, 102, 204));
        sifrelerTablo.setSelectionForeground(new java.awt.Color(255, 255, 255));
        sifrelerTablo.setShowGrid(true);
        jScrollPane2.setViewportView(sifrelerTablo);
        if (sifrelerTablo.getColumnModel().getColumnCount() > 0) {
            sifrelerTablo.getColumnModel().getColumn(0).setMinWidth(0);
            sifrelerTablo.getColumnModel().getColumn(0).setMaxWidth(0);
            sifrelerTablo.getColumnModel().getColumn(1).setMinWidth(0);
            sifrelerTablo.getColumnModel().getColumn(1).setMaxWidth(0);
        }

        geriIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        geriIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/iconlar/left-arrow.png"))); // NOI18N
        geriIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        geriIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                geriIconMouseClicked(evt);
            }
        });

        txtUygulamaAdi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtUygulamaAdi.setEnabled(false);
        txtUygulamaAdi.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUygulamaAdiFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUygulamaAdiFocusLost(evt);
            }
        });

        txtHesapAdi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtHesapAdi.setEnabled(false);
        txtHesapAdi.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtHesapAdiFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtHesapAdiFocusLost(evt);
            }
        });

        txtSifre.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtSifre.setEnabled(false);
        txtSifre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSifreFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSifreFocusLost(evt);
            }
        });

        btnGuncelle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnGuncelle.setText("GÜNCELLE");
        btnGuncelle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuncelle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuncelleMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuncelleMouseExited(evt);
            }
        });
        btnGuncelle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuncelleActionPerformed(evt);
            }
        });

        btnSil.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSil.setText("SİL");
        btnSil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSilMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSilMouseExited(evt);
            }
        });
        btnSil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSilActionPerformed(evt);
            }
        });

        sifreKopyalaIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sifreKopyalaIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/iconlar/paste.png"))); // NOI18N
        sifreKopyalaIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sifreKopyalaIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sifreKopyalaIconMouseClicked(evt);
            }
        });

        hesapAdiKopyalaIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hesapAdiKopyalaIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/iconlar/paste.png"))); // NOI18N
        hesapAdiKopyalaIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hesapAdiKopyalaIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hesapAdiKopyalaIconMouseClicked(evt);
            }
        });

        txtArama.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        deleteIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        deleteIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/iconlar/trash (1).png"))); // NOI18N

        updateIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        updateIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/iconlar/refresh (1).png"))); // NOI18N

        searchIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        searchIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/iconlar/search (2).png"))); // NOI18N

        javax.swing.GroupLayout sifrelerPanelLayout = new javax.swing.GroupLayout(sifrelerPanel);
        sifrelerPanel.setLayout(sifrelerPanelLayout);
        sifrelerPanelLayout.setHorizontalGroup(
            sifrelerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(sifrelerPanelLayout.createSequentialGroup()
                .addGroup(sifrelerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sifrelerPanelLayout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addGroup(sifrelerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sifreKopyalaIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hesapAdiKopyalaIcon))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(sifrelerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtUygulamaAdi, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSifre, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHesapAdi, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(73, 73, 73)
                        .addGroup(sifrelerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(deleteIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSil, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(100, 100, 100)
                        .addGroup(sifrelerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnGuncelle, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                            .addComponent(updateIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(sifrelerPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtArama, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sifrelerPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(geriIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(252, 252, 252)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(101, Short.MAX_VALUE))
        );

        sifrelerPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {hesapAdiKopyalaIcon, sifreKopyalaIcon});

        sifrelerPanelLayout.setVerticalGroup(
            sifrelerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sifrelerPanelLayout.createSequentialGroup()
                .addGroup(sifrelerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sifrelerPanelLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sifrelerPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(geriIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41)
                .addGroup(sifrelerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtArama)
                    .addComponent(searchIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(sifrelerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sifrelerPanelLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(hesapAdiKopyalaIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(sifreKopyalaIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sifrelerPanelLayout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addGroup(sifrelerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sifrelerPanelLayout.createSequentialGroup()
                                .addComponent(txtUygulamaAdi, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(txtHesapAdi, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(txtSifre, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(sifrelerPanelLayout.createSequentialGroup()
                                .addComponent(updateIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(sifrelerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnSil, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGuncelle, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(deleteIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(114, Short.MAX_VALUE))
        );

        sifrelerPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {hesapAdiKopyalaIcon, sifreKopyalaIcon});

        sifrelerPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtArama, txtUygulamaAdi});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sifrelerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sifrelerPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void geriIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_geriIconMouseClicked
        AnaMenu ana = new AnaMenu(aktifKullaniciId, aktifKullaniciAdi);
        this.dispose();
        ana.setVisible(true);
    }//GEN-LAST:event_geriIconMouseClicked

    private void txtUygulamaAdiFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUygulamaAdiFocusGained
        TextAyarları.checkTheTextFocusGained(txtUygulamaAdi, UYGULAMA_ADI_TEXT);
    }//GEN-LAST:event_txtUygulamaAdiFocusGained

    private void txtUygulamaAdiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUygulamaAdiFocusLost
        TextAyarları.checkTheTextFocusLost(txtUygulamaAdi);
    }//GEN-LAST:event_txtUygulamaAdiFocusLost

    private void txtHesapAdiFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHesapAdiFocusGained
        TextAyarları.checkTheTextFocusGained(txtHesapAdi, HESAP_ADI_TEXT);
    }//GEN-LAST:event_txtHesapAdiFocusGained

    private void txtHesapAdiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHesapAdiFocusLost
        TextAyarları.checkTheTextFocusLost(txtHesapAdi);
    }//GEN-LAST:event_txtHesapAdiFocusLost

    private void txtSifreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSifreFocusGained
        TextAyarları.checkTheTextFocusGained(txtSifre, SIFRE_TEXT);
    }//GEN-LAST:event_txtSifreFocusGained

    private void txtSifreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSifreFocusLost
        TextAyarları.checkTheTextFocusLost(txtSifre);
    }//GEN-LAST:event_txtSifreFocusLost

    private void btnGuncelleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuncelleMouseEntered
        ButonAyarları.setBg(btnGuncelle, Color.GREEN, Color.black);
        IconAyarları.changeIcon(updateIcon, "updt");
    }//GEN-LAST:event_btnGuncelleMouseEntered

    private void btnGuncelleMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuncelleMouseExited
        ButonAyarları.setOriginalBg(btnGuncelle, getForeground());
        IconAyarları.setOriginalIcon(updateIcon);
    }//GEN-LAST:event_btnGuncelleMouseExited

    private void btnSilMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSilMouseEntered
        ButonAyarları.setBg(btnSil, Color.red, Color.white);
        IconAyarları.changeIcon(deleteIcon, "delete");
    }//GEN-LAST:event_btnSilMouseEntered

    private void btnSilMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSilMouseExited
        ButonAyarları.setOriginalBg(btnSil, getForeground());
        IconAyarları.setOriginalIcon(deleteIcon);
    }//GEN-LAST:event_btnSilMouseExited

    private void btnGuncelleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuncelleActionPerformed
        int viewRow = sifrelerTablo.getSelectedRow();
    if (viewRow == -1) {
        Dialogs.ozelMesajGoster(this,
            "Lütfen önce değiştirmek istediğiniz şifreyi seçiniz.");
        return;
    }

    // 2) View → model satır indeksine çevir
    int modelRow = sifrelerTablo.convertRowIndexToModel(viewRow);

    // 3) Model’den istediğimiz hücreleri al
    DefaultTableModel model = (DefaultTableModel) sifrelerTablo.getModel();
    int     id    = (Integer) model.getValueAt(modelRow, 0);     // Şifre ID
    String  app   = model.getValueAt(modelRow, 2).toString();    // Uygulama Adı
    String  acc   = model.getValueAt(modelRow, 3).toString();    // Hesap Adı
    String  sifre = model.getValueAt(modelRow, 4).toString();    // Şifre
    String  len   = model.getValueAt(modelRow, 5).toString();    // Uzunluk

    // 4) AnaMenu’yu aç ve verileri set et
    AnaMenu ana = new AnaMenu(aktifKullaniciId /* , true */);
    ana.setSelectedSifreId(id);
    ana.setUygulamaAdi(app);
    ana.setKullaniciAdi(acc);
    ana.setSifre(sifre);
    ana.setSifreUzunluk(len);
    ana.enterGuncellemeModu();

    ana.setVisible(true);
    this.dispose();  // ŞifrelerEkranı’nı kapat
    }//GEN-LAST:event_btnGuncelleActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        sifrelerTablo.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int row = sifrelerTablo.getSelectedRow();
            if (row != -1) {
                //lblSifreId.setText(sifrelerTablo.getValueAt(row, 0).toString());
                //txtKullaniciId.setText(sifrelerTablo.getValueAt(row, 1).toString());
                txtUygulamaAdi.setText(sifrelerTablo.getValueAt(row, 0).toString());
                txtHesapAdi.setText(sifrelerTablo.getValueAt(row, 1).toString());
                txtSifre.setText(sifrelerTablo.getValueAt(row, 2).toString());
                //txtUzunluk.setText(sifrelerTablo.getValueAt(row, 5).toString());
            }
        }
    });
    }//GEN-LAST:event_formWindowOpened

    private void sifreKopyalaIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sifreKopyalaIconMouseClicked
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
        IconAyarları.changeIcon(sifreKopyalaIcon, "accept");
        Dialogs.ozelMesajGoster(this, "Şifre panoya kopyalandı.");
        IconAyarları.setOriginalIcon(sifreKopyalaIcon);
    }//GEN-LAST:event_sifreKopyalaIconMouseClicked

    private void hesapAdiKopyalaIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hesapAdiKopyalaIconMouseClicked
        // TextField’den şifreyi al
        String hesapAdi = txtHesapAdi.getText();
        if (hesapAdi.isEmpty() || hesapAdi.equals(HESAP_ADI_TEXT)) {
            Dialogs.ozelMesajGoster(this, "Kopyalanacak şifre yok!");
        return;
        }

        // Panoya kopyala
        StringSelection selection = new StringSelection(hesapAdi);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);

        // Kullanıcıya bilgi ver
        IconAyarları.changeIcon(hesapAdiKopyalaIcon, "accept");
        Dialogs.ozelMesajGoster(this, "Kullanıcı Adı panoya kopyalandı.");
        IconAyarları.setOriginalIcon(hesapAdiKopyalaIcon);
    }//GEN-LAST:event_hesapAdiKopyalaIconMouseClicked

    private void btnSilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSilActionPerformed
        // ── 1) Seçili satırı al ────────────────────────────────────────
    int row = sifrelerTablo.getSelectedRow();
    if (row == -1) {
        JOptionPane.showMessageDialog(this,
            "Lütfen önce silinecek bir satır seçin!",
            "Uyarı", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // ── 2) [YENİ] View’daki satır indeksini, model indeksine çevir ──
    int modelRow = sifrelerTablo.convertRowIndexToModel(row);

    // ── 3) Onay sor ──────────────────────────────────────────────────
    int secim = JOptionPane.showConfirmDialog(
        this,
        "Bu şifre kaydını tamamen silmek istediğinize emin misiniz?\n" +
        "Bu işlem geri alınamaz!",
        "Şifre Silme Onayı",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.WARNING_MESSAGE
    );
    if (secim != JOptionPane.YES_OPTION) {
        return; // kullanıcı iptal etti
    }

    // ── 4) [DEĞİŞTİRİLDİ] Model’den ID’yi al ve güvenli parse et ──
    //    → Orijinalinizde burası: (int) sifrelerTablo.getValueAt(row, 0);
    Object idObj = sifrelerTablo.getModel().getValueAt(modelRow, 0);
    int sifreId;
    try {
        if (idObj instanceof Integer) {
            sifreId = (Integer) idObj;
        } else {
            sifreId = Integer.parseInt(idObj.toString());
        }
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this,
            "Geçersiz ID formatı: " + idObj,
            "Hata", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // ── 5) Veri tabanından sil ──────────────────────────────────────
    SifreSil.sil(sifreId);

    // ── 6) Kullanıcıyı bilgilendir + tabloyu yenile ────────────────
    JOptionPane.showMessageDialog(this,
        "Şifre başarıyla silindi.",
        "Bilgi", JOptionPane.INFORMATION_MESSAGE);
    loadSifreler();

    // ── 7) Formu temizle ────────────────────────────────────────────
    txtUygulamaAdi.setText("");
    txtHesapAdi.setText("");
    txtSifre.setText("");
    
    }//GEN-LAST:event_btnSilActionPerformed

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
            java.util.logging.Logger.getLogger(SifrelerEkranı.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SifrelerEkranı.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SifrelerEkranı.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SifrelerEkranı.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new SifrelerEkranı().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuncelle;
    private javax.swing.JButton btnSil;
    private javax.swing.JLabel deleteIcon;
    private javax.swing.JLabel geriIcon;
    private javax.swing.JLabel hesapAdiKopyalaIcon;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel searchIcon;
    private javax.swing.JLabel sifreKopyalaIcon;
    private javax.swing.JPanel sifrelerPanel;
    private javax.swing.JTable sifrelerTablo;
    private javax.swing.JTextField txtArama;
    private javax.swing.JTextField txtHesapAdi;
    private javax.swing.JTextField txtSifre;
    private javax.swing.JTextField txtUygulamaAdi;
    private javax.swing.JLabel updateIcon;
    // End of variables declaration//GEN-END:variables
}

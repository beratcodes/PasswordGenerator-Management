package database.transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import database.DbConnection;  // DbConnection sınıfının bulunduğu paket
import util.AESUtil;           // AESUtil sınıfının bulunduğu paket

public class SifreGuncelle {
    public static void guncelle(int sifreId, String uygulamaAdi, String hesapAdi, String sifre, int uzunluk) {
        // 1) Şifreyi AES ile şifrele
        String encrypted = AESUtil.encrypt(sifre);

        // 2) SQL sorgusunu tek tırnak işaretlerini kaçırarak hazırla
        String query = "UPDATE sifreler SET "
                     + "uygulama_adi    = '" + uygulamaAdi.replace("'", "\\'") + "', "
                     + "hesap_adi       = '" + hesapAdi.replace("'", "\\'") + "', "
                     + "sifre_icerigi   = '" + encrypted.replace("'", "\\'") + "', "
                     + "sifre_uzunluk   = " + uzunluk
                     + " WHERE sifre_id = " + sifreId;

        // 3) Statement ile çalıştır
        try {
            Connection con = new DbConnection().connection;
            Statement st    = con.createStatement();
            st.executeUpdate(query);
            System.out.println("Şifre başarıyla güncellendi.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

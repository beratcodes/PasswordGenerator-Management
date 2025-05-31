package database.transaction;

import util.AESUtil;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;   // <-- ekledik

public class BilgileriGuncelle {

    public static void sifreGuncelle(Statement stmt, int kullaniciId, String yeniSifre) throws SQLException {
        String encrypted = AESUtil.encrypt(yeniSifre);
        String sql = "UPDATE kullanicilar SET sifre = '" + encrypted + "' WHERE id = " + kullaniciId;
        stmt.executeUpdate(sql);
    }

    public static void kullaniciAdiGuncelle(Statement stmt, int kullaniciId, String yeniKullaniciAdi) throws SQLException {
        String sql = "UPDATE kullanicilar SET kullaniciAdi = '" + yeniKullaniciAdi + "' WHERE id = " + kullaniciId;
        stmt.executeUpdate(sql);
    }

    // --- Yeni eklenen kısım ---
    /**
     * Veritabanından kullanıcı adını çeker.
     * @param stmt      Daha önce oluşturulmuş Statement
     * @param kullaniciId  Çekilecek kullanıcının ID'si
     * @return kullaniciAdi veya boş string
     * @throws SQLException
     */
    public static String getKullaniciAdi(Statement stmt, int kullaniciId) throws SQLException {
        // sütun ve PK ismini tablo yapına göre düzelttik:
        String sql = "SELECT kullanici_adi FROM kullanicilar WHERE kullanici_id = " + kullaniciId;
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            return rs.getString("kullanici_adi");
        }
        return "";
    }
    
    public static String getSifre(Statement stmt, int kullaniciId) throws SQLException {
        String sql = "SELECT sifre_hash FROM kullanicilar WHERE kullanici_id = " + kullaniciId;
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            String encrypted = rs.getString("sifre_hash");
            // AESUtil.decrypt metodunu kullanarak orijinal şifreyi elde ediyoruz
            return AESUtil.decrypt(encrypted);
        }
        return "";
    }
}
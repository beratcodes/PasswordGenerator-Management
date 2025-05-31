package database.transaction;

import database.DbConnection;
import util.AESUtil;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SifreYenile {
    private final Statement cumle;
    private final String kullaniciAdi;

    /**
     * @param kullaniciAdi  Şifresi değiştirilecek kullanıcının kullanıcı adı
     */
    public SifreYenile(String kullaniciAdi) throws SQLException {
        DbConnection db = new DbConnection();
        this.cumle        = db.statement;           // veya db.getConnection().createStatement()
        this.kullaniciAdi = kullaniciAdi.trim();
    }

    /**
     * @param guvenlikCevabi  Kullanıcının girdiği güvenlik cevabı
     * @param yeniSifre       Kullanıcının belirlediği yeni şifre (plain text)
     * @return                Güncelleme başarılıysa true, değilse false
     */
    public boolean sifreYenile(String guvenlikCevabi, String yeniSifre) {
        try {
            // 1) SQL enjeksiyondan kaçınmak için tırnak kaçır
            String safeKAdi = kullaniciAdi.replace("'", "''");

            // 2) Kayıtlı güvenlik cevabını çek
            String sqlSelect =
                "SELECT guvenlik_cevap FROM kullanicilar " +
                "WHERE kullanici_adi = '" + safeKAdi + "'";
            ResultSet rs = cumle.executeQuery(sqlSelect);

            if (!rs.next()) {
                rs.close();
                return false;  // böyle bir kullanıcı yok
            }
            String kayitliCevap = rs.getString("guvenlik_cevap");
            rs.close();

            // 3) Cevabı kontrol et
            if (!kayitliCevap.equalsIgnoreCase(guvenlikCevabi.trim())) {
                return false;  // yanlış cevap
            }

            // 4) Şifreyi AES ile şifrele
            String hash = AESUtil.encrypt(yeniSifre).replace("'", "''");

            // 5) UPDATE sorgusunu çalıştır
            String sqlUpdate =
                "UPDATE kullanicilar SET " +
                  "sifre_hash = '" + hash + "'" +
                " WHERE kullanici_adi = '" + safeKAdi + "'";
            int etkilenen = cumle.executeUpdate(sqlUpdate);

            return etkilenen > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
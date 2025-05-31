package database.transaction;

import database.DbConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import util.AESUtil;

public class KullaniciGiris {

    /**
     * @param kullaniciAdi Girdiğiniz kullanıcı adı
     * @param sifre        Girdiğiniz şifre (plain text)
     * @return eğer giriş başarılı ise kullanici_id, değilse -1
     */
    public int girisYap(String kullaniciAdi, String sifre) {
         // 1) Bağlantıyı hazırla
        DbConnection db = new DbConnection();
        
        // 2) Sorguyu çalıştır
        String sql = ""
          + "SELECT kullanici_id, sifre_hash "
          + "FROM kullanicilar "
          + "WHERE kullanici_adi = '" + kullaniciAdi + "'";
        try {
            Statement stmt = db.statement;
            ResultSet rs  = stmt.executeQuery(sql);

            if (rs.next()) {
                int id           = rs.getInt("kullanici_id");
                String storedHash = rs.getString("sifre_hash");
                rs.close();

                // 3) Girilen şifreyi AES ile encrypt et
                String inputHash = AESUtil.encrypt(sifre);

                // 4) Karşılaştır
                if (inputHash.equals(storedHash)) {
                    return id;
                }
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try { db.connection.close(); } catch (Exception ignore) { }
        }

        // başarısızsa
        return -1;
    }
}

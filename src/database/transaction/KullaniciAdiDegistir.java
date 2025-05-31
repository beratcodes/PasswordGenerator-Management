package database.transaction;

import java.sql.SQLException;
import java.sql.Statement;


public class KullaniciAdiDegistir {
    
    public static boolean kullaniciAdiGuncelle(Statement stmt, int id, String yeniAd) {
        try {
        String safeAd = yeniAd.replace("'", "''");
        // burada id yerine kullanici_id kullandık
        String sql = "UPDATE kullanicilar "
                   + "SET kullanici_adi = '" + safeAd + "' "
                   + "WHERE kullanici_id = " + id;
        int updated = stmt.executeUpdate(sql);
        return updated > 0;
    } catch (SQLException ex) {
        ex.printStackTrace();
        return false;
    }
    }
}

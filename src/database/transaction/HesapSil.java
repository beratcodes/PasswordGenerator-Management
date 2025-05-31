package database.transaction;

import java.sql.SQLException;
import java.sql.Statement;

public class HesapSil {

    /**
     * @param stmt       Daha önce açılmış ve db bağlantısına bağlı Statement
     * @param kullaniciId  Silinecek kullanıcının ID’si
     * @return true eğer satır silindiyse, false aksi halde
     */
    public static boolean hesapSil(Statement stmt, int kullaniciId) {
        try {
            // WHERE koşulundaki sütun adını kendi tablo şemanıza göre değiştirebilirsiniz
            String sql = "DELETE FROM kullanicilar WHERE kullanici_id = " + kullaniciId;
            int affected = stmt.executeUpdate(sql);
            return affected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}

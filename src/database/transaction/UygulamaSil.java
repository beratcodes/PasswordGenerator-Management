package database.transaction;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UygulamaSil {

    /**
     * Veritabanından bir kullanıcının belirli bir uygulamasını siler.
     *
     * @param stmt           Daha önce açılmış Statement nesnesi
     * @param kullaniciId    Silinecek uygulamanın ait olduğu kullanıcı ID
     * @param uygulamaAdi    Silinecek uygulama adı
     * @return               true → en az 1 satır etkilendi (silindi),  
     *                       false → hiçbir satır silinmedi  
     * @throws SQLException  SQL hatası oluşursa fırlatılır
     */
    public static boolean sil(Statement stmt, int kullaniciId, String uygulamaAdi) throws SQLException {
        // PreparedStatement ile parametrik silme
        String sql = "DELETE FROM uygulamalar WHERE kullanici_id = ? AND ad = ?";
        try (PreparedStatement ps = stmt.getConnection().prepareStatement(sql)) {
            ps.setInt(1, kullaniciId);
            ps.setString(2, uygulamaAdi);
            int affected = ps.executeUpdate();
            return affected > 0;
        }
    }
}

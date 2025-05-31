package database.transaction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import database.DbConnection;

public class UygulamaEkle {

    /**
     * Yeni bir uygulamayı hem combobox için kaydeder hem de veritabanına yazar.
     * @param kullaniciId  Oturum açmış kullanıcının ID'si
     * @param uygulamaAdi  Eklenecek uygulama adı
     */
    public static void ekle(int kullaniciId, String uygulamaAdi) {
        // SQL enjeksiyonuna basit kaçınma
        String safeAd = uygulamaAdi.replace("'", "\\'");
        String query = String.format(
            "INSERT INTO uygulamalar (ad, kullanici_id) VALUES ('%s', %d)",
            safeAd, kullaniciId
        );

        try {
            Connection con = new DbConnection().connection;
            Statement st = con.createStatement();
            st.executeUpdate(query);
            System.out.println("Uygulama ('" + safeAd + "') başarıyla eklendi.");

            st.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Bu kullanıcıya ait uygulama adlarını döner
     * @param kullaniciId  Oturum açmış kullanıcının ID'si
     */
    public static List<String> getList(int kullaniciId) throws SQLException {
        String sql = "SELECT ad FROM uygulamalar WHERE kullanici_id = " + kullaniciId;
        Connection con = new DbConnection().connection;
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        List<String> liste = new ArrayList<>();
        while (rs.next()) {
            liste.add(rs.getString("ad"));
        }

        rs.close();
        st.close();
        con.close();
        return liste;
    }
}
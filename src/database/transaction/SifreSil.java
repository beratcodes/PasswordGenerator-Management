
package database.transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import database.DbConnection;

public class SifreSil {
    /** 
     * Veritabanından bir şifre kaydını siler 
     */
    public static void sil(int sifreId) {
        String query = "DELETE FROM sifreler WHERE sifre_id = " + sifreId;
        try {
            Connection con = new DbConnection().connection;
            Statement st    = con.createStatement();
            st.executeUpdate(query);
            System.out.println("Şifre (ID=" + sifreId + ") başarıyla silindi.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

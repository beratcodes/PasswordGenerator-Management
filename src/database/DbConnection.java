package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbConnection {

    private final String HOST            = "localhost";
    private final String PORT            = "3306";
    private final String VERITABANI_ISMI = "password_manager";
    private final String USER            = "root";
    private final String PASSWORD        = "1234";

    public Connection connection = null;
    public Statement  statement  = null; // Sorgular statement ile çağırılacak.

    public DbConnection() {
        // 1) JDBC Driver’ı yükle
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            Logger.getLogger(DbConnection.class.getName())
                  .log(Level.SEVERE, "JDBC Driver bulunamadı!", e);
        }

        // 2) Bağlantı URL’i oluştur
        String url = "jdbc:mysql://" 
    + HOST + ":" + PORT + "/" + VERITABANI_ISMI
    + "?useSSL=false"
    + "&allowPublicKeyRetrieval=true"
    + "&serverTimezone=UTC";

        // 3) Bağlan ve Statement oluştur
        try {
            connection = DriverManager.getConnection(url, USER, PASSWORD);
            statement  = connection.createStatement();
            System.out.println("Bağlantı Başarılı.");
        } catch (SQLException ex) {
            System.out.println("Bağlantı Başarısız.");
            Logger.getLogger(DbConnection.class.getName())
                  .log(Level.SEVERE, null, ex);
        } 
    } 
        public Connection getConnection() {
        return this.connection;
    }
}


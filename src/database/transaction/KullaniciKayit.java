package database.transaction;

import database.DbConnection;
import java.sql.SQLException;
import util.AESUtil;


public class KullaniciKayit {
    /**
    @param kullaniciAdi Girilen kullanıcı adı
    * @param sifre Girilen şifre
    * @return true: ekleme başarılı, false: hata
    */
    DbConnection db = new DbConnection();
    
    public boolean kayitYap(String kullaniciAdi, String sifre, String guvenlikSorusu, String guvenlikCevap)
    {
        try {
        String cipher = AESUtil.encrypt(sifre);
        // 2) Cipher’ı kullanarak INSERT sorgusunu hazırla
        String query = "INSERT INTO kullanicilar (kullanici_adi, sifre_hash, guvenlik_sorusu, guvenlik_cevap) VALUES ("
                         + "'" + kullaniciAdi + "', "
                         + "'" + cipher + "', "
                         + "'" + guvenlikSorusu + "', "
                         + "'" + guvenlikCevap + "')";
        
            int eklenenSatir = db.statement.executeUpdate(query);
            db.connection.close();
            
            return eklenenSatir == 1;
        } catch (SQLException ex) {
            ex.printStackTrace();
            try { 
                db.connection.close(); 
            } 
            catch (Exception ignore) {
                
            }
            return false;
        }
    }        
}

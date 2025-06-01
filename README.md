# 🔐 Şifre Oluşturucu ve Yöneticisi

Bu Java Swing tabanlı masaüstü uygulama, güvenli ve özelleştirilebilir şifreler oluşturmanıza, bunları kaydetmenize ve yönetmenize olanak tanır. Aynı zamanda kullanıcı kaydı, giriş ve şifre sıfırlama gibi temel hesap yönetimi özelliklerini de barındırır. Kullanımı kolay, sade ve işlevsel bir arayüze sahiptir.

---

## 🚀 Özellikler

- ✅ Güvenli kullanıcı girişi ve kayıt sistemi  
- 🔁 Şifre sıfırlama (güvenlik sorusu ile)  
- 🔐 Şifre oluşturucu (karakter türü ve uzunluk seçilebilir)  
- 💾 Şifreleri kaydetme ve görüntüleme  
- 🗂️ Kayıtlı şifreleri güncelleme veya silme  
- 👤 Hesap yönetimi (hesap bilgilerini güncelleme veya silme)  
- 📊 Şifre gücü göstergesi

---

## 🛠️ Kurulum

1. **NetBeans** veya **IntelliJ IDEA** gibi bir IDE kullanarak projeyi açın.  
2. `src` klasörü içinde `main` sınıfı (örneğin `Main.java`) ile uygulamayı başlatabilirsiniz.  
3. Java 8+ sürümü ile uyumludur.  
4. **MySQL veritabanı bağlantısı için aşağıdaki adımları izleyin:**

### 🔗 Veritabanı Bağlantısı ve Kurulumu

1. **MySQL'de veritabanını oluşturun:**

```sql
CREATE DATABASE IF NOT EXISTS `password_manager`
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;
USE `password_manager`;

CREATE TABLE `kullanicilar` (
  `kullanici_id`    INT          NOT NULL AUTO_INCREMENT,
  `kullanici_adi`   VARCHAR(50)  NOT NULL,
  `sifre_hash`      VARCHAR(255) NOT NULL,
  `guvenlik_sorusu` VARCHAR(75)  NULL,
  `guvenlik_cevap`  VARCHAR(75)  NULL,
  PRIMARY KEY (`kullanici_id`),
  UNIQUE KEY `uniq_kullanici_adi` (`kullanici_adi`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `uygulamalar` (
  `id`           INT           NOT NULL AUTO_INCREMENT,
  `ad`           VARCHAR(100)  NOT NULL,
  `kullanici_id` INT           NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_uygulamalar_kullanici_id` (`kullanici_id`),
  CONSTRAINT `fk_uygulamalar_kullanici`
    FOREIGN KEY (`kullanici_id`) REFERENCES `kullanicilar` (`kullanici_id`)
    ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `sifreler` (
  `sifre_id`      INT           NOT NULL AUTO_INCREMENT,
  `kullanici_id`  INT           NOT NULL,
  `uygulama_adi`  VARCHAR(100)  NOT NULL,
  `hesap_adi`     VARCHAR(100)  NOT NULL,
  `sifre_icerigi` TEXT          NOT NULL,
  `sifre_uzunluk` INT           NOT NULL DEFAULT 0,
  `kayit_tarihi`  DATETIME      DEFAULT NULL,
  PRIMARY KEY (`sifre_id`),
  KEY `idx_sifreler_kullanici_id` (`kullanici_id`),
  CONSTRAINT `fk_sifreler_kullanici`
    FOREIGN KEY (`kullanici_id`) REFERENCES `kullanicilar` (`kullanici_id`)
    ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

2. **Veritabanı bağlantısı için JDBC URL’si:**

```java
String url = "jdbc:mysql://localhost:3306/password_manager";
String user = "root";
String password = ""; // şifre varsa buraya yaz
```

3. **MySQL JDBC sürücüsünü projeye eklemeyi unutmayın** (`mysql-connector-java-x.x.xx.jar`).

---

## 📸 Ekran Görüntüleri ve Açıklamaları

### 1. Giriş Ekranı
![Giriş Ekranı](https://github.com/user-attachments/assets/f8e21097-ddcf-4404-9db1-c91eb0578cae)

Uygulamanın açılış ekranı. Kullanıcı adı ve şifre girilerek giriş yapılabilir. Şifre unutulduysa sıfırlama bağlantısı kullanılabilir. Yeni kullanıcılar “Kayıt Ol” ile hesap oluşturabilir.

---

### 2. Şifre Yenileme Ekranı
![Şifre Yenileme Ekranı](https://github.com/user-attachments/assets/4ddd7030-d8d2-4d4e-ae90-bbea2a74977e)


Kullanıcı adı, yeni şifre ve güvenlik sorusunun cevabı girilerek şifre sıfırlama işlemi yapılabilir. Bu ekran, hesap kurtarma için güvenli bir alan sağlar.

---

### 3. Kayıt Olma Ekranı
![Kayıt Ol Ekranı](https://github.com/user-attachments/assets/5ca6c79c-2d85-4a4a-95bc-f4a4478e0b26)


Yeni kullanıcıların hesap oluşturduğu ekran. Güvenlik sorusu ve cevabı, şifre sıfırlama işlemleri için kayıt esnasında belirlenir.

---

### 4. Şifre Oluşturucu Ana Ekranı
![Şifre Oluşturucu](https://github.com/user-attachments/assets/4667885e-a759-493f-8577-93b634dc4070)


Kullanıcı burada kendine özel şifreler oluşturabilir. Şifre uzunluğu ve karakter türleri seçilebilir. Şifreler kaydedilip ilgili uygulamalarla ilişkilendirilebilir.

---

### 5. Hesap Ayarları Ekranı
![Hesap Ayarları](https://github.com/user-attachments/assets/1a9d8609-bbe3-4cd4-8f2c-cc37ac8a53ed)


Mevcut kullanıcılar hesap bilgilerini buradan düzenleyebilir veya istenirse hesabını silebilir.

---

### 6. Şifre Yönetim Ekranı
![Şifre Yönetimi](https://github.com/user-attachments/assets/b927ca1f-1535-4c44-9906-c02fa7a90dd5)

Kayıtlı şifrelerin listelendiği ekran. Şifreler silinebilir, güncellenebilir veya belirli bir kayıt aranabilir. Tüm bilgiler tablo şeklinde düzenlenmiştir.

---

## 📄 Lisans

Bu proje sadece kişisel kullanım içindir. Ticari dağıtım veya başka projelerde kullanılması yasaktır.

package gui.ayarlar;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class IconAyarları {
    private static Icon originalIcon;  // İlk yüklenen ikon referansını saklar

    // JLabel üzerindeki ikonu değiştirir, önceki ikonu saklar
    public static void changeIcon(JLabel label, String fileName) {
        originalIcon = label.getIcon();  // Mevcut ikonu yedekle
        // Yeni ikonu resources klasöründen yükle ve ata
        label.setIcon(new ImageIcon(
            IconAyarları.class.getResource("/gui/iconlar/" + fileName + ".png")
        ));
    }

    // Daha önce saklanan orijinal ikonu geri yükler
    public static void setOriginalIcon(JLabel label) {
        label.setIcon(originalIcon);  // Yedeklenen ikonu etikete tekrar uygula
    }
}

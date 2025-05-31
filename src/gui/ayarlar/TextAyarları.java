package gui.ayarlar;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class TextAyarları {
    private static String originalText;  // Placeholder or önceki metni saklamak için

    // Odaklanıldığında placeholder’ı temizler
    public static void checkTheTextFocusGained(JTextField textfield, String org) {
        originalText = org;  // Gelen orijinal metni sakla
        if (textfield.getText().trim().equals(org)) {
            textfield.setText("");  // Placeholder ile aynıysa metni temizle
        }
    }

    // Odak kaybolduğunda boşsa placeholder’ı geri yazar
    public static void checkTheTextFocusLost(JTextField textfield) {
        if (textfield.getText().trim().isEmpty()) {
            textfield.setText(originalText);  // Saklanan metni geri koy
        }
    }

    // Sadece rakam girişine izin verir
    public static void setOnlyNumber(JTextField textfield) {
        textfield.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {  // Rakam değilse
                    e.consume();              // Girişi engelle
                }
            }
        });
    }

    // Sadece harf ve boşluk girişine izin verir
    public static void setOnlyAlphabetic(JTextField textfield) {
        textfield.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isAlphabetic(c) && !Character.isWhitespace(c)) {
                    e.consume();  // Harf/boşluk değilse engelle
                }
            }
        });
    }

    // Maksimum karakter sayısı sınırı uygular
    public static void setMaximumLimit(JTextField textfield, int lim) {
        textfield.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (str == null) return;
                if ((getLength() + str.length()) <= lim) {
                    super.insertString(offs, str, a);  // Sınırı aşmıyorsa ekle
                }
            }
        });
    }

    // Minimum karakter sayısı sınırı uygular (silme işlemlerinde)
    public static void setMinimumLimit(JTextField textfield, int lim) {
        textfield.setDocument(new PlainDocument() {
            @Override
            public void remove(int offs, int len) throws BadLocationException {
                if (getLength() - len >= lim) {
                    super.remove(offs, len);  // Min sınır korunuyorsa sil
                }
            }
        });
    }

    // Anahtar bırakıldığında metni sayıya çevirir ve üst sınırı aşarsa limitle
    public static int checkTheTextKeyReleased(JTextField textField, int sifreLimit) {
        String text = textField.getText();
        if (!text.isEmpty()) {
            int miktar = Integer.parseInt(text);
            if (miktar > sifreLimit) {
                miktar = sifreLimit;  
                textField.setText(String.valueOf(miktar));  // Limit aşılırsa düzelt
            }
            return miktar;
        }
        return 0;  // Boşsa sıfır döner
    }
}               
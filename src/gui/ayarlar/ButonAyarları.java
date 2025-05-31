
package gui.ayarlar;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JToggleButton;


public class ButonAyarları {
    
    private static Color OriginalbgColor;
    private static Color OriginalfgColor;
    
    
    public static void setBg(JButton button, Color bgColor, Color fgColor)
    {
        OriginalbgColor = button.getBackground();
        button.setBackground(bgColor);
        button.setForeground(fgColor);
    }
    
    public static void setOriginalBg(JButton button, Color fgColor)
    {
        button.setBackground(OriginalbgColor);
        button.setForeground(OriginalfgColor);
    }
    
    public static void setBgToggle(JToggleButton button, Color bgColor, Color fgColor)
    {
        OriginalbgColor = button.getBackground();
        button.setBackground(bgColor);
        button.setForeground(fgColor);
    }
    
    public static void setOriginalToggle(JToggleButton button, Color fgColor)
    {
        button.setBackground(OriginalbgColor);
        button.setForeground(OriginalfgColor);
    }
}

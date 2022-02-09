
package app7food.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CellTitle extends JPanel{
    
    public CellTitle(String title){
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(295,40));
        this.setLayout(null);
        JLabel t1 = new JLabel(title);
        t1.setFont(new Font("Open Sans, Helvetica Neue, Helvetica, Arial, sans-serif",Font.TRUETYPE_FONT,16));
        t1.setForeground(new Color(55,66,99));
        t1.setBounds(14,5,295,25);
        this.add(t1);
    }
}

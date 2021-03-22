/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app7food.view.table;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author emeri
 */
public class TableTitle extends TableLine {

    public TableTitle(JPanel[] titles, LineBorder border, int[] widthColumns) {
        super(titles, border, widthColumns);
        Color c = titles[0].getBackground();
        int[] rgb = {c.getRed() - 20, c.getGreen() - 20, c.getBlue() - 20};
        boolean neg = false;
        for (int i : rgb) {
            if (i < 0) {
                neg = true;
                break;
            }
        }
        if (neg) {
            for (int i = 0; i < 3; i++) {
                rgb[i] += 40;
            }
        }
        c = new Color(rgb[0], rgb[1], rgb[2]);
        for (JPanel title : titles) {
            title.setBackground(c);
            title.setBorder(border);
            this.add(title);
        }
    }
}

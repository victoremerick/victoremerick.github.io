/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app7food.view.table;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author emeri
 */
public class TableLine extends JPanel {
    
    protected final int minHeight = 30;
    protected int[] widthColumns;
    protected LineBorder border = new LineBorder(new Color(219, 225, 232));
    
    protected List<JPanel> cells;

    protected TableLine() {
    }

    public TableLine(JPanel[] cells, LineBorder border, int[] widthColumns) {
        this.setLayout(null);
        this.border = border;
        this.setBorder(border);
        this.cells = Arrays.asList(cells);
        int max = verifySize();
        this.widthColumns = widthColumns;
        if (max == 0) {
            int x = 0;
            for (int i = 0; i < cells.length; i++) {
                int width = this.widthColumns[i];
                int height = this.minHeight;
                cells[i].setBorder(border);
                cells[i].setBounds(x, 0, width, height);
                x += width;
            }
        } else {
            int x = 0;
            for (int i = 0; i < cells.length; i++) {
                int width = this.widthColumns[i];
                int height = max;
                cells[i].setBounds(x, 0, width, height);
                x += width;
            }
            
        }
        for (JPanel cell : this.cells) {
            cell.setBorder(border);
            this.add(cell);
        }
    }
    
    public TableLine(JPanel[] cells) {
        this.setLayout(null);
        this.setBorder(new LineBorder(new Color(219, 225, 232)));
        this.cells = Arrays.asList(cells);
        this.widthColumns = new int[cells.length];
        for (JPanel cell : this.cells) {
            cell.setBorder(border);
            this.add(cell);
        }
    }
    
    public List<JPanel> getCells(){
        return cells;
    }
    
    public int verifySize() {
        int max = 0;
        for (JPanel cell : cells) {
            if (cell.getPreferredSize().height > minHeight && max < cell.getPreferredSize().height) {
                max = cell.getPreferredSize().height;
            }
        }
        return max;
    }

}

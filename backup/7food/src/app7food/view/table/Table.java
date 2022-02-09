/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app7food.view.table;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author emeri
 */
public class Table extends JPanel {

    private int columns = 0;
    private TableTitle tbTitle;
    private List<TableLine> tbLines;
    private List<Integer> HeightLines;
    private LineBorder border;
    private int[] widthColumns;
    private final Dimension d;

    public Table(JPanel[] cells, LineBorder border, Dimension d) {
        this.d = d;
        tbLines = new ArrayList();
        HeightLines = new ArrayList();
        widthColumns = new int[cells.length];
        for (int i = 0; i < cells.length; i++) {
            widthColumns[i] = cells[i].getPreferredSize().width;
        }
        init(cells, border);
    }

    public Table(JPanel[] cells, LineBorder border, int[] widthColumns, Dimension d) {
        this.d = d;
        HeightLines = new ArrayList();
        tbLines = new ArrayList();
        this.widthColumns = widthColumns;
        init(cells, border);
    }

    private void init(JPanel[] titles, LineBorder border) {
        this.columns = titles.length;
        TableTitle tbtitle = new TableTitle(titles, border, widthColumns);
        HeightLines.add(tbtitle.verifySize());
        tbtitle.setBounds(0, 1, d.width, tbtitle.verifySize());
        this.add(tbtitle);
        this.setLayout(null);
        this.tbTitle = tbtitle;
        this.border = border;
    }

    public void addTableLine(JPanel[] cells) throws Exception {
        if (cells.length == columns) {
            TableLine line = new TableLine(cells, border, widthColumns);
            line.setBounds(0, getHeight(), d.width, line.verifySize());
            HeightLines.add(line.verifySize());
            tbLines.add(line);
            this.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
            this.add(line);
            this.setPreferredSize(new Dimension(d.width,this.getHeight()));
        } else {
            throw new Exception();
        }
    }

    public void addTableLine(TableLine line) {
        line.setBounds(0, getHeight(), d.width, line.verifySize());
        List<JPanel> cells = line.getCells();
        int max = line.verifySize();
        int h = 30;
        if (max > 0) {
            h = max;
        }
        int x = 0;
        for (int i = 0; i < cells.size(); i++) {
            int width = this.widthColumns[i];
            cells.get(i).setBounds(x, 0, width, h);
            x += width;
        }
        HeightLines.add(line.verifySize());
        tbLines.add(line);
        this.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        this.add(line);
    }

    public TableTitle getTableTitle() {
        return tbTitle;
    }

    public int getLinesTable() {
        return tbLines.size();
    }

    public int getColumnsTable() {
        return columns;
    }

    @Override
    public int getHeight() {
        int height = 0;
        for (Integer tbLine : HeightLines) {
            height += tbLine;
        }
        return height;
    }

    @Override
    public void removeAll() {
        super.removeAll();
        this.add(tbTitle);
    }
}

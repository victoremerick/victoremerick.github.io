/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app7food.view;

import app7food.font.glyphiconspro.GlyphIconsPro;
import app7food.models.Pedido;
import app7food.view.icones.GetIcon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author emeri
 */
public class CellGeral extends JPanel {

    private final JLabel pagi;
    private final JLabel ni;
    private final JLabel nota;
    private final JLabel d1;
    private final JLabel d1i;
    private final JLabel d2;
    private final JLabel d2i;
    private final JLabel hora;
    private final JLabel hi;

    public CellGeral(Pedido p) {
        this(p.getPayment(), p.getIDOrder(), p.getTotal(), p.getChangeFor(), p.getDateTime(), p.getStatus()[1]);
    }

    public CellGeral(String pag, String nota, String d1, String d2, String hora, Color c) {
        this.setOpaque(false);
        setPreferredSize(new Dimension(0, 50));
        this.setLayout(null);
        if (!pag.equals("null")) {
            ImageIcon ii = GetIcon.create(pag);
            if (ii != null) {
                pagi = new JLabel(ii);
            } else {
                pagi = new JLabel();
            }
        } else {
            pagi = new JLabel();
        }
        pagi.setBounds(5, 16, 37, 16);
        this.add(pagi);

        ni = new JLabel("\ue040");
        ni.setFont(GlyphIconsPro.getFont(13));
        ni.setBounds(47, 16, 16, 16);
        ni.setForeground(c);
        this.add(ni);

        this.nota = new JLabel(nota);
        this.nota.setFont(new Font("Open Sans, Helvetica Neue, Helvetica, Arial, sans-serif", Font.TRUETYPE_FONT, 13));
        this.nota.setBounds(65, 16, 40, 16);
        this.nota.setForeground(c);
        this.add(this.nota);

        d1i = new JLabel("\ue228");
        d1i.setFont(GlyphIconsPro.getFont(13));
        d1i.setBounds(110, 16, 16, 16);
        d1i.setForeground(c);
        this.add(d1i);

        this.d1 = new JLabel(d1);
        this.d1.setFont(new Font("Open Sans, Helvetica Neue, Helvetica, Arial, sans-serif", Font.TRUETYPE_FONT, 13));
        this.d1.setBounds(126, 17, 40, 16);
        this.d1.setForeground(c);
        this.add(this.d1);

        d2i = new JLabel("\ue038");
        d2i.setFont(GlyphIconsPro.getFont(13));
        d2i.setBounds(175, 16, 16, 16);
        d2i.setForeground(c);
        this.add(d2i);

        this.d2 = new JLabel(d2);
        this.d2.setFont(new Font("Open Sans, Helvetica Neue, Helvetica, Arial, sans-serif", Font.TRUETYPE_FONT, 13));
        this.d2.setBounds(192, 17, 40, 16);
        this.d2.setForeground(c);
        this.add(this.d2);

        hi = new JLabel("\ue054");
        hi.setFont(GlyphIconsPro.getFont(13));
        hi.setBounds(238, 16, 16, 16);
        hi.setForeground(c);
        this.add(hi);

        this.hora = new JLabel(hora);
        this.hora.setFont(new Font("Open Sans, Helvetica Neue, Helvetica, Arial, sans-serif", Font.TRUETYPE_FONT, 13));
        this.hora.setBounds(255, 17, 40, 16);
        this.hora.setForeground(c);
        this.add(this.hora);

        setOpaque(false);
    }
    
    public void atualizaStatus(Color c){
        this.d2.setForeground(c);
        this.hora.setForeground(c);
        this.d1.setForeground(c);
        this.nota.setForeground(c);
        hi.setForeground(c);
        d2i.setForeground(c);
        ni.setForeground(c);
        d1i.setForeground(c);
    }
}

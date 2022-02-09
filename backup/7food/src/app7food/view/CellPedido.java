package app7food.view;

import app7food.models.Pedido;
import app7food.models.Produto;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class CellPedido extends JPanel {

    public List<JLabel> pedidos;
    public int wc = 0;
    public int hc = 0;
    
    public CellPedido(Pedido p){
        this(p.getProducts(), p.getQuantity(), p.getStatus()[1]);
    }
    
    public CellPedido(List<Produto> prods, List<Integer> Quantities, Color c) {
        List<String> peds = new ArrayList();
        setPreferredSize(new Dimension(0,50));
        for (int i = 0; i<prods.size(); i++) {
            peds.add(Quantities.get(i)+" x "+prods.get(i).getName());
        }
        this.setOpaque(false);
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.pedidos = new ArrayList();
        wc = 5;
        for (int i = 0; i < peds.size(); i++) {
            JLabel j = new JLabel(peds.get(i), SwingConstants.CENTER);
            j.setBorder(new LineBorder(c, 2, true));
            j.setBackground(c);
            j.setForeground(Color.white);
            j.setOpaque(true);
            j.setFont(new Font("Open Sans, Helvetica Neue, Helvetica, Arial, sans-serif", Font.BOLD, 11));
            Dimension d = j.getPreferredSize();
            d.width += 6;
            hc = d.height;
            j.setPreferredSize(d);
            wc += 5 + d.width;
            this.add(j);
            this.pedidos.add(j);
        }
        int tmp = (int) Math.floor((wc / 340) + 1);
        hc = hc * tmp + 5 * tmp + 7;
        this.setPreferredSize(new Dimension(wc, hc));
    }
    
    public void atualizaStatus(Color c){
        for (JLabel pedido : pedidos) {
            pedido.setBackground(c);
            pedido.setBorder(new LineBorder(c, 2, true));
        }
    }
}

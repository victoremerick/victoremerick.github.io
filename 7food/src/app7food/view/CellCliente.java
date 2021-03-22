package app7food.view;

import app7food.font.glyphiconspro.GlyphIconsPro;
import app7food.models.Pedido;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CellCliente extends JPanel {

    private JLabel local;
    private JLabel localIcone;
    private JLabel cliente;
    private JLabel clienteIcone;

    public CellCliente(Pedido p) {
        int lu = 0;
        setPreferredSize(new Dimension(0, 50));
        String local = p.getCustomerAddress().getAddress2();
        String cliente = p.getName();
        Color c = p.getStatus()[1];
        this.setOpaque(false);
        this.setLayout(null);
        if (p.getTipoDelivery().equals("delivery")) {
            lu = 0xe059;
        } else if (p.getTipoDelivery().equals("selfservice")) {
            lu = 0xe243;
        } else if (p.getTipoDelivery().equals("pickup")) {
            lu = 0xe131;
        }
        String s = Character.toString((char) lu);
        this.localIcone = new JLabel(s);
        this.localIcone.setFont(GlyphIconsPro.getFont(13));
        this.localIcone.setBounds(8, 10, 16, 16);
        this.localIcone.setForeground(c);
        this.add(this.localIcone);

        this.local = new JLabel(local);
        this.local.setFont(new Font("Open Sans, Helvetica Neue, Helvetica, Arial, sans-serif", Font.TRUETYPE_FONT, 13));
        this.local.setBounds(28, 10, 176, 16);
        this.local.setForeground(c);
        this.add(this.local);

        this.clienteIcone = new JLabel("\ue004");
        this.clienteIcone.setFont(GlyphIconsPro.getFont(13));
        this.clienteIcone.setBounds(8, 27, 16, 16);
        this.clienteIcone.setForeground(c);
        this.add(this.clienteIcone);

        this.cliente = new JLabel(cliente);
        this.cliente.setFont(new Font("Open Sans, Helvetica Neue, Helvetica, Arial, sans-serif", Font.TRUETYPE_FONT, 13));
        this.cliente.setBounds(28, 27, 156, 16);
        this.cliente.setForeground(c);
        this.add(this.cliente);
    }
    
    public void atualizaStatus(Color c){
        this.clienteIcone.setForeground(c);
        this.cliente.setForeground(c);
        this.localIcone.setForeground(c);
        this.local.setForeground(c);
    }
}

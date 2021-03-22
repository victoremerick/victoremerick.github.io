package app7food.view;

import app7food.models.Pedido;
import app7food.view.table.TableLine;
import java.awt.Color;
import javax.swing.JPanel;

public class LinePedido extends TableLine {

    Pedido ped;
    CellGeral g;
    CellPedido p;
    CellCliente c;
    CellAcao a;
    
    public LinePedido(CellGeral g, CellPedido p, CellCliente c, CellAcao a, Color color, Pedido ped) {
        super(new JPanel[]{g, p, c, a});
        setBackground(color);
        this.ped = ped;
        this.g = g;
        this.p = p;
        this.c = c;
        this.a = a;
    }

    public void atualizaStatus(Pedido p) {
        ped = p;
        setBackground(p.getStatus()[0]);
        g.atualizaStatus(p.getStatus()[1]);
        this.p.atualizaStatus(p.getStatus()[1]);
        c.atualizaStatus(p.getStatus()[1]);
    }
}

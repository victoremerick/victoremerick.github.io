package app7food.view;

import app7food.font.fontawesome.FontAwesome;
import app7food.font.glyphiconspro.GlyphIconsPro;
import app7food.models.CustomerAddress;
import app7food.models.Pedido;
import app7food.printer.FlyingSaucer2PDF;
import com.lowagie.text.DocumentException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class CellAcao extends JPanel {

    private JPanel botaoVolta;
    private JPanel botaoAvanca;
    private JPanel botaoView;
    private Pedido p;

    public CellAcao(Pedido p) {
        this.p = p;
        setPreferredSize(new Dimension(0, 50));
        this.setOpaque(false);
        this.setLayout(null);

        this.botaoVolta = new JPanel();
        this.botaoVolta.setBounds(2, 10, 40, 30);
        this.botaoVolta.setBackground(new Color(0xef, 0x8a, 0x80));
        this.botaoVolta.setBorder(new LineBorder(Color.red));
        this.botaoVolta.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (StatusManager.retornaPedido(p, botaoVolta)) {
                    atualizaStatus();
                }
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                botaoVolta.setBackground(Color.red);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                botaoVolta.setBackground(new Color(0xef, 0x8a, 0x80));
            }
        });
        this.add(this.botaoVolta);

        this.botaoAvanca = new JPanel();
        this.botaoAvanca.setBounds(43, 10, 40, 30);
        this.botaoAvanca.setBackground(new Color(0xaa, 0xd1, 0x78));
        this.botaoAvanca.setBorder(new LineBorder(new Color(0x57, 0x80, 0x22)));
        this.botaoAvanca.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (StatusManager.avancaPedido(p)) {
                    atualizaStatus();
                }
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                botaoAvanca.setBackground(new Color(0x57, 0x80, 0x22));
            }

            @Override
            public void mouseExited(MouseEvent me) {
                botaoAvanca.setBackground(new Color(0xaa, 0xd1, 0x78));
            }
        });
        this.add(this.botaoAvanca);

        this.botaoView = new JPanel();
        this.botaoView.setBounds(84, 10, 40, 30);
        this.botaoView.setBackground(new Color(0x1b, 0xba, 0xe1));
        this.botaoView.setBorder(new LineBorder(new Color(0x15, 0x93, 0xb3)));
        this.botaoView.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                FlyingSaucer2PDF f = new FlyingSaucer2PDF();
                try {
                    f.printPdf();
                    f.print(botaoView);
                } catch (DocumentException | IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (PrintException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (PrinterException ex) {
                    Logger.getLogger(FlyingSaucer2PDF.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                botaoView.setBackground(new Color(0x15, 0x93, 0xb3));
            }

            @Override
            public void mouseExited(MouseEvent me) {
                botaoView.setBackground(new Color(0x1b, 0xba, 0xe1));
            }
        });
        this.add(this.botaoView);

        this.botaoVolta.setLayout(null);
        this.botaoAvanca.setLayout(null);
        this.botaoView.setLayout(null);

        JLabel volta = new JLabel();
        volta.setText("\ue211");
        volta.setHorizontalAlignment(SwingConstants.CENTER);
        volta.setBounds(0, 0, 40, 30);
        volta.setForeground(Color.white);
        volta.setFont(GlyphIconsPro.getFont(13));
        this.botaoVolta.add(volta);

        JLabel avanca = new JLabel();
        avanca.setText("\ue212");
        avanca.setHorizontalAlignment(SwingConstants.CENTER);
        avanca.setBounds(0, 0, 40, 30);
        avanca.setForeground(Color.white);
        avanca.setFont(GlyphIconsPro.getFont(13));
        this.botaoAvanca.add(avanca);

        JLabel view = new JLabel();
        view.setText("\uf02f");
        view.setHorizontalAlignment(SwingConstants.CENTER);
        view.setBounds(0, 0, 40, 30);
        view.setForeground(Color.white);
        view.setFont(FontAwesome.getFont(13));
        this.botaoView.add(view);

    }

    public void atualizaStatus() {
        LinePedido p = (LinePedido) this.getParent();
        p.atualizaStatus(this.p);
    }
}

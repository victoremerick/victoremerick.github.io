package app7food;

import app7food.Configs;
import app7food.font.fontawesome.FontAwesome;
import app7food.font.glyphiconspro.GlyphIconsPro;
import app7food.models.Pedido;
import app7food.threads.Atualizar;
import app7food.view.CellTitle;
import app7food.view.ajuda.Ajuda;
import app7food.view.icones.GetIcon;
import app7food.view.table.Table;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class PainelDeControle extends JFrame {

    public PainelDeControle() {
        super("7Foods");
        init();
    }

    TrayIcon trayIcon;
    SystemTray tray;

    private JPanel panel;
    private JPanel topPanel;
    private JPanel topPanelPend;
    private JPanel topPanelAtras;
    private JLabel topPedPendLabel;
    private JLabel topPedAtrasLabel;
    private JLabel topPedPendNLabel;
    private JLabel topPedAtrasNLabel;
    private JPanel midPanel;
    private JPanel pedPanel;
    private JPanel pedTopPanel;
//    private JPanel pedBusca;
    private JLabel pedLabel;
//    private JLabel pedBuscaLabel;
//    private JTextField tfBusca;
    private final Ajuda help = new Ajuda(null, false);
    private final Configs config = new Configs(this, false);
    Atualizar atualizar;

    public void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setIconImage(GetIcon.create("programa").getImage());
        help.setIconImage(GetIcon.create("programa").getImage());
        setSystemTray();

        panel = new JPanel();
        panel.setBounds(0, 0, 1000, 600);
        panel.setLayout(null);
        panel.setBackground(new Color(234, 237, 241));
        this.add(panel);

        topPanel = new JPanel();
        topPanel.setBounds(0, 0, 1000, 50);
        topPanel.setLayout(null);
        topPanel.setBackground(new Color(249, 250, 252));
        panel.add(topPanel);
        
        JPanel pConfigs = new JPanel();
        pConfigs.setLayout(null);
        pConfigs.setBackground(Color.decode("#1e6798"));
        pConfigs.setBounds(5,5,150,40);
        pConfigs.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (!config.isVisible()) {
                    config.setVisible(true);
                } else {
                    config.setVisible(false);
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
                pConfigs.setBackground(new Color(52, 152, 219));
            }

            @Override
            public void mouseExited(MouseEvent me) {
                pConfigs.setBackground(Color.decode("#1e6798"));
            }
        });
        topPanel.add(pConfigs);
        
        JLabel pcIcone = new JLabel("\uf013");
        pcIcone.setFont(FontAwesome.getFont(17));
        pcIcone.setForeground(Color.white);
        pcIcone.setBounds(8, 8, 23, 23);
        pConfigs.add(pcIcone);
        
        JLabel lconfigs = new JLabel("Configurações", SwingConstants.CENTER);
        lconfigs.setFont(new Font("Open Sans, Helvetica Neue, Helvetica, Arial, sans-serif", Font.TRUETYPE_FONT, 14));
        lconfigs.setForeground(Color.white);
        lconfigs.setBounds(35, 8, 100, 23);
        pConfigs.add(lconfigs);

        topPedPendLabel = new JLabel("<html><strong>Pedidos Pendentes</strong></html>");
        topPedPendLabel.setFont(new Font("Open Sans, Helvetica Neue, Helvetica, Arial, sans-serif", Font.TRUETYPE_FONT, 14));
        topPedPendLabel.setBounds(640, 13, 200, 25);
        topPanel.add(topPedPendLabel);

        topPanelPend = new JPanel();
        topPanelPend.setLayout(null);
        topPanelPend.setBounds(795, 13, 20, 25);
        topPanelPend.setBackground(new Color(230, 126, 34));
        topPanel.add(topPanelPend);

        topPedPendNLabel = new JLabel("<html><strong>0</strong></html>", SwingConstants.CENTER);
        topPedPendNLabel.setFont(new Font("Open Sans, Helvetica Neue, Helvetica, Arial, sans-serif", Font.TRUETYPE_FONT, 14));
        topPedPendNLabel.setForeground(new Color(255, 255, 255));
        topPedPendNLabel.setBounds(0, 4, 21, 15);
        topPanelPend.add(topPedPendNLabel);

        topPedAtrasLabel = new JLabel("<html><strong>Pedidos Atrasados</strong></html>");
        topPedAtrasLabel.setFont(new Font("Open Sans, Helvetica Neue, Helvetica, Arial, sans-serif", Font.TRUETYPE_FONT, 14));
        topPedAtrasLabel.setBounds(825, 13, 200, 25);
        topPanel.add(topPedAtrasLabel);

        topPanelAtras = new JPanel();
        topPanelAtras.setLayout(null);
        topPanelAtras.setBounds(975, 13, 20, 25);
        topPanelAtras.setBackground(new Color(231, 76, 60));
        topPanel.add(topPanelAtras);

        topPedAtrasNLabel = new JLabel("<html><strong>0</strong></html>", SwingConstants.CENTER);
        topPedAtrasNLabel.setFont(new Font("Open Sans, Helvetica Neue, Helvetica, Arial, sans-serif", Font.TRUETYPE_FONT, 14));
        topPedAtrasNLabel.setForeground(new Color(255, 255, 255));
        topPedAtrasNLabel.setBounds(0, 4, 21, 15);
        topPanelAtras.add(topPedAtrasNLabel);

        midPanel = new JPanel();
        midPanel.setBounds(0, 51, 1000, 46);
        midPanel.setLayout(null);
        midPanel.setBackground(new Color(255, 255, 255));
        panel.add(midPanel);

        JLabel pedidosBotao = new JLabel("Pedidos", SwingConstants.CENTER);
        pedidosBotao.setOpaque(true);
        pedidosBotao.setFont(new Font("Open Sans, Helvetica Neue, Helvetica, Arial, sans-serif", Font.BOLD, 14));
        pedidosBotao.setBounds(3, 3, 995, 40);
        pedidosBotao.setForeground(Color.white);
        pedidosBotao.setBackground(new Color(27, 186, 225));
        pedidosBotao.setLayout(new FlowLayout(FlowLayout.CENTER));
        midPanel.add(pedidosBotao);

        //Tabela de Pedidos
        LineBorder border = new LineBorder(new Color(219, 225, 232));

        
        pedPanel = new JPanel();
        pedPanel.setBounds(10, 100, 980, 500);
        pedPanel.setLayout(null);
        pedPanel.setBackground(new Color(249, 250, 252));
        pedPanel.setBorder(border);
        panel.add(pedPanel);
/*
       **** Painel de Busca não funcional ****

        pedBusca = new JPanel();
        pedBusca.setBounds(795, 47, 176, 25);
        pedBusca.setBackground(Color.white);
        pedBusca.setBorder(border);
        pedBusca.setLayout(null);
        pedPanel.add(pedBusca);

        tfBusca = new JTextField("Busca");
        tfBusca.setBounds(6, 1, 140, 23);
        tfBusca.setBorder(null);
        tfBusca.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                if (!tfBusca.getText().isEmpty() && tfBusca.getText().equals("Busca")) {
                    tfBusca.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent fe) {
                if (tfBusca.getText().isEmpty()) {
                    tfBusca.setText("Busca");
                }
            }
        });
        pedBusca.add(tfBusca);

        pedBuscaLabel = new JLabel("\uf002", SwingConstants.CENTER);
        Font fb = FontAwesome.getFont(14f);
        pedBuscaLabel.setFont(fb);
        pedBuscaLabel.setBounds(151, 0, 25, 25);
        pedBuscaLabel.setBorder(border);
        pedBusca.add(pedBuscaLabel);
*/

        pedTopPanel = new JPanel();
        pedTopPanel.setBounds(0, 0, 980, 40);
        pedTopPanel.setLayout(null);
        pedTopPanel.setBackground(new Color(249, 250, 252));
        pedTopPanel.setBorder(border);
        pedPanel.add(pedTopPanel);

        pedLabel = new JLabel("<html><body>Painel de <strong>Pedidos</strong></body></html>");
        pedLabel.setFont(new Font("Open Sans, Helvetica Neue, Helvetica, Arial, sans-serif", Font.TRUETYPE_FONT, 16));
        pedLabel.setBounds(8, 6, 150, 25);
        pedLabel.setForeground(new Color(55, 66, 99));
        pedTopPanel.add(pedLabel);

        JPanel ajudaPanel = new JPanel();
        ajudaPanel.setBounds(895, 8, 75, 25);
        ajudaPanel.setLayout(null);
        ajudaPanel.setBackground(new Color(122, 188, 231));
        ajudaPanel.setBorder(new LineBorder(new Color(52, 152, 219)));
        ajudaPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (!help.isVisible()) {
                    help.setVisible(true);
                } else {
                    help.setVisible(false);
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
                ajudaPanel.setBackground(new Color(52, 152, 219));
            }

            @Override
            public void mouseExited(MouseEvent me) {
                ajudaPanel.setBackground(new Color(122, 188, 231));
            }
        });
        pedTopPanel.add(ajudaPanel);

        JLabel ajIcone = new JLabel("\ue196");
        ajIcone.setFont(GlyphIconsPro.getFont(13));
        ajIcone.setForeground(Color.white);
        ajIcone.setBounds(7, 1, 23, 23);
        ajudaPanel.add(ajIcone);

        JLabel ajudaTxt = new JLabel("Ajuda");
        ajudaTxt.setFont(new Font("Open Sans, Helvetica Neue, Helvetica, Arial, sans-serif", Font.BOLD, 13));
        ajudaTxt.setForeground(Color.white);
        ajudaTxt.setBounds(28, 1, 73, 23);
        ajudaPanel.add(ajudaTxt);

        JPanel title1 = new CellTitle("<html><body><strong>Geral</strong></body></html>");
        JPanel title2 = new CellTitle("<html><body><strong>Pedido</strong></body></html>");
        JPanel title3 = new CellTitle("<html><body><strong>Cliente</strong></body></html>");
        JPanel title4 = new CellTitle("<html><body><strong>Ação</strong></body></html>");

        Table t = new Table(new JPanel[]{title1, title2, title3, title4}, border, new int[]{300, 320, 200, 125}, new Dimension(945, 380));
        t.setBounds(8, 30, 965, 480);
        t.setAutoscrolls(true);
        JScrollPane scrollPane = new JScrollPane(t);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(8, 80, 965, 410);
        scrollPane.setBorder(border);
        pedPanel.add(scrollPane);
        atualizar = new Atualizar(this, t, topPedPendNLabel, topPedAtrasNLabel);
        atualizar.setDaemon(true);
        atualizar.start();

        this.setResizable(false);
        this.setLayout(null);
        this.setSize(1007, 640);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle tela = ge.getMaximumWindowBounds();
        Dimension janela = this.getSize();
        int sheight = (tela.height/2) - (janela.height/2);
        int swidth = (tela.width/2) - janela.width/2;
        this.setLocation(swidth, sheight);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Configs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Configs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Configs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Configs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        
        Config.load();
        
        new PainelDeControle().setVisible(true);
    }

    private void setSystemTray() {
        if (SystemTray.isSupported()) {
            tray = SystemTray.getSystemTray();

            Image image = GetIcon.create("programa").getImage();
            ActionListener exitListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            };
            PopupMenu popup = new PopupMenu();
            MenuItem defaultItem = new MenuItem("Abrir Aplicativo");
            defaultItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setVisible(true);
                    setExtendedState(JFrame.NORMAL);
                }
            });
            popup.add(defaultItem);
            defaultItem = new MenuItem("Sair");
            defaultItem.addActionListener(exitListener);
            popup.add(defaultItem);
            trayIcon = new TrayIcon(image, "Administrador - 7Foods", popup);
            trayIcon.setImageAutoSize(true);
            addWindowStateListener(new WindowStateListener() {
                public void windowStateChanged(WindowEvent e) {
                    if (e.getNewState() == ICONIFIED) {
                        try {
                            tray.add(trayIcon);
                            setVisible(false);
                        } catch (AWTException ex) {
                        }
                    }
                    if (e.getNewState() == NORMAL) {
                        tray.remove(trayIcon);
                        setVisible(true);
                    }
                }
            });
        }
    }
}

package app7food;

import app7food.font.fontawesome.FontAwesome;
import app7food.font.glyphiconspro.GlyphIconsPro;
import app7food.view.icones.GetIcon;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TestFontAwsome {

    public static void main(String[] args) {
        new TestFontAwsome();
    }

    private static String readUrl(String urlString) throws MalformedURLException, IOException{
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }

            return buffer.toString();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
    
    public TestFontAwsome() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
//                JOptionPane.showInputDialog(null, "Dê motivos para o pedido ser cancelado (Opcional)", "Cancelar Pedido", JOptionPane.WARNING_MESSAGE, GetIcon.create("programa"), null, null);
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                Font font = GlyphIconsPro.getFont(13f);
//                char t = 0x1f4a3;
                JLabel label = new JLabel();
//                label.setContentType("text/html");
                try {
                    label = new JLabel("<html>"+readUrl("http://www.prod.7foods.com.br/shopkeeper/impressao/pedido/542")+"</html>");
                } catch (IOException ex) {
                    Logger.getLogger(TestFontAwsome.class.getName()).log(Level.SEVERE, null, ex);
                }
//                label.setFont(font);
//                JLabel[] ls = new JLabel[10];
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new FlowLayout());
//                Random r = new Random(99999);
//                for (JLabel l : ls) {
//                    l = new JLabel(r.nextInt() + "\n");
                    frame.add(label);
//                }
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setPreferredSize(new Dimension(100, 100));
                frame.setSize(100, 100);
                frame.setVisible(true);
//                } catch (IOException | FontFormatException exp) {
//                    exp.printStackTrace();
//                }
            }
        });
    }

}

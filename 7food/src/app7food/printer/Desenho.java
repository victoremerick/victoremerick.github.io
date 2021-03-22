package app7food.printer;

import com.lowagie.text.DocumentException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xhtmlrenderer.pdf.ITextRenderer;

public class Desenho implements Printable {
    // Deve implementar Printable para que seja um objeto imprimivel

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
            throws PrinterException {
        if (pageIndex > 0) {
            return Printable.NO_SUCH_PAGE;
        } else {
            // Renderiza um quadrado
            Graphics2D g2d = (Graphics2D) graphics;
            int x = 90;
            int y = 90;
            g2d.draw(new Rectangle2D.Double(x, y, 500, 500));

            // Mostra que imprimiu o objeto
            return Printable.PAGE_EXISTS;
        }
    }

    public static void main(String[] args) {
        OutputStream os = null;

        try {
//            writeFile(FlyingSaucer2PDF.readUrl("http://www.prod.7foods.com.br/shopkeeper/impressao/pedido/256?ws=true"));
            String File_To_Convert = "file.html";
            String url = new File(File_To_Convert).toURI().toURL().toString();
            System.out.println("" + url);
            String HTML_TO_PDF = "ConvertedFile.pdf";
            os = new FileOutputStream(HTML_TO_PDF);
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocument(url);
            renderer.layout();
            renderer.createPDF(os);
            os.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Desenho.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(Desenho.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Desenho.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                os.close();
            } catch (IOException ex) {
                Logger.getLogger(Desenho.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void writeFile(String g) throws IOException {
        File f = new File("file.html");
        if (!f.exists()) {
            f.createNewFile();
        }
        FileWriter fw = new FileWriter(f);
        fw.write(g);
        fw.close();
    }
}

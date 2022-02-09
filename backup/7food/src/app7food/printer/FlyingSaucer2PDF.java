package app7food.printer;

import com.lowagie.text.DocumentException;
import java.awt.Component;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.print.PrintException;
import org.xhtmlrenderer.pdf.ITextRenderer;

public class FlyingSaucer2PDF {

    public static String HTML = "temp.html";
    public static final String PDF = "temp.pdf";

    public void printPdf() throws DocumentException, IOException {
        String url = new File(HTML).toURI().toURL().toString();
        OutputStream os = new FileOutputStream(PDF);

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocument(url);
        renderer.layout();
        renderer.createPDF(os);

        os.close();
    }

    public void print(Component c) throws FileNotFoundException, PrintException, IOException, PrinterException {
        // get the printer service by printer name
        File file = new File(PDF);
        FileInputStream fis = new FileInputStream(file);
        PrintPdf printPDFFile = new PrintPdf(fis, PDF);
        printPDFFile.print(c);
    }

    public static String readUrl(String urlString) throws MalformedURLException, IOException {
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
}

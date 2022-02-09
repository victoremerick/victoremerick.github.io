/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app7food;

import java.io.*;
import java.util.List;
import javax.swing.*;
import org.apache.pdfbox.pdfviewer.PDFPagePanel;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class Main {

    public static void setup() throws IOException {

        File PDF_Path = new File("Tested.pdf");
        PDDocument inputPDF = PDDocument.load(PDF_Path);
        List<PDPage> allPages = inputPDF.getDocumentCatalog().getAllPages();
        PDPage testPage = (PDPage)allPages.get(0);
        JFrame testFrame = new JFrame();
        PDFPagePanel pdfPanel = new PDFPagePanel();
        pdfPanel.setPage(testPage);
        testFrame.add(pdfPanel);
        testFrame.setBounds(40, 40, pdfPanel.getWidth(), pdfPanel.getHeight());
        testFrame.setVisible(true);
        testFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main.setup();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}

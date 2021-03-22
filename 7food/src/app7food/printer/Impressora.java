package app7food.printer;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class Impressora {

// Classe main para testar o exemplo
    public static void main(String[] args) {
        Impressora imp = new Impressora();
        imp.imprimir();
    }

    public void imprimir() {
        PrinterJob impressor = PrinterJob.getPrinterJob();
        // Informo ao impressor o objeto que quero imprimir
        impressor.setPrintable(new Desenho());
        try {
            // Manda imprimir diretamente na impressora padrão
            impressor.print();
            // Abre a caixa de dialogo de impressão
            // impressor.printDialog();
        } catch (PrinterException e) {
            e.printStackTrace();
        }
    }

}
